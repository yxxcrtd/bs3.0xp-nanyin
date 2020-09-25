/*
 * @Description: 与后端的接口及前端的form地址
 * @Author: Ma/Ming
 * @Email: mma@jixunet.com
 * @Date: 2019-01-03 12:06:53
 * @LastEditTime: 2019-03-08 16:50:53
 */
var constlist = {
  SMFCONFIG: ["0"], //证券主文件来源
  FXTRADESCONFIG: ["0"], //即期换汇数据来源
  SMFSOURCE: "0",
  ASSETTYPES:["F10", "F30", "60"],
  FXCONFIG: "0", //汇率创建来源
  FXCONFIGFIND: ["0"], //汇率创建来源
  TRADECONFIG: "0", //交易数据来源
  CATRADECONFIG: "0", //公司行为数据来源
  TRADECASHCONFIG: "0", //现金划拨数据来源
  ASSETTYPE: "107", //证券主文件远期资产类别
  SWAPASSETTYPE: "108", //证券主文件利率互换资产类别
  ASSETMAJOR: "制造业", //证券主文件创建中的资产主类别
  ASSETMINOR: "制造业1", //证券主文件创建中的资产次类别
  CURRENCY: "23", //货币
  HOLIDAY: "4", //节假日
  COUNTRY: "40", //国家
  ACCRUENINCOME: "0", //交易带入利息
  MARKET: "2", //市场类型：二级市场
  PRICECONFIG: "0", //价格数据来源
  PRICEList: ["0"], //价格数据List
  expireFrequency: "66", //付款频率为到期
  scheduledFrequency: "84", //付款频率为计划
  TRADEFORWARDCONFIG: "0", //远期换汇数据来源
  STARTDATE: "2019-01-01", //交易费用创建中的现金流开始生效日
  NULL: ""
};

