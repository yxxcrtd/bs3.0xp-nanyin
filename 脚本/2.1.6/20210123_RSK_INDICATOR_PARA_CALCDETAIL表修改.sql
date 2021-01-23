--原长度32在买断式回购到期交易中不适合，买断式回购交易到期最大长度可能为59
alter table RSK_INDICATOR_PARA_CALCDETAIL modify ASSET_CODE VARCHAR2(60);