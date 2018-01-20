package com.example.zoskenbaeva.weathersearching;

/**
 * Created by z.oskenbaeva on 19.01.2018.
 */

public class GMAPProperties {
    //"input=Vict&types=geocode&language=fr&key=AIzaSyBNiFTBo1qMncbxXrrNDuLY7hea1PqDUS4";


    public final static  String GMAP_AUTOCOM_API_BASE
            ="https://maps.googleapis.com/maps/api/place/autocomplete/json?";
    public final static  String GMAP_AUTOCOM_PARAMS =
            "language=en&types=(regions)&components=country:kz&input=";

    public final static  String GMAP_PLACE_API_BASE
            ="https://maps.googleapis.com/maps/api/place/details/json?";
    public final static  String GMAP_PLACE_PARAMS
            ="placeid=";

    public final static  String GMAP_KEY
            = "key=AIzaSyBNiFTBo1qMncbxXrrNDuLY7hea1PqDUS4";

}
