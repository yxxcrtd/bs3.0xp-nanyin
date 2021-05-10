package com.joyintech.service.valuation.bokdetailidupdate.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dao.ValManualVoucher003Mapper;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.service.ValManualVoucher003Service;
import com.joyintech.service.valuation.bokdetailidupdate.dao.BokDetailIdUpdate002Mapper;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokBussDetsubDto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokDetailIdUpdate003Dto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.ResultData;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.service.SystemToolService;
import com.joyintech.tams.valuation.common.auxiliary.dao.OperationAuxiliaryMapper;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.dto.TargetFinProduct;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.val.dao.BokTradeBuildMapper;

@Service("seriveBokDetailIdUpdate002Service")
public class BokDetailIdUpdate002Service extends BaseService{

	@Autowired
	private InterfCommonService interfCommonService;

	/**
     * 系统工具类
     */
    @Autowired
    private SystemToolService systemToolService;

    @Autowired
    private BokTradeBuildMapper bokTradeBuildMapper;

    @Autowired
    private BokDetailIdUpdate002Mapper bokDetailIdUpdate002Mapper;

    @Autowired
    private OperationAuxiliaryMapper operationAuxiliaryMapper;

    @Autowired
	private ValManualVoucher003Mapper valManualVoucher003Mapper;


	/**
	 * 账务明细代码加密生成
	 * @param dto
	 * @return
	 */
	public String getBokdetailId(BokDetailIdUpdate003Dto dto){
		BokDetailId bokDetailId = new BokDetailId();
    	bokDetailId.setBusiId(dto.getBusiId());
    	bokDetailId.setBusiDetailType(dto.getBusiDetailType());
    	if(DictCodeListExt1.金融产品类型_理财子产品.getValue().equals(dto.getBusiDetailType())
    			&& dto.getVdate() != null){
    		bokDetailId.setBranch( Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getVdate())));
    	}
    	bokDetailId.setCcy(dto.getCcy());
    	bokDetailId.setInvAim(dto.getInvAim());
    	dto.setBranch(bokDetailId.getBranch()==null?0L:bokDetailId.getBranch());
		return interfCommonService.genMd5BokDetailId(bokDetailId);
	}

	/**
	 * 账务明细代码调整
	 * @param dto
	 * @return
	 */
	public ResultData doSubmit(BokDetailIdUpdate003Dto dto){
		ResultData resultDate = new ResultData();
		resultDate.setRstCode("0000");
		int updatecount = 0;
		String bokDetailId = this.getBokdetailId(dto);
		dto.setBokDetailId(bokDetailId);
		//查询调整后账务明细代码数据
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("booksetId", dto.getBooksetId());
		params.put("paramCode","TYJK_FORSUBJECT");
		/**1.四级科目尾号、四级科目尾号查询**/
		List<BokBooksetExt> listBokBooksetExt = bokDetailIdUpdate002Mapper.queryBokBooksetExtList(params);
		Map<String,String> bokBooksetExtMap = new HashMap<String,String>();
		if(!StringUtil.isEmptyList(listBokBooksetExt)){
			for(BokBooksetExt obj:listBokBooksetExt){
				bokBooksetExtMap.put(obj.getBooksetId(), obj.getParamValue());
			}
		}

		String finprodId = dto.getBusiId();
		String lastFinprodType = dto.getBusiDetailType();
		if (DictCodeListExt1.金融产品类型_理财子产品.getValue().equals(dto.getBusiDetailType())) {
			lastFinprodType = DictCodeListExt1.金融产品类型_理财产品.getValue();
		}
		else if(systemToolService.getDataDictItem(DictCode.资金账户类型.getValue(), dto.getBusiDetailType()) != null){
			lastFinprodType = DictCodeListExt1.金融产品类型_资金账户.getValue();
	    }
		TargetFinProduct targetFinProduct = bokTradeBuildMapper.getTargetFinProductByCondition(finprodId, lastFinprodType);


		// 定义 变量.四级科目尾号 = 输入.金融产品代码
        String detailSubjectNoTaiL = finprodId;
        // 定义 变量.四级科目尾号名称 = 变量.标的信息Dto.金融产品名称
        String detailSubjectNameTaiL = finprodId;
        if(targetFinProduct != null){
            detailSubjectNameTaiL = targetFinProduct.getFinprodName();
        }

        if (DictCodeListExt1.金融产品类型_理财子产品.getValue().equals(dto.getBusiDetailType())) {
            // 变量.四级科目尾号 += 变量.分支序号
            detailSubjectNoTaiL += dto.getBranch();
            // 变量.四级科目尾号名称 += 变量.分支序号
            detailSubjectNameTaiL += dto.getBranch();
        }

        if (DictCodeListExt1.金融产品类型_理财产品.getValue().equals(dto.getBusiDetailType())
        		|| DictCodeListExt1.金融产品类型_理财子产品.getValue().equals(dto.getBusiDetailType())
                || DictCodeListExt1.金融产品类型_投资组合.getValue().equals(dto.getBusiDetailType())) {
            detailSubjectNoTaiL += FinalString.LAYERING_SPLIT + dto.getBusiDetailType();
        }
        if (DictCodeListExt1.金融产品类型_拆入.getValue().equals(dto.getBusiDetailType())
        		|| DictCodeListExt1.金融产品类型_拆出.getValue().equals(dto.getBusiDetailType())) {
        	// 变量.四级科目尾号 = 变量.标的信息Dto.期限品种
            detailSubjectNoTaiL = targetFinProduct.getTermType();
            // 变量.四级科目尾号名称 =
            // InterfHelper.getDictColdeList_ItemName(DictCode.期限品种_内部拆借.getValue(),变量.标的信息Dto.期限品种)
            detailSubjectNameTaiL = InterfHelper.getDictColdeList_ItemName(DictCode.期限品种_内部拆借.getValue(), targetFinProduct.getTermType());
        }
        else if (DictCodeListExt1.金融产品类型_债券质押式正回购.getValue().equals(dto.getBusiDetailType())
        		|| DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue().equals(dto.getBusiDetailType())
        		|| DictCodeListExt1.金融产品类型_债券买断式正回购.getValue().equals(dto.getBusiDetailType())
        		|| DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue().equals(dto.getBusiDetailType())) {
        	// 变量.四级科目尾号 = 变量.标的信息Dto.期限品种
            detailSubjectNoTaiL = targetFinProduct.getTermType();
            // 变量.四级科目尾号名称 =
            // InterfHelper.getDictColdeList_ItemName(DictCode.期限品种_回购.getValue(),变量.标的信息Dto.期限品种)
            detailSubjectNameTaiL = InterfHelper.getDictColdeList_ItemName(DictCode.回购期限品种.getValue(), targetFinProduct.getTermType());
        }
        else if (DictCodeListExt1.金融产品类型_同业借款.getValue().equals(dto.getBusiDetailType())) {
            dto.setTermType(targetFinProduct.getTermType());
            dto.setTermTypeName(InterfHelper.getDictColdeList_ItemName(DictCode.期限品种_内部拆借.getValue(), targetFinProduct.getTermType()));
        }
        dto.setDetailSubjectNoTail(detailSubjectNoTaiL);
        dto.setDetailSubnameTail(detailSubjectNameTaiL);

        /**2.四级科目尾号、四级科目尾号名称、四级科目号、四级科目名称更新**/
        List<BokBussDetsub> rlist = new ArrayList<BokBussDetsub>();
        List<BokBussDetsubDto> blist = new ArrayList<BokBussDetsubDto>();

        params = new HashMap<String,Object>();
        params.put("booksetId", dto.getBooksetId());
        params.put("bokDetailIdOld", dto.getBokdetailIdOld());
        List<BokBussDetsubDto>  qlist = bokDetailIdUpdate002Mapper.queryBokBussDetsubDtoList(params);
        if(!StringUtil.isEmptyList(qlist)){
        	for(BokBussDetsubDto obj :qlist){
        		String detailSubjectNoTail = dto.getDetailSubjectNoTail();
				String detailSubnameTail = dto.getDetailSubnameTail();

				if (DictCodeListExt1.金融产品类型_同业借款.getValue().equals(dto.getBusiDetailType())) {
					if(!(bokBooksetExtMap.get(obj.getBooksetId())==null
							|| "01".equals(bokBooksetExtMap.get(obj.getBooksetId())))){
						detailSubjectNoTail = dto.getTermType();
						detailSubnameTail = dto.getTermTypeName();
	        		}
				}
				//四级科目尾号、四级科目名称相同，不做更新
				if(!(detailSubjectNoTail.equals(obj.getDetailSubjectNoTail())
						&& detailSubnameTail.equals(obj.getDetailSubjectNoTail()))){
					BokBussDetsub sub = new BokBussDetsub();
	                //账套代码
	                sub.setBooksetId(obj.getBooksetId());
	                //四级科目号 = 科目号 + 四级科目尾号
	                sub.setDetailSubjectNo(obj.getSubjectNo()+detailSubjectNoTail);
	                //四级科目名称 = 科目名称 + 四级科目尾号名称
	                sub.setDetailSubjectName(obj.getSubjectName()+detailSubnameTail);
	                //上级科目号 = 入参.替换科目号
	                sub.setFsubjectId(obj.getSubjectNo());
	                //四级科目尾号
	                sub.setDetailSubjectNoTail(detailSubjectNoTail);
	                //四级科目尾号名称
	                sub.setDetailSubnameTail(detailSubnameTail);
	                //变量.业务明细科目List.add(变量.业务明细科目DTO)
	                rlist.add(sub);
				}
                if(!detailSubjectNoTail.equals(obj.getDetailSubjectNoTail())){
                	BokBussDetsubDto subDto = new BokBussDetsubDto();
                	subDto.setBooksetId(obj.getBooksetId());
                    subDto.setBokDetailId(dto.getBokdetailIdOld());
                    subDto.setDetailSubjectNo(obj.getDetailSubjectNo());
                    subDto.setSubjectNo(obj.getSubjectNo());
                    subDto.setDetailSubjectNoTail(detailSubjectNoTail);
                    blist.add(subDto);
				}
        	}
        }
        if(!StringUtil.isEmptyList(rlist)){
        	 operationAuxiliaryMapper.deleteBokBussDetsubBatch(rlist);
        	 updatecount += operationAuxiliaryMapper.insertBokBussDetsubBatch(rlist);
        }
        if(!StringUtil.isEmptyList(blist)){
        	//账务明细历史表
        	updatecount += bokDetailIdUpdate002Mapper.updateDetailSubjectNo1(blist);
        	//账务明细当前表
        	updatecount += bokDetailIdUpdate002Mapper.updateDetailSubjectNo2(blist);
        }

        /**3.调整前后账务明细代码不一致时:更新核算数据**/
        if(!bokDetailId.equals(dto.getBokdetailIdOld())){
        	params = new HashMap<String,Object>();
            params.put("booksetId", dto.getBooksetId());
            params.put("bokDetailIdOld", dto.getBokdetailIdOld());
            params.put("bokDetailId", bokDetailId);

            //增值税已抵扣增值额
			updatecount += bokDetailIdUpdate002Mapper.updateTaxDeductedBalance(params);
			//增值税待抵扣增值额
			updatecount += bokDetailIdUpdate002Mapper.updateTaxTurnedBalance(params);
			//增值税账务明细余额
			updatecount += bokDetailIdUpdate002Mapper.updateTaxBokBalance(params);
			//增值税账务明细流水
			updatecount += bokDetailIdUpdate002Mapper.updateTaxBokDetail(params);
			//统计分析明细表（账务明细）
			updatecount += bokDetailIdUpdate002Mapper.updateBokStatDetPosiiton(params);
			//账务明细辅助表
			updatecount += bokDetailIdUpdate002Mapper.updateBokBookdetailCurrentExt(params);

			params.put("busiId", dto.getBusiId());
			//账务明细（历史表）
			updatecount += bokDetailIdUpdate002Mapper.updateBokBookdetailAll(params);
			//账务明细（当前表）
			updatecount += bokDetailIdUpdate002Mapper.updateBokBookdetailCurrent(params);

			if(DictCodeListExt1.金融产品类型_债券.getValue().equals(dto.getBusiDetailType())){
				params.put("invAim", dto.getInvAim());
			}
			else if(DictCodeListExt1.金融产品类型_计划类资产.getValue().equals(dto.getBusiDetailType())
	        		&& DictCodeList.收益类型_收益型.getValue().equals(dto.getProfitType())){
				params.put("invAim", dto.getInvAim());
			}
			else{
				params.put("invAim", null);
			}
			params.put("busiDetailType", dto.getBusiDetailType());

			//手工凭证交易明细
			updatecount += bokDetailIdUpdate002Mapper.updateTrdVouchDealDetail(params);

			//核算分层信息
			updatecount += bokDetailIdUpdate002Mapper.updateBokAcctLayeringInfo(params);

			//交易入账信息表
			updatecount += bokDetailIdUpdate002Mapper.updateBokDealAccountingDetail(params);

			//账务明细代码表
			bokDetailIdUpdate002Mapper.deleteBokDetailId(params);
			updatecount += bokDetailIdUpdate002Mapper.insertBokDetailId(dto);
        }

        /**4.调整前后资金账户不一致时:更新结算指令表**/
        if(systemToolService.getDataDictItem(DictCode.资金账户类型.getValue(), dto.getBusiDetailType()) != null
        		&& !dto.getBusiId().equals(dto.getAcctNoOld())){
        	updatecount += bokDetailIdUpdate002Mapper.updateStlOrders1(dto);
        	updatecount += bokDetailIdUpdate002Mapper.updateStlOrders2(dto);
        }

		if(updatecount==0){
			resultDate.setRstDesc("调整前账务明细代码未有对应数据需要更新!");
		}
		else{
			resultDate.setRstDesc("累计更新数据"+updatecount+"条，调整成功!");
		}
		return resultDate;
	}

	public List<SuggestDto> getBusiId(String finprodType, String booksetId, String param) {

		if (DictCodeListExt1.金融产品类型_投资组合.getValue().equals(finprodType)) {
		   // 调用方法：获取核算标的信息（ 账套代码，金融产品类型）
           List<SuggestDto> qlist = bokDetailIdUpdate002Mapper.getAcctLayeringInfo(booksetId, finprodType);
           return queryForSuggest(() -> {return qlist;}, param, null);
		}
		else if(systemToolService.getDataDictItem(DictCode.资金账户类型.getValue(), finprodType) != null){
			List<SuggestDto> qlist = bokDetailIdUpdate002Mapper.getBooksetCapacctInfo(finprodType, booksetId);
			return queryForSuggest(() -> {return qlist;}, param, null);
	    }
		else{
			String lastFinprodType = finprodType;
			if(DictCodeListExt1.金融产品类型_融入债券.getValue().equals(finprodType)){
				lastFinprodType = DictCodeListExt1.金融产品类型_债券.getValue();
			}
			else if(DictCodeListExt1.金融产品类型_理财子产品.getValue().equals(finprodType)){
				lastFinprodType = DictCodeListExt1.金融产品类型_理财产品.getValue();
			}
			List<SuggestDto> qlist = bokDetailIdUpdate002Mapper.getFinProduct(lastFinprodType);
			if(qlist != null && qlist.size()>0){
				return queryForSuggest(() -> {return qlist;}, param, null);
			}
			List<SuggestDto> qlist2 = bokDetailIdUpdate002Mapper.getFinTradeProduct(lastFinprodType);
			if(qlist != null && qlist.size()>0){
				return queryForSuggest(() -> {return qlist2;}, param, null);
			}
			return queryForSuggest(() -> {return new ArrayList<SuggestDto>();}, param, null);
		}
	}
}
