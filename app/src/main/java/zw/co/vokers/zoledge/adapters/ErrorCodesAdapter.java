package zw.co.vokers.zoledge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.models.ErrorModel;
import zw.co.vokers.zoledge.utils.VinceTextView;

public class ErrorCodesAdapter extends RecyclerView.Adapter<ErrorCodesAdapter.ErrorViewHolder> implements Filterable
{
    private static final int TYPE_ROW = 0;
    private static final int TYPE_ROW_COLORFUL = 1;

    private List<ErrorModel> clubList;
    private List<ErrorModel> filteredClubList;
    private Context context;

    public ErrorCodesAdapter(Context context, List<ErrorModel> clubList)
    {
        this.context = context;
        this.clubList = clubList;
        this.filteredClubList = clubList;
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
    public ErrorCodesAdapter.ErrorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        if (viewType == TYPE_ROW)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.error1, viewGroup, false);
            return new ErrorCodesAdapter.ErrorViewHolder(view);
        } else
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.error2,
                    viewGroup, false);
            return new ErrorCodesAdapter.ErrorViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ErrorCodesAdapter.ErrorViewHolder holder, int position)
    {
        ErrorModel club = filteredClubList.get(position);

        holder.txtError.setText(club.errorCode);
        holder.txtErrorProb.setText(club.probIdent);
        holder.txtErrorSolution.setText(club.possSolution);

    }

    @Override
    public int getItemCount()
    {
        return filteredClubList.size();
    }

    public class ErrorViewHolder extends RecyclerView.ViewHolder
    {
        public VinceTextView txtError, txtErrorProb, txtErrorSolution;

        public ErrorViewHolder(View view)
        {
            super(view);
            txtError = view.findViewById(R.id.txtErrorcode);
            txtErrorProb = view.findViewById(R.id.txtErrorProb);
            txtErrorSolution = view.findViewById(R.id.txtErrorSolution);

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
                    filteredClubList = clubList;
                } else
                {
                    List<ErrorModel> filteredList = new ArrayList<>();
                    for (ErrorModel club : clubList)
                    {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name
                        if (club.errorCode.toLowerCase().contains(charString.toLowerCase()) )
                        {
                            filteredList.add(club);
                        }
                    }

                    filteredClubList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredClubList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                filteredClubList = (ArrayList<ErrorModel>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
}
