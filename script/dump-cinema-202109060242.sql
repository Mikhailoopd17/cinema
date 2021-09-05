--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

-- Started on 2021-09-06 02:42:44

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

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 204 (class 1259 OID 16750)
-- Name: cinemas; Type: TABLE; Schema: public; Owner: cinema
--

CREATE TABLE public.cinemas (
    id integer NOT NULL,
    name character varying NOT NULL,
    is_deleted boolean NOT NULL
);


ALTER TABLE public.cinemas OWNER TO cinema;

--
-- TOC entry 205 (class 1259 OID 16758)
-- Name: cinemas_id_seq; Type: SEQUENCE; Schema: public; Owner: cinema
--

CREATE SEQUENCE public.cinemas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cinemas_id_seq OWNER TO cinema;

--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 205
-- Name: cinemas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cinema
--

ALTER SEQUENCE public.cinemas_id_seq OWNED BY public.cinemas.id;


--
-- TOC entry 202 (class 1259 OID 16742)
-- Name: places; Type: TABLE; Schema: public; Owner: cinema
--

CREATE TABLE public.places (
    id integer NOT NULL,
    "row" integer NOT NULL,
    number integer NOT NULL,
    cinema_id integer NOT NULL,
    is_deleted boolean NOT NULL
);


ALTER TABLE public.places OWNER TO cinema;

--
-- TOC entry 203 (class 1259 OID 16747)
-- Name: places_id_seq; Type: SEQUENCE; Schema: public; Owner: cinema
--

CREATE SEQUENCE public.places_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.places_id_seq OWNER TO cinema;

--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 203
-- Name: places_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cinema
--

ALTER SEQUENCE public.places_id_seq OWNED BY public.places.id;


--
-- TOC entry 208 (class 1259 OID 16769)
-- Name: sessions; Type: TABLE; Schema: public; Owner: cinema
--

CREATE TABLE public.sessions (
    id integer NOT NULL,
    name character varying(256) NOT NULL,
    cinema_id integer NOT NULL,
    is_deleted boolean NOT NULL
);


ALTER TABLE public.sessions OWNER TO cinema;

--
-- TOC entry 209 (class 1259 OID 16774)
-- Name: sessions_id_seq; Type: SEQUENCE; Schema: public; Owner: cinema
--

CREATE SEQUENCE public.sessions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sessions_id_seq OWNER TO cinema;

--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 209
-- Name: sessions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cinema
--

ALTER SEQUENCE public.sessions_id_seq OWNED BY public.sessions.id;


--
-- TOC entry 206 (class 1259 OID 16761)
-- Name: tickets; Type: TABLE; Schema: public; Owner: cinema
--

CREATE TABLE public.tickets (
    id integer NOT NULL,
    customer character varying(128) NOT NULL,
    place_id integer NOT NULL,
    session_id integer NOT NULL,
    sale_date timestamp without time zone NOT NULL,
    is_deleted boolean NOT NULL
);


ALTER TABLE public.tickets OWNER TO cinema;

--
-- TOC entry 207 (class 1259 OID 16766)
-- Name: tickets_id_seq; Type: SEQUENCE; Schema: public; Owner: cinema
--

CREATE SEQUENCE public.tickets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tickets_id_seq OWNER TO cinema;

--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 207
-- Name: tickets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cinema
--

ALTER SEQUENCE public.tickets_id_seq OWNED BY public.tickets.id;


--
-- TOC entry 2707 (class 2604 OID 16760)
-- Name: cinemas id; Type: DEFAULT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.cinemas ALTER COLUMN id SET DEFAULT nextval('public.cinemas_id_seq'::regclass);


--
-- TOC entry 2706 (class 2604 OID 16749)
-- Name: places id; Type: DEFAULT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.places ALTER COLUMN id SET DEFAULT nextval('public.places_id_seq'::regclass);


--
-- TOC entry 2709 (class 2604 OID 16776)
-- Name: sessions id; Type: DEFAULT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.sessions ALTER COLUMN id SET DEFAULT nextval('public.sessions_id_seq'::regclass);


--
-- TOC entry 2708 (class 2604 OID 16768)
-- Name: tickets id; Type: DEFAULT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_seq'::regclass);


--
-- TOC entry 2850 (class 0 OID 16750)
-- Dependencies: 204
-- Data for Name: cinemas; Type: TABLE DATA; Schema: public; Owner: cinema
--

