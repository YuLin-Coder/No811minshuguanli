package com.entity.view;

import com.entity.FangdongxinxiEntity;

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
@TableName("fangdongxinxi")
public class FangdongxinxiView extends FangdongxinxiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	public FangdongxinxiView() {

	}

	public FangdongxinxiView(FangdongxinxiEntity fangdongxinxiEntity) {
		try {
			BeanUtils.copyProperties(this, fangdongxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
