-- sys_sequence
DELETE FROM sys_sequence WHERE = 'seq_equity_info';
INSERT INTO sys_sequence (NAME, BEGIN_NUM, INCR) VALUES ('seq_equity_info', 1, 1);