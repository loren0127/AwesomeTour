<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mesgs col-sm-12 col-md-6" id="mesgs">
	<div class="msg_history" id="chatMessageArea">
		<div class="outgoing_msg">
			<div class="sent_msg_long">
				<p style="text-align: center;">제목  : ${messageCommand.message_title}</p>
			</div>
		</div>
		<div class="outgoing_msg">
			<div class="sent_msg_long">
				<p style="text-align: center;">보낸이  : ${messageCommand.message_sender}</p>
			</div>
		</div>
		<div class="outgoing_msg">
			<div class="sent_msg_long">
				<p style="text-align: center;">받는이  : ${messageCommand.message_receiver}</p>
			</div>
		</div>
		<hr>

		<!-- Other user chat message(right) -->
		<div class="outgoing_msg" style="text-align:center; margin-top:5px;">
			<div class="incoming_msg_img">
				<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
			</div>
			<div class="received_msg_long">
				<div class="received_withd_msg_long">
					<p style="overflow-y: scroll; height: 150px;">${messageCommand.message_content}</p>
					<span class="time_date">작성일 : ${messageCommand.message_reg_date}</span>
				</div>
			</div>
			<div class="outgoing_msg">
				<c:if test="${messageCommand.message_type == 'chatInvite'}">
					<div class="sent_msg_long">
						<a href="${messageCommand.message_URL}"><p style="text-align: center;">채팅 참여하기</p></a>
					</div>
				</c:if>
				<c:if test="${messageCommand.message_type == 'groupInvite'}">
					<div class="sent_msg_long">
						<a href="${messageCommand.message_URL}"><p style="text-align: center;">그룹 참여하기</p></a>
					</div>
				</c:if>
			</div>
		</div>
		<hr>
	</div>
</div>