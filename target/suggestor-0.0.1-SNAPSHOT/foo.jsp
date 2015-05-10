<html>
<head>
	<title>Spring MVC Autocomplete with JQuery &amp; JSON example</title>
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
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript" 
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

</head>
<body>

<h2>Spring MVC Autocomplete with JQuery &amp; JSON example</h2>
<form method="post" action="#">
<table>
	<tr>
		<th>Technologies</th>
		<td><input path="technologies" id="technologies" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="Save" />
			<input type="reset" value="Reset" />
		</td>
	</tr>
</table>	
<br />
	
</form>

<script type="text/javascript">
function split(val) {
    return val.split(/,\s*/);
}
function extractLast(term) {
    return split(term).pop();
}

$(document).ready(function() {


	$( "#technologies").autocomplete({
	    source: function (request, response) {
	        $.getJSON("${pageContext. request. contextPath}/suggestions", {
	            inputText: extractLast(request.term)
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
	        this.value = terms.join(", ");
	        return false;
	    }
	});
	
});
</script>

</body>
</html>
