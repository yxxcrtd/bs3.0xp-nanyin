CREATE OR REPLACE PACKAGE BODY PKG_MR_STRESS_TEST IS
 /**********************************************************************************
  * AUTHOR         -  Jiaoxujin
  * CREATION DATE  -  2019-09-26
  * SERVICE NAME   -  兆尹科技-资管事业部
  * PROJECT NAME   -  邮储压力测试
  *
  * PACKAGE NAME: PKG_MR_STRESS_TEST
  *
  * DESCRIPTION: 市场风险压力测试计算包，包括因子最大变化值、情景价格的计算
  *
  * CHANGE LOG
  ***********************************************************************************
  * CHANGE NUMBER:
  * DATE:
  * DEVELOPER:
  * DESCRIPTION:
  **********************************************************************************/

  --包内全局变量
  --v_start_time DATE        := TRUNC(SYSDATE - 7);
  --v_end_time   DATE        := TO_DATE(TO_CHAR(SYSDATE - 1, 'YYYY-MM-DD') || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS');

  --Spring datasource validationQuery 验证package的时效性
  FUNCTION FUNC_PKG_VALIDATION RETURN DATE AS
  BEGIN
    RETURN SYSDATE;
  END;

  --根据债券资产id,基准日期，获取债券资产的修正久期
  FUNCTION FUNC_GETBONDDUR(f_id IN varchar2, f_date IN varchar2)

    /******************************************************************************
    * AUTHOR         -  SunHui
    *获取债券【债券估值行情】中的【市价久期】
    * Parameters :
    *  f_id               IN  资产代码（债券）
    *  f_date             IN  基准日期
    *****************************************************************************/
   RETURN NUMBER AS

    v_int_dur number(30, 14);

  BEGIN

    select mi.m_duration
      into v_int_dur --修正久期
      from MST_BOND_VALUATION_INFO mi
     where mi.sec_id = substr(f_id, instr(f_id, '_') + 1)--资产代码是加了资产一级分类的，需要截取再匹配外部数据
       and to_char(mi.value_date,'yyyymmdd') = f_date;

    RETURN v_int_dur;

    EXCEPTION
    WHEN OTHERS THEN
       --DBMS_OUTPUT.put_line('this error message is from function FUNC_GETBONDDUR.');
       --DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       --DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       --如果获取不到，返回null,避免NO_DATA_FUND异常
       RETURN null;
  END;

  --根据债券的计息基础返回计息天数
  FUNCTION FUNC_GETDAYSFORBASIS(f_id IN varchar2)

    /******************************************************************************
    * AUTHOR         -  SunHui
    *根据债券计息基础获取计息天数
    * Parameters :
    *  f_id               IN  资产代码（债券）
    *****************************************************************************/
   RETURN NUMBER AS

   v_int_days     number(30, 14);

    --所需参数申明
    p_payDateBegin date; --付息开始日，A/A 必须输入
    p_vdate        date; --资产起息日，A/A 必须输入
    p_mdate        date; --资产到期日，A/A 必须输入
    p_firstPayDate date; --首次付息日，A/A且 推算方式 = "首次付息日"时，必须输入
    p_basis        varchar2(50); --计息基础，必须输入
    p_calMethod    varchar2(20); --推算方式，必须输入，推算方式：01起息日向后     02到期日向前   03首次支付日

    --循环变量
    p_index        number;
    --中间参数申明
    v_vdate1       date;--下一周期开始
    v_vdate2       date;--下一周期结束

  BEGIN

      p_index:=0;--开始赋下脚初始值为0

      select t.vdate as pay_Date_Begin, --付息开始日
           t.vdate,                     --起息日
           t.mdate,                     --到期日
           fcpr.first_pay_date,         --首次支付日==首次付息日
           fccr.basis,                  --计息基础
           fcpr.calc_rule               --推算方式
      into p_payDateBegin,
           p_vdate,
           p_mdate,
           p_firstPayDate,
           p_basis,
           p_calMethod
      from FIN_PRODUCT t
      left join FIN_CASH_INFO fci --现金流信息---取现金流id
        on t.finprod_id = fci.finprod_id
      left join FIN_CASH_PAY_RULE fcpr --现金流支付规则表---取首次支付日、推算方式
        on fci.cash_id = fcpr.cash_id
      left join FIN_CASH_CALC_RULE fccr
        on fccr.cash_id = fci.cash_id
     where t.finprod_type2 = 'F01'
       and fccr.basis is not null
       and t.finprod_id = f_id;

     /*根据各变量值确定或计算计息基础应计天数*/
     --如果【计息基础】为空或null，无法计算，避免取不到值，返回365，防止异常
     if p_basis is null
     then
          v_int_days :=365;
     elsif
          --如果计息基础为365类型，则返回365
           p_basis like '%365%'
            then
                v_int_days :=365;
          --如果计息基础为360类型，则返回360
          elsif p_basis like'%360'
            then
              v_int_days :=360;
          --如果计息基础为A/A类型
          elsif p_basis like 'A/A%'
               then
                   --如果付息开始日！=null 且 起息日、到期日！=null 且推算方式不为空成立，则下一步
                   if (p_payDateBegin is not null and p_vdate is not null and p_mdate is not null and p_basis is not null)
                   then
                           --如果【首次付息日】！=null成立,则下一步
                             if (p_firstPayDate is not null)
                             then
                          --------如果【推算方式】=【首次支付日】
                                  if (p_calMethod='03')
                                  then
                                     --循环取值------首次支付日
                                    loop
                                       --将首次付息日加p_index年、加p_index+1年
                                       v_vdate1 := add_months(p_firstPayDate,p_index*12);
                                       v_vdate2 := add_months(p_firstPayDate,(p_index+1)*12);
                                       --如果【付息开始日】在区间{【起息日】+i,【起息日】+i+1}年内，则返回true
                                       IF p_payDateBegin >= v_vdate1 AND p_payDateBegin < v_vdate2 THEN
                                         --返回期间天数
                                         v_int_days :=v_vdate2-v_vdate1;
                                         exit;
                                       END IF;
                                       --否则 p_index+1进行下次循环，直到找到为止
                                       p_index := p_index +1;
                                     end loop;

                          -------如果【推算方式】=【起息日向后】
                                  elsif (p_calMethod='01')
                                  then
                                    --循环取值----起息日向后
                                    loop
                                     --将起息日期加p_index年、加p_index+1年
                                     v_vdate1 :=add_months(p_vdate,p_index*12);
                                     v_vdate2 :=add_months(p_vdate,(p_index+1)*12);
                                     --如果【付息开始日】在区间{【起息日】+i,【起息日】+i+1}年内，则返回true
                                     IF p_payDateBegin >= v_vdate1 AND p_payDateBegin < v_vdate2 THEN
                                       --返回天数
                                       v_int_days :=v_vdate2-v_vdate1;
                                       exit;
                                     END IF;
                                     p_index := p_index +1;
                                   end loop;
                          -------如果【推算方式】=【到期日向前】
                                  elsif  (p_calMethod='02')
                                  then
                                    --循环取值-----到日起向前
                                    loop
                                     --将到期日减p_index年、减p_index+1年
                                     v_vdate1 := add_months(p_mdate,-1*p_index*12);
                                     v_vdate2 := add_months(p_mdate,-1*(p_index+1)*12);
                                     --如果【付息开始日】在区间{【起息日】+i,【起息日】+i+1}年内，则返回true
                                     IF p_payDateBegin < v_vdate1 AND p_payDateBegin >= v_vdate2 THEN
                                       --返回天数
                                       v_int_days :=v_vdate1-v_vdate2;
                                       exit;
                                     END IF;
                                     p_index := p_index +1;
                                   end loop;
                           -------推算方式都不满足，避免取不到值，返回365，或者直接返回null
                                  else
                                       v_int_days :=365;
                                  end if;
                        --------如果计息基础是A/A类型，【首次付息日】为null,无法计算，避免取不到值，返回365，或者直接返回null
                        else
                             v_int_days :=365;
                        end if;
                   ---------如果计息基础是A/A类型，各必填参数为null,无法计算，避免取不到值，返回365，或者直接返回null
                   else
                        v_int_days :=365;
                   end if;
             end if;

    RETURN v_int_days;

    EXCEPTION
    WHEN OTHERS THEN
       --DBMS_OUTPUT.put_line('this error message is from function FUNC_GETDAYSFORBASIS.');
       --DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       --DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       --如果不满足计算条件，避免NO_DATA_FUND异常，直接返回null
       RETURN null;

  END;

  --根据债券代码，基准日期，标记，获取【剩余存续期限】OR【短期剩余期限】
  FUNCTION FUNC_GETBONDEDAYS(f_id   IN varchar2,
                             f_date IN varchar2,
                             f_flag IN NUMBER)

    /******************************************************************************
    * AUTHOR         -  SunHui
    *获取债券剩余期限【1：剩余存续期限 2：短期剩余期限】
    * Parameters :
    *  f_id               IN  资产代码（债券）
    *  f_date             IN  基准日期
    *  f_id               IN  标记【1：剩余存续期限 2：短期剩余期限】
    *****************************************************************************/
   RETURN NUMBER AS

    v_int_year number(30, 14);

  BEGIN

    select vyear into v_int_year from (
    select decode(f_flag, 1, psv.end_days_2, psv.end_days_1) / --【剩余存续期限】、【短期剩余期限】
           FUNC_GETDAYSFORBASIS(f_id) vyear --调用计息天数
      --into v_int_year
      from PTL_SEC_VALUTION psv  --头寸【组合资产估值】表
     where to_char(psv.cdate,'yyyymmdd') = f_date
       and psv.finprod_id = f_id) where rownum=1;

    RETURN v_int_year;

    EXCEPTION
    WHEN OTHERS THEN
       --DBMS_OUTPUT.put_line('this error message is from function FUNC_GETBONDEDAYS.');
       --DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       --DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       --如果找不到，返回null
       RETURN null;

  END;

  --取某日债券对应曲线的收益率，采用曲线插值
  FUNCTION FUNC_GETBONDFACHANGE(fac_id        in varchar2,
                                bond_days     in number,
                                sc_date       in date)
  /******************************************************************************
    * AUTHOR         -  SunHui
    *获取债券类曲线因子的因子变化
    *入参：因子代码、债券剩余期限（年），情景开始时间、情景结束时间
    *此处情景开始结束时间不完全是表意、也可以是资产匹配因子的使用时间
    *输出：因子变化
    *  fac_id               IN  因子代码
    *  bond_days            IN  剩余期限，单位为：年
    *  sc_date              IN  时间
    *****************************************************************************/

   RETURN NUMBER AS

    v_fac_val number(30, 14); --输出插值后的收益率

  BEGIN

    --插值
    select st1.cvalue
      into v_fac_val
      from (select bond_days as basep,
                   lifts.keyvalue as liftp,
                   lifts.value as liftv,
                   rights.keyvalue as rightp,
                   rights.value as rightv,
                   (lifts.value + (rights.value - lifts.value) *
                   ((bond_days - lifts.keyvalue) /
                   (rights.keyvalue - lifts.keyvalue))) as cvalue ---开始时间插值数
              from (select *
                      from (select r.*
                              from (
                                    --查出因子对应的外部数据列表
                                    select CURVE_CODE, --行情代码
                                            serdate, --日期
                                            KEYVALUE, --关键点
                                            value --收益率
                                      from MST_YIELD_CURVE_INFO_vw --收益率曲线视图
                                     where CURVE_CODE in
                                           (select
                                            --a.fac_id,--因子代码
                                            distinct b.input_value --外部行情代码
                                              from STT_TYPE_FAC_REF a
                                              left join stt_factor_ext b
                                                on a.fac_id = b.ftr_id
                                             where b.ftr_id = fac_id)) r
                             where r.serdate = sc_date
                               and r.keyvalue <= bond_days
                             order by to_number(r.keyvalue) desc)
                     where rownum = 1) lifts
              left join (select *
                          from (select r.*
                                  from (select CURVE_CODE, --行情代码
                                               serdate, --日期
                                               KEYVALUE, --关键点
                                               value --收益率
                                          from MST_YIELD_CURVE_INFO_vw --收益率曲线视图
                                         where CURVE_CODE in
                                               (select
                                                --a.fac_id,--因子代码
                                                distinct b.input_value --外部行情代码
                                                  from STT_TYPE_FAC_REF a
                                                  left join stt_factor_ext b
                                                    on a.fac_id = b.ftr_id
                                                 where b.ftr_id = fac_id)) r
                                 where r.serdate = sc_date
                                   and r.keyvalue >= bond_days
                                 order by to_number(r.keyvalue) asc)
                         where rownum = 1) rights
                on lifts.curve_code = rights.curve_code
               and lifts.serdate = rights.serdate) st1;

    RETURN v_fac_val;

    EXCEPTION
    WHEN OTHERS THEN
       --DBMS_OUTPUT.put_line('this error message is from function FUNC_GETBONDFACHANGE.');
       --DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       --DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       RETURN null;

  END;

  --久期计算测试
  --FUNCTION func_get_duration_bycf_test
  --RETURN VARCHAR2
  --AS
  --LANGUAGE JAVA NAME 'com/joyintech/algo/bond/cal/FxBondTest.testGetDurationByCf() return java.lang.String';

  --通过到期收益率计算全价,需要未来现金流及对应的年化剩余期限
  FUNCTION FUNC_PRICEBYYTM(
    in_fwdCf_array  IN NUMBER_ARRY,
    in_fwdEy_array  IN NUMBER_ARRY,
    in_ytm          IN NUMBER,
    in_payFreq      IN NUMBER
  )
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-10-22
    * SERVICE NAME   -  兆尹科技-资管事业部
    * PROJECT_NAME   -  邮储压力测试
    *
    * PROCEDURE NAME :FUNC_PRICEBYYTM
    *
    * DESCRIPTION    :通过到期收益率计算全价,需要未来现金流及对应的年化剩余期限
    *                 参考公式：PV = Σ FPi/(1+Y/f)^(Ni*f)
    *                 其中：PV是全价，FPi是远期现金流，f是年付息次数
    *                 Ni是计算日到到远期现金流之间的年化剩余期限
    *                 如果剩余付息周期大于1个，按复利计算；否则按单利计算
    * Parameters :
    *  in_ytm           IN  未来付息日的现金流数组
    *  in_fwdCf_array   IN  未来剩余期限数组
    *  in_fwdEy_array   IN  到期收益率
    *  in_irPayFreq     IN  年付息次数
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
    v_totalDiscount      NUMBER(30,14) := 0;
    v_discount           NUMBER(30,14) := 0;
  BEGIN
    --最后一个付息周期
    IF in_fwdCf_array.count = 1 THEN
      v_totalDiscount := in_fwdCf_array(1) / (1 + in_ytm * in_fwdEy_array(1));
    --非最后一个付息周期
    ELSE
      --现金流折现
      FOR i IN 1..in_fwdCf_array.count LOOP
        v_discount := in_fwdCf_array(i) / POWER(1 + in_ytm / in_payFreq, in_fwdEy_array(i) * in_payFreq);
        --折现后的现金流累加得到价格
        v_totalDiscount := v_totalDiscount + v_discount;
      END LOOP;
    END IF;

    RETURN v_totalDiscount;
  EXCEPTION
    WHEN OTHERS THEN
       DBMS_OUTPUT.put_line('this error message is from function PKG_MR_STRESS_TEST.FUNC_PRICEBYYTM.');
       DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       RETURN null;
  END;

  --通过股票资产代码和压测基准日期获取beta系数
  FUNCTION FUNC_GETBETA(in_sharesid      IN VARCHAR2, --股票资产代码
                        in_dealdate      IN VARCHAR2, --压测基准日期
                        p_ancestors_name IN VARCHAR2 DEFAULT null
  )
  /******************************************************************************
    * AUTHOR         -  Huangsu
    * CREATION DATE  -  2019-11-14
    * SERVICE NAME   -  兆尹科技-资管事业部
    * PROJECT_NAME   -  邮储压力测试
    *
    * PROCEDURE NAME :FUNC_GETBETA
    *
    * DESCRIPTION    :通过股票资产代码和压测基准日期获取beta系数
    * Parameters :
    *  in_sharesid   IN  股票资产代码
    *  in_dealdate   IN  压测基准日期，格式：yyyymmdd
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
    v_beta      NUMBER(30,14) := 0;
    v_log_head  LOG_HEAD_OBJ;
  BEGIN
    PKG_LOG.P_SET_LOG_HEAD(v_log_head, 'PKG_MR_STRESS_TEST.FUNC_GETBETA','{"in_sharesid":"'||in_sharesid||'","in_dealdate":"'||in_dealdate||'","p_ancestors_name":"'||p_ancestors_name||'"}');

    BEGIN
      select DAY_BETA_1Y into v_beta
        from MST_SHARE_YIELD
       where to_char(DEAL_DATE, 'yyyymmdd') = in_dealdate
         and SHARES_ID = in_sharesid;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      v_beta := NULL;
      PKG_LOG.ERROR(v_log_head, '股票['||in_sharesid||']的beta系数未取到！',TO_CLOB('{"股票代码":"'||in_sharesid||'","交易日期":"'||in_dealdate||'"}'));
    END;

    RETURN v_beta;
  EXCEPTION
    WHEN OTHERS THEN
       DBMS_OUTPUT.put_line('this error message is from function PKG_MR_STRESS_TEST.FUNC_GETBETA.');
       DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       PKG_LOG.ERROR(v_log_head, '获取beta系数时出错', DBMS_UTILITY.format_error_backtrace, SQLCODE, SQLERRM);
       RETURN null;
  END;

  --根据未来现金流计算久期、修正久期、凸性(固定利率)
  FUNCTION FUNC_GET_FIDURATION_BYCF(
    in_ytm       IN NUMBER,
    in_fwdCf     IN NUMBER_ARRY,
    in_fwdEy     IN NUMBER_ARRY,
    in_irPayFreq IN NUMBER
  )
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-10-21
    * SERVICE NAME   -  兆尹科技-资管事业部
    * PROJECT_NAME   -  邮储压力测试
    *
    * PROCEDURE NAME :FUNC_GET_FIDURATION_BYCF
    *
    * DESCRIPTION    :根据未来现金流计算久期、修正久期、凸性(固定利率)
    *
    * Parameters :
    *  in_ytm          IN  到期收益率
    *  in_fwdCf        IN  未来现金流，半角逗号隔开
    *  in_fwdEy        IN  剩余年限，半角逗号隔开
    *  in_irPayFreq    IN  付息频率
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
    RETURN TYPE_ARRAY_128 AS

    v_yieldForFreq      NUMBER(30,14); --区间的年化收益率
    v_full_price        NUMBER(30,14);
    v_M_duration        NUMBER(30,14) := 0; --麦氏久期
    v_convexity         NUMBER(30,14) := 0; --凸性
    v_A_duration        NUMBER(30,14);
    v_result_array      TYPE_ARRAY_128 := TYPE_ARRAY_128('0', '0', '0');
  BEGIN

    IF in_fwdCf IS NOT NULL AND in_fwdCf.count > 0 AND in_fwdEy IS NOT NULL AND in_fwdEy.count > 0 THEN
        v_yieldForFreq := in_ytm / in_irPayFreq;
        v_full_price := FUNC_PRICEBYYTM(in_fwdCf, in_fwdEy, in_ytm, in_irPayFreq);

        --对未来的现金流进行汇总处理
        FOR i IN 1..in_fwdEy.count LOOP
          v_M_duration := v_M_duration + in_fwdEy(i) * in_fwdCf(i) /
                          POWER(1 + v_yieldForFreq, in_fwdEy(i) * in_irPayFreq) / v_full_price;

          v_convexity := v_convexity + 1 / POWER(1 + v_yieldForFreq, 2) * (in_fwdEy(i)
                       * (in_fwdEy(i) + 1 / in_irPayFreq)
                       * in_fwdCf(i) / POWER(1 + v_yieldForFreq, in_fwdEy(i) * in_irPayFreq));
        END LOOP;

        v_A_duration := v_M_duration / (1 + v_yieldForFreq);
        v_result_array(1) := TO_CHAR(v_M_duration);--久期
        v_result_array(2) := TO_CHAR(v_A_duration);--修正久期
        v_result_array(3) := TO_CHAR(round(v_convexity / v_full_price,14));--凸性
    END IF;

    RETURN v_result_array;
  EXCEPTION
    WHEN OTHERS THEN
       --DBMS_OUTPUT.put_line('this error message is from function PKG_MR_STRESS_TEST.FUNC_GET_FIDURATION_BYCF.');
       --DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       --DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       RETURN type_array_128('0', '0', '0');
  END;

  --根据未来现金流计算久期、修正久期、凸性(浮动付息债算法)
  FUNCTION FUNC_GET_FLDURATION_BYCF(
    in_ytm       IN NUMBER,
    in_fwdCf     IN NUMBER_ARRY,
    in_fwdEy     IN NUMBER_ARRY,
    in_irPayFreq IN NUMBER
  )
  /******************************************************************************
    * AUTHOR         -  Jiaoxujin
    * CREATION DATE  -  2019-10-22
    * SERVICE NAME   -  兆尹科技-资管事业部
    * PROJECT_NAME   -  邮储压力测试
    *
    * PROCEDURE NAME :FUNC_GET_FLDURATION_BYCF
    *
    * DESCRIPTION    :根据未来现金流计算久期、修正久期、凸性(浮动付息债算法)
    *                 到期收益率使用浮息债本周期期初的票面利率
    *
    * Parameters :
    *  in_ytm          IN  到期收益率(使用浮息债本周期期初的票面利率)
    *  in_fwdCf        IN  未来现金流，半角逗号隔开
    *  in_fwdEy        IN  剩余年限，半角逗号隔开
    *  in_irPayFreq    IN  年付息次数
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
    RETURN TYPE_ARRAY_128 AS

    v_yieldForFreq      NUMBER(30,14); --区间的年化收益率
    v_full_price        NUMBER(30,14);
    v_M_duration        NUMBER(30,14) := 0; --麦氏久期
    v_convexity         NUMBER(30,14) := 0; --凸性
    v_A_duration        NUMBER(30,14);
    v_result_array            TYPE_ARRAY_128 := TYPE_ARRAY_128('0', '0', '0');
  BEGIN

    IF in_fwdCf IS NOT NULL AND in_fwdCf.count > 0 AND in_fwdEy IS NOT NULL AND in_fwdEy.count > 0 THEN
        v_yieldForFreq := in_ytm / in_irPayFreq;
        v_full_price := FUNC_PRICEBYYTM(in_fwdCf, in_fwdEy, in_ytm, in_irPayFreq);
        v_M_duration := v_M_duration + in_fwdEy(1);

        --对未来的现金流进行汇总处理
        FOR i IN 1..in_fwdEy.count LOOP
          v_convexity := v_convexity + 1 / POWER(1 + v_yieldForFreq, 2) * (in_fwdEy(i)
                       * (in_fwdEy(i) + 1 / in_irPayFreq)
                       * in_fwdCf(i) / POWER(1 + v_yieldForFreq, in_fwdEy(i) * in_irPayFreq));
        END LOOP;

        v_A_duration := v_M_duration / (1 + v_yieldForFreq);
        v_result_array(1) := TO_CHAR(v_M_duration);--久期
        v_result_array(2) := TO_CHAR(v_A_duration);--修正久期
        v_result_array(3) := TO_CHAR(round(v_convexity / v_full_price,14));--凸性
    END IF;

    RETURN v_result_array;
  EXCEPTION
    WHEN OTHERS THEN
       --DBMS_OUTPUT.put_line('this error message is from function PKG_MR_STRESS_TEST.FUNC_GET_FLDURATION_BYCF.');
       --DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
       --DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
       RETURN type_array_128('0', '0', '0');
  END;

  --计算计息基础天数
/*  FUNCTION func_calc_basis_days(
    in_payDateBegin   IN VARCHAR2,
    in_vdate          IN VARCHAR2,
    in_mdate          IN VARCHAR2,
    in_firstPayDate   IN VARCHAR2,
    in_basis          IN VARCHAR2,
    in_calMethod      IN VARCHAR2
  )
  RETURN NUMBER
  AS
  LANGUAGE JAVA NAME 'com/joyintech/algo/BasisDaysCalculator.calcBasisDays(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String) return java.lang.Integer';
*/

  --根据因子ID(复数)和情景起止时间，返回因子的最大变化值和因子匹配资产类型信息的列表
  PROCEDURE P_GET_FACTOR_MAXDRAWDOWN2(p_factor_ids        IN VARCHAR2,   --多个因子用半角逗号隔开
                                      p_beginDate         IN DATE,       --情景开始日期
                                      p_endDate           IN DATE,       --情景结束日期
                                      p_factor_cur        OUT PKG_STRESS_TEST_COMM.cur_out,
                                      p_calc_maxdrawndown IN VARCHAR2 DEFAULT 'true') IS --是否在本过程内同步计算因子波动值
    v_factor_ids             OBJECT_ID_ARRAY := OBJECT_ID_ARRAY();
    v_factor_id              VARCHAR2(32);
    v_factor_id_list         VARCHAR2(32767);
    v_factor_count           INTEGER := 0;
  BEGIN
    IF p_factor_ids IS NOT NULL AND LENGTH(LTRIM(RTRIM(p_factor_ids))) > 0 THEN
      v_factor_id_list := ',' || p_factor_ids || ',';
      v_factor_count := LENGTH(v_factor_id_list) - LENGTH(REPLACE(v_factor_id_list, ',', '')) - 1;

      FOR v_i IN 1 .. v_factor_count LOOP
        v_factor_id := SUBSTR(v_factor_id_list, INSTR (v_factor_id_list, ',', 1, v_i) + 1,
                                                INSTR (v_factor_id_list, ',', 1, v_i + 1)
                                              - INSTR (v_factor_id_list, ',', 1, v_i) - 1);
        v_factor_ids.extend;
        v_factor_ids(v_factor_ids.last) := v_factor_id;
      END LOOP;

      PKG_STRESS_TEST_COMM.P_GET_FACTOR_MAXDRAWDOWN(v_factor_ids, p_beginDate, p_endDate, p_factor_cur, p_calc_maxdrawndown);
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.put_line('this error message is from procedure PKG_MR_STRESS_TEST.P_GET_FACTOR_MAXDRAWDOWN2.');
      DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
      DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
  END P_GET_FACTOR_MAXDRAWDOWN2;

  --市场风险压力测试指标计算主存储过程
  PROCEDURE P_MARKET_STRESS_TEST(p_test_id IN VARCHAR2) IS
  /**********************************************************************************
   * AUTHOR         -  Huangsu
   * CREATION DATE  -  2019-09-27
   * SERVICE NAME   -  兆尹科技-资管事业部
   *
   * PROCEDURE NAME :P_MARKET_STRESS_TEST
   *
   * DESCRIPTION :   市场风险压力测试指标计算主存储过程
   * Parameters :
   *   p_test_id     IN  压力测试代码
   *
   * CALLING PROCEDURE :
   *
   ***********************************************************************************
   * POSSIBLE ERROR CONDITIONS :
   ***********************************************************************************
   * CHANGE LOG
   ***********************************************************************************
   * CHANGE NUMBER:
   * DATE:
   * DEVELOPER:
   * DESCRIPTION:
   **********************************************************************************/
    v_st_date     VARCHAR2(8);    --压力测试基准日
    v_de_flag     VARCHAR2(5);    --风险因子历史数据为空时异常处理标记：01 风险因子变化（率）返回0；02 风险因子变化（率）返回null
    v_start_date  DATE;           --情景开始日期
    v_end_date    DATE;           --情景结束日期
    v_scene_id    VARCHAR2(32);   --情景代码
    v_asset_count NUMBER;         --某类资产个数
    v_query_sql   VARCHAR2(4000); --查询资产信息的SQL语句
    v_log_head    LOG_HEAD_OBJ;
  BEGIN
    PKG_LOG.P_SET_LOG_HEAD(v_log_head, 'PKG_MR_STRESS_TEST.P_MARKET_STRESS_TEST','{"p_test_id":"'||p_test_id||'"}');

    delete from STT_STRESS_INDDATA where TEST_ID = p_test_id;

    update stt_stress_test
       set test_date = sysdate,
           start_time = systimestamp,
           exe_status = '02',
           update_user = 'PKG_MR_STRESS_TEST',
           update_time = systimestamp
     where test_id = p_test_id;
    COMMIT;

    BEGIN
      select to_char(base_date,'yyyymmdd'),DATAERROR_FLAG into v_st_date,v_de_flag from STT_STRESS_TEST where TEST_ID=p_test_id;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      PKG_LOG.ERROR(v_log_head, '压力测试['||p_test_id||']的分析基准日期和异常处理标记未取到！',TO_CLOB('{"压力测试编号":"'||p_test_id||'"}'));
    END;

    --根据压力测试代码查询情景起止日期
    BEGIN
      select SCENE_ID, START_DATE, end_date
        into v_scene_id,v_start_date,v_end_date
        from (select t1.SCENE_ID, t2.START_DATE, t2.end_date
                from STT_STRESS_SCENE t1
                left join STT_SCENE t2 on t1.SCENE_ID = t2.scene_id
               where t1.busi_id = p_test_id and t1.STT_BUSI_TYPE = '04'
             )
       where rownum = 1;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      PKG_LOG.ERROR(v_log_head, '压力测试['||p_test_id||']的情景编号和情景起止日期未取到！',TO_CLOB('{"压力测试编号":"'||p_test_id||'"}'));
    END;
-----------------------------------------------------------------------------------------------------------
    --1、债券收益率（债券类资产）
    select count(1) into v_asset_count
      from STT_TEST_OBJ t1                           --压力对象表
      left join STT_STAT_ELEMENT t2                  --元素统计项表
        on t1.object_id = t2.object_id               --对象代码（产品代码）
      left join STT_ELEMENT t3                       --元素表
        on t2.element_id = t3.element_id             --元素代码（资产代码）
     where t1.test_id = p_test_id                    --压力测试代码
       and to_char(t2.cdate, 'yyyymmdd') = v_st_date --压测基准日
       and t2.param_code = 'SHARE_AMT'               --持仓数量key
       and t2.param_value > 0                        --持仓数量value
       and (t3.type_1 = 'F01' or t3.type_1 = 'F17'); --债券、一级市场债

    IF v_asset_count > 0 THEN
      v_query_sql := 'select t1.object_id, t1.element_id, t1.type_1, t1.type_2, t1.type_3, t1.isright,
                             t1.inttype, t2.crrat, t3.currpr, t1.shamt, t5.mkval, t1.INV_AIM, ''''
                        from (select distinct t1.object_id, t2.element_id, t2.INV_AIM, t2.param_value shamt,
                                     t3.type_1, t3.type_2, t3.type_3,
                                     nvl(t4.param_value, ''N'') isright,
                                     nvl(t5.param_value, ''01'') inttype
                                from STT_TEST_OBJ t1
                                left join STT_STAT_ELEMENT t2 on t1.object_id = t2.object_id
                                left join STT_ELEMENT t3 on t2.element_id = t3.element_id
                                left join stt_element_attr t4 on t3.element_id = t4.Element_Id and t4.param_code = ''IS_RIGHT''
                                left join stt_element_attr t5 on t3.element_id = t5.Element_Id and t5.param_code = ''INT_TYPE''
                               where t1.test_id = '''||p_test_id||
                             ''' and to_char(t2.cdate, ''yyyymmdd'') = '''||v_st_date||
                             ''' and t2.param_code = ''SHARE_AMT''
                                 and t2.param_value > 0
                                 and (t3.type_1 = ''F01'' or t3.type_1 = ''F17'')) t1
                        left join (select OBJECT_ID, ELEMENT_ID, PARAM_VALUE crrat
                                     from STT_STAT_ELEMENT
                                    where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                  ''' and PARAM_CODE = ''GRADE_ORDER'') t2
                          on t1.element_id = t2.OBJECT_ID and t1.element_id = t2.ELEMENT_ID
                        left join (select OBJECT_ID, ELEMENT_ID, PARAM_VALUE currpr,INV_AIM
                                     from STT_STAT_ELEMENT
                                    where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                  ''' and PARAM_CODE = ''CURRENT_PRICE'') t3
                          on t1.object_id = t3.OBJECT_ID and t1.element_id = t3.ELEMENT_ID and t3.INV_AIM = t1.INV_AIM
                        left join (select OBJECT_ID, ELEMENT_ID, PARAM_VALUE mkval,INV_AIM
                                     from STT_STAT_ELEMENT
                                    where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                  ''' and PARAM_CODE = ''MARKET_VALUE'') t5
                          on t1.object_id = t5.OBJECT_ID and t1.element_id = t5.ELEMENT_ID and t1.INV_AIM = t5.INV_AIM';

      p_save_calc_result(p_test_id,v_scene_id,v_start_date,v_end_date,v_st_date,v_query_sql,'01',v_de_flag,'PKG_MR_STRESS_TEST.P_MARKET_STRESS_TEST');
    END IF;
