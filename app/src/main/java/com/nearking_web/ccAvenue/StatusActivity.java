package com.nearking_web.ccAvenue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.nearking_web.Activity.NearKingHome;
import com.nearking_web.NKDatabase.DBAdapter;
import com.nearking_web.R;

public class StatusActivity extends AppCompatActivity {
    DBAdapter db;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_status);
        db = new DBAdapter(this);

        db.open();
        db.deleteAllCartProduct();
        db.close();

        Intent mainIntent = getIntent();
        TextView tv4 = (TextView) findViewById(R.id.textView1);
       // tv4.setText(mainIntent.getStringExtra("transStatus"));
    }

    public void showToast(String msg) {
        Toast.makeText(this, "Toast: " + msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(StatusActivity.this,NearKingHome.class);
       // back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(back);
        finish();
    }
}
