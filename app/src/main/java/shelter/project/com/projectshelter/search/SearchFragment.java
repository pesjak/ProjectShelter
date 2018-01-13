package shelter.project.com.projectshelter.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.adapters.AnimalsAdapter;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.UserRepository;
import shelter.project.com.projectshelter.listeners.OnAnimalFavouriteResponse;
import shelter.project.com.projectshelter.listeners.OnAnimalListener;
import shelter.project.com.projectshelter.mvp_login.ActivityLogin;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchContract.View, OnAnimalListener {


    @BindView(R.id.toggleTypeAll)
    ToggleButton toggleTypeAll;
    @BindView(R.id.toggleTypeDog)
    ToggleButton toggleTypeDog;
    @BindView(R.id.toggleTypeCat)
    ToggleButton toggleTypeCat;
    @BindView(R.id.toggleTypeBird)
    ToggleButton toggleTypeBird;
    Unbinder unbinder;
    @BindView(R.id.tvFoundAnimals)
    TextView tvFoundAnimals;
    @BindView(R.id.progressBarAnimals)
    ContentLoadingProgressBar progressBarAnimals;
    @BindView(R.id.rvSearchResults)
    RecyclerView rvSearchResults;

    private SearchContract.Presenter mPresenter;
    private String TAG = "Search";
    private AnimalsAdapter mAnimalsAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mPresenter = new SearchPresenter(this, UserRepository.getInstance());
        unbinder = ButterKnife.bind(this, view);
        mPresenter.start();
        return view;
    }

    @Override
    public void showMessageError(String error) {
        Log.e(TAG, "showMessageError: " + error);
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserPreference() {

    }

    @Override
    public void showLogin() {
        Intent intent = new Intent(getActivity(), ActivityLogin.class);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        progressBarAnimals.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBarAnimals.setVisibility(View.GONE);
    }

    @Override
    public void showSearchResults(List<AnimalPOJO> animalPOJO) {
        tvFoundAnimals.setVisibility(View.VISIBLE);
        if (mAnimalsAdapter == null) {
            mAnimalsAdapter = new AnimalsAdapter(getContext(), this, animalPOJO);
        } else {
            if (animalPOJO == null) {
                mAnimalsAdapter.reset();
            } else {
                mAnimalsAdapter.swapData(animalPOJO, true);
            }
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvSearchResults.setAdapter(mAnimalsAdapter);
        rvSearchResults.setLayoutManager(layoutManager);
    }

    @Override
    public void setPresenter(SearchContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.toggleTypeAll, R.id.toggleTypeDog, R.id.toggleTypeCat, R.id.toggleTypeBird})
    public void onClick(View view) {
        offAll();
        switch (view.getId()) {
            case R.id.toggleTypeAll:
                toggleTypeAll.setChecked(true);
                break;
            case R.id.toggleTypeDog:
                toggleTypeDog.setChecked(true);
                break;
            case R.id.toggleTypeCat:
                toggleTypeCat.setChecked(true);
                break;
            case R.id.toggleTypeBird:
                toggleTypeBird.setChecked(true);
                break;
        }


        final String typeAnimal = ((ToggleButton) view).getText().toString();
        mPresenter.sendUserSearch(typeAnimal);
    }

    private void offAll() {
        toggleTypeAll.setChecked(false);
        toggleTypeDog.setChecked(false);
        toggleTypeCat.setChecked(false);
        toggleTypeBird.setChecked(false);
    }

    @Override
    public void onAnimalClick(AnimalPOJO animalPOJO) {
        mPresenter.loadAnimalDetails(animalPOJO);
    }

    @Override
    public void onAnimalRemove(AnimalPOJO animalPOJO, OnAnimalFavouriteResponse onAnimalFavouriteResponse) {
        mPresenter.changeFavouriteAnimal(animalPOJO, onAnimalFavouriteResponse, false);
    }

    @Override
    public void onAnimalAddFavourite(AnimalPOJO animalPOJO, OnAnimalFavouriteResponse onAnimalFavouriteResponse) {
        mPresenter.changeFavouriteAnimal(animalPOJO, onAnimalFavouriteResponse, true);
    }
}
