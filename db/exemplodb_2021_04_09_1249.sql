--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.12
-- Dumped by pg_dump version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)

-- Started on 2021-04-09 13:49:26 WAT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE exemplodb;
--
-- TOC entry 2256 (class 1262 OID 826436)
-- Name: exemplodb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE exemplodb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE exemplodb OWNER TO postgres;

\connect exemplodb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- TOC entry 194 (class 1259 OID 930716)
-- Name: bairro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bairro (
    pk_bairro integer NOT NULL,
    nome text,
    fk_municipio integer,
    eliminado boolean
);


ALTER TABLE public.bairro OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 930714)
-- Name: bairro_pk_bairro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bairro_pk_bairro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bairro_pk_bairro_seq OWNER TO postgres;

--
-- TOC entry 2258 (class 0 OID 0)
-- Dependencies: 193
-- Name: bairro_pk_bairro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bairro_pk_bairro_seq OWNED BY public.bairro.pk_bairro;


--
-- TOC entry 192 (class 1259 OID 930703)
-- Name: estado_civil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado_civil (
    pk_estado_civil integer NOT NULL,
    descricao text,
    abreviatura text,
    eliminado boolean
);


ALTER TABLE public.estado_civil OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 930701)
-- Name: estado_civil_pk_estado_civil_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_civil_pk_estado_civil_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_civil_pk_estado_civil_seq OWNER TO postgres;

--
-- TOC entry 2259 (class 0 OID 0)
-- Dependencies: 191
-- Name: estado_civil_pk_estado_civil_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_civil_pk_estado_civil_seq OWNED BY public.estado_civil.pk_estado_civil;


--
-- TOC entry 198 (class 1259 OID 930805)
-- Name: ficheiro_anexado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ficheiro_anexado (
    pk_ficheiro_anexado integer NOT NULL,
    ficheiro text,
    fk_pessoa integer
);


ALTER TABLE public.ficheiro_anexado OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 930803)
-- Name: ficheiro_anexado_pk_ficheiro_anexado_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ficheiro_anexado_pk_ficheiro_anexado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ficheiro_anexado_pk_ficheiro_anexado_seq OWNER TO postgres;

--
-- TOC entry 2260 (class 0 OID 0)
-- Dependencies: 197
-- Name: ficheiro_anexado_pk_ficheiro_anexado_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ficheiro_anexado_pk_ficheiro_anexado_seq OWNED BY public.ficheiro_anexado.pk_ficheiro_anexado;


--
-- TOC entry 190 (class 1259 OID 930677)
-- Name: municipio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.municipio (
    pk_municipio integer NOT NULL,
    nome text,
    fk_provincia integer,
    eliminado boolean
);


ALTER TABLE public.municipio OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 930675)
-- Name: municipio_pk_municipio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.municipio_pk_municipio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.municipio_pk_municipio_seq OWNER TO postgres;

--
-- TOC entry 2261 (class 0 OID 0)
-- Dependencies: 189
-- Name: municipio_pk_municipio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.municipio_pk_municipio_seq OWNED BY public.municipio.pk_municipio;


--
-- TOC entry 182 (class 1259 OID 826439)
-- Name: pais; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pais (
    pk_pais integer NOT NULL,
    nome text,
    nacionalidade text,
    eliminado boolean
);


ALTER TABLE public.pais OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 826437)
-- Name: pais_pk_pais_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pais_pk_pais_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pais_pk_pais_seq OWNER TO postgres;

--
-- TOC entry 2262 (class 0 OID 0)
-- Dependencies: 181
-- Name: pais_pk_pais_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pais_pk_pais_seq OWNED BY public.pais.pk_pais;


--
-- TOC entry 196 (class 1259 OID 930732)
-- Name: pessoa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa (
    pk_pessoa integer NOT NULL,
    nome_completo text,
    nome_pai text,
    nome_mae text,
    data_nascimento timestamp without time zone,
    numero_contribuinte text,
    rua text,
    num_casa text,
    fk_sexo integer,
    fk_estado_civil integer,
    fk_pais integer,
    fk_municipio integer,
    fk_provincia integer,
    fk_bairro integer,
    eliminado boolean
);


