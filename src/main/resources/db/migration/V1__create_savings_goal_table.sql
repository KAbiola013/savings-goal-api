CREATE TABLE savings_goal (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              target_amount DECIMAL(10,2) NOT NULL,
                              current_amount DECIMAL(10,2) DEFAULT 0,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);