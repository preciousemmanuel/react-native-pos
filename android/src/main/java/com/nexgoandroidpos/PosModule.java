// package com.cicod;

// import com.cicod.R;

// import com.facebook.react.bridge.NativeModule;
// import com.facebook.react.bridge.Promise;
// import com.facebook.react.bridge.Callback;
// import com.facebook.react.bridge.ReactApplicationContext;
// import com.facebook.react.bridge.ReactContext;
// import com.facebook.react.bridge.ReactContextBaseJavaModule;
// import com.facebook.react.bridge.ReactMethod;
// import com.facebook.react.bridge.ReadableArray;
// import com.facebook.react.bridge.ReadableType;
// import com.facebook.react.bridge.UiThreadUtil;
// import com.facebook.react.modules.core.DeviceEventManagerModule;

// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;

// import java.io.*;

// import com.nexgo.oaf.apiv3.APIProxy;
// import com.nexgo.oaf.apiv3.DeviceEngine;
// import com.nexgo.oaf.apiv3.SdkResult;
// import com.nexgo.oaf.apiv3.device.beeper.Beeper;
// import com.nexgo.oaf.apiv3.device.led.LEDDriver;
// import com.nexgo.oaf.apiv3.device.led.LightModeEnum;
// import com.nexgo.common.ByteUtils;

// import com.cicod.model.Item;

// import android.graphics.Bitmap;
// import android.graphics.Matrix;
// import android.graphics.BitmapFactory;
// import android.graphics.Typeface;
// //import androidx.appcompat.app.AlertDialog;
// //import android.app.AlertDialog.Builder;
// //import androidx.appcompat.app.AppCompatActivity;
// import android.app.Activity;
// import android.view.LayoutInflater;
// import android.view.View;
// import android.app.AlertDialog;
// import android.content.DialogInterface;
// import android.view.LayoutInflater;
// import android.widget.TextView;

// import com.nexgo.oaf.apiv3.device.printer.AlignEnum;
// import com.nexgo.oaf.apiv3.device.printer.BarcodeFormatEnum;
// import com.nexgo.oaf.apiv3.device.printer.DotMatrixFontEnum;
// import com.nexgo.oaf.apiv3.device.printer.FontEntity;
// import com.nexgo.oaf.apiv3.device.printer.GrayLevelEnum;
// import com.nexgo.oaf.apiv3.device.printer.OnPrintListener;
// import com.nexgo.oaf.apiv3.device.printer.Printer;
// import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity;
// import com.nexgo.oaf.apiv3.device.reader.CardReader;
// import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum;
// import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener;
// import com.nexgo.oaf.apiv3.device.pinpad.AlgorithmModeEnum;
// import com.nexgo.oaf.apiv3.device.pinpad.CalcModeEnum;
// import com.nexgo.oaf.apiv3.device.pinpad.MacAlgorithmModeEnum;
// import com.nexgo.oaf.apiv3.device.pinpad.OnPinPadInputListener;
// import com.nexgo.oaf.apiv3.device.pinpad.PinAlgorithmModeEnum;
// import com.nexgo.oaf.apiv3.device.pinpad.PinKeyboardModeEnum;
// import com.nexgo.oaf.apiv3.device.pinpad.PinPad;
// import com.nexgo.oaf.apiv3.device.pinpad.PinPadKeyCode;
// import com.nexgo.oaf.apiv3.device.pinpad.WorkKeyTypeEnum;

// import android.os.Handler;
// import android.os.Looper;

// //import java.util.List;
// import java.util.HashSet;
// import java.util.ArrayList;
// import java.io.IOException;
// import java.net.URL;

// import java.util.logging.Logger;

// public class PosModule extends ReactContextBaseJavaModule {

//     public DeviceEngine deviceEngine;
//     private Logger log;
//     private Printer printer;
//     private PinPad pinpad;
//     private static ReactApplicationContext reactContext;
//     private Bitmap bitmap;
//     private final int FONT_SIZE_SMALL = 20;
//     private final int FONT_SIZE_NORMAL = 24;
//     private final int FONT_SIZE_BIG = 24;
//     private FontEntity fontSmall = new FontEntity(DotMatrixFontEnum.CH_SONG_20X20, DotMatrixFontEnum.ASC_SONG_8X16);
//     private FontEntity fontNormal = new FontEntity(DotMatrixFontEnum.CH_SONG_24X24, DotMatrixFontEnum.ASC_SONG_12X24);
//     private FontEntity fontBold = new FontEntity(DotMatrixFontEnum.CH_SONG_24X24, DotMatrixFontEnum.ASC_SONG_BOLD_16X24);
//     private FontEntity fontBig = new FontEntity(DotMatrixFontEnum.CH_SONG_24X24, DotMatrixFontEnum.ASC_SONG_12X24, false, true);

//     private AlertDialog alertDialog;

