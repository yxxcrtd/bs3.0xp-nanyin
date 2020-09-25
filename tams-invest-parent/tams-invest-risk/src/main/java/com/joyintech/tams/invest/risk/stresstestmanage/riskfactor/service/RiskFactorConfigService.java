/*
 * 文件名：RiskFactor003Service01.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenyunlong
 * 修改时间：1.0 2019年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author chenyunlong
 * @version 1.0 2019年8月30日
 * @see RiskFactorConfigService
 * @since 1.0
 */
@Service
public class RiskFactorConfigService extends CommonExportService<RiskFactor003Dto> {


    public List<String> selectAll(@RequestBody Map<String, Object> param) {
        List<String> listsql = new ArrayList<String>();
        String configid = (param.get("configid") == null || param.get("configid").equals("")) ? UUID.randomUUID().toString().replaceAll("-", "") : (String) param.get("configid");
        String configsql ="";


        if (param.get("configid") == null || param.get("configid").equals("")) {
            configsql= configsql+"/*-------------------------------插入配置信息start-------------------------------------*/\n";
            configsql= configsql+"INSERT INTO STT_EXT_CONFIG (\"CONFIG_ID\", \"TABLE_NAME\", \"SELECT_COLUMN\", \"SELECT_CRITERIA\", \"INPUT_COLUMN\", \"DATE_PERIOD\", \"ASC_COLUMN\", \"DESC_COLUMN\", \"CREATE_USER\", \"CREATE_DEPT\", \"CREATE_TIME\", \"UPDATE_USER\", \"UPDATE_TIME\") VALUES" +
                    " ('" + configid + "', " +
                    "'" + param.get("tablename").toString().replaceAll("seq","=")  + "'," +
                    " '" + param.get("selectcolumn").toString().replaceAll("seq","=")  + "', " +
                    "'" + param.get("selectcriteria").toString().replaceAll("seq","=") + "', " +
                    "'" + param.get("inputcolumn") + "', " +
                    "'" + param.get("dateperiod") + "', " +
                    "'" + param.get("asccolumn") + "', " +
                    "'" + param.get("desccolumn") + "', " +
                    "'system', NULL, TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), 'system', TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'));\n";
            configsql= configsql+"/*-------------------------------插入配置信息end-------------------------------------*/\n";
        }
        param.put("sql",param.get("sql").toString().replaceAll("seq","="));

        List<RiskFactor001Dto> factor001Dtos = restTemplate.postForList(
                ServiceApplication.RISK_SERVICE_NAME +
                        "/RiskFactorConfig/findPage", param,
                RiskFactor001Dto.class);
        int i = Integer.valueOf((String) param.get("ftridcodeint"));
        configsql= configsql+"/*----------------------添加因子start-----------------------------*/\n";
        for (RiskFactor001Dto factor001Dto : factor001Dtos) {

            String ftrid = (String) param.get("ftridcode") +(String.valueOf(i).length()==1?"00"+i:String.valueOf(i).length()==2?"0"+i:String.valueOf(i));
            configsql= configsql+"INSERT INTO STT_FACTOR (" +
                    "\"FTR_ID\"," +
                    "\"FTR_NAME\"," +
                    "\"FTR_TYPE\"," +
                    "\"FTR_VALUE_STRUCT\"," +
                    "\"VALUE_EXE_TYPE\"," +
                    "\"IS_VISIBLE\"," +
                    "\"EFF_STATUS\"," +
                    "\"IS_ALL_USEABLE\"," +
                    "\"REMARK\"," +
                    "\"CREATE_USER\"," +
                    "\"CREATE_DEPT\"," +
                    "\"CREATE_TIME\"," +
                    "\"UPDATE_USER\"," +
                    "\"UPDATE_TIME\" " +
                    ")" +
                    "VALUES" +
                    "	(" +
                    "	'" + ftrid + "'," +
                    "	'" + factor001Dto.getFtrname() + "'," +
                    "	'" + param.get("ftrstatus") + "'," +
                    "	'" + param.get("ftrvaluestruct") + "'," +
                    "	'" + param.get("valueexetype") + "'," +
                    "	'E'," +
                    "	'01'," +
                    "	'E'," +
                    "	'" + factor001Dto.getRemark() + "'," +
                    "	'system'," +
                    "	NULL," +
                    "	TO_TIMESTAMP( '2019-05-24 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6' )," +
                    "	'system'," +
                    "	TO_TIMESTAMP( '2019-05-24 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6' ));\n";
            configsql= configsql+"INSERT INTO STT_FACTOR_TIPS(\"FTR_ID\", \"TIPS_CODE\", \"TIPS_VALUE\", \"DICT_CODE\", \"CREATE_USER\", \"CREATE_DEPT\", \"CREATE_TIME\", \"UPDATE_USER\", \"UPDATE_TIME\") VALUES " +
                    "('" + ftrid + "', '" + UUID.randomUUID().toString().replaceAll("-", "") + "', '" + param.get("type1") + "', 'FTR_RSK_TYPE', 'system', NULL, TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'),'system', TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'));\n" +
                    "INSERT INTO STT_FACTOR_TIPS(\"FTR_ID\", \"TIPS_CODE\", \"TIPS_VALUE\", \"DICT_CODE\", \"CREATE_USER\", \"CREATE_DEPT\", \"CREATE_TIME\", \"UPDATE_USER\", \"UPDATE_TIME\") VALUES" +
                    " ('" + ftrid + "', '" + UUID.randomUUID().toString().replaceAll("-", "") + "', '" + param.get("ftrtype2") + "', 'FTR_RSK_TYPE2', 'system', NULL, TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'),'system', TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'));\n" +
                    "INSERT INTO STT_FACTOR_TIPS(\"FTR_ID\", \"TIPS_CODE\", \"TIPS_VALUE\", \"DICT_CODE\", \"CREATE_USER\", \"CREATE_DEPT\", \"CREATE_TIME\", \"UPDATE_USER\", \"UPDATE_TIME\") VALUES" +
                    " ('" + ftrid + "','" + UUID.randomUUID().toString().replaceAll("-", "") + "', '" + param.get("type2") + "', 'FTR_RSK_TYPE_" + param.get("type1") + "', 'system', NULL, TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'),'system', TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'));\n";
            configsql= configsql+"INSERT INTO  STT_FACTOR_EXT (\"FTR_ID\",\"CONFIG_ID\",\"INPUT_VALUE\",\"CONDITION_EXT\",\"EFF_STATUS\",\"CREATE_USER\",\"CREATE_DEPT\",\"CREATE_TIME\",\"UPDATE_USER\",\"UPDATE_TIME\")values(" +
                    "'" + ftrid + "','" + configid + "','" + factor001Dto.getFtrid() + "','" + param.get("conditionext").toString().replaceAll("seq", "=") + "','E'," +
                    "'system', NULL, TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), 'system', TO_TIMESTAMP('2019-03-05 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'));\n";
            i++;

        }
        configsql= configsql+"/*----------------------添加因子end-----------------------------*/";
        listsql.add(configsql);
        return listsql;
    }

}
