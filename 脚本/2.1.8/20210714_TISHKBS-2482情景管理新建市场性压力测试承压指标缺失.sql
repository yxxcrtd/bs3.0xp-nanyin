delete from stt_stress_indicator where SEQ_NO='IndTest019';
insert into stt_stress_indicator (SEQ_NO, BUSI_ID, IND_ID, MODEL_ID, STT_BUSI_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('IndTest019', '1', '10025', 'MarketTestModel0001', '03', 'system', '', sysdate, 'system', sysdate);
