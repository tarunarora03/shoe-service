--
-- PostgreSQL database dump
--

-- Dumped from database version 11.7
-- Dumped by pg_dump version 11.7

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

SET default_with_oids = false;

--
-- Name: shoe_brand_dtls; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shoe_brand_dtls (
    id integer NOT NULL,
    shoe_brand_name text
);


ALTER TABLE public.shoe_brand_dtls OWNER TO postgres;

--
-- Name: shoe_brand_dtls_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.shoe_brand_dtls_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shoe_brand_dtls_id_seq OWNER TO postgres;

--
-- Name: shoe_brand_dtls_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.shoe_brand_dtls_id_seq OWNED BY public.shoe_brand_dtls.id;


--
-- Name: shoe_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shoe_details (
    id integer NOT NULL,
    shoe_sub_cat_id integer,
    shoe_brand_id integer,
    shoe_size text,
    true_size_count integer,
    true_size_avg numeric
);


ALTER TABLE public.shoe_details OWNER TO postgres;

--
-- Name: shoe_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.shoe_details_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shoe_details_id_seq OWNER TO postgres;

--
-- Name: shoe_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.shoe_details_id_seq OWNED BY public.shoe_details.id;


--
-- Name: shoe_true_size_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shoe_true_size_details (
    id integer NOT NULL,
    shoe_brand_id integer,
    shoe_size text,
    true_size integer
);


ALTER TABLE public.shoe_true_size_details OWNER TO postgres;

--
-- Name: shoe_true_size_details_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.shoe_true_size_details_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shoe_true_size_details_id_seq OWNER TO postgres;

--
-- Name: shoe_true_size_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.shoe_true_size_details_id_seq OWNED BY public.shoe_true_size_details.id;


--
-- Name: shoe_brand_dtls id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shoe_brand_dtls ALTER COLUMN id SET DEFAULT nextval('public.shoe_brand_dtls_id_seq'::regclass);


--
-- Name: shoe_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shoe_details ALTER COLUMN id SET DEFAULT nextval('public.shoe_details_id_seq'::regclass);


--
-- Name: shoe_true_size_details id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shoe_true_size_details ALTER COLUMN id SET DEFAULT nextval('public.shoe_true_size_details_id_seq'::regclass);


--
-- Data for Name: shoe_brand_dtls; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.shoe_brand_dtls (id, shoe_brand_name) FROM stdin;
1	nike
2	adidas
\.


--
-- Data for Name: shoe_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.shoe_details (id, shoe_sub_cat_id, shoe_brand_id, shoe_size, true_size_count, true_size_avg) FROM stdin;
1	1	1	8	3	2
\.


--
-- Data for Name: shoe_true_size_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.shoe_true_size_details (id, shoe_brand_id, shoe_size, true_size) FROM stdin;
1	1	8	3
4	1	8	2
5	1	8	1
\.


--
-- Name: shoe_brand_dtls_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.shoe_brand_dtls_id_seq', 2, true);


--
-- Name: shoe_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.shoe_details_id_seq', 1, true);


--
-- Name: shoe_true_size_details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.shoe_true_size_details_id_seq', 5, true);


--
-- PostgreSQL database dump complete
--

