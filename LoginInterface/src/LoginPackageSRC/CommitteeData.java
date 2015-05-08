package LoginPackageSRC;

public class CommitteeData {
	private String courseYear, courseName, dateOfBirth, address;

	public CommitteeData(String courseYear, String courseName,
			String dateOfBirth, String address) {
		super();
		this.courseYear = courseYear;
		this.courseName = courseName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

	public String getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(String courseYear) {
		this.courseYear = courseYear;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
 
}
