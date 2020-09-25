package com.joyintech.invest.interf.common.core.controller;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.invest.interf.common.core.contants.InterfList;
import com.joyintech.invest.interf.common.core.entity.InterfStatus;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import com.joyintech.invest.interf.common.core.service.InvestInterfCommonService;
import com.joyintech.invest.interf.common.core.service.InvestInterfaceLogService;

/**
 * @description 接口核心API入口
 *
 * @author 王波
 * @version 1.0 2020年01月16日
 * @see InterfCoreController
 * @since 1.0
 */
@RestController
@RequestMapping("/invest/interf")
public class InterfCoreController {

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 共通
	 */
	@Autowired(required = false)
	private InvestInterfCommonService interfCommonService;
	@Autowired
	private InvestInterfaceLogService interfaceLogService;

	/**
	 * @ 核心执行方法
	 *
	 * @param dataMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("send")
	public Map<String, Object> send(@RequestBody Map<String, Object> dataMap) {

		String resStr = JsonUtils.object2Json(dataMap);
		logger.info("请求报文:{}", resStr);

		// 1. 读取pageId，通过接口ID获取接口
		String pageId = (String) dataMap.get("pageId");

		// 1.1 若参数为空，则处理结束返回结果
		if (StringUtils.isBlank(pageId)) {
			return interfCommonService.errMsg("参数异常【空的参数列表】");
		}

		// 1.2 通过pageId 获取接口ID
		String interfId = getInterfIdByPageId(pageId);

		// 2.根据接口ID取得接口模块实例进行事件分发
		InterfSendBaseService<?> service = InterfList.getSendBean(interfId);
		// 2.1 若接口模块不存在，则处理结束返回结果
		if (service == null) {
			return interfCommonService.errMsg("接口模块异常【" + interfId + "对应的接口不存在】");
		}

		// 接口模块处理
		return service.execute(dataMap);
	}

	/**
	 *
	 * 直接发送
	 *
	 * @param dataMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("reSendUtility")
	public Map<String, Object> reSendUtility(@RequestBody Map<String, Object> dataMap) {
		return interfCommonService.sendToUitility(dataMap);
	}

	/**
	 * @ 核心执行方法（接收接口）
	 *
	 * @param resStr
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("recv")
	public String recv(@RequestBody String resStr) {

		logger.info("请求报文:{}", resStr);

		Map<String, Object> dataMap = JsonUtils.json2Object(resStr, Map.class);

		Map<String, Object> head = (Map<String, Object>) dataMap.get("head");
		String interfId = (String) head.get("INF_ID");
		// 2.根据接口ID取得接口模块实例进行事件分发
		InterfRecvBaseService<?> service = InterfList.getRecvBean(interfId);
		// 2.1 若接口模块不存在，则处理结束返回结果
		if (service == null) {
			return JsonUtils.object2Json(interfCommonService.errMsg("接口模块异常【" + interfId + "对应的接口不存在】"));
		}

		// 接口模块处理
		return JsonUtils.object2Json(service.execute(dataMap));
	}

	/**
	 * @ 获取日志列表
	 *
	 * @param dataMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("selectForPage/{page}/{length}")
	public Page<InterfStatus> selectForPage(@RequestBody Map<String, Object> dataMap, @PathVariable("page") int page,
			@PathVariable("length") int length) {
		return interfaceLogService.selectForPage(page, length, dataMap);
	}

	/**
	 * @decription: 根据pageId 获取接口ID SomdRepPledur
	 *
	 */
	private String getInterfIdByPageId(String pageId) {
		String res = "";
		switch (pageId) {
		case "TrdBondInvestTrade002":
		case "TrdConBond002":
		case "TrdConBond003":
		case "TrdBondToStock002":
			res = "S005";
			break;
		case "TrdBondDuration002":
			res = "S006";
			break;
		case "TrdFundInvestTrade002.01":
		case "TrdFundInvestTrade003.01":
			res = "S008";
			break;
        case "TrdPlanRateInvestTrade002":
            res = "S020";
            break;
        case "TrdPlanNetInvestTrade002":
        case "TrdPlanNetInvestTrade003":
            res = "S022";
            break;
        case "TrdPlanCcyInvestTrade002":
        case "TrdPlanCcyInvestTrade003":
            res = "S024";
            break;
		case "TrdFundInvestTrade002.02":
		case "TrdFundInvestTrade003.02":
			res = "S010";
			break;
		case "TrdStock002":
			res = "S033";
			break;
		case "TrdFundDuration002.01":
			res = "S009";
			break;
		case "TrdFundDuration002.02":
			res = "S011";
			break;
		case "TrdProjectDuration002":
			res = "S016";
			break;
		case "TrdPlanInterestDuration002":
			res = "S021";
			break;
		case "TrdPlanNetDuration002":
			res = "S023";
			break;
		case "TrdSpotexInvestTrade002":
			res = "S047";
			break;
		case "TrdForwexInvestTrade002.01":
			res = "S048";
			break;
		case "TrdForwexInvestTrade002.02":
			res = "S049";
			break;
		case "TrdFxswapInvestTrade002.01":
			res = "S050";
			break;
		case "TrdFxswapInvestTrade002.02":
			res = "S051";
			break;
		case "InvestFeePay002":
			res = "S052";
			break;
		case "PtlFundAllocation002":
			res = "S053";
			break;
		case "MstTradeOpponent002":
			res = "S054";
			break;
		case "TrdDepoDuration002":
			res = "S030";
			break;
		case "TrdLendInvestTrade002":
			res = "S038";
			break;
		case "FinBondTargetAsset002S001":
			res = "S001";
			break;
		case "FinBondTargetAsset002S002":
			res = "S002";
			break;
		case "FinBondTargetAsset002S003":
			res = "S003";
			break;
		case "FinBondTargetAsset002S004":
			res = "S004";
			break;
		case "FinFundTargetAsset002S007":
			res = "S007";
			break;
		case "FinProjectTargetAsset002S012":
			res = "S012";
			break;
		case "FinProjectTargetAsset002S013":
			res = "S013";
			break;
		case "FinProjectTargetAsset002S014":
			res = "S014";
			break;
		case "FinPlanTargetAsset002S017":
			res = "S017";
			break;
		case "FinPlanTargetAsset002S018":
			res = "S018";
			break;
		case "FinPlanTargetAsset002S019":
			res = "S019";
			break;
		case "FinDepositTargetAsset002S026":
			res = "S026";
			break;
		case "FinDepositTargetAsset002S027":
			res = "S027";
			break;
		case "FinDepositTargetAsset002S028":
			res = "S028";
			break;
		case "FinStockTargetAsset002S031":
			res = "S031";
			break;
		case "TrdRepoDuration002":
			res = "S035";
			break;
		case "TrdRepoBuyoutDuration002":
			res = "S037";
			break;
		case "TrdLendDuration002":
			res = "S039";
			break;
		case "TrdOptionTrade002":
			res = "S042";
			break;
		case "TrdSecLendDuration002":
			res = "S041";
			break;
		case "TrdOptionDuration002":
			res = "S043";
			break;
		case "TrdDepositInvestTrade002":
			res = "S029";
			break;
		case "TrdProjectInvestTrade002":
			res = "S015";
			break;
		case "TrdBondLendInvestTrade002":
			res = "S040";
			break;
		case "TrdOutRightRepoTrade002":
			res = "S036";
			break;
		case "TrdIRSTrade002":
			res = "S044";
			break;
		case "MstMarketInstitutions002":
			res = "S055";
			break;
		case "MstNetAssetQuotationInfo002":
			res = "S058";
			break;
		case "MstBondQuotationInfo002":
			res = "S059";
			break;
		case "TrdStockBonus002":
			res = "S033";
			break;
		case "TrdSecOverdueDuration001":
			res = "S057";
			break;
		case "TrdRepoTrade002":
			res = "S034";
			break;
		case "RofvBottomValuation":
				res = "S060";
				break;
		case "RofvCustomerDeal":
				res = "S061";
				break;
		default:
			break;
		}
		return res;
	}

