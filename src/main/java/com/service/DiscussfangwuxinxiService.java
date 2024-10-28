package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussfangwuxinxiEntity;
import java.util.Map;

/**
 * 民宿信息评论表 服务类
 * @author 
 * @since 2021-02-18
 */
public interface DiscussfangwuxinxiService extends IService<DiscussfangwuxinxiEntity> {

     PageUtils queryPage(Map<String, Object> params);

}