/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.manage.workmanage.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.manage.workmanage.dao.ReportWorkManage001Mapper;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 金融产品交易查询Service
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportWorkManage001Service extends CommonExportService<ReportWorkManage001Dto>
		implements BaseProcessService<ReportWorkManage001Dto> {

	/**
	 */
	@Autowired
	private ReportWorkManage001Mapper reportWorkManage001Mapper;
//	@Autowired
//	private RepJobMapper repJobMapper;
	@Autowired
	private BaseCommonService baseCommonService;
//	@Autowired
//	private InfoDisclosureReadAndGenDetailService infoDisclosureReadAndGenDetailService;
//    @Autowired
//    private SysBatchGrpMapper  batchGroupMapper;
//    @Autowired
//    private SysBatchStart001Mapper sysBatchStart001Mapper;
//    @Autowired(required = false)
//    private ProductService productService;
//    @Autowired
//    private SysBatchPrdGrpMapper  batchProductGroupMapper;
//    @Autowired
//    private SysBatchgrpExecInfoMapper  batchExecuteDetailMapper;
	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(ReportWorkManage001Service.class);

	/**
	 * 作业编码
	 * 
	 * @return 产品信息
	 */
	public List<SelectOptionDto> getJobInfo() {
//		return reportWorkManage001Mapper.selectJobInfo();
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage001/getReportIdList",
    			null,SelectOptionDto.class);
		
		
	}

	/**
	 * 根据条件获取表格信息
	 * 
	 * @param params
	 *            筛选条件
	 * @return 表格信息
	 * @throws Exception
	 */
	public Page<ReportWorkManage001Dto> findPage(Map<String, Object> params) throws Exception {

		// 获取获取理财产品类型
//		String jobId = (String) params.get("jobId");
//		String[] jobIdArr = null;
//		if (StringUtil.isNotEmpty(jobId)) {
//			jobIdArr = jobId.split(",");
//		}
//		params.put("jobIdArr", jobIdArr);
//		// 用于流程-我的待办-点击查看
//		params.put("finprodIdForFlow", (String) params.get("finprodIdForFlow"));
//		params.put("valDateForFlow", (String) params.get("valDateForFlow"));
//		params.put("valBalFlagCodeForFlow", (String) params.get("valBalFlagCodeForFlow"));
//		Page<ReportWorkManage001Dto> page = super.queryForPage(() -> reportWorkManage001Mapper.queryPageList(params));
		Page<ReportWorkManage001Dto> page =
		restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage001/findPage",
				params,ReportWorkManage001Dto.class);
		
		
		
		for (ReportWorkManage001Dto dto : page.getRoot()) {
			//计算预计执行时间
		    try {
		        parseValueToDate(dto);
            }
            catch (Exception e) {
                dto.setPreExecDate("解析错误,请检查");
            }
			
		}
		return page;
	}
    /**
     * 计算预计执行时间
     * @param dto
     */
	public void parseValueToDate(ReportWorkManage001Dto dto) {
		Calendar preExecDate = Calendar.getInstance();
		Date sysDate = baseCommonService.getSysDate();
		preExecDate.setTime(sysDate);
		//判断是否有预计执行日
		Boolean flag=true;
		String[] strs = dto.getLoopDateValue().trim().split(" ");
		// 设置年
		Integer year;
		// 设置月
		Integer month;
		// 设置日
		Integer date;
		// 设置时
		Integer hourOfDay;
		// 设置分
		Integer minute;
		// 设置秒
		Integer second;
		if (strs != null && strs.length > 0) {
			// 指定周
			Integer week;
			int days = 0;
			// 如果指定了周，则不取指定日
			if (!StringUtil.equals(strs[5], "*")&&!StringUtil.equals(strs[5], "?")){
				//如果指定年<当前时间年，则没有预计日期
				if(!StringUtil.equals(strs[6], "*")&&!StringUtil.equals(strs[6], "?")&&new Integer(strs[6])<preExecDate.get(Calendar.YEAR)){
					flag=false;
				}
				else{
					week = new Integer(strs[5])-1;
					// 获取系统时间的周
					Integer sysWeek = DateUtil.getWeekDay(sysDate);
					// 如果指定周<系统时间周
					if (week < sysWeek) {
						days = 6;
					} else if (week >= sysWeek) {// 如果指定周>=s系统时间周
						days = week - sysWeek;
					}
					Date newDate = DateUtil.addDay(preExecDate.getTime(), days);
					preExecDate.setTime(newDate);
					// 设置时
					if (StringUtil.equals(strs[2], "*")||StringUtil.equals(strs[2], "?")) {
						hourOfDay = 0;
					} else {
						hourOfDay = new Integer(strs[2]);
					}
					preExecDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
					// 设置分
					if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
						minute = 0;
					} else {
						minute = new Integer(strs[1]);
					}
					preExecDate.set(Calendar.MINUTE, minute);
					// 设置秒
					if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
						second = 0;
					} else {
						second = new Integer(strs[0]);
					}
					preExecDate.set(Calendar.SECOND, second);
					dto.setPreExecDate(DateUtil.format(preExecDate.getTime(), DateUtil.DEFAULT_DATETIME_FORMAT));
				}
			} else {
				// 未指定年份
				if (StringUtil.equals(strs[6], "*")) {
					year = preExecDate.get(Calendar.YEAR);
					// 未指定年份-未指定月份
					if (StringUtil.equals(strs[4], "?")||StringUtil.equals(strs[4], "*")) {
						month = preExecDate.get(Calendar.MONTH);
						// 未指定年份-未指定月份-未指定日
						if (StringUtil.equals(strs[3], "*")||StringUtil.equals(strs[3], "?")) {
							date = preExecDate.get(Calendar.DAY_OF_MONTH);
							// 未指定年份-未指定月份-未指定日-未指定时
							if (StringUtil.equals(strs[2], "*")||StringUtil.equals(strs[2], "?")) {
								hourOfDay = preExecDate.get(Calendar.HOUR_OF_DAY);
								// 未指定年份-未指定月份-未指定日-未指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 未指定年份-未指定月份-未指定日-未指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {// 未指定年份-未指定月份-未指定日-未指定时-未指定分-指定秒
										second = new Integer(strs[0]);
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 未指定年份-未指定月份-未指定日-未指定时-未指定分,判断是否>最大值，如果大于，则设置分=0，时+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)) {
										minute = 0;
										hourOfDay++;
									}
								} else {// 未指定年份-未指定月份-未指定日-未指定时-指定分
									minute = new Integer(strs[1]);
									// 未指定年份-未指定月份-未指定日-未指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 如果当前秒+1>最大当前秒，分已经指定，则时+1，
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&minute<preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											hourOfDay++;
										} 
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {// 未指定年份-未指定月份-未指定日-未指定时-指定分-指定秒,
										second = new Integer(strs[0]);
										// 如果当前指定秒<当前秒，分已经指定，则时+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&minute<preExecDate.get(Calendar.MINUTE)) {
											hourOfDay++;
										}
									}

								}
							} else {
								// 未指定年份-未指定月份-未指定日-指定时
								hourOfDay = new Integer(strs[2]);
								// 未指定年份-未指定月份-未指定日-指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 未指定年份-未指定月份-未指定日-指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-未指定月份-未指定日-指定时-未指定分-未指定秒,如果秒>当前时间最大秒，则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 未指定年份-未指定月份-未指定日-指定时-未指定分-指定秒,如果秒<当前时间秒，则分+1
										second = new Integer(strs[0]);
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 未指定年份-未指定月份-未指定日-指定时-未指定分,如果分>当前时间分，在指定时情况下，则日+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)
											&&hourOfDay<preExecDate.get(Calendar.HOUR_OF_DAY)) {
										minute = 0;
										date++;
									}
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)){
										minute = 0;
									}
								} else {
									// 未指定年份-未指定月份-未指定日-指定时-指定分
									minute = new Integer(strs[1]);
									// 未指定年份-未指定月份-未指定日-指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-未指定月份-未指定日-指定时-指定分-未指定秒，如果秒>最大当前秒，则日+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&& hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&& minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											date++;
										}
										if(second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 未指定年份-未指定月份-未指定日-指定时-指定分-指定秒，
										second = new Integer(strs[0]);
										// 未指定年份-未指定月份-未指定日-指定时-指定分-指定秒，如果秒<当前秒，则日+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&& hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&& minute < preExecDate.get(Calendar.MINUTE)) {
											date++;
										}
									}

								}
							}
						} else {// 未指定年份-未指定月份-指定日
							date = new Integer(strs[3]);
							// 未指定年份-未指定月份-指定日-未指定时
							if (StringUtil.equals(strs[2], "*")||StringUtil.equals(strs[2], "?")) {
								hourOfDay = preExecDate.get(Calendar.HOUR_OF_DAY);
								// 未指定年份-未指定月份-指定日-未指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 未指定年份-未指定月份-指定日-未指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-未指定月份-指定日-未指定时-未指定分-未指定秒，秒>最大当前时间秒，则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 未指定年份-未指定月份-指定日-未指定时-未指定分-指定秒
										second = new Integer(strs[0]);
										// 未指定年份-未指定月份-指定日-未指定时-未指定分-指定秒，秒<当前时间秒，则分+1
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 未指定年份-未指定月份-指定日-未指定时-未指定分，如果分>最大当前分，则时+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)) {
										minute = 0;
										hourOfDay++;
									}
								} else {
									// 未指定年份-未指定月份-指定日-未指定时-指定分
									minute = new Integer(strs[1]);
									// 未指定年份-未指定月份-指定日-未指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-未指定月份-指定日-未指定时-指定分-未指定秒,如果秒>最大当前秒，则时+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&& minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											hourOfDay++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 未指定年份-未指定月份-指定日-未指定时-指定分-指定秒
										second = new Integer(strs[0]);
										// 未指定年份-未指定月份-指定日-未指定时-指定分-指定秒,如果秒<当前秒，则时+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&& minute < preExecDate.get(Calendar.MINUTE)) {
											hourOfDay++;
										}
									}

								}
								// 未指定年份-未指定月份-指定日-未指定时,如果时>当前时间最大值，则月+1
								if (hourOfDay > preExecDate.getMaximum(Calendar.HOUR_OF_DAY)
										&& date < preExecDate.get(Calendar.DAY_OF_MONTH)) {
									hourOfDay=0;
									month++;
								}
								if (hourOfDay > preExecDate.getMaximum(Calendar.HOUR_OF_DAY)){
									hourOfDay = 0;
								}
							} else {
								// 未指定年份-未指定月份-指定日-指定时
								hourOfDay = new Integer(strs[2]);
								// 未指定年份-未指定月份-指定日-指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 未指定年份-未指定月份-指定日-指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-未指定月份-指定日-指定时-未指定分-未指定秒，秒>最大秒。则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 未指定年份-未指定月份-指定日-指定时-未指定分-指定秒，
										second = new Integer(strs[0]);
										// 未指定年份-未指定月份-指定日-指定时-未指定分-指定秒，秒<当前秒。则分+1
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 未指定年份-未指定月份-指定日-指定时-未指定分，分<最大值。则月+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)
											&& date < preExecDate.get(Calendar.DAY_OF_MONTH)
											&& hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)) {
										minute = 0;
										month++;
									}
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)){
										minute = 0;
									}
								} else {
									// 未指定年份-未指定月份-指定日-指定时-指定分，
									minute = new Integer(strs[1]);
									// 未指定年份-未指定月份-指定日-指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-未指定月份-指定日-指定时-指定分-未指定秒，如果秒>最大值，则月+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&date < preExecDate.get(Calendar.DAY_OF_MONTH)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											month++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 未指定年份-未指定月份-指定日-指定时-指定分-指定秒，
										second = new Integer(strs[0]);
										// 未指定年份-未指定月份-指定日-指定时-指定分-指定秒，如果秒<当前时间秒，则月+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&date < preExecDate.get(Calendar.DAY_OF_MONTH)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											month++;
										}
									}

								}
							}
						}
						// 未指定年份-未指定月份,如果月份>最大值，则年+1
						if (month > preExecDate.getMaximum(Calendar.MONTH)) {
							year++;
						}

					} // 未指定年份-指定月份
					else {
						month = new Integer(strs[4])-1;
						// 未指定年份-指定月份-未指定日
						if (StringUtil.equals(strs[3], "*")||StringUtil.equals(strs[3], "?")) {
							date = preExecDate.get(Calendar.DAY_OF_MONTH);
							// 未指定年份-指定月份-未指定日-未指定时
							if (StringUtil.equals(strs[2], "*")||StringUtil.equals(strs[2], "?")) {
								hourOfDay = preExecDate.get(Calendar.HOUR_OF_DAY);
								// 未指定年份-指定月份-未指定日-未指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 未指定年份-指定月份-未指定日-未指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {// 未指定年份-指定月份-未指定日-未指定时-未指定分-指定秒
										second = new Integer(strs[0]);
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 未指定年份-指定月份-未指定日-未指定时-未指定分,判断是否>最大值，如果大于，则设置分=0，时+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)) {
										minute = 0;
										hourOfDay++;
									}
								} else {// 未指定年份-指定月份-未指定日-未指定时-指定分
									minute = new Integer(strs[1]);
									// 未指定年份-未指定月份-未指定日-未指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 如果当前秒+1>最大当前秒，分已经指定，则时+1，
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											hourOfDay++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {// 未指定年份-指定月份-未指定日-未指定时-指定分-指定秒,
										second = new Integer(strs[0]);
										// 如果当前指定秒<当前秒，分已经指定，则时+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											hourOfDay++;
										}
									}

								}
								// 未指定年份-指定月份-未指定日-未指定时,
								if(hourOfDay>preExecDate.getMaximum(Calendar.HOUR_OF_DAY)){
									hourOfDay=0;
									date++;
								}
							} else {
								// 未指定年份-指定月份-未指定日-指定时
								hourOfDay = new Integer(strs[2]);
								// 未指定年份-指定月份-未指定日-指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 未指定年份-指定月份-未指定日-指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-指定月份-未指定日-指定时-未指定分-未指定秒,如果秒>当前时间最大秒，则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 未指定年份-指定月份-未指定日-指定时-未指定分-指定秒,如果秒<当前时间秒，则分+1
										second = new Integer(strs[0]);
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 未指定年份-指定月份-未指定日-指定时-未指定分,如果分>当前时间分，在指定时情况下，则日+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)
											&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)) {
										minute = 0;
										date++;
									}
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)){
										minute = 0;
									}
								} else {
									// 未指定年份-指定月份-未指定日-指定时-指定分
									minute = new Integer(strs[1]);
									// 未指定年份-指定月份-未指定日-指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-未指定月份-未指定日-指定时-指定分-未指定秒，如果秒>最大当前秒，则日+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											date++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 未指定年份-指定月份-未指定日-指定时-指定分-指定秒，
										second = new Integer(strs[0]);
										// 未指定年份-指定月份-未指定日-指定时-指定分-指定秒，如果秒<当前秒，则日+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											date++;
										}
									}

								}
							}
							// 未指定年份-指定月份-未指定日,如果日>当前日，则年+1
							if (date > preExecDate.getMaximum(Calendar.DAY_OF_MONTH)) {
								year++;
							}
						} else {// 未指定年份-指定月份-指定日
							date = new Integer(strs[3]);
							// 未指定年份-指定月份-指定日-未指定时
							if (StringUtil.equals(strs[2], "*")||StringUtil.equals(strs[2], "?")) {
								hourOfDay = preExecDate.get(Calendar.HOUR_OF_DAY);
								// 未指定年份-指定月份-指定日-未指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 未指定年份-指定月份-指定日-未指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-指定月份-指定日-未指定时-未指定分-未指定秒，秒>最大当前时间秒，则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 未指定年份-指定月份-指定日-未指定时-未指定分-指定秒
										second = new Integer(strs[0]);
										// 未指定年份-指定月份-指定日-未指定时-未指定分-指定秒，秒<当前时间秒，则分+1
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 未指定年份-指定月份-指定日-未指定时-未指定分，如果分>最大当前分，则时+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)) {
										minute = 0;
										hourOfDay++;
									}
								} else {
									// 未指定年份-指定月份-指定日-未指定时-指定分
									minute = new Integer(strs[1]);
									// 未指定年份-指定月份-指定日-未指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-指定月份-指定日-未指定时-指定分-未指定秒,如果秒>最大当前秒，则时+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											hourOfDay++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 未指定年份-指定月份-指定日-未指定时-指定分-指定秒
										second = new Integer(strs[0]);
										// 未指定年份-指定月份-指定日-未指定时-指定分-指定秒,如果秒<当前秒，则时+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											hourOfDay++;
										}
									}

								}
								// 未指定年份-指定月份-指定日-未指定时,如果时>当前时间最大值，则年+1
								if (hourOfDay > preExecDate.getMaximum(Calendar.HOUR_OF_DAY)
										&&date < preExecDate.get(Calendar.DAY_OF_MONTH)
										&&month < preExecDate.get(Calendar.MONTH)) {
									hourOfDay=0;
									year++;
								}
								if (hourOfDay > preExecDate.getMaximum(Calendar.HOUR_OF_DAY)){
									hourOfDay = 0;
								}
							} else {
								// 未指定年份-指定月份-指定日-指定时
								hourOfDay = new Integer(strs[2]);
								// 未指定年份-指定月份-指定日-指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 未指定年份-指定月份-指定日-指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-指定月份-指定日-指定时-未指定分-未指定秒，秒>最大秒。则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 未指定年份-指定月份-指定日-指定时-未指定分-指定秒，
										second = new Integer(strs[0]);
										// 未指定年份-指定月份-指定日-指定时-未指定分-指定秒，秒<当前秒。则分+1
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 未指定年份-指定月份-指定日-指定时-未指定分，分<最大值。则年+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)
											&&month < preExecDate.get(Calendar.MONTH)
											&&date < preExecDate.get(Calendar.DAY_OF_MONTH)
											&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)) {
										minute = 0;
										year++;
									}
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)){
										minute = 0;
									}
								} else {
									// 未指定年份-指定月份-指定日-指定时-指定分，
									minute = new Integer(strs[1]);
									// 未指定年份-未指定月份-指定日-指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-未指定月份-指定日-指定时-指定分-未指定秒，如果秒>最大值，则月+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&month < preExecDate.get(Calendar.MONTH)
												&&date < preExecDate.get(Calendar.DAY_OF_MONTH)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											year++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 未指定年份-未指定月份-指定日-指定时-指定分-指定秒，
										second = new Integer(strs[0]);
										// 未指定年份-未指定月份-指定日-指定时-指定分-指定秒，如果秒<当前时间秒，则月+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&month < preExecDate.get(Calendar.MONTH)
												&&date < preExecDate.get(Calendar.DAY_OF_MONTH)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											year++;
										}
									}

								}
							}
						}
					}
				} else {// 指定年
					year = new Integer(strs[6]);
					//如果执行年<当前年
					if(year<preExecDate.get(Calendar.YEAR)){
						flag=false;
					}
					// 指定年份-未指定月份
					if (StringUtil.equals(strs[4], "?")||StringUtil.equals(strs[4], "*")) {
						month = preExecDate.get(Calendar.MONTH);
						// 指定年份-未指定月份-未指定日
						if (StringUtil.equals(strs[3], "*")||StringUtil.equals(strs[3], "?")) {
							date = preExecDate.get(Calendar.DAY_OF_MONTH);
							// 指定年份-未指定月份-未指定日-未指定时
							if (StringUtil.equals(strs[2], "*")||StringUtil.equals(strs[2], "?")) {
								hourOfDay = preExecDate.get(Calendar.HOUR_OF_DAY);
								// 指定年份-未指定月份-未指定日-未指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 指定年份-未指定月份-未指定日-未指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {// 指定年份-未指定月份-未指定日-未指定时-未指定分-指定秒
										second = new Integer(strs[0]);
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 指定年份-未指定月份-未指定日-未指定时-未指定分,判断是否>最大值，如果大于，则设置分=0，时+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)) {
										minute = 0;
										hourOfDay++;
									}
								} else {// 指定年份-未指定月份-未指定日-未指定时-指定分
									minute = new Integer(strs[1]);
									// 未指定年份-未指定月份-未指定日-未指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 如果当前秒+1>最大当前秒，分已经指定，则时+1，
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											hourOfDay++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {// 指定年份-未指定月份-未指定日-未指定时-指定分-指定秒,
										second = new Integer(strs[0]);
										// 如果当前指定秒<当前秒，分已经指定，则时+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											hourOfDay++;
										}
									}
								}
								if(hourOfDay>preExecDate.getMaximum(Calendar.HOUR_OF_DAY)){
									date++;
									hourOfDay=0;
								}
							} else {
								// 指定年份-未指定月份-未指定日-指定时
								hourOfDay = new Integer(strs[2]);
								// 指定年份-未指定月份-未指定日-指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 未指定年份-未指定月份-未指定日-指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-未指定月份-未指定日-指定时-未指定分-未指定秒,如果秒>当前时间最大秒，则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 指定年份-未指定月份-未指定日-指定时-未指定分-指定秒,如果秒<当前时间秒，则分+1
										second = new Integer(strs[0]);
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 指定年份-未指定月份-未指定日-指定时-未指定分,如果分>当前时间分，在指定时情况下，则日+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)
											&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)) {
										minute = 0;
										date++;
									}
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)){
										minute = 0;
									}
								} else {
									// 指定年份-未指定月份-未指定日-指定时-指定分
									minute = new Integer(strs[1]);
									// 指定年份-未指定月份-未指定日-指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-未指定月份-未指定日-指定时-指定分-未指定秒，如果秒>最大当前秒，则日+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											date++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 指定年份-未指定月份-未指定日-指定时-指定分-指定秒，
										second = new Integer(strs[0]);
										// 指定年份-未指定月份-未指定日-指定时-指定分-指定秒，如果秒<当前秒，则日+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											date++;
										}
									}

								}
							}
							if(date>preExecDate.getMaximum(Calendar.DAY_OF_MONTH)){
								date=0;
								month++;
							}
						} else {// 指定年份-未指定月份-指定日
							date = new Integer(strs[3]);
							// 指定年份-未指定月份-指定日-未指定时
							if (StringUtil.equals(strs[2], "*")||StringUtil.equals(strs[2], "?")) {
								hourOfDay = preExecDate.get(Calendar.HOUR_OF_DAY);
								// 指定年份-未指定月份-指定日-未指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 指定年份-未指定月份-指定日-未指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-未指定月份-指定日-未指定时-未指定分-未指定秒，秒>最大当前时间秒，则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 指定年份-未指定月份-指定日-未指定时-未指定分-指定秒
										second = new Integer(strs[0]);
										// 指定年份-未指定月份-指定日-未指定时-未指定分-指定秒，秒<当前时间秒，则分+1
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 指定年份-未指定月份-指定日-未指定时-未指定分，如果分>最大当前分，则时+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)) {
										minute = 0;
										hourOfDay++;
									}
								} else {
									// 指定年份-未指定月份-指定日-未指定时-指定分
									minute = new Integer(strs[1]);
									// 指定年份-未指定月份-指定日-未指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-未指定月份-指定日-未指定时-指定分-未指定秒,如果秒>最大当前秒，则时+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											hourOfDay++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 指定年份-未指定月份-指定日-未指定时-指定分-指定秒
										second = new Integer(strs[0]);
										// 指定年份-未指定月份-指定日-未指定时-指定分-指定秒,如果秒<当前秒，则时+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											hourOfDay++;
										}
									}

								}
								// 指定年份-未指定月份-指定日-未指定时,如果时>当前时间最大值，则月+1
								if (hourOfDay > preExecDate.getMaximum(Calendar.HOUR_OF_DAY)
										&&date < preExecDate.get(Calendar.DAY_OF_MONTH)) {
									hourOfDay=0;
									month++;
								}
								if (hourOfDay > preExecDate.getMaximum(Calendar.HOUR_OF_DAY)){
									hourOfDay = 0;
								}
							} else {
								// 指定年份-未指定月份-指定日-指定时
								hourOfDay = new Integer(strs[2]);
								// 指定年份-未指定月份-指定日-指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 指定年份-未指定月份-指定日-指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-未指定月份-指定日-指定时-未指定分-未指定秒，秒>最大秒。则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 指定年份-未指定月份-指定日-指定时-未指定分-指定秒，
										second = new Integer(strs[0]);
										// 指定年份-未指定月份-指定日-指定时-未指定分-指定秒，秒<当前秒。则分+1
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 指定年份-未指定月份-指定日-指定时-未指定分，分<最大值。则月+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)
											&&date < preExecDate.get(Calendar.DAY_OF_MONTH)
											&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)) {
										minute = 0;
										month++;
									}
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)){
										minute = 0;
									}
								} else {
									// 指定年份-未指定月份-指定日-指定时-指定分，
									minute = new Integer(strs[1]);
									// 指定年份-未指定月份-指定日-指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-未指定月份-指定日-指定时-指定分-未指定秒，如果秒>最大值，则月+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&date < preExecDate.get(Calendar.DAY_OF_MONTH)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											month++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 指定年份-未指定月份-指定日-指定时-指定分-指定秒，
										second = new Integer(strs[0]);
										// 指定年份-未指定月份-指定日-指定时-指定分-指定秒，如果秒<当前时间秒，则月+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&date < preExecDate.get(Calendar.DAY_OF_MONTH)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											month++;
										}
									}

								}
							}
						}
						// 指定年份-未指定月份,如果月份>当前时间最大值，则没有预计时间
						if (month > preExecDate.getMaximum(Calendar.MONTH)) {
							flag=false;
						}
					} // 指定年份-指定月份
					else {
						month = new Integer(strs[4])-1;
						//如果执行年<=当前年,月份<当前月
						if(year<=preExecDate.get(Calendar.YEAR)&&month<preExecDate.get(Calendar.MONTH)){
							flag=false;
						}
						// 指定年份-指定月份-未指定日
						if (StringUtil.equals(strs[3], "*")||StringUtil.equals(strs[3], "?")) {
							date = preExecDate.get(Calendar.DAY_OF_MONTH);
							// 指定年份-指定月份-未指定日-未指定时
							if (StringUtil.equals(strs[2], "*")||StringUtil.equals(strs[2], "?")) {
								hourOfDay = preExecDate.get(Calendar.HOUR_OF_DAY);
								// 指定年份-指定月份-未指定日-未指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 指定年份-指定月份-未指定日-未指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {// 指定年份-指定月份-未指定日-未指定时-未指定分-指定秒
										second = new Integer(strs[0]);
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 指定年份-指定月份-未指定日-未指定时-未指定分,判断是否>最大值，如果大于，则设置分=0，时+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)) {
										minute = 0;
										hourOfDay++;
									}
								} else {// 指定年份-指定月份-未指定日-未指定时-指定分
									minute = new Integer(strs[1]);
									// 指定年份-指定月份-未指定日-未指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 如果当前秒+1>最大当前秒，分已经指定，则时+1，
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											hourOfDay++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {// 指定年份-指定月份-未指定日-未指定时-指定分-指定秒,
										second = new Integer(strs[0]);
										// 如果当前指定秒<当前秒，分已经指定，则时+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											hourOfDay++;
										}
									}

								}
								if(hourOfDay>preExecDate.getMaximum(Calendar.HOUR_OF_DAY)){
									hourOfDay=0;
									date++;
								}
							} else {
								// 指定年份-指定月份-未指定日-指定时
								hourOfDay = new Integer(strs[2]);
								// 指定年份-指定月份-未指定日-指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 指定年份-指定月份-未指定日-指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-指定月份-未指定日-指定时-未指定分-未指定秒,如果秒>当前时间最大秒，则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 指定年份-指定月份-未指定日-指定时-未指定分-指定秒,如果秒<当前时间秒，则分+1
										second = new Integer(strs[0]);
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 指定年份-指定月份-未指定日-指定时-未指定分,如果分>当前时间分，在指定时情况下，则日+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)
											&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)) {
										minute = 0;
										date++;
									}
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)){
										minute = 0;
									}
								} else {
									// 指定年份-指定月份-未指定日-指定时-指定分
									minute = new Integer(strs[1]);
									// 指定年份-指定月份-未指定日-指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 未指定年份-未指定月份-未指定日-指定时-指定分-未指定秒，如果秒>最大当前秒，则日+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											date++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 指定年份-指定月份-未指定日-指定时-指定分-指定秒，
										second = new Integer(strs[0]);
										// 指定年份-指定月份-未指定日-指定时-指定分-指定秒，如果秒<当前秒，则日+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&hourOfDay < preExecDate.get(Calendar.HOUR_OF_DAY)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											date++;
										}
									}

								}
							}
							// 未指定年份-指定月份-未指定日,如果日>当前日，则没有预计日期
							if (date > preExecDate.getMaximum(Calendar.DAY_OF_MONTH)) {
								flag=false;
							}
						} else {// 指定年份-指定月份-指定日
							date = new Integer(strs[3]);
							//如果执行年<=当前年,月份<当前月,日<当前日
							if (year <= preExecDate.get(Calendar.YEAR) && month <= preExecDate.get(Calendar.MONTH)
									&& date < preExecDate.get(Calendar.DAY_OF_MONTH)) {
								flag=false;
							}
							// 指定年份-指定月份-指定日-未指定时
							if (StringUtil.equals(strs[2], "*")||StringUtil.equals(strs[2], "?")) {
								hourOfDay = preExecDate.get(Calendar.HOUR_OF_DAY);
								// 指定年份-指定月份-指定日-未指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 指定年份-指定月份-指定日-未指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-指定月份-指定日-未指定时-未指定分-未指定秒，秒>最大当前时间秒，则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 指定年份-指定月份-指定日-未指定时-未指定分-指定秒
										second = new Integer(strs[0]);
										// 指定年份-指定月份-指定日-未指定时-未指定分-指定秒，秒<当前时间秒，则分+1
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 指定年份-指定月份-指定日-未指定时-未指定分，如果分>最大当前分，则时+1
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)) {
										minute = 0;
										hourOfDay++;
									}
								} else {
									// 指定年份-指定月份-指定日-未指定时-指定分
									minute = new Integer(strs[1]);
									// 指定年份-指定月份-指定日-未指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-指定月份-指定日-未指定时-指定分-未指定秒,如果秒>最大当前秒，则时+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											second = 0;
											hourOfDay++;
										}
										if (second > preExecDate.getMaximum(Calendar.SECOND)){
											second = 0;
										}
									} else {
										// 指定年份-指定月份-指定日-未指定时-指定分-指定秒
										second = new Integer(strs[0]);
										// 指定年份-指定月份-指定日-未指定时-指定分-指定秒,如果秒<当前秒，则时+1
										if (second <= preExecDate.get(Calendar.SECOND)
												&&minute < preExecDate.get(Calendar.MINUTE)) {
											hourOfDay++;
										}
									}

								}
								// 指定年份-指定月份-指定日-未指定时,如果时>当前时间最大值，则没有预计日
								if (hourOfDay > preExecDate.getMaximum(Calendar.HOUR_OF_DAY)) {
									flag=false;
								}
							} else {
								// 指定年份-指定月份-指定日-指定时
								hourOfDay = new Integer(strs[2]);
								//如果执行年<=当前年,月份<当前月,日<当前日,时<当前时
								if(year<=preExecDate.get(Calendar.YEAR)&&month<=preExecDate.get(Calendar.MONTH)&&date<preExecDate.get(Calendar.DAY_OF_MONTH)
										&&hourOfDay<preExecDate.get(Calendar.HOUR_OF_DAY)){
									flag=false;
								}
								// 指定年份-指定月份-指定日-指定时-未指定分
								if (StringUtil.equals(strs[1], "*")||StringUtil.equals(strs[1], "?")) {
									minute = preExecDate.get(Calendar.MINUTE);
									// 指定年份-指定月份-指定日-指定时-未指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-指定月份-指定日-指定时-未指定分-未指定秒，秒>最大秒。则分+1
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											second = 0;
											minute++;
										}
									} else {
										// 指定年份-指定月份-指定日-指定时-未指定分-指定秒，
										second = new Integer(strs[0]);
										// 指定年份-指定月份-指定日-指定时-未指定分-指定秒，秒<当前秒。则分+1
										if (second <= preExecDate.get(Calendar.SECOND)) {
											minute++;
										}
									}
									// 指定年份-指定月份-指定日-指定时-未指定分，分<最大值。则没有预计日
									if (minute > preExecDate.getMaximum(Calendar.MINUTE)) {
										flag=false;
									}
								} else {
									// 指定年份-指定月份-指定日-指定时-指定分，
									minute = new Integer(strs[1]);
									//如果执行年<=当前年,月份<当前月,日<当前日,时<当前时,分<当前分
									if(year<=preExecDate.get(Calendar.YEAR)&&month<=preExecDate.get(Calendar.MONTH)&&date<preExecDate.get(Calendar.DAY_OF_MONTH)
											&&hourOfDay<preExecDate.get(Calendar.HOUR_OF_DAY)&&minute<preExecDate.get(Calendar.MINUTE)){
										flag=false;
									}
									// 指定年份-未指定月份-指定日-指定时-指定分-未指定秒
									if (StringUtil.equals(strs[0], "*")||StringUtil.equals(strs[0], "?")) {
										second = preExecDate.get(Calendar.SECOND) + 1;
										// 指定年份-指定月份-指定日-指定时-指定分-未指定秒，如果秒>最大值，则没有预计日
										if (second > preExecDate.getMaximum(Calendar.SECOND)) {
											flag=false;
										}
									} else {
										// 指定年份-指定月份-指定日-指定时-指定分-指定秒，
										second = new Integer(strs[0]);
										//如果执行年<=当前年,月份<当前月,日<当前日,时<当前时,分<当前分
										if(year<=preExecDate.get(Calendar.YEAR)&&month<=preExecDate.get(Calendar.MONTH)&&date<preExecDate.get(Calendar.DAY_OF_MONTH)
												&&hourOfDay<preExecDate.get(Calendar.HOUR_OF_DAY)&&minute<preExecDate.get(Calendar.MINUTE)
												&&second<preExecDate.get(Calendar.SECOND)){
											flag=false;
										}
									}
								}
							}
						}
					}
				}
				if(flag){
					preExecDate.set(year, month,date, hourOfDay, minute, second);
					dto.setPreExecDate(DateUtil.format(preExecDate.getTime(), DateUtil.DEFAULT_DATETIME_FORMAT));
				}
			}
		}
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<ReportWorkManage001Dto> queryPageList(Map<String, Object> params) {
		
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage001/findAll",
				params,ReportWorkManage001Dto.class);
		
		
		
		// 获取获取理财产品类型
