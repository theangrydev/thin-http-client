package acceptance;

import io.github.theangrydev.thinhttpclient.okhttp.OkHttpClient;

public class OkHttpClientTest extends HttpClientTest {

    public OkHttpClientTest() {
        super(OkHttpClient.okHttpClient());
    }
}
