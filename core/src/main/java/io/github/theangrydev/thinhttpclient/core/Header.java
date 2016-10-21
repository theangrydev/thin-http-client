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

import java.util.Objects;

public final class Header {
    public final String name;
    public final String value;

    private Header(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static Header header(String name, String value) {
        return new Header(name, value);
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Header header = (Header) other;
        return Objects.equals(name, header.name) &&
                Objects.equals(value, header.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
