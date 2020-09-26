package com.joyintech.tams.svreport.interf.chb.prodinfo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.ChbTypeMapping;
import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.common.constant.InterfMappingConstants;
import com.joyintech.tams.svreport.interf.chb.assetinfo.dao.ChbTypeMappingMapper;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdBaseinfoExtemdMapper;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdBaseinfoInterfMapper;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ProdInfoMapper;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dto.ChbPrdBaseinfoExtend;
import com.joyintech.tams.systemtools.util.CommonUtil;


/**
 * 产品要素表接口
 * @author luminmin
 *
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdInfoInterfService extends BaseService {
	@Autowired
	private ProdInfoMapper prodInfoMapper;
	@Autowired
	private ChbPrdBaseinfoInterfMapper chbPrdBaseinfoInterfMapper;
    
    @Autowired
    private ChbTypeMappingMapper chbTypeMappingMapper;
    @Autowired
    private ChbPrdBaseinfoExtemdMapper chbPrdBaseinfoExtemdMapper;
	@Autowired
	private SqlSessionTemplate template;
	@Value("${application.reportInsertCount}")
	public  int reportInsertCount;
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 数据批量一次性insert条数(单条数据字段较多)
	 */
	@Value("${application.reportInsertLongCount}")
	private int reportInsertLongCount;
	/**
	 * @Description 接口接入产品要素信息
	 * @param batchTime
	 * @return List<ChbPrdBaseinfo>
	 * @version 1.0 2019年3月5日
	 */
	public List<String> getIssueData(Date batchTime) {
		ProdInfoInterfService prodInfoInterfService = SpringContextUtil.getBean(ProdInfoInterfService.class);
		// 从bs库FIN_PRODUCT，REP_PROD_ISSUE，REP_PROD_DECLARE获取数据
		List<String> prodList = new ArrayList<String>();
		List<ChbPrdBaseinfo> prodinfos = new ArrayList<ChbPrdBaseinfo>();
		Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.产品要素信息接口.getInterId());
		// 查询信息
		logger.debug("产品要素查询开始--------------"+new Date());
		prodinfos = prodInfoInterfService.selectProdInfo(lastUpdatetime);
		// 开放周期映射
        List<ChbTypeMapping> openCycleMappingList = chbTypeMappingMapper.selectMappingBySrcDataType(InterfMappingConstants.MAAPING_OPEN_CYCLE_UNIT_ZG);
        Map<String, ChbTypeMapping> openCycleMappingMap  = new HashMap<String, ChbTypeMapping>();
        if (!StringUtil.isEmptyList(openCycleMappingList)){
            openCycleMappingMap = openCycleMappingList.stream().collect(Collectors.toMap(ChbTypeMapping::getSrcdatatypeid, chbTypeMapping -> chbTypeMapping));
        }
        //业绩比较基准 处理
        List<ChbPrdBaseinfoExtend> prodYieldList = chbPrdBaseinfoExtemdMapper.selectProdYieldInfo(lastUpdatetime);
        Map<String, List<ChbPrdBaseinfoExtend>> prodYieldMap  = new HashMap<String, List<ChbPrdBaseinfoExtend>>();
        if (!StringUtil.isEmptyList(prodYieldList)){
        	prodYieldMap = prodYieldList.stream().collect(Collectors.groupingBy(ChbPrdBaseinfoExtend::getFinprodMarketId));
        }
        //投资管理费率 处理
        List<ChbPrdBaseinfoExtend> prodInvestFeeRateList = chbPrdBaseinfoExtemdMapper.selectProdInvestFeeRate(lastUpdatetime);
        Map<String, List<ChbPrdBaseinfoExtend>> prodInvestFeeRateListMap  = new HashMap<String, List<ChbPrdBaseinfoExtend>>();
        if (!StringUtil.isEmptyList(prodInvestFeeRateList)){
        	prodInvestFeeRateListMap = prodInvestFeeRateList.stream().collect(Collectors.groupingBy(ChbPrdBaseinfoExtend::getFinprodMarketId));
        }
		logger.debug("产品要素查询结束--------------"+new Date());
		for (ChbPrdBaseinfo model : prodinfos) {
            // 映射源数据和中债所需数据
            if (!StringUtil.isEmptyList(openCycleMappingList)){
                if (openCycleMappingMap.get(model.getOpencycleunitp()) != null){
                    model.setOpencycleunitp(openCycleMappingMap.get(model.getOpencycleunitp()).getObjdatatypeid());
                }
            }
			if ("02".equals(model.getOpentypep())) {// 高频开放设为D
				model.setOpencycleunitp("D");
			}
			// 中债对募集方式进行映射
			if("1".equals(model.getRaiseType())) {
				model.setRaiseType("01");
			}else if("2".equals(model.getRaiseType())) {
				model.setRaiseType("02");
			}
			// 中债对是否现金管理类进行映射
			if("Y".equals(model.getCashManaCate())) {
				model.setCashManaCate("01");
			}else if("N".equals(model.getCashManaCate())) {
				model.setCashManaCate("02");
			}
			//业绩比较基准 及 投资管理费率 处理赋值
			setProdYieldAndInvestFeeRate(prodYieldMap, prodInvestFeeRateListMap, model);
			//处理提前终止到期日修改
			setRealMdate(prodInfoInterfService,model);
			model.setProduuid(IDGenerator.generate(36));
			model.setCreateUser(CommonUtil.getUserName());
			model.setUpdateUser(CommonUtil.getUserName());
			model.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
			if(null==model.getOrgCode()){
				model.setOrgCode(CommonUtil.getOrgCode());
			}
			model.setDeptCode(CommonUtil.getDeptCode());
			//最大更新时间
			if (model.getUpdateTime() != null) {
			    lastUpdatetime = model.getUpdateTime().after(lastUpdatetime) ? model.getUpdateTime()
						: lastUpdatetime;
			}
			prodList.add(model.getProdId());
		}
		// 入库
		insertDataMerge(prodinfos);
