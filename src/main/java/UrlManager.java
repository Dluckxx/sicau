public class UrlManager {

	private String webUrl = "http://jiaowu.sicau.edu.cn/web/web/web/index.asp";
	private String check = "http://jiaowu.sicau.edu.cn/jiaoshi/bangong/check.asp";
	private String userUrl = "http://jiaowu.sicau.edu.cn/xuesheng/bangong/main/index1.asp";
	private String gradesTableUrl = "http://jiaowu.sicau.edu.cn/xuesheng/chengji/chengji/bchengjike.asp";
	private String timeTableUrl = "http://jiaowu.sicau.edu.cn/xuesheng/gongxuan/gongxuan/kbbanji.asp";

	private static UrlManager urlManager = new UrlManager();

	private UrlManager() {
	}

	public static UrlManager gerUrlManager() {
		return urlManager;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public String getGradesTableUrl() {
		return gradesTableUrl;
	}

	public void setGradesTableUrl(String gradesTableUrl) {
		this.gradesTableUrl = gradesTableUrl;
	}

	public String getTimeTableUrl() {
		return timeTableUrl;
	}

	public void setTimeTableUrl(String timeTableUrl) {
		this.timeTableUrl = timeTableUrl;
	}
}
