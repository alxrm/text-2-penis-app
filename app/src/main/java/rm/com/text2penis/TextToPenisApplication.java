package rm.com.text2penis;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import io.fabric.sdk.android.Fabric;

/**
 * Created by alex
 */
public final class TextToPenisApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics(), new Answers());
  }
}
