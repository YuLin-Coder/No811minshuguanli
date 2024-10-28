package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.HetongxinxiDao;
import com.entity.HetongxinxiEntity;
import com.service.HetongxinxiService;
import com.entity.view.HetongxinxiView;

/**
 *  服务实现类
 * @author 
 * @since 2021-02-05
 */
@Service("hetongxinxiService")
@Transactional
public class HetongxinxiServiceImpl extends ServiceImpl<HetongxinxiDao, HetongxinxiEntity> implements HetongxinxiService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<HetongxinxiView> page =new Query<HetongxinxiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
