package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.FangwuxinxiDao;
import com.entity.FangwuxinxiEntity;
import com.service.FangwuxinxiService;
import com.entity.view.FangwuxinxiView;

/**
 *  服务实现类
 * @author 
 * @since 2021-02-05
 */
@Service("fangwuxinxiService")
@Transactional
public class FangwuxinxiServiceImpl extends ServiceImpl<FangwuxinxiDao, FangwuxinxiEntity> implements FangwuxinxiService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<FangwuxinxiView> page =new Query<FangwuxinxiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }

}
