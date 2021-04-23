package com.joyintech.tams.valuation.common.auxiliary.service;
/**
 * @author yanya
 * @create 2020-12-12 14:00
 */

import com.google.common.collect.Lists;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.service.SystemToolService;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.common.auxiliary.dao.OperationAuxiliaryMapper;
import com.joyintech.tams.valuation.common.auxiliary.dto.*;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.utils.DataHandleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName OperationAuxiliaryService
 * @Description: 运维辅助工具共通Service
 * @Author yanya
 * @Date 2020/12/28
 * @Version V1.0
 **/
@Service
public class OperationAuxiliaryService {

    /**
     * 运维辅助工具mapper
     */
    @Autowired
    OperationAuxiliaryMapper operationAuxiliaryMapper;

    /**
     * 引入此共同是为了将传入的参数解析成Map
     */
    @Autowired
    InterfCommonService interfCommonService;

    @Autowired
    private SystemToolService systemToolService;

    /**
     * 账务当前表查询共通方法
     * @param subjectNo 科目号
     * @param booksetIdTmplList 账套模板集合
     * @param booksetIdList 账套代码集合
     * @param booksetId 账套代码
     * @return
     */
    public List<BokBookdetailCurrentDto> getBookdetailCurrentData(Map<String,Object> paramMap){
        return operationAuxiliaryMapper.getBookdetailCurrentData(paramMap);
    }
    /**
     * 科目号变更共通方法
     * @param changeSubjectNoDto
     * @return
     */
    public ChangeSubjectNoResult changeSubjectNo(ChangeSubjectNoDto changeSubjectNoDto) {
        //1.获取参数
        //出参.行号 = 入参.科目变更DTO.行号
        int rowIndexNo = changeSubjectNoDto.getRowIndexNo();
        //出参.处理结果 =	"成功"
        String 处理结果 = ResultCode.SUCCESS.getCode();
        String 结果描述 = "";
        //2.处理详情
        //2.1 查询原科目号在账务当前表的数据
        Map<String,BokBussDetsub> bokBussDetSubMap = new HashMap<String,BokBussDetsub>();

        // 定义 参数条件Map               add by zwq 2021-01-06 start
        Map<String, String> conditionMap = new HashMap<>();
        // 传入的参数条件解析
        if (StringUtil.isNotEmpty(changeSubjectNoDto.getCondition())){
            conditionMap = interfCommonService.parameterConditionAnalysis(changeSubjectNoDto.getCondition());
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("subjectNo",changeSubjectNoDto.getOriSubjectNo());
        paramMap.put("booksetIdTmplList",null);
        paramMap.put("booksetIdList",changeSubjectNoDto.getBooksetIdList());
        paramMap.put("booksetId",changeSubjectNoDto.getBooksetId());
        paramMap.put("finprodType",conditionMap.get("FINPROD_TYPE"));
        paramMap.put("type2",conditionMap.get("TYPE_2"));
        paramMap.put("tradeMarket",conditionMap.get("TRADE_MARKET"));
        paramMap.put("isQtzq",conditionMap.get("IS_QTZQ"));
        List<BokBookdetailCurrentDto> oriList = this.getBookdetailCurrentData(paramMap);
        //如果账务当前表为空
        if(StringUtil.isEmptyList(oriList)) {
            //处理结果 = "提醒";
            处理结果 = ResultCode.REMIND.getCode();
            //出参.处理信息描述 = "原科目号【"+入参.原科目号+"】在核算数据表中不存在"
            结果描述 = "原科目号【" + changeSubjectNoDto.getOriSubjectNo() + "】在核算数据表中不存在";
        }
        //不为空
        else {
            //当前时间
            Date nowDate = new Date();
            //登录用户
            String updateUser = LoginUserUtil.getUserName();
            if(StringUtil.isEmpty(updateUser)) {
                updateUser = GlobalConstant.SYSTEM_USER;
            }
            //2.2 初始化【业务明细科目】数据
            //LOOP 变量.账务当前表原科目信息List 账务当前表ENTITY
            for(BokBookdetailCurrentDto tmpDto : oriList) {
                //账务当前表ENTITY.替换科目号 = 入参.替换科目号
                tmpDto.setChangeSubjectNo(changeSubjectNoDto.getChangeSubjectNo());
                //更新人
                tmpDto.setUpdateUser(updateUser);
                //更新时间
                tmpDto.setUpdateTime(nowDate);
                if(StringUtil.isEmpty(tmpDto.getDetailSubjectNo())) {
                    continue;
                }
                //账务当前表ENTITY.替换四级科目号 = 入参.替换科目号 + 账务当前表ENTITY.四级科目尾号
                tmpDto.setChangeDetailSubjectNo(changeSubjectNoDto.getChangeSubjectNo() + tmpDto.getDetailSubjectNoTail());
                //定义 变量.业务明细科目DTO

                String key = tmpDto.getBooksetId()+changeSubjectNoDto.getChangeSubjectNo() + tmpDto.getDetailSubjectNoTail();
                if(bokBussDetSubMap.get(key)==null){
                    BokBussDetsub sub = new BokBussDetsub();
                    //账套代码	= 账务当前表ENTITY.账套代码
                    sub.setBooksetId(tmpDto.getBooksetId());
                    //四级科目号 = 入参.替换科目号 + 账务当前表ENTITY.四级科目尾号
                    sub.setDetailSubjectNo(changeSubjectNoDto.getChangeSubjectNo() + tmpDto.getDetailSubjectNoTail());
                    //四级科目名称 = 入参.替换科目号名称 + 账务当前表ENTITY.四级科目尾号名称
                    sub.setDetailSubjectName(changeSubjectNoDto.getChangeSubjectName() + tmpDto.getDetailSubnameTail());
                    //上级科目号 = 入参.替换科目号
                    sub.setFsubjectId(changeSubjectNoDto.getChangeSubjectNo());
                    //四级科目尾号 = 账务当前表ENTITY.四级科目尾号
                    sub.setDetailSubjectNoTail(tmpDto.getDetailSubjectNoTail());
                    //四级科目尾号名称 = 账务当前表ENTITY.四级科目尾号名称
                    sub.setDetailSubnameTail(tmpDto.getDetailSubnameTail());
                    bokBussDetSubMap.put(key, sub);
                }
            }
            //2.3 表数据更新
            //2.3.1 业务明细科目表(BOK_BUSS_DETSUB)
            //先删再插，删除条件：账套代码、四级科目号	更新数据：变量.业务明细科目List
            int detsubNum = 0;

            //定义 变量.业务明细科目List
            List<BokBussDetsub> detsubList = new ArrayList<>();
            for(String key:bokBussDetSubMap.keySet()){
                detsubList.add(bokBussDetSubMap.get(key));
            }
            if(!StringUtil.isEmptyList(detsubList)) {
                List<List<BokBussDetsub>> splitList = DataHandleUtil.splitList(detsubList);
                for(List<BokBussDetsub> insList : splitList) {
                    if(!StringUtil.isEmptyList(insList)) {
                        operationAuxiliaryMapper.deleteBokBussDetsubBatch(insList);
                        detsubNum = detsubNum + operationAuxiliaryMapper.insertBokBussDetsubBatch(insList);
                    }
                }
            }
            //变量.结果描述 += "业务明细科目表更新数据量：“+变量.业务明细科目List.size() +“；”
            结果描述 += "业务明细科目表更新数据量：" + detsubNum + "；";
            //增值税计税流水更新数量
            int detailNum = 0;
            //账务明细历史表更新数量
            int bokAllNum = 0;
            //账务明细当前表更新数量
            int bokCurrentNum = 0;
            //2.3.2 账务明细历史表(BOK_BOOKDETAIL_ALL)、账务明细当前表(BOK_BOOKDETAIL_CURRENT)、增值税计税流水（TAX_BOK_DETAIL）
            for(BokBookdetailCurrentDto singele : oriList) {
                int num = operationAuxiliaryMapper.updateTaxBokDetail(singele);
                if(num > 0) {
                    detailNum = detailNum + num;
                }
                num = operationAuxiliaryMapper.updateTaxBokBookdetailAll(singele);
                if(num > 0) {
                    bokAllNum = bokAllNum + num;
                }
                num = operationAuxiliaryMapper.updateTaxBokBookdetailCurrent(singele);
                if(num > 0) {
                    bokCurrentNum = bokCurrentNum + num;
                }
            }
            //变量.结果描述 += "增值税计税流水表更新数据量：“+	以上更新SQL返回值 + “；”
            结果描述 += "增值税计税流水表更新数据量：" + detailNum + "；";
            //变量.结果描述 += "账务明细历史表更新数据量：“+	以上更新SQL返回值 + “；”
            结果描述 += "账务明细历史表更新数据量：" + bokAllNum + "；";
            //变量.结果描述 += "账务明细当前表更新数据量：“+	以上更新SQL返回值 + “；”
            结果描述 += "账务明细当前表更新数据量：" + bokCurrentNum + "；";
        }
        return new ChangeSubjectNoResult(rowIndexNo, 处理结果, 结果描述);
    }

    /**
     * 科目余额查询共通方法
     * @param booksetId 账套代码
     * @param balanceDate 余额日期
     * @return List<余额明细>
     */
    public List<SubjectBalance> getBokBalanceData(String booksetId, Date balanceDate) {
        return operationAuxiliaryMapper.getBokBalanceData(booksetId,balanceDate);
    }

    /**
     * 凭证最新账务查询共通
     * @param booksetId 账套代码
     * @param bookDate 入账日
     * @param happenDate 发生日
     * @return List<余额明细>
     */
    public List<SubjectBalance> getVoucherLatestAccountingData(String booksetId, Date bookDate,Date happenDate) {
        return operationAuxiliaryMapper.getVoucherLatestAccountingData(booksetId,bookDate,happenDate,BookConstant.MAX_SUBJECT_LEVEL, DictCodeList.冲正标识_正常交易.getValue());
    }


    /**
     * 科目扩展四级科目脚本生成
     *@param booksetIdsList 模板账套代码List
     * @param extendSubNoList 科目号List
     */
    public GenerateSqlDto generateSql(List<String> booksetIdsList,List<String> extendSubNoList){
        // 执行sql
        String resultSQl = "";
        GenerateSqlDto generateSqlDto = new GenerateSqlDto();
        if (extendSubNoList == null || extendSubNoList.size() == 0){
            generateSqlDto.setDealResult("0001");
            generateSqlDto.setDealMessage("未指定待扩展科目");
            return generateSqlDto;
        }

        Map<String, String> hgqxpzMap = systemToolService.getDataDictItems(DictCode.期限品种_回购);
        String hgqxpzDecode = "";
        for (String key : hgqxpzMap.keySet()){
            hgqxpzDecode += "'"+key+"','"+hgqxpzMap.get(key)+"',";
        }
        if (StringUtil.isNotEmpty(hgqxpzDecode) && hgqxpzDecode.length() > 0){
            hgqxpzDecode = hgqxpzDecode.substring(0,hgqxpzDecode.length() - 1);
        }
//        hgqxpzDecode = "DECODE("+hgqxpzDecode+")";

        Map<String, String> cjqxpzMap = systemToolService.getDataDictItems(DictCode.期限品种_拆借);
        String cjqxpzDecode = "";
        for (String key : cjqxpzMap.keySet()){
            cjqxpzDecode += "'"+key+"','"+cjqxpzMap.get(key)+"',";
        }
        if (StringUtil.isNotEmpty(cjqxpzDecode) && cjqxpzDecode.length() > 0){
            cjqxpzDecode = cjqxpzDecode.substring(0,cjqxpzDecode.length() - 1);
        }
//        cjqxpzDecode = "DECODE("+cjqxpzDecode+")";

        // 创建中间表，初始化待更新数据
        resultSQl += "/**1.科目扩展四级科目数据准备 **/\n";
        resultSQl += "/**创建临时表：提取待处理数据**/\n";
        resultSQl += "CREATE TABLE BOK_BOOKDETAIL_CURRENT_KZ AS\n" +
                "SELECT DISTINCT A.BOOKSET_ID,\n" +
                "\tB.BUSI_ID,\n" +
                "\tA.SUBJECT_NO,\n" +
                "\tE.SUBJECT_NAME,\n" +
                "\tA.DETAIL_SUBJECT_NO,\n" +
                "\tB.BUSI_DETAIL_TYPE,\n" +
                "\tB.DETAIL_SUBJECT_NO_TAIL,\n" +
                "\tB.DETAIL_SUBNAME_TAIL,\n" +
                "\tA.BOK_DETAIL_ID,\n" +
                "\tH.TERM_TYPE,\n" +
                "\tEXT.PARAM_VALUE,\n" +
                "CASE WHEN H.FINPROD_TYPE IN ('F02','F03','F04','F05','F08','F09')\n" +
                "\t\tTHEN H.TERM_TYPE\n" +
                "\tWHEN H.FINPROD_TYPE='F21'\n" +
                "\t\tTHEN DECODE(EXT.PARAM_VALUE,'02',H.TERM_TYPE,H.FINPROD_ID)\n" +
                "ELSE H.FINPROD_ID END FINPROD_ID,\n" +
                "CASE WHEN H.FINPROD_TYPE IN ('F02','F03','F04','F05')\n" +
                "\t\tTHEN DECODE(H.TERM_TYPE," + hgqxpzDecode +")\n" +
                "\tWHEN H.FINPROD_TYPE IN ('F08','F09') OR (H.FINPROD_TYPE='F21' AND EXT.PARAM_VALUE='02')\n" +
                "\t\tTHEN DECODE(H.TERM_TYPE,"+ cjqxpzDecode +")\n" +
                "ELSE H.FINPROD_NAME END FINPROD_NAME\n" +
                "FROM BOK_BOOKDETAIL_CURRENT A\n" +
                "LEFT JOIN BOK_DETAIL_ID B\n" +
                "\tON A.BOOKSET_ID = B.BOOKSET_ID\n" +
                "\tAND A.BOK_DETAIL_ID = B.BOK_DETAIL_ID\n" +
                "LEFT JOIN BOK_BOOKSET C\n" +
                "\tON A.BOOKSET_ID = C.BOOKSET_ID\n" +
                "LEFT JOIN BOK_BOOKSET_EXT EXT\n" +
                "\tON C.BOOKSET_ID = EXT.BOOKSET_ID\n" +
                "\tAND EXT.PARAM_CODE = 'TYJK_FORSUBJECT'\n" +
                "LEFT JOIN BOK_SUBJECT E\n" +
                "\tON C.BOOKSET_ID_TMPL = E.BOOKSET_ID\n" +
                "\tAND E.SUBJECT_NO = A.SUBJECT_NO\n" +
                "LEFT JOIN (SELECT D.FINPROD_ID,\n" +
                "\t\tD.FINPROD_TYPE,\n" +
                "\t\tNVL(D.FINPROD_ABBR, D.FINPROD_NAME) FINPROD_NAME,\n" +
                "\t\tE.TERM_TYPE\n" +
                "\t\tFROM FIN_PRODUCT D\n" +
                "\t\tLEFT JOIN FIN_PRODUCT_ADD E\n" +
                "\t\tON D.FINPROD_ID = E.FINPROD_ID\n" +
                "\tUNION ALL\n" +
                "\t\tSELECT G.FINPROD_ID,\n" +
                "\t\tG.FINPROD_TYPE,\n" +
                "\t\tG.FINPROD_ID AS FINPROD_NAME,\n" +
                "\t\tG.TERM_TYPE\n" +
                "\t\tFROM FIN_TRADE_PRODUCT G\n" +
                "\t\tLEFT JOIN FIN_PRODUCT_TYPE F\n" +
                "\t\tON G.FINPROD_ID = F.FINPROD_ID\n" +
                "\t\tAND G.BRANCH = F.BRANCH\n" +
                "\t\tWHERE G.BRANCH = 0\n" +
                "\t\tAND G.FINPROD_TYPE != 'F21'\n" +
                "\tUNION ALL\n" +
                "\t\tSELECT I.ACCT_NO, 'F20' FINPROD_TYPE, I.ACCT_NAME, NULL\n" +
                "\t\tFROM STL_CAPACCT_INFO I) H\n" +
                "\t\tON B.BUSI_ID = H.FINPROD_ID\n" +
                "\t\tWHERE A.DETAIL_SUBJECT_NO IS NULL\n";

        // 处理Oracle中的in语句超过1000的情况
        List<List<String>> partitions = Lists.partition(extendSubNoList, 1000);
        resultSQl += " \t\tAND (";
        for (int j = 0; j < partitions.size(); j++) {
            List<String> partition = partitions.get(j);
            resultSQl += "A.SUBJECT_NO IN (";
            for (int i = 0; i < partition.size(); i++) {
                String booksetIdTem = partition.get(i);
                if (i == extendSubNoList.size() - 1) {
                    resultSQl += "'" + booksetIdTem + "'";
                } else {
                    resultSQl += "'" + booksetIdTem + "',";
                }
            }
            if (partition.size()>0&&partition.size()<=1000){
                resultSQl += ")";
            }else {
                resultSQl += ") OR ";
            }
        }
        // 当booksetIdsList为空添加“；”避免sql未正确结束
        if (booksetIdsList == null || booksetIdsList.size()==0){
            resultSQl += ");\n";
        }else {
            resultSQl += ")\n";
        }

        // 处理Oracle中的in语句超过1000的情况
        if (!StringUtil.isEmptyList(booksetIdsList)){
            List<List<String>> partitions1 = Lists.partition(booksetIdsList, 1000);
            resultSQl += " \t\tAND (";
            for (int j = 0; j < partitions1.size(); j++) {
                List<String> partition = partitions1.get(j);
                resultSQl += "C.BOOKSET_ID_TMPL IN (";
                for (int i = 0; i < partition.size(); i++) {
                    String booksetIdTem = partition.get(i);
                    if (i == booksetIdsList.size() - 1) {
                        resultSQl += "'" + booksetIdTem + "'";
                    } else {
                        resultSQl += "'" + booksetIdTem + "',";
                    }
                }
                if (partition.size()>0&&partition.size()<=1000){
                    resultSQl += ")";
                }else {
                    resultSQl += ") OR ";
                }
            }
            resultSQl += ");\n";
        }

        // 初始化【业务明细科目 BOK_BUSS_DETSUB】数据
        resultSQl += "/**2.开始业务数据更新**/\n";
        resultSQl += "/**2.1业务明细科目表数据**/\n";
        resultSQl +="INSERT INTO BOK_BUSS_DETSUB( \n" +
                "BOOKSET_ID,DETAIL_SUBJECT_NO,DETAIL_SUBJECT_NAME,\n" +
                "FSUBJECT_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME, \n" +
                "UPDATE_USER,UPDATE_TIME,DETAIL_SUBJECT_NO_TAIL,DETAIL_SUBNAME_TAIL \n" +
                ") \n" +
                "SELECT \n" +
                "\tA.BOOKSET_ID, \n" +
                "\tA.SUBJECT_NO||NVL(A.DETAIL_SUBJECT_NO,A.FINPROD_ID) AS DETAIL_SUBJECT_NO, \n" +
                "\tA.SUBJECT_NAME||NVL(A.DETAIL_SUBNAME_TAIL,FINPROD_NAME) AS DETAIL_SUBJECT_NAME, \n" +
                "\tA.SUBJECT_NO AS FSUBJECT_ID,\n" +
                "\t'sjzl', \n" +
                "\tnull, \n" +
                "\tSYSDATE, \n" +
                "\t'sjzl', \n" +
                "\tSYSDATE, \n" +
                "\tNVL(A.DETAIL_SUBJECT_NO,A.FINPROD_ID), \n" +
                "\tNVL(A.DETAIL_SUBNAME_TAIL,FINPROD_NAME)\n" +
                "FROM BOK_BOOKDETAIL_CURRENT_KZ A \n" +
                "\tWHERE  NOT EXISTS( \n" +
                "\tSELECT 1 FROM BOK_BUSS_DETSUB B WHERE A.BOOKSET_ID = B.BOOKSET_ID\n" +
                "\tAND A.SUBJECT_NO||NVL(A.DETAIL_SUBJECT_NO,A.FINPROD_ID) = B.DETAIL_SUBJECT_NO\n" +
                "\t);\n";
        resultSQl += "\tCOMMIT;\n";


        // 更新账务明细历史表、账务明细当前表
        resultSQl += "/**2.2 账务明细代码表、账务明细历史表、账务明细当前表数据**/\n";
        resultSQl += "DECLARE\n" +
                "\tCURSOR LIST IS\n" +
                "\t\tSELECT \n" +
                "\t\tBOOKSET_ID,BUSI_ID,SUBJECT_NO,SUBJECT_NAME,DETAIL_SUBJECT_NO,\n" +
                "\t\tBUSI_DETAIL_TYPE,DETAIL_SUBJECT_NO_TAIL,DETAIL_SUBNAME_TAIL,BOK_DETAIL_ID,\n" +
                "\t\tTERM_TYPE,PARAM_VALUE,FINPROD_ID,FINPROD_NAME \n" +
                "\t\tFROM BOK_BOOKDETAIL_CURRENT_KZ; \n" +
                "\tBEGIN\n" +
                "\tFOR item IN LIST\n" +
                "LOOP\n";

        resultSQl += "/**账务明细代码表**/  \n" +
                "UPDATE BOK_DETAIL_ID  SET   \n" +
                "\tDETAIL_SUBJECT_NO_TAIL = NVL(DETAIL_SUBJECT_NO_TAIL,item.FINPROD_ID), \n" +
                "\tDETAIL_SUBNAME_TAIL = NVL(DETAIL_SUBNAME_TAIL,item.FINPROD_NAME), \n" +
                "\tUPDATE_USER ='sjzl', \n" +
                "\tUPDATE_TIME = SYSDATE\n" +
                "\tWHERE BOK_DETAIL_ID = item.BOK_DETAIL_ID \n" +
                "\tAND BOOKSET_ID = item.BOOKSET_ID \n" +
                "\tAND (DETAIL_SUBJECT_NO_TAIL IS NULL OR DETAIL_SUBNAME_TAIL IS NULL);\n" +
                "/**账务明细历史表**/\n" +
                "UPDATE BOK_BOOKDETAIL_ALL SET\n" +
                "\tDETAIL_SUBJECT_NO = SUBJECT_NO|| NVL(item.DETAIL_SUBJECT_NO_TAIL,item.FINPROD_ID),                                                                                     \n" +
                "\tUPDATE_USER ='sjzl', \n" +
                "\tUPDATE_TIME = SYSDATE\n" +
                "\tWHERE BOOKSET_ID = item.BOOKSET_ID \n" +
                "\tAND BOK_DETAIL_ID = item.BOK_DETAIL_ID\n" +
                "\tAND SUBJECT_NO = item.SUBJECT_NO;\n" +
                "/**账务明细当前表**/ \n" +
                "UPDATE BOK_BOOKDETAIL_CURRENT SET  \n" +
                "\tDETAIL_SUBJECT_NO = SUBJECT_NO|| NVL(item.DETAIL_SUBJECT_NO_TAIL,item.FINPROD_ID),                                                                                     \n" +
                "\tUPDATE_USER ='sjzl', \n" +
                "\tUPDATE_TIME = SYSDATE \n" +
                "\tWHERE BOOKSET_ID = item.BOOKSET_ID \n" +
                "\tAND BOK_DETAIL_ID = item.BOK_DETAIL_ID\n" +
                "\tAND SUBJECT_NO = item.SUBJECT_NO;  \n" +
                "COMMIT;\n" +
                "END LOOP;\n" +
                "END;\n" +
                "/\n";

        resultSQl += "/**3.结束-业务数据更新，数据验证通过后，删除过程使用的临时表**/\n";
        resultSQl += "DROP TABLE BOK_BOOKDETAIL_CURRENT_KZ;";
        generateSqlDto.setDealResult("0000");
        generateSqlDto.setDealMessage("脚本生成成功");
        generateSqlDto.setSql(resultSQl);
        return generateSqlDto;
    }
    /**
     * 科目信息变更脚本生成
     *
     * @param 待更新科目信息List
     * @return
     */
    public ChangeSubjectNoResult accountInformationChangeScriptGeneration(List<ChangeSubjectNoDto> 待更新科目信息List) {
        ChangeSubjectNoResult changeSubjectNoResult = new ChangeSubjectNoResult();
        //定义 变量.执行SQL
        StringBuilder 执行SQL = new StringBuilder();
        执行SQL.append("");
        //2.处理详情
        //2.1 待更新数据准备
        //IF 入参.待更新科目信息List IS NULL OR 入参.待更新科目信息List.SIZE()==0
        if (StringUtil.isEmptyList(待更新科目信息List)) {
            /**
             * 出参.处理结果 = "0001"
             * 出参.处理信息描述 = "未指定待更新科目"
             * 处理返回
             */
            changeSubjectNoResult.setResultCode(ResultCode.REMIND.getCode());
            changeSubjectNoResult.setResultDesc("未指定待更新科目");
            return changeSubjectNoResult;
        }
        //END IF
        //LOOP 入参.待更新科目信息List 待更新科目DTO
        for (ChangeSubjectNoDto 待更新科目DTO : 待更新科目信息List
        ) {
            //定义 变量.参数条件Map
            Map<String, String> 参数条件Map = new HashMap<>();
            //IF 待更新科目DTO.替换条件 IS NOT NULL
            if (StringUtil.isNotEmpty(待更新科目DTO.getCondition())) {
                //调用共通设计->【详细设计-接口共通方法设计.xlsx】->参数条件解析SHEET
                //变量.参数条件Map = 返回值
                参数条件Map = interfCommonService.parameterConditionAnalysis(待更新科目DTO.getCondition());
            }
            /**
             * 待更新科目DTO							赋值
             * 金融产品类型							变量.参数条件Map.get("FINPROD_TYPE")
             * 交易场所							    变量.参数条件Map.get("TRADE_MARKET")
             * 核算分类							    变量.参数条件Map.get("TYPE_2")
             * 是否其他债权							变量.参数条件Map.get("IS_QTZQ")
             */
            待更新科目DTO.setFinprodType(参数条件Map.get("FINPROD_TYPE"));
            待更新科目DTO.setTradeMarket(参数条件Map.get("TRADE_MARKET"));
            待更新科目DTO.setType2(参数条件Map.get("TYPE_2"));
            待更新科目DTO.setIsQtzq(参数条件Map.get("IS_QTZQ"));
        }
        //END LOOP
        //2.2 查询条件初始化
        //变量.执行SQL += "/*1.科目变更数据准备*/"
        执行SQL.append("/*1.科目变更数据准备*/").append("\r\n");
        //变量.执行SQL += "/*1.1 创建临时表:初始化待科目变更数据*/"
        执行SQL.append("/*1.1 创建临时表:初始化待科目变更数据*/").append("\r\n");

        执行SQL.append("create table SJZL_SUBJECT_CHANGE").append("\r\n");
        执行SQL.append("(").append("\r\n");
        执行SQL.append("  row_index_no    NUMBER(10),").append("\r\n");
        执行SQL.append("  bookset_id      VARCHAR2(50),").append("\r\n");
        执行SQL.append("  subject_no      VARCHAR2(32),").append("\r\n");
        执行SQL.append("  subject_name    VARCHAR2(200),").append("\r\n");
        执行SQL.append("  re_subject_no   VARCHAR2(32),").append("\r\n");
        执行SQL.append("  re_subject_name VARCHAR2(200),").append("\r\n");
        执行SQL.append("  task_pri        NUMBER(10),").append("\r\n");
        执行SQL.append("  finprod_type    VARCHAR2(50),").append("\r\n");
        执行SQL.append("  trade_market    VARCHAR2(50),").append("\r\n");
        执行SQL.append("  type_2          VARCHAR2(50),").append("\r\n");
        执行SQL.append("  is_qtzq         VARCHAR2(50)").append("\r\n");
        执行SQL.append(");").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.row_index_no is '行号';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.bookset_id is '账套代码';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.subject_no is '原科目号';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.subject_name is '原科目名称';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.re_subject_no is '科目号';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.re_subject_name is '科目名称';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.task_pri is '执行顺序';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.finprod_type is '金融产品类型';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.trade_market is '交易场所';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.type_2 is '核算分类';").append("\r\n");
        执行SQL.append("comment on column SJZL_SUBJECT_CHANGE.is_qtzq is '是否其他债权';").append("\r\n");
        //LOOP 入参.待更新科目信息List 待更新科目DTO
        for (ChangeSubjectNoDto item : 待更新科目信息List
        ) {
            //变量.执行SQL += "INSERT INTO SJZL_SUBJECT_CHANGE VALUES ("
            执行SQL.append("INSERT INTO SJZL_SUBJECT_CHANGE VALUES (");
            //变量.执行SQL += 待更新科目DTO.行号+","
            执行SQL.append(item.getRowIndexNo()).append(",");
            //IF 待更新科目DTO.账套代码 IS NOT NULL
            if (StringUtil.isNotEmpty(item.getBooksetId())) {
                //变量.执行SQL += "'"+待更新科目DTO.账套代码+"',"
                执行SQL.append("'").append(item.getBooksetId()).append("',");
            } else {
                //变量.执行SQL += "NULL,"
                执行SQL.append("NULL,");
            }
            //END IF
            //IF 待更新科目DTO.原科目号 IS NOT NULL
            if (StringUtil.isNotEmpty(item.getOriSubjectNo())) {
                //变量.执行SQL += "'"+待更新科目DTO.原科目号+"',"
                执行SQL.append("'").append(item.getOriSubjectNo()).append("',");
            } else {
                //变量.执行SQL += "NULL,"
                执行SQL.append("NULL,");
            }
            //END IF
            //IF 待更新科目DTO.原科目名称 IS NOT NULL
            if (StringUtil.isNotEmpty(item.getOriSubjectName())) {
                //变量.执行SQL += "'"+待更新科目DTO.原科目名称+"',"
                执行SQL.append("'").append(item.getOriSubjectName()).append("',");
            } else {
                //变量.执行SQL += "NULL,"
                执行SQL.append("NULL,");
            }
            //END IF
            //IF 待更新科目DTO.科目号 IS NOT NULL
            if (StringUtil.isNotEmpty(item.getChangeSubjectNo())) {
                //变量.执行SQL += "'"+待更新科目DTO.科目号+"',"
                执行SQL.append("'").append(item.getChangeSubjectNo()).append("',");
            } else {
                //变量.执行SQL += "NULL,"
                执行SQL.append("NULL,");
            }
            //END IF
            //IF 待更新科目DTO.科目名称 IS NOT NULL
            if (StringUtil.isNotEmpty(item.getChangeSubjectName())) {
                //变量.执行SQL += "'"+待更新科目DTO.科目名称+"',"
                执行SQL.append("'").append(item.getChangeSubjectName()).append("',");
            } else {
                //变量.执行SQL += "NULL,"
                执行SQL.append("NULL,");
            }
            //END IF
            //变量.执行SQL += 待更新科目DTO.执行顺序+","
            执行SQL.append(item.getRowPri()).append(",");
            //IF 待更新科目DTO.金融产品类型 IS NOT NULL
            if (StringUtil.isNotEmpty(item.getFinprodType())) {
                //变量.执行SQL += "'"+待更新科目DTO.金融产品类型+"',"
                执行SQL.append("'").append(item.getFinprodType()).append("',");
            } else {
                //变量.执行SQL += "NULL,"
                执行SQL.append("NULL,");
            }
            //END IF
            //IF 待更新科目DTO.交易场所 IS NOT NULL
            if (StringUtil.isNotEmpty(item.getTradeMarket())) {
                //变量.执行SQL += "'"+待更新科目DTO.交易场所+"',"
                执行SQL.append("'").append(item.getTradeMarket()).append("',");
            } else {
                //变量.执行SQL += "NULL,"
                执行SQL.append("NULL,");
            }
            //END IF
            //IF 待更新科目DTO.核算分类 IS NOT NULL
            if (StringUtil.isNotEmpty(item.getType2())) {
                //变量.执行SQL += "'"+待更新科目DTO.核算分类+"',"
                执行SQL.append("'").append(item.getType2()).append("',");
            } else {
                //变量.执行SQL += "NULL,"
                执行SQL.append("NULL,");
            }
            //END IF
            //IF 待更新科目DTO.是否其他债权 IS NOT NULL
            if (StringUtil.isNotEmpty(item.getIsQtzq())) {
                //变量.执行SQL += "'"+待更新科目DTO.是否其他债权+"');"
                执行SQL.append("'").append(item.getIsQtzq()).append("');").append("\r\n");
            } else {
                //变量.执行SQL += "NULL);"
                执行SQL.append("NULL);").append("\r\n");
            }
            //END IF
        }
        //END LOOP
        //变量.执行SQL +="COMMIT;";
        执行SQL.append("COMMIT;").append("\r\n");

        //2.3 创建中间表，初始化待更新数据
        //变量.执行SQL += "/*1.2 创建临时表：根据初始生成的科目变更信息提取待处理数据*/"
        执行SQL.append("/*1.2 创建临时表：根据初始生成的科目变更信息提取待处理数据*/").append("\r\n");
        //变量.执行SQL +=";
        执行SQL.append("CREATE TABLE BOK_BOOKDETAIL_CURRENT_CL AS").append("\r\n");
        执行SQL.append("SELECT").append("\r\n");
        执行SQL.append("   J.*,").append("\r\n");
        执行SQL.append("   S.RE_SUBJECT_NO,").append("\r\n");
        执行SQL.append("   S.RE_SUBJECT_NAME,").append("\r\n");
        执行SQL.append("   S.TASK_PRI").append("\r\n");
        执行SQL.append("FROM SJZL_SUBJECT_CHANGE S,(").append("\r\n");
        执行SQL.append("SELECT DISTINCT A.BOOKSET_ID,").append("\r\n");
        执行SQL.append("                B.BUSI_ID,").append("\r\n");
        执行SQL.append("                A.SUBJECT_NO,").append("\r\n");
        执行SQL.append("                A.DETAIL_SUBJECT_NO,").append("\r\n");
        执行SQL.append("                B.BUSI_DETAIL_TYPE,").append("\r\n");
        执行SQL.append("                B.DETAIL_SUBJECT_NO_TAIL,").append("\r\n");
        执行SQL.append("                B.DETAIL_SUBNAME_TAIL,").append("\r\n");
        执行SQL.append("                A.BOK_DETAIL_ID,").append("\r\n");
        执行SQL.append("                B.BRANCH,").append("\r\n");
        执行SQL.append("                H.TRADE_MARKET,").append("\r\n");
        执行SQL.append("                H.IS_QTZQ,").append("\r\n");
        执行SQL.append("                H.TYPE_2").append("\r\n");
        执行SQL.append("  FROM BOK_BOOKDETAIL_CURRENT A").append("\r\n");
        执行SQL.append("  LEFT JOIN BOK_DETAIL_ID B").append("\r\n");
        执行SQL.append("    ON A.BOOKSET_ID = B.BOOKSET_ID").append("\r\n");
        执行SQL.append("   AND A.BOK_DETAIL_ID = B.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("   LEFT JOIN BOK_BOOKSET C").append("\r\n");
        执行SQL.append("    ON A.BOOKSET_ID = C.BOOKSET_ID").append("\r\n");
        执行SQL.append("   LEFT JOIN (SELECT D.FINPROD_ID,").append("\r\n");
        执行SQL.append("                    D.FINPROD_TYPE,").append("\r\n");
        执行SQL.append("                    D.TRADE_MARKET,").append("\r\n");
        执行SQL.append("                    NVL(E.IS_QTZQ, 'N') IS_QTZQ,").append("\r\n");
        执行SQL.append("                    F.TYPE_2").append("\r\n");
        执行SQL.append("               FROM FIN_PRODUCT D").append("\r\n");
        执行SQL.append("               LEFT JOIN FIN_PRODUCT_ADD E").append("\r\n");
        执行SQL.append("                 ON D.FINPROD_ID = E.FINPROD_ID").append("\r\n");
        执行SQL.append("               LEFT JOIN FIN_PRODUCT_TYPE F").append("\r\n");
        执行SQL.append("                 ON D.FINPROD_ID = F.FINPROD_ID").append("\r\n");
        执行SQL.append("             UNION ALL").append("\r\n");
        执行SQL.append("             SELECT G.FINPROD_ID,").append("\r\n");
        执行SQL.append("                    G.FINPROD_TYPE,").append("\r\n");
        执行SQL.append("                    G.TRADE_MARKET,").append("\r\n");
        执行SQL.append("                    'N' IS_QTZQ,").append("\r\n");
        执行SQL.append("                    F.TYPE_2").append("\r\n");
        执行SQL.append("               FROM FIN_TRADE_PRODUCT G").append("\r\n");
        执行SQL.append("               LEFT JOIN FIN_PRODUCT_TYPE F").append("\r\n");
        执行SQL.append("                 ON G.FINPROD_ID = F.FINPROD_ID").append("\r\n");
        执行SQL.append("                AND G.BRANCH = F.BRANCH").append("\r\n");
        执行SQL.append("              WHERE G.BRANCH = 0").append("\r\n");
        执行SQL.append("                AND G.FINPROD_TYPE != 'F21') H").append("\r\n");
        执行SQL.append("    ON B.BUSI_ID = H.FINPROD_ID").append("\r\n");
        执行SQL.append(") J").append("\r\n");
        执行SQL.append("WHERE S.SUBJECT_NO = J.SUBJECT_NO").append("\r\n");
        执行SQL.append("AND NVL(S.BOOKSET_ID,'null') = CASE WHEN S.BOOKSET_ID IS NULL THEN 'null' ELSE J.BOOKSET_ID END").append("\r\n");
        执行SQL.append("AND NVL(S.FINPROD_TYPE,'null') = CASE WHEN S.FINPROD_TYPE IS NULL THEN 'null' ELSE J.BUSI_DETAIL_TYPE END").append("\r\n");
        执行SQL.append("AND NVL(S.TRADE_MARKET,'null') = CASE WHEN S.TRADE_MARKET IS NULL THEN 'null' ELSE J.TRADE_MARKET END").append("\r\n");
        执行SQL.append("AND NVL(S.IS_QTZQ,'null') = CASE WHEN S.IS_QTZQ IS NULL THEN 'null' ELSE J.IS_QTZQ END").append("\r\n");
        执行SQL.append("AND NVL(S.TYPE_2,'null') = CASE WHEN S.TYPE_2 IS NULL THEN 'null' ELSE J.TYPE_2 END").append("\r\n");
        执行SQL.append("ORDER BY S.TASK_PRI").append("\r\n");
        执行SQL.append(";").append("\r\n");
        //2.4 初始化【业务明细科目 BOK_BUSS_DETSUB】数据
        //变量.执行SQL += "/*2.开始-业务数据更新*/"
        执行SQL.append("/*2.开始-业务数据更新*/").append("\r\n");
        //变量.执行SQL += "/*2.1 业务明细科目表数据*/"
        执行SQL.append("/*2.1 业务明细科目表数据*/").append("\r\n");
        //变量.执行SQL +=";
        执行SQL.append("INSERT INTO BOK_BUSS_DETSUB(").append("\r\n");
        执行SQL.append("BOOKSET_ID,DETAIL_SUBJECT_NO,DETAIL_SUBJECT_NAME,").append("\r\n");
        执行SQL.append("FSUBJECT_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,").append("\r\n");
        执行SQL.append("UPDATE_USER,UPDATE_TIME,DETAIL_SUBJECT_NO_TAIL,DETAIL_SUBNAME_TAIL").append("\r\n");
        执行SQL.append(")").append("\r\n");
        执行SQL.append("SELECT").append("\r\n");
        执行SQL.append(" DISTINCT").append("\r\n");
        执行SQL.append(" A.BOOKSET_ID,").append("\r\n");
        执行SQL.append(" A.RE_SUBJECT_NO||A.DETAIL_SUBJECT_NO AS DETAIL_SUBJECT_NO,").append("\r\n");
        执行SQL.append(" A.RE_SUBJECT_NAME||A.DETAIL_SUBNAME_TAIL AS DETAIL_SUBJECT_NAME,").append("\r\n");
        执行SQL.append(" A.RE_SUBJECT_NO AS FSUBJECT_ID,").append("\r\n");
        执行SQL.append(" 'sjzl',").append("\r\n");
        执行SQL.append(" null,").append("\r\n");
        执行SQL.append(" SYSDATE,").append("\r\n");
        执行SQL.append(" 'sjzl',").append("\r\n");
        执行SQL.append(" SYSDATE,").append("\r\n");
        执行SQL.append(" A.DETAIL_SUBJECT_NO,").append("\r\n");
        执行SQL.append(" A.DETAIL_SUBNAME_TAIL").append("\r\n");
        执行SQL.append("FROM BOK_BOOKDETAIL_CURRENT_CL A").append("\r\n");
        执行SQL.append("WHERE A.DETAIL_SUBJECT_NO IS NOT NULL").append("\r\n");
        执行SQL.append("AND NOT EXISTS(").append("\r\n");
        执行SQL.append(" SELECT 1 FROM BOK_BUSS_DETSUB B WHERE A.BOOKSET_ID = B.BOOKSET_ID").append("\r\n");
        执行SQL.append(" AND A.RE_SUBJECT_NO||A.DETAIL_SUBJECT_NO = B.DETAIL_SUBJECT_NO").append("\r\n");
        执行SQL.append(");").append("\r\n");
        //变量.执行SQL +="COMMIT;";
        执行SQL.append("COMMIT;").append("\r\n");
        //2.5 更新账务明细历史表、账务明细当前表、增值税计税流水表数据
        //变量.执行SQL += "/*2.2 账务明细历史表、账务明细当前表、增值税计税流水表数据*/"
        执行SQL.append("/*2.2 账务明细历史表、账务明细当前表、增值税计税流水表数据*/").append("\r\n");
        //变量.执行SQL +=";
        执行SQL.append("DECLARE").append("\r\n");
        执行SQL.append("  CURSOR LIST IS ").append("\r\n");
        执行SQL.append("         SELECT").append("\r\n");
        执行SQL.append("           BOOKSET_ID,BUSI_ID,SUBJECT_NO,DETAIL_SUBJECT_NO,BUSI_DETAIL_TYPE,DETAIL_SUBJECT_NO_TAIL,").append("\r\n");
        执行SQL.append("           DETAIL_SUBNAME_TAIL,BOK_DETAIL_ID,BRANCH,TRADE_MARKET,IS_QTZQ,TYPE_2,RE_SUBJECT_NO,").append("\r\n");
        执行SQL.append("           RE_SUBJECT_NAME,TASK_PRI").append("\r\n");
        执行SQL.append("         FROM BOK_BOOKDETAIL_CURRENT_CL ORDER BY TASK_PRI;").append("\r\n");
        执行SQL.append("  BEGIN").append("\r\n");
        执行SQL.append("  FOR item IN LIST").append("\r\n");
        执行SQL.append("  LOOP").append("\r\n");
        执行SQL.append("    /*账务明细历史表*/").append("\r\n");
        执行SQL.append("    UPDATE BOK_BOOKDETAIL_ALL SET").append("\r\n");
        执行SQL.append("       SUBJECT_NO=item.RE_SUBJECT_NO,").append("\r\n");
        执行SQL.append("       DETAIL_SUBJECT_NO =CASE WHEN DETAIL_SUBJECT_NO IS NULL THEN NULL ELSE item.RE_SUBJECT_NO||item.DETAIL_SUBJECT_NO_TAIL END,").append("\r\n");
        执行SQL.append("       UPDATE_USER ='sjzl',").append("\r\n");
        执行SQL.append("       UPDATE_TIME = SYSDATE").append("\r\n");
        执行SQL.append("    WHERE BOOKSET_ID = item.BOOKSET_ID").append("\r\n");
        执行SQL.append("    AND BOK_DETAIL_ID = item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("    AND SUBJECT_NO = item.SUBJECT_NO;").append("\r\n");
        执行SQL.append("    /*账务明细当前表*/").append("\r\n");
        执行SQL.append("    UPDATE BOK_BOOKDETAIL_CURRENT SET").append("\r\n");
        执行SQL.append("       SUBJECT_NO=item.RE_SUBJECT_NO,").append("\r\n");
        执行SQL.append("       DETAIL_SUBJECT_NO =CASE WHEN DETAIL_SUBJECT_NO IS NULL THEN NULL ELSE item.RE_SUBJECT_NO||item.DETAIL_SUBJECT_NO_TAIL END,").append("\r\n");
        执行SQL.append("       UPDATE_USER ='sjzl',").append("\r\n");
        执行SQL.append("       UPDATE_TIME = SYSDATE").append("\r\n");
        执行SQL.append("    WHERE BOOKSET_ID = item.BOOKSET_ID").append("\r\n");
        执行SQL.append("    AND BOK_DETAIL_ID = item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("    AND SUBJECT_NO = item.SUBJECT_NO;").append("\r\n");
        执行SQL.append("    /*增值税计税流水*/").append("\r\n");
        执行SQL.append("    UPDATE TAX_BOK_DETAIL  SET").append("\r\n");
        执行SQL.append("       SUBJECT_NO=item.RE_SUBJECT_NO,").append("\r\n");
        执行SQL.append("       UPDATE_USER ='sjzl',").append("\r\n");
        执行SQL.append("       UPDATE_TIME = SYSDATE").append("\r\n");
        执行SQL.append("    WHERE BOOKSET_ID = item.BOOKSET_ID").append("\r\n");
        执行SQL.append("    AND BOK_DETAIL_ID = item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("    AND SUBJECT_NO = item.SUBJECT_NO;").append("\r\n");
        执行SQL.append("    COMMIT;").append("\r\n");
        执行SQL.append("  END LOOP;").append("\r\n");
        执行SQL.append("END;").append("\r\n");
        //2.6 临时表删除
        //变量.执行SQL += "/*3.结束-业务数据更新，数据验证通过后，删除过程使用的临时表*/"
        执行SQL.append("/*3.结束-业务数据更新，数据验证通过后，删除过程使用的临时表*/").append("\r\n");
        执行SQL.append("/").append("\r\n");
        //变量.执行SQL +="DROP TABLE BOK_BOOKDETAIL_CURRENT_CL;";
        执行SQL.append("DROP TABLE BOK_BOOKDETAIL_CURRENT_CL;").append("\r\n");
        //变量.执行SQL +="DROP TABLE SJZL_SUBJECT_CHANGE;";
        执行SQL.append("DROP TABLE SJZL_SUBJECT_CHANGE;");

        //3.返回结果
        //出参.处理结果 = "0000"
        changeSubjectNoResult.setResultCode(ResultCode.SUCCESS.getCode());
        //出参.执行SQL =  变量.执行SQL
        changeSubjectNoResult.setExecSql(执行SQL.toString());
        return changeSubjectNoResult;
    }

    /**
     * 账务明细代码调整脚本生成
     *
     * @param booksetIds     账套代码List
     * @param bokdetailIdOld 原账务明细代码
     * @param acctNoOld      原资金账户号
     * @param 账务明细代码DTO
     * @return
     */
    public ChangeSubjectNoResult scriptGenerationAccountingDetailCode(List<String> booksetIds, String bokdetailIdOld, String acctNoOld, BokDetailId 账务明细代码DTO) {
        ChangeSubjectNoResult changeSubjectNoResult = new ChangeSubjectNoResult();
        //1.2 定义变量
        //定义 变量.执行SQL
        StringBuilder 执行SQL = new StringBuilder();
        执行SQL.append("");
        //2.处理详情
        //定义 变量.回购期限品种Map = systemToolService.getDataDictItems(数据字典：回购期限品种)
        Map<String, String> 回购期限品种Map = systemToolService.getDataDictItems(DictCode.回购期限品种);
        //定义 变量.回购期限品种DECODE = ""
        String 回购期限品种DECODE = "";
        //LOOP 变量.回购期限品种Map 期限品种KEY
        for (String key : 回购期限品种Map.keySet()) {
            //变量.回购期限品种DECODE += "'"+期限品种KEY+"','"+变量.回购期限品种Map.get(key)+"',";
            回购期限品种DECODE += "'" + key + "','" + 回购期限品种Map.get(key) + "',";
        }
        //END LOOP
        //IF 变量.回购期限品种DECODE IS NOT NULL AND 变量.回购期限品种DECODE.length >0
        if (StringUtil.isNotEmpty(回购期限品种DECODE)) {
            //变量.回购期限品种DECODE = 变量.回购期限品种DECODE.substr(0,变量.回购期限品种DECODE.length-1)
            回购期限品种DECODE = 回购期限品种DECODE.substring(0, 回购期限品种DECODE.length() - 1);
        }
        //END IF
        //变量.回购期限品种DECOD = " DECODE("+变量.回购期限品种DECOD+") "
        //回购期限品种DECODE = " DECODE(" + 回购期限品种DECODE + ") ";
        //定义 变量.拆借期限品种Map = systemToolService.getDataDictItems(数据字典：拆借期限品种)
        Map<String, String> 拆借期限品种Map = systemToolService.getDataDictItems(DictCode.期限品种_拆借);
        //定义 变量.拆借期限品种DECODE = ""
        String 拆借期限品种DECODE = "";
        //LOOP 变量.拆借期限品种Map 期限品种KEY
        for (String key : 拆借期限品种Map.keySet()) {
            //变量.拆借期限品种DECODE += "'"+期限品种KEY+"','"+变量.拆借期限品种Map.get(")+"',";
            拆借期限品种DECODE += "'" + key + "','" + 拆借期限品种Map.get(key) + "',";
        }
        //END LOOP
        //IF 变量.拆借期限品种DECODE IS NOT NULL AND 变量.拆借期限品种DECODE.length >0
        if (StringUtil.isNotEmpty(拆借期限品种DECODE)) {
            //变量.拆借期限品种DECODE = 变量.拆借期限品种DECODE.substr(0,变量.拆借期限品种DECODE.length-1)
            拆借期限品种DECODE = 拆借期限品种DECODE.substring(0, 拆借期限品种DECODE.length() - 1);
        }
        //END IF
        //变量.拆借期限品种DECODE = " DECODE("+变量.拆借期限品种DECODE+") "
        //拆借期限品种DECODE = " DECODE(" + 拆借期限品种DECODE + ") ";
        //2.1 调整后账务明细代码数据提取
        //变量.执行SQL += "/*1.账务明细代码调整数据转变*/"
        执行SQL.append("/*1.账务明细代码调整数据转变*/").append("\r\n");
        //变量.执行SQL += "/*1.1 创建临时表：初始化待调整账务明细代码数据*/"
        执行SQL.append("/*1.1 创建临时表：初始化待调整账务明细代码数据*/").append("\r\n");
        //变量.执行SQL += "
        执行SQL.append("create table BOK_DETAIL_ID_TZ").append("\r\n");
        执行SQL.append("(").append("\r\n");
        执行SQL.append("  bookset_id             VARCHAR2(32),").append("\r\n");
        执行SQL.append("  busi_id                VARCHAR2(32),").append("\r\n");
        执行SQL.append("  busi_detail_type       VARCHAR2(50),").append("\r\n");
        执行SQL.append("  bok_detail_id          VARCHAR2(32),").append("\r\n");
        执行SQL.append("  detail_subject_no_tail VARCHAR2(32),").append("\r\n");
        执行SQL.append("  detail_subname_tail    VARCHAR2(500),").append("\r\n");
        执行SQL.append("  bok_detail_id_old      VARCHAR2(32),").append("\r\n");
        执行SQL.append("  inv_aim                VARCHAR2(50),").append("\r\n");
        执行SQL.append("  branch                 NUMBER(10),").append("\r\n");
        执行SQL.append("  ccy                    VARCHAR2(50)").append("\r\n");
        执行SQL.append(");").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.bookset_id").append("\r\n");
        执行SQL.append("  is '账套代码';").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.busi_id").append("\r\n");
        执行SQL.append("  is '业务明细代码';").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.busi_detail_type").append("\r\n");
        执行SQL.append("  is '业务明细类型';").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.bok_detail_id").append("\r\n");
        执行SQL.append("  is '账务明细代码';").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.detail_subject_no_tail").append("\r\n");
        执行SQL.append("  is '四级科目尾号';").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.detail_subname_tail").append("\r\n");
        执行SQL.append("  is '四级科目尾号名称';").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.bok_detail_id_old").append("\r\n");
        执行SQL.append("  is '调整前账务明细代码';").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.inv_aim").append("\r\n");
        执行SQL.append("  is '投资目的';").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.branch").append("\r\n");
        执行SQL.append("  is '分支序号';").append("\r\n");
        执行SQL.append("comment on column BOK_DETAIL_ID_TZ.ccy").append("\r\n");
        执行SQL.append("  is '币种';").append("\r\n");
        //变量.执行SQL += "
        执行SQL.append("DELETE FROM BOK_DETAIL_ID_TZ;").append("\r\n");
        执行SQL.append("INSERT INTO BOK_DETAIL_ID_TZ").append("\r\n");
        执行SQL.append("  SELECT S.BOOKSET_ID,").append("\r\n");
        执行SQL.append("         S.BUSI_ID,").append("\r\n");
        执行SQL.append("         S.BUSI_DETAIL_TYPE,").append("\r\n");
        执行SQL.append("         S.BOK_DETAIL_ID,").append("\r\n");
        执行SQL.append("         CASE").append("\r\n");
        执行SQL.append("           WHEN S.DETAIL_SUBJECT_NO_TAIL IS NOT NULL THEN").append("\r\n");
        执行SQL.append("            S.DETAIL_SUBJECT_NO_TAIL").append("\r\n");
        执行SQL.append("           ELSE").append("\r\n");
        执行SQL.append("            CASE").append("\r\n");
        执行SQL.append("              WHEN S.BUSI_DETAIL_TYPE IN").append("\r\n");
        执行SQL.append("                   ('F02', 'F03', 'F04', 'F05', 'F08', 'F09') THEN").append("\r\n");
        执行SQL.append("               H.TERM_TYPE").append("\r\n");
        执行SQL.append("              WHEN S.BUSI_DETAIL_TYPE = 'F21' AND S.PARAM_VALUE = '02' THEN").append("\r\n");
        执行SQL.append("               H.TERM_TYPE").append("\r\n");
        执行SQL.append("              ELSE").append("\r\n");
        执行SQL.append("               S.BUSI_ID").append("\r\n");
        执行SQL.append("            END").append("\r\n");
        执行SQL.append("         END DETAIL_SUBJECT_NO_TAIL,").append("\r\n");
        执行SQL.append("         CASE").append("\r\n");
        执行SQL.append("           WHEN S.DETAIL_SUBNAME_TAIL IS NOT NULL THEN").append("\r\n");
        执行SQL.append("            S.DETAIL_SUBNAME_TAIL").append("\r\n");
        执行SQL.append("           ELSE").append("\r\n");
        执行SQL.append("            CASE").append("\r\n");
        执行SQL.append("              WHEN S.BUSI_DETAIL_TYPE IN").append("\r\n");
        执行SQL.append("                   ('F02', 'F03', 'F04', 'F05') THEN").append("\r\n");
        //DECODE(H.TERM_TYPE,"+ 变量.回购期限品种DECOD +")
        执行SQL.append("                      ").append("DECODE(H.TERM_TYPE,").append(回购期限品种DECODE).append(")").append("\r\n");
        执行SQL.append("              WHEN S.BUSI_DETAIL_TYPE IN ('F08', 'F09') OR").append("\r\n");
        执行SQL.append("                   (S.BUSI_DETAIL_TYPE = 'F21' AND S.PARAM_VALUE = '02') THEN").append("\r\n");
        //DECODE(H.TERM_TYPE,"+变量.拆借期限品种DECODE+")
        执行SQL.append("                      ").append("DECODE(H.TERM_TYPE,").append(拆借期限品种DECODE).append(")").append("\r\n");
        执行SQL.append("              ELSE").append("\r\n");
        执行SQL.append("               S.BUSI_ID").append("\r\n");
        执行SQL.append("            END").append("\r\n");
        执行SQL.append("         END DETAIL_SUBNAME_TAIL,").append("\r\n");
        执行SQL.append("         S.BOK_DETAIL_ID_OLD,").append("\r\n");
        执行SQL.append("         S.INV_AIM,").append("\r\n");
        执行SQL.append("         S.BRANCH,").append("\r\n");
        执行SQL.append("         S.CCY").append("\r\n");
        执行SQL.append("    FROM (SELECT A.BOOKSET_ID,").append("\r\n");
        //NVL(B.BUSI_ID,'"+入参.账务明细代码DTO.业务明细代码+"') BUSI_ID,
        执行SQL.append("                 NVL(B.BUSI_ID,'").append(账务明细代码DTO.getBusiId()).append("') BUSI_ID,").append("\r\n");
        //NVL(B.BUSI_DETAIL_TYPE,'"+入参.账务明细代码DTO.业务明细类型+"') BUSI_DETAIL_TYPE,
        执行SQL.append("                 NVL(B.BUSI_DETAIL_TYPE,'").append(账务明细代码DTO.getBusiDetailType()).append("') BUSI_DETAIL_TYPE,").append("\r\n");
        //NVL(B.BOK_DETAIL_ID, '"+入参.账务明细代码DTO.账务明细代码+"') BOK_DETAIL_ID,
        执行SQL.append("                 NVL(B.BOK_DETAIL_ID, '").append(账务明细代码DTO.getBokDetailId()).append("') BOK_DETAIL_ID,").append("\r\n");
        执行SQL.append("                 B.DETAIL_SUBJECT_NO_TAIL,").append("\r\n");
        执行SQL.append("                 B.DETAIL_SUBNAME_TAIL,").append("\r\n");
        执行SQL.append("                 EXT.PARAM_VALUE,").append("\r\n");
        //'"+入参.原账务明细代码 +"' AS BOK_DETAIL_ID_OLD,
        执行SQL.append("                 '").append(bokdetailIdOld).append("' AS BOK_DETAIL_ID_OLD,").append("\r\n");
        //'"+入参.账务明细代码DTO.投资目的 +"' AS INV_AIM,
        执行SQL.append("                 '").append(账务明细代码DTO.getInvAim()).append("' AS INV_AIM,").append("\r\n");
        //"+入参.账务明细代码DTO.分支序号 +" AS BRANCH,
        执行SQL.append("                 ").append(账务明细代码DTO.getBranch()).append(" AS BRANCH,").append("\r\n");
        //'"+入参.账务明细代码DTO.币种 +"' AS CCY
        执行SQL.append("                 '").append(账务明细代码DTO.getCcy()).append("' AS CCY").append("\r\n");
        //            FROM BOK_BOOKSET A
        执行SQL.append("            FROM BOK_BOOKSET A").append("\r\n");
        执行SQL.append("            LEFT JOIN BOK_BOOKSET_EXT EXT").append("\r\n");
        执行SQL.append("              ON A.BOOKSET_ID = EXT.BOOKSET_ID").append("\r\n");
        执行SQL.append("             AND EXT.PARAM_CODE = 'TYJK_FORSUBJECT'").append("\r\n");
        执行SQL.append("            LEFT JOIN BOK_DETAIL_ID B").append("\r\n");
        执行SQL.append("              ON A.BOOKSET_ID = B.BOOKSET_ID").append("\r\n");
        //AND B.BOK_DETAIL_ID = '"+入参.账务明细代码DTO.账务明细代码+"'
        执行SQL.append("             AND B.BOK_DETAIL_ID = '").append(账务明细代码DTO.getBokDetailId()).append("'").append("\r\n");
        执行SQL.append("           WHERE A.BOOKSET_TYPE != '01'").append("\r\n");
        执行SQL.append("           AND EXISTS (").append("\r\n");
        执行SQL.append("              SELECT 1 FROM BOK_BOOKDETAIL_CURRENT C WHERE A.BOOKSET_ID = C.BOOKSET_ID").append("\r\n");
        //AND C.BOK_DETAIL_ID = '"+入参.原账务明细代码+"'
        执行SQL.append("              AND C.BOK_DETAIL_ID = '").append(bokdetailIdOld).append("'").append("\r\n");
        执行SQL.append("           )").append("\r\n");
        //IF 入参.账套代码List is not null and 入参.账套代码List.size()>0
        //	    变量.执行SQL +=" AND A.BOOKSET_ID IN ("+入参.账套代码List+") ";	开发时考虑in 超过1000的处理
        //END IF
        //IF 入参.账套代码List is not null and 入参.账套代码List.size()>0
        if (!StringUtil.isEmptyList(booksetIds)) {
            //变量.执行SQL +=" AND A.BOOKSET_ID IN ("+入参.账套代码List+") ";
            List<List<String>> partitions = Lists.partition(booksetIds, 1000);
            执行SQL.append(" AND (");
            for (int j = 0; j < partitions.size(); j++) {
                List<String> partition = partitions.get(j);
                执行SQL.append("A.BOOKSET_ID IN (");
                for (int i = 0; i < partition.size(); i++) {
                    String booksetIdTem = partition.get(i);
                    if (i == booksetIds.size() - 1) {
                        执行SQL.append("'").append(booksetIdTem).append("'");
                    } else {
                        执行SQL.append("'").append(booksetIdTem).append("'").append(",");
                    }
                }
                if (j == partition.size() - 1){
                    执行SQL.append(")");
                }else {
                    执行SQL.append(") OR");
                }
            }
            执行SQL.append(")").append("\r\n");
        }
        //END IF
        //变量.执行SQL += "
        //             ) S
        执行SQL.append("    ) S").append("\r\n");
        执行SQL.append("    LEFT JOIN (SELECT D.FINPROD_ID,").append("\r\n");
        执行SQL.append("                      D.FINPROD_TYPE,").append("\r\n");
        执行SQL.append("                      NVL(D.FINPROD_ABBR, D.FINPROD_NAME) FINPROD_NAME,").append("\r\n");
        执行SQL.append("                      E.TERM_TYPE").append("\r\n");
        执行SQL.append("                 FROM FIN_PRODUCT D").append("\r\n");
        执行SQL.append("                 LEFT JOIN FIN_PRODUCT_ADD E").append("\r\n");
        执行SQL.append("                   ON D.FINPROD_ID = E.FINPROD_ID").append("\r\n");
        执行SQL.append("               UNION ALL").append("\r\n");
        执行SQL.append("               SELECT G.FINPROD_ID,").append("\r\n");
        执行SQL.append("                      G.FINPROD_TYPE,").append("\r\n");
        执行SQL.append("                      G.FINPROD_ID AS FINPROD_NAME,").append("\r\n");
        执行SQL.append("                      G.TERM_TYPE").append("\r\n");
        执行SQL.append("                 FROM FIN_TRADE_PRODUCT G").append("\r\n");
        执行SQL.append("                 LEFT JOIN FIN_PRODUCT_TYPE F").append("\r\n");
        执行SQL.append("                   ON G.FINPROD_ID = F.FINPROD_ID").append("\r\n");
        执行SQL.append("                  AND G.BRANCH = F.BRANCH").append("\r\n");
        执行SQL.append("                WHERE G.BRANCH = 0").append("\r\n");
        执行SQL.append("                  AND G.FINPROD_TYPE != 'F21'").append("\r\n");
        执行SQL.append("               UNION ALL").append("\r\n");
        执行SQL.append("               SELECT I.ACCT_NO, 'F20' FINPROD_TYPE, I.ACCT_NAME, NULL").append("\r\n");
        执行SQL.append("                 FROM STL_CAPACCT_INFO I) H").append("\r\n");
        执行SQL.append("      ON S.BUSI_ID = H.FINPROD_ID;").append("\r\n");
        //变量.执行SQL +="COMMIT;";
        执行SQL.append("COMMIT;").append("\r\n");
        //2.2 业务明细数据初始化
        //变量.执行SQL += "/*2.开始-业务数据更新*/"
        执行SQL.append("/*2.开始-业务数据更新*/").append("\r\n");
        //变量.执行SQL += "/*2.1 业务明细科目表数据*/"
        执行SQL.append("/*2.1 业务明细科目表数据*/").append("\r\n");
        //变量.执行SQL += "
        执行SQL.append("CREATE TABLE BOK_BUSS_DETSUB_TZ AS").append("\r\n");
        执行SQL.append("SELECT").append("\r\n");
        执行SQL.append("  DISTINCT").append("\r\n");
        执行SQL.append("  A.BOOKSET_ID,").append("\r\n");
        执行SQL.append("  A.SUBJECT_NO,").append("\r\n");
        执行SQL.append("  C.DETAIL_SUBJECT_NO_TAIL,").append("\r\n");
        执行SQL.append("  C.DETAIL_SUBNAME_TAIL,").append("\r\n");
        执行SQL.append("  A.SUBJECT_NO||C.DETAIL_SUBJECT_NO_TAIL AS DETAIL_SUBJECT_NO,").append("\r\n");
        执行SQL.append("  S.SUBJECT_NAME||C.DETAIL_SUBNAME_TAIL AS DETAIL_SUBJECT_NAME").append("\r\n");
        执行SQL.append("FROM BOK_BOOKDETAIL_CURRENT A").append("\r\n");
        执行SQL.append("LEFT JOIN BOK_BOOKSET B ON A.BOOKSET_ID = B.BOOKSET_ID").append("\r\n");
        执行SQL.append("LEFT JOIN BOK_SUBJECT S ON B.BOOKSET_ID_TMPL = S.BOOKSET_ID").append("\r\n");
//        执行SQL.append("AND EXISTS (").append("\r\n");
//        执行SQL.append(" SELECT 1 FROM BOK_DETAIL_ID_TZ C WHERE A.BOOKSET_ID = C.BOOKSET_ID").append("\r\n");
//        执行SQL.append(")").append("\r\n");
        执行SQL.append("LEFT JOIN BOK_DETAIL_ID_TZ C ON A.BOOKSET_ID = C.BOOKSET_ID AND A.BOK_DETAIL_ID = C.BOK_DETAIL_ID_OLD").append("\r\n");
        执行SQL.append("WHERE A.DETAIL_SUBJECT_NO IS NOT NULL").append("\r\n");
        //AND C.BOK_DETAIL_ID_OLD = '"+入参.原账务明细代码+"'
        执行SQL.append("AND C.BOK_DETAIL_ID_OLD = '").append(bokdetailIdOld).append("'").append("\r\n");
        //AND A.BOK_DETAIL_ID='"+入参.原账务明细代码+"';
        执行SQL.append("AND A.BOK_DETAIL_ID='").append(bokdetailIdOld).append("';").append("\r\n");
        //变量.执行SQL += "
        执行SQL.append("INSERT INTO BOK_BUSS_DETSUB").append("\r\n");
        执行SQL.append("  (BOOKSET_ID,").append("\r\n");
        执行SQL.append("   DETAIL_SUBJECT_NO,").append("\r\n");
        执行SQL.append("   DETAIL_SUBJECT_NAME,").append("\r\n");
        执行SQL.append("   FSUBJECT_ID,").append("\r\n");
        执行SQL.append("   CREATE_USER,").append("\r\n");
        执行SQL.append("   CREATE_DEPT,").append("\r\n");
        执行SQL.append("   CREATE_TIME,").append("\r\n");
        执行SQL.append("   UPDATE_USER,").append("\r\n");
        执行SQL.append("   UPDATE_TIME,").append("\r\n");
        执行SQL.append("   DETAIL_SUBJECT_NO_TAIL,").append("\r\n");
        执行SQL.append("   DETAIL_SUBNAME_TAIL)").append("\r\n");
        执行SQL.append("  SELECT DISTINCT").append("\r\n");
        执行SQL.append("         A.BOOKSET_ID,").append("\r\n");
        执行SQL.append("         A.DETAIL_SUBJECT_NO,").append("\r\n");
        执行SQL.append("         A.DETAIL_SUBJECT_NAME,").append("\r\n");
        执行SQL.append("         A.SUBJECT_NO,").append("\r\n");
        执行SQL.append("         'sjzl',").append("\r\n");
        执行SQL.append("         null,").append("\r\n");
        执行SQL.append("         SYSDATE,").append("\r\n");
        执行SQL.append("         'sjzl',").append("\r\n");
        执行SQL.append("         SYSDATE,").append("\r\n");
        执行SQL.append("         A.DETAIL_SUBJECT_NO_TAIL,").append("\r\n");
        执行SQL.append("         A.DETAIL_SUBNAME_TAIL").append("\r\n");
        执行SQL.append("    FROM BOK_BUSS_DETSUB_TZ A").append("\r\n");
        执行SQL.append("   WHERE NOT EXISTS").append("\r\n");
        执行SQL.append("   (SELECT 1").append("\r\n");
        执行SQL.append("            FROM BOK_BUSS_DETSUB B").append("\r\n");
        执行SQL.append("           WHERE A.BOOKSET_ID = B.BOOKSET_ID").append("\r\n");
        执行SQL.append("             AND A.DETAIL_SUBJECT_NO = B.DETAIL_SUBJECT_NO);").append("\r\n");
        //变量.执行SQL +="COMMIT;";
        执行SQL.append("COMMIT;").append("\r\n");
        //2.3 账务明细代码表初始化
        //变量.执行SQL += "/*2.2 账务明细代码表数据*/"
        执行SQL.append("/*2.2 账务明细代码表数据*/").append("\r\n");
        //变量.执行SQL += "
        执行SQL.append("DELETE FROM BOK_DETAIL_ID A").append("\r\n");
        执行SQL.append("WHERE EXISTS (").append("\r\n");
        执行SQL.append("  SELECT 1 FROM BOK_DETAIL_ID_TZ B WHERE A.BOOKSET_ID = B.BOOKSET_ID AND A.BOK_DETAIL_ID = B.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append(");").append("\r\n");
        执行SQL.append("INSERT INTO BOK_DETAIL_ID(").append("\r\n");
        执行SQL.append("BOOKSET_ID,BOK_DETAIL_ID,BUSI_ID,BUSI_DETAIL_TYPE,INV_AIM,").append("\r\n");
        执行SQL.append("SEC_MANAGE_ACCT_ID,TRADE_ID,DETAIL_SUBJECT_NO_TAIL,DETAIL_SUBNAME_TAIL,").append("\r\n");
        执行SQL.append("CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,BRANCH,CCY").append("\r\n");
        执行SQL.append(")").append("\r\n");
        执行SQL.append("SELECT").append("\r\n");
        执行SQL.append("  B.BOOKSET_ID,").append("\r\n");
        执行SQL.append("  B.BOK_DETAIL_ID,").append("\r\n");
        执行SQL.append("  B.BUSI_ID,").append("\r\n");
        执行SQL.append("  B.BUSI_DETAIL_TYPE,").append("\r\n");
        执行SQL.append("  B.INV_AIM,").append("\r\n");
        执行SQL.append("  NULL,").append("\r\n");
        执行SQL.append("  NULL,").append("\r\n");
        执行SQL.append("  B.DETAIL_SUBJECT_NO_TAIL,").append("\r\n");
        执行SQL.append("  B.DETAIL_SUBNAME_TAIL,").append("\r\n");
        执行SQL.append("  'sjzl',").append("\r\n");
        执行SQL.append("  NULL,").append("\r\n");
        执行SQL.append("  SYSDATE,").append("\r\n");
        执行SQL.append("  'sjzl',").append("\r\n");
        执行SQL.append("  SYSDATE,").append("\r\n");
        执行SQL.append("  B.BRANCH,").append("\r\n");
        执行SQL.append("  B.CCY").append("\r\n");
        执行SQL.append("FROM BOK_DETAIL_ID_TZ B;").append("\r\n");
        //COMMIT;
        执行SQL.append("COMMIT;").append("\r\n");
        //2.4 核算数据初始化
        //变量.执行SQL += "/*2.3 有账务明细代码字段表数据*/"
        执行SQL.append("/*2.3 有账务明细代码字段表数据*/").append("\r\n");
        //变量.执行SQL += "
        执行SQL.append("DECLARE").append("\r\n");
        执行SQL.append("  CURSOR LIST IS").append("\r\n");
        执行SQL.append("         SELECT").append("\r\n");
        执行SQL.append("            BOOKSET_ID,BUSI_ID,").append("\r\n");
        执行SQL.append("            BUSI_DETAIL_TYPE,BOK_DETAIL_ID,").append("\r\n");
        执行SQL.append("            DETAIL_SUBJECT_NO_TAIL,DETAIL_SUBNAME_TAIL,").append("\r\n");
        执行SQL.append("            BOK_DETAIL_ID_OLD,").append("\r\n");
        执行SQL.append("            INV_AIM,BRANCH,CCY").append("\r\n");
        执行SQL.append("         FROM BOK_DETAIL_ID_TZ;").append("\r\n");
        执行SQL.append("  BEGIN").append("\r\n");
        执行SQL.append("  FOR item IN LIST").append("\r\n");
        执行SQL.append("  LOOP").append("\r\n");
        执行SQL.append("    /*增值税已抵扣增值额*/").append("\r\n");
        执行SQL.append("    UPDATE TAX_DEDUCTED_BALANCE S SET BOK_DETAIL_ID= item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("    ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("    WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("    /*增值税待抵扣增值额*/").append("\r\n");
        执行SQL.append("    UPDATE TAX_TURNED_BALANCE  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("    ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("    WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("    /*增值税账务明细余额*/").append("\r\n");
        执行SQL.append("    UPDATE TAX_BOK_BALANCE  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("    ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("    WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("   /*增值税账务明细流水*/").append("\r\n");
        执行SQL.append("    UPDATE TAX_BOK_DETAIL  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("    ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("    WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("   /*账务明细辅助表*/").append("\r\n");
        执行SQL.append("   UPDATE BOK_BOOKDETAIL_CURRENT_EXT  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("   ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("   /*统计分析明细表（账务明细）*/").append("\r\n");
        执行SQL.append("   UPDATE BOK_STAT_DET_POSIITON  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("   ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("   /*账务明细（历史表）*/").append("\r\n");
        执行SQL.append("   UPDATE BOK_BOOKDETAIL_ALL  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID,").append("\r\n");
        执行SQL.append("     DETAIL_SUBJECT_NO = CASE WHEN DETAIL_SUBJECT_NO IS NULL THEN NULL ELSE SUBJECT_NO||item.DETAIL_SUBJECT_NO_TAIL END").append("\r\n");
        执行SQL.append("     ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("   /*账务明细（当前表）*/").append("\r\n");
        执行SQL.append("   UPDATE BOK_BOOKDETAIL_CURRENT  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID,").append("\r\n");
        执行SQL.append("     DETAIL_SUBJECT_NO = CASE WHEN DETAIL_SUBJECT_NO IS NULL THEN NULL ELSE SUBJECT_NO||item.DETAIL_SUBJECT_NO_TAIL END").append("\r\n");
        执行SQL.append("     ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("   /*手工凭证交易明细*/").append("\r\n");
        执行SQL.append("   UPDATE TRD_VOUCH_DEAL_DETAIL  SET BOK_DETAIL_ID = item.BOK_DETAIL_ID,").append("\r\n");
        执行SQL.append("   BUSI_ID = item.BUSI_ID,").append("\r\n");
        执行SQL.append("   BUSI_DETAIL_TYPE = item.BUSI_DETAIL_TYPE,").append("\r\n");
        执行SQL.append("   INV_AIM = item.INV_AIM").append("\r\n");
        执行SQL.append("   ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("   /*核算分层信息*/").append("\r\n");
        执行SQL.append("    UPDATE BOK_ACCT_LAYERING_INFO S SET S.BOK_DETAIL_ID= item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("    ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("    WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("   /*交易入账信息明细表*/").append("\r\n");
        执行SQL.append("   UPDATE BOK_DEAL_ACCOUNTING_DETAIL  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID").append("\r\n");
        执行SQL.append("   ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE").append("\r\n");
        执行SQL.append("   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;").append("\r\n");
        执行SQL.append("   COMMIT;").append("\r\n");
        执行SQL.append("  END LOOP;").append("\r\n");
        执行SQL.append("END;").append("\r\n");
        //2.5 交易数据更新
        //IF 入参.业务明细类型 IN (数据字典：资金账户类型) AND 入参.资金账户号 != 入参.业务明细代码
        if (systemToolService.getDataDictItem(DictCode.资金账户类型.getValue(), 账务明细代码DTO.getBusiDetailType()) != null
                && !StringUtil.equals(acctNoOld,账务明细代码DTO.getBusiId())
        ){
            执行SQL.append("/").append("\r\n");
            //变量.执行SQL += "/*2.4 结算指令表中本方账户、对方账户数据,调整前后的资金账户不同时执行此脚本*/"
            执行SQL.append("/*2.4 结算指令表中本方账户、对方账户数据,调整前后的资金账户不同时执行此脚本*/").append("\r\n");
            //变量.执行SQL += "
            执行SQL.append("UPDATE STL_ORDERS S").append("\r\n");
            //SET S.MY_ACCT_NO ='"+入参.账务明细代码DTO.业务明细代码 + "'
            执行SQL.append("   SET S.MY_ACCT_NO ='").append(账务明细代码DTO.getBusiId()).append("'").append("\r\n");
            执行SQL.append(" WHERE EXISTS (SELECT 1").append("\r\n");
            执行SQL.append("          FROM BOK_DEAL_ACCOUNTING_DETAIL B").append("\r\n");
            执行SQL.append("         WHERE (S.ORDER_ID = B.BUSI_NO OR S.TRADE_ID = B.BUSI_NO)").append("\r\n");
            //IF 入参.账套代码List is not null and 入参.账套代码List.size()>0
            if (!StringUtil.isEmptyList(booksetIds)){
                //变量.执行SQL +=" AND B.BOOKSET_ID IN ("+入参.账套代码List+") ";
                List<List<String>> partitions = Lists.partition(booksetIds, 1000);
                执行SQL.append(" AND (");
                for (int j = 0; j < partitions.size(); j++) {
                    List<String> partition = partitions.get(j);
                    执行SQL.append("B.BOOKSET_ID IN (");
                    for (int i = 0; i < partition.size(); i++) {
                        String booksetIdTem = partition.get(i);
                        if (i == booksetIds.size() - 1) {
                            执行SQL.append("'").append(booksetIdTem).append("'");
                        } else {
                            执行SQL.append("'").append(booksetIdTem).append("'").append(",");
                        }
                    }
                    if (j == partition.size() - 1){
                        执行SQL.append(")");
                    }else {
                        执行SQL.append(") OR");
                    }
                }
                执行SQL.append(")").append("\r\n");
            }
            //END IF
            //变量.执行SQL += "
            //           )
            //   AND S.MY_ACCT_NO ='"+入参.原资金账户号+"';
            执行SQL.append("           )").append("\r\n");
            执行SQL.append("   AND S.MY_ACCT_NO ='").append(acctNoOld).append("';").append("\r\n");
            //变量.执行SQL += "
            执行SQL.append("UPDATE STL_ORDERS S").append("\r\n");
            //SET S.COUNTER_ACCT_NO ='"+入参.账务明细代码DTO.业务明细代码 + "'
            执行SQL.append("   SET S.COUNTER_ACCT_NO ='").append(账务明细代码DTO.getBusiId()).append("'").append("\r\n");
            执行SQL.append(" WHERE EXISTS (SELECT 1").append("\r\n");
            执行SQL.append("          FROM BOK_DEAL_ACCOUNTING_DETAIL B").append("\r\n");
            执行SQL.append("         WHERE (S.ORDER_ID = B.BUSI_NO OR S.TRADE_ID = B.BUSI_NO)").append("\r\n");
            //IF 入参.账套代码List is not null and 入参.账套代码List.size()>0
            if (!StringUtil.isEmptyList(booksetIds)){
                //变量.执行SQL +=" AND B.BOOKSET_ID IN ("+入参.账套代码List+") ";  开发时考虑in 超过1000的处理
                List<List<String>> partitions = Lists.partition(booksetIds, 1000);
                执行SQL.append(" AND (");
                //开发时考虑in 超过1000的处理
                for (int j = 0; j < partitions.size(); j++) {
                    List<String> partition = partitions.get(j);
                    执行SQL.append("B.BOOKSET_ID IN (");
                    for (int i = 0; i < partition.size(); i++) {
                        String booksetIdTem = partition.get(i);
                        if (i == booksetIds.size() - 1) {
                            执行SQL.append("'").append(booksetIdTem).append("'");
                        } else {
                            执行SQL.append("'").append(booksetIdTem).append("'").append(",");
                        }
                    }
                    if (j == partition.size() - 1){
                        执行SQL.append(")");
                    }else {
                        执行SQL.append(") OR");
                    }
                }
                执行SQL.append(")").append("\r\n");
            }
            //END IF
            //变量.执行SQL += "
            //           )
            //   AND S.COUNTER_ACCT_NO ='"+入参.原资金账户号+"';
            执行SQL.append("           )").append("\r\n");
            执行SQL.append("AND S.COUNTER_ACCT_NO ='").append(acctNoOld).append("';").append("\r\n");
            //变量.执行SQL +="COMMIT;";
            执行SQL.append("COMMIT;").append("\r\n");
        }
        //2.6 临时表删除
        //变量.执行SQL += "/*3.结束-业务数据更新，数据验证通过后，删除过程使用的临时表*/"
        执行SQL.append("/*3.结束-业务数据更新，数据验证通过后，删除过程使用的临时表*/").append("\r\n");
        执行SQL.append("/").append("\r\n");
        //变量.执行SQL +="DROP TABLE BOK_DETAIL_ID_TZ;";
        执行SQL.append("DROP TABLE BOK_DETAIL_ID_TZ;").append("\r\n");
        //变量.执行SQL +="DROP TABLE BOK_BUSS_DETSUB_TZ;";
        执行SQL.append("DROP TABLE BOK_BUSS_DETSUB_TZ;").append("\r\n");
        //3.返回结果
        //出参.处理结果 = "0000"
        changeSubjectNoResult.setResultCode(ResultCode.SUCCESS.getCode());
        //出参.执行SQL =  变量.执行SQL
        changeSubjectNoResult.setExecSql(执行SQL.toString());
        return changeSubjectNoResult;
    }
}
