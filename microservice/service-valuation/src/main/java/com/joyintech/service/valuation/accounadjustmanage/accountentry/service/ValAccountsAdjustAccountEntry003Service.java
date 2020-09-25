/*
 * 文件名：AccountsAdjustAccountEntry001Service.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All
 * Rights Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.accountentry.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dao.ValAccountsAdjustAccountEntry003Mapper;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.definition.ValAccountEntryCodeValue;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry002Dto;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry003Dto;
import com.joyintech.tams.common.base.dao.BokSceneconfigMapper;
import com.joyintech.tams.common.base.dao.BokScenesubjectMapper;
import com.joyintech.tams.common.base.entity.BokSceneconfig;
import com.joyintech.tams.common.base.entity.BokScenesubject;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.ValStringUtil;
/**
*
* 文件名：AccountsAdjustAccountEntry003Service.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：会计分录管理新建修改用service
* 系统名称：估值核算系统V1.0
*/
@Service(value="serviceValAccountsAdjustAccountEntry003Service")
@Transactional(rollbackFor = Exception.class)
public class ValAccountsAdjustAccountEntry003Service extends CommonExportService<ValAccountsAdjustAccountEntry003Dto> {

    /**
     * AccountsAdjustAccountEntry003Mapper
     */
    @Autowired
    private ValAccountsAdjustAccountEntry003Mapper valAccountsAdjustAccountEntry003Mapper;
    
    @Autowired
    BokSceneconfigMapper bokSceneconfigMapper;
    
    @Autowired
    BokScenesubjectMapper bokScenesubjectMapper;


    /**
     * 查询分支配置信息
     * 
     * @param sceneNodeId 场景分支节点代码
     * @return 分支配置信息
     * @see
     * @since 1.0
     */
    public BokSceneconfig findSceneConfig(String sceneNodeId) {
        if (StringUtil.isNotBlank(sceneNodeId)) {
            return valAccountsAdjustAccountEntry003Mapper.findSceneConfig(sceneNodeId);
        }
        return null;
    }
    
    /**
     * 数据重复性检查
     * 
     * @param dto 参数
     * @return 已存在的数量
     * @see
     * @since 1.0
     */
    public int selectExistsConfig(ValAccountsAdjustAccountEntry003Dto dto) {
        return valAccountsAdjustAccountEntry003Mapper.selectExistsConfig(dto);
    }
    
    /**
     * 查询当前节点
     * 
     * @param sceneNodeId 场景配置id
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    public SelectOptionDto findAllFSceneBySceneId(String sceneNodeId) {
        if (StringUtil.isEmpty(sceneNodeId)) {
            SelectOptionDto dto = new SelectOptionDto();
            dto.setId(StringUtil.EMPTY);
            dto.setText(getMessage(ValAccountEntryCodeValue.根节点.getCode()));
            dto.setCol("0");
            return dto;
        }
        return valAccountsAdjustAccountEntry003Mapper.findAllFSceneBySceneId(sceneNodeId,
            getMessage(ValAccountEntryCodeValue.根节点.getCode()));
    }
    
    /**
     * 查询某个账套下的根节点
     * 
     * @param booksetId 账套代码
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    public SelectOptionDto findRootNodeByBooksetId(String booksetId) {
        return valAccountsAdjustAccountEntry003Mapper.findRootNodeByBooksetId(booksetId);
    }
    
    /**
     * 影响记账元素下拉框初始化
     * 
     * @param nodeId 所选上级节点所在树的二级节点的节点代码
     * @param sceneNodeId 场景分支节点代码
     * @param detailPageModelAdd 添加模式
     * @param pageModel 页面模式
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findNodeIds(String nodeId, String sceneNodeId, String pageModel, String detailPageModelAdd) {
        return valAccountsAdjustAccountEntry003Mapper.findNodeIds(nodeId, sceneNodeId,
            DictCodeListExt1.数据类型_下拉框.getValue(), pageModel, detailPageModelAdd);
    }
    
    /**
     * 查找所有子节点
     * @param sceneNodeId 节点id
     * @return 所有子节点
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findConfigByFSceneId(String sceneNodeId) {
        return valAccountsAdjustAccountEntry003Mapper.findConfigByFSceneId(sceneNodeId, DictCodeListExt1.数据类型_下拉框.getValue());
    }
    
    /**
     * 检索场景分支与科目配置信息
     * 
     * @param sceneNodeId 场景分支节点代码
     * @param isAddModel 
     * @return AccountsAdjustAccountEntry002Dto list
     * @see
     * @since 1.0
     */
    public List<ValAccountsAdjustAccountEntry002Dto> findAccountEntryConfigData(String sceneNodeId,String dataType, boolean isAddModel) {
        return valAccountsAdjustAccountEntry003Mapper.findAccountEntryConfigData(sceneNodeId,
            dataType, isAddModel);
    }
    
