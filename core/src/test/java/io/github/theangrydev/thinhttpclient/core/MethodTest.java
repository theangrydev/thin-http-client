package io.github.theangrydev.thinhttpclient.core;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.thinhttpclient.core.Method.*;

public class MethodTest implements WithAssertions {

    @Test
    public void methodNamesMatchTheHttpSpecification() {
        assertThat(GET).hasToString("GET");
        assertThat(POST).hasToString("POST");
        assertThat(PATCH).hasToString("PATCH");
        assertThat(OPTIONS).hasToString("OPTIONS");
        assertThat(HEAD).hasToString("HEAD");
        assertThat(TRACE).hasToString("TRACE");
        assertThat(DELETE).hasToString("DELETE");
        assertThat(Method.values().length).isEqualTo(7);
    }
}