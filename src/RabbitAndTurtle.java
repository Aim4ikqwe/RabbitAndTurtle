class AnimalThread extends Thread {
    final private String name;
    private int priority;
    private int distanceCovered = 0; // Счётчик пройденных метров

    public AnimalThread(String name, int priority) {
        this.name = name;
        this.priority = priority;
        setPriority(priority); // Устанавливаем приоритет потока
    }

    @Override
    public void run() {
        while (distanceCovered < 100) { // Животные должны пробежать 100 метров
            distanceCovered++;
            System.out.println(name + " пробежал " + distanceCovered + " метров");
        }
    }

    public int getDistanceCovered() {
        return distanceCovered;
    }
}
public class RabbitAndTurtle {
    public static void main(String[] args) {
        // Создаем два потока: Кролик и Черепаха с разными приоритетами
        AnimalThread rabbit = new AnimalThread("Кролик", Thread.MAX_PRIORITY);
        AnimalThread turtle = new AnimalThread("Черепаха", Thread.MIN_PRIORITY);

        // Запускаем оба потока
        rabbit.start();
        turtle.start();

        // Динамическое изменение приоритетов на основе прогресса
        while (rabbit.isAlive() && turtle.isAlive()) {
            if (rabbit.getDistanceCovered() - turtle.getDistanceCovered() > 2) {
                // Если Кролик сильно впереди, уменьшаем его приоритет
                rabbit.setPriority(Thread.MIN_PRIORITY);
                turtle.setPriority(Thread.MAX_PRIORITY);
                System.out.println("Приоритеты изменены: Черепаха догоняет!");
            } else if (turtle.getDistanceCovered() - rabbit.getDistanceCovered() > 2) {
                // Если Черепаха сильно впереди, восстанавливаем приоритеты
                rabbit.setPriority(Thread.MAX_PRIORITY);
                turtle.setPriority(Thread.MIN_PRIORITY);
                System.out.println("Приоритеты изменены: Кролик снова убегает!");
            }
        }
    }
}