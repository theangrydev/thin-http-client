package io.github.theangrydev.thinhttpclient.core;

public class Header {
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
}
