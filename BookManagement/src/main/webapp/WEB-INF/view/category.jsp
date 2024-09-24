<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Category List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #333;
        }
        a {
            color: #007BFF;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .actions a {
            margin-right: 10px;
        }
        .home-link {
                                     margin-top: 20px;
                                     display: block;
                                     text-align: center;
                                     color: green;
                                     text-decoration: none;
                                     font-size: 18px;
                                 }

                                 .home-link:hover {
                                     text-decoration: underline;
                                 }
    </style>
</head>
<body>
    <h2>Category List</h2>
    <a href="add">Add New Category</a>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.name}</td>
                    <td class="actions">
                        <a href="update?id=${category.id}">Edit</a>
                        <form action="delete?id=${category.id}" method="post" style="display:inline;">
                            <input type="hidden" name="_method" value="DELETE"/>
                            <button type="submit" style="background:none;border:none;color:#007BFF;cursor:pointer;font-size:15px">
                                Delete
                            </button>
                        </form>


                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
      <div class="">
                <a href="${pageContext.request.contextPath}/" class="home-link">
                    Click here to go to Home
                </a>
            </div>

     <div class="home-link">
                 <a href="${pageContext.request.contextPath}/books/list" class="home-link">
                     Click here to go to Books
                 </a>
             </div>
</body>
</html>
