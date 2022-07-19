/*
 * 文件名：ProdBonusplanManager002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.DisProdPlan;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;


/**
 * 报表模板管理新建service
 *
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see ProdBonusplanManager002Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdBonusplanManager002Service extends CommonExportService<ProdBonusplanDto> implements BaseProcessService<ProdBonusplanDto> {


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager002Service.class);


    @Autowired
    private RepProdInfoMapper repProdInfoMapper;

    /**
     * 查询模板参数
     *
     * @param
     * @return
     */
    public List<ProdBonusplanDto> findParamPage(String repId) {


        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ProdBonusplanManager002/findParamPage?repId=" + repId,
                ProdBonusplanDto.class);
//		List<String> paramList= ProdBonusplanManager002Mapper.findParamPage(repId);
//		List<ProdBonusplanDto> list=new ArrayList<>();
//			for(String param:paramList){
//				if(StringUtil.isNotEmpty(param)){
//					//去掉参数字符串的{}
//					String params=param.substring(1, param.length()-1);
//					//去掉参数字符串的''
//					String params2=params.replace("'", "");
//					//分割参数字符串
//					String[] params3=params2.split(",");
//					int i=1;
//					for(String para:params3){
//						ProdBonusplanDto dto=new ProdBonusplanDto();
//						//设置参数编号
//						dto.setParamNum(i++);
//						//设置参数名称
//						dto.setParamName(para.split(":")[0]);
//						switch (dto.getParamName()){
//							case "portnum":dto.setName("产品名称");break;
//							case "startDate":dto.setName("开始日期");break;
//							case "endDate":dto.setName("结束日期");break;
//							default: break;
//						}
//						//设置参数类型
//						dto.setParamType(para.split(":")[1]);
//						list.add(dto);
//					}
//				}
//				
//				
//				
//			
//		}
//	
//		return list;
    }

    /**
     * 保存check
     *
     * @param dto
     * @return
     */
    public AjaxResult check(ProdBonusplanDto dto) {
        Set<String> message = new HashSet<String>();

        AjaxResult result = new AjaxResult();
        if (CollectionUtils.isNotEmpty(message)) {
            List<ValidMessage> errorList = new ArrayList<>();
            message.forEach(action -> {
                errorList.add(new ValidMessage(action));
            });
            result.setErrorList(errorList);
        }
        return result;
    }

    //初始化编辑页面数据
    public ProdBonusplanDto getProdBonusplanDto(String keyId) {
//		ProdBonusplanDto dto = ProdBonusplanManager002Mapper.getProdBonusplanDto(code);

        ProdBonusplanDto dto = restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ProdBonusplanManager002/getProdBonusplanDto?keyId=" + keyId,
                ProdBonusplanDto.class);


        return dto;
    }


    /**
     * 插入数据
     *
     * @param dto
     */
    public void insert(ProdBonusplanDto dto) {
//		dto.setKeyId(ProdBonusplanManager002Mapper.setId());
//		ProdBonusplanManager002Mapper.insert(dto);

        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProdBonusplanManager002/insert",
                dto, Integer.class);

    }

    /**
     * 更新数据
     *
     * @param dto
     * @return
     */
    public void update(ProdBonusplanDto dto) {

//		ProdBonusplanManager002Mapper.update(dto);

        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProdBonusplanManager002/update",
                dto, Integer.class);
    }

    /**
     * 删除
     *
     * @param dto
     */
    public void delete(ProdBonusplanDto dto) {
//		ProdBonusplanManager002Mapper.deleteModle(param);

        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProdBonusplanManager001/delete",
                dto, Integer.class);

    }

    public RepProdInfo getPrdInfo(String prodId){
        RepProdInfo prodInfo =  repProdInfoMapper.selectByPrimaryKey2(prodId);
        return  prodInfo;
    }


    @Override
    public void doComplete(ProdBonusplanDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
            entity.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
            this.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            this.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            this.delete(entity);

        }

    }
}
