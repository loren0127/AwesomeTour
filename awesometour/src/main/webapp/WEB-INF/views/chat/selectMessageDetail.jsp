<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mesgs" id="mesgs">
	<div class="msg_history" id="chatMessageArea">
		<!-- My chat message(left) -->
				<!-- My chat message(left) -->
					<div class="outgoing_msg">
						<div class="sent_msg">
							<p>콘텐츠 부분</p>
							<span class="time_date">쪽지 생성일</span>
						</div>
					</div>
					<hr>

				<!-- Other user chat message(right) -->
					<div class="outgoing_msg">
						<div class="incoming_msg_img">
							<img src="https://ptetutorials.com/images/user-profile.png"
								alt="sunil">
						</div>
						<div class="received_msg">
							<div class="received_withd_msg">
								<p>콘텐츠 부분</p>
								<span class="time_date">쪽지 생성일</span>
							</div>
						</div>
					</div>
					<hr>
	</div>
	<div class="type_msg">
		<div class="input_msg_write">
			<input type="text" class="write_msg" placeholder="Type a message"
				id="message" style="padding-right: 55px;" disabled>
			<button class="msg_send_btn" type="button" id="sendBtn"
				style="margin-right: 15px;">
				<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
			</button>
		</div>
	</div>
</div>