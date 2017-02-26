package com.diogenes.busyflights.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class LogRequestResponse implements ClientHttpRequestInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LogRequestResponse.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		// logs before calling
		logRequest(request, body);

		ClientHttpResponse clientHttpResponse = execution.execute(request, body);

		// logs after calling
		logResponse(clientHttpResponse);

		return clientHttpResponse;
	}

	private void logRequest(HttpRequest request, byte[] body) throws IOException {
		for (String s : request.getHeaders().keySet()) {
			logger.debug("request Header " + s + ":" + request.getHeaders().getValuesAsList(s));
		}
		logger.debug("request URL : " + request.getURI());
		logger.debug("request method : " + request.getMethod());
	}

	private void logResponse(ClientHttpResponse response) throws IOException {
		for (String s : response.getHeaders().keySet()) {
			logger.debug("response Header " + s + ":" + response.getHeaders().getValuesAsList(s));
		}
		logger.debug("response status code: " + response.getStatusCode());
		logger.debug("response status text: " + response.getStatusText());
	}

}
