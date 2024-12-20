package DesignPatterns.CreationalPatterns.Singleton;

public class SingletonExample {
    public static void main(String[] args) {
        Student minsu = new Student("민수");
        Student sujin = new Student("수진");

        Captain captain1 = Captain.electCaptain(minsu);
        Captain captain2 = Captain.electCaptain(sujin);

        captain1.ask(sujin);
    }
}

class Captain{

    private static Captain captain;
    private String name;

    private Captain(Student student){
        this.name = student.getName();
        System.out.println("반장 " + this.name + " 선출 완료.");
    }

    public static Captain electCaptain(Student student){
        if(captain == null){
            captain = new Captain(student);
        }
        else{
            System.out.println("이미 반장이 있습니다." + captain.name);
        }
        return captain;
    }

    public void ask(Student student){
        System.out.println("안녕하세요 " + student.getName() + " 학생!");
        System.out.println("저는 반장 " + captain.name + " 입니다.");
    }
}

class Student{
    String name;

    public Student(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}