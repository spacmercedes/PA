<html>
<head>
    <title>
       Representation
    </title>


    <style>
        img{
            width:100px;
            height:500px;
        }

        body{
            padding:25px;
            display: flex;
            flex-direction: row;
            flex-wrap: nowrap;
            background: lightslategrey;
        }

        .list
        {
            display: flex;
            flex-direction: column;
            flex-wrap: wrap;
        }
    </style>


</head>
<body>
<div>
    <img src=${image}/>
</div>
<br>

<div class="list">
    <h3>Utilizatori:</h3>
    <#list clienti as clienti>
        <ol>
            User: ${clienti.name}
        </ol>
    </#list>
</div>


</body>
</html>