package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

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

import com.entity.HetongxinxiEntity;

import com.service.HetongxinxiService;
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
@RequestMapping("/hetongxinxi")
public class HetongxinxiController {
    private static final Logger logger = LoggerFactory.getLogger(HetongxinxiController.class);

    @Autowired
    private HetongxinxiService hetongxinxiService;

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
            page = hetongxinxiService.queryPage(params);
        }else{
            page = hetongxinxiService.queryPage(params);
        }
        if(role.equals("房东")){
            params.put("fd",request.getSession().getAttribute("userId"));
            page = hetongxinxiService.queryPage(params);
        }else{
            page = hetongxinxiService.queryPage(params);
        }
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        HetongxinxiEntity hetongxinxi = hetongxinxiService.selectById(id);
        if(hetongxinxi!=null){
            return R.ok().put("data", hetongxinxi);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody HetongxinxiEntity hetongxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<HetongxinxiEntity> queryWrapper = new EntityWrapper<HetongxinxiEntity>()
            .eq("htname", hetongxinxi.getHtname())
            .eq("fd_types", hetongxinxi.getFdTypes())
            .eq("yh_types", hetongxinxi.getYhTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        hetongxinxi.setCreateTime(new Date());
        HetongxinxiEntity hetongxinxiEntity = hetongxinxiService.selectOne(queryWrapper);
        if("".equals(hetongxinxi.getProveFile()) || "null".equals(hetongxinxi.getProveFile())){
            hetongxinxi.setProveFile(null);
        }
        if(hetongxinxiEntity==null){
            hetongxinxiService.insert(hetongxinxi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HetongxinxiEntity hetongxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<HetongxinxiEntity> queryWrapper = new EntityWrapper<HetongxinxiEntity>()
            .notIn("id",hetongxinxi.getId())
            .eq("htname", hetongxinxi.getHtname())
            .eq("fd_types", hetongxinxi.getFdTypes())
            .eq("yh_types", hetongxinxi.getYhTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HetongxinxiEntity hetongxinxiEntity = hetongxinxiService.selectOne(queryWrapper);
        if("".equals(hetongxinxi.getProveFile()) || "null".equals(hetongxinxi.getProveFile())){
                hetongxinxi.setProveFile(null);
        }
        if(hetongxinxiEntity==null){
            hetongxinxiService.updateById(hetongxinxi);//根据id更新
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
        hetongxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

