package com.example.spidersnovel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import spider.impl.chapter.DefaultChapterDetailSpider;
import spider.interfaces.IChapterDetailSpider;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private String mContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                IChapterDetailSpider spider = new DefaultChapterDetailSpider();
                mContent = spider.getChapterDetail("https://www.bxwx9.org/b/5/5740/41270028.html").getContent();
            }
        }).start();
        mTextView = findViewById(R.id.nover_content);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mContent);
            }
        });
    }
}
