// -------------------------------------------------------------------------
/**
 * A simple linked list implementation.
 * * @author benblucher
 * 
 * @author austink23
 * @version Nov 20, 2025
 * @param <E>
 *            Element type
 */
public class LinkedList<E> {
    private Link<E> head;
    private int size;

    /**
     * Constructor
     */
    public LinkedList() {
        head = new Link<E>(null, null); // Dummy header
        size = 0;
    }


    /**
     * Add element to end
     * * @param element
     * Element to add
     */
    public void append(E element) {
        Link<E> curr = head;
        while (curr.next() != null) {
            curr = curr.next();
        }
        curr.setNext(new Link<E>(element, null));
        size++;
    }


    /**
     * Add element at specific index
     * * @param index
     * Index to insert at
     * 
     * @param element
     *            Element to insert
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Link<E> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next();
        }
        curr.setNext(new Link<E>(element, curr.next()));
        size++;
    }


    /**
     * Remove specific object instance
     * * @param element
     * Element to remove
     * 
     * @return true if removed
     */
    public boolean remove(E element) {
        Link<E> curr = head;
        while (curr.next() != null) {
            if (curr.next().element().equals(element)) {
                curr.setNext(curr.next().next());
                size--;
                return true;
            }
            curr = curr.next();
        }
        return false;
    }


    /**
     * Get size
     * * @return size
     */
    public int size() {
        return size;
    }


    /**
     * Get element at index
     * * @param index
     * Index
     * 
     * @return Element
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Link<E> curr = head.next();
        for (int i = 0; i < index; i++) {
            curr = curr.next();
        }
        return curr.element();
    }


    /**
     * Check if list contains element
     * * @param element
     * Element to check
     * 
     * @return true if found
     */
    public boolean contains(E element) {
        Link<E> curr = head.next();
        while (curr != null) {
            if (curr.element().equals(element)) {
                return true;
            }
            curr = curr.next();
        }
        return false;
    }
}
