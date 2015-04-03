package desnej.domov.duchodcu.tvprogram;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by program2 on 3.4.2015.
 */
public class ChannelAdapter extends ArrayAdapter<ChannelItem> {

    private int _resource;
    private List<ChannelItem> _objects;

    public ChannelAdapter(Context context, int resource, List<ChannelItem> objects) {
        super(context, resource, objects);
        _objects = objects;
        _resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(_resource,parent,false);
        }

        ChannelItem item = _objects.get(position);
        TextView textName = (TextView)convertView.findViewById(R.id.textName);
        textName.setText(item.name);

        ImageView logo = (ImageView)convertView.findViewById(R.id.imageLogo);
        logo.setImageResource(item.logo);
        return convertView;
    }
}
