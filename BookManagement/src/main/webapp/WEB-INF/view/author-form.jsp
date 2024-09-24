<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

        .form-container input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-container .error {
            color: red;
            font-size: small;
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
    </style>
</head>
<body>
    <h2 style="text-align: center; font-family: Arial, sans-serif;">Author Form</h2>

    <form:form modelAttribute="author" action="save" method="post" class="form-container">
        <input type="hidden" name="id" value="${author.id}" />

        <div>
            <label for="name">Name:</label>
            <form:input path="name" id="name" />
            <form:errors path="name" cssClass="error" />
        </div>

        <div>
            <label for="email">Email:</label>
            <form:input path="email" id="email" />
            <form:errors path="email" cssClass="error" />
        </div>

        <div style="text-align: center;">
            <button type="submit" class="submit-button">Save</button>
        </div>
    </form:form>
</body>
</html>


