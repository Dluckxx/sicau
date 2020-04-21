import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataOutput {

	public DataOutput() {
	}

	public void createHtml(String html) {
		String fileName = "test.html";
		File file = new File(fileName);
		if (file.exists()) file.delete();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(html);
			writer.close();
			System.out.println("生成HTML文件成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
