import java.util.Map;

public class Main {
	public static void main(String[] args) {

		String name = "201703880";
		String password = "289110";

		//初始化资源
		HtmlDownloader downloader = new HtmlDownloader(name, password);
		DataOutput output = new DataOutput();

		downloader.login();
		String gradeHtml = downloader.getGradesHtml();
		int i = 0;
		for (Map.Entry<String, String> e : downloader.getCookies().entrySet()) {
			System.out.printf("%-20s : %s\n", e.getKey(), e.getValue());
			i++;
		}
		System.out.println("cookies = " + i);

		output.createHtml(gradeHtml);
	}
}
