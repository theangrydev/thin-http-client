package io.github.theangrydev.thinhttpclient.core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.System.lineSeparator;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;

public class Headers implements Iterable<Header> {

    private final Map<String, List<String>> headerValues;
    private final List<Header> headers;

    private Headers(Map<String, List<String>> headerValues, List<Header> headers) {
        this.headerValues = headerValues;
        this.headers = headers;
    }

    public static Headers headers(Header... headers) {
        return headers(Arrays.asList(headers));
    }

    public static Headers headers(List<Header> headers) {
        Map<String, List<String>> headerValues = headers.stream().collect(
                groupingBy(header -> header.name,
                        mapping(header -> header.value, toList())));
        return new Headers(headerValues, headers);
    }

    public String value(String name) {
        return headerValues.getOrDefault(name, emptyList()).stream().collect(joining(","));
    }

    public List<String> values(String name) {
        return headerValues.getOrDefault(name, emptyList());
    }

    @Override
    public Iterator<Header> iterator() {
        return headers.iterator();
    }

    @Override
    public String toString() {
        return headers.stream().map(Header::toString).collect(joining(lineSeparator()));
    }
}
