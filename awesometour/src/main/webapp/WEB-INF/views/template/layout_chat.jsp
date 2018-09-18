<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/chat.css" style="text/css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/chatEmailCheck.js"></script>
<style>
@media all and (max-width: 768px) {
    #hide_nav {
    display:inline-flex;
    }
    
    .inbox_people {
    display:none;
    }
}

@media all and (min-width: 768px) {
    #hide_nav {
    display:none;
    }
}
</style>
<script type="text/javascript">
var status = 1;
$(document).ready(function() {
	$(window).resize(function() {
		var windowSize = $(window).width();
		if(windowSize >= 768) {
			$('.inbox_people').css('display', 'inline');
			$('.mesgs').css('display', 'inline');
		} else {
			$('.mesgs').css('display', 'inline');
			$('.inbox_people').css('display', 'none');
			status = 1;
			
			$(document).on('click', '.hide_a', function() {
				$('.inbox_people').css('display', 'none');
				$('.mesgs').css('display', 'inline');
				status = 1;
			});
		}
	});
	
	$(document).on('click', '#sidebarCollapse', function() {
		if(status == 0) {
			$('.inbox_people').css('display', 'none');
			$('.mesgs').css('display', 'inline');
			status = 1;
		} else {
			$('.inbox_people').css('display', 'inline');
			$('.mesgs').css('display', 'none');
			status = 0;
		}
	});
});
</script>

<nav class="navbar navbar-expand-sm bg-light navbar-dark sticky-top" id="hide_nav" style="height: 50px; width: 100%;">
	<button type="button" id="sidebarCollapse" class="btn btn-primary" style="">
		<svg class="svg-inline--fa fa-align-left fa-w-14" aria-hidden="true" data-prefix="fas" data-icon="align-left" role="img"
			xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg="" style="width:14px;">
			<path fill="currentColor" d="M288 44v40c0 8.837-7.163 16-16 16H16c-8.837 0-16-7.163-16-16V44c0-8.837 7.163-16 16-16h256c8.837 0 16 7.163 16 16zM0 172v40c0 8.837 7.163 16 16 16h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16zm16 312h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16zm256-200H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16h256c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16z"></path>
		</svg>
		<span></span>
	</button>
