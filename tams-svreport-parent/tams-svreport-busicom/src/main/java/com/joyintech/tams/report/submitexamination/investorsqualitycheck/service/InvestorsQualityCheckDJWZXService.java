package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
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
        
    	// 1、取得对象记录数：	
    	BigDecimal checkRecTol = investorsQualityCheckDJWZXMapper.cntDJWZXTol(param);
        
    	// 2、取得错误记录数：
        List<InvestorsQualityCheck001Dto> unQualifiedList = investorsQualityCheckDJWZXMapper.searchDJWZXRec(param);

        //3、返回值
        resultDto.setPageType("8");
        resultDto.setUnQualified(String.valueOf(unQualifiedList.size()));
        resultDto.setTotal(checkRecTol.toString()); 
        resultDto.setRate(checkRecTol.subtract(BigDecimal.valueOf(unQualifiedList.size())).divide(checkRecTol).toString());

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
