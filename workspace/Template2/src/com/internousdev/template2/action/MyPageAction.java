/**
 *
 */
package com.internousdev.template2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template2.dao.MyPageDAO;
import com.internousdev.template2.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author internousdev
 *
 */
public class MyPageAction extends ActionSupport implements SessionAware {

	public Map<String, Object> session;
	public MyPageDAO myPageDAO = new MyPageDAO();
	public ArrayList<MyPageDTO> myPageList = new ArrayList<MyPageDTO>();

	private String id;
	private List<String> checkList = new ArrayList<>();
	private String deleteFlg;
	private String message;

	public String execute() throws SQLException {

		// 元は buyItemDTOのidをLoginAction内のsession.putで追加していた。

		if (!session.containsKey("buyItemDTOList")) {
			return ERROR;
		}

		if(deleteFlg == null) {
//			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();

				myPageList = myPageDAO.getMyPageUserInfo(user_master_id);

				Iterator<MyPageDTO> iterator = myPageList.iterator();
				if (!(iterator.hasNext())) {
					myPageList = null;

			}
		} else if (deleteFlg.equals("1")){
			delete();
		} else if (deleteFlg.equals("2")) {
			deleteChoose();
		}

		String result = SUCCESS;
		return result;
	}

	public void delete() throws SQLException {

//		int item_transaction_id = buyItemDTOList.get(0).getId();
		String user_master_id = session.get("login_user_id").toString();

		int res = myPageDAO.buyItemHistoryDelete(user_master_id);

		if (res > 0) {
			myPageList = null;
			setMessage("商品情報を正しく削除しました。");
		} else if (res == 0) {
			setMessage("商品情報の削除に失敗しました。");
		}
	}

	public void deleteChoose() throws SQLException {

//		List<String> idList = Arrays.asList(id.split(", "));
		MyPageDAO myPageDeleteDAO = new MyPageDAO();
		int res = 0;

//		for (int i = 0; i < checkList.size(); i++) {
//			String checkId = checkList.get(0);

			res = myPageDeleteDAO.buyItemChooseDelete(checkList);
//		}
		if (res > 0) {
			String user_master_id = session.get("login_user_id").toString();

			myPageList = myPageDAO.getMyPageUserInfo(user_master_id);

//			myPageList = null;
			setMessage(res + "件削除しました。");

			Iterator<MyPageDTO> iterator = myPageList.iterator();
			if (!(iterator.hasNext())) {
				myPageList = null;
			}
		} else if (res == 0) {
			setMessage("商品情報の削除に失敗しました。");
		}

	}

	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public String getmessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getCheckList() {
		return checkList;
	}
	public void setCheckList(List<String> checkList) {
		this.checkList = checkList;
	}

}
