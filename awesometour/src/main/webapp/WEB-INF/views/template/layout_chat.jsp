<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/chat.css"
	style="text/css" rel="stylesheet">
				
<div class="messaging">
	<div class="inbox_msg">
		<!-- Message list div start -->
		<div class="inbox_people">
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
											- <a href="#">친구채팅 생성</a>
										</h5>
									</div>
								</div>
								<div class="chat_people">
									<div class="chat_ib">
										<h5>
											- <a href="#">친구채팅 삭제</a>
										</h5>
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
													<a href="selectChatAllJoin.do?chat_all_num=${chatMemberCommand.chat_all_num}&chat_all_num_overlap=${chatMemberCommand.chat_all_num}&user_email=${chatMemberCommand.member_email}&checked=friend">${chatMemberCommand.chat_all_title}</a>
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
													<a href="selectChatAllJoin.do?chat_all_num=${chatMemberCommand.chat_all_num}&chat_all_num_overlap=${chatMemberCommand.chat_all_num}&user_email=${chatMemberCommand.member_email}&checked=group">${chatMemberCommand.chat_all_title}</a>
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
											- <a href="${pageContext.request.contextPath}/chat/insertMessageSend.do?">쪽지 보내기</a>
										</h5>
									</div>
								</div>
								<div class="chat_people">
									<div class="chat_ib">
										<h5>
											- <a href="">기록 삭제하기</a>
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
													<a href="selectMessageDetail.do?message_num=${sendMessageCommand.message_num}&checked=sendMessageList">${sendMessageCommand.message_title}</a>
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
							<div class="chat_list">
								<div class="chat_people">
									<div class="chat_ib">
										<h5>
											- <a href="#">쪽지 삭제하기</a>
										</h5>
									</div>
								</div>
							</div>
							<c:if test="${receiveMessageCount>0}">
								<c:forEach var="receiveMessageCommand" items="${receiveMessageList}">
									<div class="chat_list">
										<div class="chat_people">
											<div class="chat_img">
												<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
											</div>
											<div class="chat_ib">
												<h5>
													<a href="selectMessageDetail.do?message_num=${receiveMessageCommand.message_num}&checked=receiveMessageList">${receiveMessageCommand.message_title}</a>
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