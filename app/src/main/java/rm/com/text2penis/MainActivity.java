package rm.com.text2penis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class MainActivity extends AppCompatActivity {
  private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindString(R.string.app_name) String title;

  @BindView(R.id.intro) RelativeLayout intro;
  @BindView(R.id.intro_text) EditText moodText;
  @BindView(R.id.intro_proceed) FloatingActionButton proceed;

  @BindView(R.id.show) RelativeLayout show;
  @BindView(R.id.penis) PenisTextView penis;
  @BindView(R.id.share) FloatingActionButton share;
  @BindView(R.id.undo) FloatingActionButton undo;

  private InputMethodManager inputManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    toolbar.setTitle(title);
    setSupportActionBar(toolbar);

    this.inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
  }

  @OnTextChanged(R.id.intro_text) final void onTextChange(@NonNull CharSequence mood) {
    if (mood.length() == 0) {
      proceed.hide();
    } else {
      proceed.show();
    }
  }

  @OnClick(R.id.intro_proceed) final void onShowPenis() {
    final String mood = moodText.getText().toString();

    if (mood.isEmpty()) {
      return;
    }

    penis.changeText(mood);
    intro.setVisibility(View.GONE);
    show.setVisibility(View.VISIBLE);

    hideKeyboard(moodText);
  }

  @OnClick(R.id.undo) final void onHidePenis() {
    show.setVisibility(View.GONE);
    intro.setVisibility(View.VISIBLE);
  }

  @OnClick(R.id.share) final void onShare() {
    final Bitmap penisBitmap = loadBitmapFromView(penis);

    EXECUTOR.submit(new Runnable() {
      @Override public void run() {
        shareBitmap(penisBitmap, "penis");
      }
    });
  }

  @Override public void onBackPressed() {
    if (show.isShown()) {
      onHidePenis();
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("ResultOfMethodCallIgnored") @SuppressLint("SetWorldReadable")
  private void shareBitmap(@NonNull Bitmap bitmap, @NonNull String fileName) {
    try {
      final File file = new File(getCacheDir(), fileName + ".png");
      final FileOutputStream fOut = new FileOutputStream(file);
      final Intent intent = new Intent(android.content.Intent.ACTION_SEND);

      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
      fOut.flush();
      fOut.close();
      file.setReadable(true, false);

      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
      intent.setType("image/jpg");
      startActivity(intent);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Bitmap loadBitmapFromView(PenisTextView p) {
    Bitmap image = Bitmap.createBitmap(p.cachedWidth(), p.cachedHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(image);
    p.layout(p.getLeft(), p.getTop(), p.getRight(), p.getBottom());
    p.draw(canvas);
    return image;
  }

  private void hideKeyboard(@NonNull View fromView) {
    inputManager.hideSoftInputFromWindow(fromView.getWindowToken(), 0);
  }
}
