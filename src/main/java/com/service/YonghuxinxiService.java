package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YonghuxinxiEntity;
import java.util.Map;

/**
 *  服务类
 * @author 
 * @since 2021-02-05
 */
public interface YonghuxinxiService extends IService<YonghuxinxiEntity> {

     PageUtils queryPage(Map<String, Object> params);

}