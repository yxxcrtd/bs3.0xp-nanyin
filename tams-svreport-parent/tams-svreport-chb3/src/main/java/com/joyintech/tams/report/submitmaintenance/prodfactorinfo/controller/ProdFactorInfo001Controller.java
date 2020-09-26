/*
 * 文件名：ProdFactorInfo001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2017年10月31日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.prodfactorinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;



/**
 * 产品要素信息界面Controller
 * 〈功能详细描述〉
 * @author liuertong
 * @version 1.0 2017年12月15日
 * @see ProdFactorInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ProdFactorInfo001")
@RequestMapping("/svreport/submitmaintenance/prodfactorinfo/ProdFactorInfo001")
public class ProdFactorInfo001Controller extends BaseController{
//    
//    /**
//     * 报送前检查service//使用机构代码下拉框
//     */
//    @Autowired
//    private CheckReport001Service checkReport001Service;
//    
//    /**
//     * ProdTradeInfo001Service
//     */
//    @Autowired
//    private ProdFactorInfo001Service prodFactorInfo001Service;
//    /**
//     * 页面初始化
//     * {@inheritDoc}
//     */
//    @RequestMapping(PAGE_INIT)
//    public String init(Model model) {
//        //dingzhipeng add start
//        //判断数据是否从主页代维护信息加载 
//        if(RequestUtil.getPageParam().containsKey("status")){
//            model.addAttribute("homeDataType",RequestUtil.getPageParam().get("homeDataType"));
//            model.addAttribute("isMain","01");
//            //选择框
//            model.addAttribute("per_type_04", DictCodeList.投资者类型_一般个人客户.getValue());
//            model.addAttribute("per_type_05", DictCodeList.投资者类型_高资产净值客户.getValue());
//        }
//        else{
//            model.addAttribute("homeDataType","03");
//            model.addAttribute("isMain","02");
//        }
//        //dingzhipeng add end
//        super.setSelectOptionToModel(model, DictCode.维护数据状态,DictCode.投资者类型,DictCode.产品收益类型,
//            DictCode.报备状态,DictCode.发行状态,DictCode.是否);
//        String myOrgCode = this.getLoginUser().getOrgCode();
//        if (StringUtil.isNotEmpty(myOrgCode)) {
//            model.addAttribute("MY_AGENCY_CODE", myOrgCode);
//            model.addAttribute("AGENCY_CODE_LIST",
//                JSONUtil.toJsonString(checkReport001Service.getOrgCodeList(myOrgCode).toArray()));
//        }
//        return super.init(model);
//    }
//    /**
//     * 查询列表
//     * 
//     * @throws Exception 异常
//     * @return Page
//     */
//    @ResponseBody
//    @RequestMapping(SEARCH_PAGE)
//    public Page<ProdFactorInfo001Dto> search()
//        throws Exception {
//        Map<String, Object> param = RequestUtil.getPageParam();
//        return prodFactorInfo001Service.searchForPage(param);
//    }
//    /**
//     * 画面check<br>
//     * @param result 结果
//     * @param dto ProdTradeInfo001Dto
//     * @return check结果
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping(value = "checkDelete", method = RequestMethod.POST)
//    public WebResult check(@RequestBody @Valid ProdFactorInfo001Dto dto,BindingResult result ) {
//        if (result != null && result.hasErrors()) {
//            return new WebResult(result);
//        }
//        WebResult wr = new WebResult();
//        String btnName = "common.button.delete";
//        wr.setData(this.getMessage("COMMON_C_0010", this.getMessage(btnName)));
//        return wr;
//    }
//    /**
//     * 画面check<br>
//     *
//     * @param dto ProdTradeInfo001Dto
//     * @return check结果
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping(value = "delList", method = RequestMethod.POST)
//    public WebResult delList(@RequestBody ProdFactorInfo001Dto dto ) {
//        prodFactorInfo001Service.delList(dto);
//        return new WebResult(this.getMessage("COMMON_I_0007"));
//    }
//    
//    /**
//     * 导出excel
//     *
//     * @return 导出
//     * @throws Exception 异常信息
//     */
//    @RequestMapping(value = ACTION_EXPORT)
//    public ResponseEntity<byte[]> export()
//        throws Exception {
//        Map<String, Object> param = RequestUtil.getPageParam();
//        List<ProdFactorInfo001Dto> dtoList = this.prodFactorInfo001Service.queryExportList(param);
//        File outFile = prodFactorInfo001Service.makeFile(
//            "/com/joyintech/submitmaintenance/prodfactorinfo/conf/export_conf.xml", dtoList);
//        return ExportUtil.export(
//            this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
//                                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
//                                 + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//            outFile, true);
//    }
//    /**
//     * 导入数据文件
//     * 
//     * @param fileName 导入的文件名
//     * @return 导入结果，包含验证内容，如果验证失败，返回message
//     * @throws Exception 异常
//     */
//    @ResponseBody
//    @RequestMapping("/importFile")
//    public List<ExcelValidDto> importData(String fileName)
//        throws Exception {
//        List<ExcelValidDto> a = this.prodFactorInfo001Service.importData(fileName);
//        return a;
//    }
}
