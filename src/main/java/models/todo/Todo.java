package models.todo;

import java.sql.Date;

//TODO: create enum status
enum TodoStatus{
    COMPLETE,
    UNDONE,
}
public class Todo {
    // TODO: status, title description,createdAt, updatedAt, start, end
    public Date createdAt;
    public Date updatedAt;
    public String title;
    public TodoStatus status;
    public String description;
}
