--证券账户主键修改成字符型，可以与joyin证券账户表共表字段

alter table JXB_BK_CASH_ACCT ADD (FXID VARCHAR2(32));
update JXB_BK_CASH_ACCT set FXID=SECACCTCODE;
commit;

alter table JXB_BK_CASH_ACCT drop (SECACCTCODE);
alter table JXB_BK_CASH_ACCT rename  column "FXID" to "SECACCTCODE";
commit;

-----------------------

alter table JXS_PORTFOLIO_HDR ADD (FXID VARCHAR2(32));
update JXS_PORTFOLIO_HDR set FXID=DEFAULTSECACCT;
commit;

alter table JXS_PORTFOLIO_HDR drop (DEFAULTSECACCT);
alter table JXS_PORTFOLIO_HDR rename  column "FXID" to "DEFAULTSECACCT";
commit;

-----------------------------------------------

alter table JXT_TRAN ADD (FXID VARCHAR2(32));
update JXT_TRAN set FXID=SECACCT;
commit;

alter table JXT_TRAN drop (SECACCT);
alter table JXT_TRAN rename  column "FXID" to "SECACCT";
commit;

-----------------------------------------------

alter table JXT_TRAN_BASIC ADD (FXID VARCHAR2(32));
update JXT_TRAN_BASIC set FXID=SECACCTCODE;
commit;

alter table JXT_TRAN_BASIC drop (SECACCTCODE);
alter table JXT_TRAN_BASIC rename  column "FXID" to "SECACCTCODE";
commit;

alter table JXT_TRAN_BASIC ADD (FXID VARCHAR2(32));
update JXT_TRAN_BASIC set FXID=SECACCTCODE2;
commit;

alter table JXT_TRAN_BASIC drop (SECACCTCODE2);
alter table JXT_TRAN_BASIC rename  column "FXID" to "SECACCTCODE2";
commit;

------------------------------------------------------------


alter table JXT_TRAN_CLO ADD (FXID VARCHAR2(32));
update JXT_TRAN_CLO set FXID=SECACCT;
commit;

alter table JXT_TRAN_CLO drop (SECACCT);
alter table JXT_TRAN_CLO rename  column "FXID" to "SECACCT";
commit;


------------------------------------------------------------


alter table JXT_TRAN_SUM ADD (FXID VARCHAR2(32));
update JXT_TRAN_SUM set FXID=SECACCT;
commit;

alter table JXT_TRAN_SUM drop (SECACCT);
alter table JXT_TRAN_SUM rename  column "FXID" to "SECACCT";
commit;


------------------------------------------------------------

alter table JXT_TRAN_COLLATERAL ADD (FXID VARCHAR2(32));
update JXT_TRAN_COLLATERAL set FXID=LOCATION;
commit;

alter table JXT_TRAN_COLLATERAL drop (LOCATION);
alter table JXT_TRAN_COLLATERAL rename  column "FXID" to "LOCATION";
commit;


------------------------------------------------------------


alter table JXB_SECACCT ADD (FXID VARCHAR2(32));
update JXB_SECACCT set FXID=ACCTID;
commit;

alter table JXB_SECACCT drop (ACCTID);
alter table JXB_SECACCT rename  column FXID to ACCTID;
alter table JXB_SECACCT modify "ACCTID" not null;
alter table JXB_SECACCT add constraint PK_JXB_SECACCTID primary key (ACCTID) enable validate;