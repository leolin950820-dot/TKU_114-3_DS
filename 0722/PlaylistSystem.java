public class PlaylistSystem {

    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("====== 播放清單系統測試 ======\n");
        System.out.println("=== 1. 新增歌曲測試 ===");
        playlist.addSong("S01", "晴天");
        playlist.addSong("S02", "稻香");
        playlist.addSong("S03", "告白氣球");
        playlist.addSong("S04", "七里香");
        playlist.printPlaylist();
        System.out.println("\n=== 2. 測試代碼不可重複性 ===");
        playlist.addSong("S02", "夜曲"); 
        System.out.println("\n=== 3. 搜尋歌曲測試 ===");
        playlist.searchSong("S03"); 
        playlist.searchSong("S99"); 
        System.out.println("\n=== 4. 刪除中間歌曲 (S02) ===");
        playlist.removeSong("S02");
        playlist.printPlaylist();
        System.out.println("\n=== 5. 刪除第一首歌曲 Head (S01) ===");
        playlist.removeSong("S01");
        playlist.printPlaylist();
        System.out.println("\n=== 6. 刪除最後一首歌曲 Tail (S04) ===");
        playlist.removeSong("S04");
        playlist.printPlaylist();
        System.out.println("\n=== 7. 清空清單 (刪除 S03) ===");
        playlist.removeSong("S03");
        playlist.printPlaylist();
        System.out.println("\n=== 8. 空串列邊界測試 ===");
        playlist.removeSong("S01");
        playlist.searchSong("S01");
    }
}