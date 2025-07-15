package model.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Mbtiload {

	public static final Map<String, List<String>> mbtiMap = new HashMap<>();

	static {
		String[] tables = { "infp", "enfp", "infj", "enfj", "intj", "entj", "intp", "entp", "isfp", "esfp", "istp",
				"estp", "isfj", "esfj", "istj", "estj" };

		try (Connection conn = DBUtil.getConnection()) {
			for (String tableName : tables) {

				List<String> matchedList = new ArrayList<>();

				String sql = "SELECT matched_mbti FROM " + tableName;

				try (Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sql)) {

					while (rs.next()) {
						matchedList.add(rs.getString("matched_mbti"));
					}
				}

				mbtiMap.put(tableName, matchedList);
			}
			System.out.println("[MBTI 매칭 정보 로딩 완료]");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, List<String>> getMbtiMap() {
		return mbtiMap;
	}

	public static boolean isCompatible(String prevMbti, String nextMbti) {
		return mbtiMap.getOrDefault(prevMbti, Collections.emptyList())
				.contains(nextMbti);
	}
}
