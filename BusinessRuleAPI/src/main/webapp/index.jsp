<html>
<head>
    <title>$Title$</title>
    <script src="javascript/jquery.js"></script>
    <script>

        $(function(){

            $(".josnmooimaakshit").each(function(){
                this.innerHTML = syntaxHighlight(this.innerHTML);
            });

            $(document).on('click', '.postdinkie', function(){

                var link = $(this).attr("href");
                $('#url').val(link);

                return false;
            });

            $(document).on('click', 'button', function(){

                var json = $('#payload').val();
                var url = $('#url').val();

                if(json == ""){

                    $.getJSON(url, function(data){
                        //$('#shithier').html(print_r(data));
                        //var json = JSON.parse(data);

                        var shit = JSON.stringify(data, null, "\t");
                        $('#shithier').html(syntaxHighlight(shit));
                    });

                }
                else {

                    // construct an HTTP request
                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', url);
                    xhr.setRequestHeader("Content-type", "application/json");
                    //xhr.setRequestHeader("Content-Length",json.length);
                    xhr.setRequestHeader('Accept', 'application/json');


                    //alert(json);
                    // send the collected data as JSON
                    xhr.send(json);

                    xhr.onloadend = function () {
                        /*
                        json = xhr.responseText;
                        json = JSON.parse(json);

                        console.log(json);
                        $('#shithier').html(print_r(json));*/

                        json = xhr.responseText;
                        json = JSON.parse(json);

                        var shit = JSON.stringify(json, null, "\t");
                        $('#shithier').html(syntaxHighlight(shit));

                    }
                }
            });

        });

        var print_r = function (obj, t) {

            // define tab spacing
            var tab = t || '';

            // check if it's array
            var isArr = Object.prototype.toString.call(obj) === '[object Array]';

            // use {} for object, [] for array
            var str = isArr ? ('Array\n' + tab + '[\n') : ('Object\n' + tab + '{\n');

            // walk through it's properties
            for (var prop in obj) {
                if (obj.hasOwnProperty(prop)) {
                    var val1 = obj[prop];
                    var val2 = '';
                    var type = Object.prototype.toString.call(val1);
                    switch (type) {

                        // recursive if object/array
                        case '[object Array]':
                        case '[object Object]':
                            val2 = print_r(val1, (tab + '\t'));
                            break;

                        case '[object String]':
                            val2 = '\'' + val1 + '\'';
                            break;

                        default:
                            val2 = val1;
                    }
                    str += tab + '\t' + prop + ' => ' + val2 + ',\n';
                }
            }

            // remove extra comma for last property
            str = str.substring(0, str.length - 2) + '\n' + tab;

            return isArr ? (str + ']') : (str + '}');
        };

        function syntaxHighlight(json) {
            json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
            return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
                var cls = 'number';
                if (/^"/.test(match)) {
                    if (/:$/.test(match)) {
                        cls = 'key';
                    } else {
                        cls = 'string';
                    }
                } else if (/true|false/.test(match)) {
                    cls = 'boolean';
                } else if (/null/.test(match)) {
                    cls = 'null';
                }
                return '<span class="' + cls + '">' + match + '</span>';
            });
        }

    </script>
    <style>
        body{
            font-family: Arial;
        }

        pre {outline: 1px solid #ccc; padding: 5px; margin: 5px; }
        .string { color: green; }
        .number { color: darkorange; }
        .boolean { color: blue; }
        .null { color: magenta; }
        .key { color: red; }
    </style>
</head>
<body>
<div>
    <h1>HUBUGEN - Hogeschool Utrecht Businessrule Generator</h1>
    <p>This is the HUBUGEN (businessrule generator) REST API. HUBUGEN can convert static businessrule definitions into real implementable code. See all callable services below.</p>
    <hr>
    <h1>Service</h1>
    <p>Root where all services reside</p>
        <h2>Generate</h2>
        <p>Here resides the template and generate services</p>
            <h3>Generate code</h3>
            <p>Generate businessrules based on static businessrule definition. Note that logicalOperator is only necessary when there is more then 1 statement. All statements need to be compared with logical operators. When you only have one statement you can ignore this field. See template service to get all suported types.</p>
            <table>
                <tr>
                    <td>Method:</td>
                    <td>POST</td>
                </tr>
                <tr>
                    <td style="vertical-align: top;">URL:</td>
                    <td>/service/generator/{templateName}/{generatorName}<br>/service/generator/generate/{templateName}/{generatorName}</td>
                </tr>
                <tr>
                    <td style="vertical-align: top;">Payload:</td>
                    <td><pre class="josnmooimaakshit">{
	"businessRules": [{
		"CRUDmode": "CUD",
		"category": "CategoryName",
		"code": "CODE",
		"ruleDescription": "Lorem ipsum dolor sit amet consectetur adipiscing elit.",
		"errorMessage": "Lorem ipsum dolor sit amet consectetur adipiscing elit.",
		"table": "TABLE_NAME",
		"type": "TYPE_Rule",
		"typeDescription": "Lorem ipsum dolor sit amet consectetur adipiscing elit.",
		"statements": [{
			"attribute": "TEST",
			"comparisonOperator": "EQUAL",
			"logicalOperator": "",
			"order": 0,
			"staticAttribute": {
				"dataType": "String",
				"value": "testtest"
			}
		}]
	}]
}</pre></td>
                </tr>
            </table>
            <h3>Template</h3>
            <p>Get template by template name.</p>
            <table>
                <tr>
                    <td>Method:</td>
                    <td>GET</td>
                </tr>
                <tr>
                    <td>URL:</td>
                    <td>/service/generator/template/{templateName}</td>
                </tr>
            </table>
        <h2>Database</h2>
            <h3>Tables</h3>
            <p>Get all tables and columns from database.</p>
            <table>
                <tr>
                    <td>Method:</td>
                    <td>POST</td>
                </tr>
                <tr>
                    <td style="vertical-align: top;">URL:</td>
                    <td>/service/database/tables/</td>
                </tr>
                <tr>
                    <td style="vertical-align: top;">Payload:</td>
                    <td><pre class="josnmooimaakshit">{
	"dbDriver": "oracle.jdbc.driver.driverName",
	"dbUrl": "jdbc:oracle:thin:@examlple.com:8521/serverbla",
	"dbUsername": "username",
	"dbPassword": "password"
}</pre></td>
                </tr>
            </table>
    <p></p>
    <hr>
</div>
<h3>target</h3>
<input type="text" id="url" value="" style=" width: 500px; ">
<h3>payload</h3>
<textarea id="payload" style=" width: 100%; height: 500px; "></textarea>
<br>
<button>test</button>
<div><pre id="shithier"></pre></div>
</body>
</html>
