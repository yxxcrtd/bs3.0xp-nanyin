package com.joyintech.tams.invest.common.trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditLinesDeal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.trade.dao.CreditLinesDealsMapper;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;

@Service
@Transactional(rollbackFor=Exception.class)
public class CeLinesUpdate  extends CelinesUpdateFactory{

	@Autowired
    private CreditLinesDealsMapper creditLinesMapper;
	
	CreditLinesDeal creditLinesDeal=new CreditLinesDeal();

	/**
	 * 占用、释放授信额度共通处理
	 * @param inputmodel 交易model
	 * @param isNotBottomflag 是否是底层资产交易
	 * @param isSave 是否入库
	 * @param isdeleteflag 是否是作废表示
	 * 代码结构调整新增 by xkni 2019-08-23
	 * @return
	 */

	@Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRES_NEW)
	public List<CreditLinesDealCommon> isNeedOccupyCre(AllTradeCommonInputDto inputmodel, boolean isNotBottomflag, boolean isSave,Boolean isdeleteflag) {
 
		//底层资产返回list
		List<CreditLinesDealCommon> list = new ArrayList<CreditLinesDealCommon>();
		//监控授信开关
	    boolean isCreditSoftControl = Boolean.parseBoolean(CacheUtil.getSysParam("IS_CREDIT_SOFTCONTROL")); 
		if (StringUtil.equals(BussinessDictCodeList.监控授信开关_关.getValue(), CacheUtil.getSysParam(BussinessConstant.ISCREDIT))) {
            // 软控为true时保存仍进行额度修改
		    if (isCreditSoftControl){
                if (!isSave) {
                    return list;
                }
            }
	    }
		//交易相关信息model
		// 交易撤销时交易对手代码会被修改为市场机构bug修正 modify by renping 20190919 start
		TrdProductDeal model = new TrdProductDeal();
		BeanUtil.copyPropertiesIgnoreNull(model, inputmodel.getTrdProductDeal());
		// 交易撤销时交易对手代码会被修改为市场机构bug修正 modify by renping 20190919 end
		super.inputmodel=inputmodel;
		super.isNotBottomflag=isNotBottomflag;
		super.isSave=isSave;
		super.isdeleteflag=isdeleteflag;
		super.creditLinesMapper=creditLinesMapper;
	/*	HashMap<String, Object> map=new HashMap<>();
		List<CreditLines> crelist=new ArrayList<>();// 品种授信列表
		CreditLines creditLines=new CreditLines();
		creditLines.setSeqno("0050");
		crelist.add(creditLines);
		map.put("creLineslist",crelist);// TOdo 待实现内容List<CreditLines> list 自定义品种授信
		//自定义品种授信
		//super.getVarietiesCeLineList(map);*/

		AllTradeCommonInputDto allTradeCommonInputDto=new AllTradeCommonInputDto();
		BeanUtil.copyPropertiesIgnoreNull(allTradeCommonInputDto,inputmodel);
		return super.isNeedOccupyCre(allTradeCommonInputDto,isNotBottomflag,isSave,isdeleteflag);

	}

	//Step1:根据传入的金融产品代码  查询授信额度List<授权额度管理>
	public List<CreditLines> getCreLineslist(TrdProductDeal model, List<CreditLines> creLineslist, AllTradeCommonInputDto inputmodel, boolean isNotBottomflag, Boolean flagarrisNeedOccupyCreCounter)
	{
		if(!((model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())||
				StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue()))))){
			//授信参数传值方式修改 start
			Map<String,Object> map = new HashMap<>();
			map.put("dto", model);
			map.put("ps1", DictCodeList.交易方向_买入.getValue());
			map.put("ps4", DictCodeList.交易方向_申购.getValue());
			map.put("creditmstidZC", BussinessDictCodeList.授信配置代码_资产授信.getValue());
			map.put("creditmstidXL", BussinessDictCodeList.授信配置代码_系列授信.getValue());
			creLineslist=creditLinesMapper.getCeLinesInfoforasset(map);
			//授信参数传值方式修改 end
		}

		//是否占用主体授信flag
		Boolean iscreditflag=false;
		for(CreditLines creditLinesAsset:creLineslist){
			if(StringUtil.equals(DictCodeList.是否_是.getValue(),creditLinesAsset.getCreditFlag())){
				iscreditflag=true;
			}
		}

		//b.处理机构授信、品种授信
		//a的查询结果为0 或者（若有数据  则判断 是否占用主体授信=是 则查询机构授信及机构品种授信）
		List<CreditLines> orgcreLineslist=new ArrayList<CreditLines>();

		if((creLineslist==null || creLineslist.size()==0)||
				(creLineslist!=null && creLineslist.size()>0 && iscreditflag)){
			String cemodel = CacheUtil.getSysParam(BussinessConstant.CEMODEL);
			//如果是机构或者品种授信，交易对手代码转换成市场代码
			String counterId ="";
			if(isNotBottomflag){
				//交易
				if(flagarrisNeedOccupyCreCounter){
					counterId =creditLinesMapper.getPartyIdBycounterId(model.getCounterId(),
							DictCodeListExt1.交易对手类型_市场机构.getValue());
				}
				//存续期交易
				else{
					if((model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
							||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))){
						//回购存续期交易根据Finprodid查询交易对手
						counterId =creditLinesMapper.getPartyIdByFinprodId(model.getFinprodId());
					}
				}
				model.setCounterId(counterId);
			}
			//授信参数传值方式修改 start
			Map<String,Object> map = new HashMap<>();
			map.put("dto", model);
			map.put("ps1", DictCodeList.交易方向_买入.getValue());
			map.put("ps4", DictCodeList.交易方向_申购.getValue());
			map.put("creditmstidJG", BussinessDictCodeList.授信配置代码_机构授信.getValue());
			map.put("fzq",DictCodeList.平安资产类型_债券.getValue());
			map.put("fck",DictCodeList.平安资产类型_存款.getValue());
			map.put("fxml",DictCodeList.平安资产类型_项目类资产.getValue());
			map.put("fhg",DictCodeList.平安资产类型_质押式回购.getValue());
			//授信参数传值方式修改 end
			//如果系统参数.授信模式==01
			if("01".equals(cemodel)){
				if((model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
						||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))){
					String repType = DictCodeList.回购类型_买断式回购.getValue();
					if(StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
						repType = inputmodel.getRepoType();
					}
					//授信参数传值方式修改 start
					map.put("repType",repType);
					//预占用授权额度管理
					orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg01Rep(map);
					//授信参数传值方式修改 end
				}else{
					orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg01(map);
				}
			}else{
				if((model.getFinprodType2() != null && (StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
						||StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())))){
					String repType = DictCodeList.回购类型_买断式回购.getValue();
					if(StringUtil.equals(model.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
						repType = inputmodel.getRepoType();
					}
					//授信参数传值方式修改 start
					map.put("repType",repType);
					orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg01Rep(map);
					//授信参数传值方式修改 end
				}else{ 
				    if (!StringUtil.isNotEmpty(model.getFinprodId())) {
                        return null;
                    } 
					orgcreLineslist = creditLinesMapper.getCeLinesInfoforOrg02(map);
				}
			}
			for(CreditLines creditLinesOrg:orgcreLineslist){

				if(creLineslist!=null && creLineslist.size()>0)
				creLineslist.add(creditLinesOrg);
			}
		}
		return creLineslist;
	}
}
