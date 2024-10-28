package com.entity.view;

import com.entity.ChuzhuxinxiEntity;

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
@TableName("chuzhuxinxi")
public class ChuzhuxinxiView extends ChuzhuxinxiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	public ChuzhuxinxiView() {

	}
	private String fwname;
	private String fdname;
	private String yhname;

	public ChuzhuxinxiView(ChuzhuxinxiEntity chuzhuxinxiEntity) {
		try {
			BeanUtils.copyProperties(this, chuzhuxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFwname() {
		return fwname;
	}

	public void setFwname(String fwname) {
		this.fwname = fwname;
	}

	public String getFdname() {
		return fdname;
	}

	public void setFdname(String fdname) {
		this.fdname = fdname;
	}

	public String getYhname() {
		return yhname;
	}

	public void setYhname(String yhname) {
		this.yhname = yhname;
	}
}
