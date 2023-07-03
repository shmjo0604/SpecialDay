package com.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class KakaoLocalAPI {

    public static Map<String, String> getCoordinate(String addressdata) {
		
        final String GEOCODE_URL="http://dapi.kakao.com/v2/local/search/address.json?query=";
        final String GEOCODE_USER_API_KEY="KakaoAK e2f1e765930402a88b92b008b5b396e1";
	
        try{
            // 인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다.
            String address = URLEncoder.encode(addressdata, "UTF-8");
            
            URL obj = new URL(GEOCODE_URL+address);
			
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization",GEOCODE_USER_API_KEY);
            con.setRequestProperty("content-type", "application/json");
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);
			
            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
			
            //System.out.println(response.toString());

            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObj = (JSONObject)jsonParser.parse(response.toString());

            JSONArray documents = (JSONArray)jsonObj.get("documents");

            JSONObject data = (JSONObject) documents.get(0);

            String x = (String)data.get("x");
            String y = (String)data.get("y");

            Map<String, String> map = new HashMap<>();

            map.put("x", x);
            map.put("y", y);

            System.out.println(map.toString());

            return map;
			
		} catch (Exception e) {
            
			e.printStackTrace();
            return null;
		}
		
	}
    
}
