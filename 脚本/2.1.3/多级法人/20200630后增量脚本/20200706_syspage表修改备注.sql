--sjj add 20200706 sys_page表备注修改
update sys_page t set t.remark ='备注' where t.page_id in ('RskSupervisionIndicatorParam004','AgencyNegativeInf002');