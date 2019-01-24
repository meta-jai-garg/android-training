package com.metacube.intermediatesecond;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ImageCachingActivity extends AppCompatActivity {
    private ListView listView;
    private ListAdapter listAdapter;
    private List<MovieModelREST> modelRESTS;
    //    private ImageCache cache;
    private File file;
    private File mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_caching);
        listView = findViewById(R.id.list_view);
        modelRESTS = new ArrayList<>();
//        cache = ImageCache.getInstance();
//        cache.initializeCache();
        file = getCacheDir();
        final String url = "http://10.0.2.2:3000/movies";
        try {
            modelRESTS = new DownloadTask().execute(url).get();
            listAdapter = new ListAdapter(this, R.layout.list_item, modelRESTS);
            listView.setAdapter(listAdapter);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    class DownloadTask extends AsyncTask<String, Void, List<MovieModelREST>> {
        @Override
        protected List<MovieModelREST> doInBackground(String... params) {
            List<MovieModelREST> models = new ArrayList<>();
            URL url;
            HttpURLConnection connection = null;
            try {
                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream is = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder builder = new StringBuilder();
                String inputString;
                while ((inputString = reader.readLine()) != null) {
                    builder.append(inputString);
                }
                JSONArray jsonArray = new JSONArray(builder.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    File f =
                            readFileFromCache(Uri.parse(object.getString("url")).getLastPathSegment());
                    if (f != null) {
                        models.add(new MovieModelREST(object.getString("title"),
                                BitmapFactory.decodeStream(new FileInputStream(f))));
                    } else {
                        models.add(new MovieModelREST(object.getString("title"),
                                getImage(object.getString("url"))));
                    }
                }
                is.close();
                reader.close();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return models;
        }

        private Bitmap getImage(String param) {
            URL url;
            HttpURLConnection connection = null;
            Bitmap bitmap = null;
            try {
                url = new URL(param);
                connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                writeFileToCache(bitmap, Uri.parse(param).getLastPathSegment());
                is.close();
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return bitmap;
        }

        private void writeFileToCache(Bitmap bitmap, String fileName) {
            FileOutputStream stream = null;
            File file;
            try {
                file = new File(getApplicationContext().getCacheDir(), fileName);
                stream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert stream != null;
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            return file.getAbsolutePath();
        }

        private File readFileFromCache(String fileName) {
            File file = null;
            for (File f : getCacheDir().listFiles()) {
                if (f.getName().equals(fileName)) {
                    file = f;
                }
            }
            return file;
        }
    }
}
