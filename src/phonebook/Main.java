package phonebook;

public class Main {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();
        System.out.println(book.linearSearch());
        System.out.println(book.jumpSearch());
        System.out.println(book.binarySearch());
        System.out.println(book.hashTableSearch());
    }
}
