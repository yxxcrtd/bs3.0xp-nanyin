--资金实际投向提示修改为更详细提示脚本
update CHB_DATACHECK_RULE_INV set remark = '资金实际投向01至19选项为历史数据，不得修改或新增,请选择20至23' where remark = '资金实际投向01至19选项为历史数据，不得修改或新增';