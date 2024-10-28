package com.entity.model;

import com.entity.DiscussfangwuxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 民宿信息评论表
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-02-18
 */
public class DiscussfangwuxinxiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Long id;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date addtime;


    /**
     * 关联表id
     */
    private Long refid;


    /**
     * 用户id
     */
    private Long userid;


    /**
     * 评论内容
     */
    private String replycontent;


    /**
     * 回复内容
     */
    private String reply;


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
	 * 设置：创建时间
	 */
    public Date getAddtime() {
        return addtime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    /**
	 * 设置：关联表id
	 */
    public Long getRefid() {
        return refid;
    }


    /**
	 * 获取：关联表id
	 */

    public void setRefid(Long refid) {
        this.refid = refid;
    }
    /**
	 * 设置：用户id
	 */
    public Long getUserid() {
        return userid;
    }


    /**
	 * 获取：用户id
	 */

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    /**
	 * 设置：评论内容
	 */
    public String getReplycontent() {
        return replycontent;
    }


    /**
	 * 获取：评论内容
	 */

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent;
    }
    /**
	 * 设置：回复内容
	 */
    public String getReply() {
        return reply;
    }


    /**
	 * 获取：回复内容
	 */

    public void setReply(String reply) {
        this.reply = reply;
    }

    }
