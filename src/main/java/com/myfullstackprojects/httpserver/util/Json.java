package com.myfullstackprojects.httpserver.util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    private static ObjectMapper myObjectMapper=defaultObjectMapper();
    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper om=new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return om;
    }

    public static JsonNode parse(String jsonSrc) throws IOException {
        return myObjectMapper.readTree(jsonSrc);
    }

    public static <A> A fromJson(JsonNode node,Class<A> clazz) throws JsonProcessingException{
        return myObjectMapper.treeToValue(node,clazz);
    }

    public static JsonNode toJson(Object obj){
        return myObjectMapper.valueToTree(obj);
    }
    public static String stringify(JsonNode node) throws JsonProcessingException{
        return generateJson(node,false);
    }
    public static String stringifyPretty(JsonNode node) throws JsonProcessingException{
        return generateJson(node,true);
    }
    public static String generateJson(Object o,boolean pretty)throws JsonProcessingException{
        ObjectWriter objectWriter=myObjectMapper.writer();
        if(pretty){
            objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }
}
