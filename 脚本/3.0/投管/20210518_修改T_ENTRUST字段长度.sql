SELECT * FROM Inf_Contract_Detailfile t where t.entrust_id = 'WTREPO202105140694';

select * from ptl_sec_position_flow t order by t.create_time desc;

----更新ACCOUNT_NAME 字段长度
alter table T_ENTRUST modify ACCOUNT_NAME  varchar2 (200); 
