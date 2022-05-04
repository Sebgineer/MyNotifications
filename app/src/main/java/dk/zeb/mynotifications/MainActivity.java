package dk.zeb.mynotifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private String id = "Zeb Notify";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        this.createChannel(this.id);
    }

    /** Create An Channel with that id if its not been created before
     * @param id the name of the id you want */
    public void createChannel(String id) {
        //checks if phone version is greater then or is version 26 (OREO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(id);
            if (channel == null) {
                channel = new NotificationChannel(id, "My Notifications To You", notificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("[Description]");
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    /** Creates a simple notification
     * @param channelId id of the channel you want your notification to be
     * @param title sets the title on the notification
     * @param text sets the text on the notification
     * @return Simple Notification*/
    public Notification createSimpleTextNotification(String channelId, String title, String text) {
        //checks if phone version is greater then or is version 26 (OREO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, channelId)
                    //sets the notification icon
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    //sets the notification content title
                    .setContentTitle(title)
                    //sets the notification content text
                    .setContentText(text)
                    //when clicked it closed if true
                    .setAutoCancel(true)
                    //sets the priority on the notification
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    //builds the notification
                    .build();
            return notification;
        }
        return new Notification();
    }

    /** Creates a Notification with Big Text style
     * @param channelId id of the channel you want your notification to be
     * @param title sets the title on the notification
     * @param text sets the text on the notification
     * @param bigText sets the big text on the notification */
    public Notification createBigTextNotification(String channelId, String title, String text, String bigText) {
        //checks if phone version is greater then or is version 26 (OREO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, channelId)
                    //sets the notification content title
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    //sets the notification content title
                    .setContentTitle(title)
                    //sets the notification content text
                    .setContentText(text)
                    //sets style of the notification
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                    //when clicked it closed if true
                    .setAutoCancel(true)
                    //sets the priority on the notification
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    //builds the notification
                    .build();
            return notification;
        }
        return new Notification();
    }

    /** Creates a Notification click event that throw you to the url
     * @param channelId id of the channel you want your notification to be
     * @param title sets the title on the notification
     * @param text sets the text on the notification
     * @param url sets the url for the ACTION_VIEW on the notification */
    public Notification createLinkNotification(String channelId, String title, String text, String url) {
        //checks if phone version is greater then or is version 26 (OREO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Action View Intent
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            Notification notification = new NotificationCompat.Builder(this, channelId)
                    //sets the notification content title
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    //sets the notification content title
                    .setContentTitle(title)
                    //sets the notification content text
                    .setContentText(text)
                    //sets the pending Intent
                    .setContentIntent(pendingIntent)
                    //when clicked it closed if true
                    .setAutoCancel(true)
                    //sets the priority on the notification
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    //builds the notification
                    .build();
            return notification;
        }
        return new Notification();
    }

    public void onNotify1(View view) {
        Notification notification = this.createSimpleTextNotification(this.id, "Notify Simple", "Hello to you");
        this.notificationManager.notify(0, notification);
    }

    public void onNotify2(View view) {
        Notification notification = this.createBigTextNotification(this.id, "Nine Cats", "Na", "na na na na na na na na na na na na na na na na na na na na na na");
        this.notificationManager.notify(1, notification);
    }

    public void onNotify3(View view) {
        Notification notification = this.createLinkNotification(this.id, "Dogs", "WOOF", "https://www.youtube.com/watch?v=QH2-TGUlwu4&ab_channel=NyanCat");
        this.notificationManager.notify(2, notification);
    }


}