ALTER TABLE public.pessoa OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 930730)
-- Name: pessoa_pk_pessoa_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pessoa_pk_pessoa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pessoa_pk_pessoa_seq OWNER TO postgres;

--
-- TOC entry 2263 (class 0 OID 0)
-- Dependencies: 195
-- Name: pessoa_pk_pessoa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pessoa_pk_pessoa_seq OWNED BY public.pessoa.pk_pessoa;


--
-- TOC entry 188 (class 1259 OID 930664)
-- Name: profissao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profissao (
    pk_profissao integer NOT NULL,
    descricao text,
    eliminado boolean
);


ALTER TABLE public.profissao OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 930662)
-- Name: profissao_pk_profissao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profissao_pk_profissao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profissao_pk_profissao_seq OWNER TO postgres;

--
-- TOC entry 2264 (class 0 OID 0)
-- Dependencies: 187
-- Name: profissao_pk_profissao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profissao_pk_profissao_seq OWNED BY public.profissao.pk_profissao;


--
-- TOC entry 184 (class 1259 OID 826468)
-- Name: provincia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.provincia (
    pk_provincia integer NOT NULL,
    nome text,
    fk_pais integer,
    eliminado boolean
);


ALTER TABLE public.provincia OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 826466)
-- Name: provincia_pk_provincia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.provincia_pk_provincia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.provincia_pk_provincia_seq OWNER TO postgres;

--
-- TOC entry 2265 (class 0 OID 0)
-- Dependencies: 183
-- Name: provincia_pk_provincia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.provincia_pk_provincia_seq OWNED BY public.provincia.pk_provincia;


--
-- TOC entry 186 (class 1259 OID 930651)
-- Name: sexo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sexo (
    pk_sexo integer NOT NULL,
    descricao text,
    abreviatura text,
    eliminado boolean
);


ALTER TABLE public.sexo OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 930649)
-- Name: sexo_pk_sexo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sexo_pk_sexo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sexo_pk_sexo_seq OWNER TO postgres;

--
-- TOC entry 2266 (class 0 OID 0)
-- Dependencies: 185
-- Name: sexo_pk_sexo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sexo_pk_sexo_seq OWNED BY public.sexo.pk_sexo;


--
-- TOC entry 2080 (class 2604 OID 930719)
-- Name: bairro pk_bairro; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bairro ALTER COLUMN pk_bairro SET DEFAULT nextval('public.bairro_pk_bairro_seq'::regclass);


--
-- TOC entry 2079 (class 2604 OID 930706)
-- Name: estado_civil pk_estado_civil; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_civil ALTER COLUMN pk_estado_civil SET DEFAULT nextval('public.estado_civil_pk_estado_civil_seq'::regclass);


--
-- TOC entry 2082 (class 2604 OID 930808)
-- Name: ficheiro_anexado pk_ficheiro_anexado; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficheiro_anexado ALTER COLUMN pk_ficheiro_anexado SET DEFAULT nextval('public.ficheiro_anexado_pk_ficheiro_anexado_seq'::regclass);


--
-- TOC entry 2078 (class 2604 OID 930680)
-- Name: municipio pk_municipio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipio ALTER COLUMN pk_municipio SET DEFAULT nextval('public.municipio_pk_municipio_seq'::regclass);


--
-- TOC entry 2074 (class 2604 OID 826442)
-- Name: pais pk_pais; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pais ALTER COLUMN pk_pais SET DEFAULT nextval('public.pais_pk_pais_seq'::regclass);


--
-- TOC entry 2081 (class 2604 OID 930735)
-- Name: pessoa pk_pessoa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa ALTER COLUMN pk_pessoa SET DEFAULT nextval('public.pessoa_pk_pessoa_seq'::regclass);


--
-- TOC entry 2077 (class 2604 OID 930667)
-- Name: profissao pk_profissao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profissao ALTER COLUMN pk_profissao SET DEFAULT nextval('public.profissao_pk_profissao_seq'::regclass);


--
-- TOC entry 2075 (class 2604 OID 826471)
-- Name: provincia pk_provincia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.provincia ALTER COLUMN pk_provincia SET DEFAULT nextval('public.provincia_pk_provincia_seq'::regclass);


