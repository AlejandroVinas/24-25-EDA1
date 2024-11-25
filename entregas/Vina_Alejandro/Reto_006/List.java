package entregas.Vina_Alejandro.Reto_006;

class List {
    private Node head;

    public void append(String data) {
        if (head == null) {
            head = new Node(data);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(data);
        }
    }

    public String get(int index) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) return current.data;
            current = current.next;
            count++;
        }
        return "";
    }

    public void set(int index, String data) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                current.data = data;
                return;
            }
            current = current.next;
            count++;
        }
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public List clone() {
        List clone = new List();
        Node current = head;
        while (current != null) {
            clone.append(current.data);
            current = current.next;
        }
        return clone;
    }

    public void copyFrom(List other) {
        head = null;
        Node current = other.head;
        while (current != null) {
            append(current.data);
            current = current.next;
        }
    }
}
