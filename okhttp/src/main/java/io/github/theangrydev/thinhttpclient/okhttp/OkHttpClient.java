package io.github.theangrydev.thinhttpclient.okhttp;

import io.github.theangrydev.thinhttpclient.core.Headers;
import io.github.theangrydev.thinhttpclient.core.HttpClient;
import io.github.theangrydev.thinhttpclient.core.Request;
import io.github.theangrydev.thinhttpclient.core.Response;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.stream.Stream;

import static io.github.theangrydev.thinhttpclient.core.Header.header;
import static io.github.theangrydev.thinhttpclient.core.HeaderName.CONTENT_TYPE;
import static java.util.stream.Collectors.toList;

public class OkHttpClient implements HttpClient {

    private final okhttp3.OkHttpClient httpClient;

    private OkHttpClient(okhttp3.OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    // TODO: if a custom okhttp3.OkHttpClient is passed in, the dispatcher and connection pool may be shared amongst many
    // clients and so this class cannot be responsible for closing them
    public static HttpClient okHttpClient() {
        okhttp3.OkHttpClient httpClient = new okhttp3.OkHttpClient.Builder()
                .connectionPool(new ConnectionPool())
                .dispatcher(new Dispatcher())
                .build();
        return new OkHttpClient(httpClient);
    }

    @Override
    public Response execute(Request request) throws IOException {
        String contentType = request.header(CONTENT_TYPE);
        okhttp3.Request okHttpRequest = new okhttp3.Request.Builder()
                .method(request.method.name, adaptBody(request, contentType))
                .url(request.url)
                .headers(adaptHeaders(request.headers))
                .build();
        okhttp3.Response okHttpResponse = httpClient.newCall(okHttpRequest).execute();
        return Response.response(adaptHeaders(okHttpResponse.headers()), okHttpResponse.code(), okHttpResponse.body().string());
    }

    private RequestBody adaptBody(Request request, String contentType) {
        if (request.method.hasBody) {
            return RequestBody.create(MediaType.parse(contentType), request.body);
        } else {
            return null;
        }
    }

    private Headers adaptHeaders(okhttp3.Headers headers) {
        return Headers.headers(headers.names().stream()
                .flatMap(name -> headers.values(name).stream().map(value -> header(name, value)))
                .collect(toList()));
    }

    private okhttp3.Headers adaptHeaders(Headers headers) {
        return okhttp3.Headers.of(headers.stream()
                .flatMap(header -> Stream.of(header.name, header.value))
                .toArray(String[]::new));
    }

    @Override
    public void close() throws IOException {
        httpClient.dispatcher().executorService().shutdown();
        httpClient.connectionPool().evictAll();
    }
}
