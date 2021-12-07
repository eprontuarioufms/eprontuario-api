/*
Created: 22/07/21
Modified: --/--/--
Project: E-Prontuario
Model: PostgreSQL
Company: br.com.ufms
Author: Pedro Fernando Lima
Version: 1.0
Database: PostgreSQL
*/

----[ MODELO DOCUMENTO ]----
CREATE SEQUENCE seq_modelo_documento
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE CACHE 1
;

CREATE TABLE IF NOT EXISTS tb_modelo_documento
(
    md_id                INTEGER                NOT NULL,
    md_titulo            CHARACTER VARYING(100) NOT NULL,
    md_descricao         CHARACTER VARYING(255),
    md_status            CHARACTER VARYING(8)   NOT NULL,
    md_situacao          CHARACTER VARYING(7)   NOT NULL,
    md_dthr_cadastro     TIMESTAMP(6),
    md_dthr_alteracao    TIMESTAMP(6),
    md_usuario_cadastro  VARCHAR(255),
    md_usuario_alteracao VARCHAR(255),
    CONSTRAINT pk_modelo_documento PRIMARY KEY (md_id)
    );

COMMENT ON TABLE tb_modelo_documento IS 'Tabela contem o modelo de documento.';
COMMENT ON COLUMN tb_modelo_documento.md_id IS 'Chave primária que armazena o identificador do registro da tabela modelo documento';
COMMENT ON COLUMN tb_modelo_documento.md_titulo IS 'Coluna que armazena o titulo do documento.';
COMMENT ON COLUMN tb_modelo_documento.md_descricao IS 'Coluna que armazena a descricao do documentos.';
COMMENT ON COLUMN tb_modelo_documento.md_status IS 'Coluna que armazena o status do documento.';
COMMENT ON COLUMN tb_modelo_documento.md_situacao IS 'Coluna que armazena a situacao do documento.';
COMMENT ON COLUMN tb_modelo_documento.md_dthr_cadastro IS 'Coluna que armazena a data que foi cadastrado o documento.';
COMMENT ON COLUMN tb_modelo_documento.md_dthr_alteracao IS 'Coluna que armazena  a data que foi alterado o documento.';
COMMENT ON COLUMN tb_modelo_documento.md_usuario_cadastro IS 'Coluna que armazena o usuario que cadastrou o documento.';
COMMENT ON COLUMN tb_modelo_documento.md_usuario_alteracao IS 'Coluna que armazena  o usuario que alterou o documento.';
