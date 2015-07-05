package com.flipkart.phantom.runtime.impl.server.netty.handler.http;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UriRoutingHttpChannelHandlerWrapper extends RoutingHttpChannelHandler implements ApplicationContextAware{

	private UriRoutingHttpChannelHandler uriHandler;
	/** The ApplicationContext instance for instantiating ChannelHandlers*/
	private ApplicationContext applicationContext;
	private final String URI_ROUTING_CHANNEL_HANDLER = "UriRoutingHttpChannelHandler";
	@Override
	protected String getRoutingKey(HttpRequest request) {
		//check until handler is reinitializing 
		while(uriHandler==null){
			uriHandler = (UriRoutingHttpChannelHandler) applicationContext.getBean(URI_ROUTING_CHANNEL_HANDLER);
			if(uriHandler==null){
				try {
					Thread.sleep(10);
					continue;
				} catch (InterruptedException e) {
					continue;
				}
				
			}else{
				System.out.println(":::::::::::::::::::::::  proxymap: "+uriHandler.getProxyMap().toString());
			}
			
			
		}
		return uriHandler.getRoutingKey(request);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	public void reInitializeHandler(){
		uriHandler = (UriRoutingHttpChannelHandler) applicationContext.getBean(URI_ROUTING_CHANNEL_HANDLER);
	}

	public UriRoutingHttpChannelHandler getUriHandler() {
		return uriHandler;
	}

	public void setUriHandler(UriRoutingHttpChannelHandler uriHandler) {
		this.uriHandler = uriHandler;
	}

}
