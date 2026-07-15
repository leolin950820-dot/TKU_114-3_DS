import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String originalInput = getValidInput(scanner);
        int originalLength = originalInput.length();
        System.out.println("原始字元數：" + originalLength);
        int validCharCount = countValidCharacters(originalInput);
        System.out.println("Trim 之後的有效字元數：" + validCharCount);
        String[] words = splitWords(originalInput);
        System.out.println("單字數量：" + words.length);
        int vowelCount = countVowels(originalInput);
        System.out.println("母音 (a, e, i, o, u) 總數：" + vowelCount);
        String longestWord = findLongestWord(words);
        System.out.println("最長單字：" + longestWord);
        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = scanner.nextLine();
        int keywordCount = countKeywordOccurrences(words, keyword);
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數 (忽略大小寫)：" + keywordCount);

        scanner.close();
    }
    public static String getValidInput(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("請輸入一行文字：");
            input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                break;
            }
            System.out.println("錯誤：輸入不能為空或全空白，請重新輸入！");
        }
        return input;
    }
    public static int countValidCharacters(String text) {
        return text.trim().length();
    }
    public static String[] splitWords(String text) {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return new String[0];
        }
        return trimmed.split("\\s+");
    }
    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }
    public static String findLongestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }
    public static int countKeywordOccurrences(String[] words, String keyword) {
        if (words == null || keyword == null || keyword.trim().isEmpty()) {
            return 0;
        }
        int count = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(keyword.trim())) {
                count++;
            }
        }
        return count;
    }
}