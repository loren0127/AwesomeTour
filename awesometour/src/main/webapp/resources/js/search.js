$(document).ready(function() {	
	$('.pop').hide();
});


$(function() {
    //input을 datepicker로 선언
    $( "#date_in" ).datepicker({
        altFormat: "yyyy-mm-dd",
        changeMonth: true,
        dayNamesShort: 
            [ "일", "월", "화", "수", "목", "금", "토" ],
        defaultDate: +7,
        selectOtherMonths: true,
        stepMonths: 3//달력에서 좌우 선택시 이동할 개월 수
    });

    $('#date_out').datepicker();
});

