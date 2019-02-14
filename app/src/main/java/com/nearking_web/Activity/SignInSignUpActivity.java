package com.nearking_web.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.nearking_web.Api.ApiClient;
import com.nearking_web.Api.ApiInterface;
import com.nearking_web.Api.BannerApiClient;
import com.nearking_web.R;
import com.nearking_web.RequestModel.UserLogin;
import com.nearking_web.RequestModel.createMainModel.CreateBilling;
import com.nearking_web.RequestModel.createMainModel.CreateNewUser;
import com.nearking_web.extra.CommonConstant;
import com.nearking_web.extra.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInSignUpActivity extends AppCompatActivity {
    ImageView newUserIV;
    ApiInterface apiService, nkService;
    TextView alreadyUser, textType, textSkip;
    RelativeLayout signInRelative, signUpRelative;
    LinearLayout signInLinear, signUpLinear;
    String userName, email, mobileno, password, Firstname, Lastname;
    EditText edUsername, edMobileno, edsignupEmail, edSignupPassword, edEmail, edPassword, firstName, lastName;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Utilities utilities;
    SharedPreferences loginShare;
    SharedPreferences.Editor loginEditor;
    String restUserId,restUserEmail,restUserLogin,restUserDisplay;
    String finalUserId,finalUserEmail,finalUserLogin,finalUserDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_signup_layout);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        nkService = BannerApiClient.getClient().create(ApiInterface.class);
        newUserIV = (ImageView) findViewById(R.id.iv_newuser);
        alreadyUser = (TextView) findViewById(R.id.tv_loginuser);
        textType = (TextView) findViewById(R.id.texttype);
        textSkip = (TextView) findViewById(R.id.skip_tv);

        edEmail = (EditText) findViewById(R.id.input_email);
        edPassword = (EditText) findViewById(R.id.input_pass);

        edUsername = (EditText) findViewById(R.id.signup_username);
        edMobileno = (EditText) findViewById(R.id.signup_mobile);
        edsignupEmail = (EditText) findViewById(R.id.signup_email);
        edSignupPassword = (EditText) findViewById(R.id.signup_pass);
        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);


        signInRelative = (RelativeLayout) findViewById(R.id.rl_signin);
        signUpRelative = (RelativeLayout) findViewById(R.id.rl_signup);
        signInLinear = (LinearLayout) findViewById(R.id.signin_submit);
        signUpLinear = (LinearLayout) findViewById(R.id.signup_submit);
        textSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_intent = new Intent(SignInSignUpActivity.this, NearKingHome.class);
                startActivity(home_intent);
                finish();
            }
        });
        alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textType.setText("SignIn");
                signInRelative.setVisibility(View.VISIBLE);
                signUpRelative.setVisibility(View.GONE);
                newUserIV.setVisibility(View.VISIBLE);
            }
        });
        newUserIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textType.setText("SignUp");
                signInRelative.setVisibility(View.GONE);
                signUpRelative.setVisibility(View.VISIBLE);
                newUserIV.setVisibility(View.GONE);
            }
        });
        signInLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edEmail.getText().toString().trim();
                password = edPassword.getText().toString();

                // if (!email.matches(emailPattern)) {
                if (email.isEmpty() || email.equals(null) || email.equals("null")) {
                    Toast.makeText(getApplicationContext(), "Enter valid Username", Toast.LENGTH_SHORT).show();
                } else if ((password.isEmpty() || password.equals(null) || password.equals("null"))) {
                    Toast.makeText(SignInSignUpActivity.this, "Enter more than 6 digits password", Toast.LENGTH_SHORT).show();
                } else {
                    loginAPI();

                }
            }
        });
        signUpLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = edUsername.getText().toString();
                Firstname = firstName.getText().toString();
                Lastname = lastName.getText().toString();
                mobileno = edMobileno.getText().toString();
                email = edsignupEmail.getText().toString().trim();
                password = edSignupPassword.getText().toString();
                if (userName.isEmpty() || userName.equals(null) || userName.equals("null")) {
                    Toast.makeText(getApplicationContext(), "Enter valid username", Toast.LENGTH_SHORT).show();
                } else if (Firstname.isEmpty() || Lastname.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter valid first name & lastname", Toast.LENGTH_SHORT).show();
                } else if (mobileno.isEmpty() || mobileno.equals(null) || mobileno.equals("null") || mobileno.length() < 10) {
                    Toast.makeText(getApplicationContext(), "Enter 10 digits mobile No", Toast.LENGTH_SHORT).show();
                } else if (!email.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "Enter valid email address", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty() || password.equals(null) || password.equals("null") || password.length() < 5) {
                    Toast.makeText(getApplicationContext(), "Enter more than 6 digits password", Toast.LENGTH_SHORT).show();
                } else {
                    utilities.displayProgressDialog(getApplicationContext(), "Processing...", false);
                    CreateNewUser createNewUser = new CreateNewUser();
                    CreateBilling createBilling = new CreateBilling();
                    createBilling.setPhone(mobileno);
                    createNewUser.setEmail(email);
                    createNewUser.setFirstName(Firstname);
                    createNewUser.setLastName(Lastname);
                    createNewUser.setUsername(userName);
                    createNewUser.setPassword(password);
                    createNewUser.setBilling(createBilling);
                    Gson gson = new Gson();
                    System.out.println("gson***" + gson.toJson(createNewUser));

                    Call<JsonObject> call = apiService.CreateNewUser(CommonConstant.AUTH_TOKEN, createNewUser);
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            utilities.cancelProgressDialog();
                            System.out.println("Response***" + response.code());
                            try {
                                if (response.code() == 201) {
                                    JsonObject jsonObject = response.body().getAsJsonObject();
                                    System.out.println(" jsonObject.register***" + jsonObject);
                                    Toast.makeText(getApplicationContext(), "User Create successfull", Toast.LENGTH_SHORT).show();
                                    Intent createIntent = new Intent(SignInSignUpActivity.this, SignInSignUpActivity.class);

                                    startActivity(createIntent);
                                    finish();
                                } else if (response.code() == 400) {
                                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                                    edUsername.setText("");
                                    firstName.setText("");
                                    lastName.setText("");
                                    edMobileno.setText("");
                                    edsignupEmail.setText("");
                                    edSignupPassword.setText("");

                                }
                            } catch (JsonIOException je) {

                            }

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            utilities.cancelProgressDialog();
                            Log.i("ERROR_USER", t.toString());

                        }
                    });
                }

            }
        });
    }

    private void loginAPI() {
        try {
            UserLogin userLogin = new UserLogin();
            userLogin.setUsername(email);
            userLogin.setPassword(password);
            utilities.displayProgressDialog(SignInSignUpActivity.this, "Processing...", false);
            Gson gson = new Gson();
            System.out.println(" jsonObject.login***userLogin**" + gson.toJson(userLogin));
            Call<JsonObject> call = nkService.getLoin(userLogin);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    utilities.cancelProgressDialog();

                    if (response.code() == 200) {

                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        try {
                            Intent home_intent = new Intent(SignInSignUpActivity.this,NearKingHome.class);
                            JsonObject jsonObject = response.body().getAsJsonObject();
                            restUserId = String.valueOf(jsonObject.get("id"));
System.out.println("RESponse***"+jsonObject);
System.out.println("RESponse***"+jsonObject.get("id").toString());
System.out.println("RESponse***"+restUserId);

                            restUserEmail = jsonObject.get("user_email").toString();
                            restUserLogin= jsonObject.get("user_login").toString();
                            restUserDisplay = jsonObject.get("display_name").toString();

                            finalUserId =restUserId;
                            finalUserEmail =restUserEmail.substring(1,restUserEmail.length()-1);
                            finalUserLogin =restUserLogin.substring(1,restUserLogin.length()-1);
                            finalUserDisplay =restUserDisplay.substring(1,restUserDisplay.length()-1);

                            System.out.println(" CommonConstant.LASTNAME***" + finalUserId+"**"+finalUserLogin+"***"+finalUserDisplay);
                            loginShare = getSharedPreferences("NKing_Login", Context.MODE_PRIVATE);
                            loginEditor=loginShare.edit();
                            CommonConstant.LOGIN_STATUS = "true";
                            CommonConstant.USERID = finalUserId;
                            loginEditor.putString("LOGIN_STATUS", CommonConstant.LOGIN_STATUS);
                            loginEditor.putString("USER_ID", finalUserId);
                            loginEditor.putString(CommonConstant.USERID, finalUserId);
                            loginEditor.putString("USER_EMAIL", finalUserEmail);
                            loginEditor.putString("USER_LOGIN", finalUserLogin);
                            loginEditor.putString("DISPLAY_NAME",finalUserDisplay);
                            System.out.println("CommonConstant.USERID****"+CommonConstant.USERID+"***"+finalUserId);

                            loginEditor.commit();
                            startActivity(home_intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username & password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    utilities.cancelProgressDialog();
                    Log.i("LOGINERROR", t.toString());
                }
            });


           /* Intent home_intent = new Intent(SignInSignUpActivity.this, NearKingHome.class);
            CommonConstant.EMAIL = edEmail.getText().toString();

            SharedPreferences sharedPreferences = this.getSharedPreferences("NKing_Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            CommonConstant.LOGIN_STATUS = "true";
            editor.putString("LOGIN_STATUS", CommonConstant.LOGIN_STATUS);
            System.out.println("CommonConstant.LOGIN_STATUS***login**" + CommonConstant.LOGIN_STATUS);
            editor.commit();
            startActivity(home_intent);*/
        } catch (Exception e) {

        }
    }

}
