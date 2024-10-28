package com.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.annotation.IgnoreAuth;
import com.entity.FangdongxinxiEntity;
import com.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.service.FangdongxinxiService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 
 * 后端接口
 * @author
 * @email
 * @date 2021-02-05
*/
@RestController
@Controller
@RequestMapping("/fangdongxinxi")
public class FangdongxinxiController {
    private static final Logger logger = LoggerFactory.getLogger(FangdongxinxiController.class);

    @Autowired
    private FangdongxinxiService fangdongxinxiService;


    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping(value = "/login")
    public R login(String username, String password, String role, HttpServletRequest request) {
        FangdongxinxiEntity user = fangdongxinxiService.selectOne(new EntityWrapper<FangdongxinxiEntity>().eq("account", username));
        if(user != null){
            if(!user.getRole().equals(role)){
                return R.error("权限不正常");
            }
            if(user==null || !user.getPassword().equals(password)) {
                return R.error("账号或密码不正确");
            }
            String token = tokenService.generateToken(user.getId(),user.getName(), "users", user.getRole());
            return R.ok().put("token", token);
        }else{
            return R.error("账号或密码或权限不对");
        }

    }

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody FangdongxinxiEntity user){
        if(fangdongxinxiService.selectOne(new EntityWrapper<FangdongxinxiEntity>().eq("account", user.getAccount())) !=null) {
            return R.error("用户已存在");
        }
        user.setRole("房东");
        fangdongxinxiService.insert(user);
        return R.ok();
    }

    /**
     * 退出
     */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
        FangdongxinxiEntity user = fangdongxinxiService.selectOne(new EntityWrapper<FangdongxinxiEntity>().eq("username", username));
        if(user==null) {
            return R.error("账号不存在");
        }
        user.setPassword("123456");
        fangdongxinxiService.update(user,null);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        FangdongxinxiEntity user = fangdongxinxiService.selectById(id);
        return R.ok().put("data", user);
    }

    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",page方法");
        Object role = request.getSession().getAttribute("role");
        PageUtils page = null;
        if(role.equals("房东")){
            params.put("yh",request.getSession().getAttribute("userId"));
            page = fangdongxinxiService.queryPage(params);
        }else{
            page = fangdongxinxiService.queryPage(params);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        FangdongxinxiEntity fangdongxinxi = fangdongxinxiService.selectById(id);
        if(fangdongxinxi!=null){
            return R.ok().put("data", fangdongxinxi);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @IgnoreAuth
    @RequestMapping("/save")
    public R save(@RequestBody FangdongxinxiEntity fangdongxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<FangdongxinxiEntity> queryWrapper = new EntityWrapper<FangdongxinxiEntity>()
            .eq("name", fangdongxinxi.getName())
            .eq("account", fangdongxinxi.getAccount());
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        fangdongxinxi.setRole("房东");
        FangdongxinxiEntity fangdongxinxiEntity = fangdongxinxiService.selectOne(queryWrapper);
        if("".equals(fangdongxinxi.getImgPhoto()) || "null".equals(fangdongxinxi.getImgPhoto())){
            fangdongxinxi.setImgPhoto(null);
        }
        if(fangdongxinxiEntity==null){
            fangdongxinxiService.insert(fangdongxinxi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangdongxinxiEntity fangdongxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<FangdongxinxiEntity> queryWrapper = new EntityWrapper<FangdongxinxiEntity>()
            .notIn("id",fangdongxinxi.getId())
            .eq("name", fangdongxinxi.getName())
            .eq("account", fangdongxinxi.getAccount());
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangdongxinxiEntity fangdongxinxiEntity = fangdongxinxiService.selectOne(queryWrapper);
        if("".equals(fangdongxinxi.getImgPhoto()) || "null".equals(fangdongxinxi.getImgPhoto())){
                fangdongxinxi.setImgPhoto(null);
        }
        if(fangdongxinxiEntity==null){
            fangdongxinxiService.updateById(fangdongxinxi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        logger.debug("Controller:"+this.getClass().getName()+",delete");
        fangdongxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

