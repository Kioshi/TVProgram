package desnej.domov.duchodcu.tvprogram;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DownloadXmlTask extends AsyncTask<Object, Void, ProgramList>
{
    /*args
    * [0] String xmlAdress
    * [1] String channelName
    * [2] String date
    * [3] SQLiteDatabase database
    * [4] ArrayAdapter adapter
    */
    @Override
    protected ProgramList doInBackground(Object... args)
    {
        try
        {
            ProgramList pl = null;
            String xml = loadXmlFromNetwork(args[0].toString());
            if (xml != null)
                pl = parseXML(xml);
            if (pl != null)
            {
                ArrayAdapter adapter = (ArrayAdapter) args[4];
                List<String> porady = new ArrayList<>();
                for (Porad porad : pl.porady)
                    porady.add(porad.nazev);
                adapter.clear();
                adapter.addAll(porady);
            }
        } catch (IOException e)
        {
            Log.d(DownloadXmlTask.class.getSimpleName(), "Connection error. " + e.toString());
        } catch (Exception e)
        {
            Log.d(DownloadXmlTask.class.getSimpleName(), "XML error. " + e.toString());
        }

        return null;
    }


    private String loadXmlFromNetwork(String url) throws IOException
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(new HttpPost(url));
        HttpEntity httpentity = httpResponse.getEntity();

        return EntityUtils.toString(httpentity);
    }

    private ProgramList parseXML(String xml) throws Exception
    {
        Serializer serializer = new Persister();
        return serializer.read(ProgramList.class, new StringReader(xml), false);
    }
}
