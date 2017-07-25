package example.com.smu_1_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by YJJ on 2017-07-24.
 */

public class JanrActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janr);




        setTitle("어느 분야에 지원하시겠습니까?");

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        ListView listview = (ListView) findViewById(R.id.listview_select_janr);
        listview.setAdapter(adapter);

        adapter.add("대회 안내");
        adapter.add("운영 지원");
        adapter.add("미디어");
        adapter.add("기술");
        adapter.add("의전 및 언어");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String janr_txt = (String) adapterView.getAdapter().getItem(i);

                Intent janr = new Intent(JanrActivity.this, Adress.class);
                janr.putExtra("janr", janr_txt); //페이지를 이동할 때 선택한 리스트값을 같이 넘깁니다.
                startActivity(janr);

                finish();

            }

        });

    }

}
