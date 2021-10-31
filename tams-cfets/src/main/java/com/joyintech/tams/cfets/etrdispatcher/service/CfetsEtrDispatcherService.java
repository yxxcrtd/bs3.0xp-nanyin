/*
 * 文件名：EtrBondFirstLBiddingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.etrdispatcher.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.cfets.etrdispatcher.dao.CfetsEtrDispatcherMapper;
import com.joyintech.tams.cfets.etrdispatcher.dto.*;
import com.joyintech.tams.common.base.dao.AutoDispenseRightMapper;
import com.joyintech.tams.common.base.dao.EtrAutoDispenseRuleDetailMapper;
import com.joyintech.tams.common.base.dao.EtrAutoDispenseRuleMapper;
import com.joyintech.tams.common.base.entity.AutoDispenseRight;
import com.joyintech.tams.common.base.entity.EtrAutoDispenseRule;
import com.joyintech.tams.common.base.entity.EtrAutoDispenseRuleDetail;
import com.joyintech.tams.constant.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 场外指令分发
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherService
 * @since 1.0
 */
@Service("CfetsEtrDispatcherService")
public class CfetsEtrDispatcherService extends BaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(CfetsEtrDispatcherService.class);

    @Autowired
    private CfetsEtrDispatcherMapper cfetsEtrDispatcherMapper;

    @Autowired
    private EtrAutoDispenseRuleMapper etrAutoDispenseRuleMapper;

    @Autowired
    private EtrAutoDispenseRuleDetailMapper etrAutoDispenseRuleDetailMapper;

    @Autowired
    private AutoDispenseRightMapper autoDispenseRightMapper;

    /**
     * 指令列表分页查询
     * @param dto 入数
     * @return Page<CfetsEtrDispatcherTradeListResponse>
     */
    public Page<CfetsEtrDispatcherTradeListResponse> findAllEtrDispatcherCommon(CfetsEtrDispatcherTradeListRequest dto) {
        Map<String,Object> pageParam = BeanUtil.object2Map(dto);
        pageParam.put("entrustAwait", DictCodeList.委托状态_待接收.getValue());
        pageParam.put("entrustDistribute", DictCodeListExt3.委托状态_待分发.getValue());
        pageParam.put("entrustReceive", DictCodeList.委托状态_已接收.getValue());
        pageParam.put("entrustUntread", DictCodeList.委托状态_已完成.getValue());
        pageParam.put("entrustClose", DictCodeList.委托状态_已关闭.getValue());
        pageParam.put("entrustReturn", DictCodeList.委托状态_已退回.getValue());
        pageParam.put("entrustRecall", DictCodeList.委托状态_已撤回.getValue());


        pageParam.put("busiTypePosition01", DictCodeListExt3.头寸业务类型_债券一级投标.getValue());
        pageParam.put("busiTypePosition02", DictCodeListExt3.头寸业务类型_现券交易.getValue());
        pageParam.put("busiTypePosition03", DictCodeListExt3.头寸业务类型_债券行权交易.getValue());

        String entrustStatus = (String)pageParam.get("entrustStatus");
        if(StringUtil.isNotEmpty(pageParam.get("entrustStatus"))){
            List<String> entrustStatusList = StringUtil.splitToList(",",(String)pageParam.get("entrustStatus"));
            pageParam.put("entrustStatusList", entrustStatusList);
        }
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(pageParam.get("portfolioId"))){
            List<String> portfolioIdList = StringUtil.splitToList(",",(String)pageParam.get("portfolioId"));
            pageParam.put("portfolioIdList", portfolioIdList);
        }

        String finprodMarketId = (String)pageParam.get("finprodMarketId");
        if(StringUtil.isNotEmpty(pageParam.get("finprodMarketId"))){
            List<String> finprodMarketIdList = StringUtil.splitToList(",",(String)pageParam.get("finprodMarketId"));
            pageParam.put("finprodMarketIdList", finprodMarketIdList);
        }

        String secAcctId = (String)pageParam.get("secAcctId");
        if(StringUtil.isNotEmpty(pageParam.get("secAcctId"))){
            List<String> secAcctIdList = StringUtil.splitToList(",",(String)pageParam.get("secAcctId"));
            pageParam.put("secAcctIdList", secAcctIdList);
        }

        String invManagerId = (String)pageParam.get("invManagerId");
        if(StringUtil.isNotEmpty(pageParam.get("invManagerId"))){
            List<String> invManagerIdList = StringUtil.splitToList(",",(String)pageParam.get("invManagerId"));
            pageParam.put("invManagerIdList", invManagerIdList);
        }

        String finprodType2Old = (String)pageParam.get("finprodType2");
        if (StringUtil.isNotEmpty(finprodType2Old) || StringUtil.isNotEmpty(pageParam.get("ps"))) {
            List<String> finprodType2ListOld = StringUtil.splitToList(GlobalConstant.COMMA, finprodType2Old);
//            pageParam.put("finprodType2List", finprodType2ListOld);
            //交易方向为利率互换 转换
            if(StringUtil.isNotEmpty((String)pageParam.get("ps"))){
                if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取固定.getValue()) ){
                    pageParam.put("ps", DictCodeListExt2.互换方式_收取固定.getValue());
                    pageParam.put("IRSps", DictCodeListExt2.互换方式_收取固定.getValue());
                }
                if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取浮动.getValue()) ){
                    pageParam.put("ps", DictCodeListExt2.互换方式_收取浮动.getValue());
                    pageParam.put("IRSps", DictCodeListExt2.互换方式_收取浮动.getValue());
                }
                pageParam.put("PS_T01", DictCodeList.交易方向_买入.getValue());
            }
        }
        pageParam.put("IRSType_01", DictCodeListExt2.互换方式_收取固定.getValue());
        pageParam.put("IRSType_02", DictCodeListExt2.互换方式_收取浮动.getValue());
        pageParam.put("ASSET_TYPE_07", DictCodeList.平安资产类型_拆借.getValue());
        pageParam.put("ASSET_TYPE_08", DictCodeList.平安资产类型_质押式回购.getValue());
        pageParam.put("ASSET_TYPE_10", DictCodeList.平安资产类型_买断式回购.getValue());
        pageParam.put("ASSET_TYPE_12", DictCodeListExt2.平安资产类型_债券借贷.getValue());
        pageParam.put("ASSET_TYPE_16", DictCodeListExt2.平安资产类型_利率互换.getValue());
        pageParam.put("BACK_LEND_DIRECTION", DictCode.回购拆借方向.getValue());
        pageParam.put("IRS_TYPE", DictCode.互换方式.getValue());
        pageParam.put("PS", DictCode.交易方向.getValue());
        pageParam.put("effectFlag",DictCodeListExt1.有效状态_有效.getValue());
