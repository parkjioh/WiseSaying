package com.back;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Rq(String cmd){

        String[] cmdBits = cmd.split("\\?",2);
        actionName = cmdBits[0];
        String queryString = cmdBits.length > 1 ? cmdBits[1] : "" ;

        paramsMap = Arrays.stream(queryString.split("&"))
                .map(part -> part.split("=",2))
                .filter(bits -> bits.length > 1 && !bits[1].trim().isEmpty() &&!bits[0].trim().isEmpty())
                .collect(Collectors.toMap(
                        bits->bits[0].trim(), //key
                        bits->bits[1].trim() //value
                ));

    }


    public String getParam(String paramName, String defaultValue) {
        return paramsMap.getOrDefault(paramName,defaultValue);
    }

    public String getactionName() {
        return actionName;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName,"");

        if (value.isEmpty()){
            return defaultValue;
        }

        try{
            return Integer.parseInt(value);
        }catch (NumberFormatException e ){
            return defaultValue;
        }
    }
}
