/*
 * 文件名：DisProdPlanManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.dao.GetPrdInfoSelectMapper;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dao.DisProdPlanManage002Mapper;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.dto.ProdFeediscountDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 报表模板管理新建service
 *
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see DisProdPlanManage002Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisProdPlanManage002Service extends CommonExportService<DisProdPlanManage002Dto> implements BaseProcessService<DisProdPlanManage002Dto> {

    @Autowired
    private RepProdInfoMapper repProdInfoMapper;

    @Autowired
    private DisProdPlanManage002Mapper disProdPlanManage002Mapper;


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage002Service.class);

    public RepProdInfo getPrdInfo(String prodId) {
        RepProdInfo prodInfo = repProdInfoMapper.selectByPrimaryKey(prodId);
        return prodInfo;
    }


    /**
     * 保存check
     *
     * @param dto
     * @return
     */
    public AjaxResult check(DisProdPlanManage002Dto dto) {
        Set<String> message = new HashSet<String>();
        if (StringUtils.isNotBlank(dto.getRaiseType())
                && StringUtils.equals(dto.getRaiseType(), DictCodeListExt3.新人行募集方式_私募.getValue())) {
            message.add("请填写产品冷静期");
        }
        if (StringUtils.isNotBlank(dto.getIsPeriod())
                && StringUtils.isNotBlank(dto.getIssuingWay())
                && StringUtils.equals(dto.getIsPeriod(), "01")
                && StringUtils.equals(dto.getIssuingWay(), "01")) {
            message.add("自动赎回日");
            message.add("客户实际持有天数");
        }

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
    public DisProdPlanManage002Dto getDisProdPlanManage002Dto(String code) {
        DisProdPlanManage002Dto dto = disProdPlanManage002Mapper.getDisProdPlanManage002Dto(code);
        return dto;
    }

    /**
     * 插入数据
     *
     * @param dto
     */
    public void insert(DisProdPlanManage002Dto dto) {
        dto.setProdPlanId(IDGenerator.generate(36));
        disProdPlanManage002Mapper.insert(dto);
    }

    /**
     * 更新数据
     *
     * @param dto
     * @return
     */
    public void update(DisProdPlanManage002Dto dto) {
        disProdPlanManage002Mapper.update(dto);
    }

    /**
     * 删除
     *
     * @param entity
     */
    public void delete(DisProdPlanManage002Dto entity) {
        String keyId = entity.getProdPlanId();
        if (StringUtils.isNotEmpty(keyId)) {
            DisProdPlanManage002Dto dto = new DisProdPlanManage002Dto();
            dto.setProdPlanId(keyId);
            dto.setDataStatus(DictCodeListExt3.信披_数据状态_删除D.getValue());
            this.update(dto);
        }
    }


    @Override
    public void doComplete(DisProdPlanManage002Dto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
            entity.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
            this.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            this.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            this.delete(entity);
        }
    }

    public Page<SelectOptionDto> getMontherAndCommonProdId(Map<String, Object> paraMap) {
        Page<SelectOptionDto> page = queryForPageForSelectReturn(()
                -> disProdPlanManage002Mapper.getMontherAndCommonProdId(paraMap));
        return page;
    }

    public List<DisProdPlanManage002Dto> checkInfo(DisProdPlanManage002Dto dto){
        return disProdPlanManage002Mapper.checkInfo(dto);
    }
}
