/** add by jinmaodong on 20200402 统计分析明细表当前持仓字段精度由2位变更位4位 start **/

--统计分析明细表当前持仓字段精度由2位变更位4位
alter table BOK_STAT_DET_POSIITON rename column POS_AMT to POS_AMT_TMP;
alter table BOK_STAT_DET_POSIITON add POS_AMT NUMBER(30,4);
update BOK_STAT_DET_POSIITON set POS_AMT=POS_AMT_TMP;
alter table BOK_STAT_DET_POSIITON drop column POS_AMT_TMP;
Comment on column  BOK_STAT_DET_POSIITON.POS_AMT is '当前持仓'; 

/** add by jinmaodong on 20200402 统计分析明细表当前持仓字段精度由2位变更位4位 end **/