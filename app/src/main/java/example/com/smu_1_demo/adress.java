package example.com.smu_1_demo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class adress extends AppCompatActivity {
    public static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);

        final EditText et1 = (EditText) findViewById(R.id.editText1);
        final EditText et2 = (EditText) findViewById(R.id.editText2);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";

            public void onClick(View view) {
                String name = et1.getText().toString();
                String code = et2.getText().toString();

                SharedPreferences pref = getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);
                pref.edit().putString(name, code).apply();
                finish();

            }

        });

    }

}

//        public SharedPreferences getPref(Context co){

//            return co.getSharedPreferences(DEMO_PREFERENCE);

//

//        }









//    public class MainActivity extends AppCompatActivity {

//        String[] items = {};

//        String sfName = "myFile";

//        EditText et;

//

//        public void onCreate(Bundle savedInstanceState) {

//            super.onCreate(savedInstanceState);

//            setContentView(R.layout.activity_main);

//

//

//          listview.setOnItemClickListener(mMessageClickedHandler);

//

//

//        }

//

//        private void getPreferences(){

//            SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);

//            pref.getString("hi","");

//        }

//

//        private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {

//            public void onItemClick(AdapterView parent, View view, int position, long id) {

//                SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

//                SharedPreferences.Editor editor = sf.edit();

//                editor.putString("username", et.getText().toString());

//                editor.commit();

//

////                String str = (String) parent.getItemAtPosition(position);

////                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();

//

////                et = (EditText) findViewById(R.id.editText1);

////                SharedPreferences sf = getSharedPreferences(sfName, 0);

////

////                Intent intent = getIntent();

////

////                intent.putExtra("name", "rio");

////                intent.putExtra("age", 10);

////

////                String str = intent.getExtras().getString("name");

////                position = intent.getExtras().getInt("age");

//

////                String str = sf.getString("name","");

//                et.setText("" + sf);

//                finish();

//            }

//            public void displayData(View view){

//                SharedPreferences sharedPref = getSharedPreferences("editText1", Context.MODE_PRIVATE);

//                String sf = sharedPref.getString("username","");

//                }

//        };



//        protected void onStop() {

//            super.onStop();

//            SharedPreferences sf = getSharedPreferences(sfName, 0);

//            SharedPreferences.Editor editor = sf.edit();

//            String str = et.getText().toString();

//            editor.putString("name", str);

//            editor.putString("xx", "xx");

//            editor.commit();

//

//        }
