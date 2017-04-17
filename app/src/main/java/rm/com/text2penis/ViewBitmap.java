package rm.com.text2penis;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by alex
 */

final class ViewBitmap {
  @NonNull private final View view;

  ViewBitmap(@NonNull View view) {
    this.view = view;
  }

  @NonNull final Bitmap renderBitmap(int width, int height) {
    final Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    final Canvas canvas = new Canvas(image);

    view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    view.draw(canvas);

    return image;
  }
}
