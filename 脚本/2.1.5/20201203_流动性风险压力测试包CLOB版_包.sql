CREATE OR REPLACE PACKAGE PKG_FLOW_STRESS_TEST_CLOB  IS
  --AUTHOR         -  Jiaoxujin
  --CREATION DATE  -  2019-10-11
  --SERVICE NAME   -  兆尹科技-资管事业部
  --PROJECT NAME   -  邮储压力测试
  --DESCRIPTION    -  流动性风险压力测试
  --Spring datasource validationQuery 验证package的时效性
  FUNCTION FUNC_PKG_VALIDATION RETURN DATE;
  --计算某产品的申赎净流出基础
  FUNCTION FUNC_GET_PROD_BASE(in_finprod_id IN VARCHAR2, in_base_choice IN VARCHAR2,in_base_date IN DATE) RETURN NUMBER;
  --根据资产ID获取它的币种
  FUNCTION FUNC_GET_FINPROD_CCY(in_finprod_id IN VARCHAR2) RETURN VARCHAR2;
  --计算产品头寸的金额
  FUNCTION FUNC_GET_CBTC_AMOUNT(in_finprod_id IN VARCHAR2,in_product_type IN VARCHAR2,in_date IN DATE) RETURN NUMBER;
  --获取指定日期附近每单位外币兑换RMB的数额，可能通过美元汇率折算
  FUNCTION FUNC_CNY(in_asserts_ccy IN VARCHAR2,in_stat_date IN DATE,in_ancestors_name IN VARCHAR2 DEFAULT null) RETURN NUMBER; --多个祖宗用半角逗号隔开
  --将某币种的金额按当时汇率折算成人民币的金额
  FUNCTION FUNC_CNY_TRANS(in_asserts_mount  IN NUMBER,in_asserts_ccy IN VARCHAR2,in_stat_date IN DATE,in_ancestors_name IN VARCHAR2 DEFAULT null) RETURN NUMBER;  --多个祖宗用半角逗号隔开
  --将某资产的金额按当时汇率折算成人民币的金额
  FUNCTION FUNC_CNY_TRANS2(in_asserts_mount  IN NUMBER, in_finprod_id IN VARCHAR2, in_stat_date IN DATE,in_ancestors_name IN VARCHAR2 DEFAULT null) RETURN NUMBER; --多个祖宗用半角逗号隔开
  --根据压测ID查找待压测的产品(组)、资产列表，以管道形式返回结果
  FUNCTION FUNC_STRESS_ELEMENT_PIPELINE(in_test_id IN VARCHAR2, in_object_ids  IN CLOB,in_min_ccsl IN NUMBER,v_object_count IN INTEGER) RETURN STRESS_ELEMENT_TABLE PIPELINED;
  --流动性风险压力测试计算，将计算结果存储到压测指标值表和轻中重度指标值表
  PROCEDURE P_FLOW_STRESS_TEST(in_test_id IN VARCHAR2,in_object_ids IN CLOB,v_object_count IN INTEGER);
END PKG_FLOW_STRESS_TEST_CLOB;
/