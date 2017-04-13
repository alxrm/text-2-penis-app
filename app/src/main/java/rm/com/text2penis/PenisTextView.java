package rm.com.text2penis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by alex
 */
public final class PenisTextView extends View {

  @NonNull private final DimenConverter to = new DimenConverter(getContext());

  @NonNull private Paint paintPenis = new Paint(Paint.ANTI_ALIAS_FLAG);
  @NonNull private Path pathPenis = new Path();

  @Nullable private String text;

  private float centerY;
  private float centerX;

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
    this.centerY = h / 2F;
    this.centerX = w / 2F;

    initPath();
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (text != null) {
      canvas.drawTextOnPath(text, pathPenis, 0, 0, paintPenis);
    }
  }

  public void changeText(@Nullable String text) {
    if (text == null || text.isEmpty()) {
      return;
    }

    this.text = text;
    redraw();
  }

  private void init() {
    initPaint();
  }

  private void initPaint() {
    paintPenis.setColor(Color.BLACK);
    paintPenis.setStyle(Paint.Style.STROKE);
    paintPenis.setTextSize(to.sp(14));
  }

  private void initPath() {
    addPenisPath();
    redraw();
  }

  private void addPenisPath() {
    pathPenis.moveTo(centerX, centerY + to.dp(150));
    pathPenis.rCubicTo(to.dp(150), to.dp(60), to.dp(150), -to.dp(120), to.dp(50), -to.dp(110));
    pathPenis.rLineTo(0, -to.dp(200));
    pathPenis.rCubicTo(0, -to.dp(80), -to.dp(100), -to.dp(80), -to.dp(100), 0);
    pathPenis.rLineTo(0, to.dp(200));
    pathPenis.rCubicTo(-to.dp(100), -to.dp(10), -to.dp(100), to.dp(170), to.dp(50), to.dp(110));
  }

  private void redraw() {
    invalidate();
  }
}
