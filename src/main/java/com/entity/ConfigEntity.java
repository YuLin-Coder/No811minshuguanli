package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 轮播图
 *
 * @author 
 * @email
 * @date 2021-02-05
 */
@TableName("config")
public class ConfigEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ConfigEntity() {

	}

	public ConfigEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Long id;


    /**
     * 配置参数名称
     */
    @TableField(value = "name")
    private String name;


    /**
     * 配置参数值
     */
    @TableField(value = "idcard_photo")
    private String idcardPhoto;


    /**
	 * 设置：主键
	 */
    public Long getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Long id) {
        this.id = id;
    }
    /**
	 * 设置：配置参数名称
	 */
    public String getName() {
        return name;
    }


    /**
	 * 获取：配置参数名称
	 */

    public void setName(String name) {
        this.name = name;
    }
    /**
	 * 设置：配置参数值
	 */
    public String getIdcardPhoto() {
        return idcardPhoto;
    }


    /**
	 * 获取：配置参数值
	 */

    public void setIdcardPhoto(String idcardPhoto) {
        this.idcardPhoto = idcardPhoto;
    }

}
