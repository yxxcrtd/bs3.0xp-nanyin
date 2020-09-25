/*
 * 文件名：RskAuthorityManageValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.rskauthoritymanage.valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.rskauthoritymanage.dto.RskAuthorityManageDto;
import com.joyintech.tams.invest.risk.rskauthoritymanage.service.RskAuthorityManageService;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年11月30日
 * @see RskAuthorityManageValidator
 * @since 1.0
 */
@Component
public class RskAuthorityManageValidator implements BeanValidator{
    @Autowired
    private RskAuthorityManageService rskAuthorityManageService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        RskAuthorityManageDto dto = (RskAuthorityManageDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            // 删除check
            List<RskAuthorityManageDto> list = dto.getSelectedItem();
            if(list.size()>0 && list!=null){
                for (RskAuthorityManageDto e : list) {
                    RskAuthorityManageDto entity = rskAuthorityManageService.selectBySecNo(e);
                    if(entity == null){
                        resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0022")));
                    }
                }
            }
            else{
                resultList.add(new ValidMessage(this.getMessage("COMMON_E_0002")));
            }
            // 增加有资产交易在流程中的情况下，不准删除授权数据
            if (list != null && list.size() > 0) {
                if (rskAuthorityManageService.selectAssetTrade(list) > 0) {
                    resultList.add(new ValidMessage(this.getMessage("RSK_E_0057")));
                }
            }
        }
        else if(!StringUtil.equals(dto.getOperateType(), "delete") && dto.getOperateType() != null
            && !StringUtil.equals(dto.getOperateType(), "")){
            List<RskAuthorityManageDto> newListDto = dto.getSelectedItem();
            List<RskAuthorityManageDto> list = rskAuthorityManageService.getListDto(dto);
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            }
            else{
                //判断数据库中用户的授权是否重复
                List<String> userNames = StringUtil.splitToList(GlobalConstant.COMMA,dto.getUserName());
                if(userNames != null && userNames.size()>0 && newListDto != null && newListDto.size()>0){
                    for(String userName:userNames){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("userName", userName);
                        map.put("rolePos", dto.getRolePos());
                        List<RskAuthorityManageDto> lDto = rskAuthorityManageService.getDtoByUserName(map);
                        boolean flag1 = false;
                        if(lDto != null && lDto.size()>0){
                            for(int i=0;i < lDto.size();i++){
                                if(flag1){
                                    break;
                                }
                                for(int j=0;j<newListDto.size();j++){
                                    if(newListDto.get(j).getAssetType() != null 
                                        && !StringUtil.equals("", newListDto.get(j).getAssetType())){
                                      //如果数据库里的数据与页面数据重复
                                        flag1 = rskAuthorityManageService.checkDtoIsConflict(lDto.get(i),newListDto.get(j));
                                        if(flag1){
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if(flag1){
                            String realName = rskAuthorityManageService.selectRealNameByUserName(userName);
                            //message
                            resultList.add(new ValidMessage(this.getMessage("RSK_W_0028",getMessage("RskAuthorityManage001.userName")+":"+realName)));
                        }
                    }
                }
                /*if(list != null && list.size()>0){
                    for(RskAuthorityManageDto entity:list){
                        List<RskAuthorityManageDto> dtoList = rskAuthorityManageService.selectByDto(entity);
                        if(dtoList != null && dtoList.size()>0){
                            resultList.add(new ValidMessage(this.getMessage("RSK_W_0021", getMessage("RskAuthorityManage001.userName")+","+getMessage("RskAuthorityManage001.assetType"),
                                entity.getUserName()+","+entity.getAssetTypeStr())));
                        }
                    }
                }*/
            }
            if(list != null && list.size()>0){
                for(RskAuthorityManageDto e:list){
                    //角色岗位不能为空
                    if(e.getRolePos() == null){
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006", getMessage("RskAuthorityManage001.rolePos"))));
                    }
                    //用户不能为空
                    if(e.getUserName() == null){
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006", getMessage("RskAuthorityManage001.userName"))));
                    }
                    //交易方向不能为空
                    if(e.getTradeDirection() == null || "".equals(e.getTradeDirection())){
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006", getMessage("RskAuthorityManage001.tradeDirection"))));
                    }
                    //资产类型不能为空
                    if(e.getAssetType() == null || "".equals(e.getAssetType())){
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006", getMessage("RskAuthorityManage001.assetType"))));
                    }
                    //授权类型不能为空
                    if(e.getAuthorizeType() == null || "".equals(e.getAuthorizeType())){
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006",getMessage("RskAuthorityManage001.authorizeType"))));
                    }
                  //授权额度不能为空
                    if(e.getAuthorizeType() != null && StringUtil.equals(e.getAuthorizeType(), DictCodeList.授权类型_授权.getValue())
                        && e.getAuthorizeLimitAmt() == null){
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006",getMessage("RskAuthorityManage001.authorizeLimitAmt"))));
                    }
                }
            }
          //判断页面授权是否重复
//          校验flag
          boolean flag2 = false;
//          数据分组，以资产一级类别作为区分，组装map
          Map<String,List<RskAuthorityManageDto>> map = newListDto.stream().collect(JoyinCollectors.groupingBy(x->x.getAssetType()));
//          key ,value
          for(List<RskAuthorityManageDto> tempList : map.values()){
              if(flag2){
                  break;
              }
//              校验list
              flag2 = rskAuthorityManageService.checkList(tempList);
          }
//          check结束 判断check结果
          if(flag2){
//              message
              resultList.add(new ValidMessage(this.getMessage("RSK_W_0027")));
          }
          
          //判断新建或修改的数据跟流程表中是否冲突
          WflValidInputParameter parameter = new WflValidInputParameter();
          parameter.setPageId(dto.getPageId());
          List<RskAuthorityManageDto> workList = rskAuthorityManageService.getBusinessData(parameter);
          List<RskAuthorityManageDto> pageList = rskAuthorityManageService.getListDto(dto);
          if (!WorkFlowUtil.isNotFromToDoList()) {
              if(dto != null && dto.getWorkflowId() != null){
                //判断数据库中用户的授权是否重复
                  List<String> userNames = StringUtil.splitToList(GlobalConstant.COMMA,dto.getUserName());
                  if(userNames != null && userNames.size()>0 && newListDto != null && newListDto.size()>0){
                      for(String userName:userNames){
                          Map<String,Object> param = new HashMap<String,Object>();
                          param.put("userName", userName);
                          param.put("rolePos", dto.getRolePos());
                          List<RskAuthorityManageDto> lDto = rskAuthorityManageService.getDtoByUserName(param);
                          boolean flag1 = false;
                          if(lDto != null && lDto.size()>0){
                              for(int i=0;i < lDto.size();i++){
                                  if(flag1){
                                      break;
                                  }
                                  for(int j=0;j<newListDto.size();j++){
                                      if(newListDto.get(j).getAssetType() != null 
                                          && !StringUtil.equals("", newListDto.get(j).getAssetType())){
                                        //如果数据库里的数据与页面数据重复
                                          flag1 = rskAuthorityManageService.checkDtoIsConflict(lDto.get(i),newListDto.get(j));
                                          if(flag1){
                                              break;
                                          }
                                      }
                                  }
                              }
                          }
                          if(flag1){
                              String realName = rskAuthorityManageService.selectRealNameByUserName(userName);
                              //message
                              resultList.add(new ValidMessage(this.getMessage("RSK_W_0028",getMessage("RskAuthorityManage001.userName")+":"+realName)));
                          }
                      }
                  }
              }
          }
          if(workList != null && workList.size()>0){
              for(RskAuthorityManageDto workDto:workList){
                  if(WorkFlowUtil.isNotFromToDoList() || !StringUtil.equals(dto.getWorkflowId(), workDto.getWorkflowId()) ){
                      if(workDto.getAllData() != null && !StringUtil.equals(workDto.getAllData(), "")){
                          try {
                              RskAuthorityManageDto data0 = JsonUtils.json2Object(workDto.getAllData(), RskAuthorityManageDto.class);
                              RskAuthorityManageDto data1 = JsonUtils.json2Object(data0.getAllData(), RskAuthorityManageDto.class);
                              List<RskAuthorityManageDto> wDto = data1.getSelectedItem();
                                  boolean flag3 = false;
                                  if(wDto != null && wDto.size()>0 && pageList != null && pageList.size()>0){
                                      for(int i=0;i < wDto.size();i++){
                                          if(flag3){
                                              break;
                                          }
                                          for(int j=0;j<pageList.size();j++){
                                              if(pageList.get(j).getRolePos() != null && !StringUtil.equals("", pageList.get(j).getRolePos())
                                                  && pageList.get(j).getUserName() != null && !StringUtil.equals("", pageList.get(j).getUserName())
                                                  && pageList.get(j).getAssetType() != null && !StringUtil.equals("", pageList.get(j).getAssetType())){
                                                //如果数据库里的数据与页面数据重复
                                                  flag3 = rskAuthorityManageService.checkDtoIsConflict(wDto.get(i),pageList.get(j));
                                                  if(flag3){
                                                      break;
                                                  }
                                              }
                                          }
                                      }
                                  }
                                  if(flag3){
                                      //message
                                      resultList.add(new ValidMessage(this.getMessage("RSK_W_0035")));
                                  }
                              }
                        catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                          
                      }
                  }
              }
          }
        }
        return resultList;
    }
    
}
