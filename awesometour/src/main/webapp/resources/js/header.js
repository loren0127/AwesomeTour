$(document).ready(function(){
	var prev = 0;
	$(window).scroll(function() {
		//스크롤 내리면 헤더 흐려짐
		/*if($(this).scrollTop() < 50)
	        $('#mainNav').css('opacity','1');
	    else
	        $('#mainNav').css('opacity','.9');*/
		
		//스크롤 내리면 헤더 숨김
		var scrollTop = $(this).scrollTop();
		$('#mainNav').toggleClass('hidden', scrollTop > prev);
		prev = scrollTop;
	});
});