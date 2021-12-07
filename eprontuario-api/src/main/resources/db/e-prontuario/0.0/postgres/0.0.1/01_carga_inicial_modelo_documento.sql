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
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Admissão', 'ATIVO', 'NOVO')
;
-- DOC. ALTA
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_descricao, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Alta',
        'Obs.: Importante o registro real do horário de saída do paciente e se saiu acompanhado. Registrar ainda se foi alta médica, administrativa ou a pedido do paciente ou família.',
        'ATIVO', 'NOVO')
;
-- DOC. ADMINISTRAÇÃO DE MEDICAMENTOS
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Administração de Medicamentos', 'ATIVO', 'NOVO')
;
-- DOC. ASPIRAÇÃO ORAL
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Aspiração oral', 'ATIVO', 'NOVO')
;
-- DOC. ASPIRAÇÃO TRAQUEAL (ENFERMEIRO)
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Aspiração traqueal (Enfermeiro)', 'ATIVO', 'NOVO')
;
-- DOC. Acesso venoso
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Acesso venoso', 'ATIVO', 'NOVO')
;
-- DOC. Avaliação do nível de consciência pelo enfermeiro
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Avaliação do nível de consciência pelo enfermeiro', 'ATIVO', 'NOVO')
;
-- DOC. Auxílio na dieta
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Auxílio na dieta', 'ATIVO', 'NOVO')
;
-- DOC. Banho de assento
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Banho de assento', 'ATIVO', 'NOVO')
;
-- DOC. Balanço hidroeletrolítico pelo enfermeiro
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Balanço hidroeletrolítico pelo enfermeiro', 'ATIVO', 'NOVO')
;
-- DOC. Coleta de linfa para hanseníase
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Coleta de linfa para hanseníase', 'ATIVO', 'NOVO')
;
-- DOC. Coleta de material para o teste do pezinho
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Coleta de material para o teste do pezinho', 'ATIVO', 'NOVO')
;
-- DOC. Coleta de exame citopatológico pelo enfermeiro
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Coleta de exame citopatológico pelo enfermeiro', 'ATIVO', 'NOVO')
;
-- DOC. Condutas de segurança ao paciente
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Condutas de segurança ao paciente', 'ATIVO', 'NOVO')
;
-- DOC. Consulta de enfermagem pelo enfermeiro
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Consulta de enfermagem pelo enfermeiro', 'ATIVO', 'NOVO')
;
-- DOC. Contenção no leito
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Contenção no leito', 'ATIVO', 'NOVO')
;
-- DOC. Controle da dor
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Controle da dor', 'ATIVO', 'NOVO')
;
-- DOC. Controle hídrico
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Controle hídrico', 'ATIVO', 'NOVO')
;
-- DOC. Classificação de risco
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Classificação de risco', 'ATIVO', 'NOVO')
;
-- DOC. Curativos
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Curativos', 'ATIVO', 'NOVO')
;
-- DOC. Cuidados com estomas
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Cuidados com estomas', 'ATIVO', 'NOVO')
;
-- DOC. Cuidados com os pés
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Cuidados com os pés', 'ATIVO', 'NOVO')
;
-- DOC. Cuidados Imediatos com o RN (Enfermeiro)
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Cuidados Imediatos com o RN (Enfermeiro)', 'ATIVO', 'NOVO')
;
-- DOC. Cuidados com o RN em fototerapia
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Cuidados com o RN em fototerapia', 'ATIVO', 'NOVO')
;
-- DOC. Cuidados no pré-parto
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Cuidados no pré-parto', 'ATIVO', 'NOVO')
;
-- DOC. Cuidados na sala de parto
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Cuidados na sala de parto', 'ATIVO', 'NOVO')
;
-- DOC. Cuidados no pós-parto imediato
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Cuidados no pós-parto imediato', 'ATIVO', 'NOVO')
;
-- DOC. Drenagem de tórax (Enfermeiro)
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Drenagem de tórax (Enfermeiro)', 'ATIVO', 'NOVO')
;
-- DOC. Drenos
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Drenos', 'ATIVO', 'NOVO')
;
-- DOC. Diálise peritoneal
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Diálise peritoneal', 'ATIVO', 'NOVO')
;
-- DOC. Encaminhamento para exames, Centro Cirúrgico e Centro Obstétrico
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Encaminhamento para exames, Centro Cirúrgico e Centro Obstétrico', 'ATIVO',
        'NOVO')
