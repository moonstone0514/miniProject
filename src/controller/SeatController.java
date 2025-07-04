package controller;

import java.util.*;

import model.domain.Person;

public class SeatController {
	
	//두명 미리 뽑아서 다른 배열에 넣기
	
	public static int getIndex(Person p, List<Person> list) {
		int i=0;
		for(Person tmp : list){
			if(tmp.getName() == p.getName()) {
				break;
			}else {
				i++;
			}
		}
		return i;
		
	}
	
	public static Person[][] createSeatArray() {
        return new Person[7][4];
    }
	
	public static String[] pickBestPairFromGoodVision(List<Person> goodVisionList) {
		 String[] lastSeat = new String[2];
		 

		while(true){
			Collections.shuffle(goodVisionList, new Random());
		    
		   
		    
		    Person p1 = goodVisionList.get(0);
	        Person p2 = goodVisionList.get(1);
	        
	        if (MbtiCheck.isMatched(p1.getMbti(), p2.getMbti())) {
	        	lastSeat[0] = p1.getName();
	        	lastSeat[1] = p2.getName();
	        	System.out.println(goodVisionList.size());
	        	break;
	        	
	        }
		}
		
        goodVisionList.remove(0);
        goodVisionList.remove(0);
        
	    return lastSeat;
	}
	
	public static void assignSeatWithMbti(Person[][] seat, List<Person> remainingList) {
        System.out.println(remainingList.size()+"22");

	    Random rand = new Random();
	    int rows = seat.length;   // 7
	    int cols = seat[0].length; // 4

	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            if ((i <= 3) && (j <= 1)) continue;

	            if (j == 0) {
	                Collections.shuffle(remainingList, rand);
	                seat[i][j] = remainingList.get(0);
	                System.out.println(remainingList.size()+"dadfadf");
	                remainingList.remove(0);
	            } else {
	                Person left = seat[i][j-1];
	                Collections.shuffle(remainingList, rand);

	                Person matched = null;
	                int tmp = 0;
	                for (int index = 0 ; index < remainingList.size() ; index++) {
	                	Person candidate = remainingList.get(index);
	                    if (MbtiCheck.isMatched(left.getMbti(), candidate.getMbti())) {
	                        matched = candidate;
	                        tmp = index;
	                        break;
	                    }
	                }
	                if (matched != null) {
	                    seat[i][j] = matched;
	                    int matchedIndex = getIndex(matched, remainingList);
	                    remainingList.remove(matchedIndex);
	                } else {
	                	System.out.println(remainingList.size());
	                    seat[i][j] = remainingList.get(0);
	                    remainingList.remove(0);
	                }
	            }
	        }
	    }
	}

}
