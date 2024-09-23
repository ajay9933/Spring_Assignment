<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Book List</title>
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
        .categories {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .actions a {
            margin-right: 10px;
        }
          .home-link {
                             margin-top: 40px;
                             display: block;
                             text-align: center;
                             color: green;
                             text-decoration: none;
                             font-size: 18px;
                         }

                         .home-link:hover {
                             text-decoration: underline;
                         }


           .other-link {
                                margin-top: 20px;
                                display: block;
                                text-align: center;
                                color: green;
                                text-decoration: none;
                                font-size: 18px;
                            }
    </style>
</head>
<body>
    <h2>Book List</h2>
    <a href="add">Add New Book</a>
    <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>ISBN</th>
                <th>Author</th>
                <th>Categories</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.isbn}</td>
                    <td>${book.author.name}</td>
                    <td class="categories">
                        <c:forEach var="category" items="${book.categories}">
                            ${category.name}<br/>
                        </c:forEach>
                    </td>
                    <td class="actions">
                        <a href="update?id=${book.id}">Edit</a>
                      <form action="delete?id=${book.id}" method="post" style="display:inline;">
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

   <div class="other-link">
       <a href="${pageContext.request.contextPath}/authors/list" class="other-link">
           Click here to go to Author List
       </a>
   </div>

         <div class="other-link">
                 <a href="${pageContext.request.contextPath}/categories/list" class="other-link">
                     Click here to go to Category List
                 </a>
             </div>
</body>
</html>

