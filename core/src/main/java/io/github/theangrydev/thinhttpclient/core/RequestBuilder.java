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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static io.github.theangrydev.thinhttpclient.core.HeaderName.CONTENT_TYPE;
import static io.github.theangrydev.thinhttpclient.core.Headers.headers;
import static java.lang.String.format;

/**
 * This is a builder for {@link Request} objects.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern#Java_example">The Builder Pattern</a>
 */
@SuppressWarnings("PMD.TooManyMethods") // This is by design
public class RequestBuilder {

    private final List<Header> headers = new ArrayList<>();
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
    public RequestBuilder body(String body, MediaType mediaType) {
        return body(body).header(CONTENT_TYPE, mediaType.toString());
    }

    /**
     * Set the request body.
     *
     * @param body The request body to set.
     * @return This {@link RequestBuilder}.
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.3">RFC 2616 HTTP/1.1 4.3 Message Body</a>
     */
    public RequestBuilder body(String body, MediaType mediaType, Charset charset) {
        return body(body).header(CONTENT_TYPE, mediaType + "; charset=" + charset);
    }

    public RequestBuilder noBody() {
        return body("").removeHeader(CONTENT_TYPE);
    }

    private RequestBuilder removeHeader(String headerName) {
        headers.removeIf(header -> headerName.equals(header.name));
        return this;
    }

    private RequestBuilder body(String body) {
        this.body = body;
        return this;
    }

    public RequestBuilder header(String name, String value) {
        headers.add(Header.header(name, value));
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
        checkBodyIsEmptyForMethodsWithNoBody();
        return Request.request(url, method, body, headers(headers));
    }

    private void checkBodyIsEmptyForMethodsWithNoBody() {
        if (!method.hasBody && !body.isEmpty()) {
            throw new IllegalStateException(format("Method '%s' should not have a body!", method));
        }
    }

    private static void checkFieldWasSet(Object field, String fieldName) {
        if (field == null) {
            throw new IllegalStateException(format("%s was not set!", fieldName));
        }
    }
}
