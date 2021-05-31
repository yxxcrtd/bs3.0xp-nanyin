/*计划类资产交易增加投资目的：账务明细代码代码调整*/
/*1.数据更新准备*/
/*1.1 创建MD5加密函数*/
CREATE OR REPLACE FUNCTION GET_MD5
( busiId in varchar2,
  busiDetailType in varchar2,
  invAim in varchar2,
  branch in number,
  ccy in varchar2,
  profitType in varchar2
)
RETURN varchar2 IS
v_result varchar2(1000) := '';
BEGIN
  begin
    if busiDetailType = 'F01' then
      v_result := '1'||busiId||'2'||busiDetailType;
      if invAim is not null then
        v_result := v_result||'3'||invAim||'4';
      end if;
    else
      v_result := '1'||busiId||'2'||busiDetailType;
      if busiDetailType = 'F17' AND profitType ='02' then 
        if invAim is not null then
           v_result := v_result||'3'||invAim;
        end if;
      else
        if branch is not null and branch !=0 then
          v_result := v_result||'5'||branch;
        end if;
        if busiDetailType like 'A%' and ccy is not null then
           v_result := v_result||'6'||ccy;
        end if;
      end if;
    end if;
  end;
  RETURN LOWER(Utl_Raw.Cast_To_Raw(DBMS_OBFUSCATION_TOOLKIT.MD5(input_string => v_result)));
END;
/
/*1.2 创建临时表，提取待调整账务明细代码数据*/
CREATE TABLE BOK_DETAIL_ID_TZ AS
SELECT 
     A.BOOKSET_ID,
     A.BOK_DETAIL_ID BOK_DETAIL_ID_OLD,
     A.BUSI_ID,
     A.BUSI_DETAIL_TYPE,
     'AC' INV_AIM,
     NVL(B.PROFIT_TYPE,'02') PROFIT_TYPE,
     A.BRANCH,
     A.CCY,
     A.DETAIL_SUBJECT_NO_TAIL,
     A.DETAIL_SUBNAME_TAIL,
     GET_MD5(A.BUSI_ID,A.BUSI_DETAIL_TYPE,'AC',A.BRANCH,A.CCY,NVL(B.PROFIT_TYPE,'02')) AS BOK_DETAIL_ID
FROM BOK_DETAIL_ID A
LEFT JOIN FIN_PRODUCT B ON A.BUSI_ID = B.FINPROD_ID 
WHERE A.BUSI_DETAIL_TYPE = 'F17' AND B.FINPROD_TYPE='F17'
AND (B.PROFIT_TYPE IS NULL OR B.PROFIT_TYPE ='02')
AND (A.INV_AIM IS NULL OR A.INV_AIM ='999999');

/*2.业务数据更新-开始*/
/*2.1 有账务明细代码字段表数据*/
DECLARE
  CURSOR LIST IS
         SELECT
            BOOKSET_ID,BUSI_ID,
            BUSI_DETAIL_TYPE,BOK_DETAIL_ID,
            DETAIL_SUBJECT_NO_TAIL,DETAIL_SUBNAME_TAIL,
            BOK_DETAIL_ID_OLD,
            INV_AIM,BRANCH,CCY
         FROM BOK_DETAIL_ID_TZ;
  BEGIN
  FOR item IN LIST
  LOOP
    /*增值税已抵扣增值额*/
    UPDATE TAX_DEDUCTED_BALANCE S SET BOK_DETAIL_ID= item.BOK_DETAIL_ID
    ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
    WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
    /*增值税待抵扣增值额*/
    UPDATE TAX_TURNED_BALANCE  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID
    ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
    WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
    /*增值税账务明细余额*/
    UPDATE TAX_BOK_BALANCE  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID
    ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
    WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
   /*增值税账务明细流水*/
    UPDATE TAX_BOK_DETAIL  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID
    ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
    WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
   /*账务明细辅助表*/
   UPDATE BOK_BOOKDETAIL_CURRENT_EXT  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID
   ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
   /*统计分析明细表（账务明细）*/
   UPDATE BOK_STAT_DET_POSIITON  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID
   ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
   /*账务明细（历史表）*/
   UPDATE BOK_BOOKDETAIL_ALL  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID
     ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
   /*账务明细（当前表）*/
   UPDATE BOK_BOOKDETAIL_CURRENT  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID
     ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
   /*手工凭证交易明细*/
   UPDATE TRD_VOUCH_DEAL_DETAIL  SET BOK_DETAIL_ID = item.BOK_DETAIL_ID,
   INV_AIM = item.INV_AIM
   ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
   /*交易入账信息明细表*/
   UPDATE BOK_DEAL_ACCOUNTING_DETAIL  SET BOK_DETAIL_ID= item.BOK_DETAIL_ID
   ,UPDATE_USER ='sjzl',UPDATE_TIME =SYSDATE
   WHERE BOK_DETAIL_ID=item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
   COMMIT;
   /*账务明细代码表*/
   UPDATE BOK_DETAIL_ID  SET BOK_DETAIL_ID = item.BOK_DETAIL_ID,INV_AIM =item.INV_AIM
   WHERE BOK_DETAIL_ID = item.BOK_DETAIL_ID_OLD AND BOOKSET_ID= item.BOOKSET_ID;
   COMMIT;
  END LOOP;
END;
/

/*3.结束-业务数据更新，数据验证通过后，删除过程使用的临时表和函数*/
DROP TABLE BOK_DETAIL_ID_TZ;
DROP FUNCTION GET_MD5;