//     PosModule(ReactApplicationContext context) {
//         super(context);
//         reactContext = context;

//         deviceEngine = APIProxy.getDeviceEngine(context);
//         deviceEngine.getEmvHandler2("App2");
//         log = Logger.getLogger(this.getClass().getName());
//     }

//     @Override
//     public String getName() {
//         return "PosModule";
//     }


//     @ReactMethod
//     public void beeper() {
//         final Beeper beeper = deviceEngine.getBeeper();
//         beeper.beep(500);
//     }

//     @ReactMethod
//     public void initLed(boolean isOn) {
//         LEDDriver ledDriver = deviceEngine.getLEDDriver();
//         ledDriver.setLed(LightModeEnum.GREEN, isOn);
//     }

//     public ArrayList deserializeItems(String jsonStr) {
//         return new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Item>>() {
//         }.getType());
//     }


//     @ReactMethod
//     public void printReceipt(String logoUrl, String companyName, String location, String city, String email,
//                              String phone, String orderId, String status, String date, String items,
//                              String currency, double totalAmount, double deliveryAmount, String deliveryType) {

// //        ReadableArray readableArray = new ReadableNativeArray(items);

//         printer = deviceEngine.getPrinter();
//         printer.initPrinter();                              //init printer
//         printer.setTypeface(Typeface.DEFAULT);            //change print type
//         printer.setLetterSpacing(5);                        //change the line space between each line
//         printer.setGray(GrayLevelEnum.LEVEL_2);             //change print gray

//         //Convert image from url to bitmap
//         Bitmap image;
//         try {
// //            URL url = new URL("http://....");
//             image = BitmapFactory.decodeStream(new URL(logoUrl).openConnection().getInputStream());
// //            bitmap = image;
//             int width = image.getWidth() / 4; // resize width to 1/4 of original size
//             int height = image.getHeight() / 4; // resize height to 1/4 of original size
//             Bitmap resizedImage = Bitmap.createScaledBitmap(image, width, height, true); // resize image
//             bitmap = resizedImage;


// //            image = BitmapFactory.decodeStream(new URL(logoUrl).openConnection().getInputStream());
// //            int width = image.getWidth();
// //            int height = image.getHeight();
// //            float scaleWidth = ((float) 200) / width;
// //            float scaleHeight = ((float) 200) / height;
// //            Matrix matrix = new Matrix();
// //            matrix.postScale(scaleWidth, scaleHeight);
// //            Bitmap scaledBitmap = Bitmap.createBitmap(image, 0, 0, width, height, matrix, false);
// //            bitmap = scaledBitmap;
//         } catch (IOException e) {
//             System.out.println(e);
//         }

// //        bitmap = BitmapFactory.decodeResource(reactContext.getResources(), R.drawable.ic_launcher_background);
//         printer.appendImage(bitmap, AlignEnum.LEFT);      //append image
//         printer.appendPrnStr("Company Name: " + companyName, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("Location: " + location, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("City: " + city, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("Email: " + email, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("Phone: " + phone, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);

//         printer.appendPrnStr("=========================", FONT_SIZE_NORMAL, AlignEnum.LEFT, false);

//         printer.appendPrnStr("Order ID: " + orderId, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("Status: " + status, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("Date: " + date, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("Currency: " + currency, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);

//         printer.appendPrnStr("Items: =====================", FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("Name       Qty       Price", " ", fontNormal);
//         printer.appendPrnStr("-------------------------------------", FONT_SIZE_NORMAL, AlignEnum.LEFT, false);

//         ArrayList<Item> newItems = deserializeItems(items);
//         for (Item item : newItems) {
//             printer.appendPrnStr(item.getName() + "       " + item.getQuantity() + "       " + item.getPrice(), FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//             printer.appendPrnStr("----------------------------------------", FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         }

//         printer.appendPrnStr("Total Amount: " + totalAmount, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("Delivery Type: " + deliveryType, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("Delivery Amount: " + deliveryAmount, FONT_SIZE_NORMAL, AlignEnum.LEFT, false);
//         printer.appendPrnStr("==============================", FONT_SIZE_NORMAL, AlignEnum.LEFT, false);

//         if (deliveryType == "DELIVERY") {
//             double grandTotal = totalAmount + deliveryAmount;
//             printer.appendPrnStr("GRAND TOTAL: ", String.valueOf(grandTotal), fontBold);
//         } else {
//             printer.appendPrnStr("GRAND TOTAL: ", String.valueOf(totalAmount), fontBold);
//         }
//         printer.appendPrnStr("------------------------------------------", FONT_SIZE_NORMAL, AlignEnum.LEFT, false);

//         printer.appendPrnStr("Thanks for your patronage", FONT_SIZE_NORMAL, AlignEnum.LEFT, false);

