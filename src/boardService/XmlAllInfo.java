package boardService;

import boardService.boardInfo.Board;
import boardService.boardInfo.BoardInfo;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.ext.xml.DomRepresentation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.restlet.data.MediaType;
import java.util.List;

public class XmlAllInfo extends ServerResource {

    @Get
    public Representation toXml() {
        List<Board> list = BoardInfo.getList();
        DomRepresentation dom = null;
        try {
            dom = new DomRepresentation(MediaType.TEXT_XML);
            dom.setIndenting(true);
            Document doc = dom.getDocument();

            Element root = doc.createElement("boardInfo");
            for (Board b : list) {
                Element next = doc.createElement("board");
                next.appendChild(doc.createTextNode(b.toString()));
                root.appendChild(next);
            }
            doc.appendChild(root);
        }
        catch(Exception e) { }
        return dom;
    }
}
