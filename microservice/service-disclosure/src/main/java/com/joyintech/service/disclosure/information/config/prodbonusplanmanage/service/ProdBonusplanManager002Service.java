/*
 * 文件名：ReportModelManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.config.prodbonusplanmanage.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.dao.ProdBonusplanMapper;
import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


/**
 * 报表模板管理新建service
 * 
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see ProdBonusplanManager002Service
 * @since 2.0
 */
@Service(value="serviceProdBonusplanManager002Service")
@Transactional(rollbackFor = Exception.class)
public class ProdBonusplanManager002Service extends CommonExportService<ProdBonusplanDto> {
    
    
    
    @Autowired
    private ProdBonusplanMapper prodBonusplanMapper;

        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager002Service.class);

	/**
	 * 保存check
	 * @param dto
	 * @return
	 */
	public AjaxResult check(ProdBonusplanDto dto) {
		//todo check
		/*Set<String> message = new HashSet<String>();
		if(StringUtil.isEmpty(dto.getReportDesc())){
            message.add("请输入中文描述");
        }
		if(StringUtil.isEmpty(dto.getFolder())){
            message.add("请输入文件夹");
        }
		if(StringUtil.isEmpty(dto.getReportWarehouse())){
			message.add("请输入报表仓库");
		}
		AjaxResult result = new AjaxResult();
		if(CollectionUtils.isNotEmpty(message)){
            List<ValidMessage> errorList = new ArrayList<>();
            message.forEach(action->{
                errorList.add(new ValidMessage(action));
                });
            result.setErrorList(errorList);
        }
		return result;*/
		AjaxResult result = new AjaxResult();

		return  result ;
	}
	//初始化编辑页面数据
	public ProdBonusplanDto getProdBonusplanDto(String keyId) {
		ProdBonusplanDto dto = prodBonusplanMapper.selectByBonusPlanId(keyId);
		return dto;
	}


	/**
	 * 插入数据
	 * @param dto
	 */
	public void insert(ProdBonusplanDto dto) {
		dto.setBonusPlanId(UUID.randomUUID().toString());
		prodBonusplanMapper.insert(dto);
		
	}
	/**
	 * 更新数据
	 * @param dto
	 * @return
	 */
	public void update(ProdBonusplanDto dto) {
		prodBonusplanMapper.updateByPrimaryKeySelective(dto);
	}

}
