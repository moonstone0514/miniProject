package controller;

import java.util.*;
import model.domain.Person;

public class SeatController {
	
	//두명 미리 뽑아서 다른 배열에 넣기
	
	public static Person[][] createSeatArray() {
        return new Person[7][4];
    }
	
	public static String[] pickBestPairFromGoodVision(List<Person> goodVisionList) {
	    Collections.shuffle(goodVisionList, new Random());
	    
	    String[] lastSeat = new String[2];
	    
	    Person p1 = goodVisionList.get(0);
        Person p2 = goodVisionList.get(1);
        if (MbtiCheck.isMatched(p1.getMbti(), p2.getMbti())) {
        	lastSeat[0] = p1.getName();
        	lastSeat[1] = p2.getName();
        }
        
        goodVisionList.remove(0);
        goodVisionList.remove(1);
        
	    return lastSeat;
	}
	
	public static void assignSeatWithMbti(Person[][] seat, List<Person> remainingList) {
	    Random rand = new Random();
	    int rows = seat.length;   // 7
	    int cols = seat[0].length; // 4

	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            if ((i <= 3) && (j <= 1)) continue;

	            if (j == 0) {
	                Collections.shuffle(remainingList, rand);
	                seat[i][j] = remainingList.remove(0);
	            } else {
	                Person left = seat[i][j-1];
	                Collections.shuffle(remainingList, rand);
	                Person matched = null;
	                for (Person candidate : remainingList) {
	                    if (MbtiCheck.isMatched(left.getMbti(), candidate.getMbti())) {
	                        matched = candidate;
	                        break;
	                    }
	                }
	                if (matched != null) {
	                    seat[i][j] = matched;
	                    remainingList.remove(matched);
	                } else {
	                    seat[i][j] = remainingList.remove(0);
	                }
	            }
	        }
	    }
	}

}
