package com.nearking_web.NKDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nearking_web.model.AddCartList;

import java.util.ArrayList;


public class DBAdapter {
    private static final String TAG = "DBAdapter";

    public static final String DATABASE_NAME = "NKing.db";

    public static final String ROW_ID = "_id";
    public static final String CART_TABLE_NAME = "addCartProduct";
    public static final String CART_PROD_ID = "prod_id";
    public static final String CART_PROD_NAME = "prod_name";
    public static final String CART_PROD_PRICE = "prod_price";
    public static final String CART_PROD_IMAGE = "prod_image";
    public static final String CART_PROD_TYPE = "prod_type";
    public static final String CART_PROD_QTY = "prod_qty";


    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE =
            "create table addCartProduct(_id integer primary key autoincrement," +
                    " prod_id text not null,prod_name text not null, prod_price text not null, prod_image text not null, prod_type text not null);";


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    ArrayList<AddCartList> cartList = new ArrayList<AddCartList>();

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
                // db.execSQL(DATABASE_STARTDUTY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS addCartProduct");
            //  db.execSQL("DROP TABLE IF EXISTS startDutyInfo");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close() {
        DBHelper.close();
    }

    //---insert a contact into the database---

    public boolean insertCartProduct(String prod_id, String prod_name, String prod_price, String prod_image, String prod_type) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CART_PROD_ID, prod_id);
        contentValues.put(CART_PROD_NAME, prod_name);
        contentValues.put(CART_PROD_PRICE, prod_price);
        contentValues.put(CART_PROD_IMAGE, prod_image);
        contentValues.put(CART_PROD_TYPE, prod_type);
        System.out.println("DATA Insert Successfully******"+prod_id+"**"+prod_name+"**"+prod_price+"***"+prod_image+"**"+prod_type);
        db.insert(CART_TABLE_NAME, null, contentValues);
        return true;
    }


    //---deletes a particular items---
    public boolean deletecartProduct(long rowId) {
        return db.delete(CART_TABLE_NAME, ROW_ID + "=" + rowId, null) > 0;
    }


    //-----------deletes all  row----------
    public int deleteAllCartProduct() {
        return db.delete(CART_TABLE_NAME, null, null);
    }

    public Cursor getAllCartproduct() {
        return db.query(CART_TABLE_NAME, new String[]{ROW_ID,CART_PROD_ID, CART_PROD_NAME, CART_PROD_PRICE, CART_PROD_IMAGE, CART_PROD_TYPE}, null, null, null, null, null);
    }


    //---retrieves a particular contact---
    public Cursor getCartProduct(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, CART_TABLE_NAME, new String[]{ROW_ID,CART_PROD_ID, CART_PROD_NAME, CART_PROD_PRICE, CART_PROD_IMAGE, CART_PROD_TYPE}, ROW_ID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    //---updates a contact---

    public boolean updateCartProduct(long rowid, String prod_id,String prod_name, String prod_price, String prod_image, String prod_type) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CART_PROD_ID, prod_id);
        contentValues.put(CART_PROD_NAME, prod_name);
        contentValues.put(CART_PROD_PRICE, prod_price);
        contentValues.put(CART_PROD_IMAGE, prod_image);
        contentValues.put(CART_PROD_TYPE, prod_type);
        System.out.println("DATA update Successfully******");
        return db.update(CART_TABLE_NAME, contentValues, ROW_ID + "=" + rowid, null) > 0;
    }



/*    public Cursor CheckTable() {
        String count = "SELECT count(*) FROM endDutyInfo";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            System.out.println("HAve Data********" + icount);
        }
        else {
            System.out.println("No Data**********" + icount);
        }
        return mcursor;
    }*/


    public ArrayList<AddCartList> getcartPro() {
        Cursor cursor = db.rawQuery("select * from " + CART_TABLE_NAME, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    AddCartList addCartList = new AddCartList();
                    addCartList.setPid(cursor.getString(1));
                    addCartList.setProduct_name(cursor.getString(2));
                    addCartList.setProduct_price(cursor.getString(3));
                    addCartList.setProduct_image(cursor.getString(4));
                    addCartList.setProduct_type(cursor.getString(5));
                    cartList.add(addCartList);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            cursor.close();
        }
        return cartList;

    }
}
