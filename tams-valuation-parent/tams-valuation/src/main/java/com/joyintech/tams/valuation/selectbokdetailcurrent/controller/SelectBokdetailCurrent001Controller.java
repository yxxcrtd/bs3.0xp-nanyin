package com.joyintech.tams.valuation.selectbokdetailcurrent.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.selectbokdetailcurrent.dto.SelectBokdetailCurrent001Dto;
import com.joyintech.tams.valuation.selectbokdetailcurrent.service.SelectBokdetailCurrent001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;

/**
 * 账务明细当前表查询
 *
 * @author tanxuan
 * @createTime 2019-10-16
 */
@Controller
@PageId("SelectBokdetailCurrent001")
@RequestMapping("/valuation/selectbokdetailcurrent/SelectBokdetailCurrent001")
public class SelectBokdetailCurrent001Controller extends BaseController {


    @Autowired
    private SelectBokdetailCurrent001Service selectBokdetailCurrent001Service;
    
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
    
	/**
     * 账套代码下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> getBooksetId() {
        return valAccountHandle001Service.getBookSetId2(RequestUtil.getPageParam()); 
    }
    
    /**
     * 列表查询
     * @return 列表信息
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<SelectBokdetailCurrent001Dto> queryPageList(){
        return selectBokdetailCurrent001Service.queryPageList(RequestUtil.getPageParam());
    }
    
    /**
     * 导出数据
     *
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        String datas = this.getParam("datas");
        List<SelectBokdetailCurrent001Dto> listdto ;

        if (datas == null) {
            listdto = selectBokdetailCurrent001Service.queryAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, SelectBokdetailCurrent001Dto.class);
        }
        File outFile = selectBokdetailCurrent001Service.makeFile(
                "/com/joyintech/tams/valuation/selectbokdetailcurrent/conf/export_selectbokdetailcurrent.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}
