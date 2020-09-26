package com.joyintech.tams.valuation.bokdetailidupdate.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.bokdetailidupdate.dto.BokDetailIdUpdate001Dto;
import com.joyintech.tams.valuation.bokdetailidupdate.service.BokDetailIdUpdate001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;

import org.springframework.http.ResponseEntity;
import com.joyintech.system.constant.SystemGlobalConstant;

/**
 * 账务明细代码更新
 *
 * @author tanxuan
 * @createTime 2019-10-10 10:36
 */
@Controller
@PageId("BokDetailIdUpdate001")
@RequestMapping("/valuation/bokdetailidupdate/BokDetailIdUpdate001")
public class BokDetailIdUpdate001Controller extends BaseController {


    @Autowired
    private BokDetailIdUpdate001Service bokDetailIdUpdate001Service;
    
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.投资目的);
        this.setSelectOptionToModel(model, DictCode.币种);
        return super.init(model);
    }
//    /**
//     * 账套代码获取
//     *
//     * @return 返回的数据
//     */
//    @RequestMapping("/getBooksetId")
//    @ResponseBody
//    public List<SelectOptionDto> getBooksetId(HttpServletRequest request) {
//        Map<String, Object> param =RequestUtil.getPageParam();
//        return bokDetailIdUpdate001Service.getBooksetId(param);
//    }
    
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
     * 账务明细代码获取
     *
     * @return 返回的数据
     */
    @RequestMapping("/getBokDetailId")
    @ResponseBody
    public List<SelectOptionDto> getBokDetailId(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        return bokDetailIdUpdate001Service.getBokDetailId(param);
    }
    
    /**
     * 列表查询
     * @return 列表信息
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<BokDetailIdUpdate001Dto> queryPageList(){
        return bokDetailIdUpdate001Service.queryPageList(RequestUtil.getPageParam());
    }
    
    /**
     * 更新check
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check()
            throws Exception {
        String message = "";
        String datas = this.getParam("datas");
        List<BokDetailIdUpdate001Dto> listdto = new ArrayList<>() ;
        
        if (datas == null) {
            listdto = bokDetailIdUpdate001Service.queryAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, BokDetailIdUpdate001Dto.class);
        }
        if(listdto.isEmpty()){
            message = "没有对应的账务明细代码记录！";
            return new AjaxResult(message, MessageType.错误消息);
        }
        if(bokDetailIdUpdate001Service.checkExecute()!=0){
            message = "当前有正在进行登帐的批处理，请稍后操作！";
            return new AjaxResult(message, MessageType.错误消息);
        }
        if(bokDetailIdUpdate001Service.checkInterLog()!=0){
            message = "当前有正在进行运行的接口任务，请稍后操作！";
            return new AjaxResult(message, MessageType.错误消息);
        }
        return new AjaxResult();
    }
    
    /**
     * 更新
     * @return
     * @throws ParseException
     * @throws IOException 
     */
    @ResponseBody
    @RequestMapping("update")
    public AjaxResult update() throws ParseException, IOException {
        String massage = "";
        String datas = this.getParam("datas");
        List<BokDetailIdUpdate001Dto> listdto = new ArrayList<>() ;
        
        if (datas == null) {
            listdto = bokDetailIdUpdate001Service.queryAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, BokDetailIdUpdate001Dto.class);
        }
        massage = bokDetailIdUpdate001Service.update(listdto);
        return new AjaxResult(massage, MessageType.提示消息);
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
        List<BokDetailIdUpdate001Dto> listdto ;

        if (datas == null) {
            listdto = bokDetailIdUpdate001Service.queryAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, BokDetailIdUpdate001Dto.class);
        }
        File outFile = bokDetailIdUpdate001Service.makeFile(
                "/com/joyintech/tams/valuation/bokdetailidupdate/conf/export_bokdetailidupdate.xml",
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
