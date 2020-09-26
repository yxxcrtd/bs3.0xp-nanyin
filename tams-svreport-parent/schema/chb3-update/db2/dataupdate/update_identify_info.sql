--1.查询重复数据，并按规则记录到光标中

DECLARE
    CURSOR C1 IS  
        select * from (
            select rank() over(partition by (
            IDTYPE
            ||'|'||IDNUM
            ||'|'||IDNAME) order by IDMARK) my_rank,IDMARK,IDTYPE,IDNUM,IDNAME 
            from (
                    select equ.INDENTIFY_MARK IDMARK,equ.PERSONAL_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'') IDTYPE,equ.IDENTIFY_NUMBER IDNUM,equ.HOLDER_NAME IDNAME 
                    from chb_equ_hold equ,
                    (
                        select 
                        count(1),
                        PERSONAL_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'') IDTYPE--证件类别
                        ,IDENTIFY_NUMBER  IDNUM --证件号码
                        ,HOLDER_NAME IDNAME--权益人名称
                        from CHB_EQU_HOLD
                        where PERSONAL_IDENTIFY_TYPE is not null
                        group by PERSONAL_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,''),IDENTIFY_NUMBER,HOLDER_NAME
                        having count(1)>1 
                    ) dupequ  /*重复的数据*/
                    where equ.PERSONAL_IDENTIFY_TYPE||nvl(equ.OTHER_IDENTIFY_NAME,'')=dupequ.IDTYPE
                    and equ.IDENTIFY_NUMBER=dupequ.IDNUM
                    and equ.HOLDER_NAME=dupequ.IDNAME
            )  /*带识别标示的重复数数据*/
        ) 
        where my_rank=1;  /*分组找到最小的数据*/



   CURSOR C2 IS  
        select * from (
            select rank() over(partition by (
            IDTYPE
            ||'|'||IDNUM
            ||'|'||IDNAME) order by IDMARK) my_rank,IDMARK,IDTYPE,IDNUM,IDNAME 
            from (
                    select equ.INDENTIFY_MARK IDMARK,equ.INSTITUTION_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'') IDTYPE,equ.IDENTIFY_NUMBER IDNUM,equ.HOLDER_NAME IDNAME 
                    from chb_equ_hold equ,
                    (
                        select 
                        count(1),
                        INSTITUTION_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'') IDTYPE--证件类别
                        ,IDENTIFY_NUMBER  IDNUM --证件号码
                        ,HOLDER_NAME IDNAME--权益人名称
                        from CHB_EQU_HOLD
                        where INSTITUTION_IDENTIFY_TYPE is not null
                        group by INSTITUTION_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,''),IDENTIFY_NUMBER,HOLDER_NAME
                        having count(1)>1 
                    ) dupequ  /*重复的数据*/
                    where equ.INSTITUTION_IDENTIFY_TYPE||nvl(equ.OTHER_IDENTIFY_NAME,'')=dupequ.IDTYPE
                    and equ.IDENTIFY_NUMBER=dupequ.IDNUM
                    and equ.HOLDER_NAME=dupequ.IDNAME
            )  /*带识别标示的重复数数据*/
        ) 
        where my_rank=1;  /*分组找到最小的数据*/   
          
