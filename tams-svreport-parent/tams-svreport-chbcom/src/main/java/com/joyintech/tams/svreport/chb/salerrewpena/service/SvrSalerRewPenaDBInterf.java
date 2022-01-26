package com.joyintech.tams.svreport.chb.salerrewpena.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbSalerRewPenaInfo;
import com.joyintech.tams.common.base.entity.ChbSalerRewPenaSub;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.svreport.chb.datacheck.beforeinsert.service.ChbCommonCheckService;
import com.joyintech.tams.svreport.chb.salerrewpena.dao.SvrSalerRewPenaDBInterfMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 功能:销售人员奖惩投诉记录接口
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrSalerRewPenaDBInterf extends BaseService{
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SvrSalerRewPenaDBInterfMapper svrSalerRewPenaDBInterfMapper;
	
	/**
     * 数据批量一次查询或者删除条数
     */
    @Value("${application.reportSelectOrDeleteCount}")
    private int reportSelectOrDeleteCount;

    /**
     * 入库前校验用Service
     */
    @Autowired
    private ChbCommonCheckService chbCommonCheckService;
	
    public void generateData(Date batchDate) {
    	//1、开始检索维护表数据
    	logger.info("销售人员奖惩投诉记录检索开始------->>>>>>");
    	List<ChbSalerRewPenaInfo> list = svrSalerRewPenaDBInterfMapper.selectBatchData(DateUtil.format(batchDate));
    	logger.info("销售人员奖惩投诉记录检索结束-------<<<<<<");
    	
    	if(StringUtil.isEmptyList(list)){
    		logger.info("没有检索到数据,批处理结束!");
    		return;
    	}

		// 调用check逻辑
		logger.debug("销售人员奖惩投诉记录登记数据检查开始------检查前数据个数：" + list.size());
		list = chbCommonCheckService.checkData(list, ChbCheckTableInfo.销售人员奖惩投诉记录登记表SUB);
		logger.debug("销售人员奖惩投诉记录登记数据检查结束------检查后数据个数：" + list.size());

		if(StringUtil.isEmptyList(list)){
			logger.info("检查后的数据全部不合规，批处理结束");
			return;
		}
    	//2、查询已登记的信息
    	List<ChbSalerRewPenaSub> regList = svrSalerRewPenaDBInterfMapper.selectRegSalerRewPenaData();
    	//将已存在报送中的数据放入map中
        Map<String,ChbSalerRewPenaSub>   mapSub = regList.stream().collect(
                Collectors.toMap(ChbSalerRewPenaSub::getSalerRewPenaNo, chbSalerRewPena001Sub -> chbSalerRewPena001Sub));
        
    	//3、报送相关字段塞值处理
        //删除数据list
        List<ChbSalerRewPenaSub>  deleteList = new ArrayList<ChbSalerRewPenaSub>();
        //新建数据list
        List<ChbSalerRewPenaSub>  insertList = new ArrayList<ChbSalerRewPenaSub>();
        //中债报送截止日
		List<Date> dateList = null;
		
    	List<ChbSalerRewPenaSub> subList = new ArrayList<ChbSalerRewPenaSub>();
    	//复制上述【1】检索的数据
    	list.forEach(item -> {
    		ChbSalerRewPenaSub sub = new ChbSalerRewPenaSub();
    		BeanUtils.copyProperties(item, sub);
    		subList.add(sub);
    	});
    	
    	for(ChbSalerRewPenaSub sub : subList){
            //创建人
        	sub.setCreateUser(CommonUtil.getUserName());
            //更新人
        	sub.setUpdateUser(CommonUtil.getUserName());
            //UUID主键
        	sub.setRegistSeqno(UUID.randomUUID().toString().replace("-", "").toUpperCase());
            //报送流水号
			sub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
            // 生成的登记都为未报送
			sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());

            if (mapSub.get(sub.getSalerRewPenaNo())!= null){
				// 临时表存在、业务表也存在 的情况下
            	ChbSalerRewPenaSub subYw = mapSub.get(sub.getSalerRewPenaNo());
				// （业务表中此条记录的.【报送状态】 == “02：接口报送”|| 业务表中此条记录的.【报送状态】 ==
				// “03：手工处理” ）
				if ((StringUtil.equals(DictCodeListExt2.报送状态_接口报送.getValue(), subYw.getSendStatus())
						|| StringUtil.equals(DictCodeListExt2.报送状态_手工处理.getValue(), subYw.getSendStatus()))) {
					// 当已经报送的数据状态为删除时
					if (StringUtil.equals(subYw.getStatus(), DictCodeListExt2.报送数据状态_删除.getValue())) {
						// tmp表数据为删除时
						if (StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())) {
							// 删除数据List.add(此条记录）
							deleteList.add(sub);
						} else {
							// 删除数据List.add(此条记录）
							deleteList.add(sub);
							sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
							Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
							dateList = ChbDeadlineUtil.getChbEndDate(
									DictCodeListExt2.报表名称_销售人员奖惩投诉记录登记.getValue(), sub.getStatus(), dateMap, sub.getDataDate());
							// 报送状态
							sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
							if (!CollectionUtils.isEmpty(dateList)) {
								sub.setChbSubmitDeadline(dateList.get(0));
								sub.setSubmitDeadline(dateList.get(1));
							}
							insertList.add(sub);
						}
					} else {
						// 反馈状态为发送失败和中债退回时候
						if (StringUtil.equals(subYw.getSubmitFeedbackStatus(), DictCodeListExt2.报送反馈状态_中债退回.getValue())
								|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
										DictCodeListExt2.报送反馈状态_发送失败.getValue())) {
							// tmp表数据为删除时
							if (StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())) {
								// 删除未报送
								deleteList.add(sub);
							}
							// tmp表数据不为删除时
							else {
								// 删除数据List.add(此条记录）删除的是未报送数据
								deleteList.add(sub);
								// 内容不一致进行处理
								if (!compareSalerRewPenaRegData(subYw, sub)) {
									// 此条记录的【数据状态】= 原来的【数据状态】
									sub.setStatus(subYw.getStatus());
									// 报送状态
									sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
									Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
									dateList = ChbDeadlineUtil.getChbEndDate(
											DictCodeListExt2.报表名称_销售人员奖惩投诉记录登记.getValue(), sub.getStatus(), dateMap, sub.getDataDate());
									if (!CollectionUtils.isEmpty(dateList)) {
										sub.setChbSubmitDeadline(dateList.get(0));
										sub.setSubmitDeadline(dateList.get(1));
									}
									insertList.add(sub);
								}
							}
						} // 反馈状态为发送成功或中债通过或审核文件下载失败
						else if (StringUtil.equals(subYw.getSubmitFeedbackStatus(),
								DictCodeListExt2.报送反馈状态_发送成功.getValue())
								|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
										DictCodeListExt2.报送反馈状态_中债通过.getValue())
								|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
										DictCodeListExt2.报送反馈状态_审核文件下载失败.getValue())
								|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
										DictCodeListExt2.报送反馈状态_发送中.getValue())
								|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
										DictCodeListExt2.报送反馈状态_发送异常.getValue())) {
							// tmp表数据为删除时
							if (StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())) {
								// 删除未报送
								deleteList.add(sub);
								// 新建 数据状态为删除数据状态，未报送数据
								sub.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
								// 报送状态
								sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
								Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
								dateList = ChbDeadlineUtil.getChbEndDate(
										DictCodeListExt2.报表名称_销售人员奖惩投诉记录登记.getValue(), sub.getStatus(), dateMap, sub.getDataDate());
								if (!CollectionUtils.isEmpty(dateList)) {
									sub.setChbSubmitDeadline(dateList.get(0));
									sub.setSubmitDeadline(dateList.get(1));
								}
								insertList.add(sub);

							}
							// tmp表数据不为删除时
							else {
								// 删除数据List.add(此条记录）删除的是未报送数据
								deleteList.add(sub);
								// 两条记录的所有申报字段比较，存在不一致的情况下，进行以下处理
								if (!compareSalerRewPenaRegData(subYw, sub)) {
									// 此条记录的【数据状态】= '02:修改'
									sub.setStatus(DictCodeListExt2.报送数据状态_修改.getValue());
									// 报送状态
									sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
									Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
									dateList = ChbDeadlineUtil.getChbEndDate(
											DictCodeListExt2.报表名称_销售人员奖惩投诉记录登记.getValue(), sub.getStatus(), dateMap, sub.getDataDate());
									if (!CollectionUtils.isEmpty(dateList)) {
										sub.setChbSubmitDeadline(dateList.get(0));
										sub.setSubmitDeadline(dateList.get(1));
									}
									insertList.add(sub);

								}
							}
						}
					}
				} else {
					// 此条记录的【数据状态】= '02:新建'
					sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
					// 报送状态
					sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
					// 删除数据List.add(此条记录）
					// 新建数据List.add(此条记录）
					deleteList.add(sub);
					Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
					dateList = ChbDeadlineUtil.getChbEndDate(
							DictCodeListExt2.报表名称_销售人员奖惩投诉记录登记.getValue(), sub.getStatus(), dateMap, sub.getDataDate());
					if (!CollectionUtils.isEmpty(dateList)) {
						sub.setChbSubmitDeadline(dateList.get(0));
						sub.setSubmitDeadline(dateList.get(1));
					}
					insertList.add(sub);
				}
            } else{
                if (DictCodeListExt1.维护数据状态_删除.getValue().equals(sub.getStatus())){
                    deleteList.add(sub);
                    continue;
                } else {
                	sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
                    deleteList.add(sub);
					Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
					dateList = ChbDeadlineUtil.getChbEndDate(
							DictCodeListExt2.报表名称_销售人员奖惩投诉记录登记.getValue(), sub.getStatus(), dateMap, sub.getDataDate());
					if (!CollectionUtils.isEmpty(dateList)) {
						sub.setChbSubmitDeadline(dateList.get(0));
						sub.setSubmitDeadline(dateList.get(1));
					}
					insertList.add(sub);
                }
            }
    	}
    	
    	//4、持久化DB
    	logger.info("销售人员奖惩投诉记录入库开始------->>>>>>");
    	dbData(deleteList,insertList);
    	logger.info("销售人员奖惩投诉记录入库结束-------<<<<<<");
    	
    }
    
    /**
     * 先删后插持久化数据
     * @param deleteList
     * @param insertList
     */
    private void dbData(List<ChbSalerRewPenaSub> deleteList, List<ChbSalerRewPenaSub> insertList) {
    	
    	if (StringUtil.isNotEmpty(deleteList)) {
		    // 插入前先删除未报送的登记数据
		    logger.debug("销售人员奖惩投诉记录删除未报送数据：删除开始----------");
		    // 数据批量一次删除条数,如果参数设置过大则强制定位50
            int batchDeleteCount = reportSelectOrDeleteCount;
            // 每批最后一个的下标
            int batchDeleteLastIndex = batchDeleteCount;            
            for(int index = 0; index < deleteList.size();){
                if(batchDeleteLastIndex > deleteList.size()-1){
                    batchDeleteLastIndex = deleteList.size();
                    svrSalerRewPenaDBInterfMapper.deleteBatchData(deleteList.subList(index, batchDeleteLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                	svrSalerRewPenaDBInterfMapper.deleteBatchData(deleteList.subList(index, batchDeleteLastIndex));
                    index = batchDeleteLastIndex;//设置下一批下标
                    batchDeleteLastIndex = index + batchDeleteCount;                     
                }
            }
            logger.debug("销售人员奖惩投诉记录删除未报送数据：删除结束----------");
            logger.debug("销售人员奖惩投诉记录插入数据：插入开始----------");
			for(ChbSalerRewPenaSub sub : insertList){

				svrSalerRewPenaDBInterfMapper.insertBatchData(sub);
			}

            logger.debug("销售人员奖惩投诉记录插入数据：插入结束----------");
		}
	}


	/**
     * @Description 判断销售人员奖惩投诉记录数据是否有变
     * @param dto
     * @param regdto
     * @return boolean
     * @version 1.7 2019年5月22日
     */
    public boolean compareSalerRewPenaRegData(ChbSalerRewPenaSub dto,ChbSalerRewPenaSub regdto){
    	ChbSalerRewPenaSub compare1 = genSalerRewPenaDto(dto);
    	ChbSalerRewPenaSub compare2 = genSalerRewPenaDto(regdto);
        if(JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 对象封装
     * @param dto
     * @return
     */
	private ChbSalerRewPenaSub genSalerRewPenaDto(ChbSalerRewPenaSub dto) {
		ChbSalerRewPenaSub tmpDto = new ChbSalerRewPenaSub();
		tmpDto.setSalerInsNo(null==dto.getSalerInsNo()?"":dto.getSalerInsNo());
		tmpDto.setSalerInstCode(null==dto.getSalerInstCode()?"":dto.getSalerInstCode());
		tmpDto.setSalerRewPenaTyep(null==dto.getSalerRewPenaTyep()?"":dto.getSalerRewPenaTyep());
		tmpDto.setSalerRewPenaName(null==dto.getSalerRewPenaName()?"":dto.getSalerRewPenaName());
		tmpDto.setSalerRewPenaComments(null==dto.getSalerRewPenaComments()?"":dto.getSalerRewPenaComments());
		tmpDto.setSalerRewPenaInst(null==dto.getSalerRewPenaInst()?"":dto.getSalerRewPenaInst());
		tmpDto.setSalerRewPenaInstName(null==dto.getSalerRewPenaInstName()?"":dto.getSalerRewPenaInstName());
		tmpDto.setSalerRewPenaStart(null==dto.getSalerRewPenaStart()?new Date():dto.getSalerRewPenaStart());
		tmpDto.setSalerRewPenaEnd(null==dto.getSalerRewPenaEnd()?new Date():dto.getSalerRewPenaEnd());
		tmpDto.setSalerComplainDetail(null==dto.getSalerComplainDetail()?"":dto.getSalerComplainDetail());
		tmpDto.setSalerComplainResult(null==dto.getSalerComplainResult()?"":dto.getSalerComplainResult());
		tmpDto.setSalerComplainDate(null==dto.getSalerComplainDate()?new Date():dto.getSalerComplainDate());
		tmpDto.setUpdateTime(null);
		tmpDto.setCreateTime(null);
		return tmpDto;
	}
    
	
}
