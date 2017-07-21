package example.com.smu_1_demo;


import android.content.Intent;

import android.content.SharedPreferences;

import android.net.NetworkInfo;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.Menu;

import android.view.MenuInflater;

import android.view.MenuItem;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ListView;



import java.util.Map;



public class MainActivity extends AppCompatActivity {



    private static final String DEMO_PREFERENCE = "DEMO_PREFERENCE";

    private static final String TAG ="git test";

    private ArrayAdapter<String> mListAdapter;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        ListView listview = (ListView) findViewById(R.id.listview);

        mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        listview.setAdapter(mListAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id ) {

                String name = (String) adapterView.getAdapter().getItem(i);

                SharedPreferences pref = getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);

                String code = pref.getString(name,"UNKNOW");

                Intent o = new Intent(MainActivity.this, Contact.class);

                o.putExtra("name",name);

                o.putExtra("code",code);

                startActivity(o);

            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        SharedPreferences pref = getSharedPreferences(DEMO_PREFERENCE, MODE_PRIVATE);
        Map<String, ?>values = pref.getAll();
        mListAdapter.clear();
        for(String key : values.keySet()){
            mListAdapter.add(key);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menutest:
                Intent intent = new Intent(MainActivity.this, adress.class);
                startActivity(intent);
        }
        return true;
    }
}
