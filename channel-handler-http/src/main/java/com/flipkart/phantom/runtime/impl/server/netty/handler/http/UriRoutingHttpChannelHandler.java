package com.flipkart.phantom.runtime.impl.server.netty.handler.http;

import org.jboss.netty.handler.codec.http.HttpRequest;

public class UriRoutingHttpChannelHandler extends RoutingHttpChannelHandler{

	@Override
	protected String getRoutingKey(HttpRequest request) {
		String[] uriArray = request.getUri().split("/");
		return uriArray[2];
	}

}
