import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;


public class JavaSort {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		
		List<Student> studentList = new ArrayList<Student>();
		while(testCases>0){
			int id = in.nextInt();
			String fname = in.next();
			double cgpa = in.nextDouble();
			
			Student st = new Student(id, fname, cgpa);
			studentList.add(st);
			
			testCases--;
		}
		for(int i = 0; i<studentList.size() ; i++) {
			for(int j = i ; j<studentList.size(); j++) {
				Student student1 = studentList.get(i);
				Student student2 = studentList.get(j);
				if(student1.getCgpa() < student2.getCgpa()) {
					swap(student1,student2);
				} else if(student1.getCgpa() == student2.getCgpa()) {
					String student1Name = student1.getFname();
					String student2Name = student2.getFname();
					int compare = student1Name.compareTo(student2Name);
					//System.out.println(compare);
					if(compare > 0) {
						swap(student1,student2);
					}
					else if(compare == 0) {
						if(student1.getId() > student2.getId()) {
							swap(student1,student2);
						}
					}
				}
			}
		}
		
      	for(Student st: studentList){
			System.out.println(st.getFname());
		}
	}
	public static void swap(Student student1, Student student2) {
		int tempid = student1.getId();
		String tempfname = student1.getFname();
		double tempcgpa = student1.getCgpa();
		student1.setId(student2.getId());
		student1.setFname(student2.getFname());
		student1.setCgpa(student2.getCgpa());
		student2.setId(tempid);
		student2.setFname(tempfname);
		student2.setCgpa(tempcgpa);
	}
}
