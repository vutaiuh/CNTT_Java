# Duyệt cây nhị phân – Cơ chế, logic và cấu trúc chương trình

## 1. Tổng quan

Chương trình cài đặt các thao tác trên **cây nhị phân** bằng **đệ quy thuần** (không dùng hàng đợi, không dùng `import`):

- 5 cách duyệt cây: **LNR, NLR, LRN, RNL, theo mức**
- Các hàm thống kê: đếm node, đếm lá, node trái nhất / phải nhất, tổng label, đếm node nhỏ/lớn hơn giá trị x, chiều cao cây

Cấu trúc file:

```
node/
├── Node.java   // Lớp node: label, left, right
└── Main.java   // Các hàm duyệt + thống kê + hàm main
```

Mỗi `Node` gồm: `label` (giá trị), `left` (con trái), `right` (con phải). Node rỗng biểu diễn bằng `null`.

## 2. Cây dùng trong ví dụ

```
                7
             /     \
           5         12
          / \       /   \
         3   6     9     15
        / \       / \    / \
       1   4     8  10  13  17
```

| Thông số | Giá trị |
|---|---|
| Tổng số node | 13 |
| Số node lá (1, 4, 6, 8, 10, 13, 17) | 7 |
| Chiều cao | 4 |
| Node trái nhất / phải nhất | 1 / 17 |
| Tổng label | 110 |
| Trung bình | 110 / 13 ≈ 8.4615 |

## 3. Nguyên lý chung của duyệt đệ quy

Mỗi node có 3 "việc" phải làm: **N** (thăm node hiện tại – in label), **L** (đi sang cây con trái), **R** (đi sang cây con phải).

Tên của kiểu duyệt chính là **thứ tự thực hiện 3 việc này**. Điểm dừng của đệ quy luôn là `root == null` (cây rỗng thì không làm gì).

```
Khung chung:
    if (root != null) {
        ... 3 việc N, L, R theo thứ tự mong muốn ...
    }
```

## 4. Chi tiết từng kiểu duyệt

### 4.1. LNR – Trung thứ tự (Inorder): Trái → Gốc → Phải

```java
readtree(root.left);
print(root.label);
readtree(root.right);
```

- Xử lý xong **toàn bộ cây con trái** rồi mới in gốc, sau đó sang cây con phải.
- Với **cây nhị phân tìm kiếm (BST)**, kết quả luôn là dãy **tăng dần**.
- Kết quả: `1 3 4 5 6 7 8 9 10 12 13 15 17`

### 4.2. NLR – Tiền thứ tự (Preorder): Gốc → Trái → Phải

```java
print(root.label);
preorder(root.left);
preorder(root.right);
```

- In gốc **ngay khi vừa chạm tới** node, rồi mới đi xuống các con.
- Hữu ích để **sao chép cây**, **lưu cấu trúc cây**: gốc luôn nằm đầu dãy.
- Kết quả: `7 5 3 1 4 6 12 9 8 10 15 13 17`

### 4.3. LRN – Hậu thứ tự (Postorder): Trái → Phải → Gốc

```java
postorder(root.left);
postorder(root.right);
print(root.label);
```

- Chỉ in gốc **sau khi cả hai cây con đã xử lý xong**: con luôn được xử lý trước cha.
- Hữu ích để **xóa cây**, **tính giá trị từ dưới lên** (tính chiều cao, tổng, đếm node…): gốc luôn nằm cuối dãy.
- Kết quả: `1 4 3 6 5 8 10 9 13 17 15 12 7`

### 4.4. RNL – Đảo của LNR: Phải → Gốc → Trái

```java
reverseInorder(root.right);
print(root.label);
reverseInorder(root.left);
```

- Chỉ đổi chỗ L và R so với LNR.
- Với BST, kết quả là dãy **giảm dần**.
- Kết quả: `17 15 13 12 10 9 8 7 6 5 4 3 1`

### 4.5. Duyệt theo mức (Level order): từng tầng, trái sang phải

Cách thông thường dùng hàng đợi (Queue). Bản này **không dùng Queue**, mà kết hợp 2 hàm:

```java
// In các node ở đúng tầng "level" (tầng 1 = gốc)
printLevel(root, level):
    if (root == null) return;
    if (level == 1) print(root.label);
    else {
        printLevel(root.left,  level - 1);
        printLevel(root.right, level - 1);
    }

// Lặp qua từng tầng từ 1 đến chiều cao
levelOrder(root):
    h = height(root);
    for (i = 1; i <= h; i++) printLevel(root, i);
```

