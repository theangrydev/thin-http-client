package io.github.theangrydev.thinhttpclient.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.theangrydev.thinhttpclient.core.HeaderName.CONTENT_TYPE;
import static io.github.theangrydev.thinhttpclient.core.Headers.headers;
import static io.github.theangrydev.thinhttpclient.core.Method.GET;
import static io.github.theangrydev.thinhttpclient.core.Method.POST;
import static java.lang.String.format;

/**
 * This is a builder for {@link Request} objects.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern#Java_example">The Builder Pattern</a>
 */
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
    public RequestBuilder body(String body, ContentType contentType) {
        return body(body).header(CONTENT_TYPE, contentType.toString());
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
        return Request.request(url, method, body, headers(headers));
    }

    private static void checkFieldWasSet(Object field, String fieldName) {
        if (field == null) {
            throw new IllegalStateException(format("%s was not set!", fieldName));
        }
    }
}
