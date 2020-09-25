package com.joyintech.interf.bankname.cfetsup.exception;

/**
 * FTPUploadException.
 */
public class FTPUploadException extends Exception {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * FTPUploadException.
	 */
	public FTPUploadException() {
		super();
	}

	/**
	 * FTPUploadException.
	 * 
	 * @param msg
	 *            String
	 */
	public FTPUploadException(String msg) {
		super(msg);
	}

	/**
	 * FTPUploadException.
	 * 
	 * @param e
	 *            Throwable
	 */
	public FTPUploadException(Throwable e) {
		super(e);
	}

	/**
	 * FTPUploadException.
	 * 
	 * @param msg
	 *            String
	 * @param e
	 *            Throwable
	 */
	public FTPUploadException(String msg, Throwable e) {
		super(msg, e);
	}

}