//		String profitType = (String) params.get("profitType");
//		String finprodId = (String) params.get("finprodId");
//		String checkResult = (String) params.get("checkResult");
//		String checkStatus = (String) params.get("checkStatus");
//		String confirmStatus = (String) params.get("confirmStatus");
//		String noticeStatus = (String) params.get("noticeStatus");
//		String[] profitTypeArr = null;
//		if (StringUtil.isNotEmpty(profitType)) {
//			profitTypeArr = profitType.split(",");
//		}
//		String[] finprodIdArr = null;
//		if (StringUtil.isNotEmpty(finprodId)) {
//			finprodIdArr = finprodId.split(",");
//		}
//		String[] checkResultArr = null;
//		if (StringUtil.isNotEmpty(checkResult)) {
//			checkResultArr = checkResult.split(",");
//		}
//		String[] checkStatusArr = null;
//		if (StringUtil.isNotEmpty(checkStatus)) {
//			checkStatusArr = checkStatus.split(",");
//		}
//		String[] confirmStatustArr = null;
//		if (StringUtil.isNotEmpty(confirmStatus)) {
//			confirmStatustArr = confirmStatus.split(",");
//		}
//		String[] noticeStatusArr = null;
//		if (StringUtil.isNotEmpty(noticeStatus)) {
//			noticeStatusArr = noticeStatus.split(",");
//		}
//		params.put("profitTypeArr", profitTypeArr);
//		params.put("finprodIdArr", finprodIdArr);
//		params.put("checkResultArr", checkResultArr);
//		params.put("checkStatusArr", checkStatusArr);
//		params.put("confirmStatustArr", confirmStatustArr);
//		params.put("noticeStatusArr", noticeStatusArr);
//		List<ReportWorkManage001Dto> list = reportWorkManage001Mapper.queryPageList(params);
//		for (ReportWorkManage001Dto dto : list) {
//			try{
//				parseValueToDate(dto);
//			}catch(Exception e) {
//				log.info(e.toString());
//			}
//		}
//		return reportWorkManage001Mapper.queryPageList(params);
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param map
	 * @see
	 * @since 1.0
	 */
	public int updateConfirmStatus(List<ReportWorkManage001Dto> listdto) {
		int counts = 0;
		for (ReportWorkManage001Dto dto : listdto) {
			counts = counts + reportWorkManage001Mapper.updateConfirmStatusById(dto);
		}
		return counts;
	}

	/**
	 * 估值表余额表状态表 - 确认状态和下发状态更新 {@inheritDoc}
	 */
	@Override
	public void doComplete(ReportWorkManage001Dto entity, OperationType type) {
		if (entity != null) {
		}
	}

	/***
	 * 删除 <一句话功能简述> <功能详细描述>
	 * 
	 * @param jobId
	 * @return
	 */
	public int deleteByPrimaryKey(String jobId) {
//		return repJobMapper.deleteByPrimaryKey(jobId);
		return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage001/deleteByPrimaryKey?jobId="+jobId,
    			Integer.class);
		
	}
	  /**
     * 立即跑
     * @param map
     * @return
     */
    public String runNow(Map<String, Object> map){
    	
    	
    	
    	
    	return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage001/runJobNow",
    			map,String.class);
    	
