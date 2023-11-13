
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "models.Todo"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TodoList</title>
</head>
<body>
    <form action="/tp3/todo" method="post">
        <input type="text" name="task"> 
        <input type="submit" value="add todo">
    </form>
    <ul>
        <% 
        Set set =((Map<Integer,Todo>)request.getAttribute("todos")).entrySet();
        Iterator item = set.iterator();
        while (item.hasNext()){%>
            <% Map.Entry entry=(Map.Entry)item.next();
             Todo todo = (Todo)entry.getValue();
            %>
            <li><%= todo.task %> <span><button type="button" onclick="deleteTodo('<%=entry.getKey() %>')">x</button></span></li>
            
        <% } %>
    </ul>
  
</body>
<script>
    function deleteTodo(id){
        fetch(`/tp3/todo`,{
            method:"DELETE",
            body:JSON.stringify({"id":id})
        }).then(res=>{
            window.location.reload();
        });
    }
</script>
</html>