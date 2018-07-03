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
import zw.co.vokers.zoledge.models.AccountQuotaModel;
import zw.co.vokers.zoledge.utils.VinceTextView;

public class AccountQuotaAdapter extends RecyclerView.Adapter<AccountQuotaAdapter.QuotaViewHolder> implements Filterable
{
    private static final int TYPE_ROW = 0;
    private static final int TYPE_ROW_COLORFUL = 1;

    private List<AccountQuotaModel> clubList;
    private List<AccountQuotaModel> filteredClubList;
    private Context context;

    public AccountQuotaAdapter(Context context, List<AccountQuotaModel> clubList)
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
    public AccountQuotaAdapter.QuotaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        if (viewType == TYPE_ROW)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quota1, viewGroup, false);
            return new AccountQuotaAdapter.QuotaViewHolder(view);
        } else
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quota2,
                    viewGroup, false);
            return new AccountQuotaAdapter.QuotaViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(AccountQuotaAdapter.QuotaViewHolder holder, int position)
    {
        AccountQuotaModel club = filteredClubList.get(position);

        holder.txtPackageName.setText(club.packageName);
        holder.txtSandvineName.setText(club.sandvineName);
        holder.txtEmailAccounts.setText(club.emailAccounts);
        holder.txtDiskQuota.setText(club.diskQuota);
        holder.txtDomainsHosted.setText(club.domainsHosted);
        holder.txtWebsiteHosted.setText(club.websiteHosted);
        holder.txtFreeDomainsRegistered.setText(club.freeDomainsRegistered);

    }

    @Override
    public int getItemCount()
    {
        return filteredClubList.size();
    }

    public class QuotaViewHolder extends RecyclerView.ViewHolder
    {
        public VinceTextView txtPackageName, txtSandvineName, txtEmailAccounts,txtDiskQuota,txtDomainsHosted,txtWebsiteHosted,txtFreeDomainsRegistered;

        public QuotaViewHolder(View view)
        {
            super(view);
            txtPackageName = view.findViewById(R.id.txtPackageName);
            txtSandvineName = view.findViewById(R.id.txtSandvineName);
            txtEmailAccounts = view.findViewById(R.id.txtEmailAccounts);
            txtDiskQuota = view.findViewById(R.id.txtDiskQuota);
            txtDomainsHosted = view.findViewById(R.id.txtDomainsHosted);
            txtWebsiteHosted = view.findViewById(R.id.txtWebsiteHosted);
            txtFreeDomainsRegistered = view.findViewById(R.id.txtFreeDomainsRegistered);

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
                    List<AccountQuotaModel> filteredList = new ArrayList<>();
                    for (AccountQuotaModel club : clubList)
                    {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name
                        if (club.packageName.toLowerCase().contains(charString.toLowerCase()) )
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
                filteredClubList = (ArrayList<AccountQuotaModel>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
}

