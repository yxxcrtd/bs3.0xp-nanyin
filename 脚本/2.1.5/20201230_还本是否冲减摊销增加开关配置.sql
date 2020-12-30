--还本是否冲减摊销增加开关配置
DELETE FROM BOK_BOOKSET_EXT WHERE BOOKSET_ID IN ('00000000000000000002','00000000000000000003','00000000000000000004') AND PARAM_CODE='REDAMOFLAG';
INSERT INTO BOK_BOOKSET_EXT values ('00000000000000000002','REDAMOFLAG','Y','还本是否冲减摊销','Y：是  N：否','system','',sysdate, 'system',sysdate);
INSERT INTO BOK_BOOKSET_EXT values ('00000000000000000003','REDAMOFLAG','Y','还本是否冲减摊销','Y：是  N：否','system','',sysdate, 'system',sysdate);
INSERT INTO BOK_BOOKSET_EXT values ('00000000000000000004','REDAMOFLAG','Y','还本是否冲减摊销','Y：是  N：否','system','',sysdate, 'system',sysdate);
