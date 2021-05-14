/*1 基础科目性质由[QTZC]改为[QTFYZC]*/

/*1.1 账务明细历史表：基础科目性质字段更新*/
UPDATE BOK_BOOKDETAIL_ALL SET 																
  BASE_SUBJECT_NATURE = 'QTFYZC',																
  UPDATE_USER = 'sjzl',																
  UPDATE_TIME = SYSDATE																
WHERE SUBJECT_NO IN ('66050101','66050102','66050103','66050104','66050199','66050201','66050301','66050302');

/*1.2 账务明细当前表：基础科目性质字段更新*/
UPDATE BOK_BOOKDETAIL_CURRENT SET 																
  BASE_SUBJECT_NATURE = 'QTFYZC',																
  UPDATE_USER = 'sjzl',																
  UPDATE_TIME = SYSDATE																
WHERE SUBJECT_NO IN ('66050101','66050102','66050103','66050104','66050199','66050201','66050301','66050302');

/*1.3 明细科目数据表：基础科目性质字段更新*/
UPDATE BOK_SUBJECT_DETAIL SET 																
  BASE_SUBJECT_NATURE = 'QTFYZC',													
  UPDATE_USER = 'sjzl',																
  UPDATE_TIME = SYSDATE																
WHERE SUBJECT_NO IN ('66050101','66050102','66050103','66050104','66050199','66050201','66050301','66050302');

COMMIT;