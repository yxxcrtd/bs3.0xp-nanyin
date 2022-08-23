package com.joyintech.tams.utils.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.utils.echarts.EchartsException;

/**
 * 主要功能：实现将docx文档中的参数，用指定的参数集合替换功能
 * 包括图片
 */
public class PoiUtilsHelper {

    /**
     * 替换docx文档，并生成docx文档
     * @param srcFile，源文件
     * @param os，目标输出流
     * @param params，替换的变量集合
     * @return
     * @throws Exception 
     */
    public static boolean mergeParasToDocx(String srcFile, OutputStream os, Map<String, Object> params) throws Exception {
        try {
			XWPFDocument document = new XWPFDocument(
					POIXMLDocument.openPackage(srcFile));

			//1.处理文本和图片
			Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
			while (itPara.hasNext()) {

				XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
				List<XWPFRun> runs = paragraph.getRuns();
				//参数变量
				String paraCode=""; 
				for (int i = 0; i < runs.size(); i++) {
					if ( runs.get(i)==null ) continue;
					
                    String runString = runs.get(i).getText(runs.get(i).getTextPosition());
                    if ( runString==null ) continue;

                    //判断获取的参数是否完整
                    paraCode+=runString;
//					if ( paracode.equals("<"))
//						System.out.println("hhhhhhhh");
                    if ( paracodeIsComplete(paraCode)==false ) {  //不完整的参数变量，删除
                        runs.get(i).setText("", 0);
                        continue;
                    }					
					
					//1.2.替换图片
					replaceImage(paraCode,runs.get(i),params);
					//1.1替换文本
					replaceRun(paraCode,runs.get(i),params);
					paraCode=""; 
				}
			}

			//2.处理表格
			Iterator<XWPFTable> itTable = document.getTablesIterator();
			while (itTable.hasNext()) {
				XWPFTable table = (XWPFTable) itTable.next();

				//////////////////////设置新增的表格边框，搞了很久，终于搞定2022-08-04/////////////////////////////////////////
				table.setTableAlignment(TableRowAlign.CENTER);
				CTTblBorders borders = table.getCTTbl().getTblPr().addNewTblBorders();
				// 设置内边框样式，默认单实线
				// 设置上下左右外边框
				String bolderType = "single";
				CTBorder lBorder = borders.addNewLeft();
				lBorder.setVal(STBorder.Enum.forString(bolderType));
				lBorder.setSz(new BigInteger("1"));
				lBorder.setColor("000000");

				CTBorder rBorder = borders.addNewRight();
				rBorder.setVal(STBorder.Enum.forString(bolderType));
				rBorder.setSz(new BigInteger("1"));
				rBorder.setColor("000000");

				CTBorder tBorder = borders.addNewTop();
				tBorder.setVal(STBorder.Enum.forString(bolderType));
				tBorder.setSz(new BigInteger("1"));
				tBorder.setColor("000000");

				CTBorder bBorder = borders.addNewBottom();
				bBorder.setVal(STBorder.Enum.forString(bolderType));
				bBorder.setSz(new BigInteger("1"));
				bBorder.setColor("000000");
				    
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				
				int rcount = table.getNumberOfRows();
				
				//2.1：特殊的循环表，根据数据条数，动态创建表的行，合并或者拆分行等
				String rulestr = table.getRow(0).getCell(0).getText().trim();
				PoiTableRule rule = null;
				boolean isRule = false;
				try {
					rule = JsonUtils.json2Object(rulestr, PoiTableRule.class);
					if ( rule!=null )
						isRule = true;
				} catch (Exception e ) {
					isRule = false;
				}
				if ( isRule==true && rule!=null && rule.getForeach()!=null && rule.getForeach().length()>0 ) {
					replaceAutoTable(table,params,rule);
				}
				
				//2.2：普通表
				for (int i = 0; i < rcount; i++) {
					XWPFTableRow row = table.getRow(i);
					if ( row==null ) continue;
					List<XWPFTableCell> cells = row.getTableCells();
					for (XWPFTableCell cell : cells) {
						List<XWPFParagraph> paragraphs = cell.getParagraphs();
						for ( XWPFParagraph p:paragraphs ) {
							List<XWPFRun> listRuns = p.getRuns();
							String paraCode="";
							for ( XWPFRun r:listRuns ) {
								if (r==null ) continue;
								    String rtext = r.getText(r.getTextPosition());  //getTextPosition: 取纯文本的起始位置，本行代码取出本run中的纯文本信息（不含控制符信息）
								if ( rtext==null ) continue;
								//判断获取的参数是否完整
								paraCode+=rtext;
								if ( paracodeIsComplete(paraCode)==false ) {  //删除不完整的变量设置，如${xx，但后续没有 }，或者<，后续没有>
									r.setText("", 0);
								    continue;
								}
								replaceRun(paraCode,r, params);
								paraCode=""; 
							}
						}
					}
				}
			}

			document.write(os);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e; //new PoiException("FileTemplateExceptioin",new Throwable("找不到模板或者模板配置错误，请使用office2010格式编辑."),"" );
        }
    }

