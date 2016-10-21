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
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import static io.github.theangrydev.thinhttpclient.core.Method.*;


public class MethodTest implements WithAssertions {

    @Test
    public void lookupByMethodName() {
        assertThat(Method.method("GET")).isEqualTo(GET);
        assertThat(Method.method("HEAD")).isEqualTo(HEAD);
        assertThat(Method.method("POST")).isEqualTo(POST);
        assertThat(Method.method("PUT")).isEqualTo(PUT);
        assertThat(Method.method("DELETE")).isEqualTo(DELETE);
        assertThat(Method.method("TRACE")).isEqualTo(TRACE);
        assertThat(Method.method("CONNECT")).isEqualTo(CONNECT);
        assertThat(Method.method("PATCH")).isEqualTo(PATCH);
    }

    @Test
    public void methodNamesMatchTheHttpSpecification() {
        assertThat(OPTIONS).hasToString("OPTIONS");
        assertThat(GET).hasToString("GET");
        assertThat(HEAD).hasToString("HEAD");
        assertThat(POST).hasToString("POST");
        assertThat(PUT).hasToString("PUT");
        assertThat(DELETE).hasToString("DELETE");
        assertThat(TRACE).hasToString("TRACE");
        assertThat(CONNECT).hasToString("CONNECT");
        assertThat(PATCH).hasToString("PATCH");
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Method.class).verify();
    }
}