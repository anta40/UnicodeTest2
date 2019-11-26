package com.anta40.app.unicodetest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText edit_text;
    final String SURAT_API_URL = "https://api.banghasan.com/quran/format/json/surat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text = (EditText) findViewById(R.id.edit_text);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, SURAT_API_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        String resp = response.toString();
                        try {
                            JSONObject rawJSONObject = new JSONObject(resp);
                            JSONArray hasil = rawJSONObject.getJSONArray("hasil");

                            int len = hasil.length();

                            for (int x = 0; x < len; x++){
                                JSONObject obj = hasil.getJSONObject(x);
                                System.out.println("Asma: "+obj.getString("asma"));
                                edit_text.append("Asma: "+obj.getString("asma")+"\n");
                            }
                        }
                        catch (JSONException je){

                        }

                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    // txtView.setText("Oops! That didn't work!");
                    }
            });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}
