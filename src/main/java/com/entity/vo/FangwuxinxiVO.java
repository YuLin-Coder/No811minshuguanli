package com.entity.vo;

import com.entity.FangwuxinxiEntity;
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
@TableName("fangwuxinxi")
public class FangwuxinxiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 民宿名称 Search
     */

    @TableField(value = "fwname")
    private String fwname;


    /**
     * 民宿类型 Search
     */

    @TableField(value = "fwlx_types")
    private Integer fwlxTypes;


    /**
     * 价格（天）
     */

    @TableField(value = "money")
    private Double money;


    /**
     * 民宿图片
     */

    @TableField(value = "img_photo")
    private String imgPhoto;


    /**
     * 地址
     */

    @TableField(value = "address")
    private String address;


    /**
     * 民宿状态 Search
     */

    @TableField(value = "fwstate_types")
    private Integer fwstateTypes;


    /**
     * 所属房东
     */

    @TableField(value = "fd_types")
    private Integer fdTypes;


    /**
     * 房东联系电话
     */

    @TableField(value = "phone")
    private String phone;


    /**
     * 具体信息
     */

    @TableField(value = "notice_content")
    private String noticeContent;


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
    public String getFwname() {
        return fwname;
    }


    /**
	 * 获取：民宿名称 Search
	 */

    public void setFwname(String fwname) {
        this.fwname = fwname;
    }
    /**
	 * 设置：民宿类型 Search
	 */
    public Integer getFwlxTypes() {
        return fwlxTypes;
    }


    /**
	 * 获取：民宿类型 Search
	 */

    public void setFwlxTypes(Integer fwlxTypes) {
        this.fwlxTypes = fwlxTypes;
    }
    /**
	 * 设置：价格（天）
	 */
    public Double getMoney() {
        return money;
    }


    /**
	 * 获取：价格（天）
	 */

    public void setMoney(Double money) {
        this.money = money;
    }
    /**
	 * 设置：民宿图片
	 */
    public String getImgPhoto() {
        return imgPhoto;
    }


    /**
	 * 获取：民宿图片
	 */

    public void setImgPhoto(String imgPhoto) {
        this.imgPhoto = imgPhoto;
    }
    /**
	 * 设置：地址
	 */
    public String getAddress() {
        return address;
    }


    /**
	 * 获取：地址
	 */

    public void setAddress(String address) {
        this.address = address;
    }
    /**
	 * 设置：民宿状态 Search
	 */
    public Integer getFwstateTypes() {
        return fwstateTypes;
    }


    /**
	 * 获取：民宿状态 Search
	 */

    public void setFwstateTypes(Integer fwstateTypes) {
        this.fwstateTypes = fwstateTypes;
    }
    /**
	 * 设置：所属房东
	 */
    public Integer getFdTypes() {
        return fdTypes;
    }


    /**
	 * 获取：所属房东
	 */

    public void setFdTypes(Integer fdTypes) {
        this.fdTypes = fdTypes;
    }
    /**
	 * 设置：房东联系电话
	 */
    public String getPhone() {
        return phone;
    }


    /**
	 * 获取：房东联系电话
	 */

    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
	 * 设置：具体信息
	 */
    public String getNoticeContent() {
        return noticeContent;
    }


    /**
	 * 获取：具体信息
	 */

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

}
