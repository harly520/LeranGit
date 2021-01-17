package net.lab1024.smartadmin.module.business.table.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [ 业务实体表 ]
 *
 * @author lihf
 * @version 1.0
 * @company 海风实验室
 * @copyright (c) 海风实验室Inc. All rights reserved.
 * @date  2021-01-17 21:48:59
 * @since JDK1.8
 */
@Data
public class BussTableExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "表名")
    private String name;

    @Excel(name = "主键是否自增")
    private String auto;

    @Excel(name = "主键")
    private String primary;

    @Excel(name = "注释")
    private String comment;

    @Excel(name = "用户ID")
    private String creator;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "更新时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;



}
