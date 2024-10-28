package com.dao;

import com.entity.FangwuxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.FangwuxinxiView;

/**
 *  Dao 接口
 *
 * @author 
 * @since 2021-02-05
 */
public interface FangwuxinxiDao extends BaseMapper<FangwuxinxiEntity> {

   List<FangwuxinxiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
