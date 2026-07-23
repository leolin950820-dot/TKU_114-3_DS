public class PlaylistNode {
    private String id;     
    private String title;  
    public PlaylistNode next;

    public PlaylistNode(String id, String title) {
        this.id = id;
        this.title = title;
        this.next = null;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", id, title);
    }
}