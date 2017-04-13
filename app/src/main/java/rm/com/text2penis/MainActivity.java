package rm.com.text2penis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.penis) PenisTextView penis;
  @BindString(R.string.app_name) String title;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    toolbar.setTitle(title);

    setSupportActionBar(toolbar);

    penis.changeText("Всё нормально");
  }
}
