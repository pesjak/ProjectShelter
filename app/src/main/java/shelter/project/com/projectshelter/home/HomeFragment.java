package shelter.project.com.projectshelter.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import shelter.project.com.projectshelter.OnAnimalClicked;
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.adapters.AnimalsAdapter;
import shelter.project.com.projectshelter.data.AnimalPOJO;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeContract.View, OnAnimalClicked {

    HomeContract.Presenter mPresenter;
    @BindView(R.id.tvShelters)
    TextView tvShelters;
    @BindView(R.id.rvShelters)
    RecyclerView rvShelters;
    @BindView(R.id.tvAnimals)
    TextView tvAnimals;
    @BindView(R.id.rvAnimals)
    RecyclerView rvAnimals;
    Unbinder unbinder;
    private AnimalsAdapter mAnimalsAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mPresenter = new HomePresenter(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showListAnimals(List<AnimalPOJO> animalPOJOList) {
        mAnimalsAdapter = new AnimalsAdapter(getContext(), this, animalPOJOList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvAnimals.setAdapter(mAnimalsAdapter);
        rvAnimals.setLayoutManager(layoutManager);
    }

    @Override
    public void showMessageError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void itemClicked(AnimalPOJO wallpaperPOJO) {

    }

    @Override
    public void setPresenter(HomeContract.Presenter mPresenter) {
        mPresenter = this.mPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
