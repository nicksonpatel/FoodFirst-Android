package com.example.nicks.myfoodfirst.adpter;

import java.util.Map;

/**
 * Created by Meet on 16-10-2017.
 */

public class Subject
{
    String key;
    Map<String,Object> data;

    public Subject(String key,Map<String,Object> data)
    {
        this.key=key;
        this.data=data;
    }

}
