package com.entity.vo;

import com.entity.ChuzhuxinxiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-02-05
 */
@TableName("chuzhuxinxi")
public class ChuzhuxinxiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


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

}
