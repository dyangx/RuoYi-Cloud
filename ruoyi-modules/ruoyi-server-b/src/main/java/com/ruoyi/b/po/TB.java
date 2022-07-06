package com.ruoyi.b.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_b
 */
@TableName(value ="t_b")
@Data
public class TB implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 
     */
    @TableField(value = "b_1")
    private String b_1;

    /**
     * 
     */
    @TableField(value = "b_2")
    private String b_2;

    /**
     * 
     */
    @TableField(value = "b_3")
    private String b_3;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}