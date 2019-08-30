package in.itechvalley.topmovies.model;

import com.google.gson.annotations.SerializedName;

public class SingleMovieModel
{
    @SerializedName("Title")
    private String movieTitle;

    @SerializedName("Year")
    private String releasedYear;

    @SerializedName("Actors")
    private String starCast;

    @SerializedName("Country")
    private String country;

    /*
    * Constructor
    * */
    public SingleMovieModel(String movieTitle, String releasedYear, String starCast, String country)
    {
        this.movieTitle = movieTitle;
        this.releasedYear = releasedYear;
        this.starCast = starCast;
        this.country = country;
    }

    /*
    * Getters
    * */
    public String getMovieTitle()
    {
        return movieTitle;
    }

    public String getReleasedYear()
    {
        return releasedYear;
    }

    public String getStarCast()
    {
        return starCast;
    }

    public String getCountry()
    {
        return country;
    }
}
