package org.dynamic.config.service;

import java.util.Map;

import org.dynamic.config.exception.ConfigServiceException;

public interface ConfigService {
	
	//TODO: Code in such a way that developers can debug easily so provide as much info about the exception as possible
	public static final String CONFIG_SERVER_NOT_REACHABLE = "Config.Service.Host.Unreachable";
	
	public String ACTIVE_CHANGELIST_ID = "active_changeset_id";
	public String MAX_CONNECTIONS = "maxConnections";
	public String REQUEST_QUEUE_SIZE = "requestQueueSize";
	public String TYPE_META_DATA = "metadata";
	public String TYPE_PROPERTY = "property";
	
	/**
	 * Get active changelist id from external configuration service
	 * @return int config version
	 * @throws ConfigServiceException 
	 */
	public String getConfigValue(String projectId, String configParam, String paramType)
			throws ConfigServiceException;
	
    /**
     * Returns the proxy map from external configuration service
     * @throws ConfigServiceException
     * @return Map config value
     */
	public Map<String, String> getProxyMap(String projectId) throws ConfigServiceException;


	
	
    
    
}
