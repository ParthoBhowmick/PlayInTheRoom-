package com.example.cartpage;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CartRepository implements FindResponse {
    private CartDao cartDao;
    private LiveData<List<Cart>> allCarts;
    private Cart cart;

    public CartRepository(Application application) {
        CartDatabase database = CartDatabase.getInstance(application);
        cartDao = database.cartDao();
        allCarts = cartDao.getAllCarts();
    }

    public void insert(Cart note) {
        new InsertCartAsyncTask(cartDao).execute(note);
    }

    public void update(Cart note) {
        new UpdateCartAsyncTask(cartDao).execute(note);
    }

    public void delete(Cart note) {
        new DeleteCartAsyncTask(cartDao).execute(note);
    }

    public void deleteAllCarts() {
        new DeleteAllCartsAsyncTask(cartDao).execute();
    }

    public LiveData<List<Cart>> getAllCarts() {
        return allCarts;
    }

    public LiveData<Cart> findProductnAction(String productSku) {
        //new findProductAsyncTask(cartDao).execute(productSku);
        return cartDao.findProductnAction(productSku);
    }

    @Override
    public Cart processFinish(Cart output) {
        return output;
    }


    private static class InsertCartAsyncTask extends AsyncTask<Cart, Void, Void> {
        private CartDao cartDao;

        private InsertCartAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(Cart... notes) {
            cartDao.insert(notes[0]);
            return null;
        }
    }


//    private static class findProductAsyncTask extends AsyncTask<String, Void, Cart> {
//        private CartDao cartDao;
//        private FindResponse delegate = null;
//
//        private findProductAsyncTask(CartDao cartDao) {
//            this.cartDao = cartDao;
//        }
//
//        @Override
//        protected Cart doInBackground(String... notes) {
//            return cartDao.findProductnAction(notes[0]);
//        }
//
//        protected void onPostExecute(Cart cart) {
//            delegate.processFinish(cart);
//        }
//    }

    private static class UpdateCartAsyncTask extends AsyncTask<Cart, Void, Void> {
        private CartDao cartDao;

        private UpdateCartAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(Cart... notes) {
            cartDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteCartAsyncTask extends AsyncTask<Cart, Void, Void> {
        private CartDao cartDao;

        private DeleteCartAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(Cart... notes) {
            cartDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllCartsAsyncTask extends AsyncTask<Void, Void, Void> {
        private CartDao cartDao;

        private DeleteAllCartsAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cartDao.deleteAllCarts();
            return null;
        }
    }
}