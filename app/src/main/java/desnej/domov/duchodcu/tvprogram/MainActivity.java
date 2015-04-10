package desnej.domov.duchodcu.tvprogram;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView listChannels = (GridView)findViewById(R.id.listChannels);
        ListView listShow = (ListView)findViewById(R.id.listShows);

        List<ChannelItem> channels = new ArrayList<>();
        channels.add(new ChannelItem("CT 1","http://hosting.pilsfree.net/chudy/tv/img/ct1.png"));
        channels.add(new ChannelItem("CT 2","http://hosting.pilsfree.net/chudy/tv/img/ct2.png"));
        channels.add(new ChannelItem("CT 4","http://hosting.pilsfree.net/chudy/tv/img/ct4.png"));
        channels.add(new ChannelItem("CT 24","http://hosting.pilsfree.net/chudy/tv/img/ct24.png"));
        channels.add(new ChannelItem("Nova","http://hosting.pilsfree.net/chudy/tv/img/nova.png"));
        channels.add(new ChannelItem("Nova Cinema","http://hosting.pilsfree.net/chudy/tv/img/cinema.png"));
        channels.add(new ChannelItem("Prima","http://hosting.pilsfree.net/chudy/tv/img/prima.png"));
        channels.add(new ChannelItem("Prima Cool","http://hosting.pilsfree.net/chudy/tv/img/cool.png"));
        channels.add(new ChannelItem("Prima Love","http://hosting.pilsfree.net/chudy/tv/img/love.png"));
        channels.add(new ChannelItem("Prima Zoom","http://hosting.pilsfree.net/chudy/tv/img/zoom.png"));

        ChannelAdapter channelsAddapter = new ChannelAdapter(this,R.layout.grid_channel_item,channels);
        listChannels.setAdapter(channelsAddapter);

        List<String> porady = new ArrayList<>();
        ArrayAdapter poradyAddapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, porady);
        listShow.setAdapter(poradyAddapter);
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
}
