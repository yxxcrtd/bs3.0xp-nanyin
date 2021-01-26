/* add by dubaojun 2020-11-05 字段长度扩充 */
alter table CHB_ASSET_INFO modify IN_GRADE  varchar2(30);
alter table CHB_ASSET_INFO_SUB modify IN_GRADE  varchar2(30);
alter table CHB_ASSET_INFO_SUB_HIS modify IN_GRADE  varchar2(30);