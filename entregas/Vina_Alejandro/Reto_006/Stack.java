package entregas.Vina_Alejandro.Reto_006;


class Stack {
    private Node top;

    public void push(List data) {
        Node newNode = new Node(data.clone());
        newNode.next = top;
        top = newNode;
    }

    public List pop() {
        if (top == null) return null; // Si está vacía
        List data = top.datas;
        top = top.next;
        return data;
    }

    public List peek() {
        return (top == null) ? null : top.datas;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
    }
}
