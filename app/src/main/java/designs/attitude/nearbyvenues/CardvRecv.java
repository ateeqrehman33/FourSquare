package designs.attitude.nearbyvenues;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import designs.attitude.nearbyvenues.Model.FSModel;
import designs.attitude.nearbyvenues.Model.Venue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardvRecv extends AppCompatActivity {


   String  Client_ID = "EWH3AW5SVKVWGC40LKMBQMKRJIVBX3DVU5133L5Q1NWTUXW5";
   String Client_Secret = "JORONABDPTSOWB2JYUYJY4FKP1R23CKLDA0IVF3FEPAWTMVJ";
   String apiVersion = "20161010";



      List<Venue> venueitem_list = new ArrayList<Venue>();


      public RecyclerAdapter recyclerAdapter;
      private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardv_recv);


        String ll = getIntent().getStringExtra("ll");

        Log.e("ll",ll+"");

        String query = getIntent().getStringExtra("query");

        Log.e("query",query+"");
        if(query.length()<1){
            this.setTitle("All Venues Near by");

        }
        else {
            this.setTitle(query+" near by");
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        FourSquareAPI fourSquareService = FourSquareAPI.retrofit.create(FourSquareAPI.class);
        final Call<FSModel> call = fourSquareService.requestSearch(Client_ID, Client_Secret,apiVersion,ll, query);
        call.enqueue(new Callback<FSModel>() {
            @Override
            public void onResponse(Call<FSModel> call, Response<FSModel> response) {
                Log.d("DataCheck",new Gson().toJson(response.body().getResponse().getVenues()));

                venueitem_list = response.body().getResponse().getVenues();




                recyclerAdapter = new RecyclerAdapter(venueitem_list);
                recyclerView.setAdapter(recyclerAdapter);

            }

            @Override
            public void onFailure(Call<FSModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("Json Data Error",t.getMessage());
            }
        });




    }
}
