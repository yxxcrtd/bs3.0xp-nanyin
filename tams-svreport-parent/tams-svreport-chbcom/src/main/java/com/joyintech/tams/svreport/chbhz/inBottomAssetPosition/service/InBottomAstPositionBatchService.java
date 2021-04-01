package com.joyintech.tams.svreport.chbhz.inBottomAssetPosition.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.chbreport.codecheck.service.CommonCodeCheckTmpBatchService;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.ChbBotAstPositionSubTmp;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.service.DataCheckService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chbhz.inBottomAssetPosition.dao.InBottomAstPositionSvrMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
/**
 * 功能:底层资产持仓信息登记生成批处理主service
 * @since 1.0.0
 * @version 1.0.0
 * @作者：lingxq
 * @创建时间：
 * @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InBottomAstPositionBatchService extends FileImpService {
	
    /**
     * 重复性检查字段
     */
    private static final String[] DUPLICATIED_LIST = {"POS_DATE","PLAN_ASSET_ID","ASSET_CODE"};
    
    /**
     * 业务主键
     */
    private static final String BUSSINESSKEY = "PLAN_ASSET_ID";
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
	@Autowired
	private SvrMaptoEntityUtil svrMaptoEntityUtil;
	
    @Autowired
    private InBottomAstPositionSvrMapper inBottomAstPositionSvrMapper;
    
	@Value("${application.reportInsertCount}")
	public  int reportInsertCount;
    /**
     * 中债基础数据mapper
     */
    @Autowired
    private BaseMapper baseMapper;

	@Autowired
	private CommonCodeCheckTmpBatchService commonCodeCheckTmpBatchService;

    @Autowired
	private DataCheckService dataCheckService;

    /**
     * @param  
     * @see
     * @since 1.0
     */
    public void generateBottomAstPositionData(Date batchDate) {
        logger.debug("底层资产持仓信息登记生成批处理处理开始------" +  df.format(new Date()));
        //1、取得业务数据  新建list<产品交易登记接口表DTO>							
    	List<ChbBotAstPositionSubTmp>  listTmp = inBottomAstPositionSvrMapper
    			.genChbInBottomAstPosTmpList(DateUtil.format(batchDate));
    	
    	if(StringUtil.isEmptyList(listTmp)){
    		return;
    	}
    	
    	//2 、校验、生成错误数据、正确数据的数据结构
    	//查询数据库表字段
        List<String> listCloumn = baseMapper.getTableColumnList(botPosTableName);
    	//2.1调用共通  将list<接口表DTO>转换成List<Map<Key, Object>>格式
        List<Map<String, String>>  listMap = svrMaptoEntityUtil.convertEntityToMap(listTmp, listCloumn);

        List<String> dataIdList = listMap.stream().map(x -> StringUtil.defaultString(x.get("PLAN_ASSET_ID"), "")
                + StringUtil.defaultString(x.get("ASSET_CODE"), "")
                + StringUtil.defaultString(x.get("POS_DATE"), "")).collect(Collectors.toList());

        deletAllErroData(dataIdList, botPosTableName);
        // 数据规则校验
        DataCheckService.checkDataList(listMap,dataCheckService.queryCheckRules(botPosTableName),true);

		/**start    配置无法实现，代码实现的check   add by  zhangliang 20201012*/
		commonCodeCheckTmpBatchService.codeCheckTmpCommonBotAstPos(listMap, botPosTableName);
		/**end       配置无法实现，代码实现的check   add by  zhangliang 20201012*/
        
        //3、正确数据处理
        //3.1 对[2]清理掉错误数据的List，调用以下共通，进行数据类型转化处理。将检查后的listMap 转为List<业务表登记表Dto>
        List<ChbBotAstPositionSubTmp>  chbBomAstPositionSubList = svrMaptoEntityUtil.convertMapToEntity(listMap, listCloumn,ChbBotAstPositionSubTmp.class);
        
        //3.2.1查询已经在登记表中存在的数据
        //定义已经在登记表存在的数据
        List<ChbBotAstPositionSubTmp>  chbBomAstPosSubListin = inBottomAstPositionSvrMapper.genChbInBottomAstPosSubList(chbBomAstPositionSubList);
        
        //3.2.2  将已存在报送中的数据放入map中
        Map<String,ChbBotAstPositionSubTmp>  mapSub = chbBomAstPosSubListin.stream().collect(
                Collectors.toMap(ChbBotAstPositionSubTmp::getKey, chbBotAstPositionRegist001Dto -> chbBotAstPositionRegist001Dto, (v1, v2) -> v1));
        
        //报送时点
        List<Date> dateList = null;
        
        //删除数据list
        List<ChbBotAstPositionSubTmp>  deleteList = new ArrayList<ChbBotAstPositionSubTmp>();
        //新建数据list
        List<ChbBotAstPositionSubTmp>  insertList = new ArrayList<ChbBotAstPositionSubTmp>();
		// 获取系统参数
		String svrSubmitOnChange = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SUBMIT_ONCHANGE);

        //清理掉错误数据的List进行循环处理
        for(ChbBotAstPositionSubTmp  sub : chbBomAstPositionSubList){
        	 //登记序列
        	sub.setRegistSeqno(IDGenerator.generate(36));
        	//数据日期
        	sub.setRowDate(batchDate);
            //创建人
        	sub.setCreateUser(CommonUtil.getUserName());
            //更信任
        	sub.setUpdateUser(CommonUtil.getUserName());
            //创建部门
        	sub.setCreateDept(CommonUtil.getDeptCode());
            //创建机构
        	sub.setOrgCode(CommonUtil.getOrgCode());
            //发行机构代码取值
        	sub.setIssuePartyId(null == sub.getIssuePartyId()?CommonUtil.selectBankCode():sub.getIssuePartyId());
            //报送流水号
			sub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
			// 业务表不存在数据：
						if (!mapSub.containsKey(sub.getKey())) {
							deleteList.add(sub);
							//如果当前数据状不等于删除
							if(!StringUtil.equals(DictCodeListExt2.数据状态_删除.getValue(),sub.getStatus())){
								// 报送状态
								sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
								// 数据状态
								sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
								// 新建数据List.add(此条记录）
								insertList.add(sub);
							}
							//数据状态等于删除，则不需要插入
						} else if (mapSub.containsKey(sub.getKey())) {
							// 临时表存在、业务表也存在 的情况下
							ChbBotAstPositionSubTmp subYw = mapSub.get(sub.getKey());
							// （业务表中此条记录的.【报送状态】 == “02：接口报送”|| 业务表中此条记录的.【报送状态】 ==
							// “03：手工处理” ）
							if ((StringUtil.equals(DictCodeListExt2.报送状态_接口报送.getValue(), subYw.getSendStatus())
									|| StringUtil.equals(DictCodeListExt2.报送状态_手工处理.getValue(), subYw.getSendStatus()))
									) {
								// 当已经报送的数据状态为删除时
								if (StringUtil.equals(subYw.getStatus(), DictCodeListExt2.报送数据状态_删除.getValue())) {
									//tmp表数据为删除时
									if(StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())){
										// 删除数据List.add(此条记录）
										 deleteList.add(sub);
									}else{
									// 删除数据List.add(此条记录）
									deleteList.add(sub);
									sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
									// 报送状态
									sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
									insertList.add(sub);
									}
								}else{
								// 反馈状态为发送失败和中债退回时候
								if (StringUtil.equals(subYw.getSubmitFeedbackStatus(), DictCodeListExt2.报送反馈状态_中债退回.getValue())
										|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
												DictCodeListExt2.报送反馈状态_发送失败.getValue())) {
									//tmp表数据为删除时
									if(StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())){
										//删除未报送
										 deleteList.add(sub);
									}
									//tmp表数据不为删除时
									else{
										// 删除数据List.add(此条记录）删除的是未报送数据
										deleteList.add(sub);
										// 内容不一致进行处理
										if (!compareBomAstPosRegData(subYw, sub)) {
											// 此条记录的【数据状态】= 原来的【数据状态】
											sub.setStatus(subYw.getStatus());
											// 报送状态
											sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
											insertList.add(sub);
										}
									}
								} // 反馈状态为发送成功或中债通过或审核文件下载失败
								else if (StringUtil.equals(subYw.getSubmitFeedbackStatus(), DictCodeListExt2.报送反馈状态_发送成功.getValue())
										|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
												DictCodeListExt2.报送反馈状态_中债通过.getValue())
										|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
												DictCodeListExt2.报送反馈状态_审核文件下载失败.getValue())) {
									//tmp表数据为删除时
									if(StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())){
										//删除未报送
										deleteList.add(sub);
										if(StringUtil.equals(svrSubmitOnChange, DictCodeListExt1.是否_是.getValue())){
											// 新建 数据状态为删除数据状态，未报送数据
											sub.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
											// 报送状态
											sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
											insertList.add(sub);
										}
									}
									//tmp表数据不为删除时
									else{
										// 删除数据List.add(此条记录）删除的是未报送数据
										deleteList.add(sub);
										// 两条记录的所有申报字段比较，存在不一致的情况下，进行以下处理
										if (!compareBomAstPosRegData(subYw, sub)) {
											if(StringUtil.equals(svrSubmitOnChange, DictCodeListExt1.是否_是.getValue())){
												// 此条记录的【数据状态】= '02:修改'
												sub.setStatus(DictCodeListExt2.报送数据状态_修改.getValue());
												// 报送状态
												sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
												insertList.add(sub);
											}
										}
									}
								}
							}
							}else{
        			//此条记录的【数据状态】= '02:新建'
        			sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
        			//报送状态
            		sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
        			//删除数据List.add(此条记录）
              		//新建数据List.add(此条记录）
          		    deleteList.add(sub);
            		insertList.add(sub);
        		}
        	}
        	// 底层持仓登记报送时点:持仓日期
            if(sub.getPosDate()!=null){ 
    	    	Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
            	dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_资管计划底层持仓信息登记.getValue(), 
								            			 DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
								    					 sub.getPosDate());
				if (CollectionUtils.isNotEmpty(dateList)) {
					// 中债报送截止日
					sub.setChbSubmitDeadline(dateList.get(0));
					// 行内报送截止日
					sub.setSubmitDeadline(dateList.get(1));
				}
            }
        	
        }
        logger.debug("底层资产持仓信息生成批处理处理结束------" +  df.format(new Date()));
        //进行入库处理
        logger.debug("底层资产持仓信息登记入库开始------" +  df.format(new Date()));
        deleteBatchList(deleteList,"01");
        insertBatchList(insertList);
        logger.debug("底层资产持仓信息登记入库："+insertList.size()+"条");
        logger.debug("底层资产持仓信息登记入库结束------" +  df.format(new Date()));

        
    }
    
	@Override
	public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
		return null;
	}
	
	@Override
	public String importDB(List<Map<String, String>> listData, InterfFile interfFile) {
		return null;
	}
    /**
     * @Description 判断底层持仓登记数据是否有变
     * @param dto
     * @param regdto
     * @return boolean
     * @version 1.7 2019年5月22日
     */
    public boolean compareBomAstPosRegData(ChbBotAstPositionSubTmp dto,ChbBotAstPositionSubTmp regdto){
    	ChbBotAstPositionSubTmp compare1 = genBomAstPosDto(dto);
    	ChbBotAstPositionSubTmp compare2 = genBomAstPosDto(regdto);
        if(JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))){
            return true;
        }else{
            return false;
        }
    }
    
    private ChbBotAstPositionSubTmp genBomAstPosDto(ChbBotAstPositionSubTmp dto){
    	ChbBotAstPositionSubTmp gendto = new ChbBotAstPositionSubTmp();
    	gendto.setIssuePartyId(dto.getIssuePartyId());
    	gendto.setPosDate(dto.getPosDate());
    	gendto.setPlanAssetId(dto.getPlanAssetId());
    	gendto.setAssetCode(dto.getAssetCode());
    	gendto.setTotalNum(dto.getTotalNum()== null ? BigDecimal.ZERO : dto.getTotalNum().setScale(5,
				BigDecimal.ROUND_HALF_UP));
    	gendto.setIssueAmt(dto.getIssueAmt()== null ? BigDecimal.ZERO : dto.getIssueAmt().setScale(5,
				BigDecimal.ROUND_HALF_UP));
    	gendto.setUninvestCash(dto.getUninvestCash()== null ? BigDecimal.ZERO : dto.getUninvestCash().setScale(5,
				BigDecimal.ROUND_HALF_UP));
    	gendto.setPosNum(dto.getPosNum()== null ? BigDecimal.ZERO : dto.getPosNum().setScale(5,
				BigDecimal.ROUND_HALF_UP));
    	gendto.setPosAmt(dto.getPosAmt()== null ? BigDecimal.ZERO : dto.getPosAmt().setScale(5,
				BigDecimal.ROUND_HALF_UP));
    	gendto.setRegistBankCode(dto.getRegistBankCode());
//    	gendto.setStatus(dto.getStatus());
        return gendto;
    }
 
    
    /**
     * 功能:带提交的方式单条处理数据结果，入库
     * @param insertDataList 新增数据 
     * void
     */
    private int insertBatchList(List<ChbBotAstPositionSubTmp> insertDataList){
    	//进行入库处理
        int insertCount = insertDataList.size();
        if(insertCount>0){
        	logger.info("底层持仓数据插入--------开始--------insertDataList = " + insertDataList.size());
            if(insertCount<=reportInsertCount){
            	inBottomAstPositionSvrMapper.insertBatch(insertDataList);
            }
            else{
                for(int i = 0; i < insertCount/reportInsertCount ; i++){
                	inBottomAstPositionSvrMapper.insertBatch(insertDataList.subList(0, reportInsertCount));
                    insertDataList.removeAll(insertDataList.subList(0, reportInsertCount));
                }
                if(!CollectionUtils.isEmpty(insertDataList)){
                	inBottomAstPositionSvrMapper.insertBatch(insertDataList);
                }
            }
            logger.info("底层持仓数据插入--------结束---------");
        }
        return insertCount;
    }

    /**
     * 
     * @param deleteDataList 删除数据
     * @return
     */
	private int deleteBatchList(List<ChbBotAstPositionSubTmp> deleteDataList,String flag) {
		int singalCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
        int deleteCount = deleteDataList.size();
        if(deleteCount > 0){
        	logger.info("底层持仓登记数据删除--------开始-----deleteDataList = " + deleteDataList.size());
            if(deleteCount<=singalCount){
            	inBottomAstPositionSvrMapper.deleteBatch(deleteDataList,flag);
            }
            else{
                for(int i = 0; i < deleteCount/singalCount ; i++){
                	inBottomAstPositionSvrMapper.deleteBatch(deleteDataList.subList(0, singalCount),flag);
                    deleteDataList.removeAll(deleteDataList.subList(0, singalCount));
                }
                if(!CollectionUtils.isEmpty(deleteDataList)){
                	inBottomAstPositionSvrMapper.deleteBatch(deleteDataList,flag);
                }
            }
            logger.info("底层持仓数据删除--------结束---------");
        }
		return deleteCount;
	}

}
