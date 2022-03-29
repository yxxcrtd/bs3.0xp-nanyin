--净值化产品
delete from SYS_ELEMENT_CONTROL where PAGE_ID='FinNetValueProductBuild002' and ELEMENT_ID='bussRepFinProductInfoCommon' and ELEMENT_FIELD='bussRepFinProductInfoDto';
INSERT INTO SYS_ELEMENT_CONTROL (PAGE_ID, ELEMENT_ID, ELEMENT_FIELD, ELEMENT_LABLE, IS_SHOW, IS_REQUIRED, IS_HOLD, REQUIRED_CONTROL) VALUES ('FinNetValueProductBuild002', 'bussRepFinProductInfoCommon', 'bussRepFinProductInfoDto', '中债报送信息', '02', '02', '02', '02');

--收益型产品
delete from SYS_ELEMENT_CONTROL where PAGE_ID='FinProductBuild002' and ELEMENT_ID='bussRepFinProductInfoCommon' and ELEMENT_FIELD='bussRepFinProductInfoDto';
INSERT INTO SYS_ELEMENT_CONTROL (PAGE_ID, ELEMENT_ID, ELEMENT_FIELD, ELEMENT_LABLE, IS_SHOW, IS_REQUIRED, IS_HOLD, REQUIRED_CONTROL) VALUES ('FinProductBuild002', 'bussRepFinProductInfoCommon', 'bussRepFinProductInfoDto', '中债报送信息', '02', '02', '02', '02');
