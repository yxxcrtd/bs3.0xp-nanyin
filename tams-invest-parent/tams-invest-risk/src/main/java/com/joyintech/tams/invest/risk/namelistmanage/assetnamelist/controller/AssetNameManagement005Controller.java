package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagementService;
import com.joyintech.tools.collectors.JoyinCollectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 调整后预览
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see AssetNameManagement005Controller
 * @since 1.0
 */

@Controller
@PageId("AssetNameManagement005")
@RequestMapping("/invest/risk/namelistmanage/assetnamelist/AssetNameManagement005")
public class AssetNameManagement005Controller extends BaseController {
    @Autowired
    private AssetNameManagementService assetNameManagementService;

    @Override
    public String init(Model model) {
        String listId = getParam("listId");
        String operateStyle = getParam("operateStyle");
        model.addAttribute("listId",listId);
        model.addAttribute("operateStyle",operateStyle);
        model.addAttribute("RSK_INPUT_TYPE_MANUAL", DictCodeListExt2.名单操作方式_手工.getValue());
        model.addAttribute("RSK_INPUT_TYPE_BATCH", DictCodeListExt2.名单操作方式_批量.getValue());
        model.addAttribute("RSK_OPERATE_PS_01", DictCodeListExt2.名单操作方向_移出名单.getValue());
        model.addAttribute("RSK_OPERATE_PS_02", DictCodeListExt2.名单操作方向_移入名单.getValue());
        model.addAttribute("RSK_OPERATE_PS_03", DictCodeListExt2.名单操作方向_修改.getValue());
        model.addAttribute("RSK_OPERATE_PS_99", DictCodeListExt2.名单操作方向_不变.getValue());
        model.addAttribute("RSK_OPERATE_PS_NAME_01", DictCodeListExt2.名单操作方向_移出名单.getCodeName());
        model.addAttribute("RSK_OPERATE_PS_NAME_02", DictCodeListExt2.名单操作方向_移入名单.getCodeName());
        model.addAttribute("RSK_OPERATE_PS_NAME_03", DictCodeListExt2.名单操作方向_修改.getCodeName());
        model.addAttribute("RSK_OPERATE_PS_NAME_99", DictCodeListExt2.名单操作方向_不变.getCodeName());
        return super.init(model);
    }

    /**
     * get调整后结果
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getAdjPriview")
    public List<RskAccessListDetailDto> getAdjPriview()
        throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        //处理过的名单明细
        List<RskAccessListDetailDto> handleRskAccessListDetailList= JsonUtils.json2List(this.getParam("rskAccessListLogDtoList"),RskAccessListDetailDto.class);
        param.put("handleRskAccessListDetailList",handleRskAccessListDetailList);
        List<RskAccessListDetailDto> unhandleRskAccessListDetailDtoList = assetNameManagementService.getAdjPriview(param);
        Map<String, List<RskAccessListDetailDto>> handleRskAccessListDetailMap = handleRskAccessListDetailList.stream().collect(JoyinCollectors.groupingBy(item->item.getListDetailId()));
        List<RskAccessListDetailDto> unhandleRskAccessListDetailDtoList2 = new ArrayList<RskAccessListDetailDto>();
        for (RskAccessListDetailDto rskAccessListDetailDto : unhandleRskAccessListDetailDtoList) {
             if (handleRskAccessListDetailMap.containsKey(rskAccessListDetailDto.getListDetailId())) {
                 unhandleRskAccessListDetailDtoList2.add(rskAccessListDetailDto);
            }
        } 
        unhandleRskAccessListDetailDtoList.removeAll(unhandleRskAccessListDetailDtoList2);
        handleRskAccessListDetailList.addAll(unhandleRskAccessListDetailDtoList);
        return handleRskAccessListDetailList;
    }

}
