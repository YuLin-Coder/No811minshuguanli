package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.annotation.IgnoreAuth;
import com.entity.ChuzhuxinxiEntity;
import com.service.ChuzhuxinxiService;
import com.service.YonghuxinxiService;
import com.utils.MPUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.FangwuxinxiEntity;

import com.service.FangwuxinxiService;
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
@RequestMapping("/fangwuxinxi")
public class FangwuxinxiController {
    private static final Logger logger = LoggerFactory.getLogger(FangwuxinxiController.class);

    @Autowired
    private FangwuxinxiService fangwuxinxiService;


    @Autowired
    private ChuzhuxinxiService chuzhuxinxiService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",page方法");
        Object role = request.getSession().getAttribute("role");
        PageUtils page = null;
        if(role.equals("房东")){
            params.put("fd",request.getSession().getAttribute("userId"));
            page = fangwuxinxiService.queryPage(params);
        }else{
            page = fangwuxinxiService.queryPage(params);
        }
        return R.ok().put("data", page);
    }


    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        FangwuxinxiEntity fangwu = fangwuxinxiService.selectById(id);
        return R.ok().put("data", fangwu);
    }


    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        FangwuxinxiEntity fangwuxinxi = fangwuxinxiService.selectById(id);
        if(fangwuxinxi!=null){
            return R.ok().put("data", fangwuxinxi);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody FangwuxinxiEntity fangwuxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<FangwuxinxiEntity> queryWrapper = new EntityWrapper<FangwuxinxiEntity>()
            .eq("fwname", fangwuxinxi.getFwname())
            .eq("fwlx_types", fangwuxinxi.getFwlxTypes())
            .eq("fd_types", fangwuxinxi.getFdTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangwuxinxiEntity fangwuxinxiEntity = fangwuxinxiService.selectOne(queryWrapper);
        if("".equals(fangwuxinxi.getImgPhoto()) || "null".equals(fangwuxinxi.getImgPhoto())){
            fangwuxinxi.setImgPhoto(null);
        }
        if(fangwuxinxiEntity==null){
            fangwuxinxiService.insert(fangwuxinxi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangwuxinxiEntity fangwuxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<FangwuxinxiEntity> queryWrapper = new EntityWrapper<FangwuxinxiEntity>()
            .notIn("id",fangwuxinxi.getId())
            .eq("fwname", fangwuxinxi.getFwname())
            .eq("fwlx_types", fangwuxinxi.getFwlxTypes())
            .eq("fd_types", fangwuxinxi.getFdTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangwuxinxiEntity fangwuxinxiEntity = fangwuxinxiService.selectOne(queryWrapper);
        if("".equals(fangwuxinxi.getImgPhoto()) || "null".equals(fangwuxinxi.getImgPhoto())){
                fangwuxinxi.setImgPhoto(null);
        }
        if(fangwuxinxiEntity==null){
            fangwuxinxiService.updateById(fangwuxinxi);//根据id更新
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
        fangwuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 租房
    */
    @RequestMapping("/renting")
    public R renting(Integer id, String finishTime, HttpServletRequest request){
        if(finishTime == null){
            return R.error("租房截至日期不能为空");
        }
        Object role = request.getSession().getAttribute("role");
        if(role.equals("房东")){
            return R.error("房东是不可以租房子的哦");
        } else if(role.equals("管理员")){
            return R.error("管理员是不可以租房子的哦");
        }

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        FangwuxinxiEntity fangwuxinxi = fangwuxinxiService.selectById(id);
        if(fangwuxinxi != null){
            if(fangwuxinxi.getFwstateTypes() == 2 && fangwuxinxi.getFwstateTypes() != 1){
                try {
                    fangwuxinxi.setFwstateTypes(1);
                    ChuzhuxinxiEntity chuzhuxinxi = new ChuzhuxinxiEntity();
                    chuzhuxinxi.setFdTypes(fangwuxinxi.getFdTypes());
                    chuzhuxinxi.setFwTypes(fangwuxinxi.getId());
                    chuzhuxinxi.setYhTypes(userId);
                    chuzhuxinxi.setCreateTime(new Date());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    chuzhuxinxi.setFinishTime(sdf.parse(finishTime));
                    chuzhuxinxiService.insert(chuzhuxinxi);
                    fangwuxinxiService.updateById(fangwuxinxi);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return R.ok();
            }else {
                return R.error("这个房子已出租");
            }
        }
        return R.error("出现错误了哦");
    }

}

