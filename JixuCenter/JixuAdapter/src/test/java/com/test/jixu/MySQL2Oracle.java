package com.test.jixu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*mysql鍒囨崲鍒皁racle锛�
1.瀵煎叆鏁版嵁搴�(鍦╫racle娌℃湁鏁版嵁鐨勮娆句笅锛岄渶瑕佹墽琛岃姝ラ):
	a. 鐢╫racle jixu瀹炰緥鐨剆ystem鐢ㄦ埛鐧诲綍锛屾墽琛宺ecreateusers.sql鑴氭湰
	b. 閫氳繃sqldeveloper杩佺Щ鏁版嵁锛屽弬鑰� https://www.cnblogs.com/xusweeter/p/6512521.html
	c. 鏌ヨ鎵�鏈夌殑搴忓垪锛岃Е鍙戝櫒锛屽苟鍒犻櫎銆� 
	select 'drop sequence ' || sequence_name||';'||chr(13)||chr(10) from user_sequences;
	select 'drop trigger ' || trigger_name || ';'  from user_triggers;  
	update bk_cash_acct set NEXTCLOSE=date'2000-01-01' where nextclose is null;
	commit;

2.绯荤粺浠ｇ爜
	a. 鏇存柊database.xml锛屾敞閲妋ysql鐨刣atasource锛屾斁寮�oracle鐨刣atasource
	b. 鏇存柊hibernate.xml锛屾敞閲奙ySQLDialect椹卞姩锛屾斁寮�OracleDialect椹卞姩
	c. 鐩存帴鎵цsrc/test/java涓嬬殑MySQL2Oracle.java, 鎵ц銆傛墽琛屽墠锛岃鍋氬ソ浠ヤ笅姝ラ锛�
		(1) 宸ョ▼寮曞叆Oracle鐨勯┍鍔ㄦ枃浠讹細ojdbc6.jar銆� jixu椤圭洰鐨凧ixuCenter\jixu\src\main\webapp\WEB-INF\lib灏辨湁
		(2) 鍦╩ain鏂规硶閲岄厤缃ソ璺緞锛屾暟鎹簱绛変俊鎭�
	d. 鎵ц濂藉悗锛岀洿鎺ヨ繍琛宩ixu椤圭洰


	
recreateusers.sql锛�
==========================================================
drop user jixu cascade;	

create user jixu identified by jixu			
default tablespace jixudata				
temporary tablespace TEMP				
quota unlimited on jixuData
quota unlimited on jixuInd;								
GRANT CREATE SESSION TO jixu WITH ADMIN OPTION;	
GRANT EXP_FULL_DATABASE TO jixu;			
GRANT IMP_FULL_DATABASE TO jixu;			
GRANT CREATE ROLE TO jixu;				
GRANT DROP ANY ROLE TO jixu;			
GRANT ALTER ANY ROLE TO jixu;			
GRANT SELECT_CATALOG_ROLE To jixu;			
GRANT CREATE TABLE TO jixu WITH ADMIN OPTION;	
GRANT DROP ANY TABLE TO jixu;			
GRANT DROP ANY SYNONYM TO jixu;			
GRANT CREATE ANY SYNONYM TO jixu;			
GRANT DROP USER TO jixu;				
GRANT CREATE USER TO jixu;				
GRANT ALTER USER TO jixu;								
grant DBA to jixu;									
exit	
============================================================*/

public class MySQL2Oracle{
	
	private Statement stmt;
	
	private Connection conn;
	
