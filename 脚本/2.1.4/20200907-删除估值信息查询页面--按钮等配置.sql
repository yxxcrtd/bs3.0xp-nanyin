--Add By caiqun 20200907 估值信息查询 start  江苏个性化功能


--添加菜单以及页面和页面按钮
delete from SYS_MENU where MENU_ID='BASICDATA_MANAGEMENT_001_002_025';
delete from SYS_PAGE where PAGE_ID='MstValuateInfo001';
delete from SYS_PAGE_ELEMENT where PAGE_ID='MstValuateInfo001' and ELEMENT_ID='MstValuateInfo001_btn_export';
delete from SYS_PAGE_ELEMENT where PAGE_ID='MstValuateInfo001' and ELEMENT_ID='MstValuateInfo001_btn_search';

--二级法人改造 添加拦截器 不同成员行社的数据屏蔽
delete from data_auth_mapper_config where data_auth_mapper_config.SQL_ID='com.joyintech.service.basicdata.mstvaluateinfo.dao.MstValuateInfoMapper.selectForPage';

--Add By caiqun 20200907 添加菜单以及页面-估值信息查询 end