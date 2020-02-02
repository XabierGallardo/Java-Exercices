package academiccentre;

public class Subject {
	private String code;
	private String nameSubject;
	private String credits;

	public Subject (String code, String nameSubject, String credits) {
		this.code = code;
		this.nameSubject = nameSubject;
		this.credits = credits;
	}

	public String getCode () {
		return code;
	}

	public void setCode (String code) {
		this.code = code;
	}

	public String getNameSubject () {
                return nameSubject;
        }

        public void setNameSubject (String nameSubject) {
                this.nameSubject = nameSubject;
        }

	public String getCredits () {
                return credits;
        }

        public void setCredits (String credits) {
                this.credits = credits;
        }
}

	
