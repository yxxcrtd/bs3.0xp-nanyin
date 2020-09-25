package com.test.jixu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 即期换汇所有的科目配置
 * @author 桃汰猪
 *
 */
public class Test1 {
	public void test() {
		
		try {
			FileOutputStream fos = new FileOutputStream("D:/111.txt"); 
	        OutputStreamWriter pw = new OutputStreamWriter(fos); 
	        
			
			String[] curs= {
					"人民币:CNY:12210101:22410101",
					"美元:USD:12210102:22410102",
					"欧元:EUR:12210103:22410103",
					"英镑:GBP:12210104:22410104",
					"加拿大元:CAD:12210105:22410105",
					"法郎:FRF:12210106:22410106",
					"港币:HKD:12210107:22410107",
					"瑞士法郎:CHF:12210108:22410108",
					"日元:JPY:12210109:22410109",
					"澳大利亚元:AUD:12210110:22410110",
					"新加坡元:SGD:12210111:22410111",
					"卢布:RUB:12210112:22410112"};
			int corgroupnum=181;
			for(String cur:curs) {
				for(String cur2:curs) {
					if(!cur.equals(cur2)) {
						String cn_cur=cur.split(":")[0];
						String code_cur=cur.split(":")[1];
						String out_act=cur.split(":")[3];
						
						String cn_cur2=cur2.split(":")[0];
						String code_cur2=cur2.split(":")[1];
						String in_acct2=cur2.split(":")[2];
						
						pw.write("--------------"+cn_cur+"/"+cn_cur2+"\r\n");
						pw.write("INSERT INTO JXC_COAAGROUP (GROUPNUM,GROUPNAME,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) VALUES ("+corgroupnum+",'外汇即期-"+code_cur+"/"+code_cur2+"',sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("Insert into JXS_ISSUE_INFO (CUSIP,ACTIVEDATE,CASHFLOWCFG,CASHFLOWCFG2,GLGROUP,CONTRACTSIZE,ENDPRICE,EXPIREDATE,FIXINCOMETYPE,OPTIONTYPE,SECURITYID,SECURITYNAME,STARTPRICE,SWAPEXCHANGEIND,UNDERCUSIP,ISSUER,RPSECTYPE,SOURCEID,PRICUR,INCOMECUR,PRICUR2,INCOMECUR2,COUNTRYCODE,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER,ISSUETYPE,EXCHANGECODE) values ('SEC2000010100000"+corgroupnum+"',to_date('01-1月 -00','DD-MON-RR'),0,0,"+corgroupnum+",1,0,to_date('31-12月-99','DD-MON-RR'),0,0,'FX_"+code_cur+"_"+code_cur2+"','"+cn_cur+"/"+cn_cur2+"',0,0,null,0,0,0,'"+code_cur+"','"+code_cur+"','"+code_cur2+"','"+code_cur2+"','CN',sysdate,null,sysdate,'admin','admin','F11','01');\r\n");
						
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+out_act+"','00000000000000000002',36,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+in_acct2+"','00000000000000000002',10,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61120101','00000000000000000002',96,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61120101','00000000000000000002',97,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+in_acct2+"','00000000000000000002',7,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+out_act+"','00000000000000000002',30,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61120101','00000000000000000002',20,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61120101','00000000000000000002',26,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+in_acct2+"','00000000000000000002',35,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'64070126','00000000000000000002',145,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'22090116','00000000000000000002',38,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61120101','00000000000000000002',124,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61120101','00000000000000000002',125,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						
						pw.write("\r\n");
						pw.write("\r\n");
						pw.write("\r\n");
						
						corgroupnum++;
					}
				}
			}
			pw.flush(); 
		    pw.close();
		}catch(IOException ie) {
			ie.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Test1 test=new Test1();
		test.test();
	}
}
