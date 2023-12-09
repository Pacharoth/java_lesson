package models;

import java.util.Date;

import com.google.gson.Gson;

public class Todo {
    private int id;
    private String title;
    private String description;
    private TodoStatus status;
    private Date createdAt;
    private Date updatedAt;
    /**
     * @param id
     * @param title
     * @param description
     * @param status
     * @param createdAt
     * @param updatedAt
     */
    public Todo(int id, String title, String description, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        System.err.println( status.compareToIgnoreCase("complete"));
        this.status = status.equals("complete")?TodoStatus.complete:TodoStatus.uncomplete;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * 
     */
    public Todo() {
    }

    /**
     * @param title
     * @param description
     */
    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TodoStatus.uncomplete;
        this.createdAt = new Date();
    }

    public static String toJson(Todo todo){
        return new Gson().toJson(todo);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the status
     */
    public TodoStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
