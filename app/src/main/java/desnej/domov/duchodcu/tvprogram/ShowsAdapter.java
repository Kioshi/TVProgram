package desnej.domov.duchodcu.tvprogram;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Stepan on 4/11/2015.
 */
public class ShowsAdapter extends ArrayAdapter<GuideItem>
{

    private int _resource;
    private List<GuideItem> _objects;

    public ShowsAdapter(Context context, int resource, List<GuideItem> objects)
    {
        super(context, resource, objects);
        _objects = objects;
        _resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(_resource, parent, false);
        }

        GuideItem item = _objects.get(position);

        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(item.castime * 1000);
        date.setTimeZone(TimeZone.getDefault());


        TextView textName = (TextView) convertView.findViewById(R.id.nameText);
        textName.setText(item.nazev);

        TextView textDate = (TextView) convertView.findViewById(R.id.timeText);
        String textH = String.format("%s %02d:%02d",
                date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()),
                date.get(Calendar.HOUR), date.get(Calendar.MINUTE));
        textDate.setText(textH);

        ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
        progressBar.setProgress((int) (Math.random() * progressBar.getMax()));

        return convertView;
    }

}
