/*
 * 文件名：RskValTableImportService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.valtblimport.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumAssetSubMapper;
import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblSumAssetSub;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationInputParaDto;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationOutputDto;
import com.joyintech.tams.invest.common.util.ValItemValueGenUtil;
import com.joyintech.tams.invest.common.util.xmlrelation.XmlRelationUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.valtblimport.dao.PreOccupationChangeMapper;
import com.joyintech.service.risk.valtblimport.dao.RskValTableImportMapper;
import com.joyintech.service.risk.valtblimport.dto.PreOccupActualDetailDto;
import com.joyintech.service.risk.valtblimport.dto.PreOccupationChangeDto;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.risk.valtblimport.dto.ValTableImpRiskDto;
import com.joyintech.service.risk.valtblimport.dto.ValType;
import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumAssetSubMapper;
import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblSumAssetSub;


@Service("backRskValTableImportService")
public class RskValTableImportService {
	
    @Autowired
    private RskValtblAssetSubMapper rskValtblAssetSubMapper;
    @Autowired
    private RskValtblSumAssetSubMapper rskValtblSumAssetSubMapper;
    @Autowired
    private RskValTableImportMapper rskValTableImportMapper;
    
    @Autowired
    private PreOccupationChangeMapper preOccupationChangeMapper;
	
    @Autowired
    private FinTradeProductMapper finTradeProductMapper;
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
    /**
     * 金融产品类型 投管分类
     */
    public static final String KEY_FINPROD_TYPE_TG = "FINPROD_TYPE_TG";

    /**
     * 交易场所
     */
    public static final String KEY_TRADE_PLACE = "TRADE_PLACE";
    /**
     * 估值 资产类型
     */
    public static final String KEY_TYPE1 = "TYPE1";

    /**
     * 估值 核算分类
     */
    public static final String KEY_ACCOUNTING_TYPE = "ACCOUNTING_TYPE";

    /**
     * 估值 税务分类
     */
    public static final String KEY_TAX_ASSET_TYPE = "TAX_ASSET_TYPE";
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void saveValTbl(RskValtblAssetSubDto dto){ 
        rskValTableImportMapper.deleteValTable(dto);
        rskValTableImportMapper.deleteSumValTable(dto);
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            for(RskValtblAssetSubDto entity:dto.getSelectedItem()){
                if(StringUtil.equals(entity.getValType().getDesc(), ValType.ASSET.getDesc())
                        && StringUtil.isNotEmpty(entity.getIsMatch()) && StringUtil.equals(entity.getIsMatch(), DictCodeListExt1.是否_否.getValue())){
                    continue;
                }
                if(StringUtil.equals(entity.getValType().getDesc(),ValType.ASSET.getDesc())){
                    RskValtblAssetSub record = new RskValtblAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblAssetSubMapper.insert(record);
                }else{
                    RskValtblSumAssetSub record = new RskValtblSumAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblSumAssetSubMapper.insert(record);
                }
            }
        }
    }
    
    /**
     *  估值表科目数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public int selectCount(Map<String,Object> paramMap){
        return rskValTableImportMapper.selectCount(paramMap);
    }
    
    /**
     * 上农商个性化_估值表导入 
     * 获取估值表文件中各资产的分配明细 
     * add by zhangpei
     * 20201222
     */
	public List<PreOccupationChangeDto> preOccupationDetail(RskValtblAssetSubDto dto) {
		// 如果导入的估值表组合非自主管理资产，则跳出这个方法 TODO

		// 1.查询估值日期当日自主专户代码的组合分配情况
		Map<String, Object> paramMap = new HashedMap<String, Object>();
		// 实际持仓
		paramMap.put("POSITION_TYPE_03", BussinessDictCodeList.持仓类型_实际.getValue());
		// 估值日期
		paramMap.put("valDate", DateUtil.format(dto.getValDate(), "yyyy-MM-dd"));
		// 自主管理专户
		paramMap.put("ptlId", dto.getPtlId());
		// 自主管理资产 投管类型
		paramMap.put("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
		BigDecimal totalAmout = preOccupationChangeMapper.getSelfManagerTotal(paramMap);

		//2.计算估值表文件中各资产的分配明细 
		List<PreOccupationChangeDto> list = preOccupationChangeMapper.getSelfManagerByOne(paramMap);
		if (!StringUtil.isEmptyList(list) && StringUtil.isNotEmpty(totalAmout)) {
			for (PreOccupationChangeDto oneAmoutDto : list) {
				// 计算组合持仓占比Pi 单个持仓份额/汇总持仓份额 全精度计算
				oneAmoutDto.setPercentage(ArithmeticUtil.divide(oneAmoutDto.getAmount(), totalAmout, 8));
				// 获取估值表持仓情况Qi 计算估值表实际持仓占用风险数额
				List<PreOccupActualDetailDto> detailList = preOccupationChangeMapper.getValtblAllotAmoutForSmv(paramMap);
				if (!StringUtil.isEmptyList(detailList)) {
					for (PreOccupActualDetailDto detailDto : detailList) {
						//如果是债券，对债券授信特殊处理
						if(StringUtil.equals(detailDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
							paramMap.put("finprodId", detailDto.getAssetFinprodId());
							List<PreOccupActualDetailDto> detailListCredit = preOccupationChangeMapper.getValtblAllotAmout(paramMap);
							if(!StringUtil.isEmptyList(detailListCredit)){
								detailDto.setAllotAmoutCredit(ArithmeticUtil.multiply(detailListCredit.get(0).getValAmout(), oneAmoutDto.getPercentage()));
							}
						}else{
							detailDto.setAllotAmoutCredit(ArithmeticUtil.multiply(detailDto.getValAmout(), oneAmoutDto.getPercentage()));
						}
						// 每个组合的数量记为Rij（估值表实际持仓）=估值表文件中的该资产投资的数量Qi*1中计算的比例Pi
						detailDto.setAllotAmout(ArithmeticUtil.multiply(detailDto.getValAmout(), oneAmoutDto.getPercentage()));
					}
				}
				oneAmoutDto.setDetailDtos(detailList);
			}
		}
		
		return list;

	}
	/**
	 * 上农商个性化_估值表导入
	 * 查询需要释放的自主管理信息
	 * add by zhangpei 
	 * 20201223
	 * @param dto
	 * @return
	 */
	public List<PreOccupationChangeDto> queryNeedFreedInfo(RskValtblAssetSubDto dto){
		Map<String, Object> paramMap = new HashedMap<String, Object>();
		// 估值日期
		paramMap.put("valDate", DateUtil.format(dto.getValDate(), "yyyy-MM-dd"));
		// 自主管理专户
		paramMap.put("ptlId", dto.getPtlId());
		//查询需要释放的预占
		paramMap.put("entrustStatus", DictCodeList.委托状态_待接收.getValue());
		return preOccupationChangeMapper.queryNeedFreedInfo(paramMap);
	}
	/**
	 * 上农商个性化_估值表导入
	 * 根据委托代码，更新指令状态为‘已完成’
	 * add by zhangpei
	 * 20201224
	 * @param list
	 * @return
	 */
	public int updateEntrustStatus(List<PreOccupationChangeDto> list){
		int result=0;
		if(!StringUtil.isEmptyList(list)){
			for(PreOccupationChangeDto dto: list){
				dto.setEntrustStatus(DictCodeList.委托状态_已完成.getValue());
				result+=preOccupationChangeMapper.updateEntrustStatus(dto);
			}
		}
		return result;
	}
	/**
	 * 插入导入估值表风险实占留痕表
	 * @param list
	 * @return
	 */
	public int insertValImpRisk(List<ValTableImpRiskDto> list){
		int result=0;
		if(!StringUtil.isEmptyList(list)){
			for(ValTableImpRiskDto dto:list){
				result+=preOccupationChangeMapper.insertValImpRisk(dto);
			}
		}
		return result;
	}
	/**
	 * 删除导入估值表分线实占留痕表的相关数据
	 * @param list
	 * @return
	 */
	public int deleteByPrimaryKeyRisk(List<ValTableImpRiskDto> list){
		int result=0;
		if(!StringUtil.isEmptyList(list)){
			for(ValTableImpRiskDto dto:list){
				result+=preOccupationChangeMapper.deleteByPrimaryKeyRisk(dto.getTradeId());
			}
		}
		return result;
	}
	/**
	 * 根据委外组合和导入时间查询导入的指令数据
	 * @param dto
	 * @return
	 */
	public List<ValTableImpRiskDto> queryImpRiskInfo(RskValtblAssetSubDto dto){
		Map<String, Object> paramMap = new HashedMap<String, Object>();
		// 估值日期
		paramMap.put("valDate", DateUtil.format(dto.getValDate(), "yyyy-MM-dd"));
		// 自主管理专户
		paramMap.put("ptlId", dto.getPtlId());
		return preOccupationChangeMapper.queryImpRiskInfo(paramMap);
	}

	public int selectGoalCount(Map<String, Object> paramMap) {
		return rskValTableImportMapper.selectGoalCount(paramMap);
	}
	
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void createRepoInfo(RskValtblAssetSubDto entity2){
        //根据资产代码，查询系统中是否已经存在
        List<FinTradeProduct> list= finTradeProductMapper.selectByFinprodId(entity2.getFinprodId());
        if(StringUtil.isEmptyList(list)){
            //若不在，则生成新的数据
            String code=entity2.getSubCode().substring(0, 8);
            String finprodType= null;
            String tradeMarket = null;
            String type6= null;
            String type7= null;
            String type8= null;
            String type9= null;
            String type10= null;
            String type11= null;
            String type12= null;
            if(StringUtil.isNotEmpty(code)){
                if(StringUtil.equals(code,"12020101")){
                    //上交所质押式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue();//F03
                    tradeMarket=DictCodeListExt1.交易场所_上海证券交易所.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"12020102")){
                    //上交所买断式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue();//F05
                    tradeMarket=DictCodeListExt1.交易场所_上海证券交易所.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_拆放同业及买入返售.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_买入返售.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }else if(StringUtil.equals(code,"12020201")){
                    //银行间质押式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue();//F03
                    tradeMarket=DictCodeListExt1.交易场所_银行间.getValue();
                    type6= DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"12020202")){
                    //银行间买断式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue();//F05
                    tradeMarket=DictCodeListExt1.交易场所_银行间.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_拆放同业及买入返售.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_买入返售.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }else if(StringUtil.equals(code,"12020301")){
                    //深交所质押式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue();//F03
                    tradeMarket=DictCodeListExt1.交易场所_深圳证券交易所.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();
                }else if(StringUtil.equals(code,"12020302")){
                    //深交所买断式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue();//F05
                    tradeMarket=DictCodeListExt1.交易场所_深圳证券交易所.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_逆回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_拆放同业及买入返售.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_买入返售.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }else if(StringUtil.equals(code,"22020101")){
                    //上交所卖出质押式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();//F02
                    tradeMarket=DictCodeListExt1.交易场所_上海证券交易所.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"22020102")){
                    //上交所卖出买断式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue();//F04
                    tradeMarket=DictCodeListExt1.交易场所_上海证券交易所.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_同业拆入及卖出回购.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_卖出回购.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }else if(StringUtil.equals(code,"22020201")){
                    //银行间卖出质押式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();//F02
                    tradeMarket=DictCodeListExt1.交易场所_银行间.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"22020202")){
                    //银行间卖出买断式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue();//F04
                    tradeMarket=DictCodeListExt1.交易场所_银行间.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_同业拆入及卖出回购.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_卖出回购.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();


                }else if(StringUtil.equals(code,"22020301")){
                    //深交所卖出质押式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();//F02
                    tradeMarket=DictCodeListExt1.交易场所_深圳证券交易所.getValue();
                    type6=DictCodeList.回购类型_质押式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8=BussinessDictCodeList.平安资产类型_回购.getValue();

                }else if(StringUtil.equals(code,"22020302")){
                    //深交所卖出买断式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue();//F04
                    tradeMarket=DictCodeListExt1.交易场所_深圳证券交易所.getValue();
                    type6=DictCodeList.回购类型_买断式回购.getValue();
                    type7=DictCodeList.回购拆借方向_正回购.getValue();
                    type8= BussinessDictCodeList.平安资产类型_买断式回购.getValue();
                    type9=DictCodeListExt1.行内资产二级分类_同业拆入及卖出回购.getValue();
                    type10=DictCodeListExt1.行内资产三级分类_卖出回购.getValue();
                    type11=DictCodeListExt1.行内资产四级分类_债券买断式回购.getValue();
                    type12=DictCodeListExt1.行内资产五级分类_债券买断式回购.getValue();

                }
                FinTradeProduct ftp=new FinTradeProduct();
                ftp.setFinprodId(entity2.getFinprodId());
                ftp.setBranch(0L);
                ftp.setFinprodType(finprodType);
                ftp.setFinprodType2(finprodType);
                ftp.setTradeMarket(tradeMarket);
                ftp.setCreateUser(CommonUtil.getUserName());
                ftp.setUpdateUser(CommonUtil.getUserName());
                ftp.setCreateDept(CommonUtil.getDeptCode());
                ftp.setCreateTime(DateUtil.now());
                ftp.setUpdateTime(DateUtil.now());
                ftp.setDataSource(DictCodeListExt1.生成方式_接口接入.getValue());
                finTradeProductMapper.insert(ftp);

                FinProductType fpt=new FinProductType();
                // 调用共通获取金融产品类型（估值核算）
                List<XmlRelationInputParaDto> inputParamList = new ArrayList<XmlRelationInputParaDto>();
                // 金融产品类型（投管分类）
                XmlRelationInputParaDto inputParaDto = new XmlRelationInputParaDto();
                inputParaDto.setDictCode(KEY_FINPROD_TYPE_TG);
                inputParaDto.setItemCode(finprodType);
                inputParamList.add(inputParaDto);
                // 交易场所
                if (StringUtil.isNotBlank(tradeMarket)) {
                    inputParaDto = new XmlRelationInputParaDto();
                    inputParaDto.setDictCode(KEY_TRADE_PLACE);
                    inputParaDto.setItemCode(tradeMarket);
                    inputParamList.add(inputParaDto);
                }


                XmlRelationOutputDto outputDto = XmlRelationUtil.getOutPut(inputParamList);
                if (StringUtil.isBlank(fpt.getType1())) {
                    fpt.setType1(XmlRelationUtil.getVALOutPutResultItemCode(outputDto, KEY_TYPE1));
                }
                if (StringUtil.isBlank(fpt.getType2())) {
                    fpt.setType2(XmlRelationUtil.getVALOutPutResultItemCode(outputDto, KEY_ACCOUNTING_TYPE));
                }
                if (StringUtil.isBlank(fpt.getType3())) {
                    fpt.setType3(XmlRelationUtil.getVALOutPutResultItemCode(outputDto, KEY_TAX_ASSET_TYPE));
                }
                fpt.setFinprodId(entity2.getFinprodId());
                fpt.setBranch(0L);
                fpt.setType6(type6);
                fpt.setType7(type7);
                fpt.setType8(type8);
                fpt.setType9(type9);
                fpt.setType10(type10);
                fpt.setType11(type11);
                fpt.setType12(type12);
                fpt.setCreateUser(CommonUtil.getUserName());
                fpt.setUpdateUser(CommonUtil.getUserName());
                fpt.setCreateDept(CommonUtil.getDeptCode());
                fpt.setCreateTime(DateUtil.now());
                fpt.setUpdateTime(DateUtil.now());
                finProductTypeMapper.insert(fpt);
            }
        }
    }
}
