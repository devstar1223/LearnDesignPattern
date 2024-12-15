> 📚 **객체를 공유하여 메모리 절약을 극대화하는 패턴**

---

# 설명

- 동일한 객체가 여러 번 사용될 떄, 메모리 효율을 높이기 위해 동일한 데이터를 공유하는 방식
- 객체의 상태를 **내부 상태**와 **외부 상태**로 나눈다
    - 내부 상태
        - 객체가 공유할 수 있는 부분
        - 불변의 값
    - 외부 상태
        - 객체마다 고유한 값
        - 객체를 사용할 때마다 변할 수 있다.

## 특징

- 여러 객체가 동일한 내부 상태를 공유하고, 외부 상태만을 각기 다르게 가짐.
- 객체의 수가 많을때 메모리 절약의 극대화 가능.

## 실생활의 예제

- 도서관에는 많은 책이 있다.
- 도서관은 책을 구매하여 도서관에 소장한다.
- 이용자가 요청한 책이 도서관에 있다면, 도서관은 책을 대출해준다.
- 이용자가 요청한 책이 도서관에 없다면, 도서관은 책을 구매하여 소장한 후 대출해준다.
- 도서관의 이용자는 모두 다르지만, 도서관 책의 이름은 항상 같다.

---

# 코드

```java
package StructuralPatterns;

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

```

- `Book`
    - 책의 이름은 변경되지 않는다. → **불변의 내부상태**
- `Student`
    - 학생들은 각기 다른 대출기록을 가질 수 있다 → **개별적인 외부상태**
    - 대출 받은 책을 읽는다. → 책은 도서관이 보유한 책과 같은 객체이다.
- `Library`
    - 책을 구매할때, 그 책이 있으면 재사용하고, 없으면 새로 구매한다. → **재사용**을 통한 메모리 절약
- 만약 같은 책이 두 명의 학생에게 대출된다면, **같은 Book 객체**를 두명의 학생이 공유한다. *(실제로는 불가능하지만.. 전자책 이라고 치자.)*

---

# 장/단점

## 장점

- 메모리 절약
    - 동일한 객체를 공유하여 메모리 사용 최적화
- 객체 생성 비용 절감
    - 객체의 수가 많을 경우, 새 객체를 생성하지 않고 기존 객체 재사용이 가능하다.

## 단점

- 복잡성 증가
    - 객체의 내부 상태와 외부 상태를 분리해야 하므로, 코드가 복잡해 질 수 있다.
- 성능 저하 가능성
    - 객체 관리가 복잡해져, 내부 상태를 공유하기 위한 추가적인 처리가 필요하다.

---

# 사용 사례

- 게임 개발
    - 게임 캐릭터나 객체에서 공통된 속성 공유
    - 각 객체는 위치나 상태만 다르게 유지
- 문서 처리 시스템
    - 텍스트의 글꼴, 크기 등의 공통 속성은 공유
    - 텍스트의 내용만 다르게 유지
- 그래픽 시스템
    - 다수의 동일한 이미지를 화면에 표시할 때 메모리 절약