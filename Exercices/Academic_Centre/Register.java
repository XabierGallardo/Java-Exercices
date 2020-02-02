package academiccentre;

public class Register {
	private String record;
	private String code;
	private double qualification;

	//We'll create two constructors, because for Register we don't need qualification yet
	public Register (String record, String code) {
		this.record = record;
		this.code = code;
	}

	public Register (String record, String code, double qualification) {
                this.record = record;
                this.code = code;
		this.qualification = qualification;
        }

	public String getRecord () {
		return record;
	}

	public void setRecord (String record) {
		this.record = record;
	}

        public String getCode () {
                return code;
        }

        public void setCode (String code) {
                this.code = code;
        }

        public double getQualification () {
                return qualification;
        }

        public void setQualification (double qualification) {
                this.qualification = qualification;
        }

	@Override
	public String toString() {
		return "Record: " + record + ", code: " + code + ", qualification: " + qualification;
	}
}

