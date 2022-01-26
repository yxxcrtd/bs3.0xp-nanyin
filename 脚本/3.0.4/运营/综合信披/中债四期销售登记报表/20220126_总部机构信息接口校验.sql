update  chb_datacheck_rule set OBJVALUE='SALER_ORG_LEVEL'  WHERE   CHECKRULEID='CHB_SALER_INST_INFO-2022-0110-118   ';
delete from  chb_datacheck_rule where CHECKRULEID='CHB_SALER_INST_INFO-2022-7856-220   ';
Insert into chb_datacheck_rule (CHECKRULEID,DATATYPE,CHECKCLASS,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS)
values ('CHB_SALER_INST_INFO-2022-7856-220','CHB_SALER_INST_INFO','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','SALER_INST_NAME',null,null,'N','销售机构名称不可为空。','E');
