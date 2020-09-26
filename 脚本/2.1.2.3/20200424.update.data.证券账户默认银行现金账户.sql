--解决由于脚本初始化而造成的证券账户默认现金账户为空的bug

update JXB_SECACCT set DEFAULTCASHACCT=0 where DEFAULTCASHACCT is null;

commit;