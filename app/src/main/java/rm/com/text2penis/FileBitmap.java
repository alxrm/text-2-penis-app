package rm.com.text2penis;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by alex
 */

final class FileBitmap {

  static final String JPG = "jpg";
  static final String PNG = "png";
  static final String WEBP = "webp";

  @NonNull private final Bitmap bitmap;
  @NonNull private final String directory;
  @NonNull private final String name;

  FileBitmap(@NonNull Bitmap bitmap, @NonNull String directory, @NonNull String name) {
    this.bitmap = bitmap;
    this.directory = directory;
    this.name = name;
  }

  @SuppressWarnings("All") @WorkerThread
  @NonNull final File toSavedFile(@NonNull String extension) throws IOException {
    final File file = new File(directory, String.format("%s.%s", name, extension));
    final FileOutputStream outputStream = new FileOutputStream(file);
    file.setReadable(true, false);

    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

    outputStream.flush();
    outputStream.close();

    return file;
  }
}
