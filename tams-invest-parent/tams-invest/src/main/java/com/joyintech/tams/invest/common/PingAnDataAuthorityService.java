///*
// * 文件名：PingAnDataAuthorityService.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：xiexinting
// * 修改时间：2018年10月18日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
//
//package com.joyintech.tams.invest.common;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//import com.joyintech.tams.constant.DictCodeList;
//import com.joyintech.framework.constant.GlobalConstant;
//import com.joyintech.framework.security.SecurityUtil;
//import com.joyintech.framework.util.CacheUtil;
//import com.joyintech.framework.util.RequestUtil;
//import com.joyintech.framework.util.StringUtil;
//import com.joyintech.system.department.service.DataAuthorityServiceImpl;
//
//
///**
// * 数据权限类
// * 〈功能详细描述〉
// * @author xiexinting
// * @version 1.0 2018年10月18日
// * @see PingAnDataAuthorityService
// * @since 1.0
// */
//@Component
//@Primary
//public class PingAnDataAuthorityService extends DataAuthorityServiceImpl {
//    @Override
//    public List<String> ignoreMapper() {
//        List<String> ignoreList = new ArrayList<>();
//        if (!SecurityUtil.hasAuthenticated()
//            || (SecurityUtil.hasAuthenticated() && SecurityUtil.getLoginUser().isAdmin())) {
//            // 如果未登录或者登录用户为admin，则所有的sql都不需要进行权限过滤
//            ignoreList.add("com.joyintech.**.*");
//        }
//        else {
//            // 如果当前用户操作来自，非查询画面，或者为删除按钮以及行内操作按钮，则对所有SQL不做数据权限控制
//            HttpServletRequest request = RequestUtil.getCurrentRequest();
//            if(null != request) {
//                String pageIdForLog = request.getParameter("pageIdForLog");
//                String elementIdForLog = request.getParameter("elementIdForLog");
//                if(StringUtil.isNotEmpty(pageIdForLog)
//                    && (!StringUtil.contains(pageIdForLog, "001") || StringUtil.startsWith(elementIdForLog, "TABLE_ROW_") || StringUtil.endsWith(elementIdForLog, "_delete"))) {
//                    ignoreList.add("com.joyintech.**.*");
//                }
//            }
//            // 以下SQL中不需要添加数据权限控制。
//            // 框架的代码不需要做权限控制
//            ignoreList.add("com.joyintech.system.**");
//            ignoreList.add("com.joyintech.tools.**");
//            ignoreList.add("com.joyintech.workflow.**");
//            ignoreList.add("com.joyintech.risktrial.dao.SupervisionCalcDao");
//            ignoreList.add("com.joyintech.risktrial.dao.RiskTrialCommMapper.*");
//        // 一体化对应etliu start
//            ignoreList.add("com.joyintech.component.base.core.impl.dao.PlatformDateImplMapper.*");
//         // 一体化对应etliu end
////            ignoreList.add("com.joyintech.system.department.**");
////            ignoreList.add("com.joyintech.system.institution.**");
////            ignoreList.add("com.joyintech.tools.**");
////            ignoreList.add("com.joyintech.workflow.**");
////            ignoreList.add("com.joyintech.system.**.dao.Default*Mapper.*");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.*");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.findByUsername");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.findBySsoUserId");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.check");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.selectAllDept");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.selectListUserName");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.selectUserStyle");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.getSystemDate");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.findAllAuthModuleIds");
////            ignoreList.add("com.joyintech.system.user.dao.UserMapper.findAllSubDeptInstitution");
////            ignoreList.add("com.joyintech.system.actorrole.dao.SystemActorRoleMapper001.selectDataModule*");
////            ignoreList.add("com.joyintech.system.customerreport.**");
////            ignoreList.add("com.joyintech.system.base.dao.CustomerReport*Mapper.*");
////            ignoreList.add("com.joyintech.system.main.dao.MainMapper.**");
////            ignoreList.add("com.joyintech.system.syspara.dao.*Mapper.*");
////            ignoreList.add("com.joyintech.system.workflow.dao.*Mapper.*");
////            ignoreList.add("com.joyintech.system.batch.**.dao.**");
////            ignoreList.add("com.joyintech.system.dict.dao.*Mapper.*");
////            ignoreList.add("com.joyintech.system.excelimp.dao.*Mapper.*");
////            ignoreList.add("com.joyintech.system.log.dao.SysBatchLog001Mapper.*");
//            pingAnIgnoreListDeal(ignoreList);
//        }
//        return ignoreList;
//    }
//
//    /**
//     * 平安忽略数据权限控制的mapper
//     * <功能详细描述>
//     * @param ignoreList 待忽略的列表
//     * @see
//     * @since 1.0
//     */
//    private void pingAnIgnoreListDeal(List<String> ignoreList) {
//        ignoreList.add("com.joyintech.tams.invest.remind.dao.PaActiveRemind001Mapper.*");
//        ignoreList.add("com.joyintech.component.business.common.dao.DateCalcMapper.*");
//        // 一体化对应etliu start
//        ignoreList.add("com.joyintech.interf.**.*");
//        ignoreList.add("com.joyintech.tams.valuation.**.*");
//        ignoreList.add("com.joyintech.valuation.**.*");
//        ignoreList.add("com.joyintech.common.valuationinterf.**.*");
//        // 一体化对应etliu end
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public boolean enabled() {
//        return StringUtil.equals(DictCodeList.是否_是.getValue(), CacheUtil.getSysParam(GlobalConstant.DATA_AUTHORITY));
//    }
//}
