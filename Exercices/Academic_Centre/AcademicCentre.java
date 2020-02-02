package academiccentre;

import java.util.HashMap;

public class AcademicCentre {
	
	//Adding HashMap lists for Student, Subject and Register
	static HashMap<String, Student> studentList = new HashMap<String, Student>();
	static HashMap<String, Subject> subjectList = new HashMap<String, Subject>();
	static HashMap<String, Register> registerList = new HashMap<String, Register>();
	
	//Student creation method, checking if there's no repetitions
	public boolean createStudent (String record, String name) {
		if (studentList.isEmpty() || studentList.containsKey(record)) { 
			Student student = new Student(record, name);
			studentList.put(record, student); //record as primary key
			return true;
		} else {
			return false;
		}
	}
	
	//Subject creation method, checking if there's no repetitions
	public boolean createSubject(String code, String nameSubject, String credits) {
		if (subjectList.isEmpty() || subjectList.containsKey(code)) { 
			Subject subject = new Subject(code, nameSubject, credits);
			subjectList.put(code, subject);
			return true;
		} else {
			return false;
		}
	}
	
	//Print student method
	public void printStudent (String record) {
		if (studentList.isEmpty() || studentList.containsKey(record)) {
			System.out.println("This student doesn't appear on the list");
		} else {
			Student student = AcademicCentre.studentList.get(record);
			System.out.println(student);
			
			//Iterate over register to get subject and qualification
			for (Register registerStudent : registerList.values()) {
				if(registerStudent.equals(record)) {
					System.out.println("Code: " + registerStudent.getCode() + ", qualification: " + registerStudent.getQualification()); 
				}
			}
		}
	}
	
	//Print all students method
	public void printStudents () {
		if (studentList.isEmpty()) {
			System.out.println("Empty list");
		} else {
		for (Student student : studentList.values()) {
			System.out.println(student);
			
			for (Register registerStudent : registerList.values()) {
					
				//Check if the student is already registered
				if (registerStudent.getRecord().contentEquals(student.getRecord())) {
					System.out.println("Code: " + registerStudent.getCode() + ", qualification: " + registerStudent.getQualification());
					}
				}
			}
	}
}


