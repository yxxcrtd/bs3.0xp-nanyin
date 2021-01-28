
----

DELETE  SYS_ACTIVEREMINDER_SET WHERE REM_TYPE IN ( 'R1S3' ,'R1S5');

insert into SYS_ACTIVEREMINDER_SET (REM_TYPE, DAYS, URL, LABLE, DEALF, CLOSF, REMARK, MENU_ID)
values ('R1S3', 0, '/product/payint/FinProductPayInt001/init', 'FinProductPayInt001Label', 'Y', 'Y', null, 'PRODUCT_SIDE_001_003_006');

insert into SYS_ACTIVEREMINDER_SET (REM_TYPE, DAYS, URL, LABLE, DEALF, CLOSF, REMARK, MENU_ID)
values ('R1S5', 0, '/product/productduration/expire/FinProductExpire001/init', 'FinProductExpire001', 'Y', 'Y', null, 'PRODUCT_SIDE_001_003_008');
