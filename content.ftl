<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<p>${article.id} by ${article.title} by ${article.location}</p>
<ul>
    <#list catalog as catalog>
        <li>${catalog_index + 1}. ${catalog.id} at ${catalog.location}</li>
    </#list>
</ul>
</body>
</html>