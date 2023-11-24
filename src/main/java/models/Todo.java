package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

    public static Map<String, String> toMap(String data) {
        Map<String, String> mapData = new Gson().fromJson(data, new TypeToken<HashMap<String, String>>() {
        }.getType());
        return mapData;
    }
    public static JsonObject  toJson(TreeMap<Integer,Todo> mapTodos){
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        Set<Map.Entry<Integer, Todo>> set = mapTodos.entrySet();
        Iterator<Map.Entry<Integer, Todo>> items = set.iterator();
        while (items.hasNext()) {
            Map.Entry<Integer, Todo> entry = items.next();
            final Todo todo = entry.getValue();
            jsonArray.add(new Gson().toJsonTree(todo));
        }
         jsonObject.add("todos", jsonArray);
         return jsonObject;
    }
    public static TreeMap<Integer, Todo> search(TreeMap<Integer, Todo> todos, String searchContain) {
        TreeMap<Integer, Todo> mapData = new TreeMap<>();
        Set<Map.Entry<Integer, Todo>> set = todos.entrySet();
        Iterator<Map.Entry<Integer, Todo>> items = set.iterator();
        while (items.hasNext()) {
            Map.Entry<Integer, Todo> entry = items.next();
            final Todo todo = entry.getValue();
            if (todo.task.toLowerCase().contains(searchContain.toLowerCase())) {
                mapData.put(entry.getKey(), todo);
            }
        }
        return mapData;
    }
}
