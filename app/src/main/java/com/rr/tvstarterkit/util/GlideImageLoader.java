package com.rr.tvstarterkit.util;

import static com.bumptech.glide.request.RequestOptions.priorityOf;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideImageLoader {

    public void loadDrawableWithGlide(int drawable, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(drawable)
                .apply(priorityOf(Priority.IMMEDIATE)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(imageView);
    }


    public void loadImageFromUrl(String url, ImageView imageView) {
        loadImageFromUrl(url, imageView, true);
    }


    private void loadImageFromUrl(String url, ImageView imageView, Boolean canSkipMemoryCache) {

        Glide.with(imageView.getContext().getApplicationContext())
                .load(url)
                .apply(priorityOf(Priority.IMMEDIATE)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .skipMemoryCache(canSkipMemoryCache))
                .into(imageView);
    }

    public void loadLowQualityImageFromUrl(String url, ImageView imageView) {
        if (imageView.getContext() != null && imageView.getContext().getApplicationContext() != null)
            Glide.with(imageView.getContext().getApplicationContext())
                    .load(url)
                    .apply(priorityOf(Priority.IMMEDIATE)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)).format(DecodeFormat.PREFER_RGB_565)
                    .into(imageView);
    }
}
