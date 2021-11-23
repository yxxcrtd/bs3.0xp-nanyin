/***中债数据源规范是yyyy-MM-dd,但是提供正例是yyyyMMdd  暂时修改为同时支持yyyyMMdd和yyyy-MM-dd  需要更新相关代码*/
update CHB_DATACHECK_RULE set  CHECKMETHOD = 'isValidDate3',REMARK = REPLACE(REMARK,'YYYY-MM-DD','yyyyMMdd或yyyy-MM-dd') WHERE CHECKMETHOD = 'isValidDate2' AND DATATYPE NOT IN ('CHB_EQUDET');
/***中债数据源规范是yyyy-MM-dd*/

/***验证数据字典范围..待确认范围,如果不改也要修改提示.避免错误理解*/
UPDATE CHB_DATACHECK_RULE SET REMARK = '融资人所属地区不可为空且在值域范围内,需要填写至区域二级分类' WHERE CHECKRULEID = '884807E5-DF5C-D920-F060-007F010077FD';
/***删除新老产品的前置判断,CHB_DATACHECK_RULE的脚本放在待修改脚本中提供.自行选择  不再提供前置脚本***/
DELETE FROM CHB_DATACHECK_CONDITION WHERE CHECKRULEID IN (SELECT CHECKRULEID FROM CHB_DATACHECK_RULE R WHERE R.SRCPROPERTY = 'IS_FHXGCP');