//        //未完成状态的对应
//        if(StringUtil.isNotEmpty(entrustStatus) ){
//            if(StringUtil.equals("99", entrustStatus)){
//                pageParam.put("entrustStatus1", DictCodeList.委托状态_已接收.getValue());
//                pageParam.put("entrustStatus2", DictCodeList.委托状态_待接收.getValue());
//                pageParam.put("entrustStatus8", DictCodeListExt3.委托状态_待分发.getValue());
//            }
//        }
        pageParam.put("finType_zq", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("ps_49", DictCodeListExt3.交易方向_债转股.getValue());

        //根据组合权限表添加检索权限
        Page<CfetsEtrDispatcherTradeListResponse> pageLimit = new Page<CfetsEtrDispatcherTradeListResponse>();
        if(StringUtil.isNotEmpty(SecurityUtil.getLoginUser())) {
            pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
            pageLimit = super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(),
                    () -> cfetsEtrDispatcherMapper.findAllEtrDispatcherCommon(pageParam));
        }else{
            return pageLimit;
        }

        //将交易员和投资经理转为真实姓名
        List<CfetsEtrDispatcherTradeListResponse> list = pageLimit.getRoot();
        if(!list.isEmpty()){
//            for(CfetsEtrDispatcherTradeListResponse  cfetsEtrDispatcherTradeListResponse : list){
//                if(StringUtil.isNotEmpty(cfetsEtrDispatcherTradeListResponse.getTrader())){
//                    String[] traders = cfetsEtrDispatcherTradeListResponse.getTrader().split(",");
//                    StringBuffer buffer = new StringBuffer();
//                    StringBuffer userName = new StringBuffer();
//                    for (String trader : traders) {
//                        userName.append(trader + ",");
//                        User user=trdCommandDispatcher001Service.getRealNameByUserName(trader);
//                        if(StringUtil.isNotEmpty(user)){
//                            buffer.append(user.getRealName() + ",");
//                        }
//                    }
//                    if(buffer.toString().length() !=0){
//                        cfetsEtrDispatcherTradeListResponse.setTrader(buffer.toString().substring(0, buffer.toString().length() - 1));
//                    }
//                    if(userName.toString().length() !=0){
//                        cfetsEtrDispatcherTradeListResponse.setUserName(userName.toString().substring(0,userName.toString().length() - 1));
//                    }
//                }
//                if(StringUtil.isNotEmpty(cfetsEtrDispatcherTradeListResponse.getInvManagerId())){
//                    User user1=trdCommandDispatcher001Service.getRealNameByUserName(cfetsEtrDispatcherTradeListResponse.getInvManagerId());
//                    if(StringUtil.isNotEmpty(user1)){
//                        cfetsEtrDispatcherTradeListResponse.setInvManagerId(user1.getRealName());
//                    }
//                }
//                if(StringUtil.isNotEmpty(cfetsEtrDispatcherTradeListResponse.getBusiPs())){
//                    //委托指令附属表中取出回购/拆借方向
//                    if(StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), cfetsEtrDispatcherTradeListResponse.getFinprodType2())
//                            || StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), cfetsEtrDispatcherTradeListResponse.getFinprodType2()) ){
//                        cfetsEtrDispatcherTradeListResponse.setPs(cfetsEtrDispatcherTradeListResponse.getBusiPs());
//                    }
//                }
//                if(StringUtil.equals(cfetsEtrDispatcherTradeListResponse.getFinprodType2(), DictCodeListExt2.平安资产类型_一级市场债.getValue())){
//                    //债券一级投标交易方向的查询结果显示为空；
//                    cfetsEtrDispatcherTradeListResponse.setPs(null);
//                    TrdCommandDispatcher001Dto  dto2=trdCommandDispatcher001Service.getEtrBidInfo(dto.getEntrustId());
//                    if(StringUtil.isNotEmpty(dto2)){
//                        //列表项中委托额度后增加字段 投标标位
//                        cfetsEtrDispatcherTradeListResponse.setBidPrice(dto2.getBidPrice());
//                        // 委托额度显示的是投标委托的投标券面总额汇总；
//                        cfetsEtrDispatcherTradeListResponse.setEntrustAmt(dto2.getBidPrinAmt());
//                        //设置投标日期
//                        cfetsEtrDispatcherTradeListResponse.setBussBidDate(DateUtil.format(dto2.getBidDate()));
//                    }
//                }
//            }
            //TODO 需要特殊处理清算速度 0转成T+0
        }
        return pageLimit;
