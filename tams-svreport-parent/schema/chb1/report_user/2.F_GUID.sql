create or replace FUNCTION "F_GUID" return varchar2 is
Result varchar2(32);
kk varchar2(64);
begin
Result := '';
select sys_guid() into Result from dual;
select substr(result,1,8)||'-'||substr(result,9,4)||'-'||substr(result,13,4)||'-'||substr(result,17,4)||'-'||substr(result,21,12) into kk from dual;
return(kk);
END f_guid;