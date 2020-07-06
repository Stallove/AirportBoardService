package boardService;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class Main {
    public static void main(String[] args) throws Exception {
        //Create a new component
        Component component = new Component();

        //Add a new HTTP server listening on port 8181
        component.getServers().add(Protocol.HTTP, 8181);

        //Attach the application
        component.getDefaultHost().attach("/board", new BoardApplication());

        //Start the web server
        component.start();
    }
}
