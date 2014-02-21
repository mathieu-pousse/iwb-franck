package iwb.bo;

/**
 * Represents JSON-Formated references to related resources
 */
public class Link {

    /**
     * Represents the type of the link
     */
    String rel;

    /**
     * Contains the link's URI
     */
    String href;

    public Link(){}

    public Link(String ref, String href){
        this.rel = rel;
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
