package jellyloment.smarthouse.Services;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import jellyloment.smarthouse.Activities.AlertaActivity;

import static android.content.ContentValues.TAG;

public class FirebaseMsj extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        String body = remoteMessage.getNotification().getBody();
        String title = remoteMessage.getNotification().getTitle();
        mostrarNotificacion(title, body);

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void mostrarNotificacion(String title, String body) {
        if (title.equals("Prueba")){
            Intent intent = new Intent(this, AlertaActivity.class);
            startActivity(intent);
        }
    }
}
