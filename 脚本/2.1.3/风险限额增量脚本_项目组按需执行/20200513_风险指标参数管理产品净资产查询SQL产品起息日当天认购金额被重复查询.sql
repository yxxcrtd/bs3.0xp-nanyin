/**产品净资产查询SQL产品起息日当天认购金额被重复查询 **/
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE = 'CPJZCJZ' and PARA_GRANULARITY='04';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('CPJZCJZ', '04', 'SQL', 'select sum(amount) AS amount from      
(     
      select sum(amount) as amount      
   from     
   (      
     SELECT       
       DISTINCT t2.PORTFOLIO_ID ,     
       mujiData.mujiAmt  as AMOUNT      
       from  PTL_PORTFOLIO t2     
       left join      
       (      
       select t1.PORTFOLIO_ID, sum(t1.TRADE_AMT) as mujiAmt from TRD_FIN_PRODUCT_DEAL_TOTAL t1      
       WHERE  t1.PS in (''C1'', ''C2'')   and t1.SETTLE_DATE <= date''${valDate}''  and t1.TRADE_STATUS=''01''  
       group by t1.PORTFOLIO_ID     
       ) mujiData     
       on t2.PORTFOLIO_ID = mujiData.PORTFOLIO_ID     
       where  t2.VDATE=date''${valDate}'' and t2.PORTFOLIO_ID in ${portfolioCode}     
   UNION ALL      
      SELECT      
       DISTINCT t1.PORTFOLIO_ID RKEY,     
        nvl(TDY_ASSET_NETVALUE,0) as RVALUE     
       from PTL_PORTFOLIO_VALUATION t1      
       where  t1.CDATE=date''${valDate}''-1 and t1.PORTFOLIO_ID in ${portfolioCode}     
   )      
    union all   
    select    
     nvl(sum(decode(n.PS,''C1'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  +nvl(sum(decode(n.PS,''C2'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  +nvl(sum(decode(n.PS,''C3'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  -nvl(sum(decode(n.PS,''C4'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  -nvl(sum(decode(n.PS,''C5'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  -nvl(sum(decode(n.PS,''C6'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  -nvl(sum(decode(n.PS,''C7'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
  -nvl(sum(decode(n.PS,''C9'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
  -nvl(sum(decode(n.PS,''C11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C12'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C13'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C14'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C15'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C16'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C17'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C18'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as amount  
    from TRD_PRODUCT_DEAL_ADD m   
    inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
    left join PTL_PORTFOLIO t2  on t2.portfolio_id =  m.portfolio_id     
    where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' and  t2.VDATE<date''${valDate}'' group by m.PORTFOLIO_ID       
 ', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
---------过长截断
 update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' union all    
    select    
     nvl(sum(decode(n.PS,''C1'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  +nvl(sum(decode(n.PS,''C2'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  +nvl(sum(decode(n.PS,''C3'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  -nvl(sum(decode(n.PS,''C4'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  -nvl(sum(decode(n.PS,''C5'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  -nvl(sum(decode(n.PS,''C6'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  -nvl(sum(decode(n.PS,''C7'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
  -nvl(sum(decode(n.PS,''C9'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
  -nvl(sum(decode(n.PS,''C11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C12'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C13'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C14'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C15'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C16'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C17'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C18'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  
  -nvl(sum(decode(n.PS,''C19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as amount  
    from WFL_TRD_PRODUCT_DEAL_ADD m   
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''   
    where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' group by m.PORTFOLIO_ID   
)'  where PARA_CODE = 'CPJZCJZ' and PARA_GRANULARITY='04';