package dao;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.JsonParser;

import java.util.concurrent.ExecutionException;

import rest.RestClient;

/**
 * Created by gdavalos on 10/11/2014.
 */
public class UserDAO {

    JsonParser parser;
    Boolean state;
    onLogin callbock;

    public UserDAO(){
        parser = new JsonParser();
    }
    public interface onLogin{
        public void onFinish(Boolean state);
    }

    public void login(String user, String password, onLogin callback){

        this.callbock = callback;

        RestClient client = new RestClient("http://192.168.49.101/login?username=20110363&clave=123");

        /*client.addParam("username",user);
        client.addParam("clave",password);*/

        String res = null;
        try {
            Log.e("EJECUCION: ", "se ejecuta el GET");
            res = (String)client.execute("GET").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        /*Log.e("RESPEUSTA: ", res + "");

        JsonObject o = (JsonObject) parser.parse(res);

        String dB_user = o.getAsJsonPrimitive("id").toString();

        Log.e("RESPUESTA dB_user: ", o.getAsJsonPrimitive("id").toString() );

        if(user.equals(dB_user)){
            state = true;
        }else{
            state = false;
        }*/

        state = false;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callbock.onFinish(state);
            }
        });

    }

}
