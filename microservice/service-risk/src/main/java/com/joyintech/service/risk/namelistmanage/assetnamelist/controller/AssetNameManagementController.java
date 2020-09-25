package com.joyintech.service.risk.namelistmanage.assetnamelist.controller;


import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.service.risk.namelistmanage.assetnamelist.service.AssetNameManagementService;
import com.joyintech.tams.common.base.entity.RskAccessListDetail;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 名单管理--资产名单管理
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see AssetNameManagementController
 * @since 1.0
 */
@RestController("AssetNameManagementCtl_dtx")
@RequestMapping("/AssetNameManagement")
public class AssetNameManagementController {

    @Autowired
    private AssetNameManagementService assetNameManagementService;

    @Autowired
    private MapperDelegateService mapperDelegateService;
    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskAccessListDto> selectAll(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return assetNameManagementService.selectForPage(page, length, param);
    }

    /**
     * 插入业务表
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody RskAccessListDto dto) throws Exception {
        assetNameManagementService.insert(dto);
    }

    /**
     * 删除
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDelete")
    public void doDelete(@RequestBody String workflowId) throws Exception{
        // 1.删除各业务流程信息
        // 名单
        mapperDelegateService.deleteBySpecificKey("WFL_RSK_ACCESS_LIST", "WORKFLOW_ID",
                workflowId);
        // 名单明细
        mapperDelegateService.deleteBySpecificKey("WFL_RSK_ACCESS_LIST_DETAIL", "WORKFLOW_ID",
                workflowId);
        // 名单履历
        mapperDelegateService.deleteBySpecificKey("WFL_RSK_ACCESS_LIST_LOG", "WORKFLOW_ID",
                workflowId);
    }

    /**
     * 删除
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody RskAccessListDto dto) throws Exception{
        assetNameManagementService.delete(dto);
    }

    /**
     * 插入业务流程表
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertBussFlow")
    public void insertBussFlow(@RequestBody RskAccessListDto dto) throws Exception {
        assetNameManagementService.insertBussFlow(dto);
    }

    /**
     * 查看名单
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("selectRskAccessList")
    public RskAccessListDto selectRskAccessList(@RequestBody Map<String,Object> param)throws Exception{
        return assetNameManagementService.selectRskAccessList(param);
    }
    
    
    /**
     * 根据资产名称查询数据
     *
     * @param param 资产名称
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("getRskEntityByName")
    public int getRskEntityByName(@RequestBody Map<String,Object> param)
        throws Exception{
        return assetNameManagementService.getRskEntityByName(param);
    }
    
    /**
     * 查询数据
     *
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("getEntityList")
    public List<RskAccessListDetail> getEntityList(@RequestBody Map<String,Object> param)
        throws Exception{
        return assetNameManagementService.getEntityList(param);
    }

    /**
     * 模糊下拉框多选
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("selectListId")
    public List<SelectOptionDto> selectListId(@RequestBody Map<String,Object> param)
            throws Exception{
        return assetNameManagementService.selectListId(param);
    }

}
