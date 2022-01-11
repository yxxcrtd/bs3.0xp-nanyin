
alter table TRD_DEAL_TOTAL_ADD add ou_party_id  varchar2(32);
comment on column TRD_DEAL_TOTAL_ADD.ou_party_id is '本方';

alter table TRD_DEAL_TOTAL_ADD add ou_trader_id  varchar2(32);
comment on column TRD_DEAL_TOTAL_ADD.ou_trader_id is '本方交易员';

