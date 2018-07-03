package zw.co.vokers.zoledge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.models.VsatModem;
import zw.co.vokers.zoledge.utils.VinceTextView;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> implements Filterable
{
    private static final int TYPE_ROW = 0;
    private static final int TYPE_ROW_COLORFUL = 1;

    private List<VsatModem> vsatModemList;
    private List<VsatModem> filteredVsatModemList;
    private Context context;

    public ClubAdapter(Context context, List<VsatModem> vsatModemList)
    {
        this.context = context;
        this.vsatModemList = vsatModemList;
        this.filteredVsatModemList = vsatModemList;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position % 2 == 0)
        {
            return TYPE_ROW_COLORFUL;
        }

        return TYPE_ROW;
    }

    @Override
    public ClubViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        if (viewType == TYPE_ROW)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_club, viewGroup, false);
            return new ClubViewHolder(view);
        } else
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_club_colorful,
                    viewGroup, false);
            return new ClubViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ClubViewHolder holder, int position)
    {
        VsatModem vsatModem = filteredVsatModemList.get(position);

        holder.txtName.setText(vsatModem.nameVsat);
        holder.txtLocation.setText(vsatModem.descriptionVsat);


        Glide.with(context).load(vsatModem.imageeee).into(holder.imgLogo);
    }

    @Override
    public int getItemCount()
    {
        return filteredVsatModemList.size();
    }

    public class ClubViewHolder extends RecyclerView.ViewHolder
    {
        public VinceTextView txtName, txtLocation;
        public ImageView imgLogo;

        public ClubViewHolder(View view)
        {
            super(view);
            txtName = view.findViewById(R.id.txtName);
            txtLocation = view.findViewById(R.id.txtDescription);

            imgLogo = view.findViewById(R.id.imgLogo);
        }
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                String charString = charSequence.toString();
                if (charString.isEmpty())
                {
                    filteredVsatModemList = vsatModemList;
                } else
                {
                    List<VsatModem> filteredList = new ArrayList<>();
                    for (VsatModem vsatModem : vsatModemList)
                    {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name
                        if (vsatModem.nameVsat.toLowerCase().contains(charString.toLowerCase()) )
                        {
                            filteredList.add(vsatModem);
                        }
                    }

                    filteredVsatModemList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredVsatModemList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                filteredVsatModemList = (ArrayList<VsatModem>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
}