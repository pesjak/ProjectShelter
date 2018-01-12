package shelter.project.com.projectshelter.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.ShelterPOJO;
import shelter.project.com.projectshelter.listeners.OnAnimalFavouriteResponse;
import shelter.project.com.projectshelter.listeners.OnAnimalListener;
import shelter.project.com.projectshelter.listeners.OnShelterListener;
import shelter.project.com.projectshelter.widgets.TextViewFont;


/**
 * Custom RecyclerView ViewHolder for wallpaper in list that is ready for auto change wallpapers
 */
public class ShelterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvName)
    TextView tvName;

    private final String TAG = "ShelterViewHolder";
    private final OnShelterListener onShelterListener;
    private ShelterPOJO shelterPOJO;

    /*
    * Initialize
    * */

    public ShelterViewHolder(View itemView, final OnShelterListener onShelterListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.onShelterListener = onShelterListener;
    }

    /*
    * Setters
    * */

    /**
     * Sets wallpaper
     *
     * @param animalPOJO wallpaper Object
     */
    public void setWallpaperPOJO( final ShelterPOJO animalPOJO) {
        this.shelterPOJO = animalPOJO;
        tvName.setText(animalPOJO.getName());
    }
    /*
    *Getters
    * */

    public ShelterPOJO getWallpaperPOJO() {
        return shelterPOJO;
    }

    @OnClick(R.id.cardAnimal)
    void onClickAnimal() {
        onShelterListener.onShelterClicked(shelterPOJO);
    }
}