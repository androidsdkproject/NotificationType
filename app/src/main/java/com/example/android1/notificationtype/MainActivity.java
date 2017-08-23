package com.example.android1.notificationtype;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button buttonsimpleNotification, buttonBigTextNotification, buttonBigImageNotification, buttonInboxNotication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonsimpleNotification = (Button) findViewById(R.id.simplenoticationbutton);
        buttonsimpleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSimpleNotification("this is simple notification");
            }
        });

        buttonBigTextNotification = (Button) findViewById(R.id.bigtextnoticationbutton);
        buttonBigTextNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBigTextStyleNotification();
            }
        });


        buttonBigImageNotification = (Button) findViewById(R.id.bigimagenoticationbutton);
        buttonBigImageNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBigPictureStyleNotification();
            }
        });


        buttonInboxNotication = (Button) findViewById(R.id.inboxtypenoticationbutton);
        buttonInboxNotication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotificationCompatInboxStyle();
            }
        });

    }


    private void addNotificationCompatInboxStyle() {
        //set intents and pending intents to call activity on click of "show activity" action button of notification
        Intent resultIntent = new Intent(this, WelcomeActivity.class);
        resultIntent.putExtra("type", "NotificationCompatInboxStyle");
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent piResult = PendingIntent.getActivity(this,
                (int) Calendar.getInstance().getTimeInMillis(), resultIntent, 0);

//Assign inbox style notification
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();

        inboxStyle.setBigContentTitle("Inbox Notification");
        inboxStyle.addLine("Message 1.");
        inboxStyle.addLine("Message 2.");
        inboxStyle.addLine("Message 3.");
        inboxStyle.addLine("Message 4.");
        inboxStyle.addLine("Message 5.");
        inboxStyle.setSummaryText("+2 more");

//build notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Inbox style notification")
                        .setContentText("This is test of inbox style notification.")
                        .setStyle(inboxStyle)
                        .addAction(R.drawable.ic_notifications_black_24dp, "show activity", piResult);

// Gets an instance of the NotificationManager service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//to post your notification to the notification bar
        notificationManager.notify(0, mBuilder.build());
    }


    void addBigTextStyleNotification() {
        //To set large icon in notification
        Bitmap icon1 = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

//Assign inbox style notification
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        bigText.setBigContentTitle("Big Text Notification");
        bigText.setSummaryText("By: Author of Lorem ipsum");

//build notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("Big Text notification")
                        .setContentText("This is test of big text style notification.")
                        .setLargeIcon(icon1)
                        .setStyle(bigText);

// Gets an instance of the NotificationManager service
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//to post your notification to the notification bar
        mNotificationManager.notify(0, mBuilder.build());
    }


    void addBigPictureStyleNotification() {
        //set intents and pending intents to call activity on click of "show activity" action button of notification
        Intent resultIntent = new Intent(this, WelcomeActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent piResult = PendingIntent.getActivity(this,
                (int) Calendar.getInstance().getTimeInMillis(), resultIntent, 0);

// Assign big picture notification
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();

        bigPictureStyle.bigPicture(
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.notification)).build();
        //build notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("Big picture notification")
                        .setContentText("This is test of big picture notification.")
                        .setStyle(bigPictureStyle)
                        .addAction(R.drawable.ic_input_black_24dp, "show activity", piResult)
                        .addAction(R.drawable.ic_share_black_24dp, "Share",
                                PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, 0, null));


// Gets an instance of the NotificationManager service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//to post your notification to the notification bar
        notificationManager.notify(0, builder.build());
    }


    private void addSimpleNotification(String message) {
        android.support.v4.app.NotificationCompat.Builder builder =
                new android.support.v4.app.NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("This is Push Notification")
                        .setContentText(message);

        Intent notificationIntent = new Intent(this, WelcomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }
}
