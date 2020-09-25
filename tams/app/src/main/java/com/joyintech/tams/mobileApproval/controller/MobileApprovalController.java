package com.joyintech.tams.mobileApproval.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo001Service;
import com.joyintech.tams.mobileApproval.dto.TmAppAuditTabConfitDto;
import com.joyintech.tams.mobileApproval.dto.TmAppAuditTabDetailDto;
import com.joyintech.tams.mobileApproval.service.MobileApprovalService;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 移动审批
 *
 * @author houjifei
 */
@Controller
@RequestMapping("/mobileApproval/AssetMobileApproval")
public class MobileApprovalController {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    @Autowired
    private PtlManagementInfo001Service ptlManagementInfo001Service;

    private String path="https://www.baidu.com";
    /**
     * <b>方法：</b>sendVerificationCodeByuserId<br>
     * <b>描述：</b>发送验证码<br>
     * <b>作者：</b>zhuruqing<br>
     * <b>时间：</b>2020/5/1 14:24<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/sendVerificationCodeByuserId")
    public String sendVerificationCodeByuserId(HttpServletRequest request) {
        Map<String, Object> param = RequestUtil.getParameters(request);
        String phone = param.get("phone").toString();
        //调用短信平台 暂时无法做
        return "调用成功";

    }

    /**
     * <b>方法：</b>login<br>
     * <b>描述：</b>用户登录<br>
     * <b>作者：</b>zhuruqing<br>
     * <b>时间：</b>2020/5/1 14:27<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {

        return mobileApprovalService.login(request);
    }


    /**
     * <b>方法：</b>getNewTodoList<br>
     * <b>描述：</b>获取首页待办事项信息<br>
     * <b>作者：</b>zhuruqing<br>
     * <b>时间：</b>2020/5/6 11:09<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/getNewTodoList")
    public String getNewTodoList(HttpServletRequest request) throws Exception {
        //用户注册
//        String msg = mobileApprovalService.autoLogin(request);
//        if (msg.equals("用户已成功注册")) {
            Map<String, Object> param = RequestUtil.getParameters(request);
            return mobileApprovalService.getNewTodoList(param);
//        } else {
//            return msg;
//        }
    }
    
    /**
     * <b>方法：</b>getDoneList<br>
     * <b>描述：</b>获取某个业务类型的已办List<br>
     * <b>作者：</b>YH<br>
     * <b>时间：</b>2020/5/6 11:09<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/getDoneList")
    public String getDoneList(HttpServletRequest request) throws Exception {
        //用户注册
//        String msg = mobileApprovalService.autoLogin(request);
//        if (msg.equals("用户已成功注册")) {
            Map<String, Object> param = RequestUtil.getParameters(request);
            return mobileApprovalService.getDoneList(param);
//        } else {
//            return msg;
//        }
    }
    
    /**
     * <b>方法：</b>getMarkNum<br>
     * <b>描述：</b>获取待办事项角标，即待办事项数量<br>
     * <b>作者：</b>zhuruqing<br>
     * <b>时间：</b>2020/5/6 11:08<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/getMarkNum")
    public String getMarkNum(HttpServletRequest request) throws Exception {
        //用户注册
        int num = 0;
//        String msg = mobileApprovalService.autoLogin(request);
//        if (msg.equals("用户已成功注册")) {
            Map<String, Object> param = RequestUtil.getParameters(request);
            num = mobileApprovalService.getMarkNum(param.get("userId").toString());
//        }
        Map map = new HashMap();
        map.put("noticeNum", 0);
        map.put("taskNum", num);
        return MobileUtils.Objetc2Json("000", map, "");
    }


    /**
     * <b>方法：</b>getAuditTypeList<br>
     * <b>描述：</b>获取审核中心任务数据量，待办事项按照任务类型分组，统计各自数量<br>
     * <b>作者：</b>zhuruqing<br>
     * <b>时间：</b>2020/5/6 11:10<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/getAuditTypeList")
    public String getAuditTypeList(HttpServletRequest request) throws Exception {
        //用户注册
//        String msg = mobileApprovalService.autoLogin(request);
//        if (msg.equals("用户已成功注册")) {
            Map<String, Object> param = RequestUtil.getParameters(request);
            return mobileApprovalService.getAuditTypeList((String) param.get("userId"));
//        } else {
//            return msg;
//        }
    }
    
    
    /**
     * <b>方法：</b>getAuditTypeDoneList<br>
     * <b>描述：</b>获取已办审核数据类型<br>
     * <b>作者：</b>YH<br>
     * <b>时间：</b>2020/5/6 11:10<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/getAuditTypeDoneList")
    public String getAuditTypeDoneList(HttpServletRequest request) throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(request);
        return mobileApprovalService.getAuditTypeDoneList((String) param.get("userId"));
    }


    //获取待审批信息
    @ResponseBody
    @RequestMapping("/getWorkflowInfo")
    public String getWorkflowInfo(HttpServletRequest request) throws Exception {
//        System.out.println("==============================" + SecurityUtil.getLoginUser().getUserName());

        //用户注册
        String msg ;//= mobileApprovalService.autoLogin(request);
//        System.out.println("============" + request.getSession().getId());
//        String username=SecurityUtil.getLoginUser().getUserName();
//        if (msg.equals("用户已成功注册")) {
            Map<String, Object> param = RequestUtil.getParameters(request);
            if (param.containsKey("workFlowId") && param.containsKey("auditType")) {
                return mobileApprovalService.getWorkflowInfo(param);
            } else {
                msg = "工作流代码或者业务类型未传入，无法进行查询";
                return msg;
            }
//        } else {
//            return msg;
//        }
    }

    //同步移动端的处理操作
    @ResponseBody
    @RequestMapping("/dealMobileApproval")
    public String dealMobileApproval(HttpServletRequest request) throws Exception {
        //用户注册
        String msg ;//= mobileApprovalService.autoLogin(request);
//        if (msg.equals("用户已成功注册")) {
//        businessInfo
        
            Map<String, Object> param = RequestUtil.getParameters(request);
            if (param.containsKey("operationType") && param.containsKey("nextAccount")) {
                return mobileApprovalService.dealMobileApproval(param);
            } else {
                msg = "操作类型或下一核审人没有传入，无法进行审批";
                return msg;
            }
//        } else {
//            return msg;
//        }
    }

    //获取附件列表
    @ResponseBody
    @RequestMapping("/getFileList")
    public Object getFileList(HttpServletRequest request) throws IOException {
        //用户注册
        String msg;// = mobileApprovalService.autoLogin(request);
//        if (msg.equals("用户已成功注册")) {
            Map<String, Object> param = RequestUtil.getParameters(request);
            if (param.containsKey("fileNo")) {
                return mobileApprovalService.getFileList(param);
            } else {
                msg = "文件代码未传入，无法进行查询下载";
                return msg;
            }
//        } else {
//            return msg;
//        }
    }
    
    //获取附件
    @ResponseBody
    @RequestMapping("/getPDFFile")
    public Object getFile(HttpServletRequest request) throws IOException {
        //用户注册
        String msg;
            Map<String, Object> param = RequestUtil.getParameters(request);
            if (param.containsKey("fileNo")) {
                return mobileApprovalService.getFile(param);
            } else {
                msg = "文件代码未传入，无法进行查询下载";
                return msg;
            }
    }
    
    /**
     * <b>方法：</b>getAuditDetailConfig<br>
     * <b>描述：</b>获取审批详情配置<br>
     * <b>作者：</b>zhuruqing<br>
     * <b>时间：</b>2020/5/6 11:09<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/getAuditDetailConfig")
    public String getAuditDetailConfig(HttpServletRequest request) throws Exception {
    	
    	String methodName = "getAuditDetailConfig";
		String message = "获取审批详情配置";
//		info(methodName, message);
//		initLogin();
//		debug(methodName, "获取审批详情配置");
		HashMap<String, Object> results = new HashMap<String, Object>();
		JSONArray data = new JSONArray();
		String code = "000";
		String msg = "";
		Map<String, JSONArray> busi_rows_map = new HashMap<String, JSONArray>();
		try {
			// 查询审批详情标签配置
			TmAppAuditTabConfitDto tmAppAuditTabConfitDto = null;
			List<TmAppAuditTabConfitDto> AuditTabConfits = mobileApprovalService
					.getAuditDetailConfig();
			if (AuditTabConfits!=null && AuditTabConfits.size()>0) {
				for (TmAppAuditTabConfitDto AuditTabConfit : AuditTabConfits) {
					JSONObject row = new JSONObject();
					// 查询标签页内容配置
					List<TmAppAuditTabDetailDto> auditTabDetails = mobileApprovalService
							.getAuditDetailTabDetail(AuditTabConfit.getId());
					JSONArray tabContent = new JSONArray();
					if (auditTabDetails!=null && auditTabDetails.size()>0) {
						for (TmAppAuditTabDetailDto auditTabDetail : auditTabDetails) {
							JSONObject tab = new JSONObject();
							tab.put("key", auditTabDetail.getKey());
							tab.put("title", auditTabDetail.getTitle());
							tab.put("effectflag", auditTabDetail
									.getTmEffectflag());
							tabContent.add(tab);
						}
					} else {
						// throw new RuntimeException("审批详情配置未初始化!");
					}
					row.put("tabName", AuditTabConfit.getTabName());
					row.put("tabId", AuditTabConfit.getTabId());
					row.put("effectflag", AuditTabConfit.getTmEffectflag());
					row.put("tabContent", tabContent);
					// 按业务分类放入map中
					String business_type = AuditTabConfit.getBusinessType();
					if (busi_rows_map.get(business_type)==null) {
						JSONArray rows = new JSONArray();
						rows.add(row);
						busi_rows_map.put(business_type, rows);
					} else {
						busi_rows_map.get(business_type).add(row);
					}
				}
			} else {
				// throw new RuntimeException("审批详情配置未初始化!");
			}
		} catch (RuntimeException e) {
			code = "001";
			msg = e.getMessage();
		} catch (Exception e) {
			code = "110";
			msg = e.getMessage();
		}
		if (busi_rows_map!=null) {
			for (Entry<String, JSONArray> rows : busi_rows_map.entrySet()) {
				JSONObject dataTemp = new JSONObject();
				dataTemp.put("businessType", rows.getKey());
				dataTemp.put("rows", rows.getValue());
				data.add(dataTemp);
			}
		}
		results.put("code", code);
		results.put("message", msg);
		results.put("data", data);
    	
       return MobileUtils.Objetc2Json("000", data, msg);
    }
    
    /**
     * <b>方法：</b>getPath<br>
     * <b>描述：</b>获取用户名地址<br>
     * <b>作者：</b>YH<br>
     * <b>时间：</b>2020/5/1 14:27<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/getUserName")
    public String getPath(HttpServletRequest request) {

//    	通过标识获取userName
    	String userName="";
    	String msg = "获取成功";
        String code = "000";
        return MobileUtils.Objetc2Json(code,userName,msg);
    }
    
    /**
     * <b>方法：</b>authentication<br>
     * <b>描述：</b>用户登录<br>
     * <b>作者：</b>zhuruqing<br>
     * <b>时间：</b>2020/5/1 14:27<br>
     * <b>参数：</b>[request]<br>
     * <b>返回值：</b>java.lang.String<br>
     */
    @ResponseBody
    @RequestMapping("/authentication")
    public String authentication(HttpServletRequest request) {

        return mobileApprovalService.authentication(request);
    }

    
}