    /**
     * 查找文件夹是否存在文件，如果存在调用接口执行方法(文件传输)，如果不存在则提示
     *
     * @param resStr
     * @return
     */
    @PostMapping("labelfile")
    public String labelFile(@RequestBody String resStr) {
        logger.info("请求报文:{}", resStr);
        Map<String, Object> dataMap = JsonUtils.json2Object(resStr, Map.class);
        Map<String, Object> head = (Map<String, Object>) dataMap.get("head");
        String interfId = (String) head.get("INF_ID");
        // 拿出json报文body里所包含的文件名，文件路径，建立日期(文件路径为绝对路径)
        Map<String, Object> body = (Map<String, Object>) dataMap.get("body");
        String filePath = (String) body.get("FILE_PATH");
        String fileName = (String) body.get("FILE_NAME");
        String fileCreateTime = (String) body.get("FILE_CREATE_TIME");
        // 如果拿出的三者有一个没有，则提示对应的找不到信息
        if(StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName) || StringUtils.isBlank(fileCreateTime)){
            return JsonUtils.object2Json(interfCommonService.errMsg("接口模块异常【" + "JSON报文里参数不足】"));
        }
        try{
            // 根据文件路径查询有没有此文件
            long startTime = System.currentTimeMillis();
            long endTime = System.currentTimeMillis();
            while(!findFile(filePath)) {
                endTime = System.currentTimeMillis();
                long sub = endTime - startTime;
                // 如果超过超时时间了，那么就跳出
                if(sub > 60000) {
                    return JsonUtils.object2Json(interfCommonService.errMsg("接口模块异常【" + filePath + "根据这个路径查找不到此文件】"));
                }
            }
            return JsonUtils.object2Json(interfCommonService.executeByRecvFile(interfId, fileCreateTime, filePath));
        } catch (Exception e){
            e.printStackTrace();
            return JsonUtils.object2Json(interfCommonService.errMsg("接口模块异常【" + "查找文件的时候发生异常】"));
        }
    }

    /**
     * 根据绝对路径检查文件是否存在
     * @param filePath
     * @return
     */
	private boolean findFile(String filePath){
        File file = new File(filePath);
        return file.exists();
    }
}
