/*
Created: 21/07/21
Modified: --/--/--
Project: E-Prontuario
Model: PostgreSQL
Company: br.com.ufms
Author: Pedro Fernando Lima
Version: 1.0
Database: PostgreSQL
*/

-- VALOR FIXO
INSERT INTO tb_valor_fixo (vf_id, vf_chave, vf_valor)
VALUES (nextval('seq_valor_fixo'), 'Nível de consciência', 'Lúcido')
;
INSERT INTO tb_valor_fixo (vf_id, vf_chave, vf_valor)
VALUES (nextval('seq_valor_fixo'), 'Nível de consciência', 'Letárgico')
;
INSERT INTO tb_valor_fixo (vf_id, vf_chave, vf_valor)
VALUES (nextval('seq_valor_fixo'), 'Nível de consciência', 'Torporoso')
;
INSERT INTO tb_valor_fixo (vf_id, vf_chave, vf_valor)
VALUES (nextval('seq_valor_fixo'), 'Nível de consciência', 'Coma')
;

--Doc. Via parenteral--
INSERT INTO tb_valor_fixo (vf_id, vf_chave, vf_valor)
VALUES (nextval('seq_valor_fixo'), 'VIA_PARENTERAL', 'M')
;
INSERT INTO tb_valor_fixo (vf_id, vf_chave, vf_valor)
VALUES (nextval('seq_valor_fixo'), 'VIA_PARENTERAL', 'EV')
;
INSERT INTO tb_valor_fixo (vf_id, vf_chave, vf_valor)
VALUES (nextval('seq_valor_fixo'), 'VIA_PARENTERAL', 'SC')
;
INSERT INTO tb_valor_fixo (vf_id, vf_chave, vf_valor)
VALUES (nextval('seq_valor_fixo'), 'VIA_PARENTERAL', 'ID')
;
