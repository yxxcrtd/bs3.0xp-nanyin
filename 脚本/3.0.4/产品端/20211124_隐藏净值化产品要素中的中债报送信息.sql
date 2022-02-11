--隐藏净值化产品要素->中债报送信息 add by lilan 20211124
DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinNetValueProductBuild002' and ELEMENT_ID = 'bussRepFinProductInfoCommon';

--INSERT INTO SYS_ELEMENT_CONTROL(PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
--VALUES('FinNetValueProductBuild002','bussRepFinProductInfoCommon','bussRepFinProductInfoDto','中债报送信息','02','02','02',null);