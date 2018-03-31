package junkie.utils.searchword.data.related;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RelatedWord implements Serializable {
	private static final long serialVersionUID = 900827892998388911L;

	@Id @Column(name = "related_id") @GeneratedValue
	private Long id;
	@Column(name="related_word")
	private String relatedWord;
	@Column(name="search_word")
	private String searchWord;
	
	@Column(name = "url_string")
	private String urlString;
}