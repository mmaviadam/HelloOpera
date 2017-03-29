package candemirel.com.helloopera.model;

/**
 * Holds Reddit entries that will be received from server
 */
public class ResponseBody {

    private String kind;
    private OuterNode data;

    public String getKind() {
        return kind;
    }

    public OuterNode getData() {
        return data;
    }
}
