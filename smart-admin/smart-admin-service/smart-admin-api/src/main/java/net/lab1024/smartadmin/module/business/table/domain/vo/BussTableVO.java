package net.lab1024.smartadmin.module.business.table.domain.vo;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

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
public class BussTableVO {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("表名")
    private String name;

    @ApiModelProperty("主键是否自增")
    private String auto;

    @ApiModelProperty("主键")
    private String primary;

    @ApiModelProperty("注释")
    private String comment;

    @ApiModelProperty("用户ID")
    private String creator;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;



}
