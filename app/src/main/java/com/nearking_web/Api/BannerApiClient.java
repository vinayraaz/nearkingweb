package com.nearking_web.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vinay on 12/8/2017.
 */

public class BannerApiClient {
    //http://www.paypre.info/oms_login_test?UserName=kavitha&UserPwd=amVzdXNqZXN1cw==
    //http://13.234.46.56:8000/banners
    public static final String BASE_URL = "http://13.234.46.56:8000/";
    private static Retrofit retrofit = null;


        public static Retrofit getClient () {
            if (retrofit == null) {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
            return retrofit;
        }

}