</nav>
	
	
<div class="messaging">
	<div class="inbox_msg">
	<div class="row">
		<!-- Message list div start -->
		<div  class="inbox_people col-12 col-md-6">
			<div class="headind_srch">
				<div class="recent_heading">
					<h4>대화방</h4>
				</div>
				<div class="srch_bar">
					<div class="stylish-input-group">
						<span class="input-group-addon">
							<button type="button">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
						</span>
					</div>
				</div>
			</div>
			
			<div class="inbox_chat">
				<c:if test="${!empty user_email}">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a
							class="nav-link <c:if test="${selected eq 'friendChatList' || selected eq 'mainChat'}">active</c:if>"
							data-toggle="tab" href="#friend_chat">친구채팅</a></li>
						<li class="nav-item"><a
							class="nav-link <c:if test="${selected eq 'groupChatList'}">active</c:if>"
							data-toggle="tab" href="#group_chat">그룹채팅</a></li>
						<li class="nav-item"><a
							class="nav-link <c:if test="${selected eq 'sendMessageList'}">active</c:if>"
							data-toggle="tab" href="#send_message_chat">보낸쪽지</a></li>
						<li class="nav-item"><a
							class="nav-link <c:if test="${selected eq 'receiveMessageList'}">active</c:if>"
							data-toggle="tab" href="#receive_message_chat">받은쪽지</a></li>
					</ul>

					<div class="tab-content">
						<!-- 친구 채팅 -->
						<div id="friend_chat" class="container tab-pane fade <c:if test="${selected eq 'friendChatList' || selected eq 'mainChat'}">active show</c:if>">
							<div class="chat_list">
								<div class="chat_people">
									<div class="chat_ib">
										<h5>
											- <a href="#" data-toggle="modal" data-target="#myModal">채팅 신청</a>
										</h5>

											<div class="modal" id="myModal">
												<div class="modal-dialog">
													<div class="modal-content">

														<!-- Modal Header -->
														<div class="modal-header">
															<h4 class="modal-title">채팅 신청 쪽지</h4>
															<button type="button" class="close" data-dismiss="modal">&times;</button>
														</div>

														<!-- Modal body -->
														<form:form id="friendChatInvite" commandName="messageCommand" action="insertFriendChatSendMessage.do">
														<div class="modal-body" >
															
																제목: <form:input path="message_title" class="form-control form-control-sm"/>
																<hr>
																받는이:
																<div class="input-group mb-3">
																	<input id="message_sender_chat" name="message_receiver" class="form-control form-control-sm" type="text" value="">
																	<div class="input-group-append">
																		<button class="btn btn-success btn-sm" id="checkedEmailChat" onclick="return false;">이메일 확인</button>
																	</div>
																</div>
																<span id="receiverCheckedMessageChat"></span>
																<hr>
																내용 : <form:textarea path="message_content" class="form-control form-control-sm"/><br>
														</div>
														
														<!-- Modal footer -->
														<div class="modal-footer">
															<button type="submit">전송</button>
														</div>
														
														</form:form>
													</div>
												</div>
											</div>
										</div>
								</div>
							</div>
							<c:forEach var="chatMemberCommand" items="${selectChatMemberList}">
								<c:if test="${chatMemberCommand.group_num eq -1}">
									<div class="chat_list">
										<div class="chat_people">
											<div class="chat_img">
												<img src="https://ptetutorials.com/images/user-profile.png"
													alt="sunil">
											</div>
											<div class="chat_ib">
												<h5>
													<a class="hide_a" href="selectChatAllJoin.do?chat_all_num=${chatMemberCommand.chat_all_num}&chat_all_num_overlap=${chatMemberCommand.chat_all_num}&user_email=${chatMemberCommand.member_email}&checked=friend">${chatMemberCommand.chat_all_title}</a>
													<br><br>
													<span class="chat_date">${chatMemberCommand.chat_member_mod_date}</span>
												</h5>
												<p></p>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>

						<!-- 그룹 채팅 -->
						<div id="group_chat" class="container tab-pane fade <c:if test="${selected eq 'groupChatList'}">active show</c:if>">
							<div class="chat_list">
								<div class="chat_people">
									<div class="chat_ib">
										<h5>
											- <a href="${pageContext.request.contextPath}/group/groupMain.do" target="_blank">그룹 관리</a>
										</h5>
									</div>
								</div>
							</div>
							<c:forEach var="chatMemberCommand" items="${selectChatMemberList}">
								<c:if test="${!(chatMemberCommand.group_num eq -1)}">
									<div class="chat_list">
										<div class="chat_people">
											<div class="chat_img">
												<img src="https://ptetutorials.com/images/user-profile.png"
													alt="sunil">
											</div>
											<div class="chat_ib">
												<h5>
													<a class="hide_a" href="selectChatAllJoin.do?chat_all_num=${chatMemberCommand.chat_all_num}&chat_all_num_overlap=${chatMemberCommand.chat_all_num}&user_email=${chatMemberCommand.member_email}&checked=group">${chatMemberCommand.chat_all_title}</a>
													<br><br>
													<a href="#" data-toggle="modal" data-target="#groupChatMemberList">사용자 보기</a>
													<span class="chat_date">${chatMemberCommand.chat_member_mod_date}</span>
												</h5>
												<!-- Chat member list modal -->
												<div class="modal" id="groupChatMemberList">
													<div class="modal-dialog">
														<div class="modal-content">
															<!-- Modal Header -->
															<div class="modal-header">
																<h5 class="modal-title">${chatMemberCommand.chat_all_title}</h5>
																<button type="button" class="close" data-dismiss="modal">&times;</button>
															</div>
															
															<!-- Modal body -->
															<div class="modal-body">
																<span>- 사용자 목록 -</span><br>
																<a href="#">${chatMemberCommand.chat_all_member_list}</a>
																<hr>
															</div>
														</div>
													</div>
												</div>
												<!-- Chat member list modal -->
												<p></p>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>

						<!-- 보낸 쪽지 -->
						<div id="send_message_chat" class="container tab-pane fade <c:if test="${selected eq 'sendMessageList'}">active show</c:if>">
							<div class="chat_list">
								<div class="chat_people">
									<div class="chat_ib">
										<h5>
											- <a class="hide_a" href="${pageContext.request.contextPath}/chat/insertMessageSend.do?">쪽지 보내기</a>
										</h5>
									</div>
								</div>
							</div>
							<c:if test="${sendMessageCount>0}">
								<c:forEach var="sendMessageCommand" items="${sendMessageList}">
									<div class="chat_list">
										<div class="chat_people">
											<div class="chat_img">
												<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
											</div>
											<div class="chat_ib">
												<h5>
													<a class="hide_a" href="selectMessageDetail.do?message_num=${sendMessageCommand.message_num}&checked=sendMessageList">${sendMessageCommand.message_title}</a>
													<button class="btn btn-light btn-sm" style="float:right;" onclick="location.href='updateMessageSendStatus.do?message_num=${sendMessageCommand.message_num}'">삭제</button>
													<br><br>
													<span class="chat_date">${sendMessageCommand.message_reg_date}</span>
												</h5>
												<p>${sendMessageCommand.message_sender}</p>
											</div>
										</div>
									</div>
								</c:forEach>
								<div align="center">${sendMessagePagingHtml}</div>
							</c:if>
						</div>
						
						<!-- 받은 쪽지 -->
						<div id="receive_message_chat" class="container tab-pane fade <c:if test="${selected eq 'receiveMessageList'}">active show</c:if>">
							<c:if test="${receiveMessageCount < 1}">
								<div class="chat_list">
									<div class="chat_people">
										<div class="chat_ib">
											<h5>
												- 쪽지가 없어요!
											</h5>
										</div>
									</div>
								</div>
							</c:if>
							
							<c:if test="${receiveMessageCount > 0}">
								<c:forEach var="receiveMessageCommand" items="${receiveMessageList}">
									<div class="chat_list">
										<div class="chat_people">
											<div class="chat_img">
													<c:if test="${receiveMessageCommand.message_receive_status == 1}">
														<span class="badge badge-pill badge-warning" style="margin-bottom: -15; position: relative;">N</span>
													</c:if>
													
												<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
											</div>
											<div class="chat_ib">
												<h5>
													<a href="selectMessageDetail.do?message_num=${receiveMessageCommand.message_num}&checked=receiveMessageList">${receiveMessageCommand.message_title}</a>
													<button class="btn btn-light btn-sm" style="float:right;" onclick="location.href='updateMessageReceiveStatus.do?message_num=${receiveMessageCommand.message_num}'">삭제</button>
													<br><br>
													<span class="chat_date">${receiveMessageCommand.message_reg_date}</span>
												</h5>
												<p>${receiveMessageCommand.message_receiver}</p>
											</div>
										</div>
									</div>
								</c:forEach>
								<div align="center">${receiveMessagePagingHtml}</div>
							</c:if>
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<!-- Message list div end -->

		<!-- Message div start -->
		<tiles:insertAttribute name="body" />
		<!-- Message div end -->
	</div>
</div>
</div>