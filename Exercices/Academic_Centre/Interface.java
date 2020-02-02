package academiccentre;

import java.util.Scanner;

public class Interface {

	public static void main(String[] args) {
		
		AcademicCentre academicCentre = new AcademicCentre();
		
		Scanner keyboard = new Scanner (System.in);
		int option;
		
		do {
			System.out.println("____________________________________________");
			System.out.println("Welcome to the Academic Centre" + "\n" + "Select an option: "+ "\n");
			System.out.println("Create Student.....................1");
			System.out.println("Create Subject.....................2");
			System.out.println("Create Register....................3");
			System.out.println("Print student......................4");
			System.out.println("Print all students.................5");
			System.out.println("Add qualification..................6");
			System.out.println("Compare Students...................7");
			System.out.println("Exit...............................8");
			System.out.println("____________________________________________");
			System.out.println("Select a number: ");
			
			option = keyboard.nextInt();
			
			
			switch (option) {
			case 1:
				System.out.println("Insert student record: ");
				String record = keyboard.next();
				System.out.println("Insert student's name: ");
				String name = keyboard.next();
				
				boolean createStudent = academicCentre.createStudent(record, name);
				if (createStudent ) {
					System.out.println("Student created");
				} else {
					System.out.println("Couldn't create student");
				}
				break;
				
			case 2:
				System.out.println("Insert subject code: ");
				String code = keyboard.next();
				System.out.println("Insert subject's name: ");
				String nameSubject = keyboard.next();
				System.out.println("Insert subject's credits: ");
				String credits = keyboard.next();
				
				boolean createSubject = academicCentre.createSubject(code, nameSubject, credits);
				if (createStudent ) {
					System.out.println("Subject created");
				} else {
					System.out.println("Couldn't create subject");
				}
				break;
				
			case 3:
				System.out.println("Insert student record: ");
				String recordRegister = keyboard.next();
				System.out.println("Insert subject code: ");
				String codeSubject = keyboard.next();
				
				boolean register = academicCentre.register(recordRegister, codeSubject);
				if (register ) {
					System.out.println("Registered properly");
				} else {
					System.out.println("Couldn't register");
				}
				break;

			case 4:
				System.out.println("Insert student record: ");
				String recordStudent = keyboard.next();
				academicCenter.printStudent(recordStudent);
				break;
				
			case 5:
				academicCentre.printStudents();
				break;
				
			case 6:
				System.out.println("Insert student record: ");
				String recordQualification = keyboard.next();
				System.out.println("Insert subject code: ");
				String codeQualification = keyboard.next();
				System.out.println("Insert qualification: ");
				String qualification = keyboard.next();
				
				academicCentre.addQualification(recordQualification, codeQualification, qualification);
				break;
			
			case 7:
				System.out.println("Insert student 1 record: ");
				String recordStudent1 = keyboard.next();
				System.out.println("Insert student 2 record: ");
				String recordStudent2 = keyboard.next();
				
				academicCentre.compareStudents(recordStudent1, recordStudent2);
				break;
				
			case 8:
				System.out.println("Exiting succesfully");
				break;
			}
			
		} while (option != 8);
	}

}

