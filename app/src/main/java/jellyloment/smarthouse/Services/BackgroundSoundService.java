package jellyloment.smarthouse.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BackgroundSoundService extends Service {
    public BackgroundSoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }
}
