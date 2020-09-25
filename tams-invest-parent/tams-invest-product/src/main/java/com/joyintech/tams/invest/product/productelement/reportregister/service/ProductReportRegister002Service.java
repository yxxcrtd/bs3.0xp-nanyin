package com.joyintech.tams.invest.product.productelement.reportregister.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.reportregister.dto.ProductReportRegister002Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class ProductReportRegister002Service extends CommonExportService<ProductReportRegister002Dto> implements BaseProcessService<ProductReportRegister002Dto> {

	/**
     * 默认entity名称
     */
    public final static String DEFAULT_ENTITY_NAME = "productReportRegister002Dao";
    
	@Override
	public void doComplete(ProductReportRegister002Dto entity, OperationType type) {
		if (type == OperationType.NEW) {
			entity.setCreateUser(SecurityUtil.getLoginUser().getUserName());
			entity.setCreateTime(new Date());
			//entity.setRepRaiseAmtPlan(ArithmeticUtil.multiply(entity.getRepRaiseAmtPlan(), new BigDecimal(10000)));
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/productReportRegister/insertSelective", entity,
					Integer.class);
		}
		else if (type == OperationType.MODIFY) {
			//entity.setRepRaiseAmtPlan(ArithmeticUtil.multiply(entity.getRepRaiseAmtPlan(), new BigDecimal(10000)));
			entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
			entity.setUpdateTime(new Date());
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/productReportRegister/updateByPrimaryKeySelective", entity,
					Integer.class);
		}
		else if(type == OperationType.DELETE) {
			restTemplate.getForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME
							+ "/productReportRegister/deleteByPrimaryKey?repFinprodId=" + entity.getRepFinprodId(),
					Integer.class);
		}
		// 二级法人权限处理 WY
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //创建机构代码
            String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            entity.setCreateInst(createInst); 
            // 有查询权限的机构代码
            List<String> searchOrgCodeList = null;
            // 有编辑权限的机构代码
            List<String> editOrgCodeList = null;
            // 新建或修改时重新设置二级法人权限，删除时则清空
            if (!OperationType.DELETE.equals(type)) {
                //获取所有父级机构。  拥有查看权限
                searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                editOrgCodeList = Arrays.asList(entity.getCreateInst());
            }
            // 记录二级法人权限
            insertDataAuthTypeInst(entity.getRepFinprodId(), searchOrgCodeList, editOrgCodeList,DictCodeListExt3.共通业务类型_产品申报登记要素管理.getValue());
        } 
	}
	
	
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/product/productelement/reportregister/conf/export_conf.xml")
		.dataSource(DataSourceType.MASERT);
	}
	
	/**
	 * 根据产品Id查询
	 * @param repFinprodId
	 * @return
	 */
	public ProductReportRegister002Dto selectByPrimaryKey(String repFinprodId) {
		ProductReportRegister002Dto entity = restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME
						+ "/productReportRegister/selectByPrimaryKey?repFinprodId=" + repFinprodId,
				ProductReportRegister002Dto.class);
		/*if(entity != null && entity.getRepRaiseAmtPlan() != null && entity.getRepRaiseAmtPlan().compareTo(new BigDecimal("0")) != 0) {
			entity.setRepRaiseAmtPlan(entity.getRepRaiseAmtPlan().divide(new BigDecimal("10000")));
		}*/
		return entity;
	}
	
	/**
	 * 根据行内识别码查询是否有重复数据
	 * @param repFinprodInsId
	 * @return
	 */
	public int selsectByFinprodInsId(String repFinprodInsId) {
		return restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME
				+ "/productReportRegister/selsectByFinprodInsId?repFinprodInsId=" + repFinprodInsId,
		Integer.class);
	}

}
