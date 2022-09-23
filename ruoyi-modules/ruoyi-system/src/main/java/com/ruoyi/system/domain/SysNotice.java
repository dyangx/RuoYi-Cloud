package com.ruoyi.system.domain;

import cn.easyes.annotation.HighLight;
import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexId;
import cn.easyes.annotation.IndexName;
import cn.easyes.common.constants.Analyzer;
import cn.easyes.common.enums.FieldStrategy;
import cn.easyes.common.enums.FieldType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知公告表
 * @TableName sys_notice
 */
@TableName(value ="sys_notice")
@Data
@IndexName("sys_notice")
public class SysNotice implements Serializable {
    /**
     * 公告ID
     */
    // es中的唯一id,如果你想自定义es中的id为你提供的id,比如MySQL中的id,请将注解中的type指定为customize,如此id便支持任意数据类型)
    @IndexId(type = cn.easyes.common.enums.IdType.CUSTOMIZE)
    private Integer id;

    @TableField(value = "notice_id")
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    /**
     * 公告标题
     * es: 文档标题,不指定类型默认被创建为keyword类型,可进行精确查询
     */
    @TableField(value = "notice_title")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @TableField(value = "notice_type")
    private String noticeType;

    /**
     * 公告内容
     */
    @TableField(value = "notice_content")
    @HighLight(mappingField="highlightContent")
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @IndexField(fieldType = FieldType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @IndexField(exist = false)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 标签
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 文章地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 图片地址
     */
    @TableField(value = "pic_url")
    private String picUrl;

    public Integer getNoticeId() {
        return id;
    }

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}