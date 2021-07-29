package br.com.brzupacademy.propostas.utils;

import javax.servlet.http.HttpServletRequest;

public class ClientRequest {

    public static String ipAdress(HttpServletRequest httpServletRequest){
        String ip = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (ip != null) {
            String ipClient = ip.contains(",") ? ip.split(",")[0] : ip;
            return ipClient;
        }
        else{
            return httpServletRequest.getRemoteAddr();
        }
    }
}
