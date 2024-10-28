package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.ChuzhuxinxiDao;
import com.entity.ChuzhuxinxiEntity;
import com.service.ChuzhuxinxiService;
import com.entity.view.ChuzhuxinxiView;

/**
 *  服务实现类
 * @author 
 * @since 2021-02-05
 */
@Service("chuzhuxinxiService")
@Transactional
public class ChuzhuxinxiServiceImpl extends ServiceImpl<ChuzhuxinxiDao, ChuzhuxinxiEntity> implements ChuzhuxinxiService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<ChuzhuxinxiView> page =new Query<ChuzhuxinxiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryList(Map<String, Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<ChuzhuxinxiView> page =new Query<ChuzhuxinxiView>(params).getPage();
        page.setRecords(baseMapper.selectPageView(page,params));
        return new PageUtils(page);
    }

}
