package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableField;
import com.entity.DiscussfangwuxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;

/**
 * 民宿信息评论表
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-02-18
 */
@TableName("discussfangwuxinxi")
public class DiscussfangwuxinxiView extends DiscussfangwuxinxiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	public DiscussfangwuxinxiView() {

	}

	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 头像
	 */
	private String imgPhoto;

	public DiscussfangwuxinxiView(DiscussfangwuxinxiEntity discussfangwuxinxiEntity) {
		try {
			BeanUtils.copyProperties(this, discussfangwuxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPhoto() {
		return imgPhoto;
	}

	public void setImgPhoto(String imgPhoto) {
		this.imgPhoto = imgPhoto;
	}
}
