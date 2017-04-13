package rm.com.text2penis;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by alex
 */
final class TextCalibrator {

  @NonNull private final Paint style;

  @Nullable private String prev;
  @Nullable private String calibrated;

  TextCalibrator(@NonNull Paint style) {
    Preconditions.checkNotNull(style, "Text style is null");

    this.style = style;
  }

  @NonNull final String transform(@NonNull Path textPath, @NonNull String text) {
    Preconditions.checkNotNull(text, "Text is null");

    if (text.equals(prev) && calibrated != null) {
      return calibrated;
    }

    final float pathLength = new PathMeasure(textPath, false).getLength();

    for (int i = 0; i < text.length(); i++) {
      final String cut = text.substring(0, i);

      if (pathLength <= style.measureText(cut)) {
        this.prev = text;
        this.calibrated = cut;
        return cut;
      }
    }

    throw new IllegalStateException("Text is too small");
  }
}
