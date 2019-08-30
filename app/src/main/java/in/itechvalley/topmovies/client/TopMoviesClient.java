package in.itechvalley.topmovies.client;

import com.google.gson.annotations.SerializedName;

import butterknife.OnClick;
import in.itechvalley.topmovies.model.TopMoviesModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface TopMoviesClient
{
    @GET("bins/18buhu")
    Call<TopMoviesModel> getMoviesListFromApi();
}
