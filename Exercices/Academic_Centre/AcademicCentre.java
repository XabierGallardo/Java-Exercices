package academiccentre;

import java.util.HashMap;

public class AcademicCentre {
	
	//Adding HashMap lists for Student, Subject and Register
	static HashMap<String, Student> studentList = new HashMap<String, Student>();
	static HashMap<String, Subject> subjectList = new HashMap<String, Subject>();
	static HashMap<String, Register> registerList = new HashMap<String, Register>();
	
	public boolean createStudent (String record, String name) {
		if (studentList.isEmpty() || studentList.containsKey(record)) {
			Student student = new Student(record, name);
			studentList.put(record, student);
			return true;
		} else {
			return false;
		}
	}
}

