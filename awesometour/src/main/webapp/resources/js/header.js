$(document).ready(function(){
	$(window).scroll(function() {
		if($(this).scrollTop() < 50)
	        $('#mainNav').css('opacity','1');
	    else
	        $('#mainNav').css('opacity','.8');
	});
});