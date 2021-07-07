 --隐藏逾期转让页面的风险试算
 update PAGE_ELEMENT_CONFIG set IS_SHOW = 'N' where ELEMENT_ID IN ('TrdSecOverdueDuration003_btn_riskcal','TrdSecOverdueDuration003_BaseRiskMathComm');