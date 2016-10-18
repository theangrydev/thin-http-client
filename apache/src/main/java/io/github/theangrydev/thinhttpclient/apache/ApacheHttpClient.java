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

import io.github.theangrydev.thinhttpclient.core.HttpClient;
import io.github.theangrydev.thinhttpclient.core.Method;
import io.github.theangrydev.thinhttpclient.core.Request;
import io.github.theangrydev.thinhttpclient.core.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ApacheHttpClient implements HttpClient {

    private final CloseableHttpClient httpClient;

    public ApacheHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static ApacheHttpClient apacheHttpClient() {
        return new ApacheHttpClient(HttpClientBuilder.create().build());
    }

    @Override
    public Response execute(Request request) throws IOException {
        HttpRequest apacheRequest = new HttpRequest(request.uri, request.method);
        try (CloseableHttpResponse apacheResponse = httpClient.execute(apacheRequest)) {
            String body = EntityUtils.toString(apacheResponse.getEntity(), UTF_8);
            return new Response(body);
        }
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    private static final class HttpRequest extends HttpRequestBase {

        private final Method method;

        HttpRequest(URI uri, Method method) {
            this.method = method;
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return method.toString();
        }
    }
}
