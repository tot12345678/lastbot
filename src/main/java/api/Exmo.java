package api;

import okhttp3.*;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class Exmo {
    private static long _nonce;
    private String _key;
    private String _secret;

    public Exmo(String key, String secret) {
        _nonce = System.nanoTime();
        _key = key;
        _secret = secret;
    }

    final String Request(String method, Map<String, String> arguments) {
        Mac mac;
        SecretKeySpec key;
        String sign;

        if (arguments == null) {  // If the user provided no arguments, just create an empty argument array.
            arguments = new HashMap<>();
        }

        arguments.put("nonce", "" + ++_nonce);  // Add the dummy nonce.

        StringBuilder postData = new StringBuilder();

        for (Map.Entry<String, String> stringStringEntry : arguments.entrySet()) {

            if (postData.length() > 0) postData.append("&");
            postData.append(((Map.Entry) stringStringEntry).getKey()).append("=").append(((Map.Entry) stringStringEntry).getValue());
        }

        // Create a new secret key
        key = new SecretKeySpec(_secret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");

        // Create a new mac
        try {
            mac = Mac.getInstance("HmacSHA512");
        } catch (NoSuchAlgorithmException nsae) {
            System.err.println("No such algorithm exception: " + nsae.toString());
            return null;
        }

        // Init mac with key.
        try {
            mac.init(key);
        } catch (InvalidKeyException ike) {
            System.err.println("Invalid key exception: " + ike.toString());
            return null;
        }


        // Encode the post data by the secret and encode the result as base64.
        sign = Hex.encodeHexString(mac.doFinal(postData.toString().getBytes(StandardCharsets.UTF_8)));

        // Now do the actual request
        MediaType form = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        try {

            RequestBody body = RequestBody.create(form, postData.toString());
            Request request = new Request.Builder()
                    .url("https://api.exmo.com/v1/" + method)
                    .addHeader("Key", _key)
                    .addHeader("Sign", sign)
                    .post(body)
                    .build();
            
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            System.err.println("Request fail: " + e.toString());
            return null;  // An error occured...
        }
    }
}
