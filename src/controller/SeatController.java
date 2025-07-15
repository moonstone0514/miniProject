package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import model.ModelDAO;
import model.domain.Person;
import model.util.Mbtiload;
import view.FailView;
import view.SuccessView;

public class SeatController {
	private static final ModelDAO model = ModelDAO.getModel();

	public static Person[][] assignSeatWithMbti() {
		Person[][] seat = new Person[8][4];

		try {
			List<Person> lowVisionList = model.getLowVision(true);
			List<Person> highVisionList = model.getLowVision(false);

			Collections.shuffle(lowVisionList, new Random());
			Collections.shuffle(highVisionList, new Random());

			for (int i = 0; i < seat.length; i++) {
				for (int j = 0; j < seat[i].length; j++) {

					if (lowVisionList.isEmpty() && highVisionList.isEmpty()) {

						return seat; // 모든 학생 배정 완료

					}
					if (j == 0 && !highVisionList.isEmpty()) {

						seat[i][j] = highVisionList.remove(0);
						continue;

					}

					// lowVisionList가 있는 경우 시력 안 좋은 사람 배치
					if (!lowVisionList.isEmpty()) {

						seat[i][j] = assignLowVision(seat[i][j - 1], lowVisionList);

					} else if (!highVisionList.isEmpty()) {

						// lowVisionList가 비어있는 경우 시력 좋은 사람 배치
						seat[i][j] = assignHighVision(seat[i][j - 1], highVisionList);
					}
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
			FailView.print("오류 : 저시력자 로드 실패");

		} catch (Exception e) {

			e.printStackTrace();
			FailView.print("오류 : 알 수 없는 오류" + e.getMessage());

		}

		return seat;
	}

	public static Person assignHighVision(Person prev, List<Person> highVisionList) {
		int size = highVisionList.size();

		for (int i = 0; i < size; i++) {
			if (checkMbti(prev.getMbti(), highVisionList.get(i).getMbti())) {
				return highVisionList.remove(i);
			}
		}

		return highVisionList.remove(0); // 매칭 없으면 그냥 아무나 배정
	}

	public static Person assignLowVision(Person prev, List<Person> lowVisionList) {
		for (int i = 0; i < lowVisionList.size(); i++) {

			if (checkMbti(prev.getMbti(), lowVisionList.get(i).getMbti())) {

				return lowVisionList.remove(i);

			}

		}

		return lowVisionList.remove(0); // 매칭 없으면 그냥 아무나 배정

	}

	public static boolean checkMbti(String preMbti, String mbti) {

		return Mbtiload.getMbtiMap().getOrDefault(preMbti, Collections.emptyList()).contains(mbti);

	}


	public static void insertStudent(Scanner sc) {
		
		System.out.print("이름: ");
		String name = sc.nextLine().trim();
		
		try {
			if (name.isEmpty()) {
				
				FailView.print("[ERROR] 이름은 공백일 수 없습니다. 다시 입력해주세요.");
				return;
				
			}
			
			System.out.print("MBTI: ");
			String mbti = sc.nextLine().trim().toLowerCase();
			
			if (mbti.isEmpty()) {				
				System.out.println("[ERROR] MBTI는 공백일 수 없습니다. 다시 입력해주세요.");
				return;
				
			}
			if (!Mbtiload.mbtiMap.containsKey(mbti.toUpperCase())) {
				System.out.println("[ERROR] 유효하지 않은 MBTI입니다. 다시 입력해주세요.");
				return;
			}
			
			System.out.print("저시력 여부 (true/false): ");
			boolean isLowVision;

			if (!sc.hasNextBoolean()) { 				
				System.out.println("[ERROR] true 또는 false로 입력해 주세요.");
				sc.nextLine(); // 잘못된 입력 제거
				return;
				
			}
			
			isLowVision = sc.nextBoolean();

			sc.nextLine();

			boolean result = model.insertStudent(Person.builder().name(name).mbti(mbti).isLowVision(isLowVision).build());
			System.out.println("[INSERT] 결과: " + result);
			
		} catch (SQLException e) {
			
			FailView.print("[ERROR] 데이터 삽입 중 문제가 발생했습니다.");
			e.printStackTrace();
			
		} catch (InputMismatchException e) {
			
			FailView.print("[ERROR] true 또는 false로 입력해 주세요.");
			e.printStackTrace();
			
		} catch (Exception e) {
			
			FailView.print("[ERROR] 알 수 없는 오류가 발생했습니다.");
			e.printStackTrace();
		}

	}

	public static void updateStudent(Scanner sc) {
		try {
			System.out.print("수정할 학생 ID: ");
			String idString = sc.nextLine();

			if (idString.isEmpty()) {
				throw new NoSuchElementException();
			}
			
			int id = Integer.parseInt(idString);

			System.out.println("1. 시력 정보 수정");
			System.out.println("2. MBTI 수정");
			System.out.print("선택: ");
			
			String stringOption = sc.nextLine();
			
			if (stringOption.isEmpty()) {
				throw new NoSuchElementException();
			}

			int option = Integer.parseInt(stringOption);

			if (option == 1) {

				System.out.print("새 시력 상태 (true/false): ");
				boolean isLowVision = sc.nextBoolean();
				//sc.nextLine(); // 혹시 남아 있을 개행 제거

				boolean result = model.updateIsLowVisionStudent(id, isLowVision);
				SuccessView.print("[UPDATE] 시력 정보 수정 결과: " + result);

			} else if (option == 2) {
				
				System.out.print("새 MBTI: ");
				String mbti = sc.nextLine();

				if (!Mbtiload.mbtiMap.containsKey(mbti.toUpperCase())) {
					FailView.print("[ERROR] 유효하지 않은 MBTI입니다. 다시 입력해주세요.");
					return;
				}
				
				if (mbti.isEmpty()) {
					throw new NoSuchElementException();
				}

				SuccessView.print("[UPDATE] MBTI 수정 결과: " + model.updateMbtiStudent(id, mbti));
				
			} else {
				FailView.print("⚠️ 잘못된 선택입니다.");
			}

		} catch (InputMismatchException e) {
			
			System.out.println("⚠️ 입력 형식이 올바르지 않습니다. 숫자나 true/false만 입력해주세요.");
			FailView.print(e.getMessage());
			
		} catch (NoSuchElementException e) {
			
			System.out.println("⚠️ 공백을 입력하셨습니다.");
			FailView.print(e.getMessage());
			
		} catch (Exception e) {
			
			System.out.println("⚠️ 알 수 없는 오류가 발생했습니다: " + e.getMessage());
			FailView.print(e.getMessage());
			
		}
	}

	public static void deleteStudent(Scanner sc) {
		
		System.out.print("삭제할 학생 ID: ");
		String id = sc.nextLine().trim();
		
		try {
			if (id.isEmpty()) {
				FailView.print("[ERROR] 이름은 공백일 수 없습니다. 다시 입력해주세요.");
				return;
			}
			
			int newId = Integer.parseInt(id);
			boolean result = model.deleteStudent(newId);
			
			if (result == true) {
				SuccessView.print(id + "번 학생이 성공적으로 삭제되었습니다!");
			} else {
				FailView.print("[ERROR] " + id + "번 학생은 존재하지 않습니다!");
			}
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			FailView.print("[ERROR] 숫자를 입력해주세요!");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			FailView.print("[ERROR] 알 수 없는 에러가 발생했습니다!");
		}
	}

	public static void printAllStudents() {
		
		System.out.println("[전체 학생 목록]");
		
		ArrayList<Person> all;
		
		try {
			all = model.getStudent();
			for (Person p : all) {
				System.out.printf("%d | %s | %s | %s\n", p.getId(), p.getName(), p.getMbti().toUpperCase(),
						p.isLowVision() ? "저시력" : "정상");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FailView.print(e.getMessage());
		}
	}

	public static void printLowVisionStudents() {
		
		System.out.println("[시력 저하 학생 목록]");
		ArrayList<Person> list;
		
		try {
			list = model.getLowVision(true);
			for (Person p : list) {
				System.out.printf("%d | %s | %s\n", p.getId(), p.getName(), p.getMbti().toUpperCase());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			FailView.print(e.getMessage());
		}
	}

	public static void printSeatArrangement() {
		
		Person[][] seat = SeatController.assignSeatWithMbti();
		
		System.out.println("\n===== 좌석 배정 결과 =====");
		for (int row = 1; row < seat.length; row += 2) {
			
			for (int col = 0; col < seat[row].length; col++) {
				printPerson(seat[row][col]);
			}
			
			int prevRow = row - 1;
			for (int col = seat[prevRow].length - 1; col >= 0; col--) {
				printPerson(seat[prevRow][col]);
			}
			
			System.out.println();
		}
	}

	private static void printPerson(Person p) {
		
		if (p == null) {
			System.out.print("[빈자리] ");
		} else {
			System.out.print("[" + p.getName() + "] ");
		}
	}
}
