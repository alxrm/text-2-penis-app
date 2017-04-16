package rm.com.text2penis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by alex
 */
public final class PenisTextView extends View {
  private static final int REPEAT_COUNT = 250;
  private static final int TEXT_SIZE = 14;
  private static final long ANIMATION_TIME = 300;
  private static final float RELATIVE_UNITS_COUNT = 500;

  @NonNull private final Paint paintPenis = new Paint(Paint.ANTI_ALIAS_FLAG);

  @NonNull private final DimenConverter to = new DimenConverter(getContext());
  @NonNull private final TextCalibrator calibrator = new TextCalibrator(paintPenis);
  @NonNull private final TextRepeat repeat = new TextRepeat(REPEAT_COUNT);
  @NonNull private final MeasuredPath pathPenis = new MeasuredPath();

  @Nullable private String text;

  private float centerY;
  private float centerX;

  private int cachedWidth;
  private int cachedHeight;

  public PenisTextView(Context context) {
    this(context, null);
  }

  public PenisTextView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PenisTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    this.centerY = h * 0.85F;
    this.centerX = w * 0.5F;
    this.cachedWidth = w;
    this.cachedHeight = h;

    to.changeDensity(((float) h) / RELATIVE_UNITS_COUNT);
    initPath();
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (text != null) {
      canvas.drawColor(Color.WHITE);
      //canvas.drawTextOnPath(text, pathPenis, 0, 0, paintPenis);

      paintPenis.setStyle(Paint.Style.STROKE);
      canvas.drawPath(pathPenis, paintPenis);
    }
  }

  final int cachedWidth() {
    return cachedWidth;
  }

  final int cachedHeight() {
    return cachedHeight;
  }

  final void changeText(@Nullable final String next) {
    if (next == null || next.isEmpty()) {
      return;
    }

    postDelayed(new Runnable() {
      @Override public void run() {
        text = calibrator.transform(pathPenis, repeat.transform(next));
        redraw();
      }
    }, ANIMATION_TIME);
  }

  private void init() {
    initPaint();
  }

  private void initPaint() {
    paintPenis.setColor(Color.BLACK);
    paintPenis.setStyle(Paint.Style.FILL);
    paintPenis.setTextSize(to.sp(TEXT_SIZE));
  }

  private void initPath() {
    pathPenis.setDimensionConverter(to);
    pathPenis.moveTo(centerX, centerY);
    pathPenis.rCubicTo(150, 60, 150, -120, 50, -110);
    pathPenis.rLineTo(0, -200);
    pathPenis.rCubicTo(0, -80, -100, -80, -100, 0);
    pathPenis.rLineTo(0, 200);
    pathPenis.rCubicTo(-100, -10, -100, 170, 50, 110);
  }

  private void redraw() {
    invalidate();
  }
}
