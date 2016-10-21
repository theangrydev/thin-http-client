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

import java.net.URL;
import java.util.Objects;

import static io.github.theangrydev.thinhttpclient.core.Method.GET;
import static io.github.theangrydev.thinhttpclient.core.Method.POST;

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
     * The HTTP header fields that will be sent.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.2">RFC 2616 HTTP/1.1 4.2 Message Headers</a>
     */
    public final Headers headers;

    public String header(String name) {
        return headers.value(name);
    }

    /**
     * Start building a {@link Method#GET} request.
     *
     * @return A {@link RequestBuilder} that has the {@link #method} set to {@link Method#GET}
     * and the {@link #body} set to the empty {@link String}.
     */
    public static RequestBuilder get() {
        return new RequestBuilder().method(GET);
    }

    public static RequestBuilder post() {
        return new RequestBuilder().method(POST);
    }

    /**
     * Start building a request.
     *
     * @return A {@link RequestBuilder} with no fields set.
     */
    public static RequestBuilder builder() {
        return new RequestBuilder();
    }

    /**
     * Make a copy of this {@link Request} and use it to start building a new one.
     *
     * @return A {@link RequestBuilder} with fields set to the values of the fields in this {@link Request}.
     */
    public RequestBuilder modify() {
        return new RequestBuilder().method(method).url(url);
    }

    private Request(URL url, Method method, String body, Headers headers) {
        this.url = url;
        this.method = method;
        this.body = body;
        this.headers = headers;
    }

    static Request request(URL url, Method method, String body, Headers headers) {
        return new Request(url, method, body, headers);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Request request = (Request) other;
        return Objects.equals(url, request.url) &&
                Objects.equals(method, request.method) &&
                Objects.equals(body, request.body) &&
                Objects.equals(headers, request.headers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, method, body, headers);
    }
}
