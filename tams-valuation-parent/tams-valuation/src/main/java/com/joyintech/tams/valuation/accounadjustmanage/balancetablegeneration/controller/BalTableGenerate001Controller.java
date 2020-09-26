/*
 * 文件名：BalTableGenerate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.dto.BalTableGenerate001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.dto.BalTableGenerate001SearchDto;
import com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.service.BalTableGenerate001Service;
import com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.dto.BokBalanceStatementQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001SearchDto;

/**
 * 余额表生成Controller
 * 
 * @author 王波
 * @version 1.0 2018年8月21日
 * @see BalTableGenerate001Controller
 * @since 1.0
 */
@Controller
@PageId("BalTableGenerate001")
@RequestMapping("/valuation/accounadjustmanage/balancetablegeneration/BalTableGenerate001")
public class BalTableGenerate001Controller extends BaseController {

    @Autowired
    private BalTableGenerate001Service balTableGenerate001Service;
    
//    /**
//     * ValProductInfoQuery001Service
//     */
//    @Autowired
//    private ValNetValueConfirm001Service valNetValueConfirm001Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益类型,DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.生成状态,DictCode.是否);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        return super.init(model);
    }
    
    /**
     * 查询前校验
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkQuery")
    public AjaxResult checkQuery(@Valid @RequestBody ValVoucherQuery001SearchDto dto,
            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }
    
    /**
     * 手动生成
     *
     * @return 返回的数据
     * @throws ParseException 
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/handleByHandCheck")
    @ResponseBody
    public int handleByHand() throws IOException, ParseException  {
        Map<String, Object> map= this.getRequestMap();
        return balTableGenerate001Service.handleByHandCheck(map);
    }
    
    @RequestMapping("/handleByHandCheck2")
    @ResponseBody
    public int handleByHand2() throws IOException, ParseException  {
        Map<String, Object> map= this.getRequestMap();
        return balTableGenerate001Service.handleByHandCheck2(map);
    }
    
    /**
     * 
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
     * 
     * @return paraMap
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期
        String valDateBegin = this.getParam("valDateBegin");
        String valDateEnd = this.getParam("valDateEnd");
        if(StringUtil.isNotEmpty(valDateBegin)){
            paraMap.put("valDateBegin", DateUtil.parseDate(valDateBegin, "yyyy-MM-dd"));
        }
        if(StringUtil.isNotEmpty(valDateEnd)){
            paraMap.put("valDateEnd", DateUtil.parseDate(valDateEnd, "yyyy-MM-dd"));
        }
        
//        //获取交易类型
//        String prodTypes = (String)paraMap.get("prodTypes");
//        //交易方向多选，转为数组
//        String[] prodTypeArr = null;
//        if(StringUtil.isNotEmpty(prodTypes)){
//            prodTypeArr = prodTypes.split(",");
//        }
//        paraMap.put("prodTypes", prodTypeArr);
//        
//        //获取交易代码
//        String prodIds = (String)paraMap.get("prodIds");
//        //交易方向多选，转为数组
//        String[] prodIdArr = null;
//        if(StringUtil.isNotEmpty(prodIds)){
//            prodIdArr = prodIds.split(",");
//        }
//        paraMap.put("prodIds", prodIdArr);
//        
//      //获取生成状态
//        String buildStauses = (String)paraMap.get("buildStauses");
//        //交易方向多选，转为数组
//        String[] buildStausArr = null;
//        if(StringUtil.isNotEmpty(buildStauses)){
//            buildStausArr = buildStauses.split(",");
//        }
//        paraMap.put("buildStauses", buildStausArr);
        
        // 文件类型是余额表
        paraMap.put("fileType", DictCodeList.文件类型_余额表.getValue());
        // 对账状态
        paraMap.put("checkStatus", DictCodeList.对账状态_已对账.getValue());
        return paraMap;
    }
    /**
     * 记账凭证数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<BalTableGenerate001Dto> findPage() throws Exception {
//        Map<String, Object> params= this.getRequestMap();
//        BalTableGenerate001SearchDto dto = (BalTableGenerate001SearchDto)JsonUtils.map2entity(params, BalTableGenerate001SearchDto.class);
//        dto.setFileType(DictCodeList.文件类型_余额表.getValue());
        return balTableGenerate001Service.findPage(this.getRequestMap());

    }
    
    /**
     * 记账凭证数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("/handle")
    @ResponseBody
    public int handle() throws Exception {
        
        List<BalTableGenerate001SearchDto> listdto = JsonUtils.json2List(this.getParam("data"), BalTableGenerate001SearchDto.class);
        int flag =balTableGenerate001Service.handle(listdto);
        return flag;
//        String data=this.getParam("data");
       
//        if(listdto!=null&&listdto.size()>0){
//            for(BalTableGenerate001SearchDto dto:listdto){
//                dto.setFileType(DictCodeList.文件类型_估值表.getValue());
//                dto.setFileType(DictCodeList.文件类型_余额表.getValue());
//                int num = balTableGenerate001Service.handle(dto);
//                if(num>0){
//                    flag++;
//                }
//            }
//        }
//        if(listdto != null && flag == listdto.size()){
//            flag = 1;
//        }
////        String valDateBegin = this.getParam("data[valDateBegin]");
////        String valDateEnd = this.getParam("data[valDateEnd]");
////        BalTableGenerate001SearchDto dto = new BalTableGenerate001SearchDto();
////        dto.setValDateBegin(DateUtil.parseDate(valDateBegin, "yyyy-MM-dd"));
////        dto.setValDateEnd(DateUtil.parseDate(valDateEnd, "yyyy-MM-dd"));
////        dto.setProdId(this.getParam("data[prodId]"));
//        return flag;

    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        List<BokBalanceStatementQuery001Dto> val001dto = JsonUtils.json2List(datas,
            BokBalanceStatementQuery001Dto.class);
        return balTableGenerate001Service.exportExcel(val001dto);
//        String datas = this.getParam("datas");
//        List<ValValuationStatementQuery001Dto> val001dto = JsonUtils.json2List(datas,
//            ValValuationStatementQuery001Dto.class);
//        List<ValValuationStatementQuery001Dto> val002dto = new ArrayList<ValValuationStatementQuery001Dto>();
//        for (ValValuationStatementQuery001Dto dto:val001dto){
//            String prodid = dto.getFinprodId();
//            ValAssetbook001Dto bookdto = balTableGenerate001Service.queryBooksetId(prodid);
//            //账套代码
//            dto.setBooksetId(bookdto.getBooksetId());
//            //账套名称
//            dto.setBooksetName(bookdto.getBooksetName());
//            //查询生成估值表信息
//            ValValuationStatementQuery001Dto newDto = balTableGenerate001Service.queryExcelInfo(dto);
//            val002dto.add(newDto);
//        }
//        
//        // 如果当前画面的模板不存在，则下载所有模板
//        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
//        String zipFileName = FileNameUtil.createFileName("zip");
//        File zipFile = new File(exportFilePath + File.separator + zipFileName);
//        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
//        InputStream input = null;
//        String filepath1 = "";
//        String filename = "";
//        SftpTools sftpTools = new SftpTools();
//        try {
//            // 上传文件到文件服务器上
//            String host = CacheUtil.getSysParam("FILESERVER_HOST");
//            int port = Integer.parseInt(CacheUtil.getSysParam("FILESERVER_PORT"));
//            String userName = CacheUtil.getSysParam("FILESERVER_USERNAME");
//            String password = CacheUtil.getSysParam("FILESERVER_PASSWORD");
//
//            sftpTools.login(userName, password, host, port);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < val002dto.size(); i++ ) {
//            filepath1 = val002dto.get(i).getFilePath();
//            filename = val002dto.get(i).getFileName();
//            File file = new File(filepath1);
//            String remotePath = CacheUtil.getSysParam("FILESERVER_REMOTE_PATH");
//            String remoteFile = CacheUtil.getSysParam("FILESERVER_REMOTE_PATH") + "/" + filename;
//            try {
//                // 下载路径从SYS_PARA获取
//                String localdir = CacheUtil.getSysParam("EXPORT_VALBOKBANLANCE");
//                sftpTools.getFile(remotePath, remoteFile, localdir);
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//            input = new FileInputStream(file);
//            zipOut.putNextEntry(new ZipEntry(file.getName()));
//            int tmp = 0;
//            while ((tmp = input.read()) != -1) {
//                zipOut.write(tmp);
//            }
//            input.close();
//        }
//        try {
//            sftpTools.close();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        zipOut.flush();
//        zipOut.close();
//        return ExportUtil.export(zipFileName, zipFile, true);
    }
    
    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        return balTableGenerate001Service.getFinprodId(map);
    }
}
