package com.test.jixu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 即期换汇所有的科目配置
 * @author 桃汰猪
 *
 */
public class Test2 {
	public void test() {
		
		try {
			FileOutputStream fos = new FileOutputStream("D:/111.txt"); 
	        OutputStreamWriter pw = new OutputStreamWriter(fos); 
	        
			
			String[] curs= {
					"人民币:CNY:12210101:22410101:71040101:71040201",
					"美元:USD:12210102:22410102:71040102:71040202",
					"欧元:EUR:12210103:22410103:71040103:71040203",
					"英镑:GBP:12210104:22410104:71040104:71040204",
					"加拿大元:CAD:12210105:22410105:71040105:71040205",
					"法郎:FRF:12210106:22410106:71040106:71040206",
					"港币:HKD:12210107:22410107:71040107:71040207",
					"瑞士法郎:CHF:12210108:22410108:71040108:71040208",
					"日元:JPY:12210109:22410109:71040109:71040209",
					"澳大利亚元:AUD:12210110:22410110:71040110:71040210",
					"新加坡元:SGD:12210111:22410111:71040111:71040211",
					"卢布:RUB:12210112:22410112:71040112:71040212"
					};
			int corgroupnum=400;
			for(String cur:curs) {
				for(String cur2:curs) {
					if(!cur.equals(cur2)) {
						String cn_cur=cur.split(":")[0];
						String code_cur=cur.split(":")[1];
						String in_act=cur2.split(":")[2];
						String out_act=cur2.split(":")[3];
						String bw_in_act=cur2.split(":")[4];
						String bw_out_act=cur2.split(":")[5];
						
						String cn_cur2=cur2.split(":")[0];
						String code_cur2=cur2.split(":")[1];
						String in_acct2=cur.split(":")[2];
						String out_acct2=cur.split(":")[3];
						String bw_in_act2=cur.split(":")[4];
						String bw_out_act2=cur.split(":")[5];
						
						pw.write("--------------"+cn_cur2+"/"+cn_cur+"\r\n");
						pw.write("INSERT INTO JXC_COAAGROUP (GROUPNUM,GROUPNAME,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) VALUES ("+corgroupnum+",'外汇掉期-"+code_cur+"/"+code_cur2+"',sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+in_act+"','00000000000000000002',32,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+out_act+"','00000000000000000002',36,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+in_acct2+"','00000000000000000002',35,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+out_acct2+"','00000000000000000002',3,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+bw_in_act+"','00000000000000000002',501,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+bw_out_act+"','00000000000000000002',503,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+bw_in_act2+"','00000000000000000002',502,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+bw_out_act2+"','00000000000000000002',500,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61120101','00000000000000000002',96,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61120101','00000000000000000002',97,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61119002','00000000000000000002',124,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61119002','00000000000000000002',125,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61119002','00000000000000000002',20,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61119002','00000000000000000002',26,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");

						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'64070126','00000000000000000002',145,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'22090116','00000000000000000002',38,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");

						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'31030199','00000000000000000002',6,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61011102','00000000000000000002',51,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61011102','00000000000000000002',52,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						
						
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
		Test2 test=new Test2();
		test.test();
	}
}
