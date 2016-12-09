package twittersearch;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;


public class twitterSearch {
	public ArrayList<String> tweetSearch(String input)
	{
		ArrayList<String> results=new ArrayList<String>();
		try{
			String split_input[]=input.split("\\W");
			StringBuffer sb=new StringBuffer("");
			
			if(split_input.length>1)
			{
				for(int i=0;i<split_input.length-1;i++)
				{
					sb.append(split_input[i]+"%20");
				}
				sb.append(split_input[split_input.length-1]);
				input=sb.toString();
			}
			else
			{
				input=split_input[0];
			}
			
			// API for twitter insight
			String baseUrl="https://cdeservice.mybluemix.net:443/api/v1/messages/search?q="+input;
			
			// Connect using HttpUrlConnection
			URL url=new URL(baseUrl);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			String username_pass="7bf8dead-3768-4348-9413-60001fd4db6c:TiGeMh6aNq";
			String auth="Basic "+new String(new Base64().encode(username_pass.getBytes()));
			conn.setRequestProperty("Authorization", auth);
			conn.setRequestMethod("GET");
			//conn.addRequestProperty("Accept","application/xml"); //needs to match wadl i.e. what you want to accept
			System.out.println(conn.getResponseMessage());

			if (conn.getResponseCode() != 200) {
				throw new Exception("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}


			// Read
			InputStream in=conn.getInputStream();

			// Process it
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
			String line="",data="";
			while((line=br.readLine())!=null)
				data+=line;

//			System.out.println(data);
			JSONObject json=new JSONObject(data);
			JSONArray array_json=json.getJSONArray("tweets");
			for(int i=0;i<array_json.length();i++)
			{
				json=array_json.getJSONObject(i);
				JSONObject arr=json.getJSONObject("message");
				results.add(arr.getString("body"));
			}
			
			// Clean up
			in.close();
			conn.disconnect();

		}
		catch(Exception e)
		{
			return results;
		}
		return results;

	}
}
