package com.developer.dinhduy.jsontutorial;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Product> list=new ArrayList<>();
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.id_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
     //   adapter=new ProductAdapter(list,getApplicationContext());
        //recyclerView.setAdapter(adapter);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                // new Class
                new LoadData().execute("https://duyandroid.000webhostapp.com/vd1.php");
            }
        });


    }
    // Create class Asnytask

    public class LoadData extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            return ReadWeb(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            try {
                JSONArray jsonArray=new JSONArray(s);

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject Pr=jsonArray.getJSONObject(i);

                    list.add(new Product(
                            Pr.getString("tenmon"),
                            String.valueOf(Pr.getInt("gia")+" USA"),
                            Pr.getString("hinhanh")

                    ));
                }

                if(list.size()==0){
                    Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "not null", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter=new ProductAdapter(list,getApplicationContext());
            recyclerView.setAdapter(adapter);


        }
    }

    public String  ReadWeb(String Url){
        StringBuilder stringBuilder=new StringBuilder();

        try {
            URL url=new URL(Url);
            URLConnection connection=url.openConnection();

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line+"\n");
            }
            //close buffereader
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();

    }

}
