package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao.InvestorsQualityCheck001Mapper;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 投资者数量匹配合格率
 */
@Service
public class InvestorsQualityCheckSLPPService extends InvestorsQualityCheckBaseService {

    private static final List<String> prdSingleTotalList = Arrays.asList(DictCodeListExt1.投资者类别_普通个人.getValue(),
            DictCodeListExt1.投资者类别_高净值客户.getValue(), DictCodeListExt1.投资者类别_私人银行客户.getValue());

    private static final List<String> prdLegalTotalList = Arrays.asList(DictCodeListExt1.投资者类别_银行.getValue(),
            DictCodeListExt1.投资者类别_信托公司.getValue(), DictCodeListExt1.投资者类别_证券公司.getValue(),
            DictCodeListExt1.投资者类别_基金公司.getValue(), DictCodeListExt1.投资者类别_保险公司.getValue(),
            DictCodeListExt1.投资者类别_期货公司.getValue(), DictCodeListExt1.投资者类别_其他金融机构.getValue(),
            DictCodeListExt1.投资者类别_事业单位.getValue(), DictCodeListExt1.投资者类别_社会团体.getValue(),
            DictCodeListExt1.投资者类别_基金会.getValue(), DictCodeListExt1.投资者类别_社会服务机构.getValue(),
            DictCodeListExt1.投资者类别_机关.getValue(), DictCodeListExt1.投资者类别_其他法人机构.getValue());

    private static final List<String> prdUnLegalTotalList = Arrays.asList(DictCodeListExt1.投资者类别_信托产品.getValue(),
            DictCodeListExt1.投资者类别_券商资产管理计划.getValue(), DictCodeListExt1.投资者类别_基金资产管理计划.getValue(),
            DictCodeListExt1.投资者类别_期货资产管理计划.getValue(), DictCodeListExt1.投资者类别_保险资产管理计划.getValue(),
            DictCodeListExt1.投资者类别_社会保障类基金.getValue(), DictCodeListExt1.投资者类别_私募基金.getValue(),
            DictCodeListExt1.投资者类别_个人独资企业.getValue(), DictCodeListExt1.投资者类别_合伙企业.getValue(),
            DictCodeListExt1.投资者类别_其他非法人组织.getValue());

    @Autowired
    private InvestorsQualityCheck001Mapper investorsQualityCheck001Mapper;

    @Override
    public Page<InvestorsQualityCheck001Dto> queryPage(Map<String, Object> param) {
        int pageNum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int pageSize = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        // 查询不合格记录
        dealParamMap(param);
        return super.queryForPage(pageNum, pageSize, () -> investorsQualityCheck001Mapper.searchSLPPErr(param));
    }

    @Override
    public List<InvestorsQualityCheck001Dto> queryErrorRecList(Map<String, Object> param) {
        return null;
    }

    @Override
    public InvestorsQualityCheck001Dto cntHGL(Map<String, Object> param) {
        // 查询所有记录
        dealParamMap(param);
        List<InvestorsQualityCheck001Dto> allList = investorsQualityCheck001Mapper.searchSLPPAll(param);
        // 查询不合格记录
        List<InvestorsQualityCheck001Dto> unQualifiedList = investorsQualityCheck001Mapper.searchSLPPErr(param);

        return cntHGLComm(allList, unQualifiedList, SLPP);
    }

    @Override
    public void setParamComm(Map<String, Object> param) {
        param.put("statusNew", DictCodeListExt2.报送数据状态_新建.getValue());
        param.put("statusModify", DictCodeListExt2.报送数据状态_修改.getValue());
        param.put("prdSingleTotalStr", StringUtil.joinString(prdSingleTotalList, ","));
        param.put("prdLegalTotalStr", StringUtil.joinString(prdLegalTotalList, ","));
        param.put("prdUnLegalTotalStr", StringUtil.joinString(prdUnLegalTotalList, ","));
    }
}
