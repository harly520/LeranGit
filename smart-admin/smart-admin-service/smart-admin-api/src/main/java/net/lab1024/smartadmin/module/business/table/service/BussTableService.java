package net.lab1024.smartadmin.module.business.table.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.table.dao.BussTableDao;
import net.lab1024.smartadmin.module.business.table.domain.dto.BussTableAddDTO;
import net.lab1024.smartadmin.module.business.table.domain.dto.BussTableUpdateDTO;
import net.lab1024.smartadmin.module.business.table.domain.dto.BussTableQueryDTO;
import net.lab1024.smartadmin.module.business.table.domain.entity.BussTableEntity;
import net.lab1024.smartadmin.module.business.table.domain.vo.BussTableVO;
import net.lab1024.smartadmin.module.business.table.domain.vo.BussTableExcelVO;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class BussTableService {

    @Autowired
    private BussTableDao bussTableDao;

    /**
     * 根据id查询
     */
    public BussTableEntity getById(Long id){
        return  bussTableDao.selectById(id);
    }

    /**
     * 分页查询
     * @author lihf
     * @date 2021-01-17 21:48:59
     */
    public ResponseDTO<PageResultDTO<BussTableVO>> queryByPage(BussTableQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<BussTableVO> voList = bussTableDao.queryByPage(page, queryDTO);
        PageResultDTO<BussTableVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author lihf
     * @date 2021-01-17 21:48:59
     */
    public ResponseDTO<String> add(BussTableAddDTO addDTO) {
        BussTableEntity entity = SmartBeanUtil.copy(addDTO, BussTableEntity.class);
        bussTableDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author lihf
     * @date 2021-01-17 21:48:59
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(BussTableUpdateDTO updateDTO) {
        BussTableEntity entity = SmartBeanUtil.copy(updateDTO, BussTableEntity.class);
        bussTableDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author lihf
     * @date 2021-01-17 21:48:59
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        bussTableDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author lihf
     * @date 2021-01-17 21:48:59
     */
    public List<BussTableExcelVO> queryAllExportData(BussTableQueryDTO queryDTO) {
        return bussTableDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author lihf
     * @date 2021-01-17 21:48:59
     */
    public List<BussTableExcelVO> queryBatchExportData(List<Long> idList) {
        return bussTableDao.queryBatchExportData(idList);
    }
}
