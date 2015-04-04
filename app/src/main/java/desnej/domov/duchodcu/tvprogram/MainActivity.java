package desnej.domov.duchodcu.tvprogram;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private SQLiteDatabase _database;
    private SQLHelper _dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        _dbHelper = new SQLHelper(this, "TVProgramDB", null, 1);
        _database = _dbHelper.getWritableDatabase();

        GridView listChannels = (GridView)findViewById(R.id.listChannels);
        ListView listShow = (ListView)findViewById(R.id.listShows);

        List<ChannelItem> channels = new ArrayList<>();
        channels.add(new ChannelItem("CT 1",R.drawable.ct1));
        channels.add(new ChannelItem("CT 2",R.drawable.ct2));
        channels.add(new ChannelItem("Nova",R.drawable.nova));
        channels.add(new ChannelItem("Prima",R.drawable.prima));
        channels.add(new ChannelItem("Prima Cool",R.drawable.prima_cool));

        //ArrayAdapter channelsAddapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,channels);
        ChannelAdapter channelsAddapter = new ChannelAdapter(this,R.layout.grid_channel_item,channels);
        listChannels.setAdapter(channelsAddapter);

        List<String> porady = new ArrayList<>();
        ArrayAdapter poradyAddapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, porady);
        listShow.setAdapter(poradyAddapter);

        DownloadXmlTask i = new DownloadXmlTask();

        try
        {
            ProgramList pl = i.execute("http://profi-program.com/ex/xml.php?tv=nova&den=2015-04-03&web=hosting.pilsfree.net%2Fqwerty%2F", "nova", "2015-4-03", _database, poradyAddapter).get();
        } catch (Exception e)
        {
            Toast.makeText(this, "Nacteni programu selhalo.", Toast.LENGTH_LONG);
            Log.d(MainActivity.class.getSimpleName(), "Load of XML failed " + e.toString());
        }


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
