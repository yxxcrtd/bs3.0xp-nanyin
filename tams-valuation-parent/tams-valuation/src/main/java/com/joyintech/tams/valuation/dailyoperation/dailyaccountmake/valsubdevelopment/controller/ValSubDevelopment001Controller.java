/*
 * 文件名：ValSubDevelopment001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：zwq
 * 修改时间：2020年12月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.service.SystemToolService;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.common.auxiliary.dto.BokBookdetailCurrentDto;
import com.joyintech.tams.valuation.common.auxiliary.dto.GenerateSqlDto;
import com.joyintech.tams.valuation.common.auxiliary.service.OperationAuxiliaryService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dto.ValSubDevelopment001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.service.ValSubDevelopment001Service;
import com.joyintech.tools.dto.FileDisposeResultEntity;
import com.joyintech.tools.util.ExcelTools;
import com.joyintech.tools.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

@Controller
@PageId("ValSubDevelopment001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valsubdevelopment/ValSubDevelopment001")
public class ValSubDevelopment001Controller extends BaseController {

    @Autowired
    private ValSubDevelopment001Service valSubDevelopment001Service;

    @Autowired
    private OperationAuxiliaryService operationAuxiliaryService;

    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * 上传控件进度条Service
     */
    @Autowired(required = false)
    @Qualifier("commonProgressBarService")
    private ProgressBarService progressBarService;

    /**
     * 重写init方法，加载页面
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ValSubDevelopment001Dto dto){
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
//        if (!StringUtil.isNotEmpty(dto.getBooksetIds())){
//            errorList.add(new ValidMessage("账套模板为必填项！"));
//        }
        if (!StringUtil.isNotEmpty(dto.getThirdSubjectId())){
            errorList.add(new ValidMessage("待拓展科目号为必填项！"));
        }
        ajaxResult.setErrorList(errorList);
        if (errorList.size()>0){
            return ajaxResult;
        }
        return ajaxResult;
    }


    @ResponseBody
    @RequestMapping("submit")
    public AjaxResult submit(@RequestBody ValSubDevelopment001Dto vsdDto){
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();

        String pageId = "ValSubDevelopment001";
        String barId = ProgressBarService.FILE_UPLOAD_ID;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("subjectNo",vsdDto.getThirdSubjectId());
        paramMap.put("booksetIdTmplList",vsdDto.getBooksetIds());
        paramMap.put("booksetIdList",null);
        paramMap.put("booksetId",null);
        paramMap.put("finprodType",null);
        paramMap.put("type2",null);
        paramMap.put("tradeMarket",null);
        paramMap.put("isQtzq",null);

        // 账务当前表原科目信息List
        // modify by yanya on 20201230 增加了参数
        List<BokBookdetailCurrentDto> cwdqbykmxxList = operationAuxiliaryService.getBookdetailCurrentData(paramMap);
        if (cwdqbykmxxList == null || cwdqbykmxxList.size() == 0){
            errorList.add(new ValidMessage( "科目号【"+vsdDto.getThirdSubjectId().toString()+"】在核算数据中不存在！"));
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }

        progressBarService.updatePercent(pageId, barId, 0.50D, false);//初始化进度条

        // 查询结果数据分析
        // 待更新账务明细代码Map
        Map<String, Object> dgxcwmxMap = new HashMap<>();
        // 新增业务明细科目Map
        Map<String, Object> xzywmxMap = new HashMap<>();
        // 循环当前账务当前表原科目信息List
        for (BokBookdetailCurrentDto dto:cwdqbykmxxList){
            // 账务当前表ENTITY.四级科目号 = 账务当前表ENTITY.科目号 + 账务当前表ENTITY.业务明细代码
            dto.setDetailSubjectNo(dto.getSubjectNo()+dto.getBusiId());
            if (!StringUtil.isNotEmpty(dto.getDetailSubnameTail()) || !StringUtil.isNotEmpty(dto.getDetailSubjectNoTail())){
                BokDetailId zwmxDto = (BokDetailId)dgxcwmxMap.get(dto.getBokDetailId());         // 账务明细Dto
                if (zwmxDto == null){
                    zwmxDto = interfCommonService.settingDetailSubjectNoTaiL(dto.getBusiId(),dto.getBusiDetailType(),dto.getBranch(),null,dto.getBooksetId());
                    dgxcwmxMap.put("zwmxDto",zwmxDto);
                }
                dto.setDetailSubjectNoTail(zwmxDto.getDetailSubjectNoTail());
                dto.setDetailSubnameTail(zwmxDto.getDetailSubnameTail());
            }

            // 初始化【业务明细科目】数据
            BokBussDetsub ywmxkmDto = (BokBussDetsub)xzywmxMap.get(dto.getBooksetId() + dto.getDetailSubjectNo());
            if (ywmxkmDto == null){
                BokBussDetsub sub = new BokBussDetsub();
                sub.setBooksetId(dto.getBooksetId());                   //账套代码
                sub.setDetailSubjectNo(dto.getDetailSubjectNo()); //四级科目号
                sub.setDetailSubjectName(dto.getSubjectName()+dto.getDetailSubnameTail());   // 四级科目名称
                sub.setFsubjectId(dto.getSubjectNo());  // 上级科目号
                sub.setDetailSubjectNoTail(dto.getDetailSubjectNoTail()); //四级科目尾号
                sub.setDetailSubnameTail(dto.getDetailSubnameTail()); //四级科目尾号名称

                xzywmxMap.put(dto.getBooksetId()+dto.getDetailSubjectNo(),sub);
            }

            // 先删再插
            // 删除业务明细科目表
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("booksetId",dto.getBooksetId());
            paraMap.put("detailSubjectNo",dto.getDetailSubjectNo());
            valSubDevelopment001Service.deleteBokBussDetSub(paraMap);
            // 插入
            BokBussDetsub bokSub = (BokBussDetsub)xzywmxMap.get(dto.getBooksetId() + dto.getDetailSubjectNo());
            valSubDevelopment001Service.insertBokBussDetSub(bokSub);

            progressBarService.updatePercent(pageId, barId, 0.75D, false);//初始化进度条

            // 表数据更新
            // 更新数据：账务明细历史表(BOK_BOOKDETAIL_ALL)、账务明细当前表(BOK_BOOKDETAIL_CURRENT)、账务明细代码表(BOK_DETAIL_ID)
            // 账务明细代码表(BOK_DETAIL_ID)
            BokDetailId zDto = (BokDetailId)dgxcwmxMap.get(dto.getBokDetailId());
            if (zDto != null){
                BokBookdetailCurrentDto bb = new BokBookdetailCurrentDto();
                bb.setDetailSubjectNoTail(zDto.getDetailSubjectNoTail());
                bb.setDetailSubnameTail(zDto.getDetailSubnameTail());
                bb.setUpdateUser(LoginUserUtil.getUserName());
                bb.setUpdateTime(DateUtil.now());
                bb.setBooksetId(dto.getBooksetId());
                bb.setBokDetailId(dto.getBokDetailId());
                valSubDevelopment001Service.updateBokDetailId(bb);
            }

            BokBookdetailCurrentDto bbd = new BokBookdetailCurrentDto();
            bbd.setDetailSubjectNo(dto.getDetailSubjectNo());
            bbd.setUpdateUser(LoginUserUtil.getUserName());
            bbd.setUpdateTime(DateUtil.now());
            bbd.setSubjectNo(dto.getSubjectNo());
            bbd.setBokDetailId(dto.getBokDetailId());
            bbd.setBooksetId(dto.getBooksetId());
            // 账务明细历史表(BOK_BOOKDETAIL_ALL)
            valSubDevelopment001Service.updateBokBookDetailAll(bbd);
            // 账务明细当前表(BOK_BOOKDETAIL_CURRENT)
            valSubDevelopment001Service.updateBokBookDetailCurrent(bbd);
        }

        progressBarService.updatePercent(pageId, barId, 1D, false);//更新百分百
        ajaxResult.setMessage("拓展成功！");
        return ajaxResult;
    }

    /**
     * 查询账套模板
     */
    @ResponseBody
    @RequestMapping("selectBooksetId")
    public Page<SelectOptionDto> selectBooksetId(){
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("booksetType", DictCodeList.账套类别_账套模板.getValue());
        Page<SelectOptionDto> booksetId = valSubDevelopment001Service.selectBooksetId(param);
        return booksetId;
    }

    /**
     * 查询带扩展科目号
     * @return
     */
    @ResponseBody
    @RequestMapping("selectThirdSubjectId")
    public Page<SelectOptionDto> selectThirdSubjectId(){
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("subjectLevel", DictCodeListExt1.科目级别_3级.getValue());
        Page<SelectOptionDto> thirdSubjectId = valSubDevelopment001Service.selectThirdSubjectId(param);
        return thirdSubjectId;
    }

    /**
     * add by zwq 2021-1-14
     * 脚本生成
     */
    @RequestMapping("generateSql")
    public ResponseEntity<byte[]> generateSql() throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        Map<String, Object> param = RequestUtil.getPageParam();
        String str2 = (String) param.get("thirdSubjectId");
        List<String> extendSubNoList = Arrays.asList(str2.split(","));      // 待扩展科目号(List)
        String str1 = (String) param.get("booksetIds");
        // modify by zwq 2021-1-18 start
        // 账套模板为非必填项，所以booksetIdsList可能为空
        List<String> booksetIdsList = null;                  // 账套模板(List)
        if (StringUtil.isNotEmpty(str1)){
            booksetIdsList = Arrays.asList(str1.split(","));
        }
        // modify by zwq 2021-1-18 end
        GenerateSqlDto generateSqlDto = operationAuxiliaryService.generateSql(booksetIdsList, extendSubNoList);
        return downloadSqlFile(generateSqlDto.getSql());
    }

    private ResponseEntity<byte[]> downloadSqlFile(String sql) throws IOException {
        // 新建文件
        String fileName = UUID.randomUUID().toString() + ".sql";
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String outPath = exportFilePath + "/" + fileName;
        File tempFile = new File(outPath);
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdirs();
        }
        File file = new File(outPath);
        file.createNewFile();

        // 往文件中写入数据
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            byte[] bytes = sql.getBytes();
            outputStream.write(bytes);
        }catch (Exception e){

        }finally {
            outputStream.close();
        }

        // 导出日期
        String sqlExportDate = DateUtil.format(new Date(), DateUtil.DEFAULT_DATE_FORMAT_EN);
        // 导出文件
        return ExportUtil.export("扩展四级科目执行脚本_"+sqlExportDate+".sql",file,true);
    }

}
