--BS-1676 流动性风险-情景设置：产品指标描述与需求不一致问题脚本
update stt_indicator t set t.remark ='分析基准日的产品规模' where t.ind_id='1001';
update stt_indicator t set t.remark ='现金缺口=未来现金流出-未来现金流入-现金余额，负数表示为资金盈余，整数表示为有资金缺口 ' where t.ind_id='1003';
update stt_indicator t set t.remark ='二级风险缓释后缺口=资金缺口-一级流动性资产市值（默认为1）-二级流动性资产总市值*二级流动性资产折扣率（风险因子） ' where t.ind_id='1004';
update stt_indicator t set t.remark ='一级风险缓释后缺口=资金缺口-一级流动性资产市值*1' where t.ind_id='1009';