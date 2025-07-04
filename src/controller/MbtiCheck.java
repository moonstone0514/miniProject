package controller;

import java.util.Collections;
import java.util.Map;

import model.Database;

public class MbtiCheck {
	public static boolean isMatched(String a, String b) {
		Database db = new Database();
		
        return db.getMbti().getOrDefault(a, Collections.emptyList()).contains(b);
    }
}
