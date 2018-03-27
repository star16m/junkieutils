package junkie.utils.searchword.search.connector;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;

import junkie.utils.searchword.data.SearchWord;
import junkie.utils.searchword.search.SearchResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NaverConnector extends BaseConnector {

	public NaverConnector(String searchWord) {
		super(new SearchWord(searchWord, "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" + searchWord));
	}
	@Override
	protected Connection getConnections() {
		String encodeResult = null;
		try {
			encodeResult = URLEncoder.encode(super.getSearchWord().getWord(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			// ignored encoding exception.
		}
		Connection connection = super.getConnections();
		connection.header("authority",                 "search.naver.com, value")
		          .header("method",                    "GET")
		          .header("path",                      "/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" + encodeResult)
		          .header("scheme",                    "https")
		          .header("accept",                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		          .header("accept-encoding",           "gzip, deflate, br")
		          .header("accept-language",           "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6")
		          .header("cache-control",             "no-cache")
		          .header("pragma",                    "no-cache")
		          .header("referer",                   "https://www.naver.com/")
		          .header("upgrade-insecure-requests", "1")
		          ;
		return connection;
	}

	@Override
	protected List<SearchWord> findRelationKeyword(SearchResult connectResult) {
		log.debug("connectResult [{}]", connectResult);
		List<SearchWord> keywordList = new ArrayList<>();
		connectResult.find("#nx_related_keywords > dl > dd.lst_relate > ul > li > a", (e, i) -> {
			String relationKeyword = e.text();
			String urlString = e.attr("abs:href");
			
			keywordList.add(new SearchWord(relationKeyword, urlString));
		});
		return keywordList;
	}
}
