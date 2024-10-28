package com.dao;

import com.entity.DiscussfangwuxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussfangwuxinxiView;

/**
 * 民宿信息评论表 Dao 接口
 *
 * @author 
 * @since 2021-02-18
 */
public interface DiscussfangwuxinxiDao extends BaseMapper<DiscussfangwuxinxiEntity> {

   List<DiscussfangwuxinxiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
