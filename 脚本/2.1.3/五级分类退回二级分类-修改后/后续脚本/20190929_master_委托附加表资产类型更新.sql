UPDATE
    ETR_ENTRUST_ADD T
SET
    (
        T.ASSET_TYPE1 ,
        T.ASSET_TYPE_10 ,
        T.ASSET_TYPE_11
    )
    =
    (
        SELECT
            T1.type_9,
            T1.type_10,
            T1.type_11
        FROM
            FIN_PRODUCT_TYPE T1,
            ETR_ENTRUST T2
        WHERE
            T1.FINPROD_ID = T2.FINPROD_ID
        AND T2.ENTRUST_ID = T.ENTRUST_ID);