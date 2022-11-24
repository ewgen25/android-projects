package com.example.picasso;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    ShapeableImageView imageView;
    MaterialButton btn;
    String[] links = new String[]{"https://infoearth.ru/wp-content/uploads/2020/06/flag-korolevstva-tailand-335x220.png", "https://infoearth.ru/wp-content/uploads/2020/06/flag-tunisa-335x220.png",
            "https://infoearth.ru/wp-content/uploads/2020/05/flag-soedinyonnyh-shtatov-ameriki-335x220.png", "https://infoearth.ru/wp-content/uploads/2020/05/flag-somalijskoj-respubliki-335x220.png"
            , "https://infoearth.ru/wp-content/uploads/2020/05/flag-respubliki-uganda-335x220.png", "https://infoearth.ru/wp-content/uploads/2020/04/flag-korolevstva-saudovskoj-aravii-335x220.png"
    };
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.picture);
        btn = findViewById(R.id.nextBtn);

    }

    public void picasso(String url) {

        Picasso.get()
                .load(url)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(535, 420)
                .into(imageView);

    }

    public void OnClicked(View view) {
        if (view.getId() == R.id.nextBtn) {

            int select = random.nextInt(links.length);
            picasso(links[select]);

        }
    }

}