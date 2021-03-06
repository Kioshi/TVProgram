package desnej.domov.duchodcu.tvprogram;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView listChannels = (GridView)findViewById(R.id.listChannels);
        ListView listShow = (ListView)findViewById(R.id.listShows);

        final List<ChannelItem> channels = new ArrayList<>();
        channels.add(new ChannelItem("CT1", "http://hosting.pilsfree.net/chudy/tv/img/ct1.png", "CT1"));
        channels.add(new ChannelItem("CT2", "http://hosting.pilsfree.net/chudy/tv/img/ct2.png", "CT2"));
        channels.add(new ChannelItem("CT24", "http://hosting.pilsfree.net/chudy/tv/img/ct24.png", "CT24"));
        channels.add(new ChannelItem("Nova", "http://hosting.pilsfree.net/chudy/tv/img/nova.png", "Nova"));
        channels.add(new ChannelItem("Nova Cinema", "http://hosting.pilsfree.net/chudy/tv/img/cinema.png", "Nova Cinema"));
        channels.add(new ChannelItem("Prima", "http://hosting.pilsfree.net/chudy/tv/img/prima.png", "Prima"));
        channels.add(new ChannelItem("Prima Cool", "http://hosting.pilsfree.net/chudy/tv/img/cool.png", "Prima Cool"));
        channels.add(new ChannelItem("Prima Love", "http://hosting.pilsfree.net/chudy/tv/img/love.png", "Prima Love"));
        channels.add(new ChannelItem("Prima Zoom", "http://hosting.pilsfree.net/chudy/tv/img/zoom.png", "Prima Zoom"));

        ChannelAdapter channelsAddapter = new ChannelAdapter(this,R.layout.grid_channel_item,channels);
        listChannels.setAdapter(channelsAddapter);
        listChannels.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("CLICK CHANNEL", Integer.toString(position));
                downloadShowsList(view);
            }
        });

        listShow.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("CLICK SHOW", Integer.toString(position));
            }
        });
    }

    public void fillShowsList(ArrayList<GuideItem> shows)
    {
        ListView listShow = (ListView) findViewById(R.id.listShows);
        ShowsAdapter showsAddapter = new ShowsAdapter(this, R.layout.list_guide_item, shows);
        listShow.setAdapter(showsAddapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void downloadShowsList(View view)
    {
        TextView textName = (TextView) view.findViewById(R.id.textName);
        Log.i(MainActivity.class.getSimpleName(), textName.getText().toString());
        RestApiClient.get().getGuide(textName.getText().toString(), new Callback<ArrayList<GuideItem>>()
        {
            @Override
            public void success(ArrayList<GuideItem> items, Response response)
            {
                fillShowsList(items);
            }

            @Override
            public void failure(RetrofitError error)
            {

            }
        });

    }
}
