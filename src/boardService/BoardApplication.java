package boardService;

import boardService.boardInfo.Board;
import boardService.boardInfo.BoardInfo;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.routing.Router;

public class BoardApplication extends Application {
    @Override
    public synchronized Restlet createInboundRoot() {
        //DELETE handler
        Restlet cleaner = new Restlet(getContext()) {
            public void cleanerMethod(Request request, Response response) {
                String msg = null;
                String sid = (String) request.getAttributes().get("id");

                if (sid == null) msg = badRequest("No ID given.\n");

                Integer id = null;
                try {
                    id = Integer.parseInt(sid.trim());
                } catch (Exception e) {
                    msg = badRequest("Ill-formed ID.\n");
                }

                Board board = BoardInfo.find(id);
                if (board == null) {
                    msg = badRequest("No Board info with ID " + id + "\n");
                }
                else {
                    BoardInfo.getList().remove(board);
                    msg = "Board Info with " + id + " removed.\n";
                }

                response.setEntity(msg, MediaType.TEXT_PLAIN);
            }
        };
        Router router = new Router(getContext());
        router.attach("/", PlainInfo.class);
        router.attach("/xml", XmlAllInfo.class);
        router.attach("/xml/{id}", XmlOneInfo.class);
        router.attach("/json", JsonAllInfo.class);
        router.attach("/create", CreateInfo.class);
        router.attach("/update", UpdateInfo.class);
        router.attach("/delete/{id}", cleaner);

        return router;
    }




    private String badRequest(String msg) {
        Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST, msg);
        return error.toString();
    }


}