var funclist = {
  getBtnSession: {
    header: "/info/getSessionBtn.do",
    inputs: {
      buttonId: "buttonId"
    }
  },
  coaList: {
    header: "/config/coaList.do"
  },
  dbTest: {
    header: "dbTest",
    inputs: {
      dbURL: "dbURL",
      dbFormat: "dbFormat",
      dbUser: "dbUser",
      dbPwd: "dbPwd"
    }
  },
  currencyList: {
    header: "config/currencyList.do",
    inputs: {
      all: ":false"
    }
  },
  newMajorAsset: { //证券主文件资产类别主类别
    header: "config/newMajorAsset.do",
    inputs: {
      id: "assetType"
    }
  },
  newMinorAsset: { //证券主文件资产类别次类别
    header: "config/newMinorAsset.do",
    inputs: {
      id: "assetType"
    }
  },
  newAssets: { //证券主文件资产类别
    header: "config/newAssets.do",
    inputs: {
      assetID: "type"
    }
  },
  portfolioCurrency: {
    header: "port/portfolioCurrency.do",
    inputs: {
      id: "port"
    }
  },
  bankAccountCurrency: {
    //结算货币或一级结算货币或多头货币
    header: "config/bankAccountCurrency.do",
    inputs: {
      id: "bankAcct"
    }
  },
  bankAccountCurrency2: {
    //二级结算货币或空头货币
    header: "config/bankAccountCurrency.do",
    inputs: {
      id: "bankAcct2"
    }
  },
  autoMapGlGroupForJoyin: {//外汇掉期开仓自动获取总帐组
	    header: "config/autoMapGlGroupForJoyin.do",
	    inputs: {
	    	assetType: "assetType",
	    	curr: "incurrency2",
	    	curr2: "incurrency"
	    }
	  },
  bankAccountFeeCurrency: {
    //交易费用获取费用货币
    header: "config/bankAccountCurrency.do",
    inputs: {
      id: "bankAccount"
    }
  },
  portfolioAccountShortList: {
    //通过投资组合直接获取现金账户
    header: "port/portfolioAccountShortList.do",
    inputs: {
      portID: "port"
    }
  },
  smfTradeCurrency: {
    header: "market/smfTradeCurrency.do",
    inputs: {
      id: "secid"
    }
  },
  isPaywithTrade: {//根据费用金额自动返回是否随交易支付
	    header: "trade/isPaywithTrade.do",
	    inputs: {
	    	expense: "expense"
	    }
	  },
  getTradeFeeDtoByIDandAmount: {//根据费用金额自动计算费用化金额
	    header: "trade/getTradeFeeDtoByIDandAmount.do",
	    inputs: {
	    	expense: "expense",
	    	totalAmount: "totalAmount"
	    }
	  },
  calcTradeExpenses: {
    header: "trade/newCalcTradeExpenses.do",
    inputs: {
      port: "port:0",
      expenses: "tablefee"
    }
  },
  calcTradeExpenses2: {
	    header: "trade/newCalcTradeExpenses2.do",
	    inputs: {
	      port: "port:0",
	      expenses: "tablefee"
	    }
	  },
  valuationSides: {
    //估值方向
    header: "trade/valuationSide.do",
    inputs: {}
  },
  currencyAllList: {
    header: "config/currencyList.do",
    inputs: {
      all: ":true"
    }
  },
  portType: {
    header: "port/portfolioTypeList.do",
    inputs: {}
  },
  bankList: {
    header: "config/bankList.do",
    inputs: {}
  },
  bankCreate: {
    header: "config/bankCreate.do",
    inputs: {
      json: {
        header: ":bankInfoMsg",
        operation: ":ADD",
        code: "code",
        name: "name",
        country: "country",
        contact: "contact",
        address: "address",
        tel: "tel",
        fax: "fax"
      },
      operType: ":new"
    }
  },
  bankDetail: {
    header: "config/bankDetail_new.do",
    inputs: {
      id: "bank_id"
    }
  },
  bankUpdate: {
    header: "config/bankUpdate.do",
    inputs: {
      id: "bank_id",
      json: {
        header: ":bankInfoMsg",
        operation: ":UPDATE",
        id: "bank_id",
        code: "code",
        name: "name",
        country: "country",
        contact: "contact",
        address: "address",
        tel: "tel",
        fax: "fax"
      }
    }
  },
  bankAccountCreate: {
    header: "config/bankAccountCreate.do",
    inputs: {
      coa: ":[]",
      json: {
        header: ":bankAccountMsg",
        acct: "acct",
        bank: "bank_id",
        code: "code",
        desc: "desc",
        currency: "currency",
        status: "status",
        settlement: "settlement",
        port: "port",
        interest: "interest",
        major: ":true"
      }
    }
  },
  bankAccountDetail: {
    header: "config/bankAccountDetail_new.do",
    inputs: {
      id: "bankAccount_id"
    }
  },
  bankAccountUpdate: {
    header: "config/bankAccountUpdate.do",
    inputs: {
      id: "bankAccount_id",
      json: {
        header: ":bankAccountMsg",
        id: "bankAccount_id",
        operation: ":UPDATE",
        acct: "acct",
        bank: "bank_id",
        code: "code",
        desc: "desc",
        currency: "currency",
        status: "status",
        settlement: "settlement1",
        port: "port",
        interest: "interest",
        balance: "balance",
        date: "date"
      }
    }
  },
  bankAccountList: {
    header: "config/bankAccountList.do",
    inputs: {
      bank: "bank_id"
    }
  },
  bankCOADetail: {
    header: "config/bankCOADetail_new.do",
    inputs: {
      id: "bankAccount_id"
    }
  },
  bankInterestList: {
    header: "config/bankInterestList.do",
    inputs: {
      bank: "bank_id"
    }
  },
  bankAccountStatus: {
    header: "config/bankAcctStatusOption.do",
    inputs: {}
  },
  bankInterestListWithNo: {
    header: "config/bankInterestListWithNo.do",
    inputs: {
      bank: "bank_id"
    }
  },
  bankAccountCats: {
    header: "config/bankAccountCats.do",
    inputs: {}
  },

  bankAccountCatById: {
    header: "config/bankAccountCatById.do",
    inputs: { category: "category" }
  },
  bankCOACreate: {
    header: "config/bankCOACreate_new.do",
    // 这种最好也支持：inputs:{"pid":"bankAccount_id","params":["category","acct"]}
    inputs: {
      pid: "bankAccount_id",
      category: "category",
      bankacct_acct: "bankacct_acct"
    }
  },
  bankCOAUpdate: {
    header: "config/bankCOAUpdate_new.do",
    inputs: {
      pid: "bankAccount_id",
      id: "category",
      category: "category",
      bangkacct_acct: "bangkacct_acct"
    }
  },
  bankCOARemove: {
    header: "config/bankCOARemove.do",
    inputs: {
      pid: "bankAccount_id",
      id: "category"
    }
  },
  settleCurrencyTypes: {
    header: "config/AcctSetCurrencyOption.do",
    inputs: {}
  },
  interestSettleMethods: {
    header: "config/IntCpnDateConvOption.do",
    inputs: {}
  },
  interestDayCount: {
    header: "config/IntIntInitCodeList.do",
    inputs: {}
  },
  interestPeriods: {
    header: "config/IntPeriodConvOption.do",
    inputs: {}
  },
  interestFrequency: {
    header: "config/IntCpnfReqTypeOption.do",
    inputs: {}
  },
  interestMonth1: {
    header: "config/bankInterestMonth.do",
    inputs: {
      freq: "frequency"
    }
  },
  portShortList: {
    header: "port/portfolioShortList.do",
    inputs: {
      client: ":all"
    }
  },
  acctList: {
    header: "config/acctList_new.do",
    inputs: {
      portID: "port"
    }
  },
  bankInterestCreate: {
    header: "config/bankInterestCreate.do",
    inputs: {
      rates: ":[]",
      json: {
        header: ":bankInterestMsg",
        code: "code",
        name: "name",
        bank: "bank_id",
        delayDays: "delayDays",
        settleMethod: "settleMethod",
        dayCounts: "dayCounts",
        period: "period",
        frequency: "frequency",
        month: "interestMonth",
        day: "day"
      }
    }
  },
  bankInterestList: {
    header: "config/bankInterestList.do",
    inputs: {
      bank: "bank_id"
    }
  },
  bankInterestDetail: {
    header: "config/bankInterestDetail_new.do",
    inputs: {
      id: "interest_id"
    }
  },
  bankInterestUpdate: {
    header: "config/bankInterestUpdate.do",
    inputs: {
      id: "interest_id",
      json: {
        header: ":bankInterestMsg",
        id: "interest_id",
        operation: ":UPDATE",
        code: "code",
        name: "name",
        bank: "bank_id",
        delayDays: "delayDays",
        settleMethod: "settleMethod",
        dayCounts: "dayCounts",
        period: "period",
        frequency: "frequency",
        month: "interestMonth",
        day: "day"
      }
    }
  },
  rateCreate: {
    header: "config/rateCreate_new.do",
    inputs: {
      interestID: "interest_id",
      startDate: "startDate",
      rate: "rate"
    }
  },
  rateList: {
    header: "config/rateList.do",
    inputs: {
      id: "interest_id"
    }
  },
  rateDetail: {
    header: "config/rateDetail.do",
    inputs: {
      interestID: "interest_id",
      rateID: "rate_id"
    }
  },
  rateUpdate: {
    header: "config/rateUpdate_new.do",
    inputs: {
      pid: "interest_id",
      id: "rate_id",
      startDate: "startDate",
      rate: "rate"
    }
  },
  rateRemove: {
    header: "config/rateRemove.do",
    inputs: {
      id: "rate_id"
    }
  },
  countryList: {
    header: "config/countryList.do",
    inputs: {}
  },
  allBrokers: {
    header: "config/allBrokers.do",
    inputs: {}
  },
  brokerCreate: {
    header: "config/brokerCreate_new.do",
    inputs: {
      json: {
        header: ":jxBrokerAcctDtoMsg",
        operation: ":ADD",
        code: "code",
        bic: "bic",
        fin: "fin",
        name: "name",
        country: "country",
        address: "address",
        contact: "contact",
        tel: "tel",
        fax: "fax"
      },
      operType: ":NEW"
    }
  },
  brokerUpdate: {
    header: "config/brokerUpdate_new.do",
    inputs: {
      id: "broker_id",
      json: {
        header: ":jxBrokerAcctDtoMsg",
        operation: ":UPDATE",
        code: "code",
        bic: "bic",
        fin: "fin",
        name: "name",
        country: "country",
        address: "address",
        contact: "contact",
        tel: "tel",
        fax: "fax"
      },
      operType: ":MODIFY"
    }
  },
  brokerDetail: {
    header: "config/brokerDetail_new.do",
    inputs: {
      id: "broker_id"
    }
  },
  secAcctsInBroker: {
    header: "config/secAcctsInBroker.do",
    inputs: {
      pid: "broker_id"
    }
  },
  glList: {
    header: "config/glList.do",
    inputs: {}
  },
  newGlDetail: {
    header: "config/newGlDetail.do",
    inputs: { coaID: "coa_id", id: "glgroup_id" }
  },
  newGlColList: {
    header: "config/newGlColList.do",
    inputs: {}
  },
  secAcctCreate: {
    header: "config/secAcctCreate.do",
    inputs: {
      pid: "broker_id",
      secCash: ":[]",
      params: ["acct", "broker_id", "code", "desc", "port", "gl", "majorAcct"]
    }
  },
  secAcctDetail: {
    header: "config/secAcctDetail_new.do",
    inputs: {
      id: "secacct_id"
    }
  },
  secAcctUpdate: {
    header: "config/secAcctUpdate_new.do",
    inputs: {
      id: "secacct_id",
      json: {
        acct: "acct",
        broker_id: "broker_id",
        code: "code",
        desc: "desc",
        port: "port",
        gl: "gl",
        majorAcct: "majorAcct"
      }
    }
  },

  unMapBankAccountList: {
    header: "config/unMapBankAccountList.do",
    inputs: {
      bank: "seccash_bank"
    }
  },
  secCashTypes: {
    header: "config/secCashTypes.do",
    inputs: {}
  },
  secCashDetailList: {
    header: "config/secCashDetailList.do",
    inputs: {
      id: "secacct_id"
    }
  },
  secCashCreate: {
    header: "config/newSecCashCreate.do",
    inputs: {
      pid: "secacct_id",
      params: ["seccash_bank", "seccash_bankAccount", "seccash_type"]
    }
  },
  secCashDetail: {
    header: "config/secCashDetail_new.do",
    inputs: {
      secAcctID: "secacct_id",
      secCashID: "seccash_id"
    }
  },
  secCashRemove: {
    header: "config/secCashRemove.do",
    inputs: {
      id: "seccash_id"
    }
  },
  seatNumbers: {
    header: "config/seatNumbers.do",
    inputs: {
      broker: "broker_id"
    }
  },
  seatNumberCreate: {
    header: "config/seatNumberCreate.do",
    inputs: {
      broker: "broker_id",
      params: ["seatnum_code", "seatnum_name", "seatnum_desc"],
      json: {
        header: ":assembleSeatNumber",
        operation: ":ADD",
        broker: "broker_id",
        acctnumber: "seatnum_code",
        acctname: "seatnum_name",
        acctDesc: "seatnum_desc"
      }
    }
  },
  seatNumberUpdate: {
    header: "config/seatNumberUpdate.do",
    inputs: {
      id: "seatnum_id",
      params: ["seatnum_code", "seatnum_name", "seatnum_desc"],
      json: {
        header: ":assembleSeatNumber",
        operation: ":UPDATE",
        id: "seatnum_id",
        acctnumber: "seatnum_code",
        acctname: "seatnum_name",
        acctDesc: "seatnum_desc"
      }
    }
  },
  seatNumberRemove: {
    header: "config/seatNumberRemove.do",
    inputs: {
      id: "seatnum_id",
      json: {
        header: ":assembleSeatNumber",
        operation: ":DELETE",
        id: "seatnum_id"
      }
    }
  },
  issuers: {
    header: "config/issuers.do",
    inputs: {
      name: "issuer_filter",
      num: ":5"
    }
  },
  newIssuers: {
    header: "config/newIssuers.do",
    inputs: {
      name: "issuer_filter",
      num: ":1"
    }
  },
  issuerCreateNew: {
    header: "config/issuerCreate.do",
    inputs: {
      json: {
        header: ":jxIssuerDtoMsg",
        operation: ":ADD",
        name: "name",
        lname: "lname",
        country: "country",
        type: ":21",
        major: ":测试行业",
        minor: ":测试行业1"
      },
      operType: ":NEW"
    }
  },
  issuerUpdate: {
    header: "config/issuerUpdate.do",
    inputs: {
      json: {
        header: ":jxIssuerDtoMsg",
        operation: ":UPDATE",
        id: "issuer_id",
        name: "name",
        lname: "lname",
        country: "country",
        type: ":21",
        major: ":测试行业",
        minor: ":测试行业1"
      },
      operType: ":MODIFY"
    }
  },
  issuerRemove: {
    header: "config/newIssuerRemove.do",
    inputs: {
      json: {
        header: ":jxIssuerDtoMsg",
        operation: ":DELETE",
        id: "issuer_id"
      },
      operType: ":MODIFY"
    }
  },
  countryCode: { //发行商显示国家
    header: "config/countryCode.do",
    inputs: { country: "country" }
  },
  glCreate: {
    header: "config/glCreate.do",
    inputs: {
      coaID: ":",
      glName: "glname"
    }
  },
  holidayCreate: {
    header: "config/holidayCreate.do",
    inputs: {
      name: "holidayname"
    }
  },
  glCopy: {
    header: "config/glCopy.do",
    inputs: {
      coaID: "coa_id",
      copyGl: "glgroup_id",
      coaName: "glname"
    }
  },
  glListInCOA: {
    header: "config/glListInCOA.do",
    inputs: {
      coaID: ":"
    }
  },
  glDetail: {
    header: "config/glDetail.do",
    inputs: {
      coaID: ":",
      id: "glgroup_id"
    }
  },
  glColList: {
    header: "config/glColList.do",
    inputs: {}
  },
  glColCreate: {
    header: "config/glColCreate.do",
    inputs: {
      pid: "glgroup_id",
      coaID: "coa_id",
      params: ["glCol", "acct"]
    }
  },
  glColDetail: {
    header: "config/glColDetail.do",
    inputs: {
      coaID: ":coa_id",
      glID: "glgroup_id",
      glColID: "glCol_id"
    }
  },
  acctListInCOA: {
    header: "config/acctListInCOA_new.do",
    inputs: {
      coaID: "coa_id"
    }
  },
  glColUpdate: {
    header: "config/glColUpdate.do",
    inputs: {
      pid: "glgroup_id",
      coaID: "coa_id",
      id: "glCol_id",
      params: ["glCol", "acct"]
    }
  },
  glColRemove: {
    header: "config/glColRemove.do",
    inputs: {
      coaID: "coa_id",
      pid: "glgroup_id",
      id: "glCol_id"
    }
  },
  fxList: {
    header: "market/ex_new.do",
    inputs: {
      config: "FXCONFIGFIND",
      baseCurrency: "baseCurrency",
      localCurrency: "localCurrency",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  fxDetail: {
    header: "market/exDetail_new.do",
    inputs: {
      id: "fx_id"
    }
  },
  fxCreate: {
    header: "market/exCreate.do",
    inputs: {
      json: {
        header: ":assembleFxRateMsg",
        operation: ":ADD",
        date: "fx_date",
        local: "localCurrency",
        functional: "funcCurrency",
        rate: "rate",
        source: "FXCONFIG"
      }
    }
  },
  fxUpdate: {
    header: "market/exUpdate.do",
    inputs: {
      id: "fx_id",
      json: {
        header: ":assembleFxRateMsg",
        id: "fx_id",
        operation: ":UPDATE",
        date: "fx_date",
        local: "localCurrency",
        functional: "funcCurrency",
        rate: "rate",
        source: "FXCONFIG"
      }
    }
  },
  fxRemove: {
    header: "market/exRemove.do",
    inputs: {
      id: "fx_id"
    }
  },
  allAssets: {
    header: "trade/allAssets.do",
    inputs: {
      all: ":false"
    }
  },
  fxAssets: {
    header: "trade/fxAssets.do",
    inputs: {
      all: ":false"
    }
  },
  PriceDecideTypeOption: {
    header: "market/PriceDecideTypeOption.do",
    inputs: {}
  },
  priceList: {
    header: "market/price.do",
    inputs: {
      config: "PRICEList",
      smf: "smf:NULL",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  priceCreate: {
    header: "market/priceCreate.do",
    inputs: {
      json: {
        header: ":assemblePriceMsg",
        operation: ":ADD",
        priceto: "priceto",
        security: "security",
        date: "date",
        price: "price",
        yield: "yield",
        source: "PRICECONFIG"
      }
    }
  },
  priceUpdate: {
    header: "market/priceUpdate.do",
    inputs: {
      json: {
        header: ":assemblePriceMsg",
        operation: ":UPDATE",
        priceto: "priceto",
        security: "secid",
        date: "date",
        id: "price_id",
        price: "price",
        yield: "yield",
        source: "PRICECONFIG"
      }
    }
  },
  smf_stockList: {
    header: "market/newSmf.do",
    inputs: {
      config: "SMFCONFIG",
      type: "type",
      smf: "smf",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  smf_swapList: {
    header: "market/newSmf.do",
    inputs: {
      config: "SMFCONFIG",
      type: "type",
      smf: "smf",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  smf_forwardList: {
    header: "market/newSmf.do",
    inputs: {
      config: "SMFCONFIG",
      type: "type",
      smf: "smf",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  smf_common_fund_List: {
    header: "market/newSmf.do",
    inputs: {
      config: "SMFCONFIG",
      type: "type",
      smf: "smf",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  smf_money_fund_List: {
    header: "market/newSmf.do",
    inputs: {
      config: "SMFCONFIG",
      type: "type",
      smf: "smf",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  smf_futuresList: {
    header: "market/newSmf.do",
    inputs: {
      config: "SMFCONFIG",
      type: "type",
      smf: "smf",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  smf_optionList: {
    header: "market/newSmf.do",
    inputs: {
      config: "SMFCONFIG",
      type: "type",
      smf: "smf",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  exchangeList: {
    header: "config/exchangeList.do",
    inputs: {
      all: ":false"
    }
  },
  issuerList: {
    header: "config/issuerList.do",
    inputs: {}
  },
  smf_stockCreate: {
    header: "market/smfCreate.do",
    inputs: {
      json: {
        header: ":smfOperation",
        operation: ":ADD",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: "incurrency",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  smf_common_fund_Create: {
    header: "market/smfCreate.do",
    inputs: {
      json: {
        header: ":smfOperation",
        operation: ":ADD",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: "incurrency",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  smf_money_fund_Create: {
    header: "market/smfCreate.do",
    inputs: {
      json: {
        header: ":smfOperation",
        operation: ":ADD",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: "incurrency",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        cashflowcfg: "",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  smf_futuresCreate: {
    header: "market/smfCreate.do",
    inputs: {
      json: {
        header: ":smfOperation",
        operation: ":ADD",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        futuresType: "futuresType",
        futuresTarget: "futuresTarget",
        secContractSize: "secContractSize",
        futuresPriceUnit: "futuresPriceUnit",
        marginRate: "marginRate",
        secExpireDate: "secExpireDate",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: "incurrency",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  smfDetail: {
    header: "market/smfDetail.do",
    inputs: {
      id: "smf_id"
    }
  },
  priceDetail: {
    header: "market/priceDetail.do",
    inputs: {
      id: "price_id"
    }
  },
  tradeDetail: {//投资组合交易查看
    header: "trade/tradeDetail.do",
    inputs: {
      id: "trade_id",
      type: "tradetypeCode"
    }
  },
  caDetail: {//公司行为查看
	    header: "trade/caDetail.do",
	    inputs: {
	      id: "trade_id"
	    }
	  },
  newpriceShortList: {
    header: "market/newSmfShortList.do",
    inputs: {
      config: "SMFSOURCE",
      type: ":0"
    }
  },
  newSmfShortList: {
    header: "market/newSmfShortList.do",
    inputs: {
      config: "SMFSOURCE",
      type: ":F10"
    }
  },
  
  newReceiveSmfList: {//股票新股中签smf(去掉已经做过交易的)
		 header: "market/newReceiveSmfList.do",
		 inputs: {
		    config: "SMFSOURCE",
		    type: ":F10"
		 }
  },
  
  getSpotSmf: {//根据多空方货币自动选择
	    header: "config/getSpotSmf.do",
	    inputs: {
	      bankAcct: "bankAcct",
	      bankAcct2: "bankAcct2",
	      assetType: ":F11"
	    }
	  },
  newSwapShortList: {//即期SMF
	    header: "market/newSmfShortList.do",
	    inputs: {
	      config: "SMFSOURCE",
	      type: ":F11"
	    }
	  },
  newSmfSwapShortList: {
    header: "market/newSmfShortList.do",
    inputs: {
      config: "SMFSOURCE",
      type: ":F14"
    }
  },
  newSmfCurSwapShortList: {
	    header: "market/newSmfShortList.do",
	    inputs: {
	      config: "SMFSOURCE",
	      type: ":F34"
	    }
	  },
  newForwardSmfShortList: {
    header: "market/newForwardSmfShortList.do",
    inputs: {
      config: "SMFSOURCE",
      type: ":F12"
    }
  },
  newfuturesSmfShortList: {
    header: "market/newSmfShortList.do",
    inputs: {
      config: "SMFSOURCE",
      type: ":F30"
    }
  },
  newoptionSmfShortList: {
    header: "market/newSmfShortList.do",
    inputs: {
      config: "SMFSOURCE",
      type: ":60"
    }
  },
  newmoneyfundSmfShortList: {
    header: "market/newSmfShortList.do",
    inputs: {
      config: "SMFSOURCE",
      type: ":107"
    }
  },
  newcommonfundSmfShortList: {
    header: "market/newSmfShortList.do",
    inputs: {
      config: "SMFSOURCE",
      type: ":17"
    }
  },
  smf_stockUpdate: {
    header: "market/smfUpdate.do",
    inputs: {
      id: "smf_id",
      params: ":[]",
      json: {
        header: ":smfOperation",
        id: "smf_id",
        operation: ":UPDATE",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: "incurrency",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  smf_common_fund_Update: {
    header: "market/smfUpdate.do",
    inputs: {
      id: "smf_id",
      params: ":[]",
      json: {
        header: ":smfOperation",
        id: "smf_id",
        operation: ":UPDATE",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: "incurrency",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  smf_money_fund_Update: {
    header: "market/smfUpdate.do",
    inputs: {
      id: "smf_id",
      params: ":[]",
      json: {
        header: ":smfOperation",
        id: "smf_id",
        operation: ":UPDATE",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: "incurrency",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        cashflowcfg: "",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  smf_optionupdate: {
    header: "market/smfUpdate.do",
    inputs: {
      id: "smf_id",
      params: ":[]",
      json: {
        header: ":smfOperation",
        id: "smf_id",
        operation: ":UPDATE",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        optionType: "optionType",
        optionContractType: "optionContractType",
        endPrice: "endPrice",
        secExpireDate: "secexpiredate",
        underCusip: "underCusip",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: "incurrency",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  smf_futuresUpdate: {
    header: "market/smfUpdate.do",
    inputs: {
      id: "smf_id",
      params: ":[]",
      json: {
        header: ":smfOperation",
        id: "smf_id",
        operation: ":UPDATE",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        futuresType: "futuresType",
        futuresTarget: "futuresTarget",
        secContractSize: "secContractSize",
        futuresPriceUnit: "futuresPriceUnit",
        marginRate: "marginRate",
        secExpireDate: "secExpireDate",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: "incurrency",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  smfRemove: {
    header: "market/smfRemove.do",
    inputs: {
      id: "smf_id"
    }
  },
  priceRemove: {
    header: "market/priceRemove.do",
    inputs: {
      id: "price_id"
    }
  },
  tradeRemove: {//投资组合交易删除
    header: "trade/tradeRemove.do",
    inputs: {
      id: "trade_id",
      operType: ":MODIFY"
    }
  },
  caRemove: {//公司行为删除
	    header: "trade/caRemove.do",
	    inputs: {
	      id: "trade_id"
	    }
	  },
  smf_FXoptionList: {
    header: "market/smf.do",
    inputs: {
      config: "SMFCONFIG",
      type: "type",
      smf: "smf",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  optionTypes: {
    header: "trade/optionTypes.do",
    inputs: {}
  },
  optionContractTypes: {
    header: "trade/optionContractTypes.do",
    inputs: {}
  },
  newAssetForm: {
    header: "trade/newAssetForm.do",
    inputs: {
      assetType: "type",
      operation: ":add"
    }
  },
  editAssetForm: {
    header: "trade/newAssetForm.do",
    inputs: {
      assetType: "type",
      operation: ":update"
    }
  },
  cfURL: { //收入端现金流创建button
    header: "config/cfURL.do",
    inputs: {
      type: "couponType",
      operation: ":ADD"
    }
  },
  cfURLUPDATE: { //收入端现金流更新button
    header: "config/cfURL.do",
    inputs: {
      type: "couponType",
      operation: ":UPDATE"
    }
  },
  cfURL2pay: { //支出端现金流创建button
    header: "config/cfURL2pay.do",
    inputs: {
      type: "couponType2",
      operation: ":ADD"
    }
  },
  cfURL2payUPDATE: { //支出端现金流更新button
    header: "config/cfURL2pay.do",
    inputs: {
      type: "couponType2",
      operation: ":UPDATE"
    }
  },
  newTradeForm: {
    header: "trade/newTradeForm.do",
    inputs: {
      tradeType: "tradetypeCode"
    }
  },
  caForm: {
	    header: "trade/caForm.do",
	    inputs: {
	    	caType: "tradetypeCodeCol"
	    }
	  },
  clientList: {
    header: "client/clientList.do",
    inputs: {}
  },
  Trade_stockList: {
    header: "trade/newTradeList.do",
    inputs: {
      config: "TRADECONFIG",
      smf: "smf",
      type: "porttype",
      portfolio: "port",
      start: "start",
      end: "end",
      displayDelete: "displayDelete:false",
      num: ":1"
    }
  },
  Trade_caList: {
	    header: "trade/ca.do",
	    inputs: {
	      config: "CATRADECONFIG",
	      smf: "smf",
	      type: ":0",
	      start: "start",
	      end: "end",
	      num: ":1"
	    }
	  },
  Trade_swapList: {
    header: "trade/newTradeList.do",
    inputs: {
      config: "TRADECONFIG",
      smf: "smf",
      type: "porttype",
      portfolio: "port",
      start: "start",
      end: "end",
      displayDelete: ":false",
      num: ":1"
    }
  },
  Trade_forwardList: {
    header: "trade/newFx.do",
    inputs: {
      config: "TRADEFORWARDCONFIG",
      smf: "smf",
      type: "porttype",
      portfolio: "port",
      start: "start",
      end: "end",
      displayDelete: ":false",
      num: ":1"
    }
  },

  Trade_money_fund_List: {
    header: "trade/newTradeList.do",
    inputs: {
      config: "TRADECONFIG",
      smf: "smf",
      type: "porttype",
      portfolio: "port",
      start: "start",
      end: "end",
      displayDelete: "displayDelete",
      num: ":1"
    }
  },
  Trade_common_fund_List: {
    header: "trade/newTradeList.do",
    inputs: {
      config: "TRADECONFIG",
      smf: "smf",
      type: "porttype",
      portfolio: "port",
      start: "start",
      end: "end",
      displayDelete: "displayDelete",
      num: ":1"
    }
  },
  Trade_spot_List: {
    header: "trade/newFx.do",
    inputs: {
      config: "FXTRADESCONFIG",
      smf: "smf",
      type: "porttype",
      portfolio: "port",
      start: "start",
      end: "end",
      displayDelete: ":false",
      num: ":1"
    }
  },
  futuresExchanges: {
    header: "market/futuresExchanges.do",
    inputs: {}
  },
  futuresList: {
    header: "market/futuresTypes.do",
    inputs: {
      exchange: "exchange"
    }
  },
  futuresTargets: {
    header: "market/futuresTargets.do",
    inputs: {
      exchange: "exchange",
      futuresType: "futuresType"
    }
  },
  futuresPriceUnits: {
    header: "trade/futuresPriceUnits.do",
    inputs: {}
  },
  smf_optionCreate: {
    header: "market/smfCreate.do",
    inputs: {
      json: {
        header: ":smfOperation",
        operation: ":ADD",
        secid: "secid",
        securityName: "secname",
        issuetype: "type",
        issuer: "issuer",
        optionType: "optionType",
        optionContractType: "optionContractType",
        endPrice: "endPrice",
        secExpireDate: "secExpireDate",
        underCusip: "underCusip",
        country: "country",
        exchange: "exchange",
        prcurrency: "prcurrency",
        incurrency: ":23",
        glgroup: "glgroup",
        assetType: "assetType:ASSETTYPE",
        assetMajor: "assetMajor:ASSETMAJOR",
        assetMinor: "assetMinor:ASSETMINOR",
        source: "SMFSOURCE",
        activeDate: "activeDate"
      }
    }
  },
  secAcctListInPort: {
    header: "config/secAcctListInPort.do",
    inputs: {
      pid: "port"
    }
  },
  tradeMarkets: {
    header: "trade/tradeMarkets.do",
    inputs: {
      all: ":false"
    }
  },
  managersInPort: {
    header: "port/managersInPort.do",
    inputs: {
      port: ":port"
    }
  },
  tradeExpenseDetail: {
    header: "trade/newTradeExpenseDetail.do",
    inputs: {
      id: "trade_id"
    }
  },
  Trade_futuresList: {
    header: "trade/newTradeList.do",
    inputs: {
      config: "TRADECONFIG",
      smf: "smf",
      type: "porttype",
      portfolio: "port",
      start: "start",
      end: "end",
      displayDelete: ":false",
      num: ":1"
    }
  },
  trade_futures_secid: {
    header: "market/smf.do",
    inputs: {
      config: "SMFCONFIG",
      type: ":F30",
      smf: "smf",
      start: "start",
      end: "end",
      num: ":1"
    }
  },
  allTradeTypes: {
    header: "trade/allTradeTypes.do",
    inputs: {}
  },
  caTypes: {//公司行为类型
	    header: "trade/caTypes.do",
	    inputs: {}
	  },
  allCorpActionTypes: {
	  header: "trade/caTypes.do",
	  inputs: {
		  all: ":true"
	  }
  },
  brokerList: {
    header: "config/brokerList.do",
    inputs: {
      portID: "port",
      ports: "port"
    }
  },
  secCashList: {//现金账户或多头现金账户
    header: "config/secCashList.do",
    inputs: {
      secAcct: "secAcct:0"
    }
  },
  FXswapSetPrincipalType: {//外汇掉期面额互换
	    header: "market/swapFXType.do",
	    inputs: {
	    }
	  },
  swapSetPrincipalType: {//单币种利率互换返回面额交换下拉列表
	    header: "market/swapSetPrincipalType.do",
	    inputs: {
	    }
	  },
  secCashList2: {//空头现金账户
	    header: "config/secCashList.do",
	    inputs: {
	      secAcct: "secAcct2:0"
	    }
	  },
  secCashListOff: {
    header: "config/secCashList.do",
    inputs: {
      secAcct: "secAcctoff:0"
    }
  },
  CashOffsetOption: { //现金冲销冲销类型
    header: "trade/CashOffsetOption.do",
    inputs: {}
  },
  Trade_cashMoveList: {
    header: "trade/newCash.do",
    inputs: {
      config: "TRADECASHCONFIG",
      type: "porttype",
      portfolio: "port",
      start: "start",
      end: "end",
      displayDelete: ":false",
      num: ":1"
    }
  },
  cfTypesOld: {
    header: "config/cfTypes.do",
    inputs: {}
  },
  cfTypes: {//现金流固定浮动类型
	    header: "config/cfSwapTypes.do",
	    inputs: {}
	  },
  ca_stocksplit_Create: {
	  header: "trade/caCreate.do",
	  inputs: {
		  json: {
			  header: ":jxCorpActionStockSplitDtoMsg",
			  operation: ":ADD",
			  assetType: "assetType",
			  secid: "secid",
			  corpActionType: "corpActionType",
			  recordDate: "recordDate",
			  exDividentDate: "exDividentDate",
			  alRate: "caRate"
		  },
		  operType: ":new"
	  }
  },
  ca_stocksplit_Update: {//公司行为股票分割更新
	  header: "trade/caCreate.do",
	  inputs: {
		  id: "trade_id",
	      params: ":[]",
		  json: {
			  header: ":jxCorpActionStockSplitDtoMsg",
			  id: "trade_id",
			  operation: ":UPDATE",
			  assetType: "assetType",
			  secid: "secid",
			  corpActionType: "corpActionType",
			  recordDate: "recordDate",
			  exDividentDate: "exDividentDate",
			  alRate: "caRate"
		  },
		  operType: ":modify"
	  }
  },
  ca_stockResverseSplit_Create: {
	  header: "trade/caCreate.do",
	  inputs: {
		  json: {
			  header: ":jxCorpActionStockResverseSplitDtoMsg",
			  operation: ":ADD",
			  assetType: "assetType",
			  secid: "secid",
			  corpActionType: "corpActionType",
			  recordDate: "recordDate",
			  exDividentDate: "exDividentDate",
			  alRate: "caRate"
		  },
		  operType: ":new"
	  }
  },
  ca_stockResverseSplit_Update: {//公司行为股票合并更新
	  header: "trade/caCreate.do",
	  inputs: {
		  id: "trade_id",
	      params: ":[]",
		  json: {
			  header: ":jxCorpActionStockResverseSplitDtoMsg",
			  id: "trade_id",
			  operation: ":UPDATE",
			  assetType: "assetType",
			  secid: "secid",
			  corpActionType: "corpActionType",
			  recordDate: "recordDate",
			  exDividentDate: "exDividentDate",
			  alRate: "caRate"
		  },
		  operType: ":modify"
	  }
  },
  ca_stockdividend_Create: {
	  header: "trade/caCreate.do",
	  inputs: {
		  json: {
			  header: ":jxCorpActionStockDividendDtoMsg",
			  operation: ":ADD",
			  assetType: "assetType",
			  secid: "secid",
			  corpActionType: "corpActionType",
			  recordDate: "recordDate",
			  exDividentDate: "exDividentDate",
			  alRate: "caRate"
		  },
		  operType: ":new"
	  }
  },
  ca_stockdividend_Update: {//公司行为股票股利更新
	  header: "trade/caCreate.do",
	  inputs: {
		  id: "trade_id",
	      params: ":[]",
		  json: {
			  header: ":jxCorpActionStockDividendDtoMsg",
			  id: "trade_id",
			  operation: ":UPDATE",
			  assetType: "assetType",
			  secid: "secid",
			  corpActionType: "corpActionType",
			  recordDate: "recordDate",
			  exDividentDate: "exDividentDate",
			  alRate: "caRate"
		  },
		  operType: ":modify"
	  }
  },
  ca_cashdividend_Create: {
	  header: "trade/caCreate.do",
	  inputs: {
		  json: {
			  header: ":jxCorpActionCashDividendDtoMsg",
			  operation: ":ADD",
			  assetType: "assetType",
			  secid: "secid",
			  corpActionType: "corpActionType",
			  recordDate: "recordDate",
			  exDividentDate: "exDividentDate",
			  alRate: "caRate",
			  payDate: "payDate"
		  },
		  operType: ":new"
	  }
  },
  ca_cashdividend_Update: {
	  header: "trade/caCreate.do",
	  inputs: {
		  id: "trade_id",
	      params: ":[]",
		  json: {
			  header: ":jxCorpActionCashDividendDtoMsg",
			  id: "trade_id",
			  operation: ":UPDATE",
			  assetType: "assetType",
			  secid: "secid",
			  corpActionType: "corpActionType",
			  recordDate: "recordDate",
			  exDividentDate: "exDividentDate",
			  alRate: "caRate",
			  payDate: "payDate"
		  },
		  operType: ":modify"
	  }
  },
  stock_dividend_Create: {
	  header: "trade/tradeCreate.do",
	  inputs: {
		  json: {
			  header: ":jxStockDividendDtoMsg",
			  operation: ":ADD",
			  assetType: "assetType",
			  secid: "secid",
			  tradeType: "tradeType",
			  port: "port",
			  divDate: "divDate",
			  tradeDate: "tradeDate",
			  alRate: "alRate"
		  },
		  operType: ":new"
	  }
  },
  stock_dividend_Update: {
	  header: "trade/tradeCreate.do",
	  inputs: {
		  json: {
			  header: ":jxStockDividendDtoMsg",
			  operation: ":UPDATE",
			  id: "trade_id",
			  assetType: "assetType",
			  secid: "secid",
			  tradeType: "tradeType",
			  port: "port",
			  divDate: "divDate",
			  tradeDate: "tradeDate",
			  alRate: "alRate"
		  },
		  operType: ":modify"
	  }
  },
  cash_dividend_Create: {
	  header: "trade/tradeCreate.do",
	  inputs: {
		  json: {
			  header: ":jxCashDividendDtoMsg",
			  operation: ":ADD",
			  assetType: "assetType",
			  secid: "secid",
			  tradeType: "tradeType",
			  port: "port",
			  divDate: "divDate",
			  tradeDate: "tradeDate",
			  settleDate: "settleDate",
			  alRate: "alRate"
		  },
		  operType: ":new"
	  }
  },
  cash_dividend_Update: {
	  header: "trade/tradeCreate.do",
	  inputs: {
		  json: {
			  header: ":jxCashDividendDtoMsg",
			  operation: ":UPDATE",
			  id: "trade_id",
			  assetType: "assetType",
			  secid: "secid",
			  tradeType: "tradeType",
			  port: "port",
			  divDate: "divDate",
			  tradeDate: "tradeDate",
			  settleDate: "settleDate",
			  alRate: "alRate"
		  },
		  operType: ":modify"
	  }
  },
  stock_split_Create: {
	  header: "trade/tradeCreate.do",
	  inputs: {
		  json: {
			  header: ":jxStockSplitDtoMsg",
			  operation: ":ADD",
			  assetType: "assetType",
			  secid: "secid",
			  tradeType: "tradeType",
			  port: "port",
			  divDate: "divDate",
			  tradeDate: "tradeDate",
			  alRate: "alRate"
		  },
		  operType: ":new"
	  }
  },
  stock_split_Update: {
	  header: "trade/tradeCreate.do",
	  inputs: {
		  json: {
			  header: ":jxStockSplitDtoMsg",
			  operation: ":UPDATE",
			  id: "trade_id",
			  assetType: "assetType",
			  secid: "secid",
			  tradeType: "tradeType",
			  port: "port",
			  divDate: "divDate",
			  tradeDate: "tradeDate",
			  alRate: "alRate"
		  },
		  operType: ":modify"
	  }
  },
  stock_reserveSplit_Create: {
	  header: "trade/tradeCreate.do",
	  inputs: {
		  json: {
			  header: ":jxStockResverseSplitDtoMsg",
			  operation: ":ADD",
			  assetType: "assetType",
			  secid: "secid",
			  tradeType: "tradeType",
			  port: "port",
			  divDate: "divDate",
			  tradeDate: "tradeDate",
			  alRate: "alRate"
		  },
		  operType: ":new"
	  }
  },
  stock_reserveSplit_Update: {
	  header: "trade/tradeCreate.do",
	  inputs: {
		  json: {
			  header: ":jxStockResverseSplitDtoMsg",
			  operation: ":UPDATE",
			  id: "trade_id",
			  assetType: "assetType",
			  secid: "secid",
			  tradeType: "tradeType",
			  port: "port",
			  divDate: "divDate",
			  tradeDate: "tradeDate",
			  alRate: "alRate"
		  },
		  operType: ":modify"
	  }
  },
  stock_buy_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jixuStockTranDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        accruedIncome: "accruedIncome",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      },
      operType: ":new"
    }
  },
  stocknew_receive_Create: {
	    header: "trade/tradeCreate.do",
	    inputs: {
	      expenses: ":[]",
	      collaterals: ":[]",
	      json: {
	        header: ":jixuStockNewReceiveDtoMsg",
	        operation: ":ADD",
	        expenses: "tablefee",
	        collaterals: ":[]",
	        assetType: "assetType",
	        secid: "secid",
	        tradeType: "tradeType",
	        port: "port",
	        market: "market",
	        manager: "manager",
	        tradeDate: "tradeDate",
	        settleDate: "settleDate",
	        broker: "broker",
	        secAcct: "secAcct",
	        bankAcct: "bankAcct",
	        shares: "shares",
	        price: "price",
	        principal: "principal",
	        funcCurrency1: "funcCurrency1",
	        tradeCurrency: "tradeCurrency",
	        tradeCash: "tradeCash",
	        tradeFX: "tradeFx",
	        settleCurrency: "settleCurrency",
	        settleCash: "settleCash",
	        settleFX: "settleFx"
	      }
	    }
	  },
	  stocknew_receive_Update: {
		    header: "trade/tradeCreate.do",
		    inputs: {
		      expenses: ":[]",
		      collaterals: ":[]",
		      json: {
		        header: ":jixuStockNewReceiveDtoMsg",
		        operation: ":UPDATE",
		        id: "trade_id",
		        expenses: "tablefee",
		        collaterals: ":[]",
		        assetType: "assetType",
		        secid: "secid",
		        tradeType: "tradeType",
		        port: "port",
		        market: "market",
		        manager: "manager",
		        tradeDate: "tradeDate",
		        settleDate: "settleDate",
		        broker: "broker",
		        secAcct: "secAcct",
		        bankAcct: "bankAcct",
		        shares: "shares",
		        price: "price",
		        principal: "principal",
		        funcCurrency1: "funcCurrency1",
		        tradeCurrency: "tradeCurrency",
		        tradeCash: "tradeCash",
		        tradeFX: "tradeFx",
		        settleCurrency: "settleCurrency",
		        settleCash: "settleCash",
		        settleFX: "settleFx"
		      }
		    }
		  },	  
  cashOffset_new: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":cashOffMsg",
        operation: ":ADD",
        collaterals: ":[]",
        tradeType: "tradeType",
        port: "port",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        offsetType: "offsetType",
        tranDesc: "tranDesc",
        glacct: "glacct",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash"
      }
    }
  },
  cashMove_new: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":cashMoveMsg",
        operation: ":ADD",
        collaterals: ":[]",
        tradeType: "tradeType",
        port: "port",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        secAcct2: "secAcct2",
        bankAcct2: "bankAcct2",
        tradeCurrency2: "tradeCurrency2",
        tradeCash2: "tradeCash2",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash"
      }
    }
  },
  option_awarded_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":AssembleTranOption",
        operation: ":ADD",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: ":0",
        principal: ":0",
        accruedIncome: ":0",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },

  option_awarded_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":AssembleTranOption",
        id: "trade_id",
        operation: ":UPDATE",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: ":0",
        principal: ":0",
        accruedIncome: ":0",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  cashMove_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":cashMoveMsg",
        id: "trade_id",
        operation: ":UPDATE",
        collaterals: ":[]",
        tradeType: "tradeType",
        port: "port",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        secAcct2: "secAcct2",
        bankAcct2: "bankAcct2",
        tradeCurrency2: "tradeCurrency2",
        tradeCash2: "tradeCash2",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash"
      }
    }
  },
  trade_spot_new: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxFXOpenDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        port: "port",
        tradeType: "tradeType",
        broker: "broker",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        secid: "secid",
        purpose: "purpose",
        counterparty: "counterparty",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        secAcct2: "secAcct2",
        bankAcct2: "bankAcct2",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        glgroup: "glgroup"
      }
    }
  },
  cashOffset_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":cashOffMsg",
        id: "trade_id",
        operation: ":UPDATE",
        collaterals: ":[]",
        tradeType: "tradeType",
        port: "port",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        offsetType: "offsetType",
        tranDesc: "tranDesc",
        glacct: "glacct",
        settleCurrency: "settleCurrency1",
        settleCash: "settleCash",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash"
      }
    }
  },
  money_fund_buy_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":TranMMFund",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  forward_open_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxForwardCurrencyOpenDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        contract: "contract",
        tradeType: "tradeType",
        counterparty: "counterparty",
        broker: "broker",
        port: "port",
        manager: "manager",
        valuationMethod: "valuationMethod",
        valuationSide: "valuationSide",
        purpose: "purpose",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        bankAcct: "bankAcct",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        longFX: "longFX",
        bankAcct2: "bankAcct2",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        shortFX: "shortFX",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        glgroup: "glgroup",
        rpSecType: "ASSETTYPE",
        source: "SMFSOURCE"
      }
    }
  },
  forward_open_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxForwardCurrencyOpenDtoMsg",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        contract: "contract",
        tradeType: "tradeType",
        counterparty: "counterparty",
        broker: "broker",
        port: "port",
        manager: "manager",
        valuationMethod: "valuationMethod",
        valuationSide: "valuationSide",
        purpose: "purpose",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        bankAcct: "bankAcct",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        longFX: "longFX",
        bankAcct2: "bankAcct2",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        shortFX: "shortFX",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        glgroup: "glgroup",
        rpSecType: "ASSETTYPE",
        source: "SMFSOURCE"
      }
    }
  },

  forward_close_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxForwardCurrencyCloseDtoMsg",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        counterparty: "counterparty",
        broker: "broker",
        port: "port",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        bankAcct: "bankAcct",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        longFX: "longFX",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        shortFX: "shortFX",
        settleCurrency: "settleCurrency",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2"
      }
    }
  },

  forward_close_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxForwardCurrencyCloseDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        counterparty: "counterparty",
        broker: "broker",
        port: "port",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        bankAcct: "bankAcct",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        longFX: "longFX",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        shortFX: "shortFX",
        settleCurrency: "settleCurrency",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2"
      }
    }
  },
  
  DiffSides: {
	//差额收付方向
	header: "config/expenseRPType.do",
	inputs: {}
},
  
  forward_cutting_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxForwardCurrencyCuttingDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        counterparty: "counterparty",
        port: "port",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "tradeDate",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        longFX: "longFX",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        shortFX: "shortFX",
        settleCurrency: "settleCurrency",
        tradeFX: "tradeFX",
        bankAcct: "bankAcct",
        diffSide: "diffSide",
        diffCash: "diffCash",
        settleFX: "settleFX",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2"
      }
    }
  },
  forward_expire_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxForwardCurrencyMaurityDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        counterparty: "counterparty",
        port: "port",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "tradeDate",
        bankAcct: "bankAcct",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        longFX: "longFX",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        shortFX: "shortFX",
        settleCurrency: "settleCurrency",
        tradeFX: "tradeFX",
        bankAcct2: "bankAcct2",
        settleFX: "settleFx",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2"
      }
    }
  },

  forward_cutting_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxForwardCurrencyCuttingDtoMsg",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        counterparty: "counterparty",
        port: "port",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "tradeDate",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        longFX: "longFX",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        shortFX: "shortFX",
        settleCurrency: "settleCurrency1",
        tradeFX: "tradeFX",
        bankAcct: "bankAcct",
        specificLotID: "specificLotID",
        cash: "cash",
        diffSide: "diffSide",
        diffCash: "diffCash",
        settleFX: "settleFX",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2"
      }
    }
  },
  forward_expire_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxForwardCurrencyMaurityDtoMsg",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        counterparty: "counterparty",
        port: "port",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "tradeDate",
        bankAcct: "bankAcct",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        longFX: "longFX",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        shortFX: "shortFX",
        settleCurrency: "settleCurrency1",
        tradeFX: "tradeFX",
        bankAcct2: "shortBankAcct",
        settleFX: "settleFX",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2"
      }
    }
  },
  forward_FCOpenTrade: { //远期换汇平仓、到期、轧差从开仓中取值
    header: "trade/FCOpenTrade.do",
    inputs: {
      smf: "secid:NULL",
      portfolio: "port"
    }
  },
  swapInterestTrade: { //互换付息、到期从开仓中取值
    header: "trade/swapInterestTrade.do",
    inputs: {
      smf: "secid:NULL",
      portfolio: "port"
    }
  },
  common_fund_buy_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":TranComFund",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  common_fund_switchin_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":TranComFundSwitch",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        broker: "broker",
        secAcct: "secAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  stock_HK_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":Stock_HGT_Tran_Msg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        broker: "broker",
        settleDate: "settleDate",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        settleDate2: "settleDate2",
        secAcct2: "secAcct2",
        bankAcct2: "bankAcct2",
        shares: "shares",
        price: "price",
        principal: "principal",
        accruedIncome: "accruedIncome",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  money_fund_tradeUpdate: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: "tablefee",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":TranMMFund",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradetypeCode",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency1",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  trade_spot_update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxFXOpenDtoMsg",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        port: "port",
        tradeType: "tradeTypes",
        broker: "broker",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        secid: "secid",
        purpose: "purpose",
        counterparty: "counterparty",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        longCurrency: "longCurrency",
        tradeCash: "tradeCash",
        secAcct2: "secAcct2",
        bankAcct2: "bankAcct2",
        shortCurrency: "shortCurrency",
        tradeCash2: "tradeCash2",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        glgroup: "glgroup"
      }
    }
  },
  stock_HK_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: "tablefee",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":Stock_HGT_Tran_Msg",
        operation: ":UPDATE",
        id: "trade_id",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradetypeCode",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        broker: "broker",
        settleDate: "settleDate",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        settleDate2: "settleDate2",
        secAcct2: "secAcct2",
        bankAcct2: "bankAcct2",
        shares: "shares",
        price: "price",
        principal: "principal",
        accruedIncome: "accruedIncome",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  stock_tradeUpdate: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: "tablefee",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jixuStockTranDtoMsg",
        operation: ":UPDATE",
        id: "trade_id",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        accruedIncome: "accruedIncome",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency1",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      },
      operType: ":modify"
    }
  },
  option_buy_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":TranOption",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        accruedIncome: "accruedIncome",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  option_buy_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":TranOption",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        accruedIncome: "accruedIncome",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },

  option_exercisePrice: {
    header: "market/smfExercisePrice.do",
    inputs: {
      smfID: "secid:NULL"
    }
  },

  option_exercisePosition: {
    header: "trade/optionExercisePosition.do",
    inputs: {
      smfID: "secid:NULL",
      port: "port",
      secacct: "secAcct"
    }
  },

  option_exercise_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":AssembleTranOptionExercise",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  option_exercise_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":AssembleTranOptionExercise",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      }
    }
  },
  option_expire_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":AssembleTranOptionAbandon",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares"
      }
    }
  },
  option_expire_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: ":[]",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":AssembleTranOptionAbandon",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares"
      }
    }
  },
  futures_open_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jixuFutureTranOpenDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        lotMethod: "lotMethod",
        contract: "contract",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        purpose: "purpose",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      },
      operType: ":new"
    }
  },
  futures_close_Create: {
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jixuFutureTranCloseDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        contract: "contract",
        lotMethod: "lotMethod",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        purpose: "purpose",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      },
      operType: ":new"
    }
  },
  futures_open_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: "tablefee",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jixuFutureTranOpenDtoMsg",
        operation: ":UPDATE",
        id: "trade_id",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        lotMethod: "lotMethod",
        contract: "contract",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        purpose: "purpose",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      },
      operType: ":modify"
    }
  },
  futures_close_Update: {
    header: "trade/tradeUpdate.do",
    inputs: {
      id: "trade_id",
      expenses: "tablefee",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jixuFutureTranCloseDtoMsg",
        operation: ":UPDATE",
        id: "trade_id",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        contract: "contract",
        lotMethod: "lotMethod",
        tradeType: "tradeType",
        port: "port",
        market: "market",
        manager: "manager",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        broker: "broker",
        secAcct: "secAcct",
        bankAcct: "bankAcct",
        shares: "shares",
        price: "price",
        principal: "principal",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        purpose: "purpose",
        funcCurrency1: "funcCurrency1",
        tradeCurrency: "tradeCurrency",
        tradeCash: "tradeCash",
        tradeFX: "tradeFx",
        funcCurrency2: "funcCurrency2",
        settleCurrency: "settleCurrency",
        settleCash: "settleCash",
        settleFX: "settleFx"
      },
      operType: ":modify"
    }
  },
  gettradeExpenses: {
    header: "trade/tradeExpenses.do",
    inputs: {
      assetType: "assetType"
    }
  },
  getexpenseRPType: {
    header: "config/expenseRPType.do",
    inputs: {}
  },
  lotSelect: {
    header: "trade/lotPositionSelect.do",
    inputs: {
      secid: "secid",
      port: "port"
    }
  },
  
  futureCloseSaleLotSelect: {
	    header: "trade/futureLotSelect.do",
	    inputs: {
	      secid: "secid",
	      port: "port",
	      secAcct: "secAcct",
	      longshort:":1"
	    }
	  },
   futureCloseBuyLotSelect : {
		header : "trade/futureLotSelect.do",
		inputs : {
			secid : "secid",
			port : "port",
			secAcct: "secAcct",
			longshort : ":2"
		}
	},
  valuationMethods: {
    header: "trade/valuationMethods.do",
    inputs: {
      assetType: "assetType"
    }
  },
  lotMethods: {
    header: "trade/lotMethods.do",
    inputs: {}
  },
  futuresPurposes: {
    header: "trade/futuresPurposes.do",
    inputs: {}
  },
  Trade_optionList: {
    header: "trade/newTradeList.do",
    inputs: {
      config: "TRADECONFIG",
      smf: "smf",
      type: "porttype",
      portfolio: "port",
      start: "start",
      end: "end",
      displayDelete: ":false",
      num: ":1"
    }
  },
  expenseTypes: {
    //费用类型
    header: "config/expenseTypes.do",
    inputs: {}
  },
  expenseRateTypes: {
    //费率类型
    header: "config/expenseRateTypes.do",
    inputs: {}
  },
  accrualBasis: {
    //计提基础
    header: "trade/accrualBasis.do",
    inputs: {}
  },
  repoPaymentDateOptions: {
    //日期类型
    header: "market/repoPaymentDateOptions.do",
    inputs: {}
  },
  expenseDayCount: {
    //日计数方法
    header: "port/expenseDayCount.do",
    inputs: {}
  },
  swapCfFrequency: {
	    //付款频率
	    header: "port/expenseFrequency.do",
	    inputs: {}
	  },
  expenseFrequency: {
    //付款频率
    header: "port/newExpenseFrequency.do",
    inputs: {}
  },
  expenseMonth: {
    //付款月份
    header: "port/newExpenseMonth.do",
    inputs: {
      freq: "frequency"
    }
  },
  resetExpenseMonth: {
    //重置月份
    header: "port/newExpenseMonth.do",
    inputs: {
      freq: "resetfrequency"
    }
  },
  
  cfPaymentDetail: {
	    //收入端现金流查看付息计划
	    header: "config/cfPaymentDetail.do",
	    inputs: {
	      cashflowID: "cashflowcfg"
	    }
	  },
	  cfPaymentDetail2: {
	    //支出端现金流查看付息计划
	    header: "config/cfPaymentDetail.do",
	    inputs: {
	      cashflowID: "cashflowcfg2"
	    }
	  },
  
  expenseCreate: { //费用创建
    header: "config/expenseCreate.do",
    inputs: {
      json: {
        header: ":tranFeeConfigMsg",
        operation: ":ADD",
        name: "expense_name",
        type: "expense_type",
        expenseRatio: "expenseRatio",
        cashRatio: "cashRatio",
        principalGL: "principalGL",
        expenseGL: "expenseGL",
        settleGL: "settleGL",
        rcpyGL: "rcpyGL",
        rateType: "rateType",
        cf: "cf",
        status: ":1",
        assetTypes: "ASSETTYPES"
      }
    }
  },
  expenseUpdate: { //费用更新
    header: "config/expenseUpdate.do",
    inputs: {
      json: {
        header: ":tranFeeConfigMsg",
        id: "id",
        operation: ":UPDATE",
        name: "expense_name",
        type: "expense_type",
        expenseRatio: "expenseRatio",
        cashRatio: "cashRatio",
        principalGL: "principalGL",
        expenseGL: "expenseGL",
        settleGL: "settleGL",
        rcpyGL: "rcpyGL",
        rateType: "rateType",
        cf: "cf",
        status: ":1",
        assetTypes: "ASSETTYPES"
      }
    }
  },
  expense_cfCreate: { //费用现金流创建
    header: "config/cfCreate_new.do",
    inputs: {
      params: ":[]",
      json: {
        header: ":cashFlowMsg",
        operation: ":ADD",
        name: "expense_name",
        type: ":1",
        cfType: ":70",
        accurStartDate: "STARTDATE",
        holiday: "HOLIDAY",
        status: ":1",
        basis: "basis",
        accrStartN: "accrStartN",
        accrStartType: "accrStartType0",
        externalRate: ":false",
        contractRate: "contractRate",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: "accrStartType1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":false"
      }
    }
  },
  expense_cfUpdate: { //费用现金流更新
    header: "config/cfUpdate.do",
    inputs: {
      id: "cf",
      params: ":[]",
      json: {
        header: ":cashFlowMsg",
        id: "cf",
        operation: ":UPDATE",
        name: "expense_name",
        type: ":1",
        cfType: ":70",
        accurStartDate: "STARTDATE",
        holiday: "HOLIDAY",
        status: ":1",
        basis: "basis",
        accrStartN: "accrStartN",
        accrStartType: "accrStartType0",
        externalRate: ":false",
        contractRate: "contractRate",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: "accrStartType1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":false"
      }
    }
  },
  swap_cf_fixedCreate: { //收入端固定现金流创建
    header: "config/cfCreate_new.do",
    inputs: {
      params: ":[]",
      json: {
        header: ":jxCashFlowDtoMsg",
        payments: "tablePayInt",
        operation: ":ADD",
        name: ":固定利率",
        type: "type",
        cfType: "couponType",
        accurStartDate: "accurStartDate",
        holiday: "HOLIDAY",
        status: "status",
        basis: ":2",
        accrStartN: ":0",
        accrStartType: ":1",
        externalRate: ":false",
        contractRate: "couponRate",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency:expireFrequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: ":1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":true"
      }
    }
  },

  swap_cf_fixedUpdate: { //收入端固定现金流更新
    header: "config/newCfUpdate.do",
    inputs: {
      params: ":[]",
      id: "cashflowcfg",
      json: {
        header: ":jxCashFlowDtoMsg",
        id: "cashflowcfg",
        payments: "tablePayInt",
        operation: ":UPDATE",
        name: ":固定利率",
        type: "type",
        cfType: "couponType",
        accurStartDate: "accurStartDate",
        holiday: "HOLIDAY",
        status: "status",
        basis: ":2",
        accrStartN: ":0",
        accrStartType: ":1",
        externalRate: ":false",
        contractRate: "couponRate",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency:expireFrequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: ":1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":true"
      }
    }
  },
  swap_cf_fixedpayCreate: { //支出端固定现金流创建
    header: "config/cfCreate_new.do",
    inputs: {
      params: ":[]",
      json: {
        header: ":jxCashFlowDtoMsg",
        payments: "tablePayInt",
        operation: ":ADD",
        name: ":固定利率",
        type: "type",
        cfType: "couponType2",
        accurStartDate: "accurStartDate",
        holiday: "HOLIDAY",
        status: "status2",
        basis: ":2",
        accrStartN: ":0",
        accrStartType: ":1",
        externalRate: ":false",
        contractRate: "couponRate2",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency:expireFrequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: ":1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":true"
      }
    }
  },

  swap_cf_fixedpayUpdate: { //支出端固定现金流更新
    header: "config/newCfUpdate.do",
    inputs: {
      params: ":[]",
      id: "cashflowcfg2",
      json: {
        header: ":jxCashFlowDtoMsg",
        payments: "tablePayInt",
        id: "cashflowcfg2",
        operation: ":UPDATE",
        name: ":固定利率",
        type: "type",
        cfType: "couponType2",
        accurStartDate: "accurStartDate",
        holiday: "HOLIDAY",
        status: "status2",
        basis: ":2",
        accrStartN: ":0",
        accrStartType: ":1",
        externalRate: ":false",
        contractRate: "couponRate2",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency:expireFrequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: ":1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":true"
      }
    }
  },
  swap_cf_floatingCreate: { //收入端浮动现金流创建
    header: "config/cfCreate_new.do",
    inputs: {
      params: ":[]",
      json: {
        header: ":jxCashFlowDtoMsg",
        payments: "tablePayInt",
        floats: {
          secid: "base",
          vrMethod: "vrMethod",
          vrBasisPtAdjustment: "factor",
          max: "max",
          min: "min",
          frequency: "resetfrequency:expireFrequency",
          month: "month",
          day: "day",
          vrResetNDays: "vrResetNDays",
          vrDeterAdjMo: "vrDeterAdjMo",
          vrDeterAdjDays: "vrDeterAdjDays",
          holidaySD: "HOLIDAY"
        },
        operation: ":ADD",
        name: ":浮动利率",
        type: "type",
        cfType: "couponType",
        accurStartDate: "accurStartDate",
        holiday: "HOLIDAY",
        status: "status",
        basis: ":2",
        accrStartN: ":0",
        accrStartType: ":1",
        externalRate: ":false",
        contractRate: "couponRate",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency:expireFrequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: ":1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":true"
      }
    }
  },

  swap_cf_floatingUpdate: { //收入端浮动现金流更新
    header: "config/newCfUpdate.do",
    inputs: {
      params: ":[]",
      id: "cashflowcfg",
      json: {
        header: ":jxCashFlowDtoMsg",
        payments: "tablePayInt",
        id: "cashflowcfg",
        floats: {
          secid: "base",
          vrMethod: "vrMethod",
          vrBasisPtAdjustment: "factor",
          max: "max",
          min: "min",
          frequency: "resetfrequency:expireFrequency",
          month: "month",
          day: "day",
          vrResetNDays: "vrResetNDays",
          vrDeterAdjMo: "vrDeterAdjMo",
          vrDeterAdjDays: "vrDeterAdjDays",
          holidaySD: "HOLIDAY"
        },
        operation: ":UPDATE",
        name: ":浮动利率",
        type: "type",
        cfType: "couponType",
        accurStartDate: "accurStartDate",
        holiday: "HOLIDAY",
        status: "status",
        basis: ":2",
        accrStartN: ":0",
        accrStartType: ":1",
        externalRate: ":false",
        contractRate: "couponRate",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency:expireFrequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: ":1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":true"
      }
    }
  },
  swap_cf_floatingpayCreate: { //支出端浮动现金流创建
    header: "config/cfCreate_new.do",
    inputs: {
      params: ":[]",
      json: {
        header: ":jxCashFlowDtoMsg",
        payments: "tablePayInt",
        floats: {
          secid: "base2",
          vrMethod: "vrMethod",
          vrBasisPtAdjustment: "factor2",
          max: "max",
          min: "min",
          frequency: "resetfrequency:expireFrequency",
          month: "month",
          day: "day",
          vrResetNDays: "vrResetNDays",
          vrDeterAdjMo: "vrDeterAdjMo",
          vrDeterAdjDays: "vrDeterAdjDays",
          holidaySD: "HOLIDAY"
        },
        operation: ":ADD",
        name: ":浮动利率",
        type: "type",
        cfType: "couponType2",
        accurStartDate: "accurStartDate",
        holiday: "HOLIDAY",
        status: "status2",
        basis: ":2",
        accrStartN: ":0",
        accrStartType: ":1",
        externalRate: ":false",
        contractRate: "couponRate2",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency:expireFrequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: ":1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":true"
      }
    }
  },

  swap_cf_floatingpayUpdate: { //支出端浮动现金流更新
    header: "config/newCfUpdate.do",
    inputs: {
      params: ":[]",
      id: "cashflowcfg2",
      json: {
        header: ":jxCashFlowDtoMsg",
        payments: "tablePayInt",
        id: "cashflowcfg2",
        floats: {
          secid: "base2",
          vrMethod: "vrMethod",
          vrBasisPtAdjustment: "factor2",
          max: "max",
          min: "min",
          frequency: "resetfrequency:expireFrequency",
          month: "month",
          day: "day",
          vrResetNDays: "vrResetNDays",
          vrDeterAdjMo: "vrDeterAdjMo",
          vrDeterAdjDays: "vrDeterAdjDays",
          holidaySD: "HOLIDAY"
        },
        operation: ":UPDATE",
        name: ":浮动利率",
        type: "type",
        cfType: "couponType2",
        accurStartDate: "accurStartDate",
        holiday: "HOLIDAY",
        status: "status2",
        basis: ":2",
        accrStartN: ":0",
        accrStartType: ":1",
        externalRate: ":false",
        contractRate: "couponRate2",
        fixedValue: ":0",
        dayCount: "dayCount",
        carryOver: ":1",
        frequency: "frequency:expireFrequency",
        paymentMonth: "paymentMonth",
        paymentDay: "paymentDay",
        paymentN: ":0",
        paymentNType: ":1",
        accrEndbasis: ":1",
        accrEndN: ":0",
        accrEndType: ":0",
        amortizedMethod: ":0",
        autoFlag: ":false",
        autoInterestFlag: ":true"
      }
    }
  },
  swap_fx_open_Create: { //外汇掉期开仓创建
	    header: "trade/tradeCreate.do",
	    inputs: {
	      expenses: ":[]",
	      collaterals: ":[]",
	      json: {
	        header: ":jxSwapFXOpenDtoMsg",
	        operation: ":ADD",
	        expenses: "tablefee",
	        collaterals: ":[]",
	        assetType: "assetType",
	        secid: "secid",
	        tradeType: "tradeType",
	        port: "port",
	        market: "MARKET",
	        manager: "manager",
	        broker: "broker",
	        tradeDate: "tradeDate",
	        settleDate: "settleDate",
	        maturityDate: "maturityDate",
	        totalExpense: "totalExpense",
	        totalExpense2: "totalExpense2",
	        glgroup: "glgroup",
	        swapExchangeInd:"swapExchangeInd",
	        principal: "principal",
	        principal2: "principal2",
	        bankAcct: "bankAcct",
	        bankAcct2: "bankAcct2",
	        incurrency: "incurrency",
	        incurrency2: "incurrency2",
	        tradeFX: "tradeFX",
	        tradeCash2: "tradeCash2",
	        tradeCash: "tradeCash",
	        tradeFX2: "tradeFX2",
	        rpSecType: "SWAPASSETTYPE",
	        source: ":0"
	      }
	    }
	  },
	  swap_fx_open_Update: { //外汇掉期开仓更新
	    header: "trade/tradeUpdate.do",
	    inputs: {
	      expenses: ":[]",
	      id: "trade_id",
	      params: ":[]",
	      collaterals: ":[]",
	      json: {
	        header: ":jxSwapFXOpenDtoMsg",
	        id: "trade_id",
	        operation: ":UPDATE",
	        expenses: "tablefee",
	        collaterals: ":[]",
	        assetType: "assetType",
	        secid: "secid",
	        tradeType: "tradeType",
	        port: "port",
	        market: "MARKET",
	        manager: "manager",
	        broker: "broker",
	        tradeDate: "tradeDate",
	        settleDate: "settleDate",
	        maturityDate: "maturityDate",
	        totalExpense: "totalExpense",
	        totalExpense2: "totalExpense2",
	        glgroup: "glgroup",
	        swapExchangeInd:"swapExchangeInd",
	        principal: "principal",
	        principal2: "principal2",
	        bankAcct: "bankAcct",
	        bankAcct2: "bankAcct2",
	        incurrency: "incurrency",
	        incurrency2: "incurrency2",
	        tradeFX: "tradeFX",
	        tradeCash2: "tradeCash2",
	        tradeCash: "tradeCash",
	        tradeFX2: "tradeFX2",
	        rpSecType: "SWAPASSETTYPE",
	        source: ":0"
	      }
	    }
	  },
  swap_open_Create: { //利率互换开仓创建
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxInterestSwapOpenDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "MARKET",
        manager: "manager",
        broker: "broker",
        valuationMethod: "valuationMethod",
        purpose: "purpose",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        maturityDate: "maturityDate",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        glgroup: "glgroup",
        swapExchangeInd:"swapExchangeInd",
        principal: "principal",
        incurrency: "incurrency",
        bankAcct: "bankAcct",
        tradeFX: "tradeFX",
        cashflowcfg: "cashflowcfg",
        principal2: "principal2",
        incurrency2: "incurrency2",
        bankAcct2: "bankAcct2",
        tradeFX2: "tradeFX2",
        cashflowcfg2: "cashflowcfg2",
        rpSecType: "SWAPASSETTYPE",
        source: ":0"
      }
    }
  },
  swap_open_Update: { //利率互换开仓更新
    header: "trade/tradeUpdate.do",
    inputs: {
      expenses: ":[]",
      id: "trade_id",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxInterestSwapOpenDtoMsg",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        market: "MARKET",
        manager: "manager",
        broker: "broker",
        valuationMethod: "valuationMethod",
        purpose: "purpose",
        tradeDate: "tradeDate",
        settleDate: "settleDate",
        maturityDate: "maturityDate",
        totalExpense: "totalExpense",
        totalExpense2: "totalExpense2",
        glgroup: "glgroup",
        swapExchangeInd:"swapExchangeInd",
        principal: "principal",
        incurrency: "incurrency",
        bankAcct: "bankAcct",
        tradeFX: "tradeFX",
        cashflowcfg: "cashflowcfg",
        principal2: "principal2",
        incurrency2: "incurrency2",
        bankAcct2: "bankAcct2",
        tradeFX2: "tradeFX2",
        cashflowcfg2: "cashflowcfg2",
        rpSecType: "SWAPASSETTYPE",
        source: ":0"
      }
    }
  },
  
  swap_expire_Create: { //互换到期/付息新建
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxInterestSwapMaturityDtoMsg",
        operation: ":ADD",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        tradeDate: "tradeDate",
        settleDate: "tradeDate",
        divBasicDate: "divBasicDate",
        bankAcct: "bankAcct",
        principal: "principal",
        intatmat: "intatmat",
        incurrency: "incurrency",
        tradeFX: "tradeFX",
        setAllInterest:"setAllInterest",
        principal2: "principal2",
        intatmat2: "intatmat2",
        incurrency2: "incurrency2",
        bankAcct2: "bankAcct2",
        tradeFX2: "tradeFX2",
        setAllInterest2:"setAllInterest2"
      }
    }
  },
  swap_expire_Update: { //互换到期/付息更新
    header: "trade/tradeCreate.do",
    inputs: {
      expenses: ":[]",
      id: "trade_id",
      params: ":[]",
      collaterals: ":[]",
      json: {
        header: ":jxInterestSwapMaturityDtoMsg",
        id: "trade_id",
        operation: ":UPDATE",
        expenses: "tablefee",
        collaterals: ":[]",
        assetType: "assetType",
        secid: "secid",
        tradeType: "tradeType",
        port: "port",
        tradeDate: "tradeDate",
        settleDate: "tradeDate",
        divBasicDate: "divBasicDate",
        bankAcct: "bankAcct",
        principal: "principal",
        intatmat: "intatmat",
        incurrency: "incurrency",
        tradeFX: "tradeFX",
        setAllInterest:"setAllInterest",
        principal2: "principal2",
        intatmat2: "intatmat2",
        incurrency2: "incurrency2",
        bankAcct2: "bankAcct2",
        tradeFX2: "tradeFX2",
        setAllInterest2:"setAllInterest2"
      }
    }
  },
  DefaultFxRate: {
    //互换交易界面获取收入端即期汇率
    header: "market/newDefaultFxRate.do",
    inputs: {
      port: "port",
      date: "tradeDate",
      localCurrency: "incurrency"
    }
  },
  DefaultFxRate2: {
	    //互换交易界面获取支出端即期汇率
	    header: "market/newDefaultFxRate.do",
	    inputs: {
	      port: "port",
	      date: "tradeDate",
	      localCurrency: "incurrency2"
	    }
	  },
	  DefaultFeeFxRateTrade: {
		    //交易费用界面获取汇率
		    header: "market/newDefaultFxRate.do",
		    inputs: {
		      port: "port",
		      localCurrency: "feeTradeCurrency",
		      date: "tradeDate"
		    }
		  },
	  DefaultFxRateTrade: {
		    //交易界面获取交易汇率
		    header: "market/newDefaultFxRate.do",
		    inputs: {
		      port: "port",
		      localCurrency: "tradeCurrency",
		      date: "tradeDate"
		    }
		  },
		  DefaultFxRateSettle: {
			    //交易界面获取结算汇率
			    header: "market/newDefaultFxRate.do",
			    inputs: {
			      port: "port",
			      date: "tradeDate",
			      localCurrency: "settleCurrency"
			    }
			  },

  cfDetail: {
    //查看现金流或收入端现金流
    header: "/config/newCfDetail.do",
    inputs: {
      id: "cashflowcfg"
    }
  },
  cfDetail2: {
    //查看现金流或支出端现金流
    header: "/config/newCfDetail.do",
    inputs: {
      id: "cashflowcfg2"
    }
  },
  newExpenseDetailURL: {
    header: "config/newExpenseDetailURL.do",
    inputs: { id: "id" }
  },
  expenseStatus: { //现金流是否生效
    header: "config/expenseStatus.do",
    inputs: {}
  },
  expenseDetail: {
    header: "config/expenseDetail.do",
    inputs: { id: "id" }
  },
  expenseCreateOnce: {
    header: "config/expenseCreate.do",
    inputs: {
      json: {
        header: ":tranFeeConfigMsg",
        operation: ":ADD",
        name: "expense_name",
        type: "expense_type",
        expenseRatio: "expenseRatio",
        cashRatio: "cashRatio",
        principalGL: "principalGL",
        expenseGL: "expenseGL",
        settleGL: "settleGL",
        rcpyGL: "rcpyGL",
        rateType: "rateType",
        cf: ":0",
        status: ":1",
        assetTypes: "ASSETTYPES"
      }
    }
  },
  expenseUpdateOnce: {
    header: "config/expenseUpdate.do",
    inputs: {
      json: {
        header: ":tranFeeConfigMsg",
        id: "id",
        operation: ":UPDATE",
        name: "expense_name",
        type: "expense_type",
        expenseRatio: "expenseRatio",
        cashRatio: "cashRatio",
        principalGL: "principalGL",
        expenseGL: "expenseGL",
        settleGL: "settleGL",
        rcpyGL: "rcpyGL",
        rateType: "rateType",
        cf: ":0",
        status: ":1",
        assetTypes: "ASSETTYPES"
      }
    }
  },
  expenseList: {
    header: "config/newExpenseList.do",
    inputs: { num: "id" }
  },
  getForwardGlGroup: {//远期换汇根据空方币种获取总账组
	    header: "trade/getForwardGlGroup.do",
	    inputs: { currency: "shortCurrency" }
	  },
  holidayDetail: { //查看节假日详情
    header: "config/holidayDetail.do",
    inputs: { id: "holiday_id" }
  },
  holiday: { //节假日列表中返回节假日详情
    header: "holiday.do",
    inputs: {}
  },
  holidayList: { //节假日返回节假日选择列表
    header: "config/holidayList.do",
    inputs: {}
  },
  bankAcctNameByActID: { //交易费用返回银行账户名称
    header: "config/bankAcctNameByActID.do",
    inputs: { bankAccount: "bankAccount" }
  },
  getPayTradeType: { //交易费用是否随交易支付列表
	    header: "trade/getPayTradeType.do",
	    inputs: {}
	  },
  secAcctNameByAcctID: { //交易费用返回证券账户名称
    header: "config/secAcctNameByAcctID.do",
    inputs: { secAcct: "secAcct" }
  },
  getPayTradeTypeValue: { //交易费用列表返回是否
	    header: "trade/getPayTradeTypeValue.do",
	    inputs: { cashAmount: "cashAmount" }
	  },
  feeCurrencyCode: { //交易费用返回费用货币名称
    header: "config/feeCurrencyCode.do",
    inputs: { tableFeeCurrency: "tableFeeCurrency:23" }
  },
  tradeCurrencyCode: { //交易费用返回交易货币名称
    header: "config/tradeCurrencyCode.do",
    inputs: { feeTradeCurrency: "feeTradeCurrency:23" }
  },
  funcCurrencyCode: { //交易费用返回记账货币名称
    header: "config/funcCurrencyCode.do",
    inputs: { funcCurrency: "funcCurrency" }
  },
  rpTypeByCode: { //交易费用返回应收应付类型
    header: "config/rpTypeByCode.do",
    inputs: { rpType: "rpType" }
  },
  tradeExpenseType: { //交易费用返回交易费用类型
    header: "trade/tradeExpenseType.do",
    inputs: { expense: "expense" }
  },
  returnDateControl: { //日期控件返回当前日期
    header: "market/returnDateControl.do",
    inputs: {}
  },
  cashFlowFloatMethod: {
    header: "config/cashFlowFloatMethod.do",
    inputs: {}
  },
  longCalcFxRate: { //交易费用返回交易费用类型
    header: "trade/newCalcFxRate.do",
    inputs: {
      longCash: "tradeCash2:0",
      shortCash: "tradeCash:0"
    }
  },

  shortCalcFxRate: { //交易费用返回交易费用类型
    header: "trade/newCalcFxRate.do",
    inputs: {
      longCash: "tradeCash:0",
      shortCash: "tradeCash2:0"
    }
  },
  tranPrice: { //计算交易中的价格
    header: "trade/tranPrice.do",
    inputs: {
      secid: "secid",
      share: "shares",
      principal: "principal"
    }
  },
  caSecPositionShares: { //股票公司行为中登记日持有股数
	    header: "trade/caSecPositionShares.do",
	    inputs: {
	      port: "port",
	      secid: "secid",
	      divDate: "divDate"
	    }
	  },
  calcTradeTotal: { //交易金额和结算金额计算
    header: "trade/newCalcTradeTotal.do",
    inputs: {
      tradeType: "tradeType",
      income: "accruedIncome:0",
      expense: "totalExpense",
      trate: "tradeFx",
      srate: "settleFx",
      secid: "secid",
      principal: "principal"
    }
  },
  newReceive_calcTradeTotal: { //交易金额和结算金额计算
	    header: "trade/newCalcTradeTotal.do",
	    inputs: {
	      tradeType: "tradeType",
	      income: ":0",
	      expense: ":0",
	      trate: "tradeFx",
	      srate: "settleFx",
	      secid: "secid",
	      principal: "principal"
	    }
	  },
	 future_calcTradeTotal : { //期货交易金额和结算金额计算
		  header : "trade/newCalcTradeTotal.do",
		  inputs : {
			 tradeType : "tradeType",
			 income : "accruedIncome:0",
			 expense : "totalExpense",
			 trate : "tradeFx",
			 srate : "settleFx",
			 secid : "secid",
			 principal : ":0"
		 }
	 },
	  
  tranPrincipal: { //计算交易中的成本金额
    header: "trade/tranPrincipal.do",
    inputs: {
      secid: "secid",
      share: "shares",
      price: "price"
    }
  },
  returnDivBasicDate: { //利率互换到期返回计息截止日
	    header: "trade/returnLastDivBasicDate.do",
	    inputs: {
	    	 secid: "secid",
	    	 tradeDate: "tradeDate",
	    	 tradeType: "tradeType"
	    }
	  },
  voucherTypeList: { //凭证类型
    header: "info/voucherTypeList.do",
    inputs: {}
  },
  lotChoices: { //期货交易批次
    header: "trade/lotChoices.do",
    inputs: {}
  },
  voucherDetail: { //凭证详情
    header: "info/voucherDetail.do",
    inputs: {
      id: "id"
    }
  },
  voucherList: { //凭证列表
    header: "info/voucherList.do",
    inputs: {
      voucher: "voucherType",
      currency: "voucherCurrency",
      secid: "voucherSecid",
      portfolio: "port",
      start: "start",
      end: "end",
      acctNum: "voucherAcctNum",
      num: ":1"

    }
  },
  basisRates: {
    header: "config/basisRates.do",
    inputs: {}
  }
};

