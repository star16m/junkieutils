package junkie.utils.searchword.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import junkie.utils.searchword.data.SearchWord;
import junkie.utils.searchword.data.related.RelatedWord;
import junkie.utils.searchword.search.SearchException;
import junkie.utils.searchword.service.SearchService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class SearchController {

	@Autowired
	private SearchService searchService;
	@GetMapping
	public List<SearchWord> getAllSearchWords() throws SearchException {
		return searchService.getAllSearchWord();
	}

	@GetMapping("searchword/{word}")
	public SearchWord searchWord(@PathVariable final String word) throws SearchException {
		log.info("try search word[{}]", word);
		SearchWord searchWord = null;
		try {
			searchWord = searchService.getSearchWord(word);
		} catch (SearchException e) {
			log.error(e.getMessage());
			throw e;
		}
		log.info("founded with word [{}], related word is [{}]", searchWord, searchWord.getRelatedWordList().size());
		return searchWord;
	}
	
	@GetMapping("related/{relatedWord}")
	public List<RelatedWord> searchByRelatedWord(@PathVariable final String relatedWord) throws SearchException {
		return searchService.getSearchWordByRelatedWord(relatedWord);
	}
}
