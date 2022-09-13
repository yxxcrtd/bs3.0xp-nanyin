package com.joyintech.tams.utils.poi;

import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.oss.config.MinioProperties;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import com.joyintech.tams.common.oss.service.impl.MinioFileRemoteService;
import fr.opensagres.poi.xwpf.converter.core.ImageManager;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 * 1.将指定的参数集合进行替换docx模板文档中的参数；
 * 2.实现将docx格式的文件进行转换，包括docx->html，docx->pdf
 */
public class PoiUtils {
    private static Logger logger = LoggerFactory.getLogger(PoiUtils.class);

    public static void mergeDocx(String tempateFile,OutputStream os,Map<String,Object> params ) throws Exception {
        PoiUtilsHelper.mergeParasToDocx(tempateFile,os,params);
    }

    public static void docxToHtml(InputStream is, OutputStream os, String imagePath) throws IOException {
    	docxToHtml(is,os,imagePath,null);
    }
    
    public static void docxToHtml(InputStream is, OutputStream os, String imagePath,String keyStr) throws IOException {
        XWPFDocument wordDocument = new XWPFDocument( is );
//        File imageFolderFile = new File(imagePath);
//        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
//        options.setExtractor(new FileImageExtractor(imageFolderFile));
        /*由于不使用apache存储图片，所以这里注释掉 edit by zhengbs 20220813 start*/
       /* //服务器安装apache目录
        String tmpImagePath = CacheUtil.getSysParam("POI_APACHE_PATH");
        //安装apache服务器的ip
//        String apacheAddress = CacheUtil.getSysParam("POI_APACHE_ADDRESS");
        String apacheAddress = InetAddress.getLocalHost().getHostAddress();

        XHTMLOptions options = XHTMLOptions.create();
        options.setImageManager( new ImageManager( new File(tmpImagePath),"")  {
            String upload = null;

            @Override
            public void extract(String imagePath,byte[] imageData ) throws IOException {
                FileOutputStream fos=null;
                try {
                    imagePath = imagePath.split("/")[2];
                    //imagePath = imagePath.substring(imagePath.indexOf("."));
                    String outfile = tmpImagePath+File.separator + imagePath;
                    fos = new FileOutputStream(outfile);
                    fos.write(imageData);
                    fos.close();
                    //增加随机数，防止浏览器缓存 modify by zhengbs
                    upload = "http://"+apacheAddress+"/images/" + imagePath+"?fresh="+Math.random();
                    logger.info("【上传Apache服务器图片地址：{}】",upload);
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if ( fos!=null )
                        fos.close();
                }
            }

            @Override
            public String resolve(String uri) {
                return upload;
            }
        });*/
        /*由于不使用apache存储图片，所以这里注释掉 edit by zhengbs 20220813 end*/
        //使用minio存储图片
        XHTMLOptions options = XHTMLOptions.create();
        options.setImageManager( new ImageManager(new File(""),"")  {
            String upload = null;

            @Override
            public void extract(String imagePath,byte[] imageData ) throws IOException {
                InputStream fis =null;
                try {
                    String tmpImagePath = "echartsImages";
                    imagePath = imagePath.split("/")[2];
                    FileRemoteService minioFileRemoteService = SpringContextUtil.getBean(MinioFileRemoteService.class);
                    MinioProperties minioProperties = (MinioProperties)SpringContextUtil.getBean(MinioProperties.class);
                    //上传图片到minio服务器
                    fis = new ByteArrayInputStream(imageData);
                    minioFileRemoteService.uploadFile(File.separator+tmpImagePath+File.separator+imagePath, fis);
                    //增加随机数，防止浏览器缓存 modify by zhengbs
                    upload = minioProperties.getReadPath()+File.separator+minioProperties.getBucket()+File.separator+tmpImagePath+File.separator + imagePath+"?fresh="+Math.random();
                    logger.info("【上传Minio服务器图片地址：{}】",upload);
                }catch (Exception e) {
                    logger.error("【处理图片异常】",e);
                } finally {
                    if ( fis!=null )
                        fis.close();
                }
            }

            @Override
            public String resolve(String uri) {
                return upload;
            }
        });

        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);

