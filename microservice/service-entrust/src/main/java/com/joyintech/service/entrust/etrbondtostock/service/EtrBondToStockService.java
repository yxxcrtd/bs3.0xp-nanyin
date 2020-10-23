package com.joyintech.service.entrust.etrbondtostock.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.etrbondtostock.dao.EtrBondToStockMapper;
import com.joyintech.service.entrust.etrbondtostock.dto.EtrBondToStockDto;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;

/**
 * 可转债转股委托service
 * 〈功能详细描述〉
 * @author gaopeng
 * @version 1.0 2019年10月24日
 * @see EtrBondToStockService
 * @since 1.0
 */
@Service("backEtrBondToStockService")
public class EtrBondToStockService extends BaseService<EtrBondToStockDto>{

    /**
     * 平台日获取接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;
    /**
     * 附件信息共通Service
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;
    
    /**
     * 投资委托基础组件
     */
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoService;
    
    /**
     * 委托指令共通
     */
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    
    /**
     * 委托自动分发
     */
    @Autowired(required=false)
    private BusiAutoEtrDispService busiAutoEtrDispService;
    /**
     * 自定义mapper
     */
    @Autowired
    private EtrBondToStockMapper etrBondToStockMapper;
    
    /**
     * 可转债转股委托分页查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<EtrBondToStockDto> selectForPage(int page, int length, Map<String, Object> param) throws Exception {
        String portfolioId = (String)param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            param.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        param.put("ps", DictCodeListExt3.交易方向_债转股.getValue());
        param.put("type6_B14",DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
        param.put("type6_B15",DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());
        param.put("tradeMarketMode",DictCodeListExt1.交易市场_一级市场.getValue());
        return queryForPage(page, length, () -> etrBondToStockMapper.selectForPage(param));
    }
    
    /**
     * 持仓分页查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<EtrBondToStockDto> selectOtherPage(int page, int length, Map<String, Object> param) throws Exception {
        String portfolioId = (String)param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            param.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        param.put("type6_B14",DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
        param.put("type6_B15",DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());
        param.put("HODING_TYPE_03",DictCodeList.持仓类型_实际.getValue());
        param.put("HODING_TYPE_E01",DictCodeListExt1.持仓类型_委托指令可用.getValue());
        Page<EtrBondToStockDto>  pageList=       queryForPage(page, length, () -> etrBondToStockMapper.selectOtherPage(param));
        return pageList;
    }
    
    /**
     * 转债代码联想 <功能详细描述>
     * 
     * @param map 用户输入值
     * @return page<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(int page,int length,Map<String, Object> map) {
    	map.put("Bond_Type1",DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
    	map.put("Bond_Type2",DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());
        return this.queryForPageForSelectReturn(page, length,() -> etrBondToStockMapper.getFinprodId(map));
    }
    
    /**
     * 正股代码联想 <功能详细描述>
     * 
     * @param map 用户输入值
     * @return page<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getShareId(int page,int length,Map<String, Object> map) {
        return this.queryForPageForSelectReturn(page, length,() -> etrBondToStockMapper.getShareId(map));
    }
    
    /**
     * 新建页面转债代码联想 <功能详细描述>
     * 
     * @param map 用户输入值
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodIdForAdd(int page,int length,Map<String, Object> map) {
    	map.put("Bond_Type1", DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
    	map.put("Bond_Type2",DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());
        return this.queryForPageForSelectReturn(page, length,() -> etrBondToStockMapper.getFinprodIdForAdd(map));
    }

    /**
     * 校验
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrBondToStockDto dto) {
        String entrustId = dto.getEntrustId();
        String delegateOperationType = dto.getOperateType();
        String delegateCloseWay = dto.getCloseWay();
        List<String> traderList = new ArrayList<>();
        if(dto.getEtrInvestPersonInfo()!=null && StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())){
            traderList.add(dto.getEtrInvestPersonInfo().getTrader());
        }
        return baseEtrInvestInfoService.checkDelegateOperation(entrustId, delegateOperationType,
            traderList, delegateCloseWay);
    }
    /**
     * 撤回委托
     * 
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrBondToStockDto dto)
        throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        BeanUtil.copyProperties(etrEntrust, dto);
        BeanUtil.copyProperties(etrEntrustAdd, dto);
        List<EtrDistribute> etrDistributeList = new ArrayList<>();
        EtrDistribute etrDistribute = new EtrDistribute();
        BeanUtil.copyProperties(etrDistribute, dto);
        etrDistributeList.add(etrDistribute);
        entity.setEtrEntrust(etrEntrust);
        entity.setEtrEntrustAdd(etrEntrustAdd);
        entity.setEtrDistributeList(etrDistributeList);
        return baseEtrInvestInfoService.recallDelegate(entity);
    }

    /**
     * 关闭委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay) {
        return baseEtrInvestInfoService.closeDelegate(entrustId, closeWay);
    }
    
    /**
     * 获取债券持仓
     * @param pageParam 画面参数
     * @return
     */
    public Map<String, Object> getPositionInfo(Map<String, Object> pageParam) {
        Map<String, Object> ret = new HashMap<>();
        pageParam.put("hodingType",DictCodeListExt1.持仓类型_委托指令可用.getValue());
     /*   pageParam.put("hodingType",DictCodeList.持仓类型_实际.getValue());*/
//        持有
        BigDecimal holdetrPriceTotal = etrBondToStockMapper.getPositionInfo(pageParam);
//        可转
        pageParam.put("hodingType",DictCodeListExt1.持仓类型_委托指令可用.getValue());
        BigDecimal conetrPriceTotal = etrBondToStockMapper.getPositionInfo(pageParam);
        ret.put("holdetrPriceTotal",holdetrPriceTotal==null?BigDecimal.ZERO:holdetrPriceTotal);
        ret.put("conetrPriceTotal",conetrPriceTotal==null?BigDecimal.ZERO:conetrPriceTotal);
        return ret;
    }
    
