package com.joyintech.service.product.resultscovering.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
//import com.joyintech.framework.security.SecurityUtil;
//import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.resultscovering.dao.FinResultsCoveringMapper;
import com.joyintech.service.product.resultscovering.dto.FinResultsCovering001Dto;
import com.joyintech.service.product.resultscovering.dto.FinResultsCovering002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

@Service("backFinResultsCoveringService")
public class FinResultsCoveringService extends BaseService<FinResultsCovering001Dto> {

    @Autowired
    private FinResultsCoveringMapper finResultsCoveringMapper;
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * 查询回补信息
     * 
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Page<FinResultsCovering001Dto> selectForPage(int page, int length,
                                                        Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("param", pageParam);
        pageParam.put("ANAPLEROTIC_TYPE_02", DictCodeList.回补方式_自动回补.getValue());
        Page<FinResultsCovering001Dto> resultPage = this.queryForPage(page, length,
            () -> finResultsCoveringMapper.selectForPage(pageParam));
        return resultPage;
    }

    public FinResultsCovering002Dto selectBaseInfo(@RequestBody Map<String, Object> pageParam) {
        pageParam.put("param", pageParam);
        //判断该产品是否为高频
        pageParam.put("CASH_TYPE_1_BJ", DictCodeListExt1.现金流一级类型_本金.getValue());
        pageParam.put("CASH_TYPE_2_C01", DictCodeListExt1.现金流二级类型_本金.getValue());
        pageParam.put("OPENMODE_02", DictCodeList.开放模式_高频开放.getValue());
        pageParam.put("currentDate", iPlatformDate.getSysDate());
        pageParam.put("ISSUE_MODE_01", DictCodeList.开放类型_封闭式.getValue());
        if(StringUtil.isNotEmpty(pageParam.get("prodId"))){
        	//当小于1时为高频开放
        	if(finResultsCoveringMapper.isOpenMode02(pageParam)<1){
        		pageParam.put("isOpenMode02", "02");
        	}
        }
        return finResultsCoveringMapper.selectBaseInfo(pageParam);
    }

    public BigDecimal getSumAmt(@RequestBody FinResultsCovering002Dto dto)
        throws ParseException {
        if (dto.getCoveringDate() != null) {
            String date1 = DateUtils.format(dto.getCovCycleEndDate(), "yyyy-MM-dd");
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            Date date = format.parse(date1);
            dto.setCovCycleEndDate(date);
        }
        return finResultsCoveringMapper.getSumAmt(dto);
    }

    public FinResultsCovering002Dto selectInfo(@RequestBody Map<String, Object> pageParam) {
        pageParam.put("param", pageParam);
        return finResultsCoveringMapper.selectInfo(pageParam);
    }

    public List<FinResultsCovering001Dto> findPtradeid(@RequestBody String tradeId) {
        return finResultsCoveringMapper.findPtradeid(tradeId);

    }

    public List<FinResultsCovering002Dto> selectInfoCovering(@RequestBody Map<String, Object> pageParam) {
        pageParam.put("param", pageParam);
      //判断该产品是否为高频
        pageParam.put("CASH_TYPE_1_BJ", DictCodeListExt1.现金流一级类型_本金.getValue());
        pageParam.put("CASH_TYPE_2_C01", DictCodeListExt1.现金流二级类型_本金.getValue());
        pageParam.put("OPENMODE_02", DictCodeList.开放模式_高频开放.getValue());
        pageParam.put("currentDate", iPlatformDate.getSysDate());
        pageParam.put("ISSUE_MODE_01", DictCodeList.开放类型_封闭式.getValue());
        if(StringUtil.isNotEmpty(pageParam.get("prodId"))){
        	//当小于1时为高频开放
        	if(finResultsCoveringMapper.isOpenMode02(pageParam)<1){
        		pageParam.put("isOpenMode02", "02");
        	}
        }
        return finResultsCoveringMapper.selectInfoCovering(pageParam);
    }

    /**
     * 产品代码下拉框
     * 
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    public Page<SelectOptionDto> queryProdIdInfo(Map<String, Object> paramMap, int page,
                                                 int length) {
        paramMap.put("FINPROD_TYPE", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        paramMap.put("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        
        paramMap.put("ISSUE_MODE_02", BussinessDictCodeList.开放类型_开放式.getValue());
        paramMap.put("CASH_TYPE_1_06", DictCodeListExt1.现金流一级类型_回补.getValue());
        paramMap.put("ISSUE_STATUS_01", DictCodeListExt1.发行状态_发行成功.getValue());
        return this.queryForPageForSelectReturn(page, length,
            () -> finResultsCoveringMapper.queryProdIdInfo(paramMap));
    }

}