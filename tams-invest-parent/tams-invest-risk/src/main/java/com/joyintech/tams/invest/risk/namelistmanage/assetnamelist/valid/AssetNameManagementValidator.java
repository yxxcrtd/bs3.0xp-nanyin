package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.valid;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagementService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 债券标的信息业务验证
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see AssetNameManagementValidator
 * @since 1.0
 */
@Component
public class AssetNameManagementValidator implements BeanValidator {

    @Autowired
    private AssetNameManagementService assetNameManagementService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object value) {
        RskAccessListDto dto = (RskAccessListDto) value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        WflValidInputParameter param = new WflValidInputParameter();
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            param.setWorkflowId(dto.getWorkflowId());//去除自身这个流程
        }
        param.setBusinessDataPk1(dto.getListId());
        if(StringUtil.equals(dto.getListDetailType(),DictCodeListExt2.名单主体类型_机构.getValue())){
            param.setPageId("OrgNameManagement003");
        }else{
            param.setPageId("AssetNameManagement003");
        }
        if (null != mstWorkflowCommonService &&
                mstWorkflowCommonService.isInWflProcess(param)) {
            resultList.add(
                    new ValidMessage(this.getMessage("COMMON_E_0012", dto.getListId())));
        }
        if(StringUtil.equals(dto.getOperateType(),"delete")) {
            //判断流程中是否存在相同的数据
        }else{
            //校验批量文件上传格式
            if (StringUtil.isNotEmpty(dto.getFileName())) {
                List<String> ids = Arrays.asList("资产代码","社会信用代码");
                List<String> names = Arrays.asList("资产名称","机构名称");
                ExcelUtil excelUtil=null;
                try {
                    /*excelUtil = ExcelUtil.build(
                    FileNameUtil.getUploadFilePath(dto.getFileName()), null);*/
                    excelUtil = ExcelUtil.build(dto.getFileName(), null);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                List<List<String>> dataList = excelUtil==null?null:excelUtil.readToList(0, 0, 0);
                // 检查读出信息是否为空
                if (null != dataList) {
                    if((StringUtil.equals(dto.getListDetailType(),DictCodeListExt2.名单主体类型_机构.getValue())&&(
                        dataList.get(0).get(0) == null ||!StringUtil.equals(ids.get(1),(dataList.get(0).get(0)))
                                || dataList.get(0).get(1) == null || !StringUtil.equals(names.get(1),(dataList.get(0).get(1)))
                                || dataList.get(0).size() > 2))
                    ||(StringUtil.equals(dto.getListDetailType(),DictCodeListExt2.名单主体类型_资产.getValue())&&(
                            dataList.get(0).get(0) == null ||!StringUtil.equals(ids.get(0),(dataList.get(0).get(0)))
                            || dataList.get(0).get(1) == null || !StringUtil.equals(names.get(0),(dataList.get(0).get(1)))
                            || dataList.get(0).size() > 2))){
                            resultList.add(new ValidMessage("", getMessage("RSK_W_0063",
                                    getMessage("AssetNameManagement003.listDetailName"))));
                        }
                    }
                return resultList;
                }
            }
            if(StringUtil.equals(dto.getOperateType(),"save")||StringUtil.equals(dto.getOperateType(),"submit")){
                Set<String> set = new  HashSet<String>();
                Set<String> set2 = new  HashSet<String>();
                Map<String,Object> paramMap = new HashMap<>();Map<String,Object> paramLevelMap = new HashMap<>();
                List<RskAccessListDetailDto> allrskAccessListDetailList = new ArrayList<>();
                //其他名单属性的资产明细(或机构)
                List<RskAccessListDetailDto> typerskAccessListDetailList = new ArrayList<>();
                //其他名单级别的资产或者明细
                List<RskAccessListDetailDto> levelrskAccessListDetailList = new ArrayList<>();
                //有变更的明细list
                List<RskAccessListDetailDto> otherRskAccessListDetailList = new ArrayList<>();
                if(dto.getRskAccessListDetailList().isEmpty()){
                    resultList.add(new ValidMessage("", getMessage("RSK_W_0064",
                            getMessage("AssetNameManagement003.listDetailName"))));
                    return resultList;
                }
                paramMap.put("handleRskAccessListDetailList",dto.getRskAccessListDetailList());
                paramMap.put("listId",dto.getListId());
                paramMap.put("listType",dto.getListType());
                paramMap.put("listDetailType",dto.getListDetailType());
                if(null!=dto.getRskAccessListDetailList()&&dto.getRskAccessListDetailList().size()>0){
                    otherRskAccessListDetailList =   dto.getRskAccessListDetailList().stream()
                            .filter(rskAccessListDetailDto ->(!StringUtil.equals(rskAccessListDetailDto.getOperatePs(),DictCodeListExt2.名单操作方向_移出名单.getValue())))
                            .collect(Collectors.toList())  ;
                } 
                paramMap.put("otherRskAccessListDetailList",otherRskAccessListDetailList); 
                if (!CollectionUtils.isEmpty(otherRskAccessListDetailList)) {
                    typerskAccessListDetailList = assetNameManagementService.getOtherRskAccessListDetailDtoList(paramMap); 
                } 
                paramLevelMap.put("handleRskAccessListDetailList",dto.getRskAccessListDetailList());
                paramLevelMap.put("listId",dto.getListId());
                paramLevelMap.put("listLevel",dto.getListLevel());
                paramLevelMap.put("listDetailType",dto.getListDetailType());
                paramLevelMap.put("otherRskAccessListDetailList",otherRskAccessListDetailList);
                levelrskAccessListDetailList = assetNameManagementService.getOtherRskAccessListDetailDtoList(paramMap);
                List<RskAccessListDetailDto> unhandleRskAccessListDetailDtoList = assetNameManagementService.getAdjPriview(paramMap);
                allrskAccessListDetailList.addAll(dto.getRskAccessListDetailList());
                allrskAccessListDetailList.addAll(unhandleRskAccessListDetailDtoList); 
                for(RskAccessListDetailDto rskAccessListDetailDto : allrskAccessListDetailList){
                    /*if(!StringUtil.equals(rskAccessListDetailDto.getOperatePs(), DictCodeListExt2.名单操作方向_移出名单.getValue())){*/
                        if(!set.add(rskAccessListDetailDto.getListDetailName())){
                            if(StringUtil.equals(dto.getListDetailType(),DictCodeListExt2.名单主体类型_机构.getValue())){
                                resultList.add(new ValidMessage("",
                                        getMessage("RSK_W_0059", getMessage("OrgNameManagement003.listDetailName"),rskAccessListDetailDto.getListDetailName())));
                                break;
                            }else{
                                resultList.add(new ValidMessage("",
                                        getMessage("RSK_W_0059", getMessage("AssetNameManagement003.listDetailName"),rskAccessListDetailDto.getListDetailName())));
                                break;
                            }

                        }
                        if(!set2.add(rskAccessListDetailDto.getListDetailId())){
                            if(StringUtil.equals(dto.getListDetailType(),DictCodeListExt2.名单主体类型_机构.getValue())){
                                resultList.add(new ValidMessage("",
                                        getMessage("RSK_W_0059", getMessage("OrgNameManagement003.listDetailId"),rskAccessListDetailDto.getListDetailId())));
                                break;
                            }else{
                                resultList.add(new ValidMessage("",
                                        getMessage("RSK_W_0059", getMessage("AssetNameManagement003.listDetailId"),rskAccessListDetailDto.getListDetailId())));
                                break;
                            }

                        }
                        //add by yangwenjun 20200905 增加机构名称长度控制
                        StringBuffer sb = new StringBuffer("");
                        String str=rskAccessListDetailDto.getListDetailName();
                        if (StringUtil.isNotEmpty(str)) {
                            // 中文长度算两位，每一个中文字符总长度+1空格，方便后续解析
                            String chinese = "[\u4e00-\u9fa5]";
                            for (int i = 0; i < str.length(); i++) {
                                String tempStr = str.substring(i, i + 1);
                                sb.append(tempStr);
                                if (tempStr.matches(chinese)) {
                                    // 遇到中文加空格
                                    sb.append(" ");
                                }
                            }
                        }
                        if(sb.length()>50) {
                        	resultList.add(new ValidMessage("",getMessage("RSK_W_0072", getMessage("OrgNameManagement003.listDetailName"),rskAccessListDetailDto.getListDetailName())));
                        }
                       //add by yangwenjun 20200905 增加机构名称长度控制
                       //add by yangwenjun 20200905 增加社会机构代码长度控制
                        sb = new StringBuffer("");
                        str=rskAccessListDetailDto.getListDetailId();
                        if (StringUtil.isNotEmpty(str)) {
                            // 中文长度算两位，每一个中文字符总长度+1空格，方便后续解析
                            String chinese = "[\u4e00-\u9fa5]";
                            for (int i = 0; i < str.length(); i++) {
                                String tempStr = str.substring(i, i + 1);
                                sb.append(tempStr);
                                if (tempStr.matches(chinese)) {
                                    // 遇到中文加空格
                                    sb.append(" ");
                                }
                            }
                        }
                        if(sb.length()>32) {
                        	resultList.add(new ValidMessage("",getMessage("RSK_W_0072", getMessage("OrgNameManagement003.listDetailId"),rskAccessListDetailDto.getListDetailName())));
                        }
                       //add by yangwenjun 20200905 增加社会机构代码长度控制
                   /* }*/
                }
                //其他名单属性
              
                if(typerskAccessListDetailList!=null&&typerskAccessListDetailList.size()>0){
                    if(StringUtil.equals(dto.getOperateStyle(),DictCodeListExt2.名单操作方式_手工.getValue())){
                        StringBuffer sbf = new StringBuffer();
                        for(RskAccessListDetailDto typeDto:typerskAccessListDetailList){
                            sbf.append(typeDto.getListDetailName()).append(","); 
                        }
                        if(StringUtil.equals(dto.getListDetailType(),DictCodeListExt2.名单主体类型_资产.getValue())){
                            resultList.add(new ValidMessage("资产已存在"+ SysDataUtil.getNameFromDataDict(DictCode.名单属性, typerskAccessListDetailList.get(0).getListType())+"中，无法提交！资产如下:"+sbf.toString()));
                        }else{
                            resultList.add(new ValidMessage("机构已存在"+ SysDataUtil.getNameFromDataDict(DictCode.名单属性, typerskAccessListDetailList.get(0).getListType())+"中，无法提交！机构如下:"+sbf.toString()));
                        }

                    }else if(StringUtil.equals(dto.getOperateStyle(),DictCodeListExt2.名单操作方式_批量.getValue())){
                        resultList.add(new ValidMessage(typerskAccessListDetailList.size()+"条信息已存在"+ SysDataUtil.getNameFromDataDict(DictCode.名单属性, typerskAccessListDetailList.get(0).getListType())+"中，无法录入系统"));
                    }
                } 
                //其他名单级别
                //仅限于资产名单管理
                if(typerskAccessListDetailList!=null&&typerskAccessListDetailList.size()>0){
                if(StringUtil.equals(dto.getListDetailType(),DictCodeListExt2.名单主体类型_资产.getValue())){
                    for(RskAccessListDetailDto levelDto:levelrskAccessListDetailList){
                        if(StringUtil.equals(dto.getOperateStyle(),DictCodeListExt2.名单操作方式_手工.getValue())){
                            StringBuffer sbf = new StringBuffer();
                            for(RskAccessListDetailDto typeDto:levelrskAccessListDetailList){
                                sbf.append(typeDto.getListDetailName()).append(",");
                            }
                            resultList.add(new ValidMessage("资产已存在"+ SysDataUtil.getNameFromDataDict(DictCode.名单级别, typerskAccessListDetailList.get(0).getListLevel())+"中，无法提交！资产如下:"+sbf.toString()));
                        }else if(StringUtil.equals(dto.getOperateStyle(),DictCodeListExt2.名单操作方式_批量.getValue())){
                            resultList.add(new ValidMessage(typerskAccessListDetailList.size()+"条信息已存在"+ SysDataUtil.getNameFromDataDict(DictCode.名单级别, typerskAccessListDetailList.get(0).getListLevel())+"中，无法录入系统"));
                        }

                    }
                }
              }
           }
        return resultList;
    }
}
