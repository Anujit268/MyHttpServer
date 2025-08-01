package com.myfullstackprojects.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Httpparser {
    private final static Logger LOGGER= LoggerFactory.getLogger(Httpparser.class);

    private static final int SP=0x20;
    private static final int CR=0x0D;
    private static final int LF=0x0A;

    public HttpRequest parseHttpRequest(InputStream inputStream) throws HttpParsingException {
        InputStreamReader reader=new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest request=new HttpRequest();
        try {
            parseRequestLine(reader,request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parseHeaders(reader,request);
        parseBody(reader,request);
        return request;
    }

    private void parseBody(InputStreamReader inputStream, HttpRequest request) {

    }

    private void parseHeaders(InputStreamReader inputStream, HttpRequest request) {
        
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException, HttpParsingException {
        StringBuilder processingDataBuffer=new StringBuilder();
        boolean methodParsed=false;
        boolean requestTargetParsed=false;
        int _byte;
        while((_byte=reader.read())>=0){
            if(_byte==CR) {
                _byte = reader.read();
                if (_byte == LF) {
                    LOGGER.debug("Request line Version to process:{}",processingDataBuffer.toString());
                    return;
                }
            }

            if(_byte==SP){
                if(!methodParsed){
                    LOGGER.debug("Request Line Method to process:{}",processingDataBuffer.toString());
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed=true;
                }
                else if(!requestTargetParsed){
                    LOGGER.debug("Request Line Target to process:{}",processingDataBuffer.toString());
                    requestTargetParsed=true;
                }

                processingDataBuffer.delete(0,processingDataBuffer.length());
            }
            else{
                processingDataBuffer.append((char)_byte);
            }
        }
    }
}
