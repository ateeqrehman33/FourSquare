package designs.attitude.nearbyvenues;

import designs.attitude.nearbyvenues.Model.FSModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FourSquareAPI {



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    @GET("venues/search/")
    Call<FSModel> requestSearch(
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("v") String v,
            @Query("ll") String ll,
            @Query("query") String query);



  //  api.foursquare.com/v2/venues/search?ll=40.7,-74&query=starbucks&client_id=EWH3AW5SVKVWGC40LKMBQMKRJIVBX3DVU5133L5Q1NWTUXW5&client_secret=JORONABDPTSOWB2JYUYJY4FKP1R23CKLDA0IVF3FEPAWTMVJ&v=20161010






}
