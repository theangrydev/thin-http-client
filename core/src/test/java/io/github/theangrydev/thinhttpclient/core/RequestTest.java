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

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static io.github.theangrydev.thinhttpclient.core.Header.header;
import static io.github.theangrydev.thinhttpclient.core.Headers.headers;

public class RequestTest {

    private static final Headers HEADERS_1 = headers(header("a", "1"));
    private static final Headers HEADERS_2 = headers(header("b", "2"));
    private static final Method METHOD_1 = Method.GET;
    private static final Method METHOD_2 = Method.POST;
    private static final URL URL_1 = url("http://www.google.co.uk/");
    private static final URL URL_2 = url("http://www.bbc.co.uk/");

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Request.class)
                .withPrefabValues(Method.class, METHOD_1, METHOD_2)
                .withPrefabValues(Headers.class, HEADERS_1, HEADERS_2)
                .withPrefabValues(URL.class, URL_1, URL_2)
                .verify();
    }

    private static URL url(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }
}