INSERT INTO public.cinemas VALUES (1, 'I-MAX', true);
INSERT INTO public.cinemas VALUES (2, 'I-MAX', false);


--
-- TOC entry 2848 (class 0 OID 16742)
-- Dependencies: 202
-- Data for Name: places; Type: TABLE DATA; Schema: public; Owner: cinema
--

INSERT INTO public.places VALUES (1, 1, 1, 2, false);
INSERT INTO public.places VALUES (2, 1, 2, 2, false);
INSERT INTO public.places VALUES (3, 1, 3, 2, false);
INSERT INTO public.places VALUES (4, 1, 4, 2, false);
INSERT INTO public.places VALUES (5, 1, 5, 2, false);
INSERT INTO public.places VALUES (6, 1, 6, 2, false);
INSERT INTO public.places VALUES (7, 2, 1, 2, false);
INSERT INTO public.places VALUES (8, 2, 2, 2, false);
INSERT INTO public.places VALUES (9, 2, 3, 2, false);
INSERT INTO public.places VALUES (10, 2, 4, 2, false);
INSERT INTO public.places VALUES (11, 2, 5, 2, false);
INSERT INTO public.places VALUES (12, 2, 6, 2, false);
INSERT INTO public.places VALUES (13, 3, 1, 2, false);
INSERT INTO public.places VALUES (14, 3, 2, 2, false);
INSERT INTO public.places VALUES (16, 3, 4, 2, false);
INSERT INTO public.places VALUES (17, 3, 5, 2, false);
INSERT INTO public.places VALUES (18, 3, 6, 2, false);
INSERT INTO public.places VALUES (19, 4, 1, 2, false);
INSERT INTO public.places VALUES (20, 4, 2, 2, false);
INSERT INTO public.places VALUES (21, 4, 3, 2, false);
INSERT INTO public.places VALUES (22, 4, 4, 2, false);
INSERT INTO public.places VALUES (23, 4, 5, 2, false);
INSERT INTO public.places VALUES (24, 4, 6, 2, false);
INSERT INTO public.places VALUES (25, 5, 1, 2, false);
INSERT INTO public.places VALUES (26, 5, 2, 2, false);
INSERT INTO public.places VALUES (27, 5, 3, 2, false);
INSERT INTO public.places VALUES (28, 5, 4, 2, false);
INSERT INTO public.places VALUES (29, 5, 5, 2, false);
INSERT INTO public.places VALUES (30, 5, 6, 2, false);
INSERT INTO public.places VALUES (31, 6, 1, 2, false);
INSERT INTO public.places VALUES (32, 6, 2, 2, false);
INSERT INTO public.places VALUES (33, 6, 3, 2, false);
INSERT INTO public.places VALUES (34, 6, 4, 2, false);
INSERT INTO public.places VALUES (35, 6, 5, 2, false);
INSERT INTO public.places VALUES (36, 6, 6, 2, false);
INSERT INTO public.places VALUES (37, 7, 1, 2, false);
INSERT INTO public.places VALUES (38, 7, 2, 2, false);
INSERT INTO public.places VALUES (39, 7, 3, 2, false);
INSERT INTO public.places VALUES (40, 7, 4, 2, false);
INSERT INTO public.places VALUES (41, 7, 5, 2, false);
INSERT INTO public.places VALUES (42, 7, 6, 2, false);
INSERT INTO public.places VALUES (43, 8, 1, 2, false);
INSERT INTO public.places VALUES (44, 8, 2, 2, false);
INSERT INTO public.places VALUES (45, 8, 3, 2, false);
INSERT INTO public.places VALUES (46, 8, 4, 2, false);
INSERT INTO public.places VALUES (47, 8, 5, 2, false);
INSERT INTO public.places VALUES (48, 8, 6, 2, false);
INSERT INTO public.places VALUES (49, 9, 1, 2, false);
INSERT INTO public.places VALUES (50, 9, 2, 2, false);
INSERT INTO public.places VALUES (51, 9, 3, 2, false);
INSERT INTO public.places VALUES (52, 9, 4, 2, false);
INSERT INTO public.places VALUES (53, 9, 5, 2, false);
INSERT INTO public.places VALUES (54, 9, 6, 2, false);
INSERT INTO public.places VALUES (55, 10, 1, 2, false);
INSERT INTO public.places VALUES (56, 10, 2, 2, false);
INSERT INTO public.places VALUES (57, 10, 3, 2, false);
INSERT INTO public.places VALUES (58, 10, 4, 2, false);
INSERT INTO public.places VALUES (59, 10, 5, 2, false);
INSERT INTO public.places VALUES (60, 10, 6, 2, false);
INSERT INTO public.places VALUES (15, 3, 3, 2, true);
INSERT INTO public.places VALUES (61, 3, 3, 2, false);