-----------------------------------------------------------------------------------------------------------
    --2、基金指数（基金）
    select count(1) into v_asset_count
      from STT_TEST_OBJ t1                           --压力对象表
      left join STT_STAT_ELEMENT t2                  --元素统计项表
        on t1.object_id = t2.object_id               --对象代码（产品代码）
      left join STT_ELEMENT t3                       --元素表
        on t2.element_id = t3.element_id             --元素代码（资产代码）
     where t1.test_id = p_test_id                    --压力测试代码
       and to_char(t2.cdate, 'yyyymmdd') = v_st_date --压测基准日
       and t2.param_code = 'SHARE_AMT'               --持仓数量key
       and t2.param_value > 0                        --持仓数量value
       and t3.type_1 = 'F02';                        --基金

    IF v_asset_count > 0 THEN
      v_query_sql := 'select t0.OBJECT_ID, t0.ELEMENT_ID, t0.type_1, t0.type_2, t0.type_3,
                             '''', '''', '''', t2.currpr, t0.shamt, t4.mkval, '''', ''''
                        from (select distinct t1.object_id, t2.element_id, t2.param_value shamt,
                                     t3.type_1, t3.type_2, t3.type_3
                                from STT_TEST_OBJ t1
                                left join STT_STAT_ELEMENT t2 on t1.object_id = t2.object_id
                                left join STT_ELEMENT t3 on t2.element_id = t3.element_id
                               where t1.test_id = '''||p_test_id||
                             ''' and to_char(t2.cdate, ''yyyymmdd'') = '''||v_st_date||
                             ''' and t2.param_code = ''SHARE_AMT''
                                 and t2.param_value > 0
                                 and t3.type_1 = ''F02'') t0
                        left join (select OBJECT_ID, ELEMENT_ID, PARAM_VALUE currpr
                                     from STT_STAT_ELEMENT
                                    where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                  ''' and PARAM_CODE = ''CURRENT_PRICE'') t2
                          on t0.object_id = t2.object_id and t0.element_id = t2.ELEMENT_ID
                        left join (select OBJECT_ID, ELEMENT_ID, PARAM_VALUE mkval
                                     from STT_STAT_ELEMENT
                                    where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                  ''' and PARAM_CODE = ''MARKET_VALUE'') t4
                          on t0.object_id = t4.object_id and t0.element_id = t4.ELEMENT_ID';

      p_save_calc_result(p_test_id,v_scene_id,v_start_date,v_end_date,v_st_date,v_query_sql,'02',v_de_flag,'PKG_MR_STRESS_TEST.P_MARKET_STRESS_TEST');
    END IF;
-----------------------------------------------------------------------------------------------------------
    --3、股票指数（股票类资产）
    select count(1) into v_asset_count
      from STT_TEST_OBJ t1                           --压力对象表
      left join STT_STAT_ELEMENT t2                  --元素统计项表
        on t1.object_id = t2.object_id               --对象代码（产品代码）
      left join STT_ELEMENT t3                       --元素表
        on t2.element_id = t3.element_id             --元素代码（资产代码）
     where t1.test_id = p_test_id                    --压力测试代码
       and to_char(t2.cdate, 'yyyymmdd') = v_st_date --压测基准日
       and t2.param_code = 'SHARE_AMT'               --持仓数量key
       and t2.param_value > 0                        --持仓数量value
       and t3.type_1 = 'F11';                        --股票

    IF v_asset_count > 0 THEN
       v_query_sql := 'select t0.OBJECT_ID, t0.ELEMENT_ID, t0.type_1, t0.type_2, t0.type_3,
                              '''', '''', '''', t2.currpr, t0.shamt, t4.mkval, '''', ''''
                         from (select distinct t1.object_id, t2.element_id, t2.param_value shamt,
                                      t3.type_1, t3.type_2, t3.type_3
                                 from STT_TEST_OBJ t1
                                 left join STT_STAT_ELEMENT t2 on t1.object_id = t2.object_id
                                 left join STT_ELEMENT t3 on t2.element_id = t3.element_id
                                where t1.test_id = '''||p_test_id||
                              ''' and to_char(t2.cdate, ''yyyymmdd'') = '''||v_st_date||
                              ''' and t2.param_code = ''SHARE_AMT''
                                  and t2.param_value > 0
                                  and t3.type_1 = ''F11'') t0
                         left join (select OBJECT_ID, ELEMENT_ID, PARAM_VALUE currpr
                                      from STT_STAT_ELEMENT
                                     where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                   ''' and PARAM_CODE = ''CURRENT_PRICE'') t2
                           on t0.object_id = t2.object_id and t0.element_id = t2.ELEMENT_ID
                         left join (select OBJECT_ID, ELEMENT_ID, PARAM_VALUE mkval
                                      from STT_STAT_ELEMENT
                                     where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                   ''' and PARAM_CODE = ''MARKET_VALUE'') t4
                           on t0.object_id = t4.object_id and t0.element_id = t4.ELEMENT_ID';

      p_save_calc_result(p_test_id,v_scene_id,v_start_date,v_end_date,v_st_date,v_query_sql,'03',v_de_flag,'PKG_MR_STRESS_TEST.P_MARKET_STRESS_TEST');
    END IF;
-----------------------------------------------------------------------------------------------------------
    --4、外汇价格（外汇即期）
    select count(1) into v_asset_count
      from STT_TEST_OBJ t1                           --压力对象表
      left join STT_STAT_ELEMENT t2                  --元素统计项表
        on t1.object_id = t2.object_id               --对象代码（产品代码）
      left join STT_ELEMENT t3                       --元素表
        on t2.element_id = t3.element_id             --元素代码（资产代码）
     where t1.test_id = p_test_id                    --压力测试代码
       and to_char(t2.cdate, 'yyyymmdd') = v_st_date --压测基准日
       and t2.param_code = 'AMOUNT'                  --持仓数量key
       and t2.param_value != 0;                       --持仓数量value

    IF v_asset_count > 0 THEN
      v_query_sql := 'select t1.OBJECT_ID, t1.ELEMENT_ID, '''', '''', '''', '''', '''', '''',
                             t2.mkval/t1.shamt currpr, t1.shamt, t2.mkval, '''', t1.ftr_id
                        from (select distinct t1.object_id, t2.element_id, t2.param_value shamt, t4.ftr_id
                                from STT_TEST_OBJ t1
                                left join (select max(object_id) object_id,max(element_id) element_id,sum(param_value) param_value
                                             from STT_STAT_ELEMENT
                                            where to_char(cdate, ''yyyymmdd'') = '''||v_st_date||
                                          ''' and param_code = ''AMOUNT''
                                            group by object_id, element_id) t2
                                  on t1.object_id = t2.object_id
                                left join STT_ELEMENT t3 on t2.element_id = t3.element_id
                                left join STT_FACTOR_EXT t4 on t4.input_value = ''CNY/''||t2.element_id
                               where t1.test_id = '''||p_test_id||
                             ''' and t2.element_id is not null
                                 and t2.param_value != 0) t1
                        left join (select max(object_id) object_id,max(element_id) element_id,sum(param_value) mkval
                                     from STT_STAT_ELEMENT
                                    where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                  ''' and PARAM_CODE = ''VALUE''
                                    group by object_id, element_id) t2
                          on t1.object_id = t2.OBJECT_ID and t1.element_id = t2.ELEMENT_ID';

      p_save_calc_result(p_test_id,v_scene_id,v_start_date,v_end_date,v_st_date,v_query_sql,'0401',v_de_flag,'PKG_MR_STRESS_TEST.P_MARKET_STRESS_TEST');
    END IF;

    --4、外汇价格（外汇远期、掉期）
    select count(1) into v_asset_count
      from STT_TEST_OBJ t1                           --压力对象表
      left join STT_STAT_ELEMENT t2                  --元素统计项表
        on t1.object_id = t2.object_id               --对象代码（产品代码）
      left join STT_ELEMENT t3                       --元素表
        on t2.element_id = t3.element_id             --元素代码（资产代码）
     where t1.test_id = p_test_id                    --压力测试代码
       and to_char(t2.cdate, 'yyyymmdd') = v_st_date --压测基准日
       and t2.param_code = 'SHARE_AMT'               --持仓数量key
       and t2.param_value > 0                        --持仓数量value
       and t3.type_1 in ('F14', 'F15');              --外汇远期、掉期

    IF v_asset_count > 0 THEN
      v_query_sql := 'select t1.OBJECT_ID, t1.ELEMENT_ID, '''', '''', '''', '''', '''', '''',
                             t2.currpr, t1.shamt, t4.mkval, '''', t1.ftr_id
                        from (select distinct t1.object_id, t2.element_id, t2.param_value shamt, t5.ftr_id
                                from STT_TEST_OBJ t1
                                left join STT_STAT_ELEMENT t2 on t1.object_id = t2.object_id
                                left join STT_ELEMENT t3 on t2.element_id = t3.element_id
                                left join STT_ELEMENT_ATTR t4 on t3.element_id = t4.ELEMENT_ID
                                left join STT_FACTOR_EXT t5 on SUBSTR(t4.param_value,0,3)||''/''||SUBSTR(t4.param_value,4,3) = t5.input_value
                               where t1.test_id = '''||p_test_id||
                             ''' and to_char(t2.cdate, ''yyyymmdd'') = '''||v_st_date||
                             ''' and t2.param_code = ''SHARE_AMT''
                                 and t2.param_value > 0
                                 and t3.type_1 in (''F14'', ''F15'')
                                 and t4.param_code = ''CUR_PAIR'') t1
                        left join (select OBJECT_ID, ELEMENT_ID, PARAM_VALUE currpr
                                     from STT_STAT_ELEMENT
                                    where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                  ''' and PARAM_CODE = ''CURRENT_PRICE'') t2
                          on t1.object_id = t2.object_id and t1.element_id = t2.ELEMENT_ID
                        left join (select OBJECT_ID, ELEMENT_ID, PARAM_VALUE mkval
                                     from STT_STAT_ELEMENT
                                    where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||
                                  ''' and PARAM_CODE = ''MARKET_VALUE'') t4
                          on t1.object_id = t4.object_id and t1.element_id = t4.ELEMENT_ID';

      p_save_calc_result(p_test_id,v_scene_id,v_start_date,v_end_date,v_st_date,v_query_sql,'0402',v_de_flag,'PKG_MR_STRESS_TEST.P_MARKET_STRESS_TEST');
    END IF;
-----------------------------------------------------------------------------------------------------------
    --5、货币利率（人民币利率互换）
    select count(1) into v_asset_count
      from STT_TEST_OBJ t1                           --压力对象表
      left join STT_STAT_ELEMENT t2                  --元素统计项表
        on t1.object_id = t2.object_id               --对象代码（产品代码）
      left join STT_ELEMENT t3                       --元素表
        on t2.element_id = t3.element_id             --元素代码（资产代码）
     where t1.test_id = p_test_id                    --压力测试代码
       and to_char(t2.cdate, 'yyyymmdd') = v_st_date --压测基准日
       and t2.param_code = 'SHARE_AMT'               --持仓数量key
       and t2.param_value > 0                        --持仓数量value
       and t3.type_1 = 'F16';                        --货币利率（人民币利率互换）

    IF v_asset_count > 0 THEN
      v_query_sql := 'select t1.OBJECT_ID, t1.ELEMENT_ID, '''', '''', '''', '''', t1.inttype, '''',
                             t2.currpr, t1.shamt, t2.currpr, '''', t1.ftr_id
                        from (select distinct t1.object_id, t2.element_id, t2.param_value shamt,
                                     t5.ftr_id, nvl(t6.param_value, ''01'') inttype
                                from STT_TEST_OBJ t1
                                left join STT_STAT_ELEMENT t2 on t1.object_id = t2.object_id
                                left join STT_ELEMENT t3 on t2.element_id = t3.element_id
                                left join STT_ELEMENT_ATTR t4 on t3.element_id = t4.ELEMENT_ID
                                left join STT_FACTOR_EXT t5 on t4.param_value = t5.input_value
                                left join stt_element_attr t6 on t3.element_id = t6.Element_Id and t6.param_code = ''IRS_TYPE''
                               where t1.test_id = '''||p_test_id||
                             ''' and to_char(t2.cdate, ''yyyymmdd'') = '''||v_st_date||
                             ''' and t2.param_code = ''SHARE_AMT''
                                 and t2.param_value > 0
                                 and t3.type_1 = ''F16''
                                 and t4.param_code = ''BENCHMARK_ID'') t1
                        left join (select PORTFOLIO_ID, FINPROD_ID, TDY_FLOAT_INGPL currpr
                                     from PTL_SEC_VALUTION
                                    where to_char(CDATE, ''yyyymmdd'') = '''||v_st_date||''') t2
                          on t1.object_id = t2.PORTFOLIO_ID and t1.element_id = t2.FINPROD_ID';

      p_save_calc_result(p_test_id,v_scene_id,v_start_date,v_end_date,v_st_date,v_query_sql,'05',v_de_flag,'PKG_MR_STRESS_TEST.P_MARKET_STRESS_TEST');
    END IF;
-----------------------------------------------------------------------------------------------------------
    --6、其它类型资产
    select count(1) into v_asset_count
      from STT_TEST_OBJ t1                           --压力对象表
      left join STT_STAT_ELEMENT t2                  --元素统计项表
        on t1.object_id = t2.object_id               --对象代码（产品代码）
      left join STT_ELEMENT t3                       --元素表
        on t2.element_id = t3.element_id             --元素代码（资产代码）
     where t1.test_id = p_test_id                    --压力测试代码
       and to_char(t2.cdate, 'yyyymmdd') = v_st_date --压测基准日
       and t2.param_code = 'SHARE_AMT'               --持仓数量key
       and t2.param_value > 0                        --持仓数量value
       and (t3.type_1 not in ('F01','F02','F11','F13','F14','F15','F16','F17') or t3.type_1 is null);

    IF v_asset_count > 0 THEN
      v_query_sql := 'select ta.object_id, ta.element_id, '''', '''', '''', '''', '''', '''',
                             tb.param_value, ta.param_value, tc.param_value, '''', ''''
                        from (select distinct t1.object_id, t2.element_id, t2.param_value
                                from STT_TEST_OBJ t1
                                left join STT_STAT_ELEMENT t2 on t1.object_id = t2.object_id
                                left join STT_ELEMENT t3 on t2.element_id = t3.element_id
                               where t1.test_id = '''||p_test_id||
                             ''' and to_char(t2.cdate,''yyyymmdd'') = '''||v_st_date||
                             ''' and t2.param_code = ''SHARE_AMT''
                                 and t2.param_value > 0
                                 and (t3.type_1 not in (''F01'',''F02'',''F11'',''F13'',''F14'',''F15'',''F16'',''F17'') or t3.type_1 is null)) ta
                        left join (select t.object_id, t.element_id, t.param_value
                                     from STT_STAT_ELEMENT t
                                    where to_char(t.cdate,''yyyymmdd'') = '''||v_st_date||
                                  ''' and t.param_code = ''CURRENT_PRICE'') tb
                          on ta.object_id = tb.object_id and ta.element_id = tb.element_id
                        left join (select t.object_id, t.element_id, t.param_value
                                     from STT_STAT_ELEMENT t
                                    where to_char(t.cdate,''yyyymmdd'') = '''||v_st_date||
                                  ''' and t.param_code = ''MARKET_VALUE'') tc
                          on ta.object_id = tc.object_id and ta.element_id = tc.element_id';

      p_save_calc_result(p_test_id,v_scene_id,v_start_date,v_end_date,v_st_date,v_query_sql,'06',v_de_flag,'PKG_MR_STRESS_TEST.P_MARKET_STRESS_TEST');
    END IF;
-----------------------------------------------------------------------------------------------------------
--占组合总资产比（%）-10019-产品指标
insert into STT_STRESS_INDDATA
  (SEQ_NO, TEST_ID, OBJECT_ID, ELEMENT_ID, SCENE_ID, IND_ID, VALUE_STRUCT, VALUE,
   VALUE_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
  select SEQ_STT_STRESS_INDDATA.Nextval, p_test_id, t1.OBJECT_ID,
         t1.ELEMENT_ID, v_scene_id, '10019', '01', t1.VALUE / t2.ttmkval * 100, '01',
         'PKG_MR_STRESS_TEST', 'system', systimestamp, 'PKG_MR_STRESS_TEST', systimestamp
    from STT_STRESS_INDDATA t1
    left join (select sum(VALUE) ttmkval, OBJECT_ID
                 from STT_STRESS_INDDATA
                where TEST_ID = p_test_id
                  and SCENE_ID = v_scene_id
                  and IND_ID = '10018'
                group by OBJECT_ID) t2
    on t1.object_id = t2.object_id
   where t1.TEST_ID = p_test_id
     and t1.SCENE_ID = v_scene_id
     and t1.IND_ID = '10018'; --持仓市值

--占组合总资产比(%)-10024-承压指标
insert into STT_STRESS_INDDATA
  (SEQ_NO, TEST_ID, OBJECT_ID, ELEMENT_ID, SCENE_ID, IND_ID, VALUE_STRUCT, VALUE,
   VALUE_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
  select SEQ_STT_STRESS_INDDATA.Nextval, p_test_id, t1.OBJECT_ID,
         t1.ELEMENT_ID, v_scene_id, '10024', '01', t1.VALUE / t2.ttmkval * 100, '01',
         'PKG_MR_STRESS_TEST', 'system', systimestamp, 'PKG_MR_STRESS_TEST', systimestamp
    from STT_STRESS_INDDATA t1
    left join (select sum(VALUE) ttmkval, OBJECT_ID
                 from STT_STRESS_INDDATA
                where TEST_ID = p_test_id
                  and SCENE_ID = v_scene_id
                  and IND_ID = '10023'
                group by OBJECT_ID) t2
    on t1.object_id = t2.object_id
   where t1.TEST_ID = p_test_id
     and t1.SCENE_ID = v_scene_id
     and t1.IND_ID = '10023'; --情景市值

--损益占比(%)-10026-承压指标
insert into STT_STRESS_INDDATA
  (SEQ_NO, TEST_ID, OBJECT_ID, ELEMENT_ID, SCENE_ID, IND_ID, VALUE_STRUCT, VALUE,
   VALUE_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
  select SEQ_STT_STRESS_INDDATA.Nextval, p_test_id, t1.OBJECT_ID,
         t1.ELEMENT_ID, v_scene_id, '10026', '01', t1.VALUE / t2.ttmkval * 100, '01',
         'PKG_MR_STRESS_TEST', 'system', systimestamp, 'PKG_MR_STRESS_TEST', systimestamp
    from STT_STRESS_INDDATA t1
    left join (select sum(VALUE) ttmkval, OBJECT_ID,ELEMENT_ID
                 from STT_STRESS_INDDATA
                where TEST_ID = p_test_id
                  and SCENE_ID = v_scene_id
                  and IND_ID = '10018'
                group by OBJECT_ID,ELEMENT_ID) t2
    on t1.object_id = t2.object_id and t1.ELEMENT_ID = t2.ELEMENT_ID
   where t1.TEST_ID = p_test_id
     and t1.SCENE_ID = v_scene_id
     and t1.IND_ID = '10027'; --损失金额
-----------------------------------------------------------------------------------------------------------
--更新测试状态为“完成”
update stt_stress_test
   set finished_time = systimestamp,
       exe_status = '01',
       update_user = 'PKG_MR_STRESS_TEST',
       update_time = systimestamp
 where test_id = p_test_id;
 COMMIT;

  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;

    update stt_stress_test
       set finished_time = systimestamp,
           exe_status = '03',
           update_user = 'PKG_MR_STRESS_TEST',
           update_time = systimestamp
     where test_id = p_test_id;
     COMMIT;

      DBMS_OUTPUT.put_line('this error message is from procedure PKG_MR_STRESS_TEST.P_MARKET_STRESS_TEST.');
      DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
      DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
      PKG_LOG.ERROR(v_log_head, '市场风险压力测试执行时出错', DBMS_UTILITY.format_error_backtrace, SQLCODE, SQLERRM);
  END P_MARKET_STRESS_TEST;

  --市场风险压力测试保存指标计算结果
  PROCEDURE P_SAVE_CALC_RESULT(p_test_id        IN VARCHAR2,
                               p_scene_id       IN VARCHAR2,
                               p_start_date     IN DATE,
                               p_end_date       IN DATE,
                               p_base_date      IN VARCHAR2,
                               p_query_sql      IN VARCHAR2,
                               p_asset_type     IN VARCHAR2,
                               p_de_flag        IN VARCHAR2,
                               p_ancestors_name IN VARCHAR2 DEFAULT null) IS
  /**********************************************************************************
   * AUTHOR         -  Huangsu
   * CREATION DATE  -  2019-10-11
   * SERVICE NAME   -  兆尹科技-资管事业部
   *
   * PROCEDURE NAME :P_SAVE_CALC_RESULT
   *
   * DESCRIPTION :   市场风险压力测试保存指标计算结果
   * Parameters :
   *   p_test_id        IN 压力测试代码
   *   p_scene_id       IN 情景代码
   *   p_start_date     IN 情景开始日期
   *   p_end_date       IN 情景结束日期
   *   p_base_date      IN 压力测试基准日期
   *   p_query_sql      IN 查询资产信息的SQL语句
   *   p_asset_type     IN 资产类型（01-债券,02-基金,03-股票,0401-外汇即期,0402-外汇远期掉期,05-利率互换,06-其它）
   *   p_de_flag        IN 风险因子历史数据为空时异常处理标记：01 风险因子变化（率）返回0；02 风险因子变化（率）返回null
   *   p_ancestors_name IN 多个祖宗用半角逗号隔开
   *
   * CALLING PROCEDURE :
   *
   ***********************************************************************************
   * POSSIBLE ERROR CONDITIONS :
   ***********************************************************************************
   * CHANGE LOG
   ***********************************************************************************
   * CHANGE NUMBER:
   * DATE:
   * DEVELOPER:
   * DESCRIPTION:
   **********************************************************************************/
    v_prod_code        VARCHAR2(32);   --产品代码
    v_asset_code       VARCHAR2(50);   --资产代码
    v_asset_type1      VARCHAR2(50);   --资产一级分类
    v_asset_type2      VARCHAR2(50);   --资产二级分类
    v_asset_type3      VARCHAR2(50);   --资产三级分类
    v_is_right         VARCHAR2(100);  --是否含权
    v_int_type         VARCHAR2(100);  --利率类型
    v_credit_grade     VARCHAR2(100);  --信用评级
    v_curr_price       VARCHAR2(100);  --当前价格
    v_asset_shamt      VARCHAR2(100);  --持仓数量
    v_market_val       VARCHAR2(100);  --资产市值
    v_invest_aim       VARCHAR2(50);   --投资目的
    v_ftr_id           VARCHAR2(50);   --因子代码
    v_ftr_value        NUMBER(30,14);  --因子值（变化）
    v_scene_price      NUMBER(30,14);  --情景价格
    v_re_period        NUMBER(30,14);  --剩余期限（年）
    v_bond_dur         NUMBER(30,14);  --债券久期
    v_bond_conv        NUMBER(30,14);  --债券凸性
    v_shrs_beta        NUMBER(30,14);  --股票beta系数
    v_irs_ytm          NUMBER(30,14);  --利率互换到期收益率
    v_irs_fwdcf        NUMBER_ARRY;    --未来现金流
    v_irs_prin         NUMBER(30,14);  --名义本金
    v_irs_fwdey        NUMBER_ARRY;    --剩余期限
    v_irs_payfreq      NUMBER(30,14);  --付息频率
    v_irs_fidur        TYPE_ARRAY_128; --利率互换固定端久期算法返回值接收
    v_irs_fldur        TYPE_ARRAY_128; --利率互换浮动端久期算法返回值接收
    v_curve_data       PKG_STRESS_TEST_COMM.TWO_DIMENSIONAL_ARRAY;
    c_fetch_asset_data PKG_STRESS_TEST_COMM.cursor_type;
    v_log_head         LOG_HEAD_OBJ;
  BEGIN
    PKG_LOG.P_SET_LOG_HEAD(v_log_head, 'PKG_MR_STRESS_TEST.P_SAVE_CALC_RESULT','{"p_test_id":"'||p_test_id||'","p_scene_id":"'||p_scene_id||'","p_start_date":"'||to_char(p_start_date,'yyyymmdd')||'","p_end_date":"'||to_char(p_end_date,'yyyymmdd')||'","p_base_date":"'||p_base_date||'","p_query_sql":"'||p_query_sql||'","p_asset_type":"'||p_asset_type||'","p_de_flag":"'||p_de_flag||'","p_ancestors_name":"'||p_ancestors_name||'"}');

    open c_fetch_asset_data for p_query_sql;
    LOOP
      FETCH c_fetch_asset_data INTO v_prod_code,v_asset_code,v_asset_type1,v_asset_type2,v_asset_type3,v_is_right,v_int_type,v_credit_grade,v_curr_price,v_asset_shamt,v_market_val,v_invest_aim,v_ftr_id;
      EXIT WHEN c_fetch_asset_data%NOTFOUND;

      v_ftr_value := NULL;
--------------------------------------------------------------------------------------------------------------
      IF p_asset_type='01' THEN --债券
          --a、查询债券剩余期限
          IF v_is_right = 'N' THEN
            --剩余期限
            v_re_period := FUNC_GETBONDEDAYS(v_asset_code,p_base_date,1);--剩余存续期限
            IF v_re_period IS NULL THEN
              PKG_LOG.WARN(v_log_head, '非含权债['||v_asset_code||']的剩余期限未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            END IF;

            --久期、凸性
            IF v_int_type = '01' THEN --固定
              BEGIN
                select nvl(M_DURATION,v_re_period),nvl(M_CONVEXITY,0) into v_bond_dur,v_bond_conv from MST_BOND_VALUATION_INFO where VALUE_SOURCE='CBC' and SEC_ID=SUBSTR(v_asset_code,INSTR(v_asset_code,'_')+1) and to_char(VALUE_DATE,'yyyymmdd')=p_base_date;
              EXCEPTION WHEN NO_DATA_FOUND THEN
                v_bond_dur :=v_re_period;
                v_bond_conv :=0;
                PKG_LOG.ERROR(v_log_head, '非含权固定债['||v_asset_code||']的久期和凸性未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
              END;
            ELSE --浮动
              BEGIN
                select nvl(SDURATION,v_re_period),nvl(SCNVXTY,0) into v_bond_dur,v_bond_conv from MST_BOND_VALUATION_INFO where VALUE_SOURCE='CBC' and SEC_ID=SUBSTR(v_asset_code,INSTR(v_asset_code,'_')+1) and to_char(VALUE_DATE,'yyyymmdd')=p_base_date;
              EXCEPTION WHEN NO_DATA_FOUND THEN
                v_bond_dur :=v_re_period;
                v_bond_conv :=0;
                PKG_LOG.ERROR(v_log_head, '非含权浮动债['||v_asset_code||']的久期和凸性未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
              END;
            END IF;
          ELSE
            --剩余期限
            IF FUNC_GETBONDDUR(v_asset_code,p_base_date) > FUNC_GETBONDEDAYS(v_asset_code,p_base_date,2) THEN
              v_re_period := FUNC_GETBONDEDAYS(v_asset_code,p_base_date,1);--剩余存续期限
            ELSE
              v_re_period := FUNC_GETBONDEDAYS(v_asset_code,p_base_date,2);--短期剩余期限
            END IF;
            IF v_re_period IS NULL THEN
              PKG_LOG.WARN(v_log_head, '含权债['||v_asset_code||']的剩余期限未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            END IF;

            --久期、凸性
            BEGIN
              select nvl(M_DURATION,v_re_period),nvl(M_CONVEXITY,0) into v_bond_dur,v_bond_conv from MST_BOND_VALUATION_INFO where VALUE_SOURCE='CBC' and SEC_ID=SUBSTR(v_asset_code,INSTR(v_asset_code,'_')+1) and to_char(VALUE_DATE,'yyyymmdd')=p_base_date;
            EXCEPTION WHEN NO_DATA_FOUND THEN
              v_bond_dur :=v_re_period;
              v_bond_conv :=0;
              PKG_LOG.ERROR(v_log_head, '含权债['||v_asset_code||']的久期和凸性未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            END;
          END IF;

          --b、根据债券取因子代码
          BEGIN
            IF v_credit_grade IS NULL THEN
              v_credit_grade := PKG_STRESS_TEST_COMM.v_default_grade;
              PKG_LOG.ERROR(v_log_head, '债券['||v_asset_code||']的评级未取到，用默认评级代替！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            END IF;
            select FAC_ID into v_ftr_id from
            (select * from table(cast(PKG_STRESS_TEST_COMM.FUNC_GET_FACTORS(v_asset_type1,v_asset_type2,v_asset_type3,v_credit_grade) as FACTOR_VALUE_TABLE)))
            where rownum = 1;
          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.ERROR(v_log_head, '债券['||v_asset_code||']的风险因子未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          --c、根据因子代码取情景变化
          v_curve_data := PKG_STRESS_TEST_COMM.FUNC_GET_CURVE_DATA(v_ftr_id, p_start_date, p_end_date, v_re_period);

          IF v_curve_data IS NOT NULL AND v_curve_data.COUNT >= 1 THEN
            v_ftr_value := PKG_STRESS_TEST_COMM.FUNC_GET_MAXDRAWDOWN(v_curve_data(1), '1');
          END IF;
          IF v_ftr_value IS NULL THEN
            IF p_de_flag = '01' THEN
              v_ftr_value := 0;
              PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为0。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            ELSE
              PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为null。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            END IF;
          END IF;

          --d、计算情景价格
          IF v_invest_aim = 'AC' THEN
            v_scene_price := v_curr_price;
            PKG_LOG.INFO(v_log_head, '投资目的为摊余成本，情景价格取当前价格。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          ELSE
            v_scene_price := v_curr_price*(1+(-v_bond_dur*v_ftr_value+0.5*v_bond_conv*power(v_ftr_value,2)));
          END IF;
--------------------------------------------------------------------------------------------------------------
        ELSIF p_asset_type='02' THEN --基金
          --a、根据基金分类取因子代码
          BEGIN
            select FAC_ID into v_ftr_id from
            (select * from table(cast(PKG_STRESS_TEST_COMM.FUNC_GET_FACTORS(v_asset_type1,v_asset_type2,v_asset_type3,v_credit_grade) as FACTOR_VALUE_TABLE)))
            where rownum = 1;
          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.ERROR(v_log_head, '基金['||v_asset_code||']的风险因子未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          --b、根据因子代码取情景变化
          v_curve_data := PKG_STRESS_TEST_COMM.FUNC_GET_CURVE_DATA(v_ftr_id, p_start_date, p_end_date, v_re_period);

          IF v_curve_data IS NOT NULL AND v_curve_data.COUNT >= 1 THEN
            v_ftr_value := PKG_STRESS_TEST_COMM.FUNC_GET_MAXDRAWDOWN(v_curve_data(1), '2');
          END IF;
          IF v_ftr_value IS NULL THEN
            IF p_de_flag = '01' THEN
              v_ftr_value := 0;
              PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为0。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            ELSE
              PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为null。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            END IF;
          END IF;

          --c、计算情景价格
          v_scene_price := v_curr_price * (1 + v_ftr_value);
--------------------------------------------------------------------------------------------------------------
        ELSIF p_asset_type='03' THEN --股票
          --a、根据基金分类取因子代码
          BEGIN
            select FAC_ID into v_ftr_id from
            (select * from table(cast(PKG_STRESS_TEST_COMM.FUNC_GET_FACTORS(v_asset_type1,v_asset_type2,v_asset_type3,v_credit_grade) as FACTOR_VALUE_TABLE)))
            where rownum = 1;
          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.ERROR(v_log_head, '股票['||v_asset_code||']的风险因子未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          --b、根据因子代码取情景变化
          v_curve_data := PKG_STRESS_TEST_COMM.FUNC_GET_CURVE_DATA(v_ftr_id, p_start_date, p_end_date, v_re_period);

          IF v_curve_data IS NOT NULL AND v_curve_data.COUNT >= 1 THEN
            v_ftr_value := PKG_STRESS_TEST_COMM.FUNC_GET_MAXDRAWDOWN(v_curve_data(1), '2');
          END IF;
          IF v_ftr_value IS NULL THEN
            IF p_de_flag = '01' THEN
              v_ftr_value := 0;
              PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为0。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            ELSE
              PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为null。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
            END IF;
          END IF;

          v_shrs_beta := FUNC_GETBETA(SUBSTR(v_asset_code,INSTR(v_asset_code,'_')+1),p_base_date);
          IF v_shrs_beta IS NULL THEN
            PKG_LOG.WARN(v_log_head, '股票['||v_asset_code||']的beta系数未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END IF;

          --c、计算情景价格
          v_scene_price := v_curr_price * (1 + (v_shrs_beta * v_ftr_value));
--------------------------------------------------------------------------------------------------------------
        ELSIF p_asset_type='0401' OR p_asset_type='0402' THEN --外汇
          IF v_asset_code = 'CNY' THEN
            v_scene_price := 1;
          ELSE
            --a、根据因子代码取情景变化
            v_curve_data := PKG_STRESS_TEST_COMM.FUNC_GET_CURVE_DATA(v_ftr_id, p_start_date, p_end_date, v_re_period);

            IF v_curve_data IS NOT NULL AND v_curve_data.COUNT >= 1 THEN
              v_ftr_value := PKG_STRESS_TEST_COMM.FUNC_GET_MAXDRAWDOWN(v_curve_data(1), '2');
            END IF;
            IF v_ftr_value IS NULL THEN
              IF p_de_flag = '01' THEN
                v_ftr_value := 0;
                PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为0。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
              ELSE
                PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为null。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
              END IF;
            END IF;

            --b、计算情景价格
            v_scene_price := v_curr_price * (1 + v_ftr_value);
          END IF;
--------------------------------------------------------------------------------------------------------------
        ELSIF p_asset_type='05' THEN --利率互换
          BEGIN
            select PRIN into v_irs_prin from FIN_TRADE_PRODUCT where FINPROD_ID=v_asset_code;
          EXCEPTION WHEN NO_DATA_FOUND THEN
            v_irs_prin :=null;
            PKG_LOG.ERROR(v_log_head, '利率互换['||v_asset_code||']的名义本金未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

        --IF v_int_type = '01' THEN --固定
          --到期收益率
          BEGIN
            select t2.YIELD into v_irs_ytm
              from FIN_RATE_INFO t1
              left join FIN_CASH_CALC_RULE t2
                on t1.cash_id = t2.cash_id
             where t1.FINPROD_ID = v_asset_code
               and t1.INT_TYPE = '01';
          EXCEPTION WHEN NO_DATA_FOUND THEN
             v_irs_ytm := NULL;
             PKG_LOG.ERROR(v_log_head, '利率互换['||v_asset_code||']的固定端到期收益率未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          --未来现金流
          BEGIN
            SELECT CASH_AMT BULK COLLECT INTO v_irs_fwdcf from
            (select to_char(v_irs_prin*v_irs_ytm/(decode(pay_cycle,'01',365,'02',52,'03',12,'04',4,'05',1,'06',2,1))) CASH_AMT
               from FIN_RATE_INFO t1
               left join FIN_CASH_PLAN t2 on t1.CASH_ID=t2.cash_id
               left join FIN_CASH_PAY_RULE t3 on t1.CASH_ID=t3.cash_id
              where t1.FINPROD_ID=v_asset_code
                and t1.INT_TYPE='01'
                and t2.FINPROD_ID=v_asset_code
                and to_char(t2.PAY_DATE,'yyyymmdd')>p_base_date
                and t3.FINPROD_ID=v_asset_code
                --and to_char(t3.EFF_DATE,'yyyymmdd')=p_base_date --sunhui20191227去除现金流计算规则表里日期判断
              order by CASH_AMT);
          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.ERROR(v_log_head, '利率互换['||v_asset_code||']的固定端未来现金流未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          --剩余年限
          BEGIN
            SELECT fwdey BULK COLLECT INTO v_irs_fwdey from
            (select to_char((t2.PAY_DATE-to_date(p_base_date,'yyyymmdd'))/365) fwdey
               from FIN_RATE_INFO t1
               left join FIN_CASH_PLAN t2 on t1.CASH_ID=t2.cash_id
              where t1.FINPROD_ID=v_asset_code
                and t1.INT_TYPE='01'
                and t2.FINPROD_ID=v_asset_code
                and to_char(t2.PAY_DATE,'yyyymmdd')>p_base_date
              order by fwdey);
          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.ERROR(v_log_head, '利率互换['||v_asset_code||']的固定端剩余年限未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          --付息频率
          BEGIN
            select decode(pay_cycle,'01',365,'02',52,'03',12,'04',4,'05',1,'06',2,1) into v_irs_payfreq
              from FIN_RATE_INFO t1
              left join FIN_CASH_PAY_RULE t2 on t1.CASH_ID=t2.cash_id
             where t1.FINPROD_ID=v_asset_code
               and t1.INT_TYPE='01'
               and t2.FINPROD_ID=v_asset_code;
          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.ERROR(v_log_head, '利率互换['||v_asset_code||']的固定端付息频率未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          v_irs_fidur := func_get_fiduration_bycf(v_irs_ytm,v_irs_fwdcf,v_irs_fwdey,v_irs_payfreq);
          IF v_irs_fidur IS NULL THEN
            PKG_LOG.WARN(v_log_head, '利率互换['||v_asset_code||']的固定端久期未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END IF;
        --ELSE --浮动
          --到期收益率
          BEGIN
            select t3.YIELD into v_irs_ytm
              from FIN_RATE_INFO t1--现金流利率信息表
              left join FIN_CASH_PLAN t2--现金流计划表
                on t1.cash_id = t2.cash_id--现金流代码
               and to_char(t1.eff_date,'yyyymmdd') = to_char(t2.VDATE,'yyyymmdd')--生效日（调整后计算开始日）
              left join FIN_CASH_CALC_RULE t3--现金流计算规则表
                on t1.cash_id = t2.cash_id--现金流代码
               and to_char(t1.eff_date,'yyyymmdd') = to_char(t3.EFF_DATE,'yyyymmdd')--生效日（调整后计算开始日）
             where t1.FINPROD_ID = v_asset_code--金融产品代码（资产代码）
               and t1.INT_TYPE = '02'--浮动
               and t2.FINPROD_ID = v_asset_code--金融产品代码（资产代码）
               and to_char(t2.VDATE, 'yyyymmdd') <= p_base_date--调整后计算开始日
               and t3.FINPROD_ID = v_asset_code;--金融产品代码（资产代码）
          EXCEPTION WHEN NO_DATA_FOUND THEN
            v_irs_ytm := NULL;
            PKG_LOG.ERROR(v_log_head, '利率互换['||v_asset_code||']的浮动端到期收益率未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          --未来现金流
          BEGIN
            SELECT CASH_AMT BULK COLLECT INTO v_irs_fwdcf from
            (select to_char(v_irs_prin*v_irs_ytm/(decode(pay_cycle,'01',365,'02',52,'03',12,'04',4,'05',1,'06',2,1))) CASH_AMT
               from FIN_RATE_INFO t1
               left join FIN_CASH_PLAN t2 on t1.CASH_ID=t2.cash_id
               left join FIN_CASH_PAY_RULE t3 on t1.CASH_ID=t3.cash_id
              where t1.FINPROD_ID=v_asset_code
                and t1.INT_TYPE='02'
                and t2.FINPROD_ID=v_asset_code
                and to_char(t2.PAY_DATE,'yyyymmdd')>p_base_date
                and t3.FINPROD_ID=v_asset_code
                --and to_char(t3.EFF_DATE,'yyyymmdd')=p_base_date --sunhui20191227去除现金流计算规则表里日期判断
              order by CASH_AMT);
          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.ERROR(v_log_head, '利率互换['||v_asset_code||']的浮动端未来现金流未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          --剩余年限
          BEGIN
            SELECT fwdey BULK COLLECT INTO v_irs_fwdey from
            (select to_char((t2.PAY_DATE-to_date(p_base_date,'yyyymmdd'))/365) fwdey
               from FIN_RATE_INFO t1
               left join FIN_CASH_PLAN t2 on t1.CASH_ID=t2.cash_id
              where t1.FINPROD_ID=v_asset_code
                and t1.INT_TYPE='02'
                and t2.FINPROD_ID=v_asset_code
                and to_char(t2.PAY_DATE,'yyyymmdd')>p_base_date
              order by fwdey);
          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.ERROR(v_log_head, '利率互换['||v_asset_code||']的浮动端剩余年限未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          --付息频率
          BEGIN
            select decode(pay_cycle,'01',365,'02',52,'03',12,'04',4,'05',1,'06',2,1) into v_irs_payfreq
              from FIN_RATE_INFO t1
              left join FIN_CASH_PAY_RULE t2 on t1.CASH_ID=t2.cash_id
             where t1.FINPROD_ID=v_asset_code
               and t1.INT_TYPE='02'
               and t2.FINPROD_ID=v_asset_code;
          EXCEPTION WHEN NO_DATA_FOUND THEN
            PKG_LOG.ERROR(v_log_head, '利率互换['||v_asset_code||']的浮动端付息频率未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END;

          v_irs_fldur := func_get_flduration_bycf(v_irs_ytm,v_irs_fwdcf,v_irs_fwdey,v_irs_payfreq);
          IF v_irs_fldur IS NULL THEN
            PKG_LOG.WARN(v_log_head, '利率互换['||v_asset_code||']的浮动端久期未取到！',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END IF;
        --END IF;

        IF v_int_type = '01' THEN --固定
          v_bond_dur := v_irs_fidur(2) - v_irs_fldur(2);
        ELSE --浮动
          v_bond_dur := v_irs_fldur(2) - v_irs_fidur(2);
        END IF;

        --a、根据因子代码取情景变化
        v_curve_data := PKG_STRESS_TEST_COMM.FUNC_GET_CURVE_DATA(v_ftr_id, p_start_date, p_end_date, v_re_period);

        IF v_curve_data IS NOT NULL AND v_curve_data.COUNT >= 1 THEN
          v_ftr_value := PKG_STRESS_TEST_COMM.FUNC_GET_MAXDRAWDOWN(v_curve_data(1), '2');
        END IF;
        IF v_ftr_value IS NULL THEN
          IF p_de_flag = '01' THEN
            v_ftr_value := 0;
            PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为0。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          ELSE
            PKG_LOG.WARN(v_log_head, '风险因子['||v_ftr_id||']的变化未取到，根据用户选择置该因子变化为null。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
          END IF;
        END IF;

        --b、计算情景价格
        v_scene_price := v_curr_price * (1 - v_bond_dur * v_ftr_value);
--------------------------------------------------------------------------------------------------------------
      ELSIF p_asset_type='06' THEN --其它类型资产
        v_scene_price := v_curr_price;
        PKG_LOG.INFO(v_log_head, '其它类型资产，情景价格取当前价格。',TO_CLOB('{"产品代码":"'||v_prod_code||'","资产代码":"'||v_asset_code||'"}'));
      END IF;
--------------------------------------------------------------------------------------------------------------
--承压指标
----情景价格
insert into STT_STRESS_INDDATA
  (SEQ_NO,TEST_ID,OBJECT_ID,ELEMENT_ID,SCENE_ID,IND_ID,VALUE_STRUCT,VALUE,
   VALUE_TYPE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values
  (SEQ_STT_STRESS_INDDATA.Nextval,p_test_id,v_prod_code,v_asset_code,p_scene_id,'10021','01',
   v_scene_price,'01','PKG_MR_STRESS_TEST','system',systimestamp,'PKG_MR_STRESS_TEST',systimestamp);
----价格变动（情景价格-当前价格）
insert into STT_STRESS_INDDATA
  (SEQ_NO,TEST_ID,OBJECT_ID,ELEMENT_ID,SCENE_ID,IND_ID,VALUE_STRUCT,VALUE,
   VALUE_TYPE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values
  (SEQ_STT_STRESS_INDDATA.Nextval,p_test_id,v_prod_code,v_asset_code,p_scene_id,'10022','01',
   v_scene_price - v_curr_price,
   '01','PKG_MR_STRESS_TEST','system',systimestamp,'PKG_MR_STRESS_TEST',systimestamp);
----情景市值（情景价格*证券数量）
IF p_asset_type='05' OR p_asset_type='0402' THEN --利率互换或外汇远期、掉期
  insert into STT_STRESS_INDDATA
    (SEQ_NO,TEST_ID,OBJECT_ID,ELEMENT_ID,SCENE_ID,IND_ID,VALUE_STRUCT,VALUE,
     VALUE_TYPE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
  values
    (SEQ_STT_STRESS_INDDATA.Nextval,p_test_id,v_prod_code,v_asset_code,p_scene_id,'10023','01', v_scene_price,
     '01','PKG_MR_STRESS_TEST','system',systimestamp,'PKG_MR_STRESS_TEST',systimestamp);
ELSE
  insert into STT_STRESS_INDDATA
    (SEQ_NO,TEST_ID,OBJECT_ID,ELEMENT_ID,SCENE_ID,IND_ID,VALUE_STRUCT,VALUE,
     VALUE_TYPE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
  values
    (SEQ_STT_STRESS_INDDATA.Nextval,p_test_id,v_prod_code,v_asset_code,p_scene_id,'10023','01', v_scene_price * v_asset_shamt,
     '01','PKG_MR_STRESS_TEST','system',systimestamp,'PKG_MR_STRESS_TEST',systimestamp);
END IF;
----损失金额（情景市值-当前市值）
IF p_asset_type='05' OR p_asset_type='0402' THEN --利率互换或外汇远期、掉期
  insert into STT_STRESS_INDDATA
    (SEQ_NO,TEST_ID,OBJECT_ID,ELEMENT_ID,SCENE_ID,IND_ID,VALUE_STRUCT,VALUE,
     VALUE_TYPE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
  values
    (SEQ_STT_STRESS_INDDATA.Nextval,p_test_id,v_prod_code,v_asset_code,p_scene_id,'10027','01',
     v_scene_price - v_market_val,
     '01','PKG_MR_STRESS_TEST','system',systimestamp,'PKG_MR_STRESS_TEST',systimestamp);
ELSE
  insert into STT_STRESS_INDDATA
    (SEQ_NO,TEST_ID,OBJECT_ID,ELEMENT_ID,SCENE_ID,IND_ID,VALUE_STRUCT,VALUE,
     VALUE_TYPE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
  values
    (SEQ_STT_STRESS_INDDATA.Nextval,p_test_id,v_prod_code,v_asset_code,p_scene_id,'10027','01',
     (v_scene_price * v_asset_shamt) - v_market_val,
     '01','PKG_MR_STRESS_TEST','system',systimestamp,'PKG_MR_STRESS_TEST',systimestamp);
END IF;
--------------------------------------------------------------------------------------------------------------
--产品指标
----持仓数量
 insert into STT_STRESS_INDDATA --压测指标值表
   (SEQ_NO,TEST_ID,OBJECT_ID,ELEMENT_ID,SCENE_ID,IND_ID,VALUE_STRUCT,
    VALUE,VALUE_TYPE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values
   (SEQ_STT_STRESS_INDDATA.Nextval,p_test_id,v_prod_code,v_asset_code,p_scene_id,'10016','01',
    v_asset_shamt,'01','PKG_MR_STRESS_TEST','system',systimestamp,'PKG_MR_STRESS_TEST',systimestamp);
----当前价格
 insert into STT_STRESS_INDDATA --压测指标值表
   (SEQ_NO,TEST_ID,OBJECT_ID,ELEMENT_ID,SCENE_ID,IND_ID,VALUE_STRUCT,
    VALUE,VALUE_TYPE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values
   (SEQ_STT_STRESS_INDDATA.Nextval,p_test_id,v_prod_code,v_asset_code,p_scene_id,'10017','01',
    v_curr_price,'01','PKG_MR_STRESS_TEST','system',systimestamp,'PKG_MR_STRESS_TEST',systimestamp);
----持仓市值
 insert into STT_STRESS_INDDATA --压测指标值表
   (SEQ_NO,TEST_ID,OBJECT_ID,ELEMENT_ID,SCENE_ID,IND_ID,VALUE_STRUCT,
    VALUE,VALUE_TYPE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values
   (SEQ_STT_STRESS_INDDATA.Nextval,p_test_id,v_prod_code,v_asset_code,p_scene_id,'10018','01',
    v_market_val,'01','PKG_MR_STRESS_TEST','system',systimestamp,'PKG_MR_STRESS_TEST',systimestamp);

    END LOOP;
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.put_line('this error message is from procedure PKG_MR_STRESS_TEST.P_SAVE_CALC_RESULT.');
      DBMS_OUTPUT.put_line ('SQLCODE: ' || SQLCODE);
      DBMS_OUTPUT.put_line ('SQLERRM: ' || SQLERRM);
      PKG_LOG.ERROR(v_log_head, '市场风险压力测试计算时出错', DBMS_UTILITY.format_error_backtrace, SQLCODE, SQLERRM);
      RAISE_APPLICATION_ERROR(-20001, SQLERRM);
  END P_SAVE_CALC_RESULT;
END PKG_MR_STRESS_TEST;
/