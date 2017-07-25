package example.com.smu_1_demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by KDH on 2017-07-24.
 */

public class SplashActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Toast.makeText(this, "로딩중", Toast.LENGTH_SHORT).show();

        //Toast의 LENGTH_SHORT는 2초입니다.

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);//그래서 2초뒤에 MainActivity로 이동하게 하였습니다.
    }
}
