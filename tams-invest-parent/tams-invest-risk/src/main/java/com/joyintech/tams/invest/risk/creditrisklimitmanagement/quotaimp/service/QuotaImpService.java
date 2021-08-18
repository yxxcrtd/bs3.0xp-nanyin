package com.joyintech.tams.invest.risk.creditrisklimitmanagement.quotaimp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.invest.common.util.ExcelUtilForRisk;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.quotaimp.dto.QuotaImpDto;
import com.joyintech.tams.systemtools.util.CommonUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class QuotaImpService extends CommonExportService<QuotaImpDto>{
	
	/**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    
    public Page<QuotaImpDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME +
            "/quotaimp/findPage",
            pageParam, QuotaImpDto.class);
    }
	

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
        return restTemplate.postForObject(
        		ServiceApplication.RISK_SERVICE_NAME + "/creditrisklimitmanagement/quotaimp/checkConfirmMsgForImportData", 
	       		param,
	       		AjaxResult.class);
    }
    
    
    public List<ExcelValidDto> importData(String fileName,String impType) {
     	Map<String,Object> param = new HashMap<String,Object>();
     	if("04".equals(impType)){
        	param.put("dataList", ExcelUtilForRisk.getABSDataList(fileName));
        	param.put("tempList", ExcelUtilForRisk.getABSTempList(fileName));
     	}else {
        	param.put("dataList", ExcelUtilForRisk.getDataList(fileName));
        	param.put("tempList", ExcelUtilForRisk.getTempList(fileName));
     	}

    	param.put("userId", CommonUtil.getUid());
    	param.put("impType", impType);
		BussinessCodeParameter idParam = new BussinessCodeParameter();
	//	idParam.setCommonBusinessType(DictCodeList.风险管理_质押式回购导入白名单);
    //	String seqId = baseGetBusinessId.buildBussinessCode(idParam);
    // 	param.put("seqId", seqId);
    	return restTemplate.postForList(
    			ServiceApplication.RISK_SERVICE_NAME + "/quotaimp/importData", 
	       		param,
	       		ExcelValidDto.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/risk/creditrisklimitmanagement/quotaimp/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<QuotaImpDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<QuotaImpDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
