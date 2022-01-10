package com.joyintech.tams.business.common.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.business.common.dao.JoyinbusinessCommonMapper;
import com.joyintech.tams.business.common.dto.CfetsOprHisInfoRequest;
import com.joyintech.tams.business.common.dto.CfetsSearchInfoRequest;
import com.joyintech.tams.business.common.dto.EntrustIdRequestDto;
import com.joyintech.tams.business.common.dto.SecManageAcctIdRequestDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 公共方法service
 */
@Service("JoyinbusinessCommonService")
public class JoyinbusinessCommonService extends BaseService {

    @Autowired
    private JoyinbusinessCommonMapper joyinbusinessCommonMapper;
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    /**
     * 根据条件查询证券账户
     *
     * @param secManageAcctIdRequestDto
     * @return List<SelectOptionDto>
     */
    public Page<SelectOptionDto> getSecManageAcctId(SecManageAcctIdRequestDto secManageAcctIdRequestDto) {
        Map<String,Object> pageParam = BeanUtil.object2Map(secManageAcctIdRequestDto);
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
        return super.queryForPage(secManageAcctIdRequestDto.getPageMode().getPageNo(), secManageAcctIdRequestDto.getPageMode().getPageSize(),
                () -> joyinbusinessCommonMapper.getSecManageAcctId(pageParam));
    }

    /**
     * 委托代码的联想文本框的查询方法
     *
     * @param entrustIdRequestDto
     * @return List<SelectOptionDto>
     */
    public Page<SelectOptionDto> getEntrustId(CfetsSearchInfoRequest entrustIdRequestDto) {
        Map<String,Object> pageParam = new HashMap<String,Object>();
        pageParam.put("search", entrustIdRequestDto.getSearch());
        pageParam.put("entrustAwait", DictCodeList.委托状态_待接收.getValue());
        pageParam.put("entrustDistribute", DictCodeListExt3.委托状态_待分发.getValue());
        pageParam.put("entrustReceive", DictCodeList.委托状态_已接收.getValue());
        pageParam.put("entrustUntread", DictCodeList.委托状态_已完成.getValue());
        pageParam.put("entrustClose", DictCodeList.委托状态_已关闭.getValue());
        pageParam.put("entrustReturn", DictCodeList.委托状态_已退回.getValue());
        pageParam.put("entrustRecall", DictCodeList.委托状态_已撤回.getValue());
        return super.queryForPage(entrustIdRequestDto.getPageMode().getPageNo(), entrustIdRequestDto.getPageMode().getPageSize(),
                () ->joyinbusinessCommonMapper.getEntrustId(pageParam));
    }
    public Page<SelectOptionDto> getfinprodIdForAdd(CfetsSearchInfoRequest dto) {
        Map<String, Object> param =new HashMap<>();
        param.put("search",dto.getSearch());
        param.put("assestType", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("TRADE_PLACE_01",DictCodeListExt1.交易场所_银行间.getValue());
        param.put("TRADE_PLACE_02",DictCodeListExt1.交易场所_商业银行柜台.getValue());
        if(StringUtil.isNotEmpty(dto.getDealType()) && StringUtil.equals(dto.getDealType(),"01")){
            //需要查询债券资产和回购期限
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.getfinprodIdOfBondAndRepo(param));
        }
        if(StringUtil.isNotEmpty(dto.getPs()) && (StringUtil.equals(dto.getPs(),DictCodeList.交易方向_行权_回售.getValue())
                                                   ||StringUtil.equals(dto.getPs(),DictCodeList.交易方向_行权_赎回.getValue()))){
            param.put("platDate",DateUtil.platDateFromCache());
            param.put("ps",dto.getPs());
            String userId = SecurityUtil.getLoginUser().getUserName();
            if (StringUtil.equals(dto.getPs(),DictCodeList.交易方向_行权_回售.getValue()) && StringUtils.isNotEmpty(userId)) {
                param.put("userId", SecurityUtil.getLoginUser().getUserName());
            }
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.getRightfinprodIdForAdd(param));

        }else{
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.getfinprodIdForAdd(param));
        }
    }
    public Page<SelectOptionDto> selectPortfolioIdList(CfetsSearchInfoRequest dto) {
        Map<String, Object> param =new HashMap<>();
        param.put("search",dto.getSearch());
        if(StringUtil.isNotEmpty(dto.getPs()) && StringUtil.equals(dto.getPs(),DictCodeList.交易方向_行权_赎回.getValue())){
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.selectPortfolioIdList(param));
        }
        String userId = SecurityUtil.getLoginUser().getUserName();
        if (StringUtils.isNotEmpty(userId)) {
            param.put("userId", SecurityUtil.getLoginUser().getUserName());
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.selectPortfolioIdList(param));
        }else{
            return null;
        }
    }
    public Page<SelectOptionDto> getManager(CfetsSearchInfoRequest dto) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("manager", dto.getSearch());
        param.put("roleOne", DictCodeList.角色岗位_投资经理.getValue());
        param.put("roleTwo", DictCodeList.角色岗位_项目经理岗.getValue());
        return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.getManagers(param));
    }
    public Boolean updateCfetsOprHis(CfetsOprHisInfoRequest dto) {
        dto.setSeqNo(IDGenerator.generate(32));
        dto.setOperDate(DateUtil.platDateFromCache());
        dto.setUserId(SecurityUtil.getLoginUser().getUserName());
        dto.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        dto.setCreateDept(CommonUtil.getDeptCode());
        dto.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        int flag = joyinbusinessCommonMapper.insertCfetsOprHis(dto);
        if (flag>0) {
            return true;
        }
        return false;
    }
    public Page<SelectOptionDto> queryMySideList(CfetsSearchInfoRequest dto) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("search", dto.getSearch());
        param.put("sysDate",iPlatformDate.getSysDate());
        String searchs = dto.getSearchs();
        if(StringUtil.isNotEmpty(searchs)){
            param.put("searchList", StringUtil.splitToList(GlobalConstant.COMMA,searchs));
        }
        if(StringUtil.equals(dto.getDealType(),"01")){
            //系统内的交易对手，即市场机构和资管产品的数据
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.queryCounterPartyList(param));
        }else if(StringUtil.equals(dto.getDealType(),"02")){
            //银行间机构，且必须关联到系统的交易对手
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.queryMySideAndPartyList(param));
        }else if(StringUtil.equals(dto.getDealType(),"03")){
            //CSTP下行接口对手方
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.queryCstpPartyList(param));
        }else{
            //组合关联的银行间机构,此处前端能拿到组合的21位码
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.queryMySideList(param));
        }
    }
    public Page<SelectOptionDto> queryMySideTraderList(CfetsSearchInfoRequest dto) {
        Map<String, Object> param = BeanUtil.object2Map(dto);
        if(StringUtil.equals(dto.getDealType(),"01")){
            //系统内的用户
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.queryUserIdList(param));
        }else{
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), ()->joyinbusinessCommonMapper.queryMySideTraderList(param));
        }
    }

    /**
     * 查询页面、按钮是否走流程
     * @param param
     * @return
     */
    public String isNeedWorkflow(Map<String, String> param) {
        param.put("noneApproval", "noneApproval");
        param.put("yesOrNo01",DictCodeList.是否_中债_是.getValue());
        param.put("yesOrNo02",DictCodeList.是否_中债_否.getValue());
        param.put("yesOrNoY",DictCodeList.是否_是.getValue());
        param.put("yesOrNoN",DictCodeList.是否_否.getValue());
        return joyinbusinessCommonMapper.isNeedWorkflow(param);
    }
}
