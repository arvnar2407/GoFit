package com.gofit;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by anarasim on 4/10/2017.
 */

public class ExerciseData {
    String name;
    String description;
    String youtubelink;
    HashMap map;
    ArrayList list =null;
    public ExerciseData(HashMap data)
    {
//        this.name = name;
//        this.description =description;
//        this.youtubelink = link;
        this.map = data;
    }

    public ExerciseData(ArrayList mParam1) {
        this.list = mParam1;
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
    public int findString(String query)
    {
        int pos=0;
        HashMap temp = null;
        int count = 0;
        Iterator iter = list.iterator();
        while (iter.hasNext()) {

            temp = (HashMap) iter.next();
            Log.i("in while", temp.get("name") + "");
            String str = (String) temp.get("name");
            if (str.toLowerCase().contains(query.toLowerCase())) {

                pos = list.indexOf(temp);
                return pos;
            }
        }
        return pos;
    }
    public String getLink()
    {
        return youtubelink;
    }
}
