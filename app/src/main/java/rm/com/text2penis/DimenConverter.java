package rm.com.text2penis;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * Created by alex
 */

final class DimenConverter {

  @NonNull private final Context context;
  @NonNull private final DisplayMetrics metrics;

  private final float density;
  private final float scaledDensity;

  DimenConverter(@NonNull Context context) {
    Preconditions.checkNotNull(context);

    this.context = context;
    this.metrics = context.getResources().getDisplayMetrics();
    this.density = metrics.density;
    this.scaledDensity = metrics.scaledDensity;
  }

  final float dp(float value) {
    return value * density;
  }

  final float dp(int value) {
    return value * density;
  }

  final float sp(int value) {
    return value * scaledDensity;
  }

  final float sp(float value) {
    return value * scaledDensity;
  }
}
