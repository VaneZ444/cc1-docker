CREATE TABLE train_model (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(100),
    year_of_production INTEGER,
    power_kw INTEGER,
    max_speed_kmh INTEGER
);

CREATE TABLE train_instance (
    id SERIAL PRIMARY KEY,
    serial_number VARCHAR(50) UNIQUE NOT NULL,
    manufacture_date DATE,
    image_url TEXT,
    model_id INTEGER NOT NULL,
    FOREIGN KEY (model_id) REFERENCES train_model(id) ON DELETE CASCADE
);

CREATE INDEX idx_instance_model ON train_instance(model_id);