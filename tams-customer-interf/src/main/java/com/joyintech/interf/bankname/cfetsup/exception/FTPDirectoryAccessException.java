package com.joyintech.interf.bankname.cfetsup.exception;

/**
 * FTPDirectoryAccessException.
 */
public class FTPDirectoryAccessException extends Exception {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * FTPDirectoryAccessException.
	 */
	public FTPDirectoryAccessException() {
		super();
	}

	/**
	 * FTPDirectoryAccessException.
	 * 
	 * @param msg
	 *            String
	 */
	public FTPDirectoryAccessException(String msg) {
		super(msg);
	}

	/**
	 * FTPDirectoryAccessException.
	 * 
	 * @param e
	 *            Throwable
	 */
	public FTPDirectoryAccessException(Throwable e) {
		super(e);
	}

	/**
	 * FTPDirectoryAccessException.
	 * 
	 * @param msg
	 *            String
	 * @param e
	 *            Throwable
	 */
	public FTPDirectoryAccessException(String msg, Throwable e) {
		super(msg, e);
	}

}
