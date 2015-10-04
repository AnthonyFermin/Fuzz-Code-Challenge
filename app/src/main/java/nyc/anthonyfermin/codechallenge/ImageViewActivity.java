package nyc.anthonyfermin.codechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by c4q-anthonyf on 10/3/15.
 */
public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null) {
            String imageUrl = intent.getStringExtra(DataListAdapter.IMAGE_URL);
            ImageView imageView = new ImageView(this);
            Picasso.with(this).load(imageUrl).into(imageView);
            setContentView(imageView);
        }
    }
}
