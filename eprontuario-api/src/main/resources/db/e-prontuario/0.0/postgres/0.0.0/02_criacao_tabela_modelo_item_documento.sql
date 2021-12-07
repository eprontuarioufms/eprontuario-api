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


----[ MODELO ITEM DOCUMENTO ]----
CREATE SEQUENCE seq_modelo_item_documento
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE CACHE 1
;

CREATE TABLE IF NOT EXISTS tb_modelo_item_doc
(
    mid_id               INTEGER                NOT NULL,
    mid_tipo             CHARACTER VARYING(9)   NOT NULL,
    md_id                INTEGER                NOT NULL,
    mid_titulo           CHARACTER VARYING(100) NOT NULL,
    mid_descricao        CHARACTER VARYING(255),
    mid_id_item_pai      INTEGER,
    mid_vf_chave         CHARACTER VARYING(255),
    mid_is_obrigatorio   BOOLEAN                NOT NULL,
    mid_qtd_max_registro INTEGER                NOT NULL,
    mid_dthr_cadastro     TIMESTAMP(6),
    mid_dthr_alteracao    TIMESTAMP(6),
    mid_usuario_cadastro  VARCHAR(255),
    mid_usuario_alteracao VARCHAR(255),
    CONSTRAINT pk_modelo_item_doc PRIMARY KEY (mid_id),
    CONSTRAINT fk_md_id FOREIGN KEY (md_id) REFERENCES tb_modelo_documento (md_id)
);

COMMENT ON TABLE tb_modelo_item_doc IS 'Tabela contem o modelo item documento.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_id IS 'Chave primária que armazena o identificador do registro da tabela modelo item documento';
COMMENT ON COLUMN tb_modelo_item_doc.mid_tipo IS 'Coluna que armazena o tipo primitivo do item de documento.';
COMMENT ON COLUMN tb_modelo_item_doc.md_id IS 'Coluna que armazena chave estrangeira da tabela tb_modelo_documento.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_titulo IS 'Coluna que armazena o titulo do item documento.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_descricao IS 'Coluna que armazena a descricao do item documento.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_id_item_pai IS 'Coluna que armazena o identificado de um item documento caso item documento seja filho de outro item documento.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_vf_chave IS 'Coluna que armazena um texto para pesquisar no valor chave da tabela tb_valor_fixo.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_is_obrigatorio IS 'Coluna que armazena se o item documento é obrigatório.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_qtd_max_registro IS 'Coluna que armazena a quantidade de itens documentos que podem ser criados.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_dthr_cadastro IS 'Coluna que armazena a data que foi cadastrado o item documento.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_dthr_alteracao IS 'Coluna que armazena  a data que foi alterado o item documento.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_usuario_cadastro IS 'Coluna que armazena o usuario que cadastrou o item documento.';
COMMENT ON COLUMN tb_modelo_item_doc.mid_usuario_alteracao IS 'Coluna que armazena  o usuario que alterou o item documento.';