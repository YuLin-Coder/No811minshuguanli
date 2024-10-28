package com.entity.model;

import com.entity.ConfigEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 配置文件
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-02-05
 */
public class ConfigModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Long id;


    /**
     * 配置参数名称
     */
    private String name;


    /**
     * 配置参数值
     */
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
