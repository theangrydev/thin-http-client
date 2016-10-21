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

import java.util.Objects;

/**
 * This enumerates the possible HTTP/1.1 Method tokens according to RFC 2616 and RFC 5789.
 *
 * @see <a href="https://tools.ietf.org/html/rfc2616#section-5.1.1">RFC 2616 HTTP/1.1 5.1.1 Method</a>
 * @see <a href="https://tools.ietf.org/html/rfc5789">RFC 5789 PATCH Method for HTTP</a>
 */
public final class Method {

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-9.2">RFC 2616 HTTP/1.1 9.2 OPTIONS</a>
     */
    public static final Method OPTIONS = Method.method("OPTIONS");

    /**
     * @see <a href=" https://tools.ietf.org/html/rfc2616#section-9.3">RFC 2616 HTTP/1.1 9.3 GET</a>
     */
    public static final Method GET = Method.method("GET");

    /**
     * @see <a href=" https://tools.ietf.org/html/rfc2616#section-9.4">RFC 2616 HTTP/1.1 9.4 HEAD</a>
     */
    public static final Method HEAD = Method.method("HEAD");

    /**
     * @see <a href=" https://tools.ietf.org/html/rfc2616#section-9.5">RFC 2616 HTTP/1.1 9.5 POST</a>
     */
    public static final Method POST = Method.method("POST");

    /**
     * @see <a href=" https://tools.ietf.org/html/rfc2616#section-9.6">RFC 2616 HTTP/1.1 9.6 PUT</a>
     */
    public static final Method PUT = Method.method("PUT");

    /**
     * @see <a href=" https://tools.ietf.org/html/rfc2616#section-9.7">RFC 2616 HTTP/1.1 9.7 DELETE</a>
     */
    public static final Method DELETE = Method.method("DELETE");

    /**
     * @see <a href=" https://tools.ietf.org/html/rfc2616#section-9.8">RFC 2616 HTTP/1.1 9.8 TRACE</a>
     */
    public static final Method TRACE = Method.method("TRACE");

    /**
     * @see <a href=" https://tools.ietf.org/html/rfc2616#section-9.9">RFC 2616 HTTP/1.1 9.9 CONNECT</a>
     */
    public static final Method CONNECT = Method.method("CONNECT");

    /**
     * @see <a href="https://tools.ietf.org/html/rfc5789">RFC 5789 PATCH Method for HTTP</a>
     */
    public static final Method PATCH = Method.method("PATCH");


    private final String name;

    private Method(String name) {
        this.name = name;
    }

    public static Method method(String name) {
        return new Method(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Method method = (Method) other;
        return Objects.equals(name, method.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
