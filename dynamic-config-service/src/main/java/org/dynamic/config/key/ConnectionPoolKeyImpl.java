package org.dynamic.config.key;

public class ConnectionPoolKeyImpl implements Key {

	/**
	 * The pool name
	 */
	private String poolName;

	public ConnectionPoolKeyImpl(String poolName) {
		this.poolName = poolName;
	}

	public String getKeyString(String paramName) {
		// Some key generation logic
		String key = poolName + "." + paramName;
		return key;
	}

}
