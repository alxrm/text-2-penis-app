package rm.com.text2penis;

import android.graphics.Path;
import android.support.annotation.NonNull;

/**
 * Created by alex
 */

final class MeasuredPath extends Path {

  private DimenConverter to;

  final void setDimensionConverter(@NonNull DimenConverter converter) {
    Preconditions.checkNotNull(converter);
    this.to = converter;
  }

  @Override public final void rMoveTo(float dx, float dy) {
    super.rMoveTo(to.dp(dx), to.dp(dy));
  }

  @Override public final void rLineTo(float dx, float dy) {
    super.rLineTo(to.dp(dx), to.dp(dy));
  }

  @Override public final void rQuadTo(float dx1, float dy1, float dx2, float dy2) {
    super.rQuadTo(to.dp(dx1), to.dp(dy1), to.dp(dx2), to.dp(dy2));
  }

  @Override public final void rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
    super.rCubicTo(to.dp(x1), to.dp(y1), to.dp(x2), to.dp(y2), to.dp(x3), to.dp(y3));
  }
}
