package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao.InvestorsQualityCheck001Mapper;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 产品状态合格率Service
 * @author zhangliang
 *
 */
@Service
public class InvestorsQualityCheckCPZTService extends InvestorsQualityCheckBaseService {
    
	@Autowired
	private InvestorsQualityCheck001Mapper investorsQualityCheck001Mapper;

    @Override
    public Page<InvestorsQualityCheck001Dto> queryPage(Map<String, Object> param) {
        param.put("queryFlag", "1");
        int pageNum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int pageSize = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        // 查询不合格记录
        dealParamMap(param);
        Page<InvestorsQualityCheck001Dto> page = super.queryForPage(pageNum, pageSize, () -> investorsQualityCheck001Mapper.searchCPZT(param));
        // 数据字典转换
        for (InvestorsQualityCheck001Dto dto : page.getRoot()) {
            dto.setPrdIssueSts(SysDataUtil.getNameFromDataDict(DictCode.发行状态, dto.getPrdIssueSts()));
        }

        return page;
    }

    @Override
    public InvestorsQualityCheck001Dto cntHGL(Map<String, Object> param) {
        // 查询所有记录
        dealParamMap(param);
        List<InvestorsQualityCheck001Dto> allList = investorsQualityCheck001Mapper.searchCPZT(param);
        // 查询不合格记录
        param.put("queryFlag", "1");
        List<InvestorsQualityCheck001Dto> unQualifiedList = investorsQualityCheck001Mapper.searchCPZT(param);

        return cntHGLComm(allList, unQualifiedList, CPZT);
    }

    @Override
    public void setParamComm(Map<String, Object> param) {
        param.put("statusNew", DictCodeListExt2.报送数据状态_新建.getValue());
        param.put("statusModify", DictCodeListExt2.报送数据状态_修改.getValue());
        param.put("issuingFail", DictCodeListExt1.发行状态_发行失败.getValue());
    }

}
