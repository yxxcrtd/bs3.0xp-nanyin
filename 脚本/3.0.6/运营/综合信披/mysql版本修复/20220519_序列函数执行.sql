-- 函数
DROP FUNCTION IF EXISTS  `_NEXTINDEX`;

CREATE FUNCTION `_NEXTINDEX`(n varchar(32)) RETURNS int
    NO SQL
BEGIN
    DECLARE b_num INT;
    DECLARE incr_num INT;
    SET b_num = (SELECT begin_num FROM SYS_SEQUENCE WHERE name = n);
    SET incr_num = (SELECT incr FROM SYS_SEQUENCE WHERE name = n);
    UPDATE SYS_SEQUENCE SET begin_num = (b_num + incr_num) WHERE name = n ;
    RETURN b_num + incr_num;
END ;