;
-- DOC. Enteróclise
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Enteróclise', 'ATIVO', 'NOVO')
;
-- DOC. Exame de Montenegro
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Exame de Montenegro', 'ATIVO', 'NOVO')
;
-- DOC. Glicemia capilar
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Glicemia capilar', 'ATIVO', 'NOVO')
;
-- DOC. Hemodiálise
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Hemodiálise', 'ATIVO', 'NOVO')
;
-- DOC. Higiene do paciente – banho
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Higiene do paciente - banho', 'ATIVO', 'NOVO')
;
-- DOC. Higiene do couro cabeludo
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Higiene do couro cabeludo', 'ATIVO', 'NOVO')
;
-- DOC. Higiene íntima
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Higiene íntima', 'ATIVO', 'NOVO')
;
-- DOC. Higiene oral
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Higiene oral', 'ATIVO', 'NOVO')
;
-- DOC. Imobilização
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Imobilização', 'ATIVO', 'NOVO')
;
-- DOC. Irrigação de sonda vesical e bexiga
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Irrigação de sonda vesical e bexiga', 'ATIVO', 'NOVO')
;
-- DOC. Inalação / nebulização
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Inalação/nebulização', 'ATIVO', 'NOVO')
;
-- DOC. Lavado gástrico
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Lavado gástrico', 'ATIVO', 'NOVO')
;
-- DOC. Massagem de conforto
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Massagem de conforto', 'ATIVO', 'NOVO')
;
-- DOC. Medida antropométrica
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Medida antropométrica', 'ATIVO', 'NOVO')
;
-- DOC. Mudança de decúbito
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Mudança de decúbito', 'ATIVO', 'NOVO')
;
-- DOC. Nutrição enteral
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Nutrição enteral', 'ATIVO', 'NOVO')
;
-- DOC. Nutrição parenteral pelo enfermeiro
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Nutrição parenteral pelo enfermeiro', 'ATIVO', 'NOVO')
;
-- DOC. Óbito
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Óbito', 'ATIVO', 'NOVO')
;
-- DOC. Ordenha mamária
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Ordenha mamária', 'ATIVO', 'NOVO')
;
-- DOC. Ordenha mamária
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Ordenha mamária', 'ATIVO', 'NOVO')
;
-- DOC. Punção arterial pelo enfermeiro
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Punção arterial pelo enfermeiro', 'ATIVO', 'NOVO')
;
-- DOC. Preparo psicológico do cliente / paciente
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Preparo psicológico do cliente/paciente', 'ATIVO', 'NOVO')
;
-- DOC. Pressão venosa central (PVC) pelo enfermeiro
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Pressão venosa central (PVC) pelo enfermeiro', 'ATIVO', 'NOVO')
;
-- DOC. Pressão arterial média (PAM) pelo enfermeiro
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Pressão arterial média (PAM) pelo enfermeiro', 'ATIVO', 'NOVO')
;
-- DOC. Pré-operatório
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Pré-operatório', 'ATIVO', 'NOVO')
;
-- DOC. Pós-operatório imediato
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Pós-operatório imediato', 'ATIVO', 'NOVO')
;
-- DOC. Pós-operatório mediato
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Pós-operatório mediato', 'ATIVO', 'NOVO')
;
-- DOC. Prova do laço
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Prova do laço', 'ATIVO', 'NOVO')
;
-- DOC. Registros relativos à coleta de material para exames
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Registros relativos à coleta de material para exames', 'ATIVO', 'NOVO')
;
-- DOC. Registros relativos à deambulação
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Registros relativos à deambulação', 'ATIVO', 'NOVO')
;
-- DOC. Retirada de pontos
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Retirada de pontos', 'ATIVO', 'NOVO')
;
-- DOC. Sinais vitais
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Sinais vitais', 'ATIVO', 'NOVO')
;
-- DOC. Teste de PPD
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Teste de PPD', 'ATIVO', 'NOVO')
;
-- DOC. Teste de gravidez
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Teste de gravidez', 'ATIVO', 'NOVO')
;
-- DOC. Teste rápido de HIV
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Teste rápido de HIV', 'ATIVO', 'NOVO')
;
-- DOC. Teste rápido para Sífilis
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Teste rápido para Sífilis', 'ATIVO', 'NOVO')
;
-- DOC. Teste para Hepatite
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Teste para Hepatite', 'ATIVO', 'NOVO')
;
-- DOC. Terapia de Reidratação Oral (TRO)
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Terapia de Reidratação Oral (TRO)', 'ATIVO', 'NOVO')
;
-- DOC. Transferência interna
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Transferência interna', 'ATIVO', 'NOVO')
;
-- DOC. Transferência externa
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Transferência externa', 'ATIVO', 'NOVO')
;
-- DOC. Tratamento de pediculose
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Tratamento de pediculose', 'ATIVO', 'NOVO')
;
-- DOC. Tratamento de miíase
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Tratamento de miíase', 'ATIVO', 'NOVO')
;
-- DOC. Transoperatório
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Transoperatório', 'ATIVO', 'NOVO')
;
-- DOC. Tricotomia
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Tricotomia', 'ATIVO', 'NOVO')
;
-- DOC. Vacina
INSERT INTO tb_modelo_documento (md_id, md_titulo, md_situacao, md_status)
VALUES (nextval('seq_modelo_documento'), 'Vacina', 'ATIVO', 'NOVO')
;

