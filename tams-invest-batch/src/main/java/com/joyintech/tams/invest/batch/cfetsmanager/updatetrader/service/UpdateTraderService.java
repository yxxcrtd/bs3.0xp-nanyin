package com.joyintech.tams.invest.batch.cfetsmanager.updatetrader.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.MstCounterPartyMapper;
import com.joyintech.tams.common.base.entity.MstCfetsCustomerInfo;
import com.joyintech.tams.common.base.entity.MstCounterParty;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.cfetsmanager.cfetstrader.service.CfetsTraderService;
import com.joyintech.tams.invest.batch.cfetsmanager.updatetrader.dao.UpdateTraderMapper;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;

/**
 * 系统交易对手表更新
 * @author Administrator
 *
 */
@Service	
@Transactional(rollbackFor = Exception.class)
@Scope("prototype")
public class UpdateTraderService {
	/**
     * Logger
     */
    private static Logger logger = LoggerFactory.getLogger(CfetsTraderService.class);
    
    @Autowired
    private UpdateTraderMapper updateTraderMapper;
    
    @Autowired
    private MstCounterPartyMapper mstCounterPartyMapper;
    /**
     * 注入业务主键生产类
     */
    @Autowired(required = false)
    private BaseGetBussinessId baseGetBusinessId;
    /**
     * 系统交易对手表更新
     * @param batchDate  批处理执行时间
     * @throws Exception
     * @see
     * @since 1.0
     */
    public void updateTrader(Date batchDate) throws Exception {
    	logger.info("CfetsTraderService ---执行的操作选择的时间为：" + batchDate);
    	String date = DateUtil.format(batchDate, DateUtil.DEFAULT_OTHER_FORMAT);
    	//查询在交易对手中不存在的银行间机构
    	BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_交易对手管理);
    	List<MstCfetsCustomerInfo> mstCfetsCustomerInfos = updateTraderMapper.selectCustomerNew(date);
    	if(!StringUtil.isEmptyList(mstCfetsCustomerInfos)) {
    		for(MstCfetsCustomerInfo mstCfetsCustomerInfo:mstCfetsCustomerInfos) {
    			/*update by chenjiaxing 银行间交易对手信息录入之后需要生成对应的交易对手信息，如果是非法人机构默认为资管产品；
    			如果是法人机构需要匹配机构信息是否已存在，不存在建立为市场机构交易对手，存在的话，则不再建立 20210730 start*/
/*    			MstCounterParty mstCounterParty = new MstCounterParty();
    			mstCounterParty.setCounterId(baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                    bussinessCodeParameter) : IDGenerator.generate());
    			mstCounterParty.setCounterType(DictCodeListExt1.交易对手类型_非法人机构.getValue());
    			mstCounterParty.setCounterName(mstCfetsCustomerInfo.getPartyName());
    			mstCounterPartyMapper.insertSelective(mstCounterParty);*/
                /*判断当前当前机构客户类型 >0 非法人*/
                Map<String, Object> map = new HashMap<>();
                map.put("partyCustomerId", mstCfetsCustomerInfo.getPartyCustomerId());
                int temp01 = updateTraderMapper.selectCustomer01(map);
                int temp02 = updateTraderMapper.selectCustomer02(map);
                if(temp01 > 0){
                    //非法人相关处理
                    MstCounterParty mstCounterParty = new MstCounterParty();
                    mstCounterParty.setCounterId(baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                            bussinessCodeParameter) : IDGenerator.generate());
                    mstCounterParty.setCounterType(DictCodeListExt1.交易对手类型_资管产品.getValue());
                    mstCounterParty.setCounterName(mstCfetsCustomerInfo.getPartyName());
                    //获取主机构代码
                    MstCfetsCustomerInfo temp = updateTraderMapper.selectInfo(mstCfetsCustomerInfo.getPartyId());
                    mstCounterParty.setManagerId(temp.getPartyCustomerId());
                    mstCounterPartyMapper.insertSelective(mstCounterParty);
                }
                if(temp02 > 0){
                    //法人相关处理
                    MstCounterParty mstCounterParty = new MstCounterParty();
                    mstCounterParty.setCounterId(baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                            bussinessCodeParameter) : IDGenerator.generate());
                    mstCounterParty.setCounterType(DictCodeListExt1.交易对手类型_市场机构.getValue());
                    mstCounterParty.setCounterName(mstCfetsCustomerInfo.getPartyName());
                    mstCounterPartyMapper.insertSelective(mstCounterParty);
                }
    			/*update by chenjiaxing 银行间交易对手信息录入之后需要生成对应的交易对手信息，如果是非法人机构默认为资管产品；
    			如果是法人机构需要匹配机构信息是否已存在，不存在建立为市场机构交易对手，存在的话，则不再建立 20210730 end*/
    		}
    	}	
    	
    }
}
