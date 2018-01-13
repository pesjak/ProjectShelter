package shelter.project.com.projectshelter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import shelter.project.com.projectshelter.listeners.OnAnimalListener;
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.viewholders.AnimalViewHolder;


public class AnimalsAdapter extends RecyclerView.Adapter<AnimalViewHolder> {

    final String TAG = "ExploreAdapter";

    private Context context;
    private OnAnimalListener onAnimalListener;
    private List<AnimalPOJO> animalPOJOS;

    public AnimalsAdapter(Context context,
                          OnAnimalListener onAnimalListener,
                          List<AnimalPOJO> animalPOJOS) {
        this.context = context;
        this.onAnimalListener = onAnimalListener;
        this.animalPOJOS = animalPOJOS;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);
        return new AnimalViewHolder(v, onAnimalListener);
    }

    @Override
    public void onBindViewHolder(final AnimalViewHolder holder, final int position) {
        final AnimalPOJO wallpaperPOJO = animalPOJOS.get(position);
        holder.setWallpaperPOJO(context, wallpaperPOJO);  //Sets data
    }

    @Override
    public int getItemCount() {
        return animalPOJOS.size();
    }

    /**
     * Deletes current data if reset=TRUE and adds wallpapers to this list
     */
    public void swapData(List<AnimalPOJO> newAnimalPojos, boolean reset) {
        if (reset) {
            animalPOJOS.clear();
        }
        animalPOJOS.addAll(newAnimalPojos);
        if (animalPOJOS.size() != newAnimalPojos.size()) {
            notifyItemRangeInserted(getItemCount(), newAnimalPojos.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public void reset() {
        animalPOJOS.clear();
        notifyDataSetChanged();

    }

    public List<AnimalPOJO> getList() {
        return animalPOJOS;
    }
}
