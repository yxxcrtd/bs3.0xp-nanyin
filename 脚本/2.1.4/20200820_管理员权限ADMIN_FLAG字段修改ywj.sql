--add by yangwenjun 20200820 start

--统一数据维护  管理员权限去除按钮
update SYS_PAGE_ELEMENT set ADMIN_FLAG='' where PAGE_ID='SvrOperationalInfo001' and ELEMENT_ID='SvrOperationalInfo001_btn_resource';
update SYS_PAGE_ELEMENT set ADMIN_FLAG='' where PAGE_ID='SvrOperationalInfo001' and ELEMENT_ID='SvrOperationalInfo001_btn_save';
update SYS_PAGE_ELEMENT set ADMIN_FLAG='' where PAGE_ID='SvrOperationalInfo001' and ELEMENT_ID='SvrOperationalInfo001_btn_delete';
update SYS_PAGE_ELEMENT set ADMIN_FLAG='' where PAGE_ID='SvrOperationalInfo001' and ELEMENT_ID='SvrOperationalInfo001_btn_import';

--监管报送（中债报送、人行报送、G06）管理员权限增加导出按钮
update SYS_PAGE_ELEMENT set ADMIN_FLAG='01' where ELEMENT_ID like '%chbexport';

--手工生成人行报送文件   管理员权限去除生成按钮
update SYS_PAGE_ELEMENT set ADMIN_FLAG='' where ELEMENT_ID='ManuallyGeneratingPbcData001_btn_search' and PAGE_ID='ManuallyGeneratingPbcData001';

--add by yangwenjun 20200820 end