package rm.com.text2penis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * Created by alex
 */

final class DimenConverter {
  private  float density;
  private final float scaledDensity;

  DimenConverter(@NonNull Context context) {
    Preconditions.checkNotNull(context);
    final DisplayMetrics metrics = context.getResources().getDisplayMetrics();

    this.density = metrics.density;
    this.scaledDensity = metrics.scaledDensity;
  }

  final void changeDensity(float density) {
    this.density = density;
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
