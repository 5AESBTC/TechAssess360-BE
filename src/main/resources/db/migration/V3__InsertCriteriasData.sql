CREATE TABLE answers
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    title       VARCHAR(255)          NULL,
    value       INT                   NOT NULL,
    question_id BIGINT                NULL,
    CONSTRAINT pk_answers PRIMARY KEY (id)
);

ALTER TABLE answers
    ADD CONSTRAINT FK_ANSWERS_ON_QUESTION FOREIGN KEY (question_id) REFERENCES questions (id);

INSERT INTO criterias (title, point)
VALUES ('Hiệu suất công việc', 30),
       ('Kỹ năng và kiến thức', 20),
       ('Tinh thần làm việc và thái độ', 20),
       ('Đóng góp và sáng kiến', 15),
       ('Quy định và chính sách', 15),
       ('Đóng góp cá nhân và kết quả', null),
       ('Mục tiêu quý tiếp theo', null),
       ('Đánh giá của quản lý', null);

INSERT INTO questions (title, point, criteria_id)
VALUES ('Mức độ hoàn thành mục tiêu công việc được giao trong thời gian qua?', 15, 1),
       ('Mức độ chính xác của công việc?', 10, 1),
       ('Mức độ thường xuyên hoàn thành công việc đúng hạn?', 5, 1),
       ('Mức độ cải thiện kỹ năng chuyên môn trong quý vừa qua? ', 10, 2),
       ('Mức độ hiệu quả khi áp dụng kiến thức mới vào công việc? ', 5, 2),
       ('Kĩ năng cần cải thiện để nâng cao hiệu suất?', 5, 2),
       ('Mức độ thường xuyên hỗ trợ đồng nghiệp trong công việc?', 10, 3),
       ('Thái độ làm việc trong công việc?', 5, 3),
       ('Cách xử lý khi gặp tình huống/task khó?', 5, 3),
       ('Mức độ đóng góp và đưa ra các sáng kiến?', 15, 4),
       ('Mức độ tuân thủ quy định và chính sách trong công ty?', 15, 5);

INSERT INTO answers (title, value, question_id)
VALUES ('50%', 1, 1),
       ('75%', 2, 1),
       ('100%', 3, 1),
       ('150%', 4, 1),
       ('200%', 5, 1),
       ('50%', 1, 2),
       ('75%', 2, 2),
       ('100%', 3, 2),
       ('150%', 4, 2),
       ('200%', 5, 2),
       ('Hiếm khi', 1, 3),
       ('Thỉnh thoảng', 2, 3),
       ('Đôi khi', 3, 3),
       ('Thường xuyên', 4, 3),
       ('Luôn luôn', 5, 3),
       ('Không cải thiện', 1, 4),
       ('Cải thiện ít', 2, 4),
       ('Cải thiện vừa phải', 3, 4),
       ('Cải thiện đáng kể', 4, 4),
       ('Cải thiện vượt bậc', 5, 4),
       ('Hoàn toàn không', 1, 5),
       ('Ít hiệu quả', 2, 5),
       ('Hiệu quả trung bình', 3, 5),
       ('Khá hiệu quả', 4, 5),
       ('Rất hiệu quả', 5, 5),
       ('Quản lý thời gian', 1, 6),
       ('Giao tiếp và hợp tác', 2, 6),
       ('Chuyên môn kỹ thuật', 3, 6),
       ('Giải quyết vấn đề và ra quyết định', 4, 6),
       ('Lãnh đạo và quản lý đội nhóm', 5, 6),
       ('Rất hiếm khi', 1, 7),
       ('Thỉnh thoảng', 2, 7),
       ('Đôi khi', 3, 7),
       ('Thường xuyên', 4, 7),
       ('Luôn luôn', 5, 7),
       ('Thiếu động lực', 1, 8),
       ('Hơi thụ động', 2, 8),
       ('Bình thường', 3, 8),
       ('Tích cực', 4, 8),
       ('Rất chủ động', 5, 8),
       ('Chưa xử lý tốt', 1, 9),
       ('Xử lý chậm', 2, 9),
       ('Xử lý mức cơ bản', 3, 9),
       ('Xử lý tốt', 4, 9),
       ('Xử lý xuất sắc', 5, 9),
       ('Hầu như không', 1, 10),
       ('Có ít đóng góp', 2, 10),
       ('Đóng góp trung bình', 3, 10),
       ('Có nhiều đóng góp', 4, 10),
       ('Có rất nhiều đóng góp', 5, 10),
       ('Không tuân thủ', 1, 11),
       ('Tuân thủ ít', 2, 11),
       ('Tuân thủ trung bình', 3, 11),
       ('Tuân thủ tốt', 4, 11),
       ('Tuân thủ hoàn toàn', 5, 11);