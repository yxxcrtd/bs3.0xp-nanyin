-- 报表模板
delete from rep_reportinfo where REPORTID like'%CP_001%';
insert into rep_reportinfo values ('1203','CP_001_001_001','FXGG','/CP','/joyintech','发行公告','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1204','CP_001_002_001','CPKFQGG','/CP','/joyintech','产品开放期公告','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"year","paramType":"07","name":"生成年度","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1205','CP_001_002_002','CPKFQBG','/CP','/joyintech','产品开放期报告','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"year","paramType":"07","name":"生成年度","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1206','CP_001_002_003','RKCPJZ_GMXGL','/CP','/joyintech','日开产品净值公告（公募现管类）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"净值日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1207','CP_001_002_004','RKCPJZ_GMJZXCP','/CP','/joyintech','日开产品净值公告（公募净值型产品）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"净值日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1208','CP_001_002_005','MZJZGG','/CP','/joyintech','每周净值公告','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"净值日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1209','CP_001_002_006','MZJJBG','/CP','/joyintech','每周净值报告','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"净值日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1210','CP_001_002_007','ZQXCPKFGG_GMCP','/CP','/joyintech','周期型产品开放公告（公募产品）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"本期运作起始日","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1211','CP_001_002_008','ZQXCPKFGG_GMCPDSH','/CP','/joyintech','周期型产品开放公告（公募产品-单赎回）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"本期运作起始日","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1212','CP_001_002_009','KFJZJFHGG_BNYS','/CP','/joyintech','开放净值及分红公告（公募且开放频率在半年以上）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"收益分配基准日","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1213','CP_001_002_010','KFJZJFHGG_BNYN','/CP','/joyintech','开放净值及分红公告（公募且开放频率在半年（不含）以内）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"收益分配基准日","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1214','CP_001_002_011','KFJJZGG_GM','/CP','/joyintech','开放及净值公告（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"本期开放结束日","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1215','CP_001_002_012','KFJJZGG_SM','/CP','/joyintech','开放及净值报告（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"本期开放结束日","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1216','CP_001_002_013','JZJFHBG_SMFB','/CP','/joyintech','净值及分红报告（私募封闭式）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"收益分配基准日","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1217','CP_001_002_014','JZJFHBG_SMKF','/CP','/joyintech','净值及分红报告（私募开放式）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"收益分配基准日","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1218','CP_001_002_015','JDBG_ZSX','/CP','/joyintech','季度报告指数型产品','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"year","paramType":"07","name":"报告年度","paramMultiFlag":"N"},{"paramNum":2,"paramName":"quarter","paramType":"05","name":"报告季度","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1219','CP_001_002_016','JDBG_FZSX','/CP','/joyintech','季度报告非指数型产品','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"year","paramType":"07","name":"报告年度","paramMultiFlag":"N"},{"paramNum":2,"paramName":"quarter","paramType":"05","name":"报告季度","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1220','CP_001_002_017','BNDBG_ZSX','/CP','/joyintech','半年度报告指数型产品','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"year","paramType":"07","name":"报告年度","paramMultiFlag":"N"},{"paramNum":2,"paramName":"halfyear","paramType":"06","name":"报告（半）年度","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1221','CP_001_002_018','BNDBG_FZSX','/CP','/joyintech','半年度报告非指数型产品','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"year","paramType":"07","name":"报告年度","paramMultiFlag":"N"},{"paramNum":2,"paramName":"halfyear","paramType":"06","name":"报告（半）年度","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1222','CP_001_002_019','NDBG_ZSX','/CP','/joyintech','年度报告指数型产品','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"year","paramType":"07","name":"报告年度","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1223','CP_001_002_020','NDBG_FZSX','/CP','/joyintech','年度报告非指数型产品','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"year","paramType":"08","name":"报告年度","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1224','CP_001_002_021','TZTZFBZCGG','/CP','/joyintech','调整投资非标准化债权类资产公告','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"公告日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1225','CP_001_003_001','DQGG_GM','/CP','/joyintech','到期公告（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1226','CP_001_003_002','DQGBG_SM','/CP','/joyintech','到期报告（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1227','CP_001_004_001','ZJCPXSJGGG','/CP','/joyintech','增加产品销售机构公告','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"公告日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1228','CP_001_004_002','CPBCLGG_GM','/CP','/joyintech','产品不成立公告（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"产品发布日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1229','CP_001_004_003','CPBCLBG_SM','/CP','/joyintech','产品不成立报告（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"产品发布日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1230','CP_001_004_004','DCPDFLMB1_GM','/CP','/joyintech','单产品多费率模板1（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"feename","paramType":"03","name":"费率名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1231','CP_001_004_005','DCPDFLMB1_SM','/CP','/joyintech','单产品多费率模板1（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"feename","paramType":"03","name":"费率名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1232','CP_001_004_006','DCPDFLMB2_GM','/CP','/joyintech','单产品多费率模板2（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"feename","paramType":"03","name":"费率名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1233','CP_001_004_007','DCPDFLMB2_SM','/CP','/joyintech','单产品多费率模板2（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"feename","paramType":"03","name":"费率名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1234','CP_001_004_008','DFLDCPMB1_GM','/CP','/joyintech','单费率多产品模板1（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"feename","paramType":"03","name":"费率名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1235','CP_001_004_009','DFLDCPMB1_SM','/CP','/joyintech','单费率多产品模板1（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"feename","paramType":"03","name":"费率名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"pprodid","paramType":"02","name":"产品名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1236','CP_001_004_010','DFLDCPMB2_GM','/CP','/joyintech','单费率多产品模板2（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"feename","paramType":"03","name":"费率名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"pprodid","paramType":"03","name":"产品名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1237','CP_001_004_011','DFLDCPMB2_SM','/CP','/joyintech','单费率多产品模板2（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"feename","paramType":"03","name":"费率名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"pprodid","paramType":"04","name":"产品名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1238','CP_001_004_012','DCPDYSTZMB_GM','/CP','/joyintech','单产品多要素调整模板（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"elementname","paramType":"04","name":"要素名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1239','CP_001_004_013','DCPDYSTZMB_SM','/CP','/joyintech','单产品多要素调整模板（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"elementname","paramType":"04","name":"要素名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1240','CP_001_004_014','DYSDCPTZMB_GM','/CP','/joyintech','单要素多产品调整模板（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"elementname","paramType":"04","name":"要素名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"pprodid","paramType":"04","name":"产品名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1241','CP_001_004_015','DYSDCPTZMB','/CP','/joyintech','单要素多产品调整模板（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"elementname","paramType":"04","name":"要素名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"},{"paramNum":2,"paramName":"pprodid","paramType":"05","name":"产品名称","paramMultiFlag":"Y"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1242','CP_001_004_016','JESHGG_GM','/CP','/joyintech','巨额赎回公告（公募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1243','CP_001_004_017','JESHGBG_SM','/CP','/joyintech','巨额赎回报告（私募）','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"enddate","paramType":"09","name":"发布日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1244','CP_001_005_001','DLXSJGMDGG','/CP','/joyintech','代理销售机构名单公告','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"enddate","paramType":"09","name":"公告日期","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1245','CP_001_006_001','QSBG','/CP','/joyintech','清算报告','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));
insert into rep_reportinfo values ('1246','CP_001_007_001','FHJSQD','/CP','/joyintech','分红计算清单','PROJECT','JasperSource','','','[{"paramNum":0,"paramName":"pprodid","paramType":"01","name":"产品名称","paramMultiFlag":"N"},{"paramNum":1,"paramName":"prodid","paramType":"02","name":"子产品代码","paramMultiFlag":"N"},{"paramNum":2,"paramName":"enddate","paramType":"09","name":"收益分配基准日","paramMultiFlag":"N"}]','','system',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'));

-- 报表信息
delete from REP_INFO;
insert into REP_INFO values ('CP_001_001_001','FXGG','/CP','发行公告','发行公告','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'01','{prodname}发行公告');
insert into REP_INFO values ('CP_001_002_001','CPKFQGG','/CP','产品开放期公告','产品开放期公告','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{cusFileName}开放计划公告');
insert into REP_INFO values ('CP_001_002_002','CPKFQBG','/CP','产品开放期报告','产品开放期报告','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{cusFileName}开放计划报告');
insert into REP_INFO values ('CP_001_002_003','RKCPJZ_GMXGL','/CP','日开产品净值公告（公募现管类）','日开产品净值公告（公募现管类）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}净值公告{enddate}');
insert into REP_INFO values ('CP_001_002_004','RKCPJZ_GMJZXCP','/CP','日开产品净值公告（公募净值型产品）','日开产品净值公告（公募净值型产品）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}净值公告{enddate}');
insert into REP_INFO values ('CP_001_002_005','MZJZGG','/CP','每周净值公告','每周净值公告','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}每周净值公告');
insert into REP_INFO values ('CP_001_002_006','MZJJBG','/CP','每周净值报告','每周净值报告','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}每周净值报告');
insert into REP_INFO values ('CP_001_002_007','ZQXCPKFGG_GMCP','/CP','周期型产品开放公告（公募产品）','周期型产品开放公告（公募产品）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}{enddate}开放公告');
insert into REP_INFO values ('CP_001_002_008','ZQXCPKFGG_GMCPDSH','/CP','周期型产品开放公告（公募产品-单赎回）','周期型产品开放公告（公募产品-单赎回）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}{enddate}开放公告');
insert into REP_INFO values ('CP_001_002_009','KFJZJFHGG_BNYS','/CP','开放净值及分红公告（公募且开放频率在半年以上）','开放净值及分红公告（公募且开放频率在半年以上）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}{enddate}开放、净值及分红公告');
insert into REP_INFO values ('CP_001_002_010','KFJZJFHGG_BNYN','/CP','开放净值及分红公告（公募且开放频率在半年（不含）以内）','开放净值及分红公告（公募且开放频率在半年（不含）以内）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}{enddate}开放、净值及分红公告');
insert into REP_INFO values ('CP_001_002_011','KFJJZGG_GM','/CP','开放及净值公告（公募）','开放及净值公告（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}{enddate}开放及净值公告');
insert into REP_INFO values ('CP_001_002_012','KFJJZGG_SM','/CP','开放及净值报告（私募）','开放及净值报告（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}{enddate}开放及净值报告');
insert into REP_INFO values ('CP_001_002_013','JZJFHBG_SMFB','/CP','净值及分红报告（私募封闭式）','净值及分红报告（私募封闭式）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}{enddate}净值及分红报告');
insert into REP_INFO values ('CP_001_002_014','JZJFHBG_SMKF','/CP','净值及分红报告（私募开放式）','净值及分红报告（私募开放式）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{prodid}{enddate}净值及分红报告');
insert into REP_INFO values ('CP_001_002_015','JDBG_ZSX','/CP','季度报告指数型产品','季度报告指数型产品','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{year}年第{quarter}季度报告');
insert into REP_INFO values ('CP_001_002_016','JDBG_FZSX','/CP','季度报告非指数型产品','季度报告非指数型产品','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{year}年第{quarter}季度报告');
insert into REP_INFO values ('CP_001_002_017','BNDBG_ZSX','/CP','半年度报告指数型产品','半年度报告指数型产品','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{year}年{halfyear}年度报告');
insert into REP_INFO values ('CP_001_002_018','BNDBG_FZSX','/CP','半年度报告非指数型产品','半年度报告非指数型产品','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{year}年{halfyear}年度报告');
insert into REP_INFO values ('CP_001_002_019','NDBG_ZSX','/CP','年度报告指数型产品','年度报告指数型产品','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{year}年年度报告');
insert into REP_INFO values ('CP_001_002_020','NDBG_FZSX','/CP','年度报告非指数型产品','年度报告非指数型产品','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','{prodname}{year}年年度报告');
insert into REP_INFO values ('CP_001_002_021','TZTZFBZCGG','/CP','调整投资非标准化债权类资产公告','调整投资非标准化债权类资产公告','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'02','关于{prodname}调整投资非标准化债权类资产公告');
insert into REP_INFO values ('CP_001_003_001','DQGG_GM','/CP','到期公告（公募）','到期公告（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'03','{prodname}到期公告');
insert into REP_INFO values ('CP_001_003_002','DQGBG_SM','/CP','到期报告（私募）','到期报告（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'03','{prodname}到期报告');
insert into REP_INFO values ('CP_001_004_001','ZJCPXSJGGG','/CP','增加产品销售机构公告','增加产品销售机构公告','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','关于增加{prodname}销售机构的公告');
insert into REP_INFO values ('CP_001_004_002','CPBCLGG_GM','/CP','产品不成立公告（公募）','产品不成立公告（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}不成立公告{enddate_filename}');
insert into REP_INFO values ('CP_001_004_003','CPBCLBG_SM','/CP','产品不成立报告（私募）','产品不成立报告（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}不成立报告{enddate_filename}');
insert into REP_INFO values ('CP_001_004_004','DCPDFLMB1_GM','/CP','单产品多费率模板1（公募）','单产品多费率模板1（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}费率调整公告{enddate_filename}01');
insert into REP_INFO values ('CP_001_004_005','DCPDFLMB1_SM','/CP','单产品多费率模板1（私募）','单产品多费率模板1（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}费率调整报告{enddate_filename}01');
insert into REP_INFO values ('CP_001_004_006','DCPDFLMB2_GM','/CP','单产品多费率模板2（公募）','单产品多费率模板2（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}费率调整公告{enddate_filename}02');
insert into REP_INFO values ('CP_001_004_007','DCPDFLMB2_SM','/CP','单产品多费率模板2（私募）','单产品多费率模板2（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}费率调整报告{enddate_filename}02');
insert into REP_INFO values ('CP_001_004_008','DFLDCPMB1_GM','/CP','单费率多产品模板1（公募）','单费率多产品模板1（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','产品费率调整公告{enddate_filename}01');
insert into REP_INFO values ('CP_001_004_009','DFLDCPMB1_SM','/CP','单费率多产品模板1（私募）','单费率多产品模板1（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','产品费率调整报告{enddate_filename}01');
insert into REP_INFO values ('CP_001_004_010','DFLDCPMB2_GM','/CP','单费率多产品模板2（公募）','单费率多产品模板2（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','产品费率调整公告{enddate_filename}02');
insert into REP_INFO values ('CP_001_004_011','DFLDCPMB2_SM','/CP','单费率多产品模板2（私募）','单费率多产品模板2（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','产品费率调整报告{enddate_filename}02');
insert into REP_INFO values ('CP_001_004_012','DCPDYSTZMB_GM','/CP','单产品多要素调整模板（公募）','单产品多要素调整模板（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}要素调整公告{enddate_filename}');
insert into REP_INFO values ('CP_001_004_013','DCPDYSTZMB_SM','/CP','单产品多要素调整模板（私募）','单产品多要素调整模板（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}要素调整报告{enddate_filename}');
insert into REP_INFO values ('CP_001_004_014','DYSDCPTZMB_GM','/CP','单要素多产品调整模板（公募）','单要素多产品调整模板（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','产品要素{fieldname}调整公告{enddate_filename}');
insert into REP_INFO values ('CP_001_004_015','DYSDCPTZMB','/CP','单要素多产品调整模板（私募）','单要素多产品调整模板（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','产品要素{fieldname}调整报告{enddate_filename}');
insert into REP_INFO values ('CP_001_004_016','JESHGG_GM','/CP','巨额赎回公告（公募）','巨额赎回公告（公募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}巨额赎回公告{enddate_filename}');
insert into REP_INFO values ('CP_001_004_017','JESHGBG_SM','/CP','巨额赎回报告（私募）','巨额赎回报告（私募）','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'04','{prodname}巨额赎回报告{enddate_filename}');
insert into REP_INFO values ('CP_001_005_001','DLXSJGMDGG','/CP','代理销售机构名单公告','代理销售机构名单公告','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'05','关于南银理财有限责任公司理财产品代理销售机构名单的公告');
insert into REP_INFO values ('CP_001_006_001','QSBG','/CP','清算报告','清算报告','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'06','{prodname}清算报告');
insert into REP_INFO values ('CP_001_007_001','FHJSQD','/CP','分红计算清单','分红计算清单','Y','','system','',to_date('2022/07/18','yyyy/mm/dd'),'system',to_date('2022/07/18', 'yyyy/mm/dd'),'07','{prodname}{prodid}{enddate}分红计算清单');

-- 报表信息配置
delete from REP_SCREEN;

insert into REP_SCREEN values ('100000001','CP_001_001_001','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000002','CP_001_002_001','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000003','CP_001_002_001','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000004','CP_001_002_001','07','year','生成年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000005','CP_001_002_002','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000006','CP_001_002_002','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000007','CP_001_002_002','07','year','生成年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000008','CP_001_002_003','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000009','CP_001_002_003','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000010','CP_001_002_003','09','enddate','净值日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000011','CP_001_002_004','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000012','CP_001_002_004','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000013','CP_001_002_004','09','enddate','净值日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000014','CP_001_002_005','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000015','CP_001_002_005','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000016','CP_001_002_005','09','enddate','净值日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000017','CP_001_002_006','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000018','CP_001_002_006','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000019','CP_001_002_006','09','enddate','净值日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000020','CP_001_002_007','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000021','CP_001_002_007','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000022','CP_001_002_007','08','enddate','本期运作起始日','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000023','CP_001_002_008','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000024','CP_001_002_008','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000025','CP_001_002_008','08','enddate','本期运作起始日','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000026','CP_001_002_009','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000027','CP_001_002_009','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000028','CP_001_002_009','08','enddate','收益分配基准日','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000029','CP_001_002_010','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000030','CP_001_002_010','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000031','CP_001_002_010','08','enddate','收益分配基准日','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000032','CP_001_002_011','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000033','CP_001_002_011','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000034','CP_001_002_011','09','enddate','本期开放结束日','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000035','CP_001_002_012','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000036','CP_001_002_012','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000037','CP_001_002_012','09','enddate','本期开放结束日','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000038','CP_001_002_013','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000039','CP_001_002_013','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000040','CP_001_002_013','08','enddate','收益分配基准日','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000041','CP_001_002_014','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000042','CP_001_002_014','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000043','CP_001_002_014','08','enddate','收益分配基准日','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000044','CP_001_002_015','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000045','CP_001_002_015','07','year','报告年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000046','CP_001_002_015','05','quarter','报告季度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000047','CP_001_002_016','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000048','CP_001_002_016','07','year','报告年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000049','CP_001_002_016','05','quarter','报告季度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000050','CP_001_002_017','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000051','CP_001_002_017','07','year','报告年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000052','CP_001_002_017','06','halfyear','报告（半）年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000053','CP_001_002_018','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000054','CP_001_002_018','07','year','报告年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000055','CP_001_002_018','06','halfyear','报告（半）年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');
insert into REP_SCREEN values ('100000056','CP_001_002_019','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000057','CP_001_002_019','07','year','报告年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000058','CP_001_002_020','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000059','CP_001_002_020','07','year','报告年度','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000060','CP_001_002_021','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000061','CP_001_002_021','09','enddate','公告日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000062','CP_001_003_001','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000063','CP_001_003_001','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000064','CP_001_003_002','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000065','CP_001_003_002','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000066','CP_001_004_001','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000067','CP_001_004_001','09','enddate','公告日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000068','CP_001_004_002','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000069','CP_001_004_002','08','enddate','产品发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000070','CP_001_004_003','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000071','CP_001_004_003','08','enddate','产品发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000072','CP_001_004_004','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000073','CP_001_004_004','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000074','CP_001_004_004','03','feename','费率名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000075','CP_001_004_005','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000076','CP_001_004_005','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000077','CP_001_004_005','03','feename','费率名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000078','CP_001_004_006','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000079','CP_001_004_006','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000080','CP_001_004_006','03','feename','费率名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000081','CP_001_004_007','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000082','CP_001_004_007','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000083','CP_001_004_007','03','feename','费率名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000084','CP_001_004_008','03','feename','费率名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000085','CP_001_004_008','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000086','CP_001_004_008','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000087','CP_001_004_009','03','feename','费率名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000088','CP_001_004_009','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000089','CP_001_004_009','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000090','CP_001_004_010','03','feename','费率名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000091','CP_001_004_010','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000092','CP_001_004_010','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000093','CP_001_004_011','03','feename','费率名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000094','CP_001_004_011','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000095','CP_001_004_011','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000096','CP_001_004_012','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000097','CP_001_004_012','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000098','CP_001_004_012','04','elementname','要素名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000099','CP_001_004_013','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000100','CP_001_004_013','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000101','CP_001_004_013','04','elementname','要素名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000102','CP_001_004_014','04','elementname','要素名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000103','CP_001_004_014','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000104','CP_001_004_014','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000105','CP_001_004_015','04','elementname','要素名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000106','CP_001_004_015','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','Y');
insert into REP_SCREEN values ('100000107','CP_001_004_015','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000108','CP_001_004_016','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000109','CP_001_004_016','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000110','CP_001_004_017','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000111','CP_001_004_017','08','enddate','发布日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000112','CP_001_005_001','08','enddate','公告日期','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000113','CP_001_006_001','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000115','CP_001_007_001','01','pprodid','产品名称','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','1','N');
insert into REP_SCREEN values ('100000116','CP_001_007_001','02','prodid','子产品代码','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','2','N');
insert into REP_SCREEN values ('100000117','CP_001_007_001','08','enddate','收益分配基准日','','Y','system',to_date('2022/07/20','yyyy/mm/dd'),'system',to_date('2022/07/20', 'yyyy/mm/dd'),'','3','N');


-- 报表集合
delete from rep_tree;
insert into rep_tree values ('CP_001','','V1.0版本','','');
insert into rep_tree values ('CP_001_001','CP_001','发行公告','','');
insert into rep_tree values ('CP_001_002','CP_001','运行公告','','');
insert into rep_tree values ('CP_001_003','CP_001','到期公告','','');
insert into rep_tree values ('CP_001_004','CP_001','临时公告','','');
insert into rep_tree values ('CP_001_005','CP_001','销售公告','','');
insert into rep_tree values ('CP_001_006','CP_001','托管公告','','');
insert into rep_tree values ('CP_001_007','CP_001','其他报告','','');
insert into rep_tree values ('CP_001_001_001','CP_001_001','发行公告','Y','');
insert into rep_tree values ('CP_001_002_001','CP_001_002','产品开放期公告','Y','');
insert into rep_tree values ('CP_001_002_002','CP_001_002','产品开放期报告','Y','');
insert into rep_tree values ('CP_001_002_003','CP_001_002','日开产品净值公告（公募现管类）','Y','');
insert into rep_tree values ('CP_001_002_004','CP_001_002','日开产品净值公告（公募净值型产品）','Y','');
insert into rep_tree values ('CP_001_002_005','CP_001_002','每周净值公告','Y','');
insert into rep_tree values ('CP_001_002_006','CP_001_002','每周净值报告','Y','');
insert into rep_tree values ('CP_001_002_007','CP_001_002','周期型产品开放公告（公募产品）','Y','');
insert into rep_tree values ('CP_001_002_008','CP_001_002','周期型产品开放公告（公募产品-单赎回）','Y','');
insert into rep_tree values ('CP_001_002_009','CP_001_002','开放净值及分红公告（公募且开放频率在半年以上）','Y','');
insert into rep_tree values ('CP_001_002_010','CP_001_002','开放净值及分红公告（公募且开放频率在半年（不含）以内）','Y','');
insert into rep_tree values ('CP_001_002_011','CP_001_002','开放及净值公告（公募）','Y','');
insert into rep_tree values ('CP_001_002_012','CP_001_002','开放及净值报告（私募）','Y','');
insert into rep_tree values ('CP_001_002_013','CP_001_002','净值及分红报告（私募封闭式）','Y','');
insert into rep_tree values ('CP_001_002_014','CP_001_002','净值及分红报告（私募开放式）','Y','');
insert into rep_tree values ('CP_001_002_015','CP_001_002','季度报告指数型产品','Y','');
insert into rep_tree values ('CP_001_002_016','CP_001_002','季度报告非指数型产品','Y','');
insert into rep_tree values ('CP_001_002_017','CP_001_002','半年度报告指数型产品','Y','');
insert into rep_tree values ('CP_001_002_018','CP_001_002','半年度报告非指数型产品','Y','');
insert into rep_tree values ('CP_001_002_019','CP_001_002','年度报告指数型产品','Y','');
insert into rep_tree values ('CP_001_002_020','CP_001_002','年度报告非指数型产品','Y','');
insert into rep_tree values ('CP_001_002_021','CP_001_002','调整投资非标准化债权类资产公告','Y','');
insert into rep_tree values ('CP_001_003_001','CP_001_003','到期公告（公募）','Y','');
insert into rep_tree values ('CP_001_003_002','CP_001_003','到期报告（私募）','Y','');
insert into rep_tree values ('CP_001_004_001','CP_001_004','增加产品销售机构公告','Y','');
insert into rep_tree values ('CP_001_004_002','CP_001_004','产品不成立公告（公募）','Y','');
insert into rep_tree values ('CP_001_004_003','CP_001_004','产品不成立报告（私募）','Y','');
insert into rep_tree values ('CP_001_004_004','CP_001_004','单产品多费率模板1（公募）','Y','');
insert into rep_tree values ('CP_001_004_005','CP_001_004','单产品多费率模板1（私募）','Y','');
insert into rep_tree values ('CP_001_004_006','CP_001_004','单产品多费率模板2（公募）','Y','');
insert into rep_tree values ('CP_001_004_007','CP_001_004','单产品多费率模板2（私募）','Y','');
insert into rep_tree values ('CP_001_004_008','CP_001_004','单费率多产品模板1（公募）','Y','');
insert into rep_tree values ('CP_001_004_009','CP_001_004','单费率多产品模板1（私募）','Y','');
insert into rep_tree values ('CP_001_004_010','CP_001_004','单费率多产品模板2（公募）','Y','');
insert into rep_tree values ('CP_001_004_011','CP_001_004','单费率多产品模板2（私募）','Y','');
insert into rep_tree values ('CP_001_004_012','CP_001_004','单产品多要素调整模板（公募）','Y','');
insert into rep_tree values ('CP_001_004_013','CP_001_004','单产品多要素调整模板（私募）','Y','');
insert into rep_tree values ('CP_001_004_014','CP_001_004','单要素多产品调整模板（公募）','Y','');
insert into rep_tree values ('CP_001_004_015','CP_001_004','单要素多产品调整模板（私募）','Y','');
insert into rep_tree values ('CP_001_004_016','CP_001_004','巨额赎回公告（公募）','Y','');
insert into rep_tree values ('CP_001_004_017','CP_001_004','巨额赎回报告（私募）','Y','');
insert into rep_tree values ('CP_001_005_001','CP_001_005','代理销售机构名单公告','Y','');
insert into rep_tree values ('CP_001_006_001','CP_001_006','清算报告','Y','');
insert into rep_tree values ('CP_001_007_001','CP_001_007','分红计算清单','Y','');


-- 报表作业范围
delete from REP_JOB_RANGE;

insert into REP_JOB_RANGE values ('D0000000000000000001','CP_001_001_001','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000002','CP_001_001_001','02','ISSUE_STATUS','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000003','CP_001_001_001','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000004','CP_001_002_001','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000005','CP_001_002_001','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000006','CP_001_002_002','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000007','CP_001_002_002','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000008','CP_001_002_003','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000009','CP_001_002_003','02','OPEN_FREQ','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000010','CP_001_002_003','02','PROD_TYPE','=','02','04','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000012','CP_001_002_004','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000013','CP_001_002_004','02','OPEN_FREQ','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000014','CP_001_002_004','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000016','CP_001_002_005','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000017','CP_001_002_005','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000019','CP_001_002_006','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000020','CP_001_002_006','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000022','CP_001_002_007','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000023','CP_001_002_007','02','IS_PERIOD','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('E0000000000000000023','CP_001_002_007','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000025','CP_001_002_008','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000026','CP_001_002_008','02','IS_PERIOD','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('E0000000000000000026','CP_001_002_008','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000028','CP_001_002_009','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000029','CP_001_002_009','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000030','CP_001_002_009','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000031','CP_001_002_009','02','IS_PERIOD','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000032','CP_001_002_009','02','OPEN_FREQ','=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000034','CP_001_002_010','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000035','CP_001_002_010','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000036','CP_001_002_010','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000037','CP_001_002_010','02','IS_PERIOD','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000038','CP_001_002_010','02','OPEN_FREQ','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000040','CP_001_002_011','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000041','CP_001_002_011','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000042','CP_001_002_011','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000043','CP_001_002_011','02','IS_PERIOD','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000044','CP_001_002_011','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000046','CP_001_002_012','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000047','CP_001_002_012','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000048','CP_001_002_012','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000049','CP_001_002_012','02','IS_PERIOD','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000050','CP_001_002_012','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000052','CP_001_002_013','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000053','CP_001_002_013','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000054','CP_001_002_013','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000056','CP_001_002_014','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000057','CP_001_002_014','02','ISSUING_WAY','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000058','CP_001_002_014','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000060','CP_001_002_015','02','IS_INDEX','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000061','CP_001_002_015','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000062','CP_001_002_016','02','IS_INDEX','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000063','CP_001_002_016','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000064','CP_001_002_017','02','IS_INDEX','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000065','CP_001_002_017','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000066','CP_001_002_018','02','IS_INDEX','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000067','CP_001_002_018','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000068','CP_001_002_019','02','IS_INDEX','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000069','CP_001_002_019','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000070','CP_001_002_020','02','IS_INDEX','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000071','CP_001_002_020','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000072','CP_001_003_001','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000074','CP_001_003_002','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000076','CP_001_004_002','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000077','CP_001_004_003','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000078','CP_001_004_004','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000079','CP_001_004_005','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000080','CP_001_004_006','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000081','CP_001_004_007','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000082','CP_001_004_008','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000083','CP_001_004_009','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000084','CP_001_004_010','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000085','CP_001_004_011','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000086','CP_001_004_012','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000087','CP_001_004_013','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000088','CP_001_004_014','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000089','CP_001_004_015','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000090','CP_001_004_016','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000091','CP_001_004_017','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000101','100000001','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000102','100000001','02','ISSUE_STATUS','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000103','100000001','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000108','100000002','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000109','100000002','02','OPEN_FREQ','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000110','100000002','02','PROD_TYPE','=','02','04','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000111','100000002','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000112','100000003','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000113','100000003','02','OPEN_FREQ','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000114','100000003','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000115','100000003','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000116','100000004','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000117','100000004','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000118','100000004','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000119','100000005','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000120','100000005','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000121','100000005','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000122','100000006','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000123','100000006','02','IS_PERIOD','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000124','100000006','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('E0000000000000000124','100000006','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000125','100000007','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000126','100000007','02','IS_PERIOD','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000127','100000007','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('E0000000000000000127','100000007','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000128','100000008','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000129','100000008','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000130','100000008','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000131','100000008','02','IS_PERIOD','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000132','100000008','02','OPEN_FREQ','=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000133','100000008','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000134','100000009','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000135','100000009','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000136','100000009','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000137','100000009','02','IS_PERIOD','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000138','100000009','02','OPEN_FREQ','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000139','100000009','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000140','100000010','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000141','100000010','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000142','100000010','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000143','100000010','02','IS_PERIOD','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000144','100000010','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000145','100000010','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000146','100000011','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000147','100000011','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000148','100000011','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000149','100000011','02','IS_PERIOD','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000150','100000011','02','OPEN_FREQ','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000151','100000011','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000152','100000012','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000153','100000012','02','ISSUING_WAY','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000154','100000012','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000155','100000012','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000156','100000013','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000157','100000013','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000158','100000013','02','PROD_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000159','100000013','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000160','100000014','02','IS_INDEX','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000161','100000014','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000162','100000015','02','IS_INDEX','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000163','100000015','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000164','100000016','02','IS_INDEX','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000165','100000016','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000166','100000017','02','IS_INDEX','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000167','100000017','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000168','100000018','02','IS_INDEX','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000169','100000018','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000170','100000019','02','IS_INDEX','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000171','100000019','02','REP_PROD_TYPE','!=','02','03','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000172','100000020','02','RAISE_TYPE','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000173','100000020','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000174','100000021','02','RAISE_TYPE','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
insert into REP_JOB_RANGE values ('D0000000000000000175','100000021','02','REP_PROD_TYPE','!=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));


-- 披露指标
delete from REP_INDEX_PRIMARY;
insert into REP_INDEX_PRIMARY values ('tzclhyzfx','报告期内产品的投资策略和运作分析','报告期内产品的投资策略和运作分析','','02','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'');
insert into REP_INDEX_PRIMARY values ('ldxfxfx','报告期内产品的流动性风险分析','报告期内产品的流动性风险分析','','02','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'');
insert into REP_INDEX_PRIMARY values ('tgrbg1','报告期内托管人遵规守信情况声明','报告期内托管人遵规守信情况声明','','02','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'');
insert into REP_INDEX_PRIMARY values ('tgrbg2','托管人对报告期内投资运作遵规守信、净值计算、利润分配等情况的说明','托管人对报告期内投资运作遵规守信、净值计算、利润分配等情况的说明','','02','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'');
insert into REP_INDEX_PRIMARY values ('tgrbg3','托管人对本年度报告中财务信息等内容的真实、准确和完整发表意见','托管人对本年度报告中财务信息等内容的真实、准确和完整发表意见','','02','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'');


-- 披露指标明细
delete from REP_INDEX_SUB;
insert into REP_INDEX_SUB(ID, PRI_ID, INDEX_NAME, INDEX_VALUE, INDEX_TYPE, INDEX_DATE, INDEX_FILE, INDEX_DETAIL, CREATE_DEPT, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME, AUDIT_STATUS) values ('100000001','tzclhyzfx','报告期内产品的投资策略和运作分析','  2021年债券市场整体呈现震荡小牛市走势。经济基本面全年呈现“前高后低”特征，基建投资见底、制造业投资疲弱及地产下行压力逐步体现。通胀方面，疫情及环保政策影响下PPI持续攀高，但并未向CPI形成明显传导，且在四季度行政控价下有所缓解。货币自二季度起维持温和宽松状态，并未受到内外部通胀环境的实质制约，对债市构成了有利支撑。
  就四季度而言，受通胀、疫情、货币及宽信用预期等多方面因素扰动，长端利率波动明显放大。期间10年期国债及国开利率先上后下，振幅近30BP。分阶段看，10月通胀压力及货币约束为期间主线，在二次降准预期落空与印证下，长端利率一度明显上行。11月地产政策微调传闻一度引发市场关注，但在经济基本面压力、央行跨月投放加码以及海外疫情突变等因素支撑下，长端利率转向缓步下行。至年末12月，预期中的二次降准落地对市场影响有限，市场关注点转向宽货币与宽信用的博弈。国开与国债低利差导致二者走势分化，在降息预期升温推动下，10年国债收益率继续下行，而10年国开波动总体有限。
  展望后市，短期内货币宽松、信用未起的环境仍将对债市形成支撑，但稳增长发力与海外货币约束在中期可能构成隐忧。21年四季度期间社融尚处于磨底阶段，而中央经济工作会议强调财政政策和货币政策要协调联动，意味着后期宽财政发力作为宽信用、稳经济的抓手，货币则需要提供良好的流动性环境，短期内形成货币与信用错位的窗口期。从中期看，债市偏贵的定价、信用企稳回升及海外流动性收紧均构成了隐忧，策略及操作上不宜用到极致。
  操作方面，产品延续了稳健的投资风格，持续提高利率债、存单等高流动性资产配置，持仓结构进一步向新规靠拢，并通过适当杠杆增厚产品收益。未来拟继续坚持流动性，兼顾收益性的目标进行操作，保证流动性安全、信用风险可控的基础上为客户提供稳定的收益回报。','01',to_date('2022/07/21','yyyy/MM/dd'),'','','','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'E');
insert into REP_INDEX_SUB(ID, PRI_ID, INDEX_NAME, INDEX_VALUE, INDEX_TYPE, INDEX_DATE, INDEX_FILE, INDEX_DETAIL, CREATE_DEPT, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME, AUDIT_STATUS) values ('100000002','ldxfxfx','报告期内产品的流动性风险分析','  21年四季度，市场流动性整体充裕，11月和12月，质押式回购规模创历史新高，流动性边际趋紧，机构被动降杠杆。为保障流动性安全，本产品提前降低产品杠杆，做好资金储备，平稳度过各流动性关键节点。展望后市，短期内流动性宽松态势不变，市场降息预期加强，但阶段性波动加大；中期需关注美联储加息和缩表节奏。本产品将主动控制久期，适当使用杠杆，关注节点资金波动情况，做好流动性预判和资产的到期分布安排，防范流动性风险。','01',to_date('2022/07/21','yyyy/MM/dd'),'','','','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'E');
insert into REP_INDEX_SUB(ID, PRI_ID, INDEX_NAME, INDEX_VALUE, INDEX_TYPE, INDEX_DATE, INDEX_FILE, INDEX_DETAIL, CREATE_DEPT, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME, AUDIT_STATUS) values ('100000003','tgrbg1','报告期内托管人遵规守信情况声明','  在托管本产品的过程中，本产品托管人南京银行股份有限公司严格遵守《商业银行理财子公司管理办法》相关法律法规的规定以及产品说明书的约定，对本产品管理人—南银理财有限责任公司在报告期内产品的投资运作进行了认真、独立的会计核算和必要的投资监督，认真履行了托管人的义务，没有从事任何损害产品份额持有人利益的行为。','01',to_date('2022/07/21','yyyy/MM/dd'),'','','','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'E');
insert into REP_INDEX_SUB(ID, PRI_ID, INDEX_NAME, INDEX_VALUE, INDEX_TYPE, INDEX_DATE, INDEX_FILE, INDEX_DETAIL, CREATE_DEPT, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME, AUDIT_STATUS) values ('100000004','tgrbg2','托管人对报告期内投资运作遵规守信、净值计算、利润分配等情况的说明','  本托管人认为，南银理财有限责任公司在本产品的投资运作、产品资产净值的计算、产品份额申购赎回价格的计算、产品费用开支及利润分配等问题上不存在损害产品份额持有人利益的行为；在报告期内，严格遵守了有关法律法规，在各重要方面的运作严格按照托管协议和理财产品说明书的规定进行。','01',to_date('2022/07/21','yyyy/MM/dd'),'','','','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'E');
insert into REP_INDEX_SUB(ID, PRI_ID, INDEX_NAME, INDEX_VALUE, INDEX_TYPE, INDEX_DATE, INDEX_FILE, INDEX_DETAIL, CREATE_DEPT, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME, AUDIT_STATUS) values ('100000005','tgrbg3','托管人对本年度报告中财务信息等内容的真实、准确和完整发表意见','  本托管人认为，南银理财有限责任公司的信息披露事务符合《商业银行理财子公司管理办法》及其他相关法律法规的规定，产品管理人所编制和披露的本产品年度报告中的财务指标、净值表现、财务会计报告等信息真实、准确、完整，未发现有损害产品持有人利益的行为。','01',to_date('2022/07/21','yyyy/MM/dd'),'','','','system',to_date('2022/07/21','yyyy/mm/dd'),'system',to_date('2022/07/21', 'yyyy/mm/dd'),'E');


-- 报表规则
delete from REP_RULE;
insert into REP_RULE values ('D0000000000000000001','发行公告_规则','发行公告','CP_001_001_001','A02','','SET_DATE','02','0','01','','02','0','01','16:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000002','日开产品净值公告（公募现管类）_规则','日开产品净值公告（公募现管类）','CP_001_002_003','A02','','01','01','1','01','','02','0','01','09:00:00','01','01','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','04');
insert into REP_RULE values ('D0000000000000000003','日开产品净值公告（公募净值型产品）_规则','日开产品净值公告（公募净值型产品）','CP_001_002_004','A02','','01','01','1','01','','02','0','01','09:00:00','01','01','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','04');
insert into REP_RULE values ('D0000000000000000004','每周净值公告_规则','每周净值公告','CP_001_002_005','A02','','01','01','0','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','04');
insert into REP_RULE values ('D0000000000000000005','每周净值报告_规则','每周净值报告','CP_001_002_006','A02','','01','01','0','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','04');
insert into REP_RULE values ('D0000000000000000006','周期型产品开放公告（公募产品）_规则','周期型产品开放公告（公募产品）','CP_001_002_007','A02','','09','01','0','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000007','周期型产品开放公告（公募产品-单赎回）_规则','周期型产品开放公告（公募产品-单赎回）','CP_001_002_008','A02','','15','01','0','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000008','开放净值及分红公告（公募且开放频率在半年以上）_规则','开放净值及分红公告（公募且开放频率在半年以上）','CP_001_002_009','A02','','14','02','1','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000009','开放净值及分红公告（公募且开放频率在半年（不含）以内）_规则','开放净值及分红公告（公募且开放频率在半年（不含）以内）','CP_001_002_010','A02','','14','02','1','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000010','开放及净值公告（公募）_规则','开放及净值公告（公募）','CP_001_002_011','A02','','09','02','1','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000011','开放及净值报告（私募）_规则','开放及净值报告（私募）','CP_001_002_012','A02','','09','02','1','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000012','净值及分红报告（私募封闭式）_规则','净值及分红报告（私募封闭式）','CP_001_002_013','A02','','14','02','1','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000013','净值及分红报告（私募开放式）_规则','净值及分红报告（私募开放式）','CP_001_002_014','A02','','14','02','1','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000014','季度报告指数型产品_规则','季度报告指数型产品','CP_001_002_015','A02','','01','02','3','01','','02','10','01','09:00:00','01','06','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000015','季度报告非指数型产品_规则','季度报告非指数型产品','CP_001_002_016','A02','','01','02','3','01','','02','10','01','09:00:00','01','06','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000016','半年度报告指数型产品_规则','半年度报告指数型产品','CP_001_002_017','A02','','01','02','3','01','','','','','','01','06','','','','docx','','/joyintech/disclosure/download/','01','01','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000017','半年度报告非指数型产品_规则','半年度报告非指数型产品','CP_001_002_018','A02','','01','02','3','01','','','','','','01','06','','','','docx','','/joyintech/disclosure/download/','01','01','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000018','年度报告指数型产品_规则','年度报告指数型产品','CP_001_002_019','A02','','01','02','3','01','','','','','','01','06','','','','docx','','/joyintech/disclosure/download/','01','01','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000019','年度报告非指数型产品_规则','年度报告非指数型产品','CP_001_002_020','A02','','01','02','3','01','','','','','','01','06','','','','docx','','/joyintech/disclosure/download/','01','01','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000020','到期公告（公募）_规则','到期公告（公募）','CP_001_003_001','A02','','14','01','1','01','','02','2','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000021','到期报告（私募）_规则','到期报告（私募）','CP_001_003_002','A02','','14','01','1','01','','02','2','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000022','清算报告_规则','清算报告','CP_001_006_001','A02','','MDATE','01','1','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000023','分红计算清单_规则','分红计算清单','CP_001_007_001','A02','','14','02','1','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000024','代理销售机构名单公告_规则','代理销售机构名单公告','CP_001_005_001','A02','','01','01','0','01','','02','0','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','02','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');


-- 报表作业
delete from REP_JOB;
insert into REP_JOB values ('100000001','发行公告','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000001','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000002','日开产品净值公告（公募现管类）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000002','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000003','日开产品净值公告（公募净值型产品）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000003','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000004','每周净值公告','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000004','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000005','每周净值报告','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000005','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000006','周期型产品开放公告（公募产品）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000006','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000007','周期型产品开放公告（公募产品-单赎回）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000007','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000008','开放净值及分红公告（公募且开放频率在半年以上）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000008','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000009','开放净值及分红公告（公募且开放频率在半年（不含）以内）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000009','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000010','开放及净值公告（公募）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000010','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000011','开放及净值报告（私募）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000011','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000012','净值及分红报告（私募封闭式）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000012','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000013','净值及分红报告（私募开放式）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000013','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000014','季度报告指数型产品','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000014','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000015','季度报告非指数型产品','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000015','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000016','半年度报告指数型产品','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000016','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000017','半年度报告非指数型产品','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000017','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000018','年度报告指数型产品','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000018','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000019','年度报告非指数型产品','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000019','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000020','到期公告（公募）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000020','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000021','到期报告（私募）','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000021','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000022','清算报告','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000022','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000023','分红计算清单','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000023','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'Y');
insert into REP_JOB values ('100000024','代理销售机构名单公告','01','0 0 5 * * ? *','每年每月每日5时0分','2022-07-23','','D0000000000000000024','system','',to_date('2022/07/23','yyyy/MM/dd'),'system',to_date('2022/07/23','yyyy/MM/dd'),'N');

-- 校验

delete from REP_CHECK;
declare v_clob clob :='select 1 from dual
where 
date$qryEndDate=(
    select busi_date from(
    select a.busi_date from chb_mkt_holiday a 
    where a.busi_date >= (SELECT trunc(date$qryEndDate,''d'') FROM dual)
    and a.busi_date <= (SELECT trunc(date$qr yEndDate,''d'') + 7 from dual)
    and a.is_holiday=''0''
    and a.market_code=''04''
    order by a.busi_date asc)
    where rownum=1
)'; begin insert into  REP_CHECK(CHECK_ID,QUERY_SCOPE,QUERY_TYPE,BASE_DATA_TYPE,QUERY_CONTEN_DESCR,QUERY_CONTEN,QUERY_PARAM,DATA_TYPE,PARAM_TYPE,PARAM_VALUE1,PARAM_VALUE2,STATUS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1001','','05','01','检查每周净值公告/报告日期是否满足条件',v_clob,'qryEndDate=endDate','02','01','0','','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd')); end;
declare v_clob clob :='select 1 from dual
where 
date$qryEndDate=(
    select busi_date from(
    select a.busi_date from chb_mkt_holiday a 
    where a.busi_date >= (SELECT trunc(date$qryEndDate,''d'' ) FROM dual)
    and a.busi_date <= (SELECT trunc(date$qryEndDate,''d'') + 7 from dual)
    and a.is_holiday=''0''
    and a.market_code=''04''
    order by a.busi_date asc)
    where rownum=1
)'; begin insert into  REP_CHECK(CHECK_ID,QUERY_SCOPE,QUERY_TYPE,BASE_DATA_TYPE,QUERY_CONTEN_DESCR,QUERY_CONTEN,QUERY_PARAM,DATA_TYPE,PARAM_TYPE,PARAM_VALUE1,PARAM_VALUE2,STATUS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1002','','05','01','检查每周净值公告/报告日期是否满足条件',v_clob,'qryEndDate=endDate','02','01','0','','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd')); end;
declare v_clob clob :='select 1 from dual
where 
date$qryEndDate=(
    select busi_date from(
    select a.busi_date from chb_mkt_holiday a 
    where a.busi_date >= (SELECT trunc(date$qryEndDate,''q'') FROM dual)
    and a.busi_date <= (SELECT trunc(date$qryEndDate,''q'') + 30 from dual)
    and a.is_holiday=''0''
    and a.market_code=''01''
    order by a.busi_date asc)
    where rownum=1
)'; begin insert into  REP_CHECK(CHECK_ID,QUERY_SCOPE,QUERY_TYPE,BASE_DATA_TYPE,QUERY_CONTEN_DESCR,QUERY_CONTEN,QUERY_PARAM,DATA_TYPE,PARAM_TYPE,PARAM_VALUE1,PARAM_VALUE2,STATUS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1003','','05','01','检查季度报告（指数产品）日期是否满足条件',v_clob,'qryEndDate=endDate','02','01','0','','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd')); end;
declare v_clob clob :='select 1 from dual
where 
date$qryEndDate=(
    select busi_date from(
    select a.busi_date from chb_mkt_holiday a 
    where a.busi_date >= (SELECT trunc(date$qryEndDate,''q'') FROM dual)
    and a.busi_date <= (SELECT trunc(date$qryEndDate,''q'') + 30 from dual)
    and a.is_holiday=''0''
    and a.market_code=''01''
    order by a.busi_date asc)
    where rownum=1
)'; begin insert into  REP_CHECK(CHECK_ID,QUERY_SCOPE,QUERY_TYPE,BASE_DATA_TYPE,QUERY_CONTEN_DESCR,QUERY_CONTEN,QUERY_PARAM,DATA_TYPE,PARAM_TYPE,PARAM_VALUE1,PARAM_VALUE2,STATUS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1004','','05','01','检查季度报告（非指数型产品）日期是否满足条件',v_clob,'qryEndDate=endDate','02','01','0','','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd')); end;
declare v_clob clob :='select 1 from dual
where 
date$qryEndDate=(
    select busi_date from(
    select a.busi_date from chb_mkt_holiday a 
    where a.busi_date >= (SELECT add_months(trunc(date$qryEndDate,''yyyy''),6) from dual)
    and a.busi_date <= (SELECT add_months(trunc(date$qryEndDate,''yyyy''),6) + 10 from dual)
    and a.is_holiday=''0''
    and a.market_code=''01''
    order by a.busi_date asc)
    where rownum=1
)'; begin insert into  REP_CHECK(CHECK_ID,QUERY_SCOPE,QUERY_TYPE,BASE_DATA_TYPE,QUERY_CONTEN_DESCR,QUERY_CONTEN,QUERY_PARAM,DATA_TYPE,PARAM_TYPE,PARAM_VALUE1,PARAM_VALUE2,STATUS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1005','','05','01','检查半年度报告（指数产品）日期是否满足条件',v_clob,'qryEndDate=endDate','02','01','0','','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd')); end;
declare v_clob clob :='select 1 from dual
where 
date$qryEndDate=(
    select busi_date from(
    select a.busi_date from chb_mkt_holiday a 
    where a.busi_date >= (SELECT add_months(trunc(date$qryEndDate,''yyyy''),6) from dual)
    and a.busi_date <= (SELECT add_months(trunc(date$qryEndDate,''yyyy''),6) + 10 from dual)
    and a.is_holiday=''0''
    and a.market_code=''01''
    order by a.busi_date asc)
    where rownum=1
)'; begin insert into  REP_CHECK(CHECK_ID,QUERY_SCOPE,QUERY_TYPE,BASE_DATA_TYPE,QUERY_CONTEN_DESCR,QUERY_CONTEN,QUERY_PARAM,DATA_TYPE,PARAM_TYPE,PARAM_VALUE1,PARAM_VALUE2,STATUS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1006','','05','01','检查半年度报告（非指数型产品）日期是否满足条件',v_clob,'qryEndDate=endDate','02','01','0','','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd')); end;
declare v_clob clob :='select 1 from dual
where 
date$qryEndDate=(
    select busi_date from(
    select a.busi_date from chb_mkt_holiday a 
    where a.busi_date >= (SELECT trunc(date$qryEndDate,''yyyy'') from dual)
    and a.busi_date <= (SELECT trunc(date$qryEndDate,''yyyy'') +10 from dual)
    and a.is_holiday=''0''
    and a.market_code=''01''
    order by a.busi_date asc)
    where rownum=1
)'; begin insert into  REP_CHECK(CHECK_ID,QUERY_SCOPE,QUERY_TYPE,BASE_DATA_TYPE,QUERY_CONTEN_DESCR,QUERY_CONTEN,QUERY_PARAM,DATA_TYPE,PARAM_TYPE,PARAM_VALUE1,PARAM_VALUE2,STATUS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1007','','05','01','检查年度报告（指数产品）日期是否满足条件',v_clob,'qryEndDate=endDate','02','01','0','','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd')); end;
declare v_clob clob :='select 1 from dual
where 
date$qryEndDate=(
    select busi_date from(
    select a.busi_date from chb_mkt_holiday a 
    where a.busi_date >= (SELECT trunc(date$qryEndDate,''yyyy'') from dual)
    and a.busi_date <= (SELECT trunc(date$qryEndDate,''yyyy'') +10 from dual)
    and a.is_holiday=''0''
    and a.market_code=''01''
    order by a.busi_date asc)
    where rownum=1
)'; begin insert into  REP_CHECK(CHECK_ID,QUERY_SCOPE,QUERY_TYPE,BASE_DATA_TYPE,QUERY_CONTEN_DESCR,QUERY_CONTEN,QUERY_PARAM,DATA_TYPE,PARAM_TYPE,PARAM_VALUE1,PARAM_VALUE2,STATUS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1008','','05','01','检查年度报告（非指数型产品）日期是否满足条件',v_clob,'qryEndDate=endDate','02','01','0','','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd')); end;


-- 校验关系

delete from REP_CHECK_RELATION;
insert into REP_CHECK_RELATION values ('1001','CP_001_002_005','1001','04','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd'));
insert into REP_CHECK_RELATION values ('1002','CP_001_002_006','1002','04','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd'));
insert into REP_CHECK_RELATION values ('1003','CP_001_002_015','1003','04','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd'));
insert into REP_CHECK_RELATION values ('1004','CP_001_002_016','1004','04','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd'));
insert into REP_CHECK_RELATION values ('1005','CP_001_002_017','1003','04','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd'));
insert into REP_CHECK_RELATION values ('1006','CP_001_002_018','1004','04','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd'));
insert into REP_CHECK_RELATION values ('1007','CP_001_002_019','1003','04','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd'));
insert into REP_CHECK_RELATION values ('1008','CP_001_002_020','1004','04','E','system','',to_date('2022/07/26','yyyy/MM/dd'),'system',to_date('2022/07/26','yyyy/MM/dd'));


