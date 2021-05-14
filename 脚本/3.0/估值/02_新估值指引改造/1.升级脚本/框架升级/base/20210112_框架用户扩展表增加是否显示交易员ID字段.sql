/** modify by yanya on 20210112 框架用户扩展表增加是否显示交易员ID字段**/
alter table SYS_USER_ADD ADD IBU_TRADER VARCHAR(32);

COMMENT ON COLUMN SYS_USER_ADD.IBU_TRADER
IS
    '银行间交易员ID';

-- 系统参数相关
DELETE FROM SYS_PARA where PARA_CODE = 'IS_SHOW_IBU_TRADER';
insert into SYS_PARA values ('SYS','IS_SHOW_IBU_TRADER','02','01','','用户新建是否显示银行间交易员ID','system','',to_date('2020/12/25', 'yyyy/mm/dd'), 'system',to_date('2020/12/25', 'yyyy/mm/dd'));
