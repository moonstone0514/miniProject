package model;

import model.domain.Person;
import model.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class ModelDAO {

	private static ModelDAO model = new ModelDAO();

	private ModelDAO() {}

	public static ModelDAO getModel() {
		return model;
	}

	public static ArrayList<Person> getStudent() throws Exception {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Person> all = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM student");

			while (rs.next()) {
				all.add(new Person(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("mbti"),
					rs.getBoolean("isLowVision")
				));
			}
		} finally {
			DBUtil.close(conn, stmt, rs);
		}

		return all;
	}

	public boolean insertStudent(Person person) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO student (name, mbti, isLowVision) VALUES (?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setString(2, person.getMbti());
			pstmt.setBoolean(3, person.isLowVision());

			int result = pstmt.executeUpdate();
			return result == 1;
			
		} finally {
			DBUtil.close(conn, pstmt);
		}
	}

	public boolean updateIsLowVisionStudent(int id, boolean isLowVision) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE student SET isLowVision = ? WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, isLowVision);
			pstmt.setInt(2, id);

			int result = pstmt.executeUpdate();
			return result == 1;
		} finally {
			DBUtil.close(conn, pstmt);
		}
	}

	public ArrayList<Person> getLowVision(boolean isLowVision) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Person> list = null;

		try {
			list = new ArrayList<>();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM student WHERE isLowVision = ?");
			
			pstmt.setBoolean(1, isLowVision);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Person(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("mbti"),
					rs.getBoolean("isLowVision")
				));
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		return list;
	}

	public boolean updateMbtiStudent(int id, String mbti) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("UPDATE student SET mbti = ? WHERE id = ?");
			pstmt.setString(1, mbti);
			pstmt.setInt(2, id);

			int result = pstmt.executeUpdate();
			return result == 1;
			
		} finally {
			DBUtil.close(conn, pstmt);
		}
		
	}

	public static boolean deleteStudent(int id) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM student WHERE id = ?");
			pstmt.setInt(1, id);

			int result = pstmt.executeUpdate();
			return result == 1;
			
		} finally {
			DBUtil.close(conn, pstmt);
		}
	}

	public static ArrayList<String> getGoodMbti(String mbti) throws Exception {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> goodMbti = null;

		try {
			goodMbti = new ArrayList<>();
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT matched_mbti FROM " + mbti.toLowerCase();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				goodMbti.add(rs.getString("matched_mbti"));
			}
		} finally {
			DBUtil.close(conn, stmt, rs);
		}

		return goodMbti;
	}
}
