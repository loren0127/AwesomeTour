<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/chat.js"></script>

		<!-- Message div start -->
		<div class="mesgs col-sm-12 col-md-6" id="mesgs">
			<div class="msg_history" id="chatMessageArea">
				<!-- My chat message(left) -->
				<c:if test="${!empty chatAllTalkCommandList}">
					<c:forEach var="chatAllTalkCommand" items="${chatAllTalkCommandList}">
						<!-- My chat message(left) -->
						<c:if test="${chatAllTalkCommand.chat_all_talk_nickname eq user_nickname}">
							<div class="outgoing_msg">
								<div class="sent_msg">
									<p>${chatAllTalkCommand.chat_all_talk_content}</p>
									<span class="time_date">${chatAllTalkCommand.chat_all_talk_reg_date}</span>
								</div>
							</div>
							<hr>
						</c:if>
						
						<!-- Other user chat message(right) -->
						<c:if test="${!(chatAllTalkCommand.member_email eq user_email)}">
							<div class="outgoing_msg">
								<div class="incoming_msg_img">
									<img class="rounded-circle" src="${pageContext.request.contextPath}/member/imageView.do?member_email=${chatAllTalkCommand.member_email}">
								</div>
								 ${chatAllTalkCommand.chat_all_talk_nickname}
								<div class="received_msg">
									<div class="received_withd_msg">
										<p>${chatAllTalkCommand.chat_all_talk_content}</p>
									<span class="time_date">${chatAllTalkCommand.chat_all_talk_reg_date}</span>
									</div>
								</div>
							</div>
							<hr>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
			<div class="type_msg">
				<div class="input_msg_write">
					<input type="text" class="write_msg" placeholder="Type a message" id="message" style="padding-right:55px;" disabled>
					<button class="msg_send_btn" type="button" id="sendBtn" style="margin-right:15px;">
						<i class="fa fa-paper-plane-o" aria-hidden="true">☞</i>
					</button>
				</div>
			</div>
		</div>
		<!-- Message div end -->