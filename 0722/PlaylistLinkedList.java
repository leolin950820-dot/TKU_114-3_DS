public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;

    public PlaylistLinkedList() {
        this.head = null;
        this.size = 0;
    }
    public boolean containsId(String id) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.getId().equalsIgnoreCase(id)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public boolean addSong(String id, String title) {
        if (id == null || id.trim().isEmpty() || title == null || title.trim().isEmpty()) {
            System.out.println("[新增失敗] 歌曲代碼或名稱不可為空！");
            return false;
        }

        String cleanId = id.trim();
        if (containsId(cleanId)) {
            System.out.println("[新增失敗] 歌曲代碼 \"" + cleanId + "\" 已存在，不可重複新增！");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(cleanId, title.trim());
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
        System.out.println("[新增成功] 已加入歌曲: " + newNode);
        return true;
    }
    public PlaylistNode searchSong(String id) {
        if (head == null) {
            System.out.println("[搜尋失敗] 播放清單目前為空！");
            return null;
        }

        PlaylistNode current = head;
        int position = 1;
        while (current != null) {
            if (current.getId().equalsIgnoreCase(id.trim())) {
                System.out.println("[搜尋成功] 找到歌曲 " + current + "，位於第 " + position + " 首。");
                return current;
            }
            current = current.next;
            position++;
        }

        System.out.println("[搜尋失敗] 找不到歌曲代碼為 \"" + id + "\" 的歌曲。");
        return null;
    }
    public boolean removeSong(String id) {
        if (head == null) {
            System.out.println("[刪除失敗] 播放清單目前為空！");
            return false;
        }

        String cleanId = id.trim();
        if (head.getId().equalsIgnoreCase(cleanId)) {
            System.out.println("[刪除成功] 已移除第一首歌曲: " + head);
            head = head.next;
            size--;
            return true;
        }
        PlaylistNode current = head;
        while (current.next != null && !current.next.getId().equalsIgnoreCase(cleanId)) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("[刪除失敗] 找不到代碼為 \"" + cleanId + "\" 的歌曲！");
            return false;
        }
        System.out.println("[刪除成功] 已移除歌曲: " + current.next);
        current.next = current.next.next;
        size--;
        return true;
    }
    public void printPlaylist() {
        System.out.println("\n----------------------------------------");
        System.out.println("【 🎵 播放清單完整順序 🎵 】");
        
        if (head == null) {
            System.out.println("（目前播放清單為空）");
            System.out.println("總計歌曲數: 0 首");
            System.out.println("----------------------------------------");
            return;
        }

        PlaylistNode current = head;
        int index = 1;
        while (current != null) {
            System.out.printf(" %d. %s%n", index, current);
            current = current.next;
            index++;
        }

        System.out.println("----------------------------------------");
        System.out.println("總計歌曲數: " + size + " 首");
        System.out.println("----------------------------------------");
    }
}