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

import com.entity.DiscussfangwuxinxiEntity;

import com.service.DiscussfangwuxinxiService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 民宿信息评论表
 * 后端接口
 * @author
 * @email
 * @date 2021-02-18
*/
@RestController
@Controller
@RequestMapping("/discussfangwuxinxi")
public class DiscussfangwuxinxiController {
    private static final Logger logger = LoggerFactory.getLogger(DiscussfangwuxinxiController.class);

    @Autowired
    private DiscussfangwuxinxiService discussfangwuxinxiService;

    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        logger.debug("Controller:"+this.getClass().getName()+",page方法");
        PageUtils page = discussfangwuxinxiService.queryPage(params);
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        DiscussfangwuxinxiEntity discussfangwuxinxi = discussfangwuxinxiService.selectById(id);
        if(discussfangwuxinxi!=null){
            return R.ok().put("data", discussfangwuxinxi);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussfangwuxinxiEntity discussfangwuxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<DiscussfangwuxinxiEntity> queryWrapper = new EntityWrapper<DiscussfangwuxinxiEntity>()
            .eq("refid", discussfangwuxinxi.getRefid())
            .eq("userid", discussfangwuxinxi.getUserid())
            .eq("replycontent", discussfangwuxinxi.getReplycontent())
            .eq("reply", discussfangwuxinxi.getReply())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DiscussfangwuxinxiEntity discussfangwuxinxiEntity = discussfangwuxinxiService.selectOne(queryWrapper);
        if(discussfangwuxinxiEntity==null){
            discussfangwuxinxi.setAddtime(new Date());
            discussfangwuxinxiService.insert(discussfangwuxinxi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DiscussfangwuxinxiEntity discussfangwuxinxi, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<DiscussfangwuxinxiEntity> queryWrapper = new EntityWrapper<DiscussfangwuxinxiEntity>()
            .notIn("id",discussfangwuxinxi.getId())
            .eq("refid", discussfangwuxinxi.getRefid())
            .eq("userid", discussfangwuxinxi.getUserid())
            .eq("replycontent", discussfangwuxinxi.getReplycontent())
            .eq("reply", discussfangwuxinxi.getReply())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DiscussfangwuxinxiEntity discussfangwuxinxiEntity = discussfangwuxinxiService.selectOne(queryWrapper);
        if(discussfangwuxinxiEntity==null){
            discussfangwuxinxiService.updateById(discussfangwuxinxi);//根据id更新
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
        discussfangwuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

