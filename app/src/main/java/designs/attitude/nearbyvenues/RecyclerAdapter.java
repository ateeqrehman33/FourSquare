package designs.attitude.nearbyvenues;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import designs.attitude.nearbyvenues.Model.Venue;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    List<Venue> venueitem_list = new ArrayList<Venue>();




    private Context context;


    public RecyclerAdapter(List<Venue> objects ) {

        this.venueitem_list = objects;

    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        context = parent.getContext();

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, int position) {

        holder.vname.setText(venueitem_list.get(position).getName().toString());
        holder.vloc.setText(venueitem_list.get(position).getLocation().getFormattedAddress().toString());

        Integer distinm = Integer.parseInt(venueitem_list.get(position).getLocation().getDistance().toString());
        Integer distinkm = distinm/1000;


        holder.vdist.setText(distinkm.toString()+"km");

    }

    @Override
    public int getItemCount() {
        return venueitem_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.activity_cardv_recv, parent, false));
        }


        private TextView vname, vloc,vdist;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            vname = itemView.findViewById(R.id.vname);
            vloc = itemView.findViewById(R.id.vloc);
            vdist = itemView.findViewById(R.id.vdist);



        }
    }

}

