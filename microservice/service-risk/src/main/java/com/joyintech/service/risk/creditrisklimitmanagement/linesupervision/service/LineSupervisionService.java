package com.joyintech.service.risk.creditrisklimitmanagement.linesupervision.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditmanagement.creditrulemanage.dto.CreditRuleInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linesupervision.dao.LineSupervisionMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.linesupervision.dto.LineSupervisionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;


/**
 * 额度监测
 * @author gejiaao
 * @version 1.0 2020年03月01日
 * @see LineSupervisionService
 * @since 1.0
 */
@Service("backLineSupervisionService")
public class LineSupervisionService extends BaseService<CreditRuleInfoDto> {
	
	@Autowired
	private LineSupervisionMapper lineSupervisionMapper;

    /**
     * 画面查询方法
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<CreditRuleInfoDto> selectForPage(int page, int length,Map<String, Object> param)
        throws Exception {
        String supStatus = param.get("supStatus").toString();
        if (StringUtil.equals(supStatus,"01,02,03")){
            param.put("supStatus","");
        }
        return super.queryForPage(page, length, () -> lineSupervisionMapper.selectForPage(param));
    }

    private List<LineSupervisionDto> getLineSupervisionList(Map<String, Object> param, String supStatus) {
        List<LineSupervisionDto> lineSupervisionDtoList = new ArrayList<>();
        String[] split = supStatus.split(",");
        for (String str : split) {
            param.put("supStatus", str);
            List<LineSupervisionDto> lineSupervisionDtos = lineSupervisionMapper.selectForPage(param);
            lineSupervisionDtoList.addAll(lineSupervisionDtos);
        }
        return lineSupervisionDtoList;
    }


    /**
     * 主体名称联想文本框service层实现
     *
     * @param map 检索参数
     * @return SelectOptionDto
     */
    public Page<SelectOptionDto> getCustomerName(Map<String, Object> map, int page, int length) {
        return super.queryForPageForSelectReturn(page, length,()->lineSupervisionMapper.getCustomerName(map));
    }

    /**
     * 查询分项交易明细
     * @param param
     * @return
     */
    public List<LineSupervisionDto> getCreditCustomerMst(Map<String, Object> param){
        return lineSupervisionMapper.getCreditCustomerMst(param);
    }
}
