
package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alistudios.covid19tracker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.Country;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.VH> {

    ArrayList<Country> countryList;

    public CountryAdapter (ArrayList<Country> arrayList){
        this.countryList=arrayList;
    }


    @NonNull
    @Override
    public CountryAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list,parent,false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.VH holder, int position) {


        Country country= countryList.get(position);

        holder.countryName.setText(country.getModelCountry());
        holder.totalCases.setText(country.getModelCases());
        holder.totalRecovered.setText(country.getModelTodayRecovered());
        holder.totalDeaths.setText(country.getModelDeaths());
        Picasso.get().load(countryList.get(position).getFlagImageUrl()).into(holder.flagImage);

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private ImageView flagImage;
        private TextView countryName,totalCases,totalDeaths,totalRecovered;
        public VH(@NonNull View itemView) {
            super(itemView);

            countryName=(TextView) itemView.findViewById(R.id.country_name);
            totalCases=(TextView) itemView.findViewById(R.id.country_total_confirmed);
            totalDeaths=(TextView) itemView.findViewById(R.id.country_total_deaths);
            totalRecovered=(TextView) itemView.findViewById(R.id.country_total_recovered);
            flagImage=(ImageView) itemView.findViewById(R.id.flag_id);


        }
    }
}
