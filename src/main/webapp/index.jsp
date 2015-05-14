<html>
<head>
	<title>Suggestor</title>
	<style>
	body {
		font-family: Calibri;
	}
	table {
		border: 1px solid;
		border-collapse: collapse;
	}
	td {
		border: 1px solid;
	}
	th {
		text-align: left;
	}
	</style>
	<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" />
	
	<script type="text/javascript" 
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" 
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

</head>
<body>

<h2>Suggestor</h2>

	
		<form id="suggestionsForm">
			Input Text:<input name="inputPrefix" type="text" id="inputPrefix"/> <br/>
			<input type="submit" value="submit" />
		</form>



<script type="text/javascript">
function split(val) {
    return val.split(/,\s*/);
}
function extractLast(term) {
    return split(term).pop();
}

$(document).ready(function() {

	$("#suggestionsForm").submit(function(event) {
		event.preventDefault() ;
		var inputData = $("#inputPrefix").val().trim() ;
		if(inputData.substr(inputData.length - 1) == ',' ) {
			inputData = inputData.substring(0, inputData.length - 1);
		}
		var prefixTexts = JSON.stringify(inputData.split(","));
		$.ajax({     
		  url: "${pageContext. request. contextPath}/suggestions",
		  type: "POST",
		  cache: false,
		  data: prefixTexts,
		  success: function(){},
		  dataType: 'json',
		  contentType : 'application/json',
		   });
		});

	$( "#inputPrefix").autocomplete({
	    source: function (request, response) {
	        $.getJSON("${pageContext. request. contextPath}/suggestions", {
	        	prefixText: extractLast(request.term)
	        }, response);
	    },
	    search: function () {
	        // custom minLength
	        var term = extractLast(this.value);
	        if (term.length < 1) {
	            return false;
	        }
	    },
	    focus: function () {
	        // prevent value inserted on focus
	        return false;
	    },
	    select: function (event, ui) {
	        var terms = split(this.value);
	        // remove the current input
	        terms.pop();
	        // add the selected item
	        terms.push(ui.item.value);
	        // add placeholder to get the comma-and-space at the end
	        terms.push("");
	        this.value = terms.join(",");
	        return false;
	    }
	});
	

	});

</script>


</body>
</html>
