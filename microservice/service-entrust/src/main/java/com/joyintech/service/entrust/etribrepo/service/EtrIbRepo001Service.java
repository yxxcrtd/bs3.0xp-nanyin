/*
 * 文件名：EtrBondFirstLBiddingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etribrepo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etribrepo.dao.EtrIbRepo001Mapper;
import com.joyintech.service.entrust.etribrepo.dao.EtrIbRepoLib001Mapper;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepo001Dto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoRk004Dto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoRk005Dto;
import com.joyintech.tams.common.base.dao.EtrEntrustPledgeMapper;
import com.joyintech.tams.common.base.dao.EtrSecStockConfigMapper;
import com.joyintech.tams.common.base.dao.EtrSecStockMapper;
import com.joyintech.tams.common.base.dao.PtlSecStockMapper;
import com.joyintech.tams.common.base.entity.EtrEntrustPledge;
import com.joyintech.tams.common.base.entity.EtrSecStockConfig;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.PtlSecStock;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.interf.IPlatformDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrIbRepo001Service
 * @since 1.0
 */
@Service("backEtrIbRepo001Service")
public class EtrIbRepo001Service extends BaseService{

    @Autowired
    private EtrIbRepo001Mapper etrIbRepo001Mapper;
    
    @Autowired
    private EtrSecStockMapper etrSecStockMapper;
    @Autowired
    private PtlSecStockMapper ptlSecStockMapper;
    @Autowired
    private EtrSecStockConfigMapper etrSecStockConfigMapper;
    
    @Autowired
    private EtrEntrustPledgeMapper etrEntrustPledgeMapper;
    
