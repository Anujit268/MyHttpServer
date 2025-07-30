package com.myfullstackprojects.http;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpparserTest {

    private Httpparser httpparser;
    @BeforeAll
    public void beforeClass(){
        httpparser=new Httpparser();
    }
    @Test
    void parseHttpRequest(){
        HttpRequest request= null;
        try {
            request = httpparser.parseHttpRequest(generateValidGETTestCase());
        } catch (HttpParsingException e) {
            fail(e);
        }
        assertEquals(request.getMethod(),HttpMethod.GET);
    }

    @Test
    void parseHttpRequestBadMethod1(){
        HttpRequest request= null;
        try {
            request = httpparser.parseHttpRequest(generateBadTestCaseMethodName1());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }

    private InputStream generateValidGETTestCase(){
        String rawdata="GET / HTTP/1.1\r\n" +
                "Host: localhost:8081\r\n" +
                "Connection: keep-alive\r\n" +
                "sec-ch-ua: \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Google Chrome\";v=\"138\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: en-US,en;q=0.9"+
                "\r\n";
        InputStream inputStream=new ByteArrayInputStream(
                rawdata.getBytes(StandardCharsets.US_ASCII)
        );
        return inputStream;
    }

    private InputStream generateBadTestCaseMethodName1(){
        String rawdata="BRING / HTTP/1.1\r\n" +
                "Host: localhost:8081\r\n" +
                "Connection: keep-alive\r\n" +
                "sec-ch-ua: \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Google Chrome\";v=\"138\"\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: en-US,en;q=0.9"+
                "\r\n";
        InputStream inputStream=new ByteArrayInputStream(
                rawdata.getBytes(StandardCharsets.US_ASCII)
        );
        return inputStream;
    }
}