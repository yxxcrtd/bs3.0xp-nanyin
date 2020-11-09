-- 批处理估值_启动按钮、机构登记银行代码管理按钮设置为COM  共通
update sys_page_element set ADMIN_FLAG = 'COM' where element_id in ('ValBatchStart001_btn_batchGroupSelect'
    ,'ValBatchStart001_btn_prodGroupSelect','ValBatchStart001_btn_reset','SysBatchStart001_btn_save','SysBatchStart001_btn_save')
    or page_id = 'SysInstRegBankCode001';