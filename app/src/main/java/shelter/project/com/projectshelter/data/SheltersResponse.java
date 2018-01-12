package shelter.project.com.projectshelter.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Primož on 27/12/2017.
 */

public class SheltersResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("status_message")
    @Expose
    private String statusMessage;
    @SerializedName("data")
    @Expose
    private List<ShelterPOJO> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }


    public List<ShelterPOJO> getData() {
        return data;
    }

    public void setData(List<ShelterPOJO> data) {
        this.data = data;
    }
}
