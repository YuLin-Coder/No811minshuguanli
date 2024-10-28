package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.annotation.IgnoreAuth;
import com.entity.UserEntity;
import com.entity.YonghuxinxiEntity;
import com.service.TokenService;
import com.utils.MPUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.YonghuxinxiEntity;

import com.service.YonghuxinxiService;
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
@RequestMapping("/yonghuxinxi")
public class YonghuxinxiController {
    private static final Logger logger = LoggerFactory.getLogger(YonghuxinxiController.class);

    @Autowired
    private YonghuxinxiService yonghuxinxiService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping(value = "/login")
    public R login(String username, String password, String role, HttpServletRequest request) {
        YonghuxinxiEntity user = yonghuxinxiService.selectOne(new EntityWrapper<YonghuxinxiEntity>().eq("account", username));
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
    public R register(@RequestBody YonghuxinxiEntity user){
        if(yonghuxinxiService.selectOne(new EntityWrapper<YonghuxinxiEntity>().eq("account", user.getAccount())) !=null) {
            return R.error("用户已存在");
        }
        user.setRole("用户");
        yonghuxinxiService.insert(user);
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
        YonghuxinxiEntity user = yonghuxinxiService.selectOne(new EntityWrapper<YonghuxinxiEntity>().eq("username", username));
        if(user==null) {
            return R.error("账号不存在");
        }
        user.setPassword("123456");
        yonghuxinxiService.update(user,null);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        YonghuxinxiEntity user = yonghuxinxiService.selectById(id);
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
        if(role.equals("用户")){
            params.put("yh",request.getSession().getAttribute("userId"));
            page = yonghuxinxiService.queryPage(params);
        }else{
            page = yonghuxinxiService.queryPage(params);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        YonghuxinxiEntity yonghuxinxi = yonghuxinxiService.selectById(id);
        if(yonghuxinxi!=null){
            return R.ok().put("data", yonghuxinxi);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @IgnoreAuth
    @RequestMapping("/save")
    public R save(@RequestBody YonghuxinxiEntity yonghuxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<YonghuxinxiEntity> queryWrapper = new EntityWrapper<YonghuxinxiEntity>()
            .eq("name", yonghuxinxi.getName())
            .eq("account", yonghuxinxi.getAccount())
            .eq("password", yonghuxinxi.getPassword())
            .eq("role", yonghuxinxi.getRole())
            ;
        yonghuxinxi.setRole("用户");
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuxinxiEntity yonghuxinxiEntity = yonghuxinxiService.selectOne(queryWrapper);
        if("".equals(yonghuxinxi.getImgPhoto()) || "null".equals(yonghuxinxi.getImgPhoto())){
            yonghuxinxi.setImgPhoto(null);
        }
        if(yonghuxinxiEntity==null){
            yonghuxinxiService.insert(yonghuxinxi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuxinxiEntity yonghuxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<YonghuxinxiEntity> queryWrapper = new EntityWrapper<YonghuxinxiEntity>()
            .notIn("id",yonghuxinxi.getId())
            .eq("name", yonghuxinxi.getName())
            .eq("account", yonghuxinxi.getAccount())
            .eq("password", yonghuxinxi.getPassword())
            .eq("role", yonghuxinxi.getRole())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuxinxiEntity yonghuxinxiEntity = yonghuxinxiService.selectOne(queryWrapper);
        if("".equals(yonghuxinxi.getImgPhoto()) || "null".equals(yonghuxinxi.getImgPhoto())){
                yonghuxinxi.setImgPhoto(null);
        }
        if(yonghuxinxiEntity==null){
            yonghuxinxiService.updateById(yonghuxinxi);//根据id更新
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
        yonghuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