-- 披露地址表
delete from REP_FTP_PATH;
insert into REP_FTP_PATH values ('01','发行公告','sftpuser','sftpuser','/home/weblogic/xp_file/pl/02','22','192.168.70.237','system','',to_date('2019/05/24','yyyy/mm/dd'),'system',to_date('2019/05/24', 'yyyy/mm/dd'),'sftpRevealInterf','');
insert into REP_FTP_PATH values ('02','运行公告','sftpuser','sftpuser','/home/weblogic/xp_file/pl/04','22','192.168.70.237','system','',to_date('2019/05/24','yyyy/mm/dd'),'system',to_date('2019/05/24', 'yyyy/mm/dd'),'sftpRevealInterf','');
insert into REP_FTP_PATH values ('03','到期公告','sftpuser','sftpuser','/home/weblogic/xp_file/pl/07','22','192.168.70.237','system','',to_date('2019/05/24','yyyy/mm/dd'),'system',to_date('2019/05/24', 'yyyy/mm/dd'),'sftpRevealInterf','');
insert into REP_FTP_PATH values ('04','临时公告','sftpuser','sftpuser','/home/weblogic/xp_file/pl/08','22','192.168.70.237','system','',to_date('2019/05/24','yyyy/mm/dd'),'system',to_date('2019/05/24', 'yyyy/mm/dd'),'sftpRevealInterf','');
insert into REP_FTP_PATH values ('05','销售公告','sftpuser','sftpuser','/home/weblogic/xp_file/pl/06','22','192.168.70.237','system','',to_date('2019/05/24','yyyy/mm/dd'),'system',to_date('2019/05/24', 'yyyy/mm/dd'),'sftpRevealInterf','');
insert into REP_FTP_PATH values ('06','托管公告','sftpuser','sftpuser','/home/weblogic/xp_file/pl/03','22','192.168.70.237','system','',to_date('2019/05/24','yyyy/mm/dd'),'system',to_date('2019/05/24', 'yyyy/mm/dd'),'sftpRevealInterf','');
insert into REP_FTP_PATH values ('07','其他报告 ','sftpuser','sftpuser','/home/weblogic/xp_file/pl/09','22','192.168.70.237','system','',to_date('2019/05/24','yyyy/mm/dd'),'system',to_date('2019/05/24', 'yyyy/mm/dd'),'sftpRevealInterf','');

