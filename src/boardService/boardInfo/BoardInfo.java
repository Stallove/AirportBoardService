package boardService.boardInfo;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardInfo {
    private static CopyOnWriteArrayList<Board> boardInfoList;
    private static AtomicInteger id;

    static {
        String[] flightInfo = {
                "495!New York!A02!07:15!ON TIME",
                "536!London!C03!08:00!BOARDING",
                "849!Singapore!A10!08:35!DELAYED",
                "150!Prague!F11!09:10!CANCELED",
                "633!Tokio!A08!09:30!ON TIME"
        };
        boardInfoList = new CopyOnWriteArrayList<>();
        id = new AtomicInteger();
        for (String str : flightInfo) {
            add(str);
        }
    }

    public static String toPlain() {
        String plain = "";
        for (Board b : boardInfoList) plain += b.toString() + "\n";
        return plain;
    }

    public static CopyOnWriteArrayList<Board> getList() {
        return boardInfoList;
    }

    public static Board find(int id) {
        Board board = null;
        for(Board b : boardInfoList) {
            if(b.getId() == id) {
                board = b;
                break;
            }
        }
        return board;
    }

    public static void add(String str) {
        int localId = id.incrementAndGet();
        Board board = new Board();
        String[] splitInfo = str.split("!");
        board.setFlight(splitInfo[0]);
        board.setDestination(splitInfo[1]);
        board.setGate(splitInfo[2]);
        board.setTime(splitInfo[3]);
        board.setStatus(splitInfo[4]);
        board.setId(localId);
        boardInfoList.add(board);
    }


}
