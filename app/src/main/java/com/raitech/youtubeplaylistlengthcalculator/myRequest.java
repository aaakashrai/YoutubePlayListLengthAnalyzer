package com.raitech.youtubeplaylistlengthcalculator;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class myRequest extends StringRequest {

    public myRequest(
            int method,
            String url,
            Response.Listener<String> listener,
            @Nullable Response.ErrorListener errorListener) {
            super(method, url, listener,errorListener);

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        return headers;
    }
}
