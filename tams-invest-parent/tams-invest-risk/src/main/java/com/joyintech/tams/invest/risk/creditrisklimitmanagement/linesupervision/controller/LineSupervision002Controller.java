package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linesupervision.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linesupervision.dto.LineSupervisionDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linesupervision.service.LineSupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 额度监测 分项交易明细
 * @author gejiaao
 * @version 1.0 2020年03月01日
 * @see LineSupervision002Controller
 * @since 1.0
 */
@Controller
@PageId("LineSupervision002")
@RequestMapping("/invest/risk/creditrisklimitmanagement/linesupervision/LineSupervision002")
public class LineSupervision002Controller extends BaseController {
	 
	/**
     * lineSupervisionService
     */
    @Autowired
    private LineSupervisionService lineSupervisionService;
    
    @Override
    public String init(Model model) {
        String creditMstId = this.getParam("creditMstId");
        List<LineSupervisionDto> creditCustomerMstList = lineSupervisionService.getCreditCustomerMst(creditMstId);
        model.addAttribute("creditCustomerMstList", creditCustomerMstList);
        return super.init(model);
    }

    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<LineSupervisionDto> selectAll()
        throws Exception {
        return lineSupervisionService.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping("/getCustomerName")
    public Page<SelectOptionDto> getCustomerName() throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        return lineSupervisionService.getCustomerName(map);
    }

    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return lineSupervisionService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
