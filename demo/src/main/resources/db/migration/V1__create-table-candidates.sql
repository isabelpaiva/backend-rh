CREATE TABLE candidates (
    cod_candidate SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    status VARCHAR DEFAULT 'Recebidos'
);