--报表生成管理》开放、净值及分红公告（公募&开放频率在半年以上） 开放、净值及分红公告（公募&开放频率在半年（不含）以内） 开放及净值公告（公募） 开放及净值报告（私募） 发行方式由封闭式改成开放式 add by zhengbs 20220807
DELETE FROM REP_JOB_RANGE WHERE JOB_ID = 'CP_001_002_009' AND RULE_TYPE1 = 'ISSUING_WAY';
insert into REP_JOB_RANGE values ('D0000000000000000029','CP_001_002_009','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
DELETE FROM REP_JOB_RANGE WHERE JOB_ID = 'CP_001_002_010' AND RULE_TYPE1 = 'ISSUING_WAY';
insert into REP_JOB_RANGE values ('D0000000000000000035','CP_001_002_010','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
DELETE FROM REP_JOB_RANGE WHERE JOB_ID = 'CP_001_002_012' AND RULE_TYPE1 = 'ISSUING_WAY';
insert into REP_JOB_RANGE values ('D0000000000000000047','CP_001_002_012','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
DELETE FROM REP_JOB_RANGE WHERE JOB_ID = 'CP_001_002_011' AND RULE_TYPE1 = 'ISSUING_WAY';
insert into REP_JOB_RANGE values ('D0000000000000000041','CP_001_002_011','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));

--净值及分红报告（私募开放式） 发行方式改成开放式
DELETE FROM REP_JOB_RANGE WHERE JOB_ID = 'CP_001_002_014' AND RULE_TYPE1 = 'ISSUING_WAY';
insert into REP_JOB_RANGE values ('D0000000000000000057','CP_001_002_014','02','ISSUING_WAY','=','02','01','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));
--净值及分红报告（私募封闭式） 发行方式改成封闭式
DELETE FROM REP_JOB_RANGE WHERE JOB_ID = 'CP_001_002_013' AND RULE_TYPE1 = 'ISSUING_WAY';
insert into REP_JOB_RANGE values ('D0000000000000000053','CP_001_002_013','02','ISSUING_WAY','=','02','02','system','',to_date('2022/7/20','yyyy/MM/dd'),'system',to_date('2022/7/20','yyyy/MM/dd'));