--
-- TOC entry 2854 (class 0 OID 16769)
-- Dependencies: 208
-- Data for Name: sessions; Type: TABLE DATA; Schema: public; Owner: cinema
--

INSERT INTO public.sessions VALUES (1, 'Passengers', 2, false);
INSERT INTO public.sessions VALUES (2, 'Metro 2047', 2, false);
INSERT INTO public.sessions VALUES (3, 'Metro 2047', 2, true);


--
-- TOC entry 2852 (class 0 OID 16761)
-- Dependencies: 206
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: cinema
--

INSERT INTO public.tickets VALUES (1, '91adec9d-e549-4f9a-833e-c76b3253eaed', 1, 1, '2021-09-06 01:44:42.209054', false);
INSERT INTO public.tickets VALUES (2, '91adec9d-e549-4f9a-833e-c76b3253eaed', 2, 1, '2021-09-06 01:44:42.209054', false);
INSERT INTO public.tickets VALUES (3, '91adec9d-e549-4f9a-833e-c76b3253eaed', 3, 1, '2021-09-06 01:44:42.209054', false);
INSERT INTO public.tickets VALUES (4, '91adec9d-e549-4f9a-833e-c76b3253eaed', 4, 1, '2021-09-06 01:44:42.209054', false);
INSERT INTO public.tickets VALUES (5, '91adec9d-e549-4f9a-833e-c76b3253eaed', 5, 1, '2021-09-06 01:44:42.209054', false);


--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 205
-- Name: cinemas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: cinema
--

SELECT pg_catalog.setval('public.cinemas_id_seq', 2, true);


--
-- TOC entry 2867 (class 0 OID 0)
-- Dependencies: 203
-- Name: places_id_seq; Type: SEQUENCE SET; Schema: public; Owner: cinema
--

SELECT pg_catalog.setval('public.places_id_seq', 61, true);


--
-- TOC entry 2868 (class 0 OID 0)
-- Dependencies: 209
-- Name: sessions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: cinema
--

SELECT pg_catalog.setval('public.sessions_id_seq', 3, true);


--
-- TOC entry 2869 (class 0 OID 0)
-- Dependencies: 207
-- Name: tickets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: cinema
--

SELECT pg_catalog.setval('public.tickets_id_seq', 5, true);


--
-- TOC entry 2713 (class 2606 OID 16757)
-- Name: cinemas cinemas_pkey; Type: CONSTRAINT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.cinemas
    ADD CONSTRAINT cinemas_pkey PRIMARY KEY (id);


--
-- TOC entry 2711 (class 2606 OID 16746)
-- Name: places places_pkey; Type: CONSTRAINT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.places
    ADD CONSTRAINT places_pkey PRIMARY KEY (id);


--
-- TOC entry 2717 (class 2606 OID 16773)
-- Name: sessions sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);


--
-- TOC entry 2715 (class 2606 OID 16765)
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);


--
-- TOC entry 2718 (class 2606 OID 16777)
-- Name: places places_fk; Type: FK CONSTRAINT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.places
    ADD CONSTRAINT places_fk FOREIGN KEY (cinema_id) REFERENCES public.cinemas(id);


--
-- TOC entry 2721 (class 2606 OID 16782)
-- Name: sessions sessions_fk; Type: FK CONSTRAINT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_fk FOREIGN KEY (cinema_id) REFERENCES public.cinemas(id);


--
-- TOC entry 2719 (class 2606 OID 16787)
-- Name: tickets tickets_fk; Type: FK CONSTRAINT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_fk FOREIGN KEY (place_id) REFERENCES public.places(id);


--
-- TOC entry 2720 (class 2606 OID 16792)
-- Name: tickets tickets_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: cinema
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_fk_1 FOREIGN KEY (session_id) REFERENCES public.sessions(id);


-- Completed on 2021-09-06 02:42:44

--
-- PostgreSQL database dump complete
--

