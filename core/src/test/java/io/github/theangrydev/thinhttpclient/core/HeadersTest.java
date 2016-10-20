package io.github.theangrydev.thinhttpclient.core;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.thinhttpclient.core.Header.header;
import static io.github.theangrydev.thinhttpclient.core.Headers.headers;

public class HeadersTest implements WithAssertions {

    @Test
    public void toStringPrintsHeadersLineByLine() {
        assertThat(headers(header("name1", "value1"), header("name2", "value2")))
                .hasToString("name1: value1" + System.lineSeparator() + "name2: value2");
    }
}