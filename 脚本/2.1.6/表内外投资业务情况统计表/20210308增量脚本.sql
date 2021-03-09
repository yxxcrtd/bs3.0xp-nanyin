alter table chb_prd_rate add fprod_id 　VARCHAR2(50);
COMMENT ON COLUMN chb_prd_rate.fprod_id IS '母产品代码';
alter table chb_prd_rate add is_lay 　VARCHAR2(50);
COMMENT ON COLUMN chb_prd_rate.is_lay IS '是否分层';