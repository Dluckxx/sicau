import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParser {

	public HtmlParser() {
	}

	public static String getSign(String html) {

		Document document = Jsoup.parse(html);
		String sign = document.getElementsByTag("input").last().attr("value");
		System.out.println("sign: " + sign);

		return sign;
	}
}
