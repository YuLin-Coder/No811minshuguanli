package com.entity.view;

import com.entity.YonghuxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;

/**
 * 
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-02-05
 */
@TableName("yonghuxinxi")
public class YonghuxinxiView extends YonghuxinxiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	public YonghuxinxiView() {

	}

	public YonghuxinxiView(YonghuxinxiEntity yonghuxinxiEntity) {
		try {
			BeanUtils.copyProperties(this, yonghuxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
