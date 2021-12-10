alter table etr_entrust_add add transmit_Time TIMESTAMP;
COMMENT ON COLUMN etr_entrust_add.transmit_Time IS '指令下达时间';