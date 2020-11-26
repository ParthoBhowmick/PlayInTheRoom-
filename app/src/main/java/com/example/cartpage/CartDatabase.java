package com.example.cartpage;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {

    private static CartDatabase instance;

    public abstract CartDao cartDao();

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CartDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CartDao cartDao;

        private PopulateDbAsyncTask(CartDatabase db) {
            cartDao = db.cartDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cartDao.insert(new Cart("Floor Mat",  "JRPCMWDGGX-M", "https://5.imimg.com/data5/KJ/PD/MY-4029679/designer-ladies-sandal-500x500.jpg", 1, 300, 2, "size : 50-80cm-2"));
            cartDao.insert(new Cart("Taco Holder Wave Shape Kitchen Food Pizza Shell Restaurant Baking Tool Stand Rack Display Pastry Stainless Steel Mexican Pancake(Silver)", "0123","https://i5.walmartimages.com/asr/421ae399-b27e-4830-8983-4f711dea9c20_1.1ff865fa911a2124bd19e7a31bd3148a.jpeg?odnWidth=612&odnHeight=612&odnBg=ffffff",2 , 400, 5, null));
            cartDao.insert(new Cart("Casual Non-Slip Males Slippers", "XMA012-R-44", "https://5.imimg.com/data5/NM/ET/MY-11928137/exclusive-ladies-slipper-500x500.jpg", 2, 580, 2, "color: red-2,size: 44" ));
            return null;
        }
    }
}