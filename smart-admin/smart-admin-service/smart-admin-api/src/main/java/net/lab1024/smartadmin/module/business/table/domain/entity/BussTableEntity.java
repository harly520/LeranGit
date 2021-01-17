package net.lab1024.smartadmin.module.business.table.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 业务实体表 ]
 *
 * @author lihf
 * @version 1.0
 * @company 海风实验室
 * @copyright (c)  海风实验室Inc. All rights reserved.
 * @date 2021-01-17 21:48:59
 * @since JDK1.8
 */
@Data
@TableName("tb_buss_table")
public class BussTableEntity extends BaseEntity{


    /**
     * 表名
     */
    private String name;

    /**
     * 主键是否自增
     */
    private String auto;

    /**
     * 主键
     */
    private String primary;

    /**
     * 注释
     */
    private String comment;

    /**
     * 用户ID
     */
    private String creator;



}