var controllerPrefix = "../../../../";
var formPrefix = "../../../../";
var SYS_PERMIT_CONTROL = false;
var SYS_WORKFLOW_CONTROL = false;

var jsonformdir = [
  "data/forms/test/",
  "data/jsonforms/",
  "data/jsonforms/bank/",
  "data/jsonforms/broker/",
  "data/jsonforms/glgroup/",
  "data/jsonforms/issuer/",
  "data/jsonforms/market/",
  "data/jsonforms/smf/",
  "data/jsonforms/holiday/",
  "data/jsonforms/voucher/",
  "data/jsonforms/trade/",
  "data/jsonforms/trade/forward/",
  "data/jsonforms/trade/FXoption/",
  "data/jsonforms/trade/option/",
  "data/jsonforms/trade/futures/",
  "data/jsonforms/trade/spot/",
  "data/jsonforms/trade/stock/",
  "data/jsonforms/trade/HKstock/",
  "data/jsonforms/trade/swap/",
  "data/jsonforms/trade/caTrade/",
  "data/jsonforms/trade/interest_swap/",
  "data/jsonforms/trade/money_fund/",
  "data/jsonforms/trade/common_fund/",
  "data/jsonforms/trade/cashMove/",
  "data/jsonforms/trade/cashOffset/",
  "data/jsonforms/trade/trade_expense/",
  "data/jsonforms/smf/smf_FXoption/",
  "data/jsonforms/smf/smf_stock/",
  "data/jsonforms/smf/smf_forward/",
  "data/jsonforms/smf/smf_option/",
  "data/jsonforms/smf/smf_futures/",
  "data/jsonforms/smf/smf_swap/",
  "data/jsonforms/smf/smf_money_fund/",
  "data/jsonforms/smf/smf_common_fund/",
  "data/jsonforms/price/"
];
