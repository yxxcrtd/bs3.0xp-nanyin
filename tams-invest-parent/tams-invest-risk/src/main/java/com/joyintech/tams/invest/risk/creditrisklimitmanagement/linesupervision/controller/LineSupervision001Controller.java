package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linesupervision.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
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

import java.util.Map;


/**
 * 额度监测
 * @author gejiaao
 * @version 1.0 2020年03月01日
 * @see LineSupervision001Controller
 * @since 1.0
 */
@Controller
@PageId("LineSupervision001")
@RequestMapping("/invest/risk/creditrisklimitmanagement/linesupervision/LineSupervision001")
public class LineSupervision001Controller extends BaseController {
	 
	/**
     * lineSupervisionService
     */
    @Autowired
    private LineSupervisionService lineSupervisionService;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.监控状态, DictCode.额度分类);
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
