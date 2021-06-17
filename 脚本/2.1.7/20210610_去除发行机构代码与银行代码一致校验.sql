--去除发行机构代码与银行代码一致校验,联社模式不同的银行有不同的银行代码,无法在配置里配置,通过后台代码进行校验
update CHB_DATACHECK_RULE_INV set STATUS = 'D' where CHECKRULEID = 'de5E4A48-6813-5A9E-E050-007F01006A66';