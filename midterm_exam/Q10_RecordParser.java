package midterm_exam;

public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {
            "A101|Keyboard|3|850",
            "A102|Mouse|-1|500",
            "broken data",
            "A103|Monitor|2|4200",
            "A104||1|300"
        };

        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }

        System.out.println("合法筆數：" + countValidRecords(records));
        System.out.println("總金額：" + calculateGrandTotal(records));
    }

    public static boolean isValidRecord(String record) {
        if (record == null) {
            return false;
        }

        String[] parts = record.split("\\|", -1);

        if (parts.length != 4) {
            return false;
        }

        String code = parts[0].trim();
        String name = parts[1].trim();

        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        try {
            int quantity = Integer.parseInt(parts[2].trim());
            int price = Integer.parseInt(parts[3].trim());
            return quantity > 0 && price >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int calculateRecordTotal(String record) {
        if (!isValidRecord(record)) {
            return -1;
        }

        String[] parts = record.split("\\|", -1);
        int quantity = Integer.parseInt(parts[2].trim());
        int price = Integer.parseInt(parts[3].trim());
        return quantity * price;
    }

    public static int countValidRecords(String[] records) {
        int count = 0;

        for (String record : records) {
            if (isValidRecord(record)) {
                count++;
            }
        }

        return count;
    }

    public static int calculateGrandTotal(String[] records) {
        int total = 0;

        for (String record : records) {
            int recordTotal = calculateRecordTotal(record);
            if (recordTotal >= 0) {
                total += recordTotal;
            }
        }

        return total;
    }
}
