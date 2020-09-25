package com.joyintech.interf.bankname.cfetsup.exception;

/**
 * FTPLoginException.
 */
public class FTPLoginException extends Exception {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * FTPLoginException.
	 */
	public FTPLoginException() {
		super();
	}

	/**
	 * FTPLoginException.
	 * 
	 * @param msg
	 *            String
	 */
	public FTPLoginException(String msg) {
		super(msg);
	}

	/**
	 * FTPLoginException.
	 * 
	 * @param e
	 *            Throwable
	 */
	public FTPLoginException(Throwable e) {
		super(e);
	}

	/**
	 * FTPLoginException.
	 * 
	 * @param msg
	 *            String
	 * @param e
	 *            Throwable
	 */
	public FTPLoginException(String msg, Throwable e) {
		super(msg, e);
	}

}
