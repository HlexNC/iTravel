package com.itravel.app.ui.feed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itravel.app.R;
import com.itravel.app.model.FeedItem;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<FeedItem> feedItems;

    public FeedAdapter(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    public void updateItems(List<FeedItem> newItems) {
        this.feedItems = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_post, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedItem item = feedItems.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        holder.tvAuthor.setText("by " + item.getAuthorName());

        Glide.with(holder.itemView.getContext())
                .load(item.getImageUrl())
                .placeholder(R.drawable.ic_empty_state)
                .centerCrop()
                .into(holder.ivFeedImage);

        if (item.getWeatherTemp() != null) {
            holder.layoutWeather.setVisibility(View.VISIBLE);
            holder.tvWeatherTemp.setText(item.getWeatherTemp());
            holder.tvWeatherTemp.setVisibility(View.VISIBLE);

            if (item.getWeatherIcon() != null) {
                Glide.with(holder.itemView.getContext())
                        .load("https://openweathermap.org/img/wn/" + item.getWeatherIcon() + ".png")
                        .into(holder.ivWeatherIcon);
                holder.ivWeatherIcon.setVisibility(View.VISIBLE);
            }
        } else {
            holder.layoutWeather.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFeedImage;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvAuthor;
        View layoutWeather;
        ImageView ivWeatherIcon;
        TextView tvWeatherTemp;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFeedImage = itemView.findViewById(R.id.ivFeedImage);
            tvTitle = itemView.findViewById(R.id.tvFeedTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAuthor = itemView.findViewById(R.id.tvAuthorName);
            layoutWeather = itemView.findViewById(R.id.layoutWeather);
            ivWeatherIcon = itemView.findViewById(R.id.ivWeatherIcon);
            tvWeatherTemp = itemView.findViewById(R.id.tvWeatherTemp);
        }
    }
}