//    	String  flag = "03";
//    	try {
//    		//通过jobid获取job的内容
//        	String jobId = map.get("jobId").toString();
//        	RepJob repJob = repJobMapper.selectByPrimaryKey(jobId);
//        	String execDate = map.get("execDate").toString();
//        	//生成披露规则用
////        	Map<String,Date> dateMap = new HashMap<String,Date>();
//        	//RepInstallLog repInstallLog = reportWorkManage001Mapper.isExecByJobIdAndDate(jobId, DateUtil.now());
////        	RepInstallLog repInstallLog = reportWorkManage001Mapper.isExecByJobIdAndDate(jobId, DateUtil.parse(execDate));
//        	RepInstallLog repInstallLog = restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage001/isExecByJobIdAndDate",
//        			map,RepInstallLog.class);
//        	
//        	
//        	
//    		if(repInstallLog!=null){
////    			flag = infoDisclosureReadAndGenDetailService.readAndGenDetailAgain(repJob, dateMap, 
////    					repInstallLog.getExecDate(),repInstallLog.getJobSeq());
//    			if(StringUtil.equals(DisclosureDictCodeList.批处理信息披露状态_执行中.getValue(), repInstallLog.getExecStatus())){
//    				flag = "02";
//    			}
//    			else{
//    				boolean result = infoDisclosureReadAndGenDetailService.readAndGenMain(repJob, DisclosureDictCodeList.执行标志_重新执行.getValue()
//        					,repInstallLog.getJobSeq(), repInstallLog.getExecDate());
//        			if(result){
//        				flag = "01";
//        			}
//    			}
//    		}
//    		else{
//    			//flag = infoDisclosureReadAndGenDetailService.readAndGenMain(repJob, ReportDictCodeList.执行标志_首次执行.getValue(), null, null);
//    			boolean result =  infoDisclosureReadAndGenDetailService.readAndGenMain(repJob, DisclosureDictCodeList.执行标志_首次执行.getValue(), null, DateUtil.parse(execDate));
//    			if(result){
//    				flag = "01";
//    			}
//    		}
//		} catch (Exception e) {
////			e.printStackTrace();
//			log.error("error:",e);
//		}
//    	return flag;
    }
    /**
     * 将定时任务扫描任务组插入springBatch中
     * @return
     */
    @DataSource(DataSourceType.OTHER)
    public boolean execBatchRefresh(){
    	return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage001/execBatchRefresh",
    			null,boolean.class);
    	
    	
    }