	/**
	 * 
	 * @param codePath or鏁版嵁搴揵ean鏂囦欢鐨勮矾寰�
	 * @param host		鏁版嵁搴搃p
	 * @param port		鏁版嵁搴撶鍙�
	 * @param dbname	鏁版嵁搴撳疄渚�
	 * @param username	鐢ㄦ埛鍚�
	 * @param pwd		瀵嗙爜
	 */
	public void testM2O(String codePath, String host, int port, String dbname, String username, String pwd){
		connectOracle(host, port, dbname, username, pwd);
		
		String filepath="D:\\PwWorkSpace\\jixu\\JixuCenter\\jixu\\src\\main\\java\\com\\orm\\obj";
		m2o(filepath);
		
		try{
			stmt.close();
			conn.close();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	//閲嶆柊寰佺▼搴忓垪
	public void testReGenSeq(String codePath, String host, int port, String dbname, String username, String pwd){
		connectOracle(host, port, dbname, username, pwd);
		
		String filepath="D:\\PwWorkSpace\\jixu\\JixuCenter\\jixu\\src\\main\\java\\com\\orm\\obj";
		reGenerateSequence(filepath);
		
		try{
			stmt.close();
			conn.close();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	private void m2o(String filepath){
		File file=new File(filepath);
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "/" + filelist[i]);//reports(Daily Source)
				String filename=filelist[i].replace(".java", "");
				String tablename="";
				String idname="";
				int startNum=1;
				int readIDFlag=0;
				if (readfile.isDirectory()) {
					m2o(filepath + "/" + filelist[i]);
				}else{
					try{
						FileInputStream fis = new FileInputStream(readfile); 
			            InputStreamReader isr = new InputStreamReader(fis); 
			            BufferedReader br = new BufferedReader(isr); 
			            StringBuffer buf = new StringBuffer(); 
			            String line=null;
						while((line=br.readLine())!=null){
							if(line.contains("@Table") && tablename.equals("")){
								tablename=line.replaceAll(" ", "");
								tablename=tablename.replace("@Table(name=\"", "").trim();
								tablename=tablename.substring(0, tablename.indexOf("\"")).toString();
							}else if(line.contains("@Id")){
								readIDFlag=1;
							}else if(line.contains("@Column") && readIDFlag==1){
								idname=line.replaceAll(" ", "");
								idname=idname.replace("@Column(name=\"", "").trim();
								idname=idname.replace("\")", "").trim();
								String sql="select max("+idname+") from "+tablename;
								try{
									ResultSet rset=stmt.executeQuery(sql);
									while (rset.next()) {
										startNum=rset.getInt(1);
										break;
									}
								}catch(SQLException se){
									se.printStackTrace();
								}
								readIDFlag=2;
							}else if(line.contains("javax.persistence.Table;")){
								String line1="import javax.persistence.SequenceGenerator;";
								buf=buf.append(line1);
								buf = buf.append("\r\n");
							}else if(line.contains("GenerationType.IDENTITY")){
			            		String line1="	@SequenceGenerator(name=\"seq_generator\", sequenceName=\""+filename+"_ID_SEQ\", allocationSize=1, initialValue="+startNum+")";
			            		buf=buf.append(line1);
			            		buf = buf.append("\r\n");
			            		line="	@GeneratedValue(generator=\"seq_generator\", strategy = GenerationType.SEQUENCE)";
			            	}else if(line.contains("columnDefinition")){
			            		String line1=line.substring(0, line.indexOf("columnDefinition"));
			            		line1=line1.substring(0, line1.lastIndexOf(","));
			            		line1=line1+")";
			            		buf = buf.append(line1); 
				            	buf = buf.append("\r\n");
			            		if(!line.trim().endsWith(")")){
			            			while((line=br.readLine())!=null){
			            				if(line.trim().contains(")"))
			            					break;
			            			}
			            		}
			            		continue;
			            	}
			            	buf = buf.append(line); 
			            	buf = buf.append("\r\n");
			            }
						String reusltStr=buf.toString();
						startNum=startNum+1;
						reusltStr=reusltStr.replace("initialValue=1", "initialValue="+startNum);
						
						br.close(); 
			            FileOutputStream fos = new FileOutputStream(readfile); 
			            PrintWriter pw = new PrintWriter(fos); 
			            pw.write(reusltStr.toCharArray()); 
			            pw.flush(); 
			            pw.close(); 
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void getConnection(String host, int port, String dataBaseName, String userName, String password) {

		String dBDriver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(dBDriver);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + dataBaseName, userName, password);
			System.out.println("鏁版嵁搴撹繛鎺ユ垚鍔�!");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	private void reGenerateSequence(String filepath){
		File file=new File(filepath);
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "/" + filelist[i]);//reports(Daily Source)
				String filename=filelist[i].replace(".java", "");
				String tablename="";
				String idname="";
				int startNum=0;
				int readIDFlag=0;
				if (readfile.isDirectory()) {
					m2o(filepath + "/" + filelist[i]);
				}else{
					try{
						//filename+"_ID_SEQ
						FileInputStream fis = new FileInputStream(readfile); 
			            InputStreamReader isr = new InputStreamReader(fis); 
			            BufferedReader br = new BufferedReader(isr); 
			            String line=null;
						while((line=br.readLine())!=null){
							if(line.contains("@Table") && tablename.equals("")){
								tablename=line.replaceAll(" ", "");
								tablename=tablename.replace("@Table(name=\"", "").trim();
								tablename=tablename.substring(0, tablename.indexOf("\"")).toString();
							}else if(line.contains("@Id")){
								readIDFlag=1;
							}else if(line.contains("@Column") && readIDFlag==1){
								idname=line.replaceAll(" ", "");
								idname=idname.replace("@Column(name=\"", "").trim();
								idname=idname.replace("\")", "").trim();
								String sql="select max("+idname+") from "+tablename;
								try{
									ResultSet rset=stmt.executeQuery(sql);
									while (rset.next()) {
										startNum=rset.getInt(1);
										break;
									}
								}catch(SQLException se){
									se.printStackTrace();
								}
								readIDFlag=2;
								break;
							}
			            }
						br.close();
						
						String seqname=filename+"_ID_SEQ";
						startNum=startNum+1;
						
						//stmt.execute("drop sequence " +seqname);
						stmt.execute("CREATE SEQUENCE "+seqname+"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH "+startNum+" CACHE 20 NOORDER  NOCYCLE");
						
					}catch(IOException e){
						e.printStackTrace();
					}catch(SQLException se){
						se.printStackTrace();
					}
				}
			}
		}
	}
	
	private void connectOracle(String host, int port, String dbname, String username, String pwd){
		getConnection(host, 1521, "jixu", "jixu", "jixu");
		try{
			stmt = conn.createStatement();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		MySQL2Oracle m2o=new MySQL2Oracle();
		m2o.testM2O("D:/PwWorkSpace/jixu/JixuCenter/jixu/src/main/java/com/orm/obj", "localhost", 1521, "jixu", "jixu", "jixu");
	}
}
