package com.joyintech.tams.valuation.common.interf.transdata.pabhq.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.tams.common.base.entity.ValInterfTemplate;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.CBondAnalysisCNBD;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.CBondBenchmark;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.CBondEODPrices;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.CMoneyMarketFincome;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.Cbondanalysiscsi;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.Cbondanalysisshc;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.Cbondprices;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.Cbondpricesnet;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.ChinaMutualFundNAV;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.Chinaclosedfundeodprice;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.ValPayhsrcsecoption;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.ShiborPrices;

public class PabDbUtil{
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PabDbUtil.class);
    
	/***
	 * 读取行情数据...
	 */
	private static String querySQL(String tmpEName,Date cdate){
		String cdatestr = new SimpleDateFormat("yyyyMMdd").format(cdate);
		String SQL_= null;
		if(PabHqContans.TMP_E_NAME_中国债券交易所债券行情.equals(tmpEName)){
			SQL_="SELECT OBJECT_ID,S_INFO_WINDCODE,TRADE_DT,CRNCY_CODE,"
					+ "S_DQ_PRECLOSE,S_DQ_OPEN,S_DQ_HIGH,S_DQ_LOW,"
					+ "S_DQ_CLOSE,S_DQ_CHANGE,S_DQ_PCTCHANGE,"
					+ "S_DQ_VOLUME,S_DQ_AMOUNT,S_DQ_AVGPRICE,S_DQ_TRADESTATUS, "
					+ "OPDATE,OPMODE "
					+ "FROM CBondEODPrices "
					+ "WHERE TRADE_DT = '"+cdatestr+"'";
		}
//		else if(PabHqContans.TMP_E_NAME_货币基金每天净值收益.equals(tmpEName)){
//			SQL_ = "SELECT OBJECT_ID,S_INFO_WINDCODE,F_INFO_BGNDATE,F_INFO_ENDDATE, "
//					+ "F_INFO_UNITYIELD, F_ACCUNITNAV,F_INCOME_PER_MILLION, "
//					+ "F_INFO_YEARLYROE, ANN_DATE,"
//					+ "OPDATE,OPMODE "
//					+ "FROM CMoneyMarketDailyFIncome "
//					+ "WHERE ANN_DATE = '"+cdatestr+"' ";
//		}
		else if(PabHqContans.TMP_E_NAME_中国共同基金净值.equals(tmpEName)){
			SQL_ = "SELECT OBJECT_ID, F_INFO_WINDCODE,ANN_DATE, PRICE_DATE, F_NAV_UNIT, "
					+ "F_NAV_ACCUMULATED, F_NAV_DIVACCUMULATED,F_NAV_ADJFACTOR, "
					+ "CRNCY_CODE, F_PRT_NETASSET,F_ASSET_MERGEDSHARESORNOT, "
					+ "NETASSET_TOTAL,F_NAV_ADJUSTED,"
					+ "OPDATE,OPMODE "
					+ "FROM ChinaMutualFundNAV "
					+ "WHERE ANN_DATE = '"+cdatestr+"' ";
		}
		else if(PabHqContans.TMP_E_NAME_SHIRBO行情.equals(tmpEName)){
			SQL_ = "SELECT "
					+ "OBJECT_ID, S_INFO_WINDCODE,TRADE_DT,B_INFO_RATE,"
					+ "OPDATE,OPMODE "
					+ "FROM SHIBORPRICES "
					+ "WHERE TRADE_DT ='"+cdatestr+"'";
		}
		else if(PabHqContans.TMP_E_NAME_法定存贷款利率.equals(tmpEName)){
			SQL_ = "SELECT "
					+ "OBJECT_ID,TRADE_DT,B_INFO_BENCHMARK, B_INFO_RATE,"
					+ "OPDATE,OPMODE "
					+ "FROM CBONDBENCHMARK "
					+ "WHERE TRADE_DT = '"+cdatestr+"'";
		}
		else if(PabHqContans.TMP_E_NAME_中债登估值.equals(tmpEName)){
			SQL_ = "SELECT "
					+ "OBJECT_ID, S_INFO_WINDCODE,TRADE_DT,B_ANAL_MATU_CNBD,"
					+ "B_ANAL_DIRTY_CNBD, B_ANAL_ACCRINT_CNBD, B_ANAL_NET_CNBD, "
					+ "B_ANAL_YIELD_CNBD, B_ANAL_MODIDURA_CNBD, B_ANAL_CNVXTY_CNBD, "
					+ "B_ANAL_VOBP_CNBD, B_ANAL_SPRDURA_CNBD, B_ANAL_SPRCNXT_CNBD, "
					+ "B_ANAL_ACCRINTCLOSE_CNBD, B_ANAL_PRICE, B_ANAL_NETPRICE, "
					+ "B_ANAL_YIELD, B_ANAL_MODIFIEDDURATION, B_ANAL_CONVEXITY, "
					+ "B_ANAL_BPVALUE, B_ANAL_SDURATION, B_ANAL_SCNVXTY, "
					+ "B_ANAL_INTERESTDURATION_CNBD, B_ANAL_INTERESTCNVXTY_CNBD, "
					+ "B_ANAL_INTERESTDURATION, B_ANAL_INTERESTCNVXTY, "
					+ "B_ANAL_PRICE_CNBD, B_ANAL_BPYIELD, B_ANAL_EXCHANGE,"
					+ "B_ANAL_CREDIBILITY,"
					+ "OPDATE,OPMODE "
					+ "FROM CBONDANALYSISCNBD "
					+ "WHERE TRADE_DT = '"+cdatestr+"'";
		}else if(PabHqContans.TMP_E_NAME_货币基金净值收益.equals(tmpEName)){
			SQL_ = "SELECT OBJECT_ID, S_INFO_WINDCODE, F_INFO_BGNDATE,"
					+ "F_INFO_ENDDATE, F_INFO_UNITYIELD, F_INFO_YEARLYROE,"
					+ "ANN_DATE, OPDATE, OPMODE "
					+ "FROM CMONEYMARKETFINCOME "
					+ "WHERE ANN_DATE = '"+cdatestr+"' ";
		}
		/** add by dongjun on 20180921 start **/
		else if(PabHqContans.TMP_E_NAME_中国债券行情_净价.equals(tmpEName)){
            SQL_ = "SELECT"
                + " OBJECT_ID,S_INFO_WINDCODE,TRADE_DT,B_DQ_OPEN,B_DQ_HIGH,B_DQ_LOW,"
                + "B_DQ_ORIGINCLOSE,B_DQ_VOLUME,B_DQ_AMOUNT, "
                + "OPDATE,OPMODE "
                + "FROM CBONDPRICESNET "
                + "WHERE TRADE_DT = '"+cdatestr+"'";
        }
        else if(PabHqContans.TMP_E_NAME_中国债券行情_全价.equals(tmpEName)){
            SQL_ = "SELECT "
                + "OBJECT_ID,S_INFO_WINDCODE,TRADE_DT,B_DQ_OPEN,B_DQ_HIGH,B_DQ_LOW,"
                + "B_DQ_ORIGINCLOSE,B_DQ_VOLUME,B_DQ_AMOUNT, "
                + "OPDATE,OPMODE "
                + "FROM CBONDPRICES"
                + " WHERE TRADE_DT = '"+cdatestr+"'";
        }
        else if(PabHqContans.TMP_E_NAME_中证估值.equals(tmpEName)){
            SQL_ = "SELECT "
                + "OBJECT_ID,S_INFO_WINDCODE,TRADE_DT,B_INFO_TYPE,B_INFO_LISTINGMARKETNUMBERS,"
                + "B_ANAL_DIRTY_CSI,B_ANAL_YIELD_CSI,B_ANAL_MODIDURA_CSI,B_ANAL_CNVXTY_CSI,"
                + "B_ANAL_NET_CSI,B_ANAL_ACCRINT_CSI, "
                + "OPDATE,OPMODE "
                + "FROM CBONDANALYSISCSI "
                + "WHERE TRADE_DT = '"+cdatestr+"'";
        }
        else if(PabHqContans.TMP_E_NAME_中国债券上海清算所债券估值.equals(tmpEName)){
            SQL_ = "SELECT "
                + "OBJECT_ID,S_INFO_WINDCODE,TRADE_DT,B_ANAL_MATU_CNBD,B_ANAL_DIRTY_CNBD,"
                + "B_ANAL_ACCRINT_CNBD,B_ANAL_NET_CNBD,B_ANAL_YIELD_CNBD,B_ANAL_MODIDURA_CNBD,"
                + "B_ANAL_CNVXTY_CNBD,B_ANAL_VOBP_CNBD,B_ANAL_SPRDURA_CNBD,B_ANAL_SPRCNXT_CNBD,"
                + "B_ANAL_PRICE,B_ANAL_NETPRICE,B_ANAL_YIELD,B_ANAL_MODIFIEDDURATION,B_ANAL_CONVEXITY,"
                + "B_ANAL_BPVALUE,B_ANAL_SDURATION,B_ANAL_SCNVXTY,B_ANAL_INTERESTDURATION_CNBD,"
                + "B_ANAL_INTERESTCNVXTY_CNBD,B_ANAL_INTERESTDURATION,B_ANAL_INTERESTCNVXTY,"
                + "B_ANAL_PRICE_CNBD,B_ANAL_BPYIELD,B_ANAL_SURPLUSCAPITAL, "
                + "B_ANAL_EXCHANGE,B_ANAL_CREDIBILITY,OPDATE,OPMODE "
                + "FROM CBONDANALYSISSHC "
                + "WHERE TRADE_DT = '"+cdatestr+"'";
        }
        else if(PabHqContans.TMP_E_NAME_中国封闭式基金日行情.equals(tmpEName)){
            SQL_ = "SELECT "
                + "OBJECT_ID,S_INFO_WINDCODE,TRADE_DT,CRNCY_CODE,S_DQ_PRECLOSE,S_DQ_OPEN,S_DQ_HIGH,"
                + "S_DQ_LOW,S_DQ_CLOSE,S_DQ_CHANGE,S_DQ_PCTCHANGE,S_DQ_VOLUME,S_DQ_AMOUNT,"
                + "S_DQ_ADJPRECLOSE,S_DQ_ADJOPEN,S_DQ_ADJHIGH,S_DQ_ADJLOW,S_DQ_ADJCLOSE,"
                + "S_DQ_ADJFACTOR,TRADES_COUNT,DISCOUNT_RATE, "
                + "OPDATE,OPMODE "
                + "FROM CHINACLOSEDFUNDEODPRICE "
                + "WHERE TRADE_DT = '"+cdatestr+"'";
        }
		/** add by dongjun on 20180921 end **/
		/** add by dongjun on 20181011 title:债券行权计划 start**/
        else if(PabHqContans.TMP_E_NAME_债券行权计划.equals(tmpEName)){
            SQL_ = "SELECT "
                + "OBJECT_ID,SECID,OPTIONNUM,TO_CHAR(OPTIONSTARTDAY,'yyyy-MM-dd') OPTIONSTARTDAY,TO_CHAR(OPTIONENDDAY,'yyyy-MM-dd') OPTIONENDDAY,"
                + "TO_CHAR(OPTIONPAYDAY,'yyyy-MM-dd') OPTIONPAYDAY,OPTIONPRICE,OPTIONTYPE,TO_CHAR(LSTMNTDATE,'yyyy-MM-dd') LSTMNTDATE,"
                + "LSTMNTUSER,EFFECTFLAG,TO_CHAR(OPDATE,'yyyy-MM-dd') OPDATE,OPMODE "
                + "FROM PAYHSRCSECOPTION "
                + "WHERE TRUNC(OPDATE) = to_date('"+cdatestr+"','yyyy-MM-dd')";     
        }
		/** add by dongjun on 20181011 title:债券行权计划 end**/
		else{
			System.out.println("模板"+tmpEName+"目前功能不支持...");
		}
		return SQL_;
	}
	
	private static Object getResultSetData(ResultSet rs,String tmpEName){
		try {
			if(PabHqContans.TMP_E_NAME_中国债券交易所债券行情.equals(tmpEName)){
				List<CBondEODPrices> list = new ArrayList<CBondEODPrices>();
				while(rs.next()){
					CBondEODPrices obj = new CBondEODPrices();
					obj.setObjectId(rs.getString("OBJECT_ID"));
					obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
					obj.setTradeDt(rs.getString("TRADE_DT"));
					obj.setCrncyCode(rs.getString("CRNCY_CODE"));
					obj.setsDqPreclose(rs.getBigDecimal("S_DQ_PRECLOSE"));
					obj.setsDqOpen(rs.getBigDecimal("S_DQ_OPEN"));
					obj.setsDqHigh(rs.getBigDecimal("S_DQ_HIGH"));
					obj.setsDqLow(rs.getBigDecimal("S_DQ_LOW"));
					obj.setsDqClose(rs.getBigDecimal("S_DQ_CLOSE"));
					obj.setsDqChange(rs.getBigDecimal("S_DQ_CHANGE"));
					obj.setsDqPctchange(rs.getBigDecimal("S_DQ_PCTCHANGE"));
					obj.setsDqVolume(rs.getBigDecimal("S_DQ_VOLUME"));
					obj.setsDqAvgprice(rs.getBigDecimal("S_DQ_AVGPRICE"));
					obj.setsDqTradestatus(rs.getString("S_DQ_TRADESTATUS"));
					obj.setOpdate(rs.getDate("OPDATE"));
                    obj.setOpmode(rs.getString("OPMODE"));
					list.add(obj);
				}
				return list;
			}
//			else if(PabHqContans.TMP_E_NAME_货币基金每天净值收益.equals(tmpEName)){
//				List<CMoneyMarketDailyFIncome> list = new ArrayList<CMoneyMarketDailyFIncome>();
//				while(rs.next()){
//					CMoneyMarketDailyFIncome obj = new CMoneyMarketDailyFIncome();
//					obj.setObjectId(rs.getString("OBJECT_ID"));
//					obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
//					obj.setfInfoBgndate(rs.getString("F_INFO_BGNDATE"));
//					obj.setfInfoEnddate(rs.getString("F_INFO_ENDDATE"));
//					obj.setfInfoUnityield(rs.getBigDecimal("F_INFO_UNITYIELD"));
//					obj.setfAccunitnav(rs.getBigDecimal("F_ACCUNITNAV"));
//					obj.setfIncomePerMillion(rs.getBigDecimal("F_INCOME_PER_MILLION"));
//					obj.setfInfoYearlyroe(rs.getBigDecimal("F_INFO_YEARLYROE"));
//					obj.setAnnDate(rs.getString("ANN_DATE"));
//					obj.setOpdate(rs.getDate("OPDATE"));
//					obj.setOpmode(rs.getString("OPMODE"));
//					list.add(obj);
//				}
//				return list;
//			}
			else if(PabHqContans.TMP_E_NAME_中国共同基金净值.equals(tmpEName)){
				List<ChinaMutualFundNAV> list = new ArrayList<ChinaMutualFundNAV>();
				while(rs.next()){
					ChinaMutualFundNAV obj = new ChinaMutualFundNAV();
					obj.setObjectId(rs.getString("OBJECT_ID"));
					obj.setfInfoWindcode(rs.getString("F_INFO_WINDCODE"));
					obj.setAnnDate(rs.getString("ANN_DATE"));
					obj.setPriceDate(rs.getString("PRICE_DATE"));
					obj.setfNavUnit(rs.getBigDecimal("F_NAV_UNIT"));
					obj.setfNavAccumulated(rs.getBigDecimal("F_NAV_ACCUMULATED"));
					obj.setfNavDivaccumulated(rs.getBigDecimal("F_NAV_DIVACCUMULATED"));
					obj.setfNavAdjfactor(rs.getBigDecimal("F_NAV_ADJFACTOR"));
					obj.setCrncyCode(rs.getString("CRNCY_CODE"));
					obj.setfPrtNetasset(rs.getBigDecimal("F_PRT_NETASSET"));
					obj.setfAssetMergedsharesornot(rs.getBigDecimal("F_ASSET_MERGEDSHARESORNOT"));
					obj.setNetassetTotal(rs.getBigDecimal("NETASSET_TOTAL"));
					obj.setfNavAdjusted(rs.getBigDecimal("F_NAV_ADJUSTED"));
					obj.setOpdate(rs.getDate("OPDATE"));
					obj.setOpmode(rs.getString("OPMODE"));
					list.add(obj);
				}
				return list;
			}
			else if(PabHqContans.TMP_E_NAME_SHIRBO行情.equals(tmpEName)){
				List<ShiborPrices> list = new ArrayList<ShiborPrices>();
				while(rs.next()){
					ShiborPrices obj = new ShiborPrices();
					obj.setObjectId(rs.getString("OBJECT_ID"));
					obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
					obj.setTradeDt(rs.getString("TRADE_DT"));
					obj.setbInfoRate(rs.getBigDecimal("B_INFO_RATE"));
					obj.setOpdate(rs.getDate("OPDATE"));
					obj.setOpmode(rs.getString("OPMODE"));
					list.add(obj);
				}
				return list;
			}
			else if(PabHqContans.TMP_E_NAME_法定存贷款利率.equals(tmpEName)){
				List<CBondBenchmark> list = new ArrayList<CBondBenchmark>();
				while(rs.next()){
					CBondBenchmark obj = new CBondBenchmark();
					obj.setObjectId(rs.getString("OBJECT_ID"));
					obj.setTradeDt(rs.getString("TRADE_DT"));
					obj.setbInfoBenchmark(rs.getString("B_INFO_BENCHMARK"));
					obj.setbInfoRate(rs.getBigDecimal("B_INFO_RATE"));
					obj.setOpdate(rs.getDate("OPDATE"));
					obj.setOpmode(rs.getString("OPMODE"));
					list.add(obj);
				}
				return list;
			}
			else if(PabHqContans.TMP_E_NAME_中债登估值.equals(tmpEName)){
				List<CBondAnalysisCNBD> list = new ArrayList<CBondAnalysisCNBD>();
				while(rs.next()){
					CBondAnalysisCNBD obj = new CBondAnalysisCNBD();
					obj.setObjectId(rs.getString("OBJECT_ID"));
					obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
					obj.setTradeDt(rs.getString("TRADE_DT"));
					obj.setbAnalMatuCnbd(rs.getBigDecimal("B_ANAL_MATU_CNBD"));
					obj.setbAnalDirtyCnbd(rs.getBigDecimal("B_ANAL_DIRTY_CNBD"));
					obj.setbAnalAccrintCnbd(rs.getBigDecimal("B_ANAL_ACCRINT_CNBD"));
					obj.setbAnalNetCnbd(rs.getBigDecimal("B_ANAL_NET_CNBD"));
					obj.setbAnalYieldCnbd(rs.getBigDecimal("B_ANAL_YIELD_CNBD"));
					obj.setbAnalModiduraCnbd(rs.getBigDecimal("B_ANAL_MODIDURA_CNBD"));
					obj.setbAnalCnvxtyCnbd(rs.getBigDecimal("B_ANAL_CNVXTY_CNBD"));
					obj.setbAnalVobpCnbd(rs.getBigDecimal("B_ANAL_VOBP_CNBD"));
					obj.setbAnalSprduraCnbd(rs.getBigDecimal("B_ANAL_SPRDURA_CNBD"));
					obj.setbAnalSprcnxtCnbd(rs.getBigDecimal("B_ANAL_SPRCNXT_CNBD"));
					obj.setbAnalAccrintcloseCnbd(rs.getBigDecimal("B_ANAL_ACCRINTCLOSE_CNBD"));
					obj.setbAnalPrice(rs.getBigDecimal("B_ANAL_PRICE"));
					obj.setbAnalNetprice(rs.getBigDecimal("B_ANAL_NETPRICE"));
					obj.setbAnalYield(rs.getBigDecimal("B_ANAL_YIELD"));
					obj.setbAnalModifiedduration(rs.getBigDecimal("B_ANAL_MODIFIEDDURATION"));
					obj.setbAnalConvexity(rs.getBigDecimal("B_ANAL_CONVEXITY"));
					obj.setbAnalBpvalue(rs.getBigDecimal("B_ANAL_BPVALUE"));
					obj.setbAnalSduration(rs.getBigDecimal("B_ANAL_SDURATION"));
					obj.setbAnalScnvxty(rs.getBigDecimal("B_ANAL_SCNVXTY"));
					obj.setbAnalInterestdurationCnbd(rs.getBigDecimal("B_ANAL_INTERESTDURATION_CNBD"));
					obj.setbAnalInterestcnvxtyCnbd(rs.getBigDecimal("B_ANAL_INTERESTCNVXTY_CNBD"));
					obj.setbAnalInterestduration(rs.getBigDecimal("B_ANAL_INTERESTDURATION"));
					obj.setbAnalInterestcnvxty(rs.getBigDecimal("B_ANAL_INTERESTCNVXTY"));
					obj.setbAnalPriceCnbd(rs.getBigDecimal("B_ANAL_PRICE_CNBD"));
					obj.setbAnalBpyield(rs.getBigDecimal("B_ANAL_BPYIELD"));
					obj.setbAnalExchange(rs.getString("B_ANAL_EXCHANGE"));
					obj.setbAnalCredibility(rs.getString("B_ANAL_CREDIBILITY"));
					obj.setOpdate(rs.getDate("OPDATE"));
					obj.setOpmode(rs.getString("OPMODE"));
					list.add(obj);
				}
				return list;
			}else if(PabHqContans.TMP_E_NAME_货币基金净值收益.equals(tmpEName)){
				List<CMoneyMarketFincome> list = new ArrayList<CMoneyMarketFincome>();
				while(rs.next()){
					CMoneyMarketFincome obj = new CMoneyMarketFincome();
					obj.setObjectId(rs.getString("OBJECT_ID"));
					obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
					obj.setfInfoBgndate(rs.getString("F_INFO_BGNDATE"));
					obj.setfInfoEnddate(rs.getString("F_INFO_ENDDATE"));
					obj.setfInfoUnityield(rs.getBigDecimal("F_INFO_UNITYIELD"));
					obj.setfInfoYearlyroe(rs.getBigDecimal("F_INFO_YEARLYROE"));
					obj.setAnnDate(rs.getString("ANN_DATE"));
					obj.setOpdate(rs.getDate("OPDATE"));
					obj.setOpmode(rs.getString("OPMODE"));
					list.add(obj);
				}
				return list;
			}
			/** add by dongjun on 20180921 start **/
			else if(PabHqContans.TMP_E_NAME_中国债券行情_净价.equals(tmpEName)){
                List<Cbondpricesnet> list = new ArrayList<Cbondpricesnet>();
                while(rs.next()){
                    Cbondpricesnet obj = new Cbondpricesnet();
                    obj.setObjectId(rs.getString("OBJECT_ID"));
                    obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
                    obj.setTradeDt(rs.getString("TRADE_DT"));
                    obj.setbDqOpen(rs.getBigDecimal("B_DQ_OPEN"));
                    obj.setbDqHigh(rs.getBigDecimal("B_DQ_HIGH"));
                    obj.setbDqLow(rs.getBigDecimal("B_DQ_LOW"));
                    obj.setbDqOriginclose(rs.getBigDecimal("B_DQ_ORIGINCLOSE"));
                    obj.setbDqVolume(rs.getBigDecimal("B_DQ_VOLUME"));
                    obj.setbDqAmount(rs.getBigDecimal("B_DQ_AMOUNT"));
                    obj.setOpdate(rs.getDate("OPDATE"));
                    obj.setOpmode(rs.getString("OPMODE"));
                    list.add(obj);
                }
                return list;
            }
            else if(PabHqContans.TMP_E_NAME_中国债券行情_全价.equals(tmpEName)){
                List<Cbondprices> list = new ArrayList<Cbondprices>();
                while(rs.next()){
                    Cbondprices obj = new Cbondprices();
                    obj.setObjectId(rs.getString("OBJECT_ID"));
                    obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
                    obj.setTradeDt(rs.getString("TRADE_DT"));
                    obj.setbDqOpen(rs.getBigDecimal("B_DQ_OPEN"));
                    obj.setbDqHigh(rs.getBigDecimal("B_DQ_HIGH"));
                    obj.setbDqLow(rs.getBigDecimal("B_DQ_LOW"));
                    obj.setbDqOriginclose(rs.getBigDecimal("B_DQ_ORIGINCLOSE"));
                    obj.setbDqVolume(rs.getBigDecimal("B_DQ_VOLUME"));
                    obj.setbDqAmount(rs.getBigDecimal("B_DQ_AMOUNT"));
                    obj.setOpdate(rs.getDate("OPDATE"));
                    obj.setOpmode(rs.getString("OPMODE"));
                    list.add(obj);
                }
                return list;
            }
            else if(PabHqContans.TMP_E_NAME_中证估值.equals(tmpEName)){
                List<Cbondanalysiscsi> list = new ArrayList<Cbondanalysiscsi>();
                while(rs.next()){
                    Cbondanalysiscsi obj = new Cbondanalysiscsi();
                    obj.setObjectId(rs.getString("OBJECT_ID"));
                    obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
                    obj.setTradeDt(rs.getString("TRADE_DT"));
                    obj.setbInfoType(rs.getString("B_INFO_TYPE"));
                    obj.setbInfoListingmarketnumbers(rs.getBigDecimal("B_INFO_LISTINGMARKETNUMBERS"));
                    obj.setbAnalDirtyCsi(rs.getBigDecimal("B_ANAL_DIRTY_CSI"));
                    obj.setbAnalYieldCsi(rs.getBigDecimal("B_ANAL_YIELD_CSI"));
                    obj.setbAnalModiduraCsi(rs.getBigDecimal("B_ANAL_MODIDURA_CSI"));
                    obj.setbAnalCnvxtyCsi(rs.getBigDecimal("B_ANAL_CNVXTY_CSI"));
                    obj.setbAnalNetCsi(rs.getBigDecimal("B_ANAL_NET_CSI"));
                    obj.setbAnalAccrintCsi(rs.getBigDecimal("B_ANAL_ACCRINT_CSI"));    
                    obj.setOpdate(rs.getDate("OPDATE"));
                    obj.setOpmode(rs.getString("OPMODE"));
                    list.add(obj);
                }
                return list;
            }
            else if(PabHqContans.TMP_E_NAME_中国债券上海清算所债券估值.equals(tmpEName)){
                List<Cbondanalysisshc> list = new ArrayList<Cbondanalysisshc>();
                while(rs.next()){
                    Cbondanalysisshc obj = new Cbondanalysisshc();
                    obj.setObjectId(rs.getString("OBJECT_ID"));
                    obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
                    obj.setTradeDt(rs.getString("TRADE_DT"));
                    obj.setbAnalMatuCnbd(rs.getBigDecimal("B_ANAL_MATU_CNBD"));
                    obj.setbAnalDirtyCnbd(rs.getBigDecimal("B_ANAL_DIRTY_CNBD"));
                    obj.setbAnalAccrintCnbd(rs.getBigDecimal("B_ANAL_ACCRINT_CNBD"));
                    obj.setbAnalNetCnbd(rs.getBigDecimal("B_ANAL_NET_CNBD"));
                    obj.setbAnalYieldCnbd(rs.getBigDecimal("B_ANAL_YIELD_CNBD"));
                    obj.setbAnalModiduraCnbd(rs.getBigDecimal("B_ANAL_MODIDURA_CNBD"));
                    obj.setbAnalCnvxtyCnbd(rs.getBigDecimal("B_ANAL_CNVXTY_CNBD"));
                    obj.setbAnalVobpCnbd(rs.getBigDecimal("B_ANAL_VOBP_CNBD"));
                    obj.setbAnalSprduraCnbd(rs.getBigDecimal("B_ANAL_SPRDURA_CNBD"));
                    obj.setbAnalSprcnxtCnbd(rs.getBigDecimal("B_ANAL_SPRCNXT_CNBD"));
                    obj.setbAnalPrice(rs.getBigDecimal("B_ANAL_PRICE"));
                    obj.setbAnalNetprice(rs.getBigDecimal("B_ANAL_NETPRICE"));
                    obj.setbAnalYield(rs.getBigDecimal("B_ANAL_YIELD"));
                    obj.setbAnalModifiedduration(rs.getBigDecimal("B_ANAL_MODIFIEDDURATION"));
                    obj.setbAnalConvexity(rs.getBigDecimal("B_ANAL_CONVEXITY"));
                    obj.setbAnalBpvalue(rs.getBigDecimal("B_ANAL_BPVALUE"));
                    obj.setbAnalSduration(rs.getBigDecimal("B_ANAL_SDURATION"));
                    obj.setbAnalScnvxty(rs.getBigDecimal("B_ANAL_SCNVXTY"));
                    obj.setbAnalInterestdurationCnbd(rs.getBigDecimal("B_ANAL_INTERESTDURATION_CNBD"));
                    obj.setbAnalInterestcnvxtyCnbd(rs.getBigDecimal("B_ANAL_INTERESTCNVXTY_CNBD"));
                    obj.setbAnalInterestduration(rs.getBigDecimal("B_ANAL_INTERESTDURATION"));
                    obj.setbAnalInterestcnvxty(rs.getBigDecimal("B_ANAL_INTERESTCNVXTY"));
                    obj.setbAnalPriceCnbd(rs.getBigDecimal("B_ANAL_PRICE_CNBD"));
                    obj.setbAnalBpyield(rs.getBigDecimal("B_ANAL_BPYIELD"));
                    obj.setbAnalSurpluscapital(rs.getBigDecimal("B_ANAL_SURPLUSCAPITAL"));
                    obj.setbAnalExchange(rs.getString("B_ANAL_EXCHANGE"));
                    obj.setbAnalCredibility(rs.getString("B_ANAL_CREDIBILITY"));                    
                    obj.setOpdate(rs.getDate("OPDATE"));
                    obj.setOpmode(rs.getString("OPMODE"));
                    list.add(obj);
                }
                return list;
            }
            else if(PabHqContans.TMP_E_NAME_中国封闭式基金日行情.equals(tmpEName)){
                List<Chinaclosedfundeodprice> list = new ArrayList<Chinaclosedfundeodprice>();
                while(rs.next()){
                    Chinaclosedfundeodprice obj = new Chinaclosedfundeodprice();
                    obj.setObjectId(rs.getString("OBJECT_ID"));
                    obj.setsInfoWindcode(rs.getString("S_INFO_WINDCODE"));
                    obj.setTradeDt(rs.getString("TRADE_DT"));
                    obj.setCrncyCode(rs.getString("CRNCY_CODE"));
                    obj.setsDqPreclose(rs.getBigDecimal("S_DQ_PRECLOSE"));
                    obj.setsDqOpen(rs.getBigDecimal("S_DQ_OPEN"));
                    obj.setsDqHigh(rs.getBigDecimal("S_DQ_HIGH"));
                    obj.setsDqLow(rs.getBigDecimal("S_DQ_LOW"));
                    obj.setsDqClose(rs.getBigDecimal("S_DQ_CLOSE"));
                    obj.setsDqChange(rs.getBigDecimal("S_DQ_CHANGE"));
                    obj.setsDqPctchange(rs.getBigDecimal("S_DQ_PCTCHANGE"));
                    obj.setsDqVolume(rs.getBigDecimal("S_DQ_VOLUME"));
                    obj.setsDqAmount(rs.getBigDecimal("S_DQ_AMOUNT"));
                    obj.setsDqAdjpreclose(rs.getBigDecimal("S_DQ_ADJPRECLOSE"));
                    obj.setsDqAdjopen(rs.getBigDecimal("S_DQ_ADJOPEN"));
                    obj.setsDqAdjhigh(rs.getBigDecimal("S_DQ_ADJHIGH"));
                    obj.setsDqAdjlow(rs.getBigDecimal("S_DQ_ADJLOW"));
                    obj.setsDqAdjclose(rs.getBigDecimal("S_DQ_ADJCLOSE"));
                    obj.setsDqAdjfactor(rs.getBigDecimal("S_DQ_ADJFACTOR"));
                    obj.setTradesCount(rs.getBigDecimal("TRADES_COUNT"));
                    obj.setDiscountRate(rs.getBigDecimal("DISCOUNT_RATE"));
                    obj.setOpdate(rs.getDate("OPDATE"));
                    obj.setOpmode(rs.getString("OPMODE"));
                    list.add(obj);
                }
                return list;
            }
			/** add by dongjun on 20180921 end **/
			/** add by dongjun on 20181011 title:债券行权计划 start**/
            else if(PabHqContans.TMP_E_NAME_债券行权计划.equals(tmpEName)){
                List<ValPayhsrcsecoption> list = new ArrayList<ValPayhsrcsecoption>();
                while(rs.next()){
                    ValPayhsrcsecoption obj = new ValPayhsrcsecoption();
                    obj.setObjectId(rs.getString("OBJECT_ID"));
                    obj.setSecid(rs.getString("SECID"));
                    obj.setOptionnum(rs.getInt("OPTIONNUM"));
                    obj.setOptionstartday(rs.getString("OPTIONSTARTDAY"));
                    obj.setOptionendday(rs.getString("OPTIONENDDAY"));
                    obj.setOptionpayday(rs.getString("OPTIONPAYDAY"));
                    obj.setOptionprice(rs.getBigDecimal("OPTIONPRICE"));
                    obj.setOptiontype(rs.getString("OPTIONTYPE"));
                    obj.setLstmntdate(rs.getString("LSTMNTDATE"));
                    obj.setLstmntuser(rs.getString("LSTMNTUSER"));
                    obj.setEffectflag(rs.getString("EFFECTFLAG"));
                    obj.setOpdate(rs.getString("OPDATE"));
                    obj.setOpmode(rs.getString("OPMODE"));
                    list.add(obj);
                }
                return list;
            }
			/** add by dongjun on 20181011 title:债券行权计划 end**/
			else{
				System.out.println("模板"+tmpEName+"目前功能不支持...");
			}
		} catch (SQLException e) {
		    logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static Object queryResultData(Connection dbConnection,ValInterfTemplate tmplate,Date cdate){
		String SQL_ = querySQL(tmplate.getTmpEName(),cdate);
		System.out.println("模板"+tmplate.getTmpEName()+"查询SQL="+SQL_);
		if(SQL_==null || SQL_.length()==0){
			return null;
		}
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = dbConnection.createStatement();
			rs = statement.executeQuery(SQL_);
			return getResultSetData(rs,tmplate.getTmpEName());
		} catch (SQLException e) {
			System.out.println("模板"+tmplate.getTmpEName()+"查询错误  "+e.getMessage());
			logger.error(e.getMessage(), e);
		}finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
				    logger.error(e.getMessage(), e);
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
				    logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}
	
	
	
}