        //output
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(wordDocument, bos, options); //这里的os可以是ByteArrayOutputStream，获取内容，进行格式调整
        reviseHtml(bos,keyStr);
        bos.writeTo(os);
    }

    // doc转换为html
    public static void docToHtml(InputStream is, OutputStream os, String imagePath,String keyStr) throws Exception {
        HWPFDocument wordDocument = new HWPFDocument(is);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        // 设置图片的相对路径
        wordToHtmlConverter.setPicturesManager((a, b, suggestedName, d, e) -> {
            InputStream fis =null;
            FileRemoteService minioFileRemoteService = SpringContextUtil.getBean(MinioFileRemoteService.class);
            MinioProperties minioProperties = SpringContextUtil.getBean(MinioProperties.class);
            try {
                String tmpImagePath = StringUtils.defaultIfEmpty(imagePath, "image");
                //上传图片到minio服务器
                fis = new ByteArrayInputStream(a);
                String path = File.separator + tmpImagePath + File.separator + suggestedName;
                minioFileRemoteService.uploadFile(path, fis);
                //增加随机数，防止浏览器缓存 modify by zhengbs
                return minioProperties.getReadPath() + File.separator + minioProperties.getBucket() + path + "?fresh=" + Math.random();
            } finally {
                if(fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ex) {
                        // ignore
                    }
                }
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();

        String outDir = FileNameUtil.createUploadFilePath("");
        File dirFile = new File(outDir);
        if (!dirFile.exists()) {
            boolean ret = dirFile.mkdirs();
            if ( ret==false ) {
            	logger.error("创建目录错误："+dirFile.getName());
            }
        }
        StringBuilder imageDir = new StringBuilder();
        imageDir.append(outDir).append(StringUtils.defaultString(imagePath, "image"));
        File imagrDirFile = new File(imageDir.toString());
        if (!imagrDirFile.exists()) {
            imagrDirFile.mkdirs();
        }
        for (Picture pic : pics) {
            // 生成图片
            StringBuilder imageFilePath = new StringBuilder();
            imageFilePath.append(imageDir).append("/").append(pic.suggestFullFileName());
            pic.writeImageContent(new FileOutputStream(imageFilePath.toString()));
        }
        org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StringBuilder htmlPath = new StringBuilder();
        htmlPath.append(outDir).append("content.html");
        StreamResult streamResult = new StreamResult(os/*new File(htmlPath.toString())*/);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);

//        return htmlPath.toString();
    }
    

    public static void docxToPdf(InputStream is, OutputStream os) throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(is);
        //setFontType(xwpfDocument);
        PdfOptions pdfOptions = PdfOptions.create();
        //setAlign(xwpfDocument);
        PdfConverter.getInstance().convert(xwpfDocument, os, pdfOptions);
    }

    public static void copy(String srcFile,String destFile) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcFile));
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis!=null )
                    bis.close();
                if ( bos!=null )
                    bos.close();
            } catch (IOException e ) {
                e.printStackTrace();
            }
        }
    }

    //1.树节点单击，显示模板文件：将docx文档转换成html流


    //2.预览：将docx文档进行合并后，转换成html流


    //3.生成/导出：将docx文档进行合并后，转换成docx文档



    //4.导出：将docx文档合并后，转换成pdf文档

    /***
     * 将文档的文字设置为宋体 防止其他字体转pdf不显示
     * @Description:
     * @param xwpfDocument
     * @return:
     */
    private static void setFontType(XWPFDocument xwpfDocument) {
        //转换文档中文字字体
        List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
        if(paragraphs != null && paragraphs.size()>0){
            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                if(runs !=null && runs.size()>0){
                    for (XWPFRun run : runs) {
                        run.setFontFamily("宋体");
                    }
                }
            }
        }
        //转换表格里的字体 我也不想俄罗斯套娃但是不套真不能设置字体
        List<XWPFTable> tables = xwpfDocument.getTables();
        for (XWPFTable table : tables) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (XWPFTableCell tableCell : tableCells) {
                    List<XWPFParagraph> paragraphs1 = tableCell.getParagraphs();
                    for (XWPFParagraph xwpfParagraph : paragraphs1) {
                        List<XWPFRun> runs = xwpfParagraph.getRuns();
                        for (XWPFRun run : runs) {
                            run.setFontFamily("宋体");
                        }
                    }
                }
            }
        }
    }
    
    /***
     * 将文档的文字设置为宋体 防止其他字体转pdf不显示
     * @Description:
     * @param xwpfDocument
     * @return:
     */
    private static void setAlign(XWPFDocument xwpfDocument) {
        //转换表格里的字体 我也不想俄罗斯套娃但是不套真不能设置字体
        List<XWPFTable> tables = xwpfDocument.getTables();
        for (XWPFTable table : tables) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (XWPFTableCell tableCell : tableCells) {
                	tableCell.setVerticalAlignment(XWPFVertAlign.CENTER);
                }
            }
        }
    }
    
	/**
	 * 
     * 这里只对html宽度进行调整，因此只做最简单的替换，不用jsoup包读写html了，后面如果又复杂的转换再考虑
	 * @param bos
	 */
    private static void reviseHtml(ByteArrayOutputStream bos,String keyStr) {
        try {
//            System.out.println("==--------------------------------------------------------------------------------------------------------==");
//            System.out.println(bos.toString());
//            System.out.println("==--------------------------------------------------------------------------------------------------------==");
            String content = bos.toString();
            String beginStr = "<div style=\"width:";
            String endStr = ">";
            String replaceStr = "";
            int beginIdx = content.indexOf(beginStr);  //30%;
            if (beginIdx!=-1 ) {  //找到，需要进行替换
            	//replaceStr = beginStr + "38%" + endStr;
            	replaceStr = "<div style=\"width:450pt;margin-bottom:42.65pt;margin-top:70.9pt;margin-left:220pt;margin-right:70.9pt;\">";
            	int endIdx = content.indexOf(endStr);
            	
            	content = replaceStr + content.substring(endIdx+1);
            }
            if ( keyStr!=null && keyStr.length()>0 )
            	content = reviseMergeCells(content,keyStr);
            
//            System.out.println("==--------------------------------------------------------------------------------------------------------==");
//            System.out.println(content);
//            System.out.println("==--------------------------------------------------------------------------------------------------------==");            
            bos.reset();
            bos.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 针对定期报表中的财务指标，对其他费用单元格合并，表样如下
     * ----------------------------------------------------------------------------
     *  |                    	    |             报告期（2022年04月01日 - 2022年06月30日）|
     *  |    内部销售代码        ------------------------------------------------------|
	    |                           |          资产净值 |	份额净值	| 累计净值       | 4.其他费用 |
        ----------------------------------------------------------------------------|
        |A01                     |      9,123.45    |              1.0 |                1.0 |                 |
        -------------------|-------------- -|---------- --|---------------|      478.12|
        |A02                     |      9,123.45    |              1.0 |                1.0 |                 |
        ----------------------------------------------------------------------------
        
     * @param content
     * @return
     */
    private static String reviseMergeCells(String content,String keyStr) {
        //替换单元格合并
        Document doc = Jsoup.parse(content);
        Elements tables = doc.getElementsByTag("table");
        int rowIdx = -1;
        int colIdx = -1;
        Element tableIdx = null;
        for ( Element table:tables ) {
        	Elements lines = table.getElementsByTag("tr");
        	if ( lines!=null && lines.size()>0 ) {
        		for ( int i=0;i<lines.size();i++ ) {
        			Element line=lines.get(i);
        			Elements cols = line.getElementsByTag("td");
        			if ( cols!=null && cols.size()>2 ) {  //只有大于两列才合并
        				for ( int j=0;j<cols.size();j++ ) {
        					Element col=cols.get(j);
        					String cellStr = col.html();  
		        			if ( cellStr.contains(keyStr)) {  //从i+1以下的第j列都合并
		        				tableIdx= table;
		        				rowIdx = i+1;
		        				colIdx = j+1;
		        				break;
		        			}
        				}
        			}
        		}
	        } else {
	        	continue;
	        }
        }
        int startRow = 0;
        if ( tableIdx!=null && rowIdx>0&& colIdx>0 ) {
        	Elements lines = tableIdx.getElementsByTag("tr");
        	for ( int i=0;i<lines.size();i++ ) {
        		Element line = lines.get(i);
        		Elements cols = line.getElementsByTag("td");
        		for ( int j=0;j<cols.size();j++ ) {
        			Element col = cols.get(j);
        			//System.out.println(col);
        			if ( i==rowIdx && j==colIdx ) {
        				col.attr("rowspan",lines.size()-2+"");
        				//flag = true;
        				startRow=i;
        				break;
        			}
        		}
        		if ( startRow>0 && i>startRow )
        			cols.get(colIdx).remove();   //删除后续的列
        	}
        }
        
        
        
        return doc.html();
        //替换单元格合并--end
    }

//    public static void main(String[] args) {
//    	String content = "<div style=\"width:596.3pt;margin-bottom:42.65pt;margin-top:70.9pt;margin-left:70.9pt;margin-right:70.9pt;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正黑体简体';font-size:18.0pt;white-space:pre-wrap;\">南银理财&ldquo;日月&rdquo;固收一年分级理财产品AZ70022理财产品</span></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正黑体简体';font-size:18.0pt;white-space:pre-wrap;\">2022年</span><span style=\"font-family:'方正黑体简体';font-size:18.0pt;white-space:pre-wrap;\">第2季</span><span style=\"font-family:'方正黑体简体';font-size:18.0pt;white-space:pre-wrap;\">度报告</span></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">产品管理人:</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">南银理财有限责任公司</span></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">产品托管人:</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">安徽省宁国经开区建设投资有限公司</span></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-weight:bold;white-space:pre-wrap;\">&sect;</span><span style=\"font-weight:bold;white-space:pre-wrap;\">1 </span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">产品概况</span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><table style=\"border-collapse:collapse;\"><tr><td style=\"width:140.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">产品全称</span></p></td><td style=\"width:319.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">南银理财&ldquo;日月&rdquo;固收一年分级理财产品AZ70022</span></p></td></tr><tr><td style=\"width:140.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">产品登记编码</span></p></td><td style=\"width:319.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">Z7003222010022（投资者可依据该编码在中国理财网www.chinawealth.com.cn查询理财产品相关信息）</span></p></td></tr><tr><td style=\"width:140.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">产品运作方式</span></p></td><td style=\"width:319.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">封闭式净值型</span></p></td></tr><tr><td style=\"width:140.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">产品成立日</span></p></td><td style=\"width:319.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">2022年02月28日</span></p></td></tr><tr><td style=\"width:140.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">报告期末产品份额总额</span></p></td><td style=\"width:319.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\"> 份</span></p></td></tr><tr><td style=\"width:140.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">合作机构</span></p></td><td style=\"width:319.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">-</span></p></td></tr><tr><td style=\"width:140.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">产品管理人</span></p></td><td style=\"width:319.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">南银理财有限责任公司</span></p></td></tr><tr><td style=\"width:140.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">产品托管人</span></p></td><td style=\"width:319.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">安徽省宁国经开区建设投资有限公司</span></p></td></tr></table><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">&sect;2  主要财务指标和产品</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">业绩比较基准</span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">2.1 主要财务指标</span><span id=\"_GoBack\"/></p><p style=\"text-align:right;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">单位：人民币元</span></p><table style=\"width:466.1pt;border-collapse:collapse;\"><tr><td style=\"width:148.6pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">主要财务指标</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">报告期（2022年04月01日 - 2022年06月30日）</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p/></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p/></td></tr><tr><td style=\"width:148.6pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">Z70022001</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">Z70022002</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">Z70022003</span></p></td></tr><tr><td style=\"width:148.6pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">1.期末产品最后一个市场交易日资产净值</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\"/></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">31317527074.36</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">31317527074.36</span></p></td></tr><tr><td style=\"width:148.6pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">2.期末产品最后一个市场交易日份额净值</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\"/></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.0986</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.0986</span></p></td></tr><tr><td style=\"width:148.6pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">3.期末产品最后一个市场交易日份额累计净值</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\"/></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.0986</span></p></td><td style=\"width:79.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.0986</span></p></td></tr></table><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">2.2 产品业绩比较基准</span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">2.2.1本报告期产品业绩比较基准</span></p><table style=\"width:462.15pt;border-collapse:collapse;\"><tr><td style=\"width:192.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">阶段</span></p></td><td style=\"width:269.35pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">业绩比较基准</span></p></td></tr><tr><td style=\"width:192.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">2022年04月01日 - 2022年06月30日</span></p></td><td style=\"width:134.7pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">Z70022001</span></p></td><td style=\"width:134.65pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.13</span></p></td></tr><tr><td style=\"width:192.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">2022年04月01日 - 2022年06月30日</span></p></td><td style=\"width:134.7pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">Z70022002</span></p></td><td style=\"width:134.65pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.13</span></p></td></tr><tr><td style=\"width:192.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">2022年04月01日 - 2022年06月30日</span></p></td><td style=\"width:134.7pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">Z70022003</span></p></td><td style=\"width:134.65pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.13</span></p></td></tr></table><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">&sect;3  管理人报告</span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">3.1 报告期内产品的投资策略和运作分析</span><span>&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p style=\"text-indent:24.0pt;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\"> </span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">3.2 报告期内产品的流动性风险分析</span></p><p style=\"text-indent:24.0pt;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\"> </span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">3.3 报告期内产品的业绩表现</span></p><p style=\"text-indent:24.0pt;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">截至报告期末,本产品份额净值为</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">Z70022002份额净值为1.0986元，Z70022003份额净值为1.0986</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">元。</span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">&sect;4  投资组合报告</span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">4.1 报告期末产品资产组合情况</span></p><table style=\"width:440.9pt;border-collapse:collapse;\"><tr><td style=\"width:36.9pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">序号</span></p></td><td style=\"width:92.15pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">资产类别</span></p></td><td style=\"width:148.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">穿透前占总资产比例</span></p></td><td style=\"width:163.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">穿透后占总资产比例</span></p></td></tr><tr><td style=\"width:36.9pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1</span></p></td><td style=\"width:92.15pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">固定</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">收益类</span></p></td><td style=\"width:148.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\"/><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">30.00</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">%</span></p></td><td style=\"width:163.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\"/><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">20.00</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">%</span></p></td></tr><tr><td style=\"width:36.9pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">2</span></p></td><td style=\"width:92.15pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">权益类</span></p></td><td style=\"width:148.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">30.00</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">%</span></p></td><td style=\"width:163.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">0.00</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">%</span></p></td></tr><tr><td style=\"width:36.9pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">3</span></p></td><td style=\"width:92.15pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">商品及金融衍</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">生品类</span></p></td><td style=\"width:148.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">0.00</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">%</span></p></td><td style=\"width:163.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">5.00</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">%</span></p></td></tr><tr><td style=\"width:36.9pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">4</span></p></td><td style=\"width:92.15pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">混合类</span></p></td><td style=\"width:148.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">10.00</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">%</span></p></td><td style=\"width:163.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">5.00</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">%</span></p></td></tr><tr><td style=\"width:36.9pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">5</span></p></td><td style=\"width:92.15pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">合计</span></p></td><td style=\"width:148.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">100.00%</span></p></td><td style=\"width:163.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">100.00%</span></p></td></tr></table><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">4.2 报告期末按公允价值占产品资产净值比例大小排序的前十名资产投资明细</span></p><table style=\"width:440.0pt;border-collapse:collapse;\"><tr><td style=\"width:29.1pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">序号</span></p></td><td style=\"width:86.4pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">代码</span></p></td><td style=\"width:134.5pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">名称</span></p></td><td style=\"width:101.75pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">公允价值(元)</span></p></td><td style=\"width:88.25pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">占产品资产净值比例（％）</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">190306</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">19进出06</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">880725408.96</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">2.5</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">2</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">112216007</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">22上海银行CD007</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">496451968.1</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.41</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">3</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">112104035</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">21中国银行CD035</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">496153406.67</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.41</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">4</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">170206</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">17国开06</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">450231071.27</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.28</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">5</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">112106202</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">21交通银行CD202</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">396913612.54</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.13</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">6</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">112206008</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">22交通银行CD008</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">392036427.09</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.11</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">7</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">112206015</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">22交通银行CD015</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">391964847.55</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1.11</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">8</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">190403</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">19农发03</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">300004577.78</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">0.85</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">9</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">112103038</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">21农业银行CD038</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">300000000</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">0.85</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">10</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">112103037</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">21农业银行CD037</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">300000000</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">0.85</span></p></td></tr><tr><td style=\"width:29.1pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">11</span></p></td><td style=\"width:86.4pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">112103036</span></p></td><td style=\"width:134.5pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">21农业银行CD036</span></p></td><td style=\"width:101.75pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">300000000</span></p></td><td style=\"width:88.25pt;background-color:#ffffff;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">0.85</span></p></td></tr></table><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;background-color:#ffffff;white-space:pre-wrap;\">4.3报告期末非标准化债权类资产明细</span></p><table style=\"width:446.55pt;border-collapse:collapse;\"><tr><td style=\"width:35.45pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">序号</span></p></td><td style=\"width:63.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">融资客户</span></p></td><td style=\"width:85.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">项目名称</span></p></td><td style=\"width:70.85pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">剩余融资期限</span></p></td><td style=\"width:85.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">到期收益分配</span></p></td><td style=\"width:63.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">交易结构</span></p></td><td style=\"width:42.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">风险状况</span></p></td></tr><tr><td style=\"width:35.45pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1</span></p></td><td style=\"width:63.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">北京银行有限公司</span></p></td><td style=\"width:85.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">运营项目</span></p></td><td style=\"width:70.85pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1000</span></p></td><td style=\"width:85.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">20</span></p></td><td style=\"width:63.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">测试交易结构</span></p></td><td style=\"width:42.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">中风险</span></p></td></tr></table><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">&sect;5 投资账户信息</span></p><table style=\"width:456.9pt;border-collapse:collapse;\"><tr><td style=\"width:49.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">序号</span></p></td><td style=\"width:101.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">账户类型</span></p></td><td style=\"width:101.85pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">账号</span></p></td><td style=\"width:101.85pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">账户名称</span></p></td><td style=\"width:101.85pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;background-color:#ffffff;white-space:pre-wrap;\">开户单位</span></p></td></tr><tr><td style=\"width:49.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">1</span></p></td><td style=\"width:101.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">托管账户</span></p></td><td style=\"width:101.85pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">0120080000000329</span></p></td><td style=\"width:101.85pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">南京银行日日聚宝</span></p></td><td style=\"width:101.85pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">南京银行</span></p></td></tr></table><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:center;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">&sect;6 关联交易情况</span></p><p style=\"text-indent:24.0pt;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">报告期内，产品投资于本公司或托管机构，主要股东、控股股东、实际控制人、一致行动人、最终受益人，其控股的机构或者与其有重大利害关系的公司发行的证券交易金额</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">12061259547.44</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">。</span></p><p style=\"text-indent:24.0pt;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">产品投资于本公司或托管机构，主要股东、控股股东、实际控制人、一致行动人、最终受益人，其控股的机构或者与其有重大利害关系的公司承销的证券交易金额</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">13061259547.44</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">。</span></p><p style=\"text-indent:24.0pt;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">产品与关联方作为交易对手开展的投融资业务交易金额</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">14061259547.44</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\"> 。</span></p><p style=\"text-indent:24.0pt;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">15061259547.4374</span><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;white-space:pre-wrap;\">。</span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:right;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"text-align:right;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">南银理财有限责任公司</span></p><p style=\"text-align:right;margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><span style=\"font-family:'方正仿宋简体';font-size:12.0pt;font-weight:bold;white-space:pre-wrap;\">2022年06月30日</span></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"><br/></p><p style=\"margin-top:12.0pt;margin-bottom:6.0pt;white-space:pre-wrap;\"/></div>";
//    	System.out.println("before revise:");
//    	System.out.println(content);
//    	content = reviseMergeCells(content);
//    	System.out.println("after revise:");
//    	System.out.println(content);
//    }
    
}
