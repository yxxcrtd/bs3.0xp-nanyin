alter   table    PTL_PORTFOLIO_VALUATION    add    DEVIATION_DEGREE   number(30,2);
comment on column PTL_PORTFOLIO_VALUATION.DEVIATION_DEGREE is '偏离度%';