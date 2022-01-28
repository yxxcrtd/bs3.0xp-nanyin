/**新增序列 用于质押式回购当日登记-本币交易导出自增序号 add by chenkang 20220119 */
DROP SEQUENCE DAYREGEXPORT_REPO_SEQUENCE;
create sequence DAYREGEXPORT_REPO_SEQUENCE minvalue 1 maxvalue 9999 order cycle nocache;
