$('document').ready(function(){
	var currentPage;
	var count;
	var rowCount;
	

	//후기 목록(좋아요 순)
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
					var output = '<div style="text-align:center">등록된 후기가 없습니다</div>';
					
					$('#reviewCount').text(count);
					$('#output').append(output);
				}else{
					$(list).each(function(index,item){
						
						var output = '<div class="container single-comment justify-content-between d-flex ">';
						output += '<div class="user justify-content-between d-flex item" style="width:100%;">';
						output += ' <div class="thumb">';
						output += '<img src="../member/imageView.do?member_email='+item.re_email+'" style="width:80px;">';
						output += '</div>';
						output += ' <div class="desc" style="width:100%;">';
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
						output += ' <div class="sub-item">';
						output += '  <p class="comment" style="width:100%;height:20%;">' + item.re_content + '</p>';
						if($('#user_email').val() == item.re_email){
							output += '   <input type="button" data-num="'+item.re_num+'" data-email="'+item.re_email+'" value="수정" class="btn-reply text-uppercase modify-btn" style="float:left;width:70px;">';
							output += '   <input type="button" data-num="'+item.re_num+'" data-email="'+item.re_email+'" value="삭제" class="btn-reply text-uppercase delete-btn" style="float:left;width:70px;">';
							
							$('#re_content').attr('disabled',true);
							$('#re_content').text('후기는 한번만 작성가능합니다.');
						}
						output += '</div>';
						output += '<br><br>';
						output += '</div>';
						output += '</div>';
						output += '</div>';
						output += '<br>';
						
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
	
	//최신순
	function selectData2(pageNum,im_ac_num){
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
			url:'listReview2.do',
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
					var output = '<div style="text-align:center">등록된 후기가 없습니다</div>';
					
					$('#reviewCount').text(count);
					$('#output').append(output);
				}else{
					$(list).each(function(index,item){
						
						var output = '<div class="single-comment justify-content-between d-flex "';
						output += '<div class="user justify-content-between d-flex item" style="width:100%;">';
						output += ' <div class="thumb">';
						output += '<img src="../member/imageView.do?member_email='+item.re_email+'" style="width:80px;">';
						output += '</div>';
						output += ' <div class="desc" style="width:100%;">';
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
						output += ' <div class="sub-item" style="width:100%;height:20%;">';
						output += '  <p class="comment">' + item.re_content + '</p>';
						if($('#user_email').val() == item.re_email){
							output += '   <input type="button" data-num="'+item.re_num+'" data-email="'+item.re_email+'" value="수정" class="btn-reply text-uppercase modify-btn" style="float:left;width:70px;">';
							output += '   <input type="button" data-num="'+item.re_num+'" data-email="'+item.re_email+'" value="삭제" class="btn-reply text-uppercase delete-btn" style="float:left;width:70px;">';
							
							$('#re_content').attr('disabled',true);
							$('#re_content').text('후기는 한번만 작성가능합니다.');
						}
						output += '</div>';
						output += '<br><br>';
						output += '</div>';
						output += '</div>';
						output += '</div><br>';
						
						var remainCount = count - 4;
						$('#remainCount2').text(remainCount);
						$('#reviewCount').text(count);
						
						//문서 객체에 추가
						$('#output').append(output);
					});
					
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){//전체 페이지보다 현재 페이지가 크거나 같으면 다음 페이지 없음
						//다음 페이지가 없음
						$('.paging-link5').hide();
					}else{//전체 페이지보다 현재 페이지가 작으면 다음 페이지 존재
						//다음 페이지가 존재
						$('.paging-link5').show();
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
	$('.paging-link5 a').click(function(event){
		var pageNum = currentPage + 1;
		selectData2(pageNum, $('#im_ac_num').val());
		
		event.preventDefault();
	});
	
	//초기데이터(목록) 호출
	$('#review_like').change(function(){
		 if($('#review_like').is(':checked')){
			 $('.paging-link5').hide();
			 
			 selectData(1,$('#im_ac_num').val());
			 
	        }else{
	        	$('#output').empty();
	        }
	});
	
	$('#review_recency').change(function(){
		 if($('#review_recency').is(':checked')){
			 $('.paging-link4').hide();
			 
			 selectData2(1,$('#im_ac_num').val());
			 
		 }else{
	        $('#output').empty();
	  }
		
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
		
		//form 데이터 한번에 읽어오기
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
					alert('프라이빗 하우스를 이용하신 후 후기를 등록하실 수 있습니다!')
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
		  $(this).parent().children('span').removeClass('plus');
		  $(this).addClass('plus').prevAll('span').addClass('plus');
		  var starLength = $('.plus').length;
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
					}else if(data.result == 'not'){
						alert('프라이빗 하우스를 이용하신 후에 별점을 등록하실 수 있습니다!');
					}else{
						alert('별점이 등록되지 않았습니다.');
					}
					selectData(1, $('#im_ac_num').val());
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
	$('.paging-link5').hide();
	selectData(1,$('#im_ac_num').val());
	
	var disabledate;
	
	$.ajax({
		type:'post',
		url:'datePicker.do',
		data:{im_ac_num:$('#im_ac_num').val()},
		dataType:'json',
		cache:false,
		timeout:30000,
		success:function(data){
			disabledate = '\'' + data.startList.join(','); 
			disabledate += ',' + data.endList.join(',') + '\'';
			//console.log(disabledate);
			
			$('#datepicker_accomDetail').datepicker({
				showMonthAfterYear:true,
				prevText: '<',
		        nextText: '>',
				dateFormat:'yy/mm/dd',
				dayNamesMin:['일','월','화','수','목','금','토'],
				monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				yearSuffix: '년',
				minDate:new Date($('#start_date').val()),
				maxDate:new Date($('#end_date').val()),
				onSelect: function(selected) {
					console.log(selected);
					$("#datepicker2_accomDetail").datepicker("option","minDate", selected);//selected가 선택된 날짜
				},
				beforeShowDay: function(day) {
		            if(disabledate.indexOf($.datepicker.formatDate('yy/mm/dd', day)) != -1) return [false, "disabledate", "beforeShowDay로 블록"];
		            else return [true, "", ""];
				}
			}).datepicker("setDate", $('#check_in').val()).datepicker("option","onSelect")($('#check_in').val());
			
			$('#datepicker2_accomDetail').datepicker({
				showMonthAfterYear:true,
				prevText: '<',
		        nextText: '>',
				dateFormat:'yy/mm/dd',
				dayNamesMin:['일','월','화','수','목','금','토'],
				monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				yearSuffix: '년',
				minDate:new Date($('#check_in').val()),
				maxDate:new Date($('#end_date').val()),
				onSelect: function(selected) {
					console.log(selected);
					$("#datepicker_accomDetail").datepicker("option","maxDate", selected);//selected가 선택된 날짜
				},
				beforeShowDay: function(day) {
		            if(disabledate.indexOf($.datepicker.formatDate('yy/mm/dd', day)) != -1) return [false, "disabledate", "beforeShowDay로 블록"];
		            else return [true, "", ""];
				}
			}).datepicker("setDate", $('#check_out').val()).datepicker("option","onSelect")($('#check_out').val());
			
           //alert('전송 성공');
		},
		error:function(){
			alert('네트워크 오류 발생!');
		}
	});
	
	//예약시 로그인 조건체크
	$('.reservation_button').click(function(){
		var rev_btn = $(this);
		if($('#user_email').val() == ''){
			alert('로그인 후 프라이빗 하우스를 예약할 수 있습니다!');
			return false;//a 태그인 경우 return false이고 button인 경우에는 return
		}
		
		//예약제어
		$.ajax({
			type:'post',
			url:'checkReservationAccomDetail.do',
			data:{check_in:$('#check_in').val(),check_out:$('#check_out').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){//a태그 click 이벤트 영역이 아님->위에서 this를 보관해서 변수를 받아서 href에 접근(이 부분에서 새로운 this를 받기 때문!)
				if(data.result == 'unable'){
					alert('현재 날짜 이후에만 예약이 가능합니다!');
				}else{
					location.href = rev_btn.attr('href');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
		event.preventDefault();
	});

	var mapContainer = document.getElementById('detailMap'), // 지도를 표시할 div 
	mapOption = { 
		center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 3 // 지도의 확대 레벨
	};
	//지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new daum.maps.Map(mapContainer, mapOption); 

	//지도를 생성합니다    
	var map = new daum.maps.Map(mapContainer, mapOption); 

	//주소-좌표 변환 객체를 생성합니다
	var geocoder = new daum.maps.services.Geocoder();

	//주소로 좌표를 검색합니다
	geocoder.addressSearch($('#address').text(), function(result, status) {

		//정상적으로 검색이 완료됐으면 
		if (status === daum.maps.services.Status.OK) {

			var coords = new daum.maps.LatLng(result[0].y, result[0].x);

			//인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new daum.maps.InfoWindow();
			infowindow.open(map);

			//지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);

			var customOverlay = new daum.maps.CustomOverlay({
				map: map,
				position: coords,
				content: '<div class="arrow_box">'+$('#acc_name').val()+'</div>',
			});

			//커스텀 오버레이를 지도에 표시합니다
			customOverlay.setMap(map);

		} 
	}); 
	
	$(document).on('keyup','#message_title',function(){
		var dialogTitleLength = $(this).val().length;
	    
	    if(dialogTitleLength >= 25){
	    	alert('25자 미만으로 입력해주세요!');
	    }
	});
	
	$(document).on('keyup','#message_content',function(){
		var dialogContentLength = $(this).val().length;
	    
	    if(dialogContentLength >= 300){
	    	alert('300자 미만으로 입력해주세요!');
	    }
	});
	
	 dialog = $('#accomDetail_dialog').dialog({
	      autoOpen: false,
	      height: 410,
	      width: 350,
	      modal: true,
	      buttons:[
	          {
	              text: "보내기",
	              click: function(){
	  	    		$.ajax({
		    			type:'post',
		    			url:'hostMessage.do',
		    			data:{message_title:$('#message_title').val(),message_content:$('#message_content').val(),message_receiver:$('#message_receiver').val(),message_sender:$('#user_email').val()},
		    			dataType:'json',
		    			cache:false,
		    			timeout:30000,
		    			success:function(data){
		    				if(data.result == 'logout'){
		    					alert('로그인 후 메시지를 보낼 수 있습니다!');
		    				}else if(data.result == 'success'){
		    					alert('메시지가 전송되었습니다!');
		    				}
		    			},
		    			error:function(){
		    				alert('네트워크 오류 발생!');
		    			}
		    		});
	  	    		
	  	    		 $( this ).dialog( "close" );
		    	},
	              type: "submit"
	          },
	          {
	              text: "취소",
	              click: function() {
	                  $( this ).dialog( "close" );
	              }
	          }
	      ],
	      close: function() {
	        form[ 0 ].reset();
	      }
	    });
	 
	    form = dialog.find( "form" ).on( "submit", function( event ) {
	      event.preventDefault();
	    });
	    
	    $( "#host_message_button" ).button().on( "click", function() {
	        dialog.dialog( "open" );
	     });
	
	   
});