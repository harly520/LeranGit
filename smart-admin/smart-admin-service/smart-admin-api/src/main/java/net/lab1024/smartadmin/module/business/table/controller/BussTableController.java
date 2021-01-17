package net.lab1024.smartadmin.module.business.table.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.table.domain.dto.BussTableAddDTO;
import net.lab1024.smartadmin.module.business.table.domain.dto.BussTableUpdateDTO;
import net.lab1024.smartadmin.module.business.table.domain.dto.BussTableQueryDTO;
import net.lab1024.smartadmin.module.business.table.domain.vo.BussTableVO;
import net.lab1024.smartadmin.module.business.table.domain.vo.BussTableExcelVO;
import net.lab1024.smartadmin.module.business.table.service.BussTableService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
@RestController
@Api(tags = {"业务实体表"})
public class BussTableController extends BaseController {

    @Autowired
    private BussTableService bussTableService;

    @ApiOperation(value = "分页查询业务实体表",notes = "@author lihf")
    @PostMapping("/bussTable/page/query")
    public ResponseDTO<PageResultDTO<BussTableVO>> queryByPage(@RequestBody BussTableQueryDTO queryDTO) {
        return bussTableService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加业务实体表",notes = "@author lihf")
    @PostMapping("/bussTable/add")
    public ResponseDTO<String> add(@RequestBody @Validated BussTableAddDTO addTO){
        return bussTableService.add(addTO);
    }

    @ApiOperation(value="修改业务实体表",notes = "@author lihf")
    @PostMapping("/bussTable/update")
    public ResponseDTO<String> update(@RequestBody @Validated BussTableUpdateDTO updateDTO){
        return bussTableService.update(updateDTO);
    }

    @ApiOperation(value="批量删除业务实体表",notes = "@author lihf")
    @PostMapping("/bussTable/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return bussTableService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author lihf")
    @PostMapping("/bussTable/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<BussTableExcelVO> bussTableList = bussTableService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("业务实体表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, BussTableExcelVO.class, bussTableList);
        downloadExcel("业务实体表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author lihf")
    @PostMapping("/bussTable/export/all")
    public void exportAll(@RequestBody @Validated BussTableQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<BussTableExcelVO> bussTableList = bussTableService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("业务实体表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, BussTableExcelVO.class, bussTableList);
        downloadExcel("业务实体表", workbook, response);
    }

}
