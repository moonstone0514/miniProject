//html파일로 변환
package view;

import java.util.List;

import controller.*;
import model.Database;
import model.Model;
import model.domain.Person;


public class StartView {

	public static void main(String[] args) {
		
		Person[][] seat = SeatController.createSeatArray();
		System.out.println("좌석 생성: " + seat[0][0]);//좌석 생성
		
		List[] list = Model.getVision(true);
		
		List<Person> lowvision= list[0];
		List<Person> highvision = list[1];
		 for (Person p : highvision) {
			    System.out.println(p.getName() + " / " + p.getMbti());
			}
		
		
		
		LowVision.assignLowVisionPairsWithMbti(seat, lowvision);
		
		System.out.println("getVision: " + lowvision.get(0).getName());
		System.out.println("좌석 배정: " + seat[0][0].getName());//시력 안 좋은 사람
		
		System.out.println(highvision.size() + "::");
		String[] finals = SeatController.pickBestPairFromGoodVision(highvision);//두 사람
		//System.out.println(SeatController.pickBestPairFromGoodVision(highvision)[0]+SeatController.pickBestPairFromGoodVision(highvision)[1]);
		System.out.println(highvision.size() + ":");
		
		SeatController.assignSeatWithMbti(seat, highvision);
		System.out.println(seat[3][3]);
		
		System.out.println("===== 최종 좌석 배정 결과 =====");
		for (int row = 0; row < seat.length; row++) {
		    System.out.print("Row " + row + ": ");
		    for (int col = 0; col < seat[row].length; col++) {
		        if (seat[row][col] != null) {
		            System.out.print(seat[row][col].getName() + " ");
		        } else {
		            System.out.print("[빈자리] ");
		        }
		    }
		    System.out.println(); // 줄바꿈
		    
		}
		System.out.println("Row 7: " + finals[0] + " " + finals[1]);

		
	}

}





