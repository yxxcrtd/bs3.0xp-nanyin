package com.joyintech.service.entrust.etrprojectnet.service;

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
import com.joyintech.service.entrust.common.service.EntrustCommonPersonSelectService;
import com.joyintech.service.entrust.etrprojectnet.dao.EtrProjectNetMapper;
import com.joyintech.service.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.tams.common.base.dao.MstCounterPartyMapper;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.MstCounterParty;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
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

@Service("backEtrProjectNetService")
public class EtrProjectNetService extends BaseService<EtrProjectNetDto> {

    @Autowired
    private MstCounterPartyMapper mstCounterPartyMapper;
    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;
    @Autowired
    private EntrustCommonPersonSelectService entrustCommonPersonSelectService;
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

    @Autowired
    private EtrProjectNetMapper etrProjectNetMapper;

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
     * 委托指令共通
     */
    @Autowired(required=false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    /**
     * 计划类净值委托查询 <功能详细描述>
     *
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<EtrProjectNetDto> selectForPage(int page, int length, Map<String, Object> param)
            throws Exception {
        String portfolioId = (String)param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            param.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        param.put("FINPROD_TYPE2", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        param.put("PROFIT_TYPE", DictCodeList.收益类型_净值型.getValue());
        //获取投资经理真实姓名 guyunfei 2019/09/03 start
        Page<EtrProjectNetDto> pageDto = queryForPage(page, length,
                () -> etrProjectNetMapper.selectForPage(param));
        List<EtrProjectNetDto> dtoList = pageDto.getRoot();
        if(dtoList != null && dtoList.size() >0){
            dtoList = entrustCommonPersonSelectService.getRealName5(dtoList);
            pageDto.setRoot(dtoList);
        }
        return pageDto;
//        return queryForPage(page, length, () -> etrPlanAssetNMapper.selectForPage(param));
        //获取投资经理真实姓名 guyunfei 2019/09/03 end
    }

    /**
     * 获取组合代码的联想文本框 <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getentrustId(Map<String, Object> map) {
        map.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        map.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return etrProjectNetMapper.getentrustId(map);
    }

    /**
     * 获取资产编号的联想文本框 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getfinprodId(String param) {
        Map<String, String> map = new HashMap<>();
        map.put("param", param);
        map.put("planasset", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        map.put("notworth", DictCodeList.收益类型_净值型.getValue());
        return etrProjectNetMapper.getfinprodId(map);
    }

    /**
     * 获取新建界面的资产代码联想下拉框框 <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(int page, int length, Map<String, String> map) {
        map.put("planasset", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        map.put("notworth", DictCodeList.收益类型_净值型.getValue());
        return this.queryForPageForSelectReturn(page, length,()->etrProjectNetMapper.getfinprodIdForAdd(map));
    }

    /**
     * 获取新建界面的组合代码联想下拉框框 <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getentrustIdAdd(int page, int length,Map<String, Object> map) {
        map.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        map.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return this.queryForPageForSelectReturn(page, length,()->etrProjectNetMapper.getentrustIdAdd(map));
    }

    /**
     * 画面校验 <功能详细描述>
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrProjectNetDto dto) {
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
     * 撤回委托
     *
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrProjectNetDto dto)
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
     * 关闭委托 <一句话功能简述> <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay) {
        return baseEtrInvestInfoService.closeDelegate(entrustId, closeWay);
    }

    /**
     * 删除投资委托 <功能详细描述>
     *
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public boolean deleteDelegate(String entrustId) {
        return baseEtrInvestInfoService.deleteDelegate(entrustId);
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
    public void doComplete(EtrProjectNetDto dto, String operationType)
            throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
        entrustCommonDto.setEtrEntrust(etrEntrust);
        if (StringUtil.equals(operationType, "NEW")) {
            dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
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
            etrEntrustAdd.setAssetType(DictCodeListExt3.平安资产类型_权益类资产.getValue());
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
            etrEntrustAdd.setAssetType(DictCodeListExt3.平安资产类型_权益类资产.getValue());
            baseEtrInvestInfoService.newDelegate(entity);
            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        else {
            dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
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
            etrEntrustAdd.setAssetType(DictCodeListExt3.平安资产类型_权益类资产.getValue());
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
            etrEntrustAdd.setAssetType(DictCodeListExt3.平安资产类型_权益类资产.getValue());
            baseEtrInvestInfoService.updateDelegate(entity);
            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        //委托开始日对委托的持仓流水和现金流水做处理
        /*产品化_所有投资指令预占需求_权益类资产指令 add by libaisong 2020-11-18 start
         * if(baseEtrCashPositionCommService!=null){
            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
        }产品化_所有投资指令预占需求_权益类资产指令 add by libaisong 2020-11-18 end
        */
    }

