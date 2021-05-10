package com.joyintech.tams.valuation.basicdata.asset.option.valid; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luo
 * 修改时间：2019-12-20
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.basicdata.asset.option.dto.FinOptionTargetDto;
import com.joyintech.tams.valuation.basicdata.asset.option.service.FinOptionTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class FinOptionTargetValidator implements BeanValidator {

    @Autowired
    private FinOptionTargetService prodService;

    /**
     * 共通接口
     */
//    @Autowired
//    private CommonBusinessService commonBusinessService;

    @Override public List<ValidMessage> valid(Object value) {

        FinOptionTargetDto dto = (FinOptionTargetDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();

        if (StringUtil.equals(dto.getOperateType(), "delete")) { //删除时的联动校验
            List<FinOptionTargetDto> list = dto.getSelectedItem();
            // 判断是否选中数据
            if (list != null && list.size()>0) {
                for (FinOptionTargetDto mst : list) {



                }

            }
        }else if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit")) {//保存，提交时的校验
            //新建时，校验标的是否已经存在
            String pageModel =dto.getPageModel();
            if(StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, pageModel)){




            }else {
                if (StringUtil.isNotEmpty(dto.getTargetId())) {
                    FinOptionTargetDto tmp = prodService.getFinprodcodebyId(dto.getTargetId());
                    if (tmp != null) {
                        resultList.add(new ValidMessage("该期权标的已存在！"));
                    }
                }
            }

            if(StringUtil.isEmpty(dto.getTargetId())  ){
                resultList.add(new ValidMessage("标的代码为必填项不能为空！"));
            }
            if(StringUtil.isEmpty(dto.getTargetName())){
                resultList.add(new ValidMessage("标的名称为必填项不能为空！"));
            }
            if(StringUtil.isEmpty(dto.getTargetType())){
                resultList.add(new ValidMessage("标的类型为必填项不能为空！"));
            }


        }
        return resultList;
    }
}
