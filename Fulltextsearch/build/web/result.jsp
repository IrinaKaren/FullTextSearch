<%@page import="model.Produit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    List<Produit> produits = (List<Produit>) request.getAttribute("result");
%>
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

        <h2>Search Results</h2>
        <table>
            <tr>
                <th>Date de fabrication</th>
                <th>Catégorie</th>
                <th>Marque</th>
                <th>Produit</th>
                <th>Prix</th>
                <th>Devise</th>
            </tr>
            <%for(int i = 0; i<produits.size(); i++){%>
                <tr>
                    <td><%=produits.get(i).getDate_produit()%></td>
                    <td><%=produits.get(i).getCategorie()%></td>
                    <td><%=produits.get(i).getMarque()%></td>
                    <td><%=produits.get(i).getProduit()%></td>
                    <td><%=produits.get(i).getPrix()%></td>
                    <td><%=produits.get(i).getDevise()%></td>
                </tr>
            <%}%>
        </table>
    </div>
</body>
</html>
