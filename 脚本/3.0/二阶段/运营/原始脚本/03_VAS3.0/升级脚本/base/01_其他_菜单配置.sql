/** modify by yanya on 20210302 新增市场机构管理画面**/

delete from SYS_PAGE where page_id in ('MstMarketInstitutions001','MstMarketInstitutions002');
delete from SYS_PAGE_ELEMENT where page_id in ('MstMarketInstitutions001','MstMarketInstitutions002');
delete from SYS_WFL_BUSINESS_MODULE where page_id in ('MstMarketInstitutions001','MstMarketInstitutions002');


insert into SYS_PAGE values ('MstMarketInstitutions001','VAL','/valuation/basicdata/mstmarketinstitutions/MstMarketInstitutions001','valuation/basicdata/mstmarketinstitutions/MstMarketInstitutions001','VALINFOSERVICE_001_018_001','','','02','01','市场机构管理','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('MstMarketInstitutions002','VAL','/valuation/basicdata/mstmarketinstitutions/MstMarketInstitutions002','valuation/basicdata/mstmarketinstitutions/MstMarketInstitutions002','VALINFOSERVICE_001_018_001','','','02','01','市场机构管理-新建','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'));

insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('MstMarketInstitutions001','MstMarketInstitutions001_TBLROW_edit','01','01','','编辑','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('MstMarketInstitutions001','MstMarketInstitutions001_btn_add','01','01','','新建','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('MstMarketInstitutions001','MstMarketInstitutions001_btn_clear','01','02','','重置','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('MstMarketInstitutions001','MstMarketInstitutions001_btn_delete','01','01','','删除','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('MstMarketInstitutions001','MstMarketInstitutions001_btn_export','01','01','','导出','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('MstMarketInstitutions001','MstMarketInstitutions001_btn_search','01','01','','查询','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('MstMarketInstitutions002','MstMarketInstitutions002_btn_save','01','02','','保存','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('MstMarketInstitutions002','MstMarketInstitutions002_btn_submit','01','02','','提交','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'),'COM');


insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('MstMarketInstitutions001','MstMarketInstitutions001_btn_delete','oneApproval','system','',to_date('2021/02/22', 'yyyy/mm/dd'), 'system',to_date('2021/02/22', 'yyyy/mm/dd'),'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('MstMarketInstitutions002','MstMarketInstitutions002_btn_save','oneApproval','system','',to_date('2021/02/22', 'yyyy/mm/dd'), 'system',to_date('2021/02/22', 'yyyy/mm/dd'),'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('MstMarketInstitutions002','MstMarketInstitutions002_btn_submit','oneApproval','system','',to_date('2021/02/22', 'yyyy/mm/dd'), 'system',to_date('2021/02/22', 'yyyy/mm/dd'),'ALL','02');


/** ---  画面  市场机构 and 日历类型 add by 李炳言 on 20210306 start --- **/
delete from sys_page sp where sp.page_id in('MstCalendarType001','MstCalendarType002','MstCalendarType003','MstCalendarType004','MstMarketInstitutions001','MstMarketInstitutions002');

insert into SYS_PAGE values ('MstMarketInstitutions001','VAL','/valuation/basicdata/mstmarketinstitutions/MstMarketInstitutions001','valuation/basicdata/mstmarketinstitutions/MstMarketInstitutions001','VALINFOSERVICE_001_019_001','','','02','01','市场机构管理','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('MstMarketInstitutions002','VAL','/valuation/basicdata/mstmarketinstitutions/MstMarketInstitutions002','valuation/basicdata/mstmarketinstitutions/MstMarketInstitutions002','VALINFOSERVICE_001_019_001','','','02','01','市场机构管理-新建','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('MstCalendarType001','VAL','/valuation/basicdata/mstcalendartype/MstCalendarType001','valuation/basicdata/mstcalendartype/MstCalendarType001','VALINFOSERVICE_001_019_002','','','02','01','日历类型信息管理','system','',to_date('2021/03/05', 'yyyy/mm/dd'), 'system',to_date('2021/03/05', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('MstCalendarType002','VAL','/valuation/basicdata/mstcalendartype/MstCalendarType002','valuation/basicdata/mstcalendartype/MstCalendarType002','VALINFOSERVICE_001_019_002','','','02','01','日历类型信息管理新建','system','',to_date('2021/03/05', 'yyyy/mm/dd'), 'system',to_date('2021/03/05', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('MstCalendarType003','VAL','/valuation/basicdata/mstcalendartype/MstCalendarType003','valuation/basicdata/mstcalendartype/MstCalendarType003','VALINFOSERVICE_001_019_002','','','02','01','节假日查询','system','',to_date('2021/03/05', 'yyyy/mm/dd'), 'system',to_date('2021/03/05', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('MstCalendarType004','VAL','/valuation/basicdata/mstcalendartype/MstCalendarType004','valuation/basicdata/mstcalendartype/MstCalendarType004','VALINFOSERVICE_001_019_002','','','02','01','日历类型信息管理','system','',to_date('2021/03/05', 'yyyy/mm/dd'), 'system',to_date('2021/03/05', 'yyyy/mm/dd'));
/** ---  画面  市场机构 and 日历类型 add by 李炳言 on 20210306 start --- **/



/** 市场机构管理页面联动数据字典添加**/
delete from SYS_DATADICT_LIST_LINK where dict_code = 'INSTITUTION_INDUSTRY_SECOND_LEVEL';
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','A01','INSTITUTION_INDUSTRY_FIRST_LEVEL','01                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','A02','INSTITUTION_INDUSTRY_FIRST_LEVEL','01                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','A03','INSTITUTION_INDUSTRY_FIRST_LEVEL','01                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','A04','INSTITUTION_INDUSTRY_FIRST_LEVEL','01                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','A05','INSTITUTION_INDUSTRY_FIRST_LEVEL','01                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','B06','INSTITUTION_INDUSTRY_FIRST_LEVEL','02                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','B07','INSTITUTION_INDUSTRY_FIRST_LEVEL','02                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','B08','INSTITUTION_INDUSTRY_FIRST_LEVEL','02                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','B09','INSTITUTION_INDUSTRY_FIRST_LEVEL','02                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','B10','INSTITUTION_INDUSTRY_FIRST_LEVEL','02                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','B11','INSTITUTION_INDUSTRY_FIRST_LEVEL','02                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','B12','INSTITUTION_INDUSTRY_FIRST_LEVEL','02                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C13','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C14','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C15','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C16','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C17','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C18','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C19','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C20','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C21','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C22','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C23','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C24','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C25','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C26','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C27','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C28','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C29','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C30','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C31','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C32','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C33','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C34','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C35','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C36','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C37','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C38','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C39','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C40','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C41','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C42','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','C43','INSTITUTION_INDUSTRY_FIRST_LEVEL','03                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','D44','INSTITUTION_INDUSTRY_FIRST_LEVEL','04                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','D45','INSTITUTION_INDUSTRY_FIRST_LEVEL','04                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','D46','INSTITUTION_INDUSTRY_FIRST_LEVEL','04                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','E47','INSTITUTION_INDUSTRY_FIRST_LEVEL','05                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','E48','INSTITUTION_INDUSTRY_FIRST_LEVEL','05                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','E49','INSTITUTION_INDUSTRY_FIRST_LEVEL','05                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','E50','INSTITUTION_INDUSTRY_FIRST_LEVEL','05                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','F51','INSTITUTION_INDUSTRY_FIRST_LEVEL','06                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','F52','INSTITUTION_INDUSTRY_FIRST_LEVEL','06                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','G53','INSTITUTION_INDUSTRY_FIRST_LEVEL','07                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','G54','INSTITUTION_INDUSTRY_FIRST_LEVEL','07                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','G55','INSTITUTION_INDUSTRY_FIRST_LEVEL','07                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','G56','INSTITUTION_INDUSTRY_FIRST_LEVEL','07                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','G57','INSTITUTION_INDUSTRY_FIRST_LEVEL','07                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','G58','INSTITUTION_INDUSTRY_FIRST_LEVEL','07                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','G59','INSTITUTION_INDUSTRY_FIRST_LEVEL','07                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','G60','INSTITUTION_INDUSTRY_FIRST_LEVEL','07                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','H61','INSTITUTION_INDUSTRY_FIRST_LEVEL','08                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','H62','INSTITUTION_INDUSTRY_FIRST_LEVEL','08                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','I63','INSTITUTION_INDUSTRY_FIRST_LEVEL','09                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','I64','INSTITUTION_INDUSTRY_FIRST_LEVEL','09                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','I65','INSTITUTION_INDUSTRY_FIRST_LEVEL','09                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','J66','INSTITUTION_INDUSTRY_FIRST_LEVEL','10                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','J67','INSTITUTION_INDUSTRY_FIRST_LEVEL','10                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','J68','INSTITUTION_INDUSTRY_FIRST_LEVEL','10                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','J69','INSTITUTION_INDUSTRY_FIRST_LEVEL','10                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','K70','INSTITUTION_INDUSTRY_FIRST_LEVEL','11                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','L71','INSTITUTION_INDUSTRY_FIRST_LEVEL','12                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','L72','INSTITUTION_INDUSTRY_FIRST_LEVEL','12                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','M73','INSTITUTION_INDUSTRY_FIRST_LEVEL','13                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','M74','INSTITUTION_INDUSTRY_FIRST_LEVEL','13                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','M75','INSTITUTION_INDUSTRY_FIRST_LEVEL','13                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','N76','INSTITUTION_INDUSTRY_FIRST_LEVEL','14                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','N77','INSTITUTION_INDUSTRY_FIRST_LEVEL','14                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','N78','INSTITUTION_INDUSTRY_FIRST_LEVEL','14                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','O79','INSTITUTION_INDUSTRY_FIRST_LEVEL','15                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','O80','INSTITUTION_INDUSTRY_FIRST_LEVEL','15                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','O81','INSTITUTION_INDUSTRY_FIRST_LEVEL','15                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','P82','INSTITUTION_INDUSTRY_FIRST_LEVEL','16                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','Q83','INSTITUTION_INDUSTRY_FIRST_LEVEL','17                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','Q84','INSTITUTION_INDUSTRY_FIRST_LEVEL','17                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','R85','INSTITUTION_INDUSTRY_FIRST_LEVEL','18                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','R86','INSTITUTION_INDUSTRY_FIRST_LEVEL','18                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','R87','INSTITUTION_INDUSTRY_FIRST_LEVEL','18                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','R88','INSTITUTION_INDUSTRY_FIRST_LEVEL','18                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','R89','INSTITUTION_INDUSTRY_FIRST_LEVEL','18                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','S90','INSTITUTION_INDUSTRY_FIRST_LEVEL','19                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','S91','INSTITUTION_INDUSTRY_FIRST_LEVEL','19                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','S92','INSTITUTION_INDUSTRY_FIRST_LEVEL','19                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','S93','INSTITUTION_INDUSTRY_FIRST_LEVEL','19                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','S94','INSTITUTION_INDUSTRY_FIRST_LEVEL','19                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','S95','INSTITUTION_INDUSTRY_FIRST_LEVEL','19                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('INSTITUTION_INDUSTRY_SECOND_LEVEL','T96','INSTITUTION_INDUSTRY_FIRST_LEVEL','20                                                ','system','',to_date('2021/01/20', 'yyyy/mm/dd'), 'system',to_date('2021/01/20', 'yyyy/mm/dd'));


/** ---  业务流程模板 日历类型 add by 李炳言 on 20210306 start --- **/
DELETE from SYS_WFL_BUSINESS_MODULE SWBM WHERE SWBM.PAGE_ID IN ('MstCalendarType002');
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG)
values
  ('MstCalendarType002',
   'MstCalendarType001_btn_delete',
   'oneApproval',
   'system',
   '',
   to_date('2021/03/06', 'yyyy/mm/dd'),
   'system',
   to_date('2021/03/06', 'yyyy/mm/dd'),
   'ALL',
   '02');
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('MstCalendarType002','MstCalendarType002_btn_save','oneApproval','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'),'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('MstCalendarType002','MstCalendarType002_btn_submit','oneApproval','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'),'ALL','02');
/** ---  业务流程模板 日历类型 add by 李炳言 on 20210306 end --- **/


-----------------------------估值表（数据溯源信息查看）add by zwq start----------------------------------------------------
delete from sys_page where page_id = 'ValBalanceStatementQuery002';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, 
PAGE_FILE, MENU_ID, HEIGHT, 
WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, 
REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('ValBalanceStatementQuery002', 'VAL', '/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002', 
'valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002', 'VALSTATEACCOUNT_001_001_001', null, 
null, '02', '02', 
'估值表（数据溯源信息查看）', 'system', null, 
sysdate, 'system', sysdate);
-----------------------------估值表（数据溯源信息查看）add by zwq end----------------------------------------------------








