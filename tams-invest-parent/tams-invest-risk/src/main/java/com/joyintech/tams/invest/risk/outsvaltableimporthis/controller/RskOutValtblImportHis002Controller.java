package com.joyintech.tams.invest.risk.outsvaltableimporthis.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.risk.outsvaltableimporthis.dto.RskOutValtblImportHis001Dto;
import com.joyintech.tams.invest.risk.outsvaltableimporthis.service.RskOutValtblImportHis002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 *  估值表读取处理监控
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2021年5月22日
 * @see RskOutValtblImportHis002Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/invest/risk/outsvaltableimporthis/RskOutValtblImportHis002")
@PageId("RskOutValtblImportHis002")
public class RskOutValtblImportHis002Controller extends BaseController {

    @Autowired
    private RskOutValtblImportHis002Service rskOutValtblImportHis002Service;

    @Override
    public String init(Model model) {

        model.addAttribute("fileName",this.getParam("fileName"));
        model.addAttribute("portfolioName",this.getParam("portfolioName"));
        model.addAttribute("valdate",this.getParam("valdateStr"));

        return super.init(model);
    }

    /**
     * 根据估值表导入信息记录表主键获取历史记录
     *
     * @return 值表导入信息记录历史数据
     * @see
     */
    @RequestMapping("selectTraceByKey")
    @ResponseBody
    public List<RskOutValtblImportHis001Dto> selectTraceByKey() {
        RskOutValtblImportHis001Dto keyDto = new RskOutValtblImportHis001Dto();
        Map<String , Object> params = RequestUtil.getParameters(getRequest());
        keyDto.setFileName((String) params.get("fileName"));
        keyDto.setValdate(DateUtil.parse((String) params.get("valdate")));
        keyDto.setPortfolioName((String) params.get("portfolioName"));
        List<RskOutValtblImportHis001Dto> resultList = rskOutValtblImportHis002Service.selectTraceByKey(keyDto);
        for (RskOutValtblImportHis001Dto rskOutValtblImportHis001Dto : resultList) {
            rskOutValtblImportHis001Dto.setStartTimeStr(rskOutValtblImportHis001Dto.getStartTimeStr());
            rskOutValtblImportHis001Dto.setEndTimeStr(rskOutValtblImportHis001Dto.getEndTimeStr());
            rskOutValtblImportHis001Dto.setErrorCodeStr(rskOutValtblImportHis001Dto.getErrorCodeStr());
            rskOutValtblImportHis001Dto.setImportTypeStr(rskOutValtblImportHis001Dto.getImportTypeStr());
        }
            
        return resultList;
    }

}
