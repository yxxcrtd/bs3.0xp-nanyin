/*
 * 文件名：ResetBaseRuleInfoDelReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealGenReaderResult;
import com.joyintech.tams.invest.batch.mproddivreindealgen.service.MProdDivReinDealDelReaderService;

/**
 * 删除处理Reader
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see MProdDivReinDealGenDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MProdDivReinDealGenDelReader extends BaseReader<MProdDivReinDealGenReaderResult>{
	@Autowired
    private MProdDivReinDealDelReaderService readerService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MProdDivReinDealGenReaderResult> createList() {
        return readerService.execute(this.getParams());
    }

}
