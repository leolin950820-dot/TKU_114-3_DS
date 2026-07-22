import java.util.ArrayList;

import java.util.Scanner;



public class CourseRegistrationSystem {



    private static final Scanner scanner = new Scanner(System.in);

    private static final ArrayList<Course> courseList = new ArrayList<>();



    public static void main(String[] args) {

        Course c1 = new Course("CS101", "Java 程式設計", 3);

        Course c2 = new Course("CS102", "資料庫系統", 2);

        Course c3 = new Course("CS103", "演算法", 40);

        c2.enroll();

        c2.enroll();



        courseList.add(c1);

        courseList.add(c2);

        courseList.add(c3);



        boolean running = true;

        System.out.println("====== 歡迎使用大學選課管理系統 ======");



        while (running) {

            printMenu();

            System.out.print("請選擇功能 (1-7): ");

            String choice = scanner.nextLine().trim();



            switch (choice) {

                case "1":

                    addCourse();

                    break;

                case "2":

                    enrollCourse();

                    break;

                case "3":

                    dropCourse();

                    break;

                case "4":

                    deleteCourse();

                    break;

                case "5":

                    searchCourse();

                    break;

                case "6":

                    showAllAndStatistics();

                    break;

                case "7":

                    System.out.println("感謝使用選課系統，系統已安全退出！");

                    running = false;

                    break;

                default:

                    System.out.println("[錯誤] 無效的選項，請重新選擇！");

            }

            System.out.println();

        }



        scanner.close();

    }

    private static void printMenu() {

        System.out.println("------------------------------------");

        System.out.println("1. 新增課程");

        System.out.println("2. 學生選課 (加選)");

        System.out.println("3. 學生退選");

        System.out.println("4. 刪除課程");

        System.out.println("5. 依代碼或名稱搜尋課程");

        System.out.println("6. 顯示所有課程與統計數據");

        System.out.println("7. 離開系統");

        System.out.println("------------------------------------");

    }

    private static void addCourse() {

        System.out.print("請輸入課程代碼: ");

        String id = scanner.nextLine().trim();



        if (id.isEmpty()) {

            System.out.println("[失敗] 課程代碼不可為空！");

            return;

        }

        if (findCourseById(id) != null) {

            System.out.println("[失敗] 課程代碼 \"" + id + "\" 已存在，不可重複！");

            return;

        }



        System.out.print("請輸入課程名稱: ");

        String name = scanner.nextLine().trim();



        if (name.isEmpty()) {

            System.out.println("[失敗] 課程名稱不可為空！");

            return;

        }



        try {

            System.out.print("請輸入課程人數上限 (容量): ");

            int capacity = Integer.parseInt(scanner.nextLine().trim());



            Course newCourse = new Course(id, name, capacity);

            courseList.add(newCourse);

            System.out.println("[成功] 已成功開課：" + name + " (容量: " + capacity + " 人)");

        } catch (NumberFormatException e) {

            System.out.println("[錯誤] 人數上限必須為整數數字！");

        } catch (IllegalArgumentException e) {

            System.out.println("[錯誤] 新增失敗：" + e.getMessage());

        }

    }

    private static void enrollCourse() {

        System.out.print("請輸入要加選的課程代碼: ");

        String id = scanner.nextLine().trim();



        Course course = findCourseById(id);

        if (course == null) {

            System.out.println("[失敗] 找不到代碼為 \"" + id + "\" 的課程！");

            return;

        }

        if (course.enroll()) {

            System.out.printf("[成功] 加選成功！「%s」目前選課人數: %d/%d%n",

                    course.getName(), course.getCurrentStudents(), course.getCapacity());

        } else {

            System.out.printf("[失敗] 選課失敗！「%s」課程已額滿 (%d/%d)！%n",

                    course.getName(), course.getCurrentStudents(), course.getCapacity());

        }

    }

    private static void dropCourse() {

        System.out.print("請輸入要退選的課程代碼: ");

        String id = scanner.nextLine().trim();



        Course course = findCourseById(id);

        if (course == null) {

            System.out.println("[失敗] 找不到代碼為 \"" + id + "\" 的課程！");

            return;

        }



        if (course.drop()) {

            System.out.printf("[成功] 退選成功！「%s」目前選課人數: %d/%d%n",

                    course.getName(), course.getCurrentStudents(), course.getCapacity());

        } else {

            System.out.printf("[失敗] 退選失敗！「%s」目前選課人數為 0，無人可退！%n", course.getName());

        }

    }

    private static void deleteCourse() {

        System.out.print("請輸入要刪除的課程代碼: ");

        String id = scanner.nextLine().trim();



        Course course = findCourseById(id);

        if (course == null) {

            System.out.println("[失敗] 找不到代碼為 \"" + id + "\" 的課程，無法刪除！");

            return;

        }



        courseList.remove(course);

        System.out.println("[成功] 已成功刪除課程：" + course.getName() + " (" + id + ")");

    }

    private static void searchCourse() {

        System.out.print("請輸入要搜尋的代碼或名稱關鍵字: ");

        String query = scanner.nextLine().trim();



        if (query.isEmpty()) {

            System.out.println("[錯誤] 搜尋條件不可為空！");

            return;

        }



        System.out.println("\n=== 搜尋結果 ===");

        boolean found = false;

        for (Course c : courseList) {

            if (c.getId().equalsIgnoreCase(query) ||

                c.getName().toLowerCase().contains(query.toLowerCase())) {

                System.out.println(c.toString());

                found = true;

            }

        }



        if (!found) {

            System.out.println("找不到符合 \"" + query + "\" 的課程。");

        }

    }

    private static void showAllAndStatistics() {

        System.out.println("\n=== 完整課程清單 ===");

        if (courseList.isEmpty()) {

            System.out.println("（目前沒有開設任何課程）");

            return;

        }



        int totalStudents = 0;

        ArrayList<String> fullCourses = new ArrayList<>();



        for (int i = 0; i < courseList.size(); i++) {

            Course c = courseList.get(i);

            System.out.printf("[%d] %s%n", i + 1, c.toString());

            totalStudents += c.getCurrentStudents();

            if (c.isFull()) {

                fullCourses.add(c.getName() + " (" + c.getId() + ")");

            }

        }

        System.out.println("\n------------------------------------");

        System.out.println("【選課系統統計數據】");

        System.out.println(" * 總開設課程數 : " + courseList.size() + " 門");

        System.out.println(" * 總選課總人次 : " + totalStudents + " 人次");

        if (fullCourses.isEmpty()) {

            System.out.println(" * 額滿課程清單 : (目前無額滿課程)");

        } else {

            System.out.println(" * 額滿課程清單 : " + String.join(", ", fullCourses));

        }

        System.out.println("------------------------------------");

    }

    private static Course findCourseById(String id) {

        if (id == null || id.trim().isEmpty()) {

            return null;

        }



        String cleanId = id.trim();

        for (Course c : courseList) {

            if (c.getId().equalsIgnoreCase(cleanId)) {

                return c;

            }

        }

        return null;

    }

}