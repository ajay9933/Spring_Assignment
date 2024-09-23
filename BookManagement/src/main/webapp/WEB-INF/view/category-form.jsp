<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .form-container {
            width: 50%;
            margin: 0 auto;
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

        .form-container input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-container .error {
            color: red;
            font-size: 14px;
        }

        .form-container .submit-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        h2 {
            text-align: center;
        }
    </style>
</head>
<body>

    <h2>Category Form</h2>

    <form:form action="save" method="post" modelAttribute="category" class="form-container">
       <input type="hidden" name="id" value="${category.id}" />


        <div>
            <label for="name">Name:</label>
            <form:input path="name" id="name" />
            <div class="error">
                <form:errors path="name" />
            </div>
        </div>

        <div>
            <button type="submit" class="submit-button">Save</button>
        </div>

    </form:form>

</body>
</html>
