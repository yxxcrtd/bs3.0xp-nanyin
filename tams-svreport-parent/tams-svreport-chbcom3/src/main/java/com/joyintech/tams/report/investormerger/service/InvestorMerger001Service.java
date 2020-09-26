package com.joyintech.tams.report.investormerger.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbEquSub;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSub;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.investormerger.dao.ChbEquHoldCheckDtoMapper;
import com.joyintech.tams.report.investormerger.dao.ChbInvestholdInfoCheckDtoMapper;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.investormerger.dto.ChbInvestholdInfoCheckDto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * @Description 识别标识合并service
 * @author jiangxiao
 * @version 1.0 2019年3月19日
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestorMerger001Service extends CommonExportService<ChbEquHoldCheckDto> {

	@Autowired
	private ChbEquHoldCheckDtoMapper chbEquHoldCheckDtoMapper;
	@Autowired
	private ChbInvestholdInfoCheckDtoMapper chbInvestholdInfoCheckDtoMapper;
    @Autowired
    private InvestorsHoldInfo001Mapper investorsHoldInfo001Mapper;
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(InvestorMerger001Service.class);

	/**
	 * @Description 前台分页查询
	 * @param pageParam
	 * @return Page<ChbEquHoldCheckDto>
	 */
	public Page<ChbEquHoldCheckDto> selectForPage(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		Page<ChbEquHoldCheckDto> page;
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null && pageParam.get(GlobalConstant.PARAM_LENGTH) != null){
        	int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
    	    int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
    		page = queryForPage(
    				pagenum,legth,()->chbEquHoldCheckDtoMapper.selectAllData(pageParam));
        } else {
            page = queryForPage(ChbEquHoldCheckDtoMapper.class,"selectAllData",pageParam);
        }
		
		return page;
	}
	
	/**
	 * @Description 前台界面查询参数处理
	 * @param pageParam
	 * @return void
	 */
	private void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<ChbEquHoldCheckDto> investList = JsonUtils.json2List(keyList,
						ChbEquHoldCheckDto.class);
				pageParam.put("investorList", investList);
			} catch (IOException e) {
//				e.printStackTrace();
	            logger.error(e.getMessage(),e);
			}
		}else{
			//处理状态
			String operateStatus = (String)pageParam.get("operateStatus");
			if (StringUtil.isNotBlank(operateStatus)) {
				pageParam.put("operateStatusList", StringUtil.splitToList(GlobalConstant.COMMA, operateStatus));
			}
			//个人证件类别
			String personalIdentifyType = (String)pageParam.get("personalIdentifyType");
			if (StringUtil.isNotBlank(personalIdentifyType)) {
				pageParam.put("personalIdentifyTypeList", StringUtil.splitToList(GlobalConstant.COMMA, personalIdentifyType));
			}
			//机构证件类别
			String institutionIdentifyType = (String)pageParam.get("institutionIdentifyType");
			if (StringUtil.isNotBlank(institutionIdentifyType)) {
				pageParam.put("institutionIdentifyTypeList", StringUtil.splitToList(GlobalConstant.COMMA, institutionIdentifyType));
			}
		}
	}
	
	/**
	 * @Description 手工合并识别标识
	 * @param pageParam （三要素+合并后的识别标识）
	 * @return void
	 * @version 1.0 2019年3月21日
	 */
	public void manualMerger(Map<String, Object> pageParam){
		//查询所有要合并的数据
		ChbEquHoldCheckDto record = new ChbEquHoldCheckDto();
//		//投资者名称
//		record.setHolderName((String)pageParam.get("chooseholderName"));
//		//个人证件类型
//		record.setPersonalIdentifyType((String)pageParam.get("choosepersonalIdentifyType"));
//		//机构证件类型
//		record.setInstitutionIdentifyType((String)pageParam.get("chooseinstitutionIdentifyType"));
//		//其他证件名称
//		record.setOtherIdentifyName((String)pageParam.get("chooseotherIdentifyName"));
//		//证件号码
//		record.setIdentifyNumber((String)pageParam.get("chooseidentifyNumber"));
//		//识别标识
//		record.setIndentifyMark((String)pageParam.get("chooseindentifyMark"));
//		if (pageParam.containsKey("checkkeyList")) {
//			String checkkeyList = pageParam.get("checkkeyList").toString();
//			try {
//				List<ChbEquHoldCheckDto> investList = JsonUtils.json2List(checkkeyList,
//						ChbEquHoldCheckDto.class);
//				if(!CollectionUtils.isEmpty(investList)){
//					record = investList.get(0);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		record = chbEquHoldCheckDtoMapper.selectByPrimarykey((String)pageParam.get("indentifyMark"));
		//查询投资者身份信息
		List<ChbEquHoldCheckDto> infolist = chbEquHoldCheckDtoMapper.selectBythreeInfo(record);
		if(!CollectionUtils.isEmpty(infolist)){
			//查询投资者持有信息
			List<ChbInvestholdInfoCheckDto> holdlist = chbInvestholdInfoCheckDtoMapper.selectByIndentifyMark(infolist);
			for(ChbEquHoldCheckDto infodto : infolist){
				List<ChbInvestholdInfoCheckDto> investholdlist = new ArrayList<ChbInvestholdInfoCheckDto>();
				for(ChbInvestholdInfoCheckDto holddto : holdlist){
					if(infodto.getIndentifyMark().equals(holddto.getIndentifyMark())){
						investholdlist.add(holddto);
					}
				}
				infodto.setHoldlist(investholdlist);
			}
			//合并投资者身份信息和投资者持有信息
			mergerInfoData(infolist,record.getIndentifyMark());
			//根据三要素修改历史数据表的身份信息和持有信息
			//合并后识别标识
			record.setMergerindentifymark(record.getIndentifyMark());
			//合并状态
			record.setOperatestatus(DictCodeListExt2.处理状态_中债_已合并.getValue());
			//合并人
			record.setUpdateUser(SecurityUtil.getLoginUser() == null ? "system" : SecurityUtil.getLoginUser().getUserName());
			chbEquHoldCheckDtoMapper.updatechbEquHoldCheck(record);
			chbInvestholdInfoCheckDtoMapper.updateHoldInfoCheck(record);
			//根据合并后识别标识更新登记表数据
			for(ChbEquHoldCheckDto info : infolist){
				if(info.getIndentifyMark()!=null&&!info.getIndentifyMark().equals(record.getIndentifyMark())){
					//更新投资者身份信息登记表
//					chbEquHoldCheckDtoMapper.updateChbEquSub(info.getMergerindentifymark(),record.getIndentifyMark());
					//更新投资者持有信息登记表
//					chbInvestholdInfoCheckDtoMapper.updateChbInvestholdSub(info.getMergerindentifymark(),record.getIndentifyMark());
					//更新产品头寸表
//					chbEquHoldCheckDtoMapper.updateChbPrdPosition(info.getIndentifyMark(),record.getIndentifyMark());
					//更新产品交易表
//					chbEquHoldCheckDtoMapper.updateChbPrdDeal(info.getIndentifyMark(),record.getIndentifyMark());
//					break;
				}
			}
		}
	}
	
	/**
	 * @Description 合并投资者信息和投资者持有信息
	 * @param infolist
	 * @return List<ChbInvestholdInfoCheckDto>
	 * @version 1.0 2019年3月19日
	 */
	public List<ChbInvestholdInfoCheckDto> mergerInfoData(List<ChbEquHoldCheckDto> infolist,String indentifyMark){
		//合并后的持有信息
		List<ChbInvestholdInfoCheckDto> afterholdlist = new ArrayList<ChbInvestholdInfoCheckDto>();
		Map<String,List<ChbEquHoldCheckDto>> infoMap = new HashMap<String,List<ChbEquHoldCheckDto>>();
		if(CollectionUtils.isEmpty(infolist)){
			return afterholdlist;
		}
		for(ChbEquHoldCheckDto infodto:infolist){
			//三要素相等的持有信息合并成一条
			StringBuffer key = new StringBuffer();
			//三要素：证件类型，证件号码，投资者名称
			key.append(infodto.getHolderName()).append(
					infodto.getPersonalIdentifyType()==null?"":infodto.getPersonalIdentifyType()).append(GlobalConstant.UNDER_LINE).append(
					infodto.getInstitutionIdentifyType()==null?"":infodto.getInstitutionIdentifyType()).append(
					infodto.getOtherIdentifyName()==null?"":infodto.getOtherIdentifyName()).append(
					infodto.getIdentifyNumber()==null?"":infodto.getIdentifyNumber());
			if(infoMap.get(key.toString())!=null){
				infoMap.get(key.toString()).add(infodto);
			}else{
				List<ChbEquHoldCheckDto> infolisttemp = new ArrayList<ChbEquHoldCheckDto>();
				infolisttemp.add(infodto);
				infoMap.put(key.toString(), infolisttemp);
			}
		}
		Iterator<Map.Entry<String, List<ChbEquHoldCheckDto>>> entries = infoMap.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, List<ChbEquHoldCheckDto>> entry = entries.next();
			List<ChbEquHoldCheckDto> dtolist = entry.getValue();
			//获取该投资者的所有持有信息
			List<ChbInvestholdInfoCheckDto> investorholdlist = new ArrayList<>();
			for(ChbEquHoldCheckDto dto : dtolist){
				if(!CollectionUtils.isEmpty(dto.getHoldlist())){
					investorholdlist.addAll(dto.getHoldlist());
				}
				if(indentifyMark.equals(dto.getIndentifyMark())){
					//入目标库(先删再插)
					//删除持有信息登记数据、删除身份信息登记数据
					chbInvestholdInfoCheckDtoMapper.deleteChbInvestholdInfoSub(dto);
					chbEquHoldCheckDtoMapper.deleteChbEquSub(dto);
					//删除持有信息、删除身份信息
					chbInvestholdInfoCheckDtoMapper.deleteChbInvestholdInfo(dto);
					chbEquHoldCheckDtoMapper.deleteChbEquHold(dto);
					//身份信息入目标库
//					部门代码
					dto.setDeptCode(CommonUtil.getDeptCode());
//					机构代码
					dto.setOrgCode(CommonUtil.getOrgCode());
					chbEquHoldCheckDtoMapper.insertToChbEquHold(dto);
					//身份信息入登记库
					ChbEquSub infosub = new ChbEquSub();
					infosub = changeToChbEquSub(dto);
					chbEquHoldCheckDtoMapper.insertChbEquSub(infosub);
				}
			}
			//合并持有信息
			Map<String,ChbInvestholdInfoCheckDto> holddealMap = mergerHoldData(investorholdlist);
			
			//持有信息和持有信息登记数据入库
			Iterator<Map.Entry<String, ChbInvestholdInfoCheckDto>> holdentries = holddealMap.entrySet().iterator();
			while(holdentries.hasNext()) {
				Map.Entry<String, ChbInvestholdInfoCheckDto> holdentry = holdentries.next();
				ChbInvestholdInfoCheckDto holdmodel = holdentry.getValue();
				holdmodel.setIndentifyMark(indentifyMark);
//				部门代码
				holdmodel.setDeptCode(CommonUtil.getDeptCode());
//				机构代码
				holdmodel.setOrgCode(CommonUtil.getOrgCode());
				chbInvestholdInfoCheckDtoMapper.insertToChbInvestholdInfo(holdmodel);
				ChbInvestholdInfoSub holdsub = new ChbInvestholdInfoSub();
				holdsub = changeToChbInvestholdInfoSub(holdmodel);
				chbInvestholdInfoCheckDtoMapper.insertChbInvestholdInfoSub(holdsub);
			}
		}
		return afterholdlist;
	}
	
	private ChbEquSub changeToChbEquSub(ChbEquHoldCheckDto chbEquHoldCheckDto) {
		ChbEquSub equSub = new ChbEquSub();
		BeanUtils.copyProperties(chbEquHoldCheckDto,equSub);
//		识别标识
//		equSub.setIndentifyMark(chbEquHoldCheckDto.getIndentifyMark());
////		原识别标识
//		equSub.setOrgIndentifyMark(chbEquHoldCheckDto.getOrgIndentifyMark());
////		登记银行编码
//		equSub.setRegistBankCode(chbEquHoldCheckDto.getRegistBankCode());
////		该权益人是否属于本行
//		equSub.setIsBelongBank(chbEquHoldCheckDto.getIsBelongBank());
////		权益人所属银行名称
//		equSub.setTheBankName(chbEquHoldCheckDto.getTheBankName());
////		权益人所属银行代码
//		equSub.setTheBankCode(chbEquHoldCheckDto.getTheBankCode());
////		权益人境内外标识
//		equSub.setHolderMark(chbEquHoldCheckDto.getHolderMark());
////		权益人所属国家或地区
//		equSub.setHolderRegion(chbEquHoldCheckDto.getHolderRegion());
////		权益人类别
//		equSub.setHolderType(chbEquHoldCheckDto.getHolderType());
////		个人证件类别
//		equSub.setPersonalIdentifyType(chbEquHoldCheckDto.getPersonalIdentifyType());
////		机构证件类别
//		equSub.setInstitutionIdentifyType(chbEquHoldCheckDto.getInstitutionIdentifyType());
////		其他证件名称
//		equSub.setOtherCapAccBank(chbEquHoldCheckDto.getOtherCapAccBank());
//		数据类型
		equSub.setType(DictCodeListExt2.数据类型_中债_新增.getValue());
//		报送状态
		equSub.setSubmitStatus(DictCodeListExt2.报送状态_未报送.getValue());
//		中债报送截止日
//		报送截止日
		Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
		List<Date> dateList = null;
		dateList = ChbDeadlineUtil.getChbEndDate(
                DictCodeListExt2.报表名称_投资者身份信息登记.getValue(), DictCodeListExt2.报送数据状态_新建.getValue(), dateMap, DateUtil.now());
        if (!CollectionUtils.isEmpty(dateList)) {
        	equSub.setChbSubmitDeadline(dateList.get(0));
        	equSub.setSubmitDeadline(dateList.get(1));
        }
//		数据状态
        equSub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
//		报送流水号
        equSub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
//		部门代码
        equSub.setDeptCode(CommonUtil.getDeptCode());
//		机构代码
        equSub.setOrgCode(CommonUtil.getOrgCode());
//		创建人
        equSub.setCreateUser(SecurityUtil.getLoginUser().getUserName());
		return equSub;
	}

	private ChbInvestholdInfoSub changeToChbInvestholdInfoSub(ChbInvestholdInfoCheckDto holdmodel) {
		ChbInvestholdInfoSub holdSub = new ChbInvestholdInfoSub();
		BeanUtils.copyProperties(holdmodel,holdSub);
//		报送截止日
		List<Date> dateList = null;
		Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
		dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_投资者持有信息登记.getValue(), DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
				holdSub.getHoldDate());
		if (!CollectionUtils.isEmpty(dateList)) {
			holdSub.setChbSubmitDeadline(dateList.get(0));
			holdSub.setSubmitDeadline(dateList.get(1));
		}
