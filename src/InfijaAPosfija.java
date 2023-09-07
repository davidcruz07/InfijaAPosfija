import java.util.Scanner;
import java.util.Stack;

public class InfijaAPosfija {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la expresión infija: ");
        String expresionInfija = scanner.nextLine();
        scanner.close();

        String expresionPosfija = infijaAPosfija(expresionInfija);
        System.out.println("Expresión infija: " + expresionInfija);
        System.out.println("Expresión posfija: " + expresionPosfija);
    }

    //precedencia del operador
    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1; // el operador no es valido
        }
    }

    //convertir una expresión infija a posfija
    public static String infijaAPosfija(String expresionInfija) {
        StringBuilder posfija = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (char token : expresionInfija.toCharArray()) {
            if (Character.isLetterOrDigit(token)) {

                posfija.append(token);
            } else if (token == '(') {

                pila.push(token);
            } else if (token == ')') {

                while (!pila.isEmpty() && pila.peek() != '(') {
                    posfija.append(pila.pop());
                }
                if (!pila.isEmpty() && pila.peek() != '(') {

                    return "Expresión inválida";
                } else {
                    pila.pop(); // eliminar el '('
                }
            } else {

                while (!pila.isEmpty() && precedencia(token) <= precedencia(pila.peek())) {
                    posfija.append(pila.pop());
                }
                pila.push(token);
            }
        }

        // agregar los operadores en la pila
        while (!pila.isEmpty()) {
            if (pila.peek() == '(') {

                return "Expresión inválida";
            }
            posfija.append(pila.pop());
        }

        return posfija.toString();
    }
}
