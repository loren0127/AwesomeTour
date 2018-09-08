<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/chat.css" style="text/css" rel="stylesheet">

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
					<c:if test="${listCount == 0}">
						<div class="align-center">소속되어 있는 채팅방이 없습니다!</div>
					</c:if>
					
					<c:if test="${listCount > 0}">
						<ul class="nav nav-tabs" role="tablist">
							<%-- <li class="nav-item"><a class="nav-link <c:if test="${selected eq 'mainChat'}">active</c:if>" data-toggle="tab" href="#main_chat">메인</a></li> --%>
							<li class="nav-item"><a class="nav-link <c:if test="${selected eq 'friendChatList'}">active</c:if>" data-toggle="tab" href="#friend_chat">친구채팅</a></li>
							<li class="nav-item"><a class="nav-link <c:if test="${selected eq 'groupChatList'}">active</c:if>" data-toggle="tab" href="#group_chat">그룹채팅</a></li>
							<li class="nav-item"><a class="nav-link <c:if test="${selected eq 'messageList'}">active</c:if>" data-toggle="tab" href="#message_chat">쪽지</a></li>
							<li class="nav-item"><a class="nav-link <c:if test="${selected eq 'settingList'}">active</c:if>" data-toggle="tab" href="#setting_chat">설정</a></li>
						</ul>
						
						<div class="tab-content">
							<%-- <div id="main_chat" class="container tab-pane <c:if test="${selected eq 'mainChat'}">active show</c:if>">
								<div class="chat_list">
									<div class="chat_people">
										<div class="chat_img">
											<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
										</div>
										<div class="chat_ib">
											<h5>
												<a href="#?selected=mainChat">메인 메뉴 링크</a> <span class="chat_date"></span>
											</h5>
											<p>메인 메뉴</p>
										</div>
									</div>
								</div>
							</div> --%>
							
							<div id="friend_chat" class="container tab-pane fade <c:if test="${selected eq 'friendChatList'}">active show</c:if>">
								<c:forEach var="chatMemberCommand" items="${selectChatMemberList}">
									<div class="chat_list">
										<div class="chat_people">
											<div class="chat_img">
												<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
											</div>
											<div class="chat_ib">
												<h5>
													<a href="selectChatAllJoin.do?chat_all_num=${chatMemberCommand.chat_all_num}&chat_all_num_overlap=${chatMemberCommand.chat_all_num}&user_email=${chatMemberCommand.member_email}">${chatMemberCommand.chat_all_title}</a>
													<span class="chat_date">${chatMemberCommand.chat_member_mod_date}</span>
												</h5>
												<p></p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							
							<div id="group_chat" class="container tab-pane fade <c:if test="${selected eq 'groupChatList'}">active show</c:if>">
								<div class="chat_list">
									<div class="chat_people">
										<div class="chat_img">
											<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
										</div>
										<div class="chat_ib">
											<h5>
												<a href="#">그룹 채팅 제목</a> <span class="chat_date">2018-00-00</span>
											</h5>
											<p>최신글 출력란</p>
										</div>
									</div>
								</div>
							</div>
							
							<div id="setting_chat" class="container tab-pane fade <c:if test="${selected eq 'settingList'}">active show</c:if>">
								<div class="chat_list">
									<div class="chat_people">
										<div class="chat_img">
											<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
										</div>
										<div class="chat_ib">
											<h5>
												<a href="${pageContext.request.contextPath}/chat/insertChatFriendAdd.do">친구 채팅 생성</a>
											</h5>
											<p>친구들과 시작하는 채팅!</p>
										</div>
									</div>
								</div>
								<div class="chat_list">
									<div class="chat_people">
										<div class="chat_img">
											<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
										</div>
										<div class="chat_ib">
											<h5>
												<a href="#">친구 채팅 삭제</a>
											</h5>
											<p>채팅 기록과 채팅방이 삭제되요!</p>
										</div>
									</div>
								</div>
								<div class="chat_list">
									<div class="chat_people">
										<div class="chat_img">
											<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
										</div>
										<div class="chat_ib">
											<h5>
												<a href="#">그룹 채팅 초대</a>
											</h5>
											<p>그룹과 함께하는 채팅!</p>
										</div>
									</div>
								</div>
							</div>
							
							<div id="message_chat" class="container tab-pane fade <c:if test="${selected eq 'messageList'}">active show</c:if>">
								<div class="chat_list">
									<div class="chat_people">
										<div class="chat_img">
											<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
										</div>
										<div class="chat_ib">
											<h5>
												<a href="${pageContext.request.contextPath}/chat/insertChatFriendAdd.do">친구 채팅 생성</a>
											</h5>
											<p>친구들과 시작하는 채팅!</p>
										</div>
									</div>
								</div>
								<div class="chat_list">
									<div class="chat_people">
										<div class="chat_img">
											<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
										</div>
										<div class="chat_ib">
											<h5>
												<a href="#">친구 채팅 삭제</a>
											</h5>
											<p>채팅 기록과 채팅방이 삭제되요!</p>
										</div>
									</div>
								</div>
								<div class="chat_list">
									<div class="chat_people">
										<div class="chat_img">
											<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
										</div>
										<div class="chat_ib">
											<h5>
												<a href="#">그룹 채팅 초대</a>
											</h5>
											<p>그룹과 함께하는 채팅!</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</c:if>
			</div>
		</div>
		<!-- Message list div end -->


		<!-- Message div start -->
		<tiles:insertAttribute name="body"/>
		<!-- Message div end -->
	</div>
</div>