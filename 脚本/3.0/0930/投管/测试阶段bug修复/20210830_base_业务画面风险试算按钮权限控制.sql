--风险试算按钮不需要控制权限
update SYS_PAGE_ELEMENT SET IS_CONTROL = '02' where ELEMENT_ID like '%_btn_risk';
update SYS_PAGE_ELEMENT SET IS_CONTROL = '02' where ELEMENT_ID like '%_btn_riskcal';