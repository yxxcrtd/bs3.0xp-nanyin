package com.joyintech.service.trade.primarybiddingmanage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.dao.BidCloseStatusMapper;
import com.joyintech.tams.common.base.entity.BidCloseStatus;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.primarybiddingmanage.dao.PrimaryBiddingManage001Mapper;
import com.joyintech.service.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;

@Service("backPrimaryBiddingManage001Service")
public class PrimaryBiddingManage001Service extends BaseService<PrimaryBiddingManageDto> {
	@Autowired
	private PrimaryBiddingManage001Mapper primaryBiddingManage001Mapper;

	/**
	 * 截标状态mapper
	 */
	@Autowired
	private BidCloseStatusMapper bidCloseStatusMapper;

	public Page<PrimaryBiddingManageDto> selectForPage(int page, int length, Map<String, Object> param) {
		
		Map<String, PrimaryBiddingManageDto> map= new HashMap<String, PrimaryBiddingManageDto>();
		param.put("pinanassettypeyj", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
        param.put("entruststatusyjs", BussinessDictCodeList.委托状态_已接收.getValue());
		param.put("bidstatusyzb", BussinessDictCodeList.中标状态_已中标.getValue());
		param.put("TENDERCONFIRMSTATUS01", BussinessDictCodeList.确认状态_未确认.getValue());
		param.put("TENDERCONFIRMSTATUS02", BussinessDictCodeList.确认状态_已确认.getValue());
		param.put("TENDERCONFIRMSTATUS03", BussinessDictCodeList.确认状态_未中标.getValue());
		param.put("TENDERCONFIRMSTATUS04", BussinessDictCodeList.确认状态_已中标.getValue());

//		if(!StringUtil.isNotEmpty(param.get("finprodId")) && !StringUtil.isNotEmpty(param.get("counterId")) &&
//				!StringUtil.isNotEmpty(param.get("tenderconfirmstatus")) &&
//				!StringUtil.isNotEmpty(param.get("tenderDateBegin")) && !StringUtil.isNotEmpty(param.get("tenderDateEnd"))){
//			param.put("trader", SecurityUtil.getLoginUser().getUserName());
//		}
		//查询委托列表
//		Page<PrimaryBiddingManageDto> pageLimit =  ;
		//同一标的、同一交易对手汇总
//		List<PrimaryBiddingManageDto> list =new ArrayList<PrimaryBiddingManageDto>();
//		for (PrimaryBiddingManageDto primaryBiddingManageDto : pageLimit.getRoot()){
//			String key = primaryBiddingManageDto.getFinprodId()+','+primaryBiddingManageDto.getCounterId();
//			if(map.get(key) == null){
//        		map.put(key, primaryBiddingManageDto);
//        		map.get(key).setBidpriceStr(primaryBiddingManageDto.getBidprice().toString());
//        	}else{
//        		primaryBiddingManageDto.setAllBidPrinAmt(
//        				map.get(key).getAllBidPrinAmt().add(primaryBiddingManageDto.getAllBidPrinAmt()));
//        		primaryBiddingManageDto.setBidpriceStr(
//        				map.get(key).getBidpriceStr()+','+primaryBiddingManageDto.getBidprice().toString());
//        		map.put(key, primaryBiddingManageDto);
//        	}
//        }
//		for(String finprodidcount:map.keySet()){
//			list.add(map.get(finprodidcount));
//			pageLimit.setRoot(list);
//		}
		return super.queryForPage(page, length, () -> primaryBiddingManage001Mapper.selectForPage(param));
	}

	public List<PrimaryBiddingManageDto> findDataByFinprodIdAndCounterId(Map<String, Object> param){
		param.put("pinanassettypeyj", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
		param.put("entruststatusyjs", BussinessDictCodeList.委托状态_已接收.getValue());
		return primaryBiddingManage001Mapper.findDataByFinprodIdAndCounterId(param);
	}

	/**
	 * 截标或取消截标
	 * @param dto
	 * @return
	 */
	public String closeOrCancelBid(PrimaryBiddingManageDto dto){
		try {
			BidCloseStatus bidCloseStatus = new BidCloseStatus();
			bidCloseStatus.setFinprodId(dto.getFinprodId());
			if (StringUtil.equals(dto.getOperateType(),"closeBid")){
				bidCloseStatus.setCloseStatus(DictCodeListExt3.截标状态_已截标.getValue());
			}else {
				bidCloseStatus.setCloseStatus(DictCodeListExt3.截标状态_未截标.getValue());
			}
			bidCloseStatusMapper.deleteByPrimaryKey(dto.getFinprodId());
			bidCloseStatusMapper.insertSelective(bidCloseStatus);
		} catch (Exception e) {
			e.printStackTrace();
			return "处理失败";
		}
		return "处理完成";
	}
}
