package course1.lesson2;

public class MethodApp {

    public static final String TEMPLATE = "int = %d string = %s\n"; // Обозначается константа

    public static void main(String[] args) {
            int tp = 100;
            int totalPrice = 100;


            int v1 = multiplication(4, 10);
            int v2 = multiplication(5,6.0);
            double v4 = 10.0;
            int v3 = multiplication(v1, v2);

        PrintUtil.printFormatted("v1 = " + v1 + " v2 = " + v2 + " v3 = " + multiplication(v1, v2));

        PrintUtil.printFormatted("string to format");

        System.out.printf(TEMPLATE, 10, "str");
        System.out.println();
        System.out.printf(TEMPLATE, 113, "str12313r");

    }


    public static int multiplication(int n1, int n2) {
        int result = n1 * n2;
        return result;
    }

    public static int multiplication(int n1, double n2) {
        return (int) (n1 * n2);
    }

    public static int multiplication(double n1, int n2) {
        return (int) (n1 * n2);
    }



    //Модификатор доступа + тип значения + имя + набор параметров

    //naming - ClassName MyClassName каждое слово пишется с большой буквы, все остальное с маленькой (camelCase)
    //methods, variables - lowercaseUpperCase methodName variableName

    //CONSTANTS - UPPER_CASE (snake_case) пишутся через _ (нижнее подчеркивание)


    //type - void / real type

    //Модификатор доступа
    //public - protected - (no modifier) default - private


}
