import functions.*;

public class Main {
    public static void main(String[] args) {

            TabulatedFunction test_fun = new LinkedListTabulatedFunction(1, 10, 10);

            // Заполняем функцию значениями y = 5x
            for (int i = 0; i < test_fun.getPointsCount(); i++) {
                test_fun.setPointY(i, test_fun.getPointX(i) * 5);
            }

            System.out.println("Создали функцию через первый конструктор (y=5x)");
            for (int i = 0; i < test_fun.getPointsCount(); i++) {
                System.out.println("X: " + test_fun.getPointX(i) + " " + "Y: " + test_fun.getPointY(i));
            }

            System.out.println(" ");

            System.out.println(test_fun.getFunctionValue(4.5) + " Функция определения значения в произвольной точке 4.5");
            System.out.println(" ");

            System.out.println("Добавили у=80 по индексу 3");
            test_fun.setPointY(3, 80);
            for (int i = 0; i < test_fun.getPointsCount(); i++) {
                System.out.println("X: " + test_fun.getPointX(i) + " " + "Y: " + test_fun.getPointY(i));
            }

            System.out.println(" ");

            System.out.println(test_fun.getPointsCount() + " Количество точек");
            System.out.println(" ");

            // Операции с изменением X, которые могут вызвать исключение
            FunctionPoint test_point = new FunctionPoint(8.5, 17);
            try {
                test_fun.addPoint(test_point);
                System.out.println("Добавили точку test_point 8.5, 17");
            } catch (InappropriateFunctionPointException e) {
                System.out.println("Ошибка при добавлении точки: " + e.getMessage());
            }

            for (int i = 0; i < test_fun.getPointsCount(); i++) {
                System.out.println("Х: " + test_fun.getPointX(i) + " " + "Y: " + test_fun.getPointY(i));
            }

            System.out.println(" ");

            // Удаление точки
            try {
                test_fun.deletePoint(9);
                System.out.println("Удалили элемент с индексом 9");
            } catch (Exception e) {
                System.out.println("Ошибка при удалении точки: " + e.getMessage());
            }

            for (int i = 0; i < test_fun.getPointsCount(); i++) {
                System.out.println("Х: " + test_fun.getPointX(i) + " " + "Y: " + test_fun.getPointY(i));
            }

            System.out.println(" ");

            // Тестирование операций изменения X
            try {
                test_fun.setPointX(2, 3.5);
                System.out.println("Успешно изменили X точки с индексом 2 на 3.5");
            } catch (InappropriateFunctionPointException e) {
                System.out.println("Ошибка при изменении X точки: " + e.getMessage());
            }

            // Попытка установить некорректный X
            try {
                test_fun.setPointX(1, 100); // Вероятно, выйдет за границы
                System.out.println("Успешно изменили X точки с индексом 1 на 100");
            } catch (InappropriateFunctionPointException e) {
                System.out.println("Ошибка при изменении X точки на 100: " + e.getMessage());
            }

            // Замена точки
            FunctionPoint newPoint = new FunctionPoint(5.5, 25);
            try {
                test_fun.setPoint(4, newPoint);
                System.out.println("Успешно заменили точку с индексом 4");
            } catch (InappropriateFunctionPointException e) {
                System.out.println("Ошибка при замене точки: " + e.getMessage());
            }

            System.out.println(" ");

            double[] testarr = {4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.0};
            TabulatedFunction test_fun2 = new LinkedListTabulatedFunction(1, 5, testarr);

            System.out.println("Проверка второго конструктора");
            for (int i = 0; i < test_fun2.getPointsCount(); i++) {
                System.out.println("Х: " + test_fun2.getPointX(i) + " " + "Y: " + test_fun2.getPointY(i));
            }


        // test_fun2.setPointY(999,999);
           // test_fun2.deletePoint(999);
          //  test_fun2.setPointY(999,999);


        double[] values2 = {1.0, 3.0, 2.0, 4.0, 5.0, 6.0};
        TabulatedFunction test_fun3 = new LinkedListTabulatedFunction(1, 3.0, values2);
        try {
            TabulatedFunction funcError = new LinkedListTabulatedFunction(5.0, 0.0, 3);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            test_fun3.getPoint(100);
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            test_fun3.addPoint(new FunctionPoint(1.0, 5.0)); // Дублирование X
        } catch (InappropriateFunctionPointException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < test_fun3.getPointsCount(); i++) {
            System.out.println("Х: " + test_fun3.getPointX(i) + " " + "Y: " + test_fun3.getPointY(i));
        }


        TabulatedFunction test_fun4 = new LinkedListTabulatedFunction(4.0, 3.0, values2);

    }
}
