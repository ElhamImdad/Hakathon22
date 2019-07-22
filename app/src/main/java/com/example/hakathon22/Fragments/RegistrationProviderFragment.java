package com.example.hakathon22.Fragments;

import android.content.Context;
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
import com.example.hakathon22.Helpers.SharedPrefManager;
import com.example.hakathon22.Helpers.VolleySingleton;
import com.example.hakathon22.Models.ProvidingJob;
import com.example.hakathon22.Models.SearchingJob;
import com.example.hakathon22.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationProviderFragment extends Fragment {
    private Spinner spinnerGender;
    private EditText name, email, password ;
    private String token;
    private Button confirm;
    public RegistrationProviderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        name = view.findViewById(R.id.requesterName);
        email = view.findViewById(R.id.textInputEmail);
        password = view.findViewById(R.id.textInputPassword);

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
        final String Email = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();
        final String Name = name.getText().toString().trim();
        final String isCompany = "1";

        String url = "https://linguistixtank.website/job/public/api/auth/signup";
        if ( Email.isEmpty() || Password.isEmpty() || Name.isEmpty() ) {
            Toast.makeText(getContext(), "الرجاء إدخال جميع البيانات", Toast.LENGTH_LONG).show();
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject ob = new JSONObject(response);
                                token = ob.getString("access_token");
                                ProvidingJob user = new ProvidingJob(token);
                                SharedPrefManager.getInstance(getContext()).userLogin(user);

                                Toast.makeText(getContext(), "تم تسجيلك بنجاح", Toast.LENGTH_LONG).show();

                            /*    Fragment f = new RequestsFragment();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.container, f);
                                ft.commit();*/

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
                    params.put("age", "0");
                    params.put("email", Email);
                    //  params.put("password", Password);
                    params.put("name", Name);
                    params.put("type", "NULL");
                    params.put("gender", "NULL");
                    params.put("isCompany", isCompany);

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
