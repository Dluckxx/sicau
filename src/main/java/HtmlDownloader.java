import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HtmlDownloader {

	private String userAgent = "User-Agent";
	private String userAgentVal = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";
	private String user;
	private String pwd;

	private Map<String, String> cookies = null;

	private UrlManager manager = UrlManager.gerUrlManager();

	HtmlDownloader(String user, String pwd) {
		this.user = user;
		this.pwd = pwd;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	/**
	 * Get Html
	 *
	 * @param url
	 * @return
	 */
	private String getHtmlByUrl(String url) {
		try {
			return Jsoup.connect(url).get().outerHtml();
		} catch (IOException e) {
			System.out.println("获取HTML失败！");
			return null;
		}
	}

	/**
	 * Login by Student
	 */
	void login() {
		System.out.println("第1次访问...");
		Connection connection = Jsoup.connect(manager.getWebUrl());//获取连接
		Connection.Response response;
		Map<String, String> cookie_1;
		try {
			response = connection
					.header(userAgent, userAgentVal)
					.ignoreContentType(true)
					.timeout(8000)
					.method(Connection.Method.GET)
					.execute();
			cookie_1 = response.cookies();
			this.cookies = cookie_1;
			System.out.println("第1次访问成功！");
		} catch (IOException e) {
			System.err.println("第一次访问失败！");
//			e.printStackTrace();
			return;
		}


		System.out.println("=========================================================");

		System.out.println("第2次访问...");

		Map<String, String> datas = new HashMap<>();
		datas.put("user", user);
		datas.put("pwd", pwd);
		datas.put("lb", "S");
		datas.put("sign", HtmlParser.getSign(getHtmlByUrl(manager.getWebUrl())));
		datas.put("submit", "");

		Connection connection2 = Jsoup.connect(manager.getCheck());
		connection2.header(userAgent, userAgentVal);

		// 设置cookie和post上面的map数据
		Connection.Response response2;
		try {
			response2 = connection2
					.ignoreContentType(true)
					.data(datas)
					.cookies(cookie_1)
					.timeout(8000)
					.method(Connection.Method.POST)
					.execute();

			for (Map.Entry<String, String> entry : response2.cookies().entrySet()){
				this.cookies.put(entry.getKey(), entry.getValue());
			}

			System.out.println("**********登陆成功！**********");
		} catch (IOException e) {
			System.out.println("第二次访问失败！");
			e.printStackTrace();
		}
	}

	String getGradesHtml() {
		System.out.println("获取成绩单网页ing...");
		try {
//			URL url = new URL(manager.getGradesTableUrl());
//			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//			connection.setRequestMethod("GET");
//			connection.addRequestProperty(userAgent, userAgentVal);
//			connection.setConnectTimeout(8000);
//			connection.setReadTimeout(8000);
//			for (Map.Entry<String,String> entry: cookies.entrySet()) {
//				connection.addRequestProperty(entry.getKey(), entry.getValue());
//			}
//			Document document = Jsoup.parse(connection.getInputStream(), "GBK", manager.getGradesTableUrl());

			Connection connection = Jsoup.connect(manager.getGradesTableUrl());

			Connection.Response response = connection
					.ignoreContentType(true)
					.cookies(this.cookies)
					.header(userAgent, userAgentVal)
					.timeout(8000)
					.execute();

			Document document = Jsoup.parse(response.body());

			System.out.println("获取成绩单网页成功！");
			return document.outerHtml();
		} catch (IOException e) {
			System.err.println("获取成绩单网页失败！");
			return null;
		}
	}
}
