package org.dynamic.config.model;

import java.util.HashMap;
import java.util.Map;

public class ConfigDetails {
	Map<String, String> meta_data = new HashMap<String, String>();
	Map<String, String> properties = new HashMap<String, String>();
	/**
	 * @return the meta_data
	 */
	public Map<String, String> getMeta_data() {
		return meta_data;
	}
	/**
	 * @param meta_data the meta_data to set
	 */
	public void setMeta_data(Map<String, String> meta_data) {
		this.meta_data = meta_data;
	}
	/**
	 * @return the properties
	 */
	public Map<String, String> getProperties() {
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
}
