package junkie.utils.searchword.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import junkie.utils.searchword.data.SearchWord;
import junkie.utils.searchword.data.SearchWordRepository;
import junkie.utils.searchword.data.related.RelatedWord;
import junkie.utils.searchword.data.related.RelatedWordRepository;
import junkie.utils.searchword.search.SearchException;
import junkie.utils.searchword.search.connector.NaverConnector;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchService {

	@Autowired
	private SearchWordRepository searchRepository;
	@Autowired
	private RelatedWordRepository relatedWordRepository;

	public List<SearchWord> getAllSearchWord() throws SearchException {
		return searchRepository.findAll();
	}

	public SearchWord getSearchWord(String word) throws SearchException {

		SearchWord searchWord = searchRepository.findOne(word);
		if (searchWord == null) {
			List<RelatedWord> relationSearchWordList = null;
			NaverConnector connector = new NaverConnector(word);
			try {
				relationSearchWordList = connector.searchRelationWord();
			} catch (SearchException e) {
				log.error("error occured while search by [{}]", word);
				throw e;
			}
			searchWord = connector.getSearchWord();
			relationSearchWordList.stream().forEach(s -> {
				log.debug(s.toString());
				relatedWordRepository.save(s);
			});
			searchWord.setRelatedWordList(relationSearchWordList);
			searchWord = searchRepository.save(searchWord);
		}
		return searchWord;
	}
	
	public List<RelatedWord> getSearchWordByRelatedWord(String relatedWord) throws SearchException {
		return relatedWordRepository.findByRelatedWord(relatedWord);
	}
}
