package com.ruoyi.a.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName t_a
 */
@Data
@TableName("t_a")
public class TA implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    @TableField("a_1")
    private String a1;

    /**
     * 
     */
    @TableField("a_2")
    private String a2;

    /**
     * 
     */
    @TableField("a_3")
    private String a3;

    private static final long serialVersionUID = 1L;
}