--
-- TOC entry 2076 (class 2604 OID 930654)
-- Name: sexo pk_sexo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexo ALTER COLUMN pk_sexo SET DEFAULT nextval('public.sexo_pk_sexo_seq'::regclass);


--
-- TOC entry 2246 (class 0 OID 930716)
-- Dependencies: 194
-- Data for Name: bairro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bairro (pk_bairro, nome, fk_municipio, eliminado) VALUES (1, 'Estalagem', 2, false);
INSERT INTO public.bairro (pk_bairro, nome, fk_municipio, eliminado) VALUES (2, 'Praia do Bispo', 1, false);
INSERT INTO public.bairro (pk_bairro, nome, fk_municipio, eliminado) VALUES (3, 'Bairro Azul', 1, true);


--
-- TOC entry 2244 (class 0 OID 930703)
-- Dependencies: 192
-- Data for Name: estado_civil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estado_civil (pk_estado_civil, descricao, abreviatura, eliminado) VALUES (1, 'Casado (a)', 'C', false);
INSERT INTO public.estado_civil (pk_estado_civil, descricao, abreviatura, eliminado) VALUES (2, 'Solteiro (a)', 'S', false);


--
-- TOC entry 2250 (class 0 OID 930805)
-- Dependencies: 198
-- Data for Name: ficheiro_anexado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ficheiro_anexado (pk_ficheiro_anexado, ficheiro, fk_pessoa) VALUES (1, 'foto_teste_2021_3_26_19_1_1.jpeg', 1);
INSERT INTO public.ficheiro_anexado (pk_ficheiro_anexado, ficheiro, fk_pessoa) VALUES (2, 'foto_user_2021_3_31_6_21_40.png', 2);
INSERT INTO public.ficheiro_anexado (pk_ficheiro_anexado, ficheiro, fk_pessoa) VALUES (3, 'foto_user_other_2021_4_9_12_40_36.jpg', 1);


--
-- TOC entry 2242 (class 0 OID 930677)
-- Dependencies: 190
-- Data for Name: municipio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.municipio (pk_municipio, nome, fk_provincia, eliminado) VALUES (1, 'Luanda', 1, false);
INSERT INTO public.municipio (pk_municipio, nome, fk_provincia, eliminado) VALUES (2, 'Viana', 1, false);


--
-- TOC entry 2234 (class 0 OID 826439)
-- Dependencies: 182
-- Data for Name: pais; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pais (pk_pais, nome, nacionalidade, eliminado) VALUES (1, 'Angola', 'Angolana', false);
INSERT INTO public.pais (pk_pais, nome, nacionalidade, eliminado) VALUES (2, 'Alemanha', 'Alemã', false);
INSERT INTO public.pais (pk_pais, nome, nacionalidade, eliminado) VALUES (5, 'Outro', 'Outro', false);
INSERT INTO public.pais (pk_pais, nome, nacionalidade, eliminado) VALUES (4, 'PaisTeste', 'PaisNacionalidadeTeste', true);
INSERT INTO public.pais (pk_pais, nome, nacionalidade, eliminado) VALUES (3, 'Cabo Verde', 'Cabo Verdiana', false);
INSERT INTO public.pais (pk_pais, nome, nacionalidade, eliminado) VALUES (6, 'Moçambique', 'Moçambicana', false);
INSERT INTO public.pais (pk_pais, nome, nacionalidade, eliminado) VALUES (7, 'Namíbia', 'Namibiana', false);


--
-- TOC entry 2248 (class 0 OID 930732)
-- Dependencies: 196
-- Data for Name: pessoa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pessoa (pk_pessoa, nome_completo, nome_pai, nome_mae, data_nascimento, numero_contribuinte, rua, num_casa, fk_sexo, fk_estado_civil, fk_pais, fk_municipio, fk_provincia, fk_bairro, eliminado) VALUES (1, 'Teste', 'Teste Pai', 'Teste Mae', '2017-03-07 00:00:00', '12345793', 'N/A', 'N/A', 1, 1, 2, 2, 1, 1, false);
INSERT INTO public.pessoa (pk_pessoa, nome_completo, nome_pai, nome_mae, data_nascimento, numero_contribuinte, rua, num_casa, fk_sexo, fk_estado_civil, fk_pais, fk_municipio, fk_provincia, fk_bairro, eliminado) VALUES (2, 'Jorge Neto', 'Jorge Neto', 'Marta Melo Neto', '2006-03-23 00:00:00', '4563784LA233', 'Desconhecida', 'Desconhecido', 1, 1, 1, 2, 1, 1, false);


