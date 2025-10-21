# 🏫 SchoolHub – Backend API cho hệ thống quản lý trường học

SchoolHub là hệ thống backend được xây dựng bằng Spring Boot, cung cấp các API RESTful để quản lý toàn diện hoạt động của một trường học: học sinh, giáo viên, lớp học, môn thi, thời khóa biểu, điểm số, và phân quyền người dùng.

---


## 🛠️ Công nghệ sử dụng

- Java 21 + Spring Boot
- Spring Security + JWT
- Spring Data JPA + MySQL
- Apache POI (đọc file Excel)
- Docker (triển khai)

## 🚀 Tính năng chính

- ✅ CRUD cho học sinh, giáo viên, lớp học, môn học, kỳ học, năm học, cuộc thi, điểm thi, thời khóa biểu
- 🔐 Xác thực & phân quyền người dùng bằng JWT (Spring Security)
- 📄 Phân trang, tìm kiếm, lọc dữ liệu
- 📥 Upload tài khoản từ file Excel (học sinh & giáo viên)
- 📊 Quản lý điểm thi, thời khóa biểu, sự kiện
- 👨‍👩‍👧‍👦 Quản lý mối quan hệ phụ huynh – học sinh
- 🧑‍🏫 Quản lý vai trò, quyền hạn, và phân quyền truy cậpbiểu
