
package com.joyintech.tams.report.submitexamination.investorsdetailcheck.service;

import java.util.Map;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.submitexamination.investorsdetailcheck.dao.InvestorsDetailCheck001Mapper;
import com.joyintech.tams.report.submitexamination.investorsdetailcheck.dto.InvestorsDetailCheck001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;

/**
 * 投资者明细核查Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestorsDetailCheck001Service extends BaseService<InvestorsDetailCheck001Dto>{

    /**
     * 投资者明细核查mapper
     */
    @Autowired
    private InvestorsDetailCheck001Mapper investorsDetailCheck001Mapper;

    /**
     * 根据页面的参数查询Table的数据
     * <功能详细描述>
     * @param pageParam 存储了画面参数的MAP
     * @return 存储了查询结果
     * @see
     * @since 1.0
     */
    public Page<InvestorsDetailCheck001Dto> searchTable(Map<String, Object> pageParam){
        // 报表名称
        pageParam.put("operateModeList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)pageParam.get("operateMode")));
        // 机构代码
        pageParam.put("checkResultList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)pageParam.get("checkResult")));
        pageParam.put("checkDateStart",StringUtil.isNotEmpty(pageParam.get("checkDateStart"))?pageParam.get("checkDateStart"):null);
        pageParam.put("checkDateEnd",StringUtil.isNotEmpty(pageParam.get("checkDateEnd"))?pageParam.get("checkDateEnd"):null);
        int page = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        Page<InvestorsDetailCheck001Dto> pageList =
                queryForPage(page,legth,()->investorsDetailCheck001Mapper.searchTable(pageParam));

        pageList.getRoot().forEach(dto -> {
            dto.setCheckTotalStr(SysDataUtil.getNameFromDataDict(DictCode.检查结果, dto.getCheckTotal()));
            dto.setCheckShamtStr(SysDataUtil.getNameFromDataDict(DictCode.检查结果, dto.getCheckShamt()));
            dto.setCheckShqtyStr(SysDataUtil.getNameFromDataDict(DictCode.检查结果, dto.getCheckShqty()));
            dto.setCheckSgqtyStr(SysDataUtil.getNameFromDataDict(DictCode.检查结果, dto.getCheckSgqty()));
            dto.setCheckSgamtStr(SysDataUtil.getNameFromDataDict(DictCode.检查结果, dto.getCheckSgamt()));
            dto.setCheckMjStr(SysDataUtil.getNameFromDataDict(DictCode.检查结果, dto.getCheckMj()));
            dto.setIsConsignStr(SysDataUtil.getNameFromDataDict(DictCode.是否_中债, dto.getIsConsign()));
            dto.setOperateModeStr(SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, dto.getOperateMode()));
        });
        return pageList;
    }

    
}