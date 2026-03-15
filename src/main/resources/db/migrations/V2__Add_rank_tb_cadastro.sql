-- Migrations para adicionar  a coluna Rank na tabela de cadastro

ALTER TABLE TB_CADASTRO
ADD COLUMN rank VARCHAR(255);