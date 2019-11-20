package com.hidash.testencryption1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.TextView;

import com.hidash.testencryption1.cryption.CryptionActivity;

import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.Decrypt)
    Button Decrypt;
    @Bind(R.id.Encrypt)
    Button Encrypt;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Crypto String");

        Decrypt.setOnClickListener(this);
        Encrypt.setOnClickListener(this);
    }






    @Override
    public void onClick(View view) {
        if(view==Decrypt){
            Intent intent=new Intent(getApplicationContext(), CryptionActivity.class);
            intent.putExtra("crypto","Decrypt");
            startActivity(intent);

        }else if(view==Encrypt){
            Intent intent=new Intent(getApplicationContext(), CryptionActivity.class);
            intent.putExtra("crypto","Encrypt");
            startActivity(intent);
        }
    }

    String finalOut="";
    private void Encryptioning(final String str){

        try {
            finalOut="";
            for (int i = 0; i < str.length(); i++) {

                //check minim charecter length
                if(i>-1){
                    //for last charecters
                    if(str.length()-1==i){
                        if(str.charAt(i)==str.charAt(i-1)){
                            if(str.charAt(i)!= str.charAt(i-1)){
                                String temp2=str.charAt(i)+"2";
                                finalOut=finalOut.concat(temp2);
                            }
                        }else {

                            String temp = str.charAt(i) + "1";
                            finalOut = finalOut.concat(temp);
                        }

                    }
                    //for continues same characters
                    else if(str.charAt(i)==str.charAt(i+1)){
                        if(str.charAt(i)!= str.charAt(i-1)){
                            String temp2=str.charAt(i)+"2";
                            finalOut=finalOut.concat(temp2);
                        }
                    }
                    //for else cases
                    else {

                        // for normal difrent charecters
                        if(i>1){
                            if(str.charAt(i)!= str.charAt(i-1)) {
                                String temp = str.charAt(i) + "1";
                                finalOut = finalOut.concat(temp);
                            }

                        }
                        // for first charecter
                        else {

                            //for continues same characters
                            if(str.charAt(i)==str.charAt(i+1)){
                                if(str.charAt(i)!= str.charAt(i-1)){
                                    String temp2=str.charAt(i)+"2";
                                    finalOut=finalOut.concat(temp2);
                                }
                            }else {

                                String temp = str.charAt(i) + "1";
                                finalOut = finalOut.concat(temp);
                            }

                        }
                    }


                }else {
                    finalOut=finalOut+"-";
                }


            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {

            Log.d("INPUT IS : ",","+str);
//            output.setText(finalOut+"");
            Log.d("OUTPUT IS : ",","+finalOut);
        }


    }
}
