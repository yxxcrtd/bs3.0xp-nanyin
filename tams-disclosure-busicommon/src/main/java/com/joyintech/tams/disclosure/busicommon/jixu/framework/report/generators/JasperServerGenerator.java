package com.joyintech.tams.disclosure.busicommon.jixu.framework.report.generators;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;

import net.sf.jasperreports.engine.JRException;

public class JasperServerGenerator implements ReportGenerator{
	private DataSource dataSource = null;
	
	@Override
	public void genReport(ReportInfo report, Map<String, Object> params,
			PrintWriter out) throws JRException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void genReport(ReportInfo report, Map<String, Object> params,
			HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String genReport(ReportInfo report, Map<String, Object> params) throws JRException {
		String strUrl = getJasperUrl(report.getRepository(), report.getUser(), report.getPassword(), report.getFolder(), report.getUri(), params, "html");
		InputStream inputStream = null;
		try{
			URL connUrl = new URL(strUrl);
			URLConnection conn = connUrl.openConnection();
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	        inputStream = conn.getInputStream();
	        String getData = readInputStreamToString(inputStream);
	        return getData;
		}catch (Exception e){
			return null;
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void exportReport(ReportInfo report, Map<String, Object> params,
			String type, String dest) throws JRException {
		String strUrl = getJasperUrl(report.getRepository(), report.getUser(), report.getPassword(), report.getFolder(), report.getUri(), params, type);
		FileOutputStream fos = null;
		InputStream inputStream = null;
		try {
			URL connUrl = new URL(strUrl);
			URLConnection conn = connUrl.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

	         inputStream = conn.getInputStream();
	        byte[] getData = readInputStream(inputStream);
	        
	        File localFile = new File(dest);
	        File dir = localFile.getParentFile();
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }

	         fos = new FileOutputStream(localFile);
	        fos.write(getData);

		} catch(Exception e){
			throw new JRException("Fail to generate report");
		}
		finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
	
    public String getJasperUrl(String serverUrl, String serverUser, String serverPwd, String folder, String uri, Map<String, Object> params, String type){
//    	String url= serverUrl + ReportGlobalConstant.MID_URL + folder + ReportGlobalConstant.OBLIQUE_LINE + uri + "." + type ;
//    	String paramStr = ReportGlobalConstant.QUESTION_MARK + ReportGlobalConstant.J_USERNAME + "=" + serverUser + ReportGlobalConstant.AMPERS_AND + ReportGlobalConstant.J_PASSWORD + "=" + serverPwd;
//
//    	for (Map.Entry<String, Object> entry : params.entrySet()) {
//    		paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + entry.getKey() + "=" + entry.getValue().toString();
//    	}
//    	url = url+ paramStr;
    	return "";
    }
    
    public static String readInputStreamToString(InputStream inputStream) throws IOException {
    	BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    	 
        StringBuffer tStringBuffer = new StringBuffer();

        String sTempOneLine = new String("");

        while ((sTempOneLine = tBufferedReader.readLine()) != null){

            tStringBuffer.append(sTempOneLine);

        }
        return tStringBuffer.toString();
    }
    
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
