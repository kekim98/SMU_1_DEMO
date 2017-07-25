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

        et1.setFilters(new InputFilter[]{filterAlphaNum}); //이건 필터처리입니다. et2는 제가 xml에서 inputType을 number로 해놔서 숫자키패드만 떠서 예외처리할 필요가 없지만
        //et1은 따로 xml에서 처리가 안되기에 한글과 영어만 받을수있는 필터처리를 한 것 입니다. 필터함수는 onCreate 바깥에 있습니다.

        et1.addTextChangedListener(new TextWatcher()//에딧텍스트에서 자주 쓰는 함수입니다. 주로 입력값의 변화를 써야할때 사용합니다.
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // 입력되는 텍스트에 변화가 있을 때 활성화 되는 함수
                if((et1.length() > 0) && (et2.length() > 0))
                    button.setEnabled(true); //
                else
                    button.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable arg0)
            {
                // 입력이 끝났을 때 활성화 되는 함수
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // 입력하기 전에 활성화 되는 함수
            }
        });

        et2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // 입력되는 텍스트에 변화가 있을 때 활성화 되는 함수
                if((et1.length() > 0) && (et2.length() > 0))
                    button.setEnabled(true);
                else
                    button.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable arg0)
            {
                // 입력이 끝났을 때 활성화 되는 함수
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // 입력하기 전에 활성화 되는 함수
            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            public static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";

            public void onClick(View view)
            {
                String name = et1.getText().toString();
                String code = et2.getText().toString();

                Pattern ps = Pattern.compile("^[ㄱ-ㅣ가-힣a-zA-Z]*$");//이것은 특정 String에 이값으로만 검사하겠다는 것 입니다. 한글만은 ㄱ-ㅣ가-힣, 영어만은 a-zA-Z 로 입력되니 두개를 합치면 한글과 영어만 검열가능합니다.

                //이부분에서 조건문 안에있던 버튼 비활성화 이벤트를 아얘 빼고 위에서 보셨던 것처럼 처리했습니다.

                if((name.getBytes().length <= 0) || !ps.matcher(name).matches()) //이런식으로 이름만 들어가야 되는 부분은 ! <-- 이 NOT처리(이값이 아닐때)로 처리해 주면 한글 또는 영어가 아닐때 가 되겠죠?
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
                    notificationIntent.putExtra("name", name); //전달할 값
                    notificationIntent.putExtra("code", code); //전달할 값
                    PendingIntent contentIntent = PendingIntent.getActivity(Adress.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Adress.this);

                    builder.setContentTitle(name)
                            .setContentText(code)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
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
                    editor.putString(name, janr_txt).apply(); //프리퍼런스에 가져온 지원분야를 저장합니다.
                    editor.commit();
                    finish();
                }
            }
        });
    }

    public InputFilter filterAlphaNum = new InputFilter() //이게 위에 에딧텍스트에서 쓴 필터코드 입니다.
    {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
        {
            Pattern ps = Pattern.compile("^[ㄱ-ㅣ가-힣a-zA-Z]*$");
            if(!ps.matcher(source).matches())//et1 에딧텍스트에 글자가 입력될 때마다 값을 가져와서 그 값이 한글 또는 영어가 맞는지 아닌지 검열후 아니라면 토스트를 띄웁니다.
            {
                Toast.makeText(Adress.this, "특수문자, 숫자는 입력할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    };
}