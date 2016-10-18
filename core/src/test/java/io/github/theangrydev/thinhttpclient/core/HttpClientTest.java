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

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

/**
 * Implementations of {@link HttpClient} should extend this class to test that they satisfy the general contract.
 */
public abstract class HttpClientTest implements WithAssertions {
    private final HttpClient httpClient;

    protected HttpClientTest(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Test
    public void dummy() {
        assertThat(httpClient).isNotNull();
    }
}