//        return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), () -> cfetsEtrDispatcherMapper.findAllEtrDispatcherCommon(pageParam));
    }

    /**
     * 自动分发规则、参数设置check
     * @author sjj
     * @param cfetsDto br
     * @return Message<AjaxResult>
     */
    public AjaxResult checkSave(CfetsEtrDispatcherAutoRequest cfetsDto, BindingResult br) {
        AjaxResult result=new AjaxResult();
        List<ValidMessage> errorList=new ArrayList<ValidMessage>();
        if(StringUtil.isEmpty(cfetsDto.getVdate())){
            errorList.add(new ValidMessage("","请选择分发日期"));
        }
        result.setErrorList(errorList);
        result.setData("请选择分发日期");
        return result;
    }

    /**
     * 删除自动分发的日期
     *
     * @return int
     */
    public void deleteEtrAutoTime() {
        cfetsEtrDispatcherMapper.deleteEtrAutoRule();
        cfetsEtrDispatcherMapper.deleteEtrAutoRuleDetail();
    }

    /**
     * 插入自动分发的日期
     *
     * @param cfetsDto
     * @return int
     */
    public int insertEtrAutoTime(CfetsEtrDispatcherAutoRequest cfetsDto) {
        try {
            String generate = IDGenerator.generate(32);
            EtrAutoDispenseRule rule = new EtrAutoDispenseRule();
            rule.setRuleId(generate);
            rule.setVdate(DateUtil.parse(cfetsDto.getVdate()));
            rule.setMdate(DateUtil.parse(cfetsDto.getMdate()));
            rule.setDispenseRule(cfetsDto.getDistributeRule());
            etrAutoDispenseRuleMapper.insertSelective(rule);
            if(StringUtil.isNotBlank(cfetsDto.getDistributeParameter())){
                String[] split = cfetsDto.getDistributeParameter().split(",");
                if(split.length>0){
                    EtrAutoDispenseRuleDetail detail = new EtrAutoDispenseRuleDetail();
                    for (String str:split) {
                        detail.setRuleId(generate);
                        detail.setDispensePara(str);
                        etrAutoDispenseRuleDetailMapper.insertSelective(detail);
                    }
                }
            }
            return 1;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return 0;
        }
    }

    /**
     * 查询交易员指令分发权限配置列表
     * @return List<CfetsEtrDispatcherTraderAuthListResponse>
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    public List<CfetsEtrDispatcherTraderAuthListResponse> getTraderAuthList( ) {
        List<CfetsEtrDispatcherTraderAuthListResponse> list =  cfetsEtrDispatcherMapper.getTraderAuthList();
        return list;
    }

    public AjaxResult checkSaveTraderAuthList(List<CfetsEtrDispatcherTraderAuthListResponse> dtoList, BindingResult br) {
        AjaxResult result=new AjaxResult();
        String returnMsg="";
        List<String> selectList = new ArrayList<String>();//存入所有交易员已选择的投资品种类
        List<String> allList = new ArrayList<String>();//存入所有的投资品种类
        if(!StringUtil.isEmptyList(dtoList)&&dtoList.size()>0)
        {
            dtoList.forEach(item->{
                if(StringUtil.isNotEmpty(item.getFinprodType2()))
                {
                    List<String> itemList= Arrays.asList(item.getFinprodType2().split(GlobalConstant.COMMA));
                    itemList.forEach(ii->
                    {
                        if(!selectList.contains(ii))
                        {
                            selectList.add(ii);
                        }
                    });
                }
            });
        }
        List<? extends DataDictItem> dataDictItemlist = SysDataUtil.getDataDictItem(DictCode.平安资产类型.getValue());
        if(!StringUtil.isEmptyList(dataDictItemlist) && dataDictItemlist.size()>0)
        {
            dataDictItemlist.forEach(data->
                    {
                        allList.add(data.getItemCode());
                    }
            );
        }
        //获取未被选择的投资品，然后分配到所有的交易员上面
        List<String> noSelectList = allList.stream().filter(item -> !selectList.contains(item)).collect(Collectors.toList());
        StringBuilder stringBuilder=new StringBuilder();
        if(!StringUtil.isEmptyList(noSelectList) &&noSelectList.size()>0){
            if(!StringUtil.isEmptyList(noSelectList)) {
                noSelectList.forEach(entity ->
                {
                    stringBuilder.append(SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,entity)+"、");
                });
            }
        }
        if(StringUtil.isNotEmpty(stringBuilder.toString())) {
            returnMsg="[" + stringBuilder.toString().substring(0,stringBuilder.toString().length()-1) + "]品种权限未选，将默认所有交易员都有此权限";
        }
        //result.setErrorList(returnMsg);
        result.setData(returnMsg);
        return result;
    }

    /**
     * 分发权限配置提交
     *
     * @return Page<CfetsEtrDispatcherTradeListResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public AjaxResult saveTraderAuthList(List<CfetsEtrDispatcherTraderAuthListResponse> dtoList) {
        AjaxResult ajaxResult =new AjaxResult();
        List<ValidMessage> errorList=new ArrayList<ValidMessage>();
        List<String> selectList = new ArrayList<String>();//存入所有交易员已选择的投资品种类
        List<String> allList = new ArrayList<String>();//存入所有的投资品种类
        if(!StringUtil.isEmptyList(dtoList)&&dtoList.size()>0)
        {
            dtoList.forEach(item->{

                if(StringUtil.isNotEmpty(item.getFinprodType2()))
                {
                    List<String> itemList= Arrays.asList(item.getFinprodType2().split(GlobalConstant.COMMA));
                    itemList.forEach(ii->
                    {
                        if(!selectList.contains(ii))
                        {
                            selectList.add(ii);
                        }
                    });
                }
            });
        }
        List<? extends DataDictItem> dataDictItemlist = SysDataUtil.getDataDictItem(DictCode.平安资产类型.getValue());
        if(!StringUtil.isEmptyList(dataDictItemlist) && dataDictItemlist.size()>0)
        {
            dataDictItemlist.forEach(data->
                    {
                        allList.add(data.getItemCode());

                    }
            );
        }
        //获取未被选择的投资品，然后分配到所有的交易员上面
        List<String> noSelectList = allList.stream().filter(item -> !selectList.contains(item)).collect(Collectors.toList());

        //新增交易员分配的权限【先删除后添加】
        try{
            cfetsEtrDispatcherMapper.deleteTraderAuthList();

            if(!StringUtil.isEmptyList(dtoList)&&dtoList.size()>0)
            {
                dtoList.forEach(item->{

                    //添加原来选择的
                    if(StringUtil.isNotEmpty(item.getFinprodType2())){
                        List<String> itemList= Arrays.asList(item.getFinprodType2().split(GlobalConstant.COMMA));
                        itemList.forEach(ii->
                        {
                            AutoDispenseRight autoDispenseRight=new AutoDispenseRight();
                            autoDispenseRight.setUserId(item.getUserName());
                            autoDispenseRight.setAssetType(ii);
                            autoDispenseRightMapper.insert(autoDispenseRight);
                        });
                    }
                    // 添加未分配的默认项
                    if(!StringUtil.isEmptyList(noSelectList))
                    {
                        noSelectList.forEach(entity->
                        {
                            AutoDispenseRight  autoDispenseRightEntity=new AutoDispenseRight();
                            autoDispenseRightEntity.setUserId(item.getUserName());
                            autoDispenseRightEntity.setAssetType(entity);
                            autoDispenseRightMapper.insert(autoDispenseRightEntity);
                        });
                    }
                });
            }
        } catch (Exception e) {
            errorList.add(new ValidMessage("分发权限配置异常！请联系管理员"));
            ajaxResult.setErrorList(errorList);
        }
        return ajaxResult;
    }

    /**
     * 指令信息明细查询
     *
     * @param dto
     * @return Page<CfetsEtrDispatcherInfoResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public CfetsEtrDispatcherInfoResponse getEtrDispatcherInfo(CfetsEtrDispatcherInfoRequest dto) {
        //dto转成map
        Map<String,Object> pageParam = BeanUtil.object2Map(dto);
        List<String> entrustIdList = StringUtil.splitToList(",",(String)pageParam.get("entrustId"));
        if(entrustIdList.size() > 1){
            return new CfetsEtrDispatcherInfoResponse();
        }else{
            return cfetsEtrDispatcherMapper.getEtrDispatcherInfo(pageParam);
        }
    }

    /**
     * 指令风险试算查询
     *
     * @param dto
     * @return Page<CfetsEtrDispatcherRskInfoResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<CfetsEtrDispatcherRskInfoResponse> getEtrDispatcherRskInfo(CfetsEtrDispatcherInfoRequest dto) {

        //dto转成map
        Map<String,Object> pageParam = BeanUtil.object2Map(dto);
        List<String> entrustIdList = StringUtil.splitToList(",",(String)pageParam.get("entrustId"));
        if(entrustIdList.size() > 1){
            return new Page<CfetsEtrDispatcherRskInfoResponse>();
        }else{
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(),
                    () -> cfetsEtrDispatcherMapper.getEtrDispatcherRskInfo(pageParam));
        }
    }

    /**
     * 交易员列表模糊查询
     * @param pageParam
     * @return
     */
    public List<CfetsEtrDispatcherTraderListRequest> getEtrTraderList(Map<String, Object> pageParam) {
        String dealers = (String)pageParam.get("search");
        List<String> list=new ArrayList<>();
//        list.add(DictCodeList.角色岗位_交易处长.getValue());
        list.add(DictCodeList.角色岗位_交易员.getValue());
//        list.add(DictCodeList.角色岗位_交易员_资本境内_岗.getValue());
//        list.add(DictCodeList.角色岗位_交易员_资本境外_岗.getValue());
        pageParam.put("rolePosList", list);
        pageParam.put("dealers", dealers);
        return cfetsEtrDispatcherMapper.getEtrTraderList(pageParam);
    }

    /**
     * 交易员与工作量列表查询
     * @param pageParam
     * @return
     */
    public List<CfetsEtrDispatcherTraderListRequest> getEtrTraderAndWorkloadList(Map<String, Object> pageParam) {
        CfetsEtrDispatcherTraderListRequest dto = new CfetsEtrDispatcherTraderListRequest();
        return cfetsEtrDispatcherMapper.getEtrTraderAndWorkloadList(pageParam);
    }
}
