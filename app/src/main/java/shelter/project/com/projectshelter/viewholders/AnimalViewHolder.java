package shelter.project.com.projectshelter.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shelter.project.com.projectshelter.listeners.OnAnimalFavouriteResponse;
import shelter.project.com.projectshelter.listeners.OnAnimalListener;
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.widgets.TextViewFont;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Custom RecyclerView ViewHolder for wallpaper in list that is ready for auto change wallpapers
 */
public class AnimalViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSpeciesAge)
    TextView tvSpeciesAge;
    @BindView(R.id.tvIconLike)
    TextViewFont tvIconLike;


    private final String TAG = "WallpaperExplore";
    private final OnAnimalListener onAnimalListener;
    private AnimalPOJO animalPojo;
    private Context mContext;

    /*
    * Initialize
    * */

    public AnimalViewHolder(View itemView, final OnAnimalListener onAnimalListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.onAnimalListener = onAnimalListener;
    }

    /*
    * Setters
    * */

    /**
     * Sets wallpaper
     *
     * @param context    needed for Glide
     * @param animalPOJO wallpaper Object
     */
    public void setWallpaperPOJO(Context context, final AnimalPOJO animalPOJO) {
        this.mContext = context;
        this.animalPojo = animalPOJO;
        tvName.setText(animalPOJO.getName());
        tvSpeciesAge.setText(String.format("%s, %s", animalPOJO.getGender(), animalPOJO.getAge()));
    }
    /*
    *Getters
    * */

    public AnimalPOJO getWallpaperPOJO() {
        return animalPojo;
    }

    @OnClick({R.id.cardAnimal, R.id.tvIconLike})
    void onClickAnimal(View view) {
        switch (view.getId()) {
            case R.id.cardAnimal:
                onAnimalListener.onAnimalClick(animalPojo);
                break;
            case R.id.tvIconLike:
                if (tvIconLike.getText().toString().equals(mContext.getString(R.string.fa_heart_o))) {
                    onAnimalListener.onAnimalAddFavourite(animalPojo, new OnAnimalFavouriteResponse() {
                        @Override
                        public void onSuccess() {
                            showEmptyHeart();
                        }

                        @Override
                        public void onFailed() {
                            showEmptyHeart();
                        }
                    });
                } else {
                    onAnimalListener.onAnimalRemove(animalPojo, new OnAnimalFavouriteResponse() {
                        @Override
                        public void onSuccess() {
                            showEmptyHeart();
                        }

                        @Override
                        public void onFailed() {
                            showFullHeart();
                        }
                    });
                }
                break;

        }
    }

    /*Private Methods*/

    private void showEmptyHeart() {
        final String fontAwesomeEmptyHeart = mContext.getString(R.string.fa_heart_o);
        tvIconLike.setText(fontAwesomeEmptyHeart);
    }

    private void showFullHeart() {
        final String fontAwesomeFullHeart = mContext.getString(R.string.fa_heart);
        tvIconLike.setText(fontAwesomeFullHeart);
    }
}