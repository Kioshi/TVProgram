package desnej.domov.duchodcu.tvprogram;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class InternetPyco extends AsyncTask<Void,Void,ProgramList> {

    private static final String url = "http://profi-program.com/ex/xml.php?tv=nova&den=2015-04-03&web=hosting.pilsfree.net%2Fqwerty%2F";
    private DefaultHttpClient client = new DefaultHttpClient();

    @Override
    protected ProgramList doInBackground(Void... params) {

        try {

            String xmlData = retrieve(url);
            Serializer serializer = new Persister();

            Reader reader = new StringReader(xmlData);
            ProgramList pl;
            pl = serializer.read(ProgramList.class, reader, false);

            Log.d(InternetPyco.class.getSimpleName(),"ok"+ pl.toString());
            return pl;

        }
        catch (Exception e) {
            Log.d(InternetPyco.class.getSimpleName(),"fail" + e.toString());
        }
        return null;
    }

    public String retrieve(String url) {

        HttpGet getRequest = new HttpGet(url);

        try {

            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();

            if (getResponseEntity != null) {
                return EntityUtils.toString(getResponseEntity);
            }

        }
        catch (IOException e) {
            getRequest.abort();
            Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }

        return null;

    }
}
