package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.DiscussfangwuxinxiDao;
import com.entity.DiscussfangwuxinxiEntity;
import com.service.DiscussfangwuxinxiService;
import com.entity.view.DiscussfangwuxinxiView;

/**
 * 民宿信息评论表 服务实现类
 * @author 
 * @since 2021-02-18
 */
@Service("discussfangwuxinxiService")
@Transactional
public class DiscussfangwuxinxiServiceImpl extends ServiceImpl<DiscussfangwuxinxiDao, DiscussfangwuxinxiEntity> implements DiscussfangwuxinxiService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<DiscussfangwuxinxiView> page =new Query<DiscussfangwuxinxiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
