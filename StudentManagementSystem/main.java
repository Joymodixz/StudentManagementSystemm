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

// ...existing code...

public class main {
	private static List<Student> studentList = new ArrayList<>();
	private static final String STUDENT_FILE_NAME = "studentmanagement.txt";

	public static void main(String[] args) {
		try {
			loadFromFile();
		} catch (Exception e) {
			System.out.println("System error loading students: " + e.getMessage());
		}
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				StringBuilder menuBuilder = new StringBuilder();
				menuBuilder.append("\n--- Student Management System ---\n")
					.append("1. Add Student\n")
					.append("2. Update Student\n")
					.append("3. Delete Student\n")
					.append("4. Search Student\n")
					.append("5. View All Students\n")
					.append("6. Exit\n")
					.append("Enter choice: ");
				System.out.print(menuBuilder);
				int userChoice;
				while (true) {
					try {
						userChoice = Integer.valueOf(scanner.nextLine());
						break;
					} catch (NumberFormatException e) {
						System.out.println("Invalid choice. Please enter a valid integer.");
						System.out.print(menuBuilder);
					}
				}
				switch (userChoice) {
					case 1:
						try {
							Student newStudent = new Student(scanner);
							if (searchStudent(newStudent.getStudentId()) != null) {
								System.out.println("Student with this ID already exists.");
								break;
							}
							studentList.add(newStudent);
							saveToFile();
							System.out.println("Details added successfully.");
						} catch (Exception e) {
							System.out.println("Failed to add details of the student.");
						}
						break;
					case 2:
						try {
							int updateId;
							while (true) {
								try {
									System.out.print("Enter Student ID to update: ");
									updateId = Integer.valueOf(scanner.nextLine());
									break;
								} catch (NumberFormatException e) {
									System.out.println("Invalid Student ID. Please enter a valid integer.");
								}
							}
							Student studentToUpdate = searchStudent(updateId);
							if (studentToUpdate == null) {
								System.out.println("Student not found.");
								break;
							}
							String newName;
							while (true) {
								System.out.print("Enter New Name: ");
								newName = scanner.nextLine();
								if (newName.trim().isEmpty()) {
									System.out.println("Name cannot be empty. Please enter a valid name.");
									continue;
								}
								try {
									Double.parseDouble(newName);
									System.out.println("Name cannot be a number. Please enter a valid name.");
								} catch (NumberFormatException e) {
									break;
								}
							}
							int newAge;
							while (true) {
								try {
									System.out.print("Enter New Age: ");
									newAge = Integer.valueOf(scanner.nextLine());
									if (newAge <= 0) {
										System.out.println("Age must be greater than 0. Please enter a valid age.");
										continue;
									}
									break;
								} catch (NumberFormatException e) {
									System.out.println("Invalid Age. Please enter a valid integer.");
								}
							}
							String newCourse;
							while (true) {
								System.out.print("Enter New Course: ");
								newCourse = scanner.nextLine();
								if (newCourse.trim().isEmpty()) {
									System.out.println("Course cannot be empty. Please enter a valid course name.");
									continue;
								}
								try {
									Double.parseDouble(newCourse);
									System.out.println("Course cannot be a number. Please enter a valid course name.");
								} catch (NumberFormatException e) {
									break;
								}
							}
							studentToUpdate.setStudentName(newName);
							studentToUpdate.setStudentAge(newAge);
							studentToUpdate.setStudentCourse(newCourse);
							saveToFile();
							System.out.println("Student updated successfully.");
						} catch (Exception e) {
							System.out.println("Error: " + e.getMessage());
						}
						break;
					case 3:
						try {
							int deleteId;
							while (true) {
								try {
									System.out.print("Enter Student ID to delete: ");
									deleteId = Integer.valueOf(scanner.nextLine());
									break;
								} catch (NumberFormatException e) {
									System.out.println("Invalid Student ID. Please enter a valid integer.");
								}
							}
							Student studentToDelete = searchStudent(deleteId);
							if (studentToDelete != null) {
								studentList.remove(studentToDelete);
								saveToFile();
								System.out.println("Student deleted successfully.");
							} else {
								System.out.println("Student not found.");
							}
						} catch (Exception e) {
							System.out.println("Error: " + e.getMessage());
						}
						break;
					case 4:
						try {
							int searchId;
							while (true) {
								try {
									System.out.print("Enter Student ID to search: ");
									searchId = Integer.valueOf(scanner.nextLine());
									break;
								} catch (NumberFormatException e) {
									System.out.println("Invalid Student ID. Please enter a valid integer.");
								}
							}
							Student foundStudent = searchStudent(searchId);
							if (foundStudent != null) {
								StringBuilder detailsBuilder = new StringBuilder();
								detailsBuilder.append("\nStudent Details:\n")
								  .append("ID: ").append(foundStudent.getStudentId()).append("\n")
								  .append("Name: ").append(foundStudent.getStudentName()).append("\n")
								  .append("Age: ").append(foundStudent.getStudentAge()).append("\n")
								  .append("Course: ").append(foundStudent.getStudentCourse()).append("\n");
								System.out.print(detailsBuilder);
							} else {
								System.out.println("Student not found.");
							}
						} catch (Exception e) {
							System.out.println("Error: " + e.getMessage());
						}
						break;
					case 5:
						if (studentList.isEmpty()) {
							System.out.println("No students found.");
						} else {
							StringBuilder allStudentsBuilder = new StringBuilder();
							allStudentsBuilder.append("\n\nAll Students:\n");
														for (Student student : studentList) {
																allStudentsBuilder.append("\nID: ").append(student.getStudentId()).append("\n")
																	.append("Name: ").append(student.getStudentName()).append("\n")
																	.append("Age: ").append(student.getStudentAge()).append("\n")
																	.append("Course: ").append(student.getStudentCourse()).append("\n\n");
							}
							System.out.print(allStudentsBuilder);
						}
						break;
					case 6:
						System.out.println("Exiting...");
						scanner.close();
						return;
					default:
						System.out.println("Invalid choice. Try again.");
				}
			} catch (Exception e) {
				System.out.println("System error: " + e.getMessage());
			}
		}
	}

	private static Student searchStudent(int studentId) {
		for (Student student : studentList) {
			if (student.getStudentId() == studentId) return student;
		}
		return null;
	}

	private static void loadFromFile() {
		studentList.clear();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(STUDENT_FILE_NAME))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.startsWith("Name:")) {
					try {
						String studentName = line.replace("Name:", "").trim();
						int studentId = Integer.parseInt(bufferedReader.readLine().replace("Roll No:", "").trim());
						int studentAge = Integer.parseInt(bufferedReader.readLine().replace("Age:", "").trim());
						String studentCourse = bufferedReader.readLine().replace("Course:", "").trim();
						studentList.add(new Student(studentId, studentName, studentAge, studentCourse));
						bufferedReader.readLine(); // Skip blank line between students
					} catch (Exception e) {
						// skip invalid student block
					}
				}
			}
		} catch (FileNotFoundException e) {
			// First run, file not found
		} catch (Exception e) {
			System.out.println("Error loading students: " + e.getMessage());
		}
	}

	private static void saveToFile() {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(STUDENT_FILE_NAME))) {
			for (Student student : studentList) {
				bufferedWriter.write("Name: " + student.getStudentName());
				bufferedWriter.newLine();
				bufferedWriter.write("Roll No: " + student.getStudentId());
				bufferedWriter.newLine();
				bufferedWriter.write("Age: " + student.getStudentAge());
				bufferedWriter.newLine();
				bufferedWriter.write("Course: " + student.getStudentCourse());
				bufferedWriter.newLine();
				bufferedWriter.newLine(); // Blank line between students
			}
		} catch (Exception e) {
			System.out.println("Error saving students: " + e.getMessage());
		}
	}
}
// ...existing code...

