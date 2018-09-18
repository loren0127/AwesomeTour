$(document).ready(function() {	
	var currentPage;
	var count;
	var rowCount;
	
	//MypageList
	function MypageList(pageNum,user_email){
		currentPage = pageNum;
		if(pageNum == 1){
			$('#output').empty();
		}
		
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,user_email:user_email},
			url:'complainList_ajax.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				if(count <= 0 || list == null){
					var mypage = '<br><br>';
					mypage += '<div>없어요</div>';
					$('#output').append(mypage);
				}else{
					$(list).each(function(index,item){
						
						var output = '<div class="table-row">';
						output += '<div class="serial">'+item.acc_name+'</div>';
						output += '<div class="serial" style="margin-left:5%;">'+item.acc_num+'</div>';
						output += '<div class="serial">'+item.member_complain_content+'</div>';
						output += '<div class="serial"><a href="mypageComplainDetail.do?num='+item.member_complain_num+'">'+item.member_email+'</a></div>';
						output += '<div class="serial"></div>';
						output += '<div class="serial">'+item.member_complain_reg_date+'</div>';
						output += '<div class="serial">'+item.member_rv_end_date+'</div>';
						output += '<input type="hidden" value="'+item.member_complain_num+'">';
						output += '</div>';
						
						//문서 객체에 추가
						$('#output').append(output);
					});
					
					//pagin button
					if(currentPage>=Math.ceil(count/rowCount)){
						//더보기 없음
						$('.paging-link').hide();
					}else{
						//o
						$('.paging-link').show();
					}
				}
			},error:function(){
				alert('네트워크 오류');
			}
		});
		
		
	}
	$('.paging-link a').click(function(event){
			var pageNum = currentPage + 1;
			MypageList(pageNum,$('#user_email').val());
			
			event.preventDefault();
	});
	MypageList(1,$('#user_email').val());
	
	//----------------------------------------------------------------------------------
	
});









	