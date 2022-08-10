INSERT INTO SYS_PARA
(SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME, UPDATE_FLG)
VALUES('SYS', 'allow_force_complete', '01', '01', '数据字典是否', '是否允许审批节点提前结束流程（01：允许，02：不允许）', 'system', sysdate, 'system', sysdate, '01');