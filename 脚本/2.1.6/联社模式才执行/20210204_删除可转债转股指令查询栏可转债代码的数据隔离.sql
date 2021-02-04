--add by 马数凯 20210204 删除可转债转股指令查询栏可转债代码的数据隔离 start
delete from data_auth_mapper_config where SQL_ID = 'com.joyintech.service.entrust.etrbondtostock.dao.EtrBondToStockMapper.getFinprodId';
--add by 马数凯 20210204 删除可转债转股指令查询栏可转债代码的数据隔离 end