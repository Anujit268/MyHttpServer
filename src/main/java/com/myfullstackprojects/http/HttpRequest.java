package com.myfullstackprojects.http;

public class HttpRequest extends HttpMessage{
    public HttpMethod method;
    public String requestTarget;
    public String httpVersion;

    HttpRequest(){ //package level access:all other classes of this package can instantiate it's objects directly

    }

    public HttpMethod getMethod(){
        return this.method;
    }

    public void setMethod(String method)throws HttpParsingException{
        for(HttpMethod methodName:HttpMethod.values()){
            if(methodName.name().equals(method)){
                this.method=methodName;
                return;
            }
        }
        throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);

    }
}
