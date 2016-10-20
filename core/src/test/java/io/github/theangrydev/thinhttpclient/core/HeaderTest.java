package io.github.theangrydev.thinhttpclient.core;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.thinhttpclient.core.Header.header;

public class HeaderTest implements WithAssertions {

    @Test
    public void toStringIsNameColonValue() {
        assertThat(header("name", "value")).hasToString("name: value");
    }
}