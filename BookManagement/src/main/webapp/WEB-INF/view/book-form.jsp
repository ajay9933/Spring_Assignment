<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        .form-container {
            width: 50%;
            margin: 0 auto;
            font-family: Arial, sans-serif;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .form-container div {
            margin-bottom: 15px;
        }

        .form-container label {
            display: block;
            margin-bottom: 5px;
        }

        .form-container input[type="text"],
        .form-container select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-container .checkbox-group {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-container .checkbox-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-container .submit-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
        }

        .error {
            color: red;
            font-size: 12px;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center; font-family: Arial, sans-serif;">Book Form</h2>

    <form:form action="save" method="post" class="form-container" modelAttribute="book">

        <input type="hidden" name="id" value="${book.id}" />

        <div>
            <label for="title">Title:</label>
            <form:input path="title" id="title" cssClass="form-control" />
               <form:errors path="title" cssClass="error" />
        </div>

        <div>
            <label for="isbn">ISBN:</label>
            <form:input path="isbn" id="isbn" cssClass="form-control" />
               <form:errors path="isbn" cssClass="error" />
        </div>

        <div>
            <label for="author">Author:</label>
            <select id="author" name="author.id">
                <c:forEach var="author" items="${authors}">
                    <option value="${author.id}" <c:if test="${author.id == book.author.id}">selected</c:if>>${author.name}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="categories">Categories:</label>
            <div class="checkbox-group">
                <c:forEach var="category" items="${categories}">
                    <label>
                        <input type="checkbox" name="categoryIds" value="${category.id}"
                            <c:forEach var="bookCategory" items="${book.categories}">
                                <c:if test="${bookCategory.id == category.id}">
                                    checked
                                </c:if>
                            </c:forEach>
                        /> ${category.name}
                    </label>
                </c:forEach>
            </div>
        </div>

        <div style="text-align: center;">
            <button type="submit" class="submit-button">Save</button>
        </div>
    </form:form>
</body>
</html>
