package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChuzhuxinxiEntity;
import java.util.Map;

/**
 *  服务类
 * @author 
 * @since 2021-02-05
 */
public interface ChuzhuxinxiService extends IService<ChuzhuxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryList(Map<String, Object> params);
}