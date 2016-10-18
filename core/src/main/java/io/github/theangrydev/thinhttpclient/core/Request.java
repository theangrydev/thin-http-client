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
import java.net.URL;

import static io.github.theangrydev.thinhttpclient.core.Method.GET;
import static java.lang.String.format;

/**
 * This class represents a HTTP/1.1 request.
 *
 * @see <a href="https://tools.ietf.org/html/rfc2616">RFC 2616 HTTP/1.1</a>
 */
public final class Request {

    /**
     * This is the URI that identifies the resource that the request is for.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-3.2.2">RFC 2616 HTTP/1.1 3.2.2 http URL</a>
     */
    public final URL url;

    /**
     * The HTTP Method that the request is for.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-5.1.1">RFC 2616 HTTP/1.1 5.1.1 Method</a>
     * @see <a href="https://tools.ietf.org/html/rfc5789">RFC 5789 PATCH Method for HTTP</a>
     */
    public final Method method;

    /**
     * The HTTP message-body that will be sent.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.3">RFC 2616 HTTP/1.1 4.3 Message Body</a>
     */
    public final String body;

    /**
     * Start building a request.
     *
     * @return A {@link RequestBuilder} with no fields set.
     */
    public static RequestBuilder builder() {
        return new RequestBuilder();
    }

    /**
     * Start building a {@link Method#GET} request.
     *
     * @return A {@link RequestBuilder} that has the {@link #method} set to {@link Method#GET}
     * and the {@link #body} set to the empty {@link String}.
     */
    public static RequestBuilder get() {
        return new RequestBuilder().method(GET).body("");
    }

    /**
     * Make a copy of this {@link Request} and use it to start building a new one.
     *
     * @return A {@link RequestBuilder} with fields set to the values of the fields in this {@link Request}.
     */
    public RequestBuilder modify() {
        return new RequestBuilder().method(method).url(url);
    }

    private Request(URL url, Method method, String body) {
        this.url = url;
        this.method = method;
        this.body = body;
    }

    private static Request request(URL url, Method method, String body) {
        return new Request(url, method, body);
    }

    /**
     * This is a builder for {@link Request} objects.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern#Java_example">The Builder Pattern</a>
     */
    public static class RequestBuilder {

        private URL url;
        private Method method;
        private String body;

        /**
         * Set the HTTP Method.
         *
         * @param method The Method to set.
         * @return This {@link RequestBuilder}.
         * @see <a href="https://tools.ietf.org/html/rfc2616#section-5.1.1">RFC 2616 HTTP/1.1 5.1.1 Method</a>
         * @see <a href="https://tools.ietf.org/html/rfc5789">RFC 5789 PATCH Method for HTTP</a>
         */
        public RequestBuilder method(Method method) {
            this.method = method;
            return this;
        }

        /**
         * Set the URL.
         *
         * @param url The URL to set
         * @return This {@link RequestBuilder}.
         * @see <a href="https://tools.ietf.org/html/rfc2616#section-3.2.2">RFC 2616 HTTP/1.1 3.2.2 http URL</a>
         */
        public RequestBuilder url(URL url) {
            this.url = url;
            return this;
        }

        /**
         * Set the URL after parsing the {@code url} as a {@link URL}.
         *
         * @param url The URL to set
         * @return This {@link RequestBuilder}.
         * @throws IllegalArgumentException If no protocol is specified, or an unknown protocol is found, or {@code url} is {@code null}.
         * @see <a href="https://tools.ietf.org/html/rfc2616#section-3.2.2">RFC 2616 HTTP/1.1 3.2.2 http URL</a>
         */
        public RequestBuilder url(String url) {
            try {
                return url(new URL(url));
            } catch (MalformedURLException exception) {
                throw new IllegalArgumentException(exception.getMessage(), exception);
            }
        }

        /**
         * Set the request body.
         *
         * @param body The request body to set.
         * @return This {@link RequestBuilder}.
         * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.3">RFC 2616 HTTP/1.1 4.3 Message Body</a>
         */
        public RequestBuilder body(String body) {
            this.body = body;
            return this;
        }

        /**
         * Construct a {@link Request} with the fields that have been accumulated.
         * The required fields are URI, Method and Body.
         *
         * @return The {@link Request} if all the required fields were set.
         * @throws IllegalArgumentException If any of the required fields were not set.
         */
        public Request build() {
            checkFieldWasSet(url, "URI");
            checkFieldWasSet(method, "Method");
            checkFieldWasSet(body, "Body");
            return request(url, method, body);
        }

        private static void checkFieldWasSet(Object field, String fieldName) {
            if (field == null) {
                throw new IllegalStateException(format("%s was not set!", fieldName));
            }
        }
    }
}
