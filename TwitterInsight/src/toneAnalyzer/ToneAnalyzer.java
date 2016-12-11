package toneAnalyzer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

public class ToneAnalyzer {

	public HashMap<String,Double> toneAnalyze(String input)
	{
		HashMap<String,Double> doc_tones=new HashMap<String,Double>();
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
			String baseUrl="https://gateway.watsonplatform.net/tone-analyzer/api/v3/tone?version=2016-05-19&text="+input;

			// Connect using HttpUrlConnection
			URL url=new URL(baseUrl);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			String username_pass="81bd079b-18cc-4fe7-8085-5766a665652f:dSdsWrXNFtqp";
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

			System.out.println(data);
			JSONObject json=new JSONObject(data);
			JSONObject document_json=json.getJSONObject("document_tone");
			JSONArray array_json=document_json.getJSONArray("tone_categories");
			for(int i=0;i<array_json.length();i++)
			{
				json=array_json.getJSONObject(i);
				System.out.println(json.getString("category_name"));
				JSONArray tones=json.getJSONArray("tones");
				for(int j=0;j<tones.length();j++)
				{
					json=tones.getJSONObject(j);
					doc_tones.put(json.getString("tone_name"), json.getDouble("score"));
				}
				
			}
			
			for(String keys:doc_tones.keySet())
			{
				System.out.println(keys+": "+doc_tones.get(keys));
			}

			// Clean up
			in.close();
			conn.disconnect();


		}
		catch(Exception e)
		{
			return doc_tones;
		}
		return doc_tones;
	}
}
