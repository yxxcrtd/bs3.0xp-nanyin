package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao.InvestorsQualityCheckDJWZXMapper;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;

/**
 * 登记完整性检查Service
 * @author wdb
 *
 */
@Service
public class InvestorsQualityCheckDJWZXService extends InvestorsQualityCheckBaseService {

    
    @Autowired
    private InvestorsQualityCheckDJWZXMapper investorsQualityCheckDJWZXMapper;

    @Override
    public InvestorsQualityCheck001Dto cntHGL(Map<String, Object> param) {
    	InvestorsQualityCheck001Dto resultDto = new InvestorsQualityCheck001Dto();
        
    	HashMap<String, Object> proParam = new HashMap<>();
        //画面输入参数设置-查询日期
        proParam.put("searchDate", param.get("searchDate")); 
        //画面输入参数设置-部门
        String orgCode = (String)param.get("orgCode");
        List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, orgCode);
        proParam.put("orgCodeList", orgCodeList); 
    	// 1、取得对象记录数：	
    	BigDecimal checkRecTol = investorsQualityCheckDJWZXMapper.cntDJWZXTol(proParam);
        
    	// 2、取得错误记录数：
        List<InvestorsQualityCheck001Dto> unQualifiedList = investorsQualityCheckDJWZXMapper.searchDJWZXRec(proParam);

        //3、返回值
        resultDto.setPageType("8");
        resultDto.setUnQualified(String.valueOf(unQualifiedList.size()));
        resultDto.setTotal(checkRecTol.toString()); 
        //检查对象为0的情况下：
        if(checkRecTol.compareTo(BigDecimal.ZERO) == 1) {
        	resultDto.setRate(checkRecTol.subtract(BigDecimal.valueOf(unQualifiedList.size())).divide(
        			checkRecTol,2,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP).toPlainString());
        }       

        return resultDto;
    }
    
    @Override
    public Page<InvestorsQualityCheck001Dto> queryPage(Map<String, Object> param) {
        int pageNum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int pageSize = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        // 查询不合格记录
        dealParamMap(param);
        return super.queryForPage(pageNum, pageSize, () -> investorsQualityCheckDJWZXMapper.searchDJWZXRec(param));
    }

    @Override
    public List<InvestorsQualityCheck001Dto> queryErrorRecList(Map<String, Object> param) {
        return null;
    }

    

    @Override
    public void setParamComm(Map<String, Object> param) {
        param.put("statusNew", DictCodeListExt2.报送数据状态_新建.getValue());
        param.put("statusModify", DictCodeListExt2.报送数据状态_修改.getValue());
    }
}
