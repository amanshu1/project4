package in.com.rays.ORSprojectt4.bean;

public class SubjectBean extends BaseBean{
	
	private long id;
	private String SubjectName;
	private long CourseId;
	private String CourseName;
	private String Description;
	
	public SubjectBean() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubjectName() {
		return SubjectName;
	}
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
	public Long getCourseId() {
		return CourseId;
	}
	public void setCourseId(long i) {
		CourseId = i;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return SubjectName;
	}
}