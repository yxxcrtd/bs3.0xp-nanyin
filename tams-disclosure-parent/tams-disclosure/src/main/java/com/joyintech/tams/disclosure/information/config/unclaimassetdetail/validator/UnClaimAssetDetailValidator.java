package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service.UnClaimAssetDetail002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UnClaimAssetDetailValidator implements BeanValidator {

    @Autowired
    private UnClaimAssetDetail002Service unClaimAssetDetail002Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        UnClaimAssetDetailDto dto = (UnClaimAssetDetailDto)value;
        Date platDate = DateUtil.platDateFromCache();
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            //删除check

        }else{
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                //新建check
                for (UnClaimAssetDetailDto unClaimAssetDetailDto:dto.getUnclaimList()){
                    if (!StringUtil.isNotEmpty(unClaimAssetDetailDto.getFinancier())){
                        resultList.add(new ValidMessage("融资客户 必填！"));
                    }
                    if (!StringUtil.isNotEmpty(unClaimAssetDetailDto.getProjectName())){
                        resultList.add(new ValidMessage("项目名称 必填！"));
                    }
                    if (!StringUtil.isNotEmpty(unClaimAssetDetailDto.getRemFinancingPeriod())){
                        resultList.add(new ValidMessage("剩余融资期限 必填！"));
                    }
                    if (!StringUtil.isNotEmpty(unClaimAssetDetailDto.getIncomeDistribute())){
                        resultList.add(new ValidMessage("到期收益分配 必填！"));
                    }
                    if (!StringUtil.isNotEmpty(unClaimAssetDetailDto.getTrdConstruct())){
                        resultList.add(new ValidMessage("交易结构 必填！"));
                    }
                    if (!StringUtil.isNotEmpty(unClaimAssetDetailDto.getRiskInfo())){
                        resultList.add(new ValidMessage("风险状况 必填！"));
                    }
                }
                if(StringUtil.isNotEmpty(dto.getEffDate())){
                    if(DateUtil.compareDate(platDate,dto.getEffDate())>0){
                        resultList.add(new ValidMessage("生效日要大于等于平台日期！"));
                    }
                }
                if(StringUtil.isNotEmpty(dto.getEffEndDate())){
                    if(DateUtil.compareDate(platDate,dto.getEffEndDate())>0){
                        resultList.add(new ValidMessage("生效日截止日期要大于等于平台日期！"));
                    }
                }
                //生效日截止日期要大于等于生效日
                if(StringUtil.isNotEmpty(dto.getEffDate()) && StringUtil.isNotEmpty(dto.getEffEndDate())){
                    if(DateUtil.compareDate(dto.getEffDate(),dto.getEffEndDate())>0){
                        resultList.add(new ValidMessage("生效日截止日期要大于等于生效日！"));
                    }
                }
            }else{
                Map<String,Object> map=new HashMap<>();
                map.put("nostdAssetId",dto.getNostdAssetId());
                //根据主键查询被修改的数据
                List<UnClaimAssetDetailDto> unClaimAssetDetailDtoList=unClaimAssetDetail002Service.checkNostdExistByProdId(map);
                if(!unClaimAssetDetailDtoList.isEmpty()){
                    UnClaimAssetDetailDto unClaimAssetDetailDto = unClaimAssetDetailDtoList.get(0);
                    if(DateUtil.compareDate(unClaimAssetDetailDto.getEffDate(),platDate)>0){
                        //生效日和生效截止日都是大于平台日的，前台都可以修改，要加校验
                        if(StringUtil.isNotEmpty(dto.getEffDate())){
                            if(DateUtil.compareDate(platDate,dto.getEffDate())>0){
                                resultList.add(new ValidMessage("生效日要大于等于平台日期！"));
                            }
                        }
                        if(StringUtil.isNotEmpty(dto.getEffEndDate())){
                            if(DateUtil.compareDate(platDate,dto.getEffEndDate())>0){
                                resultList.add(new ValidMessage("生效日截止日期要大于等于平台日期！"));
                            }
                        }
                        //生效日截止日期要大于等于生效日
                        if(StringUtil.isNotEmpty(dto.getEffDate()) && StringUtil.isNotEmpty(dto.getEffEndDate())){
                            if(DateUtil.compareDate(dto.getEffDate(),dto.getEffEndDate())>0){
                                resultList.add(new ValidMessage("生效日截止日期要大于等于生效日！"));
                            }
                        }
                    }else if(DateUtil.compareDate(unClaimAssetDetailDto.getEffEndDate(),platDate)>0){
                        //前台生效截止日可修改，要检验生效截止日
                        if(StringUtil.isNotEmpty(dto.getEffEndDate())){
                            if(DateUtil.compareDate(platDate,dto.getEffEndDate())>0){
                                resultList.add(new ValidMessage("生效日截止日期要大于等于平台日期！"));
                            }
                        }
                        //生效日截止日期要大于等于生效日
                        if(StringUtil.isNotEmpty(dto.getEffDate()) && StringUtil.isNotEmpty(dto.getEffEndDate())){
                            if(DateUtil.compareDate(dto.getEffDate(),dto.getEffEndDate())>0){
                                resultList.add(new ValidMessage("生效日截止日期要大于等于生效日！"));
                            }
                        }
                    }
                }

            }
            //校验同一个产品不能有两条数据的生效区间范围有重叠
            //查询产品所有的合作机构数据
            Map<String,Object> map=new HashMap<>();
            map.put("prodId",dto.getProdId());
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                map.put("excludeId",dto.getNostdAssetId());
            }
            List<UnClaimAssetDetailDto> unClaimAssetDetailDtoList=unClaimAssetDetail002Service.checkNostdExistByProdId(map);
            if(unClaimAssetDetailDtoList.size()>0){
                for (UnClaimAssetDetailDto unClaimAssetDetailDto : unClaimAssetDetailDtoList) {
                    if(!((DateUtil.compareDate(dto.getEffDate(),unClaimAssetDetailDto.getEffDate())<0 && DateUtil.compareDate(dto.getEffEndDate(),unClaimAssetDetailDto.getEffDate())<0)
                            || (DateUtil.compareDate(dto.getEffDate(),unClaimAssetDetailDto.getEffEndDate())>0 && DateUtil.compareDate(dto.getEffEndDate(),unClaimAssetDetailDto.getEffEndDate())>0))){
                        resultList.add(new ValidMessage("生效区间范围有重叠！"+"{"+DateUtil.format(unClaimAssetDetailDto.getEffDate(),"yyyy-MM-dd")
                                +"-"+DateUtil.format(unClaimAssetDetailDto.getEffEndDate(),"yyyy-MM-dd")+"}"));
                        break;
                    }
                }
            }
        }
        return resultList;
    }
}
