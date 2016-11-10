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
 * distributed under the License is distributed on an "AS IS"BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.theangrydev.thinhttpclient.api;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

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
    public static final Method OPTIONS = new Method("OPTIONS", false);

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-9.3">RFC 2616 HTTP/1.1 9.3 GET</a>
     */
    public static final Method GET = new Method("GET", false);

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-9.4">RFC 2616 HTTP/1.1 9.4 HEAD</a>
     */
    public static final Method HEAD = new Method("HEAD", false);

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-9.5">RFC 2616 HTTP/1.1 9.5 POST</a>
     */
    public static final Method POST = new Method("POST", true);

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-9.6">RFC 2616 HTTP/1.1 9.6 PUT</a>
     */
    public static final Method PUT = new Method("PUT", true);

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-9.7">RFC 2616 HTTP/1.1 9.7 DELETE</a>
     */
    public static final Method DELETE = new Method("DELETE", false);

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-9.8">RFC 2616 HTTP/1.1 9.8 TRACE</a>
     */
    public static final Method TRACE = new Method("TRACE", false);

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-9.9">RFC 2616 HTTP/1.1 9.9 CONNECT</a>
     */
    public static final Method CONNECT = new Method("CONNECT", false);

    /**
     * @see <a href="https://tools.ietf.org/html/rfc5789">RFC 5789 PATCH Method for HTTP</a>
     */
    public static final Method PATCH = new Method("PATCH", true);


    private static final List<Method> KNOWN_METHODS = unmodifiableList(asList(OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT, PATCH));
    private static final Map<String, Method> KNOWN_METHODS_BY_NAME = unmodifiableMap(KNOWN_METHODS.stream().collect(toMap(method -> method.name, identity())));

    public final String name;
    public final boolean hasBody;

    private Method(String name, boolean hasBody) {
        this.name = name;
        this.hasBody = hasBody;
    }

    public static Method method(String name, boolean hasBody) {
        return new Method(name, hasBody);
    }

    public static Method method(String name) {
        return Optional.ofNullable(KNOWN_METHODS_BY_NAME.get(name))
                .orElseThrow(() -> new IllegalArgumentException(format("Unrecognised method '%s', please provide the boolean argument to say whether the method should have a body", name)));
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
        return hasBody == method.hasBody &&
                Objects.equals(name, method.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hasBody);
    }
}
