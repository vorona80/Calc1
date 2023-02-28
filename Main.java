import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public String calc (String input){
        String itog="";
        char c;
        int index, a=0, b=0;
        Rimskie [] rim = Rimskie.values();
        String rimskie = Arrays.toString(rim);
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == '+' || c == '-' || c == '/' || c == '*') {
                index = i;
                String first = input.substring(0, index).trim();
                String operator = input.substring(index, index + 1);
                String second = input.substring(++index).trim();
                if (second.length()>2){
                    try {throw new KalculyayorExeption();
                    }catch (KalculyayorExeption e){
                        System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    }break;
                }
                if (first.length() == 0 || operator.length() == 0 || second.length() == 0) {
                    try {throw new KalculyayorExeption();
                    } catch (KalculyayorExeption e) {
                        System.out.println("throws Exception //т.к. строка не является математической операцией ");
                    }break;
                }
                int f = rimskie.indexOf(first);
                int s = rimskie.indexOf(second);
                if (f>=0 && s>=0){
                    calcRim(first,operator,second);break;// System.out.println("Вычисляем Римские");
                }
                else if ((f<0 && s>0) || (f>0 && s<0) ){
                    try{throw new KalculyayorExeption();
                    } catch (KalculyayorExeption e){
                        System.out.println("throws Exception //т.к. используются одновременно разные системы счисления" );}
                    break;}

                a = Integer.parseInt(first);
                b = Integer.parseInt(second);
                if (a < 1 || a > 10 || b < 1 || b > 10) {
                    try{throw new KalculyayorExeption();
                    }catch (KalculyayorExeption e) {
                        System.out.println("throws Exception //т.к. введено некорректнок значение, допускаются числа от 1 до 10");
                    }break;
                }
                switch (operator) {
                    case "+": itog =Integer.toString(a + b);break;
                    case "-": itog =Integer.toString(a - b);break;
                    case "/": itog =Integer.toString(a / b);break;
                    case "*": itog =Integer.toString(a * b);break;
                }
            }
        }
        return itog;
    }
    public void calcRim(String first, String operator,String second){
        String itog ="";
        Rimskie[]rim = Rimskie.values();
        Rimskie perevod1 = Rimskie.valueOf(first);
        Rimskie perevod2 = Rimskie.valueOf(second);
        int ar = perevod1.ordinal()+1;
        int br = perevod2.ordinal()+1;

        if (ar < 1 || ar > 10 || br < 1 || br > 10) {
            try {throw new KalculyayorExeption();
            } catch (KalculyayorExeption e) {
                System.out.println("throws Exception //т.к. введено некорректнок значение, допускаются числа от 1 до 10");}
        } else { switch (operator) {
            case "+": System.out.println(rim[ar + br - 1]);break;
            case "-": try {new ArrayIndexOutOfBoundsException();
                System.out.println(rim[ar - br - 1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");}break;
            case "/": System.out.println(rim[ar / br - 1]);break;
            case "*": System.out.println(rim[ar * br - 1]);break;
        }
        }
    }
}class Main1{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Калькулятор умеет выполнять операции +,-,*,/ с двумя числами: a + b, a - b, a * b, a / b");
        System.out.println("Введите математическое выражение");
        String input1 = scan.nextLine();
        if (input1.length() == 0) {
            try {throw new KalculyayorExeption();
            }catch (KalculyayorExeption e){
                System.out.println("throws Exception //вы ничего не ввели");}return;
        }
        if (input1.contains("+") || input1.contains("-") || input1.contains("/") || input1.contains("*")) {
        } else {
            try {throw new KalculyayorExeption();
            }catch (KalculyayorExeption e){
                System.out.println("throws Exception //т.к. строка не является математической операцией ");}return;
        }
        Main m = new Main();
        System.out.println(m.calc(input1));
    }
}