package desnej.domov.duchodcu.tvprogram;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

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
        TextView textName = (TextView) convertView.findViewById(R.id.showName);
        textName.setText(item.nazev);

        TextView textDate = (TextView) convertView.findViewById(R.id.dateString);
        textDate.setText(item.cas);
        /*
        GridView gv = (GridView) parent;
        if ((position + 1) % 2 == 0)
            convertView.setBackgroundColor(Color.argb(128, 211, 211, 211));
        else
            convertView.setBackgroundColor(Color.TRANSPARENT);
*/
        return convertView;
    }

}
