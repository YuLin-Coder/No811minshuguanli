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

import com.entity.NewsEntity;

import com.service.NewsService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 新闻资讯
 * 后端接口
 * @author
 * @email
 * @date 2021-02-06
*/
@RestController
@Controller
@RequestMapping("/news")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        logger.debug("Controller:"+this.getClass().getName()+",page方法");
        PageUtils page = newsService.queryPage(params);
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("Controller:"+this.getClass().getName()+",info方法");
        NewsEntity news = newsService.selectById(id);
        if(news!=null){
            return R.ok().put("data", news);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody NewsEntity news, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",save");
        Wrapper<NewsEntity> queryWrapper = new EntityWrapper<NewsEntity>()
            .eq("title", news.getTitle())
            .eq("notice_content", news.getNoticeContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        NewsEntity newsEntity = newsService.selectOne(queryWrapper);
        if("".equals(news.getImgPhoto()) || "null".equals(news.getImgPhoto())){
            news.setImgPhoto(null);
        }
        if(newsEntity==null){
            newsService.insert(news);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody NewsEntity news, HttpServletRequest request){
        logger.debug("Controller:"+this.getClass().getName()+",update");
        //根据字段查询是否有相同数据
        Wrapper<NewsEntity> queryWrapper = new EntityWrapper<NewsEntity>()
            .notIn("id",news.getId())
            .eq("title", news.getTitle())
            .eq("notice_content", news.getNoticeContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        NewsEntity newsEntity = newsService.selectOne(queryWrapper);
        if("".equals(news.getImgPhoto()) || "null".equals(news.getImgPhoto())){
                news.setImgPhoto(null);
        }
        if(newsEntity==null){
            newsService.updateById(news);//根据id更新
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
        newsService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}

