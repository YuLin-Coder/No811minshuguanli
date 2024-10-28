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
 * 
 *
 * @author 
 * @email
 * @date 2021-02-05
 */
@TableName("chuzhuxinxi")
public class ChuzhuxinxiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ChuzhuxinxiEntity() {

	}

	public ChuzhuxinxiEntity(T t) {
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
    private Integer id;


    /**
     * 民宿名称 Search
     */
    @TableField(value = "fw_types")
    private Integer fwTypes;


    /**
     * 房东名称 Search
     */
    @TableField(value = "fd_types")
    private Integer fdTypes;


    /**
     * 用户名称 Search
     */
    @TableField(value = "yh_types")
    private Integer yhTypes;


    /**
     * 租房时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 租房时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "finish_time")
    private Date finishTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：民宿名称 Search
	 */
    public Integer getFwTypes() {
        return fwTypes;
    }


    /**
	 * 获取：民宿名称 Search
	 */

    public void setFwTypes(Integer fwTypes) {
        this.fwTypes = fwTypes;
    }
    /**
	 * 设置：房东名称 Search
	 */
    public Integer getFdTypes() {
        return fdTypes;
    }


    /**
	 * 获取：房东名称 Search
	 */

    public void setFdTypes(Integer fdTypes) {
        this.fdTypes = fdTypes;
    }
    /**
	 * 设置：用户名称 Search
	 */
    public Integer getYhTypes() {
        return yhTypes;
    }


    /**
	 * 获取：用户名称 Search
	 */

    public void setYhTypes(Integer yhTypes) {
        this.yhTypes = yhTypes;
    }
    /**
	 * 设置：租房时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：租房时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
