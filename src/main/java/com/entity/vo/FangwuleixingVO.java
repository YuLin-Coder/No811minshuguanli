package com.entity.vo;

import com.entity.FangwuleixingEntity;
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
@TableName("fangwuleixing")
public class FangwuleixingVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 民宿类型 Search
     */

    @TableField(value = "fwname")
    private String fwname;


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
	 * 设置：民宿类型 Search
	 */
    public String getFwname() {
        return fwname;
    }


    /**
	 * 获取：民宿类型 Search
	 */

    public void setFwname(String fwname) {
        this.fwname = fwname;
    }

}
