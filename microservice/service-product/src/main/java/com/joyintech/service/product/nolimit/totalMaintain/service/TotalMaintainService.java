package com.joyintech.service.product.nolimit.totalMaintain.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.nolimit.totalMaintain.dao.TotalMaintainMapper;
import com.joyintech.service.product.nolimit.totalMaintain.dto.TotalMaintain001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @program: TotalMaintainService
 * @description:
 * @author: jyl
 * @create: 2019-12-19 14:12
 **/
@Service(value = "totalMaintainService")
@Transactional(rollbackFor = Exception.class)
public class TotalMaintainService extends CommonExportService {
    @Autowired
    private TotalMaintainMapper totalMaintainMapper;

    /**
     * 非标限额信息查询
     *
     * @param page
     * @param length
     * @param pageParam
     * @return Page
     * @throws Exception
     */
    public Page<TotalMaintain001Dto> findPage(int page, int length,
                                              Map<String, Object> pageParam)
            throws Exception {


        return this.queryForPage(page, length,
                () -> totalMaintainMapper.findPage(pageParam));
    }

    /**
     * 非标限额 生效日校验
     *
     * @param effDate
     * @return int
     */
    public int effDateCheck(String effDate) {
        return totalMaintainMapper.effDateCheck(effDate);
    }

    /**
     * 新建非标限额
     *
     * @param dto
     */
    public void insertInfo(TotalMaintain001Dto dto) {
        totalMaintainMapper.insertInfo(dto);
    }

    /**
     * 修改非标限额
     *
     * @param dto
     */
    public void updateInfo(TotalMaintain001Dto dto) {
        totalMaintainMapper.updateInfo(dto);
    }

    /**
     * 删除非标限额
     *
     * @param effDate
     */
    public void deleteInfo(String effDate) {
        totalMaintainMapper.deleteInfo(effDate);
    }
}