--
-- TOC entry 2240 (class 0 OID 930664)
-- Dependencies: 188
-- Data for Name: profissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.profissao (pk_profissao, descricao, eliminado) VALUES (1, 'Engenheiro', false);


--
-- TOC entry 2236 (class 0 OID 826468)
-- Dependencies: 184
-- Data for Name: provincia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.provincia (pk_provincia, nome, fk_pais, eliminado) VALUES (1, 'Luanda', 1, false);
INSERT INTO public.provincia (pk_provincia, nome, fk_pais, eliminado) VALUES (2, 'Cuanza Norte', 1, false);
INSERT INTO public.provincia (pk_provincia, nome, fk_pais, eliminado) VALUES (4, 'Malange', 1, false);


--
-- TOC entry 2238 (class 0 OID 930651)
-- Dependencies: 186
-- Data for Name: sexo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sexo (pk_sexo, descricao, abreviatura, eliminado) VALUES (1, 'Feminino', 'F', false);
INSERT INTO public.sexo (pk_sexo, descricao, abreviatura, eliminado) VALUES (2, 'Masculino', 'M', false);


--
-- TOC entry 2267 (class 0 OID 0)
-- Dependencies: 193
-- Name: bairro_pk_bairro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bairro_pk_bairro_seq', 3, true);


--
-- TOC entry 2268 (class 0 OID 0)
-- Dependencies: 191
-- Name: estado_civil_pk_estado_civil_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_civil_pk_estado_civil_seq', 2, true);


--
-- TOC entry 2269 (class 0 OID 0)
-- Dependencies: 197
-- Name: ficheiro_anexado_pk_ficheiro_anexado_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ficheiro_anexado_pk_ficheiro_anexado_seq', 3, true);


--
-- TOC entry 2270 (class 0 OID 0)
-- Dependencies: 189
-- Name: municipio_pk_municipio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.municipio_pk_municipio_seq', 2, true);


--
-- TOC entry 2271 (class 0 OID 0)
-- Dependencies: 181
-- Name: pais_pk_pais_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pais_pk_pais_seq', 7, true);


--
-- TOC entry 2272 (class 0 OID 0)
-- Dependencies: 195
-- Name: pessoa_pk_pessoa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pessoa_pk_pessoa_seq', 2, true);


--
-- TOC entry 2273 (class 0 OID 0)
-- Dependencies: 187
-- Name: profissao_pk_profissao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profissao_pk_profissao_seq', 1, true);


--
-- TOC entry 2274 (class 0 OID 0)
-- Dependencies: 183
-- Name: provincia_pk_provincia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.provincia_pk_provincia_seq', 4, true);


--
-- TOC entry 2275 (class 0 OID 0)
-- Dependencies: 185
-- Name: sexo_pk_sexo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sexo_pk_sexo_seq', 2, true);


--
-- TOC entry 2104 (class 2606 OID 930724)
-- Name: bairro bairro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bairro
    ADD CONSTRAINT bairro_pkey PRIMARY KEY (pk_bairro);


--
-- TOC entry 2100 (class 2606 OID 930713)
-- Name: estado_civil estado_civil_descricao_abreviatura_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_civil
    ADD CONSTRAINT estado_civil_descricao_abreviatura_key UNIQUE (descricao, abreviatura);


--
-- TOC entry 2102 (class 2606 OID 930711)
-- Name: estado_civil estado_civil_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_civil
    ADD CONSTRAINT estado_civil_pkey PRIMARY KEY (pk_estado_civil);


--
-- TOC entry 2108 (class 2606 OID 930813)
-- Name: ficheiro_anexado ficheiro_anexado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficheiro_anexado
    ADD CONSTRAINT ficheiro_anexado_pkey PRIMARY KEY (pk_ficheiro_anexado);


--
-- TOC entry 2098 (class 2606 OID 930685)
-- Name: municipio municipio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT municipio_pkey PRIMARY KEY (pk_municipio);


