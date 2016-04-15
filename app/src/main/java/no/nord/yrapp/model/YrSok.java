package no.nord.yrapp.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YrSok {

    @SerializedName("_id")
    @Expose
    private String Id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("kommune")
    @Expose
    private String kommune;
    @SerializedName("sted")
    @Expose
    private String sted;
    @SerializedName("loc")
    @Expose
    private List<Double> loc = new ArrayList<Double>();

    /**
     *
     * @return
     * The Id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The kommune
     */
    public String getKommune() {
        return kommune;
    }

    /**
     *
     * @param kommune
     * The kommune
     */
    public void setKommune(String kommune) {
        this.kommune = kommune;
    }

    /**
     *
     * @return
     * The sted
     */
    public String getSted() {
        return sted;
    }

    /**
     *
     * @param sted
     * The sted
     */
    public void setSted(String sted) {
        this.sted = sted;
    }

    /**
     *
     * @return
     * The loc
     */
    public List<Double> getLoc() {
        return loc;
    }

    /**
     *
     * @param loc
     * The loc
     */
    public void setLoc(List<Double> loc) {
        this.loc = loc;
    }

}
