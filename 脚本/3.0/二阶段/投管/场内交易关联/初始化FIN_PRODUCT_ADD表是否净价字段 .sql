UPDATE FIN_PRODUCT_ADD 
SET IS_NETPRICE = 
CASE when FINPROD_ID IN (
SELECT FINPROD_ID FROM 
(SELECT SUBSTR(FINPROD_MARKET_ID ,1,LENGTH(FINPROD_MARKET_ID)-3) AS stockCode,fpa.* FROM FIN_PRODUCT fp LEFT JOIN FIN_PRODUCT_ADD fpa ON fp.FINPROD_ID = FPA .FINPROD_ID
WHERE               fp.FINPROD_TYPE2 ='F01' AND fp.TRADE_MARKET ='03') 
WHERE                  ('128000' <= stockCode and stockCode <= '128999')
                    or ('131000' <= stockCode AND stockCode <= '131999')
                    or ('142000' <= stockCode AND stockCode <= '142999')
                    or ('146000' <= stockCode AND stockCode <= '146999')
                    or ('149000' <= stockCode AND stockCode <= '149999')
                    or ('156000' <= stockCode AND stockCode <= '156999')
                    or ('165000' <= stockCode AND stockCode <= '165999')
                    or ('168000' <= stockCode AND stockCode <= '169999')
                    or ('123500' <= stockCode AND stockCode <= '123999')
                    or ('159000' <= stockCode AND stockCode <= '159999')              
union
SELECT FINPROD_ID FROM 
(SELECT SUBSTR(FINPROD_MARKET_ID ,1,LENGTH(FINPROD_MARKET_ID)-3) AS stockCode,fpa.* FROM FIN_PRODUCT fp LEFT JOIN FIN_PRODUCT_ADD fpa ON fp.FINPROD_ID = FPA .FINPROD_ID
WHERE fp.FINPROD_TYPE2 ='F01' AND fp.TRADE_MARKET ='04') 
WHERE                  ('114000' <= stockCode AND stockCode <= '114999')
                    or ('115100' <= stockCode AND stockCode <= '115199')
                    or ('117000' <= stockCode AND stockCode <= '117499')
                    or ('117500' <= stockCode AND stockCode <= '117999')
                    or ('118000' <= stockCode AND stockCode <= '118899')
                    or ('118900' <= stockCode AND stockCode <= '118999')
                    or ('121000' <= stockCode AND stockCode <= '121499')
                    or ('133000' <= stockCode AND stockCode <= '134999')
                    or ('135000' <= stockCode AND stockCode <= '137999')
                    or ('121500' <= stockCode AND stockCode <= '121999')
                    or ('138000' <= stockCode AND stockCode <= '139999')
                    or ('119000' <= stockCode AND stockCode <= '119999')
                    or ('116500' <= stockCode AND stockCode <= '116999')
                    or ('116000' <= stockCode AND stockCode <= '116499')
                    or ('113000' <= stockCode AND stockCode <= '113999')
                    or ('120000' <= stockCode AND stockCode <= '122999')
                    or ('124000' <= stockCode AND stockCode <= '124999')
                    or ('128000' <= stockCode AND stockCode <= '128899')
                    or ('127000' <= stockCode AND stockCode <= '127999')
                    or ('123000' <= stockCode AND stockCode <= '123999')
                    or ('115200' <= stockCode AND stockCode <= '115999')
                    or ('115000' <= stockCode AND stockCode <= '115099')) THEN 'Y'
WHEN FINPROD_ID   NOT LIKE 'F01%' THEN NULL 
ELSE  'N' 
END;
/