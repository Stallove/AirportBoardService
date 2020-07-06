package boardService;

import boardService.boardInfo.Board;
import boardService.boardInfo.BoardInfo;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class CreateInfo extends ServerResource {

    @Post
    public Representation create(Representation data) {
        Status status = null;
        String msg = null;

        // Extract the data from the POST body.
        Form form = new Form(data);
        String info = form.getFirstValue("info");

        if (info == null) {
            msg = "No words were given for the adage.\n";
            status = Status.CLIENT_ERROR_BAD_REQUEST;
        }
        else {
            BoardInfo.add(info);
            msg = "Info '" + info + "' has been added.\n";
            status = Status.SUCCESS_OK;
        }

        setStatus(status);
        return new StringRepresentation(msg, MediaType.TEXT_PLAIN);
    }
}
