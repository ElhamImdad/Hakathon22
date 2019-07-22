package com.example.hakathon22.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hakathon22.Activities.MainActivity;
import com.example.hakathon22.Helpers.SharedPrefManager;
import com.example.hakathon22.Helpers.VolleySingleton;
import com.example.hakathon22.Models.SearchingJob;
import com.example.hakathon22.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationUserR extends Fragment {
    private Spinner spinnerGender;
    private EditText name, age, email, password, gender, type;
    private String token;
    private Button confirm;
    public RegistrationUserR() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_user_r, container, false);
        spinnerGender = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        name = view.findViewById(R.id.requesterName);
        age = view.findViewById(R.id.age);
        email = view.findViewById(R.id.textInputEmail);
        password = view.findViewById(R.id.textInputPassword);
        type = view.findViewById(R.id.type);

        confirm = view.findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerRequester();
            }
        });
        return view;
    }
    private void registerRequester() {
        final int Age = Integer.parseInt(age.getText().toString().trim());
        final String Email = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();
        final String Name = name.getText().toString().trim();
        final String Type = type.getText().toString().trim();
        final String gender = spinnerGender.getSelectedItem().toString().trim();
        final String isCompany = "0";

        String url = "https://linguistixtank.website/job/public/api/auth/signup";
        if (Age==-1 || Email.isEmpty() || Password.isEmpty() || Name.isEmpty() || Type.isEmpty()) {
            Toast.makeText(getContext(), "الرجاء إدخال جميع البيانات", Toast.LENGTH_LONG).show();
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response fron ===", response);
                            try {

                                JSONObject ob = new JSONObject(response);
                                token = ob.getString("access_token");
                                SearchingJob user = new SearchingJob(token);
                                SharedPrefManager.getInstance(getContext()).userLogin(user);

                                Toast.makeText(getContext(), "تم تسجيلك بنجاح", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("new error>>", e.toString());
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("error response", error.toString());
                        }

                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("age", String.valueOf(Age));
                    params.put("email", Email);
                    params.put("password", Password);
                    params.put("name", Name);
                    params.put("type", Type);
                    params.put("gender", gender);
                    params.put("password_confirmation", Password);
                    params.put("isCompany", String.valueOf(0));
                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        }
    }

}