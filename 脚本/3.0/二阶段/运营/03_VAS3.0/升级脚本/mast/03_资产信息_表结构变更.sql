 CREATE TABLE "OPT_TARGET_INFO" 
   ("TARGET_ID" VARCHAR(255), 
	"TARGET_NAME" VARCHAR(255), 
	"TARGET_TYPE" VARCHAR(100), 
	"REMARK" VARCHAR(255), 
	"CREATE_USER" VARCHAR(20), 
	"CREATE_DEPT" VARCHAR(10), 
	"CREATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR(20), 
	"UPDATE_TIME" TIMESTAMP (6),
	 CONSTRAINT "PK_OPT_TARGET_INFO_ID" PRIMARY KEY ("TARGET_ID")
   );
COMMENT ON COLUMN "OPT_TARGET_INFO"."TARGET_ID" IS '标的代码';
COMMENT ON COLUMN "OPT_TARGET_INFO"."TARGET_NAME" IS '标的名称';
COMMENT ON COLUMN "OPT_TARGET_INFO"."TARGET_TYPE" IS '标的类型';
COMMENT ON COLUMN "OPT_TARGET_INFO"."REMARK" IS '备注';
COMMENT ON COLUMN "OPT_TARGET_INFO"."CREATE_USER" IS '创建人';
COMMENT ON COLUMN "OPT_TARGET_INFO"."CREATE_DEPT" IS '创建部门';
COMMENT ON COLUMN "OPT_TARGET_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "OPT_TARGET_INFO"."UPDATE_USER" IS '更新人';
COMMENT ON COLUMN "OPT_TARGET_INFO"."UPDATE_TIME" IS '更新时间';
COMMENT ON TABLE "OPT_TARGET_INFO"  IS '期权标的信息表';
 
----------------标的类金融产品附属表：新增字段【数据来源】 start--------------------------
alter table FIN_PRODUCT_ADD add DATA_SOURCE VARCHAR2(32);
comment on column FIN_PRODUCT_ADD.DATA_SOURCE
  is '数据来源';
----------------标的类金融产品附属表：新增字段【数据来源】 end--------------------------

--------------------------------标的类金融产品：新增字段【上市流通代码】 start--------------------------------
alter table FIN_PRODUCT add CIRCULATION_CODE VARCHAR2(50);
comment on column FIN_PRODUCT.CIRCULATION_CODE
  is '上市流通代码';
--------------------------------标的类金融产品：新增字段【上市流通代码】 end--------------------------------

/** 更改金融产品转债信息表转股起始日为可空字段**/
alter table FIN_PRODUCT_CONVERTIBLE modify CON_VDATE  null;
alter table FIN_PRODUCT_CONVERTIBLE modify CON_SHARE_ID  null;
alter table FIN_PRODUCT_CONVERTIBLE modify RESALE_PRICE  null;
alter table FIN_PRODUCT_CONVERTIBLE modify REDEEM_PRICE  null;


/** ----------债券核算分类映射表 添加字段 用于区分收益类型 add by lby on 20210311 start ---------- **/
-- Add/modify columns 
alter table FIN_PRODUCT_CLASS add profit_type VARCHAR2(50);
alter table FIN_PRODUCT_CLASS add profit_type_name VARCHAR2(200);
-- Add comments to the columns 
comment on column FIN_PRODUCT_CLASS.profit_type
  is '收益类型';
comment on column FIN_PRODUCT_CLASS.profit_type_name
  is '收益类型名称';
/** ----------债券核算分类映射表 添加字段 用于区分收益类型 add by lby on 20210311 end ---------- **/


--金融产品分类表字段映射表
---新增字段：场内基金类型、场内基金类型名称
alter table FIN_PRODUCT_CLASS add trm_fund_type VARCHAR2(50);
alter table FIN_PRODUCT_CLASS add trm_fund_type_name VARCHAR2(200);
comment on column FIN_PRODUCT_CLASS.trm_fund_type
  is '场内基金类型';
comment on column FIN_PRODUCT_CLASS.trm_fund_type_name
  is '场内基金类型名称';


 
