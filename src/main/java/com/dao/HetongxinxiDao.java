package com.dao;

import com.entity.HetongxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HetongxinxiView;

/**
 *  Dao 接口
 *
 * @author 
 * @since 2021-02-05
 */
public interface HetongxinxiDao extends BaseMapper<HetongxinxiEntity> {

   List<HetongxinxiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
