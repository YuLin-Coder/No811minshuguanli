package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.FangwuleixingDao;
import com.entity.FangwuleixingEntity;
import com.service.FangwuleixingService;
import com.entity.view.FangwuleixingView;

/**
 *  服务实现类
 * @author 
 * @since 2021-02-05
 */
@Service("fangwuleixingService")
@Transactional
public class FangwuleixingServiceImpl extends ServiceImpl<FangwuleixingDao, FangwuleixingEntity> implements FangwuleixingService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<FangwuleixingView> page =new Query<FangwuleixingView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
