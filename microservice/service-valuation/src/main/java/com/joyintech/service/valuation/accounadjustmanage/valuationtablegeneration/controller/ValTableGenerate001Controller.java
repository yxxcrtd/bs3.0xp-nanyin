/*
 * 文件名：ValTableGenerate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.dto.ValValuationStatementQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.HandlePaDto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.HandleReturnDto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.ValTableGenerate001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.ValTableGenerate001SearchDto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.service.ValTableGenerate001Service;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001SearchDto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery001Dto;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokBooksetExtKey;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 模板示例<br>
 * 估值表生成
 * 
 * @author ruanhongming
 * @version 2.0 2018年8月13日
 * @see ValTableGenerate001Controller
 * @since 2.0
 */
//@RestController(value="serviceValTableGenerate001Controller")
@RestController("valTableGenerate001Ctl_dtx")
@RequestMapping("ValTableGenerate001")
public class ValTableGenerate001Controller extends BaseController {
    
    /**
     * ValTableGenerate001Service
     */
    @Autowired
    private ValTableGenerate001Service valTableGenerate001Service;
    
//    @Autowired
//    private ValAssetBook001Service valAssetBook001Service;
    
//    /**
//     * 重写init方法，加载页面 {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        String[] busiType={"04"};
//        //参数过滤
//        DictCode.checkDictCode(model,DictCode.收益类型,busiType,false);
//        this.setSelectOptionToModel(model, DictCode.生成状态,DictCode.是否);
////        Map<String,Object> map=new HashMap<String,Object>();
////        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
//        return super.init(model);
//    }


//    /**
//     * 查询前校验
//     * 
//     * @param dto 参数
//     * @param result 校验结果
//     * @return AjaxResult
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("checkQuery")
//    public AjaxResult checkQuery(@Valid @RequestBody ValVoucherQuery001SearchDto dto,
//            BindingResult result) {
//        if (result != null && result.hasErrors()) {
//            return new AjaxResult(result);
//        }
//        return new AjaxResult();
//    }
    
    /**
     * 产品对账校验
     *
     * @return 返回的数据
     * @throws ParseException 
     * @exception Exception
     *                异常
     * @since 1.0
     */
//    @RequestMapping("/handleByHandCheck")
//    @ResponseBody
//    public int handleByHand() throws IOException, ParseException  {
//        Map<String, Object> map= this.getRequestMap();
//        return valTableGenerate001Service.handleByHandCheck(map);
//    }

//    /**
//     * 检索账套代码(只取理财账套)
//     * 
//     * @return 账套代码联想框内容
//     * @throws Exception
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("/getBooksetId")
//    public Page<SelectOptionDto> getBooksetId()
//        throws Exception {
//        return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
//    }
    
