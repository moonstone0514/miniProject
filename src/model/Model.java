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
	
	public static List<Person>[] getVision(boolean o) {
		Person [] student = db.getStudents();
		List[] visionList = new List[2];
		List<Person> lowVisionList = new LinkedList<>();
		List<Person> highVisionLilst = new LinkedList<>();
		
		for (Person p : student) {
			if (p.isLowVision() == o) {
				lowVisionList.add(p);
			}else {
				highVisionLilst.add(p);
			}
		}
		visionList[0] = lowVisionList;
		visionList[1] = highVisionLilst;

		
		return visionList;
		
	}

}
