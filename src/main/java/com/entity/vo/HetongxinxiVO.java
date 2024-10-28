package com.entity.vo;

import com.entity.HetongxinxiEntity;
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
@TableName("hetongxinxi")
public class HetongxinxiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 合同名称
     */

    @TableField(value = "htname")
    private String htname;


    /**
     * 附件信息
     */

    @TableField(value = "prove_file")
    private String proveFile;


    /**
     * 
     */

    @TableField(value = "fd_types")
    private Integer fdTypes;


    /**
     * 
     */

    @TableField(value = "yh_types")
    private Integer yhTypes;


    /**
     * 签约时间
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
	 * 设置：合同名称
	 */
    public String getHtname() {
        return htname;
    }


    /**
	 * 获取：合同名称
	 */

    public void setHtname(String htname) {
        this.htname = htname;
    }
    /**
	 * 设置：附件信息
	 */
    public String getProveFile() {
        return proveFile;
    }


    /**
	 * 获取：附件信息
	 */

    public void setProveFile(String proveFile) {
        this.proveFile = proveFile;
    }
    /**
	 * 设置：
	 */
    public Integer getFdTypes() {
        return fdTypes;
    }


    /**
	 * 获取：
	 */

    public void setFdTypes(Integer fdTypes) {
        this.fdTypes = fdTypes;
    }
    /**
	 * 设置：
	 */
    public Integer getYhTypes() {
        return yhTypes;
    }


    /**
	 * 获取：
	 */

    public void setYhTypes(Integer yhTypes) {
        this.yhTypes = yhTypes;
    }
    /**
	 * 设置：签约时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：签约时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
