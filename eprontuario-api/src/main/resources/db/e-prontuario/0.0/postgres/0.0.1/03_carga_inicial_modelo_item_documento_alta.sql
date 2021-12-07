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

-- DOC. ALTA
INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'DATAHORA',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Alta%'), 'Data e horário', NULL,
        NULL, NULL, true, 1)
;
INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Alta%'), 'Condições de saída',
        'deambulando, maca ou cadeira de rodas, presença de lesões, nível de consciência, presença de dispositivos como sonda vesical de demora, cateter de duplo lúmen, etc.',
        NULL, NULL, true, 1)
;
INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Alta%'), 'Procedimentos/cuidados realizados',
        'conforme prescrição ou rotina institucional (mensuração de sinais vitais, retirada de cateter venoso, etc.)',
        NULL, NULL, true, 1)
;
INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Alta%'), 'Orientações prestadas',
        NULL, NULL, NULL, true, 1)
;
INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Alta%'), 'Entrega do rol de pertences e valores ao paciente ou acompanhante',
        NULL, NULL, NULL, true, 1)
;
INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Alta%'), 'Transporte para o domicílio',
        NULL, NULL, NULL, true, 1)
;
INSERT INTO tb_modelo_item_doc (mid_id, mid_tipo, md_id, mid_titulo, mid_descricao, mid_id_item_pai, mid_vf_chave,
                                mid_is_obrigatorio, mid_qtd_max_registro)
VALUES (nextval('seq_modelo_item_documento'), 'STRING',
        (SELECT md.md_id FROM tb_modelo_documento AS md WHERE md.md_titulo LIKE '%Alta%'), 'Nome completo e Coren do responsável pelo procedimento',
        NULL, NULL, NULL, true, 1)
;
