package com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssets001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流动性资产配置service 〈功能详细描述〉
 * 
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskFlowAssets001Service
 * @since 1.0
 */
@Service
public class FlowRiskFlowAssets001Service extends CommonExportService<FlowRiskFlowAssets001Dto> 
    implements BaseProcessService<FlowRiskFlowAssets001Dto> {

    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FlowRiskFlowAssets001Dto entity, OperationType type) {
        // TODO Auto-generated method stub
        String action = StringUtil.EMPTY;
        if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskFlowAssets001/" + action, entity,
            Integer.class);
    }

    /**
     * 根据参数代码和参数值得到明细的资产类型（一级二级等）
     * <一句话功能简述>
     * <功能详细描述>
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
	public List<FlowRiskFlowAssets001Dto> getFloAssTypDetByCodVal(Map<String, Object> parameters) {
		return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskFlowAssets001/getFloAssTypDetByCodVal",
				parameters, FlowRiskFlowAssets001Dto.class);
	}
	
	/**
	 * 得到一级流动性资产
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<FlowRiskFlowAssets001Dto> getFloAssOne() {
        return restTemplate.getForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskFlowAssets001/getFloAssOne",
              FlowRiskFlowAssets001Dto.class);
    }
	
	/**
	 * 得到二级流动性资产
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<FlowRiskFlowAssets001Dto> getFloAssTwo() {
        return restTemplate.getForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskFlowAssets001/getFloAssTwo",
              FlowRiskFlowAssets001Dto.class);
    }
	
	/**
	 * 获取资产二级和三级分类的树数据
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<FlowRiskFlowAssets001Dto> getFloAssTreeData() {
        return restTemplate.getForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskFlowAssets001/getFloAssTreeData",
              FlowRiskFlowAssets001Dto.class);
    }
	
	/**
	 * 初始化页面的备注信息
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public FlowRiskFlowAssets001Dto selectFlowAssOneMes(){
	    return restTemplate.getForObject(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskFlowAssets001/selectFlowAssOneMes", 
	        FlowRiskFlowAssets001Dto.class);
	}
	
	/**
	 * 根据参数代码和参数值拼接sql
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param flowAssType
	 * @param flowAssTypeVal
	 * @return
	 * @see
	 * @since 1.0
	 */
	public String getFloAssSqlByCodVal(String flowAssType,String flowAssTypeVal) {
	    Map<String, Object> map = new HashMap<>();
	    List<FlowRiskFlowAssets001Dto> listDet = getFloAssTypDetByCodVal(map);
        StringBuffer sql = new StringBuffer();
        if(StringUtil.equals(flowAssTypeVal, DictCodeListRsk.流动性资产分类_一级流动性资产.getValue())){
            //资产一级分类，从元素表映射（横表），source_type存资产一级分类
            sql.append(" SOURCE_TYPE " + " in (");
            StringBuffer sqlIn = new StringBuffer();
            for(FlowRiskFlowAssets001Dto dto:listDet){
                //级别为01，即为一级资产分类
                if(StringUtil.equals(dto.getLev(), "01")){
                    sqlIn.append("\""+dto.getSouceCode()+"\",");
                }
            }
            if(sqlIn.toString().length() !=0){
                String sqlInStr = sqlIn.toString().substring(0, sqlIn.toString().length() - 1);
                sql = sql.append(sqlInStr+")");
            }
            //二级资产分类【还得加上资产类型为二级资产分类，即SOUCE_TYPE = xxx_2】
            if(sql.toString().length() != 0){
                sql.append(" or ");
            }
            sql.append(" PARAM_VALUE " + " in (");
            StringBuffer sqlDetIn = new StringBuffer();
            for(FlowRiskFlowAssets001Dto dto:listDet){
                //级别为02，即为二级资产分类
                if(StringUtil.equals(dto.getLev(), "02")){
                    sqlDetIn.append("\""+dto.getSouceValue()+"\",");
                }
            }
            if(sqlDetIn.toString().length() !=0){
                String sqlDetInStr = sqlDetIn.toString().substring(0, sqlDetIn.toString().length() - 1);
                sql = sql.append(sqlDetInStr+")");
            }
        }
        
        
        return sql.toString();
    }
	
    
   
}
