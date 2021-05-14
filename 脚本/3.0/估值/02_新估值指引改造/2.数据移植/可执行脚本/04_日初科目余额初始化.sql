--1.创建临时表：提取待进行日初科目余额数据
CREATE TABLE BOK_BOOKSET_BEF AS
SELECT A.BOOKSET_ID,A.START_DATE,A.END_DATE,C.START_BOOK_DATE,C.END_BOOKSET_DATE
FROM BOK_BOOKSET A
LEFT JOIN (
   SELECT B.BOOKSET_ID,MIN(B.BOOK_DATE) AS START_BOOK_DATE,MAX(B.BOOKSET_DATE) END_BOOKSET_DATE 
   FROM BOK_BOOKDETAIL_CURRENT B 
   GROUP BY B.BOOKSET_ID
) C ON A.BOOKSET_ID = C.BOOKSET_ID
WHERE A.BOOKSET_TYPE <> '01'
AND C.START_BOOK_DATE IS NOT NULL;

--2.日初科目余额汇总
DECLARE
  V_STRDATE DATE; 
  
  V_PZ_oCcyKnotsAmt NUMBER(30,2);  /*当日凭证发生：结转金额_原币余额 */ 
  V_PZ_bCoAmt       NUMBER(30,2);  /*当日凭证发生：结转金额_本币余额 */ 
   
  V_PurOUlamt    NUMBER(30,2);  /*申购未实现平准金_原币余额 */ 
  V_BpurOUlamt   NUMBER(30,2);  /*申购未实现平准金_本币余额 */   
  V_RedOUlamt    NUMBER(30,2);  /*申购未实现平准金_原币余额 */ 
  V_BredOUlamt   NUMBER(30,2);  /*申购未实现平准金_本币余额 */ 
  
  V_Balance      NUMBER(30,2);  /*科目余额_原币 */ 
  V_bAmt         NUMBER(30,2);  /*科目余额_本币 */ 
  V_TdyOAmt      NUMBER(30,2);  /*当日发生额余额_原币 */ 
  V_TdyBAmt      NUMBER(30,2);  /*当日发生额余额_本币 */ 
  V_Num          NUMBER(30,2);  /*数量余额 */ 
  V_BalFlagHappenDate VARCHAR2(50);  /*余额方向 */ 
  V_TdyAmtFlag   VARCHAR2(50);  /*当日发生额方向 */ 
  
  V_dAmt         NUMBER(30,2);  /*借方余额_原币 */ 
  V_oCcyKnotsAmtD NUMBER(30,2);  /*原币结转余额借方 */ 
  V_TdyODAmt      NUMBER(30,2);  /*原币当日发生额借方 */ 
  V_TdyOCoDAmt    NUMBER(30,2);  /*原币结转当日发生额借方 */ 
  V_TdyPurODUlamt NUMBER(30,2);  /*原币申购未实现平准金借 */ 
  V_TdyRedODUlamt NUMBER(30,2);  /*原币赎回未实现平准金借  */ 
  
  V_bDAmt         NUMBER(30,2);  /*借方余额_本币 */ 
  V_bCoDAmt NUMBER(30,2);        /*本币结转余额借方 */ 
  V_TdyBDAmt      NUMBER(30,2);  /*本币当日发生额借方 */ 
  V_TdyBCoDAmt    NUMBER(30,2);  /*本币结转当日发生额借方 */ 
  V_TdyPurBDUlamt NUMBER(30,2);  /*本币申购未实现平准金借 */ 
  V_TdyRedBDUlamt NUMBER(30,2);  /*本币赎回未实现平准金借  */ 
  
  V_cAmt          NUMBER(30,2);  /*贷方余额_原币 */ 
  V_oCcyKnotsAmtC NUMBER(30,2);  /*原币结转余额贷方 */ 
  V_TdyOCAmt      NUMBER(30,2);  /*原币当日发生额贷方 */ 
  V_TdyOCoCAmt    NUMBER(30,2);  /*原币结转当日发生额贷方 */ 
  V_TdyPurOCUlamt NUMBER(30,2);  /*原币申购未实现平准金贷 */ 
  V_TdyRedOCUlamt NUMBER(30,2);  /*原币赎回未实现平准金贷 */ 
  
  V_bCAmt         NUMBER(30,2);  /*贷方余额_原币 */ 
  V_bCoCAmt       NUMBER(30,2);  /*本币结转余额贷方 */ 
  V_TdyBCAmt      NUMBER(30,2);  /*本币当日发生额贷方 */ 
  V_TdyBCoCAmt    NUMBER(30,2);  /*本币结转当日发生额贷方 */ 
  V_TdyPurBCUlamt NUMBER(30,2);  /*本币申购未实现平准金贷 */
  V_TdyRedBCUlamt NUMBER(30,2);  /*本币赎回未实现平准金贷 */ 
  
  V_oCcyKnotsAmt NUMBER(30,2); /*结转金额_原币余额*/
  V_bCoAmt       NUMBER(30,2); /*结转金额_本币余额*/
  V_BalCoFlag     VARCHAR2(50);  /*结转余额方向*/
    
  V_TdyOCoAmt   NUMBER(30,2);  /*原币结转当日发生额 */ 
  V_TdyBCoAmt   NUMBER(30,2);  /*本币结转当日发生额 */ 
  V_TdyCoFlag     VARCHAR2(50);  /*结转当日发生额方向*/
  
    
  V_isCountBBalance VARCHAR2(50);        /*是否汇总原币金额*/
  V_CONNUM          number;
  V_SUBJECT_NO      VARCHAR2(300);        /*科目号*/
  V_SUBJECTLEVEL    NUMBER(10);        /*科目级别*/
  V_FSUBJECT_ID     VARCHAR2(32);        /*上级科目号*/
  V_FSUBJECTLEVEL   NUMBER(10);        /*上级科目级别*/
  V_ISLEAF   VARCHAR2(50);        /*是否叶子节点*/
  
 
  CURSOR LIST IS                      
         SELECT                       
           BOOKSET_ID,START_DATE,END_DATE,START_BOOK_DATE,END_BOOKSET_DATE         
         FROM BOK_BOOKSET_BEF WHERE START_BOOK_DATE IS NOT NULL;                      
  BEGIN
  /*删除科目余额_日初数据*/
  DELETE FROM BOK_BALANCE_BEF A WHERE EXISTS (
    SELECT 1 FROM BOK_BOOKSET_BEF B WHERE A.BOOKSET_ID = B.BOOKSET_ID
  );   
  COMMIT;                  
  FOR item IN LIST                      
  LOOP 
    V_STRDATE :=  item.START_BOOK_DATE+1;
    WHILE V_STRDATE <= item.END_BOOKSET_DATE+1 LOOP
        --将前一日科目余额数据初始化到当日
        INSERT INTO BOK_BALANCE_BEF(
          BOOKSET_ID,BALANCE_DATE,SUBJECT_NO,FSUBJECT_NO,SUBJECT_LEVEL,
          BAL_FLAG,BAL_CO_FLAG,O_CCY,O_AMT,O_C_AMT,
          O_D_AMT,O_CO_AMT,O_CO_C_AMT,O_CO_D_AMT,B_CCY,
          B_AMT,B_C_AMT,B_CO_AMT,B_CO_C_AMT,B_CO_D_AMT,B_D_AMT,
          TDY_O_AMT,TDY_O_C_AMT,TDY_O_D_AMT,TDY_O_CO_AMT,TDY_O_CO_C_AMT,
          TDY_O_CO_D_AMT,TDY_B_AMT,TDY_B_C_AMT,TDY_B_D_AMT,TDY_B_CO_AMT,TDY_B_CO_C_AMT,TDY_B_CO_D_AMT,IS_LEAF,NUM_AMT,
          CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,
          TDY_AMT_FLAG,TDY_CO_FLAG,
          TDY_PUR_O_D_ULAMT,TDY_RED_O_D_ULAMT,TDY_PUR_O_C_ULAMT,TDY_RED_O_C_ULAMT,TDY_PUR_B_D_ULAMT,
          TDY_RED_B_D_ULAMT,TDY_PUR_B_C_ULAMT,TDY_RED_B_C_ULAMT
        )
        SELECT 
          BOOKSET_ID,
          V_STRDATE BALANCE_DATE,
          SUBJECT_NO,FSUBJECT_NO,SUBJECT_LEVEL,
          BAL_FLAG,BAL_CO_FLAG,O_CCY,O_AMT,O_C_AMT,O_D_AMT,O_CO_AMT,
          O_CO_C_AMT,O_CO_D_AMT,B_CCY,B_AMT,
          B_C_AMT,B_CO_AMT,B_CO_C_AMT,
          B_CO_D_AMT,B_D_AMT,
          0 TDY_O_AMT,
          0 TDY_O_C_AMT,
          0 TDY_O_D_AMT,
          0 TDY_O_CO_AMT,
          0 TDY_O_CO_C_AMT,
          0 TDY_O_CO_D_AMT,
          0 TDY_B_AMT,
          0 TDY_B_C_AMT,
          0 TDY_B_D_AMT,
          0 TDY_B_CO_AMT,
          0 TDY_B_CO_C_AMT,
          0 TDY_B_CO_D_AMT,
          IS_LEAF,NUM_AMT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,
          TDY_AMT_FLAG,TDY_CO_FLAG,
          0 TDY_PUR_O_D_ULAMT,
          0 TDY_RED_O_D_ULAMT,
          0 TDY_PUR_O_C_ULAMT,
          0 TDY_RED_O_C_ULAMT,
          0 TDY_PUR_B_D_ULAMT,
          0 TDY_RED_B_D_ULAMT,
          0 TDY_PUR_B_C_ULAMT,
          0 TDY_RED_B_C_ULAMT
        FROM BOK_BALANCE_BEF WHERE BOOKSET_ID = item.BOOKSET_ID  AND BALANCE_DATE= V_STRDATE -1;
        COMMIT;
        --查询当日科目发生额
        FOR tdyitem IN (
                  select a.Bookset_Id booksetId,
                   A.BOOK_DATE bookDate,
                   NVL(A.DETAIL_SUBJECT_NO, A.SUBJECT_NO) AS subjectNo,
                   A.CD_FLAG balFlagHappenDate,
                   A.o_Ccy oCcy,
                   A.O_HAPPEN_AMT balance,
                   NVL(A.HAPPEN_NUMBER,0) num,
                   (CASE
                     WHEN a.DETAIL_SUBJECT_NO IS NOT NULL THEN
                      A.SUBJECT_NO
                     ELSE
                      BBB.FSUBJECT_ID
                   END) AS fsubjectId,
                   (CASE
                     WHEN A.DETAIL_SUBJECT_NO IS NOT NULL THEN
                      99
                     ELSE
                      BBB.SUBJECT_LEVEL
                   END) AS subjectLevel,
                   
                   CASE WHEN A.DETAIL_SUBJECT_NO IS NOT NULL THEN
                     BBB.SUBJECT_LEVEL
                   ELSE 
                     FS.SUBJECT_LEVEL
                   END fsubjectLevel, 
                   A.TABLE_ID tableId,
                   A.b_ccy bCcy,
                   A.B_HAPPEN_AMT bAmt,
                   bbb.subject_type  /*科目类型*/
                   ,bbb.bal_flag /*科目期待方向*/
              from bok_bookdetail_all A
              LEFT JOIN BOK_BOOKSET bb
                on bb.bookset_id = A.BOOKSET_ID
              LEFT JOIN BOK_SUBJECT bbb
                ON bbb.BOOKSET_ID = BB.BOOKSET_ID_TMPL
               AND bbb.SUBJECT_NO = a.SUBJECT_NO
              
             LEFT JOIN BOK_SUBJECT FS ON BB.BOOKSET_ID_TMPL = FS.BOOKSET_ID
                AND bbb.Fsubject_Id = FS.SUBJECT_NO
                
             where (A.O_HAPPEN_AMT != 0 OR A.HAPPEN_NUMBER != 0 OR A.B_HAPPEN_AMT != 0)
           
               and A.Bookset_Id = item.BOOKSET_ID
               and A.BOOK_DATE = V_STRDATE -1
               and A.HAPPEN_DATE = V_STRDATE -1
          )
        LOOP
          
          V_PZ_oCcyKnotsAmt :=0;  /*当日凭证发生：结转金额_原币余额 */ 
          V_PZ_bCoAmt       :=0;  /*当日凭证发生：结转金额_本币余额 */ 
          V_PurOUlamt   :=0;  /*申购未实现平准金_原币余额 */ 
          V_BpurOUlamt  :=0;  /*申购未实现平准金_本币余额 */   
          V_RedOUlamt   :=0;  /*申购未实现平准金_原币余额 */ 
          V_BredOUlamt  :=0;  /*申购未实现平准金_本币余额 */ 
        
        
          V_CONNUM := 0;
          V_SUBJECT_NO := tdyitem.subjectno;
          V_SUBJECTLEVEL := tdyitem.subjectlevel;
          V_ISLEAF := 'Y';
          V_FSUBJECTLEVEL:= tdyitem.fsubjectLevel;
          V_FSUBJECT_ID := tdyitem.fsubjectid;
          
           
          <<repeat_loop>> --循环点  
          loop
                     
          /*汇总开始参数值初始化 START*/
          V_oCcyKnotsAmt:=0;  /*结转金额_原币余额*/
          V_bCoAmt      :=0;       /*结转金额_本币余额*/
          
          V_Balance      := 0;  /*科目余额_原币 */ 
          V_bAmt         := 0;  /*科目余额_本币 */ 
          V_TdyOAmt      := 0;  /*当日发生额余额_原币 */ 
          V_TdyBAmt      := 0;  /*当日发生额余额_本币 */ 
          V_Num          := 0;  /*数量余额 */ 
          V_BalFlagHappenDate := null;  /*余额方向 */ 
          V_TdyAmtFlag   := null;  /*当日发生额方向 */ 
          
          
          V_dAmt         := 0;  /*借方余额_原币 */ 
          V_oCcyKnotsAmtD := 0;  /*原币结转余额借方 */ 
          V_TdyODAmt      := 0;  /*原币当日发生额借方 */ 
          V_TdyOCoDAmt    := 0;  /*原币结转当日发生额借方 */ 
          V_TdyPurODUlamt := 0;  /*原币申购未实现平准金借 */ 
          V_TdyRedODUlamt := 0;  /*原币赎回未实现平准金借  */ 
          
          V_bDAmt         := 0;  /*借方余额_本币 */ 
          V_bCoDAmt       := 0;  /*本币结转余额借方 */ 
          V_TdyBDAmt      := 0;  /*本币当日发生额借方 */ 
          V_TdyBCoDAmt    := 0;  /*本币结转当日发生额借方 */ 
          V_TdyPurBDUlamt := 0;  /*本币申购未实现平准金借 */ 
          V_TdyRedBDUlamt := 0;  /*本币赎回未实现平准金借  */ 
          
          
          V_cAmt          := 0;  /*贷方余额_原币 */ 
          V_oCcyKnotsAmtC := 0;  /*原币结转余额贷方 */ 
          V_TdyOCAmt      := 0;  /*原币当日发生额贷方 */ 
          V_TdyOCoCAmt    := 0;  /*原币结转当日发生额贷方 */ 
          V_TdyPurOCUlamt := 0;  /*原币申购未实现平准金贷 */ 
          V_TdyRedOCUlamt := 0;  /*原币赎回未实现平准金贷 */ 
          
          V_bCAmt         := 0;  /*贷方余额_原币 */ 
          V_bCoCAmt       := 0;  /*本币结转余额贷方 */ 
          V_TdyBCAmt      := 0;  /*本币当日发生额贷方 */ 
          V_TdyBCoCAmt    := 0;  /*本币结转当日发生额贷方 */ 
          V_TdyPurBCUlamt := 0;  /*本币申购未实现平准金贷 */
          V_TdyRedBCUlamt := 0;  /*本币赎回未实现平准金贷 */ 
          
          V_BalCoFlag     := null;  /*结转余额方向*/
          
          V_TdyOCoAmt     := 0;  /*原币结转当日发生额 */ 
          V_TdyBCoAmt     := 0;  /*本币结转当日发生额 */ 
          V_TdyCoFlag     := null;  /*结转当日发生额方向*/
  
          /*汇总开始参数值初始化 END*/
          
          IF V_SUBJECTLEVEL = 99 THEN 
            V_FSUBJECT_ID := tdyitem.fsubjectid;
            V_FSUBJECTLEVEL := tdyitem.fsubjectLevel;
          ELSE
            SELECT S.FSUBJECT_ID,F.SUBJECT_LEVEL INTO V_FSUBJECT_ID,V_FSUBJECTLEVEL FROM BOK_SUBJECT S 
            LEFT JOIN BOK_BOOKSET H ON S.BOOKSET_ID = H.BOOKSET_ID_TMPL
            LEFT JOIN BOK_SUBJECT F ON S.BOOKSET_ID = F.BOOKSET_ID AND S.FSUBJECT_ID = F.SUBJECT_NO
            WHERE S.SUBJECT_NO = V_SUBJECT_NO
            AND H.BOOKSET_ID= tdyitem.booksetid
            AND ROWNUM<2;
          END IF;
          V_isCountBBalance := 'Y';
          IF tdyitem.subject_type = '05' OR V_ISLEAF ='N' THEN 
            V_isCountBBalance := 'N';
          END IF;
          /*1.当日结转余额*/
          /*产品_已实现损益结转、产品_未实现损益结转、产品_未分配利润结转、
          产品_损益平准金结转、产品_本期利润未实现结转、产品_本期利润已实现结转
          */
          IF tdyitem.tableId IN ('8019','8021','8022','8023','8024','8020') AND V_ISLEAF ='Y' THEN 
             V_PZ_oCcyKnotsAmt := tdyitem.balance;
             V_PZ_bCoAmt := tdyitem.bamt;
          END IF;
          /*
            产品_净值型_认购申购交易、产品_净值型_红利再投
          */
          IF tdyitem.tableId IN ('8011','8014') AND V_ISLEAF ='Y' THEN 
             V_PurOUlamt := tdyitem.balance;
             V_BpurOUlamt := tdyitem.bamt;
          END IF;
          /*
            产品_净值型_赎回到期
          */
          IF tdyitem.tableId= '8015' AND V_ISLEAF ='Y' THEN 
             V_RedOUlamt := tdyitem.balance;
             V_BredOUlamt := tdyitem.bamt;
          END IF;
          
          /*2.开始进行科目余额汇总处理*/
          SELECT COUNT(1) INTO V_CONNUM FROM BOK_BALANCE_BEF A
          WHERE A.BOOKSET_ID = tdyitem.Booksetid
          AND A.BALANCE_DATE = V_STRDATE
          AND A.SUBJECT_NO = V_SUBJECT_NO;
          /*2.1 当日科目余额表不存在科目余额 */
          IF V_CONNUM = 0 THEN 
            V_Balance := tdyitem.balance;
            V_bAmt := tdyitem.bamt;
            V_Num := tdyitem.num;
            V_BalFlagHappenDate := tdyitem.balflaghappendate;
            IF tdyitem.bal_flag != 'B' THEN 
               IF V_BalFlagHappenDate != tdyitem.bal_flag THEN 
                 V_Balance := V_Balance *-1;
                 V_bAmt := V_bAmt * -1;
                 V_Num :=  V_Num*-1;
               END IF;  
               V_BalFlagHappenDate :=  tdyitem.bal_flag;         
            ELSE
              IF V_BalFlagHappenDate = 'C'  THEN
                IF V_Balance < 0 THEN
                   V_Balance := V_Balance *-1;
                   V_bAmt := V_bAmt * -1;
                   V_Num :=  V_Num*-1;
                   V_BalFlagHappenDate := 'D';
                ELSE
                   V_Balance := V_Balance;
                   V_bAmt := V_bAmt;
                   V_Num :=  V_Num;
                   V_BalFlagHappenDate := 'C';
                END IF;
              END IF;
              
              IF V_BalFlagHappenDate = 'D' THEN
                IF V_Balance < 0 THEN
                  V_Balance := V_Balance *-1;
                  V_bAmt := V_bAmt * -1;
                  V_Num :=  V_Num*-1;
                  V_BalFlagHappenDate := 'C';
                ELSE
                  V_Balance := V_Balance;
                  V_bAmt := V_bAmt;
                  V_Num :=  V_Num;
                  V_BalFlagHappenDate := 'D';
                END IF;
              END IF;
            END IF;
            
            V_TdyOAmt := V_Balance;
            V_TdyBAmt := V_bAmt;
            V_TdyAmtFlag := V_BalFlagHappenDate;
            IF V_isCountBBalance = 'N' THEN 
              V_Balance := 0;
              V_TdyOAmt := 0;
            END IF;
                        
            IF tdyitem.balflaghappendate = 'D' THEN
              IF V_isCountBBalance = 'Y' THEN
                V_dAmt         := tdyitem.balance;  /*借方余额_原币 */ 
                V_oCcyKnotsAmtD := V_PZ_oCcyKnotsAmt;  /*原币结转余额借方 */ 
                V_TdyODAmt      := tdyitem.balance;  /*原币当日发生额借方 */ 
                V_TdyOCoDAmt    := V_PZ_oCcyKnotsAmt;  /*原币结转当日发生额借方 */ 
                V_TdyPurODUlamt := V_PurOUlamt;  /*原币申购未实现平准金借 */ 
                V_TdyRedODUlamt := V_RedOUlamt;  /*原币赎回未实现平准金借  */ 
              END IF;
              V_bDAmt         := tdyitem.bamt;  /*借方余额_原币 */ 
              V_bCoDAmt       := V_PZ_bCoAmt;  /*原币结转余额借方 */ 
              V_TdyBDAmt      := tdyitem.bamt;  /*原币当日发生额借方 */ 
              V_TdyBCoDAmt    := V_PZ_bCoAmt;  /*原币结转当日发生额借方 */ 
              V_TdyPurBDUlamt := V_BpurOUlamt;  /*原币申购未实现平准金借 */ 
              V_TdyRedBDUlamt := V_BredOUlamt;  /*原币赎回未实现平准金借  */ 
              
            ELSE
              IF V_isCountBBalance = 'Y' THEN 
                V_cAmt          := tdyitem.balance;  /*贷方余额_原币 */ 
                V_oCcyKnotsAmtC := V_PZ_oCcyKnotsAmt;  /*原币结转余额贷方 */ 
                V_TdyOCAmt      := tdyitem.balance;  /*原币当日发生额贷方 */ 
                V_TdyOCoCAmt    := V_PZ_oCcyKnotsAmt;  /*原币结转当日发生额贷方 */ 
                V_TdyPurOCUlamt := V_PurOUlamt;  /*原币申购未实现平准金贷 */ 
                V_TdyRedOCUlamt := V_RedOUlamt;  /*原币赎回未实现平准金贷 */ 
              END IF;
              
              V_bCAmt         := tdyitem.bamt;  /*贷方余额_原币 */ 
              V_bCoCAmt       := V_PZ_bCoAmt;  /*本币结转余额贷方 */ 
              V_TdyBCAmt      := tdyitem.bamt;  /*本币当日发生额贷方 */ 
              V_TdyBCoCAmt    := V_PZ_bCoAmt;  /*本币结转当日发生额贷方 */ 
              V_TdyPurBCUlamt := V_BpurOUlamt;  /*本币申购未实现平准金贷 */
              V_TdyRedBCUlamt := V_BredOUlamt;  /*本币赎回未实现平准金贷 */ 
            END IF;
            
            
            /*本币结转余额汇总处理 START*/
            
            V_oCcyKnotsAmt := V_PZ_oCcyKnotsAmt;
            V_bCoAmt := V_PZ_bCoAmt;
            
            IF tdyitem.bal_flag != 'B' THEN 
               IF tdyitem.balflaghappendate != tdyitem.bal_flag THEN 
                 V_oCcyKnotsAmt := V_PZ_oCcyKnotsAmt *-1;
                 V_bCoAmt := V_PZ_bCoAmt * -1;
               END IF;  
               V_balCoFlag :=  tdyitem.bal_flag;         
            ELSE
              IF tdyitem.balflaghappendate = 'C' THEN
                IF V_oCcyKnotsAmt < 0 THEN
                   V_oCcyKnotsAmt := V_PZ_oCcyKnotsAmt *-1;
                   V_bCoAmt := V_PZ_bCoAmt * -1;
                   V_balCoFlag := 'D';
                ELSE
                   V_oCcyKnotsAmt:= V_PZ_oCcyKnotsAmt;
                   V_bCoAmt := V_PZ_bCoAmt;
                   V_balCoFlag := 'C';
                END IF;
              END IF;
              IF tdyitem.balflaghappendate = 'D' THEN
                 IF V_oCcyKnotsAmt < 0 THEN 
                    V_oCcyKnotsAmt := V_PZ_oCcyKnotsAmt *-1;
                    V_bCoAmt := V_PZ_bCoAmt * -1;
                    V_balCoFlag := 'C';
                 ELSE
                    V_oCcyKnotsAmt:= V_PZ_oCcyKnotsAmt;
                    V_bCoAmt := V_PZ_bCoAmt;
                    V_balCoFlag := 'D';
                    END IF;
              END IF;
            END IF;
                
            IF V_isCountBBalance = 'N' THEN V_oCcyKnotsAmt := 0;END IF;
            V_TdyOCoAmt := V_oCcyKnotsAmt;
            V_TdyBCoAmt := V_bCoAmt;
            V_TdyCoFlag := V_balCoFlag;
            
            /*本币结转余额汇总处理 END*/
                         
            INSERT INTO BOK_BALANCE_BEF(
                BOOKSET_ID,
                BALANCE_DATE,
                SUBJECT_NO,
                FSUBJECT_NO,
                SUBJECT_LEVEL,
                BAL_FLAG,BAL_CO_FLAG,
                O_CCY,
                O_AMT,O_C_AMT,O_D_AMT,O_CO_AMT,O_CO_C_AMT,O_CO_D_AMT,
                B_CCY,
                B_AMT,B_C_AMT,B_CO_AMT,B_CO_C_AMT,B_CO_D_AMT,B_D_AMT,TDY_O_AMT,TDY_O_C_AMT,
                TDY_O_D_AMT,TDY_O_CO_AMT,TDY_O_CO_C_AMT,TDY_O_CO_D_AMT,TDY_B_AMT,TDY_B_C_AMT,
                TDY_B_D_AMT,TDY_B_CO_AMT,TDY_B_CO_C_AMT,TDY_B_CO_D_AMT,
                IS_LEAF,
                NUM_AMT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,
                TDY_AMT_FLAG,TDY_CO_FLAG,TDY_PUR_O_D_ULAMT,TDY_RED_O_D_ULAMT,TDY_PUR_O_C_ULAMT,
                TDY_RED_O_C_ULAMT,TDY_PUR_B_D_ULAMT,TDY_RED_B_D_ULAMT,TDY_PUR_B_C_ULAMT,TDY_RED_B_C_ULAMT)
             VALUES(
                tdyitem.booksetid,
                V_STRDATE,
                V_SUBJECT_NO,
                V_FSUBJECT_ID,
                V_SUBJECTLEVEL,
                V_BalFlagHappenDate,V_BalCoFlag,
                tdyitem.Occy,
                V_Balance,V_cAmt,V_dAmt,V_oCcyKnotsAmt,V_oCcyKnotsAmtC,V_oCcyKnotsAmtD,
                tdyitem.bccy,
                V_bAmt,V_bCAmt,V_bCoAmt,V_bCoCAmt,V_bCoDAmt,V_bDAmt,V_TdyOAmt,V_TdyOCAmt,
                V_TdyODAmt,V_tdyOCoAmt,V_TdyOCoCAmt,V_TdyOCoDAmt,V_TdyBAmt,V_TdyBCAmt,
                V_TdyBDAmt,V_tdyBCoAmt,V_TdyBCoCAmt,V_TdyBCoDAmt,
                V_ISLEAF,
                NVL(V_Num,0),
                'sjzl',null,sysdate,'sjzl',sysdate,
                V_TdyAmtFlag,V_TdyCoFlag,V_TdyPurODUlamt,V_TdyRedODUlamt,V_TdyPurOCUlamt,
                V_TdyRedOCUlamt,V_TdyPurBDUlamt,V_TdyRedBDUlamt,V_TdyPurBCUlamt,V_TdyRedBCUlamt
            );              
            COMMIT;
          /*2.2 当日科目余额表存在科目余额 */
          ELSE
            SELECT 
              BAL_FLAG,  /*余额方向*/
              BAL_CO_FLAG, /*结转余额方向*/
              O_AMT, /*原币余额*/
              O_C_AMT, /*原币余额贷方*/
              O_D_AMT, /*原币余额借方*/
              O_CO_AMT,  /*原币结转余额*/
              O_CO_C_AMT,  /*原币结转余额贷方*/
              O_CO_D_AMT,  /*原币结转余额借方*/
              B_AMT, /*本位币余额*/
              B_C_AMT, /*本位币余额贷方*/
              B_CO_AMT,  /*本位币结转余额*/
              B_CO_C_AMT,  /*本位币结转余额贷方*/
              B_CO_D_AMT,  /*本位币结转余额借方*/
              B_D_AMT, /*本位币余额借方*/
              TDY_O_AMT, /*原币当日发生额*/
              TDY_O_C_AMT, /*原币当日发生额贷方*/
              TDY_O_D_AMT, /*原币当日发生额借方*/
              TDY_O_CO_AMT,  /*原币结转当日发生额*/
              TDY_O_CO_C_AMT,  /*原币结转当日发生额贷方*/
              TDY_O_CO_D_AMT,  /*原币结转当日发生额借方*/
              TDY_B_AMT, /*本位币当日发生额*/
              TDY_B_C_AMT, /*本位币当日发生额贷方*/
              TDY_B_D_AMT, /*本位币当日发生额借方*/
              TDY_B_CO_AMT,  /*本位币结转当日发生额*/
              TDY_B_CO_C_AMT,  /*本位币结转当日发生额贷方*/
              TDY_B_CO_D_AMT,  /*本位币结转当日发生额借方*/
              NUM_AMT, /*数量余额*/
              TDY_AMT_FLAG,  /*当日发生额方向*/
              TDY_CO_FLAG, /*结转当日发生额方向*/
              TDY_PUR_O_D_ULAMT, /*原币申购未实现平准金借*/
              TDY_RED_O_D_ULAMT, /*原币赎回未实现平准金借*/
              TDY_PUR_O_C_ULAMT, /*原币申购未实现平准金贷*/
              TDY_RED_O_C_ULAMT, /*原币赎回未实现平准金贷*/
              TDY_PUR_B_D_ULAMT, /*本位币申购未实现平准金借*/
              TDY_RED_B_D_ULAMT, /*本位币赎回未实现平准金借*/
              TDY_PUR_B_C_ULAMT, /*本位币申购未实现平准金贷*/
              TDY_RED_B_C_ULAMT /*本位币赎回未实现平准金贷*/
              INTO 
              V_BalFlagHappenDate,  /*余额方向*/
              V_BalCoFlag,  /*结转余额方向*/
              V_Balance,  /*原币余额*/
              V_cAmt, /*原币余额贷方*/
              V_dAmt, /*原币余额借方*/
              V_oCcyKnotsAmt, /*原币结转余额*/
              V_oCcyKnotsAmtC,  /*原币结转余额贷方*/
              V_oCcyKnotsAmtD,  /*原币结转余额借方*/
              V_bAmt, /*本位币余额*/
              V_bCAmt,  /*本位币余额贷方*/
              V_bCoAmt, /*本位币结转余额*/
              V_bCoCAmt,  /*本位币结转余额贷方*/
              V_bCoDAmt,  /*本位币结转余额借方*/
              V_bDAmt,  /*本位币余额借方*/
              V_TdyOAmt,  /*原币当日发生额*/
              V_TdyOCAmt, /*原币当日发生额贷方*/
              V_TdyODAmt, /*原币当日发生额借方*/
              V_tdyOCoAmt,  /*原币结转当日发生额*/
              V_TdyOCoCAmt, /*原币结转当日发生额贷方*/
              V_TdyOCoDAmt, /*原币结转当日发生额借方*/
              V_TdyBAmt,  /*本位币当日发生额*/
              V_TdyBCAmt, /*本位币当日发生额贷方*/
              V_TdyBDAmt, /*本位币当日发生额借方*/
              V_tdyBCoAmt,  /*本位币结转当日发生额*/
              V_TdyBCoCAmt, /*本位币结转当日发生额贷方*/
              V_TdyBCoDAmt, /*本位币结转当日发生额借方*/
              V_Num,  /*数量余额*/
              V_TdyAmtFlag, /*当日发生额方向*/
              V_TdyCoFlag,  /*结转当日发生额方向*/
              V_TdyPurODUlamt,  /*原币申购未实现平准金借*/
              V_TdyRedODUlamt,  /*原币赎回未实现平准金借*/
              V_TdyPurOCUlamt,  /*原币申购未实现平准金贷*/
              V_TdyRedOCUlamt,  /*原币赎回未实现平准金贷*/
              V_TdyPurBDUlamt,  /*本位币申购未实现平准金借*/
              V_TdyRedBDUlamt,  /*本位币赎回未实现平准金借*/
              V_TdyPurBCUlamt,  /*本位币申购未实现平准金贷*/
              V_TdyRedBCUlamt  /*本位币赎回未实现平准金贷*/              
            FROM BOK_BALANCE_BEF A
            WHERE A.BOOKSET_ID = tdyitem.booksetid
            AND A.BALANCE_DATE = V_STRDATE
            AND A.SUBJECT_NO = V_SUBJECT_NO;
            
            /*科目余额汇总处理START*/     
            IF tdyitem.bal_flag != 'B' THEN
              IF V_BalFlagHappenDate != tdyitem.bal_flag then
                V_Balance := V_Balance * -1;
                V_bAmt := V_bAmt *-1;
                V_Num := V_Num * -1;
              
              END IF;
              V_BalFlagHappenDate := tdyitem.bal_flag;
              IF V_BalFlagHappenDate = tdyitem.balflaghappendate then
                 V_Balance := V_Balance + tdyitem.balance;
                 V_bAmt := V_bAmt + tdyitem.bamt;
                 V_Num := V_Num + tdyitem.num;
              ELSE
                V_Balance := V_Balance - tdyitem.balance;
                V_bAmt := V_bAmt -  tdyitem.bamt;
                V_Num := V_Num - tdyitem.num;
              END IF; 
            ELSE
              IF V_BalFlagHappenDate = tdyitem.balflaghappendate then 
                 V_Balance := V_Balance + tdyitem.balance;
                 V_bAmt := V_bAmt + tdyitem.bamt;
                 V_Num := V_Num + tdyitem.num;
              ELSE
                IF V_bAmt >= tdyitem.bamt THEN
                   V_Balance := V_Balance - tdyitem.balance;
                   V_bAmt := V_bAmt -  tdyitem.bamt;
                   V_Num := V_Num - tdyitem.num;
                ELSE
                   V_Balance :=  tdyitem.balance - V_Balance;
                   V_bAmt := tdyitem.bamt - V_bAmt;
                   V_Num := tdyitem.num - V_Num;
                   V_BalFlagHappenDate := tdyitem.balflaghappendate;
                END IF;
              END IF;
            END IF;
            
            IF V_isCountBBalance = 'N' THEN V_Balance := 0;END IF;
             /*科目余额汇总处理END*/
          
            IF tdyitem.balflaghappendate = 'D' THEN 
              IF V_isCountBBalance = 'Y' THEN 
                V_dAmt := V_dAmt + tdyitem.balance; 
                V_oCcyKnotsAmtD := V_oCcyKnotsAmtD + V_PZ_oCcyKnotsAmt; 
                V_TdyODAmt := V_TdyODAmt + tdyitem.balance;  
                V_TdyOCoDAmt := V_TdyOCoDAmt + V_PZ_oCcyKnotsAmt;   
                V_TdyPurODUlamt := V_TdyPurODUlamt + V_PurOUlamt;    
                V_TdyRedODUlamt  := V_TdyRedODUlamt + V_RedOUlamt;   
              END IF;
              
              V_bDAmt := V_bDAmt + tdyitem.bamt;  
              V_bCoDAmt := V_bCoDAmt + V_PZ_bCoAmt;   
              V_TdyBDAmt := V_TdyBDAmt + tdyitem.bamt; 
              V_TdyBCoDAmt := V_TdyBCoDAmt  + V_PZ_bCoAmt;  
              V_TdyPurBDUlamt  := V_TdyPurBDUlamt + V_BpurOUlamt;    
              V_TdyRedBDUlamt  := V_TdyRedBDUlamt + V_BredOUlamt;
            ELSE
              IF V_isCountBBalance = 'Y' THEN 
                V_cAmt := V_cAmt + tdyitem.balance;   
                V_oCcyKnotsAmtC := V_oCcyKnotsAmtC + V_PZ_oCcyKnotsAmt;  
                V_TdyOCAmt := V_TdyOCAmt + tdyitem.balance;       
                V_TdyOCoCAmt := V_TdyOCoCAmt + V_PZ_oCcyKnotsAmt;    
                V_TdyPurOCUlamt := V_TdyPurOCUlamt + V_PurOUlamt;   
                V_TdyRedOCUlamt := V_TdyRedOCUlamt + V_RedOUlamt;   
              END IF;
              
              V_bCAmt := V_bCAmt + tdyitem.bamt;           
              V_bCoCAmt := V_bCoCAmt + V_PZ_bCoAmt;  
              V_TdyBCAmt := V_TdyBCAmt + tdyitem.bamt;        
              V_TdyBCoCAmt := V_TdyBCoCAmt + V_PZ_bCoAmt;   
              V_TdyPurBCUlamt := V_TdyPurBCUlamt + V_BpurOUlamt;    
              V_TdyRedBCUlamt := V_TdyRedBCUlamt + V_BredOUlamt;  
            END IF;
            
            
            
            IF V_PZ_oCcyKnotsAmt != 0 or V_PZ_bCoAmt != 0 THEN 
               IF V_BalCoFlag IS NULL THEN V_BalCoFlag := 'D'; END IF;
               IF V_PZ_oCcyKnotsAmt IS NULL THEN V_PZ_oCcyKnotsAmt := 0; END IF;
               IF V_PZ_bCoAmt IS NULL THEN V_PZ_bCoAmt :=0; END IF;
                
               /*结转余额汇总处理 START*/              
               IF tdyitem.bal_flag != 'B' THEN
                IF tdyitem.bal_flag != V_BalCoFlag then
                   V_oCcyKnotsAmt := V_oCcyKnotsAmt * -1;
                   V_bCoAmt := V_bCoAmt * -1;
                END IF; 
                V_BalCoFlag := tdyitem.bal_flag;
                IF V_BalCoFlag = tdyitem.Balflaghappendate THEN 
                   V_oCcyKnotsAmt := V_oCcyKnotsAmt + V_PZ_oCcyKnotsAmt;
                   V_bCoAmt := V_bCoAmt + V_PZ_bCoAmt;
                ELSE
                   V_oCcyKnotsAmt := V_oCcyKnotsAmt - V_PZ_oCcyKnotsAmt;
                   V_bCoAmt := V_bCoAmt - V_PZ_bCoAmt;
                END IF;
              ELSE
                IF V_BalCoFlag = tdyitem.Balflaghappendate THEN 
                   V_oCcyKnotsAmt := V_oCcyKnotsAmt + V_PZ_oCcyKnotsAmt;
                   V_bCoAmt := V_bCoAmt + V_PZ_bCoAmt;                
                ELSE
                  IF V_bCoAmt >= V_PZ_bCoAmt THEN
                     V_oCcyKnotsAmt := V_oCcyKnotsAmt - V_PZ_oCcyKnotsAmt;
                     V_bCoAmt := V_bCoAmt - V_PZ_bCoAmt;
                  END IF;
                     V_oCcyKnotsAmt := V_PZ_oCcyKnotsAmt - V_oCcyKnotsAmt;
                     V_bCoAmt := V_PZ_bCoAmt - V_bCoAmt;
                     V_BalCoFlag := tdyitem.Balflaghappendate;                
                END IF;
              END IF;
              IF V_isCountBBalance = 'N' THEN V_oCcyKnotsAmt := 0;END IF;         
              /*结转余额汇总处理 END*/ 
              
              
              /*当日结转发生额处理 START*/
              IF V_TdyCoFlag IS NULL THEN V_TdyCoFlag := 'D'; END IF;
              IF V_TdyOCoAmt IS NULL THEN V_TdyOCoAmt :=0; END IF;
              IF V_TdyBCoAmt IS NULL THEN V_TdyBCoAmt :=0; END IF;
            
              IF tdyitem.bal_flag != 'B' THEN
                IF tdyitem.bal_flag != V_TdyCoFlag then
                   V_TdyOCoAmt := V_TdyOCoAmt * -1;
                   V_TdyBCoAmt := V_TdyBCoAmt * -1;
                END IF; 
                V_TdyCoFlag := tdyitem.bal_flag;
                IF V_TdyCoFlag = tdyitem.Balflaghappendate THEN 
                   V_TdyOCoAmt := V_TdyOCoAmt + tdyitem.balance;
                   V_TdyBCoAmt := V_TdyBCoAmt + tdyitem.bamt;
                ELSE
                   V_TdyOCoAmt := V_TdyOCoAmt - tdyitem.balance;
                   V_TdyBCoAmt := V_TdyBCoAmt - tdyitem.bamt;
                END IF;
              ELSE
                IF V_TdyCoFlag = tdyitem.Balflaghappendate THEN 
                   V_TdyOCoAmt := V_TdyOCoAmt + tdyitem.balance;
                   V_TdyBCoAmt := V_TdyBCoAmt + tdyitem.bamt;                
                ELSE
                  IF V_TdyBCoAmt >= tdyitem.bamt THEN
                     V_TdyOCoAmt := V_TdyOCoAmt - tdyitem.balance;
                     V_TdyBCoAmt := V_TdyBCoAmt - tdyitem.bamt;
                  END IF;
                     V_TdyOCoAmt := tdyitem.balance - V_TdyOCoAmt;
                     V_TdyBCoAmt := tdyitem.bamt - V_TdyBCoAmt;
                     V_TdyCoFlag := tdyitem.Balflaghappendate;                
                  END IF;
              END IF;
              IF V_isCountBBalance = 'N' THEN V_TdyOCoAmt := 0;END IF;
              /*当日结转发生额处理 END*/
              
              
            END IF;
                        
            IF V_TdyAmtFlag IS NULL THEN V_TdyAmtFlag := 'D';END IF;
            IF V_TdyOAmt IS NULL THEN V_TdyOAmt := 0 ; END IF;
            IF V_TdyBAmt IS NULL THEN V_TdyBAmt := 0 ; END IF;
            
            IF tdyitem.bal_flag != 'B' THEN
              IF tdyitem.bal_flag != V_TdyAmtFlag then
                 V_TdyOAmt := V_TdyOAmt * -1;
                 V_TdyBAmt := V_TdyBAmt * -1;
              END IF; 
              V_TdyAmtFlag := tdyitem.bal_flag;
              IF V_TdyCoFlag = tdyitem.Balflaghappendate THEN 
                 V_TdyOAmt := V_TdyOAmt + tdyitem.balance;
                 V_TdyBAmt := V_TdyBAmt + tdyitem.bamt;
              ELSE
                 V_TdyOAmt := V_TdyOAmt - tdyitem.balance;
                 V_TdyBAmt := V_TdyBAmt - tdyitem.bamt;
              END IF;
            ELSE
              IF V_TdyAmtFlag = tdyitem.Balflaghappendate THEN 
                 V_TdyOAmt := V_TdyOAmt + tdyitem.balance;
                 V_TdyBAmt := V_TdyBAmt + tdyitem.bamt;                
              ELSE
                IF V_bCoAmt >= tdyitem.bamt THEN
                   V_TdyOAmt := V_TdyOAmt - tdyitem.balance;
                   V_TdyBAmt := V_TdyBAmt - tdyitem.bamt;
                END IF;
                   V_TdyOAmt := tdyitem.balance - V_TdyOAmt;
                   V_TdyBAmt := tdyitem.bamt - V_TdyBAmt;
                   V_TdyAmtFlag := tdyitem.Balflaghappendate;                
              END IF;
            END IF;
            
            IF V_isCountBBalance = 'N' THEN V_TdyOAmt := 0;END IF;
            
            UPDATE BOK_BALANCE_BEF A SET
                A.BAL_FLAG = V_BalFlagHappenDate, /*余额方向*/
                A.BAL_CO_FLAG = V_BalCoFlag, /*结转余额方向*/
                A.O_AMT = V_Balance, /*原币余额*/
                A.O_C_AMT = V_cAmt, /*原币余额贷方*/
                A.O_D_AMT = V_dAmt, /*原币余额借方*/
                A.O_CO_AMT = V_oCcyKnotsAmt, /*原币结转余额*/
                A.O_CO_C_AMT = V_oCcyKnotsAmtC, /*原币结转余额贷方*/
                A.O_CO_D_AMT = V_oCcyKnotsAmtD, /*原币结转余额借方*/
                A.B_AMT = V_bAmt, /*本位币余额*/
                A.B_C_AMT = V_bCAmt, /*本位币余额贷方*/
                A.B_CO_AMT = V_bCoAmt, /*本位币结转余额*/
                A.B_CO_C_AMT = V_bCoCAmt, /*本位币结转余额贷方*/
                A.B_CO_D_AMT = V_bCoDAmt, /*本位币结转余额借方*/
                A.B_D_AMT = V_bDAmt, /*本位币余额借方*/
                A.TDY_O_AMT = V_TdyOAmt, /*原币当日发生额*/
                A.TDY_O_C_AMT = V_TdyOCAmt, /*原币当日发生额贷方*/
                A.TDY_O_D_AMT = V_TdyODAmt, /*原币当日发生额借方*/
                A.TDY_O_CO_AMT = V_tdyOCoAmt, /*原币结转当日发生额*/
                A.TDY_O_CO_C_AMT = V_TdyOCoCAmt, /*原币结转当日发生额贷方*/
                A.TDY_O_CO_D_AMT = V_TdyOCoDAmt, /*原币结转当日发生额借方*/
                A.TDY_B_AMT = V_TdyBAmt, /*本位币当日发生额*/
                A.TDY_B_C_AMT = V_TdyBCAmt, /*本位币当日发生额贷方*/
                A.TDY_B_D_AMT = V_TdyBDAmt, /*本位币当日发生额借方*/
                A.TDY_B_CO_AMT = V_tdyBCoAmt, /*本位币结转当日发生额*/
                A.TDY_B_CO_C_AMT = V_TdyBCoCAmt, /*本位币结转当日发生额贷方*/
                A.TDY_B_CO_D_AMT = V_TdyBCoDAmt, /*本位币结转当日发生额借方*/
                A.NUM_AMT = NVL(V_Num,0), /*数量余额*/
                A.TDY_AMT_FLAG = V_TdyAmtFlag, /*当日发生额方向*/
                A.TDY_CO_FLAG = V_TdyCoFlag, /*结转当日发生额方向*/
                A.TDY_PUR_O_D_ULAMT = V_TdyPurODUlamt, /*原币申购未实现平准金借*/
                A.TDY_RED_O_D_ULAMT = V_TdyRedODUlamt, /*原币赎回未实现平准金借*/
                A.TDY_PUR_O_C_ULAMT = V_TdyPurOCUlamt, /*原币申购未实现平准金贷*/
                A.TDY_RED_O_C_ULAMT = V_TdyRedOCUlamt, /*原币赎回未实现平准金贷*/
                A.TDY_PUR_B_D_ULAMT = V_TdyPurBDUlamt, /*本位币申购未实现平准金借*/
                A.TDY_RED_B_D_ULAMT = V_TdyRedBDUlamt, /*本位币赎回未实现平准金借*/
                A.TDY_PUR_B_C_ULAMT = V_TdyPurBCUlamt, /*本位币申购未实现平准金贷*/
                A.TDY_RED_B_C_ULAMT = V_TdyRedBCUlamt /*本位币赎回未实现平准金贷*/
            WHERE A.BOOKSET_ID= tdyitem.booksetid
            AND A.BALANCE_DATE = V_STRDATE
            AND A.SUBJECT_NO = V_SUBJECT_NO;
            COMMIT;
          END IF;
          V_SUBJECT_NO := V_FSUBJECT_ID;
          V_SUBJECTLEVEL := V_FSUBJECTLEVEL;
          V_ISLEAF := 'N';
          
          EXIT repeat_loop WHEN V_FSUBJECT_ID IS NULL;/*exit结束循环*/
          END LOOP repeat_loop;
        END LOOP;
    V_STRDATE := V_STRDATE + 1;END LOOP;
  END LOOP;                 
END;
/
--3.业务数据验证通过后，删除临时表
DROP TABLE BOK_BOOKSET_BEF; 