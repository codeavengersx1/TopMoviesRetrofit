package in.itechvalley.topmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import in.itechvalley.topmovies.client.TopMoviesClient;
import in.itechvalley.topmovies.model.SingleMovieModel;
import in.itechvalley.topmovies.model.TopMoviesModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Retrofit's Code which will call the Client and
        * return us the Model
        * */

        /*
        * We are using Gson for parsing
        * other libs are Moshi, Jackson 1.x, Jackson 2.0, Gson etc.
        * */

        /*
        * 0. Create Object of GsonConverterFactory
        * */
        GsonConverterFactory converterFactory = GsonConverterFactory.create();

        /*
        * 1. Create Retrofit's Builder
        * */
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl("https://api.myjson.com/");
        retrofitBuilder.addConverterFactory(converterFactory);

        /*
        * 2. Get the Retrofit's Object from it's builder
        * */
        Retrofit retrofit = retrofitBuilder.build();

        /*
        * 3. Create the Object of Client
        * */
        TopMoviesClient topMoviesClient = retrofit.create(TopMoviesClient.class);

        /*
        * 4. Call the method inside your client
        * */
        topMoviesClient.getMoviesListFromApi()
                .enqueue(new Callback<TopMoviesModel>()
                {
                    @Override
                    public void onResponse(@NonNull Call<TopMoviesModel> call, @NonNull Response<TopMoviesModel> response)
                    {
                        TopMoviesModel topMoviesModel = response.body();

                        if (topMoviesModel == null)
                        {
                            Toast.makeText(MainActivity.this, "Server Error Occurred. Plej Try again.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (topMoviesModel != null)
                        {
                            List<SingleMovieModel> moviesList = topMoviesModel.getMoviesList();
                            /*
                            * Pass this List to RecyclerView's Adapter
                            * */
                            Toast.makeText(MainActivity.this, "Total Movies = " + moviesList.size(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<TopMoviesModel> call, @NonNull Throwable t)
                    {
                        /*
                        * Print on Logcat
                        * */
                        Log.e(TAG, "Some error Occurred", t.getCause());

                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
