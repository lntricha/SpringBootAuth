package com.web.springbootauth.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;

public class ObjectToJson {
    public static String getStringFromObject(UserDetails userDetails){
        ObjectMapper Obj = new ObjectMapper();

        // Try block to check for exceptions
        try {

            // Getting organisation object as a json string
            String jsonStr = Obj.writeValueAsString(userDetails);

            // Displaying JSON String on console
            System.out.println(jsonStr);
            return jsonStr;
        }catch (Exception e){
            e.printStackTrace();
        }
           return "";
    }
}
