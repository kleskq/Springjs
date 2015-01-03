package pl.lodz.uni.math.engine;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import pl.lodz.uni.math.pojo.Category;

@Entity
@Table(name = "Category")
public class CategoryEngine extends Category {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5062309226110515066L;

	public CategoryEngine() {
		super();
	}

	public CategoryEngine(String categoryName) {
		super(StringUtils.uncapitalize(categoryName));
	}

	public CategoryEngine(int categoryId, String categoryName,
			Set<NoteEngine> notes) {
		super(categoryId, StringUtils.uncapitalize(categoryName), notes);
	}
	
	@Override
	public String getCategoryName(){
		return StringUtils.capitalize(super.getCategoryName());
	}
	
	@Override
	public void setCategoryName(String categoryName){
		super.setCategoryName(StringUtils.uncapitalize(categoryName));
	}

}
