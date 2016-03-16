package softdev1.medll;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Tyrael on 09.01.2016.

public class JSONData {
 String strJson="
 {
 \"Employee\" :[
 {
 \"id\":\"01\",
 \"name\":\"Gopal Varma\",
 \"salary\":\"500000\"
 },
 {
 \"id\":\"02\",
 \"name\":\"Sairamkrishna\",
 \"salary\":\"500000\"
 },
 {
 \"id\":\"03\",
 \"name\":\"Sathish kallakuri\",
 \"salary\":\"600000\"
 }
 ]
 }";
 String data = "";
 try {
 JSONObject  jsonRootObject = new JSONObject(strJson);

 //Get the instance of JSONArray that contains JSONObjects
 JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");

 //Iterate the jsonArray and print the info of JSONObjects
 for(int i=0; i < jsonArray.length(); i++){
 JSONObject jsonObject = jsonArray.getJSONObject(i);

 int id = Integer.parseInt(jsonObject.optString("id").toString());
 String name = jsonObject.optString("name").toString();
 float salary = Float.parseFloat(jsonObject.optString("salary").toString());

 data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
 }
 output.setText(data);
 } catch (JSONException e) {e.printStackTrace();}
 }
*/
