/*
 * 文件名：OwnerEquityChange001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.ownerequitychangenew.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.service.valuation.accounadjustmanage.ownerequitychangenew.dao.OwnerEquityChangeNew001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.ownerequitychangenew.dto.OwnerEquityChangeNew001Dto;
import com.joyintech.tools.util.ExcelTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @author lby
 * @version 1.0 2020年11月24日
 * @see OwnerEquityChangeNew001Service
 * @since 1.0
 */
@Service(value="serviceOwnerEquityChangeNew001Service")
public class OwnerEquityChangeNew001Service extends CommonExportService<OwnerEquityChangeNew001Dto> {

    @Autowired
    private OwnerEquityChangeNew001Mapper ownerEquityChangeNew001Mapper;

    public List<OwnerEquityChangeNew001Dto> selectBegin(Map<String, Object> param) {
        return ownerEquityChangeNew001Mapper.selectBegin(param);
    }

    public List<OwnerEquityChangeNew001Dto> selectAll(Map<String, Object> param) {
        return ownerEquityChangeNew001Mapper.selectAll(param);
    }
}
