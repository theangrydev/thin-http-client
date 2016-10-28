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
package acceptance;

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.junit.TableRunner;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import io.github.theangrydev.thinhttpclient.api.HttpClient;
import io.github.theangrydev.thinhttpclient.api.Request;
import io.github.theangrydev.thinhttpclient.api.Response;
import org.assertj.core.api.WithAssertions;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.http.RequestMethod.fromString;
import static com.github.tomakehurst.wiremock.matching.RequestPatternBuilder.newRequestPattern;
import static io.github.theangrydev.thinhttpclient.api.MediaType.APPLICATION_XML;
import static io.github.theangrydev.thinhttpclient.api.Method.method;
import static java.nio.charset.StandardCharsets.UTF_16;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Implementations of {@link HttpClient} should extend this class to test that they satisfy the general contract.
 */
@RunWith(TableRunner.class)
public abstract class HttpClientTest extends TestState implements WithAssertions {
    private final HttpClient httpClient;

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule();

    @Rule
    public WireMockClassRule instanceRule = wireMockRule;

    protected HttpClientTest(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Table({
        @Row("OPTIONS"),
        @Row("GET"),
        @Row("HEAD"),
        @Row("DELETE"),
        @Row("TRACE")
    })
    @Test
    public void methodsWithoutBodyDoNotSpecifyContentLength(String methodName) throws IOException {
        httpClient.execute(Request.builder().method(method(methodName)).noBody().url(baseUrl() + "/test"));

        verify(newRequestPattern(fromString(methodName), urlPathEqualTo("/test"))
                .withRequestBody(equalTo(""))
                .withoutHeader("Content-Length"));
    }

    /**
     * @see <a href=" https://tools.ietf.org/html/rfc2616#section-9.3">RFC 2616 HTTP/1.1 9.3 GET</a>
     */
    @Test
    public void getRequest() throws IOException {
        httpClient.execute(Request.get().url(baseUrl() + "/test"));

        verify(getRequestedFor(urlPathEqualTo("/test"))
                .withRequestBody(equalTo(""))
                .withoutHeader("Content-Length"));
    }

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-7.2">RFC 2616 HTTP/1.1 7.2 Entity Body</a>
     */
    @Test
    public void requestBody() throws IOException {
        String expectedBody = "<something>wow</something>";
        httpClient.execute(Request.post().url(baseUrl() + "/test")
                .body(expectedBody, APPLICATION_XML, UTF_8));

        verify(postRequestedFor(urlPathEqualTo("/test"))
                .withRequestBody(equalTo(expectedBody))
                .withHeader("Content-Length", equalTo("26"))
                .withHeader("Content-Type", equalTo("application/xml; charset=UTF-8")));
    }

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-7.2">RFC 2616 HTTP/1.1 7.2 Entity Body</a>
     */
    @Test
    public void requestBodyUtf16() throws IOException {
        String expectedBody = "<something>wow</something>";
        httpClient.execute(Request.post().url(baseUrl() + "/test")
                .body(expectedBody, APPLICATION_XML, UTF_16));

        verify(postRequestedFor(urlPathEqualTo("/test"))
                .withRequestBody(equalTo("��\u0000<\u0000s\u0000o\u0000m\u0000e\u0000t\u0000h\u0000i\u0000n\u0000g\u0000>\u0000w\u0000o\u0000w\u0000<\u0000/\u0000s\u0000o\u0000m\u0000e\u0000t\u0000h\u0000i\u0000n\u0000g\u0000>"))
                .withHeader("Content-Length", equalTo("54"))
                .withHeader("Content-Type", equalTo("application/xml; charset=UTF-16")));
    }

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-7.2">RFC 2616 HTTP/1.1 7.2 Entity Body</a>
     */
    @Test
    public void responseBody() throws IOException {
        String expectedBody = "some body";
        givenThat(get(urlEqualTo("/test")).willReturn(aResponse()
                .withBody(expectedBody)));

        Response response = httpClient.execute(Request.get().url(baseUrl() + "/test"));

        assertThat(response.body).isEqualTo(expectedBody);
    }

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-6.1.1">RFC 2616 HTTP/1.1 6.1.1 Status Code and Reason Phrase</a>
     */
    @Test
    public void responseStatus() throws IOException {
        givenThat(get(urlEqualTo("/test")).willReturn(aResponse()
                .withStatus(200)));

        Response response = httpClient.execute(Request.get().url(baseUrl() + "/test"));

        assertThat(response.status).isEqualTo(200);
    }

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.2">RFC 2616 HTTP/1.1 4.2 Message Headers</a>
     */
    @Test
    public void responseHeaders() throws IOException {
        givenThat(get(urlEqualTo("/test")).willReturn(aResponse()
                .withHeader("name", "value")));

        Response response = httpClient.execute(Request.get().url(baseUrl() + "/test"));

        assertThat(response.header("name")).isEqualTo("value");
        assertThat(response.header("name")).isEqualTo("value");
    }

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.2">RFC 2616 HTTP/1.1 4.2 Message Headers</a>
     */
    @Test
    public void headerWithNoValue() throws IOException {
        givenThat(get(urlEqualTo("/test")).willReturn(aResponse()
                .withHeader("name", "")));

        Response response = httpClient.execute(Request.get().url(baseUrl() + "/test"));

        assertThat(response.header("name")).isEqualTo("");
    }

    /**
     * The most reasonable thing to do when multiple message-header fields have the same field-name is to combine all
     * the field-values into a comma separated list.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.2">RFC 2616 HTTP/1.1 4.2 Message Headers</a>
     * @see <a href="http://stackoverflow.com/questions/3096888/standard-for-adding-multiple-values-of-a-single-http-header-to-a-request-or-resp">Related Stack Overflow Question</a>
     */
    @Test
    public void multipleHeadersWithTheSameKeyAreCombinedIntoACommaSeparatedList() throws IOException {
        givenThat(get(urlEqualTo("/test")).willReturn(aResponse()
                .withHeader("name", "first")
                .withHeader("name", "second")
                .withHeader("name", "third")
                .withHeader("name", "fourth")));

        Response response = httpClient.execute(Request.get().url(baseUrl() + "/test"));

        assertThat(response.header("name")).isEqualTo("first,second,third,fourth");
    }

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-4.2">RFC 2616 HTTP/1.1 4.2 Message Headers</a>
     */
    @Test
    public void multipleHeaderValues() throws IOException {
        givenThat(get(urlEqualTo("/test")).willReturn(aResponse()
                .withHeader("name", "first")
                .withHeader("name", "second")
                .withHeader("name", "third")
                .withHeader("name", "fourth")));

        Response response = httpClient.execute(Request.get().url(baseUrl() + "/test"));

        assertThat(response.headerValues("name")).containsExactly("first", "second", "third", "fourth");
    }

    private String baseUrl() {
        return "http://localhost:" + Options.DEFAULT_PORT;
    }
}
