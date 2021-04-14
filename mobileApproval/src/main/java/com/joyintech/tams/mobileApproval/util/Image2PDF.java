package com.joyintech.tams.mobileApproval.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class Image2PDF {


	/***
	 * @param picturePath
	 *            图片地址
	 */
	private static void createPic(Document document, String picturePath) {
		try {
			// Image image = Image.getInstance(picturePath);
			// float documentWidth = document.getPageSize().getWidth()
			// - document.leftMargin() - document.rightMargin();
			// float documentHeight = documentWidth / 580 * 320;// 重新设置宽高
			// image.scaleAbsolute(documentWidth, documentHeight);// 重新设置宽高\
			Image image = Image.getInstance(picturePath);
			float heigth = image.getHeight();
			float width = image.getWidth();
			int percent = getPercent2(heigth, width);
			image.setAlignment(Image.MIDDLE);
			image.scalePercent(percent + 3);// 表示是原来图像的比例;
			document.add(image);
		} catch (Exception ex) {
		}
	}

	private static void image2pdf(String text, String pdf)
			throws DocumentException, IOException {
		Document document = new Document();
		OutputStream os = new FileOutputStream(new File(pdf));
		PdfWriter.getInstance(document, os);
		document.open();
		createPic(document, text);
		document.close();
	}

	/**
	 * 图片转pdf文件
	 * 
	 * @param inputFilePath
	 *            图片地址
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static String imageToPDF(String inputFilePath) throws IOException,
			DocumentException {
		String outputFilePath = getOutputFilePath(inputFilePath);
		if (StringUtils.isEmpty(inputFilePath)) {
			return null;
		}
		File inputFile = new File(inputFilePath);
		File file = new File(outputFilePath);
		if (file.exists()) {
			return outputFilePath;
		}
		if (!inputFile.exists()) {
			return null;
		}
		image2pdf(inputFilePath, outputFilePath);
		return outputFilePath;
	}

	/**
	 * 获取inputFilePath的后缀名，如："e:/test.png"的后缀名为："png"
	 * 
	 * @param inputFilePath
	 * @return
	 */
	private static String getPostfix(String inputFilePath) {
		return inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
	}

	/**
	 * 获取输出文件
	 * 
	 * @param inputFilePath
	 * @return
	 */
	private static String getOutputFilePath(String inputFilePath) {
		String outputFilePath = inputFilePath.replaceAll("."
				+ getPostfix(inputFilePath), ".pdf");
		return outputFilePath;
	}

	/**
	 * 根据传入的文件后缀判断是否图片格式(jpg,png)
	 * 
	 * @param filePostFix
	 * @return
	 */
	public static boolean isPicture(String filePostFix) {
		List<String> pictureFormat = new ArrayList<String>();
		pictureFormat.add("png");
		pictureFormat.add("jpg");

		return pictureFormat.contains(filePostFix);
	}

	/**
	 * 第一种解决方案 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩
	 * 
	 * @param h
	 * @param w
	 * @return
	 */

	@SuppressWarnings("unused")
	private static int getPercent(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		if (h > w) {
			p2 = 297 / h * 100;
		} else {
			p2 = 210 / w * 100;
		}
		p = Math.round(p2);
		return p;
	}

	/**
	 * 第二种解决方案，统一按照宽度压缩 这样来的效果是，所有图片的宽度是相等的
	 * 
	 * @param args
	 */
	private static int getPercent2(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		p2 = 530 / w * 100;
		p = Math.round(p2);
		return p;
	}
}