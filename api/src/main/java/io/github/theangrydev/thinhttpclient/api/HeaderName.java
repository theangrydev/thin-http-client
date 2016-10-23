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
package io.github.theangrydev.thinhttpclient.api;

/**
 * Some of the HTTP header field names that thin-http-client knows about.
 *
 * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.2">RFC 2616 HTTP/1.1 4.2 Message Headers</a>
 */
public class HeaderName {

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.17">RFC 2616 HTTP/1.1 14.17 Content-Type</a>
     */
    public static final String CONTENT_TYPE = "Content-Type";
}
