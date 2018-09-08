package com.example.dlarb.vacationproject2018;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    private CallbackManager callbackManager;
    ImageView Splash;
    Animation Moving;
    Animation Alpha;
    SharedPreferences mPrefs;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_splash);
        Splash = findViewById(R.id.Login_icon);
        callbackManager = CallbackManager.Factory.create();
        mPrefs = getSharedPreferences("Login",MODE_PRIVATE);
        String fb = mPrefs.getString("Login","");
        if(!fb.equals("")){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent();
                }
            }, 1500);
        }


        LoginButton loginbutton = findViewById(R.id.FB_LB);
        loginbutton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                final SharedPreferences.Editor mEditor = mPrefs.edit();
                mEditor.putString("Login","login");
                mEditor.apply();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            SharedPreferences nPrefs = getSharedPreferences("UserName",MODE_PRIVATE);
                            SharedPreferences.Editor nEditor = nPrefs.edit();
                            Name = object.getString("name");
                            nEditor.putString("UserName",Name);
                            nEditor.apply();
                            Log.e("Name","Facebook name is "+Name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
                Intent();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr", error.toString());

            }
        });
        Moving = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.login_icon_anim);
        Alpha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.login_login_anim);

        Splash.startAnimation(Moving);
        loginbutton.startAnimation(Alpha);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
    public void Intent(){
        Intent intent = new Intent(Splash.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}