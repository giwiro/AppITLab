package rest;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by gdavalos on 12/11/2014.
 */
public class RestClient extends AsyncTask<String,String,String>{

    JSONObject data = new JSONObject();
    String url;
    String headerName;
    String headerValue;


    public RestClient(String s){
        url = s;
    }

    public void addHeader(String name, String value){
        headerName = name;
        headerValue = value;
    }

    public void addParam(String key, String value){

        try {
            data.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String executePost(){  // If you want to use post method to hit server

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(headerName, headerValue);
        HttpResponse response = null;
        String result = null;
        try {
            StringEntity entity = new StringEntity(data.toString(), HTTP.UTF_8);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            result = EntityUtils.toString(entity1);
            return result;
            //Toast.makeText(MainPage.this, result, Toast.LENGTH_LONG).show();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }

    public String executeGet(){ //If you want to use get method to hit server

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        String result = null;
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            result = httpClient.execute(httpget, responseHandler);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected String doInBackground(String...params) {
        String method = params[0];
        String res = null;
        if(method.length() > 0 && method.equals("POST")){
            res = executePost();
        }else if(method.length() == 0 || method.equals("GET")){
            res = executeGet();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result == null){
            Log.e("ERROR:","NullPointerException !!!!!!!!!!");
        }else{
            Log.e("RESPUESTA:",result);
        }

    }
}
