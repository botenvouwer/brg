<html>
<head>
    <title>$Title$</title>
    <script src="javascript/jquery.js"></script>
    <script>

        $(function(){

            $(document).on('click', 'button', function(){

                var json = '{"businessRules": [ { "CRUDmode": "CRUD_test", "category": "category_test", "code": "code_test", "ruleDescription": "ruleDesc_test", "statements": [ { "attribute": "attribute_test", "comparisonOperator": "comparisonOperator_test", "dynamicAttribute": { "attribute": "attribute_test", "foreignKey": "test_foreignKey", "table": "tableTest" }, "logicalOperator": "logicalOperator_test", "order": 0 }, { "attribute": "attribute_test", "comparisonOperator": "comparisonOperator_test", "dynamicAttribute": { "attribute": "attribute_test", "foreignKey": "test_foreignKey", "table": "tableTest" }, "logicalOperator": "logicalOperator_test", "order": 0 } ], "table": "table_test", "type": "type_test", "typeDescription": "typeDesc_test" }, { "CRUDmode": "CRUD_test", "category": "category_test", "code": "code_test", "ruleDescription": "ruleDesc_test", "statements": [{ "attribute": "attribute_test", "comparisonOperator": "comparisonOperator_test", "logicalOperator": "logicalOperator_test", "order": 0, "staticAttribute": { "dataType": "int", "value": "30" } }], "table": "table_test", "type": "type_test", "typeDescription": "typeDesc_test" } ]}';

                // construct an HTTP request
                var xhr = new XMLHttpRequest();
                xhr.open('POST', '/service/bsn/convert');
                xhr.setRequestHeader("Content-type", "application/json");
                //xhr.setRequestHeader("Content-Length",json.length);
                xhr.setRequestHeader('Accept', 'application/json');


                //alert(json);
                // send the collected data as JSON
                xhr.send(json);


                xhr.onloadend = function() {
                    console.log(xhr.responseText);
                }

            });

        });

    </script>
</head>
<body>
<div>
    <h2>GET:</h2>
    <div><p>1. <b>Geeft uitgebreide JSON terug (object-in-object, arrays)</b><br/><a href="/service/bsn">/service/bsn</a></p>
        <p>2. <b>Geeft plaintext terug</b><br/><a href="/service/bsn/raw">/service/bsn/raw</a></p>
        <p>3. <b>Geeft een simpel JSON-oject terug</b><br/><a href="/service/bsn/simple">/service/bsn/simple</a></p>
        <p>4. <b>Geeft officieel voorbeeld van businessrules JSON object terug</b><br/><a href="/service/bsn/example">/service/bsn/example</a></p></div>

    <h2>POST:</h2>
    <div><p>1. <b>Hier kan een arraylist van businessrules naartoe gestuurd worden</b><br/><a href="/service/bsn/convert">/service/bsn/convert</a></p></div>
</div>
<button>test</button>
<div id="shithier"></div>
</body>
</html>
