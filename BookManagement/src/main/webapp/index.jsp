<html>
<head>
    <style>
        /* General reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Sidebar styles */
        .sidebar {
            height: 100vh;
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #2c3e50;
            padding-top: 50px;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: center;
        }

        .sidebar a {
            color: white;
            text-decoration: none;
            padding: 15px 20px;
            margin: 10px 0;
            width: 100%;
            text-align: center;
            font-size: 18px;
            font-weight: 500;
            transition: background-color 0.3s ease;
            border-radius: 5px;
        }

        .sidebar a:hover {
            background-color: #3498db;
        }

        /* Body styles */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #ecf0f1;
            color: #34495e;
            line-height: 1.6;
            margin-left: 250px; /* Adjust content position to account for sidebar */
        }

        /* Main content container */
        .content {
            padding: 40px;
            text-align: center; /* Centers the text horizontally */
        }

        h3 {
            font-size: 36px;
            margin-bottom: 20px;
            font-weight: 700;
            color: #2c3e50;
        }

        p {
            font-size: 18px;
            color: #7f8c8d;
            max-width: 800px;
            margin: 0 auto;
            line-height: 1.8;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .sidebar {
                width: 100%;
                height: auto;
                position: relative;
            }

            .sidebar a {
                display: inline-block;
                width: auto;
                padding: 10px;
                font-size: 16px;
            }

            body {
                margin-left: 0;
            }

            .content {
                padding: 20px;
            }

            h3 {
                font-size: 28px;
            }

            p {
                font-size: 16px;
            }
        }

    </style>
</head>
<body>

    <!-- Sidebar -->
    <div class="sidebar">
        <a href="${pageContext.request.contextPath}/authors/list">Authors</a>
        <a href="${pageContext.request.contextPath}/books/list">Books</a>
        <a href="${pageContext.request.contextPath}/categories/list">Categories</a>
    </div>

    <!-- Main Content -->
    <div class="content">
        <h3>Book Management Application</h3>
        <p>
            Welcome to the Book Management Application. This system provides an easy way to manage authors, books, and categories.
            Use the navigation links on the left to explore the different sections where you can view, add, edit, or delete records.
        </p>
    </div>

</body>
</html>
