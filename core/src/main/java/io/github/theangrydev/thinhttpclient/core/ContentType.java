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

import java.nio.charset.Charset;
import java.util.Objects;

public final class ContentType {

    public final MediaType mediaType;
    public final Charset charset;

    private ContentType(MediaType mediaType, Charset charset) {
        this.mediaType = mediaType;
        this.charset = charset;
    }

    public static ContentType contentType(MediaType mediaType, Charset charset) {
        return new ContentType(mediaType, charset);
    }

    @Override
    public String toString() {
        return mediaType + "; charset=" + charset.name();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ContentType that = (ContentType) other;
        return Objects.equals(mediaType, that.mediaType) &&
                Objects.equals(charset, that.charset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaType, charset);
    }
}
