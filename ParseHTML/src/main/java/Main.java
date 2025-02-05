import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import Data.ElementAndItsDepth;

public class Main {
    public static void main(String[] args) {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p><div>nextDepth<div>aaaaaa</div></div></body></html>";
        Document doc = Jsoup.parse(html);

        List<ElementAndItsDepth> elementList = new ArrayList<>();

        leafFinder(doc, 0, elementList);
        System.out.print("LISTA DE FOLHAS GLOBAIS :\n");
        printList(elementList);

        deepestLeavesFilter(elementList);
        System.out.print("LISTA DAS FOLHAS MAIS PROFUNDAS :\n");
        printList(elementList);

    }

    private static void leafFinder(Element element, int currentDepth, List<ElementAndItsDepth> elementList) {
        Elements children = element.children();

        if (children.isEmpty()) {
            ElementAndItsDepth newElement = new ElementAndItsDepth();
            newElement.setElement(element);
            newElement.setElementDepth(currentDepth);
            elementList.add(newElement);
        } else {
            for (int i = 0; i < children.size(); i++) {
                leafFinder(children.get(i), currentDepth + 1, elementList);
            }
        }
    }

    private static void deepestLeavesFilter (List<ElementAndItsDepth> elementList){
        int deepestLayer = 0;

        for (int i = 0; i<elementList.size(); i++){
            if (elementList.get(i).getElementDepth()>deepestLayer){
                deepestLayer = elementList.get(i).getElementDepth();
            }
        }

        for (int i = 0; i<elementList.size(); i++){
            if (elementList.get(i).getElementDepth()<deepestLayer){
                elementList.remove(elementList.get(i));
                i--;
            }
        }
    }

    private static void printList(List<ElementAndItsDepth> elementList){
        for (int i = 0; i < elementList.size(); i++) {
            System.out.print("Elemento " + i + "\n");
            System.out.print(elementList.get(i).getElement() + "\n");
            System.out.print("De profundidade " + elementList.get(i).getElementDepth() + "\n");
        }
    }
}