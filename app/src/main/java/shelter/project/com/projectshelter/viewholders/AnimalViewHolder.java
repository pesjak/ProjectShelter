package shelter.project.com.projectshelter.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shelter.project.com.projectshelter.listeners.OnAnimalListener;
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.data.AnimalPOJO;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Custom RecyclerView ViewHolder for wallpaper in list that is ready for auto change wallpapers
 */
public class AnimalViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvSpeciesAge)
    TextView tvSpeciesAge;

    private final String TAG = "WallpaperExplore";
    private final OnAnimalListener onAnimalListener;
    private AnimalPOJO animalPojo;

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
}