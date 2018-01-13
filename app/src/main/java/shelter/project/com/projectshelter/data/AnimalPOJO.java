package shelter.project.com.projectshelter.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Primož on 27/12/2017.
 */

public class AnimalPOJO {

    @SerializedName("Id_Animals") //Cat, Dog
    @Expose
    private String id;
    @SerializedName("Id_Shelters") //Cat, Dog
    @Expose
    private String id_shelter;
    @SerializedName("Type") //Cat, Dog
    @Expose
    private String type;
    @SerializedName("Species")
    @Expose
    private String species;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Age")
    @Expose
    private String age;
    @SerializedName("Behaviour")
    @Expose
    private String behaviour;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Picture")
    @Expose
    private String picture;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId_shelter() {
        return id_shelter;
    }

    public void setId_shelter(String id_shelter) {
        this.id_shelter = id_shelter;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
