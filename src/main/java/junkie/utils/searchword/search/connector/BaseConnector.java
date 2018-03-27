package junkie.utils.searchword.search.connector;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import junkie.utils.searchword.data.SearchWord;
import junkie.utils.searchword.data.related.RelatedSearchWord;
import junkie.utils.searchword.search.SearchException;
import junkie.utils.searchword.search.SearchResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public abstract class BaseConnector implements ISearchConnector {
	public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36";
	public static final int DEFAULT_TIMEOUT_MILISECONDS = 5000;

	private SearchWord searchWord;
	protected BaseConnector(SearchWord searchWord) {
		this.searchWord = searchWord;
	}
	public final RelatedSearchWord searchRelationWord() throws SearchException {
		log.info("try connect.");
		Connection connections = getConnections();
		log.info("success get connections");
		Document document = null;
		try {
			document = connections.get();
		} catch( IOException e) {
			log.error("error occured while connect.", e);
			throw new SearchException(e);
		}
		SearchResult connectResult = new SearchResult(document);
		log.debug("connect result [{}]", connectResult);
		List<SearchWord> relationKeywordList = findRelationKeyword(connectResult);
		RelatedSearchWord relatedSearchWord = new RelatedSearchWord(this.searchWord.getWord(), relationKeywordList);
		return relatedSearchWord;
	}
	protected Connection getConnections() {
		return Jsoup.connect(this.searchWord.getUrlString()).timeout(DEFAULT_TIMEOUT_MILISECONDS).userAgent(DEFAULT_USER_AGENT);
	}
	
	protected abstract List<SearchWord> findRelationKeyword(SearchResult connectResult);
}
