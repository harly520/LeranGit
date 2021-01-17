package net.lab1024.smartadmin.module.business.table.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.table.domain.dto.BussTableQueryDTO;
import net.lab1024.smartadmin.module.business.table.domain.entity.BussTableEntity;
import net.lab1024.smartadmin.module.business.table.domain.vo.BussTableVO;
import net.lab1024.smartadmin.module.business.table.domain.vo.BussTableExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
@Mapper
@Component
public interface BussTableDao extends BaseMapper<BussTableEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return BussTableVO
    */
    IPage<BussTableVO> queryByPage(Page page, @Param("queryDTO") BussTableQueryDTO queryDTO);

    /**
     * 根据id删除
     * @param id
     * @return
    */
    void deleteById(@Param("id") Long id);

    /**
     * 根据id批量删除
     * @param idList
     * @return
    */
    void deleteByIdList(@Param("idList") List<Long> idList);

        /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<BussTableExcelVO> queryAllExportData(@Param("queryDTO") BussTableQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<BussTableExcelVO> queryBatchExportData(@Param("idList") List<Long> idList);
}
