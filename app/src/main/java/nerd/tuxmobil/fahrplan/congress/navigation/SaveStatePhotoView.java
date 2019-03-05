package nerd.tuxmobil.fahrplan.congress.navigation;

import android.content.Context;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import uk.co.senab.photoview.PhotoView;

/**
 * PhotoView which saves and restores the current scale and approximate position.
 */
public class SaveStatePhotoView extends PhotoView {

    public SaveStatePhotoView(Context context) {
        super(context);
    }

    public SaveStatePhotoView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public SaveStatePhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.scale = getScale();
        final RectF rect = getDisplayRect();
        final float overflowWidth = rect.width() - getWidth();
        if (overflowWidth > 0f) {
            ss.pivotX = -rect.left / overflowWidth;
        }
        final float overflowHeight = rect.height() - getHeight();
        if (overflowHeight > 0f) {
            ss.pivotY = -rect.top / overflowHeight;
        }
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        final SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private int invocationCount = 0;

            // Only one layout pass for M and up. Otherwise, we'll see two and the
            // scale set in the first pass is reset during the second pass, so the scale we
            // set doesn't stick until the 2nd pass.
            // https://stackoverflow.com/a/55164078/356895
            @Override
            public void onGlobalLayout() {
                restoreSaveState(ss);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M || ++invocationCount > 1) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    void restoreSaveState(SavedState ss) {
        float scale = Math.max(ss.scale, getMinimumScale());
        scale = Math.min(scale, getMaximumScale());
        setScale(scale, getWidth() * ss.pivotX, getHeight() * ss.pivotY, false);
    }

    public static class SavedState extends View.BaseSavedState {
        float scale = 1f;
        float pivotX = 0.5f;
        float pivotY = 0.5f;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeFloat(scale);
            out.writeFloat(pivotX);
            out.writeFloat(pivotY);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {

            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        SavedState(Parcel in) {
            super(in);
            scale = in.readFloat();
            pivotX = in.readFloat();
            pivotY = in.readFloat();
        }

        @Override
        public String toString() {
            return "SavedState{" +
                    "scale=" + scale +
                    ", pivotX=" + pivotX +
                    ", pivotY=" + pivotY +
                    '}';
        }
    }
}