    /**
     * 记账凭证数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValTableGenerate001Dto> findPage(@RequestBody Map<String , Object> map,
                                                 @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valTableGenerate001Service.findPage(page,length,map);

    }
    
    /**
     * 估值表生成 - 手动生成 - 执行前数据检查
     * @param paList
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/checkAccountsForHandle")
    public HandleReturnDto checkAccountsForHandle(@RequestBody String data) throws Exception {
//        String data=this.getParam("data");
        List<HandlePaDto> paList = JsonUtils.json2List(data, HandlePaDto.class);
        //定义返回实体类
        HandleReturnDto returnDto = new HandleReturnDto();
        returnDto.setReturnNum(1);//默认塞入’成功‘情况
        returnDto.setReturnMessage("检查无误");
        
        //1.对传递的数据，进行登账检查
        List<HandlePaDto> notFitPrd = new ArrayList<HandlePaDto>();
        String returnMessage = "";
        for(HandlePaDto pa : paList){
            ValTableGenerate001SearchDto dto = new ValTableGenerate001SearchDto();
            dto.setValDateBegin(DateUtil.parseDate(pa.getValDate(), "yyyy-MM-dd"));
            dto.setValDateEnd(DateUtil.parseDate(pa.getValDate(), "yyyy-MM-dd"));
            dto.setProdId(pa.getProdId());
            dto.setFileType(DictCodeList.文件类型_估值表.getValue());
            //检查改产品该日期是否登账
            int resulteNum = valTableGenerate001Service.queryBooksetIdCount(dto); 
            
            //记录不符合条件的数据
            if(resulteNum == 0){
                notFitPrd.add(pa);
                returnMessage = returnMessage + "产品代码："+pa.getProdId()+"在"+pa.getValDate()+"日未登账；";
            }
        }
        
        //若存在未登账数据，直接进行返回
        if(notFitPrd!=null && notFitPrd.size()>0){
            returnDto.setReturnNum(0);//默认塞入’失败‘情况
            returnDto.setReturnMessage(returnMessage+"请先进行登账批处理！");
        }
        
        
        //2.其他检查。。。。，此处暂时没有
        
        return returnDto;
    }
    
    @PostMapping("/queryBooksetIdCount")
    public int queryBooksetIdCount(@RequestBody ValTableGenerate001SearchDto dto) {
        return valTableGenerate001Service.queryBooksetIdCount(dto);
    }
    
    /**
     * 估值表生成 - 手动生成
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/handle")
    public HandleReturnDto handle(@RequestBody String data) throws Exception {
//        String data=this.getParam("data");
        List<HandlePaDto> paList = JsonUtils.json2List(data, HandlePaDto.class);
        //定义返回实体类
        HandleReturnDto returnDto = new HandleReturnDto();
        returnDto.setReturnNum(0);//默认塞入’失败‘情况
        returnDto.setReturnMessage("执行失败");
        
        //对【已经对过账】和【未对过账】的统一进行对账（已查看逻辑，会先删除，再对账。故可以一起处理）；
        int sucessNum = 0;//记录成功条数
        int failNum = 0;//记录失败条数
        for(HandlePaDto pa : paList){
            ValTableGenerate001SearchDto dto = new ValTableGenerate001SearchDto();
            dto.setValDateBegin(DateUtil.parseDate(pa.getValDate(), "yyyy-MM-dd"));
            dto.setValDateEnd(DateUtil.parseDate(pa.getValDate(), "yyyy-MM-dd"));
            dto.setProdId(pa.getProdId());
            dto.setFileType(DictCodeList.文件类型_估值表.getValue());
            
            //进行登账处理
            boolean flag= valTableGenerate001Service.handle(dto);
           
            //检查是否登账成功
            if(flag){
                sucessNum ++;
            }else{
                failNum ++;
            }
        }
        
        returnDto.setReturnNum(1);//默认塞入’成功‘情况
        returnDto.setReturnMessage("执行结束：成功"+sucessNum+"条，失败"+failNum+"条。");
        
        return returnDto;
        
       //old code ......................................: 
       /* boolean flag = false;
        int num = 0 ;
        int count = 0;
        int first = 0;
        ValTableGenerate001SearchDto dto = new ValTableGenerate001SearchDto();
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期
        String valDateBegin = this.getParam("data[valDateBegin]");
        String valDateEnd = this.getParam("data[valDateEnd]");
        dto.setValDateBegin(DateUtil.parseDate(valDateBegin, "yyyy-MM-dd"));
        dto.setValDateEnd(DateUtil.parseDate(valDateEnd, "yyyy-MM-dd"));
        dto.setProdId(this.getParam("data[prodId]"));
        dto.setFileType(DictCodeList.文件类型_估值表.getValue());
       
        first = valTableGenerate001Service.queryBooksetIdCount(dto);
        if(first==0){
            num =3;
            return num;
        }
//        //获取交易代码
//        String prodIds = (String)params.get("prodIds");
//        //交易方向多选，转为数组
//        String[] prodIdArr = null;
//        if(StringUtil.isNotEmpty(prodIds)){
//            prodIdArr = prodIds.split(",");
//            params.put(prodIds, "prodIdArr");
//        }
//        params.put("fileType", DictCodeList.文件类型_估值表.getValue());
//        params.put("checkStatus", DictCodeList.对账状态_已对账.getValue());
        dto.setCheckStatus(DictCodeList.对账状态_已对账.getValue());
        count = valTableGenerate001Service.handleByHandCheck(dto);
        if(count >0){
            num =2;
            return num;
        }
//        Map<String, Object> paramsMap= this.getRequestMapExtral();
//        ValTableGenerate001SearchDto dto = (ValTableGenerate001SearchDto)JsonUtils.map2entity(paramsMap, ValTableGenerate001SearchDto.class);
        flag = valTableGenerate001Service.handle(dto);
        if(flag){
            num = 1;
        }
        return num;*/

    }

