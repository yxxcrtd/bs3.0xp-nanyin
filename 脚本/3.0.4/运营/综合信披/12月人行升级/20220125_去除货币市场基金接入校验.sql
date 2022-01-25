--去除货币市场基金接入校验
delete from chb_datacheck_rule a where a.datatype='CHB_PRD_BASEINFO' and a.srcproperty='IS_MARKET_FUND';