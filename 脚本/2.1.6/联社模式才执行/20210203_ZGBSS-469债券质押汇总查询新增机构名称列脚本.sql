--add by 马数凯 20210203 债券质押汇总查询页面配置机构名称列 start
delete from data_auth_mapper_config where SQL_ID = 'com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dao.PtlRepoPledgeSearchMapper.pledgeSummaryFindPage';
Insert into data_auth_mapper_config (SQL_ID,FILTER_TYPE,IS_NEST,JOIN_COLUMN_NAME,FILTER_SQL,SYSTEM_CODE,PAGE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADD_COLUMN,ADD_COLUMN_NAME,DATA_TYPE) values ('com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dao.PtlRepoPledgeSearchMapper.pledgeSummaryFindPage','2','02','portfolioId',null,'PTL','PtlRepoPledgeSearch004','JQQ',null,sysdate,'JQQ',sysdate,null,null,'PTL01');
--add by 马数凯 20210203 债券质押汇总查询页面配置机构名称列 end

