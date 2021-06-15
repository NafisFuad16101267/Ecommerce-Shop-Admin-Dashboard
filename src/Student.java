import java.util.*;

public class Student{
	private int id;
	private String fname;
	private double cgpa;
	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
}