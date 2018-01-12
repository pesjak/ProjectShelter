package shelter.project.com.projectshelter.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
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
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.adapters.AnimalsAdapter;
import shelter.project.com.projectshelter.adapters.SheltersAdapter;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.ShelterPOJO;
import shelter.project.com.projectshelter.data.UserRepository;
import shelter.project.com.projectshelter.listeners.OnAnimalFavouriteResponse;
import shelter.project.com.projectshelter.listeners.OnAnimalListener;
import shelter.project.com.projectshelter.listeners.OnShelterListener;
import shelter.project.com.projectshelter.mvp_login.ActivityLogin;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeContract.View, OnAnimalListener, OnShelterListener {

    HomeContract.Presenter mPresenter;
    @BindView(R.id.tvShelters)
    TextView tvShelters;
    @BindView(R.id.rvShelters)
    RecyclerView rvShelters;
    @BindView(R.id.tvAnimals)
    TextView tvAnimals;
    @BindView(R.id.rvAnimals)
    RecyclerView rvAnimals;
    @BindView(R.id.tvErrorNoShelters)
    TextView tvErrorNoShelters;
    @BindView(R.id.tvErrorNoAnimals)
    TextView tvErrorNoAnimals;
    @BindView(R.id.progressBarAnimals)
    ContentLoadingProgressBar progressBarAnimals;
    @BindView(R.id.progressBarShelters)
    ContentLoadingProgressBar progressBarShelters;

    Unbinder unbinder;


    private AnimalsAdapter mAnimalsAdapter;
    private SheltersAdapter mSheltersAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mPresenter = new HomePresenter(this, UserRepository.getInstance());
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showMessageError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAnimals(List<AnimalPOJO> animalPOJOList) {
        tvAnimals.setVisibility(View.VISIBLE);
        mAnimalsAdapter = new AnimalsAdapter(getContext(), this, animalPOJOList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvAnimals.setAdapter(mAnimalsAdapter);
        rvAnimals.setLayoutManager(layoutManager);
    }

    @Override
    public void showLogin() {
        Intent intent = new Intent(getActivity(), ActivityLogin.class);
        startActivity(intent);
    }

    @Override
    public void showShelters(List<ShelterPOJO> shelterPOJOS) {
        tvShelters.setVisibility(View.VISIBLE);
        mSheltersAdapter = new SheltersAdapter(this, shelterPOJOS);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvShelters.setAdapter(mSheltersAdapter);
        rvShelters.setLayoutManager(layoutManager);
    }

    @Override
    public void hideProgressBarAnimals() {
        progressBarAnimals.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBarShelters() {
        progressBarShelters.setVisibility(View.GONE);
    }


    @Override
    public void setPresenter(HomeContract.Presenter mPresenter) {
        mPresenter = this.mPresenter;
    }


    @Override
    public void onAnimalClick(AnimalPOJO animalPOJO) {
        mPresenter.openAnimal(animalPOJO);
    }

    @Override
    public void onAnimalRemove(AnimalPOJO animalPOJO, OnAnimalFavouriteResponse onAnimalFavouriteResponse) {
        mPresenter.changeFavouriteAnimal(animalPOJO, onAnimalFavouriteResponse, false);
    }

    @Override
    public void onAnimalAddFavourite(AnimalPOJO animalPOJO, OnAnimalFavouriteResponse onAnimalFavouriteResponse) {
        mPresenter.changeFavouriteAnimal(animalPOJO, onAnimalFavouriteResponse, true);
    }

    @Override
    public void onShelterClicked(ShelterPOJO shelterPOJO) {
        mPresenter.openShelter(shelterPOJO);
    }
}
