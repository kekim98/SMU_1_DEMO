package example.com.smu_1_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Adress extends AppCompatActivity
{
    public static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);

        setTitle("신상 정보를 입력해 주세요.");

        Intent janr = getIntent();

        final String janr_txt = janr.getExtras().getString("janr"); //리스트 값을 받습니다.

        TextView textViewJanr = (TextView)findViewById(R.id.textview_janr);
        textViewJanr.setText(janr_txt);

        final EditText et1 = (EditText) findViewById(R.id.editText1);
        final EditText et2 = (EditText) findViewById(R.id.editText2);

        final Button button = (Button) findViewById(R.id.button);

        button.setEnabled(false); // 아얘 처음부터 비활성화 시키고 시작됩니다.

        et1.setFilters(new InputFilter[]{filterAlphaNum});

        et1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                if((et1.length() > 0) && (et2.length() > 0))
                    button.setEnabled(true); //
                else
                    button.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable arg0)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }
        });

        et2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                if((et1.length() > 0) && (et2.length() > 0))
                    button.setEnabled(true);
                else
                    button.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable arg0)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            public static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";

            public void onClick(View view)
            {
                String name = et1.getText().toString();
                String code = et2.getText().toString();

                Pattern ps = Pattern.compile("^[ㄱ-ㅣ가-힣a-zA-Z]*$");

                if((name.getBytes().length <= 0) || !ps.matcher(name).matches())
                {
                    Toast.makeText(Adress.this, "이름을 입력/확인하세요", Toast.LENGTH_SHORT).show();
                }
                else if(code.getBytes().length <= 0)
                {
                    Toast.makeText(Adress.this, "학번을 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if((name.getBytes().length <= 0) && !ps.matcher(name).matches() && (code.getBytes().length <= 0))
                {
                    Toast.makeText(Adress.this, "이름과 학번을 입력/확인하세요", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Resources res = getResources();

                    Intent notificationIntent = new Intent(Adress.this, Contact.class);
                    notificationIntent.putExtra("name", name);
                    notificationIntent.putExtra("code", janr_txt);
                    PendingIntent contentIntent = PendingIntent.getActivity(Adress.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Adress.this);

                    builder.setContentTitle(name)
                            .setContentText(code)
                            .setSmallIcon(R.mipmap.ic_launcher3)
                            .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher3))
                            .setContentIntent(contentIntent)
                            .setAutoCancel(true)
                            .setWhen(System.currentTimeMillis());

                    if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                    {
                        builder.setCategory(Notification.CATEGORY_MESSAGE)
                                .setPriority(Notification.PRIORITY_HIGH)
                                .setVisibility(Notification.VISIBILITY_PUBLIC);
                    }

                    NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    nm.notify(1234, builder.build());

                    SharedPreferences pref = getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(name, janr_txt).apply();
                    editor.commit();
                    finish();
                    Toast.makeText(getApplicationContext(),"저장완료",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public InputFilter filterAlphaNum = new InputFilter() //이게 위에 에딧텍스트에서 쓴 필터코드 입니다.
    {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
        {
            Pattern ps = Pattern.compile("^[ㄱ-ㅣ가-힣a-zA-Z]*$");
            if(!ps.matcher(source).matches())
            {
                Toast.makeText(Adress.this, "특수문자, 숫자는 입력할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    };
}