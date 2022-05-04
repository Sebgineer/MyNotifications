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
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onNotify1(View view) {
        String id = "MyID";
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(id);
            if (channel == null) {
                channel = new NotificationChannel(id, "My Notifications To You", notificationManager.IMPORTANCE_HIGH);
                channel.setDescription("[Description]");
                channel.enableVibration(false);
                //channel.setVibrationPattern(new long[]{100, 1000, 100, 1000});
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                notificationManager.createNotificationChannel(channel);
            }

            Notification notification = new NotificationCompat.Builder(this, id)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText("kagemand")
                    .setContentTitle("Title")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Kagemand fjsdl nldal fsdlfsd lknflsd nfl sdnlnfdsfn ewusd nksde iwsdn fskfne iwdfjksf sdfh wendhsd nhifhsd nfuisdfhnsdifhsdi hfsdhd n9sdfh nsdifsdfh nsdf nehwnsdfns dsn fsdknk "))
                    .setPriority(2)
                    .setVibrate(new long[]{100, 1000, 100, 1000})
                    .setAutoCancel(false)
                    .build();
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
            notificationManager.notify(0, notification);
        }
    }
}