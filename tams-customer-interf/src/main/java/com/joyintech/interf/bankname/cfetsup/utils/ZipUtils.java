package com.joyintech.interf.bankname.cfetsup.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;

public class ZipUtils {

	public static void unZip(File zipFile, String outPutDir) throws IOException, ArchiveException {
		File input = zipFile;
		InputStream is = new FileInputStream(input);
			ArchiveInputStream in = new ArchiveStreamFactory()
					.createArchiveInputStream("zip", is);

			ZipArchiveEntry entry = (ZipArchiveEntry) in.getNextEntry();
			OutputStream out = new FileOutputStream(new File(outPutDir,
					new String(entry.getRawName(), "UTF-8")));
			try {
				IOUtils.copy(in, out);
			} catch (IOException e) {
				throw e;
			} finally {
				if (out != null) {
					out.close();
				}
				if (is != null) {
					is.close();
				}
			}
	}
}