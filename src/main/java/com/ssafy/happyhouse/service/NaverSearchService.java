package com.ssafy.happyhouse.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.News;

@Service
public class NaverSearchService {

	HashMap<String, String> dateMap = new HashMap<String, String>() {
		{
			put("Mon", "월요일");
			put("Tue", "화요일");
			put("Wed", "수요일");
			put("Thu", "목요일");
			put("Fri", "금요일");
			put("Sat", "토요일");
			put("Sun", "일요일");
		}
	};

	HashMap<String, String> monthMap = new HashMap<String, String>() {
		{
			put("January", "1");
			put("February", "2");
			put("March", "3");
			put("April", "4");
			put("May", "5");
			put("Jnue", "6");
			put("July", "7");
			put("August", "8");
			put("September", "9");
			put("October", "10");
			put("November", "11");
			put("December", "12");

		}
	};

	public ArrayList<News> searchNews() {
		String clientId = "2PJ7e2skZ8edF4LtP3Ig"; // 애플리케이션 클라이언트 아이디값"
		String clientSecret = "mskOSGglaH"; // 애플리케이션 클라이언트 시크릿값"
		String text = null;
		try {
			text = URLEncoder.encode("아파트", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
		String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text; // json 결과
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(responseBody);
			JSONObject jsonObj = (JSONObject) obj;
			JSONArray newsArray = (JSONArray) jsonObj.get("items");
			ArrayList<News> newsList = new ArrayList<>();

			for (int i = 0; i < newsArray.size(); i++) {
				JSONObject newsObj = (JSONObject) newsArray.get(i);
				String pubDate = (String) newsObj.get("pubDate");
				String title = ((String) newsObj.get("title")).replaceAll("&quot;", "\"").replaceAll("<b>", "")
						.replaceAll("</b>", "");
				String link = (String) newsObj.get("link");
				String description = ((String) newsObj.get("description")).replaceAll("&quot;", "\"")
						.replaceAll("<b>", "").replaceAll("</b>", "");

				String[] s = pubDate.split(" ");

				pubDate = s[3] + "/" + monthMap.get(s[2]) + "/" + s[1] + " " + dateMap.get(s[0].substring(0, 3)) + " "
						+ s[4].substring(0, 5);

				newsList.add(News.builder().pubDate(pubDate).description(description).link(link).title(title).build());
			}

			return newsList;

		} catch (ParseException e) {
			return null;
		}
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
}