	/**
	 * 	替换普通的文本
	 * @param run
	 * @param params
	 * @return
	 */
	private static XWPFRun replaceRun(String paraCode,XWPFRun run,Map<String, Object> params ) {
	//	System.out.println("table run1:"+run.toString());
	//	System.out.println("table run2:"+run.getText(run.getTextPosition()));
		String text = paraCode;
		List<String> listExp = new ArrayList<String>();
		String re = "\\$\\{([^\\}]+)\\}";
	    Pattern p = Pattern.compile(re);
	    Matcher m = p.matcher(text);
	    while(m.find()){
	    	listExp.add(m.group(1));
	    }
		if ( listExp.size()>0 ) {
			//text = replaceText(text,listExp,params);
			for ( String str:listExp) {
				String key="${"+str+"}";
				String[] props = str.split("\\.");
				Object v = null;
				Map<String,Object> itrMap = params;
				for ( String prop:props ) {
					v = itrMap.get(prop);
					if( v instanceof Map ) {
						itrMap = (Map)v;
					} else {
						break;
					}
				}
	            if ( v!=null && v instanceof Date  ) {
	            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	            	v = sdf.format(v);
	            }
//	            if ( v!=null && v instanceof BigDecimal  ) {
//	            	v = ((BigDecimal) v).setScale(2, RoundingMode.HALF_DOWN);
//	            }

				text = text.replace(key,v==null?" ":v.toString());
			}
			
			run.setText(text,0);
		}
		return run;
	}
   
