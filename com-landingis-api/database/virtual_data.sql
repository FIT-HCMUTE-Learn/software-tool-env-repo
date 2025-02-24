USE edu_central_extension;

-- Insert periods
INSERT INTO periods (id, name, description, start_date, due_date, state) VALUES
(1, 'Spring 2024', 'Spring semester of 2024', '2024-01-10', '2024-05-25', 1),
(2, 'Summer 2024', 'Summer semester of 2024', '2024-06-05', '2024-08-20', 1),
(3, 'Fall 2024', 'Fall semester of 2024', '2024-09-01', '2024-12-15', 1);

-- Insert lecturer_schedulers
INSERT INTO lecturer_schedulers (id, lecturer_id, course_id, period_id) VALUES
(1, 9, 1, 1),
(2, 10, 3, 1),
(3, 9, 2, 2),
(4, 10, 4, 2),
(5, 9, 5, 3),
(6, 10, 6, 3);

