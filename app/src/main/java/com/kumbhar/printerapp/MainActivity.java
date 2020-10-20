package com.kumbhar.printerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.aevi.print.PrinterApi;
import com.aevi.print.PrinterManager;
import com.aevi.print.model.Alignment;
import com.aevi.print.model.FontStyle;
import com.aevi.print.model.PrintJob;
import com.aevi.print.model.PrintPayload;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrinterManager printerManager = PrinterApi.getPrinterManager(this);

        if(printerManager.isPrinterServiceAvailable()) {

            PrintPayload payload = new PrintPayload();

            // fill out payload here. Described below
            payload.append("Owner Info").align(Alignment.LEFT).fontStyle(FontStyle.NORMAL).getUnderlineStyle();
            payload.appendEmptyLine();
            payload.appendLeftRight(1,"Name","Murtaza");
            payload.appendLeftRight(2,"Name","Murtaza");
            payload.appendLeftRight(3,"Name","Murtaza");
            payload.appendLeftRight(3,"Name","Murtaza");
            payload.append("car").align(Alignment.LEFT);


            printerManager.print(payload)
                    .subscribe(new Consumer<PrintJob>() {
                        @Override
                        public void accept(@NonNull PrintJob printResult) throws Exception {
                            // Do something with results here
                            Log.d("Result", "Got printing result:: " + printResult.getPrintJobState());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            Log.e("Result", "Error while printing", throwable);
                        }
                    });
        }
    }
}