DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinProjectTargetAsset002' AND ELEMENT_ID = 'bussParticipantInfo';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinProjectTargetAsset002','bussParticipantInfo','bussParticipantInfoDto','合作资产信息','02','02','02',null);