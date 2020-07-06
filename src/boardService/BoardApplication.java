package boardService;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Status;

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


            }
        };
        return null;
    }

    private String badRequest(String msg) {
        Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST, msg);
        return error.toString();
    }


}
