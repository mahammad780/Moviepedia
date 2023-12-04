package com.example.moviepedia.Model;

import java.util.List;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class MovieApiResponce implements Parcelable
{
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Result> results;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    public final static Creator<MovieApiResponce> CREATOR = new Creator<MovieApiResponce>() {


        public MovieApiResponce createFromParcel(android.os.Parcel in) {
            return new MovieApiResponce(in);
        }

        public MovieApiResponce[] newArray(int size) {
            return (new MovieApiResponce[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected MovieApiResponce(android.os.Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.results, (com.example.moviepedia.Model.Result.class.getClassLoader()));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MovieApiResponce() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(results);
        dest.writeValue(totalPages);
        dest.writeValue(totalResults);
    }

    public int describeContents() {
        return 0;
    }

}
