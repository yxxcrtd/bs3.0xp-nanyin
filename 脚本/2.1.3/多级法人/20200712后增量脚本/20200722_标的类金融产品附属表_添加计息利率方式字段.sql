--标的类金融产品附属表 添加计息利率方式
ALTER TABLE FIN_PRODUCT_ADD ADD CAL_RATE_TYPE varchar2(10);

comment on column FIN_PRODUCT_ADD.CAL_RATE_TYPE
  is '计息利率方式 01买断利率计提 02净投资收益率计提';
