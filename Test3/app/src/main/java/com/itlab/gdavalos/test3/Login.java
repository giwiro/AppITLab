package com.itlab.gdavalos.test3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import dao.UserDAO;


public class Login extends ActionBarActivity {
    ImageView iv;
    ImageView user_icon;
    ImageView lock_icon;
    EditText user;
    EditText pass;
    Button ingresar;

    UserDAO userDAO = new UserDAO();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Log.e("creado ","");

        iv = (ImageView)findViewById(R.id.img_titulo);
        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.password);
        ingresar = (Button)findViewById(R.id.login);
        user_icon = (ImageView)findViewById(R.id.user_icon);
        lock_icon = (ImageView)findViewById(R.id.password_icon);

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacion_titulo);
        final Animation anim_text = AnimationUtils.loadAnimation(this, R.anim.animacion_input);
        final Animation anim_btn = AnimationUtils.loadAnimation(this, R.anim.animacion_button);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);


        iv.startAnimation(anim);

        user.startAnimation(anim_text);
        pass.startAnimation(anim_text);

        user_icon.startAnimation(anim_text);
        lock_icon.startAnimation(anim_text);

        ingresar.startAnimation(anim_btn);
        ingresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                user.setEnabled(false);
                pass.setEnabled(false);
                ingresar.setEnabled(false);

                userDAO.login("20120202","elegante",new UserDAO.onLogin() {
                    @Override

                    public void onFinish(Boolean state) {
                        if(!state){
                            iv.startAnimation(shake);
                            user.startAnimation(shake);
                            pass.startAnimation(shake);
                            user_icon.startAnimation(shake);
                            lock_icon.startAnimation(shake);
                            v.startAnimation(shake);

                            user.setEnabled(true);
                            pass.setEnabled(true);
                            ingresar.setEnabled(true);
                        }else{

                        }
                    }

                });


            }
        });




    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
