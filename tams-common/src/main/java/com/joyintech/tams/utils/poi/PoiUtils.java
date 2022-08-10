package com.joyintech.tams.utils.poi;

import com.joyintech.framework.util.CacheUtil;
import fr.opensagres.poi.xwpf.converter.core.ImageManager;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.*;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 * 1.将指定的参数集合进行替换docx模板文档中的参数；
 * 2.实现将docx格式的文件进行转换，包括docx->html，docx->pdf
 */
public class PoiUtils {

    public static void mergeDocx(String tempateFile,OutputStream os,Map<String,Object> params ) throws PoiException {
        PoiUtilsHelper.mergeParasToDocx(tempateFile,os,params);
    }

    public static void docxToHtml(InputStream is, OutputStream os, String imagePath) throws IOException {
        XWPFDocument wordDocument = new XWPFDocument( is );

//        File imageFolderFile = new File(imagePath);
//        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
//        options.setExtractor(new FileImageExtractor(imageFolderFile));
        //服务器安装apache目录
        String tmpImagePath = CacheUtil.getSysParam("POI_APACHE_PATH");
        //安装apache服务器的ip
        String apacheAddress = CacheUtil.getSysParam("POI_APACHE_ADDRESS");

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
        });

        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);

        //output
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(wordDocument, bos, options); //这里的os可以是ByteArrayOutputStream，获取内容，进行格式调整
        reviseHtml(bos);
        bos.writeTo(os);
    }
    
    

    public static void docxToPdf(InputStream is, OutputStream os) throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(is);
        //setFontType(xwpfDocument);
        PdfOptions pdfOptions = PdfOptions.create();
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
    
	/**
	 * 
     * 这里只对html宽度进行调整，因此只做最简单的替换，不用jsoup包读写html了，后面如果又复杂的转换再考虑
	 * @param bos
	 */
    private static void reviseHtml(ByteArrayOutputStream bos) {
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
            	replaceStr = "<div style=\"width:37%;margin-bottom:42.65pt;margin-top:70.9pt;margin-left:220pt;margin-right:70.9pt;\">";
            	int endIdx = content.indexOf(endStr);
            	
            	content = replaceStr + content.substring(endIdx+1);
            }
            bos.reset();
            bos.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
