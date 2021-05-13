/*
 * 文件名：RepFinProductAssetBalanceController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.balance.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.system.department.dto.SysDepartmentDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.report.balance.dto.RepFinProductAssetBalanceDto;
import com.joyintech.tams.invest.report.balance.service.RepFinProductAssetBalanceService;
import com.joyintech.tams.invest.report.common.util.FileMergeUtil;


/**
 *  资产余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月4日
 * @see RepFinProductAssetBalanceController
 * @since 1.0
 */
@Controller
@PageId("RepFinProductAssetBalance")
@RequestMapping("/invest/report/balance/RepFinProductAssetBalance")
public class RepFinProductAssetBalanceController extends BaseController{

    @Autowired
    private IPlatformDate iPlatformDate;
    
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.层级信息,DictCode.金融产品类型_投管用};
    
    @Autowired
    private RepFinProductAssetBalanceService repFinProductAssetBalanceService;
    @Override
    public String init(Model model){
      //初始化基本信息画面数据字典
        setSelectOptionToModel(model, DICT_CODES);
        setModel(model);
        model.addAttribute("iPlatformDate", iPlatformDate.getSysDate());
        model.addAttribute("SHOW_LEVEL_02", DictCodeListExt1.层级信息_二级.getValue());
        return super.init(model);
    }
    
    /**
     * 组装所属部门
     * <功能详细描述>
     * @param model
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    private void setModel(Model model){
        Map<String, SysDepartmentDto> deptMap = CacheUtil.getAppCache(GlobalConstant.SYS_DEPARTMENT_KEY, Map.class);
        List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
        for (String in : deptMap.keySet()) {
            SysDepartmentDto SysDepartmentDto = (SysDepartmentDto)deptMap.get(in);
            SelectOptionDto option = new SelectOptionDto();
            option.setId(SysDepartmentDto.getDeptCode());
            option.setText(SysDepartmentDto.getDeptName());
            options.add(option);
        }
        model.addAttribute("DEPT", JsonUtils.objectArray2Json(options.toArray()));
    }
    
    /**
     *  汇总资产一级分类
     * <功能详细描述>
     * @param dictCode
     * @param model
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFirstLevelSelectOption")
    public  List<SelectOptionDto> getFirstLevelSelectOption() {
        Map<String,Object> paramMap = RequestUtil.getPageParam();
        List<SelectOptionDto> selectOptionList = new ArrayList<SelectOptionDto>();
        if(StringUtil.isNotEmpty(paramMap.get("finprodType2"))){
            String[] assetTypeArr= paramMap.get("finprodType2").toString().split(",");
            for(String assetType : assetTypeArr){
                if(StringUtil.isNotEmpty(assetType)){
                    if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                        selectOptionList.addAll(getOptions(DictCode.WIND一级分类_含债券一级分类等));
                        break;
                    }else if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
                        selectOptionList.addAll(getOptions(DictCode.存款类资产类型));
                        break;
                    }else if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_基金.getValue())){
                        selectOptionList.addAll(getOptions(DictCode.基金类资产类型));
                        break;
                    }else if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
                        selectOptionList.addAll(getOptions(DictCode.计划类资产一级分类));
                        break;
                    }else if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
                        selectOptionList.addAll(getOptions(DictCode.项目类一级分类));
                        break;
                    }
                    
                }
            }
        }else{
            selectOptionList.addAll(getOptions(DictCode.WIND一级分类_含债券一级分类等));
            selectOptionList.addAll(getOptions(DictCode.存款类资产类型));
            selectOptionList.addAll(getOptions(DictCode.基金类资产类型));
            selectOptionList.addAll(getOptions(DictCode.计划类资产一级分类));
            selectOptionList.addAll(getOptions(DictCode.项目类一级分类));
        }
        return selectOptionList;
    }
    
    /**
     * 汇总资产二级分类
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSecondLevelSelectOption")
    public  List<SelectOptionDto> getSecondLevelSelectOption() {
        Map<String,Object> paramMap = RequestUtil.getPageParam();
        List<SelectOptionDto> selectOptionList = new ArrayList<SelectOptionDto>();
        if(StringUtil.isNotEmpty(paramMap.get("finprodType2"))){
                String[] assetTypeArr= paramMap.get("finprodType2").toString().split(",");
                for(String assetType : assetTypeArr){
                    if(StringUtil.isNotEmpty(assetType)){
                        if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                            selectOptionList.addAll(getOptions(DictCode.WIND二级分类_含债券二级分类等));
                            break;
                        }else if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
                            selectOptionList.addAll(getOptions(DictCode.存款类资产存款类型));
                            break;
                        }else if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_基金.getValue())){
                            selectOptionList.addAll(getOptions(DictCode.基金类资产二级类型));
                            break;
                        }else if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
                            selectOptionList.addAll(getOptions(DictCode.计划类资产二级分类));
                            break;
                        }else if(StringUtil.equals(assetType, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
                            selectOptionList.addAll(getOptions(DictCode.资产_负债二级类别));
                            break;
                        }
                        
                    }
                }
            }else{
                selectOptionList.addAll(getOptions(DictCode.WIND二级分类_含债券二级分类等));
                selectOptionList.addAll(getOptions(DictCode.存款类资产存款类型));
                selectOptionList.addAll(getOptions(DictCode.基金类资产二级类型));
                selectOptionList.addAll(getOptions(DictCode.计划类资产二级分类));
                selectOptionList.addAll(getOptions(DictCode.资产_负债二级类别));
            }
            return selectOptionList;
    }
    
    private List<SelectOptionDto> getOptions(IDictCode dictCode){
        return this.getSelectDataFromDataDic(dictCode, null).getOptions();
    }
    
    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepFinProductAssetBalanceDto> selectAll()
        throws Exception {
        Page<RepFinProductAssetBalanceDto> page = repFinProductAssetBalanceService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        List<RepFinProductAssetBalanceDto> sumList = new ArrayList<RepFinProductAssetBalanceDto>();
        List<RepFinProductAssetBalanceDto> detailList = new ArrayList<RepFinProductAssetBalanceDto>();
        sumList= repFinProductAssetBalanceService.selectAllListSum(RequestUtil.getParameters(getRequest()));
        detailList = repFinProductAssetBalanceService.selectAllList(RequestUtil.getParameters(getRequest()));
        if(null!=sumList&&!sumList.isEmpty()){
            sumList.forEach(item ->{
                item.setPortfolioName(item.getPortfolioName().replaceAll(" ", StringUtil.EMPTY).replaceAll("null",StringUtil.EMPTY));
            });
        }
        File outFileSum = repFinProductAssetBalanceService.makeFile(
            "/com/joyintech/tams/invest/report/balance/conf/export_confSum.xml", sumList);
        File outFileDetail = repFinProductAssetBalanceService.makeFile(
            "/com/joyintech/tams/invest/report/balance/conf/export_confDetail.xml", detailList);
        File hbFile = fileMerge(outFileSum,outFileDetail);
        return ExportUtil.export(
            "资产余额表" + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(hbFile),
                                 hbFile, true);
    }
    
    /**
     * 文件合并
     * <功能详细描述>
     * @param outFile
     * @param outFile2
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public File fileMerge(File outFile,File outFile2) throws Exception{
        FileOutputStream fileOut = null;
        XSSFWorkbook newExcelCreat = new XSSFWorkbook();
        FileMergeUtil fileMergeUtil = new FileMergeUtil();
        fileMergeUtil.handle(outFile, newExcelCreat);
        fileMergeUtil.handle(outFile2, newExcelCreat);
        String allFileName = outFile.getPath();    //定义新生成的xlx表格文件
        try{
            fileOut = new FileOutputStream(allFileName);
            newExcelCreat.write(fileOut);
        }finally {
            if(fileOut != null){
                fileOut.flush();
                fileOut.close();
            }
        }
        return new File(allFileName);
    }
    
    
    
}
