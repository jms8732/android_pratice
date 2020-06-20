package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setMovementMethod(new ScrollingMovementMethod());
        tv2 = (TextView) findViewById(R.id.tv2);

        init();
    }

    private void init() {
        String text = "복수초 \n img \n이른 봄 설산에서 복수초는 모든 야생화 찍사들의 로망이 아닐까 싶다";

        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        int idx = 0;
        while ((idx = text.indexOf("img", idx + 1)) > -1) {
            int end = idx + "img".length();

            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.img1, null);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

            ImageSpan span = new ImageSpan(drawable);
            builder.setSpan(span, idx, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        while ((idx = text.indexOf("복수초", idx + 1)) > -1) {
            int end = idx + "복수초".length();

            StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
            RelativeSizeSpan sizeSpan = new RelativeSizeSpan(2.0f);

            builder.setSpan(styleSpan, idx, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(sizeSpan, idx, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        tv1.setText(builder);

        text = "<font color='RED'>얼레지</font> <br/> <img src='img2'/> <br/> 곰배령에서 만난 봄";

        tv2.setText(Html.fromHtml(text, new myGetter(), null));
    }

    private class myGetter implements Html.ImageGetter {
        @Override
        public Drawable getDrawable(String source) {

            if (source.equals("img2")) {
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.img2, null);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

                return drawable;
            }
            return null;
        }
    }
}