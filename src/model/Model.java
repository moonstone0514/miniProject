package model;

import java.util.LinkedList;
import java.util.List;
import model.domain.Person;

public class Model {
	

	private static Model model = new Model();
	
	private Model (){};

	public static Model getModel() {
		return model;
	}

	public static List[] getVision() {
		Person[] students = Database.getStudents();
		List[] visionList = new List[2];

		List<Person> lowVisionList = new LinkedList<>();
		List<Person> highVisionList = new LinkedList<>();

		for (Person p : students) {
			if (p.isHasLowVision()) {
				lowVisionList.add(p);
			} else {
				highVisionList.add(p);
			}
		}
		visionList[0] = lowVisionList;
		visionList[1] = highVisionList;

		return visionList;
	}

}
