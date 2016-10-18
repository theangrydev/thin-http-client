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

import static io.github.theangrydev.thinhttpclient.core.Method.GET;
import static java.lang.String.format;

public class RequestBuilder {

    private String url;
    private Method method;

    public static RequestBuilder get() {
        return new RequestBuilder().method(GET);
    }

    public RequestBuilder method(Method method) {
        this.method = method;
        return this;
    }

    public RequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    public Request build() {
        checkFieldWasSet(url, "URL");
        checkFieldWasSet(method, "Method");
        return new Request(url, method);
    }

    private void checkFieldWasSet(Object field, String fieldName) {
        if (field == null) {
            throw new IllegalStateException(format("%s was not set!", fieldName));
        }
    }
}
