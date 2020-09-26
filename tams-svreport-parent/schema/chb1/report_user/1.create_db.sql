-- 生成者Oracle SQL Developer Data Modeler 18.4.0.339.1532
--   时间:        2019-02-19 14:07:07 CST
--   站点:      Oracle Database 11g
--   类型:      Oracle Database 11g

create table CHB_ASSET_INFO (
    ASSET_UUID              char(36 byte) not null,
    ASSET_CODE              varchar2(50 byte),
    ASSET_TYPE              varchar2(50 byte),
    ASSET_TYPE2             varchar2(50 byte),
    CCY                     varchar2(50 byte),
    TRADE_MARKET            varchar2(50 byte),
    VDATE                   date,
    MDATE                   date,
    DISCOUNT_YIELD          number(30, 14),
    ASSET_YIELD             number(30, 14),
    BASIS                   varchar2(50 byte),
    AMT                     number(30, 2),
    BANK_NAME               varchar2(50 byte),
    IS_STRUCT               varchar2(50 byte),
    TARGET_TYPE             varchar2(50 byte),
    TARGET                  varchar2(600 byte),
    ASSET_ID                varchar2(50 byte),
    ASSET_NAME              varchar2(200 byte),
    CHB_SECTYPE             varchar2(50 byte),
    ISSUE_MODE              varchar2(50 byte),
    PARTY_GRADE             varchar2(50 byte),
    PARTY_TYPE1             varchar2(50 byte),
    PARTY_TYPE3             varchar2(50 byte),
    PARTY_TYPE2             varchar2(50 byte),
    INDUSTRY                varchar2(50 byte),
    INDUSTRY_LEVEL2         varchar2(50 byte),
    REGIST_ORG              varchar2(50 byte),
    REGISTORG_COMMENT       varchar2(600 byte),
    RIVAL_PARTY             varchar2(600 byte),
    TARGET_TYPE1            varchar2(50 byte),
    TARGET_AMT              number(30, 2),
    COUNTRY_AREA            varchar2(50 byte),
    PROFIT_TYPE             varchar2(50 byte),
    IS_RESALE               varchar2(50 byte),
    TERM_DATE               number(30, 4),
    RATE_FLAG               varchar2(50 byte),
    INTPAY_CYCLE            number(10),
    REPAY_CMT               varchar2(600 byte),
    BASIS_TYPE              varchar2(50 byte),
    FLOAT_FACTOR            number(30, 14),
    SPREAD_RATE             number(30, 14),
    FINANCIE                varchar2(600 byte),
    FIN_ORG_CODE            varchar2(50 byte),
    FCR_GRADE_IN            varchar2(50 byte),
    FCR_GRADE_OUT           varchar2(600 byte),
    FINANCIER_TYPE1         varchar2(50 byte),
    FINANCIER_TYPE2         varchar2(50 byte),
    FINANCIER_TYPE3         varchar2(50 byte),
    FINANCE_PROJ            varchar2(600 byte),
    INDUSTRY1               varchar2(50 byte),
    INDUSTRY_LEVEL2_TYPE    varchar2(50 byte),
    PRO_INDUSTRY            varchar2(50 byte),
    PRO_INDUSTRY_LEVEL2     varchar2(50 byte),
    INDUSTRY_FLAG           varchar2(50 byte),
    MONIT_TYPE              varchar2(50 byte),
    MON_TYPE_COMM           varchar2(600 byte),
    GUA_TYPE                varchar2(50 byte),
    GUA_TYPE_REMARK         varchar2(200 byte),
    FINANCIER_GRADE         varchar2(50 byte),
    IN_GRADE                varchar2(20 byte),
    OUT_GRADE               varchar2(50 byte),
    CHOSE                   varchar2(60 byte),
    CONDITION_CMM           varchar2(600 byte),
    FIN_AREA                varchar2(50 byte),
    TOTAL_FEE_RATE          number(30, 14),
    IS_RIGHT                varchar2(50 byte),
    NOTSTAND_SEC_TYPE       varchar2(50 byte),
    NOTSTAND_SEC_NUM        number(10),
    REMAIN_TERM             number(30, 4),
    MAX_REMAIN_TERM         number(30, 4),
    MIN_REMAIN_TERM         number(30, 4),
    SHARES_TYPE             varchar2(50 byte),
    INDUSTRY_SHARES         varchar2(50 byte),
    INDUSTRY_SHARES2        varchar2(50 byte),
    INVEST_STAGE            varchar2(50 byte),
    SHARES_TYPE1            varchar2(50 byte),
    SHARES_TYPE2            varchar2(50 byte),
    SHARES_TYPE3            varchar2(50 byte),
    IS_PLEDGE               varchar2(50 byte),
    NOM_CAPI                number(30, 2),
    TARGET_TYPE3            varchar2(50 byte),
    ISSUE_PARTY_ID          varchar2(20 byte),
    TERM_MONTH              number(30, 4),
    GUARANTEE_CMT           varchar2(600 byte),
    SPECIAL_CMT             varchar2(600 byte),
    CONTRACT_NAME           varchar2(600 byte),
    INCOME_RATIO            number(30, 14),
    DER_INSTR_RATIO         number(30, 14),
    DER_INSTR_INV_TYPE      varchar2(600 byte),
    DER_INSTR_TARGET        varchar2(600 byte),
    ACCT_MODE               varchar2(600 byte),
    SPECIAL_CMT1            varchar2(600 byte),
    MAX_YIELD               number(30, 14),
    MIN_YIELD               number(30, 14),
    BASIC_PRICE             number(30, 14),
    REG_DATE_PRICE          number(30, 14),
    TRADE_FEE               varchar2(600 byte),
    PARTY_CODE              varchar2(32 byte),
    CHECK_CODE              varchar2(256 byte),
    MANAGER_ID              varchar2(600 byte),
    TRUSTEE_ID              varchar2(600 byte),
    TRU_ACT_TYPE            varchar2(50 byte),
    INVEST_TYPE             varchar2(600 byte),
    CAP_USE_INDU            varchar2(50 byte),
    PLAN_SDATE              date,
    PLAN_EDATE              date,
    PRO_PROPERTY            varchar2(50 byte),
    MAX_RATE                number(30, 14),
    MIN_RATE                number(30, 14),
    DEBT_SCALE              number(30, 14),
    BUY_STRUCT              varchar2(50 byte),
    MANAGE_TYPE             varchar2(50 byte),
    IS_CTRL_BOTTOM          varchar2(50 byte),
    MGR_FEE_RATE            number(30, 14),
    TRU_FEE_RATE            number(30, 14),
    TRADE_FEE_RATE          number(30, 14),
    AGENCY_FEE_RATE         number(30, 14),
    OTHER_FEE_RATE          number(30, 14),
    ASSET_VALUE             number(30, 14),
    REGIST_ORG1             varchar2(50 byte),
    MNG_ORG_NAME            varchar2(600 byte),
    TRU_ORG_NAME            varchar2(600 byte),
    FUND_INVEST_ASSET       varchar2(800 byte),
    IN_ASSET_TYPE           varchar2(50 byte),
    IN_ASSET_TYPE_CMM       varchar2(600 byte),
    REMARK                  varchar2(1000 byte),
    IS_UNDER_ASSET          varchar2(50 byte),
    IS_GOVERN_FUND          varchar2(50 byte),
    GOVERN_INVEST_FUND      varchar2(200 byte),
    FUND_ENPE_NAME          varchar2(200 byte),
    TRUSTEE                 varchar2(200 byte),
    STATUS                  varchar2(50 byte),
    CREATE_USER             varchar2(20 byte),
    CREATE_TIME             timestamp,
    UPDATE_USER             varchar2(20 byte),
    UPDATE_TIME             timestamp,
    DEPT_CODE               varchar2(20 byte),
    ORG_CODE                varchar2(20 byte),
    DATA_SOURCE             varchar2(100 byte),
    PRIVATEFUND_FLAG        varchar2(50 byte),
    PRIVATEPRD_FLAG         varchar2(50 byte),
    BONDTOSTOCK_FLAG        varchar2(50 byte),
    PPPFLAG                 varchar2(50 byte),
    STATUS_DATE             date,
    CAP_USE_INDU_LEVEL2     varchar2(50 byte),
    ISSUE_PARTY_NAME        varchar2(600 byte),
    SEC_CREDIT_GRADE_TYPE   varchar2(50 byte),
    SEC_MAIN_GRADE          varchar2(50 byte)
);

comment on table CHB_ASSET_INFO is
    '资产要素表';

comment on column CHB_ASSET_INFO.ASSET_UUID is
    '资产要素uuid（为修改删除使用）';

comment on column CHB_ASSET_INFO.ASSET_CODE is
    '行内资产负债编码';

comment on column CHB_ASSET_INFO.ASSET_TYPE is
    '资产/负债类别 01本行/他行存款||大额存单 02拆放同业/买入返售（资产）||同业拆入/卖出回购（负债） 03债券类资产/理财直接融资工具/同业存单 04非标准化债权类资产/信贷资产流转项目 05	票据类资产/信用证类资产 06权益类资产 07金融衍生品 08QDII债券资产 09QDII拆出/逆回购资产 10QDII股票/基金类资产 11QDII结构性票据类资产 12资产管理产品 13商品类资产 14各类资产的其他项 15公募基金 16委外投资——协议方式 17其他（负债）类资产 18另类资产 19私募基金'
    ;

comment on column CHB_ASSET_INFO.ASSET_TYPE2 is
    '资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL';

comment on column CHB_ASSET_INFO.CCY is
    '币种 参考数据字典：CURRENCY_CHB';

comment on column CHB_ASSET_INFO.TRADE_MARKET is
    '交易流通场所 01 银行间债券市场/02 商业银行柜台市场/03 上海证券交易所/04 深圳证券交易所/05 全国中小企业股份转让系统/06 上海期货交易所/07 郑州商品交易所/08 大连商品交易所/09 中国金融期货交易所/10 上海黄金交易所/11 区域性产权/资产交易市场/12 区域性商品交易所/13 区域性贵金属交易所/14 艺术品市场/15 基金市场/16 信托市场/17全国银行间同业拆借市场 /18 上海票据交易所/99 其他交易流通场所'
    ;

comment on column CHB_ASSET_INFO.VDATE is
    '起息日';

comment on column CHB_ASSET_INFO.MDATE is
    '到期日';

comment on column CHB_ASSET_INFO.DISCOUNT_YIELD is
    '贴现利率';

comment on column CHB_ASSET_INFO.ASSET_YIELD is
    '收益率/年利率';

comment on column CHB_ASSET_INFO.BASIS is
    '计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360';

comment on column CHB_ASSET_INFO.AMT is
    '金额';

comment on column CHB_ASSET_INFO.BANK_NAME is
    '资金存入银行';

comment on column CHB_ASSET_INFO.IS_STRUCT is
    '是否为结构性存款 01是/02否';

comment on column CHB_ASSET_INFO.TARGET_TYPE is
    '结构性存款挂钩标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他';

comment on column CHB_ASSET_INFO.TARGET is
    '结构性存款挂钩标的';

comment on column CHB_ASSET_INFO.ASSET_ID is
    '资产代码';

comment on column CHB_ASSET_INFO.ASSET_NAME is
    '资产名称';

comment on column CHB_ASSET_INFO.CHB_SECTYPE is
    '具体类别01 商业银行普通债/ 02 商业银行次级债/ 03 商业银行混合资本债/ 04 商业银行二级资本工具/ 05 证券公司金融债/ 06 证券公司短期融资券/ 07 保险公司金融债/ 08 财务公司金融债/ 09 融资租赁公司金融债/ 10 资产管理公司金融债/ 11 集合企业债/ 12 项目收益债券/ 13 可转换公司债券/ 14 可交换公司债券/ 15 分离交易的可转换公司债券/ 16 短期融资券（CP）/ 17 超短期融资券（SCP）/ 18 中期票据（MTN）/ 19 项目收益票据（PRN）/ 20 中小企业集合票据（SMECN）/ 21非公开定向债务融资工具/ 22 信贷资产支持证券/ 23 资产支持专项计划/ 24 非金融企业资产支持票据（ABN）/ 25 国际金融机构债券/ 26 外国政府债券/ 27 外国金融机构债券/ 28 外国公司债券/ 99 其他债券'
    ;

comment on column CHB_ASSET_INFO.ISSUE_MODE is
    '发行方式 01 公开发行/02 非公开发行';

comment on column CHB_ASSET_INFO.PARTY_GRADE is
    '发行机构主体信用评级';

comment on column CHB_ASSET_INFO.PARTY_TYPE1 is
    '发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他';

comment on column CHB_ASSET_INFO.PARTY_TYPE3 is
    '发行机构类型（按技术领域划分）01 高新技术企业/99 其他';

comment on column CHB_ASSET_INFO.PARTY_TYPE2 is
    '发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他';

comment on column CHB_ASSET_INFO.INDUSTRY is
    '发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO.INDUSTRY_LEVEL2 is
    '发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO.REGIST_ORG is
    '登记托管机构 01 中央国债登记结算有限责任公司/ 02 上海清算所/03 中国证券登记结算有限责任公司/99 其他';

comment on column CHB_ASSET_INFO.REGISTORG_COMMENT is
    '登记托管机构说明';

comment on column CHB_ASSET_INFO.RIVAL_PARTY is
    '对手方';

comment on column CHB_ASSET_INFO.TARGET_TYPE1 is
    '回购标的类别 01 债券/ 02 股票/03 基金类产品/99 其他';

comment on column CHB_ASSET_INFO.TARGET_AMT is
    '回购标的金额';

comment on column CHB_ASSET_INFO.COUNTRY_AREA is
    '所属国家或地区 参考数据字典：COUNTRY_AREA';

comment on column CHB_ASSET_INFO.PROFIT_TYPE is
    '收/受益权类型 01 信托贷款/ 02 信贷资产转让/ 03 委托债权/ 04 应收账款/ 05 带回购条款的股权性融资/06 私募债权/ 07 融资融券/ 08 证券公司收益凭证/ 09 委托贷款/ 99 其他';

comment on column CHB_ASSET_INFO.IS_RESALE is
    '是否属于买入返售 01是/02否';

comment on column CHB_ASSET_INFO.TERM_DATE is
    '期限（天）';

comment on column CHB_ASSET_INFO.RATE_FLAG is
    '是否有预期收益率 01是/02否';

comment on column CHB_ASSET_INFO.INTPAY_CYCLE is
    '付息频率（个月/次）';

comment on column CHB_ASSET_INFO.REPAY_CMT is
    '还本付息说明';

comment on column CHB_ASSET_INFO.BASIS_TYPE is
    '基准利率种类 参考数据字典：BASIS_TYPE';

comment on column CHB_ASSET_INFO.FLOAT_FACTOR is
    '浮动因子';

comment on column CHB_ASSET_INFO.SPREAD_RATE is
    '利差';

comment on column CHB_ASSET_INFO.FINANCIE is
    '融资人';

comment on column CHB_ASSET_INFO.FIN_ORG_CODE is
    '融资人组织机构代码';

comment on column CHB_ASSET_INFO.FCR_GRADE_IN is
    '融资人内部信用评级';

comment on column CHB_ASSET_INFO.FCR_GRADE_OUT is
    '外部评级机构名称及融资人评级结果';

comment on column CHB_ASSET_INFO.FINANCIER_TYPE1 is
    '融资人类型（按规模划分） 01 中小微企业/02 大型企业/99 其他';

comment on column CHB_ASSET_INFO.FINANCIER_TYPE2 is
    '融资人类型（按技术领域划分）01 高新技术企业/99 其他';

comment on column CHB_ASSET_INFO.FINANCIER_TYPE3 is
    '融资人类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他';

comment on column CHB_ASSET_INFO.FINANCE_PROJ is
    '融资项目';

comment on column CHB_ASSET_INFO.INDUSTRY1 is
    '融资人所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO.INDUSTRY_LEVEL2_TYPE is
    '融资人所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO.PRO_INDUSTRY is
    '融资项目所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO.PRO_INDUSTRY_LEVEL2 is
    '融资项目所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO.INDUSTRY_FLAG is
    '项目是否属于重点监控行业和领域 01 是/ 02 否';

comment on column CHB_ASSET_INFO.MONIT_TYPE is
    '重点监控行业和领域类别 01 房地产(保障房除外)/ 02 地方政府融资平台/ 03 “铁公基”行业/ 04 “两高一剩”行业/ 99 其他';

comment on column CHB_ASSET_INFO.MON_TYPE_COMM is
    '重点监控行业和领域类别说明';

comment on column CHB_ASSET_INFO.GUA_TYPE is
    '担保方式  01 信用担保/ 02 第三方保证/ 03 抵押/ 04 质押/ 05 信用增级/ 99 其他';

comment on column CHB_ASSET_INFO.GUA_TYPE_REMARK is
    '担保方式说明';

comment on column CHB_ASSET_INFO.FINANCIER_GRADE is
    '融资人主体评级';

comment on column CHB_ASSET_INFO.IN_GRADE is
    '资产内部评级';

comment on column CHB_ASSET_INFO.OUT_GRADE is
    '资产外部评级';

comment on column CHB_ASSET_INFO.CHOSE is
    '选择权';

comment on column CHB_ASSET_INFO.CONDITION_CMM is
    '行权条件说明';

comment on column CHB_ASSET_INFO.FIN_AREA is
    '融资人所属地区  参考数据字典：COUNTRY_AREA';

comment on column CHB_ASSET_INFO.TOTAL_FEE_RATE is
    '融资总费率';

comment on column CHB_ASSET_INFO.IS_RIGHT is
    '是否为收/受益权 01是/02否';

comment on column CHB_ASSET_INFO.NOTSTAND_SEC_TYPE is
    '非标准化债券类资产类型 01 商业承兑汇票/ 02 银行承兑汇票/ 03 其他票据/ 04 混合类票据/ 05 信用证';

comment on column CHB_ASSET_INFO.NOTSTAND_SEC_NUM is
    '非标准化债券类资产数量';

comment on column CHB_ASSET_INFO.REMAIN_TERM is
    '加权剩余期限(天)';

comment on column CHB_ASSET_INFO.MAX_REMAIN_TERM is
    '最长剩余期限(天)';

comment on column CHB_ASSET_INFO.MIN_REMAIN_TERM is
    '最短剩余期限(天)';

comment on column CHB_ASSET_INFO.SHARES_TYPE is
    '股票类型 01 普通股/02 优先股/99 其他';

comment on column CHB_ASSET_INFO.INDUSTRY_SHARES is
    '行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO.INDUSTRY_SHARES2 is
    '行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO.INVEST_STAGE is
    '投资阶段 01 种子期/ 02 起步期/ 03 扩张期/ 04 过渡期/ 05 重建期/ 06 已上市/ 99 其它';

comment on column CHB_ASSET_INFO.SHARES_TYPE1 is
    '投资企业类型（按规模划分） 01 中小微企业/02 大型企业/99 其他';

comment on column CHB_ASSET_INFO.SHARES_TYPE2 is
    '投资企业类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他';

comment on column CHB_ASSET_INFO.SHARES_TYPE3 is
    '投资企业类型（按技术领域划分）01 高新技术企业/99 其他';

comment on column CHB_ASSET_INFO.IS_PLEDGE is
    '是否为质押融资 01是/02否';

comment on column CHB_ASSET_INFO.NOM_CAPI is
    '名义本金';

comment on column CHB_ASSET_INFO.TARGET_TYPE3 is
    '标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他';

comment on column CHB_ASSET_INFO.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_ASSET_INFO.TERM_MONTH is
    '期限（月）';

comment on column CHB_ASSET_INFO.GUARANTEE_CMT is
    '担保情况说明';

comment on column CHB_ASSET_INFO.SPECIAL_CMT is
    '是否含权等特条款情况说明';

comment on column CHB_ASSET_INFO.CONTRACT_NAME is
    '合约名称';

comment on column CHB_ASSET_INFO.INCOME_RATIO is
    '固定收益部分所占比例';

comment on column CHB_ASSET_INFO.DER_INSTR_RATIO is
    '衍生金融工具所占比例';

comment on column CHB_ASSET_INFO.DER_INSTR_INV_TYPE is
    '衍生金融工具具体投资方式';

comment on column CHB_ASSET_INFO.DER_INSTR_TARGET is
    '衍生金融工具挂钩标的资产';

comment on column CHB_ASSET_INFO.ACCT_MODE is
    '利息结算方式';

comment on column CHB_ASSET_INFO.SPECIAL_CMT1 is
    '含权情况说明';

comment on column CHB_ASSET_INFO.MAX_YIELD is
    '结构性票据最高收益率';

comment on column CHB_ASSET_INFO.MIN_YIELD is
    '结构性票据最低收益率';

comment on column CHB_ASSET_INFO.BASIC_PRICE is
    '挂钩标的资产基准价格';

comment on column CHB_ASSET_INFO.REG_DATE_PRICE is
    '挂钩标的资产登记日价格';

comment on column CHB_ASSET_INFO.TRADE_FEE is
    '交易费';

comment on column CHB_ASSET_INFO.PARTY_CODE is
    '资管计划发起人机构编码';

comment on column CHB_ASSET_INFO.CHECK_CODE is
    '资管计划登记编码';

comment on column CHB_ASSET_INFO.MANAGER_ID is
    '管理人';

comment on column CHB_ASSET_INFO.TRUSTEE_ID is
    '托管人';

comment on column CHB_ASSET_INFO.TRU_ACT_TYPE is
    '资金实际投向 01 结构性投资类/02 货币市场工具类/03 债券类/04 股票类/05 股权类/06 另类投资类/07 混合类/08 非标准化债权类/09 银行存款类/10 理财直接融资工具类/11 新增可投资资产类/12 代客境外投资类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类/18 同业存单类/19拆放同业及买入返售类'
    ;

comment on column CHB_ASSET_INFO.INVEST_TYPE is
    '资金运用方式';

comment on column CHB_ASSET_INFO.CAP_USE_INDU is
    '资金运用行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO.PLAN_SDATE is
    '资管计划成立日期';

comment on column CHB_ASSET_INFO.PLAN_EDATE is
    '资管计划终止日期';

comment on column CHB_ASSET_INFO.PRO_PROPERTY is
    '资管计划属性/委外投资属性 01 集合/02 单一/03 专项/04 定向/05 专户/99 其他';

comment on column CHB_ASSET_INFO.MAX_RATE is
    '最高预期收益率';

comment on column CHB_ASSET_INFO.MIN_RATE is
    '最低预期收益率';

comment on column CHB_ASSET_INFO.DEBT_SCALE is
    '负债规模';

comment on column CHB_ASSET_INFO.BUY_STRUCT is
    '购买结构 01 优先/02 劣后/03 整体';

comment on column CHB_ASSET_INFO.MANAGE_TYPE is
    '管理方式 01 主动（自主管理）/02被动（委托管理）/99其他';

comment on column CHB_ASSET_INFO.IS_CTRL_BOTTOM is
    '是否掌控计划底层资产详情 01 是/ 02 否';

comment on column CHB_ASSET_INFO.MGR_FEE_RATE is
    '管理费率';

comment on column CHB_ASSET_INFO.TRU_FEE_RATE is
    '托管费率';

comment on column CHB_ASSET_INFO.TRADE_FEE_RATE is
    '交易相关合计费率';

comment on column CHB_ASSET_INFO.AGENCY_FEE_RATE is
    '中介服务机构合计费率';

comment on column CHB_ASSET_INFO.OTHER_FEE_RATE is
    '其他合计费率';

comment on column CHB_ASSET_INFO.ASSET_VALUE is
    '资产价值';

comment on column CHB_ASSET_INFO.REGIST_ORG1 is
    '登记备案机构 01 中央国债登记结算有限责任公司/ 02 上海清算所 / 03 中国证券登记结算有限责任公司 / 04 中国证券投资基金业协会 /99 其他';

comment on column CHB_ASSET_INFO.MNG_ORG_NAME is
    '基金管理机构名称';

comment on column CHB_ASSET_INFO.TRU_ORG_NAME is
    '基金托管机构名称';

comment on column CHB_ASSET_INFO.FUND_INVEST_ASSET is
    '基金投资资产';

comment on column CHB_ASSET_INFO.IN_ASSET_TYPE is
    '行内资产类别 01 场内股票质押回购/02 场外股票质押融资/03 融资租赁/99 其他';

comment on column CHB_ASSET_INFO.IN_ASSET_TYPE_CMM is
    '行内资产类别说明';

comment on column CHB_ASSET_INFO.REMARK is
    '备注';

comment on column CHB_ASSET_INFO.IS_UNDER_ASSET is
    '是否底层资产 01 是/ 02 否';

comment on column CHB_ASSET_INFO.IS_GOVERN_FUND is
    '是否属于政府投资基金 01 是/ 02 否';

comment on column CHB_ASSET_INFO.GOVERN_INVEST_FUND is
    '政府投资基金投向 01 投向创业早期企业/ 02 投向中小微企业/ 03 投向产业转型升级和发展/ 04 投向基础设施和公共服务领域/99 其他';

comment on column CHB_ASSET_INFO.FUND_ENPE_NAME is
    '基金公司名称';

comment on column CHB_ASSET_INFO.TRUSTEE is
    '受托人';

comment on column CHB_ASSET_INFO.STATUS is
    '数据状态  D	删除 A	待处理 E	有效';

comment on column CHB_ASSET_INFO.CREATE_USER is
    '创建人';

comment on column CHB_ASSET_INFO.CREATE_TIME is
    '创建人';

comment on column CHB_ASSET_INFO.UPDATE_USER is
    '更新人';

comment on column CHB_ASSET_INFO.UPDATE_TIME is
    '更新时间';

comment on column CHB_ASSET_INFO.DEPT_CODE is
    '部门代码';

comment on column CHB_ASSET_INFO.ORG_CODE is
    '机构代码';

comment on column CHB_ASSET_INFO.DATA_SOURCE is
    '数据来源';

comment on column CHB_ASSET_INFO.PRIVATEFUND_FLAG is
    '是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金';

