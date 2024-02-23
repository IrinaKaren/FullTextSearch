<%@page import="model.Produit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            text-align: center;
        }
        input[type="text"] {
            padding: 8px;
            width: 60%;
            margin-bottom: 20px;
            font-size: 16px;
        }
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Search</h2>
        <form action="SearchController" method="get">
            <input type="text" name="query" placeholder="Enter your search query">
            <input type="submit" value="Search">
        </form>
    </div>
</body>
</html>
