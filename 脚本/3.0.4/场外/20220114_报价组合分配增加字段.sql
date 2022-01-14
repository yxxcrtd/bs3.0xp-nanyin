alter table IBU_QUOTE_ETR_RE add (share_ct_amt NUMBER(30,2));
COMMENT ON COLUMN IBU_QUOTE_ETR_RE.share_ct_amt IS '折算券面金额';
alter table wfl_ibu_quote_etr_re add (share_ct_amt NUMBER(30,2));
COMMENT ON COLUMN wfl_ibu_quote_etr_re.share_ct_amt IS '折算券面金额';