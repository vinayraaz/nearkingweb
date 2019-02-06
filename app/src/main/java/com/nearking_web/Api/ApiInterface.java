package com.nearking_web.Api;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nearking_web.RequestModel.OrderMain.OrderRequest;
import com.nearking_web.RequestModel.UserLogin;
import com.nearking_web.RequestModel.createMainModel.CreateNewUser;
import com.nearking_web.ResponseModel.BannerResponse;
import com.nearking_web.model.CategoryResponse;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by vinay on 12/8/2017.
 */

public interface ApiInterface {

    //create new user
    @Headers({
            "Content-Type: application/json"
    })
    @POST("customers?oauth_consumer_key=ck_e4b34cc8f1aa8ceae9b3131e8af51ec0b5dfb000&oauth_token&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1548484326&oauth_nonce=5x3wbr&oauth_version=1.0&oauth_signature=+ZYTFKKm9ioOqLa08an53q6wONo=")
    Call<JsonObject> CreateNewUser(@Header("Authorization") String token, @Body CreateNewUser createNewUser);

    @POST("login/")
    Call<JsonObject> getLoin(@Body UserLogin userLogin);

    @GET("banners")
    Call<List<BannerResponse>> getBanner();

    @GET("products/categories")
    Call<List<CategoryResponse>> getCategory(@Query("consumer_key") String consumer_key, @Query("consumer_secret") String consumer_secret);

    @GET("products")
    Call<JsonArray> getProduct(@Query("consumer_key") String consumer_key, @Query("consumer_secret") String consumer_secret);

    //  Products with categories:
    @GET("products")
    Call<JsonArray> getCategoryProduct(@Query("category") String categoryId, @Query("consumer_key") String consumer_key, @Query("consumer_secret") String consumer_secret);
// order API
    // https://www.nearking.com/wp-json/wc/v3/orders?oauth_consumer_key=ck_894c0b2552d9f6181f890a07c4f60692468fc870&oauth_token&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1548571701&oauth_nonce=N8GsPr&oauth_version=1.0&oauth_signature=jX+9tnps+wVtv+Bca2J0sAWj1s0=
    @POST("orders?oauth_consumer_key=ck_894c0b2552d9f6181f890a07c4f60692468fc870&oauth_token&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1548571701&oauth_nonce=N8GsPr&oauth_version=1.0&oauth_signature=jX+9tnps+wVtv+Bca2J0sAWj1s0=")
    Call<JsonObject> getOrder(@Header("Authorization") String authToken, @Body OrderRequest orderRequest);
}























/*

 //https://www.nearking.com/wp-json/wc/v3/customers?oauth_consumer_key=ck_e4b34cc8f1aa8ceae9b3131e8af51ec0b5dfb000&oauth_token&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1548484326&oauth_nonce=5x3wbr&oauth_version=1.0 &oauth_signature=+ZYTFKKm9ioOqLa08an53q6wONo=

// http://13.234.46.56:8000/login/
 // http://13.234.46.56:8000/banners
  // https://www.nearking.com/wp-json/wc/v3/products/categories?consumer_key=ck_1fe73bc89b578730b29acb7e9102834b91aade52&consumer_secret=cs_944f5e9a6d8d3e61e580b1c0b0854f686e301bd1
  //https://www.nearking.com/wp-json/wc/v3/products?consumer_key=ck_1fe73bc89b578730b29acb7e9102834b91aade52&consumer_secret=cs_944f5e9a6d8d3e61e580b1c0b0854f686e301bd1
// https://www.nearking.com/wp-json/wc/v3/products/categories/CategoryID/?filter[limit]=15&consumer_key=ck_1fe73bc89b578730b29acb7e9102834b91aade52&consumer_secret=cs_944f5e9a6d8d3e61e580b1c0b0854f686e301bd1    // https://www.nearking.com/wp-json/wc/v3/products/?category=15&consumer_key=ck_1fe73bc89b578730b29acb7e9102834b91aade52&consumer_secret=cs_944f5e9a6d8d3e61e580b1c0b0854f686e301bd1

*/