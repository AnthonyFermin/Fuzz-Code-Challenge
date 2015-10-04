package nyc.anthonyfermin.codechallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import nyc.anthonyfermin.codechallenge.adapters.DataListAdapter;

/**
 * Created by c4q-anthonyf on 10/3/15.
 */
public class ImageViewActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        bindViews();

        final Activity activity = this;

        Intent intent = getIntent();
        if(intent != null) {
            String imageUrl = intent.getStringExtra(DataListAdapter.IMAGE_URL);
            Picasso.with(this).load(imageUrl).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {
                    progressBar.setVisibility(View.GONE);
                    Picasso.with(activity).load(R.drawable.error_image).into(imageView);
                    Toast.makeText(activity, R.string.failed_to_load_image, Toast.LENGTH_SHORT).show();
                }

            });
        }
    }

    private void bindViews() {
        imageView = (ImageView) findViewById(R.id.imageview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // no switch-case needed, about is the only option
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        startActivity(aboutIntent);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
