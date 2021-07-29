package com.joyintech.service.entrust.etrautomanagementaccount.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etrautomanagementaccount.dao.EtrAutoManagementAccountMapper;
import com.joyintech.service.entrust.etrautomanagementaccount.dto.EtrAutoManagementAccountDto;
import com.joyintech.tams.common.base.dao.EtrEntrustWwDetailMapper;
import com.joyintech.tams.common.base.dao.EtrEntrustWwMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service("backEtrAutoManagementAccountService")
@Transactional(rollbackFor = Exception.class)
public class EtrAutoManagementAccountService extends BaseService<EtrAutoManagementAccountDto> {
    @Autowired
    private EtrAutoManagementAccountMapper etrAutoManagementAccountMapper;
    @Autowired
    private EtrEntrustWwMapper etrEntrustWwMapper;
    @Autowired
    private EtrEntrustWwDetailMapper etrEntrustWwDetailMapper;
    /**
     * 委托指令共同
     */
    @Autowired(required=false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    /**
     * 平台日期
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    /**
     * 委托自动分发
     */
    @Autowired(required=false)
    private BusiAutoEtrDispService busiAutoEtrDispService;
    /**
     * 投资委托基础组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;





    public Page<EtrAutoManagementAccountDto> selectForPage(int page, int length,Map<String, Object> pageParam)
            throws Exception {

         Page<EtrAutoManagementAccountDto> dto = super.queryForPage(page, length, () -> etrAutoManagementAccountMapper.selectForPage(pageParam));
        return dto;
    }

    public Page<EtrAutoManagementAccountDto> selectNewForPage(int page, int length,Map<String, Object> pageParam)
            throws Exception {
        if(StringUtil.equals ("0",pageParam.get ("modelStatus"))){
            Page<EtrAutoManagementAccountDto> dto = super.queryForPage(page, length, () -> etrAutoManagementAccountMapper.getSelectPortfolioIdModel(pageParam));
            return dto;
        }else {
            Page<EtrAutoManagementAccountDto> dto = super.queryForPage(page, length, () -> etrAutoManagementAccountMapper.portfolioIdChange(pageParam));
            return dto;
        }

    }


    public Page<SelectOptionDto> getSelectPortfolioId(int page, int length, Map<String, Object> map) {

        return this.queryForPageForSelectReturn(page, length,() -> etrAutoManagementAccountMapper.getSelectPortfolioId(map));
    }

    public List<SuggestDto> getFinprodMarketId(String finprodId){
        Map<String, Object> map = new HashMap<String, Object> ();
        map.put("finprodId", finprodId);
        return etrAutoManagementAccountMapper.getFinprodMarketId(map);
    }

    public Page<SelectOptionDto> getSelectFinprodMarketId(int page,int length,Map<String, Object> param) {

        Page<SelectOptionDto> pagelist = this.queryForPageForSelectReturn(page, length,() -> etrAutoManagementAccountMapper.getSelectFinprodMarketId(param));

        return pagelist;
    }
    public List<SelectOptionDto> finprodMarketIdChange(Map<String, Object> param) {
       /* if((StringUtil.equals ((String)param.get ("finprodType2"),"F01"))){ //债券
            param.put ("dictCode", "WINDTYPEONE");
        }else if((StringUtil.equals ((String)param.get ("finprodType2"),"F06"))){ //基金
            param.put ("dictCode", "FUND_ASSET_TYPE");
        }else {
            param.put ("dictCode", "CUSTOM_ASSET_TYPE_TWO");
        }
*/
    	 param.put ("dictCode", "CUSTOM_ASSET_TYPE_TWO");
      /*  Page<SelectOptionDto> pagelist =
                this.queryForPageForSelectReturn(page, length,() -> etrAutoManagementAccountMapper.finprodMarketIdChange(param));
      */
    	 List<SelectOptionDto> pagelist=etrAutoManagementAccountMapper.finprodMarketIdChange(param);
        return pagelist;
    }

