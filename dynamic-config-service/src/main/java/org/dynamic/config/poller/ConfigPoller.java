package org.dynamic.config.poller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.dynamic.config.exception.ConfigServiceException;
import org.dynamic.config.service.ConfigService;

/**
 * Ambika: Poller class for intermittent polling to external config service and
 * refresh all config objects
 */
public class ConfigPoller implements Runnable {
	private String activeChangelistId = "";
	Map<Object, String> refreshObjectMap = new HashMap<Object, String>();
	Method method;
	private ConfigService configService;

	public ConfigPoller(ConfigService configService,
			Map<Object, String> refreshObjectMap) {
		this.configService = configService;
		this.refreshObjectMap = refreshObjectMap;
	}

	private void poll() {
		try {
			String latestChangelistId = configService.getConfigValue(
					"FaultCanister", configService.ACTIVE_CHANGELIST_ID,
					ConfigService.TYPE_META_DATA);

			while (!activeChangelistId.equalsIgnoreCase(latestChangelistId)) {
				activeChangelistId = latestChangelistId;
				// if new configuration found, reinitialize all config objects
				for (Object object : refreshObjectMap.keySet()) {
					method = object.getClass().getMethod(
							refreshObjectMap.get(object));
					method.invoke(object);
				}

			}
		} catch (ConfigServiceException e) {
			// Log error and retry after 30 seconds
			// Logger.warn("Could not connect to config service at time:"+
			// System.currentTimeMillis());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {

		// Poll external config service to check config version every 30
		// seconds
		while (true) {
			// First sleep for 1 sec, no need to check update for first time
			// initialization
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Log error
				// Logger.warn("Thread inturrupted, may cause early config change");
			}
			poll();
		}

		// TODO Auto-generated method stub

	}
}