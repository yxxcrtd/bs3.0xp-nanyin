package com.jixu.adapter.common.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jixu.adapter.types.CallClassParamsDto;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;

/**
 * 投管接口业务共通
 * 
 * @program: 2.1.2
 * @description: 业务相关
 * @author: 强成西
 * @create: 2020-02-08 11:41
 **/
public class BusinessUtil {

	/**
	 * 发起方系统代码
	 */
	public static String SYSTEM_CODE_SEND = "ADAPTER";

	/**
	 * 接收方系统代码
	 */
	private static String SYSTEM_CODE_REC = "ADAPTER";

	/**
	 * check是否是日期格式
	 * 
	 * @param param 参数
	 * @return check结果
	 */
	public static boolean isErrorDate(Object param) {
		if (StringUtil.isNotEmpty(param)) {
			try {
				Date result = DateUtil.parse((String) param);
				if (result == null) {
					return true;
				}
			} catch (RuntimeException e) {
				return true;
			}
		}
		return false;
	}

	/**
	 * check是否是数值
	 * 
	 * @param param 金融产品类型
	 * @return check结果
	 */
	public static boolean isNotNumeric(Object param) {
		if (StringUtil.isNotEmpty(param)) {
			try {
				Double.parseDouble((String) param);
			} catch (NumberFormatException e) {
				return true;
			}
		}
		return false;
	}

	/**
	 * check是否正确的数据字典范围值
	 * 
	 * @param dictCode 数据字典类型
	 * @param itemCode 参数
	 * @return check结果
	 */
	public static boolean isErrorDictCode(String dictCode, Object itemCode) {
		if (StringUtil.isNotEmpty(itemCode)) {
			List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
			;
			if (null == items || items.stream().filter(item -> StringUtil.equals(item.getItemCode(), itemCode))
					.findFirst().orElse(null) == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 输出接口消息头
	 * 
	 * @param request  参数对象
	 * @param response 报文对象
	 * @param infId    接口ID
	 * @see
	 * @since 1.0
	 */
	public static void createSendInterfHeader(ClientData response, CallClassParamsDto dto) {
		// 消息头
		Map<String, Object> header = response.getHead();
		// 接口代码
		header.put("INF_ID", dto.getAdapterID());
		// 发起方系统代码
		header.put("REQUEST_SYS", SYSTEM_CODE_SEND);
		// 发起方流水号
		header.put("REQUEST_SEQNO", getSeqno());
		// 判断是否在BS系统中,是才使用平台日期，不然就使用系统日期
		// 发起方系统日期
		if (StringUtil.isNotEmpty(dto.getParams().get("platedate"))) {
			header.put("REQUEST_DATE",
					DateUtil.format((Date) (dto.getParams().get("platedate")), DateUtil.DEFAULT_OTHER_FORMAT));
		} else {
			header.put("REQUEST_DATE", DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT));
		}
		// 发起方系统时间戳
		header.put("REQUEST_TIME", DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1));
	}

	/**
	 * 输入接口消息头
	 * 
	 * @param request  参数对象
	 * @param response 报文对象
	 * @param infId    接口ID
	 * @see
	 * @since 1.0
	 */
	public static void createRecInterfHeader(Map<String, Object> request, ClientData response, String infId) {
		// 消息头
		Map<String, Object> header = response.getHead();
		// 接口代码
		header.put("INF_ID", infId);
		// 发起方流水号
		header.put("REQUEST_SEQNO", request.get("REQUEST_SEQNO"));
		// 应答码 TODO
		// header.put("RESPONSE_INFO_ID", "");
		// 应答描述
		// 接收方系统代码
		header.put("RESPONSE_SYS", SYSTEM_CODE_REC);
		// 发起方系统日期
		header.put("RESPONSE_DATE", DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT));
		// 发起方系统时间戳
		header.put("RESPONSE_TIME", DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1));
	}

	/**
	 * 流水号获取 生成规则:系统代码+日期
	 * 
	 * @see
	 * @since 1.0
	 */
	public static String getSeqno() {
		String seqno = SYSTEM_CODE_SEND + DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1);
		return seqno;
	}
}
