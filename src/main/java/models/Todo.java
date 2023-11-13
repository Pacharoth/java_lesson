package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Todo {
    public String task;
    public Date createdAt;
    public Date updatedAt;
    public Todo(String task, Date createdAt, Date updatedAt) {
        this.task = task;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public static Map<String,String> toMap(String data){
        Map<String,String> mapData = new Gson().fromJson(data, new TypeToken<HashMap<String,String>>(){}.getType());
        return mapData; 
    }
}
