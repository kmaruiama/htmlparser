package Data;

import org.jsoup.nodes.Element;

public class ElementAndItsDepth {
    private Element element;
    private int elementDepth;

    public int getElementDepth() {
        return elementDepth;
    }

    public void setElementDepth(int elementDepth) {
        this.elementDepth = elementDepth;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
