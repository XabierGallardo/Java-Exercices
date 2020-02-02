package academiccentre;

public class Student {
	private String record;
	private String name;

	public Student (String record, String name) {
		this.record = record;
		this.name = name;
	}

	public String getRecord () {
		return record;
	}

	public void setRecord (String record) {
		this.record = record;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student " + name + ", has record: " + record;
	}
}
