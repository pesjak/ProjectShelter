package shelter.project.com.projectshelter.listeners;

import shelter.project.com.projectshelter.data.AnimalPOJO;

/**
 * Created by Primož on 27/12/2017.
 */

public interface OnAnimalListener {

    void onAnimalClick(AnimalPOJO wallpaperPOJO);

    void onAnimalRemove(AnimalPOJO wallpaperPOJO);

    void onAnimalAddFavourite(AnimalPOJO wallpaperPOJO);
}
