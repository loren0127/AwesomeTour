$('document').ready(function(){
	var currentPage;
	var count;
	var rowCount;
	var breakfast = document.getElementById("breakfast").value;
	var WIFI = document.getElementById("WIFI").value;
	
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	//스탠다드룸 리스트
	function StandardRoomListData(pageNum,im_ac_num,check_in,check_out){
		currentPage = pageNum;
		if(pageNum == 1){
			//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
			$('.startContent').empty();
		}
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,im_ac_num:im_ac_num,check_in:check_in,check_out:check_out},
			url:'hotelRoomDetail_standard.do',//데이터 전송->json데이터를 ajax가 반환받아 객체로 만들어줌->success의 function의 인자로 들어감(data)
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				if(count <= 0 || list == null){
					var standardRoom = '<br><br><br><br><br>';
					standardRoom += '<div style="text-align:center;">해당 호텔에 현재 예약가능한 스텐다드 룸은 존재하지 않습니다.</div>';
					
					$('.tableStandard').append(standardRoom);
					
				}else{
				
					$(list).each(function(index,item){
						
						var output = '<div class="table-row">';
						output += '<div class="country startContent" style="margin-left:55px;"><img src="imageView.do?im_ac_num='+item.acc_num+'&ro_room_num='+item.ro_room_num+'&kind=im_cover" style="width:200px;"></div>';
						output += '<div class="country" style="padding-left:80px;">'+breakfast+'<br>'+WIFI+'<br>'+item.ro_bed_type+'x'+item.ro_bed_count+'개</div>';
						output += '<div class="visit" style="padding-left:30px;">'+numberWithCommas(item.ro_price)+'원</div>';
						output += '<div class="visit" style="padding-left:30px;"><input type="button" value="예약하기" class="btn-reply text-uppercase"></div>';
						output += '</div>';
						
						//문서 객체에 추가
						$('.tableStandard').append(output);
					});
					
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//더보기 없음
						$('.paging-link').hide();
					}else{
						//더보기 있음
						$('.paging-link').show();
					}
					
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	}
	
	//디럭스룸 리스트
	function DeluxRoomListData(pageNum,im_ac_num,check_in,check_out){
		currentPage = pageNum;
		if(pageNum == 1){
			//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
			$('.startContent2').empty();
		}
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,im_ac_num:im_ac_num,check_in:check_in,check_out:check_out},
			url:'hotelRoomDetail_delux.do',//데이터 전송->json데이터를 ajax가 반환받아 객체로 만들어줌->success의 function의 인자로 들어감(data)
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				if(count <= 0 || list == null){
					var deluxRoom = '<br><br><br><br><br>';
					deluxRoom += '<div style="text-align:center;">해당 호텔에 현재 예약가능한 디럭스 룸은 존재하지 않습니다.</div>';
					
					$('.tableDelux').append(deluxRoom);
					
				}else{
				
					$(list).each(function(index,item){
						
						var output = '<div class="table-row">';
						output += '<div class="country startContent" style="margin-left:55px;"><img src="imageView.do?im_ac_num='+item.acc_num+'&ro_room_num='+item.ro_room_num+'&kind=im_cover" style="width:200px;"></div>';
						output += '<div class="country" style="padding-left:80px;">'+breakfast+'<br>'+WIFI+'<br>'+item.ro_bed_type+'x'+item.ro_bed_count+'개</div>';
						output += '<div class="visit" style="padding-left:30px;">'+numberWithCommas(item.ro_price)+'원</div>';
						output += '<div class="visit" style="padding-left:30px;"><input type="button" value="예약하기" class="btn-reply text-uppercase"></div>';
						output += '</div>';
						
						//문서 객체에 추가
						$('.tableDelux').append(output);
					});
					
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//더보기 없음
						$('.paging-link2').hide();
					}else{
						//더보기 있음
						$('.paging-link2').show();
					}
					
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	}
	
	//스위트룸 리스트
	function SuiteRoomListData(pageNum,im_ac_num,check_in,check_out){
		currentPage = pageNum;
		if(pageNum == 1){
			//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
			$('.startContent3').empty();
		}
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,im_ac_num:im_ac_num,check_in:check_in,check_out:check_out},
			url:'hotelRoomDetail_suite.do',//데이터 전송->json데이터를 ajax가 반환받아 객체로 만들어줌->success의 function의 인자로 들어감(data)
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				if(count <= 0 || list == null){
					var suiteRoom = '<br><br><br><br><br>';
					suiteRoom += '<div style="text-align:center;">해당 호텔에 현재 예약가능한 스위트 룸은 존재하지 않습니다.</div>';
					
					$('.tableSuite').append(suiteRoom);
					
				}else{
				
					$(list).each(function(index,item){
						
						var output = '<div class="table-row">';
						output += '<div class="country startContent" style="margin-left:55px;"><img src="imageView.do?im_ac_num='+item.acc_num+'&ro_room_num='+item.ro_room_num+'&kind=im_cover" style="width:200px;"></div>';
						output += '<div class="country" style="padding-left:80px;">'+breakfast+'<br>'+WIFI+'<br>'+item.ro_bed_type+'x'+item.ro_bed_count+'개</div>';
						output += '<div class="visit" style="padding-left:30px;">'+numberWithCommas(item.ro_price)+'원</div>';
						output += '<div class="visit" style="padding-left:30px;"><input type="button" value="예약하기" class="btn-reply text-uppercase"></div>';
						output += '</div>';
						
						//문서 객체에 추가
						$('.tableSuite').append(output);
					});
					
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//더보기 없음
						$('.paging-link3').hide();
					}else{
						//더보기 있음
						$('.paging-link3').show();
					}
					
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	}
	
	
	//스탠다드룸 페이징 처리
	$('.paging-link a').click(function(event){
			var pageNum = currentPage + 1;
			StandardRoomListData(pageNum,$('#im_ac_num').val(),$('#check_in').val(),$('#check_out').val());
			
			event.preventDefault();
	});
	
	//디럭스룸 페이징 처리
	$('.paging-link2 a').click(function(event){
		var pageNum = currentPage + 1;
		DeluxRoomListData(pageNum,$('#im_ac_num').val(),$('#check_in').val(),$('#check_out').val());
		
		event.preventDefault();
	});
	
	//스위트룸 페이징 처리
	$('.paging-link3 a').click(function(event){
		var pageNum = currentPage + 1;
		SuiteRoomListData(pageNum,$('#im_ac_num').val(),$('#check_in').val(),$('#check_out').val());
		
		event.preventDefault();
	});
	
	//스탠다드룸 리스트
	StandardRoomListData(1,$('#im_ac_num').val(),$('#check_in').val(),$('#check_out').val());
	//디럭스룸 리스트
	DeluxRoomListData(1,$('#im_ac_num').val(),$('#check_in').val(),$('#check_out').val());
	//스위트룸 리스트
	SuiteRoomListData(1,$('#im_ac_num').val(),$('#check_in').val(),$('#check_out').val());
	
	
	//후기 목록
	function selectData(pageNum,im_ac_num){
		currentPage = pageNum;
		
		if(pageNum == 1){
			//처음 호출 시에는 해당 ID의 div의 내부 내용을 제거
			$('#output').empty();
		}
		
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,im_ac_num:im_ac_num},
			url:'listReview.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				//로딩이미지 감추기
				$('#loading').hide();
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count <= 0 || list == null){
					$('#reviewCount').text(count);
				}else{
					$(list).each(function(index,item){
						
						var output = '<div class="single-comment justify-content-between d-flex " style="float:left;width:50%;">';
						output += '<div class="user justify-content-between d-flex item" style="width:500px;">';
						output += ' <div class="thumb">';
						output += '<img src="../resources/images/NoImage.gif" style="width:80px;" alt="이미지가 없습니다.">';
						output += '</div>';
						output += ' <div class="desc" style="width:500px;">';
						output += ' <h5>' + item.member_nickname +'&nbsp;<span class="like" data-num="'+item.re_num+'" data-email="'+item.re_email+'"><i class="fa fa-thumbs-o-up"></i></span><span class="like_count">'+item.al_count+'</span></h5>';
						if(item.ag_grade == "0.5"){
							output += '<i class="fa fa-star-half-full" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}else if(item.ag_grade == "1"){
							output += '<i class="fa fa-star" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}else if(item.ag_grade == "1.5"){
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star-half-full" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}else if(item.ag_grade == "2"){
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}else if(item.ag_grade == "2.5"){
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star-half-full" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}else if(item.ag_grade == "3"){
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}else if(item.ag_grade == "3.5"){
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star-half-full" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}else if(item.ag_grade == "4"){
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}else if(item.ag_grade == "4.5"){
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star-half-full" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}else if(item.ag_grade == "5"){
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>';
							output += '<i class="fa fa-star" style="color:#424d97;"></i>&nbsp;';
							output += item.ag_grade+'점';
						}
						output += ' <div class="date">'+item.re_reg_date+'</div>';
						output += ' <div class="sub-item" style="width:200px;">';
						output += '  <p class="comment">' + item.re_content + '</p>';
						if($('#user_email').val() == item.re_email){
							output += '   <input type="button" data-num="'+item.re_num+'" data-email="'+item.re_email+'" value="수정" class="btn-reply text-uppercase modify-btn" style="float:left;width:30%;">';
							output += '   <input type="button" data-num="'+item.re_num+'" data-email="'+item.re_email+'" value="삭제" class="btn-reply text-uppercase delete-btn" style="float:left;width:30%;">';
							
							$('#re_content').attr('disabled',true);
							$('#re_content').text('후기는 한번만 작성가능합니다.');
						}
						output += '</div>';
						output += '<br><br>';
						output += '</div>';
						output += '</div>';
						output += '</div>';
						
						var remainCount = count - 4;
						$('#remainCount').text(remainCount);
						$('#reviewCount').text(count);
						
						//문서 객체에 추가
						$('#output').append(output);
					});
					
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){//전체 페이지보다 현재 페이지가 크거나 같으면 다음 페이지 없음
						//다음 페이지가 없음
						$('.paging-link4').hide();
					}else{//전체 페이지보다 현재 페이지가 작으면 다음 페이지 존재
						//다음 페이지가 존재
						$('.paging-link4').show();
					}
				}
			},
			error:function(){
				//로딩이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류');
			}
		});
	}
	
	//다음 댓글 보기 버튼 클릭시 데이터 추가
	$('.paging-link4 a').click(function(event){
		var pageNum = currentPage + 1;
		selectData(pageNum, $('#im_ac_num').val());
		
		event.preventDefault();
	});
	//댓글 등록
	$('#re_form').submit(function(event){//기본 이벤트를 막기위해 event 받는다.(기본이벤트 완전히 제거)
		if($('#re_content').val()==''){
			alert('내용을 입력하세요.');
			$('#re_content').focus();
			return false;//조건에 따른 일시적 제거
		}
		
		if($('.starCount').text() == ''){
			alert('별점을 등록하세요!');
			$('.starCount').focus();
			return false;
		}
		
		var data = $(this).serialize();
		//비동기적 통신 방법으로 등록
		$.ajax({
			type:'post',
			data:data,
			url:'writeReview.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(data.result == 'success'){
					//폼 초기화
					initForm();
					//목록 호출
					selectData(1, $('#im_ac_num').val());
				}else if(data.result == 'not'){
					alert('호텔을 이용하신 후 후기를 등록하실 수 있습니다!')
				}else{
					alert('등록시 오류 발생')
				}
			},
			error:function(){
				alert('등록시 네트워크 오류 발생!');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	//textarea에 내용 입력시 글자수 체크 
	$(document).on('keyup','textarea',function(){//태그명 그대로 기술//수정폼도 있으므로 on 태그 사용
		//입력한 글자수 구함
		var inputLength = $(this).val().length;
		
		if(inputLength>300){//300자를 넘어선 경우
			$(this).val($(this).val().substring(0,300));//300자까지 잘라서 표시
		}else{//300자 이하인 경우
			var remain = 300 - inputLength;
			remain += '/300';
			
			if($(this).attr('id') == 're_content'){
				//등록폼 글자수
				$('#re_first .letter-count').text(remain);
			}else{
				//수정폼 글자수
				$('#mre_first .letter-count').text(remain);
			}
		}
	});
	
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		//글자수 초기화
		$('#re_first .letter-content').text('300/300');
	}
	
	//좋아요 기능
	$(document).on('click','.like',function(){
		
		//후기 번호(후기 리스트에서 가져오기)
		var al_re_num = $(this).attr('data-num');
		
		//숙소 번호
		var al_acc_num = $('#al_acc_num').val();
		
		//아이디(나중에 이메일로 변경)
		var al_email = $('#al_email').val();
		
		//후기 작성자 아이디
		var re_email = $(this).attr('data-email');
		
		$(event.target).parent().html('<i class="fa fa-thumbs-up"></i>');
		  
		  $.ajax({
				type:'post',
				url:'likeReview.do',
				data:{al_re_num:al_re_num,al_acc_num:al_acc_num,al_email:al_email,re_email:re_email},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'logout'){
						alert('로그인 후 좋아요가 가능합니다.');
					}else if(data.result == 'unable'){
						alert('본인의 후기는 좋아요 할 수 없습니다.');
					}else if(data.result == 'cancel'){
						alert('좋아요가 취소되었습니다!');
					}else if(data.result == 'success'){
						alert('좋아요 되었습니다!');
					}else if(data.result == 'failed'){
						alert('좋아요 되지 않았습니다.');
					}else{
						alert('좋아요 되지 않았습니다.');
					}
					selectData(1, $('#im_ac_num').val());
				},
				error:function(){
					alert('네트워크 오류 발생!');
				}
				
		  });
	});
	
	//별점 기능
	$('.starRev span').click(function(){
		  $(this).parent().children('span').removeClass('on');
		  $(this).addClass('on').prevAll('span').addClass('on');
		  var starLength = $('.on').length;
		  var ag_grade = 0.5*starLength;
		  $('.starCount').text(ag_grade+'점');
		  
		  $.ajax({
				type:'post',
				url:'starReview.do',
				data:{ag_grade:ag_grade,ag_acc_num:$('#ag_acc_num').val(),ag_email:$('#ag_email').val()},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'logout'){
						alert('로그인 후 별점 등록이 가능합니다.');
					}else if(data.result == 'success'){
						alert('별점을 등록해주셔서 감사합니다!');
					}else if(data.result == 'failed'){
						alert('별점이 등록되지 않았습니다.');
					}else{
						alert('별점이 등록되지 않았습니다.');
					}
				},
				error:function(){
					alert('네트워크 오류 발생!');
				}
		  });
		  
		  
    });
	
	
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-btn',function(){
		//댓글 글번호
		var re_num = $(this).attr('data-num');
		//작성자 아이디
		var re_email = $(this).attr('data-email');
		//댓글 내용
		var content = $(this).parent().find('p').text();//부모영역에서 p태그를 찾아 정보를 읽어오기
		
		//댓글 수정폼 UI
		var modifyUI = '<form id="mre_form">';
		modifyUI += ' <input type="hidden" name="re_num" id="mre_num" value="'+re_num+'">';
		modifyUI += ' <input type="hidden" name="re_email" id="re_email" value="'+re_email+'">';
		modifyUI += ' <div class="desc">';
		modifyUI += ' <textarea rows="5" cols="100" name="re_content" id="mre_content" class="form-control mb-10 rep-content" placeholder="후기를 등록해보세요!">'+content+'</textarea>';
		modifyUI += ' <div id="mre_first"><span class="letter-count">300/300</span></div>';
		modifyUI += ' <div id="mre_second" class="align-right">';
		modifyUI += '<div class="reply-btn">';
		modifyUI += '  <input type="submit" value="수정" class="btn-reply text-uppercase"  style="float:left;width:20%;">';
		modifyUI += '  <input type="button" value="취소" class="btn-reply text-uppercase re-reset"  style="float:left;width:20%;">';
		modifyUI += '</div>';
		modifyUI += '</div>';
		modifyUI += ' </div>';
		modifyUI += '</form>';
		
		//이전에 이미 수정하는 댓글이 있을 경우
		//수정버튼을 클릭하면 숨김 sub-item을 
		//환원시키고 수정폼을 초기화
		initModifyForm();
		
		//지금 클릭해서 수정하고자 하는 데이터는 감추기
		//수정버튼을 감싸고 있는 div
		$(this).parent().hide();//직계 부모 선택(parent)
		
		//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
		$(this).parents('.desc').append(modifyUI);//여러 부모들 중 하나의 부모 선택(parents)
		
		//입력한 글자수 셋팅
		var inputLength = $('#mre_content').val().length;
		var remain = 300 - inputLength;
		remain += '/300';
		
		//문서객체에 반영
		$('#mre_first .letter-count').text(remain);
	});
	//댓글 수정폼 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.re-reset',function(){
		initModifyForm();
	});
	//댓글 수정폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mre_form').remove();
	}
	//댓글 수정
	$(document).on('submit','#mre_form',function(event){
		if($('#mre_content').val() == ''){
			alert('내용을 입력하세요!');
			$('#mre_content').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		var data = $(this).serialize();
		
		//수정
		$.ajax({
			url:'updateReview.do',
			type:'post',
			data:data,
			dataType:'json',
			cache:false,
			timeout:10000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 수정할 수 있습니다.');//세션 만료(30분 이후) 수정버튼을 눌렀을 경우
				}else if(data.result == 'success'){
					$('#mre_form').parent().find('p').text($('#mre_content').val());//폼에 입력한것을 읽어와서 표시/데이터베이스에서 읽어오는 방식도 있음
					//수정폼 초기화
					initModifyForm();//수정폼을 없애고 명시한 내용을 보여주기
				}else if(data.result == 'wrongAccess'){
					alert('타인의 글은 수정할 수 없습니다.');
				}
			},
			error:function(){
				alert('네트워크 오류');//json문자열이 정확하게 만들어지지 않았을 때->서버 프로그램 문제
			}
		});
		//기본 이벤트 제거
		event.preventDefault();//명시하지 않으면 두번 실행됨
	});
	
	//댓글 삭제
	$(document).on('click','.delete-btn',function(){//document로부터 생성된 미래 태그 처리
		//댓글 번호
		var re_num = $(this).attr('data-num');
		//작성자 아이디
		var re_email = $(this).attr('data-email');
		
		$.ajax({
			type:'post',
			url:'deleteReview.do',
			data:{re_num:re_num,re_email:re_email},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(data.result == 'success'){
					alert('삭제완료!');
					selectData(1,$('#im_ac_num').val());
				}else if(data.result == 'wrongAccess'){
					alert('타인의 글은 삭제할 수 없습니다.');
				}else{
					alert('삭제시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
	});
	
	//초기 데이터(목록) 호출
	selectData(1,$('#im_ac_num').val());
	
	//예약되어있는 호텔 방 체크
});