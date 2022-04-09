-- 更新科目定义表信息
ALTER TABLE bok_subject ADD subject_internal_code VARCHAR(32) COMMENT '内部科目编码(暂时不用)';
ALTER TABLE bok_subject ADD subject_hand_fel_code VARCHAR(200) COMMENT '辅助核算表达式(暂时不用)';
ALTER TABLE bok_subject ADD ccy VARCHAR(50) COMMENT '币种(暂时不用)';





