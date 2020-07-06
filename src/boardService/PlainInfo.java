package boardService;

import boardService.boardInfo.BoardInfo;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class PlainInfo extends ServerResource {

    @Get
    public Representation toPlain() {
        String boardInfo = BoardInfo.toPlain();
        setStatus(Status.SUCCESS_OK);
        return new StringRepresentation(boardInfo, MediaType.TEXT_PLAIN);
    }
}
