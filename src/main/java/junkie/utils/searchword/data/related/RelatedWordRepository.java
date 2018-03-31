package junkie.utils.searchword.data.related;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatedWordRepository extends JpaRepository<RelatedWord, Long> {

	public List<RelatedWord> findByRelatedWord(String relatedWord);

}
