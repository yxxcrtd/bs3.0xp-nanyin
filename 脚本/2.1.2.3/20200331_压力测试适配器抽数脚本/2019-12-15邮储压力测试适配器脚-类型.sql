--------------------------------------------------------
--  文件已创建 - 星期五-五月-01-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type ASSETS_TYPE_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "ASSETS_TYPE_OBJ" IS OBJECT (
  type_1    VARCHAR2(50),
  type_2    VARCHAR2(50),
  type_3    VARCHAR2(50),
  grade     VARCHAR2(50),
  factors   FACTOR_DATE_TABLE
);

/
--------------------------------------------------------
--  DDL for Type ASSETS_TYPE_TABLE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "ASSETS_TYPE_TABLE" IS TABLE OF ASSETS_TYPE_OBJ;

/
--------------------------------------------------------
--  DDL for Type FACTOR_DATE_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "FACTOR_DATE_OBJ" IS OBJECT (
	factor_id      VARCHAR2(100),
	start_date     DATE,
	end_date       DATE,
	days           NUMBER(14,6),
	max_drawndown  NUMBER(30,14)
);

/
--------------------------------------------------------
--  DDL for Type FACTOR_DATE_TABLE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "FACTOR_DATE_TABLE" IS TABLE OF FACTOR_DATE_OBJ;

/
--------------------------------------------------------
--  DDL for Type FACTOR_MAX_DRAWDOWN_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "FACTOR_MAX_DRAWDOWN_OBJ" IS OBJECT (
    factor_id              VARCHAR2(32),
    ftr_name               VARCHAR2(200),
    ftr_value_struct       VARCHAR2(50),
    ftr_value_struct_name  VARCHAR2(200),
    factor_type            VARCHAR2(100),
    factor_type2           VARCHAR2(100),
    factor_type_name       VARCHAR2(200),
    factor_type_name2      VARCHAR2(200),
    start_date             DATE,
    end_date               DATE,
    days                   NUMBER(14,6),
    max_drawndown          NUMBER(30,14)
)

/
--------------------------------------------------------
--  DDL for Type FACTOR_MAX_DRAWDOWN_TABLE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "FACTOR_MAX_DRAWDOWN_TABLE" IS TABLE OF FACTOR_MAX_DRAWDOWN_OBJ

/
--------------------------------------------------------
--  DDL for Type FACTOR_VALUE_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "FACTOR_VALUE_OBJ" IS OBJECT (
	  fac_id      VARCHAR2(32),
	  start_date    date,
	  end_date     date
	)

/
--------------------------------------------------------
--  DDL for Type FACTOR_VALUE_TABLE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "FACTOR_VALUE_TABLE" IS TABLE OF FACTOR_VALUE_OBJ

/
--------------------------------------------------------
--  DDL for Type INDICATOR_VALUE_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "INDICATOR_VALUE_OBJ" IS OBJECT (
  test_id      VARCHAR2(32),
  object_id    VARCHAR2(32),
  ind_name     VARCHAR2(64),
  ind_value    NUMBER(30,14),
  value_type   VARCHAR2(6) --normal, light, medium, heavy
)

/
--------------------------------------------------------
--  DDL for Type INDICATOR_VALUE_TABLE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "INDICATOR_VALUE_TABLE" IS TABLE OF INDICATOR_VALUE_OBJ

/
--------------------------------------------------------
--  DDL for Type LOG_BODY_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "LOG_BODY_OBJ" IS OBJECT(
  LOG_INFO          VARCHAR2(4000),     --日志信息，最大长度4000
  LOG_DETAIL_INFO   CLOB,               --日志明细信息（如具体的SQL代码段，查询结果数据），CLOB类型，长度不限量
  SCRIPT_LINE       NUMBER(9),          --写入日志语句在SQL脚本中的代码行数
  SQL_CODE          VARCHAR2(32),       --数据库错误代码
  SQL_ERRM          VARCHAR2(2000)      --数据库错误消息
)

/
--------------------------------------------------------
--  DDL for Type LOG_HEAD_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "LOG_HEAD_OBJ" IS OBJECT(
  --日志消息头部信息（通常一个存储过程、函数中的所有日志的头部信息都相同）
  SCRIPT_NAME       VARCHAR2(100),  --package.存储过程,package.函数
  ANCESTORS_NAME    VARCHAR2(640),  --祖宗们的名字（太爷爷、爷爷、爸爸调用层级关系）
  PAGE_ID           VARCHAR2(50),   --页面ID
  ELEMENT_ID        VARCHAR2(50),   --页面元素ID
  SCRIPT_PARAMS     VARCHAR2(4000), --存储过程或函数的参数，建议json格式，最大长度4000，若参数超出4000，那么放入LOG_BODY_RECORD.log_detail_info中
  START_TIME        TIMESTAMP(6),   --存储过程或函数的执行开始时间
  CREATE_USER       VARCHAR2(20),   --登录WEB系统的用户ID
  CREATE_DEPT       VARCHAR2(32)    --登录WEB系统的用户所在部门
)

/
--------------------------------------------------------
--  DDL for Type OBJECT_ID_ARRAY
--------------------------------------------------------

  CREATE OR REPLACE TYPE "OBJECT_ID_ARRAY" IS TABLE OF VARCHAR2(32)

/
--------------------------------------------------------
--  DDL for Type PROD_CCSL_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "PROD_CCSL_OBJ" IS OBJECT (
    prod_id           VARCHAR2(32),
    prod_name         VARCHAR2(200),
    ccsl              NUMBER(30,14)
)

/
--------------------------------------------------------
--  DDL for Type PROD_CCSL_TABLE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "PROD_CCSL_TABLE" IS TABLE OF PROD_CCSL_OBJ

/
--------------------------------------------------------
--  DDL for Type STRESS_ELEMENT_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "STRESS_ELEMENT_OBJ" IS OBJECT (
  base_date           DATE,
  analysis_vdate      DATE,
  analysis_mdate      DATE,
  object_id           VARCHAR2(32),
  element_id          VARCHAR2(50),
  ccsl                NUMBER(30,14),
  cdate               DATE,
  rowno               INTEGER
)

/
--------------------------------------------------------
--  DDL for Type STRESS_ELEMENT_TABLE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "STRESS_ELEMENT_TABLE" IS TABLE OF STRESS_ELEMENT_OBJ

/
--------------------------------------------------------
--  DDL for Type TBL_STAT_OBJ
--------------------------------------------------------

  CREATE OR REPLACE TYPE "TBL_STAT_OBJ" IS OBJECT (
  TABLE_NAME      VARCHAR2(256),
  ROWS_COUNT      INTEGER
)

/
--------------------------------------------------------
--  DDL for Type TBL_STAT_TABLE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "TBL_STAT_TABLE" IS TABLE OF TBL_STAT_OBJ

/
--------------------------------------------------------
--  DDL for Type TYPE_ARRAY
--------------------------------------------------------

  CREATE OR REPLACE TYPE "TYPE_ARRAY" is varray(128) of varchar2(32);

/
--------------------------------------------------------
--  DDL for Type TYPE_ARRAY_INT
--------------------------------------------------------

  CREATE OR REPLACE TYPE "TYPE_ARRAY_INT" is varray(128) of integer;

/
