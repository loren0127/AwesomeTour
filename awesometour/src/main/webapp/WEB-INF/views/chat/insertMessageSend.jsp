<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
function buttonSubmit() {
	document.messageSendForm.submit();
}
</script>
<div class="container">
	<div class="mesgs" id="mesgs">
		<div class="msg_history" id="chatMessageArea">
			<div class="form-group">
				<form:form id="messageSendForm" commandName="messageCommand" action="insertMessageSend.do">
					<ul>
						<li style="padding-bottom:20px;">
							<label for="message_title">제목</label>
							<form:input class="form-control form-control-sm" path="message_title"/>
							<%-- <form:errors path="message_title" cssClass="error-color" /> --%>
						</li>
						<li style="padding-bottom:20px;">
							<label for="message_sender">받는이</label>
							<div class="input-group mb-3">
							<form:input class="form-control form-control-sm" path="message_sender"/>
								<div class="input-group-append">
									<button class="btn btn-success btn-sm" type="submit">확인</button>
								</div>
							</div>
							<%-- <form:errors path="message_receiver" cssClass="error-color" /> --%>
						</li>
						<li style="padding-bottom:20px;">
							<label for="message_content">쪽지 내용</label>
							<form:textarea style="height:150px;" class="form-control form-control-sm" path="message_content"/>
						</li>
					</ul>
					<hr>
					<ul>
						<li>
							개인채팅 신청
						</li>
					</ul>
					<hr>
					<button class="btn btn-link" onclick="buttonSubmit();">보내기</button>
				</form:form>
			</div>
		</div>
	</div>
</div>