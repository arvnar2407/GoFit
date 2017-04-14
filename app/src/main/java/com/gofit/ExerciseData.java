package com.gofit;

import java.util.HashMap;

/**
 * Created by anarasim on 4/10/2017.
 */

public class ExerciseData {
    String name;
    String description;
    String youtubelink;
    HashMap map;
    public ExerciseData(HashMap data)
    {
//        this.name = name;
//        this.description =description;
//        this.youtubelink = link;
        this.map = data;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getDescription()
    {
        return description;

    }
    public String getLink()
    {
        return youtubelink;
    }
}
