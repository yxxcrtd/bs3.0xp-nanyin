delete from  chb_datacheck_rule  where CHECKRULEID='CHB_SALER_INST_INFO-2022-0110-118';
Insert into chb_datacheck_rule (CHECKRULEID,DATATYPE,CHECKCLASS,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS)
values ('CHB_SALER_INST_INFO-2022-0110-118','CHB_SALER_INST_INFO','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','inDatadict','SALER_ORG_LEVEL','==','SALER_STATUS','N','机构层级不可为空或不在值域范围内。','E');
