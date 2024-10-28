package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.annotation.IgnoreAuth;
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

import com.entity.ConfigEntity;

import com.service.ConfigService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 轮播图
 * 后端接口
 * @author
 * @email
 * @date 2021-02-05
*/
@RestController
@Controller
@RequestMapping("/config")
public class ConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    /**
    * 后端列表
    */
    @IgnoreAuth
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        logger.debug("Controller:"+this.getClass().getName()+",page方法");
        PageUtils page = configService.queryPage(params);
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        ConfigEntity config = configService.selectById(id);
        if(config!=null){
            return R.ok().put("data", config);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ConfigEntity config, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<ConfigEntity> queryWrapper = new EntityWrapper<ConfigEntity>()
            .eq("name", config.getName())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ConfigEntity configEntity = configService.selectOne(queryWrapper);
        if("".equals(config.getIdcardPhoto()) || "null".equals(config.getIdcardPhoto())){
            config.setIdcardPhoto(null);
        }
        if(configEntity==null){
            configService.insert(config);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ConfigEntity config, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<ConfigEntity> queryWrapper = new EntityWrapper<ConfigEntity>()
            .notIn("id",config.getId())
            .eq("name", config.getName())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ConfigEntity configEntity = configService.selectOne(queryWrapper);
        if("".equals(config.getIdcardPhoto()) || "null".equals(config.getIdcardPhoto())){
                config.setIdcardPhoto(null);
        }
        if(configEntity==null){
            configService.updateById(config);//根据id更新
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
        configService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