    public List<SelectOptionDto> finprodMarketIdType7Change(Map<String, Object> param) {
       /* if((StringUtil.equals ((String)param.get ("finprodType2"),"F01"))){ //债券
            param.put ("dictCode", "WINDTYPETWO");
        }else if((StringUtil.equals ((String)param.get ("finprodType2"),"F06"))){ //基金
            param.put ("dictCode", "FUND_ASSET_SECOND_TYPE");
        }*/
    	 param.put ("dictCode", "CUSTOM_ASSET_TYPE_THREE");
       /* Page<SelectOptionDto> pagelistType7 =
                this.queryForPageForSelectReturn(page, length,() -> etrAutoManagementAccountMapper.finprodMarketIdType7Change(param));
*/
    	 List<SelectOptionDto> pagelistType7=etrAutoManagementAccountMapper.finprodMarketIdType7Change(param);
        return pagelistType7;
    }


    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param entity EtrFundDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    public void doInsert(EtrAutoManagementAccountDto entity)
            throws Exception {


        //产品化_所有投资指令预占需求_公募基金指令 add by libaisong 2020-11-18 start
        if(baseEtrCashPositionCommService!=null
                && StringUtil.equals(entity.getElementId(), "EtrAutoManagementAccount002_btn_submit")){
            EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
            EtrEntrustWw etrEntrustWw = new EtrEntrustWw();
            BeanUtil.copyProperties(etrEntrustWw, entity);
//            BeanUtil.copyProperties(etrEntrustWw, entity.getBussEtrInvestPersonInfoDto());
            //设置委托状态
            etrEntrustWw.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());

            etrEntrustWw.setFinprodType2(entity.getFinprodType2 ());

//            etrEntrustWw.setIsOutTrade(DictCodeList.是否_是.getValue());
            if(iPlatformDate != null){
                etrEntrustWw.setEntrustDate(iPlatformDate.getSysDate());
            }
            // 委托指令附属表Entity
            EtrEntrustWwDetail etrEntrustWwDetail = new EtrEntrustWwDetail();

//            etrEntrustWwDetail.setAssetType(DictCodeList.平安资产类型_基金.getValue());

            BeanUtil.copyProperties(etrEntrustWwDetail, entity);

            entrustCommonDto.setEtrEntrustWw(etrEntrustWw);
            entrustCommonDto.setEtrEntrustWwDetail(etrEntrustWwDetail);
//            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
        }
        //产品化_所有投资指令预占需求_公募基金指令 add by libaisong 2020-11-18 end
    }


    public void doComplete(EtrAutoManagementAccountDto entity) throws InvocationTargetException, IllegalAccessException {
        User user = SecurityUtil.getLoginUser();
        Date sysDate = DateUtil.platDateFromCache();

        // 投资委托信息dto
        BaseEtrInvestInfoDto baseEtrInvestInfoDto = new BaseEtrInvestInfoDto();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        String type = entity.getOperateType();
        //add by tanxuan on 20200514 指令分发推送MQ  参数定义
        String isPushMsg = DictCodeList.是否_否.getValue();
        if (StringUtil.equals(type, "NEW")) {
            EtrEntrustWw etrEntrustWw = new EtrEntrustWw ();
            BeanUtil.copyProperties(etrEntrustWw, entity);
//            BeanUtil.copyProperties(etrEntrustWw, entity.getBussEtrInvestPersonInfoDto());
            //设置委托状态
            etrEntrustWw.setPortfolioId (entity.getPortfolioId ());
            etrEntrustWw.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            etrEntrustWw.setCcy (BussinessDictCodeList.币种_人民币.getValue());
            baseEtrInvestInfoDto.setEtrEntrustWw (etrEntrustWw);


            List<EtrEntrustWwDetail> list = new ArrayList<> ();
            if(entity.getSelectedItem () != null){
                for(int i = 0; i < entity.getSelectedItem ().size (); i++){
                    EtrEntrustWwDetail etrEntrustWwDetail = new EtrEntrustWwDetail();
                    BeanUtil.copyProperties(etrEntrustWwDetail, entity);

                    etrEntrustWwDetail.setEntrustId (entity.getEntrustId ());
                    etrEntrustWwDetail.setSeqNo (IDGenerator.generate());
                    etrEntrustWwDetail.setPortfolioId (entity.getSelectedItem ().get (i).getPortfolioId ());
                    etrEntrustWwDetail.setPortfolioAmt (entity.getSelectedItem ().get (i).getPortfolioAmt ());
                    etrEntrustWwDetail.setEntrustAmt (entity.getSelectedItem ().get (i).getSubjectNum ());

                    list.add (etrEntrustWwDetail);
                    entrustCommonDto.setEtrEntrustWwDetail (etrEntrustWwDetail);
                }
            }
            baseEtrInvestInfoDto.setEtrEntrustWwDetailList (list);





            List<EtrDistribute> etrDistributeList = new ArrayList<> ();
//            List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,entity.getBussEtrInvestPersonInfoDto().getTrader());
//            for(String trader:traderList){
//                EtrDistribute etrDistribute = new EtrDistribute();
//                //设置接收状态
//                etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
//                //设置指定交易员指定交易员 = 指定交易员
//                etrDistribute.setTrader(trader);
//                //设置委托代码
//                etrDistribute.setEntrustId(entity.getEntrustId());
//                //
//                etrDistributeList.add(etrDistribute);
//            }
            // List<委托完成状况表Entity>
            List<EtrFinishStatus> etrFinishStatusList = new ArrayList<>();


            if(busiAutoEtrDispService != null){
                //检测是否设置交易员
                if(!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)){
                    //跳出自动分发
                    baseEtrInvestInfoDto.setEtrDistributeList(etrDistributeList);
                    isPushMsg = DictCodeList.是否_是.getValue();
                }
                else{
                    //查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
//                    if(busiAutoEtrDispService.checkEntrustWwTime(etrEntrustWw)){
//                        //自动分发
//                        baseEtrInvestInfoDto.setEtrDistributeList(busiAutoEtrDispService.selectWwTrader(etrEntrustWw, etrEntrustWwDetail));
//                        isPushMsg = DictCodeList.是否_是.getValue();
//                    }
                }
            }
            baseEtrInvestInfoDto.setEtrFinishStatusList(etrFinishStatusList);

            // 新建委托
            if (baseEtrInvestInfoService != null) {
                baseEtrInvestInfoService.newWwDelegate(baseEtrInvestInfoDto);
            }

            entrustCommonDto.setEtrEntrustWw (etrEntrustWw);

        }else {
            EtrEntrustWw etrEntrustWw = new EtrEntrustWw ();
            BeanUtil.copyProperties(etrEntrustWw, entity);
           // BeanUtil.copyProperties(etrEntrustWw, entity.getBussEtrInvestPersonInfoDto());
            //设置委托状态
            etrEntrustWw.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            etrEntrustWw.setFinprodType2(entity.getFinprodType2 ());
            etrEntrustWw.setCcy (BussinessDictCodeList.币种_人民币.getValue());

            etrEntrustWw.setEntrustId (entity.getEntrustId ());
            etrEntrustWw.setPortfolioId (entity.getPortfolioId ());
            etrEntrustWw.setFinprodId (entity.getFinprodId ());
            etrEntrustWw.setPs (entity.getPs ());
            etrEntrustWw.setEntrustDate (entity.getEntrustDate ());
            etrEntrustWw.setEntrustAmt (entity.getEntrustAmt ());
            baseEtrInvestInfoDto.setEtrEntrustWw (etrEntrustWw);


            List<EtrEntrustWwDetail> list = new ArrayList<> ();
            if(entity.getSelectedItem () != null){
                for(int i = 0; i < entity.getSelectedItem ().size (); i++){
                    EtrEntrustWwDetail etrEntrustWwDetail = new EtrEntrustWwDetail();
                    BeanUtil.copyProperties(etrEntrustWwDetail, entity);

                    etrEntrustWwDetail.setEntrustId (entity.getEntrustId ());
                    etrEntrustWwDetail.setSeqNo (IDGenerator.generate());
                    etrEntrustWwDetail.setPortfolioId (entity.getSelectedItem ().get (i).getPortfolioId ());
                    etrEntrustWwDetail.setPortfolioAmt (entity.getSelectedItem ().get (i).getPortfolioAmt ());
                    etrEntrustWwDetail.setEntrustAmt (entity.getSelectedItem ().get (i).getSubjectNum ());

                    list.add (etrEntrustWwDetail);
                    entrustCommonDto.setEtrEntrustWwDetail (etrEntrustWwDetail);
                }
            }
            baseEtrInvestInfoDto.setEtrEntrustWwDetailList (list);





            // 修改委托
            if (baseEtrInvestInfoService != null) {
                baseEtrInvestInfoService.updateWwDelegate(baseEtrInvestInfoDto);
            }

            entrustCommonDto.setEtrEntrustWw (etrEntrustWw);
        }
    }

    /**
     * 编辑、双击查看页面详情 <功能详细描述>
     *
     * @param entrustId String
     * @return EtrFundDto
     * @see
     * @since 1.0
     */
    public EtrAutoManagementAccountDto selectByKey(String entrustId) {

        EtrAutoManagementAccountDto etrFundDto = etrAutoManagementAccountMapper.selectByKey(entrustId);

        return etrFundDto;
    }


    public List<EtrAutoManagementAccountDto> portfolioIdChange(Map<String, Object> pageParam)
            throws Exception {
        List<EtrAutoManagementAccountDto> pagelist = etrAutoManagementAccountMapper.portfolioIdChange(pageParam);

        return pagelist;
    }

    /**
     *
     * 关闭委托
     * 调用基础组件“关闭委托”
     * @param entrustId String
     * @param closeWay String
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean closeWwDelegate(String entrustId,String closeWay){
        if(baseEtrInvestInfoService != null){
            return baseEtrInvestInfoService.closeWwDelegate(entrustId, closeWay);
        }
        else{
            return false;
        }
    }

    public List<EtrAutoManagementAccountDto> selectAllData(Map<String, Object> pageParam) {
        return etrAutoManagementAccountMapper.selectAllData(pageParam);
    }
    /**
     * 获取汇总数据
     * @param entrustId
     * @return
     */
    public EtrAutoManagementAccountDto selectByKeyForOld(String entrustId) {
        EtrAutoManagementAccountDto etrFundDto = etrAutoManagementAccountMapper.selectByKeyForOld(entrustId);
        return etrFundDto;
    }
    /**
     * 获取明细数据
     * @param pageParam
     * @return
     */
    public List<EtrAutoManagementAccountDto> getSelectPortfolioIdModelForOld (Map<String, Object> pageParam){
    	return etrAutoManagementAccountMapper.getSelectPortfolioIdModelForOld(pageParam);
    }
}