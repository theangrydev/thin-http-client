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
package io.github.theangrydev.thinhttpclient.api;

import java.util.Objects;

public final class MediaType {
    public static final MediaType APPLICATION_XML = MediaType.mediaType("application/xml");
    public static final MediaType APPLICATION_JSON = MediaType.mediaType("application/json");

    private final String name;

    private MediaType(String name) {
        this.name = name;
    }

    public static MediaType mediaType(String name) {
        return new MediaType(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        MediaType mediaType = (MediaType) other;
        return Objects.equals(name, mediaType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
