// -------------------------------------------------------------------------
/**
 * A singly linked list node.
 * 
 * @author benblucher
 * @author austink23
 * @version Nov 20, 2025
 * @param <E>
 *            Element type
 */
public class Link<E> {
    private E element;
    private Link<E> next;

    /**
     * Constructor
     * 
     * @param element
     *            The element
     * @param next
     *            The next link
     */
    public Link(E element, Link<E> next) {
        this.element = element;
        this.next = next;
    }


    /**
     * Get next
     * 
     * @return next link
     */
    public Link<E> next() {
        return next;
    }


    /**
     * Set next
     * 
     * @param next
     *            The next link
     */
    public void setNext(Link<E> next) {
        this.next = next;
    }


    /**
     * Get element
     * 
     * @return element
     */
    public E element() {
        return element;
    }
}
