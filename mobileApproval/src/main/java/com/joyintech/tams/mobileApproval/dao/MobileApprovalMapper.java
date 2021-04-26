package com.joyintech.tams.mobileApproval.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.workflow.engine.dto.WflStatusDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.joyintech.framework.security.User;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditMstInfoDto;
import com.joyintech.tams.mobileApproval.dto.OneMenuDto;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.dto.TmAppAuditTabConfitDto;
import com.joyintech.tams.mobileApproval.dto.TmAppAuditTabDetailDto;
import com.joyintech.tams.mobileApproval.dto.WflBusinessNodeDto;
import com.joyintech.tams.mobileApproval.dto.WflNextReviewerDto;

/**
 * 移动审批
 *
 * @author houjifei
 */
@Component
public interface MobileApprovalMapper {

    //查询代办流程，根据用户汇总
    List<ReturnTodoDto> queryTodoList(Map<String, Object> para);
    
    //查询用已办app端审批信息
    List<ReturnTodoDto> getMyWorkflowId(Map<String, Object> para);
    
    //查询候选人信息
    List<WflNextReviewerDto> queryCandidateUser(Map<String, String> para);

    //根据工作流查询当前处理人
    String queryCurUser(String workFlowId);

    //查询用户信息，用于自动登陆
    User findUserInfo(@Param("userName") String userName);

    //查询附件信息
    SysUploadFileInfo queryFileInfo(@Param("fileNo") String fileNo);

    /**
     * <b>方法：</b>queryMenuInfoByPageId<br>
     * <b>描述：</b>根据pageid查询根级菜单名称<br>
     * <b>作者：</b>zhuruqing<br>
     * <b>时间：</b>2020/5/6 14:32<br>
     * <b>参数：</b>pageid<br>
     * <b>返回值：</b>OneMenuDto<br>
     */
    OneMenuDto queryMenuInfoByPageId(@Param("pageid") String pageid);
    
    /**
     * 获取交易对手名称
     * @param counterId 交易对手代码
     * @return
     */
    String getCounterName(@Param("counterId") String counterId);
    
    /**
     * 获取交易日历名称
     * @param calendarId 日历编号
     * @return
     */
    String getCalendarName(@Param("calendarId") String calendarId);
    
    /**
     * 获取证券账户名称
     * @param calendarId
     * @return
     */
    String getSecAcctName(@Param("secacctid") String secacctid);
    
    /**
     * 获取资金账户名称
     * @param calendarId
     * @return
     */
    String getCustomerName(@Param("customerId") String customerId);
    
    /**
     * 获取通道名称
     * @param chlId
     * @return
     */
    String getChannelName(@Param("chlId") String chlId);
    
    /**
     * 获取组合名称
     * @param portfolioId
     * @return
     */
    String getPortfolioName(@Param("portfolioId") String portfolioId);

    /**
     * 
     * @param rank
     * @return
     */
	List<CreditMstInfoDto> getTreeCeOrgQuote(@Param("rank") String rank);

	//查询tab页
	List<TmAppAuditTabConfitDto> getAuditDetailTabConfig();
	
	//查询tab页详情
	List<TmAppAuditTabDetailDto> getAuditDetailTabDetail(@Param("tabConfigId") String tabConfigId);
	
	//登录新建token
	int createToken(@Param("userId") String userId, @Param("token") String token);
	
	//清除token
	int delToken(@Param("userId") String userId);
	
	//获取token
	String getToken(@Param("userId") String userId);
	
	List<WflBusinessNodeDto> getOpinions(@Param("workflowId") String workflowId);

    String getCustomerName1(@Param("counterId") String counterId);


    //根据CreditMstId查询品种名称
    String queryCreditMstId(@Param("creditMstId") String creditMstId);

    //查询质押券
    List<RepoInfo> findRepoInfo(@Param("finprodId") String finprodId);

    //查询pageID
    String selectForPageId(@Param("workflowId") String workflowId);


    /**
     * 获取交易对手管理中市场机构名称
     * @param chlId
     * @return
     */
    String getCustomerIdName(@Param("customerId") String customerId);


    /**
     *查询资产二三四级分类
     */
    List<SelectOptionDto> selectAssetType(Map<String, String> param);

    /**
     *查询资产二三四级分类
     */
    List<SelectOptionDto> selectAssetType1(Map<String, String> param);


    /**
     * 查询记录的操作类型
     */
    String selectActionType(@Param("workflowId") String workflowId);
    /**
     * 修改审批意见时间
     */
    List<WflStatusDto> listByWorkflowId(@Param("workflowId") String workflowId);

    String selectworkFlowId(@Param("workflowId") String workflowId);
}