//    	boolean flag = false;
//    	 String selectedBatchGroup = "10";
//         String selectedProductGroup = "";
//         String startDate = DateUtil.getCurrentDate();
//         String endDate = DateUtil.getCurrentDate();
//         String batchGroupType = "1";
//         String prodGroupType = "0";
//
//         String batchGroupSeqNo = selectedBatchGroup;
//         String nowString = null;
//         if (GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(batchGroupType)) {
//             batchGroupSeqNo = IDGenerator.generate();
//             SysBatchGrp batchGroup = new SysBatchGrp();
//             //TODO
//             batchGroup.setSystemCode("SYS");
//             nowString = DateUtil.getCurrentDateTime();
//             batchGroup.setSeqNo(batchGroupSeqNo);
//             batchGroup.setBatchName(SecurityUtil.getLoginUser().getRealName() + "创建于" + nowString);
//             batchGroup.setDetail(batchGroup.getBatchName() + ";启动批处理时创建。");
//             String[] batchGroupSeqNos = selectedBatchGroup.split(",");
//             List<SysBatchJob> jobList = new ArrayList<>();
//             for (String taskId : batchGroupSeqNos) {
//                 SysBatchJob  job = new SysBatchJob();
//                 job.setBatchSeqNo(batchGroupSeqNo);
//                 job.setTaskId(taskId);
//                 jobList.add(job);
//             }
//             batchGroupMapper.insert(batchGroup);
//             sysBatchStart001Mapper.batchInsertBatchGroup(jobList);
//         }
//
//         String prodGroupSeqNo = selectedProductGroup;
//         if (StringUtil.isNotEmpty(prodGroupSeqNo)
//             && GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(prodGroupType)) {
//             prodGroupSeqNo = IDGenerator.generate();
//             SysBatchPrdGrp  productGroup = new SysBatchPrdGrp();
//             productGroup.setSeqNo(prodGroupSeqNo);
//             productGroup.setGroupName(SecurityUtil.getLoginUser().getRealName() + "创建于"
//                                       + (nowString == null ? DateUtil.getCurrentDateTime() : nowString));
//             productGroup.setDetail(productGroup.getGroupName() + ";启动批处理时创建。");
//             String[] prodGroupSeqNos = StringUtil.split(selectedProductGroup, ",");
//             List<Product> productList = new ArrayList<Product>();
//             if (productService != null) {
//             	productList = productService.findProductByIds(prodGroupSeqNos);
//             }
//             Map<String, Product> productMap = productList.parallelStream().collect(
//                 Collectors.toMap(Product::getProdId, product -> product));
//             List<SysBatchGrpPrdlink> products = new ArrayList<>();
//             for (String prodId : prodGroupSeqNos) {
//                 SysBatchGrpPrdlink  groupProducts = new SysBatchGrpPrdlink ();
//                 groupProducts.setProductId(prodId);
//                 groupProducts.setGroupSeq(prodGroupSeqNo);
//                 groupProducts.setProductName(productMap.get(prodId).getProdName());
//                 products.add(groupProducts);
//             }
//             batchProductGroupMapper.insert(productGroup);
//             sysBatchStart001Mapper.batchInsertProdGroup(products);
//         }
//
//         SysBatchgrpExecInfo executeDetail = new SysBatchgrpExecInfo();
//         executeDetail.setSystemCode("SYS");
//         executeDetail.setSeqNo(IDGenerator.generate());
//         executeDetail.setBatchSeqNo(batchGroupSeqNo);
//         executeDetail.setPrdGroupSeqNo(prodGroupSeqNo);
//         executeDetail.setStartDate(DateUtil.parse(startDate));
//         executeDetail.setEndDate(DateUtil.parse(endDate));
//         executeDetail.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
//         executeDetail.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//         executeDetail.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//         executeDetail.setDetailName("信息披露扫描作业情况"+DateUtil.getCurrentDateTime());
//         batchExecuteDetailMapper.insert(executeDetail);
//
//        flag =true;
//    	return flag;
//    }
}
