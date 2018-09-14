$(document).ready(function(){
	var prev = 0;
	$(window).scroll(function() {
		
		/*if($(this).scrollTop() < 50)
	        $('#mainNav').css('opacity','1');
	    else
	        $('#mainNav').css('opacity','.9');*/
		
		//스크롤 내리면 헤더 숨김
		var scrollTop = $(this).scrollTop();
		$('#mainNav').toggleClass('hidden', scrollTop > prev);
		prev = scrollTop;
	});
	
	$('#map_search').click(function(){
		if($('#searchyouwant').val()==''){
			alert('검색어를 입력하세요.');
			return false;
		}
	});
});