<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Message div start -->
<div class="mesgs">
	<div class="msg_history">
		<div class="incoming_msg">
			<div class="incoming_msg_img">
				<img src="https://ptetutorials.com/images/user-profile.png"
					alt="sunil">
			</div>
			<c:if test="${chatResult == 'chatMain'}">
				<div class="received_msg">
					<div class="received_withd_msg">
						<p>어썸투어 채팅 서비스에 오신것을 환영합니다!</p>
						<span class="time_date">- 운영자 -</span>
					</div>
					<hr>
				</div>
			</c:if>
			
			<c:if test="${chatResult == 'messageSend'}">
				<div class="received_msg">
					<div class="received_withd_msg">
						<p>쪽지 전송을 완료했습니다!</p>
						<span class="time_date">- 운영자 -</span>
					</div>
					<hr>
				</div>
			</c:if>
			
			<c:if test="${chatResult == 'messageReceive'}">
				<div class="received_msg">
					<div class="received_withd_msg">
						<p>쪽지 전송을 완료했습니다!</p>
						<span class="time_date">- 운영자 -</span>
					</div>
					<hr>
				</div>
			</c:if>
		</div>
	</div>
</div>