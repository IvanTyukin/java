<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Информация о пользователе</title>
    </head>
    <body>
        <p>Имя: <%= request.getParameter("username") %></p>
        <p>Страна: <%= request.getParameter("country") %></p>
        <p>Пол: <%= request.getParameter("gender") %></p>
        <h4>Выбранные курсы</h4>
        <ul>
        <%
            String[] courses = request.getParameterValues("courses");
            for(String course: courses){
                out.println("<li>" + course + "</li>");
            }
        %>
        </ul>
    </body>
</html>
