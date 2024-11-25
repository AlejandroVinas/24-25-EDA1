package entregas.Vina_Alejandro.Reto_006;

class Node {
    String data;
    List datas;
    Node next;

    Node(String data) {
        this.data = data;
        this.datas = null;
        this.next = null;
    }

    Node(List datas) {
        this.data = null;
        this.datas = datas;
        this.next = null;
    }
}
