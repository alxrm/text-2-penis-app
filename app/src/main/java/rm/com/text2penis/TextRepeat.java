package rm.com.text2penis;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import java.text.MessageFormat;

/**
 * Created by alex
 */

final class TextRepeat {
  private static final String WHITE_SPACE = " ";
  private static final int MIN_COUNT = 1;
  private static final int MAX_COUNT = 1000;

  private final int count;

  TextRepeat(@IntRange(from = MIN_COUNT, to = MAX_COUNT) int count) {
    Preconditions.check(count >= MIN_COUNT && count <= MAX_COUNT,
        MessageFormat.format("Multiply count should be in range {0}..{1}", MIN_COUNT, MAX_COUNT));

    this.count = count;
  }

  @NonNull final String transform(@NonNull String text) {
    Preconditions.checkNotNull(text);

    if (text.isEmpty()) {
      return text;
    }

    final StringBuilder builder = new StringBuilder(text.length() * count + count);

    for (int i = 0; i < count; i++) {
      builder.append(text).append(WHITE_SPACE);
    }

    return builder.toString();
  }
}
