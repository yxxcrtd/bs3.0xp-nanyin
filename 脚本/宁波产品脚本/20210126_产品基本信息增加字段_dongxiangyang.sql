ALTER  TABLE   chb_prd_baseinfo  ADD    IS_TRANSFER  VARCHAR2(2);
ALTER  TABLE   chb_prd_baseinfo  ADD    IS_FHXGCP  VARCHAR2(2);
ALTER  TABLE   chb_prd_baseinfo  ADD    IS_YLLCCP  VARCHAR2(2);

COMMENT ON COLUMN chb_prd_baseinfo.IS_TRANSFER  IS '是否母行划转产品';
COMMENT ON COLUMN chb_prd_baseinfo.IS_FHXGCP  IS '是否合规新产品';
COMMENT ON COLUMN chb_prd_baseinfo.IS_YLLCCP  IS '是否养老理财产品';