//		报送状态
		holdSub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
//		数据日期
		holdSub.setStatusDate(DateUtil.now());
//		数据状态
		holdSub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
//		创建人
		holdSub.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//		报送流水号
		holdSub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
//		登记序号
		holdSub.setRegistSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
//		登记银行代码
		holdSub.setRegistBankCode(CommonUtil.getUserBankCode());
//		机构代码
		holdSub.setOrgCode(CommonUtil.getOrgCode());
//		部门代码
		holdSub.setDeptCode(CommonUtil.getDeptCode());
//		创建部门
		holdSub.setCreateDept(holdSub.getDeptCode()!=null?holdSub.getDeptCode():holdSub.getOrgCode());
//		产品代码
		holdSub.setProdid(investorsHoldInfo001Mapper.getProdidByPrdRegistCode(holdmodel.getPrdRegistCode()));
		return holdSub;
	}

	/**
	 * @Description 处理持有信息
	 * @param holdlist
	 * @return Map<String,ChbInvestholdInfoCheckDto> 合并后的持有信息map
	 * @version 1.0 2019年3月21日
	 */
	public Map<String,ChbInvestholdInfoCheckDto> mergerHoldData(List<ChbInvestholdInfoCheckDto> holdlist){
		Map<String,ChbInvestholdInfoCheckDto> holddealMap = new HashMap<String,ChbInvestholdInfoCheckDto>();
		if (!CollectionUtils.isEmpty(holdlist)) {
			for (ChbInvestholdInfoCheckDto holdmodel : holdlist) {
				StringBuffer key = new StringBuffer();
				// 同一投资者持有信息按登记银行代码、产品登记编码、持有日期、币种合并
				key.append(holdmodel.getRegistBankCode())
						.append(holdmodel.getPrdRegistCode() == null ? "" : holdmodel.getPrdRegistCode())
						.append(holdmodel.getHoldDate() == null ? "" : DateUtil.format(holdmodel.getHoldDate()))
						.append(holdmodel.getCcy() == null ? "" : holdmodel.getCcy());
				if (holddealMap.get(key.toString()) != null) {
					// 金额
					holddealMap.get(key.toString())
							.setAmount(holddealMap.get(key.toString()).getAmount().add(holdmodel.getAmount()));
					// 份额
					holddealMap.get(key.toString())
							.setQty(holddealMap.get(key.toString()).getQty().add(holdmodel.getQty()));
					// 折算人民币金额
					holddealMap.get(key.toString())
							.setCnyAmt(holddealMap.get(key.toString()).getCnyAmt().add(holdmodel.getCnyAmt()));
				} else {
					ChbInvestholdInfoCheckDto newhold = new ChbInvestholdInfoCheckDto();
					//复制对象
					BeanUtils.copyProperties(holdmodel, newhold);
					holddealMap.put(key.toString(), holdmodel);
				}
			}
			return holddealMap;
		}else{
			return holddealMap;
		}
	}

	/**
	 * @Description 界面导出查询
	 * @param parameters
	 * @return List<ChbEquHoldCheckDto>
	 * @version 1.0 2019年3月21日
	 */
	public List<ChbEquHoldCheckDto> selectForExport(Map<String, Object> parameters) {
		dealSearchParam(parameters);
		@SuppressWarnings({"unchecked"})
		List<ChbEquHoldCheckDto> dataList = (List<ChbEquHoldCheckDto>)parameters.get("investorList");
		
		List<ChbEquHoldCheckDto> dtoList = new ArrayList<ChbEquHoldCheckDto>();
		this.selectAllDataBatch(dataList, dtoList);
		return dtoList;
	}
	
	/**
	 * @Description 根据条件分批查询
	 * @param dataList
	 * @param dtoList
	 * @version 1.0 2019年3月21日
	 */
	private void selectAllDataBatch(List<ChbEquHoldCheckDto> dataList, List<ChbEquHoldCheckDto> dtoList) {
		if (StringUtil.isEmptyList(dataList)) {
            return;
        }
        int maxDataCountOneTime = 100;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
        	dtoList.addAll(chbEquHoldCheckDtoMapper.selectAllDataByList(dataList));
            return;
        }

        List<ChbEquHoldCheckDto> newDataList = new ArrayList<ChbEquHoldCheckDto>();
        newDataList.addAll(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
        	dtoList.addAll(chbEquHoldCheckDtoMapper.selectAllDataByList(newDataList.subList(0, maxDataCountOneTime)));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (!CollectionUtils.isEmpty(newDataList)) {
        	dtoList.addAll(chbEquHoldCheckDtoMapper.selectAllDataByList(newDataList));
        }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		Map<String, Object> pageParam = exportParam.getSelectParam();
		String pageIds = (String) pageParam.get("pageIds");
		if (StringUtil.isNotEmpty(pageIds)) {
			List<String> pageNames = Arrays.asList(pageIds.split(GlobalConstant.COMMA));
			pageParam.replace("pageIds", pageIds, pageNames);
		} else {
			pageParam.replace("pageIds", pageIds, null);
		}
		// 设置参数
		dealSearchParam(pageParam);
		//无查询参数的情况
		exportParam.config("/com/joyintech/tams/report/investormerger/conf/export_conf.xml")
		.select(() -> chbEquHoldCheckDtoMapper.selectAllData(pageParam));
	}
}
