package entregas.Vinas_Alejandro.Reto_006;

import java.util.Scanner;

class Edlin {
    public static void main(String[] args) {
        int activeLine[] = {1};
        List document = new List();
        document.append("Bienvenidos al editor EDLIN");
        document.append("Utilice el menu inferior para editar el texto");
        document.append("------");
        document.append("[L] permite definir la linea activa");
        document.append("[E] permite editar la linea activa");
        document.append("[I] permite intercambiar dos lineas");
        document.append("[B] borra el contenido de la linea activa");
        document.append("[C] copiar una linea");
        document.append("[P] pegar en una linea");
        document.append("[Z] deshacer");
        document.append("[Y] rehacer");
        document.append("[S] sale del programa");

        Stack undoStack = new Stack();
        Stack redoStack = new Stack();
        String clipboard = null;

        do {
            print(document, activeLine);
        } while (processActions(document, activeLine, undoStack, redoStack, clipboard));
    }

    static void print(List document, int[] activeLine) {
        clearScreen();
        printHorizontalLine();
        for (int line = 0; line < document.size(); line++) {
            System.out.println(line + separator(line, activeLine[0]) + document.get(line));
        }
        printHorizontalLine();
    }

    static String separator(int line, int activeLine) {
        return line == activeLine ? ":*| " : ": | ";
    }

    static void printHorizontalLine() {
        System.out.println("-".repeat(50));
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static boolean processActions(List document, int[] activeLine, Stack undoStack, Stack redoStack, String clipboard) {
        System.out.println("Comandos: [L]inea activa | [E]ditar | [I]ntercambiar | [B]orrar | [C]opiar | [P]egar | [Z]deshacer | [Y]rehacer | [S]alir");

        switch (askChar()) {
            case 'S': case 's':
                return false;
            case 'L': case 'l':
                setActiveLine(document, activeLine);
                break;
            case 'E': case 'e':
                pushToUndo(document, undoStack);
                clearStack(redoStack);
                edit(document, activeLine);
                break;
            case 'I': case 'i':
                pushToUndo(document, undoStack);
                clearStack(redoStack);
                exchangeLines(document);
                break;
            case 'B': case 'b':
                pushToUndo(document, undoStack);
                clearStack(redoStack);
                delete(document, activeLine);
                break;
            case 'C': case 'c':
                clipboard = copy(document, activeLine);
                break;
            case 'P': case 'p':
                pushToUndo(document, undoStack);
                clearStack(redoStack);
                paste(document, activeLine, clipboard);
                break;
            case 'Z': case 'z':
                undo(document, undoStack, redoStack);
                break;
            case 'Y': case 'y':
                redo(document, undoStack, redoStack);
                break;
        }
        return true;
    }

    static char askChar() {
        Scanner input = new Scanner(System.in);
        return input.next().charAt(0);
    }

    static void delete(List document, int[] activeLine) {
        document.set(activeLine[0], "");
    }

    static void exchangeLines(List document) {
        System.out.print("Indique primera línea a intercambiar: ");
        int originLine = askInt();
        System.out.print("Indique segunda línea a intercambiar: ");
        int destinationLine = askInt();

        String temp = document.get(originLine);
        document.set(originLine, document.get(destinationLine));
        document.set(destinationLine, temp);
    }

    static void edit(List document, int[] activeLine) {
        System.out.println("EDITANDO> " + document.get(activeLine[0]));
        document.set(activeLine[0], askString());
    }

    static String copy(List document, int[] activeLine) {
        return document.get(activeLine[0]);
    }

    static void paste(List document, int[] activeLine, String clipboard) {
        if (clipboard != null) {
            document.set(activeLine[0], clipboard);
        } else {
            System.out.println("El portapapeles está vacío.");
        }
    }

    static void setActiveLine(List document, int[] activeLine) {
        System.out.print("Indique la nueva línea activa: ");
        activeLine[0] = askInt();
    }

    static void pushToUndo(List document, Stack undoStack) {
        undoStack.push(document.clone());
    }

    static void undo(List document, Stack undoStack, Stack redoStack) {
        if (!undoStack.isEmpty()) {
            redoStack.push(document.clone());
            document.copyFrom(undoStack.pop());
        } else {
            System.out.println("No hay acciones para deshacer.");
        }
    }

    static void redo(List document, Stack undoStack, Stack redoStack) {
        if (!redoStack.isEmpty()) {
            undoStack.push(document.clone());
            document.copyFrom(redoStack.pop());
        } else {
            System.out.println("No hay acciones para rehacer.");
        }
    }

    static void clearStack(Stack stack) {
        stack.clear();
    }

    static int askInt() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    static String askString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
