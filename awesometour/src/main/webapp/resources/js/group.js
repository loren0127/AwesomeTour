 $(function(){

	   //주소-좌표 변환 객체를 생성
	   
	   String.prototype.replaceAt=function(index, character) {
		    return this.substr(0, index) + character + this.substr(index+character.length);
		}
	   
		//관심사 츨력
		function makeHobby(){  
		  	$('.hobby').each(function(element, index){
		  		
			  var hobby = $(this).children('input').val();
			  var hobbyArray = hobby.split(',');
			  var appendText = ''
			  for(var i in hobbyArray){
				  appendText += '<div class="col-4" ><div class="hobby_small rounded" style="height:30px; width:100%; margin: auto; background-color:#dedede"><h7>'+hobbyArray[i]+'</h7></div></div>';
			  }
			  
			  $(this).append(appendText);
		  	});
	  
		  }
		
		
		 makeHobby();
	  //다이얼 로그 설정
	  var dialog = $( "#addDialog" ).dialog({
		  height:500,
		  position: {my: "center center", at: "center 10000", of: $("#list")},
	      autoOpen: false,
	      width: "auto",
	      maxWidth: 600,
	      modal: true,
	     // position :{my: "10 10", at: "center", of: window},
	      buttons: {
	        "그룹 생성":function() {
	        	$('#groupInsert').submit();
	        },
	        "취소": function() {
	          dialog.dialog( "close" );
	        },
	      
	      },  
	      
	      close: function() {
	        form[ 0 ].reset();
	      }
	    });
	 
	  	//그룹 생성
	    var form = dialog.find( "form" ).on( "submit", function( event ) {
	      event.preventDefault();
	 		$('#groupInsert').ajaxSubmit({
	    	  	type:'post',
				//data:data,
				url:'groupInsert.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'logout'){
						alert('로그인해야 작성 할 수 있습니다.');
					}else if(data.result == 'success'){
						alert('등록 완료 하였습니다!');
						dialog.dialog( "close" );

					}else{
						alert('실패??');

					}
				},
				error:function(request,status,error){
					alert('등록시 네트워크 오류 발생!');
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);


				}
	    	  
	      })
	    });
	    //다이얼로그 출력 버튼
	    $( "#groupAdd" ).button().on( "click", function() {
	      dialog.dialog( "open" );
	     


	    });
	    //윈도우 반응형 다이얼로그
	     $(window).resize(function() {
	        $("#addDialog").dialog("option", "position", {my: "center", at: "center", of: window});
	    }); 
	    
	    //탭 생성 
	     $( function() {
	    	 $( "#tabs" ).tabs();
	    } );
	    
	    //탭이동 
	    $("#move-2").click(function(){
			$( "#tabs" ).tabs({ active: 1 });
	     })
	     
	     $("#move-3").click(function(){
			$( "#tabs" ).tabs({ active: 2 });
	     })

	     
	     //날짜
   /*      $('#g_close_date').datepicker({
        			 showMonthAfterYear:true, //default 월 년
        			dateFormat:'yy/mm/dd',
        			dayNamesMin:['일','월','화','수','목','금','토'],//default 영문
        			monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],//default 영문
        			changeYear:true,
        			changeMonth:true		 
         }); */
	    
	    
	    //검색 ajax
	    $('#address,#hobby,#date,#myGroup').change(function() {
	   		var address = $('#address option:selected').val();
	   		var hobby = $('#hobby option:selected').val();
	   		var date = $('#date').val();
	   		var myGroup = 0;
	   		if($("input:checkbox[id='myGroup']").is(":checked")){
	   			myGroup = 1;
	   		}
	   		

	   		    $.ajax({
		    	  	type:'post',
					data:{address:address,hobby:hobby,date:date,myGroup:myGroup},
					url:'groupListFilter.do',
					dataType:'json',
					cache:false,
					timeout:30000,
					success:function(data){
						var appendText = "";
						$('#group-content').empty();
						

						if(data.result == 'logout'){
							
							
						}else if(data.result == 'success'){
							var list = data.list; 
							$(list).each(function(index,item){
								var image = item.g_imageName;
								appendText+='<div class="col-lg-4 col-md-6 col-sm-12" id="groupPage"  style="text-align:center; padding:20px ">';
								appendText+='<div class="rounded" onclick="location.href=\'groupDetail.do?g_num='+item.g_num+'\'"  id="pageArea" style="padding:10px 10px; " >';
								appendText+='<div class="rounded"  style="height:auto;  max-width:100%; ">';
								if(image!=null){
										if (image.endsWith('.jpg') ||
											image.endsWith('.JPG') ||
											image.endsWith('.gif') ||
											image.endsWith('.png') ||
											image.endsWith('.PNG') ||
											image.endsWith('.GIF') ){
								appendText+='<img  class="rounded" src="../group/imageView.do?g_num='+item.g_num+'" style=" max-width:100%; ">';
										}
								}else {
								appendText+='<img class="rounded" src="../resources/img/mbr-1.jpg" style="  max-width:100%; " >';
										}
								appendText+='</div>';
								appendText+='<h5 style="margin: 10px;">'+item.g_name+'</h5>';
								appendText+='<div class="row hobby justify-content-start" style="margin: 0 10px;">';
								appendText+='<input type="hidden" value="'+item.g_hobby+'">';
								appendText+='</div>';
								appendText+='</div>';
								appendText+='</div>';
								
							 });
								$('#group-content').css("dispay","none")
								$('#group-content').append($(appendText).hide().fadeIn(200));
							

								$('#group-content').fadeIn(5000);
								
				 				
				 				$('#paging').empty();
				 				$('#paging').append(data.pagingHtml);
				 				makeHobby();
				 				

						}else if(data.result == 'noCount'){
							$('#paging').empty();
							appendText+='<div style="width: 100%; text-align: center; margin: 200px 0;">일치하는 내용이 없습니다!</div>';
			 				$('#group-content').append(appendText);
						}
					},
					error:function(request,status,error){
						alert('등록시 네트워크 오류 발생!');
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
		    	  
		      })
	    });
	    
	    
	    
	    	    
	     
	  } );