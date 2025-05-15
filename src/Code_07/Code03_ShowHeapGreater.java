package Code_07;


import java.util.Comparator;

public class Code03_ShowHeapGreater {

    static class Student {
        int id;
        String name;
        int grads;

        public Student(int id, String name, int grads) {
            this.id = id;
            this.name = name;
            this.grads = grads;
        }

        @Override
        public String toString() {
            return "id:" + id;

//            return "Student{" +
//                    "id=" + id +
//                    ", name='" + name + '\'' +
//                    ", grads=" + grads +
//                    '}';
        }
    }


    public static void main(String[] args) {
        HeapGreater<Student> heapGreater = new HeapGreater<>(Comparator.comparingInt(o -> o.id));


        Student student1 = new Student(1, "wupeng1", 81);
        Student student2 = new Student(2, "wupeng2", 82);
        Student student3 = new Student(3, "wupeng3", 83);
        Student student4 = new Student(4, "wupeng4", 84);
        Student student5 = new Student(5, "wupeng5", 85);

        heapGreater.push(student1);
        heapGreater.push(student2);
        heapGreater.push(student3);
        heapGreater.push(student4);
        heapGreater.push(student5);

        heapGreater.remove(student5);
        heapGreater.print();
    }
}
