package model;

import java.util.LinkedList;
import java.util.List;

import model.domain.Person;

public class Model {
	private static Database db = new Database();

	private static Model model = new Model();

	public static Model getModel() {
		return model;
	}

	public static Object getVision() {
		return true;
	}

	
	public static List<Person> getVision(boolean o) {
		Person [] student = db.getStudents();
		List<Person> lowVisionList = new LinkedList<>();
		List<Person> highVisionLilst = new LinkedList<>();
		
		for (Person p : student) {
			if (p.isLowVision() == o) {
				lowVisionList.add(p);
			}else {
				highVisionLilst.add(p);
			}
			
		}
		
		if(o) {
			return lowVisionList;
		}else {
			return highVisionLilst;
		}
		
	}

}
