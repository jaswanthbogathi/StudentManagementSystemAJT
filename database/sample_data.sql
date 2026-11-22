
USE student_management;

INSERT INTO departments (department_name)
VALUES ('Computer Science'),
       ('Electronics'),
       ('Mechanical Engineering');

INSERT INTO students
(name, gender, dob, phone, email, department_id)
SELECT 'Demo Student One', 'Female', '2004-05-12',
       '9000000001', 'demo1@example.com', department_id
FROM departments
WHERE department_name = 'Computer Science';

INSERT INTO students
(name, gender, dob, phone, email, department_id)
SELECT 'Demo Student Two', 'Male', '2003-11-08',
       '9000000002', 'demo2@example.com', department_id
FROM departments
WHERE department_name = 'Electronics';

INSERT INTO marks (student_id, subject, marks)
SELECT student_id, 'Java Programming', 86.5
FROM students WHERE email = 'demo1@example.com';

INSERT INTO marks (student_id, subject, marks)
SELECT student_id, 'Database Management', 91.0
FROM students WHERE email = 'demo2@example.com';
