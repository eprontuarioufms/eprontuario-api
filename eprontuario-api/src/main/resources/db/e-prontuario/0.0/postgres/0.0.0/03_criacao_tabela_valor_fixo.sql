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

----[ VALOR FIXO ]----
CREATE SEQUENCE seq_valor_fixo
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE CACHE 1
;

CREATE TABLE IF NOT EXISTS tb_valor_fixo
(
    vf_id    INTEGER                NOT NULL,
    vf_chave CHARACTER VARYING(255) NOT NULL,
    vf_valor CHARACTER VARYING(255) NOT NULL,
    CONSTRAINT pk_vf_id PRIMARY KEY (vf_id)
);

COMMENT ON COLUMN tb_valor_fixo.vf_id IS 'Chave primária que armazena o identificador do registro da tabela de tb_valor_fixo';
COMMENT ON COLUMN tb_valor_fixo.vf_chave IS 'Armazena a descrição do registro';
COMMENT ON COLUMN tb_valor_fixo.vf_valor IS 'Armazena o valor referente a descrição';
