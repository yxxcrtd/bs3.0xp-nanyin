package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public abstract class InvestorsQualityCheckBaseService extends BaseService<InvestorsQualityCheck001Dto> {

    /**
     * 产品状态合格率
     */
    protected String CPZT = "4";
    /**
     * 投资者数量匹配合格率
     */
    protected String SLPP = "5";

    /**
     * 查询数据
     * @return 数据结果
     */
    public abstract Page<InvestorsQualityCheck001Dto> queryPage(Map<String, Object> param);

    /**
     * 查询数据
     * @return 数据结果(不分页)
     */
    public abstract List<InvestorsQualityCheck001Dto> queryErrorRecList(Map<String, Object> param);


    /**
     * 汇总计算
     * @return 左侧汇总计算结果值
     */
    public abstract InvestorsQualityCheck001Dto cntHGL(Map<String, Object> param);

    /**
     * 设置参数查询
     * @param param 入参
     */
    public abstract void setParamComm(Map<String, Object> param);

    /**
     * 共通方法：查询界面选择的参数处理
     * @param param
     */
    public void dealParamMap(Map<String, Object> param) {
        String orgCode = (String)param.get("orgCode");
        List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, orgCode);
        param.put("orgCodeList", orgCodeList);

        setParamComm(param);
    }

    /**
     * 计算共通
     * @param allList 全部数据
     * @param unQualifiedList 不合格数据
     * @param PageType 类型
     * @return 计算对象
     */
    public InvestorsQualityCheck001Dto cntHGLComm(List<InvestorsQualityCheck001Dto> allList, List<InvestorsQualityCheck001Dto> unQualifiedList, String PageType) {
        InvestorsQualityCheck001Dto dto = new InvestorsQualityCheck001Dto();
        dto.setUnQualified(String.valueOf(unQualifiedList.size()));
        dto.setTotal(String.valueOf(allList.size()));
        if (!StringUtil.isEmptyList(allList)){
            dto.setRate("100");
            if (!StringUtil.isEmptyList(unQualifiedList)){
                dto.setRate(ArithmeticUtil.subtract(new BigDecimal(100), ArithmeticUtil.divide(ArithmeticUtil.multiply(new BigDecimal(unQualifiedList.size()), new BigDecimal(100)), new BigDecimal(allList.size()), 2)).toPlainString());
            }
        }
        dto.setPageType(PageType);

        return dto;
    }

}
