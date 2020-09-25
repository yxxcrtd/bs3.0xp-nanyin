package com.joyintech.service.entrust.etrstock.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.etrstock.dao.EtrStockMapper;
import com.joyintech.service.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;


/**   
 * @title: EtrStockService
 * @package com.joyintech.service.entrust.etrstock.service
 * @description: 
 * @author: junYin
 * @date: 2019-06-04 11:16
*/
@Service
public class EtrStockService extends BaseService<EtrStockDto>{

    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;
    /**
     * 平台日获取接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;

    /**
     * 附件信息共通Service
     */
    @Autowired(required = false)
    private MstFileCommonService mstFileCommonService;

    /**
     * 组合可用持仓和实际持仓查询更新组件接口
     */
    @Autowired(required = false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    /**
     * 自定义mapper
     */
    @Autowired
    private EtrStockMapper etrStockMapper;
    
    /**
     * 投资委托基础组件
     */
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoService;
    
    /**
     * 委托自动分发
     */
    @Autowired(required=false)
    private BusiAutoEtrDispService busiAutoEtrDispService;
    /**
     * 委托指令共同
     */
    @Autowired(required=false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    /**
     *@title: selectForPage
     *@description: 股票委托查询
     *@author: junYin
     *@date: 2019/06/04 11:18
     *@param: * @param page 1
     * @param length 2
     * @param param 3
     *@return: com.joyintech.framework.grid.Page<com.joyintech.service.entrust.etrstock.dto.EtrStockDto>
     *@throws:
     */
    public Page<EtrStockDto> selectForPage(int page, int length, Map<String, Object> param) throws Exception {
        String portfolioId = (String)param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            param.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return queryForPage(page, length, () -> etrStockMapper.selectForPage(param));
    }

    /**
     *@title: getentrustId
     *@description: 获取组合代码的联想文本框
     * 支持组合代码或组合名称进行模糊查询
     *@author: junYin
     *@date: 2019/06/04 11:18
     *@param: * @param map 1
     *@return: java.util.List<com.joyintech.framework.select.SuggestDto>
     *@throws:
     */
    public List<SuggestDto> getentrustId(Map<String, Object> map) {
        return etrStockMapper.getentrustId(map);
    }

    /**
     *@title: getfinprodId
     *@description: 获取资产代码的联想文本框
     * 支持资产代码和资产简称的联想查询
     *@author: junYin
     *@date: 2019/06/04 11:18
     *@param: * @param param 1
     *@return: java.util.List<com.joyintech.framework.select.SuggestDto>
     *@throws:
     */
    public List<SuggestDto> getfinprodId(String finprodId) {
        Map<String, String> map = new HashMap<>();
        map.put("param",finprodId);
        map.put("planasset", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return etrStockMapper.getfinprodId(map);
    }

    /**
     *@title: getfinprodIdForAdd
     *@description: 获取新建界面的资产代码联想下拉框
     *@author: junYin
     *@date: 2019/06/04 11:19
     *@param: * @param page 1
     * @param length 2
     * @param map 3
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(int page, int length,Map<String, String> map) {
        map.put("planasset", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return this.queryForPageForSelectReturn(page, length,()->etrStockMapper.getfinprodIdForAdd(map));
    }

    /**
     *@title: getSelectTongDao
     *@description: 获取新建界面的通道联想下拉框
     *@author: daizhiwei
     *@date: 2019/07/03 11:19
     *@param: * @param page 1
     * @param length 2
     * @param map 3
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    public Page<SelectOptionDto> getSelectTongDao(int page,int length,Map<String, Object> map) {
        map.put("isChl","Y");
        map.put("finprodType2",DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return super.queryForPageForSelectReturn(page, length,() -> etrStockMapper.getSelectTongDao(map));
    }
    /**
     * 通道可用现金查询
     * @param map
     * @return
     */
    public BigDecimal getTongDaoCash(Map<String, Object> map) {
        map.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
        map.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        return etrStockMapper.getTongDaoCash(map);
    }

    /**
     *@title: getentrustIdAdd
     *@description: 获取新建界面的组合代码联想下拉框
     *@author: junYin
     *@date: 2019/06/04 11:20
     *@param: * @param page 1
     * @param length 2
     * @param map 3
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    public Page<SelectOptionDto> getentrustIdAdd(int page, int length,Map<String, Object> map) {
        return this.queryForPageForSelectReturn(page, length,()->etrStockMapper.getentrustIdAdd(map));
    }

    /**
     *@title: check
     *@description: 画面校验
     *@author: junYin
     *@date: 2019/06/04 11:20
     *@param: * @param dto 1
     *@return: java.util.List<com.joyintech.framework.validation.ValidMessage>
     *@throws:
     */
    public List<ValidMessage> check(EtrStockDto dto) {
        String entrustId = dto.getEntrustId();
        String delegateOperationType = dto.getOperateType();
        String delegateCloseWay = dto.getCloseWay();
        List<String> traderList = new ArrayList<>();
        if (dto.getEtrInvestPersonInfo() != null
            && StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
            traderList.add(dto.getEtrInvestPersonInfo().getTrader());
        }
        return baseEtrInvestInfoService.checkDelegateOperation(entrustId, delegateOperationType,
            traderList, delegateCloseWay);
    }

    /**
     *@title: recallDelegate
     *@description: 撤回委托
     *@author: junYin
     *@date: 2019/06/04 11:20
     *@param: * @param dto 1
     *@return: boolean
     *@throws:
     */
    public boolean recallDelegate(EtrStockDto dto) throws IllegalAccessException, InvocationTargetException {
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
     *@title: closeDelegate
     *@description: 关闭委托
     *@author: junYin
     *@date: 2019/06/04 11:20
     *@param: * @param entrustId 1
     * @param closeWay 2
     *@return: boolean
     *@throws:
     */
    public boolean closeDelegate(String entrustId, String closeWay) {
        return baseEtrInvestInfoService.closeDelegate(entrustId, closeWay);
    }

    /**
     *@title: doComplete
     *@description: 工作流完成方法
     *@author: junYin
     *@date: 2019/06/04 11:21
     *@param: * @param dto 1
     * @param operationType 2
     *@return: void
     *@throws:
     */
    public void doComplete(EtrStockDto dto, String operationType) throws IllegalAccessException, InvocationTargetException {

        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        entrustCommonDto.setEtrEntrust(etrEntrust);
        entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
        if (StringUtil.equals(operationType, "NEW")) {
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            dto.setIsOutTrade(judgeCounterType(dto.getEtrInvestPersonInfo().getCounterId()));
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto);
            BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto.getEtrInvestPersonInfo());
            if(iPlatformDate != null){
                etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            }
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
                etrEntrust.setEntrustAmt(dto.getEntrustAmt());
                etrEntrust.setEntrustNum(dto.getEntrustNum());
            }
            BeanUtil.copyPropertiesIgnoreNull(etrEntrustAdd, dto);
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
                
                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,
                    dto.getEtrInvestPersonInfo().getTrader());
                for (String trader : traderList) {
                    EtrDistribute etrDistribute = new EtrDistribute();
                    // 设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    // 设置指定交易员指定交易员 = 指定交易员
                    etrDistribute.setTrader(trader);
                    // 设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
                //entity.setEtrDistributeList(etrDistributeList);
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
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());
            baseEtrInvestInfoService.newDelegate(entity);
            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }else {
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            dto.setIsOutTrade(judgeCounterType(dto.getEtrInvestPersonInfo().getCounterId()));
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto);
            BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto.getEtrInvestPersonInfo());
            if(iPlatformDate != null){
                etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            }
            BeanUtil.copyPropertiesIgnoreNull(etrEntrustAdd, dto);
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
                
                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,
                    dto.getEtrInvestPersonInfo().getTrader());
                for (String trader : traderList) {
                    EtrDistribute etrDistribute = new EtrDistribute();
                    // 设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    // 设置指定交易员指定交易员 = 指定交易员
                    etrDistribute.setTrader(trader);
                    // 设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
                //entity.setEtrDistributeList(etrDistributeList);
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
            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        if(baseEtrCashPositionCommService!=null){
            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
        }
    }

    /**
     *@title: selectByKey
     *@description: 通过主键查询反显
     *@author: junYin
     *@date: 2019/06/04 11:22
     *@param: * @param entrustId 1
     *@return: com.joyintech.service.entrust.etrstock.dto.EtrStockDto
     *@throws:
     */
    public EtrStockDto selectByKey(String entrustId) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entrustId", entrustId);
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrStockDto EtrStockDto = etrStockMapper.selectByKey(entrustId);
        String trader = "";
        for(int i=0; i<traderList.size(); i++){
            trader = trader +","+ traderList.get(i);
        }
        EtrStockDto.getEtrInvestPersonInfo().setTrader(trader);
        return EtrStockDto;
    }

    /**
     *@title: getProAvailableCash
     *@description: 组合可用现金查询
     *@author: junYin
     *@date: 2019/06/04 11:22
     *@param: * @param param 1
     *@return: java.util.Map<java.lang.String,java.lang.Object>
     *@throws:
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {

        if (basePtlCashPositionInfoService != null) {
            PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
            Date vdate = DateUtil.parse((String)param.get("vdate"));
            dto.setCdate(vdate);
            List<String> acctTypeList = new ArrayList<>();
            acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
            dto.setAcctType(acctTypeList);
            dto.setPortfolioId((String)param.get("portfolioId"));
            dto.setCcy((String)param.get("ccy"));
            BigDecimal cashTG = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            Map<String, Object> result = new HashMap<>();
            result.put("cashTG", cashTG);
            acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
            BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            result.put("cashT", cashT);
            if (StringUtil.isNotEmpty(param.get("calId"))) {
                PtlCashPositionFlowDto dto2 = new PtlCashPositionFlowDto();
                Date vdate1 = DateUtil.parse((String)param.get("vdate1"));
                dto2.setCdate(vdate1);
                dto2.setAcctType(acctTypeList);
                dto2.setPortfolioId((String)param.get("portfolioId"));
                dto2.setCcy((String)param.get("ccy"));
                BigDecimal cashT1 = basePtlCashPositionInfoService.queryUsableCashPosition(dto2);
                result.put("cashT1", cashT1);
            }
            return result;
        }
        else {
            return null;
        }
    }

    /**
     *@title: getAssetAvailablePosition
     *@description: 组合可用持仓查询
     *@author: junYin
     *@date: 2019/06/04 11:23
     *@param: * @param dto 1
     *@return: java.math.BigDecimal
     *@throws:
     */
    public BigDecimal getAssetAvailablePosition(PtlSecPositionFlowDto dto) {

        BigDecimal result = null;
//        List<String> acctTypeList = new ArrayList<>();
//        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
//        acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
//        dto.setAcctType(acctTypeList);
        dto.setFinprodId("F10_"+dto.getFinprodId());
        if (basePtlSecPositionInfoService != null) {
            result = basePtlSecPositionInfoService.queryUsableSecPosition(dto);
        }
        return result;
    }

    /**
     *@title: judgeCounterType
     *@description: 判断交易对手类型
     *@author: junYin
     *@date: 2019/06/04 11:23
     *@param: * @param counterId 1
     *@return: java.lang.String
     *@throws:
     */
    public String judgeCounterType(String counterId) {

        String result = etrStockMapper.judgeCounterType(counterId);
        if (StringUtil.equals(DictCodeListExt1.交易对手类型_内部交易对手.getValue(), result)) {
            return DictCodeList.是否_是.getValue();
        }
        else {
            return DictCodeList.是否_否.getValue();
        }

    }

    /**
     *@title: doInsert
     *@description: 流程启动时为附件绑定工作流代码
     *@author: junYin
     *@date: 2019/06/04 11:24
     *@param: * @param dto 1
     * @param operateType 2
     *@return: void
     *@throws:
     */
    public void doInsert(EtrStockDto dto, String operateType) {

        // 上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if (mstFileCommonService != null) {
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }
    /**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getSecAcctId(Map<String, Object> param) {
        return this.queryForSuggest(() -> etrStockMapper.getSecAcctId(param),null);
    }


    /**
     * 查询估值净价
     * @author wangguiqi
     */
	public BigDecimal getClosePriceByPara(Map<String, Object> param) {
		return etrStockMapper.getClosePriceByPara(param);
	}
}
