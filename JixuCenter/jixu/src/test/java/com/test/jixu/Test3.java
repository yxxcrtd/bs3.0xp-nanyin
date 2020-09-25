package com.test.jixu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 即期换汇所有的科目配置
 * @author 桃汰猪
 *
 */
public class Test3 {
public void test() {
		
		try {
			FileOutputStream fos = new FileOutputStream("D:/111.txt"); 
	        OutputStreamWriter pw = new OutputStreamWriter(fos); 
	        
			
			String[] curs= {
					"人民币:CNY:30031201:71030101:71030201",
					"美元:USD:30031202:71030102:71030202",
					"欧元:EUR:30031203:71030103:71030203",
					"英镑:GBP:30031204:71030104:71030204",
					"加拿大元:CAD:30031205:71030105:71030205",
					"法郎:FRF:30031206:71030106:71030206",
					"港币:HKD:30031207:71030107:71030207",
					"瑞士法郎:CHF:30031208:71030108:71030208",
					"日元:JPY:30031209:71030109:71030209",
					"澳大利亚元:AUD:30031210:71030110:71030210",
					"新加坡元:SGD:30031211:71030111:71030211",
					"卢布:RUB:30031212:71030112:71030212"
					};
			int corgroupnum=700;
			for(String cur:curs) {
				for(String cur2:curs) {
					if(!cur.equals(cur2)) {
						String cn_cur=cur.split(":")[0];
						String code_cur=cur.split(":")[1];
						String zqqsk=cur.split(":")[2];
						String qsacct=cur.split(":")[3];
						String qfacct=cur.split(":")[4];
						
						String cn_cur2=cur2.split(":")[0];
						String code_cur2=cur2.split(":")[1];
						String zqqsk2=cur2.split(":")[2];
						String qsacct2=cur2.split(":")[3];
						String qfacct2=cur2.split(":")[4];

				
						pw.write("--------------外汇远期-"+cn_cur+"/"+cn_cur2+"\r\n");
						pw.write("INSERT INTO JXC_COAAGROUP (GROUPNUM,GROUPNAME,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) VALUES ("+corgroupnum+",'外汇远期-"+code_cur+"/"+code_cur2+"',sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'31010199','00000000000000000002',6,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61012101','00000000000000000002',51,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61012101','00000000000000000002',52,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+qsacct2+"','00000000000000000002',3,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'64070126','00000000000000000002',145,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+zqqsk+"','00000000000000000002',35,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+zqqsk+"','00000000000000000002',36,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61119003','00000000000000000002',20,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'61119003','00000000000000000002',26,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'"+qfacct+"','00000000000000000002',32,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
						pw.write("INSERT into JXC_GL_COAA_MAP (ID,ACCT_NUM,CHARTNUM,COLUMNNUM,GROUPNUM,CREATE_TIME,CREATE_DEPT,UPDATE_TIME,UPDATE_USER,CREATE_USER) values (GlCoaaMap_ID_SEQ.nextval,'22090116','00000000000000000002',38,"+corgroupnum+",sysdate,null,sysdate,'admin','admin');\r\n");
		
						
						
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
		Test3 test=new Test3();
		test.test();
	}
}
