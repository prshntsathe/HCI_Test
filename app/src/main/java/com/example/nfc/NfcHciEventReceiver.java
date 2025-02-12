package com.example.nfc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class NfcHciEventReceiver extends BroadcastReceiver {
    private static final String TAG = "NfcHciEventReceiver";
    private Handler uiHandler;

    // Constructor to receive handler from the activity
    public NfcHciEventReceiver(Handler handler) {
        this.uiHandler = handler;
    }

    public NfcHciEventReceiver() {
        String T = "NfcHciEventReceiver";
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (NfcAdapter.ACTION_TRANSACTION_DETECTED.equals(intent.getAction())) {
            byte[] aid = intent.getByteArrayExtra("android.nfc.extra.AID");
            byte[] data = intent.getByteArrayExtra("android.nfc.extra.DATA");

            Log.d(TAG, "NFC HCI Event Detected");
            Log.d(TAG, "AID: " + bytesToHex(aid));
            Log.d(TAG, "Data: " + bytesToHex(data));

            String aidHex = (aid != null) ? bytesToHex(aid) : "AID is null";
            String dataHex = (data != null) ? bytesToHex(data) : "Data is null";

            // Send message to UI thread
            Message message = uiHandler.obtainMessage();
            message.obj = "AID: " + aidHex + "\nData: " + dataHex;
            if (uiHandler != null)
                uiHandler.sendMessage(message);
        }
    }

    private String bytesToHex(byte[] bytes) {
        if (bytes == null) return "null";
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }
}
