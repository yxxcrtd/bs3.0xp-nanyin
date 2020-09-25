/*
 * 文件名：MstIndexQuotationInfo001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstindexquotationinfo.validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstindexquotationinfo.dto.MstIndexQuotationInfo001Dto;
import com.joyintech.tams.invest.basicdata.mstindexquotationinfo.service.MstIndexQuotationInfo001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 指数行情检查类
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月14日
 * @see MstIndexQuotationInfo001Validator
 * @since 1.0
 */
@Transactional
@Component
public class MstIndexQuotationInfo001Validator implements BeanValidator {

    /**
     * 注入生成生体类的Mapper类
     */
    @Autowired
    private MstIndexQuotationInfo001Service mstIndexQuotationInfo001Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        // 获取验证实体类
        MstIndexQuotationInfo001Dto dto = (MstIndexQuotationInfo001Dto)value;// 获取实体类
        ArrayList<ValidMessage> errorList = new ArrayList<>();// 生成错误集合
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        if (WorkFlowUtil.isNotFromToDoList()) {
            // 判断操作类型
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                // 判断是否获取到删除的项目
                if (dto.getSelectedItem() != null || dto.getSelectedItem().size() > 0) {
                    // 遍历选择的项目
                    for (MstIndexQuotationInfo001Dto cur : dto.getSelectedItem()) {
                        // 判断估值时间是否小于平台时间
                        if (cur.getEffDate().compareTo(sysDate) < 0) {
                            // 添加错误信息 BUSS_COM_E_0015{0}："生效日:"生效日{1}: 平台日期
                            errorList.add(new ValidMessage(getMessage(
                                "BUSS_COM_E_0015", getMessage("MstIndexQuotationInfo001.effDate")
                                                   + DateUtil.format(dto.getEffDate()),
                                DateUtil.format(sysDate).toString())));
                        }
                    }
                }

                // 判断操作类型
            }
            else if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                if (!dto.getMarketId().isEmpty() && dto.getEffDate() != null) {
                    if (mstIndexQuotationInfo001Service.selectByPrimaryKey(dto) != null) {
                        // 添加错误信息BUSS_COM_E_0006{0}:"行情代码:"+行情代码+",生效日:"+生效日
                        errorList.add(new ValidMessage(getMessage(
                            "BUSS_COM_E_0006", getMessage("MstIndexQuotationInfo001.indexName")
                                               + getMessage("MstIndexQuotationInfo001.effDate"))));
                    }
                    if (dto.getEffDate().compareTo(sysDate) < 0) {
                        // 添加错误信息BUSS_COM_E_0004{0}："生效日:"生效日{1}: 平台日期
                        errorList.add(new ValidMessage(getMessage(
                            "BUSS_COM_E_0004", getMessage("MstIndexQuotationInfo001.effDate")
                                               + DateUtil.format(dto.getEffDate()),
                            DateUtil.format(sysDate).toString())));
                    }

                }
            }
            else {
                if (dto.getEffDate().compareTo(sysDate) < 0) {// 判断发布时间是否小于平台时间
                    if ("submit".equals(dto.getOperateType())
                        || StringUtil.equals("save", dto.getOperateType())) {// 判断是否是提交事件
                        // 添加错误信息BUSS_COM_E_0004{0}："生效日:"生效日{1}: 平台日期
                        errorList.add(new ValidMessage(getMessage(
                            "BUSS_COM_E_0004", getMessage("MstIndexQuotationInfo001.effDate")
                                               + DateUtil.format(dto.getEffDate()),
                            DateUtil.format(sysDate))));
                    }
                    else {
                        // 添加错误信息BUSS_COM_E_0018{0}："生效日:"生效日{1}: 平台日期
                        errorList.add(new ValidMessage(getMessage(
                            "BUSS_COM_E_0018", getMessage("MstIndexQuotationInfo001.effDate")
                                               + DateUtil.format(dto.getEffDate()),
                            DateUtil.format(sysDate))));
                    }
                }
            }
            if (StringUtil.equals(dto.getOperateType(), "save")
                || StringUtil.equals(dto.getOperateType(), "submit")) {
                if (dto.getRelaeseDate() != null && dto.getEffDate() != null
                    && DateUtil.compareDate(dto.getRelaeseDate(), dto.getEffDate()) > 0) {
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0004",
                        getMessage("MstIndexQuotationInfo001.effDate"),
                        getMessage("MstIndexQuotationInfo001.relaeseDate"))));
                }
                if (dto.getRelaeseDate() != null && dto.getEffDate() != null
                    && DateUtil.compareDate(dto.getRelaeseDate(), sysDate) < 0) {
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0004",
                        getMessage("MstIndexQuotationInfo001.relaeseDate"),
                        getMessage("MstIndexQuotationInfo001.date"))));
                }
            }
        }
        return errorList;
    }
}
