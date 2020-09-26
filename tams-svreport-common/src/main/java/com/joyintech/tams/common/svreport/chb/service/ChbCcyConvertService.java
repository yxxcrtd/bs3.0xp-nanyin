/*
 * 文件名：ChbDeadlineService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：Administrator
 * 修改时间：2017年12月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.common.svreport.chb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.common.base.entity.ChbMstExchangeInfo;
import com.joyintech.tams.common.svreport.chb.dao.ChbCcyConvertMapper;

/**
 * 汇率换算
 * @author lingxq
 * @version 1.0 2019年02月10日
 * @see ChbCcyConvertService
 * @since 1.0
 */
@Service
public class ChbCcyConvertService extends BaseService<ChbMstExchangeInfo>{

	@Autowired
    private ChbCcyConvertMapper chbCcyConvertMapper;
    

    
    /**
     * 获取汇率详情
     * @param ccy 传入币种
     * @param date 传入日期
     * @return 汇率行情
     * @see
     * @since 1.0
     */
    public List<ChbMstExchangeInfo> getExchangerate(String ccy,Date date){
        return chbCcyConvertMapper.getExchangerate(ccy, date);
    }
}
