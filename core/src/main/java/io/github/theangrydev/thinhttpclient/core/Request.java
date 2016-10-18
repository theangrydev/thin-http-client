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
package io.github.theangrydev.thinhttpclient.core;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static io.github.theangrydev.thinhttpclient.core.Method.GET;
import static java.lang.String.format;

public final class Request {

    public final URI uri;
    public final Method method;

    private Request(URI uri, Method method) {
        this.uri = uri;
        this.method = method;
    }

    private static Request request(URI uri, Method method) {
        return new Request(uri, method);
    }

    public static RequestBuilder builder() {
        return new RequestBuilder();
    }

    public static RequestBuilder get() {
        return new RequestBuilder().method(GET);
    }

    public RequestBuilder modify() {
        return new RequestBuilder().method(method).uri(uri);
    }

    public static class RequestBuilder {

        private URI uri;
        private Method method;

        public RequestBuilder method(Method method) {
            this.method = method;
            return this;
        }

        public RequestBuilder uri(URI uri) {
            this.uri = uri;
            return this;
        }

        public RequestBuilder url(URL url) {
            try {
                return uri(url.toURI());
            } catch (URISyntaxException exception) {
                throw new IllegalArgumentException(exception.getMessage(), exception);
            }
        }

        public RequestBuilder url(String url) {
            try {
                return url(new URL(url));
            } catch (MalformedURLException exception) {
                throw new IllegalArgumentException(exception.getMessage(), exception);
            }
        }

        public Request build() {
            checkFieldWasSet(uri, "URI");
            checkFieldWasSet(method, "Method");
            return request(uri, method);
        }

        private static void checkFieldWasSet(Object field, String fieldName) {
            if (field == null) {
                throw new IllegalStateException(format("%s was not set!", fieldName));
            }
        }
    }
}
