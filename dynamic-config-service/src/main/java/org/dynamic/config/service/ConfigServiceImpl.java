package org.dynamic.config.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.dynamic.config.exception.ConfigServiceException;
import org.dynamic.config.model.ConfigDetails;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigServiceImpl implements ConfigService {

	private String host;
	private String port;

	/** max number of connections allowed */
	private int maxConnections;

	/** max size of request queue */
	private int requestQueueSize;

	/** The HTTP proxy handler map */
	private Map<String, String> proxyMap = new HashMap<String, String>();

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	public Map<String, String> getProxyMap(String projectId)
			throws ConfigServiceException {
		// TODO Make external configuration service call to get max connection
		// settings
		// Key configKey = buildKey(projectId, configParamName);
		// try{
		// proxyMap = getExtConfigValue(configKey);
		// }catch (Exception e){
		// throw new ConfigServiceException("SomeCode", e.getMessage());
		// }
		return proxyMap;
	}

	public String getConfigValue(String projectId, String configParam,
			String paramType) throws ConfigServiceException {

		// TODO Make external call to the injected client
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = null;
		ConfigDetails configDetails = new ConfigDetails();
		HttpGet getRequest = new HttpGet(
				host
						+ ":"
						+ port
						+ "/v1/appconfig/projects/FaultCanister/configurations/TRIAL/versions/1.00?changeset_id=LATEST");
		try {
			response = httpClient.execute(getRequest);
			ObjectMapper mapper = new ObjectMapper();
			configDetails = mapper.readValue(new InputStreamReader(response
					.getEntity().getContent()), ConfigDetails.class);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (TYPE_META_DATA.equalsIgnoreCase(paramType)) {
			// return "1";
			return configDetails.getMeta_data().get(configParam);
		} else {
			// return "4";
			return configDetails.getProperties().get(configParam);
		}
	}

}
