package boardService;

import boardService.boardInfo.Board;
import boardService.boardInfo.BoardInfo;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class UpdateInfo extends ServerResource {

    @Put
    public Representation update(Representation data) {
        Status status = null;
        String msg = null;

        // Extract the data from the POST body.
        Form form = new Form(data);
        String sid = form.getFirstValue("id");
        String info = form.getFirstValue("info");

        if (sid == null || info == null) {
            msg = "An ID and new words must be provided.\n";
            status = Status.CLIENT_ERROR_BAD_REQUEST;
        }
        else {
            int id = Integer.parseInt(sid.trim());
            Board board = BoardInfo.find(id);
            if (board == null) {
                msg = "There is no adage with ID " + id + "\n";
                status = Status.CLIENT_ERROR_BAD_REQUEST;
            }
            else {
                board.updateAllInfo(info);
                msg = "Adage " + id + " has been updated to '" + info + "'.\n";
                status = Status.SUCCESS_OK;
            }
        }

        setStatus(status);
        return new StringRepresentation(msg, MediaType.TEXT_PLAIN);
    }
}
