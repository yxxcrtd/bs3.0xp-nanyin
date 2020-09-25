package com.joyintech.tams.invest.settle.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.util.SettleCommonUtil;
import com.joyintech.tams.invest.settle.order.dto.StlOrderManagement001Dto;
import com.joyintech.tams.invest.settle.order.service.StlOrderManagement001Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;

/**
 * @program: tams-invest-parent
 * @description: 划款指令管理
 * @author: 强成西
 * @create: 2018-11-15 11:28
 **/
@Controller
@PageId("StlOrderManagement001")
@RequestMapping("/invest/settle/order/StlOrderManagement001")
public class StlOrderManagement001Controller extends BaseController {

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.平安划款类型, DictCode.平安业务类型,
        DictCode.指令处理状态, DictCode.成功失败标志,DictCode.发送状态};

    @Autowired
    private StlOrderManagement001Service stlOrderManagement001Service;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        Map<String, Object> params = new HashMap<>();
        SettleCommonUtil.setParams(params);
        model.addAllAttributes(params);
        //平台日
        model.addAttribute("platformDate",DateUtil.format(DateUtil.platDateFromCache()));//add by liupeilin 2020-01-07
        // 画面元素是否显示
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);

        model.addAttribute("isEleShow", isEleShow);
        
        return super.init(model);
    }

    /**
     * 查询交易的指令数据
     *
     * @return grid
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findOrderData")
    public Page<StlOrderManagement001Dto> findSysPage() {
        return stlOrderManagement001Service.findOrderData(RequestUtil.getPageParam());
    }

    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return stlOrderManagement001Service.export("划款指令管理.xls", RequestUtil.getPageParam());

    }
    
    /**
     * 
     * 重新发送接口
     * @param stlOrderManagement001Dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("resendOrderInfo")
    public AjaxResult resendOrderInfoInterf(@RequestBody StlOrderManagement001Dto stlOrderManagement001Dto){
        List<StlOrderManagement001Dto> dtoList = stlOrderManagement001Dto.getSelectedItem();
        AjaxResult ar = new AjaxResult() ;
        if(dtoList.size() > 0){
            List<ValidMessage> errorList = ar.getErrorList();
            boolean sendFlag = true ;
            for(StlOrderManagement001Dto stlOrderManagement001Dto1 : dtoList){
                if(DictCodeList.发送状态_发送成功.equals(stlOrderManagement001Dto1.getSendStatus())){
                    errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                            "发送失败")));
                    sendFlag = false ;
                    return ar ;
                }
            }
            if(sendFlag){
                List<String> orderIds = new ArrayList<>();
                for(StlOrderManagement001Dto model : dtoList){
                    orderIds.add(model.getOrderId());
                }
                Map<String,String> map = stlOrderManagement001Service.resendRoderInfo(orderIds);
                  errorList.add(new ValidMessage(map.get("msg"))) ;
            }
        }       
        return ar;        
    }
    
    /**
     * 
     * 刷新
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("refreshInfo")
    public AjaxResult refreshInfo(){
        AjaxResult ar = new AjaxResult();
        List<ValidMessage> errorList = ar.getErrorList();
        Map<String,String> map = stlOrderManagement001Service.refreshInfo();
        errorList.add(new ValidMessage(map.get("msg")));
        return ar;
    }
    
    //Add By ZhangJin  For 邮储银行--从远程文件服务器下载附件 Start 20191213
    /**
     * 检查选择的附件是否存在
     * @return 流水号
     */
    @ResponseBody
    @RequestMapping("/checkFileExists")
    public AjaxResult checkFileExists() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return stlOrderManagement001Service.checkFileExists(param);
    }

    /**
     * 下载附件
     * @return 导出
     */
    @ResponseBody
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return stlOrderManagement001Service.download(param);
    }
    //Add For 邮储银行--从远程文件服务器下载附件 End 20191213
	
    /**
     * 
     * 指令作废接口
     * @param stlOrderManagement001Dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("invalidOrderInfo")
    public AjaxResult invalidOrderInfo(@RequestBody StlOrderManagement001Dto stlOrderManagement001Dto){
        List<StlOrderManagement001Dto> dtoList = stlOrderManagement001Dto.getSelectedItem();
        AjaxResult ar = new AjaxResult() ;
        if(dtoList.size() > 0){
            List<ValidMessage> errorList = ar.getErrorList();
                List<String> orderIds = new ArrayList<>();
                for(StlOrderManagement001Dto model : dtoList){
                    orderIds.add(model.getOrderId());
                }
                Map<String,String> map = stlOrderManagement001Service.invalidOrderInfo(orderIds);
                  errorList.add(new ValidMessage(map.get("msg"))) ;
            }     
        return ar;        
    }
}