comment on column CHB_ASSET_INFO.PRIVATEPRD_FLAG is
    '是否投向金融资产投资公司发行的私募资产管理产品';

comment on column CHB_ASSET_INFO.BONDTOSTOCK_FLAG is
    '是否投向“市场化债转股相关产品的部分”';

comment on column CHB_ASSET_INFO.PPPFLAG is
    '公共PPP';

comment on column CHB_ASSET_INFO.STATUS_DATE is
    '数据日期';

comment on column CHB_ASSET_INFO.CAP_USE_INDU_LEVEL2 is
    '资金运用行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO.ISSUE_PARTY_NAME is
    '发行机构';

comment on column CHB_ASSET_INFO.SEC_CREDIT_GRADE_TYPE is
    '债券信用评级';

comment on column CHB_ASSET_INFO.SEC_MAIN_GRADE is
    '债券主体评级';

create unique index CHB_ASSET_INFO_PK on
    CHB_ASSET_INFO (
        ASSET_UUID
    asc );

alter table CHB_ASSET_INFO add constraint CHB_ASSET_INFO_PK primary key ( ASSET_UUID );

create table CHB_ASSET_INFO_SUB (
    REGIST_SEQNO             char(36 byte) not null,
    ASSET_CODE               varchar2(50 byte),
    ASSET_TYPE               varchar2(50 byte),
    ASSET_TYPE2              varchar2(50 byte),
    CCY                      varchar2(50 byte),
    TRADE_MARKET             varchar2(50 byte),
    VDATE                    date,
    MDATE                    date,
    ASSET_YIELD              number(30, 14),
    BASIS                    varchar2(50 byte),
    AMT                      number(30, 2),
    BANK_NAME                varchar2(50 byte),
    IS_STRUCT                varchar2(50 byte),
    TARGET_TYPE              varchar2(50 byte),
    TARGET                   varchar2(600 byte),
    ASSET_ID                 varchar2(50 byte),
    ASSET_NAME               varchar2(200 byte),
    CHB_SECTYPE              varchar2(50 byte),
    ISSUE_MODE               varchar2(50 byte),
    PARTY_GRADE              varchar2(50 byte),
    PARTY_TYPE1              varchar2(50 byte),
    PARTY_TYPE3              varchar2(50 byte),
    PARTY_TYPE2              varchar2(50 byte),
    INDUSTRY                 varchar2(50 byte),
    INDUSTRY_LEVEL2          varchar2(50 byte),
    SEC_MAIN_GRADE           varchar2(50 byte),
    SEC_CREDIT_GRADE         varchar2(50 byte),
    SEC_CREDIT_GRADE_TYPE    varchar2(50 byte),
    REGIST_ORG               varchar2(50 byte),
    REGISTORG_COMMENT        varchar2(600 byte),
    RIVAL_PARTY              varchar2(600 byte),
    TARGET_TYPE1             varchar2(50 byte),
    TARGET_AMT               number(30, 2),
    COUNTRY_AREA             varchar2(50 byte),
    PROFIT_TYPE              varchar2(50 byte),
    IS_RESALE                varchar2(50 byte),
    TERM_DATE                number(30, 4),
    RATE_FLAG                varchar2(50 byte),
    INTPAY_CYCLE             number(10),
    REPAY_CMT                varchar2(600 byte),
    BASIS_TYPE               varchar2(50 byte),
    FLOAT_FACTOR             number(30, 14),
    SPREAD_RATE              number(30, 14),
    FINANCIE                 varchar2(600 byte),
    FIN_ORG_CODE             varchar2(50 byte),
    FCR_GRADE_IN             varchar2(50 byte),
    FCR_GRADE_OUT            varchar2(600 byte),
    FINANCIER_TYPE1          varchar2(50 byte),
    FINANCIER_TYPE2          varchar2(50 byte),
    FINANCIER_TYPE3          varchar2(50 byte),
    FINANCE_PROJ             varchar2(600 byte),
    INDUSTRY1                varchar2(50 byte),
    INDUSTRY_LEVEL2_TYPE     varchar2(50 byte),
    PRO_INDUSTRY             varchar2(50 byte),
    PRO_INDUSTRY_LEVEL2      varchar2(50 byte),
    INDUSTRY_FLAG            varchar2(50 byte),
    MONIT_TYPE               varchar2(50 byte),
    MON_TYPE_COMM            varchar2(1600 byte),
    GUA_TYPE                 varchar2(50 byte),
    GUA_TYPE_REMARK          varchar2(200 byte),
    FINANCIER_GRADE          varchar2(50 byte),
    IN_GRADE                 varchar2(20 byte),
    OUT_GRADE                varchar2(50 byte),
    CHOSE                    varchar2(60 byte),
    CONDITION_CMM            varchar2(600 byte),
    FIN_AREA                 varchar2(50 byte),
    TOTAL_FEE_RATE           number(30, 14),
    IS_RIGHT                 varchar2(50 byte),
    NOTSTAND_SEC_TYPE        varchar2(50 byte),
    NOTSTAND_SEC_NUM         number(10),
    REMAIN_TERM              number(30, 4),
    MAX_REMAIN_TERM          number(30, 4),
    MIN_REMAIN_TERM          number(30, 4),
    SHARES_TYPE              varchar2(50 byte),
    INDUSTRY_SHARES          varchar2(50 byte),
    INDUSTRY_SHARES2         varchar2(50 byte),
    INVEST_STAGE             varchar2(50 byte),
    SHARES_TYPE1             varchar2(50 byte),
    SHARES_TYPE2             varchar2(50 byte),
    SHARES_TYPE3             varchar2(50 byte),
    IS_PLEDGE                varchar2(50 byte),
    NOM_CAPI                 number(30, 2),
    TARGET_TYPE3             varchar2(50 byte),
    ISSUE_PARTY_ID           varchar2(20 byte),
    TERM_MONTH               number(30, 4),
    GUARANTEE_CMT            varchar2(600 byte),
    SPECIAL_CMT              varchar2(600 byte),
    CONTRACT_NAME            varchar2(600 byte),
    INCOME_RATIO             number(30, 14),
    DER_INSTR_RATIO          number(30, 14),
    DER_INSTR_INV_TYPE       varchar2(600 byte),
    DER_INSTR_TARGET         varchar2(600 byte),
    ACCT_MODE                varchar2(600 byte),
    SPECIAL_CMT1             varchar2(600 byte),
    MAX_YIELD                number(30, 14),
    MIN_YIELD                number(30, 14),
    BASIC_PRICE              number(30, 14),
    REG_DATE_PRICE           number(30, 14),
    TRADE_FEE                varchar2(600 byte),
    PARTY_CODE               varchar2(32 byte),
    CHECK_CODE               varchar2(256 byte),
    MANAGER_ID               varchar2(600 byte),
    TRUSTEE_ID               varchar2(600 byte),
    TRU_ACT_TYPE             varchar2(50 byte),
    INVEST_TYPE              varchar2(600 byte),
    CAP_USE_INDU             varchar2(50 byte),
    PLAN_SDATE               date,
    PLAN_EDATE               date,
    PRO_PROPERTY             varchar2(50 byte),
    MAX_RATE                 number(30, 14),
    MIN_RATE                 number(30, 14),
    DEBT_SCALE               number(30, 14),
    BUY_STRUCT               varchar2(50 byte),
    MANAGE_TYPE              varchar2(50 byte),
    IS_CTRL_BOTTOM           varchar2(50 byte),
    MGR_FEE_RATE             number(30, 14),
    TRU_FEE_RATE             number(30, 14),
    TRADE_FEE_RATE           number(30, 14),
    AGENCY_FEE_RATE          number(30, 14),
    OTHER_FEE_RATE           number(30, 14),
    ASSET_VALUE              number(30, 14),
    REGIST_ORG1              varchar2(50 byte),
    MNG_ORG_NAME             varchar2(600 byte),
    TRU_ORG_NAME             varchar2(600 byte),
    FUND_INVEST_ASSET        varchar2(800 byte),
    IN_ASSET_TYPE            varchar2(50 byte),
    IN_ASSET_TYPE_CMM        varchar2(600 byte),
    REMARK                   varchar2(1000 byte),
    IS_UNDER_ASSET           varchar2(50 byte),
    IS_GOVERN_FUND           varchar2(50 byte),
    GOVERN_INVEST_FUND       varchar2(200 byte),
    FUND_ENPE_NAME           varchar2(200 byte),
    TRUSTEE                  varchar2(200 byte),
    SUBMIT_DEADLINE          date,
    CHB_SUBMIT_DEADLINE      date,
    SUBMIT_TIME              date,
    STATUS_DATE              date,
    STATUS                   varchar2(50 byte),
    CREATE_USER              varchar2(20 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp,
    ORG_CODE                 varchar2(20 byte),
    DEPT_CODE                varchar2(20 byte),
    SEND_STATUS              varchar2(50 byte),
    SUBMIT_FEEDBACK_STATUS   varchar2(50 byte),
    CREATE_DEPT              varchar2(32 byte),
    DATA_SOURCE              varchar2(100 byte),
    DELETE_RES               varchar2(150 byte),
    CAP_USE_INDU_LEVEL2      varchar2(50 byte),
    ISSUE_PARTY_NAME         varchar2(600 byte)
);

comment on table CHB_ASSET_INFO_SUB is
    '资产要素登记表';

comment on column CHB_ASSET_INFO_SUB.REGIST_SEQNO is
    '登记序号';

comment on column CHB_ASSET_INFO_SUB.ASSET_CODE is
    '行内资产负债编码';

comment on column CHB_ASSET_INFO_SUB.ASSET_TYPE is
    '资产/负债类别 01本行/他行存款||大额存单 02拆放同业/买入返售（资产）||同业拆入/卖出回购（负债） 03债券类资产/理财直接融资工具/同业存单 04非标准化债权类资产/信贷资产流转项目 05	票据类资产/信用证类资产 06权益类资产 07金融衍生品 08QDII债券资产 09QDII拆出/逆回购资产 10QDII股票/基金类资产 11QDII结构性票据类资产 12资产管理产品 13商品类资产 14各类资产的其他项 15公募基金 16委外投资——协议方式 17其他（负债）类资产 18另类资产 19私募基金'
    ;

comment on column CHB_ASSET_INFO_SUB.ASSET_TYPE2 is
    '资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL';

comment on column CHB_ASSET_INFO_SUB.CCY is
    '币种 参考数据字典：CURRENCY_CHB';

comment on column CHB_ASSET_INFO_SUB.TRADE_MARKET is
    '交易流通场所01 银行间债券市场/02 商业银行柜台市场/03 上海证券交易所/04 深圳证券交易所/05 全国中小企业股份转让系统/06 上海期货交易所/07 郑州商品交易所/08 大连商品交易所/09 中国金融期货交易所/10 上海黄金交易所/11 区域性产权/资产交易市场/12 区域性商品交易所/13 区域性贵金属交易所/14 艺术品市场/15 基金市场/16 信托市场/17全国银行间同业拆借市场 /18 上海票据交易所/99 其他交易流通场所'
    ;

comment on column CHB_ASSET_INFO_SUB.VDATE is
    '起息日';

comment on column CHB_ASSET_INFO_SUB.MDATE is
    '到期日';

comment on column CHB_ASSET_INFO_SUB.ASSET_YIELD is
    '收益率/年利率';

comment on column CHB_ASSET_INFO_SUB.BASIS is
    '计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360';

comment on column CHB_ASSET_INFO_SUB.AMT is
    '金额';

comment on column CHB_ASSET_INFO_SUB.BANK_NAME is
    '资金存入银行';

comment on column CHB_ASSET_INFO_SUB.IS_STRUCT is
    '是否为结构性存款 01是/02否';

comment on column CHB_ASSET_INFO_SUB.TARGET_TYPE is
    '结构性存款挂钩标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他';

comment on column CHB_ASSET_INFO_SUB.TARGET is
    '结构性存款挂钩标的';

comment on column CHB_ASSET_INFO_SUB.ASSET_ID is
    '资产代码';

comment on column CHB_ASSET_INFO_SUB.ASSET_NAME is
    '资产名称';

comment on column CHB_ASSET_INFO_SUB.CHB_SECTYPE is
    '具体类别 01 商业银行普通债/ 02 商业银行次级债/ 03 商业银行混合资本债/ 04 商业银行二级资本工具/ 05 证券公司金融债/ 06 证券公司短期融资券/ 07 保险公司金融债/ 08 财务公司金融债/ 09 融资租赁公司金融债/ 10 资产管理公司金融债/ 11 集合企业债/ 12 项目收益债券/ 13 可转换公司债券/ 14 可交换公司债券/ 15 分离交易的可转换公司债券/ 16 短期融资券（CP）/ 17 超短期融资券（SCP）/ 18 中期票据（MTN）/ 19 项目收益票据（PRN）/ 20 中小企业集合票据（SMECN）/ 21非公开定向债务融资工具/ 22 信贷资产支持证券/ 23 资产支持专项计划/ 24 非金融企业资产支持票据（ABN）/ 25 国际金融机构债券/ 26 外国政府债券/ 27 外国金融机构债券/ 28 外国公司债券/ 99 其他债券'
    ;

comment on column CHB_ASSET_INFO_SUB.ISSUE_MODE is
    '发行方式 01 公开发行/02 非公开发行';

comment on column CHB_ASSET_INFO_SUB.PARTY_GRADE is
    '发行机构主体信用评级';

comment on column CHB_ASSET_INFO_SUB.PARTY_TYPE1 is
    '发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他';

comment on column CHB_ASSET_INFO_SUB.PARTY_TYPE3 is
    '发行机构类型（按技术领域划分）01 高新技术企业/99 其他01 高新技术企业/99 其他';

comment on column CHB_ASSET_INFO_SUB.PARTY_TYPE2 is
    '发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他';

comment on column CHB_ASSET_INFO_SUB.INDUSTRY is
    '发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO_SUB.INDUSTRY_LEVEL2 is
    '发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO_SUB.SEC_MAIN_GRADE is
    '债券主体评级';

comment on column CHB_ASSET_INFO_SUB.SEC_CREDIT_GRADE is
    '债券债项评级';

comment on column CHB_ASSET_INFO_SUB.SEC_CREDIT_GRADE_TYPE is
    '债券信用评级';

comment on column CHB_ASSET_INFO_SUB.REGIST_ORG is
    '登记托管机构 01 中央国债登记结算有限责任公司/ 02 上海清算所/03 中国证券登记结算有限责任公司/99 其他';

comment on column CHB_ASSET_INFO_SUB.REGISTORG_COMMENT is
    '登记托管机构说明';

comment on column CHB_ASSET_INFO_SUB.RIVAL_PARTY is
    '对手方';

comment on column CHB_ASSET_INFO_SUB.TARGET_TYPE1 is
    '回购标的类别 01 债券/ 02 股票/03 基金类产品/99 其他';

comment on column CHB_ASSET_INFO_SUB.TARGET_AMT is
    '回购标的金额';

comment on column CHB_ASSET_INFO_SUB.COUNTRY_AREA is
    '所属国家或地区 参考数据字典：COUNTRY_AREA';

comment on column CHB_ASSET_INFO_SUB.PROFIT_TYPE is
    '收/受益权类型 01 信托贷款/ 02 信贷资产转让/ 03 委托债权/ 04 应收账款/ 05 带回购条款的股权性融资/06 私募债权/ 07 融资融券/ 08 证券公司收益凭证/ 09 委托贷款/ 99 其他';

comment on column CHB_ASSET_INFO_SUB.IS_RESALE is
    '是否属于买入返售 01是/02否';

comment on column CHB_ASSET_INFO_SUB.TERM_DATE is
    '期限（天）';

comment on column CHB_ASSET_INFO_SUB.RATE_FLAG is
    '是否有预期收益率 01是/02否';

comment on column CHB_ASSET_INFO_SUB.INTPAY_CYCLE is
    '付息频率（个月/次）';

comment on column CHB_ASSET_INFO_SUB.REPAY_CMT is
    '还本付息说明';

comment on column CHB_ASSET_INFO_SUB.BASIS_TYPE is
    '基准利率种类 参考数据字典：BASIS_TYPE';

comment on column CHB_ASSET_INFO_SUB.FLOAT_FACTOR is
    '浮动因子';

comment on column CHB_ASSET_INFO_SUB.SPREAD_RATE is
    '利差';

comment on column CHB_ASSET_INFO_SUB.FINANCIE is
    '融资人';

comment on column CHB_ASSET_INFO_SUB.FIN_ORG_CODE is
    '融资人组织机构代码';

comment on column CHB_ASSET_INFO_SUB.FCR_GRADE_IN is
    '融资人内部信用评级';

comment on column CHB_ASSET_INFO_SUB.FCR_GRADE_OUT is
    '外部评级机构名称及融资人评级结果';

comment on column CHB_ASSET_INFO_SUB.FINANCIER_TYPE1 is
    '融资人类型（按规模划分） 01 中小微企业/02 大型企业/99 其他';

comment on column CHB_ASSET_INFO_SUB.FINANCIER_TYPE2 is
    '融资人类型（按技术领域划分）01 高新技术企业/99 其他';

comment on column CHB_ASSET_INFO_SUB.FINANCIER_TYPE3 is
    '融资人类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他';

comment on column CHB_ASSET_INFO_SUB.FINANCE_PROJ is
    '融资项目';

comment on column CHB_ASSET_INFO_SUB.INDUSTRY1 is
    '融资人所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO_SUB.INDUSTRY_LEVEL2_TYPE is
    '融资人所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO_SUB.PRO_INDUSTRY is
    '融资项目所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO_SUB.PRO_INDUSTRY_LEVEL2 is
    '融资项目所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO_SUB.INDUSTRY_FLAG is
    '项目是否属于重点监控行业和领域 01 是/ 02 否';

comment on column CHB_ASSET_INFO_SUB.MONIT_TYPE is
    '重点监控行业和领域类别 01 房地产(保障房除外)/ 02 地方政府融资平台/ 03 “铁公基”行业/ 04 “两高一剩”行业/ 99 其他';

comment on column CHB_ASSET_INFO_SUB.MON_TYPE_COMM is
    '重点监控行业和领域类别说明';

comment on column CHB_ASSET_INFO_SUB.GUA_TYPE is
    '担保方式 01 信用担保/ 02 第三方保证/ 03 抵押/ 04 质押/ 05 信用增级/ 99 其他';

comment on column CHB_ASSET_INFO_SUB.GUA_TYPE_REMARK is
    '担保方式说明';

comment on column CHB_ASSET_INFO_SUB.FINANCIER_GRADE is
    '融资人主体评级';

comment on column CHB_ASSET_INFO_SUB.IN_GRADE is
    '资产内部评级';

comment on column CHB_ASSET_INFO_SUB.OUT_GRADE is
    '资产外部评级';

comment on column CHB_ASSET_INFO_SUB.CHOSE is
    '选择权';

comment on column CHB_ASSET_INFO_SUB.CONDITION_CMM is
    '行权条件说明';

comment on column CHB_ASSET_INFO_SUB.FIN_AREA is
    '融资人所属地区  参考数据字典：COUNTRY_AREA';

comment on column CHB_ASSET_INFO_SUB.TOTAL_FEE_RATE is
    '融资总费率';

comment on column CHB_ASSET_INFO_SUB.IS_RIGHT is
    '是否为收/受益权 01是/02否';

comment on column CHB_ASSET_INFO_SUB.NOTSTAND_SEC_TYPE is
    '非标准化债券类资产类型 01 商业承兑汇票/ 02 银行承兑汇票/ 03 其他票据/ 04 混合类票据/ 05 信用证';

comment on column CHB_ASSET_INFO_SUB.NOTSTAND_SEC_NUM is
    '非标准化债券类资产数量';

comment on column CHB_ASSET_INFO_SUB.REMAIN_TERM is
    '加权剩余期限(天)';

comment on column CHB_ASSET_INFO_SUB.MAX_REMAIN_TERM is
    '最长剩余期限(天)';

comment on column CHB_ASSET_INFO_SUB.MIN_REMAIN_TERM is
    '最短剩余期限(天)';

comment on column CHB_ASSET_INFO_SUB.SHARES_TYPE is
    '股票类型 01 普通股/02 优先股/99 其他';

comment on column CHB_ASSET_INFO_SUB.INDUSTRY_SHARES is
    '行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO_SUB.INDUSTRY_SHARES2 is
    '行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO_SUB.INVEST_STAGE is
    '投资阶段 01 种子期/ 02 起步期/ 03 扩张期/ 04 过渡期/ 05 重建期/ 06 已上市/ 99 其它';

comment on column CHB_ASSET_INFO_SUB.SHARES_TYPE1 is
    '投资企业类型（按规模划分） 01 中小微企业/02 大型企业/99 其他';

comment on column CHB_ASSET_INFO_SUB.SHARES_TYPE2 is
    '投资企业类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他';

comment on column CHB_ASSET_INFO_SUB.SHARES_TYPE3 is
    '投资企业类型（按技术领域划分）01 高新技术企业/99 其他';

comment on column CHB_ASSET_INFO_SUB.IS_PLEDGE is
    '是否为质押融资 01是/02否';

comment on column CHB_ASSET_INFO_SUB.NOM_CAPI is
    '名义本金';

comment on column CHB_ASSET_INFO_SUB.TARGET_TYPE3 is
    '标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他';

comment on column CHB_ASSET_INFO_SUB.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_ASSET_INFO_SUB.TERM_MONTH is
    '期限（月）';

comment on column CHB_ASSET_INFO_SUB.GUARANTEE_CMT is
    '担保情况说明';

comment on column CHB_ASSET_INFO_SUB.SPECIAL_CMT is
    '是否含权等特条款情况说明';

comment on column CHB_ASSET_INFO_SUB.CONTRACT_NAME is
    '合约名称';

comment on column CHB_ASSET_INFO_SUB.INCOME_RATIO is
    '固定收益部分所占比例';

comment on column CHB_ASSET_INFO_SUB.DER_INSTR_RATIO is
    '衍生金融工具所占比例';

comment on column CHB_ASSET_INFO_SUB.DER_INSTR_INV_TYPE is
    '衍生金融工具具体投资方式';

comment on column CHB_ASSET_INFO_SUB.DER_INSTR_TARGET is
    '衍生金融工具挂钩标的资产';

comment on column CHB_ASSET_INFO_SUB.ACCT_MODE is
    '利息结算方式';

comment on column CHB_ASSET_INFO_SUB.SPECIAL_CMT1 is
    '含权情况说明';

comment on column CHB_ASSET_INFO_SUB.MAX_YIELD is
    '结构性票据最高收益率';

comment on column CHB_ASSET_INFO_SUB.MIN_YIELD is
    '结构性票据最低收益率';

comment on column CHB_ASSET_INFO_SUB.BASIC_PRICE is
    '挂钩标的资产基准价格';

comment on column CHB_ASSET_INFO_SUB.REG_DATE_PRICE is
    '挂钩标的资产登记日价格';

comment on column CHB_ASSET_INFO_SUB.TRADE_FEE is
    '交易费';

comment on column CHB_ASSET_INFO_SUB.PARTY_CODE is
    '资管计划发起人机构编码';

comment on column CHB_ASSET_INFO_SUB.CHECK_CODE is
    '资管计划登记编码';

comment on column CHB_ASSET_INFO_SUB.MANAGER_ID is
    '管理人';

comment on column CHB_ASSET_INFO_SUB.TRUSTEE_ID is
    '托管人';

comment on column CHB_ASSET_INFO_SUB.TRU_ACT_TYPE is
    '资金实际投向 01 结构性投资类/02 货币市场工具类/03 债券类/04 股票类/05 股权类/06 另类投资类/07 混合类/08 非标准化债权类/09 银行存款类/10 理财直接融资工具类/11 新增可投资资产类/12 代客境外投资类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类/18 同业存单类/19拆放同业及买入返售类'
    ;

comment on column CHB_ASSET_INFO_SUB.INVEST_TYPE is
    '资金运用方式';

comment on column CHB_ASSET_INFO_SUB.CAP_USE_INDU is
    '资金运用行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';

comment on column CHB_ASSET_INFO_SUB.PLAN_SDATE is
    '资管计划成立日期';

comment on column CHB_ASSET_INFO_SUB.PLAN_EDATE is
    '资管计划终止日期';

comment on column CHB_ASSET_INFO_SUB.PRO_PROPERTY is
    '资管计划属性/委外投资属性 01 集合/02 单一/03 专项/04 定向/05 专户/99 其他';

comment on column CHB_ASSET_INFO_SUB.MAX_RATE is
    '最高预期收益率';

comment on column CHB_ASSET_INFO_SUB.MIN_RATE is
    '最低预期收益率';

comment on column CHB_ASSET_INFO_SUB.DEBT_SCALE is
    '负债规模';

comment on column CHB_ASSET_INFO_SUB.BUY_STRUCT is
    '购买结构 01 优先/02 劣后/03 整体';

comment on column CHB_ASSET_INFO_SUB.MANAGE_TYPE is
    '管理方式 01 主动（自主管理）/02被动（委托管理）/99其他';

comment on column CHB_ASSET_INFO_SUB.IS_CTRL_BOTTOM is
    '是否掌控计划底层资产详情 01 是/ 02 否';

comment on column CHB_ASSET_INFO_SUB.MGR_FEE_RATE is
    '管理费率';

comment on column CHB_ASSET_INFO_SUB.TRU_FEE_RATE is
    '托管费率';

comment on column CHB_ASSET_INFO_SUB.TRADE_FEE_RATE is
    '交易相关合计费率';

comment on column CHB_ASSET_INFO_SUB.AGENCY_FEE_RATE is
    '中介服务机构合计费率';

comment on column CHB_ASSET_INFO_SUB.OTHER_FEE_RATE is
    '其他合计费率';

comment on column CHB_ASSET_INFO_SUB.ASSET_VALUE is
    '资产价值';

comment on column CHB_ASSET_INFO_SUB.REGIST_ORG1 is
    '登记备案机构 01 中央国债登记结算有限责任公司/ 02 上海清算所 / 03 中国证券登记结算有限责任公司 / 04 中国证券投资基金业协会 /99 其他';

comment on column CHB_ASSET_INFO_SUB.MNG_ORG_NAME is
    '基金管理机构名称';

comment on column CHB_ASSET_INFO_SUB.TRU_ORG_NAME is
    '基金托管机构名称';

comment on column CHB_ASSET_INFO_SUB.FUND_INVEST_ASSET is
    '基金投资资产';

comment on column CHB_ASSET_INFO_SUB.IN_ASSET_TYPE is
    '行内资产类别 01 场内股票质押回购/02 场外股票质押融资/03 融资租赁/99 其他';

comment on column CHB_ASSET_INFO_SUB.IN_ASSET_TYPE_CMM is
    '行内资产类别说明';

comment on column CHB_ASSET_INFO_SUB.REMARK is
    '备注';

comment on column CHB_ASSET_INFO_SUB.IS_UNDER_ASSET is
    '是否底层资产  01 是/ 02 否';

comment on column CHB_ASSET_INFO_SUB.IS_GOVERN_FUND is
    '是否属于政府投资基金  01 是/ 02 否';

comment on column CHB_ASSET_INFO_SUB.GOVERN_INVEST_FUND is
    '政府投资基金投向 01 投向创业早期企业/ 02 投向中小微企业/ 03 投向产业转型升级和发展/ 04 投向基础设施和公共服务领域/99 其他';

comment on column CHB_ASSET_INFO_SUB.FUND_ENPE_NAME is
    '基金公司名称';

comment on column CHB_ASSET_INFO_SUB.TRUSTEE is
    '受托人';

comment on column CHB_ASSET_INFO_SUB.SUBMIT_DEADLINE is
    '行内报送截止日';

comment on column CHB_ASSET_INFO_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_ASSET_INFO_SUB.SUBMIT_TIME is
    '报送日期';

comment on column CHB_ASSET_INFO_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_ASSET_INFO_SUB.STATUS is
    '数据状态 01 新建/02 修改/03 删除';

comment on column CHB_ASSET_INFO_SUB.CREATE_USER is
    '创建人';

comment on column CHB_ASSET_INFO_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_ASSET_INFO_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_ASSET_INFO_SUB.UPDATE_TIME is
    '更新时间';

comment on column CHB_ASSET_INFO_SUB.ORG_CODE is
    '机构代码';

comment on column CHB_ASSET_INFO_SUB.DEPT_CODE is
    '部门代码';

comment on column CHB_ASSET_INFO_SUB.SEND_STATUS is
    '报送状态 01 未报送/02 手工处理/03 接口报送';

comment on column CHB_ASSET_INFO_SUB.SUBMIT_FEEDBACK_STATUS is
    '报送反馈状态';

comment on column CHB_ASSET_INFO_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_ASSET_INFO_SUB.DATA_SOURCE is
    '数据来源';

comment on column CHB_ASSET_INFO_SUB.DELETE_RES is
    '删除原因';

comment on column CHB_ASSET_INFO_SUB.CAP_USE_INDU_LEVEL2 is
    '资金运用行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';

comment on column CHB_ASSET_INFO_SUB.ISSUE_PARTY_NAME is
    '发行机构';

create unique index CHB_ASSET_INFO_SUB_PK on
    CHB_ASSET_INFO_SUB (
        REGIST_SEQNO
    asc );

alter table CHB_ASSET_INFO_SUB add constraint CHB_ASSET_INFO_SUB_PK primary key ( REGIST_SEQNO );

create table CHB_ASSET_POSITION (
    POSUUID          char(36 byte) not null,
    PROD_ID          varchar2(50 byte),
    ASSET_CODE       varchar2(50 byte),
    INVEST_TYPE      varchar2(10 byte),
    CHL_ID           varchar2(20 byte),
    DEALAMT          number(30, 2),
    DEALQTY          number(30, 4),
    ACCUAMT          number(30, 2),
    ACCUQTY          number(30, 4),
    CDATE            date,
    ORG_CODE         varchar2(20 byte),
    DEPT_CODE        varchar2(20 byte),
    CREATE_USER      varchar2(20 byte),
    CREATE_TIME      timestamp,
    UPDATE_USER      varchar2(20 byte),
    UPDATE_TIME      timestamp,
    TRADE_TYPE       varchar2(20 byte),
    IS_UNDER_TRADE   char(2 byte),
    INVEST_VARIETY   varchar2(20 byte)
);

comment on table CHB_ASSET_POSITION is
    '资产交易每日头寸';

comment on column CHB_ASSET_POSITION.POSUUID is
    '头寸ID';

comment on column CHB_ASSET_POSITION.PROD_ID is
    '产品代码';

comment on column CHB_ASSET_POSITION.ASSET_CODE is
    '资产代码';

comment on column CHB_ASSET_POSITION.INVEST_TYPE is
    '投资类型';

comment on column CHB_ASSET_POSITION.CHL_ID is
    '通道代码';

comment on column CHB_ASSET_POSITION.DEALAMT is
    '交易金额';

comment on column CHB_ASSET_POSITION.DEALQTY is
    '交易份额';

comment on column CHB_ASSET_POSITION.ACCUAMT is
    '剩余金额';

comment on column CHB_ASSET_POSITION.ACCUQTY is
    '剩余本金';

comment on column CHB_ASSET_POSITION.CDATE is
    '生成日';

comment on column CHB_ASSET_POSITION.ORG_CODE is
    '机构代码';

comment on column CHB_ASSET_POSITION.DEPT_CODE is
    '部门代码';

comment on column CHB_ASSET_POSITION.CREATE_USER is
    '创建人';

comment on column CHB_ASSET_POSITION.CREATE_TIME is
    '创建时间';

comment on column CHB_ASSET_POSITION.UPDATE_USER is
    '更新人';

comment on column CHB_ASSET_POSITION.UPDATE_TIME is
    '更新时间';

comment on column CHB_ASSET_POSITION.TRADE_TYPE is
    '交易类型';

comment on column CHB_ASSET_POSITION.IS_UNDER_TRADE is
    '是否底层交易流水 01 是/ 02 否';

comment on column CHB_ASSET_POSITION.INVEST_VARIETY is
    '投资品种 01债券 02ABS 03计划类资产（利率型）04计划类资产（净值型）05计划类资产（货币型）06存款类资产 07拆借拆入08拆借拆出 09债券质押式正回购 10债券质押式逆回购 11债券买断式正回购 12债券买断式逆回购 13基金类资产（货币型）14基金类资产（净值型）15债权类、权益类 16通道费用 17交易场所费用 18其他费用（他行费用）19理财托管户收息 20其他账户收息 21自定义现金流 22营改增 23银行间费用 24通道交易
'
    ;

create unique index CHB_ASSET_POSITION_PK on
    CHB_ASSET_POSITION (
        POSUUID
    asc );

alter table CHB_ASSET_POSITION add constraint CHB_ASSET_POSITION_PK primary key ( POSUUID );

create table CHB_AST_VAL (
    VAL_ID           char(36 byte) not null,
    ISSUE_PARTY_ID   varchar2(50 byte),
    ASSET_CODE       varchar2(50 byte),
    VAL_DATE         date,
    UNIT_VAL_NET     number(30, 14),
    UNIT_VAL_TOTAL   number(30, 14),
    REMARK           varchar2(1000 byte),
    DATASOURCE       varchar2(20 byte),
    STATUS           varchar2(20 byte),
    WORKFLOW_ID      varchar2(20 byte),
    ORG_CODE         varchar2(20 byte),
    DEPT_CODE        varchar2(20 byte),
    CREATE_USER      varchar2(20 byte),
    CREATE_TIME      timestamp,
    UPDATE_USER      varchar2(20 byte),
    UPDATE_TIME      timestamp
);

comment on table CHB_AST_VAL is
    '资产估值信息表';

comment on column CHB_AST_VAL.VAL_ID is
    '主键';

comment on column CHB_AST_VAL.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_AST_VAL.ASSET_CODE is
    '行内资产/负债编码';

comment on column CHB_AST_VAL.VAL_DATE is
    '估值日期';

comment on column CHB_AST_VAL.UNIT_VAL_NET is
    '单位净价';

comment on column CHB_AST_VAL.UNIT_VAL_TOTAL is
    '单位全价';

comment on column CHB_AST_VAL.REMARK is
    '备注';

comment on column CHB_AST_VAL.DATASOURCE is
    '数据来源';

comment on column CHB_AST_VAL.STATUS is
    '数据状态';

comment on column CHB_AST_VAL.WORKFLOW_ID is
    '工作流代码';

comment on column CHB_AST_VAL.ORG_CODE is
    '机构代码';

comment on column CHB_AST_VAL.DEPT_CODE is
    '部门代码';

comment on column CHB_AST_VAL.CREATE_USER is
    '创建人';

comment on column CHB_AST_VAL.CREATE_TIME is
    '创建时间';

comment on column CHB_AST_VAL.UPDATE_USER is
    '更新人';

comment on column CHB_AST_VAL.UPDATE_TIME is
    '更新时间';

create unique index CHB_AST_VAL on
    CHB_AST_VAL (
        VAL_ID
    asc );

alter table CHB_AST_VAL add constraint CHB_AST_VAL primary key ( VAL_ID );

create table CHB_AST_VAL_SUB (
    REGIST_SEQNO             char(36 byte) not null,
    REGIST_BANK_CODE         varchar2(6 byte),
    ISSUE_PARTY_ID           varchar2(50 byte),
    ASSET_CODE               varchar2(50 byte),
    VAL_DATE                 date,
    UNIT_VAL_NET             number(30, 14),
    UNIT_VAL_TOTAL           number(30, 14),
    REMARK                   varchar2(1000 byte),
    CHB_SUBMIT_DEADLINE      timestamp,
    SUBMIT_DEADLINE          timestamp,
    SEND_STATUS              varchar2(2 byte),
    SUBMIT_TIME              timestamp,
    SUMIT_SEQ                varchar2(20 byte),
    SUBMIT_FEEDBACK_STATUS   varchar2(2 byte),
    STATUS                   varchar2(20 byte),
    STATUS_DATE              date,
    ORG_CODE                 varchar2(20 byte),
    DEPT_CODE                varchar2(20 byte),
    CREATE_USER              varchar2(20 byte),
    CREATE_DEPT              varchar2(20 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp
);

comment on table CHB_AST_VAL_SUB is
    '资产估值信息登记表';

comment on column CHB_AST_VAL_SUB.REGIST_SEQNO is
    '登记序号';

comment on column CHB_AST_VAL_SUB.REGIST_BANK_CODE is
    '银行登记编码';

comment on column CHB_AST_VAL_SUB.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_AST_VAL_SUB.ASSET_CODE is
    '行内资产/负债编码';

comment on column CHB_AST_VAL_SUB.VAL_DATE is
    '估值日期';

comment on column CHB_AST_VAL_SUB.UNIT_VAL_NET is
    '单位净价';

comment on column CHB_AST_VAL_SUB.UNIT_VAL_TOTAL is
    '单位全价';

comment on column CHB_AST_VAL_SUB.REMARK is
    '备注';

comment on column CHB_AST_VAL_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_AST_VAL_SUB.SUBMIT_DEADLINE is
    '行内报送截止日';

comment on column CHB_AST_VAL_SUB.SEND_STATUS is
    '报送状态';

comment on column CHB_AST_VAL_SUB.SUBMIT_TIME is
    '报送日期';

comment on column CHB_AST_VAL_SUB.SUMIT_SEQ is
    '报送流水号';

comment on column CHB_AST_VAL_SUB.SUBMIT_FEEDBACK_STATUS is
    '反馈状态';

comment on column CHB_AST_VAL_SUB.STATUS is
    '数据状态';

comment on column CHB_AST_VAL_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_AST_VAL_SUB.ORG_CODE is
    '机构代码';

comment on column CHB_AST_VAL_SUB.DEPT_CODE is
    '部门代码';

comment on column CHB_AST_VAL_SUB.CREATE_USER is
    '创建人';

comment on column CHB_AST_VAL_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_AST_VAL_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_AST_VAL_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_AST_VAL_SUB.UPDATE_TIME is
    '更新时间';

create unique index CHB_AST_VAL_SUB on
    CHB_AST_VAL_SUB (
        REGIST_SEQNO
    asc );

alter table CHB_AST_VAL_SUB add constraint CHB_AST_VAL_SUB primary key ( REGIST_SEQNO );

create table CHB_BOT_AST_POS (
    POS_ID           char(36 byte) not null,
    ISSUE_PARTY_ID   varchar2(50 byte),
    POS_DATE         date,
    PLAN_ASSET_ID    varchar2(50 byte),
    ASSET_CODE       varchar2(50 byte),
    TOTAL_NUM        number(20),
    ISSUE_AMT        number(30, 2),
    UNINVEST_CASH    number(30, 2),
    POS_NUM          number(20),
    POS_AMT          number(30, 2),
    ISFINAPLAN       char(2 byte),
    ISCHL            char(2 byte),
    WORKFLOW_ID      varchar2(20 byte),
    STATUS           varchar2(20 byte),
    ORG_CODE         varchar2(20 byte),
    DEPT_CODE        varchar2(20 byte),
    CREATE_USER      varchar2(20 byte),
    CREATE_DEPT      varchar2(20 byte),
    CREATE_TIME      timestamp,
    UPDATE_USER      varchar2(20 byte),
    UPDATE_TIME      timestamp,
    DATASOURCE       varchar2(50 byte)
);

comment on table CHB_BOT_AST_POS is
    '底层资产持仓信息表';

comment on column CHB_BOT_AST_POS.POS_ID is
    '主键';

comment on column CHB_BOT_AST_POS.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_BOT_AST_POS.POS_DATE is
    '持仓日期';

comment on column CHB_BOT_AST_POS.PLAN_ASSET_ID is
    '资管计划行内资产/负债编码';

comment on column CHB_BOT_AST_POS.ASSET_CODE is
    '行内资产/负债编码';

comment on column CHB_BOT_AST_POS.TOTAL_NUM is
    '总发行数量';

comment on column CHB_BOT_AST_POS.ISSUE_AMT is
    '人民币发行金额';

comment on column CHB_BOT_AST_POS.UNINVEST_CASH is
    '未投资头寸';

comment on column CHB_BOT_AST_POS.POS_NUM is
    '持仓数量';

comment on column CHB_BOT_AST_POS.POS_AMT is
    '底层资产折算人民币币值（元）';

comment on column CHB_BOT_AST_POS.ISFINAPLAN is
    '是否资管计划 01是 02 否';

comment on column CHB_BOT_AST_POS.ISCHL is
    '是否通过通道 01是 02 否';

comment on column CHB_BOT_AST_POS.WORKFLOW_ID is
    '工作流代码';

comment on column CHB_BOT_AST_POS.STATUS is
    '数据状态';

comment on column CHB_BOT_AST_POS.ORG_CODE is
    '机构代码';

comment on column CHB_BOT_AST_POS.DEPT_CODE is
    '部门代码';

comment on column CHB_BOT_AST_POS.CREATE_USER is
    '创建人';

comment on column CHB_BOT_AST_POS.CREATE_DEPT is
    '创建部门';

comment on column CHB_BOT_AST_POS.CREATE_TIME is
    '创建时间';

comment on column CHB_BOT_AST_POS.UPDATE_USER is
    '更新人';

comment on column CHB_BOT_AST_POS.UPDATE_TIME is
    '更新时间';

comment on column CHB_BOT_AST_POS.DATASOURCE is
    '数据来源';

create unique index CHB_BOT_AST_POS on
    CHB_BOT_AST_POS (
        POS_ID
    asc );

alter table CHB_BOT_AST_POS add constraint CHB_BOT_AST_POS primary key ( POS_ID );

create table CHB_BOT_AST_POS_SUB (
    REGIST_SEQNO             char(36 byte) not null,
    REGIST_BANK_CODE         varchar2(6 byte),
    ISSUE_PARTY_ID           varchar2(50 byte),
    POS_DATE                 date,
    PLAN_ASSET_ID            varchar2(50 byte),
    ASSET_CODE               varchar2(50 byte),
    TOTAL_NUM                number(20),
    ISSUE_AMT                number(30, 2),
    UNINVEST_CASH            number(30, 2),
    POS_NUM                  number(20),
    POS_AMT                  number(30, 2),
    SEND_STATUS              varchar2(2 byte),
    CHB_SUBMIT_DEADLINE      timestamp,
    SUBMIT_DEADLINE          timestamp,
    SUBMIT_TIME              timestamp,
    SUMIT_SEQ                varchar2(20 byte),
    SUBMIT_FEEDBACK_STATUS   varchar2(2 byte),
    STATUS                   varchar2(20 byte),
    STATUS_DATE              date,
    ORG_CODE                 varchar2(20 byte),
    DEPT_CODE                varchar2(20 byte),
    CREATE_USER              varchar2(20 byte),
    CREATE_DEPT              varchar2(20 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp
);

comment on table CHB_BOT_AST_POS_SUB is
    '底层资产持仓信息登记表';

comment on column CHB_BOT_AST_POS_SUB.REGIST_SEQNO is
    '登记序号';

comment on column CHB_BOT_AST_POS_SUB.REGIST_BANK_CODE is
    '银行登记编码';

comment on column CHB_BOT_AST_POS_SUB.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_BOT_AST_POS_SUB.POS_DATE is
    '持仓日期';

comment on column CHB_BOT_AST_POS_SUB.PLAN_ASSET_ID is
    '资管计划代码(对应资产管理计划行内资产/负债编码)';

comment on column CHB_BOT_AST_POS_SUB.ASSET_CODE is
    '行内资产/负债编码';

comment on column CHB_BOT_AST_POS_SUB.TOTAL_NUM is
    '资管计划当前总发行数量';

comment on column CHB_BOT_AST_POS_SUB.ISSUE_AMT is
    '资管计划当前总折算人民币金额（元）';

comment on column CHB_BOT_AST_POS_SUB.UNINVEST_CASH is
    '资管计划折算人民币未投资头寸（元）';

comment on column CHB_BOT_AST_POS_SUB.POS_NUM is
    '底层资产持仓数量';

comment on column CHB_BOT_AST_POS_SUB.POS_AMT is
    '底层资产折算人民币币值（元）';

comment on column CHB_BOT_AST_POS_SUB.SEND_STATUS is
    '报送状态';

comment on column CHB_BOT_AST_POS_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_BOT_AST_POS_SUB.SUBMIT_DEADLINE is
    '行内报送截止日';

comment on column CHB_BOT_AST_POS_SUB.SUBMIT_TIME is
    '报送日期';

comment on column CHB_BOT_AST_POS_SUB.SUMIT_SEQ is
    '报送流水号';

comment on column CHB_BOT_AST_POS_SUB.SUBMIT_FEEDBACK_STATUS is
    '反馈状态';

comment on column CHB_BOT_AST_POS_SUB.STATUS is
    '数据状态';

comment on column CHB_BOT_AST_POS_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_BOT_AST_POS_SUB.ORG_CODE is
    '机构代码';

comment on column CHB_BOT_AST_POS_SUB.DEPT_CODE is
    '部门代码';

comment on column CHB_BOT_AST_POS_SUB.CREATE_USER is
    '创建人';

comment on column CHB_BOT_AST_POS_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_BOT_AST_POS_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_BOT_AST_POS_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_BOT_AST_POS_SUB.UPDATE_TIME is
    '更新时间';

create unique index CHB_BOT_AST_POS_SUB on
    CHB_BOT_AST_POS_SUB (
        REGIST_SEQNO
    asc );

alter table CHB_BOT_AST_POS_SUB add constraint CHB_BOT_AST_POS_SUB primary key ( REGIST_SEQNO );

create table CHB_INVEST_TRADE (
    TRADE_SEQNO         char(36 byte) not null,
    TRADE_CODE          varchar2(50 byte),
    INVEST_VARIETY      varchar2(50 byte),
    INVEST_TYPE         varchar2(50 byte),
    PROD_ID             varchar2(50 byte),
    REGIST_BANK_CODE    varchar2(50 byte),
    ASSET_CODE          varchar2(50 byte),
    CCY                 varchar2(50 byte),
    AMT                 number(30, 2),
    CNY_AMT             number(30, 2),
    NUM                 number(30, 4),
    CUR_ASSET_TYPE      varchar2(50 byte),
    CUR_ASSETDET_TYPE   varchar2(50 byte),
    DEALDATE            date,
    TRADE_COUNTER       varchar2(60 byte),
    UNIT_PRICE_FULL     number(30, 14),
    UNIT_PRICE_NET      number(30, 14),
    END_RATE            number(30, 14),
    CONTRACT_CODE       varchar2(50 byte),
    REMARK              varchar2(1000 byte),
    CHL_CPCT_ID         varchar2(50 byte),
    TRADE_TYPE          varchar2(50 byte),
    CHL_AGRT_NAME       varchar2(200 byte),
    IS_UNDER_TRADE      varchar2(50 byte),
    STATUS              varchar2(50 byte),
    ORG_CODE            varchar2(20 byte),
    DEPT_CODE           varchar2(20 byte),
    CREATE_USER         varchar2(20 byte),
    CREATE_TIME         timestamp,
    UPDATE_USER         varchar2(20 byte),
    UPDATE_TIME         timestamp,
    STATUS_DATE         date,
    REV_DATE            date,
    DATA_SOURCE         varchar2(100 byte),
    RATE                number(30, 14),
    BASIS               varchar2(50 byte),
    DEALTYPE            varchar2(50 byte),
    FACEVALUE           NUMBER(30, 14)
);

comment on table CHB_INVEST_TRADE is
    '资产交易表';

comment on column CHB_INVEST_TRADE.TRADE_SEQNO is
    '交易UUID';

comment on column CHB_INVEST_TRADE.TRADE_CODE is
    '行内交易编码';

comment on column CHB_INVEST_TRADE.INVEST_VARIETY is
    '投资品种 01债券 02ABS 03计划类资产（利率型）04计划类资产（净值型）05计划类资产（货币型）06存款类资产 07拆借拆入08拆借拆出 09债券质押式正回购 10债券质押式逆回购 11债券买断式正回购 12债券买断式逆回购 13基金类资产（货币型）14基金类资产（净值型）15债权类、权益类 16通道费用 17交易场所费用 18其他费用（他行费用）19理财托管户收息 20其他账户收息 21自定义现金流 22营改增 23银行间费用 24通道交易'
    ;

comment on column CHB_INVEST_TRADE.INVEST_TYPE is
    '投资类型AFS 可供出售 TRA 交易性 HTM 持有到期 SRM应收款项';

comment on column CHB_INVEST_TRADE.PROD_ID is
    '产品代码';

comment on column CHB_INVEST_TRADE.REGIST_BANK_CODE is
    '发行机构代码';

comment on column CHB_INVEST_TRADE.ASSET_CODE is
    '行内资产/负债编码';

comment on column CHB_INVEST_TRADE.CCY is
    '币种 参考数据字典：CURRENCY_CHB';

comment on column CHB_INVEST_TRADE.AMT is
    '发生金额';

comment on column CHB_INVEST_TRADE.CNY_AMT is
    '折算人民币金额';

comment on column CHB_INVEST_TRADE.NUM is
    '数量';

comment on column CHB_INVEST_TRADE.CUR_ASSET_TYPE is
    '资金流动类型 01 资产买入/ 02 资产卖出或到期/ 03 存入银行存款/ 04 银行存款到期/ 05拆放同业和买入返售首期/ 06同业拆入和卖出回购首期/ 07拆放同业和买入返售到期/ 08同业拆入和卖出回购到期/09投资资产所得收益/ 10 投资资产支出/ 11 其他收入/ 12 其他支出'
    ;

comment on column CHB_INVEST_TRADE.CUR_ASSETDET_TYPE is
    '资金流动类型说明';

comment on column CHB_INVEST_TRADE.DEALDATE is
    '交易日';

comment on column CHB_INVEST_TRADE.TRADE_COUNTER is
    '交易对手';

comment on column CHB_INVEST_TRADE.UNIT_PRICE_FULL is
    '单位成交价格（全价）';

comment on column CHB_INVEST_TRADE.UNIT_PRICE_NET is
    '单位成交价格（净价）';

comment on column CHB_INVEST_TRADE.END_RATE is
    '到期收益率';

comment on column CHB_INVEST_TRADE.CONTRACT_CODE is
    '成交编号/合同号';

comment on column CHB_INVEST_TRADE.REMARK is
    '备注';

comment on column CHB_INVEST_TRADE.CHL_CPCT_ID is
    '通道代码';

comment on column CHB_INVEST_TRADE.TRADE_TYPE is
    '交易类型01 资产交易 02通道交易 03产品费用交易 04其他交易 05资管计划交易  06通道费用交易';

comment on column CHB_INVEST_TRADE.CHL_AGRT_NAME is
    '通道名称';

comment on column CHB_INVEST_TRADE.IS_UNDER_TRADE is
    '是否底层交易流水 01 是/ 02 否';

comment on column CHB_INVEST_TRADE.STATUS is
    '数据状态 D	删除 A	待处理 E	有效';

comment on column CHB_INVEST_TRADE.ORG_CODE is
    '机构代码';

comment on column CHB_INVEST_TRADE.DEPT_CODE is
    '部门代码';

comment on column CHB_INVEST_TRADE.CREATE_USER is
    '创建人';

comment on column CHB_INVEST_TRADE.CREATE_TIME is
    '创建时间';

comment on column CHB_INVEST_TRADE.UPDATE_USER is
    '更新人';

comment on column CHB_INVEST_TRADE.UPDATE_TIME is
    '更新时间';

comment on column CHB_INVEST_TRADE.STATUS_DATE is
    '数据日期';

comment on column CHB_INVEST_TRADE.REV_DATE is
    '撤销日';

comment on column CHB_INVEST_TRADE.DATA_SOURCE is
    '数据来源';

comment on column CHB_INVEST_TRADE.RATE is
    '利率';

comment on column CHB_INVEST_TRADE.BASIS is
    '计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360';

COMMENT ON COLUMN CHB_INVEST_TRADE.DEALTYPE IS 
    '交易类型01  到期-本金/02    到期-利息/03    份额调减/04 份额调增/05 红利再投/06 交易场所费用支出/07 理财托管户收息确认/08    买入/09   卖出/10   其他费用支出/11   其他账户收息确认/12 申购/13   自定义现金流收入/14 自定义现金流支出/15 赎回/16   通道费用支付/17   通道交易-买入/18  通道交易-卖出/19  通道托管（管理）费/20    现金分红/21 银行间费用支出/22  营改增缴纳/23    中间付息/24 中间还本-本金/25  中间还本-利息';

COMMENT ON COLUMN CHB_INVEST_TRADE.FACEVALUE IS '面值';

create unique index CHB_INVEST_TRADE_PK on
    CHB_INVEST_TRADE (
        TRADE_SEQNO
    asc );

alter table CHB_INVEST_TRADE add constraint CHB_INVEST_TRADE_PK primary key ( TRADE_SEQNO );

create table CHB_INVEST_TRADE_SUB (
    REGIST_SEQNO             char(36 byte) not null,
    TRADE_CODE               varchar2(50 byte),
    PROD_ID                  varchar2(50 byte),
    REGIST_BANK_CODE         varchar2(50 byte),
    ASSET_CODE               varchar2(50 byte),
    CCY                      varchar2(50 byte),
    CNY_AMT                  number(30, 2),
    AMT                      number(30, 2),
    NUM                      number(30, 4),
    CUR_ASSET_TYPE           varchar2(50 byte),
    CUR_ASSETDET_TYPE        varchar2(50 byte),
    DEALDATE                 date,
    TRADE_COUNTER            varchar2(60 byte),
    UNIT_PRICE_FULL          number(30, 14),
    UNIT_PRICE_NET           number(30, 14),
    END_RATE                 number(30, 14),
    CONTRACT_CODE            varchar2(50 byte),
    REMARK                   varchar2(1000 byte),
    CHL_CPCT_ID              varchar2(50 byte),
    TRADE_TYPE               varchar2(50 byte),
    CHL_AGRT_NAME            varchar2(200 byte),
    FEE_ID                   varchar2(50 byte),
    FEE_NAME                 varchar2(50 byte),
    FEE_TYPE                 varchar2(50 byte),
    FEE_SECTYPE              varchar2(50 byte),
    STATUS                   varchar2(50 byte),
    ORG_CODE                 varchar2(20 byte),
    DEPT_CODE                varchar2(20 byte),
    CREATE_USER              varchar2(20 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp,
    CHB_SUBMIT_DEADLINE      DATE,
    SUBMIT_DEADLINE          DATE,
    SEND_STATUS              varchar2(50 byte),
    SUBMIT_FEEDBACK_STATUS   varchar2(50 byte),
    STATUS_DATE              date,
    SUBMIT_TIME              timestamp,
    DELETE_RES               varchar2(150 byte),
    DATA_SOURCE              varchar2(100 byte),
    CREATE_DEPT              varchar2(32 byte),
    DEAL_REGIST_DATE         date,
    IS_ASSET_SUB             varchar2(50 byte),
    REF_TRADESEQNO           char(36 byte)
);

comment on table CHB_INVEST_TRADE_SUB is
    '投资交易表登记';

comment on column CHB_INVEST_TRADE_SUB.REGIST_SEQNO is
    '登记序号';

comment on column CHB_INVEST_TRADE_SUB.TRADE_CODE is
    '行内交易编码';

comment on column CHB_INVEST_TRADE_SUB.PROD_ID is
    '产品代码';

comment on column CHB_INVEST_TRADE_SUB.REGIST_BANK_CODE is
    '发行机构代码';

comment on column CHB_INVEST_TRADE_SUB.ASSET_CODE is
    '行内资产/负债编码';

comment on column CHB_INVEST_TRADE_SUB.CCY is
    '币种 参考数据字典：CURRENCY_CHB';

comment on column CHB_INVEST_TRADE_SUB.CNY_AMT is
    '折算人民币金额';

comment on column CHB_INVEST_TRADE_SUB.AMT is
    '发生金额';

comment on column CHB_INVEST_TRADE_SUB.NUM is
    '数量';

comment on column CHB_INVEST_TRADE_SUB.CUR_ASSET_TYPE is
    '资金流动类型 01 资产买入/ 02 资产卖出或到期/ 03 存入银行存款/ 04 银行存款到期/ 05拆放同业和买入返售首期/ 06同业拆入和卖出回购首期/ 07拆放同业和买入返售到期/ 08同业拆入和卖出回购到期/09投资资产所得收益/ 10 投资资产支出/ 11 其他收入/ 12 其他支出'
    ;

comment on column CHB_INVEST_TRADE_SUB.CUR_ASSETDET_TYPE is
    '资金流动类型说明';

comment on column CHB_INVEST_TRADE_SUB.DEALDATE is
    '交易日';

comment on column CHB_INVEST_TRADE_SUB.TRADE_COUNTER is
    '交易对手';

comment on column CHB_INVEST_TRADE_SUB.UNIT_PRICE_FULL is
    '单位成交价格（全价）';

comment on column CHB_INVEST_TRADE_SUB.UNIT_PRICE_NET is
    '单位成交价格（净价）';

comment on column CHB_INVEST_TRADE_SUB.END_RATE is
    '到期收益率';

comment on column CHB_INVEST_TRADE_SUB.CONTRACT_CODE is
    '成交编号/合同号';

comment on column CHB_INVEST_TRADE_SUB.REMARK is
    '备注';

comment on column CHB_INVEST_TRADE_SUB.CHL_CPCT_ID is
    '通道代码';

comment on column CHB_INVEST_TRADE_SUB.TRADE_TYPE is
    '交易类型01 资产交易 02通道交易 03产品费用交易 04其他交易 05资管计划交易 06通道费用交易';

comment on column CHB_INVEST_TRADE_SUB.CHL_AGRT_NAME is
    '通道名称';

comment on column CHB_INVEST_TRADE_SUB.FEE_ID is
    '费用代码';

comment on column CHB_INVEST_TRADE_SUB.FEE_NAME is
    '费用名称';

comment on column CHB_INVEST_TRADE_SUB.FEE_TYPE is
    '费用类型 01:本机构费用 02:其他机构费用';

comment on column CHB_INVEST_TRADE_SUB.FEE_SECTYPE is
    '费用二级类型01:本机构托管费 02:本机构管理费 03:本机构销售手续费 04:本机构其他产品费用 05:其他机构托管费 06:其他机构管理费 07:其他机构销售手续费 08:投资顾问费用 09:其他机构其他产品费用 10：中收费 11：浮动费'
    ;

comment on column CHB_INVEST_TRADE_SUB.STATUS is
    '数据状态 01 新建/02 修改/03 删除';

comment on column CHB_INVEST_TRADE_SUB.ORG_CODE is
    '机构代码';

comment on column CHB_INVEST_TRADE_SUB.DEPT_CODE is
    '部门代码';

comment on column CHB_INVEST_TRADE_SUB.CREATE_USER is
    '创建人';

comment on column CHB_INVEST_TRADE_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_INVEST_TRADE_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_INVEST_TRADE_SUB.UPDATE_TIME is
    '更新时间';

comment on column CHB_INVEST_TRADE_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_INVEST_TRADE_SUB.SUBMIT_DEADLINE is
    '行内报送截止日';

comment on column CHB_INVEST_TRADE_SUB.SEND_STATUS is
    '报送状态 01 未报送/03 手工处理/02 接口报送';

comment on column CHB_INVEST_TRADE_SUB.SUBMIT_FEEDBACK_STATUS is
    '报送反馈状态 01 发送成功 02 发送失败 03 中债通过 04 中债退回 05 审核文件下载失败';

comment on column CHB_INVEST_TRADE_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_INVEST_TRADE_SUB.SUBMIT_TIME is
    '报送日期';

comment on column CHB_INVEST_TRADE_SUB.DELETE_RES is
    '删除原因';

comment on column CHB_INVEST_TRADE_SUB.DATA_SOURCE is
    '数据来源';

comment on column CHB_INVEST_TRADE_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_INVEST_TRADE_SUB.DEAL_REGIST_DATE is
    '交易登记日';

comment on column CHB_INVEST_TRADE_SUB.IS_ASSET_SUB is
    '资产是否已登记 01 是/ 02 否';

comment on column CHB_INVEST_TRADE_SUB.REF_TRADESEQNO is
    '关联交易主键（投资交易、产品费用交易）';

create unique index CHB_INVEST_TRADE_SUB_PK on
    CHB_INVEST_TRADE_SUB (
        REGIST_SEQNO
    asc );

alter table CHB_INVEST_TRADE_SUB add constraint CHB_INVEST_TRADE_SUB_PK primary key ( REGIST_SEQNO );

create table CHB_MONTH_ASSET_POSITION (
    POSUUID            char(36 byte) not null,
    BOOKDATE           date,
    STARTDATE          date,
    ENDDATE            date,
    INVEST_VARIETY     varchar2(4 byte),
    ASSET_CODE         varchar2(50 byte),
    ASSETTYPE          varchar2(4 byte) not null,
    PROD_ID            varchar2(50 byte),
    ASSETVALUE_A       number(28, 6),
    ASSETVALUE_B       number(28, 6),
    INDUSTRY1          varchar2(4 byte),
    IN_ASSET_TYPE      varchar2(2 byte),
    PPPFLAG            varchar2(4 byte),
    MONIT_TYPE         varchar2(2 byte),
    MANAGE_TYPE        varchar2(2 byte),
    IS_GOVERN_FUND1    varchar2(2 byte),
    PRIVATEFUND_FLAG   varchar2(2 byte),
    PRIVATEPRD_FLAG    varchar2(2 byte),
    BONDTOSTOCK_FLAG   varchar2(2 byte),
    RATING             varchar2(4 byte),
    ASSETTYPE2         varchar2(4 byte),
    ORG_CODE           varchar2(20 byte),
    DEPT_CODE          varchar2(20 byte)
);

comment on table CHB_MONTH_ASSET_POSITION is
    '月度报表资产头寸';

comment on column CHB_MONTH_ASSET_POSITION.POSUUID is
    '头寸编号';

comment on column CHB_MONTH_ASSET_POSITION.BOOKDATE is
    '统计日期';

comment on column CHB_MONTH_ASSET_POSITION.STARTDATE is
    '开始日期';

comment on column CHB_MONTH_ASSET_POSITION.ENDDATE is
    '结束日期';

comment on column CHB_MONTH_ASSET_POSITION.INVEST_VARIETY is
    '投资品种';

comment on column CHB_MONTH_ASSET_POSITION.ASSET_CODE is
    '资产ID';

comment on column CHB_MONTH_ASSET_POSITION.ASSETTYPE is
    '资产一级分类';

comment on column CHB_MONTH_ASSET_POSITION.PROD_ID is
    '产品代码';

comment on column CHB_MONTH_ASSET_POSITION.ASSETVALUE_A is
    '穿透前资产价值';

comment on column CHB_MONTH_ASSET_POSITION.ASSETVALUE_B is
    '穿透后资产价值';

comment on column CHB_MONTH_ASSET_POSITION.INDUSTRY1 is
    '所属行业';

comment on column CHB_MONTH_ASSET_POSITION.IN_ASSET_TYPE is
    '行类一级分类';

comment on column CHB_MONTH_ASSET_POSITION.PPPFLAG is
    '公共PPP';

comment on column CHB_MONTH_ASSET_POSITION.MONIT_TYPE is
    '是否投向地方政府平台';

comment on column CHB_MONTH_ASSET_POSITION.MANAGE_TYPE is
    '管理方式';

comment on column CHB_MONTH_ASSET_POSITION.IS_GOVERN_FUND1 is
    '是否政府投资基金';

comment on column CHB_MONTH_ASSET_POSITION.PRIVATEFUND_FLAG is
    '是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金';

comment on column CHB_MONTH_ASSET_POSITION.PRIVATEPRD_FLAG is
    '是否投向金融资产投资公司发行的私募资产管理产品';

comment on column CHB_MONTH_ASSET_POSITION.BONDTOSTOCK_FLAG is
    '是否投向市场化债转股相关产品的部分';

comment on column CHB_MONTH_ASSET_POSITION.RATING is
    '评级结果';

comment on column CHB_MONTH_ASSET_POSITION.ASSETTYPE2 is
    '资产二级分类';
    
comment on column CHB_MONTH_ASSET_POSITION.ORG_CODE is
    '机构代码';
    
comment on column CHB_MONTH_ASSET_POSITION.DEPT_CODE is
    '部门代码';
            
              
create unique index CHB_MONTH_ASSET_POSITION_PK on
    CHB_MONTH_ASSET_POSITION (
        POSUUID
    asc );

alter table CHB_MONTH_ASSET_POSITION add constraint CHB_MONTH_ASSET_POSITION_PK primary key ( POSUUID );

create table CHB_MONTH_FEE_POSITION (
    PRODID            varchar2(50 byte),
    STARTDATE         date,
    ENDDATE           date,
    BOOKDATE          date,
    CCY               varchar2(3 byte),
    RAISEMODE         varchar2(2 byte),
    IVSTORIENTATION   varchar2(2 byte),
    BANKAMT           number(24, 2),
    TRUSTFEEAMT       number(24, 2),
    SALEFEEAMT        number(24, 2),
    OTHERFEEAMT       number(24, 2),
    MANAFEEAMT        number(24, 2),
    FLOATFEEAMT       number(24, 2),
    PROFIT_TYPE       varchar2(2 byte),
    CON_PRD_TRAGET    varchar2(2 byte),
    POSUUID           char(36 byte) not null,
    ORG_CODE           varchar2(20 byte),
    DEPT_CODE          varchar2(20 byte)
);

comment on table CHB_MONTH_FEE_POSITION is
    '月度报表产品头寸';

comment on column CHB_MONTH_FEE_POSITION.PRODID is
    '产品代码';

comment on column CHB_MONTH_FEE_POSITION.STARTDATE is
    '报表开始日期';

comment on column CHB_MONTH_FEE_POSITION.ENDDATE is
    '报表结束日期';

comment on column CHB_MONTH_FEE_POSITION.BOOKDATE is
    '统计日期';

comment on column CHB_MONTH_FEE_POSITION.CCY is
    '币种CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种';

comment on column CHB_MONTH_FEE_POSITION.RAISEMODE is
    '募集方式01：公募 02：私募';

comment on column CHB_MONTH_FEE_POSITION.IVSTORIENTATION is
    '投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类';

comment on column CHB_MONTH_FEE_POSITION.BANKAMT is
    '本期银行实现收益总额';

comment on column CHB_MONTH_FEE_POSITION.TRUSTFEEAMT is
    '本期本机构托管费';

comment on column CHB_MONTH_FEE_POSITION.SALEFEEAMT is
    '本期本机构销售服务费';

comment on column CHB_MONTH_FEE_POSITION.OTHERFEEAMT is
    '本期本机构其他费用';

comment on column CHB_MONTH_FEE_POSITION.MANAFEEAMT is
    '本期本机构固定管理';

comment on column CHB_MONTH_FEE_POSITION.FLOATFEEAMT is
    '本期浮动费/中收';

comment on column CHB_MONTH_FEE_POSITION.PROFIT_TYPE is
    '产品收益标识 01 保证收益/02 保本浮动收益/03 非保本浮动收益';

comment on column CHB_MONTH_FEE_POSITION.CON_PRD_TRAGET is
    '结构性产品挂钩标的';
    
comment on column CHB_MONTH_FEE_POSITION.ORG_CODE is
    '机构代码';
    
comment on column CHB_MONTH_FEE_POSITION.DEPT_CODE is
    '部门代码';
    
create unique index CHB_MONTH_FEE_POSITION_PK on
    CHB_MONTH_FEE_POSITION (
        POSUUID
    asc );

alter table CHB_MONTH_FEE_POSITION add constraint CHB_MONTH_FEE_POSITION_PK primary key ( POSUUID );

create table CHB_MONTH_PRD_POSITION (
    PRODID              varchar2(50 byte),
    STARTDATE           date,
    ENDDATE             date,
    BOOKDATE            date,
    CCY                 varchar2(3 byte),
    RAISEMODE           varchar2(2 byte),
    IVSTORIENTATION     varchar2(2 byte),
    TERMTYPE            varchar2(20 byte),
    OPERATE_MODE        varchar2(2 byte),
    PROFIT_TYPE         varchar2(2 byte),
    SALE_TARGET         varchar2(50 byte),
    CON_PRD_TRAGET      varchar2(2 byte),
    ORD_IDENTIFIED      varchar2(2 byte),
    DIRTYAMTSUM         number(24, 2),
    CLEANAMTSUM         number(24, 2),
    REDEEMAMT           number(24, 2),
    TERMINATIONAMT      number(24, 2),
    ENDAMTSUM_OTH       number(24, 2),
    CUSTOMERAMT         number(24, 2),
    REDEEMINTAMT        number(24, 2),
    TERMINATIONINTAMT   number(24, 2),
    CASHAMT             number(24, 2),
    DURATIONFLAG        varchar2(2 byte),
    VDATE               date,
    POSUUID             char(36 byte) not null,
    OPENDURATION        varchar2(2 byte),
    ORG_CODE           varchar2(20 byte),
    DEPT_CODE          varchar2(20 byte)
);

comment on table CHB_MONTH_PRD_POSITION is
    '月度报表产品头寸';

comment on column CHB_MONTH_PRD_POSITION.PRODID is
    '产品代码';

comment on column CHB_MONTH_PRD_POSITION.STARTDATE is
    '报表开始日期';

comment on column CHB_MONTH_PRD_POSITION.ENDDATE is
    '报表结束日期';

comment on column CHB_MONTH_PRD_POSITION.BOOKDATE is
    '统计日期';

comment on column CHB_MONTH_PRD_POSITION.CCY is
    '币种 CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种';

comment on column CHB_MONTH_PRD_POSITION.RAISEMODE is
    '募集方式 01：公募 02：私募';

comment on column CHB_MONTH_PRD_POSITION.IVSTORIENTATION is
    '投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类';

comment on column CHB_MONTH_PRD_POSITION.TERMTYPE is
    '期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上';

comment on column CHB_MONTH_PRD_POSITION.OPERATE_MODE is
    '运行方式01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型';

comment on column CHB_MONTH_PRD_POSITION.PROFIT_TYPE is
    '产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益';

comment on column CHB_MONTH_PRD_POSITION.SALE_TARGET is
    '客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户';

comment on column CHB_MONTH_PRD_POSITION.CON_PRD_TRAGET is
    '结构性产品挂钩标的';

comment on column CHB_MONTH_PRD_POSITION.ORD_IDENTIFIED is
    '投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构';

comment on column CHB_MONTH_PRD_POSITION.DIRTYAMTSUM is
    '本期总募集金额';

comment on column CHB_MONTH_PRD_POSITION.CLEANAMTSUM is
    '本期净募集金额';

comment on column CHB_MONTH_PRD_POSITION.REDEEMAMT is
    '本期赎回本金';

comment on column CHB_MONTH_PRD_POSITION.TERMINATIONAMT is
    '提前终止/到期本金';

comment on column CHB_MONTH_PRD_POSITION.ENDAMTSUM_OTH is
    '期末余额';

comment on column CHB_MONTH_PRD_POSITION.CUSTOMERAMT is
    '本期客户实现收益总额';

comment on column CHB_MONTH_PRD_POSITION.REDEEMINTAMT is
    '本期赎回利息';

comment on column CHB_MONTH_PRD_POSITION.TERMINATIONINTAMT is
    '本期提前终止/到期 利息';

comment on column CHB_MONTH_PRD_POSITION.CASHAMT is
    '本期现金分红/中间付息';

comment on column CHB_MONTH_PRD_POSITION.DURATIONFLAG is
    '存续状态 01存续/02终止/03未起息';

comment on column CHB_MONTH_PRD_POSITION.VDATE is
    '起息日';

comment on column CHB_MONTH_PRD_POSITION.OPENDURATION is
    '开放期限 01每日/02 180天以上/03 其他';
    
comment on column CHB_MONTH_PRD_POSITION.ORG_CODE is
    '机构代码';
    
comment on column CHB_MONTH_PRD_POSITION.DEPT_CODE is
    '部门代码';
    
create unique index CHB_MONTH_PRD_POSITION_PK on
    CHB_MONTH_PRD_POSITION (
        POSUUID
    asc );

alter table CHB_MONTH_PRD_POSITION add constraint CHB_MONTH_PRD_POSITION_PK primary key ( POSUUID );

create table CHB_MONTH_REPORT (
    TERMID                   varchar2(20 byte),
    REPORTTYPE               varchar2(20 byte),
    STARTDATE                date,
    ENDDATE                  date,
    ISSUE_PARTY_ID           varchar2(50 byte),
    ISSUE_PARTY_NAME         varchar2(50 byte),
    REPORTFREQU              varchar2(2 byte),
    SUBMIT_TIME              date,
    CHB_SUBMIT_DEADLINE      date,
    SUBMIT_DEADLINE          date,
    SUBMIT_FEEDBACK_STATUS   varchar2(50 byte),
    STATUS                   varchar2(50 byte),
    REPORTUUID               char(36 byte) not null,
    SEND_STATUS              varchar2(20 byte),
    STATUS_DATE              date,
    CREATE_USER              varchar2(20 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp,
    ORG_CODE                 varchar2(20 byte),
    DEPT_CODE                varchar2(20 byte),
    CREATE_DEPT              varchar2(32 byte)
);

comment on table CHB_MONTH_REPORT is
    '月报表';

comment on column CHB_MONTH_REPORT.TERMID is
    '期数';

comment on column CHB_MONTH_REPORT.REPORTTYPE is
    '报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款';

comment on column CHB_MONTH_REPORT.STARTDATE is
    '开始日期';

comment on column CHB_MONTH_REPORT.ENDDATE is
    '结束日期';

comment on column CHB_MONTH_REPORT.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_MONTH_REPORT.ISSUE_PARTY_NAME is
    '发行机构名称';

comment on column CHB_MONTH_REPORT.REPORTFREQU is
    '报表频率';

comment on column CHB_MONTH_REPORT.SUBMIT_TIME is
    '报送日期';

comment on column CHB_MONTH_REPORT.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_MONTH_REPORT.SUBMIT_DEADLINE is
    '行内报送截止日';

comment on column CHB_MONTH_REPORT.SUBMIT_FEEDBACK_STATUS is
    '返回信息';

comment on column CHB_MONTH_REPORT.STATUS is
    '有效状态';

comment on column CHB_MONTH_REPORT.SEND_STATUS is
    '报送状态';

comment on column CHB_MONTH_REPORT.STATUS_DATE is
    '数据日期';

comment on column CHB_MONTH_REPORT.CREATE_USER is
    '创建人';

comment on column CHB_MONTH_REPORT.CREATE_TIME is
    '创建时间';

comment on column CHB_MONTH_REPORT.UPDATE_USER is
    '更新人';

comment on column CHB_MONTH_REPORT.UPDATE_TIME is
    '更新时间';

comment on column CHB_MONTH_REPORT.ORG_CODE is
    '机构代码';

comment on column CHB_MONTH_REPORT.DEPT_CODE is
    '部门代码';

comment on column CHB_MONTH_REPORT.CREATE_DEPT is
    '创建部门';

create unique index CHB_MONTH_REPORT_PK1 on
    CHB_MONTH_REPORT (
        REPORTUUID
    asc );

alter table CHB_MONTH_REPORT add constraint CHB_MONTH_REPORT_PK primary key ( REPORTUUID );

create table CHB_MONTH_REPORT_DETAIL (
    TERMID               varchar2(20 byte),
    REPORTTYPE           varchar2(10 byte),
    PROPERTYCODE         varchar2(20 byte),
    PRODCOUNTS           number(10),
    PRODAMOUNT           number(24, 6),
    PRODNETAMOUNT        number(24, 6),
    DURATIONPRODCOUNTS   number(10),
    BALANCE              number(24, 6),
    BOTTOMBALANCE        number(24, 6),
    CUSTOMERPROFIT       number(24, 6),
    BANKPROFIT           number(24, 6),
    PRODYIELD            number(24, 6),
    DETAILUUID           char(36 byte) not null,
    ORG_CODE                 varchar2(20 byte),
    DEPT_CODE                varchar2(20 byte)
);

comment on table CHB_MONTH_REPORT_DETAIL is
    '月报明细表';

comment on column CHB_MONTH_REPORT_DETAIL.TERMID is
    '期数';

comment on column CHB_MONTH_REPORT_DETAIL.REPORTTYPE is
    '报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款';

comment on column CHB_MONTH_REPORT_DETAIL.PROPERTYCODE is
    '属性代码';

comment on column CHB_MONTH_REPORT_DETAIL.PRODCOUNTS is
    '本期产品只数';

comment on column CHB_MONTH_REPORT_DETAIL.PRODAMOUNT is
    '本期总募集金额';

comment on column CHB_MONTH_REPORT_DETAIL.PRODNETAMOUNT is
    '本期净募集金额';

comment on column CHB_MONTH_REPORT_DETAIL.DURATIONPRODCOUNTS is
    '期末存续产品只数';

comment on column CHB_MONTH_REPORT_DETAIL.BALANCE is
    '期末余额';

comment on column CHB_MONTH_REPORT_DETAIL.BOTTOMBALANCE is
    '期末余额（穿透后）';

comment on column CHB_MONTH_REPORT_DETAIL.CUSTOMERPROFIT is
    '本期客户收益';

comment on column CHB_MONTH_REPORT_DETAIL.BANKPROFIT is
    '本期银行收益';

comment on column CHB_MONTH_REPORT_DETAIL.PRODYIELD is
    '本期产品收益率';

comment on column CHB_MONTH_REPORT_DETAIL.ORG_CODE is
    '机构代码';

comment on column CHB_MONTH_REPORT_DETAIL.DEPT_CODE is
    '部门代码';
    
create unique index CHB_MONTH_REPORT_DETAIL_PK1 on
    CHB_MONTH_REPORT_DETAIL (
        DETAILUUID
    asc );

alter table CHB_MONTH_REPORT_DETAIL add constraint CHB_MONTH_REPORT_DETAIL_PK primary key ( DETAILUUID );

create table CHB_MONTH_REPORT_TEMPLATE (
    TEMPLATEID     char(36 byte),
    PROPERTYID     char(36 byte),
    PROPERTYCODE   varchar2(20 byte),
    PROPERNAME     varchar2(100 byte),
    ORDERNO        number,
    REPORTTYPE     varchar2(10 byte),
    REPORTCYCLE    varchar2(2 byte),
    STATUS         varchar2(2 byte),
    TEMPUUID       char(36 byte) not null
);

comment on table CHB_MONTH_REPORT_TEMPLATE is
    '月报模版表';

comment on column CHB_MONTH_REPORT_TEMPLATE.TEMPLATEID is
    '模版编号';

comment on column CHB_MONTH_REPORT_TEMPLATE.PROPERTYID is
    '属性编号';

comment on column CHB_MONTH_REPORT_TEMPLATE.PROPERTYCODE is
    '属性代码';

comment on column CHB_MONTH_REPORT_TEMPLATE.PROPERNAME is
    '属性名称';

comment on column CHB_MONTH_REPORT_TEMPLATE.ORDERNO is
    '排序';

comment on column CHB_MONTH_REPORT_TEMPLATE.REPORTTYPE is
    '报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款';

comment on column CHB_MONTH_REPORT_TEMPLATE.REPORTCYCLE is
    '报表周期：D-日，WE-周报，M-月报，S-季，Y-年';

comment on column CHB_MONTH_REPORT_TEMPLATE.STATUS is
    '状态';

create unique index CHB_MONTH_REPORT_TEMPLATE_PK1 on
    CHB_MONTH_REPORT_TEMPLATE (
        TEMPUUID
    asc );

alter table CHB_MONTH_REPORT_TEMPLATE add constraint CHB_MONTH_REPORT_TEMPLATE_PK primary key ( TEMPUUID );

create table CHB_PRD_BASEINFO (
    PRODUUID              char(36 byte) not null,
    FPROD_ID              varchar2(50 byte),
    PROD_ID               varchar2(50 byte) not null,
    ORG_CODE              varchar2(50 byte),
    PROD_NAME             varchar2(200 byte),
    REG_CODE              varchar2(50 byte),
    LACKS                 varchar2(50 byte),
    PROD_INS_ID           varchar2(50 byte),
    PROFIT_TYPE           varchar2(50 byte),
    INVESTOR_TYPE         varchar2(50 byte),
    IS_INTACT             varchar2(50 byte),
    STATUS                varchar2(50 byte),
    BASIC                 varchar2(50 byte),
    PRODTYPE              varchar2(50 byte),
    ISAGENCY              varchar2(50 byte),
    SALETARGET            varchar2(50 byte),
    DURATIONFLAG          varchar2(50 byte),
    MDATE                 date,
    VDATE                 date,
    TERMINATEDATE         date,
    IVSTORIENTATION       varchar2(50 byte),
    OPEN_BUSS_REMARK      varchar2(256 byte),
    RAISE_PSTRDATE        date,
    RAISE_PENDDATE        date,
    DECLARE_STATUS        varchar2(50 byte),
    ISSUING_STATUS        varchar2(50 byte),
    RISK_LEVEL            varchar2(50 byte),
    TERMTYPE              varchar2(50 byte),
    RAISE_CCY             varchar2(50 byte),
    RAISE_VDATE           date,
    RAISE_ENDDATE         date,
    TEMPLATE_NAME         varchar2(50 byte),
    PROD_BRAND            varchar2(180 byte),
    PROD_STAGE            number(30, 4),
    DESIGNER_NAME         varchar2(50 byte),
    DESIGNER_ID           varchar2(50 byte),
    APPROVER_NAME         varchar2(50 byte),
    APPROVER_ID           varchar2(50 byte),
    MANAGER_NAME          varchar2(50 byte),
    MANAGER_ID            varchar2(50 byte),
    BOOK_MODE             varchar2(50 byte),
    LIAISON_NAME          varchar2(50 byte),
    LIAISON_TEL           varchar2(50 byte),
    LIAISON_MOBILE        varchar2(50 byte),
    LIAISON_EMAIL         varchar2(50 byte),
    INVEST_ARAE           varchar2(50 byte),
    INVEST_COUNTRY        varchar2(50 byte),
    FINAN_SER_MODEL       varchar2(50 byte),
    OPERATE_MODE          varchar2(50 byte),
    CMMODE                varchar2(50 byte),
    PMMODE                varchar2(50 byte),
    MNAME                 varchar2(180 byte),
    INV_ASSET_TYPE        varchar2(50 byte),
    CONT_MODE             varchar2(50 byte),
    COOPERATION_NAME      varchar2(180 byte),
    CON_PRD_TRAGET        varchar2(50 byte),
    IS_YIELD              varchar2(50 byte),
    IS_YIELD_MEAS_BASIS   varchar2(50 byte),
    INV_TYPE_RAT          varchar2(300 byte),
    RISK_TYPE             varchar2(50 byte),
    HIGH_YIELD            number(30, 4),
    LOW_YIELD             number(30, 4),
    SALE_AREA             varchar2(50 byte),
    SALE_AMT_STR          number(30, 4),
    CASH_PRIN_CCY         varchar2(50 byte),
    CASH_PROF_CCY         varchar2(50 byte),
    RAISE_AMT_PLAN        number(30, 4),
    SALE_FEE_RATE         number(30, 14),
    INVEST_PRIN_DATE      varchar2(50 byte),
    INVEST_PROF_DATE      varchar2(50 byte),
    TRU_FEE_RATE          number(30, 14),
    IN_DEP_CODE           varchar2(50 byte),
    IN_DEP_NAME           varchar2(50 byte),
    OUT_DEP_CTRY          varchar2(50 byte),
    OUT_DEP_NAME          varchar2(200 byte),
    TERM_RIGHT_FLAG       varchar2(50 byte),
    CUS_REDEEM_FLAG       varchar2(50 byte),
    CREDIT_FLAG           varchar2(50 byte),
    CREDIT_TYPE           varchar2(50 byte),
    PRICED_MODE           varchar2(50 byte),
    PRD_VDATE             date,
    PRD_MDATE             date,
    EXP_AVG_YIELD         number(30, 14),
    IS_CONSTRACTE         varchar2(50 byte),
    GRADE_TYPE            varchar2(50 byte),
    YIELD_COMMENT         varchar2(400 byte),
    OPEN_MODE             varchar2(50 byte),
    OPEN_CIRCLE           varchar2(50 byte),
    OPEN_CIRCLE_DAYS      number(30, 4),
    RULE_COMMENT          varchar2(256 byte),
    FIRST_OPEN_DAY        date,
    HOLIDAY_ON_FLAG       varchar2(50 byte),
    AVG_OPEN_COUNT        number(30, 4),
    OPEN_BUSS             varchar2(50 byte),
    HIGH_YIELD_ISSUE      number(30, 14),
    LOW_YIELD_ISSUE       number(30, 14),
    CREDIT_MODE           varchar2(50 byte),
    ISSUE_PARTY_ID        varchar2(50 byte),
    OPENTYPEP             varchar2(50 byte),
    OPENDAYP              varchar2(50 byte),
    OPENCYCLEP            varchar2(50 byte),
    OPENCYCLEUNITP        varchar2(50 byte),
    WEEKP                 varchar2(50 byte),
    DAYP                  varchar2(50 byte),
    WORKDAYP              varchar2(50 byte),
    REMARK                varchar2(1000 byte),
    DEPT_CODE             varchar2(50 byte),
    WORKFLOW_ID           varchar2(50 byte),
    DATA_SOURCE           varchar2(50 byte),
    RAISE_TYPE            varchar2(50 byte),
    MANAGE_TYPE           varchar2(50 byte),
    UPDATE_USER           varchar2(20 byte),
    UPDATE_TIME           timestamp,
    CREATE_USER           varchar2(20 byte),
    CREATE_TIME           timestamp,
    AGENT                 varchar2(20 byte)
);

comment on table CHB_PRD_BASEINFO is
    '产品信息维护表';

comment on column CHB_PRD_BASEINFO.PRODUUID is
    '主键，自动生成';

comment on column CHB_PRD_BASEINFO.FPROD_ID is
    '父产品代码';

comment on column CHB_PRD_BASEINFO.PROD_ID is
    '产品代码';

comment on column CHB_PRD_BASEINFO.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_BASEINFO.PROD_NAME is
    '产品名称';

comment on column CHB_PRD_BASEINFO.REG_CODE is
    '产品登记编码';

comment on column CHB_PRD_BASEINFO.LACKS is
    '信息缺失项 无缺失、缺失中债申报、缺失中债发行';

comment on column CHB_PRD_BASEINFO.PROD_INS_ID is
    '行内标识码';

comment on column CHB_PRD_BASEINFO.PROFIT_TYPE is
    '产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益';

comment on column CHB_PRD_BASEINFO.INVESTOR_TYPE is
    '投资者类型';

comment on column CHB_PRD_BASEINFO.IS_INTACT is
    '发行要素维护完整';

comment on column CHB_PRD_BASEINFO.STATUS is
    '数据状态  A 新建/ E 有效/D删除';

comment on column CHB_PRD_BASEINFO.BASIC is
    '计息基础 A/360, A/365';

comment on column CHB_PRD_BASEINFO.PRODTYPE is
    '标识哪种产品，净值N，货币M，利率R，客户周期C';

comment on column CHB_PRD_BASEINFO.ISAGENCY is
    '是否代销，01/02';

comment on column CHB_PRD_BASEINFO.SALETARGET is
    '目标客户类型,01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户';

comment on column CHB_PRD_BASEINFO.DURATIONFLAG is
    '存续状态  存续01，终止02，未起息03';

comment on column CHB_PRD_BASEINFO.MDATE is
    '到期日';

comment on column CHB_PRD_BASEINFO.VDATE is
    '起息日';

comment on column CHB_PRD_BASEINFO.TERMINATEDATE is
    '提前终止日';

comment on column CHB_PRD_BASEINFO.IVSTORIENTATION is
    '投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类';

comment on column CHB_PRD_BASEINFO.OPEN_BUSS_REMARK is
    '开放期业务说明';

comment on column CHB_PRD_BASEINFO.RAISE_PSTRDATE is
    '募集起始日期（从）';

comment on column CHB_PRD_BASEINFO.RAISE_PENDDATE is
    '募集起始日期（到）';

comment on column CHB_PRD_BASEINFO.DECLARE_STATUS is
    '报备状态';

comment on column CHB_PRD_BASEINFO.ISSUING_STATUS is
    '发行状态  确认发行/发行成功/发行失败/不发行';

comment on column CHB_PRD_BASEINFO.RISK_LEVEL is
    '产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）';

comment on column CHB_PRD_BASEINFO.TERMTYPE is
    '产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上';

comment on column CHB_PRD_BASEINFO.RAISE_CCY is
    '募集币种';

comment on column CHB_PRD_BASEINFO.RAISE_VDATE is
    '募集起始日期';

comment on column CHB_PRD_BASEINFO.RAISE_ENDDATE is
    '募集结束日期';

comment on column CHB_PRD_BASEINFO.TEMPLATE_NAME is
    '模板名称';

comment on column CHB_PRD_BASEINFO.PROD_BRAND is
    '产品品牌';

comment on column CHB_PRD_BASEINFO.PROD_STAGE is
    '产品期次';

comment on column CHB_PRD_BASEINFO.DESIGNER_NAME is
    '产品设计人姓名';

comment on column CHB_PRD_BASEINFO.DESIGNER_ID is
    '产品设计人身份证号';

comment on column CHB_PRD_BASEINFO.APPROVER_NAME is
    '产品审批人姓名';

comment on column CHB_PRD_BASEINFO.APPROVER_ID is
    '产品审批人身份证号';

comment on column CHB_PRD_BASEINFO.MANAGER_NAME is
    '投资经理姓名';

comment on column CHB_PRD_BASEINFO.MANAGER_ID is
    '投资经理身份证号';

comment on column CHB_PRD_BASEINFO.BOOK_MODE is
    '会计核算方式  01 表内/02 表外';

comment on column CHB_PRD_BASEINFO.LIAISON_NAME is
    '业务联络人姓名';

comment on column CHB_PRD_BASEINFO.LIAISON_TEL is
    '业务联络人座机';

comment on column CHB_PRD_BASEINFO.LIAISON_MOBILE is
    '业务联络人手机';

comment on column CHB_PRD_BASEINFO.LIAISON_EMAIL is
    '业务联络人邮箱';

comment on column CHB_PRD_BASEINFO.INVEST_ARAE is
    '资金投向地区  01 境内/02 境外/03 境内和境外';

comment on column CHB_PRD_BASEINFO.INVEST_COUNTRY is
    '产品投资国家或地区参考《世界各国和地区名称代码》';

comment on column CHB_PRD_BASEINFO.FINAN_SER_MODEL is
    '理财业务服务模式01 综合理财服务/02 理财顾问服务/99 其他';

comment on column CHB_PRD_BASEINFO.OPERATE_MODE is
    '产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型';

comment on column CHB_PRD_BASEINFO.CMMODE is
    '产品资产配置方式01 单一资产配置（一对一）/02 资产组合配置（一对多）/03 其他配置（多对多）';

comment on column CHB_PRD_BASEINFO.PMMODE is
    '产品管理模式01银行为实际管理人/02 其他机构为实际管理人/03 资产管理公司/04 特殊目的载体（SPV）';

comment on column CHB_PRD_BASEINFO.MNAME is
    '实际管理人名称';

comment on column CHB_PRD_BASEINFO.INV_ASSET_TYPE is
    '投资资产类型  01 结构性投资类/02 货币市场工具类/03 债券类/04 非标准化债权类/05 股票类/06 股权类/07 另类投资类/08 混合类/09代客境外投资类/10 银行存款类/11 理财直接融资工具类/12 新增可投资资产类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类'
    ;

comment on column CHB_PRD_BASEINFO.CONT_MODE is
    '合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他';

comment on column CHB_PRD_BASEINFO.COOPERATION_NAME is
    '合作机构';

comment on column CHB_PRD_BASEINFO.CON_PRD_TRAGET is
    '结构性产品挂钩标的01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他';

comment on column CHB_PRD_BASEINFO.IS_YIELD is
    '是否有预期收益率01 是/02 否';

comment on column CHB_PRD_BASEINFO.IS_YIELD_MEAS_BASIS is
    '是否有收益率测算依据  01 是/02 否';

comment on column CHB_PRD_BASEINFO.INV_TYPE_RAT is
    '投资资产种类及比例';

comment on column CHB_PRD_BASEINFO.RISK_TYPE is
    '投资者风险偏好01 保守型/02 稳健型/03 平衡型/04 成长型/05 进取型';

comment on column CHB_PRD_BASEINFO.HIGH_YIELD is
    '预计客户最高年收益率%';

comment on column CHB_PRD_BASEINFO.LOW_YIELD is
    '预计客户最低年收益率%';

comment on column CHB_PRD_BASEINFO.SALE_AREA is
    '产品销售地区';

comment on column CHB_PRD_BASEINFO.SALE_AMT_STR is
    '起点销售金额';

comment on column CHB_PRD_BASEINFO.CASH_PRIN_CCY is
    '兑付本金币种';

comment on column CHB_PRD_BASEINFO.CASH_PROF_CCY is
    '兑付收益币种';

comment on column CHB_PRD_BASEINFO.RAISE_AMT_PLAN is
    '计划募集金额（元）';

comment on column CHB_PRD_BASEINFO.SALE_FEE_RATE is
    '销售手续费率%';

comment on column CHB_PRD_BASEINFO.INVEST_PRIN_DATE is
    '投资本金到账日';

comment on column CHB_PRD_BASEINFO.INVEST_PROF_DATE is
    '投资收益到账日';

comment on column CHB_PRD_BASEINFO.TRU_FEE_RATE is
    '托管费率';

comment on column CHB_PRD_BASEINFO.IN_DEP_CODE is
    '境内托管机构代码';

comment on column CHB_PRD_BASEINFO.IN_DEP_NAME is
    '境内托管机构名称';

comment on column CHB_PRD_BASEINFO.OUT_DEP_CTRY is
    '境外托管机构国别';

comment on column CHB_PRD_BASEINFO.OUT_DEP_NAME is
    '境外托管机构名称';

comment on column CHB_PRD_BASEINFO.TERM_RIGHT_FLAG is
    '发行机构提前终止权标识';

comment on column CHB_PRD_BASEINFO.CUS_REDEEM_FLAG is
    '客户赎回权标识';

comment on column CHB_PRD_BASEINFO.CREDIT_FLAG is
    '产品增信标识';

comment on column CHB_PRD_BASEINFO.CREDIT_TYPE is
    '产品增信机构类型';

comment on column CHB_PRD_BASEINFO.PRICED_MODE is
    '产品定价方式';

comment on column CHB_PRD_BASEINFO.PRD_VDATE is
    '产品起始日期';

comment on column CHB_PRD_BASEINFO.PRD_MDATE is
    '产品终止日期';

comment on column CHB_PRD_BASEINFO.EXP_AVG_YIELD is
    '平均预计客户年收益率';

comment on column CHB_PRD_BASEINFO.IS_CONSTRACTE is
    '是否结构化分层产品';

comment on column CHB_PRD_BASEINFO.GRADE_TYPE is
    '层级类别  11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五';

comment on column CHB_PRD_BASEINFO.YIELD_COMMENT is
    '收益率分档情况说明';

comment on column CHB_PRD_BASEINFO.OPEN_MODE is
    '开放模式01 有规律开放/02 无规律开放';

comment on column CHB_PRD_BASEINFO.OPEN_CIRCLE is
    '规律开放周期';

comment on column CHB_PRD_BASEINFO.OPEN_CIRCLE_DAYS is
    '其他规律开放周期（天）';

comment on column CHB_PRD_BASEINFO.RULE_COMMENT is
    '无规律开放说明';

comment on column CHB_PRD_BASEINFO.FIRST_OPEN_DAY is
    '首次开放周期起始日';

comment on column CHB_PRD_BASEINFO.HOLIDAY_ON_FLAG is
    '节假日是否开放';

comment on column CHB_PRD_BASEINFO.AVG_OPEN_COUNT is
    '平均开放次数（年化）';

comment on column CHB_PRD_BASEINFO.OPEN_BUSS is
    '开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他';

comment on column CHB_PRD_BASEINFO.HIGH_YIELD_ISSUE is
    '预计最高收益率-发行';

comment on column CHB_PRD_BASEINFO.LOW_YIELD_ISSUE is
    '预计最低收益率-发行';

comment on column CHB_PRD_BASEINFO.CREDIT_MODE is
    '产品增信形式';

comment on column CHB_PRD_BASEINFO.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_BASEINFO.OPENTYPEP is
    '开放模式申 01	定期开放02	高频开放03	周期开放';

comment on column CHB_PRD_BASEINFO.OPENDAYP is
    '开放天数申';

comment on column CHB_PRD_BASEINFO.OPENCYCLEP is
    '开放周期申';

comment on column CHB_PRD_BASEINFO.OPENCYCLEUNITP is
    '开放周期单位';

comment on column CHB_PRD_BASEINFO.WEEKP is
    '周申';

comment on column CHB_PRD_BASEINFO.DAYP is
    '几号申';

comment on column CHB_PRD_BASEINFO.WORKDAYP is
    '第几工作日申';

comment on column CHB_PRD_BASEINFO.REMARK is
    '备注';

comment on column CHB_PRD_BASEINFO.DEPT_CODE is
    '部门代码';

comment on column CHB_PRD_BASEINFO.WORKFLOW_ID is
    '工作流代码';

comment on column CHB_PRD_BASEINFO.DATA_SOURCE is
    '数据来源';

comment on column CHB_PRD_BASEINFO.RAISE_TYPE is
    '募集方式 01公募 02私募';

comment on column CHB_PRD_BASEINFO.MANAGE_TYPE is
    '管理方式';

comment on column CHB_PRD_BASEINFO.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_BASEINFO.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_BASEINFO.CREATE_USER is
    '创建人';

comment on column CHB_PRD_BASEINFO.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_BASEINFO.AGENT is
    '经办人';

create unique index CHB_PRD_BASEINFO_PK on
    CHB_PRD_BASEINFO (
        PRODUUID
    asc );

alter table CHB_PRD_BASEINFO add constraint CHB_PRD_BASEINFO_PK primary key ( PRODUUID );

create table CHB_PRD_DEAL (
    SEQNO               char(36 byte) not null,
    TRADE_ID            varchar2(50 byte),
    FPROD_ID            varchar2(50 byte),
    PROD_ID             varchar2(50 byte) not null,
    ISSUE_PARTY_ID      varchar2(50 byte),
    INDENTIFY_MARK      varchar2(50 byte) not null,
    BUY_PLACE           varchar2(50 byte),
    TRADE_TYPE          varchar2(50 byte),
    SETTDATE_DATE       date,
    PAY_DATE            date,
    INTEREST_DATE       date,
    INT_AMT             number(30, 2),
    TRADE_PRINCE        number(30, 2),
    UNIT_BONUS_AMOUNT   number(30, 2),
    REDEMPTION_FEE      number(30, 2),
    TRADE_QTY           number(30, 4),
    CCY                 varchar2(50 byte),
    STATUS              varchar2(50 byte),
    ORG_CODE            varchar2(20 byte),
    DEPT_CODE           varchar2(20 byte),
    DATA_SOURCE         varchar2(50 byte),
    REMARK              varchar2(600 byte),
    REV_DATE            date,
    UPDATE_USER         varchar2(20 byte),
    UPDATE_TIME         timestamp,
    CREATE_USER         varchar2(20 byte),
    CREATE_TIME         timestamp,
    INTEREST_CCY        varchar2(50 byte)
);

comment on table CHB_PRD_DEAL is
    '产品交易维护表';

comment on column CHB_PRD_DEAL.SEQNO is
    '交易编号';

comment on column CHB_PRD_DEAL.TRADE_ID is
    '上游系统交易编码';

comment on column CHB_PRD_DEAL.FPROD_ID is
    '父产品代码';

comment on column CHB_PRD_DEAL.PROD_ID is
    '产品代码';

comment on column CHB_PRD_DEAL.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_DEAL.INDENTIFY_MARK is
    '识别标识';

comment on column CHB_PRD_DEAL.BUY_PLACE is
    '销售区域';

comment on column CHB_PRD_DEAL.TRADE_TYPE is
    '交易类型(01 募集期认购/02 存续期申购/03 存续期赎回/04 收益分配/05 收益再投资/14 份额强增/15 份额强减/16 到期兑付/17 提前赎回；)';

comment on column CHB_PRD_DEAL.SETTDATE_DATE is
    '确认日';

comment on column CHB_PRD_DEAL.PAY_DATE is
    '本金支付日';

comment on column CHB_PRD_DEAL.INTEREST_DATE is
    '利息支付日';

comment on column CHB_PRD_DEAL.INT_AMT is
    '利息金额';

comment on column CHB_PRD_DEAL.TRADE_PRINCE is
    '交易本金(赎回时存净金额=交易本金-赎回费)';

comment on column CHB_PRD_DEAL.UNIT_BONUS_AMOUNT is
    '单位分红金额';

comment on column CHB_PRD_DEAL.REDEMPTION_FEE is
    '赎回费';

comment on column CHB_PRD_DEAL.TRADE_QTY is
    '交易份额';

comment on column CHB_PRD_DEAL.CCY is
    '币种';

comment on column CHB_PRD_DEAL.STATUS is
    '数据状态(新建、修改、删除)';

comment on column CHB_PRD_DEAL.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_DEAL.DEPT_CODE is
    '部门代码';

comment on column CHB_PRD_DEAL.DATA_SOURCE is
    '数据来源';

comment on column CHB_PRD_DEAL.REMARK is
    '备注';

comment on column CHB_PRD_DEAL.REV_DATE is
    '撤销日';

comment on column CHB_PRD_DEAL.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_DEAL.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_DEAL.CREATE_USER is
    '创建人';

comment on column CHB_PRD_DEAL.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_DEAL.INTEREST_CCY is
    '收益币种';

create unique index CHB_PRD_DEAL_PK on
    CHB_PRD_DEAL (
        SEQNO
    asc );

alter table CHB_PRD_DEAL add constraint CHB_PRD_DEAL_PK primary key ( SEQNO );

create table CHB_PRD_DEAL_SUB (
    REGIST_SEQNO             char(36 byte) not null,
    PROD_ID                  varchar2(50 byte),
    ISSUE_PARTY_ID           varchar2(50 byte),
    PROD_REGIST_CODE         varchar2(50 byte),
    ORIGINAL_VALUE           number(30, 4),
    PRD_VALUE                number(30, 4),
    TOTAL_VALUE              number(30, 4),
    VALUE_CCY                varchar2(50 byte),
    CNY_ORL_VALUE            number(30, 4),
    CNY_PRD_VALUE            number(30, 4),
    CNY_TAL_VALUE            number(30, 4),
    REA_YIELD                number(30, 4),
    NEW_EXP_YIELD            number(30, 4),
    BANK_IMP_INT             number(30, 2),
    VDATE                    date,
    MDATE                    date,
    PURCHASE_QTY             number(30, 4),
    REDEEM_QTY               number(30, 4),
    BALANCE_AMT              number(30, 2),
    PRD_QTY                  number(30, 4),
    CCY_PURCHASE             varchar2(500 byte),
    SEND_STATUS              varchar2(50 byte),
    SUBMIT_FEEDBACK_STATUS   varchar2(50 byte),
    SUMIT_SEQ                varchar2(20 byte),
    STATUS_DATE              date,
    CHB_SUBMIT_DEADLINE      timestamp,
    SUBMIT_DEADLINE          timestamp,
    STATUS                   varchar2(50 byte),
    CREATE_USER              varchar2(20 byte),
    ORG_CODE                 varchar2(20 byte),
    CREATE_DEPT              varchar2(20 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp,
    SUBMIT_TIME              timestamp,
    REMARK                   varchar2(1000 byte)
);

comment on table CHB_PRD_DEAL_SUB is
    '产品存续登记表';

comment on column CHB_PRD_DEAL_SUB.REGIST_SEQNO is
    '登记序列';

comment on column CHB_PRD_DEAL_SUB.PROD_ID is
    '理财产品代码';

comment on column CHB_PRD_DEAL_SUB.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_DEAL_SUB.PROD_REGIST_CODE is
    '产品登记编码';

comment on column CHB_PRD_DEAL_SUB.ORIGINAL_VALUE is
    '初始净值';

comment on column CHB_PRD_DEAL_SUB.PRD_VALUE is
    '产品净值';

comment on column CHB_PRD_DEAL_SUB.TOTAL_VALUE is
    '累计净值';

comment on column CHB_PRD_DEAL_SUB.VALUE_CCY is
    '净值币种';

comment on column CHB_PRD_DEAL_SUB.CNY_ORL_VALUE is
    '人民币初始净值';

comment on column CHB_PRD_DEAL_SUB.CNY_PRD_VALUE is
    '折算人民币净值';

comment on column CHB_PRD_DEAL_SUB.CNY_TAL_VALUE is
    '人民币累计净值';

comment on column CHB_PRD_DEAL_SUB.REA_YIELD is
    '实现收益率';

comment on column CHB_PRD_DEAL_SUB.NEW_EXP_YIELD is
    '最新预期收益率';

comment on column CHB_PRD_DEAL_SUB.BANK_IMP_INT is
    '银行实现收益(元)';

comment on column CHB_PRD_DEAL_SUB.VDATE is
    '业务起始日';

comment on column CHB_PRD_DEAL_SUB.MDATE is
    '业务结束日';

comment on column CHB_PRD_DEAL_SUB.PURCHASE_QTY is
    '累计申购份额';

comment on column CHB_PRD_DEAL_SUB.REDEEM_QTY is
    '累计赎回份额';

comment on column CHB_PRD_DEAL_SUB.BALANCE_AMT is
    '产品余额';

comment on column CHB_PRD_DEAL_SUB.PRD_QTY is
    '产品份额';

comment on column CHB_PRD_DEAL_SUB.CCY_PURCHASE is
    '币种及申购兑付金额';

comment on column CHB_PRD_DEAL_SUB.SEND_STATUS is
    '报送状态';

comment on column CHB_PRD_DEAL_SUB.SUBMIT_FEEDBACK_STATUS is
    '报送反馈状态';

comment on column CHB_PRD_DEAL_SUB.SUMIT_SEQ is
    '报送流水号';

comment on column CHB_PRD_DEAL_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_PRD_DEAL_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_PRD_DEAL_SUB.SUBMIT_DEADLINE is
    '行内报送截止日';

comment on column CHB_PRD_DEAL_SUB.STATUS is
    '数据状态';

comment on column CHB_PRD_DEAL_SUB.CREATE_USER is
    '创建人';

comment on column CHB_PRD_DEAL_SUB.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_DEAL_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_PRD_DEAL_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_DEAL_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_DEAL_SUB.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_DEAL_SUB.SUBMIT_TIME is
    '报送日期';

comment on column CHB_PRD_DEAL_SUB.REMARK is
    '备注';

create unique index CHB_PRD_DEAL_SUB_PK on
    CHB_PRD_DEAL_SUB (
        REGIST_SEQNO
    asc );

alter table CHB_PRD_DEAL_SUB add constraint CHB_PRD_DEAL_SUB_PK primary key ( REGIST_SEQNO );

create table CHB_PRD_DECLARE_SUB (
    REGIST_SEQNO             char(36 byte),
    PROD_ID                  varchar2(50 byte) not null,
    PROD_NAME                varchar2(200 byte),
    PROD_INS_ID              varchar2(50 byte), 
    PROD_BRAND               varchar2(50 byte),
    PROD_STAGE               number(30,4),
    ISSUE_PARTY_ID           varchar2(50 byte), 
    APPROVER_ID              varchar2(50 byte),
    APPROVER_NAME            varchar2(50 byte),
    DESIGNER_ID              varchar2(50 byte),
    DESIGNER_NAME            varchar2(50 byte),
    MANAGER_ID               varchar2(50 byte),
    MANAGER_NAME             varchar2(50 byte),
    LIAISON_NAME             varchar2(50 byte),
    LIAISON_TEL              varchar2(50 byte),
    LIAISON_MOBILE           varchar2(50 byte),
    LIAISON_EMAIL            varchar2(50 byte),
    PROFIT_TYPE              varchar2(50 byte),
    TERMTYPE                 varchar2(50 byte),
    SALETARGET               varchar2(50 byte),
    INVEST_ARAE              varchar2(50 byte),
    INVEST_COUNTRY           varchar2(50 byte),
    FINAN_SER_MODEL          varchar2(50 byte),
    OPERATE_MODE             varchar2(50 byte),
    BOOK_MODE                varchar2(50 byte),
    CMMODE                   varchar2(50 byte),
    PMMODE                   varchar2(50 byte),
    MNAME                    varchar2(400 byte),
    PRICED_MODE              varchar2(50 byte),
    INV_ASSET_TYPE           varchar2(50 byte),
    CON_PRD_TRAGET           varchar2(200 byte),
    CONT_MODE                varchar2(50 byte),
    COOPERATION_NAME         varchar2(400 byte),
    INV_TYPE_RAT             varchar2(300 byte),
    IS_YIELD                 varchar2(50 byte),
    HIGH_YIELD              number(30,4),
    LOW_YIELD               number(30,4),
    IS_YIELD_MEAS_BASIS      varchar2(50 byte),
    RISK_TYPE                varchar2(50 byte),
    SALE_AREA                varchar2(50 byte),
    RAISE_CCY                varchar2(50 byte),
    CASH_PRIN_CCY            varchar2(50 byte),
    CASH_PROF_CCY            varchar2(50 byte),
    SALE_AMT_STR            number(30,4),
    RAISE_AMT_PLAN          number(30,4),
    RAISE_PSTRDATE           date,
    RAISE_PENDDATE           date,
    INVEST_PRIN_DATE         varchar2(50 byte),
    INVEST_PROF_DATE         varchar2(50 byte),
    SALE_FEE_RATE           number(30,4),
    IN_DEP_NAME              varchar2(50 byte),
    IN_DEP_CODE              varchar2(1250 byte),
    OUT_DEP_CTRY             varchar2(50 byte),
    OUT_DEP_NAME             varchar2(50 byte),
    TRU_FEE_RATE            number(30,4),
    RISK_LEVEL               varchar2(50 byte),
    TERM_RIGHT_FLAG          varchar2(50 byte),
    CUS_REDEEM_FLAG          varchar2(50 byte),
    CREDIT_FLAG              varchar2(50 byte),
    CREDIT_TYPE              varchar2(50 byte),
    CREDIT_MODE              varchar2(50 byte),
    REMARK                   varchar2(1000 byte),
    NINE_FILE                varchar2(600 byte),
    FILE_NO                  varchar2(600 byte),
    PRE_REG_DATE             date,
    PRE_APP_DATE             date,
    SEND_STATUS              varchar2(2 byte), 
    STATUS_DATE              date,
    STATUS                   varchar2(2 byte), 
    CREATE_USER              varchar2(20 byte), 
    CREATE_DEPT              varchar2(20 byte),
    CREATE_TIME              timestamp ,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp, 
    CHB_SUBMIT_DEADLINE      timestamp,
    SUBMIT_DEADLINE          timestamp,
    SUBMIT_TIME              timestamp,
    ORG_CODE                 varchar2(20 byte),
    FILE_NUM                 number(30,4),
    SUBMIT_FEEDBACK_STATUS   varchar2(50 byte),
    REG_CODE                 varchar2(50 byte)
);

comment on table CHB_PRD_DECLARE_SUB is
    '个人产品申报登记';

comment on column CHB_PRD_DECLARE_SUB.REGIST_SEQNO is
    '登记序列';

comment on column CHB_PRD_DECLARE_SUB.PROD_ID is
    '产品代码';

comment on column CHB_PRD_DECLARE_SUB.PROD_NAME is
    '产品名称';

comment on column CHB_PRD_DECLARE_SUB.PROD_INS_ID is
    '行内标识码';

comment on column CHB_PRD_DECLARE_SUB.PROD_BRAND is
    '产品品牌';

comment on column CHB_PRD_DECLARE_SUB.PROD_STAGE is
    '产品期次';

comment on column CHB_PRD_DECLARE_SUB.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_DECLARE_SUB.APPROVER_ID is
    '产品审批人身份证号';

comment on column CHB_PRD_DECLARE_SUB.APPROVER_NAME is
    '产品审批人姓名';

comment on column CHB_PRD_DECLARE_SUB.DESIGNER_ID is
    '产品设计人身份证号';

comment on column CHB_PRD_DECLARE_SUB.DESIGNER_NAME is
    '产品设计人姓名';

comment on column CHB_PRD_DECLARE_SUB.MANAGER_ID is
    '投资经理身份证号';

comment on column CHB_PRD_DECLARE_SUB.MANAGER_NAME is
    '投资经理姓名';

comment on column CHB_PRD_DECLARE_SUB.LIAISON_NAME is
    '业务联络人姓名';

comment on column CHB_PRD_DECLARE_SUB.LIAISON_TEL is
    '业务联络人座机';

comment on column CHB_PRD_DECLARE_SUB.LIAISON_MOBILE is
    '业务联络人手机';

comment on column CHB_PRD_DECLARE_SUB.LIAISON_EMAIL is
    '业务联络人邮箱';

comment on column CHB_PRD_DECLARE_SUB.PROFIT_TYPE is
    '产品收益类型';

comment on column CHB_PRD_DECLARE_SUB.TERMTYPE is
    '产品期限';

comment on column CHB_PRD_DECLARE_SUB.SALETARGET is
    '目标客户类型';

comment on column CHB_PRD_DECLARE_SUB.INVEST_ARAE is
    '资金投向地区';

comment on column CHB_PRD_DECLARE_SUB.INVEST_COUNTRY is
    '产品投资国家或地区';

comment on column CHB_PRD_DECLARE_SUB.FINAN_SER_MODEL is
    '理财业务服务模式';

comment on column CHB_PRD_DECLARE_SUB.OPERATE_MODE is
    '产品运作模式';

comment on column CHB_PRD_DECLARE_SUB.BOOK_MODE is
    '会计核算方式';

comment on column CHB_PRD_DECLARE_SUB.CMMODE is
    '产品资产配置方式';

comment on column CHB_PRD_DECLARE_SUB.PMMODE is
    '产品管理模式';

comment on column CHB_PRD_DECLARE_SUB.MNAME is
    '实际管理人员名称';

comment on column CHB_PRD_DECLARE_SUB.PRICED_MODE is
    '产品定价方式';

comment on column CHB_PRD_DECLARE_SUB.INV_ASSET_TYPE is
    '投资资产类型';

comment on column CHB_PRD_DECLARE_SUB.CON_PRD_TRAGET is
    '结构性产品挂钩标的';

comment on column CHB_PRD_DECLARE_SUB.CONT_MODE is
    '合作模式';

comment on column CHB_PRD_DECLARE_SUB.COOPERATION_NAME is
    '合作机构名称';

comment on column CHB_PRD_DECLARE_SUB.INV_TYPE_RAT is
    '投资资产种类及比例';

comment on column CHB_PRD_DECLARE_SUB.IS_YIELD is
    '是否有预期收益率';

comment on column CHB_PRD_DECLARE_SUB.HIGH_YIELD is
    '预计客户最高年收益率';

comment on column CHB_PRD_DECLARE_SUB.LOW_YIELD is
    '预计客户最低年收益率';

comment on column CHB_PRD_DECLARE_SUB.IS_YIELD_MEAS_BASIS is
    '是否有收益率测算依据';

comment on column CHB_PRD_DECLARE_SUB.RISK_TYPE is
    '投资者风险偏好';

comment on column CHB_PRD_DECLARE_SUB.SALE_AREA is
    '产品销售区域';

comment on column CHB_PRD_DECLARE_SUB.RAISE_CCY is
    '募集币种';

comment on column CHB_PRD_DECLARE_SUB.CASH_PRIN_CCY is
    '兑付本金币种';

comment on column CHB_PRD_DECLARE_SUB.CASH_PROF_CCY is
    '兑付收益币种';

comment on column CHB_PRD_DECLARE_SUB.SALE_AMT_STR is
    '起点销售金额';

comment on column CHB_PRD_DECLARE_SUB.RAISE_AMT_PLAN is
    '计划募集金额';

comment on column CHB_PRD_DECLARE_SUB.RAISE_PSTRDATE is
    '募集起始日期（从）';

comment on column CHB_PRD_DECLARE_SUB.RAISE_PENDDATE is
    '募集起始日期（到）';

comment on column CHB_PRD_DECLARE_SUB.INVEST_PRIN_DATE is
    '投资本金到账日';

comment on column CHB_PRD_DECLARE_SUB.INVEST_PROF_DATE is
    '投资收益到账日';

comment on column CHB_PRD_DECLARE_SUB.SALE_FEE_RATE is
    '销售手续费';

comment on column CHB_PRD_DECLARE_SUB.IN_DEP_NAME is
    '境内托管机构名称';

comment on column CHB_PRD_DECLARE_SUB.IN_DEP_CODE is
    '境内托管机构代码';

comment on column CHB_PRD_DECLARE_SUB.OUT_DEP_CTRY is
    '境外托管机构国别';

comment on column CHB_PRD_DECLARE_SUB.OUT_DEP_NAME is
    '境外托管机构名称';

comment on column CHB_PRD_DECLARE_SUB.TRU_FEE_RATE is
    '托管费率';

comment on column CHB_PRD_DECLARE_SUB.RISK_LEVEL is
    '产品风险等级';

comment on column CHB_PRD_DECLARE_SUB.TERM_RIGHT_FLAG is
    '发型机构提前终止权标识';

comment on column CHB_PRD_DECLARE_SUB.CUS_REDEEM_FLAG is
    '客户赎回权标识';

comment on column CHB_PRD_DECLARE_SUB.CREDIT_FLAG is
    '产品增信标识';

comment on column CHB_PRD_DECLARE_SUB.CREDIT_TYPE is
    '产品增信机构类型';

comment on column CHB_PRD_DECLARE_SUB.CREDIT_MODE is
    '产品增信形式';

comment on column CHB_PRD_DECLARE_SUB.REMARK is
    '备注';

comment on column CHB_PRD_DECLARE_SUB.NINE_FILE is
    '九大附件';

comment on column CHB_PRD_DECLARE_SUB.FILE_NO is
    '文件代码';

comment on column CHB_PRD_DECLARE_SUB.PRE_REG_DATE is
    '预计登记日期';

comment on column CHB_PRD_DECLARE_SUB.PRE_APP_DATE is
    '预计审批日期';

comment on column CHB_PRD_DECLARE_SUB.SEND_STATUS is
    '报送状态';

comment on column CHB_PRD_DECLARE_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_PRD_DECLARE_SUB.STATUS is
    '数据状态';

comment on column CHB_PRD_DECLARE_SUB.CREATE_USER is
    '创建人';

comment on column CHB_PRD_DECLARE_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_PRD_DECLARE_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_DECLARE_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_DECLARE_SUB.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_DECLARE_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_PRD_DECLARE_SUB.SUBMIT_DEADLINE is
    '报送截止日';

comment on column CHB_PRD_DECLARE_SUB.SUBMIT_TIME is
    '报送时间';

comment on column CHB_PRD_DECLARE_SUB.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_DECLARE_SUB.FILE_NUM is
    '附件个数';

comment on column CHB_PRD_DECLARE_SUB.SUBMIT_FEEDBACK_STATUS is
    '反馈状态';

comment on column CHB_PRD_DECLARE_SUB.REG_CODE is
    '产品登记编码';

create table CHB_PRD_END_SUB (
    REGIST_SEQNO             char(36 byte) not null,
    PROD_ID                  varchar2(50 byte),
    MDATE                    date,
    ISSUE_PARTY_ID           varchar2(50 byte),
    PROFIT                   number(30, 2),
    CUSTOM_PROFIT            number(30, 2),
    CUSTOM_PROFIT_ORI        number(30, 2),
    CUSTOM_AMT_ORI           number(30, 2),
    CUSTOM_AMT               number(30, 2),
    CUSTOM_QTY               number(30, 2),
    CUS_YEAR_YIELD           number(10, 7),
    PRD_YEAR_YIELD           number(10, 7),
    CUST_FEE_IN              number(30, 2),
    MAN_FEE_IN               number(30, 2),
    COU_FEE_IN               number(30, 2),
    OTHER_FEE_IN             number(30, 2),
    CUST_FEE_OUT             number(30, 2),
    MAN_FEE_OUT              number(30, 2),
    COU_FEE_OUT              number(30, 2),
    ADV_FEE_OUT              number(30, 2),
    OTHER_FEE_OUT            number(30, 2),
    SEND_STATUS              varchar2(2 byte),
    SUBMIT_FEEDBACK_STATUS   varchar2(2 byte),
    STATUS_DATE              date,
    STATUS                   varchar2(50 byte),
    CREATE_USER              varchar2(20 byte),
    CREATE_DEPT              varchar2(20 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp,
    SUBMIT_TIME              date,
    CHB_SUBMIT_DEADLINE      date,
    SUBMIT_DEADLINE          date,
    ORG_CODE                 varchar2(20 byte),
    DEPT_CODE                varchar2(32 byte)
);

comment on table CHB_PRD_END_SUB is
    '终止登记';

comment on column CHB_PRD_END_SUB.REGIST_SEQNO is
    '登记序列';

comment on column CHB_PRD_END_SUB.PROD_ID is
    '理财产品代码';

comment on column CHB_PRD_END_SUB.MDATE is
    '实际终止日期';

comment on column CHB_PRD_END_SUB.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_END_SUB.PROFIT is
    '银行实际实现收入';

comment on column CHB_PRD_END_SUB.CUSTOM_PROFIT is
    '兑付客户收益';

comment on column CHB_PRD_END_SUB.CUSTOM_PROFIT_ORI is
    '兑付客户收益(原币种)';

comment on column CHB_PRD_END_SUB.CUSTOM_AMT_ORI is
    '兑付客户总金额(原币种)';

comment on column CHB_PRD_END_SUB.CUSTOM_AMT is
    '兑付客户总金额';

comment on column CHB_PRD_END_SUB.CUSTOM_QTY is
    '兑付总份额';

comment on column CHB_PRD_END_SUB.CUS_YEAR_YIELD is
    '客户实际年化收益率';

comment on column CHB_PRD_END_SUB.PRD_YEAR_YIELD is
    '产品实际年化收益率';

comment on column CHB_PRD_END_SUB.CUST_FEE_IN is
    '本机构托管费';

comment on column CHB_PRD_END_SUB.MAN_FEE_IN is
    '本机构管理费';

comment on column CHB_PRD_END_SUB.COU_FEE_IN is
    '本机构销售手续费';

comment on column CHB_PRD_END_SUB.OTHER_FEE_IN is
    '本机构其他产品费用';

comment on column CHB_PRD_END_SUB.CUST_FEE_OUT is
    '其他机构托管费';

comment on column CHB_PRD_END_SUB.MAN_FEE_OUT is
    '其他机构管理费';

comment on column CHB_PRD_END_SUB.COU_FEE_OUT is
    '其他机构销售手续费';

comment on column CHB_PRD_END_SUB.ADV_FEE_OUT is
    '投资顾问费用';

comment on column CHB_PRD_END_SUB.OTHER_FEE_OUT is
    '其他机构其他产品费用';

comment on column CHB_PRD_END_SUB.SEND_STATUS is
    '报送状态';

comment on column CHB_PRD_END_SUB.SUBMIT_FEEDBACK_STATUS is
    '反馈状态';

comment on column CHB_PRD_END_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_PRD_END_SUB.STATUS is
    '数据状态 参照数据字典:STATUS';

comment on column CHB_PRD_END_SUB.CREATE_USER is
    '创建人';

comment on column CHB_PRD_END_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_PRD_END_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_END_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_END_SUB.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_END_SUB.SUBMIT_TIME is
    '报送日期';

comment on column CHB_PRD_END_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_PRD_END_SUB.SUBMIT_DEADLINE is
    '行内报送截止日';

comment on column CHB_PRD_END_SUB.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_END_SUB.DEPT_CODE is
    '部门代码';

create unique index CHB_PRD_END_SUB on
    CHB_PRD_END_SUB (
        REGIST_SEQNO
    asc );

alter table CHB_PRD_END_SUB add constraint CHB_PRD_END_SUB primary key ( REGIST_SEQNO );

create table CHB_PRD_FEE_DEAL (
    SEQNO         char(36 byte) not null,
    TRADE_ID      varchar2(50 byte),
    FPROD_ID      varchar2(50 byte),
    PROD_ID       varchar2(50 byte),
    PROD_NAME     varchar2(200 byte),
    REG_CODE      varchar2(200 byte),
    FEE_ID        varchar2(40 byte),
    FEE_NAME      varchar2(50 byte),
    FEE_TYPE      varchar2(50 byte),
    FEE_SECTYPE   varchar2(50 byte),
    CCY           varchar2(50 byte),
    AMT           number(30, 2),
    PAY_DATE      date,
    ORG_CODE      varchar2(20 byte),
    DEPT_CODE     varchar2(20 byte),
    STATUS        varchar2(50 byte),
    DATA_SOURCE   varchar2(50 byte),
    REV_DATE      date,
    UPDATE_USER   varchar2(20 byte),
    UPDATE_TIME   timestamp,
    CREATE_USER   varchar2(20 byte),
    CREATE_TIME   timestamp,
    REMARK        varchar2(1000 byte)
);

comment on table CHB_PRD_FEE_DEAL is
    '产品费用交易维护';

comment on column CHB_PRD_FEE_DEAL.SEQNO is
    '主键';
    
comment on column CHB_PRD_FEE_DEAL.TRADE_ID is
    '上游系统交易编码';

comment on column CHB_PRD_FEE_DEAL.FPROD_ID is
    '父产品代码';

comment on column CHB_PRD_FEE_DEAL.PROD_ID is
    '产品代码';

comment on column CHB_PRD_FEE_DEAL.PROD_NAME is
    '产品名称';

comment on column CHB_PRD_FEE_DEAL.REG_CODE is
    '产品登记编码';

comment on column CHB_PRD_FEE_DEAL.FEE_ID is
    '费用代码';

comment on column CHB_PRD_FEE_DEAL.FEE_NAME is
    '费用名称';

comment on column CHB_PRD_FEE_DEAL.FEE_TYPE is
    '费用类型 01:本机构费用 02:其他机构费用';

comment on column CHB_PRD_FEE_DEAL.FEE_SECTYPE is
    '01:本机构托管费 02:本机构管理费 03:本机构销售手续费 04:本机构其他产品费用 05:其他机构托管费 06:其他机构管理费 07:其他机构销售手续费 08:投资顾问费用 09:其他机构其他产品费用 10：中收费 11：浮动费';

comment on column CHB_PRD_FEE_DEAL.CCY is
    '币种';

comment on column CHB_PRD_FEE_DEAL.AMT is
    '金额';

comment on column CHB_PRD_FEE_DEAL.PAY_DATE is
    '支付日';

comment on column CHB_PRD_FEE_DEAL.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_FEE_DEAL.DEPT_CODE is
    '部门代码';

comment on column CHB_PRD_FEE_DEAL.STATUS is
    '数据状态';

comment on column CHB_PRD_FEE_DEAL.DATA_SOURCE is
    '数据来源';

comment on column CHB_PRD_FEE_DEAL.REV_DATE is
    '撤销日';

comment on column CHB_PRD_FEE_DEAL.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_FEE_DEAL.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_FEE_DEAL.CREATE_USER is
    '创建人';

comment on column CHB_PRD_FEE_DEAL.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_FEE_DEAL.REMARK is
    '备注';

create unique index PK_CHB_PRD_FEE_INFO on
    CHB_PRD_FEE_DEAL (
        SEQNO
    asc );

alter table CHB_PRD_FEE_DEAL add constraint PK_CHB_PRD_FEE_INFO primary key ( SEQNO );

create table CHB_PRD_ISSUE_SUB (
    REGIST_SEQNO             char(36 byte) not null,
    PROD_REGIST_CODE         varchar2(50 byte),
    PROD_ID                  varchar2(50 byte),
    RAISE_VDATE              date,
    RAISE_ENDDATE            date,
    PRD_VDATE                date,
    PRD_MDATE                date,
    IS_CONSTRACTE            varchar2(50 byte),
    GRADE_TYPE               varchar2(50 byte),
    EXP_MIN_YIELD            number(30, 4),
    EXP_MAX_YIELD            number(30, 4),
    EXP_AVG_YIELD            number(30, 4),
    YIELD_COMMENT            varchar2(50 byte),
    OPEN_MODE                varchar2(50 byte),
    OPEN_CIRCLE              varchar2(50 byte),
    OPEN_CIRCLE_DAYS         number(30, 4),
    RULE_COMMENT             varchar2(50 byte),
    FIRST_OPEN_DAY           date,
    HOLIDAY_ON_FLAG          varchar2(50 byte),
    AVG_OPEN_COUNT           number(30, 4),
    OPEN_BUSS                varchar2(50 byte),
    OPEN_BUSS_REMARK         varchar2(50 byte),
    WORKFLOW_ID              varchar2(50 byte),
    SUBMIT_FEEDBACK_STATUS   varchar2(50 byte),
    SEND_STATUS              varchar2(50 byte),
    STATUS_DATE              date,
    STATUS                   varchar2(50 byte),
    ORG_CODE                 varchar2(50 byte),
    CREATE_USER              varchar2(20 byte),
    CREATE_DEPT              varchar2(50 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp,
    SUBMIT_TIME              timestamp,
    CHB_SUBMIT_DEADLINE      timestamp,
    SUBMIT_DEADLINE          timestamp,
    ISSUE_PARTY_ID           varchar2(50 byte),
    SUMIT_SEQ                varchar2(50 byte)
);

comment on table CHB_PRD_ISSUE_SUB is
    '个人发行登记表';

comment on column CHB_PRD_ISSUE_SUB.REGIST_SEQNO is
    '登记序列';

comment on column CHB_PRD_ISSUE_SUB.PROD_REGIST_CODE is
    '产品登记编码';

comment on column CHB_PRD_ISSUE_SUB.PROD_ID is
    '理财产品代码';

comment on column CHB_PRD_ISSUE_SUB.RAISE_VDATE is
    '募集起始日期';

comment on column CHB_PRD_ISSUE_SUB.RAISE_ENDDATE is
    '募集结束日期';

comment on column CHB_PRD_ISSUE_SUB.PRD_VDATE is
    '产品起始日期';

comment on column CHB_PRD_ISSUE_SUB.PRD_MDATE is
    '产品终止日期';

comment on column CHB_PRD_ISSUE_SUB.IS_CONSTRACTE is
    '是否为结构化（分级）产品';

comment on column CHB_PRD_ISSUE_SUB.GRADE_TYPE is
    '层级类别      11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五';

comment on column CHB_PRD_ISSUE_SUB.EXP_MIN_YIELD is
    '预计客户最低年收益率 %';

comment on column CHB_PRD_ISSUE_SUB.EXP_MAX_YIELD is
    '预计客户最高年收益率 %';

comment on column CHB_PRD_ISSUE_SUB.EXP_AVG_YIELD is
    '平均预计客户年收益率%';

comment on column CHB_PRD_ISSUE_SUB.YIELD_COMMENT is
    '收益率分档情况说明';

comment on column CHB_PRD_ISSUE_SUB.OPEN_MODE is
    '开放模式      01 有规律开放/02 无规律开放';

comment on column CHB_PRD_ISSUE_SUB.OPEN_CIRCLE is
    '规律开放周期';

comment on column CHB_PRD_ISSUE_SUB.OPEN_CIRCLE_DAYS is
    '其他规律开放周期 (天)';

comment on column CHB_PRD_ISSUE_SUB.RULE_COMMENT is
    '无规律开放说明';

comment on column CHB_PRD_ISSUE_SUB.FIRST_OPEN_DAY is
    '首次开放周期起始日';

comment on column CHB_PRD_ISSUE_SUB.HOLIDAY_ON_FLAG is
    '节假日是否开放            01 是/02 否';

comment on column CHB_PRD_ISSUE_SUB.AVG_OPEN_COUNT is
    '平均开放次数（年化）';

comment on column CHB_PRD_ISSUE_SUB.OPEN_BUSS is
    '开放期业务';

comment on column CHB_PRD_ISSUE_SUB.OPEN_BUSS_REMARK is
    '开放期业务说明';

comment on column CHB_PRD_ISSUE_SUB.WORKFLOW_ID is
    '工作流代码';

comment on column CHB_PRD_ISSUE_SUB.SUBMIT_FEEDBACK_STATUS is
    '反馈状态 未反馈/中债通过/中债退回/发送成功/发送失败';

comment on column CHB_PRD_ISSUE_SUB.SEND_STATUS is
    '报送状态      未报送/接口报送/手工处理';

comment on column CHB_PRD_ISSUE_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_PRD_ISSUE_SUB.STATUS is
    '数据状态，      新建/修改/删除';

comment on column CHB_PRD_ISSUE_SUB.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_ISSUE_SUB.CREATE_USER is
    '创建人';

comment on column CHB_PRD_ISSUE_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_PRD_ISSUE_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_ISSUE_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_ISSUE_SUB.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_ISSUE_SUB.SUBMIT_TIME is
    '报送日期';

comment on column CHB_PRD_ISSUE_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_PRD_ISSUE_SUB.SUBMIT_DEADLINE is
    '报送截止日';

comment on column CHB_PRD_ISSUE_SUB.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_ISSUE_SUB.SUMIT_SEQ is
    '报送流水号';

alter table CHB_PRD_ISSUE_SUB add constraint CHB_PRD_ISSUE_SUB_PK primary key ( REGIST_SEQNO );

create table CHB_PRD_OPENSCH (
    SEQNO            char(36 byte) not null,
    PROD_ID          varchar2(50 byte),
    PROD_NAME        varchar2(200 byte),
    ISSUE_PARTY_ID   varchar2(50 byte),
    PLAN_TYPE        varchar2(50 byte),
    OPEN_START       date,
    OPEN_END         date,
    OPEN_VDATE       date,
    ORG_CODE         varchar2(20 byte),
    DEPT_CODE        varchar2(20 byte),
    DATA_SOURCE      varchar2(20 byte),
    STATUS           varchar2(50 byte),
    REV_DATE         date,
    UPDATE_USER      varchar2(20 byte),
    UPDATE_TIME      timestamp,
    CREATE_USER      varchar2(20 byte),
    CREATE_TIME      timestamp,
    REMARK           varchar2(1000 byte)
);

comment on table CHB_PRD_OPENSCH is
    '产品开放计划维护表';

comment on column CHB_PRD_OPENSCH.SEQNO is
    '流水号';

comment on column CHB_PRD_OPENSCH.PROD_ID is
    '产品代码';

comment on column CHB_PRD_OPENSCH.PROD_NAME is
    '产品名称';

comment on column CHB_PRD_OPENSCH.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_OPENSCH.PLAN_TYPE is
    '计划类型 01:申购计划 02：赎回计划';

comment on column CHB_PRD_OPENSCH.OPEN_START is
    '开放周期起始日';

comment on column CHB_PRD_OPENSCH.OPEN_END is
    '开放周期结束日';

comment on column CHB_PRD_OPENSCH.OPEN_VDATE is
    '开放周期起息日';

comment on column CHB_PRD_OPENSCH.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_OPENSCH.DEPT_CODE is
    '部门代码';

comment on column CHB_PRD_OPENSCH.DATA_SOURCE is
    '数据来源';

comment on column CHB_PRD_OPENSCH.STATUS is
    '数据状态';

comment on column CHB_PRD_OPENSCH.REV_DATE is
    '撤销日';

comment on column CHB_PRD_OPENSCH.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_OPENSCH.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_OPENSCH.CREATE_USER is
    '创建人';

comment on column CHB_PRD_OPENSCH.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_OPENSCH.REMARK is
    '备注';

create unique index CHB_PRD_OPENSCH_PK on
    CHB_PRD_OPENSCH (
        SEQNO
    asc );

alter table CHB_PRD_OPENSCH add constraint CHB_PRD_OPENSCH_PK primary key ( SEQNO );

create table CHB_PRD_ORG_ISSUE_SUB (
    REGIST_SEQNO             char(36 byte) not null,
    PROD_NAME                varchar2(200 byte),
    PROD_ID                  varchar2(50 byte),
    ISSUE_PARTY_ID           varchar2(50 byte),
    APPROVER_NAME            varchar2(50 byte),
    APPROVER_ID              varchar2(50 byte),
    DESIGNER_NAME            varchar2(50 byte),
    DESIGNER_ID              varchar2(50 byte),
    MANAGER_NAME             varchar2(50 byte),
    MANAGER_ID               varchar2(50 byte),
    LIAISON_NAME             varchar2(50 byte),
    LIAISON_TEL              varchar2(50 byte),
    LIAISON_MOBILE           varchar2(50 byte),
    LIAISON_EMAIL            varchar2(50 byte),
    PROFIT_TYPE              varchar2(50 byte),
    TERMTYPE                 varchar2(50 byte),
    INVESTOR_TYPE            varchar2(50 byte),
    INVEST_ARAE              varchar2(50 byte),
    INVEST_COUNTRY           varchar2(50 byte),
    FINAN_SER_MODEL          varchar2(50 byte),
    OPERATE_MODE             varchar2(50 byte),
    BOOK_MODE                varchar2(50 byte),
    CMMODE                   varchar2(50 byte),
    PMMODE                   varchar2(50 byte),
    MNAME                    varchar2(50 byte),
    PRICED_MODE              varchar2(50 byte),
    INV_ASSET_TYPE           varchar2(50 byte),
    CON_PRD_TRAGET           varchar2(50 byte),
    IS_YIELD                 varchar2(50 byte),
    IS_YIELD_MEAS_BASIS      varchar2(50 byte),
    SALE_AREA                varchar2(50 byte),
    SALE_AMT_STR             number(30, 4),
    RAISE_CCY                varchar2(50 byte),
    CASH_PRIN_CCY            varchar2(50 byte),
    CASH_PROF_CCY            varchar2(50 byte),
    SALE_FEE_RATE            number(30, 4),
    TRU_FEE_RATE             number(30, 4),
    IN_DEP_NAME              varchar2(50 byte),
    IN_DEP_CODE              varchar2(50 byte),
    OUT_DEP_CTRY             varchar2(50 byte),
    OUT_DEP_NAME             varchar2(50 byte),
    RISK_TYPE                varchar2(50 byte),
    RISK_LEVEL               varchar2(50 byte),
    TERM_RIGHT_FLAG          varchar2(50 byte),
    CUS_REDEEM_FLAG          varchar2(50 byte),
    PROD_BRAND               varchar2(50 byte),
    PROD_STAGE               number(30, 4),
    CONT_MODE                varchar2(50 byte),
    COOPERATION_NAME         varchar2(50 byte),
    INVEST_PRIN_DATE         varchar2(50 byte),
    INVEST_PROF_DATE         varchar2(50 byte),
    CREDIT_FLAG              varchar2(50 byte),
    CREDIT_TYPE              varchar2(50 byte),
    CREDIT_MODE              varchar2(50 byte),
    INV_TYPE_RAT             varchar2(300 byte),
    RAISE_PSTRDATE           date,
    RAISE_PENDDATE           date,
    PRD_VDATE                date,
    PRD_MDATE                date,
    IS_CONSTRACTE            varchar2(50 byte),
    GRADE_TYPE               varchar2(50 byte),
    EXP_MIN_YIELD            number(30, 4),
    EXP_MAX_YIELD            number(30, 4),
    EXP_AVG_YIELD            number(30, 4),
    YIELD_COMMENT            varchar2(50 byte),
    OPEN_MODE                varchar2(50 byte),
    OPEN_CIRCLE              varchar2(50 byte),
    OPEN_CIRCLE_DAYS         number(30, 4),
    RULE_COMMENT             varchar2(50 byte),
    FIRST_OPEN_DAY           date,
    HOLIDAY_ON_FLAG          varchar2(50 byte),
    AVG_OPEN_COUNT           number(30, 4),
    OPEN_BUSS                varchar2(50 byte),
    OPEN_BUSS_REMARK         varchar2(50 byte),
    REMARK                   varchar2(50 byte),
    NINE_FILE                varchar2(50 byte),
    FILE_NO                  varchar2(50 byte),
    WORKFLOW_ID              varchar2(50 byte),
    SUBMIT_FEEDBACK_STATUS   varchar2(50 byte),
    SEND_STATUS              varchar2(50 byte),
    STATUS_DATE              date,
    STATUS                   varchar2(50 byte),
    CREATE_USER              varchar2(50 byte),
    CREATE_DEPT              varchar2(50 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(50 byte),
    UPDATE_TIME              timestamp,
    SUBMIT_TIME              timestamp,
    CHB_SUBMIT_DEADLINE      timestamp,
    SUBMIT_DEADLINE          timestamp,
    ORG_CODE                 varchar2(50 byte),
    FILE_NUM                 varchar2(50 byte),
    SUMIT_SEQ                varchar2(36 byte)
);

comment on table CHB_PRD_ORG_ISSUE_SUB is
    '机构和私人银行产品发行登记';

comment on column CHB_PRD_ORG_ISSUE_SUB.REGIST_SEQNO is
    '登记序列';

comment on column CHB_PRD_ORG_ISSUE_SUB.PROD_NAME is
    '产品名称';

comment on column CHB_PRD_ORG_ISSUE_SUB.PROD_ID is
    '理财产品代码';

comment on column CHB_PRD_ORG_ISSUE_SUB.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_ORG_ISSUE_SUB.APPROVER_NAME is
    '产品审批人姓名';

comment on column CHB_PRD_ORG_ISSUE_SUB.APPROVER_ID is
    '产品审批人身份证号';

comment on column CHB_PRD_ORG_ISSUE_SUB.DESIGNER_NAME is
    '产品设计人姓名';

comment on column CHB_PRD_ORG_ISSUE_SUB.DESIGNER_ID is
    '产品设计人身份证号';

comment on column CHB_PRD_ORG_ISSUE_SUB.MANAGER_NAME is
    '投资经理姓名';

comment on column CHB_PRD_ORG_ISSUE_SUB.MANAGER_ID is
    '投资经理身份证号';

comment on column CHB_PRD_ORG_ISSUE_SUB.LIAISON_NAME is
    '业务联络人姓名';

comment on column CHB_PRD_ORG_ISSUE_SUB.LIAISON_TEL is
    '业务联络人座机';

comment on column CHB_PRD_ORG_ISSUE_SUB.LIAISON_MOBILE is
    '业务联络人手机';

comment on column CHB_PRD_ORG_ISSUE_SUB.LIAISON_EMAIL is
    '业务联络人邮箱';

comment on column CHB_PRD_ORG_ISSUE_SUB.PROFIT_TYPE is
    '产品收益类型';

comment on column CHB_PRD_ORG_ISSUE_SUB.TERMTYPE is
    '产品期限';

comment on column CHB_PRD_ORG_ISSUE_SUB.INVESTOR_TYPE is
    '目标客户类型';

comment on column CHB_PRD_ORG_ISSUE_SUB.INVEST_ARAE is
    '资金投向地区';

comment on column CHB_PRD_ORG_ISSUE_SUB.INVEST_COUNTRY is
    '产品投资国家或地区';

comment on column CHB_PRD_ORG_ISSUE_SUB.FINAN_SER_MODEL is
    '理财业务服务模式';

comment on column CHB_PRD_ORG_ISSUE_SUB.OPERATE_MODE is
    '产品运作模式';

comment on column CHB_PRD_ORG_ISSUE_SUB.BOOK_MODE is
    '会计核算方式';

comment on column CHB_PRD_ORG_ISSUE_SUB.CMMODE is
    '产品资产配置方式';

comment on column CHB_PRD_ORG_ISSUE_SUB.PMMODE is
    '产品管理模式';

comment on column CHB_PRD_ORG_ISSUE_SUB.MNAME is
    '实际管理人员称';

comment on column CHB_PRD_ORG_ISSUE_SUB.PRICED_MODE is
    '产品定价方式          01 公允价值定价/02 成本法定价/03 综合定价';

comment on column CHB_PRD_ORG_ISSUE_SUB.INV_ASSET_TYPE is
    '投资资产类型';

comment on column CHB_PRD_ORG_ISSUE_SUB.CON_PRD_TRAGET is
    '结构性产品挂钩标的';

comment on column CHB_PRD_ORG_ISSUE_SUB.IS_YIELD is
    '是否有预期收益率';

comment on column CHB_PRD_ORG_ISSUE_SUB.IS_YIELD_MEAS_BASIS is
    '是否有收益率测算依据';

comment on column CHB_PRD_ORG_ISSUE_SUB.SALE_AREA is
    '产品销售区域        参照中国城市地区分类';

comment on column CHB_PRD_ORG_ISSUE_SUB.SALE_AMT_STR is
    '起点销售金额';

comment on column CHB_PRD_ORG_ISSUE_SUB.RAISE_CCY is
    '募集币种        参考《币种枚举值》';

comment on column CHB_PRD_ORG_ISSUE_SUB.CASH_PRIN_CCY is
    '兑付本金币种        参考《币种枚举值》';

comment on column CHB_PRD_ORG_ISSUE_SUB.CASH_PROF_CCY is
    '兑付收益币种        参考《币种枚举值》';

comment on column CHB_PRD_ORG_ISSUE_SUB.SALE_FEE_RATE is
    '销售手续费率%';

comment on column CHB_PRD_ORG_ISSUE_SUB.TRU_FEE_RATE is
    '托管费率%';

comment on column CHB_PRD_ORG_ISSUE_SUB.IN_DEP_NAME is
    '境内托管机构名称';

comment on column CHB_PRD_ORG_ISSUE_SUB.IN_DEP_CODE is
    '境内托管机构代码';

comment on column CHB_PRD_ORG_ISSUE_SUB.OUT_DEP_CTRY is
    '境外托管机构国别             参考《世界各国和地区名称代码》';

comment on column CHB_PRD_ORG_ISSUE_SUB.OUT_DEP_NAME is
    '境外托管机构名称';

comment on column CHB_PRD_ORG_ISSUE_SUB.RISK_TYPE is
    '投资者风险偏好';

comment on column CHB_PRD_ORG_ISSUE_SUB.RISK_LEVEL is
    '产品风险等级          01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）';

comment on column CHB_PRD_ORG_ISSUE_SUB.TERM_RIGHT_FLAG is
    '发行机构提前终止权标识                01 有/02 无';

comment on column CHB_PRD_ORG_ISSUE_SUB.CUS_REDEEM_FLAG is
    '客户赎回权标识              01 有/02 无';

comment on column CHB_PRD_ORG_ISSUE_SUB.PROD_BRAND is
    '产品品牌';

comment on column CHB_PRD_ORG_ISSUE_SUB.PROD_STAGE is
    '产品期次';

comment on column CHB_PRD_ORG_ISSUE_SUB.CONT_MODE is
    '合作模式';

comment on column CHB_PRD_ORG_ISSUE_SUB.COOPERATION_NAME is
    '合作机构名称';

comment on column CHB_PRD_ORG_ISSUE_SUB.INVEST_PRIN_DATE is
    '投资本金到账日          01 T+0/02 T+1/03 T+2/04 T+3/05 T+4/06 T+5及以上';

comment on column CHB_PRD_ORG_ISSUE_SUB.INVEST_PROF_DATE is
    '投资收益到账日          01 T+0/02 T+1/03 T+2/04 T+3/05 T+4/06 T+5及以上';

comment on column CHB_PRD_ORG_ISSUE_SUB.CREDIT_FLAG is
    '产品增信标识            01 有/02 无';

comment on column CHB_PRD_ORG_ISSUE_SUB.CREDIT_TYPE is
    '产品增信机构类型            01 广义政府/02 非金融性公司/03 金融性公司/04 住户部门/05 国外部门';

comment on column CHB_PRD_ORG_ISSUE_SUB.CREDIT_MODE is
    '产品增信形式              01 内部增级/02 外部增级/03 内外增级';

comment on column CHB_PRD_ORG_ISSUE_SUB.INV_TYPE_RAT is
    '投资资产种类及比例';

comment on column CHB_PRD_ORG_ISSUE_SUB.RAISE_PSTRDATE is
    '募集起始日期';

comment on column CHB_PRD_ORG_ISSUE_SUB.RAISE_PENDDATE is
    '募集结束日期';

comment on column CHB_PRD_ORG_ISSUE_SUB.PRD_VDATE is
    '产品起始日期';

comment on column CHB_PRD_ORG_ISSUE_SUB.PRD_MDATE is
    '产品终止日期';

comment on column CHB_PRD_ORG_ISSUE_SUB.IS_CONSTRACTE is
    '是否为结构化（分级）产品                    01 是/02 否';

comment on column CHB_PRD_ORG_ISSUE_SUB.GRADE_TYPE is
    '层级类别        11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五';

comment on column CHB_PRD_ORG_ISSUE_SUB.EXP_MIN_YIELD is
    '预计客户最低年收益率%';

comment on column CHB_PRD_ORG_ISSUE_SUB.EXP_MAX_YIELD is
    '预计客户最高年收益率%';

comment on column CHB_PRD_ORG_ISSUE_SUB.EXP_AVG_YIELD is
    '平均预计客户年收益率%';

comment on column CHB_PRD_ORG_ISSUE_SUB.YIELD_COMMENT is
    '收益率分档情况说明';

comment on column CHB_PRD_ORG_ISSUE_SUB.OPEN_MODE is
    '开放模式        01 有规律开放/02 无规律开放';

comment on column CHB_PRD_ORG_ISSUE_SUB.OPEN_CIRCLE is
    '规律开放周期        每天开放；02 每周开放一次；03 每两周开放一次；04每月开放一次；05每季度开放一次；06 每半年开放一次；07 每年开放一次；99 其他规律开放周期';

comment on column CHB_PRD_ORG_ISSUE_SUB.OPEN_CIRCLE_DAYS is
    '其他规律开放周期(天)';

comment on column CHB_PRD_ORG_ISSUE_SUB.RULE_COMMENT is
    '无规律开放说明';

comment on column CHB_PRD_ORG_ISSUE_SUB.FIRST_OPEN_DAY is
    '首次开放周期起始日';

comment on column CHB_PRD_ORG_ISSUE_SUB.HOLIDAY_ON_FLAG is
    '节假日是否开放';

comment on column CHB_PRD_ORG_ISSUE_SUB.AVG_OPEN_COUNT is
    '平均开放次数（年化）';

comment on column CHB_PRD_ORG_ISSUE_SUB.OPEN_BUSS is
    '开放期业务';

comment on column CHB_PRD_ORG_ISSUE_SUB.OPEN_BUSS_REMARK is
    '开放期业务说明';

comment on column CHB_PRD_ORG_ISSUE_SUB.REMARK is
    '备注';

comment on column CHB_PRD_ORG_ISSUE_SUB.NINE_FILE is
    '九大附件';

comment on column CHB_PRD_ORG_ISSUE_SUB.FILE_NO is
    '文件代码';

comment on column CHB_PRD_ORG_ISSUE_SUB.WORKFLOW_ID is
    '工作流代码';

comment on column CHB_PRD_ORG_ISSUE_SUB.SUBMIT_FEEDBACK_STATUS is
    '反馈状态 未反馈/中债通过/中债退回/发送成功/发送失败';

comment on column CHB_PRD_ORG_ISSUE_SUB.SEND_STATUS is
    '报送状态';

comment on column CHB_PRD_ORG_ISSUE_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_PRD_ORG_ISSUE_SUB.STATUS is
    '数据状态';

comment on column CHB_PRD_ORG_ISSUE_SUB.CREATE_USER is
    '创建人';

comment on column CHB_PRD_ORG_ISSUE_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_PRD_ORG_ISSUE_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_ORG_ISSUE_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_ORG_ISSUE_SUB.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_ORG_ISSUE_SUB.SUBMIT_TIME is
    '报送日期';

comment on column CHB_PRD_ORG_ISSUE_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_PRD_ORG_ISSUE_SUB.SUBMIT_DEADLINE is
    '报送截止日';

comment on column CHB_PRD_ORG_ISSUE_SUB.ORG_CODE is
    '报送截止日';

comment on column CHB_PRD_ORG_ISSUE_SUB.FILE_NUM is
    '附件个数';

comment on column CHB_PRD_ORG_ISSUE_SUB.SUMIT_SEQ is
    '报送流水号';

alter table CHB_PRD_ORG_ISSUE_SUB add constraint CHB_PRD_ORG_ISSUE_SUB_PK primary key ( REGIST_SEQNO );

create table CHB_PRD_PERIODYIELD (
    SEQNO         char(36 byte) not null,
    FPROD_ID      varchar2(50 byte),
    PROD_ID       varchar2(50 byte) not null,
    CHANGE_DATE   date,
    CHANG_TYPE    varchar2(50 byte),
    PROD_VALUE    number(30, 14),
    Unit_Value_Total number(30, 14),
    ORG_CODE      varchar2(20 byte),
    DEPT_CODE     varchar2(20 byte),
    STATUS        varchar2(50 byte),
    REV_DATE      date,
    DATA_SOURCE   varchar2(20 byte),
    UPDATE_USER   varchar2(20 byte),
    UPDATE_TIME   timestamp,
    CREATE_USER   varchar2(20 byte),
    CREATE_TIME   timestamp,
    REMARK        varchar2(1000 byte)
);

comment on table CHB_PRD_PERIODYIELD is
    '产品收益率/净值变动公告维护表';

comment on column CHB_PRD_PERIODYIELD.SEQNO is
    '交易编号';

comment on column CHB_PRD_PERIODYIELD.FPROD_ID is
    '父产品代码';

comment on column CHB_PRD_PERIODYIELD.PROD_ID is
    '产品代码';

comment on column CHB_PRD_PERIODYIELD.CHANGE_DATE is
    '变动日期';

comment on column CHB_PRD_PERIODYIELD.CHANG_TYPE is
    '变动类型 01:净值变动 02:收益率变动';

comment on column CHB_PRD_PERIODYIELD.PROD_VALUE is
    '变动值';

comment on column CHB_PRD_PERIODYIELD.Unit_Value_Total is
    '单位累计净值';

comment on column CHB_PRD_PERIODYIELD.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_PERIODYIELD.DEPT_CODE is
    '部门代码';

comment on column CHB_PRD_PERIODYIELD.STATUS is
    '数据状态';

comment on column CHB_PRD_PERIODYIELD.REV_DATE is
    '撤销日';

comment on column CHB_PRD_PERIODYIELD.DATA_SOURCE is
    '数据来源';

comment on column CHB_PRD_PERIODYIELD.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_PERIODYIELD.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_PERIODYIELD.CREATE_USER is
    '创建人';

comment on column CHB_PRD_PERIODYIELD.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_PERIODYIELD.REMARK is
    '备注';

create unique index CHB_PRD_PERIODYIELD_PK on
    CHB_PRD_PERIODYIELD (
        SEQNO
    asc );

alter table CHB_PRD_PERIODYIELD add constraint CHB_PRD_PERIODYIELD_PK primary key ( SEQNO );

create table CHB_PRD_POSITION (
    CDATE            date not null,
    PROD_ID          varchar2(50 byte) not null,
    INDENTIFY_MARK   varchar2(50 byte) not null,
    ISSUE_PARTY_ID   varchar2(50 byte) not null,
    CCY              varchar2(50 byte) not null,
    TDADD_QTY        number(30, 4),
    TDADD_AMT        number(30, 2),
    TDADD_COST       number(30, 2),
    TDADD_INTAMT     number(30, 2) not null,
    BALANCE_QTY      number(30, 4),
    BALANCE_AMT      number(30, 2),
    TOTAL_INTAMT     number(30, 2),
    ORG_CODE         varchar2(20 byte),
    DEPT_CODE        varchar2(20 byte),
    CREATE_USER      varchar2(20 byte),
    CREATE_TIME      timestamp,
    REMARK           varchar2(1000 byte)
);

comment on table CHB_PRD_POSITION is
    '产品头寸表';

comment on column CHB_PRD_POSITION.CDATE is
    '生成日';

comment on column CHB_PRD_POSITION.PROD_ID is
    '产品代码';

comment on column CHB_PRD_POSITION.INDENTIFY_MARK is
    '识别标识';

comment on column CHB_PRD_POSITION.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_POSITION.CCY is
    '币种
';

comment on column CHB_PRD_POSITION.TDADD_QTY is
    '新增份额(赎回、提前到期为负，其他为正)
';

comment on column CHB_PRD_POSITION.TDADD_AMT is
    '新增金额(赎回、提前到期为负，其他为正)';

comment on column CHB_PRD_POSITION.TDADD_COST is
    '新增成本(赎回、提前到期按照移动平均算成本)';

comment on column CHB_PRD_POSITION.TDADD_INTAMT is
    '今日新增收益=金额-成本';

comment on column CHB_PRD_POSITION.BALANCE_QTY is
    '剩余份额
';

comment on column CHB_PRD_POSITION.BALANCE_AMT is
    '剩余成本';

comment on column CHB_PRD_POSITION.TOTAL_INTAMT is
    '累计收益';

comment on column CHB_PRD_POSITION.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_POSITION.DEPT_CODE is
    '部门代码
';

comment on column CHB_PRD_POSITION.CREATE_USER is
    '创建人';

comment on column CHB_PRD_POSITION.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_POSITION.REMARK is
    '备注';

create unique index CHB_PRD_POSITION_PK on
    CHB_PRD_POSITION (
        CDATE
    asc,
        PROD_ID
    asc,
        INDENTIFY_MARK
    asc,
        CCY
    asc );

alter table CHB_PRD_POSITION
    add constraint CHB_PRD_POSITION_PK primary key ( CDATE,
                                                     PROD_ID,
                                                     INDENTIFY_MARK,
                                                     CCY );

create table CHB_PRD_RAISETOT_SUB (
    REGIST_SEQNO             char(36 byte) not null,
    PROD_ID                  varchar2(50 byte),
    ISSUE_PARTY_ID           varchar2(50 byte),
    SINGLE_TOTAL             number(10),
    LEGAL_TOTAL              number(10),
    UNLEGAL_TOTAL            number(10),
    SUBSCRIBE_CCY_AMT        varchar2(1000 byte),
    SALE_AREA_AMT            varchar2(1000 byte),
    TOTAL_AMT                number(30, 2),
    RAISE_QTY                number(30, 2),
    IS_CONSIGN               varchar2(50 byte),
    CONSIGN_AMT              number(30, 2),
    REMARK                   varchar2(1000 byte),
    SEND_STATUS              varchar2(50 byte),
    SUBMIT_FEEDBACK_STATUS   varchar2(50 byte),
    STATUS_DATE              date,
    STATUS                   varchar2(50 byte),
    CREATE_USER              varchar2(20 byte),
    CREATE_DEPT              varchar2(20 byte),
    CREATE_TIME              timestamp,
    UPDATE_USER              varchar2(20 byte),
    UPDATE_TIME              timestamp,
    SUBMIT_TIME              timestamp,
    CHB_SUBMIT_DEADLINE      date,
    SUBMIT_DEADLINE          date,
    ORG_CODE                 varchar2(20 byte),
    DEPT_CODE                varchar2(32 byte)
);

comment on table CHB_PRD_RAISETOT_SUB is
    '募集总量登记';

comment on column CHB_PRD_RAISETOT_SUB.REGIST_SEQNO is
    '登记序列';

comment on column CHB_PRD_RAISETOT_SUB.PROD_ID is
    '产品代码';

comment on column CHB_PRD_RAISETOT_SUB.ISSUE_PARTY_ID is
    '发行机构代码';

comment on column CHB_PRD_RAISETOT_SUB.SINGLE_TOTAL is
    '个人投资者总数';

comment on column CHB_PRD_RAISETOT_SUB.LEGAL_TOTAL is
    '法人投资者总数';

comment on column CHB_PRD_RAISETOT_SUB.UNLEGAL_TOTAL is
    '非法人投资者总数';

comment on column CHB_PRD_RAISETOT_SUB.SUBSCRIBE_CCY_AMT is
    '认购币种及金额';

comment on column CHB_PRD_RAISETOT_SUB.SALE_AREA_AMT is
    '产品销售区域及募集金额';

comment on column CHB_PRD_RAISETOT_SUB.TOTAL_AMT is
    '实际募集金额';

comment on column CHB_PRD_RAISETOT_SUB.RAISE_QTY is
    '募集总份额';

comment on column CHB_PRD_RAISETOT_SUB.IS_CONSIGN is
    '是否代销 参照数据字典';

comment on column CHB_PRD_RAISETOT_SUB.CONSIGN_AMT is
    '代销总金额';

comment on column CHB_PRD_RAISETOT_SUB.REMARK is
    '备注';

comment on column CHB_PRD_RAISETOT_SUB.SEND_STATUS is
    '报送状态';

comment on column CHB_PRD_RAISETOT_SUB.SUBMIT_FEEDBACK_STATUS is
    '反馈状态';

comment on column CHB_PRD_RAISETOT_SUB.STATUS_DATE is
    '数据日期';

comment on column CHB_PRD_RAISETOT_SUB.STATUS is
    '数据状态 参照数据字典:STATUS';

comment on column CHB_PRD_RAISETOT_SUB.CREATE_USER is
    '创建人';

comment on column CHB_PRD_RAISETOT_SUB.CREATE_DEPT is
    '创建部门';

comment on column CHB_PRD_RAISETOT_SUB.CREATE_TIME is
    '创建时间';

comment on column CHB_PRD_RAISETOT_SUB.UPDATE_USER is
    '更新人';

comment on column CHB_PRD_RAISETOT_SUB.UPDATE_TIME is
    '更新时间';

comment on column CHB_PRD_RAISETOT_SUB.SUBMIT_TIME is
    '报送日期';

comment on column CHB_PRD_RAISETOT_SUB.CHB_SUBMIT_DEADLINE is
    '中债报送截止日';

comment on column CHB_PRD_RAISETOT_SUB.SUBMIT_DEADLINE is
    '行内报送截止日';

comment on column CHB_PRD_RAISETOT_SUB.ORG_CODE is
    '机构代码';

comment on column CHB_PRD_RAISETOT_SUB.DEPT_CODE is
    '部门代码';

create unique index CHB_PRD_RAISETOT_SUB on
    CHB_PRD_RAISETOT_SUB (
        REGIST_SEQNO
    asc );

alter table CHB_PRD_RAISETOT_SUB add constraint CHB_PRD_RAISETOT_SUB primary key ( REGIST_SEQNO );

create table CHB_PTL_VALUTION (
    CDATE                 date not null,
    PORTFOLIO_ID          varchar2(50 byte) not null,
    FINPROD_ID            varchar2(50 byte) not null,
    INV_AIM               varchar2(50 byte) not null,
    CCY                   varchar2(50 byte),
    SHARE_AMT             number(30, 4),
    DSC_COST_AMT          number(30, 14),
    DSC_CLEAN_PRICE       number(30, 14),
    BUY_COST_AMT          number(30, 14),
    BUY_CLEAN_PRICE       number(30, 14),
    MARKET_VALUE          number(30, 14),
    MARKET_CLEAN_PRICE    number(30, 14),
    TDY_INTINCEXP         number(30, 2),
    TDY_DSCINCEXP_ADD     number(30, 2),
    TDY_INTINCEXP_ADD     number(30, 2),
    TDY_DSCLOSS_ADD       number(30, 2),
    TDY_FLOAT_INGPL_ADD   number(30, 2),
    TDY_FEE_ADD           number(30, 2),
    ACCU_NET_VALUE        number(30, 14),
    CREATE_USER           varchar2(20 byte),
    CREATE_DEPT           varchar2(32 byte),
    CREATE_TIME           timestamp,
    UPDATE_USER           varchar2(20 byte),
    UPDATE_TIME           timestamp
);

comment on table CHB_PTL_VALUTION is
    '组合资产估值';

comment on column CHB_PTL_VALUTION.CDATE is
    '日期';

comment on column CHB_PTL_VALUTION.PORTFOLIO_ID is
    '组合代码';

comment on column CHB_PTL_VALUTION.FINPROD_ID is
    '金融产品代码';

comment on column CHB_PTL_VALUTION.INV_AIM is
    '投资目的';

comment on column CHB_PTL_VALUTION.CCY is
    '币种';

comment on column CHB_PTL_VALUTION.SHARE_AMT is
    '数量';

comment on column CHB_PTL_VALUTION.DSC_COST_AMT is
    '摊销总成本';

comment on column CHB_PTL_VALUTION.DSC_CLEAN_PRICE is
    '摊销成本净价';

comment on column CHB_PTL_VALUTION.BUY_COST_AMT is
    '买入总成本';

comment on column CHB_PTL_VALUTION.BUY_CLEAN_PRICE is
    '买入成本净价';

comment on column CHB_PTL_VALUTION.MARKET_VALUE is
    '市值';

comment on column CHB_PTL_VALUTION.MARKET_CLEAN_PRICE is
    '市价净价';

comment on column CHB_PTL_VALUTION.TDY_INTINCEXP is
    '当日应计利息';

comment on column CHB_PTL_VALUTION.TDY_DSCINCEXP_ADD is
    '当日发生摊销收入';

comment on column CHB_PTL_VALUTION.TDY_INTINCEXP_ADD is
    '当日发生利息收入';

comment on column CHB_PTL_VALUTION.TDY_DSCLOSS_ADD is
    '当日发生价差收入';

comment on column CHB_PTL_VALUTION.TDY_FLOAT_INGPL_ADD is
    '当日发生浮动盈亏';

comment on column CHB_PTL_VALUTION.TDY_FEE_ADD is
    '当日发生费用支出';

comment on column CHB_PTL_VALUTION.ACCU_NET_VALUE is
    '累计单位净值';

comment on column CHB_PTL_VALUTION.CREATE_USER is
    '创建人';

comment on column CHB_PTL_VALUTION.CREATE_DEPT is
    '创建部门';

comment on column CHB_PTL_VALUTION.CREATE_TIME is
    '创建时间';

comment on column CHB_PTL_VALUTION.UPDATE_USER is
    '更新人';

comment on column CHB_PTL_VALUTION.UPDATE_TIME is
    '更新时间';

create unique index CHB_PTL_VALUTION on
    CHB_PTL_VALUTION (
        CDATE
    asc,
        PORTFOLIO_ID
    asc,
        FINPROD_ID
    asc,
        INV_AIM
    asc );

alter table CHB_PTL_VALUTION
    add constraint CHB_PTL_VALUTION primary key ( CDATE,
                                                  PORTFOLIO_ID,
                                                  FINPROD_ID,
                                                  INV_AIM );

create table CHB_SECRATING (
    SECRATINGID        char(36 byte) not null,
    ASSET_CODE         varchar2(50 byte),
    REGIST_BANK_CODE   varchar2(50 byte),
    RATINGTYPE         varchar2(50 byte),
    RATINGSUBTYPE      varchar2(50 byte),
    RATINGSRC          varchar2(50 byte),
    RATINGORG          varchar2(100 char),
    RATING             varchar2(50 byte),
    PUBDATE            date,
    STATUS             varchar2(50 byte),
    CREATE_USER        varchar2(20 byte),
    CREATE_TIME        timestamp,
    UPDATE_USER        varchar2(20 byte),
    UPDATE_TIME        timestamp,
    DEPT_CODE          varchar2(20 byte),
    ORG_CODE           varchar2(20 byte)
);

comment on table CHB_SECRATING is
    '债券评级信息表';

comment on column CHB_SECRATING.SECRATINGID is
    '流水号';

comment on column CHB_SECRATING.ASSET_CODE is
    '行内资产/负债编码';

comment on column CHB_SECRATING.REGIST_BANK_CODE is
    '发行机构代码';

comment on column CHB_SECRATING.RATINGTYPE is
    '评级类型：C主体评级 I债项评级';

comment on column CHB_SECRATING.RATINGSUBTYPE is
    '长期短期：S:短期 L:长期';

comment on column CHB_SECRATING.RATINGSRC is
    '评级来源';

comment on column CHB_SECRATING.RATINGORG is
    '评级机构';

comment on column CHB_SECRATING.RATING is
    '评级结果';

comment on column CHB_SECRATING.PUBDATE is
    '发布日期';

comment on column CHB_SECRATING.STATUS is
    '数据状态  D	删除 A	待处理 E	有效';

comment on column CHB_SECRATING.CREATE_USER is
    '创建人';

comment on column CHB_SECRATING.CREATE_TIME is
    '创建时间';

comment on column CHB_SECRATING.UPDATE_USER is
    '更新人';

comment on column CHB_SECRATING.UPDATE_TIME is
    '更新时间';

comment on column CHB_SECRATING.DEPT_CODE is
    '部门代码';

comment on column CHB_SECRATING.ORG_CODE is
    '机构代码';

create unique index CHB_SECRATING_PK on
    CHB_SECRATING (
        SECRATINGID
    asc );

alter table CHB_SECRATING add constraint CHB_SECRATING_PK primary key ( SECRATINGID );

create table CHB_TYPE_MAPPING (
    MAPPINGID           char(36 byte) not null,
    SRCDATATYPE     varchar2(50 byte),
    SRCDATATYPEID   varchar2(10 byte),
    OBJDATATYPE     varchar2(50 byte),
    OBJDATATYPEID   varchar2(10 byte),
    STATUS          varchar2(10 byte),
    WORKFLOW_ID     varchar2(20 byte),
    DEPT_CODE       varchar2(32 byte),
    UPDATE_USER     varchar2(20 byte),
    UPDATE_TIME     timestamp,
    CREATE_USER     varchar2(20 byte),
    CREATE_TIME     timestamp,
    ORG_CODE        varchar2(20 byte)
);

COMMENT ON TABLE CHB_TYPE_MAPPING IS '类别映射表';

COMMENT ON COLUMN CHB_TYPE_MAPPING.MAPPINGID IS '流水号';

COMMENT ON COLUMN CHB_TYPE_MAPPING.SRCDATATYPE IS 'CHB_PROD_DEAL-产品交易表，CHB_PROD_INFO';

COMMENT ON COLUMN CHB_TYPE_MAPPING.SRCDATATYPEID IS '类别明细1';

COMMENT ON COLUMN CHB_TYPE_MAPPING.OBJDATATYPE IS '类别2';

COMMENT ON COLUMN CHB_TYPE_MAPPING.OBJDATATYPEID IS '类别明细2';

COMMENT ON COLUMN CHB_TYPE_MAPPING.STATUS IS '数据状态';

COMMENT ON COLUMN CHB_TYPE_MAPPING.WORKFLOW_ID IS '工作流代码';

COMMENT ON COLUMN CHB_TYPE_MAPPING.DEPT_CODE IS '部门代码';

COMMENT ON COLUMN CHB_TYPE_MAPPING.UPDATE_USER IS '更新人';

COMMENT ON COLUMN CHB_TYPE_MAPPING.UPDATE_TIME IS '更新时间';

COMMENT ON COLUMN CHB_TYPE_MAPPING.CREATE_USER IS '创建人';

COMMENT ON COLUMN CHB_TYPE_MAPPING.CREATE_TIME IS '创建时间';

COMMENT ON COLUMN CHB_TYPE_MAPPING.ORG_CODE IS '机构代码';

create unique index CHB_TYPE_MAPPING_PK on
    CHB_TYPE_MAPPING (
        MAPPINGID
    asc );
    
alter table CHB_TYPE_MAPPING add constraint CHB_TYPE_MAPPING_PK primary key ( MAPPINGID );

/*创建交易编码序列 add by weiyuqiang 2019-2-20 start*/
CREATE SEQUENCE SEQ_INVEST_TRADE_SUB MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  CYCLE ;
/*创建交易编码序列 add by weiyuqiang 2019-2-20 end*/
 /*创建交易对手表 add by yuluan 2019-2-25 start*/
drop table CHB_MST_COUNTER_PARTY  CASCADE CONSTRAINTS;
  CREATE TABLE CHB_MST_COUNTER_PARTY 
   (	COUNTER_ID VARCHAR2(32 BYTE) NOT NULL ENABLE, 
	COUNTER_TYPE VARCHAR2(50 BYTE), 
	COUNTER_NAME VARCHAR2(200 BYTE), 
	LINK_ID VARCHAR2(32 BYTE), 
	MANAGER_ID VARCHAR2(32 BYTE), 
	HEAD_BANK VARCHAR2(32 BYTE), 
	CONTACT VARCHAR2(200 BYTE), 
	CONTACT_WAY VARCHAR2(1000 BYTE), 
	REMARK VARCHAR2(1000 BYTE), 
	CREATE_USER VARCHAR2(20 BYTE) , 
	CREATE_DEPT VARCHAR2(32 BYTE), 
	CREATE_TIME TIMESTAMP (6)  , 
	UPDATE_USER VARCHAR2(20 BYTE), 
	UPDATE_TIME TIMESTAMP (6) , 
	 CONSTRAINT PK_CHB_MST_COUNTER_PARTY PRIMARY KEY (COUNTER_ID)
 
   )  ;
 

   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.COUNTER_ID IS '交易对手代码';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.COUNTER_TYPE IS '交易对手类型，市场机构、资管产品、通道';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.COUNTER_NAME IS '交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.LINK_ID IS '关联代码，市场机构关联机构信息、资管产品无，通道关联标的类金融产品';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.MANAGER_ID IS '管理人，资管产品时录入';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.HEAD_BANK IS '所属总行，对应客户信息表的客户代码';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.CONTACT IS '联系人';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.CONTACT_WAY IS '联系方式';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.REMARK IS '备注';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.CREATE_USER IS '创建人';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.CREATE_DEPT IS '创建部门';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.CREATE_TIME IS '创建时间';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.UPDATE_USER IS '更新人';
 
   COMMENT ON COLUMN CHB_MST_COUNTER_PARTY.UPDATE_TIME IS '更新时间';
 
   COMMENT ON TABLE CHB_MST_COUNTER_PARTY  IS '交易对手';
 /*创建交易对手表 add by yuluan 2019-2-25 end*/
   

/*中债接口数据更新时间表 add by zhangzhongliang 2019-2-26 start*/
DROP TABLE CHB_INTERF_CONFIG;
CREATE TABLE CHB_INTERF_CONFIG (	
    INTERF_ID VARCHAR2(50 BYTE) not null, 
	LAST_UPDATETIME TIMESTAMP (6), 
	REMARK VARCHAR2(50 BYTE)
);

COMMENT ON COLUMN CHB_INTERF_CONFIG.INTERF_ID IS '接口代码';
 
COMMENT ON COLUMN CHB_INTERF_CONFIG.LAST_UPDATETIME IS '最近更新时间';

COMMENT ON COLUMN CHB_INTERF_CONFIG.REMARK IS '备注';

COMMENT ON TABLE CHB_INTERF_CONFIG  IS '中债接口数据更新时间表';
/*中债接口数据更新时间表 add by zhangzhongliang 2019-2-26 start*/

/*产品要素表和开放计划表添加产品id索引 add by jiangxiao 2019-03-08 start*/
CREATE INDEX "CHB_PRD_BASEINFO_INDEX" ON "CHB_PRD_BASEINFO" ("PROD_ID");
CREATE INDEX "CHB_PRD_OPENSCH_INDEX" ON "CHB_PRD_OPENSCH" ("PROD_ID");
CREATE INDEX "CHB_PRD_DEAL_INDEX_PD" ON "CHB_PRD_DEAL" ("PROD_ID","SETTDATE_DATE");
/*产品要素表和开放计划表添加产品id索引 add by jiangxiao 2019-03-08 end*/
/*创建债券评级信息索引 add by weiyuqiang 2019-03-08 start*/
CREATE INDEX CHB_SECRATING_INDEX ON CHB_SECRATING(ASSET_CODE,PUBDATE,RATINGORG,RATINGSUBTYPE,RATINGTYPE);
/*创建债券评级信息索引 add by weiyuqiang 2019-03-08 end*/

/*底层资产持仓数量字段小数位 add by zhangzhongliang 2019-03-25 start*/
alter table CHB_BOT_AST_POS modify (TOTAL_NUM number(25,5));
alter table CHB_BOT_AST_POS modify (POS_NUM number(25,5));
alter table CHB_BOT_AST_POS_SUB modify (TOTAL_NUM number(25,5));
alter table CHB_BOT_AST_POS_SUB modify (POS_NUM number(25,5));
/*底层资产持仓数量字段小数位 add by zhagzhongliang 2019-03-25 end*/

/*申报登记升级  add by zhangzhongliang 2019-03-23 start*/
ALTER TABLE CHB_PRD_DECLARE_SUB ADD (RAISE_TYPE VARCHAR2(50 BYTE));
comment on column CHB_PRD_DECLARE_SUB.RAISE_TYPE is 
    '募集方式 01公募 02私募';
ALTER TABLE CHB_PRD_DECLARE_SUB ADD (IVSTORIENTATION VARCHAR2(2 BYTE));
comment on column CHB_PRD_DECLARE_SUB.IVSTORIENTATION is
    '投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类';
ALTER TABLE CHB_PRD_DECLARE_SUB ADD (EXC_FIN_SECTOR VARCHAR2(2 BYTE));
comment on column CHB_PRD_DECLARE_SUB.EXC_FIN_SECTOR is
    '是否金融同业专属';
ALTER TABLE CHB_PRD_DECLARE_SUB ADD (IS_SHORT_TERM VARCHAR2(2 BYTE));
comment on column CHB_PRD_DECLARE_SUB.IS_SHORT_TERM is
    '是否设置最短持有期限';
ALTER TABLE CHB_PRD_DECLARE_SUB ADD (SHORT_TERM NUMBER(30));
comment on column CHB_PRD_DECLARE_SUB.SHORT_TERM is
    '最短持有期限(天)';
ALTER TABLE CHB_PRD_DECLARE_SUB ADD (IS_FREE_REDEM VARCHAR2(2 BYTE));
comment on column CHB_PRD_DECLARE_SUB.IS_FREE_REDEM is
    '最短持有期后是否自由赎回';
ALTER TABLE CHB_PRD_DECLARE_SUB ADD (CASH_MANA_CATE VARCHAR2(2 BYTE));
comment on column CHB_PRD_DECLARE_SUB.CASH_MANA_CATE is
    '是否现金管理类';
ALTER TABLE CHB_PRD_DECLARE_SUB ADD (YIELD NUMBER(8,7));
comment on column CHB_PRD_DECLARE_SUB.YIELD is
    '业绩比较基准%';
ALTER TABLE CHB_PRD_DECLARE_SUB ADD (INVEST_FEE_RATE NUMBER(8,7));
comment on column CHB_PRD_DECLARE_SUB.INVEST_FEE_RATE is
    '投资管理费率%';
    

ALTER TABLE CHB_PRD_BASEINFO ADD (EXC_FIN_SECTOR VARCHAR2(2 BYTE));
comment on column CHB_PRD_BASEINFO.EXC_FIN_SECTOR is
    '是否金融同业专属';
ALTER TABLE CHB_PRD_BASEINFO ADD (IS_SHORT_TERM VARCHAR2(2 BYTE));
comment on column CHB_PRD_BASEINFO.IS_SHORT_TERM is
    '是否设置最短持有期限';
ALTER TABLE CHB_PRD_BASEINFO ADD (SHORT_TERM NUMBER(30));
comment on column CHB_PRD_BASEINFO.SHORT_TERM is
    '最短持有期限(天)';
ALTER TABLE CHB_PRD_BASEINFO ADD (IS_FREE_REDEM VARCHAR2(2 BYTE));
comment on column CHB_PRD_BASEINFO.IS_FREE_REDEM is
    '最短持有期后是否自由赎回';
ALTER TABLE CHB_PRD_BASEINFO ADD (CASH_MANA_CATE VARCHAR2(2 BYTE));
comment on column CHB_PRD_BASEINFO.CASH_MANA_CATE is
    '是否现金管理类';
ALTER TABLE CHB_PRD_BASEINFO ADD (YIELD NUMBER(30,14));
comment on column CHB_PRD_BASEINFO.YIELD is
    '业绩比较基准%';
ALTER TABLE CHB_PRD_BASEINFO ADD (INVEST_FEE_RATE NUMBER(30,14));
comment on column CHB_PRD_BASEINFO.INVEST_FEE_RATE is
    '投资管理费率%';
/*申报登记升级  add by zhangzhongliang 2019-03-23 end*/
    
/*中债一二期修改资产要素字段 add by weiyuqiang 2019-3-28 start*/
ALTER TABLE CHB_ASSET_INFO MODIFY BANK_NAME VARCHAR2(600 BYTE);
ALTER TABLE CHB_ASSET_INFO_SUB MODIFY BANK_NAME VARCHAR2(600 BYTE);
/*中债一二期修改资产要素字段 add by weiyuqiang 2019-3-28 end*/
/*中债一二期修改资产交易字段 add by weiyuqiang 2019-4-8 end*/
ALTER TABLE CHB_INVEST_TRADE MODIFY TRADE_COUNTER VARCHAR2(200 BYTE);
alter table chb_invest_trade_sub modify TRADE_COUNTER VARCHAR2(200 BYTE);
/*中债一二期修改资产交易字段 add by weiyuqiang 2019-4-8 end*/
/*修改中债表字段 add by weiyuqiang 20190-04-25 start*/
alter table chb_prd_fee_deal modify fee_name varchar2(200);
alter table chb_invest_trade_sub modify fee_name varchar2(200);
/*修改中债表字段 add by weiyuqiang 20190-04-25 end*/