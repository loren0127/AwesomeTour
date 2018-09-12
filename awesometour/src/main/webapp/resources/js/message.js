$(document).ready(function(){
	var currentPage;
	var count;
	var rowCount;
	
	//댓글 목록
	function selectData(pageNum,num){
		currentPage = pageNum;
		
		if(pageNum == 1){
			//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
			$('#ouput').empty();
		}
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			type:'post',
			data:{pageNum:pageNum, num:num},
			url:'listReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				//로딩 이미지 감추기
				$('#loading').hide();
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count < 0 || list == null){
					alert('목록 호출 오류 발생!');
				}else{
					$(list).each(function(index,item){
						var output = '<div class="item">';
						output += ' <h4>' + item.id + '</h4>';
						output += ' <div class="sub-item">';
						output += '   <p>' + item.re_content + '</p>';
						output += item.re_date;
						if($('#user_id').val()==item.id){
							//로그인 한 id가 댓글 작성자 id와 같으면
							output += '  <input type="button" data-num="'+item.re_num+'" data-id="'+item.id+'" value="수정" class="modify-btn">';
							output += '  <input type="button" data-num="'+item.re_num+'" data-id="'+item.id+'" value="삭제" class="delete-btn">';
						}
						output += '   <hr size="1" noshade>';
						output += ' </div>';
						output += '</div>';
						
						//문서 객체에 추가
						$('#output').append(output);
					});
					
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//다음 페이지가 없음
						$('.paging-button').hide();
					}else{
						//다음 페이지가 존재
						$('.paging-button').show();
					}
				}
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류');
			}
		});
	}
});