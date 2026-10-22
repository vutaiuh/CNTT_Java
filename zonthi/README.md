# Java Core — Cheat Sheet
Tổng hợp cú pháp từ bài tập thực hành JAVA
---
## Mục lục
- [String.split()](#stringsplit)
- [String.substring()](#stringsubstring)
- [Các method String khác](#các-method-string-khác)
- [Mảng (Array)](#mảng-array)
- [ArrayList](#arraylist)
- [Linked List](#linked-list)
- [So sánh Array vs ArrayList vs LinkedList](#so-sánh-array-vs-arraylist-vs-linkedlist)
- [Class & Object (OOP)](#class--object-oop)
- [Comparator — sort object](#comparator--sort-object)
- [Cây nhị phân tìm kiếm (BST)](#cây-nhị-phân-tìm-kiếm-bst)
- [File I/O — Serialization](#file-io--serialization)
---
## String.split()
Cắt chuỗi thành mảng dựa vào ký tự phân cách.
**Cú pháp:**
```java
String[] arr = str.split("delimiter");
String[] arr = str.split("delimiter", limit); // giới hạn số phần tử
```
**Ví dụ — tách theo khoảng trắng:**
```java
String hoTen = "Nguyen Van An";
String[] parts = hoTen.split(" ");
// parts[0] = "Nguyen"
// parts[1] = "Van"
// parts[2] = "An"
System.out.println(parts[0]);   // Nguyen
System.out.println(parts.length); // 3
```
**Ví dụ — tách CSV (tách theo dấu phẩy):**
```java
String csv = "101,Nguyen Van An,nam,20";
String[] fields = csv.split(",");
int    id     = Integer.parseInt(fields[0]); // 101
String name   = fields[1];                   // "Nguyen Van An"
String gender = fields[2];                   // "nam"
int    age    = Integer.parseInt(fields[3]); // 20
```
> ⚠️ **Lưu ý:** Nếu delimiter là dấu chấm `.`, phải viết `"\\."` vì `.` là ký tự đặc biệt trong regex.
---
## String.substring()
Cắt một phần chuỗi từ `beginIndex` đến `endIndex`.
**Cú pháp:**
```java
str.substring(beginIndex)           // từ beginIndex đến cuối
str.substring(beginIndex, endIndex) // từ beginIndex đến endIndex (không bao gồm endIndex)
```
**Ví dụ:**
```java
String s = "LapTrinhJava";
//          0123456789...
System.out.println(s.substring(0, 3));  // "Lap"
System.out.println(s.substring(3, 8));  // "Trinh"
System.out.println(s.substring(8));     // "Java"
```
**Kết hợp với `indexOf()` — tách email:**
```java
String email = "anhgau@gmail.com";
int at = email.indexOf('@');
String user   = email.substring(0, at); // "anhgau"
String domain = email.substring(at + 1); // "gmail.com"
```
---
## Các method String khác
| Method | Mô tả |
|--------|-------|
| `.length()` | Trả về độ dài chuỗi |
| `.charAt(i)` | Lấy ký tự tại vị trí `i` |
| `.indexOf(c)` | Vị trí xuất hiện đầu tiên của ký tự/chuỗi con |
| `.toUpperCase()` | Chuyển thành chữ HOA |
| `.toLowerCase()` | Chuyển thành chữ thường |
| `.trim()` | Xóa khoảng trắng đầu/cuối |
| `.equals(s2)` | So sánh bằng (phân biệt hoa/thường) |
| `.compareTo(s2)` | So sánh thứ tự từ điển — dùng trong sort |
| `.contains(s)` | Kiểm tra chuỗi có chứa `s` không |
| `.replace(a, b)` | Thay thế tất cả `a` bằng `b` |
**Ví dụ:**
```java
String s = "  Hello World  ";
System.out.println(s.trim());               // "Hello World"
System.out.println(s.trim().toUpperCase()); // "HELLO WORLD"
System.out.println(s.trim().length());      // 11
System.out.println(s.trim().charAt(0));     // 'H'
System.out.println(s.contains("World"));   // true
// So sánh chuỗi (dùng trong bài Customer):
if (gender.compareTo("nam") == 0)      { /* là nam */ }
if (gender.equalsIgnoreCase("nu"))     { /* là nữ */ }
```
---
## Mảng (Array)
### Khai báo & nhập liệu
```java
int[] a = new int[n];        // khai báo mảng n phần tử
int[] a = {1, 2, 3};        // khởi tạo trực tiếp
a[i]                         // truy cập phần tử thứ i (từ 0)
a.length                     // số phần tử
```
```java
Scanner sc = new Scanner(System.in);
System.out.print("Nhap n: ");
int n = sc.nextInt();
int[] a = new int[n];
for (int i = 0; i < n; i++) {
    System.out.print("a[" + i + "]: ");
    a[i] = sc.nextInt();
}
```
### Tìm max, min, trung bình
```java
int max = a[0], min = a[0], sum = 0;
for (int i = 0; i < n; i++) {
    if (a[i] > max) max = a[i];
    if (a[i] < min) min = a[i];
    sum += a[i];
}
double tb = (double) sum / n;
```
### Sắp xếp (Selection Sort)
```java
// Tăng dần — O(n²)
for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {
        if (a[i] > a[j]) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
// In tăng dần:
for (int i = 0; i < n; i++) System.out.print(a[i] + " ");
// In giảm dần (sau khi đã sort tăng):
for (int i = n - 1; i >= 0; i--) System.out.print(a[i] + " ");
```
### Tìm kiếm nhị phân (Binary Search)
> ⚠️ Mảng phải được sắp xếp trước.
```java
public static int timKiem(int[] a, int n, int x) {
    int left = 0, right = n - 1;
    while (left <= right) {
        int mid = (left + right) / 2;
        if      (a[mid] == x) return mid;      // tìm thấy
        else if (a[mid] <  x) left  = mid + 1; // tìm nửa phải
        else                  right = mid - 1; // tìm nửa trái
    }
    return -1; // không tìm thấy
}
```
### Thêm & Xóa phần tử
```java
// Thêm vào đúng vị trí (mảng đã sort)
public static int them(int[] a, int n, int x) {
    int i = n - 1;
    while (i >= 0 && a[i] > x) { // dời phần tử lớn hơn x sang phải
        a[i + 1] = a[i];
        i--;
    }
    a[i + 1] = x;  // chèn x vào vị trí thích hợp
    return n + 1;  // trả về độ dài mới
}
// Xóa tại vị trí pos
public static int xoa(int[] a, int n, int pos) {
    for (int i = pos; i < n - 1; i++) {
        a[i] = a[i + 1]; // dời trái
    }
    return n - 1; // trả về độ dài mới
}
```
> 💡 **Mẹo:** Khai báo mảng dư chỗ khi cần thêm: `int[] a = new int[100];`
---
## ArrayList
### Cơ bản
```java
import java.util.ArrayList;
ArrayList<Integer> list = new ArrayList<>();
list.add(value);        // thêm vào cuối
list.get(i);            // lấy phần tử thứ i
list.size();            // số phần tử
list.remove(i);         // xóa tại vị trí i
list.add(i, value);     // chèn vào vị trí i
```
```java
ArrayList<Integer> a = new ArrayList<>();
for (int i = 0; i < n; i++) {
    System.out.print("a[" + i + "]: ");
    a.add(sc.nextInt());
}
// Duyệt bằng for-each:
for (int x : a) {
    System.out.print(x + " ");
}
```
### Sắp xếp với Collections
```java
import java.util.Collections;
ArrayList<Integer> tang = new ArrayList<>(a);         // sao chép
Collections.sort(tang);                               // tăng dần
ArrayList<Integer> giam = new ArrayList<>(a);
Collections.sort(giam, Collections.reverseOrder());   // giảm dần
```
### Tìm kiếm & thêm/xóa đúng vị trí
```java
Collections.sort(a); // phải sort trước
int viTri = Collections.binarySearch(a, x);
// >= 0  → tìm thấy tại vị trí viTri
// <  0  → không có, nên chèn tại (-viTri - 1)
if (viTri >= 0) {
    System.out.println("Tim thay tai vi tri: " + viTri);
} else {
    int insertAt = -viTri - 1;
    a.add(insertAt, x); // chèn vào đúng vị trí giữ sort
}
// Xóa:
int pos = Collections.binarySearch(a, xoa);
if (pos >= 0) a.remove(pos);
```
---
## Linked List
### Khái niệm
**Linked List** là cấu trúc dữ liệu gồm các **node** liên kết với nhau qua con trỏ `next`. Mỗi node chứa dữ liệu và địa chỉ node tiếp theo.
```
[Data1 | next]  →  [Data2 | next]  →  [Data3 | next]  →  null
```
### Class Node & Linked List cơ bản
```java
// Node đơn liên kết (Singly Linked List)
public class Node {
    public int  data;
    public Node next;
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
// Hoặc sử dụng java.util.LinkedList thẳng
import java.util.LinkedList;
LinkedList<Integer> list = new LinkedList<>();
list.add(10);           // thêm vào cuối
list.addFirst(5);       // thêm vào đầu
list.addLast(15);       // thêm vào cuối (tương tự add)
list.get(i);            // lấy phần tử thứ i
list.getFirst();        // lấy phần tử đầu
list.getLast();         // lấy phần tử cuối
list.remove(i);         // xóa tại vị trí i
list.removeFirst();     // xóa đầu
list.removeLast();      // xóa cuối
list.size();            // số phần tử
list.clear();           // xóa tất cả
```
### Thao tác cơ bản với Linked List (tự cài đặt)
#### Thêm phần tử
```java
public class LinkedListCustom {
    public Node head;
    // Thêm vào cuối
    public void add(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    // Thêm vào đầu
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }
    // Thêm vào vị trí pos (0-based)
    public void addAt(int pos, int value) {
        if (pos == 0) {
            addFirst(value);
            return;
        }
        Node newNode = new Node(value);
        Node current = head;
        for (int i = 0; i < pos - 1 && current != null; i++) {
            current = current.next;
        }
        if (current != null) {
            newNode.next = current.next;
            current.next = newNode;
        }
    }
}
```
#### Duyệt & In danh sách
```java
// Duyệt forward
public void display() {
    Node current = head;
    while (current != null) {
        System.out.print(current.data + " → ");
        current = current.next;
    }
    System.out.println("null");
}
// Duyệt & lấy vào ArrayList
public ArrayList<Integer> toList() {
    ArrayList<Integer> list = new ArrayList<>();
    Node current = head;
    while (current != null) {
        list.add(current.data);
        current = current.next;
    }
    return list;
}
// Đếm số phần tử
public int size() {
    int count = 0;
    Node current = head;
    while (current != null) {
        count++;
        current = current.next;
    }
    return count;
}
```
#### Tìm kiếm & Xóa
```java
// Tìm phần tử
public boolean search(int value) {
    Node current = head;
    while (current != null) {
        if (current.data == value) return true;
        current = current.next;
    }
    return false;
}
// Xóa phần tử đầu
public void removeFirst() {
    if (head != null) {
        head = head.next;
    }
}
// Xóa tại vị trí pos
public void removeAt(int pos) {
    if (pos == 0) {
        removeFirst();
        return;
    }
    Node current = head;
    for (int i = 0; i < pos - 1 && current != null; i++) {
        current = current.next;
    }
    if (current != null && current.next != null) {
        current.next = current.next.next;
    }
}
// Xóa tất cả có giá trị value
public void removeAll(int value) {
    while (head != null && head.data == value) {
        head = head.next;
    }
    Node current = head;
    while (current != null && current.next != null) {
        if (current.next.data == value) {
            current.next = current.next.next;
        } else {
            current = current.next;
        }
    }
}
```
#### Sắp xếp Linked List
```java
// Bubble Sort — O(n²)
public void bubbleSort() {
    if (head == null) return;
    Node i = head;
    while (i != null) {
        Node j = head;
        while (j != null && j.next != null) {
            if (j.data > j.next.data) {
                // hoán đổi dữ liệu
                int temp = j.data;
                j.data = j.next.data;
                j.next.data = temp;
            }
            j = j.next;
        }
        i = i.next;
    }
}
// Hoặc chuyển sang ArrayList, sort, rồi rebuild
public void sort() {
    ArrayList<Integer> list = toList();
    Collections.sort(list);
    head = null;
    for (int x : list) add(x);
}
```
---
## So sánh Array vs ArrayList vs LinkedList
| Tiêu chí | **Array** | **ArrayList** | **LinkedList** |
|----------|----------|--------------|----------------|
| **Kích cỡ cố định?** | ✅ Cố định | ❌ Động (tự mở rộng) | ❌ Động |
| **Truy cập phần tử** | O(1) | O(1) | O(n) |
| **Thêm vào cuối** | O(1) | O(1) amortized | O(1) |
| **Thêm vào đầu** | O(n) | O(n) | O(1) |
| **Thêm vào giữa** | O(n) | O(n) | O(n) để tìm, O(1) để chèn |
| **Xóa từ cuối** | O(1) | O(1) | O(1) |
| **Xóa từ đầu** | O(n) | O(n) | O(1) |
| **Xóa từ giữa** | O(n) | O(n) | O(n) để tìm, O(1) để xóa |
| **Sắp xếp** | O(n log n) | O(n log n) | O(n log n) |
| **Bộ nhớ** | Liên tục | Liên tục (hiệu quả cache) | Phân tán (mỗi node riêng) |
| **Độ phức tạp** | Đơn giản | Dễ sử dụng | Phức tạp |
---
## Khi nào dùng Array vs ArrayList vs LinkedList?
### ✅ Dùng **Array**
- Biết trước số lượng phần tử **cố định** (kích cỡ không thay đổi)
- Cần **truy cập nhanh** (`a[i]`)
- **Bộ nhớ hạn chế** (Array gọn gàng hơn)
- **Đơn giản hóa code** (không cần import)
**Ví dụ:**
```java
int[] scores = new int[100];  // Lớp có 100 học sinh
String[] weekDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
```
### ✅ Dùng **ArrayList**
- Số phần tử **không biết trước** (thay đổi liên tục)
- Cần **truy cập nhanh** (`list.get(i)`) + **thêm/xóa cuối** thường xuyên
- **Chuẩn của Java** (được dùng rộng rãi)
- **Hầu hết các bài tập** trong course
**Ví dụ:**
```java
ArrayList<Customer> customers = new ArrayList<>();
// Đọc từ file, thêm vào khi người dùng nhập
```
### ✅ Dùng **LinkedList**
- **Thêm/xóa nhiều ở đầu hoặc giữa danh sách**
- Cần **queue (hàng chờ)** hoặc **stack (ngăn xếp)**
- **Ít truy cập** theo chỉ số (`get(i)`)
- Kích cỡ có thể rất lớn (tránh phải reallocate)
**Ví dụ:**
```java
LinkedList<Task> queue = new LinkedList<>();  // Hàng chờ
queue.addLast(task);      // Thêm vào cuối
Task next = queue.removeFirst();  // Xóa đầu (O(1))
```
---
### Ví dụ thực tế — So sánh 3 cách
**Scenario: Quản lý danh sách học sinh, thêm/xóa người ở giữa danh sách**
#### ❌ Dùng Array — Phải dời lại tất cả
```java
// Xóa học sinh ở vị trí 5 (O(n))
for (int i = 5; i < n - 1; i++) {
    students[i] = students[i + 1];  // dời tất cả
}
n--;  // Phức tạp!
```
#### ✅ Dùng ArrayList — Tự động dời
```java
students.remove(5);  // O(n) nhưng ẩn, gọn gàng
```
#### ✅ Dùng LinkedList — Xóa liền lập tức
```java
// Nếu đã có Node tại vị trí 5:
// current.next = current.next.next;  // O(1) ngay lập tức!
// Nhưng phải duyệt để tìm (O(n))
```
**Kết luận:** Cho bài tập (thường xuyên thêm/xóa ở cuối + truy cập random), dùng **ArrayList**.
---
## LinkedList trong Java Collections
```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
// Queue (FIFO)
Queue<Integer> queue = new LinkedList<>();
queue.offer(1);      // add
queue.offer(2);
System.out.println(queue.poll());  // 1 (xóa đầu)
// Stack (LIFO)
Stack<Integer> stack = new Stack<>();  // hoặc LinkedList
stack.push(1);
stack.push(2);
System.out.println(stack.pop());   // 2 (xóa cuối)
// LinkedList thông dụng
LinkedList<String> list = new LinkedList<>();
list.addFirst("head");
list.addLast("tail");
list.add("middle");
for (String s : list) {
    System.out.print(s + " ");
}
// Output: head middle tail
```
---
## Class & Object (OOP)
```java
public class Customer {
    private int    customer_ID;
    private String customer_Name;
    private String customer_Gender;
    private int    customer_Age;
    // Constructor
    public Customer(int id, String name, String gender, int age) {
        this.customer_ID     = id;
        this.customer_Name   = name;
        this.customer_Gender = gender;
        this.customer_Age    = age;
    }
    // Getter
    public int    getCustomer_ID()     { return customer_ID; }
    public String getCustomer_Name()   { return customer_Name; }
    public String getCustomer_Gender() { return customer_Gender; }
    public int    getCustomer_Age()    { return customer_Age; }
    // toString
    @Override
    public String toString() {
        return "[" + customer_ID + "] "
             + customer_Name + " (" + customer_Gender + ", " + customer_Age + ")";
    }
}
```
**Sử dụng:**
```java
Customer c = new Customer(101, "Nguyen Van An", "nam", 20);
System.out.println(c); // [101] Nguyen Van An (nam, 20)
ArrayList<Customer> list = new ArrayList<>();
list.add(c);
for (Customer e : list) {
    if (e.getCustomer_Gender().compareTo("nam") == 0) {
        System.out.println(e);
    }
}
```
---
## Comparator — sort object
```java
import java.util.Comparator;
// Sort theo ID tăng dần
public class IDComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer a, Customer b) {
        return a.getCustomer_ID() - b.getCustomer_ID();
    }
}
// Sort theo tên (thứ tự từ điển)
public class NameComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer a, Customer b) {
        return a.getCustomer_Name().compareTo(b.getCustomer_Name());
    }
}
```
**Sử dụng:**
```java
list.sort(new IDComparator());   // sort theo ID
list.sort(new NameComparator()); // sort theo tên
```
---
## Cây nhị phân tìm kiếm (BST)
### Class Node
```java
public class Node {
    public int  label;
    public Node left, right;
    public Node(int label) {
        this.label = label;
    }
}
```
### Duyệt cây
```java
// Inorder: trái → gốc → phải  (ra kết quả tăng dần với BST)
public static void inorder(Node root) {
    if (root != null) {
        inorder(root.left);
        System.out.print(root.label + " ");
        inorder(root.right);
    }
}
// Đếm node & tính tổng:
public static int countNode(Node root) {
    if (root == null) return 0;
    return 1 + countNode(root.left) + countNode(root.right);
}
public static int sumNode(Node root) {
    if (root == null) return 0;
    return root.label + sumNode(root.left) + sumNode(root.right);
}
```
### Thêm node
```java
public static Node addNode(Node root, int value) {
    if (root == null) return new Node(value);
    if (value < root.label)
        root.left  = addNode(root.left,  value);
    else
        root.right = addNode(root.right, value);
    return root;
}
// Sử dụng:
Node root = null;
root = addNode(root, 60);
root = addNode(root, 41);
root = addNode(root, 74);
```
### Tìm & Xóa node
```java
public static boolean findNode(Node root, int value) {
    if (root == null)        return false;
    if (value == root.label) return true;
    if (value < root.label)  return findNode(root.left,  value);
    return                          findNode(root.right, value);
}
public static Node deleteNode(Node root, int value) {
    if (root == null) return null;
    if      (value < root.label) root.left  = deleteNode(root.left,  value);
    else if (value > root.label) root.right = deleteNode(root.right, value);
    else { // node cần xóa
        if (root.left  == null) return root.right;
        if (root.right == null) return root.left;
        // Có 2 con: thay bằng node nhỏ nhất bên phải
        Node T = theleftNode(root.right);
        T.left  = root.left;
        T.right = deleteNode(root.right, T.label);
        return T;
    }
    return root;
}
// Tìm node trái nhất (nhỏ nhất):
public static Node theleftNode(Node root) {
    if (root == null || root.left == null) return root;
    return theleftNode(root.left);
}
```
---
## File I/O — Serialization
> ⚠️ Class cần lưu phải `implements Serializable`
### Ghi file
```java
import java.io.*;
public static boolean saveToFile(ArrayList<Customer> list, String path) {
    try (ObjectOutputStream oos =
             new ObjectOutputStream(new FileOutputStream(path))) {
        oos.writeObject(list);
        return true;
    } catch (Exception e) {
        System.out.println("Loi ghi: " + e.getMessage());
        return false;
    }
}
```
### Đọc file
```java
@SuppressWarnings("unchecked")
public static ArrayList<Customer> readFromFile(String path) {
    try (ObjectInputStream ois =
             new ObjectInputStream(new FileInputStream(path))) {
        return (ArrayList<Customer>) ois.readObject();
    } catch (Exception e) {
        System.out.println("Loi doc: " + e.getMessage());
        return null;
    }
}
```
**Sử dụng:**
```java
saveToFile(malelist, "malelist.dat");
ArrayList<Customer> loaded = readFromFile("malelist.dat");
```
---
## BST Customer — Bài tập ôn thi giữa kỳ
> Quản lý khách hàng `Customer(ID, Name, Gender, Age, Blood)` bằng cây BST lấy nhãn theo `customer_ID`.
---
### Cấu trúc project
```
src/onthi/
├── Customer.java     // Model + BST node (left, right)
├── BST.java          // Logic cây: insert, search, delete, filter, sort
├── FileManager.java  // Lưu/đọc file .dat (Serialization)
└── Main.java         // Menu + 9 chức năng
```
---
### Customer.java — Node kiêm Model
```java
public class Customer implements Serializable {
    private int    customer_ID;
    private String customer_Name;   // họ và tên đầy đủ
    private String customer_Gender; // "Nam" / "Nu"
    private int    customer_Age;
    private String customer_Blood;  // "A" / "B" / "O" / "AB"
    public Customer left;   // con trái trong BST
    public Customer right;  // con phải trong BST
    public Customer(int id, String name, String gender, int age, String blood) {
        this.customer_ID     = id;
        this.customer_Name   = name;
        this.customer_Gender = gender;
        this.customer_Age    = age;
        this.customer_Blood  = blood;
        this.left = this.right = null;
    }
}
```
> 💡 Node **không cần class riêng** — `Customer` tự có `left` và `right` như Node.
---
### BST.java — Các hàm cốt lõi
#### Thêm node
```java
public class BST {
    private Customer root;
    public BST() {
        this.root = null;
    }
    public Customer getRoot() { return root; }
    public void setRoot(Customer root) { this.root = root; }
}
public void insert(Customer e) {
    root = insertRec(root, e);
}
private Customer insertRec(Customer node, Customer e) {
    if (node == null)
        return new Customer(e.getId(), e.getName(),
                            e.getGender(), e.getAge(), e.getBlood());
    if (e.getId() < node.getId())
        node.left  = insertRec(node.left,  e);
    else if (e.getId() > node.getId())
        node.right = insertRec(node.right, e);
    else
        System.out.println("ID " + e.getId() + " da ton tai!");
    return node;
}
```
#### Duyệt LNR → ArrayList tăng dần theo ID
```java
public ArrayList<Customer> toList() {
    ArrayList<Customer> list = new ArrayList<>();
    inorder(root, list);
    return list;
}
private void inorder(Customer node, ArrayList<Customer> list) {
    if (node != null) {
        inorder(node.left, list);
        list.add(node);
        inorder(node.right, list);
    }
}
```
#### Tìm kiếm theo ID
```java
public Customer search(int id) {
    return searchRec(root, id);
}
private Customer searchRec(Customer node, int id) {
    if (node == null)          return null;
    if (id == node.getId())    return node;
    if (id <  node.getId())    return searchRec(node.left,  id);
    return                            searchRec(node.right, id);
}
```
#### Xóa theo ID
```java
public void delete(int id) {
    root = deleteRec(root, id);
}
private Customer deleteRec(Customer node, int id) {
    if (node == null) return null;
    if (id < node.getId())       node.left  = deleteRec(node.left,  id);
    else if (id > node.getId())  node.right = deleteRec(node.right, id);
    else {
        if (node.left  == null) return node.right; // 0 hoặc 1 con
        if (node.right == null) return node.left;
        // 2 con: lấy node nhỏ nhất bên phải (inorder successor)
        Customer suc = findMin(node.right);
        suc.right = deleteRec(node.right, suc.getId());
        suc.left  = node.left;
        return suc;
    }
    return node;
}
private Customer findMin(Customer node) {
    if (node == null || node.left == null) return node;
    return findMin(node.left);
}
```
#### Lọc theo giới tính / nhóm máu
```java
public ArrayList<Customer> filterGender(String gender) {
    ArrayList<Customer> res = new ArrayList<>();
    filterGenderRec(root, gender, res);
    return res;
}
private void filterGenderRec(Customer node, String gender,
                              ArrayList<Customer> res) {
    if (node == null) return;
    filterGenderRec(node.left, gender, res);
    if (node.getGender().equalsIgnoreCase(gender)) res.add(node);
    filterGenderRec(node.right, gender, res);
}
```
> Tương tự với `filterBlood(String blood)` — thay `getGender()` bằng `getBlood()`.
#### Sắp xếp theo tên (lấy tên cuối — tiếng Việt)
```java
public ArrayList<Customer> sortByName(String gender) {
    ArrayList<Customer> list = filterGender(gender);
    Collections.sort(list, Comparator.comparing(
        c -> normalize(c.getName())));
    return list;
}
private String normalize(String fullName) {
    if (fullName == null || fullName.trim().isEmpty()) return "";
    String[] parts = fullName.trim().split("\\s+");
    return parts[parts.length - 1].toLowerCase(); // lấy tên cuối
}
```
#### In bảng
```java
public static void printTable(ArrayList<Customer> list) {
    if (list == null || list.isEmpty()) {
        System.out.println("  (Danh sach trong)"); return;
    }
    System.out.println("+---------+---------------------------+------+-----+-------+");
    System.out.printf ("| %-7s | %-25s | %-4s | %-3s | %-5s |%n",
            "ID","Ho Ten","GT","Tuoi","Mau");
    System.out.println("+---------+---------------------------+------+-----+-------+");
    for (Customer c : list)
        System.out.printf("| %-7d | %-25s | %-4s | %-3d | %-5s |%n",
                c.getId(), c.getName(), c.getGender(),
                c.getAge(), c.getBlood());
    System.out.println("+---------+---------------------------+------+-----+-------+");
}
```
#### In dạng cây
```java
public void printTree() {
    System.out.println("\n  Cay BST hien tai:");
    if (root == null) { System.out.println("  (Cay rong)"); return; }
    printTreeRec(root, "", "ROOT");
}
private void printTreeRec(Customer node, String prefix, String label) {
    if (node == null) return;
    System.out.println(prefix + "[" + label + "] ID=" + node.getId());
    if (node.left != null || node.right != null) {
        printTreeRec(node.left,  prefix + "    ", "TRAI");
        printTreeRec(node.right, prefix + "    ", "PHAI");
    }
}
```
---
### FileManager.java — Lưu/đọc BST
```java
public class FileManager {
    private static final String FILE_PATH = "customers.dat";
    public static boolean saveTree(Customer root) {
        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(root);
            return true;
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
            return false;
        }
    }
    public static Customer loadTree() {
        File f = new File(FILE_PATH);
        if (!f.exists()) return null;
        try (ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Customer) ois.readObject();
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
            return null;
        }
    }
}
```
> 💡 `writeObject(root)` tự lưu cả cây vì Java theo `left`/`right` theo dây chuyền.
---
### Main.java — Cấu trúc menu
```java
static BST bst = new BST();
static Scanner sc = new Scanner(System.in);
public static void main(String[] args) {
    // Đọc file khi khởi động
    Customer savedRoot = FileManager.loadTree();
    if (savedRoot != null) bst.setRoot(savedRoot);
    int luaChon;
    do {
        inMenu();
        luaChon = soNguyen();
        switch (luaChon) {
            case 1 -> chuc1_NhapKhachHang();
            case 2 -> chuc2_XuatDanhSach();
            case 3 -> chuc3_TinhTuoi();
            case 4 -> chuc4_LocGioiTinhNhomMau();
            case 5 -> chuc5_SapXepTheoTen();
            case 6 -> chuc6_TimNamMaxMinTuoi();
            case 7 -> chuc7_TimTheoID();
            case 8 -> chuc8_XoaTheoID();
            case 9 -> chuc9_LuuFile();
            case 0 -> System.out.println("Tam biet!");
        }
    } while (luaChon != 0);
}
// Nhập số nguyên an toàn (tránh crash khi nhập sai)
static int soNguyen() {
    while (true) {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) {
            System.out.print("Vui long nhap so nguyen: ");
        }
    }
}
```
---
### Compile & Chạy (VSCode Terminal)
```bash
# Đứng ở thư mục chứa src/
mkdir out
javac -d out src/onthi/*.java
# Chạy
java -cp out onthi.Main
```
> ⚠️ Không dùng `java Main.java` — single-file mode không thấy các file khác trong package.
---
### Tóm tắt 9 chức năng
| # | Chức năng | Hàm BST dùng |
|---|-----------|-------------|
| 1 | Nhập khách hàng vào BST | `insert()` |
| 2 | Xuất danh sách dạng bảng | `toList()` + `printTable()` |
| 3 | Tuổi TB, max, min | `toList()` + vòng lặp |
| 4 | Lọc Nam/Nữ, nhóm máu | `filterGender()`, `filterBlood()` |
| 5 | Sắp xếp theo tên — xuất Nam | `sortByName("Nam")` |
| 6 | Nam lớn/nhỏ tuổi nhất | `maxAgeByGender()`, `minAgeByGender()` |
| 7 | Tìm theo ID | `search()` |
| 8 | Xóa theo ID | `delete()` |
| 9 | Lưu/đọc file | `FileManager.saveTree()` / `loadTree()` |
Mẹo làm bài BST (đặc biệt khi thi)
BST có công thức chung cho hầu hết bài — nắm được vài mẹo dưới đây là "ăn" 80% dạng đề.
🎯 Mẹo #1: Mọi hàm BST đều có cùng một khung
Đây là mẹo quan trọng nhất. 99% hàm BST viết theo đúng 3 nhánh:
```java
Node hamX(Node node, ...) {
    if (node == null) return ...;              // (1) BASE CASE
    if (id < node.id) {                        // (2) ĐI TRÁI
        node.left = hamX(node.left, ...);
    } else if (id > node.id) {                 // (3) ĐI PHẢI
        node.right = hamX(node.right, ...);
    } else {                                   // (4) TÌM THẤY — xử lý
        ...
    }
    return node;                               // (5) TRẢ GỐC
}
```
Nhớ khung này là viết được: insert, delete, search, update, thậm chí cả findFloor, findCeiling... Chỉ khác nhau ở phần (4).
Câu thần chú: "Null → so sánh → đi trái/phải → gặp rồi xử lý → trả node".
🎯 Mẹo #2: Phân biệt rõ "duyệt theo khóa" vs "duyệt toàn cây"
Đây là chỗ nhiều bạn lú nhất.
| Dạng bài | Cách duyệt | Ví dụ |
|----------|-----------|-------|
| Có khóa cụ thể (ID, mã) | Đi theo if id < node.id | search, delete, insert, findFloor |
| Quét hết cả cây (đếm, lọc, max/min, TB) | Duyệt LNR/NLR/LRN | count, height, filterGender, avgAge, maxAge |
Sai lầm phổ biến: dùng nhánh id < node.id để viết hàm maxAge → SAI, vì tuổi không phải khóa BST. Phải duyệt hết cả cây rồi so sánh.
Câu hỏi tự vấn trước khi viết: "Cái mình tìm có phải là KHÓA của BST không?"
- Có → duyệt theo khóa (nhanh O(h)).
- Không → duyệt toàn cây (O(n)).
🎯 Mẹo #3: 4 kiểu duyệt — nhớ bằng câu "Node Trước / Giữa / Sau"
| Kiểu | Thứ tự | Dùng khi nào |
|------|--------|-------------|
| NLR (Pre-order) | Node → Left → Right | Copy cây, in cấu trúc |
| LNR (In-order) | Left → Node → Right | Lấy danh sách tăng dần ← hay dùng nhất |
| LRN (Post-order) | Left → Right → Node | Tính toán phụ thuộc con (height, count, xóa cả cây) |
| RNL (Reverse In) | Right → Node → Left | Lấy danh sách giảm dần |
Mẹo nhớ: chữ N đứng vị trí nào trong tên gọi → node được xử lý ở vị trí đó.
🎯 Mẹo #4: Xóa node — nhớ "3 case + 1 câu thần chú"
Chỉ cần nhớ:
```
Con trái null  → trả về con phải
Con phải null  → trả về con trái
Có đủ 2 con   → successor (min nhánh phải)
```
Câu thần chú: "Thiếu trái trả phải, thiếu phải trả trái, đủ hai thì successor".
Và nhớ công thức tìm successor: "Xuống phải 1 lần, rồi đi trái đến cùng" — tức là min(node.right).
⚠️ Bẫy thi: successor phải bị gỡ khỏi chỗ cũ trước khi gắn vào vị trí mới, nếu không cây sẽ trùng node. Câu `successor.right = deleteRec(node.right, successor.id)` chính là để làm việc đó.
🎯 Mẹo #5: Câu gán node.left = ... — hiểu rõ để không viết sai
```java
node.left = deleteRec(node.left, id);   // ✅ ĐÚNG
deleteRec(node.left, id);               // ❌ SAI — không cập nhật được gốc mới
```
Lý do: hàm đệ quy trả về gốc MỚI của cây con (có thể khác gốc cũ nếu node con bị xóa). Nếu không gán lại → cây vẫn trỏ vào node cũ đã bị "bỏ rơi".
Quy tắc vàng: hàm đệ quy BST trả về Node → luôn gán lại vào node.left / node.right.
Nếu hàm trả về void / int / List (như count, height, inorder) → không cần gán.
🎯 Mẹo #6: return node ở cuối — đừng quên
```java
private Customer deleteRec(Customer node, int id) {
    if (node == null) return null;
    ...
    return node;   // ← CỰC KỲ QUAN TRỌNG
}
```
Nhiều bạn viết thiếu dòng này → hàm trả về null → mất nguyên cây sau khi đệ quy quay lui.
Mẹo: đầu hàm có `if (node == null) return null;` thì cuối hàm phải có `return node;` để "đối xứng".
🎯 Mẹo #7: Các hàm tính toán — 3 dòng là xong
```java
int count(Node node) {
    if (node == null) return 0;
    return 1 + count(node.left) + count(node.right);
}
int height(Node node) {
    if (node == null) return 0;
    return 1 + Math.max(height(node.left), height(node.right));
}
int sum(Node node) {
    if (node == null) return 0;
    return node.value + sum(node.left) + sum(node.right);
}
```
Mẹo nhớ: "1 + tổng hai con" cho count/sum, "1 + max hai con" cho height.
Bẫy: height cây rỗng = 0 hay -1? Tùy đề — đọc kỹ. Đa số VN dùng 0 (node lá height = 1).
🎯 Mẹo #8: Tìm min / max — không cần đệ quy phức tạp
```java
Node findMin(Node node) {           // node nhỏ nhất = đi trái tới cùng
    while (node.left != null) node = node.left;
    return node;
}
Node findMax(Node node) {           // node lớn nhất = đi phải tới cùng
    while (node.right != null) node = node.right;
    return node;
}
```
Quy tắc: "Min đi trái, Max đi phải".
🎯 Mẹo #9: Search không bao giờ đệ quy 2 nhánh
```java
// ✅ BST search — O(h), chỉ đi 1 nhánh
Customer search(Node node, int id) {
    if (node == null) return null;
    if (id == node.id) return node;
    return id < node.id ? search(node.left, id) : search(node.right, id);
}
// ❌ ĐỪNG viết kiểu này — O(n), mất hết lợi thế BST
if (search(node.left, id) != null) return ...;
if (search(node.right, id) != null) return ...;
```
Dấu hiệu SAI: thấy 2 dòng search(node.left) và search(node.right) cạnh nhau.
🎯 Mẹo #10: Kiểm tra BST hợp lệ — dùng min/max range
```java
// ❌ SAI — chỉ so cha với con trực tiếp, bỏ sót vi phạm sâu hơn
boolean isBST(Node node) {
    if (node == null) return true;
    if (node.left != null && node.left.value > node.value) return false;
    if (node.right != null && node.right.value < node.value) return false;
    return isBST(node.left) && isBST(node.right);
}
// ✅ ĐÚNG — mỗi node phải nằm trong khoảng (min, max)
boolean isBST(Node node, long min, long max) {
    if (node == null) return true;
    if (node.value <= min || node.value >= max) return false;
    return isBST(node.left, min, node.value)
        && isBST(node.right, node.value, max);
}
// gọi: isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)
```
Câu thần chú: "Node phải nằm trong khoảng (min, max) — không chỉ so với cha."
🎯 Mẹo #11: Insert — viết gọn 5 dòng
```java
Node insert(Node node, int id) {
    if (node == null) return new Node(id);
    if (id < node.id) node.left = insert(node.left, id);
    else if (id > node.id) node.right = insert(node.right, id);
    // id == node.id → bỏ qua (trùng) hoặc update
    return node;
}
```
🎯 Mẹo #12: Khi bí — vẽ cây 5 node ra giấy, chạy tay
```
        50
       /  \
     30    70
    /  \
  20   40
```
Tự hỏi: "Tôi sẽ làm gì với node 30 khi xóa nó?" — câu trả lời chính là code cần viết.
---
## 🆕 Mẹo bổ sung
🎯 Mẹo #13: Đọc đề — xác định khóa BST ngay đầu
Trước khi viết bất kỳ hàm nào, hỏi ngay: "BST được sắp xếp theo trường nào?"
- Theo ID → so sánh id
- Theo mã sản phẩm → so sánh code
- Theo tên → so sánh name (dùng compareTo)
Sai khóa → sai toàn bộ cây. Đây là lỗi ngầm nhất khi thi.
---
🎯 Mẹo #14: root = ... — đừng quên gán lại root
```java
// ❌ SAI
bst.insert(root, newNode);  // root không thay đổi nếu cây rỗng!
// ✅ ĐÚNG
root = bst.insert(root, newNode);
```
Lý do: khi cây rỗng `root == null`, hàm trả về node mới — nếu không gán lại `root` thì cây mãi rỗng.
Quy tắc: **hàm trả về Node → luôn gán lại nơi gọi** (`root = ...`, `node.left = ...`).
---
🎯 Mẹo #15: Đếm / lọc có điều kiện — vẫn duyệt toàn cây, chỉ thêm if
```java
// Đếm node có tuổi > 30
int countOver30(Node node) {
    if (node == null) return 0;
    int cur = (node.age > 30) ? 1 : 0;           // kiểm tra điều kiện
    return cur + countOver30(node.left) + countOver30(node.right);
}
// Lọc ra ArrayList theo điều kiện bất kỳ
void filter(Node node, String gender, ArrayList<Customer> res) {
    if (node == null) return;
    filter(node.left, gender, res);
    if (node.gender.equalsIgnoreCase(gender)) res.add(node); // điều kiện
    filter(node.right, gender, res);
}
```
Công thức: **khung duyệt toàn cây + if điều kiện ở giữa** — áp dụng được cho mọi bài lọc/đếm/tính.
---
🎯 Mẹo #16: Truyền ArrayList vào hàm đệ quy — không cần return
```java
// ✅ Cách đúng — truyền list vào, thêm dần, không cần return List
void inorder(Node node, ArrayList<Customer> list) {
    if (node == null) return;
    inorder(node.left, list);
    list.add(node.data);       // thêm vào list dùng chung
    inorder(node.right, list);
}
// Gọi:
ArrayList<Customer> result = new ArrayList<>();
inorder(root, result);
```
Tránh viết kiểu return ArrayList từ đệ quy → phức tạp và dễ mất data.
---
🎯 Mẹo #17: Tìm max/min theo điều kiện (vd: tuổi lớn nhất trong nam) — không dùng khóa BST
```java
// Duyệt toàn cây, giữ biến max
Customer maxAgeMale(Node node, Customer best) {
    if (node == null) return best;
    if (node.gender.equals("Nam")) {
        if (best == null || node.age > best.age) best = node;
    }
    best = maxAgeMale(node.left,  best);
    best = maxAgeMale(node.right, best);
    return best;
}
// Gọi: Customer result = maxAgeMale(root, null);
```
Trick: truyền `best` qua mỗi lần gọi, cập nhật khi tìm được tốt hơn — không cần biến global.
---
🎯 Mẹo #18: floor / ceiling — đi theo khóa nhưng lưu lại ứng viên
```java
// Floor: node lớn nhất mà <= key
Node floor(Node node, int key) {
    if (node == null) return null;
    if (key == node.id) return node;               // khớp chính xác
    if (key < node.id) return floor(node.left, key); // chỉ đi trái
    // key > node.id: node này là ứng viên, nhưng có thể tìm được tốt hơn bên phải
    Node better = floor(node.right, key);
    return (better != null) ? better : node;
}
// Ceiling: node nhỏ nhất mà >= key — đối xứng hoàn toàn
Node ceiling(Node node, int key) {
    if (node == null) return null;
    if (key == node.id) return node;
    if (key > node.id) return ceiling(node.right, key);
    Node better = ceiling(node.left, key);
    return (better != null) ? better : node;
}
```
---
🎯 Mẹo #19: Xóa toàn cây — chỉ cần 1 dòng
```java
root = null;  // GC tự dọn hết (Java)
```
Không cần duyệt LRN xóa từng node như C/C++. Java có Garbage Collector.
---
🎯 Mẹo #20: Kiểm tra nhanh trước khi nộp bài
Trước khi nộp, tự check 5 điểm:
1. ✅ Mọi hàm trả về Node đều có `return node;` ở cuối?
2. ✅ Mọi lần gọi đệ quy đều gán lại: `node.left = f(...)` / `root = f(...)`?
3. ✅ Hàm tìm theo thuộc tính không phải khóa → duyệt toàn cây (không dùng `id < node.id`)?
4. ✅ Xóa node 2 con: successor được gỡ khỏi cây phải trước khi gắn vào?
5. ✅ isBST dùng range (min, max) thay vì chỉ so cha-con?
Nếu 5/5 → tự tin nộp.