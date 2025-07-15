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

    public static ArrayList<Person> getStudent() {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return all;
    }

    public static boolean insertStudent(Person person) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();

            // id는 생략하고 name, mbti, isLowVision만 삽입
            String sql = "INSERT INTO student (name, mbti, isLowVision) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getMbti());
            pstmt.setBoolean(3, person.isLowVision());

            int result = pstmt.executeUpdate();
            if(result == 1) {
            	return true;
            }      
            
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 출력 유지
            throw e;
        } finally {
            DBUtil.close(conn, pstmt);
        }
        
        return false;
    }

    public static boolean updateIsLowVisionStudent(int id, boolean isLowVision) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE student SET isLowVision = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, isLowVision);
            pstmt.setInt(2, id);

            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return false;
    }

    public static ArrayList<Person> getLowVision(boolean isLowVision) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Person> list = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM student WHERE isLowVision = ?");
            pstmt.setBoolean(1, isLowVision);
            rs = pstmt.executeQuery(); // ✅ 빠졌던 실행문 추가

            while (rs.next()) {
                list.add(new Person(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("mbti"),
                    rs.getBoolean("isLowVision")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return list;
    }

    public static boolean updateMbtiStudent(int id, String mbti) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("UPDATE student SET mbti = ? WHERE id = ?");
            pstmt.setString(1, mbti);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() == 1; // ✅ 실행문 추가
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        return false;
    }

    public static boolean deleteStudent(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("DELETE FROM student WHERE id = ?");
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() == 1; // ✅ 실행문 추가
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }
        
        return false;
    }

    public static ArrayList<String> getGoodMbti(String mbti) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> goodMbti = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            // ✅ PreparedStatement에서 ?로 테이블 이름 바인딩 불가능 → 문자열 직접 조합
            String sql = "SELECT matched_mbti FROM " + mbti.toLowerCase();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                goodMbti.add(rs.getString("matched_mbti"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return goodMbti;
    }
}
