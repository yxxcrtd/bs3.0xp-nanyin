alter table BOK_POSITION_PRD add tdy_profit_carry NUMBER(30,2);
comment on column BOK_POSITION_PRD.tdy_profit_carry
  is '当日利润结转';
