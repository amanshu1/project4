package in.com.rays.ORSprojectt4.bean;

public class CourseBean extends BaseBean{
	private long id;
	private String courseName;
	private String description;
	private String Duration;
	
	public CourseBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDuration() {
		return Duration;
	}

	public void setDuration(String duration) {
		Duration = duration;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String discription) {
		this.description = discription;
	}
	
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	
	
	public String getValue() {
		// TODO Auto-generated method stub
		return courseName;
	}
	
}
