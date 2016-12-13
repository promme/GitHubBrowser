package se.berg.githubbrowser.common.helper;

import android.content.res.Resources;
import android.databinding.ObservableField;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class BindableFieldTarget extends SimpleTarget {

    private ObservableField<Drawable> observableField;
    private Resources resources;

    public BindableFieldTarget(ObservableField<Drawable> observableField, Resources resources) {
        this.observableField = observableField;
        this.resources = resources;
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        observableField.set(placeholder);

    }

    @Override
    public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
        observableField.set(new BitmapDrawable(resources, ((GlideBitmapDrawable) resource).getBitmap()));
    }
}