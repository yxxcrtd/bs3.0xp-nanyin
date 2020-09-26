package com.joyintech.tams.valuation.system.batchstart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dao.SysBatchGrpPrdlinkMapper;
import com.joyintech.framework.base.dao.SysBatchPrdGrpMapper;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.base.entity.SysBatchPrdGrp;
import com.joyintech.framework.base.service.ProductService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysCodeUtil;
import com.joyintech.system.batch.batchprocess.dao.SysBatchProcess003Mapper;
import com.joyintech.system.batch.batchprocess.dto.SysBatchPrdGrpDto001;
import com.joyintech.tams.valuation.system.batchstart.dao.ValBatchStart001Mapper;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class ValBatchProcess003Service extends BaseService<SysBatchPrdGrpDto001> implements BaseProcessService<SysBatchPrdGrpDto001> {

    @Autowired
    private ValBatchStart001Mapper valBatchStart001Mapper;
    
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Page<SysBatchPrdGrpDto001> findAllPrdGroup(Map<String, Object> keyword)
        throws Exception {
        return super.queryForPage(() -> valBatchStart001Mapper.findAllPrdGroup(keyword));
    }

	@Override
	public void doComplete(SysBatchPrdGrpDto001 arg0, OperationType arg1) {
		// TODO Auto-generated method stub
		
	}
}
