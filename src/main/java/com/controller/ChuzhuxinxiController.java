package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.entity.FangwuxinxiEntity;
import com.service.FangwuxinxiService;
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

import com.entity.ChuzhuxinxiEntity;

import com.service.ChuzhuxinxiService;
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
@RequestMapping("/chuzhuxinxi")
public class ChuzhuxinxiController {
    private static final Logger logger = LoggerFactory.getLogger(ChuzhuxinxiController.class);

    @Autowired
    private ChuzhuxinxiService chuzhuxinxiService;

    @Autowired
    private FangwuxinxiService fangwuxinxiService;



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
            page = chuzhuxinxiService.queryPage(params);
        }else{
            page = chuzhuxinxiService.queryPage(params);
        }
        if(role.equals("房东")){
            params.put("fd",request.getSession().getAttribute("userId"));
            page = chuzhuxinxiService.queryPage(params);
        }else{
            page = chuzhuxinxiService.queryPage(params);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端列表
    */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",page方法");
        PageUtils page = chuzhuxinxiService.queryList(params);
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        ChuzhuxinxiEntity chuzhuxinxi = chuzhuxinxiService.selectById(id);
        if(chuzhuxinxi!=null){
            return R.ok().put("data", chuzhuxinxi);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ChuzhuxinxiEntity chuzhuxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<ChuzhuxinxiEntity> queryWrapper = new EntityWrapper<ChuzhuxinxiEntity>()
            .eq("fw_types", chuzhuxinxi.getFwTypes())
            .eq("fd_types", chuzhuxinxi.getFdTypes())
            .eq("yh_types", chuzhuxinxi.getYhTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChuzhuxinxiEntity chuzhuxinxiEntity = chuzhuxinxiService.selectOne(queryWrapper);
        if(chuzhuxinxiEntity==null){
            chuzhuxinxiService.insert(chuzhuxinxi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ChuzhuxinxiEntity chuzhuxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<ChuzhuxinxiEntity> queryWrapper = new EntityWrapper<ChuzhuxinxiEntity>()
            .notIn("id",chuzhuxinxi.getId())
            .eq("fw_types", chuzhuxinxi.getFwTypes())
            .eq("fd_types", chuzhuxinxi.getFdTypes())
            .eq("yh_types", chuzhuxinxi.getYhTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChuzhuxinxiEntity chuzhuxinxiEntity = chuzhuxinxiService.selectOne(queryWrapper);
        if(chuzhuxinxiEntity==null){
            chuzhuxinxiService.updateById(chuzhuxinxi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 续租
    */
    @RequestMapping("/relet")
    public R relet(Integer id,String leaseTime){
        try {
            if(leaseTime == null){
                return R.error("续租日期不能为空哦");
            }
            ChuzhuxinxiEntity chuzhuxinxi = chuzhuxinxiService.selectById(id);
            if(chuzhuxinxi == null){
                return R.error();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(chuzhuxinxi.getFinishTime().getTime() > sdf.parse(leaseTime).getTime()){
                return R.error("续租时间不能小于截至日期");
            }
            chuzhuxinxi.setFinishTime(sdf.parse(leaseTime));

            chuzhuxinxiService.updateById(chuzhuxinxi);
            return R.ok();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return R.error();
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer ids){
        ChuzhuxinxiEntity chuzhuxinxi = chuzhuxinxiService.selectById(ids);
        if(chuzhuxinxi == null){
            return R.error();
        }
        FangwuxinxiEntity fangwuxinxi = fangwuxinxiService.selectById(chuzhuxinxi.getFwTypes());
        if (fangwuxinxi == null){
            return R.error();
        }
        fangwuxinxi.setFwstateTypes(2);
        fangwuxinxiService.updateById(fangwuxinxi);
        chuzhuxinxiService.deleteById(ids);
        return R.ok();
    }
}