**Ý tưởng:** mỗi lần đi xuống một cây con thì `level` giảm 1. Khi `level == 1` nghĩa là đã tới đúng tầng cần in.

Ví dụ `printLevel(root, 3)` trên cây trên:

```
printLevel(7, 3)
├─ printLevel(5, 2)
│   ├─ printLevel(3, 1) → in 3
│   └─ printLevel(6, 1) → in 6
└─ printLevel(12, 2)
    ├─ printLevel(9, 1)  → in 9
    └─ printLevel(15, 1) → in 15
```

Kết quả tầng 3: `3 6 9 15`. Vì luôn đi trái trước phải nên thứ tự trong tầng là trái → phải.

- Kết quả toàn bộ: `7 5 12 3 6 9 15 1 4 8 10 13 17`

## 5. Minh họa chạy tay (LNR trên cây con nhỏ)

Xét cây con gốc 5 (con trái 3, con phải 6; node 3 có con 1 và 4):

```
readtree(5)
├─ readtree(3)
│   ├─ readtree(1)
│   │   ├─ readtree(null)  → thoát
│   │   ├─ in 1
│   │   └─ readtree(null)  → thoát
│   ├─ in 3
│   └─ readtree(4) → in 4
├─ in 5
└─ readtree(6) → in 6
```

Thứ tự in: `1 3 4 5 6`. Đệ quy dùng **call stack** để "nhớ" node cha khi đi xuống con, và quay lại đúng chỗ khi con xử lý xong.

## 6. Các hàm nghiệp vụ khác

Tất cả đều theo cùng mẫu: **kết quả của cây = kết quả cây trái + phần của node hiện tại + kết quả cây phải**.

| Hàm | Điều kiện dừng | Công thức đệ quy |
|---|---|---|
| `countNode` | `null` → 0 | `countNode(L) + 1 + countNode(R)` |
| `countLeaf` | `null` → 0; không con → 1 | `countLeaf(L) + countLeaf(R)` |
| `theleftNode` | `null` → null; `left == null` → chính nó | đi tiếp `left` |
| `therightNode` | `null` → null; `right == null` → chính nó | đi tiếp `right` |
| `sumLabel` | `null` → 0 | `sumLabel(L) + label + sumLabel(R)` |
| `countLess(x)` | `null` → 0 | `countLess(L) + (label < x ? 1 : 0) + countLess(R)` |
| `countGreater(x)` | `null` → 0 | `countGreater(L) + (label > x ? 1 : 0) + countGreater(R)` |
| `height` | `null` → 0 | `max(height(L), height(R)) + 1` |

Luồng nghiệp vụ trong `main`:

1. Dựng cây bằng cách tạo các `Node` rồi gán `left`, `right`.
2. Gọi 5 kiểu duyệt và in kết quả.
3. Tính `total = countNode(root)`, `sum = sumLabel(root)`, rồi `avg = (double) sum / total`.
4. Dùng `avg` làm ngưỡng cho `countLess` và `countGreater`.
5. In chiều cao cây.

> Lưu ý: phải ép kiểu `(double)` trước khi chia, nếu không phép chia hai số nguyên sẽ cắt phần thập phân (110 / 13 = 8 thay vì 8.4615).

## 7. Độ phức tạp

| Hàm | Thời gian | Bộ nhớ (call stack) |
|---|---|---|
| LNR, NLR, LRN, RNL | O(n) – mỗi node thăm đúng 1 lần | O(h) |
| Các hàm đếm, tổng, chiều cao | O(n) | O(h) |
| `theleftNode`, `therightNode` | O(h) | O(h) |
| `levelOrder` (bản không Queue) | O(n × h), tệ nhất O(n²) với cây lệch | O(h) |

Trong đó **n** là số node, **h** là chiều cao cây. Bản `levelOrder` dùng Queue sẽ nhanh hơn (O(n)) nhưng cần `import java.util.*`.

## 8. Bảng tổng hợp so sánh

| Kiểu duyệt | Thứ tự | Vị trí gốc trong kết quả | Ứng dụng tiêu biểu |
|---|---|---|---|
| LNR | Trái – Gốc – Phải | Giữa | Dãy tăng dần trên BST |
| NLR | Gốc – Trái – Phải | Đầu | Sao chép / lưu cấu trúc cây |
| LRN | Trái – Phải – Gốc | Cuối | Xóa cây, tính từ dưới lên |
| RNL | Phải – Gốc – Trái | Giữa | Dãy giảm dần trên BST |
| Theo mức | Từng tầng, trái → phải | Đầu | Xem cây theo độ sâu |

## 9. Chạy chương trình

```bash
javac node/Node.java node/Main.java
java node.Main
```