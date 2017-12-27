package shelter.project.com.projectshelter.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Primož on 27/12/2017.
 */

public class AnimalPOJO {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Age")
    @Expose
    private String age;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Description")
    @Expose
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
