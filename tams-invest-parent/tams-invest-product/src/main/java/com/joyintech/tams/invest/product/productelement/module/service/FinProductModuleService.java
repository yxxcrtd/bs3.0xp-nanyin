/*
 * 文件名：FinProductModule002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.module.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.dao.PrdTemplateMapper;
import com.joyintech.tams.common.base.entity.PrdTemplate;
import com.joyintech.tams.common.base.entity.PrdTemplateKey;
import com.joyintech.tams.component.business.prod.BussFinProductRevalDocumentService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductRevalDocumentDto;
import com.joyintech.tams.component.business.report.prod.BussRepFinProductInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfo002Dto;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfoDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 产品系列模板新建Service
 * 
 * @author 何阳阳
 * @version 1.0 2018年8月31日
 * @see FinProductModuleService
 * @since 1.0
 */
@Service
public class FinProductModuleService extends BaseService<FinProductModuleInfo002Dto> implements BaseProcessService<FinProductModuleInfo002Dto> {

	
	@Autowired
	private PrdTemplateMapper prdTemplateMapper;
    /**
     * BussRepFinProductInfoService
     */
    @Autowired(required = false)
    private BussRepFinProductInfoService bussRepFinProductInfoService;

    /**
     * 信息披露业务组件Service
     */
    @Autowired(required = false)
    private BussFinProductRevalDocumentService bussFinProductRevalDocumentService;
    
    /**
     * 根据模板ID查询模板
     * @param templateId
     * @return
     */
    public FinProductModuleInfo002Dto selectByTemplateId(String templateId) {
    	FinProductModuleInfoDto finProductModuleInfoDto = restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinProductModule/selectByTemplateId?templateId=" + templateId,
                	FinProductModuleInfoDto.class);
    	if(StringUtil.isNotEmpty(finProductModuleInfoDto) && StringUtil.isNotEmpty(finProductModuleInfoDto.getTemplateContent())) {
    		return JsonUtils.json2Object(finProductModuleInfoDto.getTemplateContent(), FinProductModuleInfo002Dto.class);
    	}
    	return null;
    }
    @Override
    public void doComplete(FinProductModuleInfo002Dto entity, OperationType type) {
    	//产品模板
        PrdTemplate prdTemplate = new PrdTemplate();
        prdTemplate.setTemplateId(entity.getProductModuleInfo().getTemplateId());
        prdTemplate.setTemplateName(entity.getProductModuleInfo().getTemplateName());
        prdTemplate.setProfitType(entity.getProductModuleInfo().getProfitType());
        prdTemplate.setTemplateContent(JsonUtils.object2Json(entity));
        prdTemplate.setDealMode(entity.getProductModuleInfo().getDealMode());
        prdTemplate.setOperationType(entity.getProductModuleInfo().getOperationType());
        prdTemplate.setProfitFlag(entity.getProductModuleInfo().getProfitFlag());
        PrdTemplateKey prdTemplateKey = new PrdTemplateKey();
        prdTemplateKey.setTemplateId(entity.getProductModuleInfo().getTemplateId());
        prdTemplateKey.setTemplateName(entity.getProductModuleInfo().getTemplateName());
        if(type == OperationType.NEW) {
        	prdTemplateMapper.insertSelective(prdTemplate);
        }else if(type == OperationType.MODIFY) {
        	prdTemplateMapper.deleteByPrimaryKey(prdTemplateKey);
        	prdTemplateMapper.insertSelective(prdTemplate);
        }else if(type == OperationType.DELETE) {
        	prdTemplateMapper.deleteByPrimaryKey(prdTemplateKey);
		}
        
        
        
    	
    	
    	
        // 处理信息披露   前台信息披露查看或修改模式带出的是文件名称，此处转成对应Id
        /*if (entity.getBussFinProductRevalDocumentDto() != null) {
            List<BussFinProductRevalDocumentDto> finProductRevalDocumentDtoList = entity.getBussFinProductRevalDocumentDto().getFinProductRevalDocumentDtoList();
            if (!StringUtil.isEmptyList(finProductRevalDocumentDtoList)) {
                for (BussFinProductRevalDocumentDto bussFinProductRevalDocumentDto : finProductRevalDocumentDtoList) {
                    List<SelectOptionDto> fileNameList = bussFinProductRevalDocumentService.selectByFileType(
                        bussFinProductRevalDocumentDto.getFileType(), null);
                    fileNameList.forEach(item -> {
                        if (StringUtil.equals(item.getText(),
                            bussFinProductRevalDocumentDto.getFileName())) {
                            bussFinProductRevalDocumentDto.setFileName(item.getId());
                        }
                    });
                }
            }
        }*/
        
        
        // 清除费用添加时的缓存
       // CacheUtil.clearAppCache(entity.getFinprodId());
        
        // 二级法人权限处理 WY
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //创建机构代码
            String createInst = MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            prdTemplate.setCreateInst(createInst);
            // 有查询权限的机构代码
            List<String> searchOrgCodeList = null;
            // 有编辑权限的机构代码
            List<String> editOrgCodeList = null;
            // 新建或修改时重新设置二级法人权限，删除时则清空
            if (!OperationType.DELETE.equals(type)) {
                //获取所有父级机构。  拥有查看权限
                searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                editOrgCodeList = Arrays.asList(prdTemplate.getCreateInst());
            } 
            // 记录二级法人权限
            insertDataAuthTypeInst(prdTemplate.getTemplateId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_产品模板管理.getValue());
        } 
    }

    /**
     * 根据模板代码查询模板信息
     * 
     * @param finprodId 金融产品代码
     * @return 产品信息
     */
    public FinProductModuleInfoDto getBussinessEntityByFinprodId(String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                          + "/FinProductModule/queryModuleInfoByFinprodId",
            map, FinProductModuleInfoDto.class);
    }

    /**
     * 根据模板名称查询模板信息
     * 
     * @param finprodName 模板名称
     * @param finprodId 模板代码
     * @return 模板信息
     */
    public FinProductModuleInfoDto getBussinessEntityByFinprodName(String finprodName, String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("finprodName", finprodName);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                          + "/FinProductModule/queryModuleInfoByFinprodName",
            map, FinProductModuleInfoDto.class);
    }
}
