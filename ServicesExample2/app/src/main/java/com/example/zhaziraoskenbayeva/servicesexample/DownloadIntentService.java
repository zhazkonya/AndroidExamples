package com.example.zhaziraoskenbayeva.servicesexample;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.future.ResponseFuture;

import java.io.File;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String BROADCAST_DW =
            "com.example.zhaziraoskenbayeva.servicesexample.downloadintentservice";

    public static final String ACTION_DOWNLOAD =
            "com.example.zhaziraoskenbayeva.servicesexample.action.DOWNLOAD";
    private static final String ACTION_BAZ = "com.example.zhaziraoskenbayeva.servicesexample.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_URL =
            "com.example.zhaziraoskenbayeva.servicesexample.extra.URL";
    public static final String EXTRA_FILENAME =
            "com.example.zhaziraoskenbayeva.servicesexample.extra.FILENAME";

    public DownloadIntentService() {
        super("DownloadIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadIntentService.class);
        intent.setAction(ACTION_DOWNLOAD);
        intent.putExtra(EXTRA_URL, param1);
        intent.putExtra(EXTRA_FILENAME, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_URL, param1);
        intent.putExtra(EXTRA_FILENAME, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(action)) {
                final String url = intent.getStringExtra(EXTRA_URL);
                final String filename = intent.getStringExtra(EXTRA_FILENAME);
                handleActionDownload(url, filename);

            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_URL);
                final String param2 = intent.getStringExtra(EXTRA_FILENAME);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionDownload(String url, String filename) {
        File f = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                filename);


        ResponseFuture<File> response = Ion.with(getBaseContext())
                .load(url)
                .write(f);
        response.setCallback(new FutureCallback<File>() {
            @Override
            public void onCompleted(Exception e, File result) {
                if(result != null){
                    Log.d("cats", "done "+ result.getAbsolutePath());
                    publishResult(result.getAbsolutePath());
                }

            }
        });
    }

    private void publishResult(String absolutePath) {
        Intent down = new Intent();
        down.setAction(BROADCAST_DW);
        down.putExtra("filepath", absolutePath);
        sendBroadcast(down);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