//         printer.startPrint(false, new OnPrintListener() {       //roll paper or not
//             @Override
//             public void onPrintResult(final int retCode) {
//                 new Handler(Looper.getMainLooper()).post(new Runnable() {
//                     @Override
//                     public void run() {
//                         int printerStatus = printer.getStatus();
//                         System.out.println("ddls printer status: " + printerStatus);


//                         if (printerStatus == SdkResult.Success) {
//                             System.out.println("ddls print successful");
//                         } else if (printerStatus == SdkResult.Printer_UnFinished) {
//                             System.out.println("ddls print NOT Complete");
//                         } else if (printerStatus == SdkResult.Printer_PaperLack) {
//                             System.out.println("ddls out of paper!");
//                         } else if (printerStatus == SdkResult.Printer_TooHot) {
//                             System.out.println("ddls printer is overheating...");
//                         } else if (printerStatus == SdkResult.Printer_Print_Fail) {
//                             System.out.println("ddls printer failed");
//                         } else if (printerStatus == SdkResult.Fail) {
//                             System.out.println("ddls Other error...");
//                         } else {
//                             System.out.println("ddls unknown error...");
//                         }
//                     }
//                 });
//             }
//         });
//     }

//     @ReactMethod
//     public void cardReader(final Callback failure, final Callback success) {

//         final CardReader cardReader = deviceEngine.getCardReader();
//         HashSet<CardSlotTypeEnum> slotTypes = new HashSet<>();
//         slotTypes.add(CardSlotTypeEnum.SWIPE);
//         slotTypes.add(CardSlotTypeEnum.ICC1);
//         slotTypes.add(CardSlotTypeEnum.RF);
//         cardReader.searchCard(slotTypes, 60, new OnCardInfoListener() {
//             @Override
//             public void onCardInfo(int retCode, CardInfoEntity cardInfo) {
// //                final StringBuilder sb = new StringBuilder();
//                 if (cardInfo != null) {
//                     success.invoke("Success");
//                 }
//                 new Handler(Looper.getMainLooper()).post(new Runnable() {
//                     @Override
//                     public void run() {
//                         //Todo: call PinPadActivity
//                     }
//                 });
//             }

//             @Override
//             public void onSwipeIncorrect() {
//                 failure.invoke("Failure");

//                 new Handler(Looper.getMainLooper()).post(new Runnable() {
//                     @Override
//                     public void run() {
//                         // Show error message
//                     }
//                 });
//             }

//             @Override
//             public void onMultipleCards() {
//                 cardReader.stopSearch();
//                 new Handler(Looper.getMainLooper()).post(new Runnable() {
//                     @Override
//                     public void run() {
//                     }
//                 });
//             }
//         });
//     }


//     String text = "";

//     @ReactMethod
//     private void inputPinTest() {
//         pinpad = deviceEngine.getPinPad();
//         pinpad.setAlgorithmMode(AlgorithmModeEnum.DES);
//         pinpad.setPinKeyboardMode(PinKeyboardModeEnum.FIXED);
//         text = "";
//         View dv = LayoutInflater.from(reactContext).inflate(R.layout.dialog_inputpin_layout, null);
//         final TextView tv = (TextView) dv.findViewById(R.id.input_pin);

//         Activity activity = reactContext.getCurrentActivity();
//         if (activity != null) {
//             alertDialog = new AlertDialog.Builder(activity).setView(dv).create();
//             alertDialog.setCanceledOnTouchOutside(true);
//             alertDialog.show();
//         }

//         int[] supperLen = new int[]{0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c};
//         byte[] pan = ByteUtils.string2ASCIIByteArray("1234567890123");
//         pinpad.inputOnlinePin(supperLen, 60, pan, 10, PinAlgorithmModeEnum.ISO9564FMT1, new OnPinPadInputListener() {
//             @Override
//             public void onInputResult(final int retCode, byte[] data) {

//                 new Handler(Looper.getMainLooper()).post(new Runnable() {
//                     @Override
//                     public void run() {
//                         if (alertDialog != null) {
//                             alertDialog.dismiss();
//                             alertDialog = null;
//                         }
//                     }
//                 });
//             }

//             @Override
//             public void onSendKey(byte keyCode) {
//                 if (keyCode == PinPadKeyCode.KEYCODE_CLEAR) {
//                     text = "";
//                 } else {
//                     text += "* ";
//                 }
//                 sendPinUpdateEvent(text);

//                 new Handler(Looper.getMainLooper()).post(new Runnable() {
//                     //                UiThreadUtil.runOnUiThread(new Runnable() {
//                     @Override
//                     public void run() {
//                         try {
//                             tv.setText(text);
//                         } catch (Exception e) {
//                             System.out.println("ddls Exception: ");
//                         }
//                     }
//                 });
//             }
//         });
//     }

//     private void sendPinUpdateEvent(String text) {
//         reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
//                 .emit("pinUpdateEvent", text);
//     }

// }