package com.joyintech.interf.bankname.cfetsup.exception;

/**
 * FTPDownloadException.
 */
public class FTPDownloadException extends Exception {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * FTPDownloadException.
	 */
	public FTPDownloadException() {
		super();
	}

	/**
	 * FTPDownloadException.
	 * 
	 * @param msg
	 *            String
	 */
	public FTPDownloadException(String msg) {
		super(msg);
	}

	/**
	 * FTPDownloadException.
	 * 
	 * @param e
	 *            Throwable
	 */
	public FTPDownloadException(Throwable e) {
		super(e);
	}

	/**
	 * FTPDownloadException.
	 * 
	 * @param msg
	 *            String
	 * @param e
	 *            Throwable
	 */
	public FTPDownloadException(String msg, Throwable e) {
		super(msg, e);
	}

}
