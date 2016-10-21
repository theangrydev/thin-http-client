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

import java.util.List;
import java.util.Objects;

public final class Response {
    private final Headers headers;
    public final int status;
    public final String body;

    private Response(Headers headers, int status, String body) {
        this.headers = headers;
        this.status = status;
        this.body = body;
    }

    public static Response response(Headers headers, int status, String body) {
        return new Response(headers, status, body);
    }

    public String header(String name) {
        return headers.value(name);
    }

    public List<String> headerValues(String name) {
        return headers.values(name);
    }

    @Override
    public String toString() {
        return body;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Response response = (Response) other;
        return status == response.status &&
                Objects.equals(headers, response.headers) &&
                Objects.equals(body, response.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headers, status, body);
    }
}
