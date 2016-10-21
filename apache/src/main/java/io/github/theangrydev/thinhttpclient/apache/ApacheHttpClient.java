/*
 * Copyright 2016 Liam Williams <liam.williams@zoho.com>.
 *
 * This file is part of thin-http-client.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.theangrydev.thinhttpclient.apache;

import io.github.theangrydev.thinhttpclient.core.*;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import static io.github.theangrydev.thinhttpclient.core.HeaderName.CONTENT_TYPE;
import static io.github.theangrydev.thinhttpclient.core.Headers.headers;
import static io.github.theangrydev.thinhttpclient.core.Response.response;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class ApacheHttpClient implements HttpClient {

    private final CloseableHttpClient httpClient;

    private ApacheHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static ApacheHttpClient apacheHttpClient() {
        return new ApacheHttpClient(HttpClientBuilder.create().build());
    }

    @Override
    public Response execute(Request request) throws IOException {
        HttpRequest apacheRequest = HttpRequest.httpRequest(request.url, request.method, request.body, request.header(CONTENT_TYPE));
        try (CloseableHttpResponse apacheResponse = httpClient.execute(apacheRequest)) {
            return adaptResponse(apacheResponse);
        }
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    private Response adaptResponse(CloseableHttpResponse apacheResponse) throws IOException {
        StatusLine statusLine = apacheResponse.getStatusLine();
        return response(adaptHeaders(apacheResponse), statusLine.getStatusCode(), adaptBody(apacheResponse));
    }

    private String adaptBody(CloseableHttpResponse apacheResponse) throws IOException {
        return EntityUtils.toString(apacheResponse.getEntity(), UTF_8);
    }

    private Headers adaptHeaders(CloseableHttpResponse apacheResponse) {
        return headers(stream(apacheResponse.getAllHeaders()).map(this::adaptHeader).collect(toList()));
    }

    private Header adaptHeader(org.apache.http.Header header) {
        return Header.header(header.getName(), adaptHeaderValue(header.getValue()));
    }

    private String adaptHeaderValue(String value) {
        if (value == null) {
            return "";
        } else {
            return value;
        }
    }

    private static final class HttpRequest extends HttpEntityEnclosingRequestBase {

        private final Method method;

        private HttpRequest(Method method) {
            this.method = method;
        }

        public static HttpRequest httpRequest(URL url, Method method, String body, String contentType) {
            HttpRequest httpRequest = new HttpRequest(method);
            httpRequest.setURI(URI.create(url.toExternalForm()));
            if (!body.isEmpty()) {
                httpRequest.setEntity(new StringEntity(body, ContentType.parse(contentType)));
            }
            return httpRequest;
        }

        @Override
        public String getMethod() {
            return method.toString();
        }
    }
}
