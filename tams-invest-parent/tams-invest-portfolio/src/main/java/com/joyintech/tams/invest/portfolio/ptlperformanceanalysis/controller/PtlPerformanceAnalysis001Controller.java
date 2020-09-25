package com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.dto.PtlPerformanceAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.dto.PtlPerformanceLineDto;
import com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.service.PtlPerformanceAnalysis001Service;
import com.joyintech.tams.invest.portfolio.ptlpositionanalysis.dto.PtlPositionAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlpositionanalysis.service.PtlPositionAnalysis001Service;

@Controller
@PageId("PtlPerformanceAnalysis001")
@RequestMapping("/invest/portfolio/ptlperformanceanalysis/PtlPerformanceAnalysis001")
public class PtlPerformanceAnalysis001Controller extends BaseController  {
	
    @Autowired
    private PtlPositionAnalysis001Service ptlPositionAnalysis001Service;
	
    @Autowired
    private PtlPerformanceAnalysis001Service ptlPerformanceAnalysis001Service;
    
    /**
     * 开放计划组件
     */
    @Autowired
    private BussBenchMarketInfoService bussBenchMarketInfoService;

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	
    	
        this.setSelectOptionToModel(model, DictCode.收益类型, DictCode.展示方式,DictCode.比较指标,DictCode.无风险利率);
        // 平台日期
        model.addAttribute("platDate", iPlatformDate.getSysDateStr());
        
        //业绩基准
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
        // 利率行情
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.利率行情, null);
        for (SelectOptionDto dto : selectDto.getOptions()) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(dto.getId());
            dto1.setText(dto.getText());
            dto1.setObject(DictCodeListExt1.基准类型_2_利率.getValue());
            listFloatRate.add(dto1);
        }
        
      SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.曲线指数, null);
      for(SelectOptionDto dto : selectDto1.getOptions()){
          SelectOptionDto dto1 = new SelectOptionDto();
          dto1.setId(dto.getId());
          dto1.setText(dto.getText());
          dto1.setObject(DictCodeListExt1.基准类型_2_指数.getValue());
          listFloatRate.add(dto1);
      }

/*      SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.曲线代码, null);
      for(SelectOptionDto dto : selectDto2.getOptions()){
          SelectOptionDto dto1 = new SelectOptionDto();
          dto1.setId(dto.getId());
          dto1.setText(dto.getText());
          dto1.setObject(DictCodeListExt1.基准类型_2_收益率曲线.getValue());
          listFloatRate.add(dto1);
      }*/
        
        // 复合基准
//        List<SelectOptionDto> finBaseRuleInfoList = bussBenchMarketInfoService.getFinBaseRuleInfo();
//        for (SelectOptionDto dto : finBaseRuleInfoList) {
//            SelectOptionDto dto1 = new SelectOptionDto();
//            dto1.setId(dto.getId());
//            dto1.setText(dto.getText());
//            dto1.setObject(DictCodeListExt1.基准类型_2_复合.getValue());
//            listFloatRate.add(dto1);
//        }
        model.addAttribute("INDEX_ID_SOURCE", JsonUtils.objectArray2Json(listFloatRate.toArray()));
       
        return super.init(model);
    }

    /**
     * 组合持仓明细查询
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlPerformanceAnalysis001Dto> selectAll()
            throws Exception {
        
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<PtlPerformanceAnalysis001Dto> list = ptlPerformanceAnalysis001Service.selectForPage(map);
//        Page<PtlPositionAnalysis001Dto> page = ptlPositionAnalysis001Service.selectForPage(map);
        return list;
    }
    
    
    /**
     * 组合持仓明细查询
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findLineData")
    public PtlPerformanceLineDto findLineData()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(super.getRequest());
    	return ptlPerformanceAnalysis001Service.findLineData(map);

    }
    
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> exportNew() throws Exception{
        return ptlPerformanceAnalysis001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 获取动态列结果
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("getTableCols")
    public String getTableCols()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
      return ptlPerformanceAnalysis001Service.getDataTableCols(map);
    }
    
    @ResponseBody
    @RequestMapping("getShowDataTableCols")
    public String getShowDataTableCols()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
      return ptlPerformanceAnalysis001Service.getShowDataTableCols(map);
    } 
}
