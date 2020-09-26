

DECLARE
 SQLDBSQL CLOB := 'SELECT
        asset.REP_FINPROD_ID asset_ID,/*资产代码*/
        asset.FINPROD_MARKET_ID as MARKET_ID,/*市场代码*/
        asset.REP_FINPROD_NAME ASSET_NAME,/*资产名称*/
        F.ISSUER as ISSUER,/*发行人*/
        F.IDATE as IDATE,/*发行日*/
        F.ISSUE_PRICE as ISSUE_PRICE,/*发行价*/
        F.ISSUE_AMT as ISSUE_AMT,/*发行规模*/
        asset.REP_CCY CCY,/*币种*/
        f.VDATE AS VDATE,/*起息日*/
        F.MDATE AS MDATE,/*到期日*/
        F.ACTMDATE as ACTMDATE,/*实际到期日*/
        asset.REP_TRADE_MARKET TRADE_MARKET,/*交易场所*/
        F.REGIST_ORG AS REGIST_ORG,/*登记托管机构*/
        ''E'' STATUS,/*状态*/
        F.CREATE_USER CREATE_USER,/*创建人*/
        '''' CREATE_DEPT,/*创建部门*/
        F.UPDATE_USER UPDATE_USER,/*更新人*/
        null as ASSET_TYPE,/**/
        --F.FINANCIER as FIX_ANCIER,/*融资人*/
        mci.CUSTOMER_NAME as FIX_ANCIER,/*融资人*/
        null as ASSET_TYPE_ONE,/*资产一级分类*/
             case when  FPT.TYPE_8=''F08'' THEN ''DIS01''--  拆借
               when  FPT.TYPE_8=''F07'' THEN ''DIS02''--  质押式回购
               when  FPT.TYPE_8=''F10'' THEN ''DIS03''--  买断式回购
               when  FPT.TYPE_8=''F05'' THEN ''DIS04''--  存款类资产
               when  FPT.TYPE_8=''F02'' THEN ''DIS05''--  基金类资产
               when  FPT.TYPE_8=''F04'' THEN ''DIS06''--  计划类（净值型）
               when  FPT.TYPE_8=''F03'' THEN ''DIS07''--  计划类（利率型）
               when  FPT.TYPE_8=''F09'' THEN ''DIS08''--  计划类（货币型）
               when  FPT.TYPE_8=''F06'' THEN ''DIS09''--  项目类资产
               when  FPT.TYPE_8=''F01'' THEN ''DIS10''--  债券
               when  FPT.TYPE_8=''F11'' THEN ''DIS11'' --股票
               when  FPT.TYPE_8=''F12'' THEN ''DIS12'' --债券借贷
               when  FPT.TYPE_8=''F13'' THEN ''DIS12'' --外汇即期
               when  FPT.TYPE_8=''F14'' THEN ''DIS12'' --外汇远期
               when  FPT.TYPE_8=''F15'' THEN ''DIS12'' --外汇掉期
               when  FPT.TYPE_8=''F16'' THEN ''DIS12'' --利率互换
               END AS DIS_ASSET_TYPEONE,--  资产类型
             case when  FPT.TYPE_6=''01''  THEN ''DIS0101''--  拆入
               when  FPT.TYPE_6=''02'' THEN ''DIS0102''--  拆出
               when  (FPT.TYPE_8=''F07'' and FPT.TYPE_6=''03'')  THEN ''DIS0201''--  质押式正回购
               when  (FPT.TYPE_8=''F07'' and FPT.TYPE_6=''04'')  THEN ''DIS0202''--  质押式逆回购
               when  (FPT.TYPE_8=''F10'' and FPT.TYPE_6=''03'')  THEN ''DIS0301''--  买断式正回购
               when  (FPT.TYPE_8=''F10'' and FPT.TYPE_6=''04'')  THEN ''DIS0302''--  买断式逆回购
               when  FPT.TYPE_6=''D01'' THEN ''DIS0401''--  同业借款
               when  FPT.TYPE_6=''D02'' THEN ''DIS0402''--  存放同业
               when  FPT.TYPE_6=''F01'' THEN ''DIS0501''--  货币基金
               when  FPT.TYPE_6=''F02'' THEN ''DIS0502''--  非货币基金
               when  (FPT.TYPE_8=''F04'' AND  FPT.TYPE_6=''N01'') THEN ''DIS0601''--  券商资产管理计划
               when  (FPT.TYPE_8=''F04'' AND FPT.TYPE_6=''N02'') THEN ''DIS0602''--  信托产品
               when  (FPT.TYPE_8=''F04'' AND FPT.TYPE_6=''N03'') THEN ''DIS0603''--  基金资产管理计划
               when  (FPT.TYPE_8=''F04'' AND FPT.TYPE_6=''N04'') THEN ''DIS0604''--  保险资产管理计划
               when  (FPT.TYPE_8=''F04'' AND FPT.TYPE_6=''N05'') THEN ''DIS0605''--  期货资产管理计划
               when  (FPT.TYPE_8=''F04'' AND FPT.TYPE_6=''N06'') THEN ''DIS0606''--  其他资产管理计划
               when  (FPT.TYPE_8=''F03'' AND FPT.TYPE_6=''N01'') THEN ''DIS0701''--  券商资产管理计划
               when  (FPT.TYPE_8=''F03'' AND FPT.TYPE_6=''N02'') THEN ''DIS0702''--  信托产品
               when  (FPT.TYPE_8=''F03'' AND FPT.TYPE_6=''N04'') THEN ''DIS0703''--  保险资产管理计划
               when  (FPT.TYPE_8=''F09'' AND FPT.TYPE_6=''N01'') THEN ''DIS0801''--  券商资产管理计划
               when  (FPT.TYPE_8=''F09'' AND FPT.TYPE_6=''N02'') THEN ''DIS0802''--  信托产品
               when  (FPT.TYPE_8=''F09'' AND FPT.TYPE_6=''N03'') THEN ''DIS0803''--  基金资产管理计划
               when  (FPT.TYPE_8=''F09'' AND FPT.TYPE_6=''N04'') THEN ''DIS0804''--  保险资产管理计划
               when  (FPT.TYPE_8=''F09'' AND FPT.TYPE_6=''N05'') THEN ''DIS0805''--  期货资产管理计划
               when  (FPT.TYPE_8=''F09'' AND FPT.TYPE_6=''N06'') THEN ''DIS0806''--  其他资产管理计划
               when  FPT.TYPE_6=''P01'' THEN ''DIS0901''--  非标债权
               when  FPT.TYPE_6=''P02'' THEN ''DIS0902''--  结构化证券
               when  FPT.TYPE_6=''P03'' THEN ''DIS0903''--  其他权益类投资
               when  FPT.TYPE_6=''P04'' THEN ''DIS0904''--  其他产业投资基金
               when  FPT.TYPE_6=''P05'' THEN ''DIS0905''--  股票定向增发投资
               when  FPT.TYPE_6=''P06'' THEN ''DIS0906''--  基础设施基金
               when  FPT.TYPE_6=''P07'' THEN ''DIS0907''--  券商债权收益权投资
               when  FPT.TYPE_6=''P08'' THEN ''DIS0908''--  信贷资产流转项目
               when  FPT.TYPE_6=''P09'' THEN ''DIS0909''--  券商收益凭证
               when  FPT.TYPE_6=''P10'' THEN ''DIS0910''--  股票质押式回购
               when  FPT.TYPE_6=''P11'' THEN ''DIS0911''--  理财直接融资工具
               when  FPT.TYPE_6='''' THEN ''DIS0912''--  委外投资
               when  FPT.TYPE_6=''B01'' THEN ''DIS1001''--  国债
               when  FPT.TYPE_6=''B02'' THEN ''DIS1002''--  地方政府债
               when  FPT.TYPE_6=''B03'' THEN ''DIS1003''--  央行票据
               when  FPT.TYPE_6=''B05'' THEN ''DIS1004''--  金融债
               when  FPT.TYPE_6=''B06'' THEN ''DIS1005''--  企业债
               when  FPT.TYPE_6=''B07'' THEN ''DIS1006''--  公司债
               when  FPT.TYPE_6=''B08'' THEN ''DIS1007''--    中期票据
               when    FPT.TYPE_6=''B09'' THEN ''DIS1008''--    短期融资券
               when    FPT.TYPE_6=''B10'' THEN ''DIS1009''--    定向工具
               when    FPT.TYPE_6=''B11'' THEN ''DIS1010''--    国际机构债
               when    FPT.TYPE_6=''B12'' THEN ''DIS1011''--    政府支持机构债
               when    FPT.TYPE_6=''B04'' THEN ''DIS1012''--    同业存单
               when    FPT.TYPE_6=''B13'' THEN ''DIS1013''--    资产支持证券
               when    FPT.TYPE_6=''B14'' THEN ''DIS1014''--    可转债
               when    FPT.TYPE_6=''B15'' THEN ''DIS1015''--    可交换债
               when    FPT.TYPE_6=''B16'' THEN ''DIS1016''--    可分离转债存债
               when    FPT.TYPE_6=''B17'' THEN ''DIS1017''--    优先股
               when    FPT.TYPE_6='''' THEN ''DIS1101''--    股权

               when    FPT.TYPE_8=''F11'' THEN ''DIS1102''--    股票(一级市场)
         when    FPT.TYPE_8=''F14'' THEN ''DIS1204''--    远期
         when    FPT.TYPE_8=''F16'' THEN ''DIS1205''--    互换
         when    FPT.TYPE_8=''F13'' THEN ''DIS1206''--    即期
         when    FPT.TYPE_8=''F15'' THEN ''DIS1207''--    掉期
         when    FPT.TYPE_8=''F12'' THEN ''DIS1208''--    债券借贷
               when    FPT.TYPE_6='''' THEN ''DIS1103'' END AS DIS_ASSET_TYPETWO,--    股票(二级市场)
             case  when   FPT.TYPE_6=''F02'' and FPT.TYPE_7=''01'' THEN ''DIS050201''--    债券基金
               when   FPT.TYPE_6=''F02'' and FPT.TYPE_7=''03'' THEN ''DIS050203''--    股票基金
               when   FPT.TYPE_6=''F02'' and FPT.TYPE_7=''04'' THEN ''DIS050204''--    混合基金
               when   FPT.TYPE_6=''F02'' and FPT.TYPE_7=''06'' THEN ''DIS050206''--    其他公募基金
               when   FPT.TYPE_6=''F02'' and FPT.TYPE_7=''05'' THEN ''DIS050205''--    基金中基金
               when    FPT.TYPE_7=''1201'' THEN ''DIS090101''--    票据类
               when    FPT.TYPE_7=''1210'' THEN ''DIS090110''--    信用证
               when    FPT.TYPE_7=''1202'' THEN ''DIS090102''--    信托贷款
               when    FPT.TYPE_7=''1203'' THEN ''DIS090103''--    委托贷款
               when    FPT.TYPE_7=''1204'' THEN ''DIS090104''--    信贷资产转让
               when    FPT.TYPE_7=''1205'' THEN ''DIS090105''--    收/受益权
               when    FPT.TYPE_7=''1206'' THEN ''DIS090106''--    委托债权
               when    FPT.TYPE_7=''1207'' THEN ''DIS090107''--    应收账款
               when    FPT.TYPE_7=''1208'' THEN ''DIS090108''--    带回购条款的股权性融资
               when    FPT.TYPE_7=''1209'' THEN ''DIS090109''--    债权融资类产品
               when    FPT.TYPE_7=''1299'' THEN ''DIS090199''--    其他非标准化债权类投资
               when    FPT.TYPE_6=''B05'' and FPT.TYPE_7=''01'' THEN ''DIS100401''--    政策银行债
               when    FPT.TYPE_6=''B05'' and FPT.TYPE_7=''02'' THEN ''DIS100402''--    商业银行债
               when    FPT.TYPE_6=''B05'' and FPT.TYPE_7=''03'' THEN ''DIS100403''--    商业银行次级债券
               when    FPT.TYPE_6=''B05'' and FPT.TYPE_7=''04'' THEN ''DIS100404''--    保险公司债
               when    FPT.TYPE_6=''B05'' and FPT.TYPE_7=''05'' THEN ''DIS100405''--    证券公司债
               when    FPT.TYPE_6=''B05'' and FPT.TYPE_7=''06'' THEN ''DIS100406''--    证券公司短期融资券
               when    FPT.TYPE_6=''B05'' and FPT.TYPE_7=''07'' THEN ''DIS100407''--    其他金融机构债
               when    FPT.TYPE_7=''08'' THEN ''DIS100501''--    一般企业债
               when    FPT.TYPE_7=''09'' THEN ''DIS100502''--    集合企业债
               when    FPT.TYPE_7=''10'' THEN ''DIS100601''--    一般公司债
               when    FPT.TYPE_7=''11'' THEN ''DIS100602''--    私募债
               when    FPT.TYPE_7=''12'' THEN ''DIS100701''--    一般中期票据
               when    FPT.TYPE_7=''13'' THEN ''DIS100702''--    集合票据
               when    FPT.TYPE_7=''14'' THEN ''DIS100801''--    超短期融资债券
               when    FPT.TYPE_7=''15'' THEN ''DIS100802''--    一般短期融资券
               when    FPT.TYPE_7=''16'' THEN ''DIS101301''--    证监会主管ABS
               when    FPT.TYPE_7=''17'' THEN ''DIS101302''--    银监会主管ABS
               when    FPT.TYPE_7=''18'' THEN ''DIS101303'' --    交易商协会ABN
         when    FPT.TYPE_6=''F29'' THEN ''DIS120801''--    债券借贷融入
         when    FPT.TYPE_6=''F30'' THEN ''DIS12080''--    债券借贷融出
         END AS DIS_ASSET_TYPETHREE,
        null as IS_STAND_ASSET,/*是否标准资产*/
        re.rep_industry INDUSTRY_SHARES,
        re.rep_industry_level2 INDUSTRY_SHARES2
        FROM REP_ASSET_DEPT ASSET
        left join FIN_PRODUCT f
        on ASSET.REP_FINPROD_ID = f.FINPROD_ID
        LEFT JOIN FIN_PRODUCT_TYPE FPT
        ON FPT.FINPROD_ID=F.FINPROD_ID AND FPT.BRANCH=0
        LEFT JOIN rep_asset_equity RE
        ON re.rep_finprod_id=f.finprod_id
    left join mst_customer_info mci on mci.CUSTOMER_ID = F.FINANCIER
        WHERE 1 =1
    and ASSET.IS_REP_TARGET = ''Y''
    AND F.FINPROD_ID IS NOT NULL
    union
    SELECT
        f.FINPROD_ID asset_ID,/*资产代码*/
        f.FINPROD_ID as MARKET_ID,/*市场代码*/
        (case
               when  FPT.TYPE_8=''F12'' THEN ''债券借贷'' --债券借贷
               when  FPT.TYPE_8=''F13'' THEN ''外汇即期'' --外汇即期
               when  FPT.TYPE_8=''F14'' THEN ''外汇远期'' --外汇远期
               when  FPT.TYPE_8=''F15'' THEN ''外汇掉期'' --外汇掉期
               when  FPT.TYPE_8=''F16'' THEN ''利率互换'' --利率互换
               END)||f.FINPROD_ID ASSET_NAME,/*资产名称*/
        null as ISSUER,/*发行人*/
        null as IDATE,/*发行日*/
        null as ISSUE_PRICE,/*发行价*/
        null as ISSUE_AMT,/*发行规模*/
        f.Ccy CCY,/*币种*/
        f.VDATE AS VDATE,/*起息日*/
        F.MDATE AS MDATE,/*到期日*/
        null as ACTMDATE,/*实际到期日*/
        F.TRADE_MARKET TRADE_MARKET,/*交易场所*/
        null AS REGIST_ORG,/*登记托管机构*/
        ''E'' STATUS,/*状态*/
        F.CREATE_USER CREATE_USER,/*创建人*/
        '''' CREATE_DEPT,/*创建部门*/
        F.UPDATE_USER UPDATE_USER,/*更新人*/
        null as ASSET_TYPE,/**/
        null as FIX_ANCIER,/*融资人*/
        null as ASSET_TYPE_ONE,/*资产一级分类*/
             case
               when  FPT.TYPE_8=''F08'' THEN ''DIS01''--  拆借
               when  FPT.TYPE_8=''F07'' THEN ''DIS02''--  质押式回购
               when  FPT.TYPE_8=''F10'' THEN ''DIS03''--  买断式回购  
               when  FPT.TYPE_8=''F12'' THEN ''DIS12'' --债券借贷
               when  FPT.TYPE_8=''F13'' THEN ''DIS12'' --外汇即期
               when  FPT.TYPE_8=''F14'' THEN ''DIS12'' --外汇远期
               when  FPT.TYPE_8=''F15'' THEN ''DIS12'' --外汇掉期
               when  FPT.TYPE_8=''F16'' THEN ''DIS12'' --利率互换
               END AS DIS_ASSET_TYPEONE,--  资产类型
         case
         when  (FPT.TYPE_6=''01''OR FPT.TYPE_7=''01'' ) THEN ''DIS0101''--  拆入
         when  (FPT.TYPE_6=''02''OR FPT.TYPE_7=''02'' ) THEN ''DIS0102''--  拆出
         when  (FPT.TYPE_8=''F07'' AND( FPT.TYPE_6=''03'' OR FPT.TYPE_7=''03''))  THEN ''DIS0201''--  质押式正回购
         when  (FPT.TYPE_8=''F07'' AND( FPT.TYPE_6=''04'' OR FPT.TYPE_7=''04''))  THEN ''DIS0202''--  质押式逆回购
         when  (FPT.TYPE_8=''F10'' AND( FPT.TYPE_6=''03'' OR FPT.TYPE_7=''03''))  THEN ''DIS0301''--  买断式正回购
         when  (FPT.TYPE_8=''F10'' AND( FPT.TYPE_6=''04'' OR FPT.TYPE_7=''04''))  THEN ''DIS0302''--  买断式逆回购
         when    FPT.TYPE_8=''F14'' THEN ''DIS1204''--    远期
         when    FPT.TYPE_8=''F16'' THEN ''DIS1205''--    互换
         when    FPT.TYPE_8=''F13'' THEN ''DIS1206''--    即期
         when    FPT.TYPE_8=''F15'' THEN ''DIS1207''--    掉期
         when    FPT.TYPE_8=''F12'' THEN ''DIS1208''--    债券借贷
               END AS DIS_ASSET_TYPETWO,--    股票(二级市场)
             case
         when    FPT.TYPE_6=''F29'' THEN ''DIS120801''--    债券借贷融入
         when    FPT.TYPE_6=''F30'' THEN ''DIS12080''--    债券借贷融出
         END AS DIS_ASSET_TYPETHREE,
        null as IS_STAND_ASSET,/*是否标准资产*/
        re.rep_industry INDUSTRY_SHARES,
        re.rep_industry_level2 INDUSTRY_SHARES2
        FROM FIN_TRADE_PRODUCT f
        LEFT JOIN FIN_PRODUCT_TYPE FPT
        ON FPT.FINPROD_ID=f.FINPROD_ID AND FPT.BRANCH=0
        LEFT JOIN rep_asset_equity RE
        ON re.rep_finprod_id=f.finprod_id
        WHERE 1 =1
        and f.FINPROD_TYPE2 in(''F02'',''F03'',''F04'',''F05'',''F08'',''F09'',''F11'',''F12'',''F13'',''F14'',''F29'',''F30'')';


BEGIN

	UPDATE ADPT_INTERFACESOURCE SET DBSQL = SQLDBSQL  WHERE SOURCEID = '5028';

end ;
