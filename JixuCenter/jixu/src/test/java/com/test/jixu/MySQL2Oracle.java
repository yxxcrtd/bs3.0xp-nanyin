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

/*mysql切换到oracle：
1.导入数据库(在oracle没有数据的请款下，需要执行该步骤):
	a. 用oracle jixu实例的system用户登录，执行recreateusers.sql脚本
	b. 通过sqldeveloper迁移数据，参考 https://www.cnblogs.com/xusweeter/p/6512521.html
	c. 查询所有的序列，触发器，并删除。 
	select 'drop sequence ' || sequence_name||';'||chr(13)||chr(10) from user_sequences;
	select 'drop trigger ' || trigger_name || ';'  from user_triggers;  
	update bk_cash_acct set NEXTCLOSE=date'2000-01-01' where nextclose is null;
	commit;

2.系统代码
	a. 更新database.xml，注释mysql的datasource，放开oracle的datasource
	b. 更新hibernate.xml，注释MySQLDialect驱动，放开OracleDialect驱动
	c. 直接执行src/test/java下的MySQL2Oracle.java, 执行。执行前，请做好以下步骤：
		(1) 工程引入Oracle的驱动文件：ojdbc6.jar。 jixu项目的JixuCenter\jixu\src\main\webapp\WEB-INF\lib就有
		(2) 在main方法里配置好路径，数据库等信息
	d. 执行好后，直接运行jixu项目


	
recreateusers.sql：
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
	 * @param codePath or数据库bean文件的路径
	 * @param host		数据库ip
	 * @param port		数据库端口
	 * @param dbname	数据库实例
	 * @param username	用户名
	 * @param pwd		密码
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
	
	//重新征程序列
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
			System.out.println("数据库连接成功!");
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
		m2o.testReGenSeq("D:/PwWorkSpace/jixu/JixuCenter/jixu/src/main/java/com/orm/obj", "localhost", 1521, "jixu", "jixu", "jixu");
	}
}
