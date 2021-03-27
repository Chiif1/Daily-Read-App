package com.nugrs.newsapp_dailyread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import android.app.DownloadManager;
import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String News_Url;
    String title;
    TextView title1;
    TextView title2;
    TextView title3;
    TextView title4;
    TextView title5;
    TextView title6;
    TextView source1;
    TextView source2;
    TextView source3;
    TextView source4;
    TextView source5;
    TextView source6;
    TextView name1;
    TextView name2;
    TextView name3;
    TextView name4;
    TextView name5;
    TextView name6;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    String urlToImage;
    String source;
    String name;
    String url;
    ArrayList<String> urlToImageArray;
    ArrayList<String> titleArray;
    ArrayList<String> urlArray;
    ArrayList<String> sourceArray;
    ArrayList<String> nameArray;
    LinearLayout linearLay;
    DownloadManager downloadManager;
    OkHttpClient client = new OkHttpClient();
    Handler parseHandler = new Handler();
    String responseToParse = null;
    /*private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;*/
    private RecyclerView mRecyclerview;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       News_Url = "https://newsapi.org/v2/top-headlines?q=money&from=2021-03-15&sortBy=publishedAt&apiKey=0de47076b15346e9b88989314d0e8938";

        //downoladJSON(News_Url);

        mRecyclerview = findViewById(R.id.recycerView);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        downoladJSON(News_Url);

        /*title1 = (TextView) findViewById(R.id.title1);
        name1 = (TextView) findViewById(R.id.name1);
        image1 = (ImageView) findViewById(R.id.image1);

        title2 = (TextView) findViewById(R.id.Title2);
        name2 = (TextView) findViewById(R.id.name2);
        image2 = (ImageView) findViewById(R.id.image2);

        title3 = (TextView) findViewById(R.id.Title3);
        name3 = (TextView) findViewById(R.id.name3);
        image3 = (ImageView) findViewById(R.id.image3);

        title4 = (TextView) findViewById(R.id.Title4);
        name4 = (TextView) findViewById(R.id.name4);
        image4 = (ImageView) findViewById(R.id.image4);

        title5 = (TextView) findViewById(R.id.Title5);
        name5 = (TextView) findViewById(R.id.name5);
        image5 = (ImageView) findViewById(R.id.image5);

        LinearLayout holdList1 = (LinearLayout) findViewById(R.id.storyHolder1);
        holdList1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String website = urlArray.get(1);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(website));
                startActivity(intent);
            }
        });

        RelativeLayout holdList2 = (RelativeLayout) findViewById(R.id.holdList2);
        holdList2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String website = urlArray.get(1);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(website));
                startActivity(intent);
            }
        });
        RelativeLayout holdList3 = (RelativeLayout) findViewById(R.id.holdList3);
        holdList3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String website = urlArray.get(2);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(website));
                startActivity(intent);
            }
        });

        RelativeLayout holdList4 = (RelativeLayout) findViewById(R.id.holdList4);
        holdList4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String website = urlArray.get(3);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(website));
                startActivity(intent);
            }
        });

        RelativeLayout holdList5 = (RelativeLayout) findViewById(R.id.holdList5);
        holdList5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String website = urlArray.get(4);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(website));
                startActivity(intent);
            }
        });*/
    }

    public void testParse() {
        News_Url = "https://newsapi.org/v2/top-headlines?q=money&from=2020-12-20&sortBy=publishedAt&apiKey=0de47076b15346e9b88989314d0e8938";
            JsonObjectRequest jor = new JsonObjectRequest(com.android.volley.Request.Method.GET, News_Url, null,
                    new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
    }

   /* public void parseJSON() {
        News_Url = "https://newsapi.org/v2/top-headlines?q=money&from=2020-12-20&sortBy=publishedAt&apiKey=0de47076b15346e9b88989314d0e8938";
        Log.d("MyApp", ">>>>> parsed JsonRequest Start");

        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, News_Url, (String) null,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(Object response) {

                    try {
                        JSONArray jsonArray = response.getJSONArray("articles");
                        Log.d("MyApp","Articles: " + jsonArray);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject articles = jsonArray.getJSONObject(i);
                            title = articles.optString("title");

                            JSONObject source = articles.getJSONObject("source");
                            name = source.getString("name");

                            url = articles.optString("url");
                            urlToImage = articles.optString("urlToImage");



                            Log.d("MyApp", urlToImage + title +  name);

                            mExampleList.add(new ExampleItem(urlToImage, title, name));
                            mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
                            mRecyclerview.setAdapter(mExampleAdapter

                            );
                                Log.d("MyApp", ">>>>> parsed completed");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }*/


   /* String run(String url) {

        Request request = new Request.Builder()
                .url(url)
                .build();
        Log.d("MYApp", "Run Started");
        try {
            String response = client.newCall(request).execute().body().toString();
            Log.d("MyApp", "async" + response);
            //return response.body().string();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("MyApp", "Run error");
        }
        return null;
    }*/

    public void downoladJSON(String mNewsUrl) {

        Handler handlerParse = new Handler();
        Handler setViewsHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(mNewsUrl)
                        .build();
                Log.d("MYApp", "Run Started");

                Response response = null;
                try {
                    response = client.newCall(request).execute();

                    responseToParse = response.body().string();
                    Log.d("MyApp", responseToParse);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                parseHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        parsing(responseToParse);

                    }
                });

                setViewsHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        setViews();
                    }
                });
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
    public void parsing(String responseparse){
        JSONObject response = null;
        titleArray = new ArrayList<String>();
        sourceArray = new ArrayList<String>();
        nameArray = new ArrayList<String>();
        urlArray = new ArrayList<String>();
        urlToImageArray = new ArrayList<String>();
        try {
            response = new JSONObject(responseparse);
            JSONArray articles = response.optJSONArray("articles");

            for (int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.optJSONObject(i);
                title = article.optString("title");
                Log.i("MyApp", title);
                Log.i("MyApp", "Json Threaded" + Thread.currentThread().getId());
                // JSONArray source = article.optJSONArray("source");
                JSONObject source = article.getJSONObject("source");
                name = source.optString("name");
                // Log.i("nthe name", "This is the name");
                url = article.optString("url");
                urlToImage = article.optString("urlToImage");

                titleArray.add(title);
                nameArray.add(name);
                urlArray.add(url);
                urlToImageArray.add(urlToImage);

                mExampleList.add(new ExampleItem(urlToImage, title, name, url));


            }
            mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
            mRecyclerview.setAdapter(mExampleAdapter
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setViews(){

       /* for (int i  = 0; i < titleArray.size(); i++) {
           switch (i) {

                case 0:

                    title1.setText(titleArray.get(0));
                    name1.setText(nameArray.get(0));
                    if (urlToImageArray.get(0) != null) {
                       // Picasso.get().load(urlToImageArray.get(0)).fit().centerInside().into(image1);

                        Picasso.get().load(urlToImageArray.get(0)).into(image1);

                    }
                    else {
                        image1.setImageResource(R.drawable.no_image);
                    }
                    Log.i("MyApp", "Setting Views");
                    break;*/

               /* case 1:

                    title2.setText(titleArray.get(1));
                    name2.setText(nameArray.get(1));
                    if (urlToImageArray.get(1) != "null") {
                        Log.i("MyApp", "Which Image" + urlToImageArray.get(1));
                        Picasso.get().load(urlToImageArray.get(1)).fit().centerInside().into(image2);
                    }
                    else {
                            image2.setImageResource(R.drawable.no_image);
                        Log.i("MyApp", "2nd drawable");
                        }

                    break;

                case 2:

                    title3.setText(titleArray.get(2));
                    name3.setText(nameArray.get(2));
                    if (urlToImageArray.get(2) != "null") {
                    Picasso.get().load(urlToImageArray.get(2)).fit().centerInside().into(image3);
                    }
                    else {
                        image3.setImageResource(R.drawable.no_image);
                    }
                break;

                case 3:

                    title4.setText(titleArray.get(3));
                    name4.setText(nameArray.get(3));
                    if (urlToImageArray.get(3) != "null") {
                        Picasso.get().load(urlToImageArray.get(3)).fit().centerInside().into(image4);
                    }
                    else {
                            image4.setImageResource(R.drawable.no_image);
                        }
                break;

                case 4:

                    title5.setText(titleArray.get(4));
                    name5.setText(nameArray.get(4));
                    if (urlToImageArray.get(4) != "null") {
                        Picasso.get().load(urlToImageArray.get(4)).fit().centerInside().into(image5);
                    }
                    else {
                            image5.setImageResource(R.drawable.no_image);
                        }
                break;
                default:
            }
        }*/
    }
}




