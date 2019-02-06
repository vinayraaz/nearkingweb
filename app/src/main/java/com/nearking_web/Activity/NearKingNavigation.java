package com.nearking_web.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nearking_web.NearKing.MyAccount_Activity;
import com.nearking_web.R;
import com.nearking_web.SplashActivity;
import com.nearking_web.extra.CommonConstant;

public class NearKingNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sharpf;
    NavigationView navigationView;
    Menu nav_Menu;
    MenuItem myAccount, signIn, logOut;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_king_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        nav_Menu = navigationView.getMenu();
        myAccount = nav_Menu.findItem(R.id.nav_account);
        signIn = nav_Menu.findItem(R.id.nav_signin);
        logOut = nav_Menu.findItem(R.id.nav_logout);

        sharpf = getSharedPreferences("NKing_Login", Context.MODE_PRIVATE);
        CommonConstant.LOGIN_STATUS = sharpf.getString("LOGIN_STATUS", "0");
       /* CommonConstant.USERID =sharpf.getString(CommonConstant.USERID,"");
        CommonConstant.USEREMAIL =sharpf.getString(CommonConstant.USEREMAIL,"");
        CommonConstant.USERLOGIN =sharpf.getString(CommonConstant.USERLOGIN,"");
        CommonConstant.DISPLAYNAME =sharpf.getString(CommonConstant.DISPLAYNAME,"");
*/
        System.out.println("CommonConstant.LOGIN_STATUS***navigation**" + CommonConstant.LOGIN_STATUS+"***"+CommonConstant.USEREMAIL);
        if (CommonConstant.LOGIN_STATUS.equals("true")) {
            showMenuItems();

        } else {
            hideMenuItem();
        }
    }

    private void showMenuItems() {

        myAccount.setVisible(true);
        logOut.setVisible(true);
        signIn.setVisible(false);
    }

    private void hideMenuItem() {
        signIn.setVisible(true);
        myAccount.setVisible(false);
        logOut.setVisible(false);


    }

    @Override
    public void onBackPressed() {
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.near_king_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ic_cart) {
            Intent nk_sign = new Intent(NearKingNavigation.this, AddCart_Activity.class);
            startActivity(nk_sign);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent nk_home = new Intent(NearKingNavigation.this, NearKingHome.class);
            startActivity(nk_home);

        } else if (id == R.id.nav_account) {
            Intent myaccount = new Intent(NearKingNavigation.this, MyAccount_Activity.class);
            //  myaccount.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(myaccount);
        } else if (id == R.id.nav_signin) {
            Intent nk_sign = new Intent(NearKingNavigation.this, SignInSignUpActivity.class);
            startActivity(nk_sign);
        } /*else if (id == R.id.nav_slideshow) {

        }*/ else if (id == R.id.nav_cart) {
            Intent nk_sign = new Intent(NearKingNavigation.this, AddCart_Activity.class);
            startActivity(nk_sign);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            SharedPreferences shrefClear = this.getSharedPreferences("NKing_Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shrefClear.edit();
            CommonConstant.LOGIN_STATUS = "";
            editor.clear();
            editor.commit();
            Toast.makeText(getApplicationContext(), "Logout successfull", Toast.LENGTH_LONG).show();
            Intent logout = new Intent(NearKingNavigation.this, SignInSignUpActivity.class);
            startActivity(logout);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
