package shelter.project.com.projectshelter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.ShelterPOJO;
import shelter.project.com.projectshelter.listeners.OnAnimalListener;
import shelter.project.com.projectshelter.listeners.OnShelterListener;
import shelter.project.com.projectshelter.viewholders.AnimalViewHolder;
import shelter.project.com.projectshelter.viewholders.ShelterViewHolder;


public class SheltersAdapter extends RecyclerView.Adapter<ShelterViewHolder> {

    final String TAG = "ShelterAdapter";

    private OnShelterListener onShelterListener;
    private List<ShelterPOJO> shelterPOJOS;

    public SheltersAdapter(OnShelterListener onShelterListener,
                           List<ShelterPOJO> shelterPOJOS) {
        this.onShelterListener = onShelterListener;
        this.shelterPOJOS = shelterPOJOS;
    }

    @Override
    public ShelterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shelter, parent, false);
        return new ShelterViewHolder(v, onShelterListener);
    }

    @Override
    public void onBindViewHolder(final ShelterViewHolder holder, final int position) {
        final ShelterPOJO shelterPOJO = shelterPOJOS.get(position);
        holder.setWallpaperPOJO(shelterPOJO);  //Sets data
    }

    @Override
    public int getItemCount() {
        return shelterPOJOS.size();
    }


    public void swapData(ArrayList<ShelterPOJO> shelterPOJOS, boolean reset) {
        if (reset) {
            this.shelterPOJOS.clear();
        }
        this.shelterPOJOS.addAll(shelterPOJOS);
        if (this.shelterPOJOS.size() != shelterPOJOS.size()) {
            notifyItemRangeInserted(getItemCount(), shelterPOJOS.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public List<ShelterPOJO> getList() {
        return shelterPOJOS;
    }
}
