package midterm_exam;

public class Q12_BookingReport {
    public static void main(String[] args) {
        Q12_Booking[] bookings = {
            new Q12_Booking("B001", "Amy", 2, 750, true),
            new Q12_Booking("B002", "Ben", 4, 600, false),
            new Q12_Booking("B003", "Cara", 3, 900, true),
            new Q12_Booking("B004", "Dan", 1, 1200, true)
        };

        System.out.println("已確認筆數：" + countConfirmed(bookings));
        System.out.println("已確認收入：" + calculateConfirmedRevenue(bookings));

        Q12_Booking found = findById(bookings, "b003");
        System.out.println("搜尋結果：" + found);

        Q12_Booking largest = findLargestConfirmed(bookings);
        System.out.println("最高確認預約：" + largest);
    }

    public static int countConfirmed(Q12_Booking[] bookings) {
        int count = 0;

        for (Q12_Booking booking : bookings) {
            if (booking.isConfirmed()) {
                count++;
            }
        }

        return count;
    }

    public static double calculateConfirmedRevenue(Q12_Booking[] bookings) {
        double total = 0.0;

        for (Q12_Booking booking : bookings) {
            if (booking.isConfirmed()) {
                total += booking.getTotalPrice();
            }
        }

        return total;
    }

    public static Q12_Booking findById(
        Q12_Booking[] bookings,
        String id
    ) {
        if (id == null) {
            return null;
        }

        for (Q12_Booking booking : bookings) {
            if (booking.getId().equalsIgnoreCase(id)) {
                return booking;
            }
        }

        return null;
    }

    public static Q12_Booking findLargestConfirmed(
        Q12_Booking[] bookings
    ) {
        Q12_Booking largest = null;

        for (Q12_Booking booking : bookings) {
            if (!booking.isConfirmed()) {
                continue;
            }

            if (largest == null
                || booking.getTotalPrice() > largest.getTotalPrice()) {
                largest = booking;
            }
        }

        return largest;
    }
}
