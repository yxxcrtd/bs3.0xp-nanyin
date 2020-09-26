package com.joyintech.tams.report.common.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.chencknum.dao.CheckNumMapper;
import com.joyintech.tams.report.chencknum.dto.TotalAmountInfoDto;
import com.joyintech.tams.report.interf.common.service.InterfService;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfo001Service;
import com.joyintech.tams.report.submitmaintenance.equityinfo.util.FileIO;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo001Service;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;


/**
 * 接口轮询任务
 * 
 * @author 何阳阳
 * @version 1.0 2017年10月30日
 * @see CheckNumService
 * @since 1.0
 */
@Service("InterfCheckNumService")
@Scope("prototype")
public class InterfCheckNumService extends InterfService {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(InterfCheckNumService.class);

	public void run() {
		long a = System.currentTimeMillis();
		// 报送日期为前一天
		String reportDate = new SimpleDateFormat(DateUtil.DEFAULT_OTHER_FORMAT).format(DateUtil.addDay(new Date(), -1));
		String fileName = "99701080000" + "_" + "CHB_TOTALAMOUNT" + "_0000_" + reportDate + "_A_"+reportDate.substring(4, 8)+"_"
				+ new SimpleDateFormat(DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1).format(new Date()).substring(8, 12);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();

		map.put("REPORT_DATE", reportDate);
		// 根据日期和类型获取待报送数据总数
		TotalAmountInfoDto totalAmountInfoDto = new TotalAmountInfoDto();
		totalAmountInfoDto.setReportDate(reportDate);
		totalAmountInfoDto.setInterfaceType("1");
		List<TotalAmountInfoDto> totalAmountInfoDto1 = checkNumMapper.queryTotalAmountInfo(totalAmountInfoDto);
		// 库中待报送数据为空则查询更新，不为空赋值
		if (totalAmountInfoDto1.size() != 0) {
			if (StringUtil.isEmpty(totalAmountInfoDto1.get(0).getAmount())) {

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("statusListStr", DictCodeListExt2.报送数据状态_新建.getValue() + GlobalConstant.COMMA + DictCodeListExt2.报送数据状态_修改.getValue());
				List<EquityInfoRegist001Dto> needSendList = equityInfo001Service.getNeedSendList(param);
				map.put("ID_AMOUNT", needSendList.size() + "");
				totalAmountInfoDto.setAmount(needSendList.size() + "");
			} else {
				map.put("ID_AMOUNT", totalAmountInfoDto1.get(0).getAmount());
			}
		} else {
			map.put("ID_AMOUNT", "0");
		}
		// 根据报送更新时间查询已报送数据身份信息数量
		long amountReport = checkNumMapper.getSendCount(new SimpleDateFormat(DateUtil.DEFAULT_OTHER_FORMAT).format(new Date()));
		map.put("ID_AMOUNT_REPORT", amountReport + "");
		totalAmountInfoDto.setAmountReport(amountReport + "");
		checkNumMapper.updateTotalAmountInfo(totalAmountInfoDto);
		// 根据日期和类型获取待报送数据总数
		totalAmountInfoDto.setInterfaceType("2");
		List<TotalAmountInfoDto> totalAmountInfoDto2 = checkNumMapper.queryTotalAmountInfo(totalAmountInfoDto);
		// 库中待报送数据为空则查询更新，不为空赋值
		if (totalAmountInfoDto2.size() != 0) {
			if (StringUtil.isEmpty(totalAmountInfoDto2.get(0).getAmount())) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("sendStatus", DictCodeListExt2.报送状态_未报送.getValue());
				map1.put("status", DictCodeListExt2.报送数据状态_新建.getValue() + GlobalConstant.COMMA + DictCodeListExt2.报送数据状态_修改.getValue());
				List<InvestorsHoldInfoRegist001Dto> needSendHoldList = investorsHoldInfo001Service.selectAllList2(map1);
				map.put("HOLD_AMOUNT", needSendHoldList.size() + "");
				totalAmountInfoDto.setAmount(needSendHoldList.size() + "");
			} else {
				map.put("HOLD_AMOUNT", totalAmountInfoDto2.get(0).getAmount());
			}
		} else {
			map.put("HOLD_AMOUNT", "0");
		}
		// 查询已报送数据持有信息数量
		long holdAmountReport = checkNumMapper.getSendHoldCount(reportDate);
		map.put("HOLD_AMOUNT_REPORT", holdAmountReport + "");
		totalAmountInfoDto.setAmountReport(holdAmountReport + "");
		checkNumMapper.updateTotalAmountInfo(totalAmountInfoDto);
		list.add(map);
		FileIO.sendRequestFile(list, fileName);
		logger.info("发送生成文件" + fileName + "使用时间:" + (System.currentTimeMillis() - a) + "毫秒");

	}

	@Autowired
	private EquityInfo001Service equityInfo001Service;

	@Autowired
	private InvestorsHoldInfo001Service investorsHoldInfo001Service;

	@Resource
	private CheckNumMapper checkNumMapper;
}
