package com.latynin.testproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.Spinner;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;


public class Reg extends AppCompatActivity {


    ArrayList<String> region = new ArrayList<>();
    ArrayList<String> Vreg = new ArrayList<>();
    String[] im = {"Я","Админ","Водитель"};
    EditText regT;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> regAdapter;
    AppCompatSpinner spinner;
    AppCompatSpinner spinnerReg;
    Animation anim;
    LinearLayoutCompat ln1;
    LinearLayoutCompat ln2;
    Context v;

    VKRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        v = this.getApplicationContext();

        regT = findViewById(R.id.regT);
        ln1 = findViewById(R.id.admin);
        ln2 = findViewById(R.id.driver);
        ln1.setScaleY(0);
        ln2.setScaleY(0);

        request = new VKRequest("database.getRegions", VKParameters.from("v",5.69,"country_id",1,"count",83));
        System.out.println(request.toString());
        request.executeWithListener(new VKreq());


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Vreg);
        regAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, im);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.region);
        spinnerReg = findViewById(R.id.im);
        spinner.setAdapter(adapter);
        spinnerReg.setAdapter(regAdapter);

        spinner.post(new postOnSpin());
        spinnerReg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1){
                    ln1.setScaleY(1);
                    ln2.setScaleY(0);
                    anim = AnimationUtils.loadAnimation(v, R.anim.size_swipe);
                    ln1.startAnimation(anim);
                }else if(position == 2){
                    ln1.setScaleY(0);
                    ln2.setScaleY(1);
                    anim = AnimationUtils.loadAnimation(v, R.anim.size_swipe);
                    ln2.startAnimation(anim);
                }else {
                    ln1.setScaleY(0);
                    ln2.setScaleY(0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        regT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    editVreg(regT.getText().toString());
            }
           }
        );

    }
    void editVreg(String text){
        Vreg.clear();
        Vreg.add("");
        for (int i = 0; i < region.size(); i++) {
            if (region.get(i).toLowerCase().startsWith(text.toLowerCase()))Vreg.add(region.get(i));
        }
        spinner.setSelection(0);
        adapter.notifyDataSetChanged();
    }

    class VKreq extends VKRequest.VKRequestListener{
        @Override
        public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
            super.onProgress(progressType, bytesLoaded, bytesTotal);
        }

        @Override
        public void onError(VKError error) {
            super.onError(error);
        }

        @Override
        public void onComplete(VKResponse response) {
            super.onComplete(response);

            try {
                JSONArray array = response.json.getJSONObject("response").getJSONArray("items");
                for(int i = 0; i < array.length(); i++){
                    region.add(array.getJSONObject(i).getString("title"));
                }
                editVreg("");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    class postOnSpin implements Runnable {
        @Override
        public void run() {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!((CharSequence) parent.getItemAtPosition(position)).equals("")) {
                        regT.setText((CharSequence) parent.getItemAtPosition(position));
                        editVreg("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    regT.setText("");
                }
            });
        }
    }
}

