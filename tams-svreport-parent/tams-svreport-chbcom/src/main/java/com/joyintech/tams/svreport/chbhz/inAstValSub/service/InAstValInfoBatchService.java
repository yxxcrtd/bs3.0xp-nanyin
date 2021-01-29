package com.joyintech.tams.svreport.chbhz.inAstValSub.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.service.CommonCodeCheckBatchService;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.dao.ChbAstValueSubMapper;
import com.joyintech.tams.common.base.entity.ChbAstValSubTmp;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.svreport.chb.astvalueregist.dao.ChbAstValueRegist001Mapper;
import com.joyintech.tams.svreport.chb.astvalueregist.dto.ChbAstValueRegist001Dto;
import com.joyintech.tams.svreport.chbhz.inAstValSub.dao.InAstValInfoSvrMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
/**
 * 功能:资产估值信息登记生成批处理主service
 * @since 1.0.0
 * @version 1.0.0
 * @作者：zhaoyongsheng
 * @创建时间：
 * @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InAstValInfoBatchService extends FileImpService {
	
    /**
     * 重复性检查字段
     */
    private static final String[] DUPLICATIED_LIST = {"ASSET_CODE"};
    
    /**
     * 业务主键
     */
    private static final String BUSSINESSKEY = "ASSET_CODE";
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
	
	@Autowired
	private SvrMaptoEntityUtil svrMaptoEntityUtil;
	
    @Autowired
    private ChbAstValueRegist001Mapper chbAstValueRegist001Mapper;
	
	
    @Autowired
    private InAstValInfoSvrMapper inAstValInfoSvrMapper;
    
    @Autowired
    private SqlSessionTemplate template;
    /**
     * 中债基础数据mapper
     */
    @Autowired
    private BaseMapper baseMapper;

    // add by dubaojun 2020-10-12 配置无法实现，代码实现的check start
    @Autowired
    private CommonCodeCheckBatchService commonCodeCheckBatchService;
    // add by dubaojun 2020-10-12 配置无法实现，代码实现的check end
    /**
     * @param  
     * @see
     * @since 1.0
     */
    public void generateAstValInfoData(Date batchDate) {
        logger.debug("资产估值信息登记生成批处理处理开始------" +  df.format(new Date()));
        //1、取得业务数据  新建list<产品估值信息登记接口表DTO>							
    	List<ChbAstValSubTmp>  listTmp = inAstValInfoSvrMapper
    			.genChbAstValSubTmpList(DateUtil.format(batchDate));
    	
    	//2 、校验、生成错误数据、正确数据的数据结构
    	//查询数据库表字段
        List<String> listCloumn = baseMapper.getTableColumnList(astValInfoTableName);
    	//2.1调用共通  将list<接口表DTO>转换成List<Map<Key, Object>>格式
        List<Map<String, String>>  listMap=svrMaptoEntityUtil.convertEntityToMap(listTmp, listCloumn);
        //2.2调用FileImpService.checkData方法
        // add by dubaojun 2020-10-12 修复文件名为null异常的问题 start
        checkData(true,listMap, astValInfoTableName, astValInfoTableName, astValInfoTableName, BUSSINESSKEY, DUPLICATIED_LIST);
        // add by dubaojun 2020-10-12 修复文件名为null异常的问题 end

        // add by dubaojun 2020-10-12 配置无法实现，代码实现的check start
        commonCodeCheckBatchService.codeCheckCommon(listMap, ChbCheckTableInfo.资产估值信息登记表SUB, BUSSINESSKEY);
        // add by dubaojun 2020-10-12 配置无法实现，代码实现的check end
        
        //3、正确数据处理
        //3.1 对[2]清理掉错误数据的List，调用以下共通，进行数据类型转化处理。将检查后的listMap 转为List<业务表登记表Dto>
        List<ChbAstValueRegist001Dto>  chbAstValueSubList=svrMaptoEntityUtil
        		.convertMapToEntity(listMap, listCloumn,ChbAstValueRegist001Dto.class);
        
        //拼接查询主keylist
        List<String> keylist = chbAstValueSubList.stream().map(dto -> getBusinessKeyStr(dto))
        		.collect(Collectors.toList());
        
        //3.2.1查询已经在登记表中存在的数据
        //定义已经在登记表存在的数据
        List<ChbAstValueRegist001Dto>  chbAstValueSubListin=new  ArrayList<>();
        chbAstValueSubListin=inAstValInfoSvrMapper.genChbAstValSubList(keylist);
        
        //3.2.2  将已存在报送中的数据放入map中
        Map<String,ChbAstValueRegist001Dto>   mapSub=chbAstValueSubListin.stream().collect(
	                Collectors.toMap(dto -> getBusinessKeyStr(dto), 
                		chbAstValueRegist001Dto -> chbAstValueRegist001Dto));
        
        //报送时点
        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        
        //删除数据list
        List<ChbAstValueRegist001Dto>  deleteList=new ArrayList<ChbAstValueRegist001Dto>();
        //新建数据list
        List<ChbAstValueRegist001Dto>  insertList=new ArrayList<ChbAstValueRegist001Dto>();

        //]清理掉错误数据的List进行循环处理
        for(ChbAstValueRegist001Dto  sub:chbAstValueSubList){
        	 //登记序列
        	sub.setRegistSeqno(IDGenerator.generate(36));
        	//数据日期
        	sub.setStatusDate(batchDate);
            //创建人
        	sub.setCreateUser(CommonUtil.getUserName());
            //更信任
        	sub.setUpdateUser(CommonUtil.getUserName());
            //创建部门
        	sub.setCreateDept(CommonUtil.getDeptCode());
            //创建机构
        	sub.setOrgCode(CommonUtil.getOrgCode());
            //发行机构代码取值
        	//sub.setIssuePartyId(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID));
            //报送流水号
			sub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
			// 业务表不存在数据：
						if (!mapSub.containsKey(getBusinessKeyStr(sub))) {
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
						} else if (mapSub.containsKey(getBusinessKeyStr(sub))) {
							// 临时表存在、业务表也存在 的情况下
							ChbAstValueRegist001Dto subYw = mapSub.get(getBusinessKeyStr(sub));
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
										if (!compareAstValRegData(subYw, sub)) {
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
											// 新建 数据状态为删除数据状态，未报送数据
											sub.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
											// 报送状态
											sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
											insertList.add(sub);
									}
									//tmp表数据不为删除时
									else{
										// 删除数据List.add(此条记录）删除的是未报送数据
										deleteList.add(sub);
										// 两条记录的所有申报字段比较，存在不一致的情况下，进行以下处理
										if (!compareAstValRegData(subYw, sub)) {
												// 此条记录的【数据状态】= '02:修改'
												sub.setStatus(DictCodeListExt2.报送数据状态_修改.getValue());
												// 报送状态
												sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
												insertList.add(sub);
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
        	// 产品交易登记报送时点:交易登记日期
            if(sub.getValDate()!=null){ 
                List<Date> issueDateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_资产估值信息登记.getValue(), 
                        DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
                        sub.getValDate());
			        
                if (CollectionUtils.isNotEmpty(issueDateList)) {
                    sub.setChbSubmitDeadline(issueDateList.get(0));
                    sub.setSubmitDeadline(issueDateList.get(1));
                }

            }
        	
        }
        logger.debug("资产估值信息登记生成批处理处理结束------" +  df.format(new Date()));
        //进行入库处理
        logger.debug("资产估值信息登记入库开始------" +  df.format(new Date()));
        dealResultAstValCommitSingle(deleteList, insertList);
        logger.debug("资产估值信息登记入库："+insertList.size()+"条");
        logger.debug("资产估值信息登记入库结束------" +  df.format(new Date()));

        
    }
	@Override
	public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String importDB(List<Map<String, String>> listData, InterfFile interfFile) {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     * @Description 判断要素表中发行登记数据是否有变
     * @param dto
     * @param regdto
     * @return boolean
     * @version 1.7 2019年5月22日
     */
    public boolean compareAstValRegData(ChbAstValueRegist001Dto dto,ChbAstValueRegist001Dto regdto){
    	ChbAstValueRegist001Dto compare1 = genAstValDto(dto);
    	ChbAstValueRegist001Dto compare2 = genAstValDto(regdto);
        if(JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))){
            return true;
        }else{
            return false;
        }
    }
    
    private ChbAstValueRegist001Dto genAstValDto(ChbAstValueRegist001Dto dto){
    	ChbAstValueRegist001Dto gendto = new ChbAstValueRegist001Dto();
    	gendto.setUnitValNet((dto.getUnitValNet()==null?BigDecimal.ZERO:dto.getUnitValNet())
    			.setScale(8, BigDecimal.ROUND_HALF_UP));
    	gendto.setUnitValTotal((dto.getUnitValTotal()==null?BigDecimal.ZERO:dto.getUnitValTotal())
    			.setScale(8, BigDecimal.ROUND_HALF_UP));
    	gendto.setRemark(dto.getRemark());
        return gendto;
    }
 
    
    /**
     * 功能:带提交的方式单条处理数据结果，入库
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月7日
     * @param assetList 
     * void
     */
    private void dealResultAstValCommitSingle(List<ChbAstValueRegist001Dto> deleteDataList,List<ChbAstValueRegist001Dto> insertDataList){
        if (CollectionUtils.isNotEmpty(deleteDataList)) {
            int batchCount = Integer.parseInt( null == SvrDataUtil
            		.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"50" : SvrDataUtil
            				.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));//每批commit的个数
            int batchLastIndex = batchCount;//每批最后一个的下标
            for(int index = 0; index < deleteDataList.size();){
                if(batchLastIndex > deleteDataList.size()-1){
                    batchLastIndex = deleteDataList.size();
                    chbAstValueRegist001Mapper.deleteByAssetCodeList(deleteDataList.subList(index, batchLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                	chbAstValueRegist001Mapper.deleteByAssetCodeList(deleteDataList.subList(index, batchLastIndex));
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + batchCount;                     
                }
            }
        }
        if (CollectionUtils.isNotEmpty(insertDataList)) {
            int count = 0;
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try{
            	ChbAstValueSubMapper batchMapper =  session.getMapper(ChbAstValueSubMapper.class);
                for(ChbAstValueRegist001Dto asset : insertDataList){
                    batchMapper.insert(asset);
                    count++;
                    if(count%1000==0){
                        session.commit();
                        // 清理缓存，防止溢出
                        session.clearCache();
                    }
                }
                session.commit();
                // 清理缓存，防止溢出
                session.clearCache();
            }
            catch(Exception e){
                session.rollback();
                logger.error(e.getMessage(),e);
            }
            finally {
                session.close();
            }
        }
    }
	/**
	 * 获取业务主键拼接的字符串
	 * 
	 * @param sub
	 *            数据
	 * @return 业务主键
	 */
	private String getBusinessKeyStr(ChbAstValueRegist001Dto sub) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(sub.getAssetCode());
		buffer.append("|");
		buffer.append(DateUtil.format(sub.getValDate()));
		return buffer.toString();
	}
}
