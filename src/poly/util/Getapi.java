package poly.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import poly.dto.FoodDTO;


public class Getapi {

   public FoodDTO apirun(String search) throws Exception {
      String apiUrl = "http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/getFoodNtrItdntList"; /* URL */
      String serviceKey = "oJzWRxx8UMJkHJcJ8Gk3SSpFJqUekWl4dfIZ0lu5iYvk1v6fJATJ8GlTmFtwqWpjL7Mt%2B7hnvhUriX5QDIdBrw%3D%3D";
      StringBuilder urlBuilder = new StringBuilder(apiUrl);

      urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
      urlBuilder.append(
            "&" + URLEncoder.encode("desc_kor", "UTF-8") + "=" + URLEncoder.encode(search, "UTF-8")); /* 식품이름 */

      URL url = new URL(urlBuilder.toString());
      //System.out.println("URL :" + url);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/json");
      //System.out.println("Response code: " + conn.getResponseCode());

      BufferedReader rd;
      if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      } else {
         rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
         sb.append(line);
      }
      rd.close();
      conn.disconnect();
      org.json.JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
      String xmlJSONObjString = xmlJSONObj.toString();
      //System.out.println("xmlJSONObjString :" + xmlJSONObjString);

      JSONParser parser = new JSONParser();
      JSONObject obj = (JSONObject) parser.parse(xmlJSONObjString);
      //System.out.println("obj :" + obj);

      JSONObject parse_response = (JSONObject) obj.get("response");
      JSONObject parse_body = (JSONObject) parse_response.get("body");
      JSONObject parse_items = (JSONObject) parse_body.get("items");
      JSONObject parse_item = (JSONObject) parse_items.get("item");
      
      FoodDTO rDTO = new FoodDTO();
      
      String food_name="";
      String food_gram="";
      String food_kcal="";
      
      
      try {

         for (int i = 0; i < parse_item.size(); i++) {

             food_name = parse_item.get("DESC_KOR").toString();
            System.out.println("DESC_KOR :" + food_name);
            break;
         }

         for (int i = 0; i < parse_item.size(); i++) {

             food_gram = parse_item.get("SERVING_WT").toString();
            System.out.println("SERVING_WT :" + food_gram);
            break;
         }
         for (int i = 0; i < parse_item.size(); i++) {

             food_kcal = parse_item.get("NUTR_CONT1").toString();
            System.out.println("NUTR_CONT1 :" + food_kcal);
            break;
         }
         
			/*
			 * rMap.put("food_name", food_name); rMap.put("food_gram", food_gram);
			 * rMap.put("food_kcal", food_kcal);
			 * 
			 * rList.add(rMap);
			 */
              
         rDTO.setFood_name(food_name);
        
         
        
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return rDTO;
      
   }
}