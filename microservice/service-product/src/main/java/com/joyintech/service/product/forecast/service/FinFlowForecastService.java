/*
 * 文件名：FlowForecastService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.forecast.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.forecast.dao.FinFlowForecastConfigMapper;
import com.joyintech.service.product.forecast.dto.FinFlowForecastConfig;
import com.joyintech.service.product.forecast.dto.FinFlowForecastDealAmontDto;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author chendijun
 * @version 1.0 2019年8月3日
 * @see FinFlowForecastService
 * @since 1.0
 */
@Service(value = "serviceFlowForecastService")
@Transactional(rollbackFor = Exception.class)
public class FinFlowForecastService extends BaseService<FinFlowForecastConfig> {

	@Autowired
	private FinFlowForecastConfigMapper flowForecastConfigMapper;

	public Page<FinFlowForecastConfig> findForecast(Map<String, Object> param, int page, int length) throws Exception {
		String depType = (String) param.get("depType");
		if (StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_产品管理处.getValue(), depType)) {
			Page<FinFlowForecastConfig> pageFlowForecastConfig = super.queryForPage(page, length,
					() -> flowForecastConfigMapper.queryPageList(param));
			List<FinFlowForecastConfig> pageList = pageFlowForecastConfig.getRoot();
			//如果不存在历史记录，就取净值型产品开放期净申赎预测的合计值
			if(flowForecastConfigMapper.isExistFlowForecast(param)<=0) {
				for (FinFlowForecastConfig dto : pageList) {
					if (StringUtil.equals(dto.getTargetType(), DictCodeListExt2.流动性预测维护_指标种类_净值型产品开放期净申赎.getValue())) {
						List<FinFlowForecastDealAmontDto> list = flowForecastConfigMapper.findNetValueOpenforecast(param);
						for (FinFlowForecastDealAmontDto fDto : list) {
							dto.setTargetTimeone(ArithmeticUtil.doAdd(dto.getTargetTimeone(), ArithmeticUtil.subtract(
									fDto.getTargetTimeoneSG() == null ? BigDecimal.ZERO : fDto.getTargetTimeoneSG(),
									fDto.getTargetTimeoneSH() == null ? BigDecimal.ZERO : fDto.getTargetTimeoneSH())));

							dto.setTargetTimetwo(ArithmeticUtil.doAdd(dto.getTargetTimetwo(), ArithmeticUtil.subtract(
									fDto.getTargetTimetwoSG() == null ? BigDecimal.ZERO : fDto.getTargetTimetwoSG(),
									fDto.getTargetTimetwoSH() == null ? BigDecimal.ZERO : fDto.getTargetTimetwoSH())));

							dto.setTargetTimethree(ArithmeticUtil.doAdd(dto.getTargetTimethree(), ArithmeticUtil.subtract(
									fDto.getTargetTimethreeSG() == null ? BigDecimal.ZERO : fDto.getTargetTimethreeSG(),
									fDto.getTargetTimethreeSH() == null ? BigDecimal.ZERO : fDto.getTargetTimethreeSH())));

							dto.setTargetTimefour(ArithmeticUtil.doAdd(dto.getTargetTimefour(), ArithmeticUtil.subtract(
									fDto.getTargetTimefourSG() == null ? BigDecimal.ZERO : fDto.getTargetTimefourSG(),
									fDto.getTargetTimefourSH() == null ? BigDecimal.ZERO : fDto.getTargetTimefourSH())));

							dto.setTargetTimefive(ArithmeticUtil.doAdd(dto.getTargetTimefive(), ArithmeticUtil.subtract(
									fDto.getTargetTimefiveSG() == null ? BigDecimal.ZERO : fDto.getTargetTimefiveSG(),
									fDto.getTargetTimefiveSH() == null ? BigDecimal.ZERO : fDto.getTargetTimefiveSH())));

							dto.setTargetTimesix(ArithmeticUtil.doAdd(dto.getTargetTimesix(), ArithmeticUtil.subtract(
									fDto.getTargetTimesixSG() == null ? BigDecimal.ZERO : fDto.getTargetTimesixSG(),
									fDto.getTargetTimesixSH() == null ? BigDecimal.ZERO : fDto.getTargetTimesixSH())));
						   }
					}
				}
			}

			return pageFlowForecastConfig;
		} else if (StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_投融资处.getValue(), depType)) {
			Page<FinFlowForecastConfig> FlowForecastConfigPage = super.queryForPage(page, length,
					() -> flowForecastConfigMapper.queryPageList(param));
//			List<FinFlowForecastConfig> pageList = new ArrayList<FinFlowForecastConfig>();
			return FlowForecastConfigPage;
		} else {
			return super.queryForPage(page, length, () -> flowForecastConfigMapper.queryPageList(param));
		}

	}

	public Page<FinFlowForecastConfig> findReserveDetail(Map<String, Object> param, int page, int length)
			throws Exception {
		// 查询历史记录
		List<FinFlowForecastConfig> reserveDetailList = flowForecastConfigMapper.findReserveDetailList(param);
		// 新建返回结果
		Page<FinFlowForecastConfig> reserveDetailPage = new Page<FinFlowForecastConfig>();
		// 存储转换的对象
		List<FinFlowForecastConfig> list = new ArrayList<FinFlowForecastConfig>();
		FinFlowForecastConfig flowForecastConfig = new FinFlowForecastConfig();
		flowForecastConfig.setTargetType("货基");
		flowForecastConfig.setFlowType(DictCodeListExt2.流动性预测维护_流动性种类_流动性储备.getValue());
		flowForecastConfig.setDepType(DictCodeListExt2.流动性预测维护_部门类型_产品管理处.getValue());
		flowForecastConfig.setQueryDate((String) param.get("queryDate"));
		if (reserveDetailList != null) {
			for (int i = 0; i < reserveDetailList.size(); i++) {
				if (i == 0) {
					flowForecastConfig.setTargetTimeone(reserveDetailList.get(i).getTargetTimeone());
				} else if (i == 1) {
					flowForecastConfig.setTargetTimetwo(reserveDetailList.get(i).getTargetTimeone());
				} else if (i == 2) {
					flowForecastConfig.setTargetTimethree(reserveDetailList.get(i).getTargetTimeone());
				} else if (i == 3) {
					flowForecastConfig.setTargetTimefour(reserveDetailList.get(i).getTargetTimeone());
				} else if (i == 4) {
					flowForecastConfig.setTargetTimefive(reserveDetailList.get(i).getTargetTimeone());
				} else if (i == 5) {
					flowForecastConfig.setTargetTimesix(reserveDetailList.get(i).getTargetTimeone());
				}
			}
		}

		list.add(flowForecastConfig);
		reserveDetailPage.setRoot(list);
		return reserveDetailPage;
	}

	public Page<FinFlowForecastDealAmontDto> findNetValueOpenforecast(Map<String, Object> param, int page, int length)
			throws Exception {
		Page<FinFlowForecastDealAmontDto> pageFlowForecastDealAmontDto = new Page<FinFlowForecastDealAmontDto>();
		List<FinFlowForecastDealAmontDto> list = flowForecastConfigMapper.findNetValueOpenforecast(param);
		List<FinFlowForecastDealAmontDto> relist = new ArrayList<FinFlowForecastDealAmontDto>();
		if (list != null) {
			for (FinFlowForecastDealAmontDto dto : list) {
				FinFlowForecastDealAmontDto dtoSG = new FinFlowForecastDealAmontDto();
				dtoSG.setTargetName(dto.getTargetName());
				dtoSG.setTargetType("申购金额");
				dtoSG.setTargetTimeone(dto.getTargetTimeoneSG());
				dtoSG.setTargetTimetwo(dto.getTargetTimetwoSG());
				dtoSG.setTargetTimethree(dto.getTargetTimethreeSG());
				dtoSG.setTargetTimefour(dto.getTargetTimefourSG());
				dtoSG.setTargetTimefive(dto.getTargetTimefiveSG());
				dtoSG.setTargetTimesix(dto.getTargetTimesixSG());
				relist.add(dtoSG);

				FinFlowForecastDealAmontDto dtoSH = new FinFlowForecastDealAmontDto();
				dtoSH.setTargetType("赎回金额");
				dtoSH.setTargetTimeone(dto.getTargetTimeoneSH());
				dtoSH.setTargetTimetwo(dto.getTargetTimetwoSH());
				dtoSH.setTargetTimethree(dto.getTargetTimethreeSH());
				dtoSH.setTargetTimefour(dto.getTargetTimefourSH());
				dtoSH.setTargetTimefive(dto.getTargetTimefiveSH());
				dtoSH.setTargetTimesix(dto.getTargetTimesixSH());
				relist.add(dtoSH);
				FinFlowForecastDealAmontDto dtoJSG = new FinFlowForecastDealAmontDto();
				dtoJSG.setTargetType("净申购金额");
				// 指标1的净申购金额
				dtoJSG.setTargetTimeone(ArithmeticUtil.subtract(
						dto.getTargetTimeoneSG() == null ? BigDecimal.ZERO : dto.getTargetTimeoneSG(),
						dto.getTargetTimeoneSH() == null ? BigDecimal.ZERO : dto.getTargetTimeoneSH()));
				// 指标2的净申购金额
				dtoJSG.setTargetTimetwo(ArithmeticUtil.subtract(
						dto.getTargetTimetwoSG() == null ? BigDecimal.ZERO : dto.getTargetTimetwoSG(),
						dto.getTargetTimetwoSH() == null ? BigDecimal.ZERO : dto.getTargetTimetwoSH()));
				// 指标3的净申购金额
				dtoJSG.setTargetTimethree(ArithmeticUtil.subtract(
						dto.getTargetTimethreeSG() == null ? BigDecimal.ZERO : dto.getTargetTimethreeSG(),
						dto.getTargetTimethreeSH() == null ? BigDecimal.ZERO : dto.getTargetTimethreeSH()));
				// 指标4的净申购金额
				dtoJSG.setTargetTimefour(ArithmeticUtil.subtract(
						dto.getTargetTimefourSG() == null ? BigDecimal.ZERO : dto.getTargetTimefourSG(),
						dto.getTargetTimefourSH() == null ? BigDecimal.ZERO : dto.getTargetTimefourSH()));
				// 指标5的净申购金额
				dtoJSG.setTargetTimefive(ArithmeticUtil.subtract(
						dto.getTargetTimefiveSG() == null ? BigDecimal.ZERO : dto.getTargetTimefiveSG(),
						dto.getTargetTimefiveSH() == null ? BigDecimal.ZERO : dto.getTargetTimefiveSH()));
				// 指标6的净申购金额
				dtoJSG.setTargetTimesix(ArithmeticUtil.subtract(
						dto.getTargetTimesixSG() == null ? BigDecimal.ZERO : dto.getTargetTimesixSG(),
						dto.getTargetTimesixSH() == null ? BigDecimal.ZERO : dto.getTargetTimesixSH()));
				relist.add(dtoJSG);
			}
		}
		pageFlowForecastDealAmontDto.setRoot(relist);
		return pageFlowForecastDealAmontDto;
	}

	public String save(FinFlowForecastConfig flowForecastConfig) {
		// FlowForecastConfig flowForecastConfig =
		// (FlowForecastConfig)param.get("dto");
		if (flowForecastConfig != null) {
			if (StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_产品管理处.getValue(), flowForecastConfig.getDepType())) {
				// 插入流动性储备情况列表(需要将一行数据拆成多条数据入库,货基存入targetTimeone字段)
				List<FinFlowForecastConfig> list = new ArrayList<FinFlowForecastConfig>();
				if (flowForecastConfig.getReserveDetailList() != null) {
					for (FinFlowForecastConfig reserveDetailDto : flowForecastConfig.getReserveDetailList()) {
						list = this.splitReserveDetail(reserveDetailDto);
					}
				}
				for (FinFlowForecastConfig reserveDetailDto : list) {
					flowForecastConfigMapper.deleteByPrimaryKey(reserveDetailDto);
					flowForecastConfigMapper.insert(reserveDetailDto);
				}
				// 插入流动性预测情况列表
				if (flowForecastConfig.getForecastList() != null) {
					for (FinFlowForecastConfig forecastDto : flowForecastConfig.getForecastList()) {
						flowForecastConfigMapper.deleteByPrimaryKey(forecastDto);
						flowForecastConfigMapper.insert(forecastDto);
					}
				}

			} else if (StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_投融资处.getValue(),
					flowForecastConfig.getDepType())) {
				// 插入流动性预测情况列表
				if (flowForecastConfig.getForecastList() != null) {
					for (FinFlowForecastConfig forecastDto : flowForecastConfig.getForecastList()) {
						flowForecastConfigMapper.deleteByPrimaryKey(forecastDto);
						flowForecastConfigMapper.insert(forecastDto);
					}
				}
			} else if (StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_固收处.getValue(),
					flowForecastConfig.getDepType())) {
				// 插入流动性预测情况列表
				if (flowForecastConfig.getForecastList() != null) {
					for (FinFlowForecastConfig forecastDto : flowForecastConfig.getForecastList()) {
						flowForecastConfigMapper.deleteByPrimaryKey(forecastDto);
						flowForecastConfigMapper.insert(forecastDto);
					}
				}
			}
			if (StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_组合投资处.getValue(), flowForecastConfig.getDepType())) {
				// 插入流动性预测情况列表
				if (flowForecastConfig.getForecastList() != null) {
					for (FinFlowForecastConfig forecastDto : flowForecastConfig.getForecastList()) {
						flowForecastConfigMapper.deleteByPrimaryKey(forecastDto);
						flowForecastConfigMapper.insert(forecastDto);
					}
				}
			}
		}
		return "保存成功";
	}

    private List<FinFlowForecastConfig> splitReserveDetail(FinFlowForecastConfig flowForecastConfig) {
        List<FinFlowForecastConfig> list = new ArrayList<FinFlowForecastConfig>();
        FinFlowForecastConfig dto1 = new FinFlowForecastConfig();
        FinFlowForecastConfig dto2 = new FinFlowForecastConfig();
        FinFlowForecastConfig dto3 = new FinFlowForecastConfig();
        FinFlowForecastConfig dto4 = new FinFlowForecastConfig();
        FinFlowForecastConfig dto5 = new FinFlowForecastConfig();
        FinFlowForecastConfig dto6 = new FinFlowForecastConfig();
        // 日日升
        dto1.setQueryDate(flowForecastConfig.getQueryDate());
        dto1.setDepType(flowForecastConfig.getDepType());
        dto1.setFlowType(flowForecastConfig.getFlowType());
        dto1.setTargetType(DictCodeListExt2.流动性预测维护_指标种类_日日升.getValue());
        dto1.setTargetTimeone(flowForecastConfig.getTargetTimeone());
        // 月月升
        dto2.setQueryDate(flowForecastConfig.getQueryDate());
        dto2.setDepType(flowForecastConfig.getDepType());
        dto2.setFlowType(flowForecastConfig.getFlowType());
        dto2.setTargetType(DictCodeListExt2.流动性预测维护_指标种类_月月升.getValue());
        dto2.setTargetTimeone(flowForecastConfig.getTargetTimetwo());

        // 鑫鑫向荣
        dto3.setQueryDate(flowForecastConfig.getQueryDate());
        dto3.setDepType(flowForecastConfig.getDepType());
        dto3.setFlowType(flowForecastConfig.getFlowType());
        dto3.setTargetType(DictCodeListExt2.流动性预测维护_指标种类_鑫鑫向荣.getValue());
        dto3.setTargetTimeone(flowForecastConfig.getTargetTimethree());

        // 鑫鑫向荣B
        dto4.setQueryDate(flowForecastConfig.getQueryDate());
        dto4.setDepType(flowForecastConfig.getDepType());
        dto4.setFlowType(flowForecastConfig.getFlowType());
        dto4.setTargetType(DictCodeListExt2.流动性预测维护_指标种类_鑫鑫向荣B.getValue());
        dto4.setTargetTimeone(flowForecastConfig.getTargetTimefour());

        // 理财宝
        dto5.setQueryDate(flowForecastConfig.getQueryDate());
        dto5.setDepType(flowForecastConfig.getDepType());
        dto5.setFlowType(flowForecastConfig.getFlowType());
        dto5.setTargetType(DictCodeListExt2.流动性预测维护_指标种类_理财宝.getValue());
        dto5.setTargetTimeone(flowForecastConfig.getTargetTimefive());

        // 定开净值型产品
        dto6.setQueryDate(flowForecastConfig.getQueryDate());
        dto6.setDepType(flowForecastConfig.getDepType());
        dto6.setFlowType(flowForecastConfig.getFlowType());
        dto6.setTargetType(DictCodeListExt2.流动性预测维护_指标种类_净值型产品开放期净申赎.getValue());
        dto6.setTargetTimeone(flowForecastConfig.getTargetTimesix());

        list.add(dto1);
        list.add(dto2);
        list.add(dto3);
        list.add(dto4);
        list.add(dto5);
        list.add(dto6);
        return list;

	}

	/**
	 * 查询产品申赎数据 <功能详细描述>
	 * 
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<FinFlowForecastDealAmontDto> findMaintainProductRedemptionData(Map<String, Object> param, int page,
			int length) {
		Page<FinFlowForecastDealAmontDto> pageFlowForecastDealAmontDto = new Page<FinFlowForecastDealAmontDto>();
		List<FinFlowForecastDealAmontDto> list = flowForecastConfigMapper.queryProductRedemptionData(param);
		pageFlowForecastDealAmontDto.setRoot(list);
		return pageFlowForecastDealAmontDto;
	}

}
