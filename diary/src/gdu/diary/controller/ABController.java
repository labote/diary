package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.ABService;

@WebServlet("/ABController")
public class ABController extends HttpServlet {
	/*
	 * private DBUtil dbUtil; private ADao aDao; private BDao bDao;
	 * 
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // 의존 객체 생성 this.dbUtil =
	 * new DBUtil(); this.aDao = new ADao(); this.bDao = new BDao();
	 * 
	 * Connection conn = null; try { conn = this.dbUtil.getConnection();
	 * conn.setAutoCommit(false); // false -> 둘다 성공할때까지 갖고 있음 aDao.insert(conn); //
	 * insert 메서드 자체에서 예외처리를 했기 때문에 실패하지 않음(try ~ catch) -> 따라서 예외 처리 없앤다(throws)
	 * bDao.insert(conn); // insert 메서드 자체에서 예외처리를 했기 때문에 실패하지 않음 -> 따라서 예외 처리 없앤다.
	 * conn.commit(); } catch(Exception e) { try { conn.rollback(); } catch
	 * (SQLException e1) { e1.printStackTrace(); } e.printStackTrace(); } finally {
	 * dbUtil.close(null, null, conn); } }
	 */
	
	private ABService abService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.abService = new ABService();
		this.abService.insert();
	}
}
