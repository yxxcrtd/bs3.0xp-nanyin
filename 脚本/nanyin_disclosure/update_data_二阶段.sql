-- add by CHENXC 2022-09-21 修改分红计算清单报表的导出文件名称 start
update REP_INFO set FILE_NAME='{pprodname}({prodid}){enddate}分红计算清单' where TEMPLATE_ID='FHJSQD';
-- add by CHENXC 2022-09-21 修改分红计算清单报表的导出文件名称 end


---放在最后，请勿删除
commit;