package net.lab1024.smartadmin.module.business.table.domain.dto;

import net.lab1024.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

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
public class BussTableQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("表名")
    private String name;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
