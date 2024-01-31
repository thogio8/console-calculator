import java.util.Scanner;
import java.util.function.BiFunction;

public class CalculatriceConsole {
    enum Operation{
        ADDITION("+",CalculatriceConsole::addition),
        SOUSTRACTION("-",CalculatriceConsole::soustraction),
        MULTIPLICATION("*",CalculatriceConsole::mulitplication),
        DIVISION("/",CalculatriceConsole::division);

        private final String symbole;
        private final BiFunction<Double, Double, Double> operation;

        Operation(String symbole, BiFunction<Double, Double, Double> operation){
            this.symbole = symbole;
            this.operation = operation;
        }

        public static Operation fromString(String symbole){
            for(Operation op : Operation.values()){
                if(op.symbole.equals(symbole)){
                    return op;
                }
            }
            return null;
        }

        public double appliquer(double num1, double num2){
            return operation.apply(num1, num2);
        }
    }

    static double addition(double num1, double num2) {
        return num1 + num2;
    }

    static double soustraction(double num1, double num2) {
        return num1 - num2;
    }

    static double mulitplication(double num1, double num2) {
        return num1 * num2;
    }

    static double division(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Division par zéro.");
        }
        return num1 / num2;
    }

    static double lireNombre(Scanner scanner, String prompt){
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.print("La valeur saisie n'est pas correcte\n");
                scanner.next();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] operValides = {"+", "-", "*", "/"};
        double num1, num2;
        Operation oper;
        boolean continuer = true;

        while(continuer) {
            num1 = lireNombre(scanner, "Entrez le premier nombre : \n");
            num2 = lireNombre(scanner, "Entrez le deuxième nombre : \n");

            do {
                System.out.print("Choisissez l'opération (+, -, *, /): ");
                oper = Operation.fromString(scanner.next());
            } while (oper == null);

            try {
                System.out.println(oper.appliquer(num1, num2));
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }

            String reponse;
            do {
                System.out.print("\nVoulez-vous effectuer un autre calcul ? (O/N): ");
                reponse = scanner.next().toUpperCase();

                if (reponse.equals("N")) {
                    continuer = false;
                }
            }while (!reponse.equals("O") && !reponse.equals("N"));
        }
        scanner.close();
        System.out.print("Merci d'avoir utilisé ma calculatrice !");
    }
}