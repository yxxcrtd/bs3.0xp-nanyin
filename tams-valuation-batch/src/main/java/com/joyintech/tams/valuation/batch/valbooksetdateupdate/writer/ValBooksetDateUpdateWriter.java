/*
 * 文件名：ValBooksetDateUpdateWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valbooksetdateupdate.writer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.valbooksetdateupdate.dao.ValBooksetDateUpdateMapper;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年7月11日
 * @see ValBooksetDateUpdateWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValBooksetDateUpdateWriter extends BaseWriter<NeedToWriteEntity>{

    @Autowired
    private ValBooksetDateUpdateMapper valBooksetDateUpdateMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        Set<String> booksetIds = (Set<String>) this.getParams().get("booksetIds");
        //1.更新入账信息表 是否已入账为是
        Date bookDate=(Date) this.getParams().get(BookConstant.BOOK_DATE);
        valBooksetDateUpdateMapper.updateBokDealAccounting(new ArrayList<String>(booksetIds),bookDate);
       
        /**add by yanya on 20191031 根据账套日开关配置系统参数是否更新账套日期   （从单估值合并过来） start**/
        String autoDaySwitch = CacheUtil.getSysParam("AUTO_DAY_SWITCH");
        if(StringUtil.isEmpty(autoDaySwitch) || !"OFF".equals(autoDaySwitch)) {
            //2.更新账套日期
            valBooksetDateUpdateMapper.updateBooksetDate(new ArrayList<String>(booksetIds));
        }
        /**add by yanya on 20191031 根据账套日开关配置系统参数是否更新账套日期 （从单估值合并过来） end**/
    }


}
