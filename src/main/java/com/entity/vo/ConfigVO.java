package com.entity.vo;

import com.entity.ConfigEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 配置文件
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-02-05
 */
@TableName("config")
public class ConfigVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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

    @TableField(value = "value")
    private String value;


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
    public String getValue() {
        return value;
    }


    /**
	 * 获取：配置参数值
	 */

    public void setValue(String value) {
        this.value = value;
    }

}
