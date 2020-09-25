package com.joyintech.service.settle.order.service;

import static com.joyintech.tams.invest.common.util.SettleCommonUtil.setParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.settle.order.dao.StlOrderManagementMapper;
import com.joyintech.tams.common.base.dao.InfPayOrdersStatusMapper;
import com.joyintech.tams.common.base.entity.InfPayOrdersStatus;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.settle.order.StlOrderManagementDto;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2019-01-03 11:28
 **/
@Service
public class StlOrderManagementService extends BaseService<StlOrderManagementDto> {

    @Autowired
    private StlOrderManagementMapper stlOrderManagementMapper;
    
    @Autowired
    private InfPayOrdersStatusMapper infPayOrdersStatusMapper;

    public Page<StlOrderManagementDto> findOrderData(int page, int length, Map<String, Object> params) {
        setParams(params);
        if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(params.get("finprodId"))) {
            params.put("finprodId", StringUtil.splitToList(",", (String)params.get("finprodId")));
        }
        if (StringUtil.isNotEmpty(params.get("paBusinessType"))) {
            params.put("paBusinessType", StringUtil.splitToList(",", (String)params.get("paBusinessType")));
        }
        return queryForPageForSelectReturn(page, length, () -> stlOrderManagementMapper.findOrderData(params));
    }

    /**
     * 投管接口（交易清算状态输入接口）
     * 
     * @param inputParam 参数
     * @return 是否成功
     * @see
     * @since 1.0
     */
	public String insertInfPayOrdersStatus(Map<String, String> inputParam) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tradeId", inputParam.get("tradeId"));
        params.put("YES_NO_NO", DictCodeListExt1.是否_否.getValue());
        params.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        // TODO 汇总指令的问题
        StringUtil.equals(inputParam.get("settleStatus"), DictCodeList.指令处理状态_处理失败.getValue());
        List<StlOrderManagementDto> orderList = stlOrderManagementMapper.findOrderIdByTradeId(
            params);
        if (!StringUtil.isEmptyList(orderList)) {
            for (StlOrderManagementDto item : orderList) {
                InfPayOrdersStatus infPayOrdersStatusNew = new InfPayOrdersStatus();
                InfPayOrdersStatus infPayOrdersStatus = infPayOrdersStatusMapper.selectByPrimaryKey(
                    item.getOrderId());
                if (infPayOrdersStatus != null) {
                    // 处理状态
                    infPayOrdersStatus.setOrderRetStatus(inputParam.get("settleStatus"));
                    // 备注
                    infPayOrdersStatus.setRemark(inputParam.get("remark"));
                    infPayOrdersStatus.setUpdateUser(infPayOrdersStatusNew.getUpdateUser());
                    infPayOrdersStatus.setUpdateTime(infPayOrdersStatusNew.getUpdateTime());
                    infPayOrdersStatusMapper.updateByPrimaryKeySelective(infPayOrdersStatus);
                }
                else {
                    // 指令编号
                    infPayOrdersStatusNew.setOrderId(item.getOrderId());
                    // 发送状态
                    infPayOrdersStatusNew.setSendStatus(DictCodeList.发送状态_发送成功.getValue());
                    // 接收状态
                    infPayOrdersStatusNew.setOrderRecStatus(DictCodeList.成功失败标志_成功.getValue());
                    // 处理状态
                    infPayOrdersStatusNew.setOrderRetStatus(inputParam.get("settleStatus"));
                    // 备注
                    infPayOrdersStatusNew.setRemark(inputParam.get("remark"));
                    infPayOrdersStatusMapper.insert(infPayOrdersStatusNew);
                }
            }
        }
        return DictCodeList.是否_是.getValue();
	}

    /**
     * 投管接口（交易发送接口时更新指令发送/接收状态）
     * 
     * @param param 参数
     * @return 是否成功
     * @see
     * @since 1.0
     */
    public String insertInfPayOrdersSendStatus(Map<String, String> inputParam) {
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("tradeId", inputParam.get("TRADE_ID"));
	    params.put("YES_NO_NO", DictCodeListExt1.是否_否.getValue());
	    params.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
	    List<StlOrderManagementDto> orderList = stlOrderManagementMapper.findOrderIdByTradeId(
	        params);
	    if (!StringUtil.isEmptyList(orderList)) {
	        for (StlOrderManagementDto item : orderList) {
	            InfPayOrdersStatus infPayOrdersStatusNew = new InfPayOrdersStatus();
	            InfPayOrdersStatus infPayOrdersStatus = infPayOrdersStatusMapper.selectByPrimaryKey(
	                item.getOrderId());
	            if (infPayOrdersStatus != null) {
	                // 处理状态
	                infPayOrdersStatus.setOrderRetStatus(inputParam.get("settleStatus"));
	                // 备注
	                infPayOrdersStatus.setRemark(inputParam.get("remark"));
	                infPayOrdersStatus.setUpdateUser(infPayOrdersStatusNew.getUpdateUser());
	                infPayOrdersStatus.setUpdateTime(infPayOrdersStatusNew.getUpdateTime());
	                infPayOrdersStatusMapper.updateByPrimaryKeySelective(infPayOrdersStatus);
	            }
	            else {
	                // 指令编号
	                infPayOrdersStatusNew.setOrderId(item.getOrderId());
	                // 发送状态
	                infPayOrdersStatusNew.setSendStatus(DictCodeList.发送状态_发送成功.getValue());
	                // 接收状态
	                infPayOrdersStatusNew.setOrderRecStatus(DictCodeList.成功失败标志_成功.getValue());
	                // 处理状态
	                infPayOrdersStatusNew.setOrderRetStatus(inputParam.get("settleStatus"));
	                // 备注
	                infPayOrdersStatusNew.setRemark(inputParam.get("remark"));
	                infPayOrdersStatusMapper.insert(infPayOrdersStatusNew);
	            }
	        }
	    }
	    return DictCodeList.是否_是.getValue();
	}
}
