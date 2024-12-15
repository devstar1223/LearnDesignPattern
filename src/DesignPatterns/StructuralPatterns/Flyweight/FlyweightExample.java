package StructuralPatterns.Flyweight;

import java.util.*;

public class FlyweightExample {
    public static void main(String[] args) {
        Library schoolLibrary = new Library();

        Student minsu = new Student("김민수");
        Student sujin = new Student("이수진");

        schoolLibrary.buyBook("클린코드");

        schoolLibrary.lendBook(minsu,"클린코드");
        schoolLibrary.lendBook(sujin,"오브젝트");

        schoolLibrary.buyBook("오브젝트");

        minsu.readBook();
        sujin.readBook();
    }
}

class Book{
    private String name;

    public Book(String bookName){
        this.name = bookName;
    }

    public String getBookName(){
        return this.name;
    }
}

class Student{
    private String name;
    private Book haveBook;

    public Student(String studentname) {
        this.name = studentname;
    }
    public void borrowBook(Book book){
        this.haveBook = book;
    }
    public void readBook(){
        System.out.println(name+" 학생이 "+haveBook.getBookName()+"책을 읽고 있습니다.");
    }
    public String getName(){
        return name;
    }
}

class Library{

    Map<String,Book> libraryHaveBookMap = new HashMap<>();

    public void buyBook(String bookName) {
        if (libraryHaveBookMap.containsKey(bookName)) {
            System.out.println(bookName+"책은 이미 도서관에 있습니다.");
            return;
        }
        Book purchasedBook = new Book(bookName);
        System.out.println("도서 구매 완료 : " + purchasedBook.getBookName());
        libraryHaveBookMap.put(purchasedBook.getBookName(), purchasedBook);
    }

    public void lendBook(Student borrower, String bookName){
        System.out.println(borrower.getName()+" 학생의 도서 대출 요청 : " + bookName);
        Book requestBook = libraryHaveBookMap.get(bookName);
        if(requestBook == null){
            System.out.println("요청된 "+ bookName +"책이 도서관에 없어, 구매를 진행합니다.");
            buyBook(bookName);
            requestBook = libraryHaveBookMap.get(bookName);
        }
        System.out.println("도서 대출 완료 : " + bookName);
        borrower.borrowBook(requestBook);
    }
}
