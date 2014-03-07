package iwb.bo;

/**
 * An object that aggregates the item component with the matching trash
 * @author franckylm
 *
 */
public class ConstituentTrash {
	
	private Constituent consituent;
	private Trash trash;
	
	public ConstituentTrash(){}
	
	public ConstituentTrash(Constituent constituent, Trash trash){
		this.consituent = constituent;
		this.trash = trash;
	}
	
	public Constituent getConsituent() {
		return consituent;
	}
	public void setConsituent(Constituent consituent) {
		this.consituent = consituent;
	}
	public Trash getTrash() {
		return trash;
	}
	public void setTrash(Trash trash) {
		this.trash = trash;
	}
	
	
}
