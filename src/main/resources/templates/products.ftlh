<!DOCTYPE html>
<html>
<head>
    <title>MINIMARKET</title>
</head>
<body>
<h1>MINIMARKET</h1>
<h4>Goods from all over Ukraine</h4>
<form action="/" method="get">
    Search by title:<input type="text" name="title"><br>
    <input type="submit" value="Search"/>
</form>
<#list products as product>
    <div>
        <p><b>${product.title} </b> ${product.price} hrn. | <a href="/product/${product.id}">detail...</a></p>
    </div>
    <#else >
    <h3> Dont have products</h3>
</#list>
<hr>
<h3>create new product</h3>
<form action="/product/create" method="post" enctype="multipart/form-data">
    Title Ad : <input type="text" name="title"/><br><br>
    Ad Description : <input type="text" name="description"/><br><br>
    Price : <input type="number" name="price"/><br><br>
    City: <input type="text" name="city"/><br><br>
    Name : <input type="text" name="author"/><br><br>
    First foto : <input type="file" name="file1"/><br><br>
    Second foto : <input type="file" name="file2"/><br><br>
    Third foto : <input type="file" name="file3"/><br><br>
    <input type="submit" value="Add item"/>
</form>
</body>
</html>