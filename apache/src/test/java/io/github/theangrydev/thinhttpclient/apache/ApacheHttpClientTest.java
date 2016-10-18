package io.github.theangrydev.thinhttpclient.apache;

import io.github.theangrydev.thinhttpclient.core.HttpClientTest;

import static io.github.theangrydev.thinhttpclient.apache.ApacheHttpClient.apacheHttpClient;

public class ApacheHttpClientTest extends HttpClientTest {

    public ApacheHttpClientTest() {
        super(apacheHttpClient());
    }
}