begin
    for i_equ in C1
      loop   
      insert into CHB_EQU_HOLD_CHECK (
        INDENTIFY_MARK
        ,ORG_INDENTIFY_MARK
        ,MERGER_INDENTIFY_MARK
        ,REGIST_BANK_CODE
        ,IS_BELONG_BANK
        ,THE_BANK_NAME
        ,THE_BANK_CODE
        ,HOLDER_MARK
        ,HOLDER_REGION
        ,HOLDER_TYPE
        ,PERSONAL_IDENTIFY_TYPE
        ,INSTITUTION_IDENTIFY_TYPE
        ,OTHER_IDENTIFY_NAME
        ,IDENTIFY_NUMBER
        ,SPV_CAP_ACC_BANK
        ,OTHER_CAP_ACC_BANK
        ,SPV_REGIST_CODE
        ,HOLDER_DATE
        ,HOLDER_NAME
        ,HOLDER_SEX
        ,HOLDER_RISK_PREFER
        ,HOLDER_CELLPHONE
        ,HOLDER_TELEPHONE
        ,IS_SUBMIT
        ,HOLDER_EMAIL
        ,REMARK
        ,STATUS
        ,OPERATE_STATUS
        ,WORKFLOW_ID
        ,DEPT_CODE
        ,UPDATE_USER
        ,UPDATE_TIME
        ,CREATE_USER
        ,CREATE_TIME
        ,DATA_DATE
        ,DATA_SOURCE
        ,ORG_CODE
        ,AGENT
        ,CREATE_DEPT
        ,INTERF_CHECK_RESULT
    )
    select         
        INDENTIFY_MARK
        ,ORG_INDENTIFY_MARK
        ,i_equ.IDMARK
        ,REGIST_BANK_CODE
        ,IS_BELONG_BANK
        ,THE_BANK_NAME
        ,THE_BANK_CODE
        ,HOLDER_MARK
        ,HOLDER_REGION
        ,HOLDER_TYPE
        ,PERSONAL_IDENTIFY_TYPE
        ,INSTITUTION_IDENTIFY_TYPE
        ,OTHER_IDENTIFY_NAME
        ,IDENTIFY_NUMBER
        ,SPV_CAP_ACC_BANK
        ,OTHER_CAP_ACC_BANK
        ,SPV_REGIST_CODE
        ,HOLDER_DATE
        ,HOLDER_NAME
        ,HOLDER_SEX
        ,HOLDER_RISK_PREFER
        ,HOLDER_CELLPHONE
        ,HOLDER_TELEPHONE
        ,IS_SUBMIT
        ,HOLDER_EMAIL
        ,REMARK
        ,STATUS
        ,'02'
        ,WORKFLOW_ID
        ,DEPT_CODE
        ,UPDATE_USER
        ,UPDATE_TIME
        ,CREATE_USER
        ,CREATE_TIME
        ,sysdate
        ,DATA_SOURCE
        ,ORG_CODE
        ,AGENT
        ,CREATE_DEPT
        ,INTERF_CHECK_RESULT
        from CHB_EQU_HOLD
      where PERSONAL_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ.IDTYPE
        and IDENTIFY_NUMBER=i_equ.IDNUM
        and HOLDER_NAME=i_equ.IDNAME;
        
      delete from CHB_EQU_HOLD 
      where PERSONAL_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ.IDTYPE
        and IDENTIFY_NUMBER=i_equ.IDNUM
        and HOLDER_NAME=i_equ.IDNAME
        and INDENTIFY_MARK<>i_equ.IDMARK;
    
      delete from CHB_EQU_HOLD_HIS 
      where PERSONAL_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ.IDTYPE
        and IDENTIFY_NUMBER=i_equ.IDNUM
        and HOLDER_NAME=i_equ.IDNAME
        and INDENTIFY_MARK<>i_equ.IDMARK;
    
      delete from CHB_EQU_SUB 
      where PERSONAL_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ.IDTYPE
        and IDENTIFY_NUMBER=i_equ.IDNUM
        and HOLDER_NAME=i_equ.IDNAME
        and INDENTIFY_MARK<>i_equ.IDMARK;
    
      delete from CHB_EQU_SUB_HIS 
      where PERSONAL_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ.IDTYPE
        and IDENTIFY_NUMBER=i_equ.IDNUM
        and HOLDER_NAME=i_equ.IDNAME
        and INDENTIFY_MARK<>i_equ.IDMARK;
      
      end loop;

    for i_equ2 in C2
      loop   
      insert into CHB_EQU_HOLD_CHECK (
        INDENTIFY_MARK
        ,ORG_INDENTIFY_MARK
        ,MERGER_INDENTIFY_MARK
        ,REGIST_BANK_CODE
        ,IS_BELONG_BANK
        ,THE_BANK_NAME
        ,THE_BANK_CODE
        ,HOLDER_MARK
        ,HOLDER_REGION
        ,HOLDER_TYPE
        ,PERSONAL_IDENTIFY_TYPE
        ,INSTITUTION_IDENTIFY_TYPE
        ,OTHER_IDENTIFY_NAME
        ,IDENTIFY_NUMBER
        ,SPV_CAP_ACC_BANK
        ,OTHER_CAP_ACC_BANK
        ,SPV_REGIST_CODE
        ,HOLDER_DATE
        ,HOLDER_NAME
        ,HOLDER_SEX
        ,HOLDER_RISK_PREFER
        ,HOLDER_CELLPHONE
        ,HOLDER_TELEPHONE
        ,IS_SUBMIT
        ,HOLDER_EMAIL
        ,REMARK
        ,STATUS
        ,OPERATE_STATUS
        ,WORKFLOW_ID
        ,DEPT_CODE
        ,UPDATE_USER
        ,UPDATE_TIME
        ,CREATE_USER
        ,CREATE_TIME
        ,DATA_DATE
        ,DATA_SOURCE
        ,ORG_CODE
        ,AGENT
        ,CREATE_DEPT
        ,INTERF_CHECK_RESULT
    )
    select         
        INDENTIFY_MARK
        ,ORG_INDENTIFY_MARK
        ,i_equ2.IDMARK
        ,REGIST_BANK_CODE
        ,IS_BELONG_BANK
        ,THE_BANK_NAME
        ,THE_BANK_CODE
        ,HOLDER_MARK
        ,HOLDER_REGION
        ,HOLDER_TYPE
        ,PERSONAL_IDENTIFY_TYPE
        ,INSTITUTION_IDENTIFY_TYPE
        ,OTHER_IDENTIFY_NAME
        ,IDENTIFY_NUMBER
        ,SPV_CAP_ACC_BANK
        ,OTHER_CAP_ACC_BANK
        ,SPV_REGIST_CODE
        ,HOLDER_DATE
        ,HOLDER_NAME
        ,HOLDER_SEX
        ,HOLDER_RISK_PREFER
        ,HOLDER_CELLPHONE
        ,HOLDER_TELEPHONE
        ,IS_SUBMIT
        ,HOLDER_EMAIL
        ,REMARK
        ,STATUS
        ,'02'
        ,WORKFLOW_ID
        ,DEPT_CODE
        ,UPDATE_USER
        ,UPDATE_TIME
        ,CREATE_USER
        ,CREATE_TIME
        ,sysdate
        ,DATA_SOURCE
        ,ORG_CODE
        ,AGENT
        ,CREATE_DEPT
        ,INTERF_CHECK_RESULT
        from CHB_EQU_HOLD
      where INSTITUTION_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ2.IDTYPE
        and IDENTIFY_NUMBER=i_equ2.IDNUM
        and HOLDER_NAME=i_equ2.IDNAME;
        
      delete from CHB_EQU_HOLD 
      where INSTITUTION_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ2.IDTYPE
        and IDENTIFY_NUMBER=i_equ2.IDNUM
        and HOLDER_NAME=i_equ2.IDNAME
        and INDENTIFY_MARK<>i_equ2.IDMARK;
    
      delete from CHB_EQU_HOLD_HIS 
      where INSTITUTION_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ2.IDTYPE
        and IDENTIFY_NUMBER=i_equ2.IDNUM
        and HOLDER_NAME=i_equ2.IDNAME
        and INDENTIFY_MARK<>i_equ2.IDMARK;
    
      delete from CHB_EQU_SUB 
      where INSTITUTION_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ2.IDTYPE
        and IDENTIFY_NUMBER=i_equ2.IDNUM
        and HOLDER_NAME=i_equ2.IDNAME
        and INDENTIFY_MARK<>i_equ2.IDMARK;
    
      delete from CHB_EQU_SUB_HIS 
      where INSTITUTION_IDENTIFY_TYPE||nvl(OTHER_IDENTIFY_NAME,'')=i_equ2.IDTYPE
        and IDENTIFY_NUMBER=i_equ2.IDNUM
        and HOLDER_NAME=i_equ2.IDNAME
        and INDENTIFY_MARK<>i_equ2.IDMARK;
      
      end loop; 
 
    commit;
--    close C1;
--    close C2;
end;


