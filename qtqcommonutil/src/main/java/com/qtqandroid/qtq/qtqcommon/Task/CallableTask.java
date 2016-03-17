package com.qtqandroid.qtq.qtqcommon.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.qtqandroid.qtq.qtqcommon.Utils.DialogUtil;

import java.util.concurrent.Callable;


public abstract class CallableTask implements Callable<Object> {
    final String tag = "CallableTask";

    Boolean mIsRunning = false;

    protected abstract void successUI();

    void failUI(Exception e) {
        Log.e(tag, e.getMessage(), e);
        DialogUtil.Alert(mContext, e.getMessage());
    }

    Context mContext;
    ProgressDialog mDialog;
    String mMessage;

    public CallableTask(Context context, String message) {
        mContext = context;
        mMessage = message;
    }

    public final void execute() {
        if (mIsRunning) {
            Log.w(tag, "任务重复执行");
            return;
        }
        new Task().execute();
    }

    class Task extends AsyncTask<Void, Void, Void> {
        Exception mException;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                mIsRunning = true;
                call();
            } catch (Exception ex) {
                mException = ex;
                cancel(false);
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            endExecute();
            failUI(mException);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            endExecute();
            successUI();
        }

        public void endExecute() {
            if (mDialog != null) {
                mDialog.dismiss();
            }
            mIsRunning = false;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog = ProgressDialog.show(mContext, "请稍等", mMessage, true);
        }

    }

}
