package Test_app;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // валидные арабские цифры
    String[] validArabic = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    // валидные римские цифры
    String[] validRoman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    // валидные операторы
    String[] validOperators = {"+", "-", "*", "/"};
    // раздельный инпут
    String[] separatedInput;

    //Решение должно содержать данный метод
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input: ");  // для отладки
        String expr = in.nextLine();
//        System.out.println("Your input: \n" + expr2 + "\n"); // для отладки
        in.close();

        Main calculator = new Main(); // создаем экземпляр класса
        calculator.calc(expr); // запускаем калькулятор с полученным выражением от пользователя

    }

    public String calc(String inputString) {

        if (!validCheck(inputString)) {
            System.out.println("Output: \nИсключение");
//            System.out.println("Валидация ОШИБОЧНА!!!");
        } else {
//            System.out.println("Валидация УСПЕШНА!!!");
            System.out.println("Output: \n" + calcHelp()); // печатаем финальный результат
        }
        return null;
    }

    public String calcHelp() { // дополнительный метод поомщник, проверяет оператор и отправляет ввод в соотв метод
        switch (this.separatedInput[1]) {
            case ("+"):
                return addition(this.separatedInput[0], this.separatedInput[2]);
            case ("-"):
                return subtraction(this.separatedInput[0], this.separatedInput[2]);
            case ("*"):
                return multiplication(this.separatedInput[0], this.separatedInput[2]);
            case ("/"):
                return division(this.separatedInput[0], this.separatedInput[2]);
            default:
                System.out.println("Исключение");
//                System.out.println("оператор некорректен");
            return null;
        }
    }

    public Boolean validCheck(String inputString) { // проверка ввода на валидность, передали ответ тру или фолс

        // парсим ввод пользователя в массив
        this.separatedInput = inputString.split(" ");
//        System.out.println(Arrays.deepToString(separatedInput));
//        System.out.println(separatedInput[0] + separatedInput[1] + separatedInput[2]);

        // проверяем поступивший ввод на валидность
        if (this.separatedInput.length != 3) { // проверка на количество символов
//            System.out.println("Исключение 4");
//            System.out.println("введиено больше или меньше 3х символов");
            return false;
        } else if ((!Arrays.asList(this.validArabic).contains(this.separatedInput[0])) && (!Arrays.asList(this.validRoman).contains(this.separatedInput[0]))){
//            System.out.println("Исключение 5");
//            System.out.println("первое число некорректно");
            return false;
        } else if ((!Arrays.asList(this.validArabic).contains(this.separatedInput[2])) && (!Arrays.asList(this.validRoman).contains(this.separatedInput[2]))){
//            System.out.println("Исключение 6");
//            System.out.println("второе число некорректно");
            return false;
        } else if ((!Arrays.asList(this.validOperators).contains(this.separatedInput[1]))){
//            System.out.println("Исключение 7");
//            System.out.println("оператор некорректен");
            return false;
        } else if ((Arrays.asList(this.validArabic).contains(this.separatedInput[0])) && (Arrays.asList(this.validArabic).contains(this.separatedInput[2]))) {
            // Метод умеет работать только с арабскими одновременно
//            System.out.println("Выполнено: метод умеет работать только с арабскими цифрами одновременно");
            return true;
        } else if ((Arrays.asList(this.validRoman).contains(this.separatedInput[0])) && (Arrays.asList(this.validRoman).contains(this.separatedInput[2]))) {
            // Метод умеет работать только с римскими цифрами одновременно
//            System.out.println("Исключение 9");
//            System.out.println("Выполнено: метод умеет работать только с римскими цифрами одновременно");
            return true;
        }
        else {
//            System.out.println("Исключение 10");
//            System.out.println("ИСКЛЮЧЕНИЕ: Метод умеет работать только с арабскими или римскими цифрами одновременно");
            return false;
        }
    }



    // метод сложения
    public String addition(String a, String b) {
        // если римские
        if (Arrays.asList(this.validRoman).contains(this.separatedInput[0])) {
          int getIndex1 = Arrays.asList(this.validRoman).indexOf(this.separatedInput[0]) + 1;
          int getIndex2 = Arrays.asList(this.validRoman).indexOf(this.separatedInput[2]) + 1;
          int result = getIndex1 + getIndex2;

          if (result >= 10){
            return RomanNumber.toRoman(result);
          }
          return this.validRoman[result-1];
        } else return Integer.toString(Integer.parseInt(a) + Integer.parseInt(b)); // если арабские
    }

    // метод вычитания
    public String subtraction(String a, String b) {
        // если римские
        if (Arrays.asList(this.validRoman).contains(this.separatedInput[0])) {
            int getIndex1 = Arrays.asList(this.validRoman).indexOf(this.separatedInput[0]) + 1;
            int getIndex2 = Arrays.asList(this.validRoman).indexOf(this.separatedInput[2]) + 1;
            int result = getIndex1 - getIndex2;

            if (result > 10){
                return RomanNumber.toRoman(result);
            } else if (result <= 0){
                return "Исключение";
//                return "Исключение! результатом операции с римскими цифрами не может быть 0 или отрицательное число";
            } return this.validRoman[result-1];
        } else return Integer.toString((Integer.parseInt(a) - Integer.parseInt(b))); // если арабские
    }

    // метод умножения
    public String multiplication(String a, String b) {
        // если римские
        if (Arrays.asList(this.validRoman).contains(this.separatedInput[0])) {
            int getIndex1 = Arrays.asList(this.validRoman).indexOf(this.separatedInput[0]) + 1;
            int getIndex2 = Arrays.asList(this.validRoman).indexOf(this.separatedInput[2]) + 1;
            int result = getIndex1 * getIndex2;

            if (result > 10) {
                return RomanNumber.toRoman(result);
            } else if (result <= 0) {
                return "Исключение";
//                return "Исключение! результатом операции с римскими цифрами не может быть 0 или отрицательное число";
            } return this.validRoman[result - 1];
        } else return Integer.toString(Integer.parseInt(a) * Integer.parseInt(b)); // если арабские
    }

    // метод деления
    public String division(String a, String b) {
        // если римские
        if (Arrays.asList(this.validRoman).contains(this.separatedInput[0])) {
            int getIndex1 = Arrays.asList(this.validRoman).indexOf(this.separatedInput[0]) + 1;
            int getIndex2 = Arrays.asList(this.validRoman).indexOf(this.separatedInput[2]) + 1;
            int result = getIndex1 / getIndex2;

            if (result > 10) {
                return RomanNumber.toRoman(result);
            } else if (result <= 0) {
                return "Исключение";
//                return "Исключение! результатом операции с римскими цифрами не может быть 0 или отрицательное число";
            } return this.validRoman[result - 1];
        } return Integer.toString(Integer.parseInt(a) / Integer.parseInt(b));
    }
}

