package view;

import java.util.Scanner;
import controller.SeatController;

public class StartView {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("\n===== 학생 좌석 배치 시스템 =====");
			System.out.println("1. 신입생 추가");
			System.out.println("2. 학생 정보 수정");
			System.out.println("3. 학생 삭제");
			System.out.println("4. 전체 학생 조회");
			System.out.println("5. 시력 저하 학생 조회");
			System.out.println("6. 좌석 배치 결과 출력");
			System.out.println("0. 종료");
			System.out.print("메뉴 선택: ");

			String stringChoice = sc.nextLine();
			
			int choice = 0;
			if (stringChoice.isEmpty()) {
				FailView.print("공백을 입력하셨습니다.");
				continue;
			} else {
				choice = Integer.parseInt(stringChoice);
			}

			switch (choice) {
				case 1 -> SeatController.insertStudent(sc);
				case 2 -> SeatController.updateStudent(sc);
				case 3 -> SeatController.deleteStudent(sc);
				case 4 -> SeatController.printAllStudents();
				case 5 -> SeatController.printLowVisionStudents();
				case 6 -> SeatController.printSeatArrangement();
				case 0 -> running = false;
				default -> System.out.println("⚠️ 잘못된 입력입니다.");
			}
		}
		sc.close();
		System.out.println("프로그램을 종료합니다.");
	}
}
