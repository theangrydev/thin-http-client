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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.System.lineSeparator;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;

/**
 * HTTP header fields.
 *
 * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.2">RFC 2616 HTTP/1.1 4.2 Message Headers</a>
 */
public final class Headers implements Iterable<Header> {

    private final List<Header> headers;
    private final Map<String, List<String>> headerValues;

    private Headers(List<Header> headers, Map<String, List<String>> headerValues) {
        this.headerValues = headerValues;
        this.headers = headers;
    }

    public static Headers headers(Header... headers) {
        return headers(Arrays.asList(headers));
    }

    public static Headers headers(List<Header> headers) {
        Map<String, List<String>> headerValues = headers.stream().collect(
                groupingBy(header -> header.name,
                        mapping(header -> header.value, toList())));
        return new Headers(headers, headerValues);
    }

    public String value(String name) {
        return values(name).stream().collect(joining(","));
    }

    public List<String> values(String name) {
        return headerValues.getOrDefault(name, emptyList());
    }

    @Override
    public Iterator<Header> iterator() {
        return headers.iterator();
    }

    @Override
    public String toString() {
        return headers.stream().map(Header::toString).collect(joining(lineSeparator()));
    }
}
