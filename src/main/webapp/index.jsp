<html>
<head>
	<title>Suggestor</title>
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>

<body>

	<h2>Suggestor</h2>
	
		<form id="suggestionsForm">
			<p>Input Text:<input name="inputPrefix" type="text" id="inputPrefix" size="60"/></p>
			<p><input type="submit" value="submit" /></p>
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
			  
			  success: function(response){
				  $("#inputPrefix").val("") ;
			      alert(response);
			  },
			  error: function(response){
				  $("#inputPrefix").val("") ;
			      alert(response.responseText);
			  },
			  contentType : 'application/json',
			   });
			});
	
		$( "#inputPrefix").autocomplete({
		    source: function (request, response) {
		        $.getJSON("${pageContext. request. contextPath}/suggestions", {
		        	prefixText: extractLast(request.term)
		        }, response);
		    },
		    minLength: 2,
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
		    },
		    position: {
		        my: "left top",
		        at: "left bottom",
		        collision: "none"
		    }
		});
		
	
		});

</script>


</body>
</html>
