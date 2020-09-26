/*
 * 文件名：ProRegistCodeManage001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：tangliang
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.system.proregistcodemanage.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.instregbankcode.service.SysInstRegBankCodeMService;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
import com.joyintech.tams.report.system.proregistcodemanage.service.ProRegistCodeManage001Service;
import com.joyintech.tams.report.system.proregistcodemanage.service.ProRegistCodeManageMService;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 产品登记编码管理
 * 〈功能详细描述〉
 * @author tangliang
 * @version 1.0 2017年9月19日
 * @see ProRegistCodeManage001Controller
 * @since 1.0
 */
@Controller
@PageId("ProRegistCodeManage001")
@RequestMapping("/svreport/system/proregistcodemanage/ProRegistCodeManage001")
public class ProRegistCodeManage001Controller extends BaseController {
    /**
     * ProRegistCodeManage001Service
     */
    @Autowired
    private ProRegistCodeManage001Service proRegistCodeManage001Service;
    @Autowired
    private ProRegistCodeManageMService proRegistCodeManageMService;
    @Autowired
    private SysInstRegBankCodeMService sysInstRegBankCodeMService;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        //dingzhipeng add start
        //判断数据是否从主页代维护信息加载
        if(RequestUtil.getPageParam().containsKey("status")){
            model.addAttribute("isFromMain","01");
        }
        else{
            model.addAttribute("isFromMain","02");
        }
        String myOrgCode = CommonUtil.getOrgCode();
        if(StringUtil.isNotEmpty(myOrgCode)){
            model.addAttribute("MY_AGENCY_CODE",myOrgCode);
        }
      //dingzhipeng add end
        super.setSelectOptionToModel(model, DictCode.目标客户类型, DictCode.发送状态);

        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        String svrSystemType=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PRDRESIST_CODE);
        if(svrSystemType!=null&&svrSystemType.equals("01")){
        	model.addAttribute("isSvr",false);
        }else{
        	model.addAttribute("isSvr",true);
        }
        // 画面元素是否显示
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }

    /**
     * 产品下拉框
     * @return 产品代码和名称列表
     */
    @RequestMapping(value = "getProd", method = RequestMethod.GET)
    @ResponseBody
    public List<SuggestDto> getProd(String param) {
        return proRegistCodeManageMService.getProd(param);
    } 
    
    /**
     *
     * 机构代码下拉框取得值
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getInstitutionCode")
    public List<SelectOptionDto> getInstitutionCode(){
        String param = super.getRequest().getParameter("param");
        List<SuggestDto> list = sysInstRegBankCodeMService.getInstitutionCode(param);
        List<SelectOptionDto> resultList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)){
            for (SuggestDto suggestDto : list) {
                SelectOptionDto resultDto = new SelectOptionDto();
                resultDto.setId(suggestDto.getCode());
                resultDto.setText(suggestDto.getShortName());
                resultList.add(resultDto);
            }
        }
        return resultList;
    }
    
    /**
     * 产品登记编码下拉框
     * @return 产品名称列表
     */
    @RequestMapping("getRegCode")
    @ResponseBody
    public List<SelectOptionDto> getRegCode() {
        return proRegistCodeManageMService.queryPrdRegList(RequestUtil.getPageParam());
    }
    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ProRegistCodeManage001Dto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        boolean isNotNull = Boolean.parseBoolean(this.getParam("isNotNull"));
        param.put("isNotNull", isNotNull);
        return proRegistCodeManageMService.searchForPage(param);
    }
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.proRegistCodeManageMService.importData(fileName);
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {
        List<ProRegistCodeManage001Dto> dtoList = this.proRegistCodeManageMService.queryReportList(
        		RequestUtil.getPageParam());
        File outFile = proRegistCodeManage001Service.makeFile(
            "/com/joyintech/tams/report/system/proregistcodemanage/conf/export_conf.xml", dtoList);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                 + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
    /**
     * 检查产品是否存在交易或者存在基期数据
     * @param orgCodes 机构代码
     * @param prodIds 产品ids
     * @return WebResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(path = "deleteOrEditCheck", method = RequestMethod.POST)
    public AjaxResult deleteOrEditCheck(String prdRegistCodes,String prodIds) {
    	AjaxResult webResult = new AjaxResult();
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        Map<String,Object> map =  new HashMap<String,Object>();
//        map.put("prdName", prodIds);
//        map.put("orgCode", orgCodes);
          List<String> prodIdList = null;
          List<String> prdRegistCodeList = null;
          if(StringUtil.isNotEmpty(prodIds)){
              prodIdList = StringUtil.splitToList(GlobalConstant.COMMA, prodIds);
          }
          if(StringUtil.isNotEmpty(prdRegistCodes)){
              prdRegistCodeList = StringUtil.splitToList(GlobalConstant.COMMA, prdRegistCodes);
          }
          map.put("prodIdList", prodIdList);
          map.put("prdRegistCodeList", prdRegistCodeList);
          map.put("status", DictCodeListExt1.维护数据状态_有效.getValue());
//        //业务明细校验
//        List<EquiteBusiDetails001Dto> list = equityBusiDetails001Service.queryReportList(map);
//        if(CollectionUtils.isNotEmpty(list)){
//            StringBuilder sb = new StringBuilder();
//            for (EquiteBusiDetails001Dto equiteBusiDetails001Dto : list) {
//                if(sb.indexOf(equiteBusiDetails001Dto.getAccount())==-1){
//                    sb.append(equiteBusiDetails001Dto.getAccount());
//                    sb.append(",");
//                }
//                sb.deleteCharAt(sb.lastIndexOf(","));
//                validMessageList.add(new ValidMessage(this.getMessage("CHB_EQUITYINFO_E_025","产品代码", sb.toString())));
//            }
//        }
//        //基期数据校验
//        List<EquiteBaseInfo001Dto> baseList = equityBaseInfo001Service.queryReportList(map);
//        if(CollectionUtils.isNotEmpty(baseList)){
//            StringBuilder sb = new StringBuilder();
//            for (EquiteBaseInfo001Dto equiteBaseInfo001Dto : baseList) {
//                sb.append(equiteBaseInfo001Dto.getProdId());
//                sb.append(",");
//                sb.deleteCharAt(sb.lastIndexOf(","));
//                validMessageList.add(new ValidMessage(this.getMessage("CHB_EQUITYINFO_E_026","产品代码", sb.toString())));
//            }
//        }
        Integer prodIsUsed = proRegistCodeManageMService.selectProdIsUsed(map);
        if(prodIsUsed>0){
            validMessageList.add(new ValidMessage(this.getMessage("PROREGISTCODE_E_001")));
        }
        webResult.setData(getMessage("REPORT_COMMON_C_0003"));
        webResult.appendMessageList(validMessageList);
        return webResult;
    }
    /**
     * 导出excel
     *
     * @param map 参数
     * @return 导出
     */
    @ResponseBody
    @RequestMapping(path = "deleteRegCode", method = RequestMethod.POST)
    public AjaxResult deleteRegCode(@RequestBody Map<String,Object> map) {
        String key = (String)map.get("deleteKeys");
        this.proRegistCodeManageMService.deleteRegCode(key);
        AjaxResult wb=new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
        return wb;
    }
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return proRegistCodeManageMService.checkConfirmMsgForImportData(fileName);
    }
    /**
    *
    * 产品代码下拉框取得值
    *
    * @return 下拉框值
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("/getProdID")
   public List<SelectOptionDto> getProdID() {
        Map<String, Object> param = RequestUtil.getPageParam();
       List<ChbPrdBaseinfo> list = proRegistCodeManageMService.getProdinfo(param);
       List<SelectOptionDto> resultList = new ArrayList<>();
       if (CollectionUtils.isNotEmpty(list)) {
           for (ChbPrdBaseinfo suggestDto : list) {
               SelectOptionDto resultDto = new SelectOptionDto();
               resultDto.setId(suggestDto.getProdId());
               resultDto.setText(suggestDto.getProdName());
               resultList.add(resultDto);
           }
       }
       return resultList;
   }
   
   /**
    * 导入数据文件之前检查数据
    *
    * @param fileName 导入的文件名
    * @return 处理结果
    */
   @ResponseBody
   @RequestMapping(value = "checkConfirmMsgForImportNewData", method = RequestMethod.POST)
   public AjaxResult checkConfirmMsgForImportNewData(String fileName)  {
       return proRegistCodeManageMService.checkConfirmMsgForImportNewData(fileName);
   }
   
   /**
    * 导入数据文件
    *
    * @param fileName 导入的文件名
    * @return 导入结果，包含验证内容，如果验证失败，返回message
    */
   @ResponseBody
   @RequestMapping("/importNewFile")
   public List<ExcelValidDto> importNewFile(String fileName) {
       return this.proRegistCodeManageMService.importNewFile(fileName);
   }
}
