<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-12 col-md-6">
	<div class="mesgs" id="mesgs" style="width: 100%;">
		<div class="msg_history" id="chatMessageArea">
			<div class="form-group">
				<form:form id="messageSendForm" commandName="messageCommand" action="insertMessageSend.do">
							<label for="message_title">제목</label>
							<form:input class="form-control form-control-sm" path="message_title"/>
							<%-- <form:errors path="message_title" cssClass="error-color" /> --%>
							<label for="message_receiver">받는이</label>
							<div class="input-group mb-3">
								<form:input class="form-control form-control-sm" id="message_sender" path="message_receiver"/>
								<div class="input-group-append">
									<button class="btn btn-success btn-sm" id="checkedEmail" onclick="return false;">이메일 확인</button>
								</div>
							</div>
							<span id="receiverCheckedMessage"></span>
							<%-- <form:errors path="message_receiver" cssClass="error-color" /> --%>
							
					<hr>
					
							<label for="message_content">내용</label>
							<form:textarea class="form-control form-control-sm" path="message_content"/>
							<%-- <form:errors path="message_title" cssClass="error-color" /> --%>
					<hr>
					<button class="btn btn-link" id="messageSendBtn" type="submit">보내기</button>
				</form:form>
			</div>
		</div>
	</div>
</div> 