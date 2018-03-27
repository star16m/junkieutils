package junkie.utils.searchword.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import junkie.utils.searchword.data.SearchWord;
import junkie.utils.searchword.data.SearchWordRepository;
import junkie.utils.searchword.data.related.RelatedSearchWord;
import junkie.utils.searchword.data.related.RelatedSearchWordRepository;
import junkie.utils.searchword.search.SearchException;
import junkie.utils.searchword.search.connector.NaverConnector;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/searchword")
@Slf4j
public class SearchController {

	@Autowired
	private SearchWordRepository searchRepository;
	
	@Autowired
	private RelatedSearchWordRepository relatedSearchWordRepository;

	@GetMapping
	public List<SearchWord> getAllSearchWords() {
		return searchRepository.findAll();
	}
	@GetMapping("{word}")
	public RelatedSearchWord searchWord(@PathVariable String word) throws SearchException {
		log.info("try search word[{}]", word);
		
		SearchWord searchWord = null;
		RelatedSearchWord relatedSearchWord = null;
		searchWord = searchRepository.findOne(word);
		if (searchWord == null) {
			NaverConnector connector = new NaverConnector(word);
			try {
				relatedSearchWord = connector.searchRelationWord();
			} catch (SearchException e) {
				log.error("error occured while search by [{}]", word);
				throw e;
			}
			// save word
			searchWord = searchRepository.save(connector.getSearchWord());
			// save related word as search word
			relatedSearchWord.getRelatedWordList().stream().forEach(s -> {searchRepository.save(s);});
			relatedSearchWord.setWord(searchWord.getWord());
			// save related word list
			relatedSearchWord = relatedSearchWordRepository.save(relatedSearchWord);
		} else {
			relatedSearchWord = relatedSearchWordRepository.findOne(searchWord.getWord());
		}
		relatedSearchWord.getRelatedWordList().stream().forEach(w -> log.debug(" find related word [{}]", w));
		return relatedSearchWord;
	}
}
