/*
 * 文件名：BaseRiskMathCommController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.riskrds.controller.InterfRiskRdsController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 上农商个性化_指令环节风险试算改造需求_风险试算共通结果展示画面
 *
 * @author libaisong
 * @version 1.0 2020年11月27日
 * @see BaseRiskMathCommEtrController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/common/BaseRiskMathCommEtr")
public class BaseRiskMathCommEtrController extends BaseController {

    @Autowired
    private InterfRiskRdsController interfRiskRdsController;


    private static final IDictCode[] DICT_CODES = new DictCode[]{DictCode.指标类型, DictCode.监督状态, DictCode.单位,
            DictCode.监督类型, DictCode.监督频率, DictCode.金融产品类型_投管用, DictCode.WIND一级分类_含债券一级分类等,
            DictCode.基金类资产二级类型, DictCode.资产评级, DictCode.申万行业一级类型, DictCode.指标类型映射编码};

    /**
     * 初始化
     */
    @Override
    public String init(Model model) {
        Map<String, Object> map = RequestUtil.getPageParam();
		String initParam = this.getParam("initParam");
		ClientData resp = JSONObject.parseObject(initParam, ClientData.class);
		Map<String, Object> map1 = JsonUtils.object2Map(resp.getBody());
		List<Map> rskList = JSONObject.parseArray(JsonUtils.object2Json(map1.get("RSK_LIST")),Map.class);
		/*List<RiskTrialRetDto> li = rskList.stream().map(item -> JSONObject.parseObject(JsonUtils.object2Json(item),RiskTrialRetDto.class))
			.filter(riskTrialRetDto -> (StringUtil.equals("4", riskTrialRetDto.getKriStatus())|| (StringUtil.equals("3", riskTrialRetDto.getKriStatus()) && StringUtil.equals("向坏", riskTrialRetDto.getKriTrend()))))
			.collect(Collectors.toList());*/
		//add by longpeng 20201218 start上农商个性化——交易金额不能大于关联方单笔交易额上限（关联方单笔交易额上限-交易金额）”指标在非标资产指令新建时，指令超标显示试算未通过，但展示详情界面为空
		List<RiskTrialRetDto> li = rskList.stream().map(item -> JSONObject.parseObject(JsonUtils.object2Json(item), RiskTrialRetDto.class))
				.filter(riskTrialRetDto -> (StringUtil.equals("3", riskTrialRetDto.getKriStatus())|| StringUtil.equals("4", riskTrialRetDto.getKriStatus())|| (StringUtil.equals("3", riskTrialRetDto.getKriStatus()) && StringUtil.equals("向坏", riskTrialRetDto.getKriTrend()))))
				.collect(Collectors.toList());
		//add by longpeng 20201218 end上农商个性化——交易金额不能大于关联方单笔交易额上限（关联方单笔交易额上限-交易金额）”指标在非标资产指令新建时，指令超标显示试算未通过，但展示详情界面为空
        model.addAttribute("RiskTrialRetDto", li);
        if(StringUtil.isNotEmpty(map.get("IS_ETR"))){
        	model.addAttribute("IS_ETR", DictCodeListExt1.是否_是.getValue());
        }else{
        	model.addAttribute("IS_ETR", DictCodeListExt1.是否_否.getValue());
        }
        return "component/common/BaseRiskMathCommEtr";
    }

}
