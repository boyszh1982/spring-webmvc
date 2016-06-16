
// ---------- Preloader ----------
(function($) {
	"use strict";
	$(window).load(function() {
		$("#loader").fadeOut();
		$("#preloader").delay(1000).fadeOut("slow");
	});
	//console.log("(function($) {...})(jQuery); line 3 ");
})(jQuery);

// paginations
$(function() {
	//console.log("$(function() {...}); line 20");
    $('#pagination_nm').pagination({
        totalPages: 8,
        visiblePages: 5,
        onPageClick: function (event, page) {
            $('#page-content_nm').text('Page ' + page);
        }
    });
});  
  

$(function() {
	
	$('.btn-danger').click(function(){
		var tagval = $('#tags').val();
		$('#tags_hidden').val(tagval);
		$('#form0').submit();
		/*
		var url = "http://localhost:8080/boyz/OnlineController/show.do";
		if( tagval != '' ){
			url += "?msgs="+encodeURIComponent(tagval);
		}
		
		window.location.href = url ;
		*/
	});
	
	$('#tags').keydown(function( event ){
		if(event.keyCode == 13) {
			$('.btn-danger').click();
		}
	});
	
	
	
	//console.log("(function($) {...})(jQuery); line 121");
	
	/*
	var availableTags = ["ActionScript", "AppleScript", "Asp", "BASIC", "C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp", "Perl", "PHP", "Python", "Ruby", "Scala", "Scheme"];
	$("#tags").autocomplete({
	    source: availableTags
	});
	*/
	/*
	var proposals = ['boat', 'bear', 'dog', 'drink', 'elephant', 'fruit'];

	$(document).ready(function(){
		$('#search-form').autocomplete({
			hints: proposals,
			width: 300,
			height: 30,
			onSubmit: function(text){
				$('#message').html('Selected: <b>' + text + '</b>');			
			}
		});
	});
	*/
});