	/**
	 * 合并单元格：横向合并
	 * @param table
	 * @param row
	 * @param fromCell
	 * @param toCell
	 */
	public  static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {  
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {  
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);  
            if ( cellIndex == fromCell ) {  
                // The first merged cell is set with RESTART merge value  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);  
            } else {  
                // Cells which join (merge) the first one, are set with CONTINUE  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    }  
	
	/**
	 * 合并单元格：纵向合并，即word表格跨行并单元格
	 * @param table
	 * @param col  指定列
	 * @param fromRow 开始行
	 * @param toRow  结束行。
	 */
    public void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }
    
    /**
     * 替换图片
     * @param run
     * @param pic
     * @throws EchartsException
     */
    private static void replaceImage(String paraCode,XWPFRun run, Map<String, Object> pic) throws EchartsException {
		// 先判断是否是图片标志
		String re = "\\$\\{@image:([^\\}]+)\\}";
		Pattern p = Pattern.compile(re);
		String text = paraCode;
		Matcher m = p.matcher(text);
		List<String> imageList = new ArrayList<>();
		while (m.find()) {
			imageList.add(m.group(1));
		}
		if (imageList.size() > 0) { // 替换图片
			for (String image : imageList) {
				String key = "${@image:" + image + "}";
				//NYLCXP-394 图形化界面预览报错问题，map传值有误 modify by zhengbs
				Map<String,Object> picMap = (Map<String, Object>) pic.get(key);
				int width = Integer.parseInt(picMap.get("width").toString());
				int height = Integer.parseInt(picMap.get("height").toString());
				int picType = getPictureType(picMap.get("type").toString());
				String byteArray = (String) picMap.get("content");

				FileInputStream fis = null;
				try {
					if (byteArray != null)
						fis = new FileInputStream(byteArray);
					run.addPicture(fis, getPictureType("png"), "", Units.toEMU(width), Units.toEMU(height));
					run.setText(" ", 0);
				} catch (Exception e) {
					throw new EchartsException("updateImage", e.getCause(), "");
				} finally {
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

    /**
     * 判断参数代码是否完整，判断标志：
     * 如果前面有<，后面必须有>，否则不完整
     * 或者如果前面有${，后面必须有}，否则不完整
     * 如果都没有，则是完整的字符串
     * @param paracode
     * @return
     */
    private static boolean paracodeIsComplete(String paracode) {
        //boolean ret =false;
        int beginpos = paracode.indexOf('<');
        if ( beginpos!=-1) {  //是关键字"<"
            if ( paracode.substring(beginpos+1).indexOf('>')!=-1 )  //完整啊
                return true;
            else
                return false;
        }else {  //不是关键字"<"
            beginpos = paracode.indexOf("${");
            if ( beginpos!=-1) {  //是关键字"${"
                if ( paracode.substring(beginpos+1).indexOf('}')!=-1 )  //完整
                    return true;
                else
                    return false;
            }else {  //不是关键字
                return true;
            }
        }
    }

    /**
     * 根据图片类型，取得对应的图片类型代码
     *
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = Document.PICTURE_TYPE_PICT;
        if (picType != null) {
            if (picType.equalsIgnoreCase("png")) {
                res = Document.PICTURE_TYPE_PNG;
            } else if (picType.equalsIgnoreCase("dib")) {
                res = Document.PICTURE_TYPE_DIB;
            } else if (picType.equalsIgnoreCase("emf")) {
                res = Document.PICTURE_TYPE_EMF;
            } else if (picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")) {
                res = Document.PICTURE_TYPE_JPEG;
            } else if (picType.equalsIgnoreCase("wmf")) {
                res = Document.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

	/**
	 * 根据规则动态解析表
	 * @param table
	 * @param mapDatas
	 * @param rule {foreach:"listFinIndex",loopmode:"row",fields:["inxNo","empName","empAge","empAmt"],startRow:2,startCol:0}
	 * @return
	 */
	private static int replaceAutoTable (XWPFTable table,Map<String, Object> mapDatas,PoiTableRule rule) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		//规则行，取出list名称
		String listname = rule.getForeach();
		List<Map<String,Object>> listData = (List<Map<String,Object>>)mapDatas.get(listname);

		XWPFTableRow styleRow = table.getRow(rule.getStartRow());
//		System.out.println("==================");
//		for ( int k=0;k<4;k++ ) {
//			System.out.println("水平："+styleRow.getCell(k).getParagraphs().get(0).getAlignment());
//			System.out.println("垂直："+styleRow.getCell(k).getParagraphs().get(0).getVerticalAlignment());
//		}
//		System.out.println("==================");
		String fontFamily = "方正仿宋简体";
		int fontSize = 11;
		if ( styleRow!=null  ) {  //从规则行里面拿格式
			fontFamily = table.getRow(0).getCell(0).getParagraphs().get(0).getRuns().get(0).getFontFamily();
			fontSize = table.getRow(0).getCell(0).getParagraphs().get(0).getRuns().get(0).getFontSize();
		}
		//获取明细列的规则
		if ( "row".equals(rule.getLoopMode()) ) {
			XWPFTableRow templateRow = table.getRow(rule.getStartRow());  //起始行（空白行）作为样板行，并且起始行输入样板字体，否则只能用默认的宋体
			int rowNo = rule.getStartRow();
			for ( int i=0;i<listData.size();i++ ) {
				Map<String,Object> data = listData.get(i);
				XWPFTableRow dataRow = table.insertNewTableRow(rowNo++);
				createCellsAndCopyStyles(dataRow,templateRow,fontFamily,fontSize);
				
				for ( int j=0;j<rule.getFields().size();j++ ) {
					String f = rule.getFields().get(j);
					Object o = (f==null?"null":data.get(f));
					if ( o!=null && o instanceof Date ) {
						o=sdf.format(o);
					}
					String content = (o==null?"":o.toString());
					
					XWPFTableCell cell = dataRow.getCell(j);  //createCell();
					cell.setVerticalAlignment(XWPFVertAlign.CENTER);
					
					XWPFParagraph paragraph = null;
					List<XWPFParagraph> listParagraph = cell.getParagraphs();
					if ( listParagraph.size()>0 ) {
						paragraph = listParagraph.get(0);
					} else {
						paragraph = cell.addParagraph();
					}
					paragraph.setVerticalAlignment(TextAlignment.CENTER);
					
					XWPFRun run = null;
					if ( paragraph.getRuns() !=null && paragraph.getIRuns().size()>0 )
						run = paragraph.getRuns().get(0);
					else
						run= paragraph.createRun();

//					if ( cell.getParagraphs().size()>0 ) 
//						cell.removeParagraph(0);					
					run.setText(content,0);					
				}
			}
			table.removeRow(0);  //删除规则行1
			table.removeRow(rowNo-1);  //删除最后一行
		}
		
		if ( "col".equals(rule.getLoopMode()) && listData!=null ) {
			Iterator itr = listData.iterator();
			int colNo = rule.getStartCol();   //列号
			int tableWidth = table.getRow(0).getCell(0).getWidth();
			int initWidth = table.getRow(rule.getStartRow()).getCell(rule.getStartCol()-1).getWidth();
			int newColWidth = (tableWidth-initWidth)/(listData.size()+1);
			while ( itr.hasNext() ) {
				Map<String,Object> data = (Map)itr.next();  //取出一条数据
				for ( int rowNo=0;rowNo<table.getNumberOfRows();rowNo++ ) {  //
					XWPFTableRow dataRow = table.getRow(rowNo);
					List<XWPFTableCell> cells = dataRow.getTableCells();  
					XWPFTableCell cell = null;           //取出该行的相应单元格
					if ( colNo<cells.size() ) {
						cell = cells.get(colNo);
						cell.setWidth(newColWidth+"");
					} else {
						cell = dataRow.createCell();
						cell.setWidth( newColWidth+"" );
					}
					cell.setVerticalAlignment(XWPFVertAlign.CENTER);
					//填充数据
					if ( rowNo>=rule.getStartRow() ) {  //从指定的行开始填写数据
						String content = "";      //取出各字段
						int fieldNo = rowNo-rule.getStartRow();
						try {
							String f = rule.getFields().get(fieldNo);
							Object o = (f==null?"null":data.get(f));
							if ( o!=null && o instanceof Date ) {
								o=sdf.format(o);
							}
							content = (o==null?"":o.toString());							
						} catch (Exception e) {
							e.printStackTrace();
						}
						if ( cell.getParagraphs().size()>0 ) 
							cell.removeParagraph(0);
						XWPFParagraph paragraph = cell.addParagraph();
						paragraph.setAlignment(styleRow.getCell(1).getParagraphs().get(0).getAlignment());
						paragraph.setVerticalAlignment(TextAlignment.CENTER);
						XWPFRun run = paragraph.createRun();
						run.setFontFamily(fontFamily);
						if ( fontSize!=-1 )
							run.setFontSize( fontSize);			
						run.setText(content,0);			
						//cell.setText(content);
					}
				}
				colNo++;  //每循环一条数据，列号增加1
			}				
			mergeCellsHorizontal(table,1,rule.getStartCol(),listData.size());
			table.removeRow(0);  //删除规则行1
		}
		return listData==null?0:listData.size();
	}

    public static void createCellsAndCopyStyles(XWPFTableRow targetRow, XWPFTableRow sourceRow,String defaultFontFamily,int defaultFontSize) {
        targetRow.getCtRow().setTrPr(sourceRow.getCtRow().getTrPr());
        List<XWPFTableCell> tableCells = sourceRow.getTableCells();
        if (CollectionUtils.isEmpty(tableCells)) {
            return;
        }
        for (XWPFTableCell sourceCell : tableCells) {
            XWPFTableCell newCell = targetRow.addNewTableCell();
            newCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());
            List<XWPFParagraph> sourceParagraphs = sourceCell.getParagraphs();
            if (CollectionUtils.isEmpty(sourceParagraphs)) {
                continue;
            }
            XWPFParagraph sourceParagraph = sourceParagraphs.get(0);
            List<XWPFParagraph> targetParagraphs = newCell.getParagraphs();
            if (CollectionUtils.isEmpty(targetParagraphs)) {
                XWPFParagraph p = newCell.addParagraph();
                p.getCTP().setPPr(sourceParagraph.getCTP().getPPr());
                XWPFRun run = p.getRuns().isEmpty() ? p.createRun() : p.getRuns().get(0);
                if ( sourceParagraph.getRuns()!=null && sourceParagraph.getRuns().size()>0 ) {
                	run.setFontFamily(sourceParagraph.getRuns().get(0).getFontFamily());
                	int fontSzie = sourceParagraph.getRuns().get(0).getFontSize();
					if ( fontSzie!=-1 )
						run.setFontSize(fontSzie);                	
                }
                else {
                	run.setFontFamily(defaultFontFamily);
                	if ( defaultFontSize!=-1 )
                		run.setFontSize(defaultFontSize);
                }
            } else {
                XWPFParagraph p = targetParagraphs.get(0);
                p.getCTP().setPPr(sourceParagraph.getCTP().getPPr());
                XWPFRun run = p.getRuns().isEmpty() ? p.createRun() : p.getRuns().get(0);
                if ( sourceParagraph.getRuns()!=null && sourceParagraph.getRuns().size()>0 ) {
                	run.setFontFamily(sourceParagraph.getRuns().get(0).getFontFamily());
                	int fontSzie = sourceParagraph.getRuns().get(0).getFontSize();
					if ( fontSzie!=-1 )
						run.setFontSize(fontSzie);                	
                }
                else {
                	run.setFontFamily(defaultFontFamily);
                	if ( defaultFontSize!=-1 )
                		run.setFontSize(defaultFontSize);
                }
            }
        }
    }
}
