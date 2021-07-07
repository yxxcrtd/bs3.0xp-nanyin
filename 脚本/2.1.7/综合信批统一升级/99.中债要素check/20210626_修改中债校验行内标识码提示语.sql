--修改中债校验行内标识码提示语
update CHB_DATACHECK_RULE set remark = '行内标识码只能含有白名单内的阿拉伯数字，英文字母，半角符号。' where CHECKRULEID = 'de294A49-6813-5A9E-F060-007F01006B12';  