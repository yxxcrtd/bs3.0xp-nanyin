CREATE OR REPLACE PACKAGE BODY PKG_FLOW_STRESS_TEST IS
  --AUTHOR         -  Jiaoxujin
  --CREATION DATE  -  2019-10-11
  --SERVICE NAME   -  兆尹科技-资管事业部
  --PROJECT NAME   -  邮储压力测试
  --DESCRIPTION    -  流动性风险压力测试

  --Spring datasource validationQuery 验证package的时效性
  FUNCTION FUNC_PKG_VALIDATION RETURN DATE AS
  BEGIN
    RETURN SYSDATE;
  END;

  --计算某产品的申赎净流出基础
  FUNCTION FUNC_GET_PROD_BASE(in_finprod_id     IN VARCHAR2,
                              in_base_choice    IN VARCHAR2,
                in_base_date     IN DATE)
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-09-04
    * SERVICE NAME   -  兆尹科技-资管事业部
    *
    * PROCEDURE NAME :FUNC_GET_PROD_BASE
    *
    * DESCRIPTION    :计算某产品的申赎净流出基础
    *
    * Parameters :
    *  in_finprod_id      IN  金融产品代码
    *  in_product_type    IN  申赎净流出基础选择 01：最近五次申赎净流出平均，
    *                         02：最近一次申赎净流出，03：产品历史最大申赎净流出
    ******************************************************************************
    * POSSIBLE ERROR CONDITIONS :
    ******************************************************************************
    * CHANGE LOG
    ******************************************************************************
    * CHANGE NUMBER:
    * DATE:
    * DEVELOPER:
    * DESCRIPTION:
    *****************************************************************************/
    RETURN NUMBER AS
    v_result              NUMBER(30, 2);
    v_sql                 VARCHAR2(2000);
    v_finprod_id          VARCHAR2(200);--sunhui:用于查找市场代码对应的金融产品代码
  BEGIN
    IF in_finprod_id IS NULL OR in_base_choice IS NULL THEN
      RETURN null;
    END IF;

    --查找前台传入的产品市场代码对应的金融产品代码--sunhui
    SELECT NVL(fp.finprod_id,in_finprod_id)
      INTO v_finprod_id
      FROM fin_product fp
     WHERE fp.finprod_market_id=in_finprod_id;

    --统计金融产品交易表各金融产品存在申赎流出情况的记录
    v_sql := 'with t1 as' ||
    '(' ||
    'select sh.finprod_id,' ||
    '       sh.trade_date,' ||
    '       case when (nvl(sh.sumAmt, 0) - nvl(sg.sumAmt, 0)) < 0 then 0' ||
    '            else (nvl(sh.sumAmt,0) - nvl(sg.sumAmt, 0))' ||
    '       end as outAmt ' ||
    'from' ||
    ' (select t1.finprod_id, t1.trade_date, sum(nvl(t1.TRADE_AMT, 0)) as sumAmt' ||
    '    from TRD_PRODUCT_DEAL t1' ||
    '   where t1.ps in (''C4'', ''C5'', ''C6'', ''C7'', ''C9'', ''C14'', ''C15'', ''C17'', ''C18'', ''C20'')' ||
    '     and t1.TRADE_STATUS in (''01'', ''02'')' ||
    '     and t1.IS_CANCEL = ''N''' ||
	'    and t1.trade_date <= to_date('''||to_char(in_base_date,'yyyy-mm-dd')||''',''yyyy-mm-dd'')'||
    '   group by t1.finprod_id, t1.trade_date) sh left join' ||
    ' (select t1.finprod_id, t1.trade_date, sum(nvl(t1.TRADE_AMT, 0)) as sumAmt' ||
    '    from TRD_PRODUCT_DEAL t1' ||
    '   where t1.ps in (''C1'', ''C2'', ''C3'')' ||
    '     and t1.TRADE_STATUS in (''01'', ''02'')' ||
    '     and t1.IS_CANCEL = ''N''' ||
    '    and t1.trade_date <= to_date('''||to_char(in_base_date,'yyyy-mm-dd')||''',''yyyy-mm-dd'')'||
    '   group by t1.finprod_id, t1.trade_date) sg on sh.finprod_id = sg.finprod_id and sh.trade_date = sg.trade_date' ||
    ') ';

	
    CASE in_base_choice
      --最近五次申赎净流出平均
      when '01' then
        --v_sql := v_sql || 'select round(avg(t2.outAmt), 2)' ||
        v_sql := v_sql || 'select avg(t2.outAmt)' ||
                          '  from (select t1.*,' ||
                          '               row_number() over(order by t1.trade_date desc) as rownumber' ||
                          '          from t1' ||
                          '         where t1.finprod_id = ''' || v_finprod_id || ''') t2' ||
                          ' where t2.rownumber <= 5';
      --最近一次申赎净流出
      when '02' then
        v_sql := v_sql || 'select t2.outAmt' ||
                          '  from (select t1.*,' ||
                          '               row_number() over(order by t1.trade_date desc) as rownumber' ||
                          '          from t1' ||
                          '         where t1.finprod_id = ''' || v_finprod_id || ''') t2' ||
                          ' where t2.rownumber = 1';
      --产品历史最大申赎净流出
      when '03' then
        v_sql := v_sql || 'select max(t1.outAmt) from t1 where t1.finprod_id = ''' || v_finprod_id || '''';
      else
        RETURN null;

    END CASE;
	

    --DBMS_OUTPUT.put_line ('v_sql: ' || v_sql);
    execute immediate v_sql into v_result;

    RETURN v_result;
  EXCEPTION
    WHEN OTHERS THEN
       DBMS_OUTPUT.put_line('this error message is from function FUNC_GET_PROD_BASE.');
       DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       RETURN null;
  END;

  --根据资产ID获取它的币种
  FUNCTION FUNC_GET_FINPROD_CCY(in_finprod_id IN VARCHAR2)
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-09-20
    * SERVICE NAME   -  兆尹科技-资管事业部
    *
    * PROCEDURE NAME :FUNC_GET_FINPROD_CCY
    *
    * DESCRIPTION    :根据资产ID获取它的币种
    *
    * Parameters :
    *  in_finprod_id            IN  金融产品代码
    ******************************************************************************
    * POSSIBLE ERROR CONDITIONS :
    ******************************************************************************
    * CHANGE LOG
    ******************************************************************************
    * CHANGE NUMBER:
    * DATE:
    * DEVELOPER:
    * DESCRIPTION:
    *****************************************************************************/
    RETURN VARCHAR2 AS
    v_result              VARCHAR2(50) := null;

  BEGIN
      with t1 as
       (select a.finprod_id, a.ccy
          from fin_product a
        union
        select b.finprod_id, b.ccy
          from fin_trade_product b)
      select ccy into v_result from t1
       where t1.finprod_id = in_finprod_id;

      RETURN v_result;
  EXCEPTION
    WHEN OTHERS THEN
       --DBMS_OUTPUT.put_line('this error message is from function FUNC_GET_FINPROD_CCY.');
       --DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       --DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       RETURN null;
  END;

  --计算产品头寸的金额
  FUNCTION FUNC_GET_CBTC_AMOUNT(in_finprod_id     IN VARCHAR2,
                                in_product_type   IN VARCHAR2,
                                in_date           IN DATE)
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-09-02
    * SERVICE NAME   -  兆尹科技-资管事业部
    *
    * PROCEDURE NAME :FUNC_GET_CBTC_AMOUNT
    *
    * DESCRIPTION    :计算产品头寸的金额
    *
    * Parameters :
    *  in_finprod_id      IN  产品ID
    *  in_product_type    IN  产品类型
    *  in_date            IN  日期
    ******************************************************************************
    * POSSIBLE ERROR CONDITIONS :
    ******************************************************************************
    * CHANGE LOG
    ******************************************************************************
    * CHANGE NUMBER:
    * DATE:
    * DEVELOPER:
    * DESCRIPTION:
    *****************************************************************************/
    RETURN NUMBER AS
    v_result              NUMBER(30, 4);
    v_amt                 NUMBER(30, 4);
    v_unit                NUMBER(30, 14);
  BEGIN
      IF in_finprod_id is null or in_product_type is null or in_date is null THEN
          RETURN null;
      END IF;

      with t1 as (
        select to_number(t.param_value) as amt,
               row_number() over (order by in_date - t.cdate) as rowno
          from stt_stat_element t
         where t.cdate between in_date - PKG_STRESS_TEST_COMM.v_max_base_deviation and in_date
           and t.object_id = in_finprod_id
           and t.dict_code='TDY_SHARE_AMT'
      )
      --如果无查询结果，直接exception，return null
      select amt into v_amt from t1 where rowno = 1;

      CASE in_product_type
          --预期收益型
          when '02' then
              v_result := FUNC_CNY_TRANS2(v_amt, in_finprod_id, in_date, 'FUNC_GET_CBTC_AMOUNT');
          --货币型
          when '03' then
              v_result := FUNC_CNY_TRANS2(v_amt, in_finprod_id, in_date, 'FUNC_GET_CBTC_AMOUNT');
          --净值型
          when '01' then
              with t1 as (
                select to_number(t.param_value) as unit,
                       row_number() over (order by in_date - t.cdate) as rowno
                  from stt_stat_element t
                 where t.cdate between in_date - PKG_STRESS_TEST_COMM.v_max_base_deviation and in_date
                   and t.object_id = in_finprod_id
                   and t.dict_code='TDY_UNIT_VALUE'
              )
              --如果无查询结果，直接exception，return null
              select unit into v_unit from t1 where rowno = 1;

              v_result := v_amt * FUNC_CNY_TRANS2(v_unit, in_finprod_id, in_date, 'FUNC_GET_CBTC_AMOUNT');

          else
              v_result := null;
      END CASE;

      RETURN v_result;
  EXCEPTION
    WHEN OTHERS THEN
       --DBMS_OUTPUT.put_line('this error message is from function FUNC_GET_CBTC_AMOUNT.');
       --DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       --DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       RETURN null;
  END;

  --获取指定日期附近每单位外币兑换RMB的数额，可能通过美元汇率折算
  FUNCTION FUNC_CNY(in_asserts_ccy    IN VARCHAR2,
                    in_stat_date      IN DATE,
                    in_ancestors_name IN VARCHAR2 DEFAULT null)
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-11-27
    * SERVICE NAME   -  兆尹科技-资管事业部
    *
    * PROCEDURE NAME :FUNC_CNY
    *
    * DESCRIPTION    :获取指定日期附近每单位外币兑换RMB的数额，可能通过美元汇率折算
    *
    * Parameters :
    *  in_asserts_ccy        IN  外币币种
    *  in_stat_date          IN  汇率日期（资产统计日期）
    *  in_ancestors_name     IN  多个祖宗用半角逗号隔开
    ******************************************************************************
    * POSSIBLE ERROR CONDITIONS :
    ******************************************************************************
    * CHANGE LOG
    ******************************************************************************
    * CHANGE NUMBER:
    * DATE:
    * DEVELOPER:
    * DESCRIPTION:
    *****************************************************************************/
    RETURN NUMBER AS

    v_result         NUMBER(30,14);
    v_price          NUMBER(30,14);   --1美元兑换多少人民币的值
    v_log_head       LOG_HEAD_OBJ;
  BEGIN
    PKG_LOG.P_SET_LOG_HEAD(v_log_head, 'PKG_FLOW_STRESS_TEST.FUNC_CNY', '{"in_asserts_ccy": "'|| in_asserts_ccy ||
                           '", "in_stat_date": "'|| to_char(in_stat_date, 'YYYY-MM-DD') || '"}',
                           in_ancestors_name);

    IF in_asserts_ccy = 'CNY' THEN
      v_result := 1.00;
      RETURN v_result;
    END IF;

    BEGIN
      with t1 as (
          select m.quot_unit, NVL(m.close_price, m.open_price) as price,
                 row_number() over (order by in_stat_date - m.trade_date) as rowno
            from mst_excharge_quote m
                 --若找不到资产价值统计日期的数据，可以往前几天再找一找
           where m.trade_date between in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation and in_stat_date
             and m.basis_cyy  = in_asserts_ccy
             and m.quot_cyy = 'CNY'
      )
      select price / quot_unit into v_result
        from t1
       where t1.rowno = 1;

      EXCEPTION WHEN NO_DATA_FOUND THEN
        PKG_LOG.DEBUG(v_log_head, '在日期范围['|| to_char(in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation, 'YYYY-MM-DD') ||
        '至' || to_char(in_stat_date, 'YYYY-MM-DD') || ']内，没有找到基础币种为' || in_asserts_ccy || '兑人民币的汇率行情数据。');

        BEGIN
          with t1 as (
              select m.quot_unit, NVL(m.close_price, m.open_price) as price,
                     row_number() over (order by in_stat_date - m.trade_date) as rowno
                from mst_excharge_quote m
               where m.trade_date between in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation and in_stat_date
                 and m.basis_cyy  = 'CNY'
                 and m.quot_cyy = in_asserts_ccy
          )
          select quot_unit / price into v_result
            from t1
           where t1.rowno = 1;

          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.DEBUG(v_log_head, '在日期范围['|| to_char(in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation, 'YYYY-MM-DD') ||
            '至' || to_char(in_stat_date, 'YYYY-MM-DD') || ']内，没有找到报价币种为' || in_asserts_ccy || '兑人民币的汇率行情数据。');

            BEGIN
              BEGIN
                --查询美元兑人民币的汇率
                with t1 as (
                    select m.quot_unit, NVL(m.close_price, m.open_price) as price,
                           row_number() over (order by in_stat_date - m.trade_date) as rowno
                      from mst_excharge_quote m
                           --若找不到资产价值统计日期的数据，可以往前几天再找一找
                     where m.trade_date between in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation and in_stat_date
                       and m.basis_cyy  = 'USD'
                       and m.quot_cyy = 'CNY'
                )
                select price into v_price
                  from t1
                 where t1.rowno = 1;

                EXCEPTION WHEN NO_DATA_FOUND THEN
                  PKG_LOG.ERROR(v_log_head, '在日期范围['|| to_char(in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation, 'YYYY-MM-DD') ||
                  '至' || to_char(in_stat_date, 'YYYY-MM-DD') || ']内，没有找到美元兑人民币的汇率行情数据。');

                  --没法算了，直接返回空
                  RETURN NULL;
              END;

              with t1 as (
                  select m.quot_unit, NVL(m.close_price, m.open_price) as price,
                         row_number() over (order by in_stat_date - m.trade_date) as rowno
                    from mst_excharge_quote m
                         --若找不到资产价值统计日期的数据，可以往前几天再找一找
                   where m.trade_date between in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation and in_stat_date
                     and m.basis_cyy  = in_asserts_ccy
                     and m.quot_cyy = 'USD'
              )
              select price / quot_unit into v_result
                from t1
               where t1.rowno = 1;

              --折算成美元再转换成人民币
              v_result := v_result * v_price;

              EXCEPTION WHEN NO_DATA_FOUND THEN
                PKG_LOG.DEBUG(v_log_head, '在日期范围['|| to_char(in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation, 'YYYY-MM-DD') ||
                '至' || to_char(in_stat_date, 'YYYY-MM-DD') || ']内，没有找到基础币种为' || in_asserts_ccy || '兑美元的汇率行情数据。');

                BEGIN
                  with t1 as (
                      select m.quot_unit, NVL(m.close_price, m.open_price) as price,
                             row_number() over (order by in_stat_date - m.trade_date) as rowno
                        from mst_excharge_quote m
                       where m.trade_date between in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation and in_stat_date
                         and m.basis_cyy  = 'USD'
                         and m.quot_cyy = in_asserts_ccy
                  )
                  select quot_unit / price into v_result
                    from t1
                   where t1.rowno = 1;

                  --折算成美元再转换成人民币
                  v_result := v_result * v_price;

                  EXCEPTION WHEN NO_DATA_FOUND THEN
                    PKG_LOG.DEBUG(v_log_head, '在日期范围['|| to_char(in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation, 'YYYY-MM-DD') ||
                    '至' || to_char(in_stat_date, 'YYYY-MM-DD') || ']内，没有找到报价币种为' || in_asserts_ccy || '兑美元的汇率行情数据。');
                END;
            END;
        END;
    END;

    IF v_result IS NULL OR v_result = 0 THEN
      PKG_LOG.ERROR(v_log_head, '在日期范围['|| to_char(in_stat_date - PKG_STRESS_TEST_COMM.v_max_asset_deviation, 'YYYY-MM-DD') ||
                    '至' || to_char(in_stat_date, 'YYYY-MM-DD') || ']内，没有找到币种为' || in_asserts_ccy || '兑人民币或美元的数据。');
      RETURN null;
    ELSE
      RETURN v_result;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
       DBMS_OUTPUT.put_line('this error message is from function PKG_FLOW_STRESS_TEST.FUNC_CNY.');
       DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       PKG_LOG.ERROR(v_log_head, '获取指定日期附近每单位外币兑换RMB的数额时出错', DBMS_UTILITY.format_error_backtrace, SQLCODE, SQLERRM);
       RETURN null;
  END;

  --将某币种的金额按当时汇率折算成人民币的金额
  FUNCTION FUNC_CNY_TRANS(in_asserts_mount  IN NUMBER,
                          in_asserts_ccy    IN VARCHAR2,
                          in_stat_date      IN DATE,
                          in_ancestors_name IN VARCHAR2 DEFAULT null)
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-09-20
    * SERVICE NAME   -  兆尹科技-资管事业部
    *
    * PROCEDURE NAME :FUNC_CNY_TRANS
    *
    * DESCRIPTION    :将某币种的金额按当时汇率折算成人民币的金额
    *
    * Parameters :
    *  in_asserts_mount           IN  资产价值（资产总额）
    *  in_asserts_ccy             IN  资产币种（外币币种）
    *  in_stat_date               IN  资产价值统计日期
    *  in_ancestors_name          IN  多个祖宗用半角逗号隔开
    ******************************************************************************
    * POSSIBLE ERROR CONDITIONS :
    ******************************************************************************
    * CHANGE LOG
    ******************************************************************************
    * CHANGE NUMBER:
    * DATE:
    * DEVELOPER:
    * DESCRIPTION:
    *****************************************************************************/
    RETURN NUMBER AS

    v_result              NUMBER(30,14) := 0;
    v_log_head            LOG_HEAD_OBJ;
  BEGIN
    PKG_LOG.P_SET_LOG_HEAD(v_log_head, 'PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS',
                           '{"in_asserts_mount": '|| in_asserts_mount ||
                           ', "in_asserts_ccy": "'|| in_asserts_ccy ||
                           '", "in_stat_date": "'|| to_char(in_stat_date, 'YYYY-MM-DD') ||
                           '", "in_ancestors_name": "'|| in_ancestors_name || '"}',
                           in_ancestors_name);

    v_result := FUNC_CNY(in_asserts_ccy, in_stat_date, in_ancestors_name || ',PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS');

    IF v_result IS NULL THEN
      PKG_LOG.WARN(v_log_head, '表[MST_EXCHARGE_QUOTE]中找不到币种[' || in_asserts_ccy ||
                   ']与人民币或美元的汇率数据,无法进行汇率折算，资产价值原值返回。',
                   to_clob('{"资产币种": "' || in_asserts_ccy || '"}'));

      v_result := in_asserts_mount;
    ELSE
      v_result := in_asserts_mount * v_result;
    END IF;

    RETURN v_result;
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.put_line('this error message is from function FUNC_CNY_TRANS.');
      DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
      DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
      PKG_LOG.ERROR(v_log_head, '将某币种的金额按当时汇率折算成人民币的金额时出错', DBMS_UTILITY.format_error_backtrace, SQLCODE, SQLERRM);
      RETURN null;
  END;

  --将某资产的金额按当时汇率折算成人民币的金额
  FUNCTION FUNC_CNY_TRANS2(in_asserts_mount  IN NUMBER,
                           in_finprod_id     IN VARCHAR2,
                           in_stat_date      IN DATE,
                           in_ancestors_name IN VARCHAR2 DEFAULT null)
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-09-20
    * SERVICE NAME   -  兆尹科技-资管事业部
    *
    * PROCEDURE NAME :FUNC_CNY_TRANS2
    *
    * DESCRIPTION    :将某资产的金额按当时汇率折算成人民币的金额
    *
    * Parameters :
    *  in_asserts_mount           IN  资产价值（资产总额）
    *  in_finprod_id              IN  金融产品代码
    *  in_stat_date               IN  资产价值统计日期
    *  in_ancestors_name          IN  多个祖宗用半角逗号隔开
    ******************************************************************************
    * POSSIBLE ERROR CONDITIONS :
    ******************************************************************************
    * CHANGE LOG
    ******************************************************************************
    * CHANGE NUMBER:
    * DATE:
    * DEVELOPER:
    * DESCRIPTION:
    *****************************************************************************/
    RETURN NUMBER AS

    v_result              NUMBER(30,14) := 0;
    v_asserts_ccy         VARCHAR2(50);

    CURSOR c_ccy IS
      with t1 as
       (select a.finprod_id, a.ccy
          from fin_product a
        union
        select b.finprod_id, b.ccy
          from fin_trade_product b)
      select ccy from t1
       where t1.finprod_id = in_finprod_id;

    v_log_head            LOG_HEAD_OBJ;
  BEGIN
    PKG_LOG.P_SET_LOG_HEAD(v_log_head, 'PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS2',
                           '{"in_asserts_mount": '|| in_asserts_mount ||
                           ', "in_finprod_id": "'|| in_finprod_id ||
                           '", "in_stat_date": "'|| to_char(in_stat_date, 'YYYY-MM-DD') ||
                           '", "in_ancestors_name": "'|| in_ancestors_name || '"}',
                           in_ancestors_name);

    FOR r_ccy IN c_ccy LOOP
      EXIT WHEN c_ccy%NOTFOUND;
      v_asserts_ccy := r_ccy.ccy;
      EXIT;
    END LOOP;

    IF v_asserts_ccy IS NULL THEN
      PKG_LOG.WARN(v_log_head, '找不到资产[' || in_finprod_id || ']的币种，无法进行币种折算，资产价值原值返回。',
                   to_clob('{"资产代码": "' || in_finprod_id || '"}'));
      v_result := in_asserts_mount;
    ELSE
      v_result := FUNC_CNY_TRANS(in_asserts_mount, v_asserts_ccy, in_stat_date,
                                 in_ancestors_name || ',PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS2');
    END IF;

    RETURN v_result;
  EXCEPTION
    WHEN OTHERS THEN
       DBMS_OUTPUT.put_line('this error message is from function FUNC_CNY_TRANS2.');
       DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       PKG_LOG.ERROR(v_log_head, '将某资产的金额按当时汇率折算成人民币的金额时出错', DBMS_UTILITY.format_error_backtrace, SQLCODE, SQLERRM);
       RETURN null;
  END;

  --根据压测ID查找待压测的产品(组)、资产列表，以管道形式返回结果
  FUNCTION FUNC_STRESS_ELEMENT_PIPELINE(
      in_test_id     IN VARCHAR2,
      in_object_ids  IN VARCHAR2,
      in_min_ccsl    IN NUMBER
  )
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-10-22
    * SERVICE NAME   -  兆尹科技-资管事业部
    * PROJECT_NAME   -  邮储压力测试
    *
    * PROCEDURE NAME :FUNC_STRESS_ELEMENT_PIPELINE
    *
    * DESCRIPTION    :根据压测ID查找待压测的产品(组)、资产列表，以管道形式返回结果
    *
    * Parameters :
    *  in_test_id           IN  压力测试代码
    *  in_object_ids        IN  产品组/产品ID，多个用半角逗号隔开
    *  in_min_ccsl          IN  最小持仓数量，传0表示必须大于0，传-1表示必须大于-1，即不考虑持仓
    ******************************************************************************
    * POSSIBLE ERROR CONDITIONS : 参数in_object_ids长度不能超过32767
    ******************************************************************************
    * CHANGE LOG
    ******************************************************************************
    * CHANGE NUMBER:
    * DATE:
    * DEVELOPER:
    * DESCRIPTION:
    *****************************************************************************/
    RETURN STRESS_ELEMENT_TABLE PIPELINED AS

    v_object_ids             OBJECT_ID_ARRAY := OBJECT_ID_ARRAY();
    v_object_id              VARCHAR2(32);
    v_object_id_list         VARCHAR2(32767);
    v_object_count           INTEGER := 0;

  BEGIN
    --字符串转换成数组
    IF in_object_ids IS NOT NULL AND LENGTH(LTRIM(RTRIM(in_object_ids))) > 0 THEN
      v_object_id_list := ',' || in_object_ids || ',';
      v_object_count := LENGTH(v_object_id_list) - LENGTH(REPLACE(v_object_id_list, ',', '')) - 1;

      FOR v_i IN 1 .. v_object_count LOOP
        v_object_id := SUBSTR(v_object_id_list, INSTR (v_object_id_list, ',', 1, v_i) + 1,
                                                INSTR (v_object_id_list, ',', 1, v_i + 1)
                                              - INSTR (v_object_id_list, ',', 1, v_i) - 1);
        v_object_ids.extend;
        v_object_ids(v_object_ids.last) := v_object_id;
      END LOOP;
    END IF;

    DECLARE
      CURSOR c_elements IS
       select a.base_date,
              a.analysis_vdate,
              a.analysis_mdate,
              b.object_id,
              c.element_id,
              to_number(c.param_value) as ccsl,
              c.cdate,
              row_number() over(partition by c.object_id, c.element_id order by a.base_date - c.cdate) as rowno
         from STT_STRESS_TEST a, STT_TEST_OBJ b, STT_STAT_ELEMENT c
        where a.test_id = in_test_id
          and a.test_id = b.test_id
          and b.object_id = c.object_id --就是产品
          and c.cdate between a.base_date - PKG_STRESS_TEST_COMM.v_max_base_deviation and a.base_date --找不到基准日的持仓数据，可以往前几天再找一找
          and c.element_id is not null
          and c.dict_code = 'SHARE_AMT' --组合资产估值
          and PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_object_ids, b.object_id) = 1
          and nvl(to_number(c.param_value), 0) > in_min_ccsl;

    BEGIN
      FOR r_element IN c_elements LOOP
        PIPE ROW(STRESS_ELEMENT_OBJ(r_element.base_date,
                                    r_element.analysis_vdate,
                                    r_element.analysis_mdate,
                                    r_element.object_id,
                                    r_element.element_id,
                                    r_element.ccsl,
                                    r_element.cdate,
                                    r_element.rowno));
      END LOOP;
    END;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
       DBMS_OUTPUT.put_line('this error message is from function FUNC_STRESS_ELEMENT_PIPELINE.');
       DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       RETURN;
  END;

  --流动性风险压力测试计算，将计算结果存储到压测指标值表和轻中重度指标值表
  PROCEDURE P_FLOW_STRESS_TEST(
      in_test_id     IN VARCHAR2,
      in_object_ids  IN VARCHAR2 DEFAULT ''
    ) AS
    /**********************************************************************************
     * AUTHOR         -  Jiaoxujin
     * CREATION DATE  -  2019-09-05
     * SERVICE NAME   -  兆尹科技-资管事业部
     *
     * PROCEDURE NAME: P_FLOW_STRESS_TEST
     *
     * DESCRIPTION: 流动性风险压力测试计算，将计算结果存储到压测指标值表和轻中重度指标值表
     *              共25个指标：1001——1009 、 10001——10014 、 10028 、 1000401
     *
     * Parameters:
     *   in_test_id            IN   压力测试代码
     *   in_object_ids         IN  产品组/产品ID，多个用半角逗号隔开，可为空
     *
     * CALLING PROCEDURE:      FUNC_GET_CBTC_AMOUNT,FUNC_GET_PROD_BASE
     *
     ***********************************************************************************
     * POSSIBLE ERROR CONDITIONS:
     ***********************************************************************************
     * CHANGE LOG
     ***********************************************************************************
     * CHANGE NUMBER: 1
     * DATE:          2019-11-20
     * DEVELOPER:     JIAOXUJIN
     * DESCRIPTION:   添加5个压测指标输出：1001001~1001004  10014001(F04)，
     *                原10014正回购，改为质押式正回购到期：F02
     **********************************************************************************/

      v_dicount_level1                  NUMBER(9, 6) := 1.0; --一级流动性资产市值折现系数
      v_create_uesr                     VARCHAR2(20) := 'P_FLOW_STRESS_TEST';
      v_create_dept                     VARCHAR2(32) := 'system';
      v_update_uesr                     VARCHAR2(20) := 'P_FLOW_STRESS_TEST';

      v_cash_shortfall_light            NUMBER(30,14); --情景现金缺口(轻)
      v_cash_shortfall_medium           NUMBER(30,14); --情景现金缺口(中)
      v_cash_shortfall_heavy            NUMBER(30,14); --情景现金缺口(重)
      v_leverage_ratio                  NUMBER(30,14); --杠杆率
      v_leverage_light                  NUMBER(30,14); --杠杆率(轻)
      v_leverage_medium                 NUMBER(30,14); --杠杆率(中)
      v_leverage_heavy                  NUMBER(30,14); --杠杆率(重)
      v_d_light                         NUMBER(30,14); --二级流动性资产市值 * 二级流动性资产变化率（轻）
      v_d_medium                        NUMBER(30,14); --二级流动性资产市值 * 二级流动性资产变化率（中）
      v_d_heavy                         NUMBER(30,14); --二级流动性资产市值 * 二级流动性资产变化率（重）
      v_r_light                         NUMBER(30,14); --轻度产品赎回、轻度申赎净流出
      v_r_medium                        NUMBER(30,14); --中度产品赎回、中度申赎净流出
      v_r_heavy                         NUMBER(30,14); --重度产品赎回、重度申赎净流出

      v_previous_object_id              VARCHAR2(32);
      v_level1MarkketValue              NUMBER(30,14); --某产品一级流动性资产市值
      v_level2MarkketValue              NUMBER(30,14); --某产品二级流动性资产市值
      v_light_value                     NUMBER(30,14);
      v_medium_value                    NUMBER(30,14);
      v_heavy_value                     NUMBER(30,14);

      v_scene_id                        VARCHAR2(32) := null; --压测情景代码
      v_indicator_list                  INDICATOR_VALUE_TABLE := INDICATOR_VALUE_TABLE();

      --产品规模(一) 申赎净流出[基础](二) 产品赎回(十七###)
      CURSOR c_cpgm_ssjlc_cpsh IS
      with t2 as (
      select object_id,
             cptc_object_id,
             ccsl,
             FUNC_GET_CBTC_AMOUNT(cptc_object_id, param_value, base_date) as cbgm  --产品规模
        from
            (select t1.*,
                    x.object_id as cptc_object_id,     -- 产品头寸表过来的object_id具有F16_前缀
                    x.param_value,
                    row_number() over (partition by x.object_id order by t1.base_date - x.cdate) as rowno
               from (
                       select base_date, object_id, sum(ccsl) as ccsl    --产品持仓数量
                         from table(FUNC_STRESS_ELEMENT_PIPELINE(in_test_id, in_object_ids, -1)) bo
                        where rowno = 1
                        group by base_date, object_id
                       having sum(ccsl) > 0
                    ) t1,
                    (
                        select c.cdate, c.object_id, d.param_value
                         from stt_stat_element c
                         left join stt_object_attr d
                           on substr(c.object_id, instr(c.object_id, '_') + 1) = d.object_id     -- 产品头寸表过来的object_id具有F16_前缀
                          and d.dict_code = 'PROFIT_TYPE'
                        where c.element_id is null
                          and c.dict_code = 'TDY_SHARE_AMT' --产品头寸
                     ) x
              where t1.object_id = substr(x.object_id, instr(x.object_id, '_') + 1)
                and x.cdate between t1.base_date - PKG_STRESS_TEST_COMM.v_max_base_deviation and t1.base_date) tc
       where tc.rowno = 1
      ),
      t3 as
      (
          --压测因子
          select g.object_id,
                 c.ftr_id,
                 d.light_value,
                 d.medium_value,
                 d.heavy_value,
                 c.baseChoice,
         a.base_date
            from stt_stress_test a,
                 stt_stress_scene b,
                 (select k.ftr_id, k.scene_id, k.scene_ftr_id, h.param_value as baseChoice
                    from stt_scene_factor k
                    left join stt_scene_ftrtips h
                      on k.scene_ftr_id = h.scene_ftr_id
                     and k.scene_id = h.scene_id
                     and h.dict_code = 'NET_OUTFLOW_BASE_CHOICE') c,
                 stt_scene_lmh d,
                 stt_scene e,
                 stt_factor f,
                 stt_test_obj g
           where a.test_id = in_test_id
             and a.test_id = b.busi_id
             and a.test_id = g.test_id
             and b.stt_busi_type = '04'
             and b.scene_id = c.scene_id
             and c.scene_ftr_id = d.scene_ftr_id
             and (c.ftr_id = 'PrdScl01001' or c.ftr_id = 'PrdScl01002')
             and c.scene_id = e.scene_id
             and e.eff_status = '01'
             and c.ftr_id = f.ftr_id
             and f.eff_status = '01'
      )
      select t2.object_id,
             t2.ccsl,         --持仓数量
             t2.cbgm,         --产品规模
             func_get_prod_base(t2.cptc_object_id, t3.baseChoice,t3.base_date) as exitBase,                                 --产品申赎净流出基础
             decode(t3.ftr_id,
                    'PrdScl01002',
                    t2.cbgm * t3.light_value,                                                                  --轻度产品赎回
                    (1 + t3.light_value) * func_get_prod_base(t2.cptc_object_id, t3.baseChoice,t3.base_date)) as lightCbsh, --轻度申赎净流出
             decode(t3.ftr_id,
                    'PrdScl01002',
                    t2.cbgm * t3.medium_value,                                                                 --中度产品赎回
                    (1 + t3.medium_value) * func_get_prod_base(t2.cptc_object_id, t3.baseChoice,t3.base_date)) as midCbsh,  --中度申赎净流出
             decode(t3.ftr_id,
                    'PrdScl01002',
                    t2.cbgm * t3.heavy_value,                                                                  --重度产品赎回
                    (1 + t3.heavy_value) * func_get_prod_base(t2.cptc_object_id, t3.baseChoice,t3.base_date)) as heavyCbsh  --重度申赎净流出

        from t2,t3
       where t2.object_id = t3.object_id;

      --拆入到期(十八) 债券借贷到期(十九) 融入资产到期(正回购)(二十)
      CURSOR c_crdq_zqjddq_rrzcdq IS
      select t5.object_id,
             sum(decode(t5.finprodType2, 'F08', t5.mTradeAmt, null)) as crdqze, --拆入到期总额
             sum(decode(t5.finprodType2, 'F29', t5.mTradeAmt, null)) as zqjddqze, --债券借贷到期总额
             --sum(decode(t5.finprodType2, 'F02', t5.mTradeAmt, 'F04', t5.mTradeAmt, null)) as zhg, --融入资产到期（正回购）--commented by JIAOXUJIN ON 2019-11-20
             sum(decode(t5.finprodType2, 'F02', t5.mTradeAmt, null)) as zyszhg, --质押式正回购到期 Added by JIAOXUJIN on 2019-11-20
             sum(decode(t5.finprodType2, 'F04', t5.mTradeAmt, null)) as mdszhg --买断式正回购到期 Added by JIAOXUJIN on 2019-11-20
        from (
            with t1 as
             (
               select analysis_vdate, analysis_mdate, object_id, element_id
                 from table(FUNC_STRESS_ELEMENT_PIPELINE(in_test_id, in_object_ids, 0)) bo
                where rowno = 1
             ),
            t7 as
             (select t2.element_id, t2.finprodType2, t3.mDate, t4.mTradeAmt
                from --纵表转换成横表
                  (select d.element_id, d.param_value as finprodType2
                     from stt_element_attr d
                    where d.dict_code = 'FINPROD_TYPE_TG'
                      --拆入\债券借贷
                      and (d.param_value = 'F08' or d.param_value = 'F29' or
                           d.param_value = 'F02' or d.param_value = 'F04')) t2
                  inner join
                  (select d.element_id, to_number(d.param_value) as mTradeAmt
                     from stt_element_attr d
                    where d.dict_code = 'M_TRADE_AMT') t4
                  on t2.element_id = t4.element_id
                  left join
                  (select d.element_id, to_date(d.param_value, 'YYYY-MM-DD') as mDate
                     from stt_element_attr d
                    where d.dict_code = 'MDATE') t3
                  on t2.element_id = t3.element_id)
            select t1.object_id, t1.element_id, t7.finprodType2, t7.mTradeAmt
              from t1
              left join t7
              on (t1.element_id = t7.element_id
              and nvl(t7.mDate, to_date('29991231', 'YYYYMMDD')) >= nvl(t1.analysis_vdate,to_date('19700101', 'YYYYMMDD'))
              and nvl(t7.mDate, to_date('19700101', 'YYYYMMDD')) <= nvl(t1.analysis_mdate,sysdate))
        ) t5
       group by t5.object_id;

      --情景现金缺口 = 现金缺口(三###) = 流入 一 流出 + 现金余额
      CURSOR c_cash_shortfall IS
      with t1 as
       (
           select base_date, object_id, sum(ccsl) as ccsl
             from table(FUNC_STRESS_ELEMENT_PIPELINE(in_test_id, in_object_ids, -1)) bo
            where rowno = 1
            group by base_date, object_id
           having sum(ccsl) > 0
       ),
      t2 as
       (
        select dd.object_id, sum(dd.cashRemaining) as cashRemaining --现金余额，可能有不同币种的资产，如何求和？
          from
            (select t1.object_id, to_number(a.param_value) as cashRemaining,
                   dense_rank() over (partition by a.object_id order by t1.base_date - a.cdate) as rowno
              from t1
              left join stt_stat_element a
                on t1.object_id = a.object_id
               and a.dict_code = 'VALUE'
               and a.cdate between t1.base_date - PKG_STRESS_TEST_COMM.v_max_base_deviation and t1.base_date) dd
         where rowno = 1
         group by dd.object_id
       ),
      t3 as
       (select t1.object_id,
               --流入资产
               sum(case when (to_number(a.ind_id) >= 10001 and to_number(a.ind_id) <= 10010) or a.ind_id = '1000401' then to_number(a.value) else null end) as enterCash,
               --产品赎回 轻、中、重 or 申赎净流出 轻、中、重
               sum(case when a.value_struct = '02' and (a.ind_id = '10011' or a.ind_id = '10028')
                        then decode(sign(a.light_value), -1, 0, a.light_value) else null end) as cbshLight,
               sum(case when a.value_struct = '02' and (a.ind_id = '10011' or a.ind_id = '10028')
                        then decode(sign(a.medium_value), -1, 0, a.medium_value) else null end) as cbshMedium,
               sum(case when a.value_struct = '02' and (a.ind_id = '10011' or a.ind_id = '10028')
                        then decode(sign(a.heavy_value), -1, 0, a.heavy_value) else null end) as cbshHeavy,
               --其它流出资产
               sum(case when (to_number(a.ind_id) >= 10012 and to_number(a.ind_id) <= 10015) or a.ind_id = '10014001' then --Modified by JIAOXUJIN on 2019-11-20
                             to_number(a.value) else null end) as otherExitCash
          from t1
          left join (select b.object_id,
                            b.ind_id,
                            b.value_struct,
                            b.value,
                            c.light_value,
                            c.medium_value,
                            c.heavy_value
                        from stt_stress_inddata b
                        left join STT_INDDATA_LMH c
                          on b.seq_no = c.seq_no
                         and b.ind_id = c.ind_id
                         and b.test_id = c.test_id
                       where b.test_id = in_test_id
                      ) a
            on t1.object_id = a.object_id
           and (to_number(a.ind_id) between 10001 and 10015 or a.ind_id = '10028' or a.ind_id = '1000401' or a.ind_id = '10014001')  --Modified by JIAOXUJIN on 2019-11-20
         group by t1.object_id)
      select nvl(t2.object_id, t3.object_id) as object_id,
             t2.cashRemaining, --现金余额
             t3.enterCash,     --流入
             case when t3.otherExitCash is null and t3.cbshLight is null then
                 null
             else
                 nvl(t3.otherExitCash, 0) + nvl(t3.cbshLight, 0)
             end as lightExitCash,
             case when t3.otherExitCash is null and t3.cbshMedium is null then
                 null
             else
                 nvl(t3.otherExitCash, 0) + nvl(t3.cbshMedium, 0)
             end as mediumExitCash,
             case when t3.otherExitCash is null and t3.cbshHeavy is null then
                 null
             else
                 nvl(t3.otherExitCash, 0) + nvl(t3.cbshHeavy, 0)
             end as heavyExitCash,
             nvl(t3.otherExitCash, 0) as otherExitCash  --Added by JIAOXUJIN on 2019-11-20
        from t2
        full outer join t3
          on t2.object_id = t3.object_id;

      --杠杆率(六) = 总资产/净资产
      CURSOR c_leverage_ratio IS
      with t1 as
       (
           select base_date, object_id, sum(ccsl) as ccsl
             from table(FUNC_STRESS_ELEMENT_PIPELINE(in_test_id, in_object_ids, -1)) bo
            where rowno = 1
            group by base_date, object_id
           having sum(ccsl) > 0
       )
      select jj.object_id,
             sum(decode(jj.dict_code, 'TDY_ASSET_NETVALUE', to_number(jj.param_value), null)) as jzc, --净资产
             sum(decode(jj.dict_code, 'TDY_ASSET_VALUE', to_number(jj.param_value), null)) as zzc --总资产
        from
          (
              select a.object_id, a.dict_code, a.param_value,
                     row_number() over (partition by a.object_id, a.dict_code order by t1.base_date - a.cdate) as rowno
                from STT_STAT_ELEMENT a, t1
               where a.object_id = t1.object_id
                 and a.cdate between t1.base_date - PKG_STRESS_TEST_COMM.v_max_base_deviation and t1.base_date
                 and (a.dict_code = 'TDY_ASSET_NETVALUE' or a.dict_code = 'TDY_ASSET_VALUE')
          ) jj
       where rowno = 1
       group by jj.object_id;

      --流入十指标（10001——10010  1000401，七至十六）
      CURSOR c_inflow_indicators IS
      with baseReselt as (
          select object_id,
                 element_id,
                 source_type, --金融产品分类
                 type_1,      --投管资产大类
                 type_2,      --投管资产一级分类
                 type_3,      --投管资产二级分类
                 cash_type_1,
                 cash_type_2,
                 cash_type,
                 sum(cashflow) as cashflows,
                 --sunhui:取出基准日 2019-1122
                 BASE_DATE
            from (select t."OBJECT_ID",
                         t."ELEMENT_ID",
                         t."TYPE_1",
                         t."TYPE_2",
                         t."TYPE_3",
                         t."SHARE_AMT",
                         t."ANALYSIS_VDATE",
                         t."ANALYSIS_MDATE",
                         t."BASE_DATE",
                         t.source_type,
                         t1.cash_id,
                         t1.cash_type_1,     --CASH_TYPE_1：01本金、02利息
                         t1.cash_type_2,     --CASH_TYPE_2：C01本金、C02利息
                         t2.cash_type,       --CASH_CLASS_TYP：01中间收息、02中间还本、03到期还本、04到期收息、05费用支付
                         t2.vdate,
                         t2.mdate,
                         t2.PAY_DATE,
                         --t1.ccy,
                         --t2.CASH_AMT,
                         --t2.CASH_BASEAMT,
                         case
                           when t.type_1 = 'F02' then
                            to_number(decode(t2.CASH_BASEAMT,
                                             null,
                                             null,
                                             0,
                                             null,
                                             -1 * nvl(t.share_amt, 0) *
                                             FUNC_CNY_TRANS(nvl(t2.CASH_AMT, 0),
                                                            t1.ccy,
                                                            t.base_date, 'P_FLOW_STRESS_TEST') / t2.CASH_BASEAMT))
                           when t.type_1 = 'F01' then
                            nvl(t.share_amt, 0) *
                            FUNC_CNY_TRANS(nvl(t2.CASH_AMT, 0), t1.ccy, t.base_date, 'P_FLOW_STRESS_TEST')
                           when t.type_1 = 'F07' or t.type_1 = 'F08' or t.type_1 = 'F10' or t.type_1 = 'F12' then
                            FUNC_CNY_TRANS(nvl(t2.CASH_AMT, 0), t1.ccy, t.base_date, 'P_FLOW_STRESS_TEST')
                           else
                            to_number(decode(t2.CASH_BASEAMT,
                                             null,
                                             null,
                                             0,
                                             null,
                                             nvl(t.share_amt, 0) *
                                             FUNC_CNY_TRANS(nvl(t2.CASH_AMT, 0),
                                                            t1.ccy,
                                                            t.base_date, 'P_FLOW_STRESS_TEST') / t2.CASH_BASEAMT))
                         end as cashflow
                    from (select result1.object_id,
                                 --to_number(result3.param_value) * nvl(to_number(result4.param_value), 1) as prodAmt,
                                 result1.element_id,
                                 result2.source_type,--金融产品分类
                                 result2.type_1,
                                 result2.type_2,
                                 result2.type_3,
                                 --result1.cdate,
                                 result1.share_amt,
                                 result1.analysis_vdate,
                                 result1.analysis_mdate,
                                 result1.base_date
                            from (select sse1.object_id,
                                         sse1.element_id,
                                         --sse1.cdate,
                                         sse1.ccsl as share_amt,
                                         sse1.analysis_vdate,
                                         sse1.analysis_mdate,
                                         sse1.base_date
                                    from (select *
                                            from table(FUNC_STRESS_ELEMENT_PIPELINE(in_test_id, in_object_ids, 0)) bo
                                           where rowno = 1) sse1
                                  ) result1

                            left join (select se.element_id,
                                             se.object_name,
                                             se.source_type,--金融产品分类
                                             se.type_1,
                                             se.type_2,
                                             se.type_3
                                        from STT_ELEMENT se) result2
                              on result1.element_id = result2.element_id) t

                    left join FIN_CASH_INFO t1
                      on t.element_id = t1.finprod_id

                    left join FIN_CASH_PLAN t2
                      on t1.cash_id = t2.cash_id
                     and t2.PAY_DATE >= nvl(t.analysis_vdate, to_date('19700101', 'YYYYMMDD'))
                     and t2.PAY_DATE <= nvl(t.analysis_mdate, sysdate)
                 )
           group by object_id,element_id,cash_type_1,cash_type_2,cash_type,source_type,type_1,type_2,type_3,BASE_DATE --sunhui:2019-11-22 增加基准日分组条件
      )

      select
           baseReselt.object_id as prod_id,
           '10001' as IND_ID,--债券收息
           sum(case when
             (baseReselt.type_1='F01' and
             baseReselt.cash_type_1='02' and
             (baseReselt.cash_type='01'or baseReselt.cash_type='04')
             )
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id

      union all

      select
           baseReselt.object_id as prod_id,
           '10002' as IND_ID,--存款收息
           sum(case when
             ((baseReselt.type_1='F05' or type_1='F21') and
             baseReselt.cash_type_1='02' and
             (baseReselt.cash_type='01'or baseReselt.cash_type='04')
             )
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id

      union all

      select
           baseReselt.object_id as prod_id,
           '10003' as IND_ID,--利率型计划类型资产的收息
           sum(case when
             (baseReselt.type_1='F03' and
             baseReselt.cash_type_1='02' and
             (baseReselt.cash_type='01'or baseReselt.cash_type='04')
             )
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id

      union all

      select
           baseReselt.object_id as prod_id,
           '10004' as IND_ID,--项目类收息
           sum(case when
             (baseReselt.type_1='F06' and
              baseReselt.cash_type_1='02' and
              (baseReselt.cash_type='01' or baseReselt.cash_type='04'))--20191226孙辉增加项目类资产到期收息
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id

      union all

      select
           baseReselt.object_id as prod_id,
           '10005' as IND_ID,--债券本方券到期
           sum(case when
             (baseReselt.type_1='F01' and
             baseReselt.cash_type_1='01' and
             (baseReselt.cash_type='02'or baseReselt.cash_type='03')
             )
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id

      union all

      select
           baseReselt.object_id as prod_id,
           '10006' as IND_ID,--存款到期
           sum(case when
             (baseReselt.type_1='F05' and
             baseReselt.cash_type_1='01' and
             (baseReselt.cash_type='02'or baseReselt.cash_type='03')
             )
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id

      union all
      --sunhui:计划类资产到期
      --sunhui:2019-11-22注释
      /*select
           baseReselt.object_id as prod_id,
           '1000701' as IND_ID,--计划类资产到期
           sum(case when
             ((baseReselt.type_1='F03' or baseReselt.type_1='F04') and --计划类资产（利率型）+计划类资产（净值型）--sunhui
             baseReselt.cash_type_1='01' and      --本金
             baseReselt.cash_type='03')           --到期还本
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id*/

      --sunhui:2019-11-22调整  start
      --计划类资产到期：计划类利率型取现金流+计划类净值型/货币型取头寸中市值
      select
      q1.object_id  as prod_id,
      '1000701' as IND_ID,--计划类资产到期
      nvl(q1.cash1,0)+nvl(q2.cash2,0) as cash
      from
      (
      select
           baseReselt.object_id,
           sum(case when
                    (baseReselt.type_1='F03' and --计划类资产（利率型）
                    baseReselt.cash_type_1='01' and      --本金
                    (baseReselt.cash_type='02' or baseReselt.cash_type='03'))           --到期还本
                    then cashflows
               else null
           end)
      as cash1
      from baseReselt
      group by baseReselt.object_id
      ) q1
      full join
      (
        select baseReselt.object_id,
               sum(nvl(sse.param_value,0)) as cash2
        from baseReselt
        left join stt_stat_element sse
        on sse.object_id=baseReselt.object_id
        and sse.element_id=baseReselt.element_id
        and sse.cdate=baseReselt.BASE_DATE
        where (baseReselt.type_1='F04' --计划类（净值型）
              or baseReselt.type_1='F09')--计划类（货币型）
        and baseReselt.cash_type_1='01'
        and sse.param_code='MARKET_VALUE'
        group by baseReselt.object_id
        )q2  on q1.object_id = q2.object_id
      --sunhui:2019-11-22调整  end
      union all

      select
           baseReselt.object_id as prod_id,
           '10007' as IND_ID,--拆出到期
           sum(case when
             (
             (baseReselt.type_1='F08' and baseReselt.type_3='02') and   --拆借--拆出
             (baseReselt.cash_type_1='01' or baseReselt.cash_type_1='02') and  --本金、利息
             (baseReselt.cash_type='03'or baseReselt.cash_type='04')           --到期还本、到期付息
             )
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id

      union all

      select baseReselt.object_id as prod_id,
             '10008' as IND_ID, --拆出到期计划类资产到期
             sum(case
                   when (baseReselt.source_type = 'F30' and  --投管金融产品分类【F30债券借贷融入】
                         baseReselt.type_1 = 'F12' and       --投管资产大类【F12债券借贷】
                         baseReselt.cash_type_1 = '02' and   --利息
                         baseReselt.cash_type = '04'         --到期收息
                        ) then
                    cashflows
                   else
                    null
                 end) as cash
        from baseReselt
       group by baseReselt.object_id

      union all

      ----与stt_element_attr表中取出的值二选一，约1000行
      select baseReselt.object_id as prod_id,
             '10013' as IND_ID,                              --债券借贷到期(流出)
             sum(case
                   when (baseReselt.source_type = 'F29' and  --投管金融产品分类【F30债券借贷融入】
                         baseReselt.type_1 = 'F12' and       --投管资产大类【F12债券借贷】
                         baseReselt.cash_type_1 = '02' and   --利息
                         baseReselt.cash_type = '04'         --到期收息
                        ) then
                    cashflows
                   else
                    null
                 end) as cash
        from baseReselt
       group by baseReselt.object_id

      union all

      select
           baseReselt.object_id as prod_id,
           '10009' as IND_ID,--质押式逆回购到期
           sum(case when
           --sunhui:2019-11-21
             ((baseReselt.source_type = 'F03' or (baseReselt.type_1='F07' and baseReselt.type_3='04')) and--质押式逆回购
             (baseReselt.cash_type_1='01' or baseReselt.cash_type_1='02') and --本金、利息
             (baseReselt.cash_type='03' or baseReselt.cash_type='04')--到期还本、到期收息
             )
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id

      union all

      select
           baseReselt.object_id as prod_id,
           '10010' as IND_ID,--买断式逆回购到期
           sum(case when
           --sunhui:2019-11-21
             ((baseReselt.source_type = 'F05' or (baseReselt.type_1='F10' and baseReselt.type_3='04')) and--买断式逆回购
             (baseReselt.cash_type_1='01' or baseReselt.cash_type_1='02') and --本金、利息
             (baseReselt.cash_type='03' or baseReselt.cash_type='04')--到期还本、到期收息
             )
             then cashflows
               else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id

      union all

      select
           baseReselt.object_id as prod_id,
           '1000401' as IND_ID,--项目类到期
           sum(case when
           (baseReselt.type_1='F06' and --项目类资产
            baseReselt.cash_type_1='01' and --本金
           (baseReselt.cash_type='02' or baseReselt.cash_type='03')--中间还本、到期还本
           )
           then cashflows
           else null
           end) as cash
      from baseReselt
      group by baseReselt.object_id;

      ----------------------------------------以下为统计中间指标----------------------------------------

      ---一级流动性资产市值*折现系数（默认为1）
      ---二级流动性资产总市值*二级流动性资产变化率
      CURSOR c_market_value IS
      with t2 as
       (select t1.base_date,
               t1.analysis_vdate,
               t1.analysis_mdate,
               t1.object_id,
               t1.element_id,
               --tgt_value: 01一级流动性资产，02二级流动性资产
               case d.tgt_value
                 when '01' then '1'
                 when '02' then '2'
                 else 'Na'
               end as assetslevel,
               d.dict_code || ':' || d.souce_value as detailType
          from (
                   select base_date, analysis_vdate, analysis_mdate, object_id, element_id
                     from table(FUNC_STRESS_ELEMENT_PIPELINE(in_test_id, in_object_ids, 0)) bo
                    where rowno = 1
               ) t1
          left join (select a.element_id, b.tgt_value, a.dict_code, c.souce_value
                       from stt_element_attr a, stt_mapping b, stt_mapping_det c
                      where b.mapping_id = c.mapping_id
                        and a.param_code = c.souce_code
                        and a.param_value = c.souce_value) d
            on t1.element_id = d.element_id),
      t3 as
      (
       select lm.object_id,
              lm.detailType,
              lm.assetslevel,
              sum(decode(lm.assetslevel, '1',
                  case
                    when lm.zyhrcRate >=0 and lm.isUse <> 0 then lm.param_value * lm.zyhrcRate
                    else lm.param_value
                  end,
              null)) as level1MarkketValue,
              sum(decode(lm.assetslevel, '2',
                  case
                    when lm.zyhrcRate >=0 and lm.isUse <> 0 then lm.param_value * lm.zyhrcRate
                    else lm.param_value
                  end,
              null)) as level2MarkketValue
         from
            (select t5.object_id, t5.assetslevel,
                    t5.detailType,
                    case t5.assetslevel
                      when '1' then
                        decode(t5.finprodType2, 'F02', -abs(t5.param_value), 'F04', -abs(t5.param_value),
                               'F03', abs(t5.param_value), 'F05', abs(t5.param_value), t5.param_value)
                      when '2' then
                        to_number(t5.param_value)
                    end as param_value,
                    t5.finprodType2,
                    sum(decode(t5.param_code, '03', t5.mrhzyhrc, -nvl(t5.mrhzyhrc,0))) /
                    nvl(sum(decode(t5.param_code, '03', t5.mrhzyhrc, null)), 1) as zyhrcRate, --若结果小于0，则不参与计算
                    sum(nvl(t5.mrhzyhrc,0)) as isUse --若等于0，则不参与计算
               from
             (select t2.object_id, t2.assetslevel, a.param_value, cc.param_value as finprodType2,
                     bb.param_code,
                     bb.param_value as mrhzyhrc,
                     t2.detailType,
                     row_number() over (partition by a.object_id, a.element_id, bb.param_code order by t2.base_date - a.cdate) as rowno
                from t2 left join (
                  select dd.element_id, to_date(dd.param_value, 'YYYY-MM-DD') as mDate
                    from stt_element_attr dd
                   where dd.dict_code = 'MDATE'
                ) mm
                on t2.element_id = mm.element_id

                left join stt_stat_element a
                  on t2.object_id = a.object_id
                 and t2.element_id = a.element_id
                 and a.dict_code = 'MARKET_VALUE'
                 and a.cdate between t2.base_date - PKG_STRESS_TEST_COMM.v_max_base_deviation and t2.base_date

                left join stt_stat_element bb
                  on t2.object_id = bb.object_id
                 and t2.element_id = bb.element_id
                 and bb.dict_code = 'POSITION_TYPE'
                 --买入 质押 融出
                 and ((bb.param_code = '03' and bb.param_value is not null and bb.param_value <> 0) or bb.param_code = '04' or bb.param_code = '06')
                 and bb.cdate = a.cdate

                left join stt_element_attr cc
                  on t2.element_id = cc.element_id
                 and cc.dict_code = 'FINPROD_TYPE_TG'
                 --正回购、负回购
                 and (cc.param_value = 'F03' or cc.param_value = 'F05' or cc.param_value = 'F02' or cc.param_value = 'F04')
               --sunhui:一二级流动性资产则只统计分析结束日未到期的，即资产到期日>指标计算结束日
               where nvl(mm.mDate, to_date('29991231', 'YYYYMMDD')) > nvl(t2.analysis_mdate,sysdate)) t5
              where t5.rowno = 1
              group by t5.object_id, t5.assetslevel, t5.detailType, t5.param_value, t5.finprodType2
            ) lm
        --乘以质押或融出的比例
        group by lm.object_id, lm.detailType, lm.assetslevel
      ),
      t4 as
       (select g.object_id,   --是少数
               b.scene_id,
               c.ftr_id,
               d.light_value,
               d.medium_value,
               d.heavy_value
          from stt_stress_test  a,
               stt_stress_scene b,
               stt_scene        e,
               stt_factor       f,
               stt_scene_factor c,
               stt_scene_lmh    d,
               stt_test_obj     g
         where a.test_id = in_test_id
           and a.test_id = b.busi_id
           and b.stt_busi_type = '04'
           and a.test_id = g.test_id
           and b.scene_id = c.scene_id
           and c.scene_id = e.scene_id
           and e.eff_status = '01'
           and c.ftr_id = f.ftr_id
           and f.eff_status = '01'
           and c.scene_ftr_id = d.scene_ftr_id
           and c.ftr_id = 'AssLqu01001')
      select t3.object_id,
             t3.detailType,
             t3.assetslevel,
             t3.level1MarkketValue,   --可能为null
             t3.level2MarkketValue,   --可能为null
             t4.light_value,          --可能为null number(30,14)
             t4.medium_value,         --可能为null
             t4.heavy_value           --可能为null
        from t3 left join t4
          on t3.object_id = t4.object_id;

      r_market_row c_market_value%rowtype;

      ---现金余额
      CURSOR c_cash_remaining IS
      with t1 as
       (
           select base_date, object_id, sum(ccsl) as ccsl
             from table(FUNC_STRESS_ELEMENT_PIPELINE(in_test_id, in_object_ids, -1)) bo
            where rowno = 1
            group by base_date, object_id
           having sum(ccsl) > 0
       )
       --sunhui20191227剩余现金取汇总值dense_rank
      select cc.object_id, sum(to_number(cc.param_value)) as cashRemaining  --不同资产不同币种，如何求和
        from (
          select t1.object_id, a.param_value,
                 dense_rank() over (partition by a.object_id order by t1.base_date - a.cdate) as rowno
            from t1
            left join stt_stat_element a
              on t1.object_id = a.object_id
             --and t1.element_id = a.element_id
             and a.dict_code = 'VALUE'
             and a.cdate between t1.base_date - PKG_STRESS_TEST_COMM.v_max_base_deviation and t1.base_date
        ) cc
       where rowno = 1
       group by cc.object_id;

      ----------------------------------------以上为统计中间指标----------------------------------------

      --查询压测指标
      CURSOR c_ind_id IS
      select c.ind_id
        from stt_stress_indicator c
       where c.busi_id = in_test_id
         and c.stt_busi_type = '04';

      v_ind_array           OBJECT_ID_ARRAY := OBJECT_ID_ARRAY();

      --各压测指标是否存在于压测中标记
      v_ind_1001            INTEGER := 0;  --产品规模
      v_ind_1002            INTEGER := 0;  --申赎净流出[基础]
      v_ind_1003            INTEGER := 0;  --情景现金缺口
      v_ind_1004            INTEGER := 0;  --二级风险缓释后缺口
      v_ind_1005            INTEGER := 0;  --流动性储备/现金缺口
      v_ind_1006            INTEGER := 0;  --杠杆率(轻中重)  Added on 2019-09-17
      v_ind_1007            INTEGER := 0;  --现金余额  Added on 2019-09-17
      v_ind_1008            INTEGER := 0;  --杠杆率
      v_ind_1009            INTEGER := 0;  --一级风险缓释后缺口  Added on 2019-09-17
      v_ind_10001           INTEGER := 0;  --债券收息
      v_ind_10002           INTEGER := 0;  --存款收息
      v_ind_10003           INTEGER := 0;  --利率型计划类型资产的收息
      v_ind_10004           INTEGER := 0;  --项目类收息
      v_ind_10005           INTEGER := 0;  --债券本方券到期
      v_ind_10006           INTEGER := 0;  --存款到期
      --sunhui
      --v_ind_1000701         INTEGER := 0;--计划类资产到期
      v_ind_10007           INTEGER := 0;  --拆出到期
      v_ind_10008           INTEGER := 0;  --债券借贷到期
      v_ind_10009           INTEGER := 0;  --质押式逆回购到期
      v_ind_10010           INTEGER := 0;  --买断式逆回购到期
      v_ind_10011           INTEGER := 0;  --产品赎回
      v_ind_10012           INTEGER := 0;  --拆入到期
      v_ind_10013           INTEGER := 0;  --债券借贷到期
      v_ind_10014           INTEGER := 0;  --正回购，于2019-11-20改为质押式正回购到期：F02
      v_ind_10028           INTEGER := 0;  --申赎净流出轻、中、重
      v_ind_1000401         INTEGER := 0;  --现金流入：项目类到期

      --以下5个指标增加于2019-11-20
      --v_ind_1001001         INTEGER := 0;  --一级流动性储备余额
      --v_ind_1001002         INTEGER := 0;  --二级流动性储备余额
      --v_ind_1001003         INTEGER := 0;  --资金流入合计-资产端
      --v_ind_1001004         INTEGER := 0;  --资金流出合计-资产端
      --v_ind_10014001        INTEGER := 0;  --买断式正回购到期：F04

      --指标值代码
      v_ind_seq_no          VARCHAR2(32);
      v_log_head            LOG_HEAD_OBJ;
  BEGIN
      PKG_LOG.P_SET_LOG_HEAD(v_log_head, 'PKG_FLOW_STRESS_TEST.P_FLOW_STRESS_TEST',
                             '{"in_test_id": "'|| in_test_id ||
                             '", "in_object_ids": "'|| in_object_ids || '"}');

      update stt_stress_test t
         set t.test_date = trunc(sysdate),
             t.start_time = systimestamp,
             t.exe_status = '02',
             t.update_user = v_update_uesr,
             t.update_time = systimestamp
       where t.test_id = in_test_id;

      COMMIT;

      FOR r_ind IN c_ind_id LOOP
        v_ind_array.extend;
        v_ind_array(v_ind_array.last) := r_ind.ind_id;
      END LOOP;

      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1001') into v_ind_1001 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1002') into v_ind_1002 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1003') into v_ind_1003 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1004') into v_ind_1004 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1005') into v_ind_1005 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1006') into v_ind_1006 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1007') into v_ind_1007 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1008') into v_ind_1008 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1009') into v_ind_1009 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10001') into v_ind_10001 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10002') into v_ind_10002 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10003') into v_ind_10003 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10004') into v_ind_10004 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10005') into v_ind_10005 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10006') into v_ind_10006 from dual;
      --sunhui
      --select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1000701') into v_ind_1000701 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10007') into v_ind_10007 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10008') into v_ind_10008 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10009') into v_ind_10009 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10010') into v_ind_10010 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10011') into v_ind_10011 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10012') into v_ind_10012 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10013') into v_ind_10013 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10014') into v_ind_10014 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '10028') into v_ind_10028 from dual;
      select PKG_STRESS_TEST_COMM.FUNC_CONTAINS_ITEM(v_ind_array, '1000401') into v_ind_1000401 from dual;

      --Warning: 2019-11-20新增5指标未考虑

      --获取压测情景代码
      select b.scene_id into v_scene_id
        from stt_stress_test a,
             stt_stress_scene b
       where a.test_id = in_test_id
         and a.test_id = b.busi_id
         and b.stt_busi_type = '04';

      --已计算出来的最终指标直接入库

      -- 压测指标中是否存在1001或1002或10011
      IF v_ind_1001 = 1 OR v_ind_1002 = 1 OR v_ind_10011 = 1 OR v_ind_10028 = 1 THEN
          --产品规模(1001) 申赎净流出[基础](1002) 产品赎回(10011###)
          FOR r_csc_row IN c_cpgm_ssjlc_cpsh LOOP
            --插入压测指标值表指标数据：产品规模(1001)

            insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                            create_user, create_dept, create_time, update_user, update_time)
            values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_csc_row.object_id, v_scene_id, '1001', '01',
                to_char(r_csc_row.cbgm), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

            --插入压测指标值表指标数据：申赎净流出[基础](1002)
            IF v_ind_1002 = 1 OR v_ind_10028 = 1 THEN
              IF v_ind_1002 = 1 THEN
                  insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                    create_user, create_dept, create_time, update_user, update_time)
                  values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_csc_row.object_id, v_scene_id, '1002', '01',
                          to_char(r_csc_row.exitBase), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

                    v_ind_seq_no := SEQ_STT_STRESS_INDDATA.nextval;
               END IF;

               IF v_ind_10028 = 1 THEN
                  --插入压测指标值表指标数据：申赎净流出(10028)
                  insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                    create_user, create_dept, create_time, update_user, update_time)
                  values (v_ind_seq_no, in_test_id, r_csc_row.object_id, v_scene_id, '10028', '02',
                          null, '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

                  --插入轻中重度指标值表指标数据：申赎净流出(10028)
                  insert into stt_inddata_lmh(seq_no, ind_id, test_id, light_value, medium_value, heavy_value,
                                  create_user, create_dept, create_time, update_user, update_time)
                  values (v_ind_seq_no, '10028', in_test_id, r_csc_row.lightCbsh, r_csc_row.midCbsh,
                          r_csc_row.heavyCbsh, v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
               END IF;

                --将申赎净流出值存入内存表
                v_indicator_list.EXTEND;
                v_indicator_list(v_indicator_list.LAST) :=
                indicator_value_obj(in_test_id, r_csc_row.object_id, '轻度申赎净流出', case sign(r_csc_row.lightCbsh) when -1 then 0 else r_csc_row.lightCbsh end, 'light');

                v_indicator_list.EXTEND;
                v_indicator_list(v_indicator_list.LAST) :=
                indicator_value_obj(in_test_id, r_csc_row.object_id, '中度申赎净流出', case sign(r_csc_row.midCbsh) when -1 then 0 else r_csc_row.midCbsh end, 'medium');

                v_indicator_list.EXTEND;
                v_indicator_list(v_indicator_list.LAST) :=
                indicator_value_obj(in_test_id, r_csc_row.object_id, '重度申赎净流出', case sign(r_csc_row.heavyCbsh) when -1 then 0 else r_csc_row.heavyCbsh end, 'heavy');
            END IF;

            --只有互斥因子选的产品赎回比例[PrdScl01002]时才存
            IF v_ind_10011 = 1 THEN
                v_ind_seq_no := SEQ_STT_STRESS_INDDATA.nextval;

                --插入压测指标值表指标数据：产品赎回(10011)
                insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                create_user, create_dept, create_time, update_user, update_time)
                values (v_ind_seq_no, in_test_id, r_csc_row.object_id, v_scene_id, '10011', '02',
                    null, '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

                --插入轻中重度指标值表指标数据：产品赎回(10011)
                insert into stt_inddata_lmh(seq_no, ind_id, test_id, light_value, medium_value, heavy_value,
                              create_user, create_dept, create_time, update_user, update_time)
                values (v_ind_seq_no, '10011', in_test_id, r_csc_row.lightCbsh, r_csc_row.midCbsh,
                        r_csc_row.heavyCbsh, v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

                --将产品赎回值存入内存表
                v_indicator_list.EXTEND;
                v_indicator_list(v_indicator_list.LAST) :=
                indicator_value_obj(in_test_id, r_csc_row.object_id, '轻度产品赎回', r_csc_row.lightCbsh, 'light');

                v_indicator_list.EXTEND;
                v_indicator_list(v_indicator_list.LAST) :=
                indicator_value_obj(in_test_id, r_csc_row.object_id, '中度产品赎回', r_csc_row.midCbsh, 'medium');

                v_indicator_list.EXTEND;
                v_indicator_list(v_indicator_list.LAST) :=
                indicator_value_obj(in_test_id, r_csc_row.object_id, '重度产品赎回', r_csc_row.heavyCbsh, 'heavy');
            END IF;
          END LOOP;
      END IF;

      IF v_ind_10012 = 1 OR v_ind_10013 = 1 OR v_ind_10014 = 1 THEN
          --拆入到期(10012) 债券借贷到期(10013) 融入资产到期(正回购)(10014)
          FOR r_czr_row IN c_crdq_zqjddq_rrzcdq LOOP
              IF v_ind_10012 = 1 THEN
                  insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                                  create_user, create_dept, create_time, update_user, update_time)
                  values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_czr_row.object_id, v_scene_id, '10012', '01',
                          to_char(r_czr_row.crdqze), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
              END IF;

              --IF v_ind_10013 = 1 THEN
                  --与FIN_CASH_PLAN表中取出的值二选一，约586行
  /*                 insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                                  create_user, create_dept, create_time, update_user, update_time)
                  values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_czr_row.object_id, v_scene_id, '10013', '01',
                          to_char(r_czr_row.zqjddqze), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp); */
              --END IF;

              IF v_ind_10014 = 1 THEN
                  insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                                  create_user, create_dept, create_time, update_user, update_time)
                  values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_czr_row.object_id, v_scene_id, '10014', '01',
                          to_char(r_czr_row.zyszhg), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

                  -- Added by JIAOXUJIN on 2019-11-20
                  insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                                  create_user, create_dept, create_time, update_user, update_time)
                  values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_czr_row.object_id, v_scene_id, '10014001', '01',
                          to_char(r_czr_row.mdszhg), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

              END IF;
          END LOOP;
      END IF;

      IF v_ind_1004 = 1 OR v_ind_1005 = 1 OR v_ind_1009 = 1 THEN
          --中间指标先入库到内存表
          ---一级流动性资产市值*折现系数（默认为1）
          ---二级流动性资产总市值*二级流动性资产变化率
          OPEN c_market_value;
          LOOP
            FETCH c_market_value INTO r_market_row;
            --第一条
            IF v_previous_object_id IS NULL THEN
              --一条都没有，那就不用算了，直接退出
              IF c_market_value%NOTFOUND THEN
                EXIT;
              END IF;
              v_previous_object_id := r_market_row.object_id;
            END IF;

            --最后收尾或OBJECT_ID发生变更
            IF c_market_value%NOTFOUND OR v_previous_object_id <> r_market_row.object_id THEN
              v_indicator_list.EXTEND;
              v_indicator_list(v_indicator_list.LAST) :=
              indicator_value_obj(in_test_id, v_previous_object_id, '一级流动性资产市值', v_level1MarkketValue, 'normal');

              v_indicator_list.EXTEND;
              v_indicator_list(v_indicator_list.LAST) :=
              indicator_value_obj(in_test_id, v_previous_object_id, '二级流动性资产市值', v_level2MarkketValue, 'normal');

              v_indicator_list.EXTEND;
              v_indicator_list(v_indicator_list.LAST) :=
              indicator_value_obj(in_test_id, v_previous_object_id, '二级流动性资产变化率', v_light_value, 'light');

              v_indicator_list.EXTEND;
              v_indicator_list(v_indicator_list.LAST) :=
              indicator_value_obj(in_test_id, v_previous_object_id, '二级流动性资产变化率', v_medium_value, 'medium');

              v_indicator_list.EXTEND;
              v_indicator_list(v_indicator_list.LAST) :=
              indicator_value_obj(in_test_id, v_previous_object_id, '二级流动性资产变化率', v_heavy_value, 'heavy');

              -- Added by JIAOXUJIN on 2019-11-20
              insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                              create_user, create_dept, create_time, update_user, update_time)
              values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, v_previous_object_id, v_scene_id, '1001001', '01',
                      to_char(v_level1MarkketValue), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
              insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                              create_user, create_dept, create_time, update_user, update_time)
              values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, v_previous_object_id, v_scene_id, '1001002', '01',
                      to_char(v_level2MarkketValue), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

              v_level1MarkketValue := r_market_row.level1MarkketValue;
              v_level2MarkketValue := r_market_row.level2MarkketValue;
              v_previous_object_id := r_market_row.object_id;

              --收尾退出
              IF c_market_value%NOTFOUND THEN
                EXIT;
              END IF;
            ELSE
              v_level1MarkketValue := r_market_row.level1MarkketValue + NVL(v_level1MarkketValue,0);
              v_level2MarkketValue := r_market_row.level2MarkketValue + NVL(v_level2MarkketValue,0);
            END IF;

            v_light_value := r_market_row.light_value;
            v_medium_value := r_market_row.medium_value;
            v_heavy_value := r_market_row.heavy_value;

            IF r_market_row.assetslevel = '1' THEN
              --20001 一级流动性资产明细指标
              insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                              create_user, create_dept, create_time, update_user, update_time, flowasset_type)
              values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_market_row.object_id, v_scene_id, '20001', '01',
                      to_char(r_market_row.level1MarkketValue), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp, r_market_row.detailType);
            ELSIF r_market_row.assetslevel = '2' THEN
              --20002 一级流动性资产明细指标
              insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                              create_user, create_dept, create_time, update_user, update_time, flowasset_type)
              values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_market_row.object_id, v_scene_id, '20002', '01',
                      to_char(r_market_row.level2MarkketValue), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp, r_market_row.detailType);
            END IF;
          END LOOP;
          CLOSE c_market_value;
      END IF;

      --杠杆率(轻中重)
      IF v_ind_1006 = 1 THEN
        FOR r_leverage_row IN c_leverage_ratio LOOP
          if r_leverage_row.jzc is null or r_leverage_row.jzc = 0 or r_leverage_row.zzc is null or r_leverage_row.zzc = 0 then
              v_leverage_light := null;
              v_leverage_medium := null;
              v_leverage_heavy := null;
          else
            --轻
            BEGIN
              with t3 as (
                  select t.object_id,
                         sum(nvl(t.ind_value, 0)) as amtLevel2
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产市值'
                  group by t.object_id
              ),
              t4 as (
                  select t.object_id,
                         avg(nvl(t.ind_value, 0)) as changeRateLight
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产变化率'
                    and t.value_type = 'light'
                  group by t.object_id
              )
              select t3.amtLevel2 * nvl(t4.changeRateLight,0) as diluteGapLight
                     into v_d_light
                from t3
                left join t4 on t3.object_id = t4.object_id
               where t3.object_id = r_leverage_row.object_id;

              EXCEPTION WHEN NO_DATA_FOUND THEN
                v_d_light := null;
            END;

            --中
            BEGIN
              with t3 as (
                  select t.object_id,
                         sum(nvl(t.ind_value, 0)) as amtLevel2
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产市值'
                  group by t.object_id
              ),
              t5 as (
                  select t.object_id,
                         avg(nvl(t.ind_value, 0)) as changeRateMed
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产变化率'
                    and t.value_type = 'medium'
                  group by t.object_id
              )
              select t3.amtLevel2 * nvl(t5.changeRateMed,0) as diluteGapMedium
                     into v_d_medium
                from t3
                left join t5 on t3.object_id = t5.object_id
               where t3.object_id = r_leverage_row.object_id;

              EXCEPTION WHEN NO_DATA_FOUND THEN
                v_d_medium := null;
            END;

            --重
            BEGIN
              with t3 as (
                  select t.object_id,
                         sum(nvl(t.ind_value, 0)) as amtLevel2
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产市值'
                  group by t.object_id
              ),
              t6 as (
                  select t.object_id,
                         avg(nvl(t.ind_value, 0)) as changeRateHeavy
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产变化率'
                    and t.value_type = 'heavy'
                  group by t.object_id
              )
              select t3.amtLevel2 * nvl(t6.changeRateHeavy,0) as diluteGapHeavy
                     into v_d_heavy
                from t3
                left join t6 on t3.object_id = t6.object_id
               where t3.object_id = r_leverage_row.object_id;

              EXCEPTION WHEN NO_DATA_FOUND THEN
                v_d_heavy := null;
            END;

            --当压测因子为产品赎回时，产品赎回金额 = 产品规模 * 产品赎回比例
            IF v_ind_1002 = 0 AND v_ind_10011 = 1 THEN
              BEGIN
                select t.ind_value into v_r_light
                  from table(cast(v_indicator_list as indicator_value_table)) t
                 where t.test_id = in_test_id
                   and t.object_id = r_leverage_row.object_id
                   and t.ind_name = '轻度产品赎回'
                   and t.value_type = 'light';
                EXCEPTION WHEN NO_DATA_FOUND THEN
                  v_r_light := null;
              END;

              BEGIN
                select t.ind_value into v_r_medium
                  from table(cast(v_indicator_list as indicator_value_table)) t
                 where t.test_id = in_test_id
                   and t.object_id = r_leverage_row.object_id
                   and t.ind_name = '中度产品赎回'
                   and t.value_type = 'medium';
                EXCEPTION WHEN NO_DATA_FOUND THEN
                  v_r_medium := null;
              END;

              BEGIN
                select t.ind_value into v_r_heavy
                  from table(cast(v_indicator_list as indicator_value_table)) t
                 where t.test_id = in_test_id
                   and t.object_id = r_leverage_row.object_id
                   and t.ind_name = '重度产品赎回'
                   and t.value_type = 'heavy';
                EXCEPTION WHEN NO_DATA_FOUND THEN
                  v_r_heavy := null;
              END;

            --当压测因子为申赎净流出时：产品赎回金额 = 申赎净流出基础值 * (1 + 申赎净流出变化值)
            ELSIF v_ind_1002 = 1 OR v_ind_10028 = 1 THEN
              BEGIN
                select t.ind_value into v_r_light
                  from table(cast(v_indicator_list as indicator_value_table)) t
                 where t.test_id = in_test_id
                   and t.object_id = r_leverage_row.object_id
                   and t.ind_name = '轻度申赎净流出'
                   and t.value_type = 'light';
                EXCEPTION WHEN NO_DATA_FOUND THEN
                  v_r_light := null;
              END;

              BEGIN
                select t.ind_value into v_r_medium
                  from table(cast(v_indicator_list as indicator_value_table)) t
                 where t.test_id = in_test_id
                   and t.object_id = r_leverage_row.object_id
                   and t.ind_name = '中度申赎净流出'
                   and t.value_type = 'medium';
                EXCEPTION WHEN NO_DATA_FOUND THEN
                  v_r_medium := null;
              END;

              BEGIN
                select t.ind_value into v_r_heavy
                  from table(cast(v_indicator_list as indicator_value_table)) t
                 where t.test_id = in_test_id
                   and t.object_id = r_leverage_row.object_id
                   and t.ind_name = '重度申赎净流出'
                   and t.value_type = 'heavy';
                EXCEPTION WHEN NO_DATA_FOUND THEN
                  v_r_heavy := null;
              END;
            END IF;

            --杠杆率=(资产合计 - R + D)/(基金净值 - R)
            v_leverage_light := (r_leverage_row.zzc - nvl(v_r_light, 0) + nvl(v_d_light, 0)) / (r_leverage_row.jzc - nvl(v_r_light, 0));
            v_leverage_medium := (r_leverage_row.zzc - nvl(v_r_medium, 0) + nvl(v_d_medium, 0)) / (r_leverage_row.jzc - nvl(v_r_medium, 0));
            v_leverage_heavy := (r_leverage_row.zzc - nvl(v_r_heavy, 0) + nvl(v_d_heavy, 0)) / (r_leverage_row.jzc - nvl(v_r_heavy, 0));
          end if;

          v_ind_seq_no := SEQ_STT_STRESS_INDDATA.nextval;

          --插入压测指标值表指标数据：杠杆率-轻中重(1006)
          insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                        create_user, create_dept, create_time, update_user, update_time)
          values (v_ind_seq_no, in_test_id, r_leverage_row.object_id, v_scene_id, '1006', '02',
                  null, '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

          --插入轻中重度指标值表指标数据：杠杆率-轻中重(1006)
          insert into stt_inddata_lmh(seq_no, ind_id, test_id, light_value, medium_value, heavy_value,
                      create_user, create_dept, create_time, update_user, update_time)
          values (v_ind_seq_no, '1006', in_test_id, v_leverage_light, v_leverage_medium,
                  v_leverage_heavy, v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
        END LOOP;
      END IF;

      IF v_ind_1008 = 1 THEN
          --杠杆率(1008) = 总资产/净资产
          FOR r_leverage_row IN c_leverage_ratio LOOP
              if r_leverage_row.jzc is null or r_leverage_row.jzc = 0 or r_leverage_row.zzc is null or r_leverage_row.zzc = 0 then
                  v_leverage_ratio := null;
              else
                  v_leverage_ratio := r_leverage_row.zzc / r_leverage_row.jzc;
              end if;

              insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                              create_user, create_dept, create_time, update_user, update_time)
              values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_leverage_row.object_id, v_scene_id, '1008', '01',
                      to_char(v_leverage_ratio), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
          END LOOP;
      END IF;

      IF v_ind_10001 = 1 OR v_ind_10002 = 1 OR v_ind_10003 = 1 OR v_ind_10004 = 1 OR v_ind_10005 = 1
         --sunhui
         OR v_ind_10006 = 1 OR v_ind_10007 = 1 OR v_ind_10008 = 1 OR v_ind_10009 = 1 OR v_ind_10010 = 1 OR v_ind_1000401 = 1 THEN
          --流入十一指标直接入库:10001——10010  1000401  10013？
          FOR r_inflow_row IN c_inflow_indicators LOOP
              insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                              create_user, create_dept, create_time, update_user, update_time)
              values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_inflow_row.prod_id, v_scene_id, r_inflow_row.ind_id, '01',
                      to_char(r_inflow_row.cash), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
          END LOOP;
      END IF;

      IF v_ind_1003 = 1 OR v_ind_1004 = 1 OR v_ind_1005 = 1 OR v_ind_1009 = 1 THEN
          --情景现金缺口 = 现金缺口(1003###) = 现金流出 – 现金流入 - 现金余额
          FOR r_cash_row IN c_cash_shortfall LOOP
              if r_cash_row.enterCash is null and r_cash_row.lightExitCash is null and r_cash_row.cashRemaining is null then
                  v_cash_shortfall_light := null;
              else
                  v_cash_shortfall_light := nvl(r_cash_row.lightExitCash,0) - nvl(r_cash_row.enterCash,0) - nvl(r_cash_row.cashRemaining,0);
              end if;
              if r_cash_row.enterCash is null and r_cash_row.mediumExitCash is null and r_cash_row.cashRemaining is null then
                  v_cash_shortfall_medium := null;
              else
                  v_cash_shortfall_medium := nvl(r_cash_row.mediumExitCash,0) - nvl(r_cash_row.enterCash,0) - nvl(r_cash_row.cashRemaining,0);
              end if;
              if r_cash_row.enterCash is null and r_cash_row.heavyExitCash is null and r_cash_row.cashRemaining is null then
                  v_cash_shortfall_heavy := null;
              else
                  v_cash_shortfall_heavy := nvl(r_cash_row.heavyExitCash,0) - nvl(r_cash_row.enterCash,0) - nvl(r_cash_row.cashRemaining,0);
              end if;

              v_ind_seq_no := SEQ_STT_STRESS_INDDATA.nextval;

              insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                              create_user, create_dept, create_time, update_user, update_time)
              values (v_ind_seq_no, in_test_id, r_cash_row.object_id, v_scene_id, '1003', '02',
                      null, '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

              insert into stt_inddata_lmh(seq_no, ind_id, test_id, light_value, medium_value, heavy_value,
                                          create_user, create_dept, create_time, update_user, update_time)
              values (v_ind_seq_no, '1003', in_test_id, v_cash_shortfall_light, v_cash_shortfall_medium,
                      v_cash_shortfall_heavy, v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

              --现金缺口(1003###)在二级风险缓释后缺口(1004###)和流动性储备/现金缺口(1005###)指标计算中需要用到，因此先将此指标暂存到内存表
              v_indicator_list.EXTEND;
              v_indicator_list(v_indicator_list.LAST) :=
              indicator_value_obj(in_test_id, r_cash_row.object_id, '现金缺口', v_cash_shortfall_light, 'light');
              v_indicator_list.EXTEND;
              v_indicator_list(v_indicator_list.LAST) :=
              indicator_value_obj(in_test_id, r_cash_row.object_id, '现金缺口', v_cash_shortfall_medium, 'medium');
              v_indicator_list.EXTEND;
              v_indicator_list(v_indicator_list.LAST) :=
              indicator_value_obj(in_test_id, r_cash_row.object_id, '现金缺口', v_cash_shortfall_heavy, 'heavy');

              -- Added by JIAOXUJIN on 2019-11-20
              insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                              create_user, create_dept, create_time, update_user, update_time)
              values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_cash_row.object_id, v_scene_id, '1001003', '01',
                      to_char(r_cash_row.enterCash), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
              insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                              create_user, create_dept, create_time, update_user, update_time)
              values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_cash_row.object_id, v_scene_id, '1001004', '01',
                      to_char(r_cash_row.otherExitCash), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
          END LOOP;
      END IF;

      IF v_ind_1005 = 1 OR v_ind_1007 = 1 THEN
          ---现金余额
          FOR r_cashr_row IN c_cash_remaining LOOP
              IF v_ind_1007 = 1 THEN
                  insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                                  create_user, create_dept, create_time, update_user, update_time)
                  values (SEQ_STT_STRESS_INDDATA.nextval, in_test_id, r_cashr_row.object_id, v_scene_id, '1007', '01',
                          to_char(r_cashr_row.cashRemaining), '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
              END IF;

              v_indicator_list.EXTEND;
              v_indicator_list(v_indicator_list.LAST) :=
              indicator_value_obj(in_test_id, r_cashr_row.object_id, '现金余额', r_cashr_row.cashRemaining, 'normal');
          END LOOP;
      END IF;

      IF v_ind_1004 = 1 OR v_ind_1009 = 1 THEN
          --根据内存表中的中间指标统计出部分最终指标并入库

          --二级风险缓释后缺口(四###) = 现金缺口 – 一级流动性资产市值*折现系数（默认为1）– 二级流动性资产市值*二级流动性资产变化率（风险因子）
          DECLARE
              CURSOR c_dilute_gap IS
              --现金缺口 轻，值可能为null
              with t11 as (
                  select t.object_id,
                         nvl(t.ind_value, 0) as cashShortfallLight
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '现金缺口'
                    and t.value_type = 'light'
              ),
              --现金缺口 中，值可能为null
              t12 as (
                  select t.object_id,
                         nvl(t.ind_value, 0) as cashShortfallMed
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '现金缺口'
                    and t.value_type = 'medium'
              ),
              --现金缺口 重，值可能为null
              t13 as (
                  select t.object_id,
                         nvl(t.ind_value, 0) as cashShortfallHeavy
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '现金缺口'
                    and t.value_type = 'heavy'
              ),
              t2 as (
                  select t.object_id,
                         sum(nvl(t.ind_value, 0)) as amtLevel1
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '一级流动性资产市值'
                  group by t.object_id
              ),
              t3 as (
                  select t.object_id,
                         sum(nvl(t.ind_value, 0)) as amtLevel2
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产市值'
                  group by t.object_id
              ),
              t4 as (
                  select t.object_id,
                         avg(nvl(t.ind_value, 0)) as changeRateLight
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产变化率'
                    and t.value_type = 'light'
                  group by t.object_id
              ),
              t5 as (
                  select t.object_id,
                         avg(nvl(t.ind_value, 0)) as changeRateMed
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产变化率'
                    and t.value_type = 'medium'
                  group by t.object_id
              ),
              t6 as (
                  select t.object_id,
                         avg(nvl(t.ind_value, 0)) as changeRateHeavy
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产变化率'
                    and t.value_type = 'heavy'
                  group by t.object_id
              )
              select t11.object_id,
                     t11.cashShortfallLight - nvl(t2.amtLevel1,0) * v_dicount_level1 - nvl(t3.amtLevel2,0) * nvl(t4.changeRateLight,0) as diluteGapLight,
                     t12.cashShortfallMed - nvl(t2.amtLevel1,0) * v_dicount_level1 - nvl(t3.amtLevel2,0) * nvl(t5.changeRateMed,0) as diluteGapMedium,
                     t13.cashShortfallHeavy - nvl(t2.amtLevel1,0) * v_dicount_level1 - nvl(t3.amtLevel2,0) * nvl(t6.changeRateHeavy,0) as diluteGapHeavy,
                     t11.cashShortfallLight - nvl(t2.amtLevel1,0) * v_dicount_level1 as diluteGapLight1,
                     t12.cashShortfallMed - nvl(t2.amtLevel1,0) * v_dicount_level1 as diluteGapMedium1,
                     t13.cashShortfallHeavy - nvl(t2.amtLevel1,0) * v_dicount_level1 as diluteGapHeavy1
                from t11
                inner join t12 on t11.object_id = t12.object_id
                inner join t13 on t11.object_id = t13.object_id
                left join t2 on t11.object_id = t2.object_id
                left join t3 on t11.object_id = t3.object_id
                left join t4 on t11.object_id = t4.object_id
                left join t5 on t11.object_id = t5.object_id
                left join t6 on t11.object_id = t6.object_id;
          BEGIN
              FOR r_row IN c_dilute_gap LOOP
                  IF v_ind_1004 = 1 THEN
                      v_ind_seq_no := SEQ_STT_STRESS_INDDATA.nextval;

                      insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                                      create_user, create_dept, create_time, update_user, update_time)
                      values (v_ind_seq_no, in_test_id, r_row.object_id, v_scene_id, '1004', '02',
                              null, '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

                      insert into stt_inddata_lmh(seq_no, ind_id, test_id, light_value, medium_value, heavy_value,
                                                  create_user, create_dept, create_time, update_user, update_time)
                      values (v_ind_seq_no, '1004', in_test_id, r_row.diluteGapLight, r_row.diluteGapMedium,
                              r_row.diluteGapHeavy, v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
                  END IF;

                  --一级风险缓释后缺口 1009
                  IF v_ind_1009 = 1 THEN
                      --一级风险缓释后缺口1009 = 现金缺口 – 一级流动性资产市值*折现系数（默认为1）
                      v_ind_seq_no := SEQ_STT_STRESS_INDDATA.nextval;

                      insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                                      create_user, create_dept, create_time, update_user, update_time)
                      values (v_ind_seq_no, in_test_id, r_row.object_id, v_scene_id, '1009', '02',
                              null, '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

                      insert into stt_inddata_lmh(seq_no, ind_id, test_id, light_value, medium_value, heavy_value,
                                                  create_user, create_dept, create_time, update_user, update_time)
                      values (v_ind_seq_no, '1009', in_test_id, r_row.diluteGapLight1, r_row.diluteGapMedium1,
                              r_row.diluteGapHeavy1, v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
                  END IF;
              END LOOP;
          END;
      END IF;

      IF v_ind_1005 = 1 THEN
          --流动性资产/现金缺口 = 流动性储备/现金缺口(五###) = （一级流动性资产市值*折现系数（默认为1）+二级流动性资产市值*二级流动性资产变化率）/ 现金缺口
          DECLARE
              CURSOR c_store_gap IS
              --现金缺口 轻，值可能为null
              with t11 as (
                  select t.object_id,
                         nvl(t.ind_value, 0) as cashShortfallLight
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '现金缺口'
                    and t.value_type = 'light'
              ),
              --现金缺口 中，值可能为null
              t12 as (
                  select t.object_id,
                         nvl(t.ind_value, 0) as cashShortfallMed
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '现金缺口'
                    and t.value_type = 'medium'
              ),
              --现金缺口 重，值可能为null
              t13 as (
                  select t.object_id,
                         nvl(t.ind_value, 0) as cashShortfallHeavy
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '现金缺口'
                    and t.value_type = 'heavy'
              ),
              t2 as (
                  select t.object_id,
                         sum(nvl(t.ind_value, 0)) as amtLevel1
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '一级流动性资产市值'
                  group by t.object_id
              ),
              t3 as (
                  select t.object_id,
                         sum(nvl(t.ind_value, 0)) as amtLevel2
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产市值'
                  group by t.object_id
              ),
              t4 as (
                  select t.object_id,
                         avg(nvl(t.ind_value, 0)) as changeRateLight
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产变化率'
                    and t.value_type = 'light'
                  group by t.object_id
              ),
              t5 as (
                  select t.object_id,
                         avg(nvl(t.ind_value, 0)) as changeRateMed
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产变化率'
                    and t.value_type = 'medium'
                  group by t.object_id
              ),
              t6 as (
                  select t.object_id,
                         avg(nvl(t.ind_value, 0)) as changeRateHeavy
                  from table(cast(v_indicator_list as indicator_value_table)) t
                  where t.test_id = in_test_id
                    and t.ind_name = '二级流动性资产变化率'
                    and t.value_type = 'heavy'
                  group by t.object_id
              )
              select t11.object_id,
                     decode(t11.cashShortfallLight, 0, null, (nvl(t2.amtLevel1,0) * v_dicount_level1 +
                     nvl(t3.amtLevel2,0) * nvl(t4.changeRateLight,0)) / t11.cashShortfallLight) as storeGapLight,
                     decode(t12.cashShortfallMed, 0, null, (nvl(t2.amtLevel1,0) * v_dicount_level1 +
                     nvl(t3.amtLevel2,0) * nvl(t5.changeRateMed,0)) / t12.cashShortfallMed) as storeGapMedium,
                     decode(t13.cashShortfallHeavy, 0, null, (nvl(t2.amtLevel1,0) * v_dicount_level1 +
                     nvl(t3.amtLevel2,0) * nvl(t6.changeRateHeavy,0)) / t13.cashShortfallHeavy) as storeGapHeavy
                from t11
                inner join t12 on t11.object_id = t12.object_id
                inner join t13 on t11.object_id = t13.object_id
                left join t2 on t11.object_id = t2.object_id
                left join t3 on t11.object_id = t3.object_id
                left join t4 on t11.object_id = t4.object_id
                left join t5 on t11.object_id = t5.object_id
                left join t6 on t11.object_id = t6.object_id;
          BEGIN
              FOR r_row IN c_store_gap LOOP
                  v_ind_seq_no := SEQ_STT_STRESS_INDDATA.nextval;

                  insert into stt_stress_inddata (seq_no, test_id, object_id, scene_id, ind_id, value_struct, value, value_type,
                                                  create_user, create_dept, create_time, update_user, update_time)
                  values (v_ind_seq_no, in_test_id, r_row.object_id, v_scene_id, '1005', '02',
                          null, '01', v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);

                  insert into stt_inddata_lmh(seq_no, ind_id, test_id, light_value, medium_value, heavy_value,
                                              create_user, create_dept, create_time, update_user, update_time)
                  values (v_ind_seq_no, '1005', in_test_id, r_row.storeGapLight, r_row.storeGapMedium,
                          r_row.storeGapHeavy, v_create_uesr, v_create_dept, systimestamp, v_update_uesr, systimestamp);
              END LOOP;
          END;
      END IF;

      --更新压测状态
      update stt_stress_test t
         set t.finished_time = systimestamp,
             t.exe_status = '01',
             t.update_user = v_update_uesr,
             t.update_time = systimestamp
       where t.test_id = in_test_id;

      COMMIT;
  EXCEPTION
     WHEN OTHERS THEN
      IF c_market_value%ISOPEN THEN
          CLOSE c_market_value;
      END IF;

      ROLLBACK;

      update stt_stress_test t
         set t.finished_time = systimestamp,
             t.exe_status = '03',
             t.update_user = v_update_uesr,
             t.update_time = systimestamp
       where t.test_id = in_test_id;

      COMMIT;

      DBMS_OUTPUT.put_line('this error message is from procedure P_FLOW_STRESS_TEST.');
      DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
      DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
      PKG_LOG.ERROR(v_log_head, '流动性风险压测存储过程出错', DBMS_UTILITY.format_error_backtrace, SQLCODE, SQLERRM);
  END P_FLOW_STRESS_TEST;

END PKG_FLOW_STRESS_TEST;
/