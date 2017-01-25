package com.temparatureanalysis.constants;

/**
 * Created by Techno Blogger on 25/1/17.
 */

public class AppConstants {


    public interface JSON_PARAMS {

        String URL = "http://api.openweathermap.org/data/2.5/group?" +
                "id=1277333,1273294,1259229,1260086,1263364,1269447,1264733," +
                "1258972,1268865,1267995,6619347,7279746,6992326,1462711" +
                "%22&units=metric&appid=ad55f494ee70b109b8dcfbae44795b28";

        String PARAM_LIST = "list";
        String PARAM_NAME = "name";
        String PARAM_MAIN = "main";
        String PARAM_TEMP = "temp";
    }
}