//		this.insert2(batchTime, prodinfos);
		// 新的最后更新时间
		if (lastUpdatetime != null) {
			ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.产品要素信息接口.getInterId(), lastUpdatetime);
		}
		// 插入母产品代码
		/*
		 * for(ChbPrdBaseinfo model:prodinfos){
		 * if("F24".equals(model.getFinprodtype2())){ String prodid =
		 * model.getProdId(); for(ChbPrdBaseinfo fmodel:prodinfos){
		 * if("F16".equals(fmodel.getFinprodtype2())&&model.getProdId()==prodid)
		 * { model.setFprodId(fmodel.getProdId()); } } } }
		 */
		return prodList;
	}

	/**   
	* @Function: ProdInfoInterfService.java
	* @Description: 产品要素接入时，对 业绩比较基准 及 投资管理费率 进行处理赋值
	*
	* @param: prodYieldMap 业绩比较基准map
	* @param: prodInvestFeeRateListMap 投资管理费率map
	* @param: model 产品要素model
	*
	* @author: 王冠/wangguan
	* @date: 2019年8月8日 下午6:03:00 
	*/
	public void setProdYieldAndInvestFeeRate(Map<String, List<ChbPrdBaseinfoExtend>> prodYieldMap,
			Map<String, List<ChbPrdBaseinfoExtend>> prodInvestFeeRateListMap, ChbPrdBaseinfo model) {
		// 业绩比较基准 赋值
		if (prodYieldMap.containsKey(model.getProdId())) {
			List<ChbPrdBaseinfoExtend> prodYieldList = prodYieldMap.get(model.getProdId());
			if (!prodYieldList.isEmpty()) {
				// 对产品集合进行排序，找到一个固定排序的产品
				prodYieldList.sort(new Comparator<ChbPrdBaseinfoExtend>() {
					@Override
					public int compare(ChbPrdBaseinfoExtend prod1, ChbPrdBaseinfoExtend prod2) {
						return prod2.getFinprodid().compareTo(prod1.getFinprodid());
					}
				});
				ChbPrdBaseinfoExtend prodYiedInfo = prodYieldList.get(0);
				if ("01".equals(prodYiedInfo.getRateType())) {//固定利率
					// 对 业绩比较基准 进行赋值，乘以了100
					model.setYield(prodYiedInfo.getRate().multiply(new BigDecimal(100)));
				} else if ("02".equals(prodYiedInfo.getRateType())) {//浮动利率
					StringBuilder sBuilder = new StringBuilder();
					// 浮动利率 不写 业绩比较基准，而在remark中进行备注，如：业绩比较基准=个人活期*1-0.02%
					// 以下就是为了找到相关数据进行拼接，写入remark
					sBuilder.append(model.getRemark() == null ? "" : model.getRemark().toString()+";");
					sBuilder.append("业绩比较基准=")
							.append(SysDataUtil.getNameFromDataDict(DictCode.利率行情, prodYiedInfo.getIndexId()))
							.append("*").append(prodYiedInfo.getCoefficient());
					if (prodYiedInfo.getSpreadRate() != null) {
						sBuilder.append("+").append(prodYiedInfo.getSpreadRate().multiply(new BigDecimal(100)))
								.append("%");
					}
					model.setRemark(sBuilder.toString());
				}
			}
		}
		// 投资管理费率 赋值
		if (prodInvestFeeRateListMap.containsKey(model.getProdId())) {
			List<ChbPrdBaseinfoExtend> prodInvestFeeRatList = prodInvestFeeRateListMap.get(model.getProdId());
			if (!prodInvestFeeRatList.isEmpty()) {
				// 对产品费率集合按照费率进行倒序排序
				prodInvestFeeRatList.sort(new Comparator<ChbPrdBaseinfoExtend>() {
					@Override
					public int compare(ChbPrdBaseinfoExtend prod1, ChbPrdBaseinfoExtend prod2) {
						return prod2.getYield().compareTo(prod1.getYield());
					}
				});
				// 取最大费率写入 投资管理费率
				model.setInvestFeeRate(prodInvestFeeRatList.get(0).getYield());
			} else {
				model.setInvestFeeRate(BigDecimal.ZERO);
			}
		} else {
			model.setInvestFeeRate(BigDecimal.ZERO);
		}
	}
	private void setRealMdate(ProdInfoInterfService prodInfoInterfService,ChbPrdBaseinfo model){
		//查询上游到期日调整
		List<PrdMdateAdjust> adjusts=prodInfoInterfService.selectProdMdateAdjust(model.getProdId());
		PrdMdateAdjust last=null;
		for(PrdMdateAdjust adjust:adjusts){
			if(adjust.getMdate().compareTo(model.getMdate())==0){
				if(!"01".equals(adjust.getAdjustType())){
					break;
				}else{
					if(adjusts.size()==1){
						model.setMdate(adjust.getMdateUnadjust());
					}
				}
				last=adjust;
			}
			if(last!=null){
				if(adjust.getMdate().compareTo(last.getMdateUnadjust())==0){
					if(!"01".equals(adjust.getAdjustType())){
						model.setMdate(adjust.getMdate());
						break;
					}else{
						last=adjust;
					}
				}
			}
		}
	}
	/**
	 * @Description 批量入库，先删再插
	 * @param batchTime
	 * @param prodinfos
	 * @version 1.0 2019年3月5日
	 */
	public void insert(Date batchTime,List<ChbPrdBaseinfo> prodinfos)   {
		//插入中债库report_chb  CHB_PRD_BASEINFO 先删后插
//		int singalCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"500" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		int singalCount = reportInsertCount;
		int totalCount = prodinfos.size();
    	if(totalCount>0){
    		logger.debug("删除开始--------------"+new Date());
    		int batchCount = 100;//每批commit的个数
            int batchLastIndex = batchCount;//每批最后一个的下标
            for(int index = 0; index < prodinfos.size();){
                if(batchLastIndex > prodinfos.size()-1){
                    batchLastIndex = prodinfos.size();
                    chbPrdBaseinfoInterfMapper.deleteByProdid(prodinfos.subList(index, batchLastIndex));
//                    System.out.println("index:"+index+"     batchLastIndex:"+batchLastIndex+"list"+prodinfos.subList(index, batchLastIndex).size());
                    break;//数据插入完毕,退出循环
                     
                }else{
                	chbPrdBaseinfoInterfMapper.deleteByProdid(prodinfos.subList(index, batchLastIndex));
//                    System.out.println("index:"+index+"     batchLastIndex:"+batchLastIndex +"list"+prodinfos.subList(index, batchLastIndex).size());
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + batchCount;                     
                }
            }
            logger.debug("删除结束--------------"+new Date());
			if(totalCount<=singalCount){
//				chbPrdBaseinfoInterfMapper.deleteByProdid(prodinfos);
				chbPrdBaseinfoInterfMapper.insert(prodinfos);
			}
			else{
				for(int i = 0; i < totalCount/singalCount ; i++){
//					chbPrdBaseinfoInterfMapper.deleteByProdid(prodinfos.subList(0, singalCount));
					chbPrdBaseinfoInterfMapper.insert(prodinfos.subList(0, singalCount));
					
					prodinfos.removeAll(prodinfos.subList(0, singalCount));
				}
                if(CollectionUtils.isNotEmpty(prodinfos)){
//                	chbPrdBaseinfoInterfMapper.deleteByProdid(prodinfos);
                	chbPrdBaseinfoInterfMapper.insert(prodinfos);
                }
			}
		}
    	                      
	}

	public void insert2(Date batchTime, List<ChbPrdBaseinfo> prodinfos) {
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			int batchCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
			int batchLastIndex = batchCount;// 每批最后一个的下标
			logger.debug("产品要素删除开始--------------" + new Date());
			for (int index = 0; index < prodinfos.size();) {
				if (batchLastIndex > prodinfos.size() - 1) {
					batchLastIndex = prodinfos.size();
					chbPrdBaseinfoInterfMapper.deleteByProdid(prodinfos.subList(index, batchLastIndex));
					break;// 数据删除完毕,退出循环
				} else {
					chbPrdBaseinfoInterfMapper.deleteByProdid(prodinfos.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + batchCount;
				}
			}
			logger.debug("产品要素删除结束--------------" + new Date());
			int count = 0;
			logger.debug("产品要素插入开始--------------" + new Date());
			ChbPrdBaseinfoInterfMapper batchMapper = session.getMapper(ChbPrdBaseinfoInterfMapper.class);
			for (ChbPrdBaseinfo prod : prodinfos) {
				batchMapper.insertBymodel(prod);
				count++;
				if (count % 1000 == 0) {
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
					logger.debug("产品要素已插入条数-----------" + count + new Date());
				}
			}
			session.commit();
			// 清理缓存，防止溢出
			session.clearCache();
		} 
//		catch (Exception e) {
//			session.rollback();
//			throw e;
//		} 
		finally {
			session.close();
		}
		logger.debug("产品要素插入结束--------------" + new Date());
	}
	
	private void insertDataMerge(List<ChbPrdBaseinfo> assetInfoList) {
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			int count = 0;
			logger.debug("产品要素插入开始--------------" + new Date());
			ChbPrdBaseinfoInterfMapper batchMapper = session.getMapper(ChbPrdBaseinfoInterfMapper.class);
			int batchCount = reportInsertLongCount == 0 ? 50 : reportInsertLongCount;// 每批commit的个数
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < assetInfoList.size();) {
				if (batchLastIndex > assetInfoList.size() - 1) {
					batchLastIndex = assetInfoList.size();
					batchMapper.mergeInfoList(assetInfoList.subList(index, batchLastIndex));
					break;// 数据插入完毕,退出循环
				} else {
					batchMapper.mergeInfoList(assetInfoList.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + batchCount;
				}
				count++;
				// 每20次提交
				if (count % 20 == 0) {
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
				}
			}
			session.commit();
			// 清理缓存，防止溢出
			session.clearCache();
		} finally {
			session.close();
		}
		logger.debug("产品要素插入结束--------------" + new Date());
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<Date> isUpdated(Date batchTime){
    	return prodInfoMapper.isUpdated(batchTime);
    }
	 /**
     * 功能:查询接入日当天的产品要素
     * @since 1.6.0
     * @version 1.6.0
     * @author：luminmin 2019年2日26
     * @param interfDate 跑批日
     * @return 
     * List<ChbAssetInfo>
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    // 切换数据源
    @DataSource(DataSourceType.INTERF)
    public List<ChbPrdBaseinfo> selectProdInfo(Date batchTime){
    	HashMap<String,Object> paraMap = new HashMap<String,Object>();
    	//add by leipeng 20200628 多法人模式改造
    	if(CommonUtil.isBAmodel()){
    		paraMap.put("model_type", "01");
    	}
		paraMap.put("batchTime", batchTime);
		List<ChbPrdBaseinfo> list = new ArrayList<ChbPrdBaseinfo>();
		template.select("com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ProdInfoMapper.selectProdInfo",paraMap,
                new ResultHandler() {
			@Override
			public void handleResult(ResultContext resultContext) {
				list.add((ChbPrdBaseinfo) resultContext.getResultObject());
			}
        });
    	return list;
    }
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    // 切换数据源
    @DataSource(DataSourceType.INTERF)
    public List<PrdMdateAdjust> selectProdMdateAdjust(String  prodid){
		List<PrdMdateAdjust> list = new ArrayList<PrdMdateAdjust>();
		list=prodInfoMapper.selectMdateAdjustByProdid(prodid);
    	return list;
    }
    /**
     * 功能:查询对应的计息基础
     * @since 1.6.0
     * @version 1.6.0
     * @author：luminmin 2019年2日26
     * @param prodid
     * @return 
     * List<ChbAssetInfo>
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    // 切换数据源
    @DataSource(DataSourceType.INTERF)
    public String selectBasisByprodid(String finprodid) {
    	String basis = prodInfoMapper.selectBasisByprodid(finprodid);
    	if(basis==null){
    		basis = DictCodeList.计息基础_A_365.getValue();
    	}
    	return basis;
	}
}
