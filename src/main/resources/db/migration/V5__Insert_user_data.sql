INSERT INTO users (create_at, name, email, phone_number, dob, gender, username, password, is_active, is_deleted, rank_id) VALUES
    (NOW(), 'Nguyen Van A', 'nguyenvana@example.com', '0987654321', '1990-01-01', 'MALE', 'nguyenvana', 'password123', 1, 0, 1),
    (NOW(), 'Tran Thi B', 'tranthib@example.com', '0976543210', '1992-05-15', 'FEMALE', 'tranthib', 'password456', 1, 0, 2),
    (NOW(), 'Le Van C', 'levanc@example.com', '0965432109', '1988-09-30', 'MALE', 'levanc', 'password789', 1, 0, 3);
