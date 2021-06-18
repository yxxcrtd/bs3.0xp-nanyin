--修改债券标的行业二级分类提示
update CHB_DATACHECK_RULE_INV set remark = '行业二级分类不可为空' where  CHECKRULEID = 'B4D4ACB1-4DA7-14B5-E053-045AA8C0F0EF';
