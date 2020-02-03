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
	
	//Register student method
	public boolean register (String record, String code) {
		if(studentList.containsKey(record) && subjectList.containsKey(code)) {
			Register register = new Register(record, code);
			registerList.put(record + "-" + code, register);
			return true;
		}
		return false;
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
	
	//Add qualification method
	public boolean addQualification (String record, String code, double qualification) {
		Register registerQualification = registerList.get(record + "-" + code);
		
		if (registerQualification.getCode().equals(code)) {
			registerQualification.setQualification(qualification);
			return true;
		}
		
		return false;
	}
	
	//Student comparison method
	public void studentComparison (String record1, String record2) {
		
		Student student1 = studentList.get(record1);
		Student student2 = studentList.get(record2);
		Double averageQual1;
		Double averageQual2;
		int asignNumStudent1 = 0;
		Double totalQualStudent1 = null;
		
		for (Register registerStudent : registerList.values()) {
			if (registerStudent.getRecord().contentEquals(student1.getRecord())) {
				totalQualStudent1 = +registerStudent.getQualification();
				asignNumStudent1 = +1;
			}
		}
		
		averageQual1 = totalQualStudent1 / asignNumStudent1;
		int asignNumStudent2 = 0;
		Double totalQualStudent2 = null;
		
		for (Register registerStudent : registerList.values()) {
			if (registerStudent.getRecord().contentEquals(student2.getRecord())) {
				totalQualStudent2 = +registerStudent.getQualification();
				asignNumStudent2 = +1;
			}
		}
		
		averageQual2 = totalQualStudent2 / asignNumStudent2;
		
		if (averageQual1 > averageQual2) {
			System.out.println("Student with record: " + record1 + " has superior qualifications than " + record2);
		}else if (averageQual1 < averageQual2) {
			System.out.println("Student with record: " + record2 + " has superior qualifications than " + record1);
	} else {
		System.out.println("Both students have same qualifications");
	}
}
}

