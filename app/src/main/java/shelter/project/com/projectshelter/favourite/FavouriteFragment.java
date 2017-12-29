package shelter.project.com.projectshelter.favourite;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.data.AnimalPOJO;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment implements FavouriteContract.View{


    private FavouriteContract.Presenter mPresenter;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        mPresenter = new FavouritePresenter(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showListAnimals(List<AnimalPOJO> animalPOJOList) {

    }

    @Override
    public void showMessageError(String error) {

    }

    @Override
    public void setPresenter(FavouriteContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }
}
