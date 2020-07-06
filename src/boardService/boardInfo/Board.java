package boardService.boardInfo;

public class Board {
    private int id;
    private String status;
    private String flight;
    private String gate;
    private String destination;
    private String time;

    @Override
    public String toString() {
        return String.format("%d: %s %s %s %s %s", id, flight, destination, gate, time, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void updateAllInfo(String str) {
        String[] splitStr = str.split("!");
        this.setFlight(splitStr[0]);
        this.setDestination(splitStr[1]);
        this.setGate(splitStr[2]);
        this.setTime(splitStr[3]);
        this.setStatus(splitStr[4]);
    }
}