    /**
     * 工作流完成方法 <功能详细描述>
     * 
     * @param dto
     * @param operationType
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public void doComplete(EtrBondToStockDto dto, String operationType)
        throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        entrustCommonDto.setEtrEntrust(etrEntrust);
        entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
        if (StringUtil.equals(operationType, "NEW")) {
        	dto.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
            dto.setIsOutTrade(BussinessDictCodeList.是否_是.getValue());
            dto.setRecStatus(BussinessDictCodeList.委托状态_待接收.getValue());
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setPs(DictCodeListExt3.交易方向_债转股.getValue());
            etrEntrust.setChlId(dto.getChlId());
            if(iPlatformDate != null){
                etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            }
            etrEntrust.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
            etrEntrust.setEntrustAmt(dto.getStocketrPriceTotal().divide(new BigDecimal(100)));
            etrEntrust.setEntrustNum(dto.getStocketrPriceTotal().divide(new BigDecimal(100)));
            etrEntrust.setTradeMarketMode(DictCodeListExt1.交易市场_一级市场.getValue());
            etrEntrustAdd.setEntrustId(dto.getEntrustId());
            etrEntrustAdd.setAssetType(BussinessDictCodeList.平安资产类型_债券.getValue());
            etrEntrustAdd.setAssetType1(DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
            etrEntrustAdd.setActNum(dto.getHoldetrPriceTotal().divide(new BigDecimal(100)));
            etrEntrustAdd.setVdate(dto.getFinvdate());
            etrEntrustAdd.setMdate(dto.getFinmdate());
            etrEntrustAdd.setExpConNum(dto.getExpConNum());
            etrEntrustAdd.setExpConValue(dto.getExpConValue());
            etrEntrustAdd.setSharePrice(dto.getSharePrice());
            etrEntrustAdd.setSecAcctId(dto.getSecAcctId());
            etrEntrustAdd.setPreb(dto.getPreb());
            etrEntrustAdd.setPrec(dto.getPrec());
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
                
                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,dto.getEtrInvestPersonInfo().getTrader());
                for(String trader:traderList){
                    EtrDistribute etrDistribute = new EtrDistribute();
                    //设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    //设置指定交易员指定交易员 = 指定交易员       
                    etrDistribute.setTrader(trader);
                    //设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
            }
            //自动分发
            if(busiAutoEtrDispService != null){
                //检测是否设置交易员
                if(!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)){
                    //跳出自动分发
                    entity.setEtrDistributeList(etrDistributeList);
                }
                else{
                    //查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if(busiAutoEtrDispService.checkEntrustTime(etrEntrust)){
                        //自动分发
                        entity.setEtrDistributeList(busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                    }
                }
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            baseEtrInvestInfoService.newDelegate(entity);
            //文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        else {
        	 dto.setIsOutTrade(BussinessDictCodeList.是否_是.getValue());
             dto.setRecStatus(BussinessDictCodeList.委托状态_待接收.getValue());
             dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
             BeanUtil.copyProperties(etrEntrust, dto);
             BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
             etrEntrust.setPs(DictCodeListExt3.交易方向_债转股.getValue());
             if(iPlatformDate != null){
                 etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
             }
             etrEntrust.setChlId(dto.getChlId());
             etrEntrust.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
             etrEntrust.setEntrustAmt(dto.getStocketrPriceTotal().divide(new BigDecimal(100)));
             etrEntrust.setEntrustNum(dto.getStocketrPriceTotal().divide(new BigDecimal(100)));
             etrEntrust.setTradeMarketMode(DictCodeListExt1.交易市场_一级市场.getValue());
             etrEntrustAdd.setEntrustId(dto.getEntrustId());
             etrEntrustAdd.setAssetType(BussinessDictCodeList.平安资产类型_债券.getValue());
             etrEntrustAdd.setAssetType1(DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
             etrEntrustAdd.setActNum(dto.getHoldetrPriceTotal().divide(new BigDecimal(100)));
             etrEntrustAdd.setVdate(dto.getFinvdate());
             etrEntrustAdd.setMdate(dto.getFinmdate());
             etrEntrustAdd.setExpConNum(dto.getExpConNum());
             etrEntrustAdd.setExpConValue(dto.getExpConValue());
             etrEntrustAdd.setSharePrice(dto.getSharePrice());
             etrEntrustAdd.setSecAcctId(dto.getSecAcctId());
             etrEntrustAdd.setPreb(dto.getPreb());
             etrEntrustAdd.setPrec(dto.getPrec());
             BeanUtil.copyProperties(etrEntrustAdd, dto);
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
                
                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,dto.getEtrInvestPersonInfo().getTrader());
                for(String trader:traderList){
                    EtrDistribute etrDistribute = new EtrDistribute();
                    //设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    //设置指定交易员指定交易员 = 指定交易员       
                    etrDistribute.setTrader(trader);
                    //设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
            }
            //自动分发
            if(busiAutoEtrDispService != null){
                //检测是否设置交易员
                if(!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)){
                    //跳出自动分发
                    entity.setEtrDistributeList(etrDistributeList);
                }
                else{
                    //查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if(busiAutoEtrDispService.checkEntrustTime(etrEntrust)){
                        //自动分发
                        entity.setEtrDistributeList(busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                    }
                }
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            baseEtrInvestInfoService.updateDelegate(entity);
            //文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
            if(baseEtrCashPositionCommService!=null){
                baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
            }
        }
    }

    /**
     * 流程启动时为附件绑定工作流代码
     * <功能详细描述>
     * @param dto
     * @param operateType
     * @see
     * @since 1.0
     */
    public void doInsert(EtrBondToStockDto dto, String operateType) {
      //上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if(mstFileCommonService != null){
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }
    /**
     * 查询修改/查看反显数据
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrBondToStockDto selectByKey(String entrustId,String sysDate) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entrustId", entrustId);
        String hodingType = DictCodeListExt1.持仓类型_委托指令可用.getValue();
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrBondToStockDto etrBondToStockDto = etrBondToStockMapper.selectByKey(entrustId,sysDate,hodingType);
        String trader = "";
        for(int i=0; i<traderList.size(); i++){
            trader = trader +","+ traderList.get(i);
        }
        etrBondToStockDto.getEtrInvestPersonInfo().setTrader(trader);
        return etrBondToStockDto;
    }
    
    public String getCounterIdByFinprodId(String finprodId) {
        return etrBondToStockMapper.getCounterId(finprodId);
    }
    
    /**
     * 判断是否证券账户
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
	public boolean getYesOrNo(String secAcctId) {
		return etrBondToStockMapper.getYesOrNo(secAcctId);
	}
}
