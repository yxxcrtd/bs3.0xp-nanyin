--流程模版名称修改
update SYS_PAGE set REMARK = '本币业务交易质押式回购报价' where PAGE_ID = 'CfetsQuoteRepo003';
update SYS_PAGE set REMARK = '本币业务交易债券强制完成' where PAGE_ID = 'EntrustForcedFinish';
update SYS_PAGE set REMARK = '本币业务交易质押式回购强制完成' where PAGE_ID = 'RepoEntrustForcedFinish';