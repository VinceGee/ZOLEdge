package zw.co.vokers.zoledge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.models.WifiModel;

public class WifiAdapter extends ArrayAdapter<WifiModel> {

    private final Context context;
    private final int textViewResourceId;
    private ArrayList<WifiModel> wifiModels;

    public WifiAdapter(Context context, int textViewResourceId,
                       ArrayList<WifiModel> wifiModels) {
        super(context, textViewResourceId, wifiModels);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.wifiModels = wifiModels;
    }

    public int getCount() {
        return wifiModels.size();
    }

    public WifiModel getItem(int position) {
        return wifiModels.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View spView = inflater.inflate(R.layout.select_wifi_spinner_item, parent, false);
        TextView tvSsid = (TextView) spView.findViewById(R.id.tv_ssid);
        TextView tvType = (TextView) spView.findViewById(R.id.tv_type);
        tvSsid.setText(wifiModels.get(position).getSsid());
        tvType.setText(wifiModels.get(position).getType());
        return spView;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View dropDownView = inflater.inflate(R.layout.select_wifi_spinner_item, parent, false);

        TextView tvSsid = (TextView) dropDownView.findViewById(R.id.tv_ssid);

        TextView tvType = (TextView) dropDownView.findViewById(R.id.tv_type);

        tvSsid.setText(wifiModels.get(position).getSsid());
        tvType.setText(wifiModels.get(position).getType());
        return dropDownView;

    }

}


