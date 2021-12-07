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

-- DOC. ADMISSAO
INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'), 'Nome completo do paciente', NULL,
        NULL, NULL, true, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'DATAHORA',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'), 'Data e hora da admissão', NULL,
        NULL, NULL, true, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'), 'Condições de chegada', NULL,
        NULL, NULL, true, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'VALORFIXO',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'), 'Nível de consciência', NULL,
        NULL, NULL, true, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'),
        'Presença de acompanhante ou responsável', NULL,
        NULL, NULL, true, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'), 'Condições de higiene', NULL,
        NULL, NULL, true, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'),
        'Presença de lesões prévias e sua localização',
        'Feridas corto-contusas, hematoma, úlceras de pressão ou crônicas, e outras.',
        NULL, NULL, true, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'), 'Descrever deficiências',
        'Descreva deficiências, se houver.',
        NULL, NULL, false, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'), 'Uso de próteses ou órteses',
        'Descreva se houver.',
        NULL, NULL, false, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'),
        'Queixas relacionadas ao motivo da internação',
        NULL, NULL, NULL, false, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'),
        'Procedimentos/cuidados realizados',
        'Procedimentos/cuidados realizados conforme prescrição ou rotina institucional (mensuração de sinais vitais, punção de acesso venoso, coleta de exames, necessidade de elevação de grades, contenção, etc.',
        NULL, NULL, TRUE, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'DOUBLE',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'),
        'Rol de valores e pertences do paciente',
        NULL, NULL, NULL, TRUE, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'),
        'Orientações prestadas',
        NULL, NULL, NULL, TRUE, 1)
;

INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Admissão%'),
        'Nome completo e Coren do responsável pelo procedimento',
        NULL, NULL, NULL, TRUE, 1)
;
