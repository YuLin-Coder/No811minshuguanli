package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.FangwuleixingEntity;
import java.util.Map;

/**
 *  服务类
 * @author 
 * @since 2021-02-05
 */
public interface FangwuleixingService extends IService<FangwuleixingEntity> {

     PageUtils queryPage(Map<String, Object> params);

}