	@Autowired
	private EtrIbRepoLib001Mapper etrIbRepoLib001Mapper;

    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    /**
     * 指令管理查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrIbRepo001Dto> findZlglPage(int page, int length,
                                                       Map<String, Object> pageParam) {
        pageParam.put("FINPROD_TYPE2_Z", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        pageParam.put("FINPROD_TYPE2_N", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        pageParam.put("ENTRUST_STATUS_08", DictCodeListExt3.委托状态_流程中.getValue());
        pageParam.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        pageParam.put("sysDate", iPlatformDate.getSysDateStr());
        pageParam.put("TRADE_PLACE_01", DictCodeListExt1.交易场所_银行间.getValue());
//        pageParam.put("startDate", iPlatformDate.getSysDateStr());
//        pageParam.put("endDate", iPlatformDate.getSysDateStr());
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        Page<EtrIbRepo001Dto> result = super.queryForPage(page, length, () -> etrIbRepo001Mapper.findZlglPage(pageParam));
        return result;
//        List<EtrIbRepo001Dto> list = etrIbRepo001Mapper.findZlglPage(pageParam);
//        return new Page<EtrIbRepo001Dto>(list);
    }

    /**
     * 债券质押持仓信息查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrIbRepo001Dto> findZlglPositionPage(int page, int length,
            Map<String, Object> pageParam) {
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        String secAcctId = (String)pageParam.get("secAcctId");
        if(StringUtil.isNotEmpty(secAcctId)){
            pageParam.put("secAcctIdList", StringUtil.splitToList(GlobalConstant.COMMA,secAcctId));
        }
        String zyqk = (String)pageParam.get("zyqk");
        if(StringUtil.isNotEmpty(zyqk)){
            pageParam.put("zyqk", StringUtil.splitToList(GlobalConstant.COMMA,zyqk));
        }
        pageParam.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
        pageParam.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        pageParam.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
        pageParam.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
        pageParam.put("POSITION_TYPE_06", DictCodeList.持仓类型_融出.getValue());
        pageParam.put("POSITION_TYPE_08", DictCodeListExt1.持仓类型_预占用.getValue());
        pageParam.put("POSITION_TYPE_11", DictCodeListExt1.持仓类型_卖空.getValue());
        pageParam.put("POSITION_TYPE_12", DictCodeListExt1.持仓类型_卖空可用.getValue());

        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("sysDate", iPlatformDate.getSysDateStr());
        pageParam.put("TRADEPLACE01", DictCodeListExt1.交易场所_银行间.getValue());
        pageParam.put("VALUATION_SOURCE_CBC", DictCodeList.估值来源_中债登_CBC.getValue());
        Page<EtrIbRepo001Dto> result = super.queryForPage(page, length, () -> etrIbRepo001Mapper.findZlglPositionPage(pageParam));
        return result;
    }

    /**
     * 查询证券账户
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getSecManageAcctId(int page, int length,
                                                  Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length,
                () -> etrIbRepo001Mapper.getSecManageAcctId(param));
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<EtrIbRepoRk004Dto> getAssetName(Map<String, Object> param) {
        return etrIbRepo001Mapper.getAssetName(param);
    }
    
    /**
     * <一句话功能简述>
     * 查询某系列下的资产明细
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<EtrIbRepoRk005Dto> getAssetDetailBySeqno(int page, int length,
                                                                    Map<String, Object> param) throws Exception {
        return super.queryForPage(page, length, () ->etrIbRepo001Mapper.getAssetDetailBySeqno(param));
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void insert(EtrIbRepoRk004Dto dto) {
    	//先删后插
    	etrIbRepo001Mapper.deleteByPrimaryKey(dto.getPledgeStockId());
    	etrIbRepo001Mapper.delete(dto.getPledgeStockId());
        etrSecStockMapper.deleteByPrimaryKey(dto.getPledgeStockId());
    	EtrIbRepoRk004Dto record = new EtrIbRepoRk004Dto();
        record.setPledgeStockId(dto.getPledgeStockId());
        record.setPledgeStockName(dto.getPledgeStockName());
        etrSecStockMapper.insert(record);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(EtrIbRepoRk004Dto dto) {
    	etrIbRepo001Mapper.deleteByPrimaryKey(dto.getPledgeStockId());
    	etrIbRepo001Mapper.delete(dto.getPledgeStockId());
        return etrSecStockMapper.deleteByPrimaryKey(dto.getPledgeStockId());
    }
    
    public int checkIsExistByPledgeStockName(Map<String, Object> map) {
        return etrIbRepo001Mapper.checkIsExistByPledgeStockName(map);
    }
    
    public EtrIbRepoRk004Dto getBusinessInfoByPledgeStockId(String pledgeStockId) {
        return etrIbRepo001Mapper.getBusinessInfoByPledgeStockId(pledgeStockId);
    }
    
    public int checkIsExistDetail(String pledgeStockId) {
        return etrIbRepo001Mapper.checkIsExistDetail(pledgeStockId);
    }
    
    public void inserttotalq(String pledgeStockId) {
    	//先删后插
    	etrIbRepo001Mapper.deleteByPrimaryKey(pledgeStockId);
    	etrIbRepo001Mapper.delete(pledgeStockId);
    	etrIbRepo001Mapper.deleteEtrEntrustPledgeByPledgeStockId(pledgeStockId);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void inserttotal(EtrSecStockConfig dto) {
    	etrSecStockConfigMapper.insert(dto);
    }
    
    public void insertptl(PtlSecStock dto) {
    	ptlSecStockMapper.insert(dto);
    }
    
    @SuppressWarnings("rawtypes")
    public List<TreeNode> getTreeNodeList(Map<String,Object> params){
    	User user = SecurityUtil.getLoginUser();
    	params.put("username", user.getUserName());
    	String portfolioId = (String)params.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
        	params.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        List<PtlPortfolio> list=etrIbRepo001Mapper.findAllTask(params);
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        TreeNode node=null;
    	for (PtlPortfolio dto : list) {
    		node = new TreeNode();
            node.setId(dto.getPortfolioId());
            node.setName(dto.getPortfolioName());
            node.setpId("组合");
            treeList.add(node);
		}
    	TreeNode node1=null;
    	node1 = new TreeNode();
        node1.setId("组合");
        node1.setName("组合");
        node1.setpId("");
        treeList.add(node1);
    	int i = 3;
    	if("InterfData".equals(params.get("taskGrpType"))){
    		i = 2;
    	}
        if("task".equals(params.get("taskGrpType"))){
            i = 2;
        }
    	if(treeList.size()<2){
    		return new ArrayList<TreeNode>();
    	}
    	return treeList;
    }
    
    public EtrIbRepoRk004Dto getName(String pledgeStockId) {
        return etrIbRepo001Mapper.getName(pledgeStockId);
    }
    
    /**
     * 质押券出库，删除即可
     *
     * @param param 参数
     * @return saveCount
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Long outSave(EtrIbRepo001Dto dto) throws Exception {
        Long deleteCount = 0L;
        if (!StringUtil.isEmptyList(dto.getSelectedItem())) {
            for (EtrIbRepo001Dto temp : dto.getSelectedItem()) {
                EtrEntrustPledge etrEntrustPledgeKey = new EtrEntrustPledge();
                etrEntrustPledgeKey.setPortfolioId(temp.getPortfolioId());
                etrEntrustPledgeKey.setFinprodId(temp.getFinprodId());
                etrEntrustPledgeKey.setBranch(temp.getBranch());
                etrEntrustPledgeKey.setSecAcctId(temp.getSecAcctId());
                etrEntrustPledgeKey.setPledgeStockId(temp.getPledgeStockId());
                
                deleteCount += etrIbRepo001Mapper.deleteEtrEntrustPledge(etrEntrustPledgeKey) ;
            }
        }
        /*if (!StringUtil.isEmptyList(dto.getSelectedItem())) {
            for (EtrIbRepo001Dto temp : dto.getSelectedItem()) {
                EtrEntrustPledgeKey etrEntrustPledgeKey = new EtrEntrustPledge();
                etrEntrustPledgeKey.setPortfolioId(temp.getPortfolioId());
                etrEntrustPledgeKey.setFinprodId(temp.getFinprodId());
                etrEntrustPledgeKey.setBranch(temp.getBranch());
                etrEntrustPledgeKey.setSecAcctId(temp.getSecAcctId());
                
                deleteCount += etrEntrustPledgeMapper.deleteByPrimaryKey(etrEntrustPledgeKey) ;
            }
        }*/
        return deleteCount;
    }
    
   public List<EtrIbRepo001Dto> selectF01List(Map<String, Object> pageParam){
	   pageParam.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
       pageParam.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
       pageParam.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
       pageParam.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
       pageParam.put("POSITION_TYPE_06", DictCodeList.持仓类型_融出.getValue());
       pageParam.put("POSITION_TYPE_08", DictCodeListExt1.持仓类型_预占用.getValue());
       pageParam.put("POSITION_TYPE_11", DictCodeListExt1.持仓类型_卖空.getValue());
       pageParam.put("POSITION_TYPE_12", DictCodeListExt1.持仓类型_卖空可用.getValue());

       pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
       pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
       pageParam.put("sysDate", iPlatformDate.getSysDateStr());
       pageParam.put("TRADEPLACE01", DictCodeListExt1.交易场所_银行间.getValue());
       pageParam.put("TRADEPLACE03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
       pageParam.put("TRADEPLACE04", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
       pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
       pageParam.put("VALUATION_SOURCE_CBC", DictCodeList.估值来源_中债登_CBC.getValue());
	   return etrIbRepo001Mapper.selectF01List(pageParam);
   }
   
   public int insertEtrEntrustPledge(EtrEntrustPledge etrEntrustPledge){
	   EtrEntrustPledge count = etrIbRepoLib001Mapper.selectEtrEntrustPledge(etrEntrustPledge) ;
       if(count != null){
       	etrIbRepoLib001Mapper.deleteEtrEntrustPledge(etrEntrustPledge) ;
       }
	   return etrEntrustPledgeMapper.insertSelective(etrEntrustPledge);
   }
   
   public List<EtrIbRepo001Dto> selectZlglPositionPage(Map<String, Object> pageParam){
	   
	   pageParam.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
       pageParam.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
       pageParam.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
       pageParam.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
       pageParam.put("POSITION_TYPE_06", DictCodeList.持仓类型_融出.getValue());
       pageParam.put("POSITION_TYPE_08", DictCodeListExt1.持仓类型_预占用.getValue());
       pageParam.put("POSITION_TYPE_11", DictCodeListExt1.持仓类型_卖空.getValue());
       pageParam.put("POSITION_TYPE_12", DictCodeListExt1.持仓类型_卖空可用.getValue());

       pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
       pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
       pageParam.put("sysDate", iPlatformDate.getSysDateStr());
       pageParam.put("TRADEPLACE01", DictCodeListExt1.交易场所_银行间.getValue());
       pageParam.put("TRADEPLACE03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
       pageParam.put("TRADEPLACE04", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
       pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
       return etrIbRepo001Mapper.findZlglPositionPage(pageParam);
   }
}


