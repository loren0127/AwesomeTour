<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
function buttonSubmit() {
	document.messageSendForm.submit();
}

</script>
<div class="col-sm-12 col-md-6">
	<div class="mesgs" id="mesgs" style="width: 100%;">
		<div class="msg_history" id="chatMessageArea">
			<div class="form-group">
				<form:form id="messageSendForm" commandName="messageCommand" action="insertMessageSend.do">
					<ul>
						<li style="padding-bottom:10px;">
							<label for="message_title">제목</label>
							<form:input class="form-control form-control-sm" path="message_title"/>
							<%-- <form:errors path="message_title" cssClass="error-color" /> --%>
						</li>
						<li style="padding-bottom:10px;">
							<label for="message_sender">받는이</label>
							<div class="input-group mb-3">
								<form:input class="form-control form-control-sm" path="message_sender"/>
								<div class="input-group-append">
									<button class="btn btn-success btn-sm" type="submit">확인</button>
								</div>
							</div>
							<%-- <form:errors path="message_receiver" cssClass="error-color" /> --%>
						</li>
					</ul>
					
					<hr>
					
					<ul>
						<li style="padding-bottom:10px;">
							<label for="message_type">쪽지 종류</label>
							<form:select path="message_type" class="form-control-sm">
								<form:option value="normal">쪽지</form:option>
								<form:option value="chatInvite">채팅신청</form:option>
								<form:option value="groupInvite">그룹신청</form:option>
							</form:select>
							<%-- <form:errors path="message_title" cssClass="error-color" /> --%>
							
							<form:input class="form-control form-control-sm" path="message_URL" placeholder="초대링크"/>
							<%-- <form:errors path="message_title" cssClass="error-color" /> --%>
						</li>
					</ul>
					
					<hr>
					
					<ul>
						<li style="padding-bottom:10px;">
							<label for="message_content">내용</label>
							<form:textarea class="form-control form-control-sm" path="message_content"/>
							<%-- <form:errors path="message_title" cssClass="error-color" /> --%>
						</li>
					</ul>
					<hr>
					<button class="btn btn-link" onclick="buttonSubmit();">보내기</button>
				</form:form>
			</div>
		</div>
	</div>
</div> 