    /**
     * 通过主键查询反显 <功能详细描述>
     *
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrProjectNetDto selectByKey(String entrustId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entrustId", entrustId);
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrProjectNetDto etrPlanAssetNDto = etrProjectNetMapper.selectByKey(entrustId);
        String trader = "";
        for(int i=0; i<traderList.size(); i++){
            trader = trader +","+ traderList.get(i);
        }
        etrPlanAssetNDto.getEtrInvestPersonInfo().setTrader(trader);
        return etrPlanAssetNDto;
    }

    /**
     * 根据资产代码查询资产类型 <功能详细描述>
     *
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, String> searchType(String finprodId) {
        return etrProjectNetMapper.searchType(finprodId);
    }

    /**
     * 组合可用现金查询 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
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
            // 通道交易 新增 强成西 2019/03/26 start
            if (StringUtil.isNotEmpty(param.get("chlId"))) {
                dto.setNeedAcctType(false);
                dto.setChlFinprodId((String)param.get("chlId"));
                dto.setAcctType(null);
                result.put("cashTD", basePtlCashPositionInfoService.queryUsableCashPosition(dto));
            }
            // 通道交易 新增 强成西 2019/03/26 end
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
     * 组合可用持仓查询 <功能详细描述>
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getAssetAvailablePosition(PtlSecPositionFlowDto dto) {
        BigDecimal result = null;
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
        dto.setAcctType(acctTypeList);
        if (basePtlSecPositionInfoService != null) {
            result = basePtlSecPositionInfoService.queryUsableSecPosition(dto);
        }
        return result;
    }

    /**
     * 判断交易对手类型 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public String judgeCounterType(String counterId) {
        if(StringUtil.isNotEmpty(counterId)){
            String result = etrProjectNetMapper.judgeCounterType(counterId);
            if (StringUtil.equals(DictCodeListExt1.交易对手类型_内部交易对手.getValue(), result)) {
                return DictCodeList.是否_是.getValue();
            }
            else {
                return DictCodeList.是否_否.getValue();
            }
        }
        return DictCodeList.是否_否.getValue();
    }

    /**
     * 流程启动时为附件绑定工作流代码 <功能详细描述>
     *
     * @param dto
     * @param operateType
     * @see
     * @since 1.0
     */
    public void doInsert(EtrProjectNetDto dto, String operateType) {
        // 上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if (mstFileCommonService != null) {
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
        
     	//产品化_所有投资指令预占需求_权益类资产指令 add by libaisong 2020-11-18 start
        if(baseEtrCashPositionCommService!=null 
        		&& StringUtil.equals(dto.getElementId(), "EtrProjectNet002_btn_submit")){
        	EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
            EtrEntrust etrEntrust = new EtrEntrust();
            EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
            dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
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
            etrEntrustAdd.setAssetType(DictCodeListExt3.平安资产类型_权益类资产.getValue());
            entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
            entrustCommonDto.setEtrEntrust(etrEntrust);
            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
        }
        //产品化_所有投资指令预占需求_权益类资产指令 add by libaisong 2020-11-18 end
    }
    /**
     * 获取交易对手名称
     * @param counterId
     * @return
     */
    public String getCounterName(String counterId){
        MstCounterParty mstCounterParty = mstCounterPartyMapper.selectByPrimaryKey(counterId);
        if(mstCounterParty ==null) {
            return "";
        }
        return mstCounterParty.getCounterName();
    }
}
