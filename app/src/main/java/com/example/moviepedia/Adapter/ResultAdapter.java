package com.example.moviepedia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.pool.GlideTrace;
import com.example.moviepedia.Activities.MovieDetailsActivity;
import com.example.moviepedia.Model.Result;
import com.example.moviepedia.R;

import java.util.ArrayList;

public class ResultAdapter extends PagedListAdapter<Result,ResultAdapter.ResultViewHolder> {
    private Context context;
    //private ArrayList<Result> adapterResults;

    public ResultAdapter(Context context/*ArrayList<Result> adapterResults*/) {
        super(Result.CALL_BACK);
        this.context = context;
        //this.adapterResults = adapterResults;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list_item,
                parent,false);

        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        holder.movieName.setText(getItem(position).getOriginalTitle());
        holder.movieReleaseDate.setText(getItem(position).getReleaseDate());

        String imagePath = "https://image.tmdb.org/t/p/w500/" + getItem(position).getPosterPath();

        Glide.with(context).load(imagePath).into(holder.moviePoster);


    }

   // @Override
    //public int getItemCount() {
       // return adapterResults.size();
   // }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        public TextView movieName;
        public TextView movieReleaseDate;
        public ImageView moviePoster;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            movieName = itemView.findViewById(R.id.movieName);
            movieReleaseDate = itemView.findViewById(R.id.movieReleaseDate);
            moviePoster = itemView.findViewById(R.id.moviePoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Result result = getItem(position);
                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra(context.getString(R.string.intent_key), result);
                        context.startActivity(intent);
                    }

                }
            });
        }
    }
}
