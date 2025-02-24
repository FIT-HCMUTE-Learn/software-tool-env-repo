SET FOREIGN_KEY_CHECKS = 0;

SET @tables = NULL;

SELECT GROUP_CONCAT('`', table_name, '`') INTO @tables
FROM information_schema.tables
WHERE table_schema = 'edu_central_extension';

SET @query = IFNULL(CONCAT('DROP TABLE ', @tables), 'SELECT "No tables to drop";');

PREPARE stmt FROM @query;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET FOREIGN_KEY_CHECKS = 1;
