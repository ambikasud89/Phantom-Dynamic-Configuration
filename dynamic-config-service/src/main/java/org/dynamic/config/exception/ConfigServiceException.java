package org.dynamic.config.exception;

/**
 * Exception class wraps all service exceptions as enclosing classes just have to handle this exception
 * 
 * @author sushantasahoo1
 *
 */
public class ConfigServiceException extends Exception {

	/**
	 * Error code
	 */

	private String errCode;

	/**
	 * Error message
	 */
	private String msg;

	public ConfigServiceException(String errCode, String msg) {

		this.errCode = errCode;
		this.msg = msg;
	}

}
