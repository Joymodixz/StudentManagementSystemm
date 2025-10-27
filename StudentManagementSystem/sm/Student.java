package sm;
import java.io.*;
import java.util.*;

class Student implements Serializable {
	private int studentId;
	private String studentName;
	private int studentAge;
	private String studentCourse;

	// Constructor for direct assignment
	public Student(int studentId, String studentName, int studentAge, String studentCourse) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentAge = studentAge;
		this.studentCourse = studentCourse;
	}

	// Constructor to take input from user
	public Student(Scanner scanner) {
		while (true) {
			try {
				System.out.print("Enter Student ID: ");
				this.studentId = Integer.valueOf(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid Student ID. Please enter a valid integer.");
			}
		}
		while (true) {
			System.out.print("Enter Student Name: ");
			String inputStudentName = scanner.nextLine();
			// Check if name is not empty and not a number
			if (inputStudentName.trim().isEmpty()) {
				System.out.println("Student name cannot be empty. Please enter a valid name.");
				continue;
			}
			try {
				Double.parseDouble(inputStudentName);
				System.out.println("Student name cannot be a number. Please enter a valid name.");
			} catch (NumberFormatException e) {
				this.studentName = inputStudentName;
				break;
			}
		}
		while (true) {
			try {
				System.out.print("Enter Student Age: ");
				int inputStudentAge = Integer.valueOf(scanner.nextLine());
				if (inputStudentAge <= 0) {
					System.out.println("Student age must be greater than 0. Please enter a valid age.");
					continue;
				}
				this.studentAge = inputStudentAge;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid Student Age. Please enter a valid integer.");
			}
		}
		while (true) {
			System.out.print("Enter Student Course: ");
			String inputStudentCourse = scanner.nextLine();
			if (inputStudentCourse.trim().isEmpty()) {
				System.out.println("Student course cannot be empty. Please enter a valid course name.");
				continue;
			}
			try {
				Double.parseDouble(inputStudentCourse);
				System.out.println("Student course cannot be a number. Please enter a valid course name.");
			} catch (NumberFormatException e) {
				this.studentCourse = inputStudentCourse;
				break;
			}
		}
	}

	public int getStudentId() { return studentId; }
	public String getStudentName() { return studentName; }
	public int getStudentAge() { return studentAge; }
	public String getStudentCourse() { return studentCourse; }

	public void setStudentName(String studentName) { this.studentName = studentName; }
	public void setStudentAge(int studentAge) { this.studentAge = studentAge; }
	public void setStudentCourse(String studentCourse) { this.studentCourse = studentCourse; }

	@Override
	public String toString() {
		return String.format("ID: %d, Name: %s, Age: %d, Course: %s", studentId, studentName, studentAge, studentCourse);
	}
}
