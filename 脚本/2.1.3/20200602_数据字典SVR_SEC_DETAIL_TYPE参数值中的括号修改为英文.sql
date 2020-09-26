update sys_datadict_list set item_value='短期融资券(CP)' where dict_code='SVR_SEC_DETAIL_TYPE' and item_value='短期融资券（CP）';
update sys_datadict_list set item_value='超短期融资券(SCP)' where dict_code='SVR_SEC_DETAIL_TYPE' and item_value='超短期融资券（SCP）';
update sys_datadict_list set item_value='中期票据(MTN)' where dict_code='SVR_SEC_DETAIL_TYPE' and item_value='中期票据（MTN）';
update sys_datadict_list set item_value='项目收益票据(PRN)' where dict_code='SVR_SEC_DETAIL_TYPE' and item_value='项目收益票据（PRN）';
update sys_datadict_list set item_value='中小企业集合票据(SMECN)' where dict_code='SVR_SEC_DETAIL_TYPE' and item_value='中小企业集合票据（SMECN）';
update sys_datadict_list set item_value='非金融企业资产支持票据(ABN)' where dict_code='SVR_SEC_DETAIL_TYPE' and item_value='非金融企业资产支持票据（ABN）';
commit;