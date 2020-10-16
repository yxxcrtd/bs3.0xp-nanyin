/*
 * 文件名：MstBankTranFeeManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年3月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstbanktranfeemanage.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.TrdFeeConfigDetailMapper;
import com.joyintech.tams.common.base.dao.TrdFeeConfigMapper;
import com.joyintech.tams.common.base.entity.TrdFeeConfig;
import com.joyintech.tams.common.base.entity.TrdFeeConfigDetail;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstbanktranfeemanage.dao.MstBankTranFeeManageMapper;
import com.joyintech.service.basicdata.mstbanktranfeemanage.dto.MstBankTranFeeManageDto;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年3月25日
 * @see MstBankTranFeeManageService
 * @since 1.0
 */
@Service("backMstBankTranFeeManageService")
public class MstBankTranFeeManageService extends BaseService<MstBankTranFeeManageDto> {
    @Autowired
    private MstBankTranFeeManageMapper mstBankTranFeeManageMapper;
    @Autowired
    private TrdFeeConfigMapper trdFeeConfigMapper;
    @Autowired
    private TrdFeeConfigDetailMapper trdFeeConfigDetailMapper;
    @Autowired
    private MstBankTranFeeManageService mstBankTranFeeManageService;
    /**
     * 分页查询方法
     * <功能详细描述>
     * @param page
     * @param length
     * @param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<MstBankTranFeeManageDto> selectForPage(int page, int length,
                                                       Map<String, Object> pageParam) throws Exception {
        pageParam.put("TRADE_PLACE_01", BussinessDictCodeList.交易场所_银行间.getValue());
        pageParam.put("CHARGE_KIND_02", BussinessDictCodeList.计费类型_固定金额.getValue());
        return super.queryForPage(page, length, () -> mstBankTranFeeManageMapper.selectForPage(pageParam));
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public MstBankTranFeeManageDto getEntity(Map<String, Object> map) {
        map.put("TRADE_PLACE_01", BussinessDictCodeList.交易场所_银行间.getValue());
        return mstBankTranFeeManageMapper.getEntity(map);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(MstBankTranFeeManageDto dto) {
        String batchSeq = dto.getBatchSeq();
        //根据页面的批次号查询出交易费用配置表和交易费用配置明细表的流水号
        List<MstBankTranFeeManageDto> list = mstBankTranFeeManageService.getSeqNoByBatchSeq(batchSeq);
        if(list != null && list.size()>0){
            for(MstBankTranFeeManageDto d:list){
                trdFeeConfigMapper.deleteByPrimaryKey(d.getSeqNo());
                trdFeeConfigDetailMapper.deleteByPrimaryKey(d.getDetailSeqNo());
            }
        }
        
        return 1;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param batchSeq
     * @return
     * @see
     * @since 1.0
     */
    public List<MstBankTranFeeManageDto> getSeqNoByBatchSeq(String batchSeq) {
        return mstBankTranFeeManageMapper.getSeqNoByBatchSeq(batchSeq);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @see
     * @since 1.0
     */
    public int insert(MstBankTranFeeManageDto dto) throws IllegalAccessException, InvocationTargetException {
        for(MstBankTranFeeManageDto d:dto.getSelectedItem()){
            TrdFeeConfig trdFeeConfig = new TrdFeeConfig();
            //获取交易费用配置dto
            BeanUtil.copyProperties(trdFeeConfig,d);
            //多级法人改造以BatchSeq存入data_auth_notify_inst表，修改时不能以新的newBatchSeq存入TRD_FEE_CONFIG表，故还是以BatchSeq
            //存入TRD_FEE_CONFIG表  modify caojin 2020/7/6  start
            //存入批次号
            trdFeeConfig.setBatchSeq(dto.getBatchSeq());
            //多级法人改造  modify caojin 2020/7/6  start
            //交易场所设置为银行间
            trdFeeConfig.setTradeMarket(BussinessDictCodeList.交易场所_银行间.getValue());
            trdFeeConfig.setSeqNo(IDGenerator.generate());
            trdFeeConfigMapper.insert(trdFeeConfig);
            TrdFeeConfigDetail trdFeeConfigDetail = new TrdFeeConfigDetail();
            //获取交易费用配置明细dto
            BeanUtil.copyProperties(trdFeeConfigDetail,dto);
            trdFeeConfigDetail.setSeqNo(trdFeeConfig.getSeqNo());
            trdFeeConfigDetail.setConfigSeqNo(trdFeeConfig.getSeqNo());
            if(StringUtil.isNotEmpty(trdFeeConfigDetail.getChargeRate())) {
                trdFeeConfigDetail.setChargeRate(trdFeeConfigDetail.getChargeRate().divide(new BigDecimal(100)));
            }
            trdFeeConfigDetailMapper.insert(trdFeeConfigDetail);
        }
        
        return 1;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    public int update(MstBankTranFeeManageDto dto) throws IllegalAccessException, InvocationTargetException {
        //先将原批次号下的配置和配置明细删除
        delete(dto);
        //再将新的配置插入
        insert(dto);
        return 1;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param batchSeq
     * @return
     * @see
     * @since 1.0
     */
    public boolean isExit(String batchSeq) {
        return getSeqNoByBatchSeq(batchSeq).size()>0;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<MstBankTranFeeManageDto> getAllDto() {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("TRADE_PLACE_01", BussinessDictCodeList.交易场所_银行间.getValue());
        return mstBankTranFeeManageMapper.getAllDto(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param parameter
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<MstBankTranFeeManageDto> getBusinessData(WflValidInputParameter parameter) {
      //查询流程中数据"01"
        parameter.setSeachMode("01");
        List<MstBankTranFeeManageDto> list = mstBankTranFeeManageMapper.getBusinessData(parameter);
        return list;
    }
    
    // add by wangguoxian 银行间费用设置 20190718 start
    /**
     * 
     * 费用设置查重
     * @param param 入参 tradeType:交易品种，ps:交易方向，termType:期限品种
     * @return 重复条数
     * @see
     * @since 1.0
     */
    public Integer selExistCnt(Map<String, Object> param) {
        param.put("PINAN_ASSET_TYPE_F07", BussinessDictCodeList.平安资产类型_回购.getValue());
        param.put("PINAN_ASSET_TYPE_F10", BussinessDictCodeList.平安资产类型_买断式回购.getValue());
        return mstBankTranFeeManageMapper.selExistCnt(param);
        
    }
    // add by wangguoxian 银行间费用设置 20190718 end
    public Integer selExistCnt1(Map<String, Object> param) {
        param.put("PINAN_ASSET_TYPE_F07", BussinessDictCodeList.平安资产类型_回购.getValue());
        param.put("PINAN_ASSET_TYPE_F10", BussinessDictCodeList.平安资产类型_买断式回购.getValue());
        List<String> list =  mstBankTranFeeManageMapper.selExistCnt1(param);
        if(list == null)
        	return 0;
        return list.size();        
    }
}
