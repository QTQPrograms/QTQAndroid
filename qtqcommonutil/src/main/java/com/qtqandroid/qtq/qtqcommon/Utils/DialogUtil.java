package com.qtqandroid.qtq.qtqcommon.Utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qtqandroid.qtq.qtqcommon.Interfaces.OnPromptListener;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

public class DialogUtil {
    abstract static class ProgressDialogHelper {
        Context mContext;
        String mMessage;
        Callable<?> mRunnable;
        Handler mHandler = new Handler();

        Callable<?> mCallableAfterSuccess;

        public void setCallableAfterSuccess(Callable<?> callable) {
            mCallableAfterSuccess = callable;
        }

        public ProgressDialogHelper(Context context, String message,
                                    Callable<?> runnable) {
            this.mContext = context;
            this.mMessage = message;
            this.mRunnable = runnable;
        }

        ProgressDialog mDialog;

        public void Start() {
            mDialog = ProgressDialog.show(mContext, "请稍等", mMessage, true);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        mRunnable.call();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Runnable runnable = new Runnable() {

                            public void run() {
                                DialogUtil.Alert(mContext, mMessage);

                            }

                            String mMessage;

                            public Runnable setMessage(String message) {
                                mMessage = message;
                                return this;
                            }

                        }.setMessage(ex.getMessage());
                        mHandler.post(runnable);
                    } finally {
                        mDialog.dismiss();
                    }
                }

            };
            thread.start();
        }
    }

    public static void Progress(Context context, String message,
                                Callable<?> callable) {
        new ProgressDialogHelper(context, message, callable) {
        }.Start();
    }

    public static void Progress(Context context, String message,
                                Callable<?> callable, Callable<?> callableAfterSuccess) {
        ProgressDialogHelper helper = new ProgressDialogHelper(context,
                message, callable) {
        };

        helper.Start();

    }

    public static void Alert(Context context, String message) {
        new AlertDialog.Builder(context).setMessage(message)
                .setPositiveButton("确定", null).show();
    }

    public static void AlertAndFinish(final Activity activity, String message) {
        new AlertDialog.Builder(activity).setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }

                }).show();
    }

    public static void Alert(View view, String message) {
        Alert(view.getContext(), message);
    }

    public static void AutoHiddenAlert(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void AutoHiddenAlert(View view, String message) {
        AutoHiddenAlert(view.getContext(), message);
    }

    public interface SetDate {
        void setDate(Date date);
    }

    public static void AddSelectDateDialog(TextView arg0,
                                           final SetDate onsetdate) {
        arg0.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {
                final TextView textView = (TextView) view;
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker datePicker, int year,
                                          int month, int day) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);
                        textView.setText(DateTimeUtil.FormatDate(calendar));
                        onsetdate.setDate(calendar.getTime());
                    }

                };

                String dateStr = textView.getText().toString();
                Calendar calendar = DateTimeUtil.SafeParseCalendar(dateStr);

                new DatePickerDialog(view.getContext(), onDateSetListener,
                        calendar.get(Calendar.YEAR), calendar
                        .get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH)).show();
            }

        });
    }

    public static void Prompt(Context context, String title, String message,
                              String inputValue, int inputType, final OnPromptListener onprompt) {
        final EditText input = new EditText(context);
        input.setText(inputValue);
        input.setInputType(inputType);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setView(input)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        String prompt = input.getText().toString();
                        onprompt.OnPrompt(prompt);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    public static void Confirm(Context context, String title,
                               String trueString, String falseString,
                               DialogInterface.OnClickListener onConfirm) {
        new AlertDialog.Builder(context).setTitle(title)
                .setPositiveButton(trueString, onConfirm)
                .setNegativeButton(falseString, null).show();
    }

}
