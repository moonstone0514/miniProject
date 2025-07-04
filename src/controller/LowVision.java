package controller;

import java.util.*;

import model.domain.Person;


public class LowVision {
	public static void assignLowVisionPairsWithMbti(Person[][] seat, List<Person> lowVisionList) {
	    Random rand = new Random();
	    int row = 0;
	    
	    while (lowVisionList.size() > 2 && row < 4) {
	        Collections.shuffle(lowVisionList, rand);

	        Person p1 = lowVisionList.get(0);
	        Person p2 = lowVisionList.get(1);
	        
	        if (MbtiCheck.isMatched(p1.getMbti(), p2.getMbti())) {
	        	seat[row][0] = p1;
                seat[row][1] = p2;
                
                lowVisionList.remove(0);
                lowVisionList.remove(1);
                
                row++;
	        }}
	 
	    seat[row][0] = lowVisionList.get(0);
	    if(!lowVisionList.isEmpty()) {
	    	seat[row][1] = lowVisionList.get(0);
	    }
	    

	}
}