    /**
     * 检索账套下的叶子科目
     * 
     * @param booksetId 账套代码
     * @return 下拉菜单选项
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findSubjectByBooksetId(String booksetId) {
        return valAccountsAdjustAccountEntry003Mapper.findSubjectByBooksetId(booksetId);
    }
    
    /**
     * 金额元素控件初期化
     * 
     * @param tableId 会计场景代码
     * @return 金额元素
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findDictIdsByTableId(String tableId) {
        return valAccountsAdjustAccountEntry003Mapper.findDictIdsByTableId(tableId,
            DictCodeListExt1.元素数据类型_数值型.getValue());
    }
    
    /**
     * 保存会计场景配置信息
     * 
     * @param accountEntryDto AccountsAdjustAccountEntry003Dto
     * @see
     * @since 1.0
     */
    public void save(ValAccountsAdjustAccountEntry003Dto accountEntryDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("fsceneNodeid", accountEntryDto.getFsceneNodeid());
        params.put("rootNode", ValAccountEntryCodeValue.ROOTNODE.getCode());
        if (ValStringUtil.equals(accountEntryDto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW,
            GlobalConstant.DETAIL_PAGE_MODEL_ADD, GlobalConstant.DETAIL_PAGE_MODEL_COPY)) {
            // 3.1.1 场景分支配置表
            String fsceneNodeid = accountEntryDto.getFsceneNodeid();
            String sceneNodeId = genSceneNodeId(fsceneNodeid, params);
            BokSceneconfig config = new BokSceneconfig();
            config.setFsceneNodeid(ValAccountEntryCodeValue.ROOTNODE.getCode().equals(
                fsceneNodeid) ? null : fsceneNodeid);
            config.setSceneNodeId(sceneNodeId);
            config.setBooksetId(accountEntryDto.getBooksetId());
            config.setNodeId(accountEntryDto.getNodeId());
            config.setNodeValue(accountEntryDto.getNodeValue());
            config.setLevel(accountEntryDto.getLevel());
            config.setRemark(accountEntryDto.getRemark());
            bokSceneconfigMapper.insert(config);
            // 3.1.1.2 如果界面.同时生成该节点的所有其他分录为check on
            genOtherConfig(accountEntryDto, params);
            // 3.1.2 场景科目关系表
            insertScenSubject(accountEntryDto, sceneNodeId);
        }
        else if (StringUtil.equals(accountEntryDto.getPageModel(),
            GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            // 3.2.1.1 场景分支配置表
            // 3.2.1.1.1 场景分支配置表
            valAccountsAdjustAccountEntry003Mapper.updateConfig(accountEntryDto);
            // 3.2.1.1.2 如果界面.同时生成该节点的所有其他分录为check on
            // 3.1.1.2 如果界面.同时生成该节点的所有其他分录为check on
            genOtherConfig(accountEntryDto, params);
            // 3.2.1.2 场景科目关系表
            // 3.2.1.2.1 删除场景科目关系表数据
            valAccountsAdjustAccountEntry003Mapper.deleteSubject(accountEntryDto);
            // 3.2.1.2.2 场景科目关系表
            if (accountEntryDto.getRows() != null && !accountEntryDto.getRows().isEmpty()) {
                insertScenSubject(accountEntryDto, accountEntryDto.getSceneNodeId());
            }
        }
    }
    
    
    /**
     * 场景科目关系数据插入
     * 
     * @param accountEntryDto 场景科目关系数据
     * @param sceneNodeId 
     * @see
     * @since 1.0
     */
    private void insertScenSubject(ValAccountsAdjustAccountEntry003Dto accountEntryDto, String sceneNodeId) {
    	List<ValAccountsAdjustAccountEntry002Dto> gridData = accountEntryDto.getRows();
        for (ValAccountsAdjustAccountEntry002Dto row : gridData) {
        	System.out.println("row.getIsExtends()："+row.getIsExtends());
            if (!row.getIsExtends()) {
                BokScenesubject bokScenesubject = new BokScenesubject();
                // TODO
//                bokScenesubject.setSceneSubId(IDGenerator.generate());
                bokScenesubject.setBooksetId("xxxxxxxx");
                bokScenesubject.setSceneNodeId(sceneNodeId);
                bokScenesubject.setSubjectNo(row.getSubjectId());
                bokScenesubject.setCdFlag(row.getCdFlag());
                bokScenesubject.setFormEle(row.getFormEle());
                bokScenesubject.setAccountNet(row.getAccountNet());
                bokScenesubjectMapper.insert(bokScenesubject);
            }
        }
    }
    
    
    /**
     * 计算会计场景代码
     * 
     * @param fsceneNodeid 父场景代码
     * @param params 其他参数
     * @return 会计场景代码
     * @see
     * @since 1.0
     */
    private String genSceneNodeId(String fsceneNodeid,Map<String, Object> params) {
        String sceneNodeIdInt = valAccountsAdjustAccountEntry003Mapper.selectSceneNodeId(params);
        // 如果检索不到数据，说明没有子节点，场景分支节点代码从1开始
        if (sceneNodeIdInt == null) {
            sceneNodeIdInt = "1";
        }
        String sceneNodeId = String.format("%03d", Integer.parseInt(sceneNodeIdInt));
        sceneNodeId = (ValAccountEntryCodeValue.ROOTNODE.getCode().equals(
            fsceneNodeid) ? StringUtil.EMPTY : fsceneNodeid) + sceneNodeId;
        return sceneNodeId;
    }
    
    /**
     * 生成该节点下的其他分支
     * 
     * @param accountEntryDto 节点信息
     * @param params 检索参数
     * @see
     * @since 1.0
     */
    private void genOtherConfig(ValAccountsAdjustAccountEntry003Dto accountEntryDto,
                                Map<String, Object> params) {
        String sceneNodeId;
        BokSceneconfig config;
        if (accountEntryDto.isGen() && accountEntryDto.getNodeValues() != null
            && !accountEntryDto.getNodeValues().isEmpty()) {
            for (String nodeValue : accountEntryDto.getNodeValues()) {
                int count = valAccountsAdjustAccountEntry003Mapper.findSceneConfigCount(
                    ValAccountEntryCodeValue.ROOTNODE.getCode().equals(
                        accountEntryDto.getFsceneNodeid()) ? null : accountEntryDto.getFsceneNodeid(),
                    accountEntryDto.getBooksetId(), accountEntryDto.getNodeId(), nodeValue);
                if (count == 0) {
                    sceneNodeId = genSceneNodeId(accountEntryDto.getFsceneNodeid(), params);
                    config = new BokSceneconfig();
                    config.setFsceneNodeid(ValAccountEntryCodeValue.ROOTNODE.getCode().equals(
                        accountEntryDto.getFsceneNodeid()) ? null : accountEntryDto.getFsceneNodeid());
                    config.setSceneNodeId(sceneNodeId);
                    config.setBooksetId(accountEntryDto.getBooksetId());
                    config.setNodeId(accountEntryDto.getNodeId());
                    config.setNodeValue(nodeValue);
                    config.setLevel(accountEntryDto.getLevel());
                    bokSceneconfigMapper.insert(config);
                }
            }
        }
    }
}
