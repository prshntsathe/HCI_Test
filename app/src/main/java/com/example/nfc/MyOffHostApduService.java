package com.example.nfc;

import android.content.Intent;
import android.nfc.cardemulation.OffHostApduService;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MyOffHostApduService extends OffHostApduService {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public MyOffHostApduService() {
        String T = "NfcHciEventReceiver";
    }
}