--
-- TOC entry 2084 (class 2606 OID 826449)
-- Name: pais pais_nome_nacionalidade_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pais
    ADD CONSTRAINT pais_nome_nacionalidade_key UNIQUE (nome, nacionalidade);


--
-- TOC entry 2086 (class 2606 OID 826447)
-- Name: pais pais_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pais
    ADD CONSTRAINT pais_pkey PRIMARY KEY (pk_pais);


--
-- TOC entry 2106 (class 2606 OID 930740)
-- Name: pessoa pessoa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (pk_pessoa);


--
-- TOC entry 2094 (class 2606 OID 930674)
-- Name: profissao profissao_descricao_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profissao
    ADD CONSTRAINT profissao_descricao_key UNIQUE (descricao);


--
-- TOC entry 2096 (class 2606 OID 930672)
-- Name: profissao profissao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profissao
    ADD CONSTRAINT profissao_pkey PRIMARY KEY (pk_profissao);


--
-- TOC entry 2088 (class 2606 OID 826476)
-- Name: provincia provincia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (pk_provincia);


--
-- TOC entry 2090 (class 2606 OID 930661)
-- Name: sexo sexo_descricao_abreviatura_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexo
    ADD CONSTRAINT sexo_descricao_abreviatura_key UNIQUE (descricao, abreviatura);


--
-- TOC entry 2092 (class 2606 OID 930659)
-- Name: sexo sexo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexo
    ADD CONSTRAINT sexo_pkey PRIMARY KEY (pk_sexo);


--
-- TOC entry 2111 (class 2606 OID 930725)
-- Name: bairro bairro_fk_municipio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bairro
    ADD CONSTRAINT bairro_fk_municipio_fkey FOREIGN KEY (fk_municipio) REFERENCES public.municipio(pk_municipio);


--
-- TOC entry 2118 (class 2606 OID 930814)
-- Name: ficheiro_anexado ficheiro_anexado_fk_pessoa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficheiro_anexado
    ADD CONSTRAINT ficheiro_anexado_fk_pessoa_fkey FOREIGN KEY (fk_pessoa) REFERENCES public.pessoa(pk_pessoa);


--
-- TOC entry 2110 (class 2606 OID 930686)
-- Name: municipio municipio_fk_provincia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT municipio_fk_provincia_fkey FOREIGN KEY (fk_provincia) REFERENCES public.provincia(pk_provincia);


--
-- TOC entry 2117 (class 2606 OID 930766)
-- Name: pessoa pessoa_fk_bairro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_fk_bairro_fkey FOREIGN KEY (fk_bairro) REFERENCES public.bairro(pk_bairro);


--
-- TOC entry 2113 (class 2606 OID 930746)
-- Name: pessoa pessoa_fk_estado_civil_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_fk_estado_civil_fkey FOREIGN KEY (fk_estado_civil) REFERENCES public.estado_civil(pk_estado_civil);


--
-- TOC entry 2115 (class 2606 OID 930756)
-- Name: pessoa pessoa_fk_municipio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_fk_municipio_fkey FOREIGN KEY (fk_municipio) REFERENCES public.municipio(pk_municipio);


--
-- TOC entry 2114 (class 2606 OID 930751)
-- Name: pessoa pessoa_fk_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_fk_pais_fkey FOREIGN KEY (fk_pais) REFERENCES public.pais(pk_pais);


--
-- TOC entry 2116 (class 2606 OID 930761)
-- Name: pessoa pessoa_fk_provincia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_fk_provincia_fkey FOREIGN KEY (fk_provincia) REFERENCES public.provincia(pk_provincia);


--
-- TOC entry 2112 (class 2606 OID 930741)
-- Name: pessoa pessoa_fk_sexo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_fk_sexo_fkey FOREIGN KEY (fk_sexo) REFERENCES public.sexo(pk_sexo);


--
-- TOC entry 2109 (class 2606 OID 826477)
-- Name: provincia provincia_fk_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.provincia
    ADD CONSTRAINT provincia_fk_pais_fkey FOREIGN KEY (fk_pais) REFERENCES public.pais(pk_pais);


--
-- TOC entry 2257 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2021-04-09 13:49:26 WAT

--
-- PostgreSQL database dump complete
--

