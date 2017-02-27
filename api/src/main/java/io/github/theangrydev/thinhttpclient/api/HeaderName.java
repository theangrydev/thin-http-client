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
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.1">RFC 2616 HTTP/1.1 14.1 Accept</a>
     */
    public static final String ACCEPT = "Accept";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.2">RFC 2616 HTTP/1.1 14.2 Accept-Charset</a>
     */
    public static final String ACCEPT_CHARSET = "Accept-Charset";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.3">RFC 2616 HTTP/1.1 14.3 Accept-Encoding</a>
     */
    public static final String ACCEPT_ENCODING = "Accept-Encoding";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.4">RFC 2616 HTTP/1.1 14.4 Accept-Language</a>
     */
    public static final String ACCEPT_LANGUAGE = "Accept-Language";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.5">RFC 2616 HTTP/1.1 14.5 Accept-Ranges</a>
     */
    public static final String ACCEPT_RANGES = "Accept-Ranges";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.6">RFC 2616 HTTP/1.1 14.6 Age</a>
     */
    public static final String AGE = "Age";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.7">RFC 2616 HTTP/1.1 14.7 Allow</a>
     */
    public static final String ALLOW = "Allow";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.8">RFC 2616 HTTP/1.1 14.8 Authorization</a>
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.9">RFC 2616 HTTP/1.1 14.9 Cache-Control</a>
     */
    public static final String CACHE_CONTROL = "Cache-Control";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.10">RFC 2616 HTTP/1.1 14.10 Connection</a>
     */
    public static final String CONNECTION = "Connection";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.11">RFC 2616 HTTP/1.1 14.11 Content-Encoding</a>
     */
    public static final String CONTENT_ENCODING = "Content-Encoding";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.12">RFC 2616 HTTP/1.1 14.12 Content-Language</a>
     */
    public static final String CONTENT_LANGUAGE = "Content-Language";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.13">RFC 2616 HTTP/1.1 14.13 Content-Length</a>
     */
    public static final String CONTENT_LENGTH = "Content-Length";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.14">RFC 2616 HTTP/1.1 14.14 Content-Location</a>
     */
    public static final String CONTENT_LOCATION = "Content-Location";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.15">RFC 2616 HTTP/1.1 14.15 Content-MD5</a>
     */
    public static final String CONTENT_MD5 = "Content-MD5";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.16">RFC 2616 HTTP/1.1 14.16 Content-Range</a>
     */
    public static final String CONTENT_RANGE = "Content-Range";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.17">RFC 2616 HTTP/1.1 14.17 Content-Type</a>
     */
    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.18">RFC 2616 HTTP/1.1 14.18 Date</a>
     */
    public static final String DATE = "Date";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.19">RFC 2616 HTTP/1.1 14.19 ETag</a>
     */
    public static final String ETAG = "ETag";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.20">RFC 2616 HTTP/1.1 14.20 Expect</a>
     */
    public static final String EXPECT = "Expect";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.21">RFC 2616 HTTP/1.1 14.21 Expires</a>
     */
    public static final String EXPIRES = "Expires";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.22">RFC 2616 HTTP/1.1 14.22 From</a>
     */
    public static final String FROM = "From";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.23">RFC 2616 HTTP/1.1 14.23 Host</a>
     */
    public static final String HOST = "Host";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.24">RFC 2616 HTTP/1.1 14.24 If-Match</a>
     */
    public static final String IF_MATCH = "If-Match";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.25">RFC 2616 HTTP/1.1 14.25 If-Modified-Since</a>
     */
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.26">RFC 2616 HTTP/1.1 14.26 If-None-Match</a>
     */
    public static final String IF_NONE_MATCH = "If-None-Match";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.27">RFC 2616 HTTP/1.1 14.27 If-Range</a>
     */
    public static final String IF_RANGE = "If-Range";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.28">RFC 2616 HTTP/1.1 14.28 If-Unmodified-Since</a>
     */
    public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.29">RFC 2616 HTTP/1.1 14.29 Last-Modified</a>
     */
    public static final String LAST_MODIFIED = "Last-Modified";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.30">RFC 2616 HTTP/1.1 14.30 Location</a>
     */
    public static final String LOCATION = "Location";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.31">RFC 2616 HTTP/1.1 14.31 Max-Forwards</a>
     */
    public static final String MAX_FORWARDS = "Max-Forwards";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.32">RFC 2616 HTTP/1.1 14.32 Pragma</a>
     */
    public static final String PRAGMA = "Pragma";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.33">RFC 2616 HTTP/1.1 14.33 Proxy-Authenticate</a>
     */
    public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.34">RFC 2616 HTTP/1.1 14.34 Proxy-Authorization</a>
     */
    public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.35.2">RFC 2616 HTTP/1.1 14.35.2 Range Retrieval Requests</a>
     */
    public static final String RANGE = "Range";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.36">RFC 2616 HTTP/1.1 14.36 Referer</a>
     */
    public static final String REFERER = "Referer";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.37">RFC 2616 HTTP/1.1 14.37 Retry-After</a>
     */
    public static final String RETRY_AFTER = "Retry-After";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.38">RFC 2616 HTTP/1.1 14.38 Server</a>
     */
    public static final String SERVER = "Server";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.39">RFC 2616 HTTP/1.1 14.39 TE</a>
     */
    public static final String TE = "TE";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.40">RFC 2616 HTTP/1.1 14.40 Trailer</a>
     */
    public static final String TRAILER = "Trailer";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.41">RFC 2616 HTTP/1.1 14.41 Transfer-Encoding</a>
     */
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.42">RFC 2616 HTTP/1.1 14.42 Upgrade</a>
     */
    public static final String UPGRADE = "Upgrade";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.43">RFC 2616 HTTP/1.1 14.43 User-Agent</a>
     */
    public static final String USER_AGENT = "User-Agent";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.44">RFC 2616 HTTP/1.1 14.44 Vary</a>
     */
    public static final String VARY = "Vary";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.45">RFC 2616 HTTP/1.1 14.45 Via</a>
     */
    public static final String VIA = "Via";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.46">RFC 2616 HTTP/1.1 14.46 Warning</a>
     */
    public static final String WARNING = "Warning";

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14.47">RFC 2616 HTTP/1.1 14.47 WWW-Authenticate</a>
     */
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
}
