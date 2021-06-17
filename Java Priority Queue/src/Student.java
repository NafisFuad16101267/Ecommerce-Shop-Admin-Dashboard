class Student {
	private int token;
	private String fname;
	private double cgpa;

	public Student(int id, String fname, double cgpa) {
		this.token = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}

	public int getToken() {
		return token;
	}

	public String getName() {
		return fname;
	}

	public double getCgpa() {
		return cgpa;
	}
}
