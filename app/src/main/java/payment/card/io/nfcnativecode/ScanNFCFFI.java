package payment.card.io.nfcnativecode;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;

/**
 * Created by KIT912 on 1/12/2018.
 */

public class ScanNFCFFI implements PassNFCDataListener {

    private static Function cb;

    public static void CallNFCFFI(Function callback)
    {
        cb=callback;
        ScanNFCFFI objScan = new ScanNFCFFI();
        objScan.goToMyActivity();
    }

    public void goToMyActivity()
    {
        NFCReaderActivity.initializeDataListener(this);
        Intent in = new Intent(KonyMain.getActivityContext(), NFCReaderActivity.class);
        KonyMain.getActContext().startActivity(in);
    }

    @Override
    public void passNFCData(String data) {
        try
        {
            Log.d("StandardLib", "data binding " + data);
            cb.executeAsync(new Object[] { data });
            System.out.println(">>>>>>>>>>>>>>>>>>SCAN_RESULT is " + data);
        }
        catch (Exception e)
        {
            System.out.println(">>>>>>>>>>>>>>>>>>JSCallback invocation failed");
            e.printStackTrace();
        }

    }
}
