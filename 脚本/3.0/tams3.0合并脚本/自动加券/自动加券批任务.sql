delete from SYS_BATCH_TASK where BEAN_NAME in ('autoCouponBondTask','autoCouponFundTask');
insert into SYS_BATCH_TASK values ('997','INV','债券自动加券','','autoCouponBondTask','1','system', '',sysdate, 'system',sysdate);
insert into SYS_BATCH_TASK values ('998','INV','基金自动加券','','autoCouponFundTask','1','system', '',sysdate, 'system',sysdate);
