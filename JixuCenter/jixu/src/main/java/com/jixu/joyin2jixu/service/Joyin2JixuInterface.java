package com.jixu.joyin2jixu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.ResultInterface;
import com.jixu.common.types.ActiveType;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkInfoDao;
import com.jixu.dao.intf.BrokerDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.SecAcctDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.joyin2jixu.dto.SecurityInfoDto;
import com.jixu.msg.dto.JJBkCashAcctDto;
import com.jixu.msg.dto.JJPortfolioDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInfo;
import com.orm.obj.Broker;
import com.orm.obj.CoaaGroup;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Province;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;

/**
 * 该类为joyin调用接口，新建产品等信息
 * @author 桃汰猪
 *
 */

@Component
public class Joyin2JixuInterface {
	
	@Autowired
	private BrokerDao brokerDao;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Autowired
	private SecAcctDao secAcctDao;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private BkInfoDao bkInfoDao;
	
	@Resource
	private CommFuncService commService;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	protected TranBasicDao tranBasicDao;
	
	
	/**
	 * 创建产品
	 * @param dtoList
	 * @return
	 */
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultInterface createPortfolios(List<JJPortfolioDto> dtoList) {
		ResultInterface resultInterface=new ResultInterface();
		int successnum=0;
		int failnum=0;
		for (JJPortfolioDto jjPortfolioDto : dtoList) {
			//新建产品前   先删除有关该投资组合的表中的数据
			//portfolioHdrDao.deletePortfolioMap(jjPortfolioDto.getBookSet_id());
			ResultContainer container=createPortfolio(jjPortfolioDto);
			if(container.isCorrectFlag()){
				successnum++;
			}else{
				failnum++;
				resultInterface.setCorrectFlag(false);
				resultInterface.getErrorMsgs().add(container.getErrorMsg());
			}
				
		}
		resultInterface.setInNums(dtoList.size());
		resultInterface.setSuccessNums(successnum);
		resultInterface.setFailedNums(failnum);
		return resultInterface;
	}
	
	
	/**
	 * 新建产品
	 * @param dto
	 * @return
	 */
	public ResultContainer createPortfolio(JJPortfolioDto dto) {
		ResultContainer container=new ResultContainer();
		PortfolioHdr portfolio=new PortfolioHdr(dto.getBookSet_id());
		SecAcct secAcct=new SecAcct();
		secAcct.setAcctCode("SECACCT "+dto.getBookSet_id());
		secAcct.setAcctDesc("SECACCT "+dto.getBookSet_id());
		secAcct.setPortfolio(dto.getBookSet_id());
		secAcct.setGlGrop(new CoaaGroup(1));
		Broker broker=brokerDao.loadDefaultBroker();
		if(broker!=null){
			secAcct.setBrokerCode(broker.getBrokerID());
		}else{
			container.setCorrectFlag(false);
			container.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,"券商"));
			return container;
		}
		SecAcct oldSecAcct=secAcctDao.loadSecAcctByAcctCode(secAcct.getAcctCode());
		if(oldSecAcct!=null){
			BeanUtils.copyProperties(secAcct, oldSecAcct, "acctID");
			secAcctDao.updateEntity(oldSecAcct);
			portfolio.setDefaultSecAcct(oldSecAcct.getAcctID());
		}else{
			secAcctDao.saveEntity(secAcct);
			if(secAcct!=null && !secAcct.getAcctID().equals("") && !secAcct.getAcctID().equals("0")){
				portfolio.setDefaultSecAcct(secAcct.getAcctID());
			}else{
				container.setCorrectFlag(false);
				container.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,"证券账户"));
				return container;
			}
		}
		
		
		portfolio.setBookNBDays(0);
		portfolio.setClassid(0);
		
		Date startDate=DateUtil.getLastDay(dto.getStart_date());
		
		portfolio.setInceptionDate(startDate);
		portfolio.setLastActiveDate(new Date());
		portfolio.setLastGLClose(startDate);
		portfolio.setLastTranClose(startDate);
		portfolio.setCurID(dto.getStandard_ccy());
		portfolio.setLotSelection(1);
		portfolio.setNavPrecision(3);
		portfolio.setNextGLClose(startDate);
		portfolio.setNextTranClose(startDate);
		portfolio.setNextYealClose(startDate);
		portfolio.setPortName(dto.getBookset_fullname());
		portfolio.setPortType(0);
		portfolio.setShortCode(dto.getBookSet_id());
		portfolio.setShortName(dto.getBookset_name());
		portfolio.setSinglelot("Y");
		portfolio.setTradeSettleLedger(0);
		portfolio.setWkndValuationClose(0);
		portfolio.setCoaahdr(dto.getBookset_id_tmpl());
		portfolio.setHolidaySD(dto.getCalendar_id());
		portfolio.setManagerGroup(1);
		portfolio.setPortfolioClient(1);
		portfolio.setProvince(new Province(1));
		
		PortfolioHdr oldPortfolio=portfolioHdrDao.loadEntity(PortfolioHdr.class,dto.getBookSet_id());
		if(oldPortfolio!=null){
			BeanUtils.copyProperties(portfolio, oldPortfolio, "id");
			portfolioHdrDao.updateEntity(oldPortfolio);
		}else{
			portfolioHdrDao.saveEntity(portfolio);
		}
		return container;
	}
	
	
	/**
	 * 更新银行现金账户(不修改银行现金账户的内容   只是新增现金账户)
	 * @param dtoList
	 * @return
	 */
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultInterface updateBkCashAccts(List<JJBkCashAcctDto> dtoList) {
		ResultInterface resultInterface=new ResultInterface();
		resultInterface.setCorrectFlag(true);
		ResultContainer container=new ResultContainer();
		//投管传来的要更新的银行账户信息
		HashMap<String, JJBkCashAcctDto> bkHashMap = new HashMap<String, JJBkCashAcctDto>();
		for (JJBkCashAcctDto bkdto : dtoList) {
			bkHashMap.put(bkdto.getBookset_id()+"_"+bkdto.getAcct_no(),bkdto);
		}
		
		List<BkCashAcct> bkList=null;
		//更新的银行账户个数大于0
		if(dtoList.size()>0){
			//根据账套信息查询库里该组合下现有的银行账户
			bkList=bkCashAcctDao.getBankCashAcctByPortNum(dtoList.get(0).getBookset_id());
			for (BkCashAcct bkAcct : bkList) {
				//如果数据库中的银行账户信息在传来的dto中查询不到  就返回错误信息
				if(bkHashMap.get(bkAcct.getPortfolio().getId()+"_"+bkAcct.getExternalacctnumber())==null){
					//修改账户id时 查询改账户是否有交易存在  如果有则报错 如果没有则删除
					List<TranBasic> tbList = tranBasicDao.loadTranBasicByBankAcct(bkAcct.getPortfolio().getId(), bkAcct.getId());
					if(tbList.size()>0){
						container.setCorrectFlag(false);
						container.setErrorMsg("该银行账户有历史交易存在: "+bkAcct.getExternalacctnumber());
						break;
					}else{
						bkCashAcctDao.deleteEntity(bkAcct);
					}
				}else{
					//如果库中的银行账户信息在传来的dto中可以查到   就移除掉   最后只留下此次更新增加的银行账户 
					bkHashMap.remove(bkAcct.getPortfolio().getId()+"_"+bkAcct.getExternalacctnumber());
				}
			}
		}
		
		//如果校验通过
		if(!container.isCorrectFlag()){
			resultInterface.setCorrectFlag(false);
			resultInterface.getErrorMsgs().add(container.getErrorMsg());
		}else{
			//把更新需要增加的银行账户赋给dtoList
			dtoList=new ArrayList<JJBkCashAcctDto>( bkHashMap.values());
			int successnum=0;
			int failnum=0;
			for (JJBkCashAcctDto jjBkCashAcctDto : dtoList) {
				container=createBkCashAcct(jjBkCashAcctDto);
				if(container.isCorrectFlag()){
					successnum++;
				}else{
					failnum++;
					resultInterface.setCorrectFlag(false);
					resultInterface.getErrorMsgs().add(container.getErrorMsg());
				}
					
			}
			resultInterface.setInNums(dtoList.size());
			resultInterface.setSuccessNums(successnum);
			resultInterface.setFailedNums(failnum);
		}
		return resultInterface;
	}
	
	
	/**
	 * 新建银行现金账户
	 * @param dto
	 * @return
	 */
	public ResultContainer createBkCashAcct(JJBkCashAcctDto dto) {
		ResultContainer container=new ResultContainer();
		Boolean bkexist=bkCashAcctDao.checkBkCashAcctNameExist(dto.getBookset_id(), dto.getAcct_no());
		if(bkexist){
			container.setCorrectFlag(false);
			container.setErrorMsg("银行账户已存在"+dto.getAcct_no());
			return container;
		}
		List<SecAcct> secacctList=secAcctDao.loadSecAcctsByPortnum(dto.getBookset_id());
		if(secacctList==null || secacctList.size()==0) {
			container.setCorrectFlag(false);
			container.setErrorMsg("未查到对应的证券账户");
			return container;
		}
		
		SecAcct secacct=secacctList.get(0);
		
		BkCashAcct bka=new BkCashAcct();
		Date sysdate=new Date();
		bka.setBankdesc(dto.getAcct_name());
		
		//读取bkcashacct.properties配置文件中的银行现金科目配置
		String cashglcode=SecurityCoreFactory.getBkcashacctProperties().getProperty(dto.getAcc_type()+"_"+dto.getCcy());
		bka.setCashglcode(cashglcode);
		//获取汇兑损益科目
		String hdsy=SecurityCoreFactory.getBkcashacctProperties().getProperty("HDSY");
		bka.setCashrealfxgnls(hdsy);
		bka.setCashunrealfxgnls(hdsy);
		bka.setExternalacctnumber(dto.getAcct_no());
		bka.setIntCshflwMasterRec(0);
		bka.setLastBalDate(sysdate);
		bka.setFxcurrency(dto.getCcy());
		bka.setLastclose(sysdate);
		bka.setNextclose(sysdate);
		bka.setStatus(ActiveType.active);
		bka.setSecAcctCode(secacct.getAcctCode());
		if(dto.getBank_dep()!=null && !dto.getBank_dep().equals("")){
			BkInfo bk=bkInfoDao.loadloadBkInfoIDByName(dto.getBank_dep());
			if(bk==null){
				bk=new BkInfo();
				bk.setBankID(dto.getBank_dep());
				bk.setBankCode(dto.getBank_dep());
				bk.setBankDesc(dto.getBank_dep());
				bkCashAcctDao.saveEntity(bk);
			}
			bka.setBkinfo(bk);
		}
		bka.setPortfolio(new PortfolioHdr(dto.getBookset_id()));		
		bkCashAcctDao.saveEntity(bka);
		
		//设置该证券账户的默认现金账户为本
		secacct.setDefaultCashAcct(bka.getId());
		secAcctDao.updateEntity(secacct);
		
		return container;
	}
	
	
	/**
	 * @param jsonMsg
	 * @return	外部系统直接调用jixu消息引擎
	 */
	@Transactional(value = "jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer executeJixuMessage(String jsonMsg) {
		//ResultContainer
		//isCorrectFlag() 执行是否成功
		//getErrorMsg()	错误消息
		return commService.executeMsg(jsonMsg);
	}
	
	public ResultContainer executeJixuMessageNoTx(String jsonMsg) {
		//ResultContainer
		//isCorrectFlag() 执行是否成功
		//getErrorMsg()	错误消息
		return commService.executeMsgNoTx(jsonMsg);
	}
	
	private String mapJixuDict(String jsonMsg) {
		HashMap<String, HashMap<String, Object>> jixujoyinmap=commService.mapOutDataDict();
		HashMap<String, Object> jsonMap=JSONObject.parseObject(jsonMsg, HashMap.class);
		String secacct_key="secAcct";
		String secacct_key2="secAcct2";
		
		if(jsonMap.containsKey(secacct_key)) {
			jsonMap.put(secacct_key, jixujoyinmap.get(JixuDictCode.JX_JOYIN_MAP_SEC_ACCT).get(jsonMap.get(secacct_key)));
		}
		
		if(jsonMap.containsKey(secacct_key2)) {
			jsonMap.put(secacct_key2, jixujoyinmap.get(JixuDictCode.JX_JOYIN_MAP_SEC_ACCT).get(jsonMap.get(secacct_key2)));
		}
		
		return JSONObject.toJSONString(jsonMap);
	}
	
	/**
	 *  <p>根据资产类型获取资产集合</p>
	 * @param assetType	资产类型，如股票: F10
	 * @return
	 */
	public List<SelectOptionDto> getSecurityDtoByAssetType(String assetType){
		List<SecurityInfoDto> secList=issueInfoDao.loadSecurityByAssetType(assetType);
		List<SelectOptionDto> dtoList=new ArrayList<SelectOptionDto>();
		
		for(SecurityInfoDto sec:secList) {
			SelectOptionDto dto=new SelectOptionDto();
			dto.setId(sec.getSecurityid());
			dto.setText(sec.getSecurityid());
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	/**
	 * <p>根据资产类型获取资产集合</p>
	 * @param assetTypes		F10:股票 F11:即期  F12: 远期  F13: 掉期  F14:利率互换
	 * @return
	 */
	public List<SelectOptionDto> getSecurityDtoByAssetTypes(String[] assetTypes){
		List<SecurityInfoDto> secList=issueInfoDao.loadSecurityByAssetType(assetTypes);
		List<SelectOptionDto> dtoList=new ArrayList<SelectOptionDto>();
		
		for(SecurityInfoDto sec:secList) {
			SelectOptionDto dto=new SelectOptionDto();
			dto.setId(sec.getSecurityid());
			dto.setText(sec.getSecurityid());
			//dto.setText(sec.getSecurityname());
			dtoList.add(dto);
		}
		return dtoList;
	}
}