//    /**
//     * 导出信息excel
//     * 
//     * @return excel
//     * @throws Exception
//     *             异常
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export() throws Exception {
//        String datas = this.getParam("datas");
//        List<ValValuationStatementQuery001Dto> val001dto = JsonUtils.json2List(datas,
//            ValValuationStatementQuery001Dto.class);
//        return valTableGenerate001Service.exportExcel(val001dto);
//    }

//    /**
//     * 
//     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
//     * 
//     * @return paraMap
//     * @throws ParseException 
//     * @see
//     * @since 1.0
//     */
//    public Map<String, Object> getRequestMap() throws ParseException {
//        Map<String, Object> paraMap = RequestUtil.getPageParam();
//        // 估值日期
//        String valDateBegin = this.getParam("valDateBegin");
//        String valDateEnd = this.getParam("valDateEnd");
//        if(StringUtil.isNotEmpty(valDateBegin)){
//            paraMap.put("valDateBegin", DateUtil.parseDate(valDateBegin, "yyyy-MM-dd"));
//        }
//        if(StringUtil.isNotEmpty(valDateEnd)){
//            paraMap.put("valDateEnd", DateUtil.parseDate(valDateEnd, "yyyy-MM-dd"));
//        }
//        
//        return paraMap;
//    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param prodid
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryBooksetId")
    public ValAssetbook001Dto queryBooksetId(@RequestBody String prodid) {
        return valTableGenerate001Service.queryBooksetId(prodid); 
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectBalanceAll")
    public List<ValBalanceStatementQuery001Dto> selectBalanceAll(@RequestBody Map<String, Object> pageParam) {
        return valTableGenerate001Service.selectBalanceAll(pageParam);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectBalanceSum")
    public List<ValBalanceStatementQuery001Dto> selectBalanceSum(@RequestBody Map<String, Object> pageParam) {
        return valTableGenerate001Service.selectBalanceSum(pageParam);
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByPrimaryKey")
    public BokBooksetExt selectByPrimaryKey(@RequestBody BokBooksetExtKey key) {
        return valTableGenerate001Service.selectByPrimaryKey(key);
        
    }
    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String,Object> map) {
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        return valTableGenerate001Service.getFinprodId(map);
    }
    
    public Map<String, Object> getRequestMapExtral() throws ParseException {
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
      //获取交易类型
        String prodType = (String)paraMap.get("prodTypes");
        //交易方向多选，转为数组
        String[] prodTypeArr = null;
        if(StringUtil.isNotEmpty(prodType)){
            prodTypeArr = prodType.split(",");
            paraMap.put("prodTypes", prodTypeArr);
        }
        
        //获取交易代码
        String prodIds = (String)paraMap.get("prodIds");
        //交易方向多选，转为数组
        String[] prodIdArr = null;
        if(StringUtil.isNotEmpty(prodIds)){
            prodIdArr = prodIds.split(",");
            paraMap.put("prodIds", prodIdArr);
        }
        
        //获取生成状态
        String buildStauses = (String)paraMap.get("buildStauses");
        //交易方向多选，转为数组
        String[] buildStausArr = null;
        if(StringUtil.isNotEmpty(buildStauses)){
            buildStausArr = buildStauses.split(",");
            paraMap.put("buildStauses", buildStausArr);
        }
        return paraMap;
    }

}
