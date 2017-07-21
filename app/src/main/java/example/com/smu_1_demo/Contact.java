package example.com.smu_1_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Contact extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);

        TextView nameText = (TextView) findViewById(R.id.name);
        TextView codeText = (TextView) findViewById(R.id.code);

        Intent recvintent = getIntent();

        Bundle extras = recvintent.getExtras();

        String name = extras.getString("name","UNKNOWN");

        String code = extras.getString("code","UNKNOWN");



        nameText.setText(name);

        codeText.setText(code);

    }

}
