<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="imagetoolbar" content="no">
<meta name="description" content="">
<meta name="keywords" content="">
<title>MyPage画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<script type="text/javascript">

	function allDelete() {

	}
	function chooseDelete() {

	}
	function deleteAll(action) {
	       document.forms[0].action = action;
	       document.forms[0].submit();
	       var deleteFlg = 1;
	       return false;
	}
	function deleteChoose(action) {
	       document.forms[0].action = action;
	       document.forms[0].submit();
	       var deleteFlg = 2;
	       return false;
	}
</script>
</head>
<body>
	<div id="header">
		<div id="pr"></div>
	</div>
	<div id="main">
		<div id="top">
			<p>MyPage</p>
		</div>
		<div>
			<s:if test="myPageList == null">
				<h3>ご購入情報はありません。</h3>
			</s:if>
			<s:elseif test="message == null">
				<h3>ご購入情報は以下になります。</h3>
				<s:form action="MyPageAction" theme="simple">
				<table border="1">
					<tr>
						<th></th>
						<th>ID</th>
						<th>商品名</th>
						<th>値段</th>
						<th>購入個数</th>
						<th>支払い方法</th>
						<th>購入日</th>
					</tr>
					<s:iterator value="myPageList">
						<tr>
 							<td><input type="checkbox" name="checkList" value="${id}" ></td>
							<td><s:property value="id" /><s:hidden name="id" value="%{id}" />
							<td><s:property value="itemName" /></td>
							<td><s:property value="totalPrice" /><span>円</span></td>
							<td><s:property value="totalCount" /><span>個</span></td>
							<td><s:property value="payment" /></td>
							<td><s:property value="insert_date" /></td>
						</tr>
					</s:iterator>
				</table>
				<!-- javascriptで全削除、選択削除の２つのボタンを用意。バリデーションを仕込む -->

				<s:url var="MyPageAction_url" action="MyPageAction">
				<!-- <input type="button" value="選択したものを削除" onClick="return deleteChoose('${MyPageAction_url}')"> -->
				<button type="submit" name="deleteFlg" value="2">選択したものを削除</button>
				</s:url>
				<s:url var="MyPageAction_url" action="MyPageAction">
				<!-- <input type="button" value="すべて削除" onClick="return deleteAll('${MyPageAction_url}')"> -->
				<button type="submit" name="deleteFlg" value="1">すべて削除</button>
				</s:url>
  				<!--	<input type="hidden" name="deleteFlg" value="1">
					<s:submit value="削除" /> -->
				</s:form>
			</s:elseif>
			<s:if test="message != null">
				<h3><s:property value="message" /></h3>
			</s:if>
			<div id="text-right">
				<p>HOMEへ戻る場合は<a href='<s:url action="GoHomeAction" />'>こちら</a></p>
				<p>ログアウトする場合は<a href='<s:url action="LogoutAction" />'>こちら</a>
			</div>
		</div>
	</div>
	<div id="footer">
		<div id="pr"></div>
	</div>

</body>
</html>