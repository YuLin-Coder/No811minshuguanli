package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DictionaryEntity;
import java.util.Map;

/**
 * 字典表 服务类
 * @author 
 * @since 2021-02-05
 */
public interface DictionaryService extends IService<DictionaryEntity> {

     PageUtils queryPage(Map<String, Object> params);

}