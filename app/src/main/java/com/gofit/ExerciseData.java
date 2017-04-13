package com.gofit;

/**
 * Created by anarasim on 4/10/2017.
 */

public class ExerciseData {
    String name;
    String description;
    String youtubelink;
    public ExerciseData(String name, String description , String link)
    {
        this.name = name;
        this.description =description;
        this.youtubelink = link;
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
