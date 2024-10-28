package com.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.entity.ChuzhuxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ChuzhuxinxiView;

/**
 *  Dao 接口
 *
 * @author 
 * @since 2021-02-05
 */
public interface ChuzhuxinxiDao extends BaseMapper<ChuzhuxinxiEntity> {

   List<ChuzhuxinxiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

    List<ChuzhuxinxiView> selectPageView(Page<ChuzhuxinxiView> page,@Param("params") Map<String, Object> params);
}
