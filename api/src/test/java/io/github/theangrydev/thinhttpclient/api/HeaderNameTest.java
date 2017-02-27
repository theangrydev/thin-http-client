package io.github.theangrydev.thinhttpclient.api;

import org.assertj.core.api.WithAssertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static io.github.theangrydev.thinhttpclient.api.HeaderName.*;
import static java.util.stream.Collectors.toList;

public class HeaderNameTest implements WithAssertions {

    /**
     * @see <a href="https://tools.ietf.org/html/rfc2616#section-14">Header Field Definitions</a>
     */
    @Test
    public void headerNamesMatchTheSpecification() throws NoSuchFieldException, IllegalAccessException {
        assertThat(ACCEPT).isEqualTo("Accept");
        assertThat(ACCEPT_CHARSET).isEqualTo("Accept-Charset");
        assertThat(ACCEPT_ENCODING).isEqualTo("Accept-Encoding");
        assertThat(ACCEPT_LANGUAGE).isEqualTo("Accept-Language");
        assertThat(ACCEPT_RANGES).isEqualTo("Accept-Ranges");
        assertThat(AGE).isEqualTo("Age");
        assertThat(ALLOW).isEqualTo("Allow");
        assertThat(AUTHORIZATION).isEqualTo("Authorization");
        assertThat(CACHE_CONTROL).isEqualTo("Cache-Control");
        assertThat(CONNECTION).isEqualTo("Connection");
        assertThat(CONTENT_ENCODING).isEqualTo("Content-Encoding");
        assertThat(CONTENT_LANGUAGE).isEqualTo("Content-Language");
        assertThat(CONTENT_LENGTH).isEqualTo("Content-Length");
        assertThat(CONTENT_LOCATION).isEqualTo("Content-Location");
        assertThat(CONTENT_MD5).isEqualTo("Content-MD5");
        assertThat(CONTENT_RANGE).isEqualTo("Content-Range");
        assertThat(CONTENT_TYPE).isEqualTo("Content-Type");
        assertThat(DATE).isEqualTo("Date");
        assertThat(ETAG).isEqualTo("ETag");
        assertThat(EXPECT).isEqualTo("Expect");
        assertThat(EXPIRES).isEqualTo("Expires");
        assertThat(FROM).isEqualTo("From");
        assertThat(HOST).isEqualTo("Host");
        assertThat(IF_MATCH).isEqualTo("If-Match");
        assertThat(IF_MODIFIED_SINCE).isEqualTo("If-Modified-Since");
        assertThat(IF_NONE_MATCH).isEqualTo("If-None-Match");
        assertThat(IF_RANGE).isEqualTo("If-Range");
        assertThat(IF_UNMODIFIED_SINCE).isEqualTo("If-Unmodified-Since");
        assertThat(LAST_MODIFIED).isEqualTo("Last-Modified");
        assertThat(LOCATION).isEqualTo("Location");
        assertThat(MAX_FORWARDS).isEqualTo("Max-Forwards");
        assertThat(PRAGMA).isEqualTo("Pragma");
        assertThat(PROXY_AUTHENTICATE).isEqualTo("Proxy-Authenticate");
        assertThat(PROXY_AUTHORIZATION).isEqualTo("Proxy-Authorization");
        assertThat(RANGE).isEqualTo("Range");
        assertThat(REFERER).isEqualTo("Referer");
        assertThat(RETRY_AFTER).isEqualTo("Retry-After");
        assertThat(SERVER).isEqualTo("Server");
        assertThat(TE).isEqualTo("TE");
        assertThat(TRAILER).isEqualTo("Trailer");
        assertThat(TRANSFER_ENCODING).isEqualTo("Transfer-Encoding");
        assertThat(UPGRADE).isEqualTo("Upgrade");
        assertThat(USER_AGENT).isEqualTo("User-Agent");
        assertThat(VARY).isEqualTo("Vary");
        assertThat(VIA).isEqualTo("Via");
        assertThat(WARNING).isEqualTo("Warning");
        assertThat(WWW_AUTHENTICATE).isEqualTo("WWW-Authenticate");
    }

    @Test
    public void allTheHeaderNamesMentionedInTheSpecification() throws IOException {
        List<String> definedHeaderNames = definedHeaderNames("https://tools.ietf.org/html/rfc2616");
        List<String> declaredHeaderNames = declaredHeaderNames();

        assertThat(declaredHeaderNames).isEqualTo(definedHeaderNames);
    }

    private List<String> declaredHeaderNames() {
        return Arrays.stream(HeaderName.class.getDeclaredFields())
                .map(this::fieldValue)
                .collect(toList());
    }

    private String fieldValue(Field field){
        try {
            return field.get(null).toString();
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<String> definedHeaderNames(String specificationUrl) throws IOException {
        Document document = Jsoup.connect(specificationUrl).get();

        String headerSection = document.getElementsContainingOwnText("Header Field Definitions")
                .select(".h2 a")
                .attr("name");

        Pattern headerNameSectionPattern = Pattern.compile(headerSection + ".\\d+$");
        return document.getElementsByAttributeValueMatching("name", headerNameSectionPattern)
                .stream()
                .map(Element::parent)
                .map(Element::ownText)
                .collect(toList());
    }
}