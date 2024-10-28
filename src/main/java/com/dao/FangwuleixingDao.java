package com.dao;

import com.entity.FangwuleixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.FangwuleixingView;

/**
 *  Dao 接口
 *
 * @author 
 * @since 2021-02-05
 */
public interface FangwuleixingDao extends BaseMapper<FangwuleixingEntity> {

   List<FangwuleixingView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
