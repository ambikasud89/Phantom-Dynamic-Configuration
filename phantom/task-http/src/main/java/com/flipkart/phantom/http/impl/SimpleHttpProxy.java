/*
 * Copyright 2012-2015, the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flipkart.phantom.http.impl;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

/**
 * A simple implementation for the HttpProxy abstract class
 *
 * @author kartikbu
 * @created 16/7/13 1:54 AM
 * @version 1.0
 */
public class SimpleHttpProxy extends HttpProxy {

	/**
	 * Abstract method implementation
	 * 
	 * @see com.flipkart.phantom.http.impl.HttpProxy#fallbackRequest(HttpRequestWrapper)
	 */
	@Override
    public HttpResponse fallbackRequest(HttpRequestWrapper requestWrapper) {
		//Sushanta: Example fall back implementation
    	//Sushanta: Call a fallback service or show a warning page
    	//Sushanta: Returning a dummy response for now
    	BasicStatusLine stLine = new BasicStatusLine(new ProtocolVersion(requestWrapper.getProtocol()
    																	,requestWrapper.getMajorVersion()
    																	,requestWrapper.getMinorVersion())
    												,HttpStatus.SC_REQUEST_TIMEOUT
    												,"Enjoy coffee for sometime and come back");
    	HttpResponse response = new BasicHttpResponse(stLine);
    	System.out.println("Status==========="+response.getStatusLine().getStatusCode());
    	return response;
    	//throw new UnsupportedOperationException("No fallback implementation found!");
    }

	/**
	 * Abstract method implementation
	 * 
	 * @return String Group name
	 */
	@Override
	public String getGroupKey() {
		return "SimpleHttpProxy";
	}

	/**
	 * Abstract method implementation
	 * 
	 * @return String Command name
	 */
	@Override
	public String getCommandKey() {
		return this.getName() + "HttpPool";
	}

	/**
	 * Abstract method implementation
	 * 
	 * @return String Thread pool name
	 */
	@Override
	public String getThreadPoolKey() {
		return "SimpleHttpThreadPool";
	}

}
