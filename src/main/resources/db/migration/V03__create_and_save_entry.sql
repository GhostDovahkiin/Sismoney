CREATE TABLE entries(
  id IDENTITY NOT NULL PRIMARY KEY,
  entry_description VARCHAR(50) NOT NULL,
  due_date DATE NOT NULL,
  pay_date DATE,
  amount DECIMAL(10,2) NOT NULL,
  observation VARCHAR(100),
  entry_type VARCHAR(20) NOT NULL,
  category_id INT NOT NULL,
  person_id INT NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category(id),
  FOREIGN KEY (person_id) REFERENCES person(id)
);