package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.NewsEntity;
import java.util.Map;

/**
 * 新闻资讯 服务类
 * @author 
 * @since 2021-02-06
 */
public interface NewsService extends IService<NewsEntity> {

     PageUtils queryPage(Map<String, Object> params);

}