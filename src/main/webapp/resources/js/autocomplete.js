$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/suggestor-0.0.1-SNAPSHOT/suggestions?inputText=jas"
    }).then(function(data) {
       $('.greeting-id').append(data[0].id);
       $('.greeting-content').append(data[0].value);
    });
});