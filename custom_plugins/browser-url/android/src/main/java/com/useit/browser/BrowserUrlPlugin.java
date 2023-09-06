package com.useit.browser;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

@CapacitorPlugin(name = "BrowserUrl")
public class BrowserUrlPlugin extends Plugin {

    private BroadcastReceiver broadcastReceiver;

    @Override
    public void load() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (BrowserUrl.ACTION_PAGE_LOADED.equals(intent.getAction())) {
                    notifyListeners("browserPageLoaded", null);
                } else if (BrowserUrl.ACTION_URL_CHANGED.equals(intent.getAction())) {
                    JSObject ret = new JSObject();
                    if (intent.hasExtra(BrowserUrl.EXTRA_CURRENT_URL)) {
                        ret.put("url", intent.getStringExtra(BrowserUrl.EXTRA_CURRENT_URL));
                        notifyListeners("urlCurrent", ret);
                    } else {
                        notifyListeners("urlCurrent", null);
                    }
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BrowserUrl.ACTION_PAGE_LOADED);
        intentFilter.addAction(BrowserUrl.ACTION_URL_CHANGED);
        LocalBroadcastManager.getInstance(getContext())
            .registerReceiver(broadcastReceiver, intentFilter);
    }

    @PluginMethod()
    public void open(PluginCall call) {
        String url = call.getString("url");
        String color = call.getString("color");
        String title = call.getString("title");

        Intent intent = new Intent(getContext(), BrowserUrl.class);
        intent.putExtra("url", url);
        intent.putExtra("color", color);
        intent.putExtra("title", title);
        getContext().startActivity(intent);
        call.resolve();
    }

    @PluginMethod()
    public void removeAllListeners(PluginCall call) {
        // Removes all listeners
        removeAllListeners(call);
        call.resolve();
    }

    @Override
    protected void handleOnDestroy() {
        LocalBroadcastManager.getInstance(getContext())
            .unregisterReceiver(broadcastReceiver);
    }
}
