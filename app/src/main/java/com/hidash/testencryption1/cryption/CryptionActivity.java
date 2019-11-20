package com.hidash.testencryption1.cryption;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hidash.testencryption1.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CryptionActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.input_text)
    EditText input_text;
    @Bind(R.id.Submit) Button Submit;
    @Bind(R.id.output)
    TextView output;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    Intent intent;
    String cryptoType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        intent= getIntent();
        if(intent!=null){
            cryptoType= intent.getStringExtra("crypto");
            if(cryptoType.equals("Decrypt")){
                getSupportActionBar().setTitle("Decryption");
               // input_text.setText("a1n1 1a1p2l1e1 1l1a2p1t1o1p2");

            }else if(cryptoType.equals("Encrypt")) {
                getSupportActionBar().setTitle("Encryption");
               // input_text.setText("an apple laaptop");

            }
        }


        //text change Listener for EditText
        input_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){

                    Submit.setEnabled(true);
                    Submit.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else {
                    Submit.setEnabled(false);
                    Submit.setBackgroundColor(getResources().getColor(R.color.colorPrimarylight));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


    }

   private void Init(){

       Submit.setOnClickListener(this);
//       Submit.setBackgroundColor(getResources().getColor(R.color.colorPrimarylight));
//       Submit.setEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v==Submit){
            if(cryptoType.equals("Decrypt")){
                Decrypting(input_text.getText().toString()+"");

            }else if(cryptoType.equals("Encrypt")) {

                Encryptioning(input_text.getText().toString()+"");
            }
        }

    }

    private void Decrypting(final String str2) {


        try {
            finalOut="";
            for (int i = 0; i < str2.length()+2; i++) {

                if(Character.isLetter(str2.charAt(i))){
                    if(Character.isDigit(str2.charAt(i+1))){
                        Integer siz=Integer.parseInt(String.valueOf(str2.charAt(i+1)));
                        for(int j=0;j<siz;j++){

                            finalOut+=str2.charAt(i)+"";
                        }

                    }


                }else  if(Character.isSpaceChar(str2.charAt(i))){
                    if(Character.isDigit(str2.charAt(i+1))){

                        Integer siz2=Integer.parseInt(String.valueOf(str2.charAt(i+1)));
                        for(int k=0;k<siz2;k++){

                            finalOut+=" ";
                        }
                    }
                }


            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {

            Log.d("INPUT IS : ",","+str2);
            output.setText(finalOut+"");
            Log.d("OUTPUT IS : ",","+finalOut);
        }
    }


    String finalOut="";
    private void Encryptioning(final String str){

        try {
            finalOut="";
            for (int i = 0; i < str.length(); i++) {

                //check minim charecters length
                if(i>-1){
                    //for last charecters
                    if(str.length()-1==i){
                        //for same charectors
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
                        // for first charector
                        if(i==0){
                            String temp = str.charAt(i) + "2";
                            finalOut = finalOut.concat(temp);
                        }
                        else if(str.charAt(i)!= str.charAt(i-1)){
                            String temp2=str.charAt(i)+"2";
                            finalOut=finalOut.concat(temp2);
                        }
                    }
                    //for else cases
                    else {

                        // for normal diffrent charecters
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
                            } else {
                                String temp = str.charAt(i) + "1";
                                finalOut = finalOut.concat(temp);
                            }

                        }
                    }


                }


            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {

            Log.d("INPUT IS : ",","+str);
            output.setText(finalOut+"");
            Log.d("OUTPUT IS : ",","+finalOut);
        }


    }


}
