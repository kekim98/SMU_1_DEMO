package example.com.smu_1_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Contact extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        setTitle("회원 정보");

        TextView nameText = (TextView) findViewById(R.id.name);
        TextView codeText = (TextView) findViewById(R.id.code);
        TextView sangText = (TextView) findViewById(R.id.sang);

        Intent recvintent = getIntent();

        String name = recvintent.getExtras().getString("name");
        String code = recvintent.getExtras().getString("code");
        String sang = recvintent.getExtras().getString("sang");

        nameText.setText(name);
        codeText.setText(code);
        sangText.setText(sang);
    }
}
