package weject.example.rishel.weject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by rishel on 23/08/2015.
 */
public class wservice extends Service {
    private WindowManager windowManager;
    private ImageView chatHead,tray;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    int gscore;

    WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT);
    WindowManager.LayoutParams params1 = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT);
    int width,wdiff ;
    int hdiff;
    private static String savedNumber;
    private static String contactName;
    @Override public IBinder onBind(Intent intent) {
        // Not used
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        savedNumber=(String) intent.getExtras().get("number");
        contactName=(String) intent.getExtras().get("cname");
        if (intent.getBooleanExtra("stop_service", false)){

            stopSelf();
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.cancel(0);
        }else{

            Intent notificationIntent = new Intent(this, wservice.class);
            notificationIntent.putExtra("stop_service", true);
            PendingIntent pendingIntent = PendingIntent.getService(this, 0, notificationIntent, 0);


            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.wejectni)
                            .setContentTitle(contactName+"'s Missed/Rejected call")
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText("Swipe Weject icon towards WhatsApp tray to open "+contactName+"'s WhatsApp chat. Tap this notification to close widget."))
                            .setContentText("Weject icon-->WhatsApp tray. Tap to close.")
                            .setTicker("Weject SwipeCut Widget")
                            .setColor(Color.WHITE)

                            .setOngoing(true);
            mBuilder.setContentIntent(pendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(0, mBuilder.build());
        }
        return START_STICKY;

    }
    @Override public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        gscore=((global)getApplicationContext()).getcheck2();
        wdiff=width-150;
        hdiff=15;

        chatHead = new ImageView(this);
        chatHead.setImageResource(R.drawable.weject);
        tray = new ImageView(this);
        tray.setImageResource(R.drawable.tray2);



        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 0;
        params1.gravity = Gravity.TOP | Gravity.RIGHT;
        params1.x = 0;
        params1.y = 0;

        chatHead.setOnTouchListener(new MyTouchListener());
        windowManager.addView(chatHead, params);
        windowManager.addView(tray, params1);



    }
    class MyTouchListener implements View.OnTouchListener {

        private int X;
        private int Y;
        private float xDelta;
        private float yDelta;

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    chatHead.setImageResource(R.drawable.wejectbig);
                    tray.setImageResource(R.drawable.tray);
                    X = params.x;
                    Y = params.y;
                    xDelta = event.getRawX();
                    yDelta = event.getRawY();
                    windowManager.updateViewLayout(chatHead, params);
                    windowManager.updateViewLayout(tray, params1);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:

                    if ((X - xDelta) <= 0 || (X - xDelta) >= 0) {
                        if (params.x >=wdiff && params.y<=hdiff)
                        {
                            myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                            myClip = ClipData.newPlainText("text", ((global) getApplicationContext()).getcopytext());
                            myClipboard.setPrimaryClip(myClip);
                            Uri mUri = Uri.parse("smsto:"+savedNumber);
                            Intent mIntent = new Intent(Intent.ACTION_SENDTO, mUri);
                            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mIntent.setPackage("com.whatsapp");
                            ((global)getApplication()).addscore(15);
                            ((global)getApplication()).update(((global)getApplication()).getcheck2());
                            startActivity(mIntent);
                            stopSelf();
                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            mNotificationManager.cancel(0);

                            break;
                        }

                        chatHead.setImageResource(R.drawable.weject);
                        tray.setImageResource(R.drawable.tray2);
                        params.x = 0;

                        params.y =0;
                        ;
                        windowManager.updateViewLayout(chatHead, params);
                        windowManager.updateViewLayout(tray, params1);}



                    return true;
                case MotionEvent.ACTION_MOVE:
                    chatHead.setImageResource(R.drawable.wejectbig);
                    tray.setImageResource(R.drawable.tray);
                    params.x = X + (int) (event.getRawX() - xDelta);
                    params.y = Y + (int) (event.getRawY() - yDelta);

//                    if (params.x >= 500&& params.y<=100)
//                    {
//                        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
//                        stopSelf();
//                    }
                    windowManager.updateViewLayout(chatHead, params);
                    windowManager.updateViewLayout(tray, params1);

                    return true;
            }
            return false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null) windowManager.removeView(chatHead);
        if (tray != null) windowManager.removeView(tray);
    }
}
