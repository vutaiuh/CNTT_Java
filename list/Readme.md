# So sánh ArrayList và LinkedList

Thư mục này gồm 2 chương trình Java (package `list`) có cùng menu và chức năng, chỉ khác cấu trúc dữ liệu bên dưới:

| File | Cấu trúc dữ liệu |
|---|---|
| `QuanLySoArrayList.java` | `ArrayList<Integer>` |
| `QuanLySoLinkedList.java` | `LinkedList<Integer>` |

Chức năng: hiển thị, thêm, sửa, xóa, tìm kiếm, sắp xếp, đếm số trùng.

---

## 1. Cơ chế hoạt động bên trong

### ArrayList: mảng động
- Lưu phần tử trong **một mảng liên tục** trong bộ nhớ (`Object[]`).
- Khi mảng đầy, nó tạo mảng mới lớn hơn (khoảng 1.5 lần) rồi sao chép phần tử sang.
- Truy cập theo chỉ số là tính địa chỉ trực tiếp, nên rất nhanh.
- Chèn hoặc xóa ở giữa phải **dịch chuyển** các phần tử phía sau.

```
Chỉ số:   0    1    2    3    4
        [ 10 ][ 20 ][ 30 ][ 40 ][ 50 ]
Chèn 25 vào vị trí 2 -> 30, 40, 50 phải dịch sang phải
```

### LinkedList: danh sách liên kết đôi
- Mỗi phần tử nằm trong một **node** riêng gồm: `dữ liệu`, con trỏ `prev`, con trỏ `next`.
- Các node nằm rải rác trong bộ nhớ, nối với nhau bằng con trỏ.
- Chèn hoặc xóa chỉ cần **đổi con trỏ**, không dịch chuyển dữ liệu.
- Muốn tới phần tử thứ `i` phải **đi từng node** từ đầu hoặc từ cuối (chọn phía gần hơn).

```
null <- [10] <-> [20] <-> [30] <-> [40] -> null
Chèn 25 giữa 20 và 30 -> chỉ sửa 4 con trỏ
```

---

## 2. Bảng so sánh độ phức tạp

| Thao tác | ArrayList | LinkedList |
|---|---|---|
| Truy cập `get(i)` / `set(i, x)` | **O(1)** | O(n) |
| Thêm cuối `add(x)` | O(1) (trung bình) | **O(1)** |
| Thêm đầu `add(0, x)` / `addFirst` | O(n) | **O(1)** |
| Thêm giữa `add(i, x)` | O(n) (dịch phần tử) | O(n) (tìm vị trí), gắn node O(1) |
| Xóa theo chỉ số `remove(i)` | O(n) (dịch phần tử) | O(n) (tìm vị trí), gỡ node O(1) |
| Xóa khi đang duyệt bằng `Iterator` | O(n) mỗi lần xóa | **O(1)** mỗi lần xóa |
| Tìm kiếm theo giá trị | O(n) | O(n) |
| Sắp xếp `Collections.sort` | O(n log n) | O(n log n) |
| Bộ nhớ | Ít hơn (chỉ lưu dữ liệu) | Nhiều hơn (mỗi node thêm 2 con trỏ) |
| Tận dụng cache CPU | Tốt (dữ liệu liền kề) | Kém (node rải rác) |

Lưu ý: "tìm vị trí" trong LinkedList tốn O(n) nên thêm hoặc xóa ở giữa **không nhanh hơn** ArrayList nếu bạn chỉ có chỉ số. LinkedList chỉ thật sự có lợi khi thao tác ở **hai đầu**, hoặc khi đã đứng sẵn tại vị trí cần xóa (qua `Iterator`).

---

## 3. Áp dụng vào code của 2 file

| Chức năng | `QuanLySoArrayList` | `QuanLySoLinkedList` |
|---|---|---|
| Thêm | `add(x)` cuối, `add(i, x)` | Thêm `addFirst(x)`, `addLast(x)` cho thao tác hai đầu O(1) |
| Sửa | `set(i, x)` O(1) | `set(i, x)` phải đi tới node thứ `i` |
| Xóa theo giá trị | `removeAll(Collections.singleton(x))` | Dùng `Iterator.remove()` để xóa an toàn khi duyệt |
| Tìm kiếm | Duyệt `for` với `get(i)` (nhanh vì O(1)) | Duyệt `for-each`, không dùng `get(i)` vì mỗi lần là O(n), cả vòng thành O(n²) |
| Sắp xếp | `Collections.sort(ds)` | `Collections.sort(ds)` |
| Đếm trùng | Sắp xếp rồi đếm bằng `TreeMap` | Giống ArrayList |

Chi tiết đáng chú ý:
- **Tìm kiếm trong LinkedList** dùng `for-each` (ngầm dùng iterator) thay vì `get(i)`. Đây là lỗi hiệu năng hay gặp: `for (i...) list.get(i)` trên LinkedList là O(n²).
- **Sắp xếp** ở cả hai đều chuyển danh sách sang mảng, sắp xếp bằng TimSort rồi ghi ngược lại. Vì vậy LinkedList không hề sắp xếp chậm hơn về mặt độ phức tạp.
- **Cẩn thận `remove`**: với `List<Integer>`, `remove(int)` xóa theo **chỉ số**, còn `remove(Object)` xóa theo **giá trị**. Vì vậy code xóa theo giá trị dùng `removeAll` hoặc `Iterator` để tránh nhầm.

---

## 4. Khi nào nên dùng cái nào?

**Chọn ArrayList khi:**
- Cần truy cập ngẫu nhiên theo chỉ số thường xuyên.
- Chủ yếu thêm ở cuối, đọc nhiều, ít xóa ở đầu hoặc giữa.
- Muốn tiết kiệm bộ nhớ.

**Chọn LinkedList khi:**
- Thường xuyên thêm hoặc xóa ở **đầu** (dùng như hàng đợi `Queue` hoặc `Deque`).
- Duyệt tuần tự và xóa hoặc chèn ngay tại vị trí đang duyệt bằng `Iterator` / `ListIterator`.
- Ít khi cần `get(i)`.

**Thực tế:** trong đa số ứng dụng, **ArrayList là lựa chọn mặc định** vì nhanh hơn nhờ cache CPU và tốn ít bộ nhớ. Chỉ chọn LinkedList khi có lý do rõ ràng như trên.

---

## 5. Cách chạy

Từ thư mục chứa `list/`:

```
javac list/QuanLySoArrayList.java list/QuanLySoLinkedList.java
java list.QuanLySoArrayList
java list.QuanLySoLinkedList
```

Ví dụ chức năng **đếm số trùng**: với danh sách `5 1 3 5 2 1 5 4 2 6`, chương trình in:

```
Danh sach sau khi sap xep: 1 1 2 2 3 4 5 5 5 6
So 1 xuat hien 2 lan
So 2 xuat hien 2 lan
So 5 xuat hien 3 lan
```